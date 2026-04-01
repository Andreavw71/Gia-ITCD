package br.gov.mt.sefaz.itc.model.tabelabasica.naturezadaoperacao;

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
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcesso;
import br.gov.mt.sefaz.itc.util.facade.ConfiguracaoITCD;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.MensagemSucesso;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.Date;


/**
 * @author Ricardo Vitor de Oliveira Moraes
 * @version
 */
public class NaturezaOperacaoBe extends AbstractBe
{

	/**
	 * Construtor Padrão
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	public NaturezaOperacaoBe() throws SQLException
	{
		super();
	}

	/**
	 * Construtor que recebe a conexão com o Banco de Dados
	 * @param conexao
	 * @implemented by Daniel Balieiro
	 */
	public NaturezaOperacaoBe(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Método para validar a inclusão do Natureza de Operação
	 * @param naturezaOperacaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @implemented by Daniel Balieiro
	 */
	private void validaParametrosIncluirNaturezaOperacao(final NaturezaOperacaoVo naturezaOperacaoVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException, ConsultaException, ConexaoException
	{
		Validador.validaObjeto(naturezaOperacaoVo);
		if (!Validador.isDominioNumericoValido(naturezaOperacaoVo.getTipoGIA()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_NATUREZA_OPERACAO_TIPO_GIA);
		}
		if (!Validador.isDominioNumericoValido(naturezaOperacaoVo.getTipoProcesso()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_NATUREZA_OPERACAO_TIPO_PROCESSO);
		}
		if (!Validador.isStringValida(naturezaOperacaoVo.getDescricaoNaturezaOperacao()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_NATUREZA_OPERACAO_DESCRICAO);
		}
		if(naturezaOperacaoVo.getTipoProcesso().is(DomnTipoProcesso.DOACAO))
		{
			if(!Validador.isDominioNumericoValido(naturezaOperacaoVo.getFlagIsencaoPrevistaLei()))
			{
				throw new ParametroObrigatorioException(MensagemErro.VALIDAR_NATUREZA_OPERCAO_FLAG_ISENCAO_PREVISTA);
			}
		}
		if (Validador.isDominioNumericoValido(naturezaOperacaoVo.getTipoBaseCalculo()) && 
				 naturezaOperacaoVo.getTipoBaseCalculo().getValorCorrente() == DomnSimNao.SIM && 
				 (naturezaOperacaoVo.getPercentualBaseCalculo() <= 0 || 
				 naturezaOperacaoVo.getPercentualBaseCalculo() > 100))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_NATUREZA_OPERACAO_VALOR_BASE_CALCULO_REDUZIDA_MAIOR_ZERO_MENOR_CEM);
		}
	}

