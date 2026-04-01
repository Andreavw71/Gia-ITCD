package br.gov.mt.sefaz.itc.modulo.tabelabasica.naturezadaoperacao;

import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.PaginaNaoEncontradaException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.RegistroExistenteException;
import br.com.abaco.util.exceptions.TipoRedirecionamentoInvalidoException;
import br.com.abaco.util.http.AbstractAbacoServlet;

import br.gov.mt.sefaz.itc.model.tabelabasica.naturezadaoperacao.Form;
import br.gov.mt.sefaz.itc.model.tabelabasica.naturezadaoperacao.NaturezaOperacaoBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.naturezadaoperacao.NaturezaOperacaoVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoGIA;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcesso;
import br.gov.mt.sefaz.itc.util.facade.Flow;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 
 * @author Rogério Sanches de Oliveira
 * @revisao Wendell Pereira de Farias /Mar2008
 * @version $Revision: 1.1.1.1 $
 */
public class NaturezaOperacaoIncluir extends AbstractAbacoServlet implements Flow, Form
{
	/**
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private static final int REQUISICAO_INCLUIR_NATUREZA_OPERACAO = 2;

	/**
	 * Este método trabalha colaborativamente com o método redirecionar. 
	 * O método redirecionar determina a ação a ser tomada e processoRequest a executa.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @throws ConexaoException Esta exceção deverá ser lançada para substibuir o SQLException na camada de negócio e na camada de apresentação.
	 * @implemented by Rogério Sanches de Oliveira
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, ConexaoException, ConsultaException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		switch (redirecionar(request))
		{
			case REQUISICAO_VAZIA:
				{
					solicitarIncluirNaturezaOperacao(request, response);
					break;
				}
			case REQUISICAO_INCLUIR_NATUREZA_OPERACAO:
				{
					incluirNaturezaOperacao(request, response);
					break;
				}
		}
	}

	/**
	 *  Este método é responsável pela análise dos parâmetros e a tomada de decisão quanto ao controle do fluxo da aplicação.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	protected int redirecionar(HttpServletRequest request)
	{
		if (Validador.isStringValida(request.getParameter(BOTAO_CONFIRMAR)))
		{
			return REQUISICAO_INCLUIR_NATUREZA_OPERACAO;
		}
		return REQUISICAO_VAZIA;
	}

	/**
	 * Método responsável por redirecionar para a JSP de incluir Bem.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void solicitarIncluirNaturezaOperacao(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		removeBuffer(request);
		getBuffer(request).setAttribute(NATUREZA_OPERACAO_VO, new NaturezaOperacaoVo(), request);
		processFlow(VIEW_INCLUIR_NATUREZA_OPERACAO, request, response, FORWARD);
	}

	/**
	 * Método responsável por incluir um Bem.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @throws ConexaoException Esta exceção deverá ser lançada para substibuir o SQLException na camada de negócio e na camada de apresentação.
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private void incluirNaturezaOperacao(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException, ConexaoException, ConsultaException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		NaturezaOperacaoVo naturezaOperacaoVo = controladorInterativoJSP(request);
		Validador.validaObjeto(naturezaOperacaoVo);
		NaturezaOperacaoBe naturezaOperacaoBe = null;
		try
		{
			naturezaOperacaoBe = new NaturezaOperacaoBe();
			obterInformacoesLogSefaz(request, naturezaOperacaoVo);
			naturezaOperacaoBe.incluirNaturezaOperacao(naturezaOperacaoVo);
			request.setAttribute(NATUREZA_OPERACAO_VO, naturezaOperacaoVo);
			processFlow(VIEW_SUCESSO, request, response, FORWARD);
		}
		catch (ParametroObrigatorioException e)
		{
			request.setAttribute(EXCEPTION, e);
			request.setAttribute(CAMPO_QUE_RECEBERA_FOCO, CAMPO_PERCENTUAL_NATUREZA_OPERACAO);
			getBuffer(request).setAttribute(NATUREZA_OPERACAO_VO, naturezaOperacaoVo, request);
			processFlow(VIEW_INCLUIR_NATUREZA_OPERACAO, request, response, FORWARD);
		}
		catch (RegistroExistenteException e)
		{
			getBuffer(request).setAttribute(NATUREZA_OPERACAO_VO, naturezaOperacaoVo, request);
			request.setAttribute(EXCEPTION, e);
			request.setAttribute(CAMPO_QUE_RECEBERA_FOCO, CAMPO_DESCRICAO_NATUREZA_OPERACAO);
			processFlow(VIEW_INCLUIR_NATUREZA_OPERACAO, request, response, FORWARD);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		finally
		{
			if (naturezaOperacaoBe != null)
			{
				naturezaOperacaoBe.close();
				naturezaOperacaoBe = null;
			}
		}
	}

	/**
	 * Todas as informações fornecidas pelo usuário será capturada por este método.
	 * Este método é responsável por adicionar ao VO todas as informações da funcionalidade Incluir NaturezaOperacao e 
	 * devolve-lo preenchido para gravar no banco de dados.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @throws ClassCastException Esta exceção deve ser lançada para indicar que o código tentou converter um objeto para uma subclasse da qual ele não é uma instância.
	 * @throws NumberFormatException Esta exceção deve ser lançada para indicar que a aplicação tentou converter uma string para um dos tipos numéricos, mas a string não contém o formato apropriado.
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private NaturezaOperacaoVo controladorInterativoJSP(HttpServletRequest request) throws ObjetoObrigatorioException
	{
		NaturezaOperacaoVo naturezaOperacaoVo = iniciaNaturezaOperacaoVo(request);
		return naturezaOperacaoVo;
	}

	/**
	 * Este método verifica se o buffer possui o VO para adicionar as informações da funcionalidade IncluirBem. Se possuir, o VO é retornado.
	 * Caso contrário, é criada uma nova instância do BemVo para atender a necessidade do fluxo.
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @throws ClassCastException Esta exceção deve ser lançada para indicar que a aplicação tentou converter uma string para um dos tipos numéricos, mas a string não contém o formato apropriado.
	 * @throws NumberFormatException Esta exceção deve ser lançada para indicar que a aplicação tentou converter uma string para um dos tipos numéricos, mas a string não contém o formato apropriado.
	 * @return
	 * @implemented by Rogério Sanches de Oliveira
	 */
	private NaturezaOperacaoVo iniciaNaturezaOperacaoVo(HttpServletRequest request) throws ObjetoObrigatorioException
	{
		NaturezaOperacaoVo naturezaOperacaoVo = (NaturezaOperacaoVo) getBuffer(request).getAttribute(NATUREZA_OPERACAO_VO);
		Validador.validaObjeto(naturezaOperacaoVo);
		// gia
		int tipoNaturezaOperacao = StringUtil.toInt(request.getParameter(CAMPO_SELECT_TIPO_GIA));
		if (Validador.isNumericoValido(tipoNaturezaOperacao))
		{
			naturezaOperacaoVo.setTipoGIA(new DomnTipoGIA(tipoNaturezaOperacao));
		}
		// tipo pocesso causa mortis e inter vivos 
		if (tipoNaturezaOperacao == DomnTipoGIA.CAUSA_MORTIS)
		{
			int tipoProcessoNatureza = StringUtil.toInt(request.getParameter(CAMPO_SELECT_TIPO_PROCESSO_CAUSA_MORTIS));
			naturezaOperacaoVo.setTipoProcesso(new DomnTipoProcesso(tipoProcessoNatureza));
		}
		else
		{
			int tipoProcessoNatureza = StringUtil.toInt(request.getParameter(CAMPO_SELECT_TIPO_PROCESSO_INTER_VIVOS));
			naturezaOperacaoVo.setTipoProcesso(new DomnTipoProcesso(tipoProcessoNatureza));
		}
		naturezaOperacaoVo.setFlagIsencaoPrevistaLei(new DomnSimNao(StringUtil.toInt(request.getParameter(CAMPO_SELECT_ISENCAO_PREVISTA_LEI))));
		// descricao
		if (Validador.isStringValida(request.getParameter(CAMPO_DESCRICAO_NATUREZA_OPERACAO)))
		{
			naturezaOperacaoVo.setDescricaoNaturezaOperacao(request.getParameter(CAMPO_DESCRICAO_NATUREZA_OPERACAO));
		}
		double percentualNaturezaOperacao = 
				 StringUtil.monetarioToDouble(request.getParameter(CAMPO_PERCENTUAL_NATUREZA_OPERACAO));
		// tipo_base_calculo sim ou não
		int tipoIBaseCalculo = 0;
		if (Validador.isStringValida(request.getParameter(CAMPO_SELECT_TIPO_BASE_CALCULO_NATUREZA_OPERACAO)))
		{
			tipoIBaseCalculo = StringUtil.toInt(request.getParameter(CAMPO_SELECT_TIPO_BASE_CALCULO_NATUREZA_OPERACAO));
			if (tipoIBaseCalculo == DomnSimNao.SIM)
			{
				naturezaOperacaoVo.setTipoBaseCalculo(new DomnSimNao(tipoIBaseCalculo));
				// percentual
				if (Validador.isNumericoValido(percentualNaturezaOperacao))
				{
					naturezaOperacaoVo.setPercentualBaseCalculo(percentualNaturezaOperacao);
				}
			}
			else
			{
				naturezaOperacaoVo.setTipoBaseCalculo(new DomnSimNao(tipoIBaseCalculo));
				naturezaOperacaoVo.setPercentualBaseCalculo(percentualNaturezaOperacao);
			}
		}
		naturezaOperacaoVo.setStatusNaturezaOperacao(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		//getBuffer(request).setAttribute(NATUREZA_OPERACAO_VO, naturezaOperacaoVo, request);
		return naturezaOperacaoVo;
	}
}
