package br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.benfeitoria;

import br.com.abaco.log5.GenericoLogAnotacaoDao;
import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.DadoNecessarioInexistenteException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.FichaImovelRuralVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.benfeitoria.BenfeitoriaBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.benfeitoria.BenfeitoriaVo;
import br.gov.mt.sefaz.itc.util.facade.ConfiguracaoITCD;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.Iterator;


/**
 * Classe responsável por implementar a regra de negócio da entidade "Ficha Imóvel Rural Benfeitoria"
 * 
 * @author Daniel Balieiro
 * @version $Revision: 1.6 $
 */
public class FichaImovelRuralBenfeitoriaBe extends AbstractBe
{

   /**
    * Construtor Padrão
    * 
    * @throws SQLException
    * @implemented by Daniel Balieiro
    */
   public FichaImovelRuralBenfeitoriaBe() throws SQLException
   {
      super();
   }

   /**
    * Construtor que recebe a conexão com o banco de dados
    * 
    * @param conexao
    * @implemented by Daniel Balieiro
    */
   public FichaImovelRuralBenfeitoriaBe(Connection conexao)
   {
      super(conexao);
   }

   /**
    * @param fichaImovelRuralBenfeitoriaVo
    * @throws ObjetoObrigatorioException
    * @throws LogException
    * @throws AnotacaoException
    * @throws PersistenciaException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private synchronized void excluir(final FichaImovelRuralBenfeitoriaVo fichaImovelRuralBenfeitoriaVo) throws ObjetoObrigatorioException, 
                                                                                                               LogException, 
                                                                                                               AnotacaoException, 
                                                                                                               PersistenciaException
   {
      Validador.validaObjeto(fichaImovelRuralBenfeitoriaVo);
      new GenericoLogAnotacaoDao(conn, true).delete(fichaImovelRuralBenfeitoriaVo);
   }

   /**
    * Método para remover uma Ficha Imóvel Rural Benfeitoria
    * 
    * @param fichaImovelRuralBenfeitoriaVo
    * @throws ObjetoObrigatorioException
    * @throws ConexaoException
    * @throws LogException
    * @throws PersistenciaException
    * @throws AnotacaoException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public synchronized void removerFichaImovelRuralBenfeitoria(final FichaImovelRuralBenfeitoriaVo fichaImovelRuralBenfeitoriaVo) throws ObjetoObrigatorioException, 
                                                                                                                                         ConexaoException, 
                                                                                                                                         LogException, 
                                                                                                                                         PersistenciaException, 
                                                                                                                                         AnotacaoException
   {
      Validador.validaObjeto(fichaImovelRuralBenfeitoriaVo);
      try
      {
         try
         {
            synchronized (FichaImovelRuralBenfeitoriaVo.class)
            {
               excluir(fichaImovelRuralBenfeitoriaVo);
            }
         } catch (ObjetoObrigatorioException e)
         {
            conn.rollback();
            throw e;
         } catch (LogException e)
         {
            conn.rollback();
            throw e;
         } catch (PersistenciaException e)
         {
            conn.rollback();
            throw e;
         } catch (AnotacaoException e)
         {
            conn.rollback();
            throw e;
         }
      } catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
      }
   }

   /**
    * Método para validar a inclusão de Ficha Imóvel Rural Benfeitoria
    * @param fichaImovelRuralBenfeitoriaVo
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    * @implemented by Daniel Balieiro
    */
   private void validarIncluirFichaImovelRuralBenfeitoria(final FichaImovelRuralBenfeitoriaVo fichaImovelRuralBenfeitoriaVo) throws ObjetoObrigatorioException, 
                                                                                                                                    ParametroObrigatorioException
   {
      Validador.validaObjeto(fichaImovelRuralBenfeitoriaVo);
      if (!Validador.isStringValida(fichaImovelRuralBenfeitoriaVo.getBenfeitoriaVo().getDescricaoBenfeitoria()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_BENFEITORIA_DESCRICAO);
      }
      for (Iterator iteBen = fichaImovelRuralBenfeitoriaVo.getCollVO().iterator(); iteBen.hasNext(); )
      {
         FichaImovelRuralBenfeitoriaVo benfeitoria = (FichaImovelRuralBenfeitoriaVo) iteBen.next();
         for (Iterator iteBenConsulta = fichaImovelRuralBenfeitoriaVo.getCollVO().iterator(); iteBenConsulta.hasNext(); )
         {
            FichaImovelRuralBenfeitoriaVo benfeitoriaConsulta = (FichaImovelRuralBenfeitoriaVo) iteBenConsulta.next();
            validaDescricaoComplementarBenfeitoria(benfeitoria, benfeitoriaConsulta);
         }
      }
   }

