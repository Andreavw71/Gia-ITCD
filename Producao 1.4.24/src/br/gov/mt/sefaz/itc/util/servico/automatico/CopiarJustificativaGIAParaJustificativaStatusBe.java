/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: CopiarJustificativaGIAParaJustificativaStatus.java
 * Revisão: Dherkyan Ribeiro da Silva
 * Data criação : 28/10/2014
 * Data ultima revisão : 28/10/2014
 */
package br.gov.mt.sefaz.itc.util.servico.automatico;

import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.DadoNecessarioInexistenteException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd.StatusGIAITCDBe;
import br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd.StatusGIAITCDQDao;
import br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd.StatusGIAITCDVo;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusGIAITCD;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposGIAITCD;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import sefaz.mt.util.SefazDataHora;


public class CopiarJustificativaGIAParaJustificativaStatusBe extends AbstractBe implements CamposGIAITCD
{
   public CopiarJustificativaGIAParaJustificativaStatusBe() throws SQLException
   {
      super();
   }

   public CopiarJustificativaGIAParaJustificativaStatusBe(Connection conn)
   {
      super(conn);
   }


   public void iniciarCopiaJustificativa(EntidadeVo entidadeVo) throws ObjetoObrigatorioException, 
                                                                       ConsultaException
   {
      GIAITCDVo gias = listarGIA();
      StatusGIAITCDBe statusBe = new StatusGIAITCDBe(conn);
      DomnStatusGIAITCD alteradoPeloservidor = new DomnStatusGIAITCD(DomnStatusGIAITCD.ALTERADO_PELO_SERVIDOR);
      int contadorGIA = 0;
      int contadorStatus = 0;
      for (GIAITCDVo gia: gias.getCollVO())
      {

         StatusGIAITCDVo consulta = new StatusGIAITCDVo();
         StatusGIAITCDVo parametro = new StatusGIAITCDVo();
         parametro.getGiaITCDVo().setCodigo(gia.getCodigo());
         consulta = new StatusGIAITCDVo(parametro);

         gia.setStatusVo(new StatusGIAITCDQDao(conn).listStatusGIAITCD(consulta));

         StatusGIAITCDVo statusServidor = procurarStatusNaCollectionDoVo(gia.getStatusVo(), alteradoPeloservidor);

         if (statusServidor != null)
         {
            
            boolean imprmirLinha = false;
            for (StatusGIAITCDVo status: gia.getStatusVo().getCollVO())
            {
               if (status.getCodigo() >= statusServidor.getCodigo())
               {
                  try
                  {
                     if (!Validador.isStringValida(status.getJustificativaAlteracao()))
                     {
                        status.setJustificativaAlteracao(gia.getJustificativaAlteracao());
                        status.setLogSefazVo(entidadeVo.getLogSefazVo());
                        status.setUsuarioLogado(entidadeVo.getUsuarioLogado());
                        statusBe.alterarStatus(status);
                        String log = "GIA : " + gia.getCodigo() + "\n STATUS : " + status.getCodigo() + "\n DOMINIO : " + status.getStatusGIAITCD().getValorCorrente() + "\n JUSTIFICATIVA : " + gia.getJustificativaAlteracao();
                        if (Validador.isStringValida(log))
                        {
                           try
                           {
                              imprmirLinha = true;
                              contadorStatus++;
                              registrarLogExecucao(log);
                           } catch (IOException e)
                           {
                              e.printStackTrace();
                           }
                        }

                        try
                        {
                           commit();
                        } catch (SQLException e)
                        {
                           throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
                        }
                     }


                  } catch (ConexaoException erro)
                  {
                     erro.printStackTrace();
                  } catch (LogException erro)
                  {
                     erro.printStackTrace();
                  } catch (DadoNecessarioInexistenteException erro)
                  {
                     erro.printStackTrace();
                  } catch (PersistenciaException erro)
                  {
                     erro.printStackTrace();
                  } catch (AnotacaoException erro)
                  {
                     erro.printStackTrace();
                  }
               }
            }
            try
            {
               if (imprmirLinha)
               {
                  registrarLogExecucao("----------------------------------------------------------------" + SefazDataHora.getDataHoraAtual().formata() + "----------------------------------------------------------------");
                  contadorGIA++;
               }

            } catch (IOException e)
            {
              e.printStackTrace();
            }
         }
         //System.out.println(gia.getCodigo());
      }


      try
      {
         registrarLogExecucao("TOTAL GIAS : "+contadorGIA);
         registrarLogExecucao("TOTAL STATUS : "+contadorStatus);
      } catch (IOException e)
      {
         e.printStackTrace();
      }
   }


   private GIAITCDVo listarGIA()
   {
      String SQL_LISTAR_GIA = "SELECT  GIA.CODG_ITCD , GIA.JUST_ALTERACAO   FROM ITC.ITCTB14_ITCD GIA WHERE GIA.JUST_ALTERACAO IS NOT NULL ORDER BY GIA.CODG_ITCD";
      PreparedStatement ps = null;
      ResultSet rs = null;
      GIAITCDVo gia = new GIAITCDVo();

      try
      {
         ps = conn.prepareStatement(SQL_LISTAR_GIA);
         rs = ps.executeQuery();
         while (rs.next())
         {
            GIAITCDVo temp = new GIAITCDVo();
            temp.setCodigo(rs.getLong(CAMPO_CODIGO_GIA_ITCD));
            temp.setJustificativaAlteracao(rs.getString(CAMPO_JUSTIFICATIVA_ALTERACAO));
            if (Validador.isNumericoValido(temp.getCodigo()))
            {
               gia.getCollVO().add(temp);
            }
         }
      } catch (SQLException e)
      {
         e.printStackTrace();
      }
      return gia;
   }


   public StatusGIAITCDVo procurarStatusNaCollectionDoVo(final StatusGIAITCDVo statusGIAITCDVo, DomnStatusGIAITCD statusProcurado)
   {
      StatusGIAITCDVo status = null;
      if (Validador.isObjetoValido(statusGIAITCDVo))
      {
         if (Validador.isCollectionValida(statusGIAITCDVo.getCollVO()))
         {
            for (StatusGIAITCDVo sts: statusGIAITCDVo.getCollVO())
            {
               if (sts.getStatusGIAITCD().is(statusProcurado.getValorCorrente()))
               {
                  return sts;
               }
            }
         }
      }
      return status;
   }


   protected static void registrarLogExecucao(String log) throws IOException
   {
      BufferedWriter br = null;
      br = new BufferedWriter(new FileWriter("/usr/appl/servicos/itc/out/CopiarJustificativaGIAParaJustificativaStatus.log", true));

      if (br != null)
      {
         br.write(log);
         br.newLine();
         br.flush();
         br.close();
      }

   }


}
