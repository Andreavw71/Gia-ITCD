package br.gov.mt.sefaz.itc.util.integracao.acessoweb;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.IntegracaoErro;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.util.integracao.acessoweb.UsuarioIntegracaoVo;

import java.sql.Connection;
import java.sql.SQLException;

import sefaz.mt.acessoweb.Usuario;
import sefaz.mt.acessoweb.integracao.IntegracaoAcessoWeb;


public class AcessoWebBe extends AbstractBe
{

	/**
	 * @param conexao
	 */
	public AcessoWebBe(Connection conexao)
	{
		super(conexao);
	}

	public AcessoWebBe() throws SQLException
	{
		super();
	}
	
	/**Método de Integração responsável por obter os dados do usuario pelo seu código.
	 * @param usuarioIntegracaoVo objeto contendo o codg do usuario.
	 * @throws ObjetoObrigatorioException
	 * @throws IntegracaoException
	 * @return UsuarioIntegracaoVo contendo os dados do usuário
	 * @implemented by Roniselton Barreto Rodrigues Silva
	 */
	public UsuarioIntegracaoVo obterUsuarioPorCodigo(UsuarioIntegracaoVo usuarioIntegracaoVo) throws ObjetoObrigatorioException, 
			  IntegracaoException
	{
		Validador.validaObjeto(usuarioIntegracaoVo);
		try
		{
         // TODO Não Utiliza conexão do ITCD.
			IntegracaoAcessoWeb integracao = new IntegracaoAcessoWeb();
			if (usuarioIntegracaoVo.isConsultaParametrizada())
			{
				if (Validador.isNumericoValido(usuarioIntegracaoVo.getParametroConsulta().getCodigo()))
				{
					return new UsuarioIntegracaoVo(integracao.buscarUsuario(usuarioIntegracaoVo.getParametroConsulta().getCodigo().intValue()));
				}
				else
				{
					throw new ParametroObrigatorioException("O código do usuário é obrigatório.");
				}
			}
			else
			{
				throw new ParametroObrigatorioException("O código do usuário é obrigatório.");
			}
		}
		catch (Exception e)
		{
			throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_ACESSO_WEB, e);
		}
		catch (Error e)
		{
			throw IntegracaoErro.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_ACESSO_WEB, e);
		}
	}

	/**Método de Integração responsável por obter os dados do usuario pelo seu código.
	 * @param usuarioIntegracaoVo objeto contendo o codg do usuario.
	 * @throws ObjetoObrigatorioException
	 * @throws IntegracaoException
	 * @return UsuarioIntegracaoVo contendo os dados do usuário
	 * @implemented by Roniselton Barreto Rodrigues Silva
	 */
	public UsuarioIntegracaoVo obterUsuarioPorIdentificacao(UsuarioIntegracaoVo usuarioIntegracaoVo) throws ObjetoObrigatorioException, 
			  IntegracaoException
	{
		Validador.validaObjeto(usuarioIntegracaoVo);
		try
		{
			IntegracaoAcessoWeb integracao = new IntegracaoAcessoWeb();
			if (usuarioIntegracaoVo.isConsultaParametrizada())
			{
				if(!Validador.isStringValida(usuarioIntegracaoVo.getParametroConsulta().getIdentificacao()))
				{
				   throw new ParametroObrigatorioException("A identificação do usuário é obrigatória.");
				}
				if(!Validador.isNumericoValido(usuarioIntegracaoVo.getParametroConsulta().getCodgTipoUsuario()))
				{
					usuarioIntegracaoVo.getParametroConsulta().setCodgTipoUsuario(Usuario.FUNCIONARIOSEFAZ);
				}
				if(!Validador.isNumericoValido(usuarioIntegracaoVo.getParametroConsulta().getCodgTipoOutroUsuario()))
				{
					usuarioIntegracaoVo.getParametroConsulta().setCodgTipoOutroUsuario(0);
				}
				return new UsuarioIntegracaoVo(integracao.buscarUsuario(usuarioIntegracaoVo.getParametroConsulta().getIdentificacao(), usuarioIntegracaoVo.getParametroConsulta().getCodgTipoUsuario(), usuarioIntegracaoVo.getParametroConsulta().getCodgTipoOutroUsuario()));
			}
			else
			{
				throw new ParametroObrigatorioException("A identificação do usuário é obrigatória.");
			}
		}
		catch (Exception e)
		{
			throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_ACESSO_WEB, e);
		}
		catch (Error e)
		{
			throw IntegracaoErro.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_ACESSO_WEB, e);
		}
	}	
}
