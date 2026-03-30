package br.gov.mt.sefaz.itc.model.tabelabasica.importacaoiptu;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoAtributoExterno;
import br.com.abaco.log5.anotacoes.AnotacaoColunaExterna;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoSequencia;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.log5.util.dominio.DomnTipoSequencia;
import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.model.tabelabasica.iptuprefeitura.IPTUPrefeituraVo;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnGeracaoServico;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposImportacaoIPTU;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import br.gov.mt.sefaz.itc.util.integracao.cadastro.MunicipioIntegracaoVo;

import br.gov.mt.sefaz.itc.util.integracao.gestaopessoas.ServidorSefazIntegracaoVo;

import java.io.File;

import java.util.Date;


@AnotacaoTabelaClasse
 (
     nomeTabela = TabelasITC.TABELA_IMPORTACAO_IPTU
     ,nomeAmigavel = "ITC -Importacao IPTU"
     ,identificadores =
     {
			@AnotacaoIdentificador
			(
			    nomeAtributo = "codigo"
			    ,nomeColuna = CamposImportacaoIPTU.CAMPO_CODG_IMPORTACAO_SEQC
				 ,sequencia = @AnotacaoSequencia
				 (
				     nomeSequencia = SequencesITC.SEQUENCE_IMPORTAR_ARQUIVO_IPTU
				     ,tipoSequencia = DomnTipoSequencia.SEQUENCE
				 )
			)
     }
 )

public class ImportacaoIPTUVo extends EntidadeVo<ImportacaoIPTUVo>
{      
   private static final long serialVersionUID = Long.MAX_VALUE;
   private long codgUsuarioSeq;
   private String descNomeArquivoAntigo;
   private String descNomeArquivoNovo;
   private MunicipioIntegracaoVo municipioVo;
   private DomnGeracaoServico statusImportacao;
   private java.util.Date dataHoraUpload;
   private java.util.Date dataHoraProcessamento;
   private byte[] arqvIptu;
   private File arquivoIPTU;
   private ServidorSefazIntegracaoVo servidorSefazIntegracaoVo;

   public ImportacaoIPTUVo()
   {
   }
   
   public ImportacaoIPTUVo(long codigo)
   {
      super(codigo);
   }
   
   public ImportacaoIPTUVo(ImportacaoIPTUVo importacaoIPTUVo)
   {
      super();
      setParametroConsulta(importacaoIPTUVo);
   }
   
   public void setCodgUsuarioSeq(long codgUsuarioSeq)
   {
      this.codgUsuarioSeq = codgUsuarioSeq;
   }
   @AnotacaoAtributo
   (
       nomeColuna = CamposImportacaoIPTU.CAMPO_ACSTB11_CODG_USUARIO_SEQ
       ,obrigatorio = true
   )
   public long getCodgUsuarioSeq()
   {
      return codgUsuarioSeq;
   }

   public void setDescNomeArquivoAntigo(String descNomeArquivoAntigo)
   {
      this.descNomeArquivoAntigo = descNomeArquivoAntigo;
   }
   @AnotacaoAtributo
   (
       nomeColuna = CamposImportacaoIPTU.CAMPO_DESC_NOME_ARQUIVO_ANTIGO
       ,obrigatorio = false
   )
   public String getDescNomeArquivoAntigo()
   {
      return descNomeArquivoAntigo;
   }
  
   public void setDataHoraUpload(Date dataHoraUpload)
   {
      this.dataHoraUpload = dataHoraUpload;
   }
   
   @AnotacaoAtributo(nomeColuna = CamposImportacaoIPTU.CAMPO_DATA_HORA_UPLOAD, 
   obrigatorio = false
   ,timestamp = true)
   public Date getDataHoraUpload()
   {
      return dataHoraUpload;
   }
   
   public void setDataHoraProcessamento(Date dataHoraProcessamento)
   {
      this.dataHoraProcessamento = dataHoraProcessamento;
   }
   
   @AnotacaoAtributo(nomeColuna = CamposImportacaoIPTU.CAMPO_DATA_HORA_PROCESSAMENTO, 
   obrigatorio = false
   ,timestamp = true)
   public Date getDataHoraProcessamento()
   {
      return dataHoraProcessamento;
   }

   public void setArqvIptu(byte[] arqvIptu)
   {
      this.arqvIptu = arqvIptu;
   }
   
   @AnotacaoAtributo
   (
       nomeColuna = CamposImportacaoIPTU.CAMPO_ARQV_IPTU
       ,obrigatorio = false
   )
   public byte[] getArqvIptu()
   {
      return arqvIptu;
   }

   public void setArquivoIPTU(File arquivoIPTU)
   {
      this.arquivoIPTU = arquivoIPTU;
   }

   public File getArquivoIPTU()
   {
      return arquivoIPTU;
   }

   public void setMunicipioVo(MunicipioIntegracaoVo municipioVo)
   {
      this.municipioVo = municipioVo;
   }
   @AnotacaoAtributoExterno(obrigatorio = true, colunaReferencia =
         { @AnotacaoColunaExterna(nomeColuna = CamposImportacaoIPTU.CAMPO_ACCTB09_CODG_MUNICIPIO, nomeAtributo = "codgMunicipio")
            } )
   public MunicipioIntegracaoVo getMunicipioVo()
   {
      if(!Validador.isObjetoValido(municipioVo))
      {
         municipioVo = new MunicipioIntegracaoVo();
      }
      return municipioVo;
   }

   public void setStatusImportacao(DomnGeracaoServico statusImportacao)
   {
      this.statusImportacao = statusImportacao;
   }
   @AnotacaoAtributo
   (
       nomeColuna = CamposImportacaoIPTU.CAMPO_STAT_IMPORTACAO
       ,obrigatorio = false
   )
   public DomnGeracaoServico getStatusImportacao()
   {
      return statusImportacao;
   }

   public void setDescNomeArquivoNovo(String descNomeArquivoNovo)
   {
      this.descNomeArquivoNovo = descNomeArquivoNovo;
   }
   @AnotacaoAtributo
   (
       nomeColuna = CamposImportacaoIPTU.CAMPO_DESC_NOME_ARQUIVO_NOVO
       ,obrigatorio = false
   )
   public String getDescNomeArquivoNovo()
   {
      return descNomeArquivoNovo;
   }

   public void setServidorSefazIntegracaoVo(ServidorSefazIntegracaoVo servidorSefazIntegracaoVo)
   {
      this.servidorSefazIntegracaoVo = servidorSefazIntegracaoVo;
   }

   public ServidorSefazIntegracaoVo getServidorSefazIntegracaoVo()
   {
   
      if(!Validador.isObjetoValido(servidorSefazIntegracaoVo))
      {
         servidorSefazIntegracaoVo = new ServidorSefazIntegracaoVo();
      }
      return servidorSefazIntegracaoVo;
   }
}
