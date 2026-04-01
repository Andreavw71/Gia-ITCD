package br.gov.mt.sefaz.itc.model.tabelabasica.iptuprefeitura;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoAtributoExterno;
import br.com.abaco.log5.anotacoes.AnotacaoColunaExterna;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoSequencia;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.log5.util.dominio.DomnTipoSequencia;
import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.cadastro.model.municipio.MunicipioVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.importacaoiptu.ImportacaoIPTUVo;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnAtivoInativo;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.externas.CamposIPTUPrefeitura;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.MunicipioIntegracaoVo;

import java.util.Date;


@AnotacaoTabelaClasse
   (
       nomeTabela = TabelasITC.TABELA_IPTU_PREFEITURA
       ,nomeAmigavel = "ITC - Prefeitura IPTU"
       ,identificadores =
       {
           @AnotacaoIdentificador
           (
               nomeAtributo = "codigo"
               ,nomeColuna = CamposIPTUPrefeitura.CAMPO_CODG_IPTU_PREFEITURA_SEQC
               ,sequencia = @AnotacaoSequencia
               (
                   nomeSequencia = SequencesITC.SEQUENCE_IPTU_PREFEITURA
                   ,tipoSequencia = DomnTipoSequencia.SEQUENCE
               )
           )
       }
   )
public class IPTUPrefeituraVo extends EntidadeVo<IPTUPrefeituraVo>
{
   

   private static final long serialVersionUID = Long.MAX_VALUE;
   private MunicipioIntegracaoVo municipioVo;
   private String numrInscricaoImovel;
   private String statImovel;
   private Double valrVenal = 0d;
   private Double qtdeAreaTotal = 0d;
   private Double qtdeAreaConstruida = 0d;;
   private String nomeContribuinte;
   private long numrDocumento;
   private java.util.Date dataAtualizacaoBd;
   private ImportacaoIPTUVo importacaoIPTUVo;
   private DomnAtivoInativo statIPTUprefeitura;

   public IPTUPrefeituraVo()
   {
   }
   
   public IPTUPrefeituraVo(long codigo)
   {
      super(codigo);
   }
   
   public IPTUPrefeituraVo(IPTUPrefeituraVo iptuPrefeituraVo)
   {
      super();
      setParametroConsulta(iptuPrefeituraVo);
   }

   public void setMunicipioVo(MunicipioIntegracaoVo municipioVo)
   {
      this.municipioVo = municipioVo;
   }
   
   @AnotacaoAtributoExterno(obrigatorio = true, colunaReferencia =
         { @AnotacaoColunaExterna(nomeColuna = CamposIPTUPrefeitura.CAMPO_ACCTB09_CODG_MUNICIPIO, nomeAtributo = "codgMunicipio")
            } )
   public MunicipioIntegracaoVo getMunicipioVo()
   {
      if(!Validador.isObjetoValido(municipioVo))
      {
         municipioVo = new MunicipioIntegracaoVo();
      }   
      return municipioVo;
   }
    
   public void setNumrInscricaoImovel(String numrInscricaoImovel)
   {
      this.numrInscricaoImovel = numrInscricaoImovel;
   }
   @AnotacaoAtributo
   (
       nomeColuna = CamposIPTUPrefeitura.CAMPO_NUMR_INSCRICAO_IMOVEL
       ,obrigatorio = false
   )
   public String getNumrInscricaoImovel()
   {
      return numrInscricaoImovel;
   }

   public void setStatImovel(String statImovel)
   {
      this.statImovel = statImovel;
   }
   @AnotacaoAtributo
   (
       nomeColuna = CamposIPTUPrefeitura.CAMPO_DESC_STAT_IMOVEL
       ,obrigatorio = false
   )
   public String getStatImovel()
   {
      return statImovel;
   }

   public void setValrVenal(Double valrVenal)
   {
      this.valrVenal = valrVenal;
   }
   @AnotacaoAtributo
   (
       nomeColuna = CamposIPTUPrefeitura.CAMPO_VALR_VENAL
       ,obrigatorio = false
   )
   public Double getValrVenal()
   {
      return valrVenal;
   }

