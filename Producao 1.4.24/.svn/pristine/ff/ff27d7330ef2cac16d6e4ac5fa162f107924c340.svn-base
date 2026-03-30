package br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.rebanho;

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
import br.com.abaco.util.exceptions.RegistroExistenteException;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.FichaImovelRuralVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.rebanho.RebanhoBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.rebanho.RebanhoVo;
import br.gov.mt.sefaz.itc.util.facade.ConfiguracaoITCD;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.Iterator;


/**
 * Classe de encapsulamento da regra de negócio da entidade "Ficha Imóvel Rural Rebanho"
 * 
 * @author Daniel Balieiro
 * @version $Revision: 1.5 $
 */
public class FichaImovelRuralRebanhoBe extends AbstractBe
{

   /**
    * Construtor Padrão
    * 
    * @throws SQLException
    * @implemented by Daniel Balieiro
    */
   public FichaImovelRuralRebanhoBe() throws SQLException
   {
      super();
   }

   /**
    * Construtor que recebe a conexão com o banco de dados
    * 
    * @param conexao
    * @implemented by Daniel Balieiro
    */
   public FichaImovelRuralRebanhoBe(Connection conexao)
   {
      super(conexao);
   }

   /**
    * @param fichaImovelRuralRebanhoVo
    * @throws ObjetoObrigatorioException
    * @throws LogException
    * @throws AnotacaoException
    * @throws PersistenciaException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private synchronized void excluir(final FichaImovelRuralRebanhoVo fichaImovelRuralRebanhoVo) throws ObjetoObrigatorioException, 
                                                                                                       LogException, 
                                                                                                       AnotacaoException, 
                                                                                                       PersistenciaException
   {
      Validador.validaObjeto(fichaImovelRuralRebanhoVo);
      new GenericoLogAnotacaoDao(conn, true).delete(fichaImovelRuralRebanhoVo);
   }

   /**
    * Método para remover uma Ficha Imóvel Rural Rebanho
    * 
    * @param fichaImovelRuralRebanhoVo
    * @throws ObjetoObrigatorioException
    * @throws ConexaoException
    * @throws LogException
    * @throws PersistenciaException
    * @throws AnotacaoException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public synchronized void removerFichaImovelRuralRebanho(final FichaImovelRuralRebanhoVo fichaImovelRuralRebanhoVo) throws ObjetoObrigatorioException, 
                                                                                                                             ConexaoException, 
                                                                                                                             LogException, 
                                                                                                                             PersistenciaException, 
                                                                                                                             AnotacaoException
   {
      Validador.validaObjeto(fichaImovelRuralRebanhoVo);
      try
      {
         try
         {
            synchronized (FichaImovelRuralRebanhoVo.class)
            {
               excluir(fichaImovelRuralRebanhoVo);
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
    * Método para validar a inclusao da Ficha Imovel Rural - Rebanho
    * @param fichaImovelRuralRebanhoVo
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    * @throws RegistroExistenteException
    * @implemented by Daniel Balieiro
    */
   private void validarIncluirFichaImovelRuralRebanho(final FichaImovelRuralRebanhoVo fichaImovelRuralRebanhoVo) throws ObjetoObrigatorioException, 
                                                                                                                        ParametroObrigatorioException, 
                                                                                                                        RegistroExistenteException
   {
      Validador.validaObjeto(fichaImovelRuralRebanhoVo);
      if (!Validador.isStringValida(fichaImovelRuralRebanhoVo.getRebanhoVo().getDescricaoRebanho()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_REBANHO_TIPO);
      }
      for (Iterator iteReb = fichaImovelRuralRebanhoVo.getCollVO().iterator(); iteReb.hasNext(); )
      {
         FichaImovelRuralRebanhoVo rebanho = (FichaImovelRuralRebanhoVo) iteReb.next();
         for (Iterator iteRebConsulta = fichaImovelRuralRebanhoVo.getCollVO().iterator(); iteRebConsulta.hasNext(); )
         {
            FichaImovelRuralRebanhoVo rebanhoConsulta = (FichaImovelRuralRebanhoVo) iteRebConsulta.next();
            validaDescricaoComplementarRebanho(rebanho, rebanhoConsulta);
         }
      }
      if (!Validador.isNumericoValido(fichaImovelRuralRebanhoVo.getQuantidadeRebanho()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_REBANHO_QUANTIDADE);
      }
      if (!Validador.isNumericoValido(fichaImovelRuralRebanhoVo.getValorMercado()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_REBANHO_VALOR_MERCADO);
      }
   }