   /**
    * Método responsável por validar dados necessários à alteração de uma benfeitoria.
    * @param fichaImovelRuralBenfeitoriaVo
    * @throws ObjetoObrigatorioException
    * @throws DadoNecessarioInexistenteException
    * @throws ParametroObrigatorioException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private void validaAlterarFichaImovelRuralBenfeitoria(final FichaImovelRuralBenfeitoriaVo fichaImovelRuralBenfeitoriaVo) throws ObjetoObrigatorioException, 
                                                                                                                                   DadoNecessarioInexistenteException, 
                                                                                                                                   ParametroObrigatorioException
   {
      Validador.validaObjeto(fichaImovelRuralBenfeitoriaVo);
      if (!Validador.isNumericoValido(fichaImovelRuralBenfeitoriaVo.getCodigo()))
      {
         throw new DadoNecessarioInexistenteException(MensagemErro.CODIGO_FICHA_IMOVEL_RURAL_BENFEITORIA_NECESSARIO_PARA_ALTERAR);
      }
      validarIncluirFichaImovelRuralBenfeitoria(fichaImovelRuralBenfeitoriaVo);
   }

   /**
    * Método responsável por validar a descrição complementar de uma Benfeitoria da ficha de Imóvel Rural.
    * @param fichaImovelRuralBenfeitoriaVo
    * @param fichaImovelRuralBenfeitoriaConsultaVo
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private static void validaDescricaoComplementarBenfeitoria(final FichaImovelRuralBenfeitoriaVo fichaImovelRuralBenfeitoriaVo, final FichaImovelRuralBenfeitoriaVo fichaImovelRuralBenfeitoriaConsultaVo) throws ObjetoObrigatorioException, 
                                                                                                                                                                                                                   ParametroObrigatorioException
   {
      Validador.validaObjeto(fichaImovelRuralBenfeitoriaVo);
      Validador.validaObjeto(fichaImovelRuralBenfeitoriaConsultaVo);
      if (Validador.isStringValida(fichaImovelRuralBenfeitoriaVo.getDescricaoComplementarBenfeitoria()) && fichaImovelRuralBenfeitoriaVo.getDescricaoComplementarBenfeitoria().length() > ConfiguracaoITCD.TAMANHO_MAXIMO_DESCRICAO_COMPLEMENTAR_BENFEITORIA)
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_BENFEITORIA_TAMANHO_MAXIMO_DESCRICAO);
      } else if (fichaImovelRuralBenfeitoriaVo.getBenfeitoriaVo().getCodigo() == fichaImovelRuralBenfeitoriaConsultaVo.getBenfeitoriaVo().getCodigo() && fichaImovelRuralBenfeitoriaVo.getDescricaoComplementarBenfeitoria().equals(fichaImovelRuralBenfeitoriaConsultaVo.getDescricaoComplementarBenfeitoria()))
      {
         if (Validador.isStringValida(fichaImovelRuralBenfeitoriaVo.getDescricaoComplementarBenfeitoria()))
         {
            throw new ParametroObrigatorioException(MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_BENFEITORIA_DESCRICAO_EXISTENTE);
         } else
         {
            throw new ParametroObrigatorioException(MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_BENFEITORIA_DESCRICAO_VAZIA_EXISTENTE);
         }
      }
   }

   /**
    * Método responsável por adicionar na ficha de Imóvel Rural uma Benfeitoria.
    * @param fichaImovelRuralBenfeitoriaVo
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public static void adicionarBenfeitoriaImovelRural(final FichaImovelRuralBenfeitoriaVo fichaImovelRuralBenfeitoriaVo) throws ObjetoObrigatorioException, 
                                                                                                                                ParametroObrigatorioException
   {
      Validador.validaObjeto(fichaImovelRuralBenfeitoriaVo);
      Iterator itFicha = fichaImovelRuralBenfeitoriaVo.getCollVO().iterator();
      while (itFicha.hasNext())
      {
         FichaImovelRuralBenfeitoriaVo fichaImovelRuralBenfeitoriaAtualVo = (FichaImovelRuralBenfeitoriaVo) itFicha.next();
         validaDescricaoComplementarBenfeitoria(fichaImovelRuralBenfeitoriaVo, fichaImovelRuralBenfeitoriaAtualVo);
      }
      fichaImovelRuralBenfeitoriaVo.getCollVO().add(fichaImovelRuralBenfeitoriaVo.clonePadrao());
      reinicializaBenfeitoriaImovelRural(fichaImovelRuralBenfeitoriaVo);
   }

   /**
    * Método responsável por zerar os valores do objeto fichaImovelRuralBenfeitoriaVo, 
    * qual armazena os dados advindos da JSP.
    * @param fichaImovelRuralBenfeitoriaVo
    * @throws ObjetoObrigatorioException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private static void reinicializaBenfeitoriaImovelRural(final FichaImovelRuralBenfeitoriaVo fichaImovelRuralBenfeitoriaVo) throws ObjetoObrigatorioException
   {
      Validador.validaObjeto(fichaImovelRuralBenfeitoriaVo);
      fichaImovelRuralBenfeitoriaVo.getBenfeitoriaVo().setCodigo(0);
      fichaImovelRuralBenfeitoriaVo.getBenfeitoriaVo().setDescricaoBenfeitoria("");
      fichaImovelRuralBenfeitoriaVo.setDescricaoComplementarBenfeitoria("");
   }

   /**
    * @param fichaImovelRuralBenfeitoriaVo
    * @throws ObjetoObrigatorioException
    * @throws LogException
    * @throws AnotacaoException
    * @throws PersistenciaException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private synchronized void incluir(final FichaImovelRuralBenfeitoriaVo fichaImovelRuralBenfeitoriaVo) throws ObjetoObrigatorioException, 
                                                                                                               LogException, 
                                                                                                               AnotacaoException, 
                                                                                                               PersistenciaException
   {
      Validador.validaObjeto(fichaImovelRuralBenfeitoriaVo);
      new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).insert(fichaImovelRuralBenfeitoriaVo);
   }

   /**
    * Método para incluir uma Ficha Imóvel Rural Benfeitoria
    * 
    * @param fichaImovelRuralBenfeitoriaVo
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    * @throws ConexaoException
    * @throws LogException
    * @throws PersistenciaException
    * @throws AnotacaoException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public synchronized void incluirFichaImovelRuralBenfeitoria(final FichaImovelRuralBenfeitoriaVo fichaImovelRuralBenfeitoriaVo) throws ObjetoObrigatorioException, 
                                                                                                                                         ParametroObrigatorioException, 
                                                                                                                                         ConexaoException, 
                                                                                                                                         LogException, 
                                                                                                                                         PersistenciaException, 
                                                                                                                                         AnotacaoException
   {
      Validador.validaObjeto(fichaImovelRuralBenfeitoriaVo);
      try
      {
         try
         {
            synchronized (FichaImovelRuralBenfeitoriaVo.class)
            {
               validarIncluirFichaImovelRuralBenfeitoria(fichaImovelRuralBenfeitoriaVo);
               incluir(fichaImovelRuralBenfeitoriaVo);
            }
         } catch (ParametroObrigatorioException e)
         {
            conn.rollback();
            throw e;
         } catch (ObjetoObrigatorioException e)
         {
            conn.rollback();
            throw e;
         } catch (LogException e)
         {
            conn.rollback();
            throw e;
         } catch (PersistenciaException e)
         {
            conn.rollback();
            throw e;
         } catch (AnotacaoException e)
         {
            conn.rollback();
            throw e;
         }
      } catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
      }
   }

   /**
    * Método para consultar uma Ficha Imóvel Rural Benfeitoria
    * 
    * @param fichaImovelRuralBenfeitoriaVo
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @throws ConexaoException
    * @return FichaImovelRuralBenfeitoriaVo
    * @implemented by Daniel Balieiro
    */
   public FichaImovelRuralBenfeitoriaVo consultaFichaImovelRuralBenfeitoria(final FichaImovelRuralBenfeitoriaVo fichaImovelRuralBenfeitoriaVo) throws ObjetoObrigatorioException, 
                                                                                                                                                      ConsultaException, 
                                                                                                                                                      ConexaoException
   {
      Validador.validaObjeto(fichaImovelRuralBenfeitoriaVo);
      (new FichaImovelRuralBenfeitoriaQDao(conn)).findFichaImovelRuralBenfeitoria(fichaImovelRuralBenfeitoriaVo);
      if (fichaImovelRuralBenfeitoriaVo.isConsultaCompleta())
      {
         (new BenfeitoriaBe(conn)).listarBenfeitoria(fichaImovelRuralBenfeitoriaVo.getBenfeitoriaVo());
      }
      return fichaImovelRuralBenfeitoriaVo;
   }

