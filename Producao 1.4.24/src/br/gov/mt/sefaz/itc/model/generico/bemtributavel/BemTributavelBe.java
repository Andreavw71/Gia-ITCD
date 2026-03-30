/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: BemTributavelBe.java
 * Revisão:
 * Data revisão:
 * $Id: BemTributavelBe.java,v 1.10 2009/05/05 19:54:24 ricardo.moraes Exp $
 */
package br.gov.mt.sefaz.itc.model.generico.bemtributavel;

import br.com.abaco.log5.GenericoLogAnotacaoDao;
import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.DadoNecessarioInexistenteException;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.RegistroExistenteException;
import br.com.abaco.util.exceptions.RegistroNaoPodeSerUtilizadoException;
import br.com.abaco.util.log.LogUtil;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.model.generico.avaliacao.AvaliacaoBemTributavelBe;
import br.gov.mt.sefaz.itc.model.generico.avaliacao.AvaliacaoBemTributavelVo;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichabem.ficharebanholpm.FichaRebanhoLPMBe;
import br.gov.mt.sefaz.itc.model.giaitcd.fichabem.ficharebanholpm.FichaRebanhoLPMVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichabem.fichaveiculo.FichaVeiculoBe;
import br.gov.mt.sefaz.itc.model.giaitcd.fichabem.fichaveiculo.FichaVeiculoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.FichaImovelRuralBe;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.FichaImovelRuralVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.benfeitoria.FichaImovelRuralBenfeitoriaBe;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.construcao.FichaImovelRuralConstrucaoBe;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.cultura.FichaImovelRuralCulturaBe;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.rebanho.FichaImovelRuralRebanhoBe;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelurbano.FichaImovelUrbanoBe;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelurbano.FichaImovelUrbanoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.GIAITCDInventarioArrolamentoVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.bem.BemBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.bem.BemVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnAtivoInativo;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoBem;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProtocolo;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoUsuario;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoVerificacao;
import br.gov.mt.sefaz.itc.util.facade.ConfiguracaoITCD;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;

import java.io.IOException;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Classe de negócio.
 * 
 * @author Marlo Eichenberg Motta
 * @version $Revision: 1.10 $
 */
public class BemTributavelBe extends AbstractBe
{
   /**
    * Construtor da classe recebendo uma conexão.
    * 
    * @param conexao		conexão a ser usada pela classe
    * @implemented by Marlo Eichenberg Motta
    */
   public BemTributavelBe(Connection conexao)
   {
      super(conexao);
   }

   /**
    * Construtor público padrão
    * Este construtor abre uma conexão com o banc ode dados do ITCD para suas validações.
    * @throws SQLException
    * @implemented by Leandro Dorileo
    */
   public BemTributavelBe() throws SQLException
   {
      super();
   }

