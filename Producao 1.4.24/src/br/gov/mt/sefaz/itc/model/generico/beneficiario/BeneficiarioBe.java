package br.gov.mt.sefaz.itc.model.generico.beneficiario;

import br.com.abaco.log5.GenericoLogAnotacaoDao;
import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.beneficiario.aliquota.GIAITCDInventarioArrolamentoBeneficiarioAliquotaBe;
import br.gov.mt.sefaz.itc.util.facade.ConfiguracaoITCD;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.CadastroBe;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.ContribuinteIntegracaoVo;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * Classe responsável por implementar a regra de negócio da entidade "Beneficiario"
 * @author Daniel Balieiro
 * @version $Revision: 1.5 $
 */
public class BeneficiarioBe extends AbstractBe
{
	/**
	 * Construtor Padrï¿½o
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	public BeneficiarioBe() throws SQLException
	{
		super();
	}

	/**
	 * Construtor que recebe a Conexão com o Banco de Dados
	 * @param conexao
	 * @implemented by Daniel Balieiro
	 */
	public BeneficiarioBe(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Efetua a Consulta do Beneficiário
	 * @param beneficiarioVo
	 * @return BeneficiarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws IntegracaoException
	 * @implemented by Daniel Balieiro
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public BeneficiarioVo consultaBeneficiario(final BeneficiarioVo beneficiarioVo) throws ObjetoObrigatorioException, 
			  ConsultaException, IntegracaoException, SQLException
   {
		Validador.validaObjeto(beneficiarioVo);
		(new BeneficiarioQDao(conn)).findBeneficiario(beneficiarioVo);
		if (beneficiarioVo.isConsultaCompleta())
		{
			if (Validador.isNumericoValido(beneficiarioVo.getPessoaBeneficiaria().getNumrContribuinte()))
			{            
               CadastroBe cadastroBe = new CadastroBe(conn);
               ContribuinteIntegracaoVo pessoaConsulta = 
                  new ContribuinteIntegracaoVo(beneficiarioVo.getPessoaBeneficiaria().getNumrContribuinte());
               pessoaConsulta = new ContribuinteIntegracaoVo(pessoaConsulta);
               beneficiarioVo.setPessoaBeneficiaria(cadastroBe.obterContribuinte(pessoaConsulta));
            			   			
			}
		}
		return beneficiarioVo;
	}

	/**
	 * Efetua a Listagem do Beneficiário
	 * @param beneficiarioVo
	 * @return BeneficiarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @implemented by Daniel Balieiro
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public BeneficiarioVo listaBeneficiario(final BeneficiarioVo beneficiarioVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(beneficiarioVo);
		(new BeneficiarioQDao(conn)).listBeneficiario(beneficiarioVo);
		return beneficiarioVo;
	}

	/**
	 * Método responsável por verificar se umbeneficiarioVo está apto a ser excluído.
	 * @param beneficiarioVo
	 * @throws ParametroObrigatorioException
	 * @throws ObjetoObrigatorioException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public void validaParametrosExcluirBeneficiario(final BeneficiarioVo beneficiarioVo) throws ParametroObrigatorioException, 
			  ObjetoObrigatorioException
	{
		Validador.validaObjeto(beneficiarioVo);
	   if (!Validador.isNumericoValido(beneficiarioVo.getCodigo()))
	   {
	      throw new ParametroObrigatorioException(MensagemErro.VALIDAR_BENEFICIARIO_CODIGO_NAO_INFORMADO);
	   }
	}

	/**
	 * Valida um BeneficiarioVo antes de ser feita a inclusão. Verifica se os dados obrigatórios
	 * estão configurados corretamente.
	 * 
	 * @param beneficiarioVo VO do beneficiário a ser validado
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public void validaParametrosIncluirBeneficiario(final BeneficiarioVo beneficiarioVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException
	{
		Validador.validaObjeto(beneficiarioVo);
		try
		{
			Validador.validaObjeto(beneficiarioVo.getPessoaBeneficiaria());
		}
		catch (ObjetoObrigatorioException e)
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_BENEFICIARIO_PESSOA_NAO_INFORMADA);
		}
		if (!Validador.isNumericoValido(beneficiarioVo.getPessoaBeneficiaria().getNumrContribuinte()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_BENEFICIARIO_PESSOA_NAO_INFORMADA);
		}
//		if(beneficiarioVo.getGiaITCDVo() instanceof GIAITCDInventarioArrolamentoVo)
//		{
//			if (!(beneficiarioVo.getGiaITCDVo().getTipoGIA().is(DomnTipoGIA.CAUSA_MORTIS) && 
//					 (!((GIAITCDInventarioArrolamentoVo) beneficiarioVo.getGiaITCDVo()).getUfAbertura().getSiglUf().equals(ConfiguracaoITCD.SIGLA_ESTADO_MATO_GROSSO))))
//			{
//				if (!Validador.isNumericoValido(beneficiarioVo.getValorRecebido()))
//				{
//					throw new ParametroObrigatorioException(MensagemErro.VALIDAR_BENEFICIARIO_VALOR_RECEBIDO);
//				}
//			}
//		}
//		else if (!Validador.isNumericoValido(beneficiarioVo.getValorRecebido()))
//		{
//			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_BENEFICIARIO_VALOR_RECEBIDO);
//		}
		try
		{
			Validador.validaObjeto(beneficiarioVo.getGiaITCDVo());
		}
		catch (ObjetoObrigatorioException e)
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_PARAMETRO_CODIGO_ITC);
		}
		if (!Validador.isNumericoValido(beneficiarioVo.getGiaITCDVo().getCodigo()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_PARAMETRO_CODIGO_ITC);
		}
	}

	/**
	 * @param beneficiarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void incluir(final BeneficiarioVo beneficiarioVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(beneficiarioVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).insert(beneficiarioVo, BeneficiarioVo.class);
	}

	/**
	 * Inclui um beneficiï¿½rio no banco de dados.
	 * 
	 * @param beneficiarioVo VO de beneficiário(Value Object). Dados a serem inseridos.
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @throws ConexaoException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void incluirBeneficiario(final BeneficiarioVo beneficiarioVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException, LogException, AnotacaoException, PersistenciaException, ConexaoException
	{
		Validador.validaObjeto(beneficiarioVo);
		validaParametrosIncluirBeneficiario(beneficiarioVo);
		try
		{
			try
			{
				synchronized (BeneficiarioVo.class)
				{
					incluir(beneficiarioVo);
				}
			}			
			catch (ObjetoObrigatorioException e)
			{
				conn.rollback();
				throw e;
			}
		   catch(LogException e)
		   {
		      conn.rollback();
		      throw e;
		   }
		   catch(PersistenciaException e)
		   {
		      conn.rollback();
		      throw e;
		   }
		   catch(AnotacaoException e)
		   {
		      conn.rollback();
		      throw e;
		   }			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}
	}

	/**
	 * @param beneficiarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void alterar(final BeneficiarioVo beneficiarioVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(beneficiarioVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).update(beneficiarioVo, BeneficiarioVo.class);
	}

	/**
	 * altera um beneficiário no banco de dados.
	 * 
	 * @param beneficiarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @throws ConexaoException
	 * @implemented by Leandro Dorileo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void alterarBeneficiario(final BeneficiarioVo beneficiarioVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException, LogException, PersistenciaException, AnotacaoException, ConexaoException
	{
		Validador.validaObjeto(beneficiarioVo);
		if (!Validador.isNumericoValido(beneficiarioVo.getCodigo()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_BENEFICIARIO_CODIGO_NAO_INFORMADO);
		}
		validaParametrosIncluirBeneficiario(beneficiarioVo);
		try
		{
			try
			{
				synchronized (BeneficiarioVo.class)
				{
					alterar(beneficiarioVo);
				}
			}
			catch (ObjetoObrigatorioException e)
			{
				conn.rollback();
				throw e;
			}
		   catch(LogException e)
		   {
		      conn.rollback();
		      throw e;
		   }
		   catch(PersistenciaException e)
		   {
		      conn.rollback();
		      throw e;
		   }
		   catch(AnotacaoException e)
		   {
		      conn.rollback();
		      throw e;
		   }
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}
	}

	/**
	 * @param beneficiarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void excluir(final BeneficiarioVo beneficiarioVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(beneficiarioVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).delete(beneficiarioVo, BeneficiarioVo.class);
	}

	/**
	 * Método responsável por realizar a exclusão de um beneficiário.
	 * @param beneficiarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @throws ConexaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void excluirBeneficiario(final BeneficiarioVo beneficiarioVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException, LogException, PersistenciaException, AnotacaoException, ConexaoException
	{
		Validador.validaObjeto(beneficiarioVo);
		if (!Validador.isNumericoValido(beneficiarioVo.getCodigo()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_BENEFICIARIO_CODIGO_NAO_INFORMADO);
		}
		validaParametrosExcluirBeneficiario(beneficiarioVo);
		try
		{
			try
			{
				synchronized (BeneficiarioVo.class)
				{
					excluir(beneficiarioVo);
				}
			}
			catch (ObjetoObrigatorioException e)
			{
				conn.rollback();
				throw e;
			}
		   catch(LogException e)
		   {
		      conn.rollback();
		      throw e;
		   }
		   catch(PersistenciaException e)
		   {
		      conn.rollback();
		      throw e;
		   }
		   catch(AnotacaoException e)
		   {
		      conn.rollback();
		      throw e;
		   }
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}
	}	
}