   /**
    * Método para listar Ficha Imóvel Rural Benfeitoria
    * 
    * @param fichaImovelRuralBenfeitoriaVo
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @return FichaImovelRuralBenfeitoriaVo
    * @implemented by Daniel Balieiro
    */
   public FichaImovelRuralBenfeitoriaVo listarFichaImovelRuralBenfeitoria(final FichaImovelRuralBenfeitoriaVo fichaImovelRuralBenfeitoriaVo) throws ObjetoObrigatorioException, 
                                                                                                                                                    ConsultaException
   {
      Validador.validaObjeto(fichaImovelRuralBenfeitoriaVo);
      (new FichaImovelRuralBenfeitoriaQDao(conn)).listFichaImovelRuralBenfeitoria(fichaImovelRuralBenfeitoriaVo);
      if (fichaImovelRuralBenfeitoriaVo.isConsultaCompleta())
      {
         for (Iterator iteBenfeitoria = fichaImovelRuralBenfeitoriaVo.getCollVO().iterator(); iteBenfeitoria.hasNext(); )
         {
            FichaImovelRuralBenfeitoriaVo ficha = (FichaImovelRuralBenfeitoriaVo) iteBenfeitoria.next();
            BenfeitoriaVo benfeitoria = new BenfeitoriaVo(ficha.getBenfeitoriaVo().getCodigo());
            benfeitoria = new BenfeitoriaVo(benfeitoria);
            (new BenfeitoriaBe(conn)).consultarBenfeitoria(benfeitoria);
            ficha.setBenfeitoriaVo(benfeitoria);
         }
      }
      return fichaImovelRuralBenfeitoriaVo;
   }

