package br.gov.mt.sefaz.itc.util.integracao.gestaopessoas;

import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.PaginaNaoEncontradaException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.TipoRedirecionamentoInvalidoException;
import br.com.abaco.util.http.AbstractAbacoServlet;

import br.gov.mt.sefaz.itc.util.facade.Flow;
import br.gov.mt.sefaz.itc.util.facade.FormITC;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 
 * @author Rogério Sanches de Oliveira
 * @version $Revision: 1.1.1.1 $
 */
public class GestaoPessoasPesquisar extends AbstractAbacoServlet implements FormITC, Flow
{
	private static final int REQUISICAO_LISTAR_TIPO_UNIDADE_SEFAZ_ATIVO = 2;
	private static final int REQUISICAO_CONSULTAR_UNIDADE_SEFAZ_POR_TIPO_UNIDADE = 3;
	private static final int REQUISICAO_CONSULTAR_SERVIDOR_SEFAZ = 4;
	private static final int REQUISICAO_LISTAR_UNIDADE_SEFAZ_POR_UNIDADE_SUPERIOR = 5;

	/**
	 * Torna obrigatória a implementação do método processRequest pela servlet da
	 * aplicação. Este método trabalha colaborativamente com o método
	 * redirecionar. O método redirecionar determina a ação a ser tomada e
	 * processoRequest a executa.
	 * @param request
	 * @param response
	 * @throws ParametroObrigatorioException
	 * @throws IntegracaoException
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ParametroObrigatorioException, 
			  IntegracaoException, ObjetoObrigatorioException, PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ConexaoException
	{
		switch (redirecionar(request))
		{
			case REQUISICAO_LISTAR_TIPO_UNIDADE_SEFAZ_ATIVO:
				{
					listarTipoUnidadeSefazAtivo(request);
					break;
				}
			case REQUISICAO_CONSULTAR_UNIDADE_SEFAZ_POR_TIPO_UNIDADE:
				{
					listarUnidadeSefazPorTipoUnidade(request);
					break;
				}
			case REQUISICAO_CONSULTAR_SERVIDOR_SEFAZ:
				{
					consultarServidorSefaz(request, response);
					break;
				}
			case REQUISICAO_VAZIA:
				{
					processFlow(VIEW_ERRO, request, response, FORWARD);
					break;
				}
			case REQUISICAO_LISTAR_UNIDADE_SEFAZ_POR_UNIDADE_SUPERIOR:
				{
					listarUnidadeSefazAtivaPorSiglaUnidadeSuperior(request);
				}
		}
	}

	/**
	 * Este método é responsável pela análise dos parâmetros e a
	 * tomada de decisão quanto ao controle do fluxo da aplicação.
	 * @param request
	 * @return int - requisição do usuário
	 * @implemented by Rogério Sanches de Oliveira
	 */
	protected int redirecionar(HttpServletRequest request)
	{
		if (Validador.isStringValida((String) request.getAttribute(UNIDADE_SEFAZ_VO)))
		{
			return REQUISICAO_CONSULTAR_UNIDADE_SEFAZ_POR_TIPO_UNIDADE;
		}
		else if (Validador.isStringValida((String) request.getAttribute(TIPO_UNIDADE_SEFAZ_VO)))
		{
			return REQUISICAO_LISTAR_TIPO_UNIDADE_SEFAZ_ATIVO;
		}
		else if (request.getAttribute(SERVIDOR_VO) != null)
		{
			return REQUISICAO_CONSULTAR_SERVIDOR_SEFAZ;
		}
		else if(Validador.isStringValida((String) request.getAttribute(SIGLA_UNIDADE_SUPERIOR)))
		{
			return REQUISICAO_LISTAR_UNIDADE_SEFAZ_POR_UNIDADE_SUPERIOR;
		}
		else
		{
			return REQUISICAO_VAZIA;
		}
	}