   /**
    * Valida um objeto do tipo <code>BemTributavelVo</code>. Todos os atributos
    * são obrigatórios, refletindo o modelo de dados onde todas as colunas são
    * notnull.
    *
    * @param bemTributavelVo		VO de Bem Tributável a ser validada
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    * @implemented by Leandro Dorileo
    */
   public void validaParametrosIncluirAlterarBemTributavel(final BemTributavelVo bemTributavelVo) throws ObjetoObrigatorioException, 
                                                                                                         ParametroObrigatorioException
   {
      Validador.validaObjeto(bemTributavelVo);
      Validador.validaObjeto(bemTributavelVo.getGiaITCDVo());
      //GIA
      if (!Validador.isNumericoValido(bemTributavelVo.getGiaITCDVo().getCodigo()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_BEM_TRIBUTAVEL_PARAMETRO_CODIGO_ITC);
      }
      // BEM
      if (!Validador.isNumericoValido(bemTributavelVo.getBemVo().getCodigo()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_BEM_TRIBUTAVEL_PARAMETRO_TIPO);
      }
      //VALOR_MERCADO
      if (!Validador.isNumericoValido(bemTributavelVo.getValorMercado()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_BEM_TRIBUTAVEL_PARAMETRO_VALOR_MERCADO);
      }
      //DESCRICAO_BEM_TRIBUTAVEL
      if (!Validador.isStringValida(bemTributavelVo.getDescricaoBemTributavel()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_BEM_TRIBUTAVEL_PARAMETRO_DESCRICAO);
      }
      //INSENCAO_PREVISTA
      if (!Validador.isDominioNumericoValido(bemTributavelVo.getIsencaoPrevista()))
      {
         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_BEM_TRIBUTAVEL_PARAMETRO_ISENCAO_PREVISTA);
      }
      if (!Validador.isDominioNumericoValido(bemTributavelVo.getBemParticular()))
      {
         if (bemTributavelVo.getGiaITCDVo() instanceof GIAITCDInventarioArrolamentoVo)
         {
            throw new ParametroObrigatorioException(MensagemErro.VALIDAR_BEM_TRIBUTAVEL_PARAMETRO_BEM_PARTICULAR_GIA_ARROLAMENTO);
         } else
         {
            bemTributavelVo.setBemParticular(new DomnSimNao(DomnSimNao.NAO));
         }
      }
   }

   /**
    * Método responsável por realizar a inclusão de um bem tributável a partir da inclusão de uma GIA-ITCD.
    * @param bemTributavelVo
    * @throws ObjetoObrigatorioException
    * @throws ConexaoException
    * @throws RegistroExistenteException
    * @throws IntegracaoException
    * @throws ParametroObrigatorioException
    * @throws LogException
    * @throws PersistenciaException
    * @throws AnotacaoException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public void incluirBemTributavelIncluirGIAITCD(final BemTributavelVo bemTributavelVo) throws ObjetoObrigatorioException, 
                                                                                                RegistroExistenteException, 
                                                                                                IntegracaoException, 
                                                                                                ParametroObrigatorioException, 
                                                                                                LogException, 
                                                                                                PersistenciaException, 
                                                                                                AnotacaoException, 
                                                                                                ConexaoException, ConsultaException
   {
      Validador.validaObjeto(bemTributavelVo);
      try
      {
         try
         {
            synchronized (BemTributavelVo.class)
            {
               Iterator it = bemTributavelVo.getCollVO().iterator();
               while (it.hasNext())
               {
                  BemTributavelVo bemAtual = (BemTributavelVo) it.next();
                  bemAtual.setGiaITCDVo(bemTributavelVo.getGiaITCDVo());
                  bemAtual.setLogSefazVo(bemTributavelVo.getGiaITCDVo().getLogSefazVo());
                  incluirBemTributavel(bemAtual);
               }
            }
         } catch (RegistroExistenteException e)
         {
            conn.rollback();
            throw e;
         } catch (IntegracaoException e)
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
    * @param bemTributavelVo
    * @throws ObjetoObrigatorioException
    * @throws LogException
    * @throws AnotacaoException
    * @throws PersistenciaException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private synchronized void incluir(final BemTributavelVo bemTributavelVo) throws ObjetoObrigatorioException, 
                                                                                   LogException, 
                                                                                   AnotacaoException, 
                                                                                   PersistenciaException
   {
      Validador.validaObjeto(bemTributavelVo);
      new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).insert(bemTributavelVo);
   }


   /**
    * Inclui os dados de um Bem Tributável no banco de dados.
    * Antes de inserir o Bem Tributável é feito uma verificação de acordo com o caso
    * de uso Incluir GIA - ITCD - Inventário/Arrolamento fluxo de exceção 14:4 que diz
    * que deve haver somente um Bem Tributável com uma determina descrição para cada GIA.
    * 
    * @param bemTributavelVo VO de Bem Tributável (Value Object).
    * @throws ObjetoObrigatorioException
    * @throws RegistroExistenteException
    * @throws ParametroObrigatorioException
    * @throws ConsultaException
    * @throws ConexaoException
    * @throws IntegracaoException
    * @throws LogException
    * @throws PersistenciaException
    * @throws AnotacaoException
    * @implemented by Leandro Dorileo
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private void incluirBemTributavel(final BemTributavelVo bemTributavelVo) throws ObjetoObrigatorioException, 
                                                                                   RegistroExistenteException, 
                                                                                   IntegracaoException, 
                                                                                   ParametroObrigatorioException, 
                                                                                   LogException, 
                                                                                   PersistenciaException, 
                                                                                   AnotacaoException, 
                                                                                   ConexaoException, ConsultaException
   {
      Validador.validaObjeto(bemTributavelVo);
      try
      {
         try
         {
            synchronized (BemTributavelVo.class)
            {
               validaParametrosIncluirAlterarBemTributavel(bemTributavelVo);
               incluir(bemTributavelVo);
               bemTributavelVo.getFichaImovelVo().setBemTributavelVo(bemTributavelVo);
               if(Validador.isObjetoValido(bemTributavelVo.getFichaVo()))
               {
                  bemTributavelVo.getFichaVo().setBemTributavelVo(bemTributavelVo);
               }
               if (bemTributavelVo.getBemVo().getClassificacaoTipoBem().is(DomnTipoBem.IMOVEL_RURAL))
               {
                  FichaImovelRuralVo ficha = (FichaImovelRuralVo) bemTributavelVo.getFichaImovelVo();
                  ficha.setLogSefazVo(bemTributavelVo.getLogSefazVo());
                  (new FichaImovelRuralBe(conn)).incluirFichaImovelRural(ficha);
               } else if (bemTributavelVo.getBemVo().getClassificacaoTipoBem().is(DomnTipoBem.IMOVEL_URBANO))
               {
                  FichaImovelUrbanoVo ficha = (FichaImovelUrbanoVo) bemTributavelVo.getFichaImovelVo();
                  ficha.setLogSefazVo(bemTributavelVo.getLogSefazVo());
                  (new FichaImovelUrbanoBe(conn)).incluirFichaImovelUrbano(ficha);
               }else if(bemTributavelVo.getBemVo().getClassificacaoTipoBem().is(DomnTipoBem.OUTROS_BENS))
               {
                  if(bemTributavelVo.getBemVo().getTipoVerificacao().is(DomnTipoVerificacao.IPVA))
                  {
                     FichaVeiculoVo ficha = (FichaVeiculoVo) bemTributavelVo.getFichaVo();
                     ficha.setLogSefazVo(bemTributavelVo.getLogSefazVo());
                     (new FichaVeiculoBe(conn)).incluirVeiculo(ficha);
                  }else if(bemTributavelVo.getBemVo().getTipoVerificacao().is(DomnTipoVerificacao.REBANHO))
                  {
                     FichaRebanhoLPMVo ficha = (FichaRebanhoLPMVo) bemTributavelVo.getFichaVo();
                     ficha.setLogSefazVo(bemTributavelVo.getLogSefazVo());
                     (new FichaRebanhoLPMBe(conn)).incluirRebanho(ficha);
                  }
               }
            }
         } catch (RegistroExistenteException e)
         {
            conn.rollback();
            throw e;
         } catch (IntegracaoException e)
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
    * Método responsável por alterar um bem tributável para uma GIA ITCD.
    * @param bemTributavelVo
    * @throws ObjetoObrigatorioException
    * @throws ConexaoException
    * @throws ConsultaException
    * @throws IntegracaoException
    * @throws ParametroObrigatorioException
    * @throws RegistroExistenteException
    * @throws DadoNecessarioInexistenteException
    * @throws AnotacaoException
    * @throws PersistenciaException
    * @throws LogException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public synchronized void alterarBemTributavelAlterarGIAITCD(final BemTributavelVo bemTributavelVo) throws ObjetoObrigatorioException, 
                                                                                                             ConexaoException, 
                                                                                                             RegistroExistenteException, 
                                                                                                             ParametroObrigatorioException, 
                                                                                                             IntegracaoException, 
                                                                                                             AnotacaoException, 
                                                                                                             PersistenciaException, 
                                                                                                             LogException, 
                                                                                                             ConsultaException, 
                                                                                                             DadoNecessarioInexistenteException, IOException
   {
      Validador.validaObjeto(bemTributavelVo);
      try
      {
         try
         {
            synchronized (BemTributavelVo.class)
            {
               // BENS TRIBUTÁVEIS
               BemTributavelVo bemTributavelConsultaVo = new BemTributavelVo();
               BemTributavelVo bemTributavelParametroVo = new BemTributavelVo();
               bemTributavelParametroVo.getGiaITCDVo().setCodigo(bemTributavelVo.getGiaITCDVo().getCodigo());
               //bemTributavelParametroVo.setTipoUsuario(new DomnTipoUsuario(DomnTipoUsuario.SERVIDOR));
               bemTributavelConsultaVo.setParametroConsulta(bemTributavelParametroVo);
               bemTributavelConsultaVo.setConsultaCompleta(true);
               bemTributavelConsultaVo = listarItemBemTributavel(bemTributavelConsultaVo);
               Collection bemTributavelOriginalVo = bemTributavelConsultaVo.getCollVO();
               Collection bemTributavelInserirVo = null;
               Collection bemTributavelAlterarVo = null;
               //Collection bemTributavelServidorVo = solicitarFiltrarBensTributaveis(bemTributavelVo);
               Collection bemTributavelServidorVo = bemTributavelVo.getCollVO();
               //INSERIR BEM TRIBUTAVEL
               bemTributavelInserirVo = LogUtil.obterListaObjetosParaInclusao(bemTributavelOriginalVo, bemTributavelServidorVo);
               if (Validador.isCollectionValida(bemTributavelInserirVo))
               {
                  Iterator it = bemTributavelInserirVo.iterator();
                  while (it.hasNext())
                  {
                     BemTributavelVo bemAtual = (BemTributavelVo) it.next();
                     bemAtual.setGiaITCDVo(bemTributavelVo.getGiaITCDVo());
                     bemAtual.setLogSefazVo(bemTributavelVo.getLogSefazVo());
                     incluirBemTributavel(bemAtual);
                  }
               }
               //ALTERAR BEM TRIBUTAVEL
               bemTributavelAlterarVo = LogUtil.obterListaObjetosParaAlteracao(bemTributavelOriginalVo, bemTributavelServidorVo);
               if (Validador.isCollectionValida(bemTributavelAlterarVo))
               {
                  Iterator it = bemTributavelAlterarVo.iterator();
                  while (it.hasNext())
                  {
                     BemTributavelVo bemAtual = (BemTributavelVo) it.next();
                     bemAtual.setGiaITCDVo(bemTributavelVo.getGiaITCDVo());
                     bemAtual.setLogSefazVo(bemTributavelVo.getLogSefazVo());
                     alterarBemTributavel(bemAtual);
                  }
               }
            }
         } catch (ConsultaException e)
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
         } catch (IntegracaoException e)
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
         } catch (DadoNecessarioInexistenteException e)
         {
            conn.rollback();
            throw e;
         }
      } catch (SQLException e)
      {
         throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
      }
   }

   /**
    * Método responsável por excluir um bem tributável na alteração de uma GIA ITCD.
    * @param bemTributavelVo
    * @throws ObjetoObrigatorioException
    * @throws ConexaoException
    * @throws DadoNecessarioInexistenteException
    * @throws ConsultaException
    * @throws IntegracaoException
    * @throws ParametroObrigatorioException
    * @throws LogException
    * @throws PersistenciaException
    * @throws AnotacaoException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public synchronized void alterarBemTributavelAlterarGIAITCDExcluir(final BemTributavelVo bemTributavelVo) throws ObjetoObrigatorioException, 
                                                                                                                    ConexaoException, 
                                                                                                                    DadoNecessarioInexistenteException, 
                                                                                                                    ConsultaException, 
                                                                                                                    IntegracaoException, 
                                                                                                                    ParametroObrigatorioException, 
                                                                                                                    LogException, 
                                                                                                                    PersistenciaException, 
                                                                                                                    AnotacaoException, IOException
   {
      Validador.validaObjeto(bemTributavelVo);
      try
      {
         try
         {
            synchronized (BemTributavelVo.class)
            {
               // Lista BENS TRIBUTAVEIS pertencentes a GIA.
               BemTributavelVo bemTributavelConsultaVo = new BemTributavelVo();
               BemTributavelVo bemTributavelParametroVo = new BemTributavelVo();
               bemTributavelParametroVo.getGiaITCDVo().setCodigo(bemTributavelVo.getGiaITCDVo().getCodigo());
               //					bemTributavelParametroVo.setTipoUsuario(new DomnTipoUsuario(DomnTipoUsuario.SERVIDOR));
               bemTributavelConsultaVo.setParametroConsulta(bemTributavelParametroVo);
               bemTributavelConsultaVo.setConsultaCompleta(true);
               bemTributavelConsultaVo = listarItemBemTributavel(bemTributavelConsultaVo);
               Collection bemTributavelOriginalVo = bemTributavelConsultaVo.getCollVO();
               Collection bemTributavelExcluirVo = null;
               //					Collection bemTributavelServidorVo = solicitarFiltrarBensTributaveis(bemTributavelVo);
               Collection bemTributavelServidorVo = bemTributavelVo.getCollVO();
               //EXCLUIR BEM TRIBUTAVEL
               bemTributavelExcluirVo = LogUtil.obterListaObjetosParaExclusao(bemTributavelOriginalVo, bemTributavelServidorVo);

               // Método Adicional para excluir ficha do bens.
               excluirFicha(bemTributavelVo, bemTributavelConsultaVo);

               if (Validador.isCollectionValida(bemTributavelExcluirVo))
               {
                  Iterator it = bemTributavelExcluirVo.iterator();
                  while (it.hasNext())
                  {
                     BemTributavelVo bemAtual = (BemTributavelVo) it.next();
                     bemAtual.setGiaITCDVo(bemTributavelVo.getGiaITCDVo());
                     bemAtual.setLogSefazVo(bemTributavelVo.getLogSefazVo());
                     // Altera para INATIVO
                     bemAtual.setStatusBemTributavel(new DomnAtivoInativo(DomnAtivoInativo.INATIVO));
                     excluirBemTributavel(bemAtual);
                  }
               }
            }
         } catch (ConsultaException e)
         {
            conn.rollback();
            throw e;
         } catch (DadoNecessarioInexistenteException e)
         {
            conn.rollback();
            throw e;
         } catch (IntegracaoException e)
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
         throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
      }
   }


   /**
    * Método responsável por remover da Collection de bens tributáveis os bens inseridos pelo contribuinte, uma vez que estes não podem ser alterados.
    * @param bemTributavelVo
    * @return Collection
    * @throws ObjetoObrigatorioException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private Collection solicitarFiltrarBensTributaveis(final BemTributavelVo bemTributavelVo) throws ObjetoObrigatorioException
   {
      Validador.validaObjeto(bemTributavelVo);
      Collection retorno = new ArrayList();
      if (Validador.isCollectionValida(bemTributavelVo.getCollVO()))
      {
         Iterator it = bemTributavelVo.getCollVO().iterator();
         while (it.hasNext())
         {
            BemTributavelVo bemAtual = (BemTributavelVo) it.next();
            if (!bemAtual.getTipoUsuario().is(DomnTipoUsuario.CONTRIBUINTE))
            {
               retorno.add(bemAtual);
            }
         }
      }
      return retorno;
   }


   /**
    * Lista todos os registros de Bem Tributável que se enquadrem com os parametros
    * fornecidos.
    * 
    * @param bemTributavelVo VO de Bem Tributável(Value Object).
    * @return BemTributavelVo
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @throws ConexaoException
    * @throws IntegracaoException
    * @throws ParametroObrigatorioException
    * @implemented by Leandro Dorileo
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public BemTributavelVo listarItemBemTributavel(final BemTributavelVo bemTributavelVo) throws ObjetoObrigatorioException, 
                                                                                                ConsultaException, 
                                                                                                ConexaoException, 
                                                                                                IntegracaoException, 
                                                                                                ParametroObrigatorioException, SQLException, 
             IOException
   {
      Validador.validaObjeto(bemTributavelVo);
      (new BemTributavelQDao(conn)).listBemTributavel(bemTributavelVo);
      for (Iterator iteBem = bemTributavelVo.getCollVO().iterator(); iteBem.hasNext(); )
      {
         BemTributavelVo bemTributavel = (BemTributavelVo) iteBem.next();
         if ((((BemTributavelVo) bemTributavelVo.getParametroConsulta()).getGiaITCDVo()).isExiste())
         {
            bemTributavel.setGiaITCDVo(((BemTributavelVo) bemTributavelVo.getParametroConsulta()).getGiaITCDVo());
         }
         if (bemTributavelVo.isConsultaCompleta())
         {
            if (Validador.isNumericoValido(bemTributavel.getBemVo().getCodigo()))
            {
               BemVo bemVo = new BemVo();
               bemVo.setCodigo(bemTributavel.getBemVo().getCodigo());
               bemVo = new BemVo(bemVo);
               (new BemBe(conn)).consultarBem(bemVo);
               bemTributavel.setBemVo(bemVo);
            }
            AvaliacaoBemTributavelVo avaliacao = new AvaliacaoBemTributavelVo();
            avaliacao.getBemTributavel().setCodigo(bemTributavel.getCodigo());
            avaliacao = new AvaliacaoBemTributavelVo(avaliacao);
            avaliacao.setConsultaCompleta(true);
            (new AvaliacaoBemTributavelBe(conn)).consultarAvaliacaoBemTributavel(avaliacao);
            bemTributavel.setAvaliacaoBemTributavelVo(avaliacao);
            if (bemTributavel.getBemVo().getClassificacaoTipoBem().is(DomnTipoBem.IMOVEL_RURAL))
            {
               FichaImovelRuralVo fichaImovelRuralVo = new FichaImovelRuralVo();
               fichaImovelRuralVo.setBemTributavelVo(bemTributavel);
               fichaImovelRuralVo = new FichaImovelRuralVo(fichaImovelRuralVo);
               fichaImovelRuralVo.setConsultaCompleta(true);
               (new FichaImovelRuralBe(conn)).consultarFichaImovelRural(fichaImovelRuralVo);
               bemTributavel.setFichaImovelVo(fichaImovelRuralVo);
               fichaImovelRuralVo.setBemTributavelVo(bemTributavel);
            } else if (bemTributavel.getBemVo().getClassificacaoTipoBem().is(DomnTipoBem.IMOVEL_URBANO))
            {
               FichaImovelUrbanoVo fichaImovelUrbanoVo = new FichaImovelUrbanoVo();
               fichaImovelUrbanoVo.setBemTributavelVo(bemTributavel);
               fichaImovelUrbanoVo = new FichaImovelUrbanoVo(fichaImovelUrbanoVo);
               fichaImovelUrbanoVo.setConsultaCompleta(true);
               (new FichaImovelUrbanoBe(conn)).consultarFichaImovelUrbano(fichaImovelUrbanoVo);
               bemTributavel.setFichaImovelVo(fichaImovelUrbanoVo);
               fichaImovelUrbanoVo.setBemTributavelVo(bemTributavel);
            }
            else if (bemTributavel.getBemVo().getClassificacaoTipoBem().is(DomnTipoBem.OUTROS_BENS))
            {
               if (bemTributavel.getBemVo().getTipoVerificacao().is(DomnTipoVerificacao.IPVA))
               {
                  FichaVeiculoVo fichaVeiculoVo = new FichaVeiculoVo();
                  fichaVeiculoVo.setBemTributavelVo(bemTributavel);
                  fichaVeiculoVo = new FichaVeiculoVo(fichaVeiculoVo);
                  fichaVeiculoVo.setConsultaCompleta(Boolean.TRUE);
                  (new FichaVeiculoBe(conn)).consultarVeiculo(fichaVeiculoVo);
                  bemTributavel.setFichaVo(fichaVeiculoVo);
               }
               else if (bemTributavel.getBemVo().getTipoVerificacao().is(DomnTipoVerificacao.REBANHO))
               {
                  FichaRebanhoLPMVo fichaRebanhoLPMVo = new FichaRebanhoLPMVo();
                  fichaRebanhoLPMVo.setBemTributavelVo(bemTributavel);
                  fichaRebanhoLPMVo = new FichaRebanhoLPMVo(fichaRebanhoLPMVo);
                  fichaRebanhoLPMVo.setConsultaCompleta(Boolean.TRUE);
                  (new FichaRebanhoLPMBe(conn)).consultarRebanhoLPMVo(fichaRebanhoLPMVo);
                  bemTributavel.setFichaVo(fichaRebanhoLPMVo);
               }
            }
            if (!bemTributavel.getGiaITCDVo().isExiste())
            {
               GIAITCDVo giaITCDVo = new GIAITCDVo();
               giaITCDVo.setBemTributavelVo(bemTributavel);
               giaITCDVo = new GIAITCDVo(giaITCDVo);
               (new GIAITCDBe(conn)).consultarGIAITCDAtiva(giaITCDVo);
               bemTributavel.setGiaITCDVo(giaITCDVo);
            }
         }
      }
      return bemTributavelVo;
   }

   /**
    * @param bemTributavelVo
    * @throws ObjetoObrigatorioException
    * @throws LogException
    * @throws AnotacaoException
    * @throws PersistenciaException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private synchronized void alterar(final BemTributavelVo bemTributavelVo) throws ObjetoObrigatorioException, 
                                                                                   LogException, 
                                                                                   AnotacaoException, 
                                                                                   PersistenciaException
   {
      Validador.validaObjeto(bemTributavelVo);
      new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).update(bemTributavelVo);
   }

   /**
    * Modifica os dados de um determinado Bem Tributável no banco de dados. De acordo
    * com o caso de uso Incluir GIA - ITCD - Inventário/Arrolamento no Fluxo de Exceção
    * 14:4 não deve haver dois Bens Tributáveis com a mesma descrição para a mesma GIA
    * para tanto antes de alterar o Bem Tributável em questão é feita uma consulta usando
    * a descrição e o código da gia como parametro e então verificamos se o Bem Tributável
    * encontrado não se refere ao que está sendo modificado.
    * 
    * @param bemTributavelVo VO de Bem Tributável(Value Object).
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    * @throws ConsultaException
    * @throws RegistroExistenteException
    * @throws ConexaoException
    * @throws IntegracaoException
    * @throws DadoNecessarioInexistenteException
    * @throws LogException
    * @throws PersistenciaException
    * @throws AnotacaoException
    * @implemented by Leandro Dorileo
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private void alterarBemTributavel(final BemTributavelVo bemTributavelVo) throws ObjetoObrigatorioException, 
                                                                                   ParametroObrigatorioException, 
                                                                                   ConexaoException, 
                                                                                   IntegracaoException, 
                                                                                   ConsultaException, 
                                                                                   RegistroExistenteException, 
                                                                                   DadoNecessarioInexistenteException, 
                                                                                   LogException, 
                                                                                   PersistenciaException, 
                                                                                   AnotacaoException, 
             IOException
   {
      Validador.validaObjeto(bemTributavelVo);
      validaParametrosIncluirAlterarBemTributavel(bemTributavelVo);
      try
      {
         try
         {
            synchronized (BemTributavelVo.class)
            {
               validaParametrosIncluirAlterarBemTributavel(bemTributavelVo);
               BemTributavelVo bemConsultaVo = new BemTributavelVo();
               bemConsultaVo.setDescricaoBemTributavel(bemTributavelVo.getDescricaoBemTributavel());
               bemConsultaVo.getBemVo().setCodigo(bemTributavelVo.getBemVo().getCodigo());
               bemConsultaVo.setGiaITCDVo(bemTributavelVo.getGiaITCDVo());
               bemConsultaVo = new BemTributavelVo(bemConsultaVo);
               consultarBemTributavel(bemConsultaVo);
               if (bemConsultaVo.isExiste() && bemTributavelVo.getCodigo() == bemConsultaVo.getCodigo())
               {
                  alterar(bemTributavelVo);
                  bemTributavelVo.getFichaImovelVo().setBemTributavelVo(bemTributavelVo);
                  if (bemTributavelVo.getBemVo().getClassificacaoTipoBem().is(DomnTipoBem.IMOVEL_RURAL))
                  {
                     FichaImovelRuralVo ficha = (FichaImovelRuralVo) bemTributavelVo.getFichaImovelVo();
                     ficha.setLogSefazVo(bemTributavelVo.getLogSefazVo());
                     (new FichaImovelRuralBe(conn)).alterarFichaImovelRuralAlterarBemTributavel(ficha);
                  } else if (bemTributavelVo.getBemVo().getClassificacaoTipoBem().is(DomnTipoBem.IMOVEL_URBANO))
                  {
                     FichaImovelUrbanoVo ficha = (FichaImovelUrbanoVo) bemTributavelVo.getFichaImovelVo();
                     ficha.setLogSefazVo(bemTributavelVo.getLogSefazVo());
                     (new FichaImovelUrbanoBe(conn)).alterarFichaImovelUrbanoAlterarBemTributavel(ficha);
                  }
               } else
               {
                  throw new RegistroExistenteException(MensagemErro.VALIDAR_BEM_TRIBUTAVEL_INCLUIR_DESCRICAO_E_GIA);
               }
            }
         } catch (ParametroObrigatorioException e)
         {
            conn.rollback();
            throw e;
         } catch (IntegracaoException e)
         {
            conn.rollback();
            throw e;
         } catch (ConsultaException e)
         {
            conn.rollback();
            throw e;
         } catch (RegistroExistenteException e)
         {
            conn.rollback();
            throw e;
         } catch (DadoNecessarioInexistenteException e)
         {
            conn.rollback();
            throw e;
         } catch (ConexaoException e)
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
         catch (IOException e)
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
    * @param bemTributavelVo
    * @throws ObjetoObrigatorioException
    * @throws LogException
    * @throws AnotacaoException
    * @throws PersistenciaException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   private synchronized void excluir(final BemTributavelVo bemTributavelVo) throws ObjetoObrigatorioException, 
                                                                                   LogException, 
                                                                                   AnotacaoException, 
                                                                                   PersistenciaException
   {
      Validador.validaObjeto(bemTributavelVo);
      new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).delete(bemTributavelVo);
   }

   /**
    * Remove um registro de Bem tributável do banco de dados.
    * 
    * @param bemTributavelVo
    * @throws ObjetoObrigatorioException
    * @throws ConexaoException
    * @throws DadoNecessarioInexistenteException
    * @throws LogException
    * @throws PersistenciaException
    * @throws AnotacaoException
    * @implemented by Leandro Dorileo
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public void excluirBemTributavel(final BemTributavelVo bemTributavelVo) throws ObjetoObrigatorioException, 
                                                                                  ConexaoException, 
                                                                                  DadoNecessarioInexistenteException, 
                                                                                  LogException, 
                                                                                  PersistenciaException, 
                                                                                  AnotacaoException
   {
      Validador.validaObjeto(bemTributavelVo);
      try
      {
         try
         {
            if (Validador.isNumericoValido(bemTributavelVo.getCodigo()))
            {
               if (bemTributavelVo.getBemVo().getClassificacaoTipoBem().is(DomnTipoBem.IMOVEL_RURAL))
               {
                  FichaImovelRuralVo ficha = (FichaImovelRuralVo) bemTributavelVo.getFichaImovelVo();
                  ficha.setLogSefazVo(bemTributavelVo.getLogSefazVo());
                  (new FichaImovelRuralBe(conn)).excluirFichaImovelRuralAlterarBemTributavel(ficha);
               } else if (bemTributavelVo.getBemVo().getClassificacaoTipoBem().is(DomnTipoBem.IMOVEL_URBANO))
               {
                  FichaImovelUrbanoVo ficha = (FichaImovelUrbanoVo) bemTributavelVo.getFichaImovelVo();
                  ficha.setLogSefazVo(bemTributavelVo.getLogSefazVo());
                  (new FichaImovelUrbanoBe(conn)).excluirFichaImovelUrbanoAlterarBemTributavel(ficha);
               }
               //excluir(bemTributavelVo);
               // Grava o Bem com status INATIVO que é equivalente a excluir.
               alterar(bemTributavelVo);
            } else
            {
               throw new DadoNecessarioInexistenteException(MensagemErro.EXCLUIR_BEM_TRIBUTAVEL_CHAVE);
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

   /**
    * Consulta as informações de um Bem Tributável no banco de dados.
    * @param bemTributavelVo VO de Bem Tributável(Value Object).
    * @return BemTributavelVo
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException
    * @throws ConexaoException
    * @throws IntegracaoException
    * @throws ParametroObrigatorioException
    * @implemented by Leandro Dorileo
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public BemTributavelVo consultarBemTributavel(final BemTributavelVo bemTributavelVo) throws ObjetoObrigatorioException, 
                                                                                               ConsultaException, 
                                                                                               ConexaoException, 
                                                                                               IntegracaoException, 
                                                                                               ParametroObrigatorioException, SQLException, 
             IOException
   {
      Validador.validaObjeto(bemTributavelVo);
      (new BemTributavelQDao(conn)).findBemTributavel(bemTributavelVo);
      if (bemTributavelVo.isConsultaCompleta())
      {
         if (Validador.isNumericoValido(bemTributavelVo.getBemVo().getCodigo()))
         {
            BemVo bemParametroVo = new BemVo(bemTributavelVo.getBemVo().getCodigo());
            BemVo bemConsultaVo = new BemVo(bemParametroVo);
            (new BemBe(conn)).consultarBem(bemConsultaVo);
            bemTributavelVo.setBemVo(bemConsultaVo);
         }
         /*** AVALIAÇÃO */
         AvaliacaoBemTributavelVo avaliacaoParametroVo = new AvaliacaoBemTributavelVo();
         avaliacaoParametroVo.setBemTributavel(bemTributavelVo);
         AvaliacaoBemTributavelVo avaliacaoConsultaVo = new AvaliacaoBemTributavelVo(avaliacaoParametroVo);
         avaliacaoConsultaVo.setConsultaCompleta(true);
         (new AvaliacaoBemTributavelBe(conn)).consultarAvaliacaoBemTributavel(avaliacaoConsultaVo);
         bemTributavelVo.setAvaliacaoBemTributavelVo(avaliacaoConsultaVo);
         if (Validador.isNumericoValido(bemTributavelVo.getFichaImovelVo().getCodigo()))
         {
            /*** FICHA DE IMÓVEL RURAL */
            if (bemTributavelVo.getFichaImovelVo() instanceof FichaImovelRuralVo)
            {
               FichaImovelRuralVo fichaImovelRuralVo = new FichaImovelRuralVo();
               fichaImovelRuralVo.setBemTributavelVo(bemTributavelVo);
               fichaImovelRuralVo = new FichaImovelRuralVo(fichaImovelRuralVo);
               fichaImovelRuralVo.setConsultaCompleta(true);
               (new FichaImovelRuralBe(conn)).consultarFichaImovelRural(fichaImovelRuralVo);
               bemTributavelVo.setFichaImovelVo(fichaImovelRuralVo);
            }
            /*** FICHA DE IMÓVEL URBANO */
            else if (bemTributavelVo.getFichaImovelVo() instanceof FichaImovelUrbanoVo)
            {
               FichaImovelUrbanoVo fichaImovelUrbanoVo = new FichaImovelUrbanoVo();
               fichaImovelUrbanoVo.setBemTributavelVo(bemTributavelVo);
               fichaImovelUrbanoVo = new FichaImovelUrbanoVo(fichaImovelUrbanoVo);
               fichaImovelUrbanoVo.setConsultaCompleta(true);
               (new FichaImovelUrbanoBe(conn)).consultarFichaImovelUrbano(fichaImovelUrbanoVo);
               bemTributavelVo.setFichaImovelVo(fichaImovelUrbanoVo);
            }
         }
         /*** GIA ITCD */
         //			if(Validador.isNumericoValido(bemTributavelVo.getGiaITCDVo().getCodigo()))
         //			{
         //			   GIAITCDVo giaITCDParametroVo = new GIAITCDVo();
         //			   giaITCDParametroVo.setCodigo(bemTributavelVo.getGiaITCDVo().getCodigo());
         //			   GIAITCDVo giaITCDConsultaVo = new GIAITCDVo(giaITCDParametroVo);
         //			   giaITCDConsultaVo.setConsultaCompleta(false);
         //			   (new GIAITCDBe(conn)).consultarGIAITCD(giaITCDConsultaVo);
         //			   bemTributavelVo.setGiaITCDVo(giaITCDConsultaVo);				
         //			}
      }
      return bemTributavelVo;
   }

   /**
    * Valida se essa descrição já esta cadastrada para esse bem tributável
    * @param bemTributavelVo
    * @throws ParametroObrigatorioException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public static void validaDescricaoDuplicada(BemTributavelVo bemTributavelVo) throws ParametroObrigatorioException
   {
      for (Iterator iteBem = bemTributavelVo.getCollVO().iterator(); iteBem.hasNext(); )
      {
         BemTributavelVo atual = (BemTributavelVo) iteBem.next();
         if (atual.getDescricaoBemTributavel().equals(bemTributavelVo.getDescricaoBemTributavel()) && (atual.getBemVo().getClassificacaoTipoBem().getValorCorrente() == bemTributavelVo.getBemVo().getClassificacaoTipoBem().getValorCorrente()) && (atual.getBemVo().getDescricaoTipoBem().equals(bemTributavelVo.getBemVo().getDescricaoTipoBem())))
         {
            throw new ParametroObrigatorioException(MensagemErro.VALIDAR_BEM_INCLUIR_DESCRICAO_E_TIPO);
         }
      }
   }

   /**
    * Adiciona um bem tribuitável em uma determinada Collection e valida a descrição para
    * garantir que as descrições dos bens tributáveis são únicas.
    * 
    * @param listaBemTributavel lista onde será adicionado o BemTributavel
    * @param bemTributavelVo Bem Tributável a ser adicionado na lista
    * @return Collection
    * @throws ParametroObrigatorioException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public static Collection adicionaBemTributavel(Collection listaBemTributavel, BemTributavelVo bemTributavelVo) throws ParametroObrigatorioException
   {
      if (!Validador.isCollectionValida(listaBemTributavel))
      {
         listaBemTributavel = new ArrayList();
      }
      Iterator it = listaBemTributavel.iterator();
      while (it.hasNext())
      {
         BemTributavelVo atual = (BemTributavelVo) it.next();        
         if (atual.getDescricaoBemTributavel().equals(bemTributavelVo.getDescricaoBemTributavel()) && (atual.getBemVo().getClassificacaoTipoBem().getValorCorrente() == bemTributavelVo.getBemVo().getClassificacaoTipoBem().getValorCorrente()) && (atual.getBemVo().getDescricaoTipoBem().equals(bemTributavelVo.getBemVo().getDescricaoTipoBem())))
         {
            throw new ParametroObrigatorioException(MensagemErro.VALIDAR_BEM_INCLUIR_DESCRICAO_E_TIPO);
         }
      }
      listaBemTributavel.add(bemTributavelVo);
      return listaBemTributavel;
      
   }
   
   /**
    * Adiciona um bem tribuitável em uma determinada Collection e valida a descrição para
    * garantir que as descrições dos bens tributáveis são únicas.
    * 
    * @param bemTributavelVoColl lista onde será adicionado o BemTributavel
    * @param bemTributavelVo Bem Tributável a ser adicionado na lista
    * @return Collection
    * @throws ParametroObrigatorioException
    * @implemented by Dherkyan Ribeiro da Silva.
    */
   public static Collection adicionaBemTributavelUtil(BemTributavelVo bemTributavelVoColl , BemTributavelVo bemTributavelVo) throws ParametroObrigatorioException
   {
      Collection listaBemTributavel = bemTributavelVoColl.getCollVO();
      if (!Validador.isObjetoValido(listaBemTributavel))
      {
         listaBemTributavel = new ArrayList();
      }
      Iterator it = listaBemTributavel.iterator();
      while (it.hasNext())
      {
         BemTributavelVo atual = (BemTributavelVo) it.next();        
         if (atual.getDescricaoBemTributavel().equals(bemTributavelVo.getDescricaoBemTributavel()) && (atual.getBemVo().getClassificacaoTipoBem().getValorCorrente() == bemTributavelVo.getBemVo().getClassificacaoTipoBem().getValorCorrente()) && (atual.getBemVo().getDescricaoTipoBem().equals(bemTributavelVo.getBemVo().getDescricaoTipoBem())))
         {
            throw new ParametroObrigatorioException(MensagemErro.VALIDAR_BEM_INCLUIR_DESCRICAO_E_TIPO);
         }
      }
      listaBemTributavel.add(bemTributavelVo);
      GIAITCDBe.alterarAutomaticamenteTipoProtocoloGia( bemTributavelVoColl.getGiaITCDVo() );
      return listaBemTributavel;
      
   }

   /**
    * Extrai os dados do BemTributavelVo do uma GIAITCDVo para uma nova instancia de BemTributavelVo e limpa os valores
    * configurados em GIAITCDVo.bemTributavelVo.
    * 
    * @param gia a gia que possui o BemTributavelVo requerido
    * @return um novo BemTributavel com os valores configurados de acordo com o BemTributavelVo da Gia informada no parametro <b>gia</b>
    * @implemented by Leandro Dorileo
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public static BemTributavelVo extraiEReinicializaBemTributavelDaGia(GIAITCDVo gia)
   {
      BemTributavelVo retorno = BemTributavelBe.extraiBemTributavel(gia.getBemTributavelVo());
      BemTributavelBe.reinicializaBemTributavel(gia.getBemTributavelVo());
      return retorno;
   }

   /**
    * Copia os valores dos atributos de um determinado BemTributavelVo para uma nova instancia de BemTributavelVo
    * 
    * @param arg0 Bem tributável contendo os dados requeridos
    * @return uma nova instancia de BemTributavelVo coms os atributos configurados com valores iguals ao BemTributavelVo informado no <b>arg0</b>
    * @implemented by Leandro Dorileo
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public static BemTributavelVo extraiBemTributavel(BemTributavelVo arg0)
   {
      BemTributavelVo bemTributavelVo = new BemTributavelVo();
      bemTributavelVo.getBemVo().setClassificacaoTipoBem(arg0.getBemVo().getClassificacaoTipoBem());
      bemTributavelVo.getBemVo().setDescricaoTipoBem(arg0.getBemVo().getDescricaoTipoBem());
      bemTributavelVo.getBemVo().setCodigo(arg0.getBemVo().getCodigo());
      bemTributavelVo.getBemVo().setTipoProtocoloGIA(arg0.getBemVo().getTipoProtocoloGIA());
      bemTributavelVo.getBemVo().setTipoVerificacao(arg0.getBemVo().getTipoVerificacao());
      bemTributavelVo.getBemVo().setPossuiConstrucao(arg0.getBemVo().getPossuiConstrucao());
      bemTributavelVo.setDescricaoBemTributavel(arg0.getDescricaoBemTributavel());
      bemTributavelVo.setValorMercado(arg0.getValorMercado());
      bemTributavelVo.setIsencaoPrevista(arg0.getIsencaoPrevista());
      bemTributavelVo.setBemParticular(arg0.getBemParticular());
      bemTributavelVo.setTipoUsuario(arg0.getTipoUsuario());
      bemTributavelVo.setCodigo(arg0.getCodigo());
      bemTributavelVo.setValorInformadoContribuinte( arg0.getValorInformadoContribuinte() );
	  bemTributavelVo.setConcordaComValorArbitrado(arg0.getConcordaComValorArbitrado());
      return bemTributavelVo;
   }

   /**
    * Limpa os atributos de um determinado BemTributavelVo - em casos que se é necessário fazer um pseudo clone e que seja necessário um posterior uso de uma
    * mesma instancia de BemTributavelVO.
    * 
    * @param bemTributavel
    * @implemented by Leandro Dorileo
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public static void reinicializaBemTributavel(BemTributavelVo bemTributavel)
   {
      bemTributavel.setBemVo(new BemVo());
      bemTributavel.setDescricaoBemTributavel("");
      bemTributavel.setValorMercado(0.0);
      bemTributavel.setIsencaoPrevista(new DomnSimNao(DomnSimNao.NAO));
      bemTributavel.setBemParticular(new DomnSimNao(DomnSimNao.NAO));
      bemTributavel.setTipoUsuario(new DomnTipoUsuario(-1));
      bemTributavel.setCodigo(0);
	  bemTributavel.setConcordaComValorArbitrado(null);
   }

   /**
    * Método para reativar um Bem Tributável
    * @param bemTributavel
    * @throws ObjetoObrigatorioException
    * @throws RegistroExistenteException
    * @throws ParametroObrigatorioException
    * @throws ConsultaException
    * @throws ConexaoException
    * @throws LogException
    * @throws PersistenciaException
    * @throws AnotacaoException
    * @implemented by Daniel Balieiro
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public void reativarBemTributavelBem(BemTributavelVo bemTributavel) throws ObjetoObrigatorioException, 
                                                                              ParametroObrigatorioException, 
                                                                              RegistroExistenteException, 
                                                                              ConsultaException, 
                                                                              LogException, 
                                                                              PersistenciaException, 
                                                                              AnotacaoException, 
                                                                              ConexaoException
   {
      Validador.validaObjeto(bemTributavel);
      bemTributavel.getBemVo().setLogSefazVo(bemTributavel.getLogSefazVo());
      (new BemBe(conn)).reativarBem(bemTributavel.getBemVo());
   }

   /**
    * Método responsável por selecionar um selecionar na collection um bem.
    * @param bemTributavelVo
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public static void selecionaBem(final BemTributavelVo bemTributavelVo)
   {
      if (Validador.isCollectionValida(bemTributavelVo.getBemVo().getCollVO()))
      {
         Iterator it = bemTributavelVo.getBemVo().getCollVO().iterator();
         while (it.hasNext())
         {
            BemVo bemAtualVo = (BemVo) it.next();
            if (bemAtualVo.getCodigo() == bemTributavelVo.getBemVo().getCodigo())
            {
               bemTributavelVo.getBemVo().setDescricaoTipoBem(bemAtualVo.getDescricaoTipoBem());
               bemTributavelVo.getBemVo().setPossuiConstrucao(new DomnSimNao(bemAtualVo.getPossuiConstrucao().getValorCorrente()));
               bemTributavelVo.getBemVo().setTipoProtocoloGIA(bemAtualVo.getTipoProtocoloGIA());
               bemTributavelVo.getBemVo().setTipoVerificacao(bemAtualVo.getTipoVerificacao());
               break;
            }
         }
      }
   }

   /**
    * Método responsável por percorrer a lista de bens tributáveis e setar a opção bem particular como NÃO.
    * @param bemTributavelVo
    * @throws ObjetoObrigatorioException
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public static void ocultaOpcaoBemParticular(final BemTributavelVo bemTributavelVo) throws ObjetoObrigatorioException
   {
      Validador.validaObjeto(bemTributavelVo);
      Iterator it = bemTributavelVo.getCollVO().iterator();
      while (it.hasNext())
      {
         BemTributavelVo bem = (BemTributavelVo) it.next();
         if (bem.getBemParticular().is(DomnSimNao.SIM))
         {
            bem.setBemParticular(new DomnSimNao(DomnSimNao.NAO));
         }
      }
   }

   /**
    * Método responsável por selecionar qual o valor do bem tributável, ou seja, 
    * dado que na Avaliação pode-se corrigir o valor do bem declarado, logo após a realização da mesma, 
    * será então obtido a partir daí o valor do bem sugerido na avaliação.
    * @param bemTributavelVo
    * @throws ObjetoObrigatorioException
    * @return double
    * @implemented by Ricardo Vitor de Oliveira Moraes
    */
   public static double getValorBemTributavel(final BemTributavelVo bemTributavelVo) throws ObjetoObrigatorioException
   {
      Validador.validaObjeto(bemTributavelVo);
      return getValorBemTributavel(bemTributavelVo, false);
   }

   public static double getValorBemTributavel(final BemTributavelVo bemTributavelVo, boolean ignoraAvaliacao) throws ObjetoObrigatorioException
   {
      Validador.validaObjeto(bemTributavelVo);
      if (!ignoraAvaliacao)
      {
         if (Validador.isNumericoValido(bemTributavelVo.getAvaliacaoBemTributavelVo().getCodigo()) && bemTributavelVo.getAvaliacaoBemTributavelVo().getIsento().is(DomnSimNao.NAO))
         {
            return bemTributavelVo.getAvaliacaoBemTributavelVo().getValorAvaliacao();
         } else if (bemTributavelVo.getIsencaoPrevista().is(DomnSimNao.NAO))
         {
            return bemTributavelVo.getValorMercado();
         }
      } else
      {
         if (bemTributavelVo.getIsencaoPrevista().is(DomnSimNao.NAO))
         {
            return bemTributavelVo.getValorMercado();
         }
      }
      return 0;
   }
	public static double getValorBemInformado(final BemTributavelVo bemTributavelVo) throws ObjetoObrigatorioException
	{
	   Validador.validaObjeto(bemTributavelVo);
	   return getValorBemInformado(bemTributavelVo, false);
	}
	public static double getValorBemInformado(final BemTributavelVo bemTributavelVo, boolean ignoraAvaliacao) throws ObjetoObrigatorioException
	{
	   Validador.validaObjeto(bemTributavelVo);
	   if (!ignoraAvaliacao)
	   {
		  if (Validador.isNumericoValido(bemTributavelVo.getAvaliacaoBemTributavelVo().getCodigo()) && bemTributavelVo.getAvaliacaoBemTributavelVo().getIsento().is(DomnSimNao.NAO))
		  {
			 return bemTributavelVo.getAvaliacaoBemTributavelVo().getValorAvaliacao();
		  } else if (bemTributavelVo.getIsencaoPrevista().is(DomnSimNao.NAO))
		  {
			 return bemTributavelVo.getValorInformadoContribuinte();
		  }
	   } else
	   {
		  if (bemTributavelVo.getIsencaoPrevista().is(DomnSimNao.NAO))
		  {
			 return bemTributavelVo.getValorInformadoContribuinte();
		  }
	   }
	   return 0;
	}

   /**
    * 
    * @param bemTributavelVoModificado
    * @param bemTributavelVoOriginal
    * @implemented by Dherkyan Ribeiro Silva
    */
   private void excluirFicha(BemTributavelVo bemTributavelVoModificado, BemTributavelVo bemTributavelVoOriginal) throws ObjetoObrigatorioException, 
                                                                                                                        ConexaoException, 
                                                                                                                        DadoNecessarioInexistenteException, 
                                                                                                                        LogException, 
                                                                                                                        PersistenciaException, 
                                                                                                                        AnotacaoException
   {
      Iterator itBemOriginal = bemTributavelVoOriginal.getCollVO().iterator();
      Iterator itBemModificado = bemTributavelVoModificado.getCollVO().iterator();
      while (itBemOriginal.hasNext())
      {
         BemTributavelVo bemOriginal = (BemTributavelVo) itBemOriginal.next();
         bemOriginal.setLogSefazVo(bemTributavelVoOriginal.getLogSefazVo());
         while (itBemModificado.hasNext())
         {
            BemTributavelVo bemModificado = (BemTributavelVo) itBemModificado.next();
            if (bemOriginal.getCodigo() == bemModificado.getCodigo())
            {
               if (bemOriginal.getFichaImovelVo() instanceof FichaImovelRuralVo && bemModificado.getFichaImovelVo() instanceof FichaImovelRuralVo)
               {
                  new FichaImovelRuralBenfeitoriaBe(conn).excluirFichaImovelRuralBemfeitoriaVo(bemModificado, bemOriginal);
                  new FichaImovelRuralConstrucaoBe(conn).excluirFichaImovelRuralRebanhoVo(bemModificado, bemOriginal);
                  new FichaImovelRuralRebanhoBe(conn).excluirFichaImovelRuralRebanhoVo(bemModificado, bemOriginal);
                  new FichaImovelRuralCulturaBe(conn).excluirFichaImovelRuralCulturaVo(bemModificado, bemOriginal);

               }
            }
         }
      }
   }
   
   
   /**
    * <b>Objetivo:</b>este método tem por objetivo verificar todo o objetdo CollVO 
    * procurando se alguns dos bens tributaveis contém o dominio informado como parametro.
    * <br><br>
    * <b><h1>Atenção:</h1></b> este método NÃO verifica dentro do objeto CollVO.
    * 
    * @param bemTributavelVo
    * @param domnTipoProtocolo
    * @return true se e somente algum dos bens contiver o dominio informado.
    */
   public static boolean isBemTributavelVoTipoProtocoloCollVO(final BemTributavelVo bemTributavelVo, DomnTipoProtocolo domnTipoProtocolo)
   {
      if(Validador.isObjetoValido(bemTributavelVo) && Validador.isDominioNumericoValido( domnTipoProtocolo ))
      {
         for( BemTributavelVo bem : bemTributavelVo.getListVo() )
         {
            if( isBemTributavelVoTipoProtocolo(bem , domnTipoProtocolo ))
            {
               return true;
            }
         }
      }
      return false;
   }
   
   
   /**
    * <b>Objetivo:</b>este método tem por objetivo verificar se o BemVo informado
    * dentro do objeto bemTributavelVo passado como parametro contém o mesmo dominio informado
    * como parametro.
    * <br><br>
    * <b><h1>Atenção:</h1></b> este método NÃO verifica dentro do objeto CollVO.
    * 
    * @param bemTributavelVo
    * @param domnTipoProtocolo
    * @return true se e somente se o bemTributavelVo contiver o dominio informado.
    */
   public static boolean isBemTributavelVoTipoProtocolo(final BemTributavelVo bemTributavelVo, DomnTipoProtocolo domnTipoProtocolo)
   {
      if(Validador.isObjetoValido(bemTributavelVo) && Validador.isDominioNumericoValido( domnTipoProtocolo ))
      {
         if( BemBe.isBemVoTipoProtocolo( bemTributavelVo.getBemVo() , domnTipoProtocolo )  )
         {
            return true;
         }
      }
      return false;
   }
   
   /**
    * <b>Objetivo:</b>este método tem por objetivo verificar se algum o BemTributavelVo
    * no CollVo está definido como ISENTO.
    * <br><br>
    * <b><h1>Atenção:</h1></b> este método NÃO verifica dentro do objeto CollVO.
    * 
    * @param bemTributavelVo
    * @return true se e somente se exisitr o bemTributavelVo estiver definido como isento.
    */
   public static boolean isExisteBemTributavelVoIsentoCollVo(final BemTributavelVo bemTributavelVo)
   { 
      for(BemTributavelVo bt : bemTributavelVo.getListVo())
      {
         if(isBemTributavelVoIsento(bt))
         {
            return true;
         }
      }
      return false;
   }
   
   /**
    * <b>Objetivo:</b>este método tem por objetivo verificar se o BemTributavelVo
    * está definido como ISENTO.
    * <br><br>
    * <b><h1>Atenção:</h1></b> este método NÃO verifica dentro do objeto CollVO.
    * 
    * @param bemTributavelVo
    * @return true se e somente se o bemTributavelVo estiver definido como isento.
    */
   public static boolean isBemTributavelVoIsento(final BemTributavelVo bemTributavelVo)
   { 
      return bemTributavelVo.getIsencaoPrevista().is(DomnSimNao.SIM );
   }
   
   public static boolean isTodosBemComValorDeclaradoIgualArbitradoCollVo(final BemTributavelVo bemTributavelVo)
   {
      return !isExisteBemComValorDeclaradoDiferenteDeArbitradoCollVo( bemTributavelVo );
   }

   public static boolean isExisteBemComValorDeclaradoDiferenteDeArbitradoCollVo(final BemTributavelVo bemTributavelVo)
   {
      for(BemTributavelVo bt : bemTributavelVo.getListVo())
      {
         if(isBemComValorDeclaradoDiferenteDeArbitrado(bt))
         {
            return true;
         }
      }
      return false;
   }

   public static boolean isBemComValorDeclaradoDiferenteDeArbitrado(final BemTributavelVo bemTributavelVo)
   {
      return bemTributavelVo.getValorMercado() != bemTributavelVo.getValorInformadoContribuinte();
   }

   public static void validarPercentualTransmitido(FichaImovelUrbanoVo fichaImovelUrbanoVo)
      throws ParametroObrigatorioException
   {
      if(fichaImovelUrbanoVo.getValorPercentualTransmitido() == 0)
       {
          throw new ParametroObrigatorioException(MensagemErro.VALIDAR_FICHA_IMOVEL_URBANO_PERCENTUAL_TRANSMITIDO_NAO_INFORMADO);
       }
       if(fichaImovelUrbanoVo.getValorPercentualTransmitido() > 100)
       {
          throw new ParametroObrigatorioException(MensagemErro.VALIDAR_FICHA_IMOVEL_URBANO_PERCENTUAL_TRANSMITIDO_INVALIDO);
       }
      
   }
   
  
}
