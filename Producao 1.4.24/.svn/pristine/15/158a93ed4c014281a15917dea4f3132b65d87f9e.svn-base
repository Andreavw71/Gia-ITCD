package br.gov.mt.sefaz.itc.model.tabelabasica.tipoprocesso;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoAtributoExterno;
import br.com.abaco.log5.anotacoes.AnotacaoColunaExterna;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoSequencia;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.log5.util.dominio.DomnTipoSequencia;

import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcessoEprocess;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposTipoProcesso;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;
import br.gov.mt.sefaz.itc.util.integracao.eprocess.TipoProcessoIntegracaoVo;


@AnotacaoTabelaClasse(nomeTabela = TabelasITC.TABELA_TIPO_PROCESSO, 
                      nomeAmigavel = TabelasITC.NOME_AMIGAVEL_TIPO_PROCESSO, 
                      identificadores =
      { @AnotacaoIdentificador(nomeAtributo = "codigo", 
                               nomeColuna = CamposTipoProcesso.CAMPO_CODG_TIPO_PROCESSO, 
                               sequencia = 
                               @AnotacaoSequencia(nomeSequencia = SequencesITC.SEQUENCE_TIPO_PROCESSO, 
                                                  tipoSequencia = 
                                                  DomnTipoSequencia.SEQUENCE)
         )
         } )
public class TipoProcessoVo
   extends EntidadeVo<TipoProcessoVo>
{
   private static final long serialVersionUID = Long.MAX_VALUE;
   private TipoProcessoIntegracaoVo tipoProcessoIntegracaoVo;
   private String descTipoProcesso;
   private DomnTipoProcessoEprocess domnTipoEprocess;

   public TipoProcessoVo()
   {
      super();
   }
   
   public TipoProcessoVo(DomnTipoProcessoEprocess domnTipoProcessoEprocess) 
   {
      this.domnTipoEprocess = domnTipoProcessoEprocess;
   }
   
   public TipoProcessoVo(long codigo)
   {
      super(codigo);
   }

   public TipoProcessoVo(TipoProcessoVo tipoProcessoVo)
   {
      super();
      setParametroConsulta(tipoProcessoVo);
   }

   public TipoProcessoVo getPk()
   {
      return new TipoProcessoVo(getCodigo());
   }

   @AnotacaoAtributoExterno(obrigatorio = true, colunaReferencia =
         { @AnotacaoColunaExterna(nomeColuna = 
                                  CamposTipoProcesso.CAMPO_CODG_TIPO_PRCO_SEQC, 
                                  nomeAtributo = "codgTipoProcesso")
            } )
   public TipoProcessoIntegracaoVo getTipoProcessoIntegracaoVo()
   {
      if(!Validador.isObjetoValido( this.tipoProcessoIntegracaoVo))
      {
         this.tipoProcessoIntegracaoVo = new TipoProcessoIntegracaoVo();
      }
      return this.tipoProcessoIntegracaoVo;
   }

   public void setTipoProcessoIntegracaoVo(TipoProcessoIntegracaoVo tipoProcessoIntegracaoVo)
   {
      this.tipoProcessoIntegracaoVo = tipoProcessoIntegracaoVo;
   }

   @AnotacaoAtributo(nomeColuna = CamposTipoProcesso.CAMPO_DESC_TIPO_PROCESSO, 
                     obrigatorio = true)
   public String getDescTipoProcesso()
   {
      return descTipoProcesso;
   }

   public void setDescTipoProcesso(String descTipoProcesso)
   {
      this.descTipoProcesso = descTipoProcesso;
   }

   @AnotacaoAtributo(nomeColuna = CamposTipoProcesso.CAMPO_DOMN_TIPO_PROCESO, 
                     obrigatorio = true)
   public DomnTipoProcessoEprocess getDomnTipoEprocess()
   {
      if(!Validador.isDominioNumericoValido(this.domnTipoEprocess))
      {
         this.domnTipoEprocess = new DomnTipoProcessoEprocess(-1);
      }
      return domnTipoEprocess;
   }

   public void setDomnTipoEprocess(DomnTipoProcessoEprocess domnTipoEprocess)
   {
      this.domnTipoEprocess = domnTipoEprocess;
   }
}
