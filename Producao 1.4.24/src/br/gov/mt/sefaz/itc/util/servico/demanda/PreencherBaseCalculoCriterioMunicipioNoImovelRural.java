package br.gov.mt.sefaz.itc.util.servico.demanda;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.com.abaco.util.exceptions.ParametroObrigatorioException;

import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelVo;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.FichaImovelRuralBe;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.FichaImovelRuralVo;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.ITCDServico;

import br.gov.mt.sefaz.itc.util.facade.MensagemErro;

import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposGIAITCD;

import br.gov.mt.sefaz.itc.util.log.ExibirLOG;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import sefaz.mt.util.Propriedades;
import sefaz.mt.util.SefazDisplay;

/**
 * 
 * 
 * 
 */
public class PreencherBaseCalculoCriterioMunicipioNoImovelRural extends ITCDServico
{

   private Connection conexao;
   private EntidadeVo entidadeVo;
   private static final long NUMR_MATRICULA_SERVIDOR_SERVICO = 999999997L;
   private static final String NOME_ATIVIDADE = "/servicos"; //Este é o diretório padrão quando é um serviço, mas pode variar a pedido da SEFAZ.
   private static final String NOME_SISTEMA = "/itc"; //Este é o nome do seu sistema.
   private static final String NOME_SAIDA = "/out"; //Este é o diretório padrão de saída de um serviço, mas pode variar a pedido da SEFAZ.
   private static final String NOME_ARQUIVO_LOG = "/PreencherBaseCalculoCriterioMunicipioNoImovelRural.log"; //Este é o nome do seu arquivo que será gravado o LOG.
   private static final String NOME_ARQUIVO_PROPERTIES = "ITCDS.properties"; //Este arquivo será criado e empacotado pela SEFAZ.
   private static final String CONFIG_GERENCIAL = "PREENCHER BASE DE CALCULO E CRITERIO MUNICIPIO NO IMOVEL RURAL";
   private static final String INFO_OBSERVACAO = "Preencher Base de Calculo e Criterio Municipio no Imovel Rural";
   private static final String NOME_SERVICO = "preencherbasecalculocriteriomunicipionoImovelrural";
   private SefazDisplay logger = null;
   private List<String> logs = null;
   
   
   public PreencherBaseCalculoCriterioMunicipioNoImovelRural()
      throws SQLException, ConexaoException, ObjetoObrigatorioException
   {
      Propriedades.setArquivoPropriedades(NOME_ARQUIVO_PROPERTIES);
      this.conexao = abreConexao();
      this.conexao.setAutoCommit(false);
      this.logger = new SefazDisplay("/usr/appl/servicos/itc/out/PreencherBaseCalculoCriterioMunicipioNoImovelRural.log");
      this.logs = new ArrayList();
   }
   
   public static void main(String[] args)
      throws SQLException, ConexaoException, ObjetoObrigatorioException, 
             ConsultaException
   {
      ExibirLOG.exibirLog("Inicio servico: PreencherBaseCalculoCriterioMunicipioNoImovelRural");
      
      PreencherBaseCalculoCriterioMunicipioNoImovelRural servico = new PreencherBaseCalculoCriterioMunicipioNoImovelRural();
      List<Long> codigos = servico.listarGiaItcdParaPreencherBaseCalculoECriterioMunicipio();
      GIAITCDBe be = new GIAITCDBe();
      try
      {
         for(Long codigo :codigos)
         {
            try{
               ExibirLOG.exibirLog("Processando GIA: "+codigo);
               GIAITCDVo giaItcdVo = new GIAITCDVo(codigo);
               giaItcdVo = new GIAITCDVo(giaItcdVo);
               giaItcdVo.setConsultaCompleta(true);
              
               GIAITCDVo retorno = be.consultaGiaPermanente(giaItcdVo);
               Iterator<BemTributavelVo> it = retorno.getBemTributavelVo().getCollVO().iterator();
               while(it.hasNext())
               {
                  BemTributavelVo bem = it.next();
                  try{
                     if(bem.getFichaImovelVo() instanceof FichaImovelRuralVo)
                     {
                        FichaImovelRuralVo fr = (FichaImovelRuralVo)bem.getFichaImovelVo();
                        if(Validador.isNumericoValido(fr.getBaseCalculoImovelRuralVo().getCodigo()) && Validador.isNumericoValido(fr.getCriterioMunicipioVo().getCodigo()))
                        {
                          ExibirLOG.exibirLogSimplificado("Imovel "+fr.getCodigo() +"| Codigos ja preenchido");
                        }else
                        {
                           ExibirLOG.exibirLogSimplificado("Processar Imovel | "+fr.getCodigo());
                           fr.getValorTributavelFormatado();
                           if(Validador.isNumericoValido(fr.getBaseCalculoImovelRuralVo().getCodigo()) && Validador.isNumericoValido(fr.getCriterioMunicipioVo().getCodigo()))
                           {
                              ExibirLOG.exibirLog("Preparando atualizacao do imovel");
                              servico.altualizarRegistro(fr,codigo);
                           }
                           else
                           {
                              ExibirLOG.exibirLogSimplificado(" Nao foi possivel recuperar os dados para preenchimento");
                              ExibirLOG.exibirLogSimplificado(" Base Calculo "+fr.getBaseCalculoImovelRuralVo().getCodigo());
                              ExibirLOG.exibirLogSimplificado(" Criterio Municipio "+fr.getCriterioMunicipioVo().getCodigo());
                           }
                        }
                     }
                  }
                  catch(Exception e)
                  {
                     ExibirLOG.exibirLogSimplificado("Erro no processamento | Mensagem: "+e.getMessage());
                     e.printStackTrace();
                  }
               }
            }
            catch(Exception e)
            {
               ExibirLOG.exibirLogSimplificado("Erro no processamento | Mensagem: "+e.getMessage());
               e.printStackTrace();
            }
         }
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }finally
      {
         for(String s : servico.logs)
         {
            //ExibirLOG.exibirLogSimplificado(s);
         }
      }
   }
   
   
   
   
   
