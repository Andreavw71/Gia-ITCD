package br.gov.mt.sefaz.itc.model.tabelabasica.imovelrural;

import br.com.abaco.log5.util.excecoes.IncluiException;

import br.com.abaco.util.Validador;

import br.com.abaco.util.exceptions.CodigoTransacaoInvalidoException;
import br.com.abaco.util.exceptions.NumeroParticaoInvalidoException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposFichaImovelRural;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;

import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.Types;

import sefaz.mt.log.GeradorLogSefazMT;
import sefaz.mt.log.util.dominios.DomnOperacao;
import sefaz.mt.log.util.excecao.LogSefazException;
import sefaz.mt.util.AlteraException;
import sefaz.mt.util.SefazSequencia;
import sefaz.mt.util.UtilStmt;

public class ImovelRuralDao extends AbstractDao implements TabelasITC, SequencesITC, CamposFichaImovelRural
{
   public ImovelRuralDao(Connection conexao) throws SQLException 
   {
      super(conexao);
      utilStmt = new UtilStmt(TABELA_FICHA_IMOVEL_RURAL);
   }
   
   public String [] getCamposImovelRural() 
   {
      return new String []
      {
         CAMPO_CODIGO_IMOVEL_RURAL,
         CAMPO_ACCTB06_CODIGO_ENDERECO,
         CAMPO_ITCTB18_CODIGO_ITCD_BEM_TRIBUTARIO,
         CAMPO_DESCRICAO_DENOMINACAO,
         CAMPO_QUANTIDADE_DISTANCIA,
         CAMPO_AREA_TOTAL,
         CAMPO_NUMERICO_INDEA,
         CAMPO_CODIGO_RECEITA_FEDERAL,
         CAMPO_SITUACAO_PASTAGEM,
         CAMPO_AREA_PASTAGEM,
         CAMPO_VALOR_PASTAGEM,
         CAMPO_SITUACAO_ACESSAO_NATURAL,
         CAMPO_VALOR_ACESSAO_NATURAL,
         CAMPO_VALOR_MERCADO_IMOVEL,
         CAMPO_VALOR_MAQUINA_EQUIPAMENTO,
         CAMPO_VALOR_OUTRO,
         CAMPO_VALOR_ITR,
         CAMPO_QTDE_DISTANCIA_ASFALTO,
         CAMPO_NUMR_SEQC_BASE_CALC,
         CAMPO_NUMR_SEQC_CRIT_MUNC
      };
   }
   