   public void setQtdeAreaTotal(Double qtdeAreaTotal)
   {
      this.qtdeAreaTotal = qtdeAreaTotal;
   }
   @AnotacaoAtributo
   (
       nomeColuna = CamposIPTUPrefeitura.CAMPO_QTDE_AREA_TOTAL
       ,obrigatorio = false
   )
   public Double getQtdeAreaTotal()
   {
      return qtdeAreaTotal;
   }

   public void setQtdeAreaConstruida(Double qtdeAreaConstruida)
   {
      this.qtdeAreaConstruida = qtdeAreaConstruida;
   }
   @AnotacaoAtributo
   (
       nomeColuna = CamposIPTUPrefeitura.CAMPO_QTDE_AREA_CONSTRUIDA
       ,obrigatorio = false
   )
   public Double getQtdeAreaConstruida()
   {
      return qtdeAreaConstruida;
   }

   public void setNomeContribuinte(String nomeContribuinte)
   {
      this.nomeContribuinte = nomeContribuinte;
   }
    @AnotacaoAtributo
   (
       nomeColuna = CamposIPTUPrefeitura.CAMPO_NOME_CONTRIBUINTE
       ,obrigatorio = false
   )
   public String getNomeContribuinte()
   {
      return nomeContribuinte;
   }
   
   public void setNumrDocumento(long numrDocumento)
   {
      this.numrDocumento = numrDocumento;
   }
   @AnotacaoAtributo
   (
      nomeColuna = CamposIPTUPrefeitura.CAMPO_NUMR_DOCUMENTO
      ,obrigatorio = false
   )
   public long getNumrDocumento()
   {
      return numrDocumento;
   }

   public void setDataAtualizacaoBd(Date dataAtualizacaoBd)
   {
      this.dataAtualizacaoBd = dataAtualizacaoBd;
   }
   @AnotacaoAtributo(nomeColuna = CamposIPTUPrefeitura.CAMPO_DATA_ATUALIZACAO_BD,
   obrigatorio = false
   ,timestamp = true)
   public Date getDataAtualizacaoBd()
   {
      return dataAtualizacaoBd;
   }
   
   public void setImportacaoIPTUVo(ImportacaoIPTUVo importacaoIPTUVo)
   {
      this.importacaoIPTUVo = importacaoIPTUVo;
   }
   
   @AnotacaoAtributoExterno(obrigatorio = false, colunaReferencia =
         { @AnotacaoColunaExterna(nomeColuna = CamposIPTUPrefeitura.CAMPO_ITCTB56_CODG_IMPORTACAO_SEQC, nomeAtributo = "codigo")
            } )
   public ImportacaoIPTUVo getImportacaoIPTUVo()
   {
      if(!Validador.isObjetoValido(importacaoIPTUVo))
      {
         importacaoIPTUVo = new ImportacaoIPTUVo();
      }
      return importacaoIPTUVo;
   }


   public void setStatIPTUprefeitura(DomnAtivoInativo statIPTUprefeitura)
   {
      this.statIPTUprefeitura = statIPTUprefeitura;
   }
    
   @AnotacaoAtributo(nomeColuna = CamposIPTUPrefeitura.CAMPO_STAT_IPTU_PREFEITURA,
   obrigatorio = false)
   public DomnAtivoInativo getStatIPTUprefeitura()
   {
      return statIPTUprefeitura;
   }
   
   /**
    * Reorna o Valor Percentual Estimado
    * @return
    * @implemented by Dherkyan Ribeiro
    */
   public String getValorVenalFormatado()
   {
      final double valrVenal = getValrVenal();
      if (!Validador.isNumericoValido(valrVenal))
      {
         return STRING_VAZIA;
      }
      return StringUtil.doubleToMonetario(valrVenal, 4);
   }
   
}