   /**
    * @param fichaImovelRuralBenfeitoriaVo
    * @throws ObjetoObrigatorioException
    * @throws LogException
    * @throws AnotacaoException
    * @throws PersistenciaException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private synchronized void alterar(final FichaImovelRuralBenfeitoriaVo fichaImovelRuralBenfeitoriaVo) throws ObjetoObrigatorioException, 
                                                                                                               LogException, 
                                                                                                               AnotacaoException, 
                                                                                                               PersistenciaException
   {
      Validador.validaObjeto(fichaImovelRuralBenfeitoriaVo);
      new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).update(fichaImovelRuralBenfeitoriaVo);
   }

   /**
    * Método responsável por alterar uma benfeitoria constante em uma ficha de imóvel rural.
    * @param fichaImovelRuralBenfeitoriaVo
    * @throws ObjetoObrigatorioException
    * @throws DadoNecessarioInexistenteException
    * @throws ParametroObrigatorioException
    * @throws LogException
    * @throws PersistenciaException
    * @throws AnotacaoException
    * @throws ConexaoException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public void alterarFichaImovelRuralBenfeitoriaAlterarFichaImovelRural(final FichaImovelRuralBenfeitoriaVo fichaImovelRuralBenfeitoriaVo) throws ObjetoObrigatorioException, 
                                                                                                                                                   DadoNecessarioInexistenteException, 
                                                                                                                                                   ParametroObrigatorioException, 
                                                                                                                                                   LogException, 
                                                                                                                                                   PersistenciaException, 
                                                                                                                                                   AnotacaoException, 
                                                                                                                                                   ConexaoException
   {
      Validador.validaObjeto(fichaImovelRuralBenfeitoriaVo);
      try
      {
         try
         {
            synchronized (FichaImovelRuralBenfeitoriaVo.class)
            {
               validaAlterarFichaImovelRuralBenfeitoria(fichaImovelRuralBenfeitoriaVo);
               alterar(fichaImovelRuralBenfeitoriaVo);
            }
         } catch (ObjetoObrigatorioException e)
         {
            conn.rollback();
            throw e;
         } catch (DadoNecessarioInexistenteException e)
         {
            conn.rollback();
            throw e;
         } catch (ParametroObrigatorioException e)
         {
            conn.rollback();
            throw e;
         } catch (LogException e)
         {
            conn.rollback();
            throw e;
         } catch (PersistenciaException e)
         {
            conn.rollback();
            throw e;
         } catch (AnotacaoException e)
         {
            conn.rollback();
            throw e;
         }
      } catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
      }
   }

   /**
    * Método responsável por validar se os dados necessários à exclusão de uma benfeitoria foram informados.
    * @param fichaImovelRuralBenfeitoriaVo
    * @throws ObjetoObrigatorioException
    * @throws DadoNecessarioInexistenteException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private void validarExcluirFichaImovelRuralBenfeitoria(final FichaImovelRuralBenfeitoriaVo fichaImovelRuralBenfeitoriaVo) throws ObjetoObrigatorioException, 
                                                                                                                                    DadoNecessarioInexistenteException
   {
      Validador.validaObjeto(fichaImovelRuralBenfeitoriaVo);
      if (!Validador.isNumericoValido(fichaImovelRuralBenfeitoriaVo.getCodigo()))
      {
         throw new DadoNecessarioInexistenteException(MensagemErro.CODIGO_FICHA_IMOVEL_RURAL_BENFEITORIA_NECESSARIO_PARA_EXCLUIR);
      }
   }

   /**
    * Método responsável por solicitar a exclusão de uma benfeitoria para uma ficha de imóvel rural.
    * @param fichaImovelRuralBenfeitoriaVo
    * @throws ObjetoObrigatorioException
    * @throws DadoNecessarioInexistenteException
    * @throws LogException
    * @throws PersistenciaException
    * @throws AnotacaoException
    * @throws ConexaoException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public void exluirFichaImovelRuralBenfeitoriaExcluirFichaImovelRural(final FichaImovelRuralBenfeitoriaVo fichaImovelRuralBenfeitoriaVo) throws ObjetoObrigatorioException, 
                                                                                                                                                  DadoNecessarioInexistenteException, 
                                                                                                                                                  LogException, 
                                                                                                                                                  PersistenciaException, 
                                                                                                                                                  AnotacaoException, 
                                                                                                                                                  ConexaoException
   {
      Validador.validaObjeto(fichaImovelRuralBenfeitoriaVo);
      try
      {
         try
         {
            synchronized (FichaImovelRuralBenfeitoriaVo.class)
            {
               validarExcluirFichaImovelRuralBenfeitoria(fichaImovelRuralBenfeitoriaVo);
               excluir(fichaImovelRuralBenfeitoriaVo);
            }
         } catch (DadoNecessarioInexistenteException e)
         {
            conn.rollback();
            throw e;
         } catch (LogException e)
         {
            conn.rollback();
            throw e;
         } catch (PersistenciaException e)
         {
            conn.rollback();
            throw e;
         } catch (AnotacaoException e)
         {
            conn.rollback();
            throw e;
         }
      } catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
      }

   }

   public void processarExcluirFichaImovelRuralBenfeitoriaVo(FichaImovelRuralBenfeitoriaVo fichaImovelRuralBenfeitoriaVoModificada, FichaImovelRuralBenfeitoriaVo fichaImovelRuralBenfeitoriaVoOriginal) throws ObjetoObrigatorioException, 
                                                                                                                                                                                                                DadoNecessarioInexistenteException, 
                                                                                                                                                                                                                LogException, 
                                                                                                                                                                                                                PersistenciaException, 
                                                                                                                                                                                                                AnotacaoException, 
                                                                                                                                                                                                                ConexaoException
   {
      if (fichaImovelRuralBenfeitoriaVoOriginal.getCollVO().size() > fichaImovelRuralBenfeitoriaVoModificada.getCollVO().size())
      {
         for (Iterator itOriginal = fichaImovelRuralBenfeitoriaVoOriginal.getCollVO().iterator(); itOriginal.hasNext(); )
         {
            boolean excluir = true;
            FichaImovelRuralBenfeitoriaVo original = (FichaImovelRuralBenfeitoriaVo) itOriginal.next();
            for (Iterator itModificado = fichaImovelRuralBenfeitoriaVoModificada.getCollVO().iterator(); itModificado.hasNext(); )
            {
               FichaImovelRuralBenfeitoriaVo modificada = (FichaImovelRuralBenfeitoriaVo) itModificado.next();
               if (original.getCodigo() == modificada.getCodigo())
               {
                  excluir = false;
               }
            }
            if (excluir)
            {
               exluirFichaImovelRuralBenfeitoriaExcluirFichaImovelRural(original);
               //excluir(original);
            }
         }
      }
   }

   public void excluirFichaImovelRuralBemfeitoriaVo(BemTributavelVo bemModificado, BemTributavelVo bemOriginal) throws ObjetoObrigatorioException, 
                                                                                                                       DadoNecessarioInexistenteException, 
                                                                                                                       LogException, 
                                                                                                                       PersistenciaException, 
                                                                                                                       AnotacaoException, 
                                                                                                                       ConexaoException
   {
      //------------------------------ Exclusão da FichaImovelRuralBenfeitoriaVo ----------------------------------------
      if (Validador.isObjetoValido(bemModificado) & Validador.isObjetoValido(bemOriginal))
      {
         Iterator itFichaBenfeitoriaOriginal = ((FichaImovelRuralVo) bemOriginal.getFichaImovelVo()).getFichaImovelRuralBenfeitoriaVo().getCollVO().iterator();

         while (itFichaBenfeitoriaOriginal.hasNext())
         {
            boolean excluirBenfeitoriaRural = true;
            FichaImovelRuralBenfeitoriaVo bemFeitoriaVoOriginal = (FichaImovelRuralBenfeitoriaVo) itFichaBenfeitoriaOriginal.next();
            Iterator itFichaBenfeitoriaModificada = ((FichaImovelRuralVo) bemModificado.getFichaImovelVo()).getFichaImovelRuralBenfeitoriaVo().getCollVO().iterator();
            while (itFichaBenfeitoriaModificada.hasNext())
            {
               FichaImovelRuralBenfeitoriaVo bemFeitoriaVoModificada = (FichaImovelRuralBenfeitoriaVo) itFichaBenfeitoriaModificada.next();
               if (bemFeitoriaVoOriginal.getCodigo() == bemFeitoriaVoModificada.getCodigo())
               {
                  excluirBenfeitoriaRural = false;
               }
            }
            if (excluirBenfeitoriaRural)
            {
               exluirFichaImovelRuralBenfeitoriaExcluirFichaImovelRural(bemFeitoriaVoOriginal);
            }
         }
      }

   }


}