   private void preparedStatementInsertImovelRural(final PreparedStatement ps, final ImovelRuralVo imovelRuralVo)  throws ObjetoObrigatorioException, 
			  SQLException
   {
      Validador.validaObjeto(ps);
      Validador.validaObjeto(imovelRuralVo);
      int contador = 0;
      
      //codg_imovel_rural           NUMBER(10) not null,
      if (Validador.isNumericoValido(imovelRuralVo.getCodigo())) 
      {
         ps.setLong(++contador, imovelRuralVo.getCodigo());    
      }
     
      
      //acctb06_codg_endereco       NUMBER(11) not null,
      if (Validador.isNumericoValido(imovelRuralVo.getCodgEndereco())) 
      {
         ps.setLong(++contador, imovelRuralVo.getCodgEndereco());    
      }
     
      //itctb18_codg_itcd_bem_trbt  NUMBER(5) not null,
      if (Validador.isNumericoValido(imovelRuralVo.getCodgItcdBemTrbt())) 
      {
         ps.setLong(++contador, imovelRuralVo.getCodgItcdBemTrbt());    
      }
    
      //desc_denominacao            VARCHAR2(50) not null,
      if (Validador.isStringValida(imovelRuralVo.getDescDenominacao())) 
      {
         ps.setString(++contador, imovelRuralVo.getDescDenominacao());
      }
    
      
      //qtde_distancia              NUMBER(12,4) not null,
      if (Validador.isNumericoValido(imovelRuralVo.getQtdeDistancia().doubleValue()))  
      {
         ps.setDouble(++contador, imovelRuralVo.getQtdeDistancia().doubleValue());  
      }
     
      
      //area_total                  NUMBER(12,4) not null,
      if (Validador.isNumericoValido(imovelRuralVo.getAreaTotal().doubleValue())) 
      {
         ps.setDouble(++contador, imovelRuralVo.getAreaTotal().doubleValue());
      }
    
      
      //numr_indea                  NUMBER(10),
      if (Validador.isNumericoValido(imovelRuralVo.getNumrIndea()))  
      {
         ps.setLong(++contador, imovelRuralVo.getNumrIndea());
      }
      else 
      {
         ps.setNull(++contador, Types.NUMERIC);
      }
      
      //codg_receita_federal        NUMBER(10),
      if (Validador.isNumericoValido(imovelRuralVo.getCodgReceitaFederal()))  
      {
         ps.setLong(++contador, imovelRuralVo.getNumrIndea());
      }
      else 
      {
         ps.setNull(++contador, Types.NUMERIC);
      }
      
      //sitc_pastagem               NUMBER(5) not null,
      if (Validador.isNumericoValido(imovelRuralVo.getSitcPastagem()))  
      {
         ps.setLong(++contador, imovelRuralVo.getSitcPastagem());
      }      
     
      //area_pastagem               NUMBER(12,4),
      if (Validador.isNumericoValido(imovelRuralVo.getAreaPastagem().doubleValue()))  
      {
         ps.setDouble(++contador, imovelRuralVo.getAreaPastagem().doubleValue());
      }
      else 
      {
         ps.setNull(++contador, Types.NUMERIC);
      }      
      
      //valr_pastagem               NUMBER(11,2),
      if (Validador.isNumericoValido(imovelRuralVo.getValrPastagem().doubleValue()))  
      {
         ps.setDouble(++contador, imovelRuralVo.getValrPastagem().doubleValue());
      }
      else 
      {
         ps.setNull(++contador, Types.NUMERIC);
      }        
      
      //sitc_acessao_natural        NUMBER(5) not null,
      if (Validador.isNumericoValido(imovelRuralVo.getSitcAcessaoNatural().getKey())) 
      {
         ps.setInt(++contador, imovelRuralVo.getSitcAcessaoNatural().getKey());
      }
   
      //valr_acessao_natural        NUMBER(11,2),
      if (Validador.isNumericoValido(imovelRuralVo.getValrAcessaoNatural().doubleValue()))   
      {
         ps.setDouble(++contador, imovelRuralVo.getValrAcessaoNatural().doubleValue());
      }
      else 
      {
         ps.setNull(++contador, Types.NUMERIC);
      }
      
      //valr_mercado_imovel         NUMBER(11,2) not null,
      if (Validador.isNumericoValido(imovelRuralVo.getValrAcessaoNatural().doubleValue()))  
      {
         ps.setDouble(++contador, imovelRuralVo.getValrAcessaoNatural().doubleValue());
      }
      
      
      //valr_maquina_equipamento    NUMBER(11,2),
      if (Validador.isNumericoValido(imovelRuralVo.getValrMaquinaEquipamento().doubleValue()))   
      {
         ps.setDouble(++contador, imovelRuralVo.getValrMaquinaEquipamento().doubleValue());
      }
      else 
      {
         ps.setNull(++contador, Types.NUMERIC);
      }
      
      //valr_outro                  NUMBER(11,2),
      if (Validador.isNumericoValido(imovelRuralVo.getValrOutro().doubleValue()))   
      {
         ps.setDouble(++contador, imovelRuralVo.getValrOutro().doubleValue());
      }
      else 
      {
         ps.setNull(++contador, Types.NUMERIC);
      }
             
      //valr_itr                    NUMBER(11,2),
      if (Validador.isNumericoValido(imovelRuralVo.getValrItr().doubleValue()))   
      {
         ps.setDouble(++contador, imovelRuralVo.getValrItr().doubleValue());
      }
      else 
      {
         ps.setNull(++contador, Types.NUMERIC);
      }      
      
      //qtde_distancia_asfalto      NUMBER(12,4),
      if (Validador.isNumericoValido(imovelRuralVo.getQtdeDistanciaAsfalto().doubleValue()))   
      {
         ps.setDouble(++contador, imovelRuralVo.getQtdeDistanciaAsfalto().doubleValue());
      }
      else 
      {
         ps.setNull(++contador, Types.NUMERIC);
      }        
      
      //itctb52_numr_seqc_base_calc NUMBER(10)
      if (Validador.isNumericoValido(imovelRuralVo.getNumrSeqcBaseCalc()))   
      {
         ps.setLong(++contador, imovelRuralVo.getNumrSeqcBaseCalc());
      }
      
      //itctb53_numr_seqc_crit_munc NUMBER(5)
      if (Validador.isNumericoValido(imovelRuralVo.getNumrSeqcCritMunc()))   
      {
         ps.setLong(++contador, imovelRuralVo.getNumrSeqcBaseCalc());
      }
   }
   
