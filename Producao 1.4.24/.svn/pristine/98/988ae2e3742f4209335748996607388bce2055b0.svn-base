package br.gov.mt.sefaz.itc.model.tabelabasica.imovelrural;

import br.com.abaco.log5.util.facade.MensagemErro;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;

import java.sql.Connection;
import java.sql.SQLException;

public class ImovelRuralBe extends AbstractBe
{
   public ImovelRuralBe() throws SQLException
   {
      super();
   }
   
   public ImovelRuralBe(Connection conexao) throws SQLException
   {
      super(conexao);
   }
   
   private void validaParametrosIncluirImovelRural(final ImovelRuralVo imovelRuralVo) throws ObjetoObrigatorioException, ParametroObrigatorioException 
   {
      Validador.validaObjeto(imovelRuralVo);
      
      //  acctb06_codg_endereco       NUMBER(11) not null,
      if(imovelRuralVo.getCodgEndereco() == null)
         throw new ParametroObrigatorioException(MensagemErro.PARAMETRO_OBRIGATORIO);
      
      
      //  itctb18_codg_itcd_bem_trbt  NUMBER(5) not null,
      if(imovelRuralVo.getCodgItcdBemTrbt() == null)
         throw new ParametroObrigatorioException(MensagemErro.PARAMETRO_OBRIGATORIO);
      
      //  qtde_distancia              NUMBER(12,4) not null,
      if(imovelRuralVo.getQtdeDistancia() == null)
         throw new ParametroObrigatorioException(MensagemErro.PARAMETRO_OBRIGATORIO);
      
      //  area_total                  NUMBER(12,4) not null,
      if(imovelRuralVo.getAreaTotal() == null)
         throw new ParametroObrigatorioException(MensagemErro.PARAMETRO_OBRIGATORIO);

      //  sitc_pastagem               NUMBER(5) not null,
      if(imovelRuralVo.getSitcPastagem() == null)
         throw new ParametroObrigatorioException(MensagemErro.PARAMETRO_OBRIGATORIO);      

      //  sitc_acessao_natural        NUMBER(5) not null,
      if(imovelRuralVo.getSitcAcessaoNatural() == null)
         throw new ParametroObrigatorioException(MensagemErro.PARAMETRO_OBRIGATORIO);      

      //  valr_mercado_imovel         NUMBER(11,2) not null,
      if(imovelRuralVo.getValrMercadoImovel() == null)
          throw new ParametroObrigatorioException(MensagemErro.PARAMETRO_OBRIGATORIO);      

   }
}