	private void validaParametrosAlterarNaturezaOperacao(final NaturezaOperacaoVo naturezaOperacaoVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException, ConsultaException, ConexaoException, DadoNecessarioInexistenteException
	{
		Validador.validaObjeto(naturezaOperacaoVo);
		if(!Validador.isNumericoValido(naturezaOperacaoVo.getCodigo()))
		{
			throw new DadoNecessarioInexistenteException(MensagemErro.VALIDAR_NATUREZA_OPERACAO_CODIGO_NATUREZA);
		}
	   if (!Validador.isDominioNumericoValido(naturezaOperacaoVo.getTipoGIA()))
	   {
	      throw new DadoNecessarioInexistenteException(MensagemErro.VALIDAR_NATUREZA_OPERACAO_TIPO_GIA);
	   }
	   if (!Validador.isDominioNumericoValido(naturezaOperacaoVo.getTipoProcesso()))
	   {
	      throw new DadoNecessarioInexistenteException(MensagemErro.VALIDAR_NATUREZA_OPERACAO_TIPO_PROCESSO);
	   }
	   if (!Validador.isStringValida(naturezaOperacaoVo.getDescricaoNaturezaOperacao()))
	   {
	      throw new ParametroObrigatorioException(MensagemErro.VALIDAR_NATUREZA_OPERACAO_DESCRICAO);
	   }
	   if(naturezaOperacaoVo.getTipoProcesso().is(DomnTipoProcesso.DOACAO))
	   {
	      if(!Validador.isDominioNumericoValido(naturezaOperacaoVo.getFlagIsencaoPrevistaLei()))
	      {
	         throw new DadoNecessarioInexistenteException(MensagemErro.VALIDAR_NATUREZA_OPERCAO_FLAG_ISENCAO_PREVISTA);
	      }
	   }
	}	

	/**
	 * Método para alterar a Natureza de Operação no Banco de Dados
	 * @param naturezaOperacaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConexaoException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @throws RegistroExistenteException
	 * @throws ConsultaException
	 * @throws ParametroObrigatorioException
	 * @implemented by Daniel Balieiro
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void alterarNaturezaOperacao(final NaturezaOperacaoVo naturezaOperacaoVo) throws ObjetoObrigatorioException, 
			  ConexaoException, LogException, AnotacaoException, PersistenciaException, RegistroExistenteException, 
			  ConsultaException, ParametroObrigatorioException, DadoNecessarioInexistenteException
	{
		Validador.validaObjeto(naturezaOperacaoVo);
		try
		{
			try
			{
				synchronized (NaturezaOperacaoVo.class)
				{
					validaParametrosAlterarNaturezaOperacao(naturezaOperacaoVo);
					NaturezaOperacaoVo naturezaOperacaoConsultaVo = new NaturezaOperacaoVo();
					naturezaOperacaoConsultaVo.setTipoGIA(naturezaOperacaoVo.getTipoGIA());
					naturezaOperacaoConsultaVo.setTipoProcesso(naturezaOperacaoVo.getTipoProcesso());
					naturezaOperacaoConsultaVo.setDescricaoNaturezaOperacao(naturezaOperacaoVo.getDescricaoNaturezaOperacao());
					naturezaOperacaoConsultaVo.setStatusNaturezaOperacao(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
					naturezaOperacaoConsultaVo = new NaturezaOperacaoVo(naturezaOperacaoConsultaVo);
					(new NaturezaOperacaoQDao(conn)).findNaturezaOperacaoVo(naturezaOperacaoConsultaVo);
					if (naturezaOperacaoConsultaVo.isExiste() && naturezaOperacaoConsultaVo.getCodigo() != naturezaOperacaoVo.getCodigo())
					{
						throw new RegistroExistenteException(MensagemErro.VALIDAR_NATUREZA_OPERACAO_JA_EXISTE_DESCRICAO);
					}
					naturezaOperacaoVo.setDataAtualizacao(new Date());
					alterar(naturezaOperacaoVo);
					commit();
					naturezaOperacaoVo.setMensagem(MensagemSucesso.ALTERAR);
				}
			}
			catch (RegistroExistenteException ree)
			{
				conn.rollback();
				throw ree;
			}
			catch (ConsultaException ce)
			{
				conn.rollback();
				throw ce;
			}
			catch (ParametroObrigatorioException poe)
			{
				conn.rollback();
				throw poe;
			}
			catch (ObjetoObrigatorioException ooe)
			{
				conn.rollback();
				throw ooe;
			}
			catch(LogException e)
			{
				conn.rollback();
				throw e;
			}
			catch(AnotacaoException e)
			{
				conn.rollback();
				throw e;
			}
			catch(PersistenciaException e)
			{
				conn.rollback();
				throw e;
			}
			catch(DadoNecessarioInexistenteException e)
			{
				conn.rollback();
				throw e;
			}
		}
		catch (SQLException se)
		{
			se.printStackTrace();
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}
	}

	/**
	 * @param naturezaOperacaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void alterar(final NaturezaOperacaoVo naturezaOperacaoVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(naturezaOperacaoVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).update(naturezaOperacaoVo);
	}

	/**
	 * Método para inserir uma Natureza de Operação no Banco de Dados
	 * @param naturezaOperacaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConexaoException
	 * @throws RegistroExistenteException
	 * @throws ConsultaException
	 * @throws ParametroObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Daniel Balieiro
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void incluirNaturezaOperacao(final NaturezaOperacaoVo naturezaOperacaoVo) throws ObjetoObrigatorioException, 
			  ConexaoException, RegistroExistenteException, ConsultaException, ParametroObrigatorioException, LogException, 
			  AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(naturezaOperacaoVo);
		try
		{
			try
			{
				synchronized (NaturezaOperacaoVo.class)
				{
					validaParametrosIncluirNaturezaOperacao(naturezaOperacaoVo);
					NaturezaOperacaoVo naturezaOperacaoConsultaVo = new NaturezaOperacaoVo();
					naturezaOperacaoConsultaVo.setTipoGIA(naturezaOperacaoVo.getTipoGIA());
					naturezaOperacaoConsultaVo.setTipoProcesso(naturezaOperacaoVo.getTipoProcesso());
					naturezaOperacaoConsultaVo.setDescricaoNaturezaOperacao(naturezaOperacaoVo.getDescricaoNaturezaOperacao());
				   naturezaOperacaoConsultaVo.setStatusNaturezaOperacao(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
					naturezaOperacaoConsultaVo = new NaturezaOperacaoVo(naturezaOperacaoConsultaVo);
					(new NaturezaOperacaoQDao(conn)).findNaturezaOperacaoVo(naturezaOperacaoConsultaVo);
					if (naturezaOperacaoConsultaVo.isExiste())
					{
						throw new RegistroExistenteException(MensagemErro.VALIDAR_NATUREZA_OPERACAO_JA_EXISTE_DESCRICAO);
					}
					naturezaOperacaoVo.setStatusNaturezaOperacao(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
					naturezaOperacaoVo.setDataAtualizacao(new Date());
					incluir(naturezaOperacaoVo);
					commit();
					naturezaOperacaoVo.setMensagem(MensagemSucesso.INCLUIR);
				}
			}
			catch (RegistroExistenteException ree)
			{
				conn.rollback();
				throw ree;
			}
			catch (ConsultaException ce)
			{
				conn.rollback();
				throw ce;
			}
			catch (ParametroObrigatorioException poe)
			{
				conn.rollback();
				throw poe;
			}
			catch (ObjetoObrigatorioException ooe)
			{
				conn.rollback();
				throw ooe;
			}
			catch(LogException e)
			{
				conn.rollback();
				throw e;
			}
			catch(AnotacaoException e)
			{
				conn.rollback();
				throw e;
			}
			catch(PersistenciaException e)
			{
				conn.rollback();
				throw e;
			}
		}
		catch (SQLException se)
		{
			se.printStackTrace();
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}
	}

	/**
	 * @param naturezaOperacaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void incluir(final NaturezaOperacaoVo naturezaOperacaoVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(naturezaOperacaoVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).insert(naturezaOperacaoVo);
	}

	/**
	 * Método utilizado para Consultar uma Natureza da Operação
	 * @param naturezaOperacaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @implemented by Daniel Balieiro
	 */
	public NaturezaOperacaoVo consultarNaturezaOperacao(final NaturezaOperacaoVo naturezaOperacaoVo) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException
	{
		Validador.validaObjeto(naturezaOperacaoVo);
		(new NaturezaOperacaoQDao(conn)).findNaturezaOperacaoVo(naturezaOperacaoVo);
		return naturezaOperacaoVo;
	}

	/**
	 * Método utilizado para Listar as Naturezas da Operação
	 * @param naturezaOperacaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @implemented by Daniel Balieiro
	 */
	public NaturezaOperacaoVo listarNaturezaOperacao(final NaturezaOperacaoVo naturezaOperacaoVo) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException
	{
		Validador.validaObjeto(naturezaOperacaoVo);
		(new NaturezaOperacaoQDao(conn)).listNaturezaOperacaoVo(naturezaOperacaoVo);
		naturezaOperacaoVo.ordenaPorTipoGiaTipoProcessoCodigo();
		return naturezaOperacaoVo;
	}

	/**
	 * @param naturezaOperacaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConexaoException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @throws RegistroExistenteException
	 * @throws ConsultaException
	 * @throws ParametroObrigatorioException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public void reativarNaturezaOperacao(final NaturezaOperacaoVo naturezaOperacaoVo) throws ObjetoObrigatorioException, 
			  ConexaoException, LogException, AnotacaoException, PersistenciaException, RegistroExistenteException, 
			  ConsultaException, ParametroObrigatorioException, DadoNecessarioInexistenteException
	{
		Validador.validaObjeto(naturezaOperacaoVo);
		naturezaOperacaoVo.setStatusNaturezaOperacao(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		alterarNaturezaOperacao(naturezaOperacaoVo);
	}
}
