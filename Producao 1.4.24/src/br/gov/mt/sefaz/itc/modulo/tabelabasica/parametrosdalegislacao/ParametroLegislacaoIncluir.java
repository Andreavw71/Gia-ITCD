package br.gov.mt.sefaz.itc.modulo.tabelabasica.parametrosdalegislacao;

import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.PaginaNaoEncontradaException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.RegistroExistenteException;
import br.com.abaco.util.exceptions.RegistroNaoPodeSerUtilizadoException;
import br.com.abaco.util.exceptions.TipoRedirecionamentoInvalidoException;

import br.gov.mt.sefaz.itc.model.tabelabasica.parametrosdalegislacao.AliquotaVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.parametrosdalegislacao.Form;
import br.gov.mt.sefaz.itc.model.tabelabasica.parametrosdalegislacao.MultaVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.parametrosdalegislacao.ParametroLegislacaoBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.parametrosdalegislacao.ParametroLegislacaoVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoGIA;
import br.gov.mt.sefaz.itc.util.facade.Flow;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.http.AbstractServletITCD;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 
 * @author Anderson Boehler Iglesias Araujo
 * @version $Revision: 1.1.1.1 $
 * Revisão: Wendell Farias.
 */
public class ParametroLegislacaoIncluir extends AbstractServletITCD implements Flow, Form
{
	private static final int REQUISICAO_VALIDAR_NUMERO_LEGISLACAO = 2;
	private static final int REQUISICAO_INCLUIR_ALIQUOTA = 3;
	private static final int REQUISICAO_INCLUIR_MULTA = 4;
	private static final int REQUISICAO_INCLUIR_PARAMETRO_LEGISLACAO = 5;
	private static final int REQUISICAO_EXCLUIR_ALIQUOTA = 6;
	private static final int REQUISICAO_EXCLUIR_MULTA = 7;
	private static final int REQUISICAO_CONSULTAR_DATA_FINAL_ULTIMA_LEI = 8;