   private void preparedStatementUpdateImovelRural(final PreparedStatement ps, final ImovelRuralVo imovelRuralVo)  throws ObjetoObrigatorioException, 
           SQLException
   {
      Validador.validaObjeto(ps);
      Validador.validaObjeto(imovelRuralVo);
      int contador = 0;
      
      //acctb06_codg_endereco       NUMBER(11) not null,
      if (Validador.isNumericoValido(imovelRuralVo.getCodgEndereco())) 
      {
         ps.setLong(++contador, imovelRuralVo.getCodgEndereco());    
      }
     
      //itctb18_codg_itcd_bem_trbt  NUMBER(5) not null,
      if (Validador.isNumericoValido(imovelRuralVo.getCodgItcdBemTrbt())) 
      {
         ps.setLong(++contador, imovelRuralVo.getCodgItcdBemTrbt());    
      }
    
      //desc_denominacao            VARCHAR2(50) not null,
      if (Validador.isStringValida(imovelRuralVo.getDescDenominacao())) 
      {
         ps.setString(++contador, imovelRuralVo.getDescDenominacao());
      }
    
      
      //qtde_distancia              NUMBER(12,4) not null,
      if (Validador.isNumericoValido(imovelRuralVo.getQtdeDistancia().doubleValue()))  
      {
         ps.setDouble(++contador, imovelRuralVo.getQtdeDistancia().doubleValue());  
      }
     
      
      //area_total                  NUMBER(12,4) not null,
      if (Validador.isNumericoValido(imovelRuralVo.getAreaTotal().doubleValue())) 
      {
         ps.setDouble(++contador, imovelRuralVo.getAreaTotal().doubleValue());
      }
    
      
      //numr_indea                  NUMBER(10),
      if (Validador.isNumericoValido(imovelRuralVo.getNumrIndea()))  
      {
         ps.setLong(++contador, imovelRuralVo.getNumrIndea());
      }
      else 
      {
         ps.setNull(++contador, Types.NUMERIC);
      }
      
      //codg_receita_federal        NUMBER(10),
      if (Validador.isNumericoValido(imovelRuralVo.getCodgReceitaFederal()))  
      {
         ps.setLong(++contador, imovelRuralVo.getNumrIndea());
      }
      else 
      {
         ps.setNull(++contador, Types.NUMERIC);
      }
      
      //sitc_pastagem               NUMBER(5) not null,
      if (Validador.isNumericoValido(imovelRuralVo.getSitcPastagem()))  
      {
         ps.setLong(++contador, imovelRuralVo.getSitcPastagem());
      }      
     
      //area_pastagem               NUMBER(12,4),
      if (Validador.isNumericoValido(imovelRuralVo.getAreaPastagem().doubleValue()))  
      {
         ps.setDouble(++contador, imovelRuralVo.getAreaPastagem().doubleValue());
      }
      else 
      {
         ps.setNull(++contador, Types.NUMERIC);
      }      
      
      //valr_pastagem               NUMBER(11,2),
      if (Validador.isNumericoValido(imovelRuralVo.getValrPastagem().doubleValue()))  
      {
         ps.setDouble(++contador, imovelRuralVo.getValrPastagem().doubleValue());
      }
      else 
      {
         ps.setNull(++contador, Types.NUMERIC);
      }        
      
      //sitc_acessao_natural        NUMBER(5) not null,
      if (Validador.isNumericoValido(imovelRuralVo.getSitcAcessaoNatural().getKey())) 
      {
         ps.setInt(++contador, imovelRuralVo.getSitcAcessaoNatural().getKey());
      }
   
      //valr_acessao_natural        NUMBER(11,2),
      if (Validador.isNumericoValido(imovelRuralVo.getValrAcessaoNatural().doubleValue()))   
      {
         ps.setDouble(++contador, imovelRuralVo.getValrAcessaoNatural().doubleValue());
      }
      else 
      {
         ps.setNull(++contador, Types.NUMERIC);
      }
      
      //valr_mercado_imovel         NUMBER(11,2) not null,
      if (Validador.isNumericoValido(imovelRuralVo.getValrAcessaoNatural().doubleValue()))  
      {
         ps.setDouble(++contador, imovelRuralVo.getValrAcessaoNatural().doubleValue());
      }
      
      
      //valr_maquina_equipamento    NUMBER(11,2),
      if (Validador.isNumericoValido(imovelRuralVo.getValrMaquinaEquipamento().doubleValue()))   
      {
         ps.setDouble(++contador, imovelRuralVo.getValrMaquinaEquipamento().doubleValue());
      }
      else 
      {
         ps.setNull(++contador, Types.NUMERIC);
      }
      
      //valr_outro                  NUMBER(11,2),
      if (Validador.isNumericoValido(imovelRuralVo.getValrOutro().doubleValue()))   
      {
         ps.setDouble(++contador, imovelRuralVo.getValrOutro().doubleValue());
      }
      else 
      {
         ps.setNull(++contador, Types.NUMERIC);
      }
             
      //valr_itr                    NUMBER(11,2),
      if (Validador.isNumericoValido(imovelRuralVo.getValrItr().doubleValue()))   
      {
         ps.setDouble(++contador, imovelRuralVo.getValrItr().doubleValue());
      }
      else 
      {
         ps.setNull(++contador, Types.NUMERIC);
      }      
      
      //qtde_distancia_asfalto      NUMBER(12,4),
      if (Validador.isNumericoValido(imovelRuralVo.getQtdeDistanciaAsfalto().doubleValue()))   
      {
         ps.setDouble(++contador, imovelRuralVo.getQtdeDistanciaAsfalto().doubleValue());
      }
      else 
      {
         ps.setNull(++contador, Types.NUMERIC);
      }        
      
      //itctb52_numr_seqc_base_calc NUMBER(10)
      if (Validador.isNumericoValido(imovelRuralVo.getNumrSeqcBaseCalc()))   
      {
         ps.setLong(++contador, imovelRuralVo.getNumrSeqcBaseCalc());
      }
      
      //itctb53_numr_seqc_crit_munc NUMBER(5)
      if (Validador.isNumericoValido(imovelRuralVo.getNumrSeqcCritMunc()))   
      {
         ps.setLong(++contador, imovelRuralVo.getNumrSeqcBaseCalc());
      }
      
      //codg_imovel_rural           NUMBER(10) not null,
      if (Validador.isNumericoValido(imovelRuralVo.getCodigo())) 
      {
         ps.setLong(++contador, imovelRuralVo.getCodigo());    
      }
      
   }   

