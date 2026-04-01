 /**
  * Ábaco Tecnologia de Informação - LTDA
  * Arquivo: IPTUVo.java
  * Revisão: Dherkyan Ribeiro da Silva
  * Data criação : 24/11/2015
  * Data ultima revisão : 24/11/2015
  */
package br.gov.mt.sefaz.itc.util.integracao.lpm;

import br.com.abaco.util.exceptions.IntegracaoErro;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.lpm.integracao.IntegracaoLpm;
import br.gov.mt.sefaz.lpm.model.produtoncm.ProdutoNcmVo;

import java.sql.Connection;
import java.sql.SQLException;

public class ProdutoNcmBe extends AbstractBe
{
   /**
    * Construtor público padrão
    * @throws SQLException
    */
   public ProdutoNcmBe() throws SQLException
   {
      super();
   }

   /**
    * Construtor público padrão
    * @throws SQLException
    */
   public ProdutoNcmBe(Connection con) throws SQLException
   {
      super(con);
   }

   /**
    * <b>Objetivo:</b> Este método tem por objetivo listar ProdutoNcm e valor
    * <br>
    * Com:<br>
    * ACGTB93_CODG_CAPITULO = 1
    * <br>
    * ACGTB95_PRODUTO_NCM.UNIDADE_MEDIDA = 'CB'
    * @param produtoNcmVo
    * @throws SQLException
    */
   public ProdutoNcmIntegracaoVo listarProdutoNcmAnimaisVivosPorUnidadeDeMedida(ProdutoNcmIntegracaoVo produtoNcmIntegracaoVo) throws ParametroObrigatorioException, 
         IntegracaoException
   {
      ProdutoNcmVo produtoNcmVo = new ProdutoNcmVo();
      try
      {
         new IntegracaoLpm(conn).listarProdutoNcmAnimaisVivosPorUnidadeDeMedida(produtoNcmVo);
         produtoNcmIntegracaoVo.setProdutoNcmVo(produtoNcmVo);
         return produtoNcmIntegracaoVo;

      }
      catch (Exception e)
      {
         throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_LPM, e);
      }
      catch (Error e)
      {
         throw IntegracaoErro.getInstance(IntegracaoErro.OPERACAO_CONSULTAR, IntegracaoErro.NOME_SISTEMA_INTEGRACAO_LPM, e);
      }
   }
}