	/**
	 * Este método trabalha colaborativamente com o método redirecionar. 
	 * O método redirecionar determina a ação a ser tomada e processoRequest a executa.
	 * 
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws IntegracaoException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, ObjetoObrigatorioException, ConsultaException, ConexaoException, 
			  IntegracaoException, LogException, AnotacaoException, PersistenciaException
	{
		switch (redirecionar(request))
		{
			case REQUISICAO_VAZIA:
				{
					solicitarIncluirParametroLegislacao(request, response);
					break;
				}
			case REQUISICAO_VALIDAR_NUMERO_LEGISLACAO:
				{
					solicitarConsultarNumeroLegislacao(request, response);
					break;
				}
			case REQUISICAO_INCLUIR_ALIQUOTA:
				{
					incluirAliquota(request, response);
					break;
				}
			case REQUISICAO_INCLUIR_MULTA:
				{
					incluirMulta(request, response);
					break;
				}
			case REQUISICAO_EXCLUIR_ALIQUOTA:
				{
					excluirAliquota(request, response);
					break;
				}
			case REQUISICAO_EXCLUIR_MULTA:
				{
					excluirMulta(request, response);
					break;
				}
			case REQUISICAO_INCLUIR_PARAMETRO_LEGISLACAO:
				{
					incluirParametroLegislacao(request, response);
					break;
				}
			case REQUISICAO_CONSULTAR_DATA_FINAL_ULTIMA_LEI:
				{
					consultarDataFinalUltimaLeiCadastrada(request, response);
					break;
				}
		}
	}

	/**
	 *  Método responsável pela análise dos parâmetros e a tomada de decisão quanto ao controle do fluxo da aplicação.
	 *      
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @return
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	protected int redirecionar(HttpServletRequest request)
	{
		if (Validador.isStringValida(request.getParameter(PARAMETRO_VALIDAR_NUMERO_LEGISLACAO)))
		{
			return REQUISICAO_VALIDAR_NUMERO_LEGISLACAO;
		}
		if (Validador.isStringValida(request.getParameter(BOTAO_ADICIONAR_ALIQUOTA)))
		{
			return REQUISICAO_INCLUIR_ALIQUOTA;
		}
		if (Validador.isStringValida(request.getParameter(BOTAO_ADICIONAR_MULTA)))
		{
			return REQUISICAO_INCLUIR_MULTA;
		}
		if (Validador.isStringValida(request.getParameter(PARAMETRO_EXCLUIR_ALIQUOTA)))
		{
			return REQUISICAO_EXCLUIR_ALIQUOTA;
		}
		if (Validador.isStringValida(request.getParameter(PARAMETRO_EXCLUIR_MULTA)))
		{
			return REQUISICAO_EXCLUIR_MULTA;
		}
		if (Validador.isStringValida(request.getParameter(BOTAO_CONFIRMAR)))
		{
			return REQUISICAO_INCLUIR_PARAMETRO_LEGISLACAO;
		}
		if (Validador.isStringValida(request.getParameter(PARAMETRO_CONSULTAR_DATA_FINAL_ULTIMA_LEI)))
		{
			return REQUISICAO_CONSULTAR_DATA_FINAL_ULTIMA_LEI;
		}
		return REQUISICAO_VAZIA;
	}

	/**
	 * Método responsável por mostrar a tela inicial dessa funcionalidade
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws PaginaNaoEncontradaException Esta exceção será lançada pelo framework Ábaco caso o sistema tente acessar uma URL que não está corretamente mapeada ou não foi mapeada.
	 * @throws TipoRedirecionamentoInvalidoException Esta exceção será lançada pelo framework Ábaco caso o sistema tente passar para o parâmetro tipoRedirecionamento do método processFlow um tipo de redirecionamento não válido.
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private void solicitarIncluirParametroLegislacao(HttpServletRequest request, HttpServletResponse response) throws PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		removeBuffer(request);
		getBuffer(request).setAttribute(PARAMETRO_LEGISLACAO_VO, new ParametroLegislacaoVo(), request);
		processFlow(VIEW_INCLUIR_PARAMETRO_LEGISLACAO, request, response, FORWARD);
	}

	/**
	 * Método responsável por solicitar uma consulta ao banco para verificar se o número da legislação já existe no banco de dados
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws IntegracaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void solicitarConsultarNumeroLegislacao(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, IntegracaoException, PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException
	{
		ParametroLegislacaoVo parametroLegislacaoVo = controladorInterativoJSP(request);
		Validador.validaObjeto(parametroLegislacaoVo);
		ParametroLegislacaoBe parametroLegislacaoBe = null;
		try
		{
			if (Validador.isNumericoValido(parametroLegislacaoVo.getNumeroLegislacao()) & 
						Validador.isNumericoValido(parametroLegislacaoVo.getAnoLegislacao()))
			{
				ParametroLegislacaoVo parametroLegislacaoConsultaVo = new ParametroLegislacaoVo();
				parametroLegislacaoConsultaVo.setNumeroLegislacao(parametroLegislacaoVo.getNumeroLegislacao());
				parametroLegislacaoConsultaVo.setAnoLegislacao(parametroLegislacaoVo.getAnoLegislacao());
				parametroLegislacaoConsultaVo.setConsultaCompleta(false);
				parametroLegislacaoConsultaVo = new ParametroLegislacaoVo(parametroLegislacaoConsultaVo);
				parametroLegislacaoBe = new ParametroLegislacaoBe();
				parametroLegislacaoBe.consultarParametroLegislacao(parametroLegislacaoConsultaVo);
				Validador.validaObjeto(parametroLegislacaoConsultaVo);
				if (parametroLegislacaoConsultaVo.isExiste())
				{
					request.setAttribute(EXCEPTION, new RegistroExistenteException(MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_NUMERO_JA_EXISTE));
					request.setAttribute(CAMPO_QUE_RECEBERA_FOCO, CAMPO_NUMERO_LEGISLACAO);
				}
				parametroLegislacaoConsultaVo = null;
				request.setAttribute(CAMPO_QUE_RECEBERA_FOCO, CAMPO_DATA_VIGENCIA_INICIAL); //direciona o foco para o proximo campo do formulario.
			}
			else
			{
				request.setAttribute(EXCEPTION, new ParametroObrigatorioException(MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_NUMERO_LEGISLACAO));
				request.setAttribute(CAMPO_QUE_RECEBERA_FOCO, CAMPO_NUMERO_LEGISLACAO);
			}
		   getBuffer(request).setAttribute(PARAMETRO_LEGISLACAO_VO, parametroLegislacaoVo, request);
		   processFlow(VIEW_INCLUIR_PARAMETRO_LEGISLACAO, request, response, FORWARD);			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		catch (ParametroObrigatorioException e)
		{
			getBuffer(request).setAttribute(PARAMETRO_LEGISLACAO_VO, parametroLegislacaoVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_INCLUIR_PARAMETRO_LEGISLACAO, request, response, FORWARD);
		}
		finally
		{
			if (parametroLegislacaoBe != null)
			{
				parametroLegislacaoBe.close();
				parametroLegislacaoBe = null;
			}
		}
	}

	/**
	 * Todas as informações fornecidas pelo usuário será capturada por este método.
	 * Este método é responsável por adicionar ao VO todas as informações da funcionalidade Incluir e 
	 * devolve-lo preenchido para gravar no banco de dados.
	 * 
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @return
	 * @throws ObjetoObrigatorioException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private ParametroLegislacaoVo controladorInterativoJSP(HttpServletRequest request) throws ObjetoObrigatorioException
	{
		ParametroLegislacaoVo parametroLegislacaoVo = iniciaParametroLegislacaoVo(request);
		return parametroLegislacaoVo;
	}

	/**
	 * Metodo responsável por iniciar o vo do Parametro Legislação
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @return
	 * @throws ObjetoObrigatorioException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private ParametroLegislacaoVo iniciaParametroLegislacaoVo(HttpServletRequest request) throws ObjetoObrigatorioException
	{
	   ParametroLegislacaoVo parametroLegislacaoVo = (ParametroLegislacaoVo) getObjetoRequest(request, PARAMETRO_LEGISLACAO_VO);
		Validador.validaObjeto(parametroLegislacaoVo);
		if (Validador.isStringValida(request.getParameter(CAMPO_NUMERO_LEGISLACAO)))
		{
			parametroLegislacaoVo.setNumeroLegislacao(StringUtil.toInt(request.getParameter(CAMPO_NUMERO_LEGISLACAO)));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_ANO_LEGISLACAO)))
		{
			parametroLegislacaoVo.setAnoLegislacao(StringUtil.toInt(request.getParameter(CAMPO_ANO_LEGISLACAO)));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_DATA_VIGENCIA_INICIAL)))
		{
			parametroLegislacaoVo.setDataInicioVigencia(StringUtil.toUtilData(request.getParameter(CAMPO_DATA_VIGENCIA_INICIAL)));
		}
		else
		{
			parametroLegislacaoVo.setDataInicioVigencia(null);
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_DATA_VIGENCIA_FINAL)))
		{
			parametroLegislacaoVo.setDataFimVigencia(StringUtil.toUtilData(request.getParameter(CAMPO_DATA_VIGENCIA_FINAL)));
		}
		else
		{
			parametroLegislacaoVo.setDataFimVigencia(null);
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_NUMERO_LEGISLACAO_PRINCIPAL)))
		{
			parametroLegislacaoVo.setNumeroLegislacaoPrincipal(StringUtil.toInt(request.getParameter(CAMPO_NUMERO_LEGISLACAO_PRINCIPAL)));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_ANO_LEGISLACAO_PRINCIPAL)))
		{
			parametroLegislacaoVo.setAnoLegislacaoPrincipal(StringUtil.toInt(request.getParameter(CAMPO_ANO_LEGISLACAO_PRINCIPAL)));
		}
		if (Validador.isStringValida(request.getParameter(CAMPO_SELECT_CALCULO_CASCATA)))
		{
			parametroLegislacaoVo.setCalculoCascata(new DomnSimNao(StringUtil.toInt(request.getParameter(CAMPO_SELECT_CALCULO_CASCATA))));
		}
		parametroLegislacaoVo.setStatusLegislacao(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		return parametroLegislacaoVo;
	}

	/**
	 * Método responsável por iniciar o vo da Aliquota
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @return
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private AliquotaVo iniciaAliquotaVo(HttpServletRequest request)
	{
		//Começa a AliquotaVo
		AliquotaVo aliquotaVo = new AliquotaVo();
		int tipoFatoGerador = StringUtil.toInt(request.getParameter(CAMPO_SELECT_TIPO_FATO_GERADOR));
		if (Validador.isNumericoValido(tipoFatoGerador))
		{
			aliquotaVo.setTipoFatoGerador(new DomnTipoGIA(tipoFatoGerador));
		}
		int tipoIsencao = StringUtil.toInt(request.getParameter(CAMPO_CHECK_ISENCAO));
		if (tipoIsencao == 0)
		{
			aliquotaVo.setTipoIsencao(new DomnSimNao(DomnSimNao.NAO));
			double percentual = StringUtil.monetarioToDouble(request.getParameter(CAMPO_PERCENTUAL));
			if (Validador.isNumericoValido(percentual))
			{
				aliquotaVo.setPercentualLegislacaoAliquota(percentual);
			}
		}
		else if (Validador.isNumericoValido(tipoIsencao))
		{
			aliquotaVo.setTipoIsencao(new DomnSimNao(tipoIsencao));
		}
		int qtdUPFIni = StringUtil.toInt(request.getParameter(CAMPO_QUANTIDADE_UPF_INICIAL));
		if (Validador.isNumericoValido(qtdUPFIni))
		{
			aliquotaVo.setQuantidadeUPFInicial(qtdUPFIni);
		}
		int qtdUPFFin = StringUtil.toInt(request.getParameter(CAMPO_QUANTIDADE_UPF_FINAL));
		if (Validador.isNumericoValido(qtdUPFFin))
		{
			aliquotaVo.setQuantidadeUPFFinal(qtdUPFFin);
		}
		getBuffer(request).setAttribute(ALIQUOTA_VO, aliquotaVo, request);
		return aliquotaVo;
	}

	/**
	 * Método responsável por iniciar o vo da Multa
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @return
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	private MultaVo iniciaMultaVo(HttpServletRequest request)
	{
		//Começa a MultaVo
		MultaVo multaVo = new MultaVo();
		int qtdDiaIni = StringUtil.toInt(request.getParameter(CAMPO_QUANTIDADE_DIAS_INICIAL));
		if (Validador.isNumericoValido(qtdDiaIni))
		{
			multaVo.setQuantidadeDiasInicial(qtdDiaIni);
		}
		int qtdDiaFin = StringUtil.toInt(request.getParameter(CAMPO_QUANTIDADE_DIAS_FINAL));
		if (Validador.isNumericoValido(qtdDiaFin))
		{
			multaVo.setQuantidadeDiasFinal(qtdDiaFin);
		}
		double percentualMulta = StringUtil.monetarioToDouble(request.getParameter(CAMPO_PERCENTUAL_MULTA));
		if (Validador.isNumericoValido(percentualMulta))
		{
			multaVo.setPercentualMulta(percentualMulta);
		}
		getBuffer(request).setAttribute(MULTA_VO, multaVo, request);
		return multaVo;
	}

	/**
	 * Método responsável por incluir a Aliquota do CollVo
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void incluirAliquota(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		ParametroLegislacaoVo parametroLegislacaoVo = controladorInterativoJSP(request);
		AliquotaVo aliquotaVo = iniciaAliquotaVo(request);
		Validador.validaObjeto(aliquotaVo);
		aliquotaVo.setUsuarioLogado(getCodigoUsuarioLogado(request));
		ParametroLegislacaoBe parametroLegislacaoBe = null;
		try
		{
			parametroLegislacaoBe = new ParametroLegislacaoBe();
			parametroLegislacaoBe.validaParametrosIncluirAliquota(aliquotaVo, parametroLegislacaoVo);
			parametroLegislacaoVo.getAliquotaVo().getCollVO().add(aliquotaVo); //adiciona na lista 
			Collections.sort((List) parametroLegislacaoVo.getAliquotaVo().getCollVO());
			parametroLegislacaoBe.validaParametrosIncluirAliquotaFaixaUPFCausaMortis(parametroLegislacaoVo);
			parametroLegislacaoBe.validaParametrosIncluirAliquotaFaixaUPFInterVivos(parametroLegislacaoVo);
			aliquotaVo = new AliquotaVo();
		   getBuffer(request).setAttribute(PARAMETRO_LEGISLACAO_VO, parametroLegislacaoVo, request);
		   getBuffer(request).setAttribute(ALIQUOTA_VO, aliquotaVo, request);
		   processFlow(VIEW_INCLUIR_PARAMETRO_LEGISLACAO, request, response, FORWARD);			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		catch (ParametroObrigatorioException e)
		{
			parametroLegislacaoVo.getAliquotaVo().getCollVO().remove(aliquotaVo);
			getBuffer(request).setAttribute(PARAMETRO_LEGISLACAO_VO, parametroLegislacaoVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_INCLUIR_PARAMETRO_LEGISLACAO, request, response, FORWARD);
		}
		catch (RegistroExistenteException e)
		{
			getBuffer(request).setAttribute(PARAMETRO_LEGISLACAO_VO, parametroLegislacaoVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_INCLUIR_PARAMETRO_LEGISLACAO, request, response, FORWARD);
		}
		finally
		{
			if (parametroLegislacaoBe != null)
			{
				parametroLegislacaoBe.close();
				parametroLegislacaoBe = null;
			}
		}
	}

	/**
	 * Método responsável por incluir a Multa do CollVo
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void incluirMulta(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		ParametroLegislacaoVo parametroLegislacaoVo = controladorInterativoJSP(request);
		MultaVo multaVo = iniciaMultaVo(request);
		Validador.validaObjeto(multaVo);
		multaVo.setUsuarioLogado(getCodigoUsuarioLogado(request));
		ParametroLegislacaoBe parametroLegislacaoBe = null;
		try
		{
			parametroLegislacaoBe = new ParametroLegislacaoBe();
			parametroLegislacaoBe.validaParametrosIncluirMulta(multaVo, parametroLegislacaoVo);

			parametroLegislacaoBe.validaParametrosIncluirMultaGrupo(parametroLegislacaoVo);
			parametroLegislacaoVo.getMultaVo().getCollVO().add(multaVo);			

			multaVo = new MultaVo();
		   getBuffer(request).setAttribute(PARAMETRO_LEGISLACAO_VO, parametroLegislacaoVo, request);
		   getBuffer(request).setAttribute(MULTA_VO, multaVo, request);
		   processFlow(VIEW_INCLUIR_PARAMETRO_LEGISLACAO, request, response, FORWARD);			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		catch (ParametroObrigatorioException e)
		{
			parametroLegislacaoVo.getMultaVo().getCollVO().remove(multaVo);
			getBuffer(request).setAttribute(PARAMETRO_LEGISLACAO_VO, parametroLegislacaoVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_INCLUIR_PARAMETRO_LEGISLACAO, request, response, FORWARD);
		}
		catch (RegistroExistenteException e)
		{
			getBuffer(request).setAttribute(PARAMETRO_LEGISLACAO_VO, parametroLegislacaoVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_INCLUIR_PARAMETRO_LEGISLACAO, request, response, FORWARD);
		}
		finally
		{
			if (parametroLegislacaoBe != null)
			{
				parametroLegislacaoBe.close();
				parametroLegislacaoBe = null;
			}
		}
	}

	/**
	 * Método responsável por incluir ParametroLegislação
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws IntegracaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void incluirParametroLegislacao(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, IntegracaoException, PaginaNaoEncontradaException, 
			  TipoRedirecionamentoInvalidoException, LogException, AnotacaoException, PersistenciaException
	{
		ParametroLegislacaoVo parametroLegislacaoVo = controladorInterativoJSP(request);
		Validador.validaObjeto(parametroLegislacaoVo);
		ParametroLegislacaoBe parametroLegislacaoBe = null;
		try
		{
			parametroLegislacaoBe = new ParametroLegislacaoBe();
			parametroLegislacaoVo.setUsuarioLogado(getCodigoUsuarioLogado(request));
			//codigo do LOG
			obterInformacoesLogSefaz(request, parametroLegislacaoVo);
			//fim do código do LOG
			parametroLegislacaoBe.incluirParametroLegislacao(parametroLegislacaoVo);
			request.setAttribute(ENTIDADE_VO, parametroLegislacaoVo);
			processFlow(VIEW_SUCESSO, request, response, FORWARD);
		}
		catch (RegistroNaoPodeSerUtilizadoException e)
		{
			getBuffer(request).setAttribute(PARAMETRO_LEGISLACAO_VO, parametroLegislacaoVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_ALTERAR_PARAMETRO_LEGISLACAO, request, response, FORWARD);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		catch (ParametroObrigatorioException e)
		{
			getBuffer(request).setAttribute(PARAMETRO_LEGISLACAO_VO, parametroLegislacaoVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_INCLUIR_PARAMETRO_LEGISLACAO, request, response, FORWARD);
		}
		catch (RegistroExistenteException e)
		{
			getBuffer(request).setAttribute(PARAMETRO_LEGISLACAO_VO, parametroLegislacaoVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_INCLUIR_PARAMETRO_LEGISLACAO, request, response, FORWARD);
		}
		finally
		{
			if (parametroLegislacaoBe != null)
			{
				parametroLegislacaoBe.close();
				parametroLegislacaoBe = null;
			}
		}
	}

	/**
	 * Método responsável por excluir a Aliquota do CollVo
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void excluirAliquota(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		ParametroLegislacaoVo parametroLegislacaoVo = controladorInterativoJSP(request);
		AliquotaVo aliquotaVo = iniciaAliquotaVo(request);
		int index = StringUtil.toInt(request.getParameter(PARAMETRO_EXCLUIR_ALIQUOTA));
		((ArrayList) parametroLegislacaoVo.getAliquotaVo().getCollVO()).remove(index);
		getBuffer(request).setAttribute(PARAMETRO_LEGISLACAO_VO, parametroLegislacaoVo, request);
		getBuffer(request).setAttribute(ALIQUOTA_VO, aliquotaVo, request);
		processFlow(VIEW_INCLUIR_PARAMETRO_LEGISLACAO, request, response, FORWARD);
	}

	/**
	 * Método responsável por excluir a Multa do CollVo
	 * @param request (javax.servlet.http.HttpServletRequest)
	 * @param response (javax.servlet.http.HttpServletResponse)
	 * @throws ObjetoObrigatorioException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Anderson Boehler Iglesias Araujo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void excluirMulta(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		ParametroLegislacaoVo parametroLegislacaoVo = controladorInterativoJSP(request);
		MultaVo multaVo = iniciaMultaVo(request);
		int index = StringUtil.toInt(request.getParameter(PARAMETRO_EXCLUIR_MULTA));
		((ArrayList) parametroLegislacaoVo.getMultaVo().getCollVO()).remove(index);
		getBuffer(request).setAttribute(PARAMETRO_LEGISLACAO_VO, parametroLegislacaoVo, request);
		getBuffer(request).setAttribute(MULTA_VO, multaVo, request);
		processFlow(VIEW_INCLUIR_PARAMETRO_LEGISLACAO, request, response, FORWARD);
	}

	/**
	 * Método que efetua a consulta de ultima lei cadastrada
	 * @param request
	 * @param response
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws PaginaNaoEncontradaException
	 * @throws TipoRedirecionamentoInvalidoException
	 * @implemented by Daniel Balieiro
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void consultarDataFinalUltimaLeiCadastrada(HttpServletRequest request, HttpServletResponse response) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, PaginaNaoEncontradaException, TipoRedirecionamentoInvalidoException
	{
		ParametroLegislacaoVo parametroLegislacaoVo = controladorInterativoJSP(request);
		Validador.validaObjeto(parametroLegislacaoVo);
		ParametroLegislacaoBe parametroLegislacaoBe = null;
		try
		{
			parametroLegislacaoBe = new ParametroLegislacaoBe();
			parametroLegislacaoBe.consultarParametroLegislacaoUltima(parametroLegislacaoVo);
			Validador.validaObjeto(parametroLegislacaoVo);
			if (parametroLegislacaoVo.getDataFimVigencia() == null)
			{
				request.setAttribute(EXCEPTION, new RegistroExistenteException(MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_EXISTE_LEI_ABERTO));
				request.setAttribute(CAMPO_QUE_RECEBERA_FOCO, CAMPO_NUMERO_LEGISLACAO);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		catch (ParametroObrigatorioException e)
		{
			getBuffer(request).setAttribute(PARAMETRO_LEGISLACAO_VO, parametroLegislacaoVo, request);
			request.setAttribute(EXCEPTION, e);
			processFlow(VIEW_INCLUIR_PARAMETRO_LEGISLACAO, request, response, FORWARD);
		}
		finally
		{
			if (parametroLegislacaoBe != null)
			{
				parametroLegislacaoBe.close();
				parametroLegislacaoBe = null;
			}
		}
		//TODOVerificar o porque dessa linha comentada getBuffer(request).setAttribute(PARAMETRO_LEGISLACAO_VO, parametroLegislacaoVo, request);
		processFlow(VIEW_INCLUIR_PARAMETRO_LEGISLACAO, request, response, FORWARD);
	}
}