   /**
    * Método responsável por validar a descrição complementar de um Rebanho da ficha de Imóvel Rural.
    * @param fichaImovelRuralRebanhoVo
    * @param fichaImovelRuralRebanhoConsultaVo
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private static void validaDescricaoComplementarRebanho(final FichaImovelRuralRebanhoVo fichaImovelRuralRebanhoVo, final FichaImovelRuralRebanhoVo fichaImovelRuralRebanhoConsultaVo) throws ObjetoObrigatorioException, 
                                                                                                                                                                                               ParametroObrigatorioException
   {
      Validador.validaObjeto(fichaImovelRuralRebanhoVo);
      Validador.validaObjeto(fichaImovelRuralRebanhoConsultaVo);
      if (fichaImovelRuralRebanhoVo.getDescricaoRebanho().equals(fichaImovelRuralRebanhoConsultaVo.getDescricaoRebanho()) && fichaImovelRuralRebanhoVo.getRebanhoVo().getCodigo() == fichaImovelRuralRebanhoConsultaVo.getRebanhoVo().getCodigo())
      {
         if (Validador.isStringValida(fichaImovelRuralRebanhoVo.getDescricaoRebanho()))
         {
            throw new ParametroObrigatorioException(MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_REBANHO_DESCRICAO_EXISTENTE);
         } else
         {
            throw new ParametroObrigatorioException(MensagemErro.VALIDAR_FICHA_IMOVEL_RURAL_REBANHO_DESCRICAO_VAZIA_EXISTENTE);
         }
      }
   }

   /**
    * Método responsável por adicionar na ficha de Imóvel Rural um Rebanho.
    * @param fichaImovelRuralRebanhoVo
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public static void adicionarRebanhoImovelRural(final FichaImovelRuralRebanhoVo fichaImovelRuralRebanhoVo) throws ObjetoObrigatorioException, 
                                                                                                                    ParametroObrigatorioException
   {
      Validador.validaObjeto(fichaImovelRuralRebanhoVo);
      Iterator itFicha = fichaImovelRuralRebanhoVo.getCollVO().iterator();
      while (itFicha.hasNext())
      {
         FichaImovelRuralRebanhoVo fichaImovelRuralRebanhoAtualVo = (FichaImovelRuralRebanhoVo) itFicha.next();
         validaDescricaoComplementarRebanho(fichaImovelRuralRebanhoVo, fichaImovelRuralRebanhoAtualVo);
      }
      calculaValorTotalRebanho(fichaImovelRuralRebanhoVo);
      fichaImovelRuralRebanhoVo.getCollVO().add(fichaImovelRuralRebanhoVo.clonePadrao());
      reinicializaRebanhoImovelRural(fichaImovelRuralRebanhoVo);
   }

   /**
    * 
    * @param fichaImovelRuralRebanhoVo
    * @return
    * @implemented by João Batista Padilha e Silva
    */
   public static void reinicializaRebanhoImovelRural(FichaImovelRuralRebanhoVo fichaImovelRuralRebanhoVo)
   {
      fichaImovelRuralRebanhoVo.setDescricaoRebanho("");
      fichaImovelRuralRebanhoVo.setQuantidadeRebanho(0);
      fichaImovelRuralRebanhoVo.setValorMercado(0.00);
      fichaImovelRuralRebanhoVo.getRebanhoVo().setCodigo(0);
      fichaImovelRuralRebanhoVo.getRebanhoVo().setDescricaoRebanho("");
      fichaImovelRuralRebanhoVo.setValorTotal(0);
   }