	/**
	 * Método que lista todos os tipos de Unidades Sefaz que possuem o status ativo.
	 * @param request
	 * @throws ParametroObrigatorioException
	 * @throws IntegracaoException
	 * @throws ObjetoObrigatorioException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void listarTipoUnidadeSefazAtivo(HttpServletRequest request) throws ParametroObrigatorioException, 
			  IntegracaoException, ObjetoObrigatorioException
	{
		GestaoPessoasBe gestaoPessoasBe = null;
		try
		{
			gestaoPessoasBe = new GestaoPessoasBe();
			TipoUnidadeSefazIntegracaoVo tipoUnidadeSefazIntegracaoVo = gestaoPessoasBe.listarTipoUnidadeSefazAtivo();
			Validador.validaObjeto(tipoUnidadeSefazIntegracaoVo);
			request.setAttribute(TIPO_UNIDADE_SEFAZ_VO, tipoUnidadeSefazIntegracaoVo);
		}
		catch (SQLException e)
		{
			throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_GESTAO_PESSOAS, e);
		}
		finally
		{
			if (gestaoPessoasBe != null)
			{
				gestaoPessoasBe.close();
				gestaoPessoasBe = null;
			}
		}
	}

	/**
	 * Método que lista todas as Unidades Sefaz referente ao Tipo de Unidade Sefaz selecionado.
	 * @param request
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @throws IntegracaoException
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void listarUnidadeSefazPorTipoUnidade(HttpServletRequest request) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException, IntegracaoException
	{
		GestaoPessoasBe gestaoPessoasBe = null;
		TipoUnidadeSefazIntegracaoVo tipoUnidadeSefazVo = 
				 new TipoUnidadeSefazIntegracaoVo(StringUtil.toInteger(request.getAttribute(UNIDADE_SEFAZ_VO).toString()));
		UnidadeSefazIntegracaoVo unidadeSefazVo = new UnidadeSefazIntegracaoVo();
		unidadeSefazVo.setTipoUnidade(tipoUnidadeSefazVo);
		unidadeSefazVo.setParametroConsulta(unidadeSefazVo);
		try
		{
			gestaoPessoasBe = new GestaoPessoasBe();
			unidadeSefazVo = gestaoPessoasBe.listUnidSefazAtivaPorCodgTipoUnid(unidadeSefazVo);
			Validador.validaObjeto(unidadeSefazVo);
			request.setAttribute(UNIDADE_SEFAZ_VO, unidadeSefazVo);
		}
		catch (SQLException e)
		{
			throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_GESTAO_PESSOAS, e);
		}
		finally
		{
			if (gestaoPessoasBe != null)
			{
				gestaoPessoasBe.close();
				gestaoPessoasBe = null;
			}
		}
	}

	/**
	 * Método responsável por consultarServidorSefaz através da integração com o gestaoPessoasBe.
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws IntegracaoException
	 * @throws ConexaoException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private void consultarServidorSefaz(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  IntegracaoException, ConexaoException
	{
		GestaoPessoasBe gestaoPessoasBe = null;
		try
		{
			gestaoPessoasBe = new GestaoPessoasBe();
			ServidorSefazIntegracaoVo servidorSefazIntegracaoVo = 
						(ServidorSefazIntegracaoVo) request.getAttribute(SERVIDOR_VO);
			servidorSefazIntegracaoVo = gestaoPessoasBe.buscarServidorSefazPorNumrMatricula(servidorSefazIntegracaoVo);
			Validador.validaObjeto(servidorSefazIntegracaoVo);
			request.setAttribute(SERVIDOR_VO, servidorSefazIntegracaoVo);
		}
		catch (ParametroObrigatorioException e)
		{
			request.setAttribute(EXCEPTION, e);
		}
		catch (SQLException e)
		{
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		catch (Exception e)
		{
			throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_GESTAO_PESSOAS, e);
		}
		finally
		{
			if (gestaoPessoasBe != null)
			{
				gestaoPessoasBe.close();
				gestaoPessoasBe = null;
			}
		}
	}

	private void listarUnidadeSefazAtivaPorSiglaUnidadeSuperior(HttpServletRequest request) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException, IntegracaoException
	{
	   GestaoPessoasBe gestaoPessoasBe = null;
	   UnidadeSefazIntegracaoVo consulta = new UnidadeSefazIntegracaoVo();
	   UnidadeSefazIntegracaoVo parametro = new UnidadeSefazIntegracaoVo();
		parametro.setSiglaUnidade((String) request.getAttribute(SIGLA_UNIDADE_SUPERIOR));
	   consulta.setParametroConsulta(parametro);
	   try
	   {
	      gestaoPessoasBe = new GestaoPessoasBe();
	      consulta = gestaoPessoasBe.listUnidSefazAtivaPorSiglUnidSuperior(consulta);
	      Validador.validaObjeto(consulta);
	      request.setAttribute(UNIDADE_SEFAZ_VO, consulta);
	   }
	   catch (SQLException e)
	   {
	      throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_GESTAO_PESSOAS, e);
	   }
	   finally
	   {
	      if (gestaoPessoasBe != null)
	      {
	         gestaoPessoasBe.close();
	         gestaoPessoasBe = null;
	      }
	   }	
	}
}
