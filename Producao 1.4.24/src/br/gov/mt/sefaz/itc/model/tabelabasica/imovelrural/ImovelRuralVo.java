package br.gov.mt.sefaz.itc.model.tabelabasica.imovelrural;

import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoSequencia;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;

import br.com.abaco.log5.util.dominio.DomnTipoSequencia;

import br.gov.mt.sefaz.itc.util.EntidadeVo;

import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;


import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposFichaImovelRural;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;

import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.math.BigDecimal;

@AnotacaoTabelaClasse(nomeTabela = TabelasITC.TABELA_FICHA_IMOVEL_RURAL , nomeAmigavel = 
      TabelasITC.TABELA_FICHA_IMOVEL_RURAL, identificadores =
      { @AnotacaoIdentificador(nomeAtributo = "codigo", nomeColuna = CamposFichaImovelRural.CAMPO_CODIGO_IMOVEL_RURAL, sequencia = 
               @AnotacaoSequencia(nomeSequencia = SequencesITC.SEQUENCE_FICHA_IMOVEL_RURAL, tipoSequencia = DomnTipoSequencia.SEQUENCE)
         )
         } )
public class ImovelRuralVo extends EntidadeVo<ImovelRuralVo>
{
   private static final long serialVersionUID = Long.MAX_VALUE;
   
   private Long codgImovelRural;
   private Long codgEndereco;
   private Long codgItcdBemTrbt;
   private String descDenominacao;
   private BigDecimal qtdeDistancia;
   private BigDecimal areaTotal;
   private Long numrIndea;
   private Long codgReceitaFederal;
   private Integer sitcPastagem;
   private BigDecimal areaPastagem;
   private BigDecimal valrPastagem;
   private DomnSimNao sitcAcessaoNatural;
   private BigDecimal valrAcessaoNatural;
   private BigDecimal valrMercadoImovel;
   private BigDecimal valrMaquinaEquipamento;
   private BigDecimal valrOutro;
   private BigDecimal valrItr;
   private BigDecimal qtdeDistanciaAsfalto;
   private Long numrSeqcBaseCalc;
   private Integer numrSeqcCritMunc;


   public ImovelRuralVo() 
   {
      super();
   }
   
   public ImovelRuralVo(long codigo) 
   {
      super(codigo);
   }
   
   public ImovelRuralVo(ImovelRuralVo imovelRuralVo) 
   {
      super();
      setParametroConsulta(imovelRuralVo);
   }
   
   public ImovelRuralPk getPkPk() 
   {
      return new ImovelRuralPk(getCodigo());
   }
   
   public long getCodigo() 
   {
      return super.getCodigo();
   }

   public void setCodgImovelRural(Long codgImovelRural)
   {
      this.codgImovelRural = codgImovelRural;
   }

   public Long getCodgImovelRural()
   {
      return codgImovelRural;
   }

   public void setCodgEndereco(Long codgEndereco)
   {
      this.codgEndereco = codgEndereco;
   }

   public Long getCodgEndereco()
   {
      return codgEndereco;
   }

   public void setCodgItcdBemTrbt(Long codgItcdBemTrbt)
   {
      this.codgItcdBemTrbt = codgItcdBemTrbt;
   }

   public Long getCodgItcdBemTrbt()
   {
      return codgItcdBemTrbt;
   }

   public void setDescDenominacao(String descDenominacao)
   {
      this.descDenominacao = descDenominacao;
   }

   public String getDescDenominacao()
   {
      return descDenominacao;
   }

   public void setQtdeDistancia(BigDecimal qtdeDistancia)
   {
      this.qtdeDistancia = qtdeDistancia;
   }

   public BigDecimal getQtdeDistancia()
   {
      return qtdeDistancia;
   }

   public void setAreaTotal(BigDecimal areaTotal)
   {
      this.areaTotal = areaTotal;
   }

   public BigDecimal getAreaTotal()
   {
      return areaTotal;
   }

   public void setNumrIndea(Long numrIndea)
   {
      this.numrIndea = numrIndea;
   }

   public Long getNumrIndea()
   {
      return numrIndea;
   }

   public void setCodgReceitaFederal(Long codgReceitaFederal)
   {
      this.codgReceitaFederal = codgReceitaFederal;
   }

   public Long getCodgReceitaFederal()
   {
      return codgReceitaFederal;
   }

   public void setSitcPastagem(Integer sitcPastagem)
   {
      this.sitcPastagem = sitcPastagem;
   }

   public Integer getSitcPastagem()
   {
      return sitcPastagem;
   }

   public void setAreaPastagem(BigDecimal areaPastagem)
   {
      this.areaPastagem = areaPastagem;
   }

   public BigDecimal getAreaPastagem()
   {
      return areaPastagem;
   }

   public void setValrPastagem(BigDecimal valrPastagem)
   {
      this.valrPastagem = valrPastagem;
   }

   public BigDecimal getValrPastagem()
   {
      return valrPastagem;
   }

   public void setSitcAcessaoNatural(DomnSimNao sitcAcessaoNatural)
   {
      this.sitcAcessaoNatural = sitcAcessaoNatural;
   }

   public DomnSimNao getSitcAcessaoNatural()
   {
      return sitcAcessaoNatural;
   }

   public void setValrAcessaoNatural(BigDecimal valrAcessaoNatural)
   {
      this.valrAcessaoNatural = valrAcessaoNatural;
   }

   public BigDecimal getValrAcessaoNatural()
   {
      return valrAcessaoNatural;
   }

   public void setValrMercadoImovel(BigDecimal valrMercadoImovel)
   {
      this.valrMercadoImovel = valrMercadoImovel;
   }

   public BigDecimal getValrMercadoImovel()
   {
      return valrMercadoImovel;
   }

   public void setValrMaquinaEquipamento(BigDecimal valrMaquinaEquipamento)
   {
      this.valrMaquinaEquipamento = valrMaquinaEquipamento;
   }

   public BigDecimal getValrMaquinaEquipamento()
   {
      return valrMaquinaEquipamento;
   }

   public void setValrOutro(BigDecimal valrOutro)
   {
      this.valrOutro = valrOutro;
   }

   public BigDecimal getValrOutro()
   {
      return valrOutro;
   }

   public void setValrItr(BigDecimal valrItr)
   {
      this.valrItr = valrItr;
   }

   public BigDecimal getValrItr()
   {
      return valrItr;
   }

   public void setQtdeDistanciaAsfalto(BigDecimal qtdeDistanciaAsfalto)
   {
      this.qtdeDistanciaAsfalto = qtdeDistanciaAsfalto;
   }

   public BigDecimal getQtdeDistanciaAsfalto()
   {
      return qtdeDistanciaAsfalto;
   }

   public void setNumrSeqcBaseCalc(Long numrSeqcBaseCalc)
   {
      this.numrSeqcBaseCalc = numrSeqcBaseCalc;
   }

   public Long getNumrSeqcBaseCalc()
   {
      return numrSeqcBaseCalc;
   }

   public void setNumrSeqcCritMunc(Integer numrSeqcCritMunc)
   {
      this.numrSeqcCritMunc = numrSeqcCritMunc;
   }

   public Integer getNumrSeqcCritMunc()
   {
      return numrSeqcCritMunc;
   }

}