   /**
    * @param fichaImovelRuralRebanhoVo
    * @throws ObjetoObrigatorioException
    * @throws LogException
    * @throws AnotacaoException
    * @throws PersistenciaException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private synchronized void incluir(final FichaImovelRuralRebanhoVo fichaImovelRuralRebanhoVo) throws ObjetoObrigatorioException, 
                                                                                                       LogException, 
                                                                                                       AnotacaoException, 
                                                                                                       PersistenciaException
   {
      Validador.validaObjeto(fichaImovelRuralRebanhoVo);
      new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).insert(fichaImovelRuralRebanhoVo);
   }

   /**
    * Método para incluir uma Ficha Imóvel Rura Rebanho
    * 
    * @param fichaImovelRuralRebanhoVo
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    * @throws RegistroExistenteException
    * @throws ConexaoException
    * @throws LogException
    * @throws PersistenciaException
    * @throws AnotacaoException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public synchronized void incluirFichaImovelRuralRebanho(final FichaImovelRuralRebanhoVo fichaImovelRuralRebanhoVo) throws ObjetoObrigatorioException, 
                                                                                                                             ParametroObrigatorioException, 
                                                                                                                             RegistroExistenteException, 
                                                                                                                             ConexaoException, 
                                                                                                                             LogException, 
                                                                                                                             PersistenciaException, 
                                                                                                                             AnotacaoException
   {
      Validador.validaObjeto(fichaImovelRuralRebanhoVo);
      try
      {
         try
         {
            synchronized (FichaImovelRuralRebanhoVo.class)
            {
               validarIncluirFichaImovelRuralRebanho(fichaImovelRuralRebanhoVo);
               incluir(fichaImovelRuralRebanhoVo);
            }
         } catch (RegistroExistenteException e)
         {
            conn.rollback();
            throw e;
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
    * Método de consulta de uma Ficha Imóvel Rural Rebanho
    * @param fichaImovelRuralRebanhoVo
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @throws ConexaoException
    * @return FichaImovelRuralRebanhoVo
    * @implemented by Daniel Balieiro
    */
   public FichaImovelRuralRebanhoVo consultaFichaImovelRuralRebanho(final FichaImovelRuralRebanhoVo fichaImovelRuralRebanhoVo) throws ObjetoObrigatorioException, 
                                                                                                                                      ConsultaException, 
                                                                                                                                      ConexaoException
   {
      Validador.validaObjeto(fichaImovelRuralRebanhoVo);
      (new FichaImovelRuralRebanhoQDao(conn)).findFichaImovelRuralRebanho(fichaImovelRuralRebanhoVo);
      if (fichaImovelRuralRebanhoVo.isConsultaCompleta())
      {
         (new RebanhoBe(conn)).listarRebanho(fichaImovelRuralRebanhoVo.getRebanhoVo());
      }
      return fichaImovelRuralRebanhoVo;
   }

   /**
    * Método de listagem de Ficha Imóvel Rural Rebanho
    * 
    * @param fichaImovelRuralRebanhoVo
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @throws ConexaoException
    * @return FichaImovelRuralRebanhoVo
    * @implemented by Daniel Balieiro
    */
   public FichaImovelRuralRebanhoVo listarFichaImovelRuralRebanho(final FichaImovelRuralRebanhoVo fichaImovelRuralRebanhoVo) throws ObjetoObrigatorioException, 
                                                                                                                                    ConsultaException, 
                                                                                                                                    ConexaoException, 
                                                                                                                                    ParametroObrigatorioException
   {
      Validador.validaObjeto(fichaImovelRuralRebanhoVo);
      (new FichaImovelRuralRebanhoQDao(conn)).listFichaImovelRuralRebanho(fichaImovelRuralRebanhoVo);
      if (fichaImovelRuralRebanhoVo.isConsultaCompleta())
      {
         for (Iterator iteRural = fichaImovelRuralRebanhoVo.getCollVO().iterator(); iteRural.hasNext(); )
         {
            FichaImovelRuralRebanhoVo ficha = (FichaImovelRuralRebanhoVo) iteRural.next();
            RebanhoVo rebanho = new RebanhoVo(ficha.getRebanhoVo().getCodigo());
            rebanho = new RebanhoVo(rebanho);
            (new RebanhoBe(conn)).consultarRebanho(rebanho);
            ficha.setRebanhoVo(rebanho);
         }
      }
      return fichaImovelRuralRebanhoVo;
   }

