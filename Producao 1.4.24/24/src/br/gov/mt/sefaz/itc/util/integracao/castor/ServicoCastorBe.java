package br.gov.mt.sefaz.itc.util.integracao.castor;

import br.gov.mt.sefaz.cas.integracao.IntegracaoCas;
import br.gov.mt.sefaz.cas.integracao.LifepointVo;
import br.gov.mt.sefaz.cas.integracao.ObjetoVo;
import br.gov.mt.sefaz.cas.integracao.PoliticaVo;
import br.gov.mt.sefaz.generico.util.sql.AbstractBe;

import br.gov.mt.sefaz.itc.util.facade.ConfiguracaoITCD;
import br.gov.mt.sefaz.itc.util.log.ExibirLOG;

import java.io.File;
import java.io.IOException;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.Date;

import org.apache.commons.httpclient.Header;

/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: ServicoCastorBe.java
 * Revisão: Cristiano Aparecido de Oliveira
 * Data criação : 28/07/2016
 * Data ultima revisão : 24/06/2016
 */
public class ServicoCastorBe extends AbstractBe
{
   public ServicoCastorBe() throws SQLException
   { 
      super();  
   }
   
   public ServicoCastorBe(Connection conexao) throws SQLException
   {
      super(conexao);
   
   }
   
   
   public ObjetoVo gravar(File arquivo, String nomeTabela) throws SQLException, IOException
   {
      IntegracaoCas cas = null;
      ObjetoVo objetoVo = null;
      LifepointVo lifePointVo = null;
      try
      {
         cas = new IntegracaoCas(conn);

         //alterar politica do castor     
         // Configura a política dos arquivos a serem gravados.
         PoliticaVo politicaVo = new PoliticaVo();

         lifePointVo = new LifepointVo();
         // Indica se o item pode ser excluído após ter expirado
         lifePointVo.setDeletable(false);
         // Quantidade mínima de réplicas
         lifePointVo.setMinReplicas(2);
         // Quantidade máxima de réplicas
         lifePointVo.setMaxReplicas(6);

         politicaVo.addLifepoint(lifePointVo);

         cas.setPoliticaVo(politicaVo);

         // Dados do objeto a ser persistido no banco de dados.
         objetoVo = new ObjetoVo();
         objetoVo.setDataCriacao(new Date());
         objetoVo.setSiglSistema(ConfiguracaoITCD.SIGLA_ITC);
         objetoVo.setNomeTabela(nomeTabela);
         objetoVo.setNomeArquivo(arquivo.getCanonicalFile().getName());
         objetoVo.setDescObjeto(arquivo.getCanonicalFile().getName());
         objetoVo.setTamanhoBytes(arquivo.length());

         // Define os cabeçalhos do arquivo.
         //Header h1 = new Header("Content-Type", "application/vnd.ms-excel");
         Header h1 = new Header("Content-Type", "application/zip");
         Header h2 =  new Header("Content-Disposition", "attachment; filename=\"" + arquivo.getCanonicalFile().getName() + "\"");
         Header[] headers =
         { h1, h2 };

         // Escreve o arquivo no CAS.
         ExibirLOG.exibirLog("Enviando arquivo pro servidor Castor ...");
         objetoVo = cas.gravar(objetoVo, headers, arquivo);
         ExibirLOG.exibirLog("ID do arquivo no Castor: " + objetoVo.getNumrObjetoSeqc());
      }
      finally
      {
         if (cas != null)
         {
            cas.close();
         }
      }
      return objetoVo;
   }


}