   /**
    * metodo utilizado para listar as gias que devem ser processadas pelo servico
    * PreencherBaseCalculoCriterioMunicipioNoImovelRural
    * 
    * 
    * @return
    * @throws ConsultaException
    */
   public List<Long> listarGiaItcdParaPreencherBaseCalculoECriterioMunicipio()
      throws ConsultaException
   {
      String sql = "SELECT DISTINCT ITC.CODG_ITCD FROM ITC.ITCTB21_IMOVEL_RURAL IR " + 
      " INNER JOIN ITC.ITCTB18_ITCD_BEM_TRBT BT ON BT.CODG_ITCD_BEM_TRBT = IR.ITCTB18_CODG_ITCD_BEM_TRBT " + 
      " INNER JOIN ITC.ITCTB14_ITCD ITC ON ITC.CODG_ITCD = BT.ITCTB14_CODG_ITCD " + 
      " WHERE IR.ITCTB52_NUMR_SEQC_BASE_CALC IS NULL AND IR.ITCTB52_NUMR_SEQC_BASE_CALC IS NULL " + 
      " AND ITC.TIPO_PROTOCOLO = 2 AND BT.ITCTB14_CODG_ITCD = ( SELECT DISTINCT( STS.ITCTB14_CODG_ITCD) FROM ITC.ITCTB27_STATUS_ITCD STS " + 
      " WHERE STS.STAT_ITCD = 4 AND STS.ITCTB14_CODG_ITCD = ITC.CODG_ITCD)";
      
      PreparedStatement ps = null;
      ResultSet rs = null;
      List<Long> codigos = new ArrayList<Long>();
      
      try
      {
         ps = conexao.prepareStatement(sql);
         rs = ps.executeQuery();
         
         while (rs.next())
         {
            codigos.add(rs.getLong(CamposGIAITCD.CAMPO_CODIGO_GIA_ITCD));
            
         }
         
         ExibirLOG.exibirLog("Total de gias para processamento: "+codigos.size());

         String[] codes = codigos.toString().split(",");
         StringBuffer sb = new StringBuffer("\n");
         for (int i = 0; i < codes.length; i++)
         {
            sb.append(((i % 11) != 0)? codes[i].concat(", "): codes[i].concat(", \n"));
         }
         
         ExibirLOG.exibirLogSimplificado(sb);
         
         return codigos;
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConsultaException(MensagemErro.CONSULTAR_GIA_ITCD);
      }
      finally
      {
         try
         {
            ps.close();
            rs.close();
         }
         catch (SQLException e)
         {
            e.printStackTrace();
         }
      }
   }
   
   private void altualizarRegistro(final FichaImovelRuralVo ficha, long codigoGia)
   {
      String sql = "UPDATE ITC.ITCTB21_IMOVEL_RURAL IR " + 
      " SET IR.ITCTB52_NUMR_SEQC_BASE_CALC = %s , " + 
      " IR.ITCTB53_NUMR_SEQC_CRIT_MUNC = %s " + 
      " WHERE IR.CODG_IMOVEL_RURAL = %s " + 
      " AND IR.ITCTB52_NUMR_SEQC_BASE_CALC IS NULL " + 
      " AND IR.ITCTB53_NUMR_SEQC_CRIT_MUNC IS NULL ";
      
      PreparedStatement ps = null;
      int row = 0;
      StringBuffer sb = new StringBuffer();
      try
      {
         ps = conexao.prepareStatement(sql);
         
         sb.append("Informacoes").append("\n");
         sb.append("GIA: "+codigoGia).append("\n");
         sb.append("Imovel: "+ficha.getCodigo() +" "+ficha.getDescricaoDenominacao()).append("\n");
         sb.append("Base Calculo: "+ficha.getBaseCalculoImovelRuralVo().getCodigo()).append("\n");
         sb.append("Criterio Municipio: "+ficha.getCriterioMunicipioVo().getCodigo()).append("\n");
         
         long bc = ficha.getBaseCalculoImovelRuralVo().getCodigo();
         long cm = ficha.getCriterioMunicipioVo().getCodigo();
         long fc = ficha.getCodigo();
         
         String sqlUpdate = String.format(sql, bc, cm, fc);
         
         sb.append("SQL: "+sqlUpdate).append("\n");
         
         row = ps.executeUpdate(sqlUpdate);
         conexao.commit();
         
         sb.append("Registro Atualizado: "+ ((row > 0) ? "SIM - " +row:"NÃO")).append("\n");
      } 
      catch (SQLException e)
      {
         try
         {
            sb.append("Registro Atualizado: "+ ((row > 0) ? "SIM - " +row:"NÃO")).append("\n");
            sb.append("Erro: "+ e.getMessage()).append("\n");
            conexao.rollback();
         }
         catch (SQLException ex)
         {
            ex.printStackTrace();
         }
         e.printStackTrace();
      }
      finally
      {
         String[] localLog = sb.toString().split("\n");
         for(int i = 0; i < localLog.length;i++)
         {
            ExibirLOG.exibirLogSimplificado(localLog[i]);
         }
        
         //this.logs.add(sb.toString());
         try
         {
            ps.close();
         }
         catch (SQLException e)
         {
            e.printStackTrace();
         }
      }
   }
   
}