   /**
    * Método responsável por realizar o cálculo do valor do rebanho.
    * @param fichaImovelRuralRebanhoVo
    * @throws ObjetoObrigatorioException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public static void calculaValorTotalRebanho(final FichaImovelRuralRebanhoVo fichaImovelRuralRebanhoVo) throws ObjetoObrigatorioException
   {
      Validador.validaObjeto(fichaImovelRuralRebanhoVo);
      if (fichaImovelRuralRebanhoVo.getQuantidadeRebanho() > 0 && fichaImovelRuralRebanhoVo.getValorMercado() > 0)
      {
         fichaImovelRuralRebanhoVo.setValorTotal(fichaImovelRuralRebanhoVo.getQuantidadeRebanho() * fichaImovelRuralRebanhoVo.getValorMercado());
      } else
      {
         fichaImovelRuralRebanhoVo.setValorTotal(0);
      }
   }

   /**
    * Método responsável por validar os dados necessários à alteração de um rebanho para uma ficha de imóvel rural.
    * @param fichaImovelRuralRebanhoVo
    * @throws ObjetoObrigatorioException
    * @throws DadoNecessarioInexistenteException
    * @throws ParametroObrigatorioException
    * @throws RegistroExistenteException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private void validarAlterarFichaImovelRuralRebanho(final FichaImovelRuralRebanhoVo fichaImovelRuralRebanhoVo) throws ObjetoObrigatorioException, 
                                                                                                                        DadoNecessarioInexistenteException, 
                                                                                                                        ParametroObrigatorioException, 
                                                                                                                        RegistroExistenteException
   {
      Validador.validaObjeto(fichaImovelRuralRebanhoVo);
      if (!Validador.isNumericoValido(fichaImovelRuralRebanhoVo.getCodigo()))
      {
         throw new DadoNecessarioInexistenteException(MensagemErro.CODIGO_FICHA_IMOVEL_RURAL_REBANHO_NECESSARIO_PARA_ALTERAR);
      }
      validarIncluirFichaImovelRuralRebanho(fichaImovelRuralRebanhoVo);
   }

   /**
    * @param fichaImovelRuralRebanhoVo
    * @throws ObjetoObrigatorioException
    * @throws LogException
    * @throws AnotacaoException
    * @throws PersistenciaException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private synchronized void alterar(final FichaImovelRuralRebanhoVo fichaImovelRuralRebanhoVo) throws ObjetoObrigatorioException, 
                                                                                                       LogException, 
                                                                                                       AnotacaoException, 
                                                                                                       PersistenciaException
   {
      Validador.validaObjeto(fichaImovelRuralRebanhoVo);
      new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).update(fichaImovelRuralRebanhoVo);
   }

   /**
    * Método responsável por realizar a alteração de um rebanho para uma ficha de imóvel rural.
    * @param fichaImovelRuralRebanhoVo
    * @throws ObjetoObrigatorioException
    * @throws DadoNecessarioInexistenteException
    * @throws ParametroObrigatorioException
    * @throws RegistroExistenteException
    * @throws ConexaoException
    * @throws LogException
    * @throws PersistenciaException
    * @throws AnotacaoException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public void alterarFichaImovelRuralRebanhoAlterarFichaImovelRural(final FichaImovelRuralRebanhoVo fichaImovelRuralRebanhoVo) throws ObjetoObrigatorioException, 
                                                                                                                                       DadoNecessarioInexistenteException, 
                                                                                                                                       ParametroObrigatorioException, 
                                                                                                                                       RegistroExistenteException, 
                                                                                                                                       ConexaoException, 
                                                                                                                                       LogException, 
                                                                                                                                       PersistenciaException, 
                                                                                                                                       AnotacaoException
   {
      Validador.validaObjeto(fichaImovelRuralRebanhoVo);
      try
      {
         try
         {
            synchronized (FichaImovelRuralRebanhoVo.class)
            {
               validarAlterarFichaImovelRuralRebanho(fichaImovelRuralRebanhoVo);
               alterar(fichaImovelRuralRebanhoVo);
            }
         } catch (DadoNecessarioInexistenteException e)
         {
            conn.rollback();
            throw e;
         } catch (ParametroObrigatorioException e)
         {
            conn.rollback();
            throw e;
         } catch (RegistroExistenteException e)
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
    * Método responsável por validar os dados necessários à exclusão de um rebanho para uma ficha de imóvel rural.
    * @param fichaImovelRuralRebanhoVo
    * @throws ObjetoObrigatorioException
    * @throws DadoNecessarioInexistenteException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private void validarExcluirFichaImovelRuralRebanho(final FichaImovelRuralRebanhoVo fichaImovelRuralRebanhoVo) throws ObjetoObrigatorioException, 
                                                                                                                        DadoNecessarioInexistenteException
   {
      Validador.validaObjeto(fichaImovelRuralRebanhoVo);
      if (!Validador.isNumericoValido(fichaImovelRuralRebanhoVo.getCodigo()))
      {
         throw new DadoNecessarioInexistenteException(MensagemErro.CODIGO_FICHA_IMOVEL_RURAL_REBANHO_NECESSARIO_PARA_EXCLUIR);
      }

   }

   /**
    * Método responsável por realizar a exclusão de um rebanho para uma ficha de imóvel rural.
    * @param fichaImovelRuralRebanhoVo
    * @throws ObjetoObrigatorioException
    * @throws ConexaoException
    * @throws DadoNecessarioInexistenteException
    * @throws LogException
    * @throws PersistenciaException
    * @throws AnotacaoException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public void exluirFichaImovelRuralRebanhoExcluirFichaImovelRural(final FichaImovelRuralRebanhoVo fichaImovelRuralRebanhoVo) throws ObjetoObrigatorioException, 
                                                                                                                                      ConexaoException, 
                                                                                                                                      DadoNecessarioInexistenteException, 
                                                                                                                                      LogException, 
                                                                                                                                      PersistenciaException, 
                                                                                                                                      AnotacaoException
   {
      Validador.validaObjeto(fichaImovelRuralRebanhoVo);
      try
      {
         try
         {
            synchronized (FichaImovelRuralRebanhoVo.class)
            {
               validarExcluirFichaImovelRuralRebanho(fichaImovelRuralRebanhoVo);
               excluir(fichaImovelRuralRebanhoVo);
            }
         } catch (DadoNecessarioInexistenteException e)
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


   public void excluirFichaImovelRuralRebanhoVo(BemTributavelVo bemModificado, BemTributavelVo bemOriginal) throws ObjetoObrigatorioException, 
                                                                                                                   ConexaoException, 
                                                                                                                   DadoNecessarioInexistenteException, 
                                                                                                                   LogException, 
                                                                                                                   PersistenciaException, 
                                                                                                                   AnotacaoException
   {
      //------------------------------ Exclusão da FichaImovelRuralRebanhoVo ----------------------------------------
      Iterator itFichaRebanhoOriginal = ((FichaImovelRuralVo) bemOriginal.getFichaImovelVo()).getFichaImovelRuralRebanhoVo().getCollVO().iterator();
      if (Validador.isObjetoValido(bemModificado) & Validador.isObjetoValido(bemOriginal))
      {
         while (itFichaRebanhoOriginal.hasNext())
         {
            boolean excluirRebanhoVo = true;
            FichaImovelRuralRebanhoVo bemRebanhoVoOriginal = (FichaImovelRuralRebanhoVo) itFichaRebanhoOriginal.next();
            Iterator itFichaRebanhoModificada = ((FichaImovelRuralVo) bemModificado.getFichaImovelVo()).getFichaImovelRuralRebanhoVo().getCollVO().iterator();

            while (itFichaRebanhoModificada.hasNext())
            {
               FichaImovelRuralRebanhoVo bemRebanhoVoModificada = (FichaImovelRuralRebanhoVo) itFichaRebanhoModificada.next();
               if (bemRebanhoVoOriginal.getCodigo() == bemRebanhoVoModificada.getCodigo())
               {
                  excluirRebanhoVo = false;
               }
            }
            if (excluirRebanhoVo)
            {
               exluirFichaImovelRuralRebanhoExcluirFichaImovelRural(bemRebanhoVoOriginal);
            }
         }
      }
   }

}