   public void insertImovelRural(final ImovelRuralVo imovelRuralVo) throws ObjetoObrigatorioException, IncluiException, 
             CodigoTransacaoInvalidoException
   {
      PreparedStatement ps = null;
      String sql = utilStmt.geraInsr(getCamposImovelRural());
      Validador.validaObjeto(imovelRuralVo);
      
      try
      {
         SefazSequencia sequence = new SefazSequencia(conn);
         imovelRuralVo.setCodigo(sequence.next(SEQUENCE_FICHA_IMOVEL_RURAL));
         GeradorLogSefazMT.gerar(imovelRuralVo, DomnOperacao.OPERACAO_INSERT, imovelRuralVo.getNumeroParticao(), 
         imovelRuralVo.getCodigoTransacao(), imovelRuralVo.getLogSefazVo().getUsuario().getCodigo(), conn);
         ps = conn.prepareStatement(sql);
         preparedStatementInsertImovelRural(ps, imovelRuralVo);
         
         if (ps.executeUpdate() != 1)
         {
            throw new SQLException();
         }
      }
      catch (SQLException e) 
      {
         e.printStackTrace();
         throw new IncluiException(MensagemErro.INCLUIR_FICHA_IMOVEL_RURAL);
      }
      catch (LogSefazException e)
      {
         e.printStackTrace();
         throw new IncluiException(MensagemErro.INCLUIR_FICHA_IMOVEL_RURAL);
      }
      catch (NumeroParticaoInvalidoException e) 
      {
         e.printStackTrace();
         throw new IncluiException(MensagemErro.INCLUIR_FICHA_IMOVEL_RURAL);
      }
      catch (CodigoTransacaoInvalidoException e) 
      {
         e.printStackTrace();
         throw new IncluiException(MensagemErro.INCLUIR_FICHA_IMOVEL_RURAL);
      }
      finally
      {
         if (ps != null)
         {
            try
            {
               close(ps);
               ps = null;
            }
            catch (SQLException e)
            {
               e.printStackTrace();
            }
         }
      }
   }
   
   public void updateImovelRural(final ImovelRuralVo imovelRuralVo) throws ObjetoObrigatorioException, AlteraException
   {
      Validador.validaObjeto(imovelRuralVo);
      PreparedStatement ps = null;
      String sql = utilStmt.geraUpdt(getCamposImovelRural(), new String[] { CAMPO_CODIGO_IMOVEL_RURAL });
      try
      {
         GeradorLogSefazMT.gerar(imovelRuralVo, DomnOperacao.OPERACAO_UPDATE, imovelRuralVo.getNumeroParticao(), 
         imovelRuralVo.getCodigoTransacao(), imovelRuralVo.getLogSefazVo().getUsuario().getCodigo(), conn);
         ps = conn.prepareStatement(sql);
         preparedStatementUpdateImovelRural(ps, imovelRuralVo);
         if (ps.executeUpdate() != 1)
         {
            throw new SQLException();
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new AlteraException(MensagemErro.ALTERAR_CULTURA);
      }
            catch (LogSefazException e)
            {
               e.printStackTrace();
               throw new AlteraException(MensagemErro.ALTERAR_CULTURA);
            }
      catch (Exception e)
      {
         e.printStackTrace();
         throw new AlteraException(MensagemErro.ALTERAR_CULTURA);
      }
      finally
      {
         if (ps != null)
         {
            try
            {
               close(ps);
               ps = null;
            }
            catch (SQLException e)
            {
               e.printStackTrace();
            }
         }
      }
   }
}