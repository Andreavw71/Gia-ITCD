package br.gov.mt.sefaz.itc.model.giaitcd.giaitcdseparacaodivorcio;

import br.com.abaco.log5.GenericoLogAnotacaoDao;
import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.DadoNecessarioInexistenteException;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.RegistroExistenteException;
import br.com.abaco.util.exceptions.RegistroNaoPodeSerUtilizadoException;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelBe;
import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelVo;
import br.gov.mt.sefaz.itc.model.generico.beneficiario.BeneficiarioVo;
import br.gov.mt.sefaz.itc.model.generico.conjuge.ConjugeBemTributavelBe;
import br.gov.mt.sefaz.itc.model.generico.conjuge.ConjugeBemTributavelVo;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.GIAITCDDoacaoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.GIAITCDDoacaoBeneficiarioVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.aliquota.GIAITCDDoacaoBeneficiarioAliquotaVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdseparacaodivorcio.aliquota.GIAITCDSeparacaDivorcioAliquotaBe;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdseparacaodivorcio.aliquota.GIAITCDSeparacaoDivorcioAliquotaVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.configuracaogerencialparametros.ConfiguracaoGerencialParametrosBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.configuracaogerencialparametros.ConfiguracaoGerencialParametrosVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.parametrosdalegislacao.AliquotaVo;
import br.gov.mt.sefaz.itc.util.NumeroUtil;
import br.gov.mt.sefaz.itc.util.calculo.CalculoITCD;
import br.gov.mt.sefaz.itc.util.dominio.DomnRegimeCasamento;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoConjuge;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoGIA;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProtocolo;
import br.gov.mt.sefaz.itc.util.dominio.DomnVersaoGIAITCD;
import br.gov.mt.sefaz.itc.util.facade.ConfiguracaoITCD;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.MensagemSucesso;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.CadastroBe;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.CepIntegracaoVo;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.ContribuinteIntegracaoVo;
import br.gov.mt.sefaz.itc.util.integracao.gestaopessoas.ServidorSefazIntegracaoVo;
import br.gov.mt.sefaz.itc.util.log.ExibirLOG;

import java.io.IOException;

import java.sql.Connection;
import java.sql.SQLException;

import java.text.DateFormat;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


/**
 * Classe que encapsula a regra de negócio da entidade GIA ITCD Separação Divórcio.
 * 
 * @author Thiago de Castilho Pacheco
 * @version $Revision: 1.7 $
 */
public class GIAITCDSeparacaoDivorcioBe extends AbstractBe
{  
	/**
	 * Construtor padrão.
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 * @implemented by Thiago de Castilho Pacheco
	 * @implemented by Lucas Nascimento
	 */
	public GIAITCDSeparacaoDivorcioBe() throws SQLException
	{
		super();
	}

	/**
	 * Construtor que recebe a conexão com o banco de dados.
	 * @param conexao
	 * @implemented by Daniel Balieiro
	 * @implemented by Thiago de Castilho Pacheco
	 * @implemented by Lucas Nascimento
	 */
	public GIAITCDSeparacaoDivorcioBe(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Método para validar a Alteração de uma GIA-ITCD Separação
	 * @param giaITCDSeparacaoDivorcioVo
	 * @throws ObjetoObrigatorioException
	 * @throws IntegracaoException
	 * @throws ParametroObrigatorioException
	 * @implemented by Daniel Balieiro
	 */
	private void validarAlterarGIAITCDSeparacaoDivorcio(final GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo) throws ObjetoObrigatorioException, 
			  IntegracaoException, ParametroObrigatorioException, RegistroNaoPodeSerUtilizadoException
	{
		Validador.validaObjeto(giaITCDSeparacaoDivorcioVo);
		validarAbaDadosGerais(giaITCDSeparacaoDivorcioVo);
		validarAbaBemTributavel(giaITCDSeparacaoDivorcioVo);
		validarAbaConjuge(giaITCDSeparacaoDivorcioVo);
	}

	/**
	 * Validacoes da Aba Conjuge
	 * @param giaITCDSeparacaoDivorcioVo
	 * @throws ParametroObrigatorioException
	 * @throws IntegracaoException
	 * @throws ObjetoObrigatorioException
	 * @implemented by Lucas Nascimento
	 */
	public static void validarAbaConjuge(final GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo) throws ParametroObrigatorioException, 
			  IntegracaoException, ObjetoObrigatorioException
	{
	   if (!Validador.isStringValida(giaITCDSeparacaoDivorcioVo.getConjuge1().getPessoaConjuge().getNumrDocumento()))
	   {
	      throw new ParametroObrigatorioException(MensagemErro.VALIDAR_CPF_INVALIDO);
	   }		
		//validaExisteConjuge1(giaITCDSeparacaoDivorcioVo);
		 if (!Validador.isStringValida(giaITCDSeparacaoDivorcioVo.getConjuge2().getPessoaConjuge().getNumrDocumento()))
		 {
		    throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_SEPARACAO_VALIDA_CONJUGE);
		 }		
		//validaCpfConjuge2(giaITCDSeparacaoDivorcioVo);
		//validaExisteConjuge2(giaITCDSeparacaoDivorcioVo);
		//validaBemTributavelConjuge1Vazio(giaITCDSeparacaoDivorcioVo); -> Validação retirada do Caso de Uso
		//validaBemTributavelConjuge2Vazio(giaITCDSeparacaoDivorcioVo); -> Validação retirada do Caso de Uso
		//validaConjuge2ResponsavelProcuradorIguais(giaITCDSeparacaoDivorcioVo);
		validaConjugeBemTributavel(giaITCDSeparacaoDivorcioVo);
	}

	/**
	 * Validadações da Aba Bem Tributavel
	 * @param giaITCDSeparacaoDivorcioVo
	 * @throws ParametroObrigatorioException
	 * @implemented by Lucas Nascimento
	 */
	public static void validarAbaBemTributavel(final GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo) throws ParametroObrigatorioException
	{
		validaBemTributavelVazio(giaITCDSeparacaoDivorcioVo);
	}

	/**
	 * Validações da Aba Dados Gerais
	 * @param giaITCDSeparacaoDivorcioVo
	 * @throws ParametroObrigatorioException
	 * @throws IntegracaoException
	 * @throws ObjetoObrigatorioException
	 * @implemented by Lucas Nascimento
	 */
	public static void validarAbaDadosGerais(final GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo) throws ParametroObrigatorioException, 
			  IntegracaoException, ObjetoObrigatorioException, RegistroNaoPodeSerUtilizadoException
	{
		validaResponsavel(giaITCDSeparacaoDivorcioVo);
	   validaDataCasamento(giaITCDSeparacaoDivorcioVo);
		validaRegimeCasamento(giaITCDSeparacaoDivorcioVo);
	   validaDataSeparacao(giaITCDSeparacaoDivorcioVo);
		validaNaturezaOperacao(giaITCDSeparacaoDivorcioVo);
		validaCPFPessoas(giaITCDSeparacaoDivorcioVo);
		GIAITCDBe.validaJustificativaAlteracao(giaITCDSeparacaoDivorcioVo);
	}
	
	

	/**
	 * Método responsável por realizer a validação referente ao atributo data de casamento.
	 * @param giaITCDSeparacaoDivorcioVo
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private static void validaDataCasamento(final GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException
	{
		Validador.validaObjeto(giaITCDSeparacaoDivorcioVo);
		if(!Validador.isDataValida(giaITCDSeparacaoDivorcioVo.getDataCasamento()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_DATA_CASAMENTO_NAO_INFORMADA);
		}
		if(!Validador.isDataValida(giaITCDSeparacaoDivorcioVo.getDataSeparacao()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_SEPARACAO_DIVORCIO_DATA_SEPARACAO);
		}
	   Date dataCasamento = giaITCDSeparacaoDivorcioVo.getDataParaCompararInicioDia(giaITCDSeparacaoDivorcioVo.getDataCasamento());
	   Date dataSeparacao = giaITCDSeparacaoDivorcioVo.getDataParaCompararInicioDia(giaITCDSeparacaoDivorcioVo.getDataSeparacao());  
		Date dataAtual = giaITCDSeparacaoDivorcioVo.getDataParaCompararInicioDia(new Date());		
		if(dataSeparacao.before(dataCasamento))
		{
			throw new ParametroObrigatorioException(MensagemErro.DATA_CASAMENTO_MAIOR_DATA_SEPARACAO);
		}
		else if(dataSeparacao.equals(dataCasamento))
		{
			throw new ParametroObrigatorioException(MensagemErro.DATA_CASAMENTO_IGUAL_DATA_SEPARACAO);
		}
		if(dataAtual.before(dataCasamento))
		{
			throw new ParametroObrigatorioException(MensagemErro.DATA_CASAMENTO_MAIOR_DATA_ATUAL);
		}
		else if(dataCasamento.equals(dataAtual))
		{
			throw new ParametroObrigatorioException(MensagemErro.DATA_CASAMENTO_IGUAL_DATA_ATUAL);
		}
	}

	/**
	 * Método responsável pore realizer a validação do Declarante ou Cônjuge 1, Procurador e Cônjuge 2.
	 * @param giaITCDSeparacaoDivorcioVo
	 * @throws ObjetoObrigatorioException
	 * @throws RegistroNaoPodeSerUtilizadoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private static void validaCPFPessoas(final GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo) throws ObjetoObrigatorioException, 
			  RegistroNaoPodeSerUtilizadoException, ParametroObrigatorioException
	{
		Validador.validaObjeto(giaITCDSeparacaoDivorcioVo);
	   validaCPFDeclarante(giaITCDSeparacaoDivorcioVo.getResponsavelVo(), giaITCDSeparacaoDivorcioVo);
		validaCPFProcurador(giaITCDSeparacaoDivorcioVo.getProcuradorVo(), giaITCDSeparacaoDivorcioVo);
	}

	/**
	 * Método responsavel por validar se todos os Bens Tributaveis foram atribuidos aos conjuges
	 * @param giaITCDSeparacaoDivorcioVo
	 * @throws ParametroObrigatorioException
	 * @implemented by Lucas Nascimento
	 */
	private static void validaConjugeBemTributavel(final GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo) throws ParametroObrigatorioException
	{
		if(!giaITCDSeparacaoDivorcioVo.getRegimeCasamento().is(DomnRegimeCasamento.SEPARACAO_TOTAL_DE_BENS))
		{
		   Map bensTributaveis = new TreeMap();
         double calculoPorcentual = giaITCDSeparacaoDivorcioVo.getCalculoPercentualValoresArbitradoConcordado();
		   for (Iterator itBensTributaveis = giaITCDSeparacaoDivorcioVo.getBemTributavelVo().getCollVO().iterator(); itBensTributaveis.hasNext(); )
		   {
		      BemTributavelVo bemTributavel = (BemTributavelVo) itBensTributaveis.next();
				if(bemTributavel.getBemParticular().is(DomnSimNao.NAO))
				{
               if(calculoPorcentual >= giaITCDSeparacaoDivorcioVo.getPorcentagemAconsiderar()){
                  if(bemTributavel.getConcordaComValorArbitrado().getTextoCorrente().equals("SIM")){
                     bensTributaveis.put(bemTributavel.getDescricaoBemTributavel() + 
                                 bemTributavel.getBemVo().getClassificacaoTipoBem().getTextoCorrente() +
                                 bemTributavel.getBemVo().getDescricaoTipoBem(), new Double(bemTributavel.getValorMercado()));
                  }else{
                  bensTributaveis.put(bemTributavel.getDescricaoBemTributavel() + 
                              bemTributavel.getBemVo().getClassificacaoTipoBem().getTextoCorrente() +
                              bemTributavel.getBemVo().getDescricaoTipoBem(), new Double(bemTributavel.getValorInformadoContribuinte()));
                  }
               }else{
				   bensTributaveis.put(bemTributavel.getDescricaoBemTributavel() + 
				               bemTributavel.getBemVo().getClassificacaoTipoBem().getTextoCorrente() +
									bemTributavel.getBemVo().getDescricaoTipoBem(), new Double(bemTributavel.getValorMercado()));	
               }
				}
		   }
		   Map conjuge1BemTributavel = new TreeMap();
		   for (Iterator itConjuge1BemTributavel = giaITCDSeparacaoDivorcioVo.getConjuge1().getCollVO().iterator(); itConjuge1BemTributavel.hasNext(); )
		   {
		      ConjugeBemTributavelVo conjugeBemTributavel = (ConjugeBemTributavelVo) itConjuge1BemTributavel.next();            
		      validaConjugeBemTributavelValorZero(conjugeBemTributavel);
            String  key = conjugeBemTributavel.getBemTributavelVo().getDescricaoBemTributavel() + 
		                  conjugeBemTributavel.getBemTributavelVo().getBemVo().getClassificacaoTipoBem().getTextoCorrente() +
								conjugeBemTributavel.getBemTributavelVo().getBemVo().getDescricaoTipoBem();
		      Double valorMercadoBem = (Double) bensTributaveis.get(key);
		      if (!Validador.isObjetoValido(valorMercadoBem))
		      {          
               String msgErro = "O bem ("+conjugeBemTributavel.getBemTributavelVo().getDescricaoBemTributavel()+ ") atribuído ao Cônjuge 1 não foi encontrado na lista de bens declarados. Por favor, verifique se descrição do bem esta examente igual na lista de bens declarados.";
		         ExibirLOG.exibirLog(msgErro, giaITCDSeparacaoDivorcioVo.getCodigo());
               throw new ParametroObrigatorioException(msgErro);
		      }
		      validaConjugeBemTributavelMaiorMercado(conjugeBemTributavel, valorMercadoBem);
		      conjuge1BemTributavel.put(conjugeBemTributavel.getBemTributavelVo().getDescricaoBemTributavel() + 
		                  conjugeBemTributavel.getBemTributavelVo().getBemVo().getClassificacaoTipoBem().getTextoCorrente() +
								conjugeBemTributavel.getBemTributavelVo().getBemVo().getDescricaoTipoBem(), new Double(conjugeBemTributavel.getValorConjuge()));
		   }
		   Map conjuge2BemTributavel = new TreeMap();
		   for (Iterator itConjuge2BemTributavel = giaITCDSeparacaoDivorcioVo.getConjuge2().getCollVO().iterator(); itConjuge2BemTributavel.hasNext(); )
		   {
		      ConjugeBemTributavelVo conjugeBemTributavel = (ConjugeBemTributavelVo) itConjuge2BemTributavel.next();
		      validaConjugeBemTributavelValorZero(conjugeBemTributavel);
		      String  key = conjugeBemTributavel.getBemTributavelVo().getDescricaoBemTributavel() + 
		                  conjugeBemTributavel.getBemTributavelVo().getBemVo().getClassificacaoTipoBem().getTextoCorrente() +
								conjugeBemTributavel.getBemTributavelVo().getBemVo().getDescricaoTipoBem();
		      Double valorMercadoBem = (Double) bensTributaveis.get(key);
		      if (valorMercadoBem == null)
		      {
		         valorMercadoBem = new Double(0);
		      }
		      validaConjugeBemTributavelMaiorMercado(conjugeBemTributavel, valorMercadoBem);
		      conjuge2BemTributavel.put(conjugeBemTributavel.getBemTributavelVo().getDescricaoBemTributavel() + 
		                  conjugeBemTributavel.getBemTributavelVo().getBemVo().getClassificacaoTipoBem().getTextoCorrente() +
								conjugeBemTributavel.getBemTributavelVo().getBemVo().getDescricaoTipoBem(), new Double(conjugeBemTributavel.getValorConjuge()));
		   }
		   for (Iterator itChaveBens = bensTributaveis.keySet().iterator(); itChaveBens.hasNext(); )
		   {
		      Double valorConjuge1 = new Double(0);
		      Double valorConjuge2 = new Double(0);
		      String descricaoBem = (String) itChaveBens.next();
		      if (conjuge1BemTributavel.get(descricaoBem) != null)
		      {
		         valorConjuge1 = (Double) conjuge1BemTributavel.get(descricaoBem);
		      }
		      if (conjuge2BemTributavel.get(descricaoBem) != null)
		      {
		         valorConjuge2 = (Double) conjuge2BemTributavel.get(descricaoBem);
		      }
		      Double somaValorConjuge = new Double(valorConjuge1.doubleValue() + valorConjuge2.doubleValue());
		      if (somaValorConjuge.equals(new Double(0)))
		      {
		         throw new ParametroObrigatorioException(MensagemErro.GIA_ITCD_SEPARACAO_VALIDAR_BEM_NAO_RELACIONADO_A_NENHUM_CONJUGE);
		      }
		      Double valorMercadoBemTributavel = (Double) bensTributaveis.get(descricaoBem);
		      if (NumeroUtil.arredondaNumero(valorMercadoBemTributavel.doubleValue()) != 
		               NumeroUtil.arredondaNumero(somaValorConjuge.doubleValue()))
		      {
		         throw new ParametroObrigatorioException(MensagemErro.GIA_ITCD_SEPARACAO_VALIDAR_SOMA_VALORES_BEM_CONJUGE_DIFERENTE_VALOR_MERCADO);
		      }
		   }			
		}
	}

	/**
	 * Valida se o valor atribuido ao conjuge é meior que o valor de mercado.
	 * @param conjugeBemTributavel
	 * @param valorMercadoBem
	 * @throws ParametroObrigatorioException
	 * @implemented by Lucas Nascimento
	 */
	private static void validaConjugeBemTributavelMaiorMercado(ConjugeBemTributavelVo conjugeBemTributavel, Double valorMercadoBem) throws ParametroObrigatorioException
	{
		if (conjugeBemTributavel.getValorConjuge() > valorMercadoBem.doubleValue())
		{
			throw new ParametroObrigatorioException(MensagemErro.GIA_ITCD_SEPARACAO_VALIDAR_VALOR_BEM_CONJUGE_MAIOR_VALOR_MERCADO);
		}
	}

	/**
	 * Valida se o valor atribuido ao Bem Tributavel é zero
	 * @param conjugeBemTributavel
	 * @throws ParametroObrigatorioException
	 * @implemented by Lucas Nascimento
	 */
	private static void validaConjugeBemTributavelValorZero(ConjugeBemTributavelVo conjugeBemTributavel) throws ParametroObrigatorioException
	{
		if (conjugeBemTributavel.getValorConjuge() <= 0)
		{
			throw new ParametroObrigatorioException(MensagemErro.GIA_ITCD_SEPARACAO_VALIDAR_VALOR_BEM_CONJUGE_ZERO_OU_MENOR);
		}
	}

	/**
	 * Valida se foram informados Bens Tributaveis.
	 * @param giaITCDSeparacaoDivorcioVo
	 * @throws ParametroObrigatorioException
	 * @implemented by Lucas Nascimento
	 */
	public static void validaBemTributavelVazio(final GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo) throws ParametroObrigatorioException
	{
		if (!Validador.isCollectionValida(giaITCDSeparacaoDivorcioVo.getBemTributavelVo().getCollVO()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_SEPARACAO_DIVORCIO_INFORMAR_PRIMEIRO_BEM_TRIBUTAVEL);
		}
	}

	/**
	 * Valida se a natureza de operação foi informada
	 * @param giaITCDSeparacaoDivorcioVo
	 * @throws ParametroObrigatorioException
	 * @implemented by Lucas Nascimento
	 */
	private static void validaNaturezaOperacao(final GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo) throws ParametroObrigatorioException
	{
		if (!giaITCDSeparacaoDivorcioVo.getNaturezaOperacaoVo().isExiste())
		{
			throw new ParametroObrigatorioException(MensagemErro.GIA_ITCD_SEPARACAO_VALIDAR_NATUREZA_OPERACAO);
		}
	}

	/**
	 * Valida de o Regime de Casamento foi informado.
	 * @param giaITCDSeparacaoDivorcioVo
	 * @throws ParametroObrigatorioException
	 * @implemented by Lucas Nascimento
	 */
	private static void validaRegimeCasamento(final GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo) throws ParametroObrigatorioException
	{
		if (!Validador.isDominioNumericoValido(giaITCDSeparacaoDivorcioVo.getRegimeCasamento()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_REGIME_CASAMENTO);
		}
	}

	/**
	 * Valida se a data de separação e maior que a data atual.
	 * @param giaITCDSeparacaoDivorcioVo
	 * @throws ParametroObrigatorioException
	 * @implemented by Lucas Nascimento
	 */
	private static void validaDataSeparacao(final GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo) throws ParametroObrigatorioException, 
			  ObjetoObrigatorioException
	{
		Validador.validaObjeto(giaITCDSeparacaoDivorcioVo);
		if(!Validador.isDataValida(giaITCDSeparacaoDivorcioVo.getDataSeparacao()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_SEPARACAO_DIVORCIO_DATA_SEPARACAO);
		}
		if(!Validador.isDataValida(giaITCDSeparacaoDivorcioVo.getDataCasamento()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_DATA_CASAMENTO_NAO_INFORMADA);
		}
	   Date dataCasamento = giaITCDSeparacaoDivorcioVo.getDataParaCompararInicioDia(giaITCDSeparacaoDivorcioVo.getDataCasamento());
	   Date dataSeparacao = giaITCDSeparacaoDivorcioVo.getDataParaCompararInicioDia(giaITCDSeparacaoDivorcioVo.getDataSeparacao());
		Date dataAtual = giaITCDSeparacaoDivorcioVo.getDataParaCompararInicioDia(new Date());
		
		if(dataSeparacao.before(dataCasamento))
		{
			throw new ParametroObrigatorioException(MensagemErro.DATA_SEPARACAO_MENOR_DATA_CASAMENTO);
		}
		else if(dataSeparacao.equals(dataCasamento))
		{
			throw new ParametroObrigatorioException(MensagemErro.DATA_SEPARACAO_IGUAL_DATA_CASAMENTO);
		}
		if (dataSeparacao.after(dataAtual))
		{
			throw new ParametroObrigatorioException(MensagemErro.GIA_ITCD_SEPARACAO_VALIDAR_DATA_SEPARACAO_MAIOR_DATA_ATUAL);
		}		
		else if(dataSeparacao.equals(dataAtual))
		{
			throw new ParametroObrigatorioException(MensagemErro.DATA_SEPARACAO_IGUAL_DATA_ATUAL);
		}
	}

	/**
	 * Valida se o responsavel existe
	 * @param giaITCDSeparacaoDivorcioVo
	 * @throws ParametroObrigatorioException
	 * @throws IntegracaoException
	 * @throws ObjetoObrigatorioException
	 * @implemented by Lucas Nascimento
	 */
	private static void validaResponsavel(final GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo) throws ParametroObrigatorioException, 
			  IntegracaoException, ObjetoObrigatorioException
	{
		ContribuinteIntegracaoVo declarante = new ContribuinteIntegracaoVo();
		declarante.setNumrDocumento(giaITCDSeparacaoDivorcioVo.getResponsavelVo().getNumrDocumento());
		declarante = new ContribuinteIntegracaoVo(declarante);
		CadastroBe cadastroBe = null;
		try
		{
			cadastroBe = new CadastroBe();
			declarante = cadastroBe.obterContribuintePorCpf(declarante, true);
		}
		catch (SQLException e)
		{
			throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_CADASTRO, e);
		}
		finally
		{
			if(cadastroBe != null)
			{
			   cadastroBe.close();
			   cadastroBe = null;
			}
		}
		if (!declarante.isExiste() || 
				 !Validador.isStringValida(giaITCDSeparacaoDivorcioVo.getResponsavelVo().getNumrDocumento()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_SEPARACAO_VALIDA_CPF);
		}
	}

	/**
	 * Método para validar as regras sobre a possibilidade de inserir ou alterar um GIA ITCD Separação.
	 * @param giaITCDSeparacaoDivorcioVo
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @throws IntegracaoException
	 * @throws SQLException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @implemented by Daniel Balieiro
	 * @implemented by Thiago de Castilho Pacheco
	 * @implemented by Lucas Nascimento
	 */
	public void validarIncluirAlterarGIAITCDSeparacaoDivorcio(final GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException, IntegracaoException, ConsultaException, ConexaoException, 
			  RegistroNaoPodeSerUtilizadoException
	{
		validarAbaDadosGerais(giaITCDSeparacaoDivorcioVo);
		validarAlterarGIAITCDSeparacaoDivorcio(giaITCDSeparacaoDivorcioVo);
	}

 
	private double doGerarValorParaCalculo(GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo) throws ObjetoObrigatorioException
	{
		double valorArbitrado = 0;
	    double valorConcordado = 0;
		double valorCalculo = 0;
		double valorPercentual = 0;
	    int quantidadeBemConcordado = 0;
	    if (Validador.isObjetoValido(giaITCDSeparacaoDivorcioVo.getBemTributavelVo()) && Validador.isCollectionValida(giaITCDSeparacaoDivorcioVo.getBemTributavelVo().getCollVO()))
	    {
		    //TODO versão 1.3
		    if((giaITCDSeparacaoDivorcioVo.getNumeroVersaoGIAITCD().is(DomnVersaoGIAITCD.VERSAO_1_3)) || (giaITCDSeparacaoDivorcioVo.getNumeroVersaoGIAITCD().is(DomnVersaoGIAITCD.VERSAO_1_4)))
		    {
		        for (Iterator iteBem = giaITCDSeparacaoDivorcioVo.getBemTributavelVo().getCollVO().iterator(); iteBem.hasNext(); )
		        {
		            BemTributavelVo bem = (BemTributavelVo) iteBem.next();
		            if (Validador.isObjetoValido(bem))
		            {
		                if(bem.getBemParticular().is(DomnSimNao.NAO) && bem.getIsencaoPrevista().is(DomnSimNao.NAO))
		                {
		                        valorArbitrado += BemTributavelBe.getValorBemTributavel(bem);
		                        if (bem.getConcordaComValorArbitrado().is(DomnSimNao.SIM))
		                        {
		                            ++quantidadeBemConcordado;
		                            valorConcordado += BemTributavelBe.getValorBemTributavel(bem);
		                        }
		                        else
		                        {
		                           valorConcordado += BemTributavelBe.getValorBemInformado(bem);
		                        }
		                    
		                }
		            }
		        }
		    
		         valorPercentual = (valorConcordado/valorArbitrado)*100;
		        
		        valorPercentual = Validador.isNumericoValido(valorPercentual)?NumeroUtil.arredondaNumero(valorPercentual): valorPercentual;
		        if (valorPercentual >= giaITCDSeparacaoDivorcioVo.getPorcentagemAconsiderar() || quantidadeBemConcordado == giaITCDSeparacaoDivorcioVo.getBemTributavelVo().getCollVO().size())
		        {
		            valorCalculo = valorConcordado;
		        }
		        else
		        {
		            valorCalculo = valorArbitrado;
		        }
		    }
		    else
		    {
		        for (Iterator iteBem = giaITCDSeparacaoDivorcioVo.getBemTributavelVo().getCollVO().iterator(); iteBem.hasNext(); )
		        {
		            BemTributavelVo bem = (BemTributavelVo) iteBem.next();
		            if (Validador.isObjetoValido(bem))
		            {
		                if(bem.getBemParticular().is(DomnSimNao.NAO) && bem.getIsencaoPrevista().is(DomnSimNao.NAO))
		                {
		                    valorCalculo += BemTributavelBe.getValorBemTributavel(bem);
		                }
		            }
		        }
		    }
		}
		
		return valorCalculo;
	}	

	/**
	 * Método para gerar o demonstrativo de cálculo.
	 * @param giaITCDSeparacaoDivorcioVo
	 * @throws ObjetoObrigatorioException
	 * @implemented by Daniel Balieiro
	 * @implemented by Lucas Nascimento
	 */
	public void gerarDemonstrativoCalculo(final GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(giaITCDSeparacaoDivorcioVo);
		if(giaITCDSeparacaoDivorcioVo.getRegimeCasamento().is(DomnRegimeCasamento.SEPARACAO_TOTAL_DE_BENS))
		{
			giaITCDSeparacaoDivorcioVo.setValorCalculoDemonstrativo(0);
		   giaITCDSeparacaoDivorcioVo.setValorAliquota(0);
		   giaITCDSeparacaoDivorcioVo.setValorIncidencia(0);
		   giaITCDSeparacaoDivorcioVo.setValorITCD(0);
		   giaITCDSeparacaoDivorcioVo.setValorRecolhimento(0);
			giaITCDSeparacaoDivorcioVo.setValorBaseCalculoTributavel(0);
			giaITCDSeparacaoDivorcioVo.setConjugeExcesso(new ConjugeBemTributavelVo());
		}
		else
		{		   
		   giaITCDSeparacaoDivorcioVo.setValorCalculoDemonstrativo(doGerarValorParaCalculo(giaITCDSeparacaoDivorcioVo));
		   
		   double media = giaITCDSeparacaoDivorcioVo.getValorCalculoDemonstrativo() / 2;
		   double incidenciaConjuge1 = giaITCDSeparacaoDivorcioVo.getValorTotalConjuge1Valido() - media;
		   double incidenciaConjuge2 =giaITCDSeparacaoDivorcioVo.getValorTotalConjuge2Valido() - media;
		   double incidencia = 0;
		   
		   if(incidenciaConjuge1 >= CalculoITCD.VALOR_MINIMO_DIFERENCA_ENTRE_CONJUGES.doubleValue() || incidenciaConjuge2 >= CalculoITCD.VALOR_MINIMO_DIFERENCA_ENTRE_CONJUGES.doubleValue())
		   {
				if(incidenciaConjuge1 > incidenciaConjuge2)
				{
			      incidencia = incidenciaConjuge1;
			      giaITCDSeparacaoDivorcioVo.setConjugeExcesso(giaITCDSeparacaoDivorcioVo.getConjuge1());
				   giaITCDSeparacaoDivorcioVo.setValorTotalConjugeExcesso(giaITCDSeparacaoDivorcioVo.getValorTotalConjuge1());
				}
				else
				{
				   incidencia = incidenciaConjuge2;
				   giaITCDSeparacaoDivorcioVo.setConjugeExcesso(giaITCDSeparacaoDivorcioVo.getConjuge2());
				   giaITCDSeparacaoDivorcioVo.setValorTotalConjugeExcesso(giaITCDSeparacaoDivorcioVo.getValorTotalConjuge2());
				}
		   }
		   AliquotaVo aliquotaVo = new AliquotaVo();
         
         if(giaITCDSeparacaoDivorcioVo.getParametroLegislacaoVo().isLegislacaoCascata())
         {
            calculoEmCascata(giaITCDSeparacaoDivorcioVo , incidencia);
            giaITCDSeparacaoDivorcioVo.setValorITCD(giaITCDSeparacaoDivorcioVo.getValorRecolhimento());
         }
         else
         {
            aliquotaVo = encontraAliquota(giaITCDSeparacaoDivorcioVo.getParametroLegislacaoVo().getAliquotaVo().getCollVO(), incidencia, giaITCDSeparacaoDivorcioVo.getValorUPF());
            giaITCDSeparacaoDivorcioVo.setValorAliquota(aliquotaVo.getPercentualLegislacaoAliquota());
            giaITCDSeparacaoDivorcioVo.setValorIncidencia(incidencia);
            giaITCDSeparacaoDivorcioVo.setValorBaseCalculoTributavel(incidencia);
            giaITCDSeparacaoDivorcioVo.setValorRecolhimento(incidencia * (giaITCDSeparacaoDivorcioVo.getValorAliquota() / 100));
            giaITCDSeparacaoDivorcioVo.setValorITCD(giaITCDSeparacaoDivorcioVo.getValorRecolhimento());
         }
         
		   verificarIsencaoImpostoMenorQueUmaUpfPorBeneficiario(giaITCDSeparacaoDivorcioVo);
		}
	}
   
   public void verificarIsencaoImpostoMenorQueUmaUpfPorBeneficiario(final GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo) {

        ConfiguracaoGerencialParametrosVo configuracaoConsultaDataInicial = new ConfiguracaoGerencialParametrosVo();
        ConfiguracaoGerencialParametrosVo configuracaoConsultaDataFinal = new ConfiguracaoGerencialParametrosVo();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        configuracaoConsultaDataInicial.setCodigo(63);
        configuracaoConsultaDataFinal.setCodigo(64);

        configuracaoConsultaDataInicial = new ConfiguracaoGerencialParametrosVo(configuracaoConsultaDataInicial);
        configuracaoConsultaDataFinal = new ConfiguracaoGerencialParametrosVo(configuracaoConsultaDataFinal);
        ConfiguracaoGerencialParametrosBe configuracaoGerencialParametrosBe = null;

        Date dateInicial = null;
        Date dateFinal = null;
        try {
          configuracaoGerencialParametrosBe = new ConfiguracaoGerencialParametrosBe(conn);
          configuracaoGerencialParametrosBe.consultarConfiguracaoGerencialParametros(configuracaoConsultaDataInicial);
          configuracaoGerencialParametrosBe.consultarConfiguracaoGerencialParametros(configuracaoConsultaDataFinal);

          if (configuracaoConsultaDataInicial != null) {
            dateInicial = df.parse(configuracaoConsultaDataInicial.getValorItem());
          }

         if (configuracaoConsultaDataFinal.getValorItem().equals("")) {
         
            // Como não está decidido a Data Final para isenções é criado uma Data de 1 dia a mais para controle da verificação 
            Calendar cal = Calendar.getInstance();  
            cal.add(Calendar.DAY_OF_YEAR, 1);
            String dataFormatada = df.format(cal.getTime());         
            dateFinal = df.parse(dataFormatada);
            
          } else {
            dateFinal = df.parse(configuracaoConsultaDataFinal.getValorItem());
          }

        } catch (Exception e) {
          System.out.println("Erro ao buscar Parametros Gerenciais" + e.getStackTrace());
        }

        if (giaITCDSeparacaoDivorcioVo.getDataSeparacao().after(dateInicial) && giaITCDSeparacaoDivorcioVo.getDataSeparacao().before(dateFinal)) {
        
         //Não preciso olhar os dois conjugue somente o que estiver com mais bem atribuido por que só um paga o imposto que é o que fica com a maior parte
          if ((giaITCDSeparacaoDivorcioVo.getValorITCD() < giaITCDSeparacaoDivorcioVo.getValorUPF()) && (giaITCDSeparacaoDivorcioVo.getValorITCD() > 0)) {

            giaITCDSeparacaoDivorcioVo.getConjuge1().setValorConjuge(0);
            giaITCDSeparacaoDivorcioVo.getConjuge2().setValorConjuge(0);
            giaITCDSeparacaoDivorcioVo.setValorRecolhimento(0);
            giaITCDSeparacaoDivorcioVo.getConjugeExcesso().setInfoDispensaRecolhimento(2);
            giaITCDSeparacaoDivorcioVo.getConjugeExcesso().setInfoIsencao(1);

            for (Iterator iteAliquotas = giaITCDSeparacaoDivorcioVo.getGiaItcdSeparacaoDivorcioAliquotaVo().getCollVO().iterator(); iteAliquotas.hasNext();) {
              GIAITCDSeparacaoDivorcioAliquotaVo aliquotaSeparacaoDivorcio = (GIAITCDSeparacaoDivorcioAliquotaVo) iteAliquotas.next();
              aliquotaSeparacaoDivorcio.setValorRecolher(0);
            }
            
            giaITCDSeparacaoDivorcioVo.setValorRecolhimento(0);
            giaITCDSeparacaoDivorcioVo.setGiaIsentaUpf(true);
            
          }else if(giaITCDSeparacaoDivorcioVo.getValorITCD() == 0){
             giaITCDSeparacaoDivorcioVo.getConjugeExcesso().setInfoDispensaRecolhimento(1);
             giaITCDSeparacaoDivorcioVo.getConjugeExcesso().setInfoIsencao(2);
          }
          else{
             giaITCDSeparacaoDivorcioVo.getConjugeExcesso().setInfoDispensaRecolhimento(1);
             giaITCDSeparacaoDivorcioVo.getConjugeExcesso().setInfoIsencao(1);
             giaITCDSeparacaoDivorcioVo.setGiaIsentaUpf(false);
          }
        }

      }
	
   
   /**
    * Efetua o cálculo da GIA em cascasta
    * 
    * 
    */
   private void calculoEmCascata(final GIAITCDSeparacaoDivorcioVo giaItcdSeparacaoDivorcioVo, double valorIncidencia)
   {
      AliquotaVo aliquotasInterVivos = new AliquotaVo();
      double valorUPF = giaItcdSeparacaoDivorcioVo.getValorUPF();
      double valorRecebido = valorIncidencia;
      double valorRecolherConjuge = 0;
      
      for(Iterator ite = giaItcdSeparacaoDivorcioVo.getParametroLegislacaoVo().getAliquotaVo().getCollVO().iterator(); ite.hasNext();)
      {
         AliquotaVo atual = (AliquotaVo) ite.next();
         if (atual.getTipoFatoGerador().is(DomnTipoGIA.INTER_VIVOS))
         {
            aliquotasInterVivos.getCollVO().add(atual);
         }
      }
      
      GIAITCDSeparacaoDivorcioAliquotaVo giaItcdSeparacaoDivorcioAliquotaVo = new GIAITCDSeparacaoDivorcioAliquotaVo();
      
      for (Iterator it = aliquotasInterVivos.getCollVO().iterator(); it.hasNext(); )
      {
         AliquotaVo atual = (AliquotaVo) it.next();
         GIAITCDSeparacaoDivorcioAliquotaVo giaItcdSeparacaoAliquotaVo = new GIAITCDSeparacaoDivorcioAliquotaVo();
         giaItcdSeparacaoAliquotaVo.setCodigoAliquota(atual.getCodigo());
         giaItcdSeparacaoAliquotaVo.setPercentualAliquota(atual.getPercentualLegislacaoAliquota());
         double valorUPFFinal = (atual.getQuantidadeUPFFinal()) * valorUPF;
         double valorUPFInicial = (atual.getQuantidadeUPFInicial()-1) * valorUPF;
         double valorFaixa = 0;
         
         if (valorUPFFinal != 0)
         {
            valorFaixa = (atual.getQuantidadeUPFFinal() - atual.getQuantidadeUPFInicial() + 1) * valorUPF;
         }
         else
         {
            valorFaixa = valorRecebido - valorUPFInicial;
         }
         if (valorRecebido > valorUPFInicial)
         {
            if (valorRecebido > valorUPFFinal)
            {
               giaItcdSeparacaoAliquotaVo.setValorBaseCalculo(valorFaixa);
               giaItcdSeparacaoAliquotaVo.setValorRecolher(valorFaixa * atual.getPercentualLegislacaoAliquota() / 100);
            }
            else
            {
               giaItcdSeparacaoAliquotaVo.setValorBaseCalculo(valorRecebido - valorUPFInicial);
               giaItcdSeparacaoAliquotaVo.setValorRecolher(giaItcdSeparacaoAliquotaVo.getValorBaseCalculo() * atual.getPercentualLegislacaoAliquota() / 100);
            }
         }
         else
         {
            break;
         }
         giaItcdSeparacaoAliquotaVo.setGiaItcdSeparacaoDivorcioVo(giaItcdSeparacaoDivorcioVo);
         giaItcdSeparacaoDivorcioAliquotaVo.getCollVO().add(giaItcdSeparacaoAliquotaVo);
         valorRecolherConjuge += giaItcdSeparacaoAliquotaVo.getValorRecolher();
      }
      giaItcdSeparacaoDivorcioVo.setGiaItcdSeparacaoDivorcioAliquotaVo(giaItcdSeparacaoDivorcioAliquotaVo);
      giaItcdSeparacaoDivorcioVo.setValorRecolhimento(valorRecolherConjuge);
      giaItcdSeparacaoDivorcioVo.setValorBaseCalculoTributavel(valorIncidencia);
   }

   
	/**
	 * Este método calcula a aliquota em modo normal(sem cascata).
	 *
	 * @param aliquotas        listagem de aliquotas aplicáveis à legislacao em questão
	 * @param valorRecebido    valor recebido por cada beneficiário
	 * @param valorUPF         valor corrente da UPF
	 * @return
	 */
	public AliquotaVo encontraAliquota(Collection aliquotas, double valorRecebido, double valorUPF)
	{
		if(Validador.isNumericoValido(valorRecebido))
		{
			for (Iterator it = aliquotas.iterator(); it.hasNext(); )
			{
				AliquotaVo atual = (AliquotaVo) it.next();
				double upfInicio = (atual.getQuantidadeUPFInicial()-1) * valorUPF;
				double upfFim = atual.getQuantidadeUPFFinal() * valorUPF;
				if(atual.getTipoFatoGerador().is(DomnTipoGIA.INTER_VIVOS))
				{
					if(valorRecebido <= upfFim && upfInicio != 0)
					{
						return atual;     
					}
					else if((valorRecebido > upfInicio) && (valorRecebido <= upfFim))
					{
						return atual;
					}
					else if(valorRecebido > upfInicio && upfFim == 0)
					{
						return atual;
					}
				}
			}
		}
		return new AliquotaVo();
	}

	/**
	 * @param giaITCDSeparacaoDivorcioVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void incluir(final GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(giaITCDSeparacaoDivorcioVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).insert(giaITCDSeparacaoDivorcioVo);
	}

	/**
	 * @param giaITCDSeparacaoDivorcioVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void alterar(final GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(giaITCDSeparacaoDivorcioVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).update(giaITCDSeparacaoDivorcioVo, GIAITCDSeparacaoDivorcioVo.class);
	}	
	

	/**
	 * Método que insere uma GIA ITCD Separação Divórcio no banco de dados.
	 * @param giaITCDSeparacaoDivorcioVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws ParametroObrigatorioException
	 * @throws IntegracaoException
	 * @throws RegistroNaoPodeSerUtilizadoException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @implemented by Daniel Balieiro
	 * @implemented by Thiago de Castilho Pacheco
	 * @implemented by Lucas Nascimento
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void incluirGIAITCDSeparacaoDivorcio(final GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, ParametroObrigatorioException, IntegracaoException, 
			  RegistroNaoPodeSerUtilizadoException, LogException, PersistenciaException, AnotacaoException, 
             RegistroExistenteException
   {
		Validador.validaObjeto(giaITCDSeparacaoDivorcioVo);
		try
		{
			try
			{
				synchronized (GIAITCDSeparacaoDivorcioVo.class)
				{
					validarIncluirAlterarGIAITCDSeparacaoDivorcio(giaITCDSeparacaoDivorcioVo);
					incluir(giaITCDSeparacaoDivorcioVo);
					ConjugeBemTributavelBe conjugeBemTributavelBe = new ConjugeBemTributavelBe(conn);
					giaITCDSeparacaoDivorcioVo.getConjuge1().setGiaITCDVo(giaITCDSeparacaoDivorcioVo);
				   giaITCDSeparacaoDivorcioVo.getConjuge1().setLogSefazVo(giaITCDSeparacaoDivorcioVo.getLogSefazVo());
					conjugeBemTributavelBe.incluirConjugeBemTributavelIncluirGIASeparacaoDivorcio(giaITCDSeparacaoDivorcioVo.getConjuge1(), new DomnTipoConjuge(DomnTipoConjuge.CONJUGE1));
					giaITCDSeparacaoDivorcioVo.getConjuge2().setGiaITCDVo(giaITCDSeparacaoDivorcioVo);				
				   giaITCDSeparacaoDivorcioVo.getConjuge2().setLogSefazVo(giaITCDSeparacaoDivorcioVo.getLogSefazVo());					
					conjugeBemTributavelBe.incluirConjugeBemTributavelIncluirGIASeparacaoDivorcio(giaITCDSeparacaoDivorcioVo.getConjuge2(), new DomnTipoConjuge(DomnTipoConjuge.CONJUGE2));					
               
               for(GIAITCDSeparacaoDivorcioAliquotaVo aliquota : giaITCDSeparacaoDivorcioVo.getGiaItcdSeparacaoDivorcioAliquotaVo().getListVo())
               {
                  if(Validador.isNumericoValido(aliquota.getCodigoAliquota()))
                  {
                     aliquota.setLogSefazVo(giaITCDSeparacaoDivorcioVo.getLogSefazVo());
                     new GIAITCDSeparacaDivorcioAliquotaBe(conn).incluirGiaItcdSeparacaoAliquota(aliquota);
                  }
               }				
               
               commit();
					giaITCDSeparacaoDivorcioVo.setMensagem(MensagemSucesso.INCLUIR);
				}
			}		   
			catch (ConsultaException e)
			{
				conn.rollback();
				throw e;
			}
			catch (ParametroObrigatorioException e)
			{
				conn.rollback();
				throw e;
			}
			catch (IntegracaoException e)
			{
				conn.rollback();
				throw e;
			}
			catch (RegistroNaoPodeSerUtilizadoException e)
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
	 * Método que altera uma GIA ITCD Separação Divórcio no banco de dados.
	 * @param giaITCDSeparacaoDivorcioVo
	 * @throws ObjetoObrigatorioException
	 * @throws RegistroNaoPodeSerUtilizadoException
	 * @throws ParametroObrigatorioException
	 * @throws IntegracaoException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @throws ConexaoException
	 * @implemented by Daniel Balieiro
	 * @implemented by Thiago de Castilho Pacheco
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void alterarGIAITCDSeparacaoDivorcio(final GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo) throws ObjetoObrigatorioException, 
			  RegistroNaoPodeSerUtilizadoException, ParametroObrigatorioException, IntegracaoException, LogException, 
			  PersistenciaException, AnotacaoException, ConexaoException, ConsultaException, IOException
   {
		Validador.validaObjeto(giaITCDSeparacaoDivorcioVo);
		try
		{
			try
			{
				synchronized (GIAITCDSeparacaoDivorcioVo.class)
				{
					
					validarAlterarGIAITCDSeparacaoDivorcio(giaITCDSeparacaoDivorcioVo);
					alterar(giaITCDSeparacaoDivorcioVo);
					ConjugeBemTributavelBe conjugeBemTributavelBe = new ConjugeBemTributavelBe(conn);
					giaITCDSeparacaoDivorcioVo.getConjuge1().setGiaITCDVo(giaITCDSeparacaoDivorcioVo);
				   giaITCDSeparacaoDivorcioVo.getConjuge1().setLogSefazVo(giaITCDSeparacaoDivorcioVo.getLogSefazVo());
					conjugeBemTributavelBe.alterarConjugeBemTributavelAlterarGIASeparacaoDivorcio(giaITCDSeparacaoDivorcioVo.getConjuge1(), new DomnTipoConjuge(DomnTipoConjuge.CONJUGE1));
				   giaITCDSeparacaoDivorcioVo.getConjuge2().setGiaITCDVo(giaITCDSeparacaoDivorcioVo);
				   giaITCDSeparacaoDivorcioVo.getConjuge2().setLogSefazVo(giaITCDSeparacaoDivorcioVo.getLogSefazVo());					
					conjugeBemTributavelBe.alterarConjugeBemTributavelAlterarGIASeparacaoDivorcio(giaITCDSeparacaoDivorcioVo.getConjuge2(), new DomnTipoConjuge(DomnTipoConjuge.CONJUGE2));
				   
               //new GIAITCDSeparacaDivorcioAliquotaBe(conn).rotinaAlterarAliquota(giaITCDSeparacaoDivorcioVo);
               
               commit();
					giaITCDSeparacaoDivorcioVo.setMensagem(MensagemSucesso.ALTERAR);
				}
			}
			catch (RegistroNaoPodeSerUtilizadoException e)
			{
				conn.rollback();
				throw e;
			}
			catch (ParametroObrigatorioException e)
			{
				conn.rollback();
				throw e;
			}
			catch (IntegracaoException e)
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
			catch(ConsultaException e)
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
	 * Método que consulta uma GIA ITCD Separação Divïórcio no banco de dados.
	 * @param giaITCDSeparacaoDivorcioVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws ParametroObrigatorioException
	 * @throws IntegracaoException
	 * @return
	 * @implemented by Daniel Balieiro
	 * @implemented by Thiago de Castilho Pacheco
	 * @implemented by Lucas Nascimento
	 */
	public GIAITCDSeparacaoDivorcioVo consultarGIAITCDSeparacaoDivorcio(final GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, ParametroObrigatorioException, IntegracaoException, SQLException, 
             IOException
   {
		Validador.validaObjeto(giaITCDSeparacaoDivorcioVo);
		(new GIAITCDSeparacaoDivorcioQDao(conn)).findGIAITCDSeparacaoDivorcio(giaITCDSeparacaoDivorcioVo);
		if (giaITCDSeparacaoDivorcioVo.isConsultaCompleta())
		{
			if (Validador.isNumericoValido(giaITCDSeparacaoDivorcioVo.getConjuge1().getPessoaConjuge().getNumrContribuinte()))
			{							   
				giaITCDSeparacaoDivorcioVo.getConjuge1().setTipoConjuge(new DomnTipoConjuge(DomnTipoConjuge.CONJUGE1));
				giaITCDSeparacaoDivorcioVo.getConjuge1().setGiaITCDVo(giaITCDSeparacaoDivorcioVo);
				giaITCDSeparacaoDivorcioVo.setConjuge1(new ConjugeBemTributavelVo(giaITCDSeparacaoDivorcioVo.getConjuge1()));
				giaITCDSeparacaoDivorcioVo.getConjuge1().setConsultaCompleta(true);
				(new ConjugeBemTributavelBe(conn)).listarConjugeBemTributavel(giaITCDSeparacaoDivorcioVo.getConjuge1());				
				
				// força a atribuição manualmente para garantir os dados do Bem Tributável e não entrar em Loop Infinito 
				for(Iterator iteCon = giaITCDSeparacaoDivorcioVo.getConjuge1().getCollVO().iterator(); iteCon.hasNext();)
				{
					ConjugeBemTributavelVo conjuge = (ConjugeBemTributavelVo) iteCon.next();
					
					if(giaITCDSeparacaoDivorcioVo.getBemTributavelVo().getCollVO().contains(conjuge.getBemTributavelVo())) 
					{
						List listaBens = (List) giaITCDSeparacaoDivorcioVo.getBemTributavelVo().getCollVO();
						conjuge.setBemTributavelVo((BemTributavelVo) listaBens.get(listaBens.indexOf(conjuge.getBemTributavelVo())));
					}
				}
			   giaITCDSeparacaoDivorcioVo.getConjuge1().setPessoaConjuge(giaITCDSeparacaoDivorcioVo.getResponsavelVo());				
			}
			if (Validador.isNumericoValido(giaITCDSeparacaoDivorcioVo.getConjuge2().getPessoaConjuge().getNumrContribuinte()))
			{
				ContribuinteIntegracaoVo conjuge2Consulta = new ContribuinteIntegracaoVo(new ContribuinteIntegracaoVo(giaITCDSeparacaoDivorcioVo.getConjuge2().getPessoaConjuge().getNumrContribuinte()));
				giaITCDSeparacaoDivorcioVo.getConjuge2().setTipoConjuge(new DomnTipoConjuge(DomnTipoConjuge.CONJUGE2));
				giaITCDSeparacaoDivorcioVo.getConjuge2().setGiaITCDVo(giaITCDSeparacaoDivorcioVo);
				giaITCDSeparacaoDivorcioVo.setConjuge2(new ConjugeBemTributavelVo(giaITCDSeparacaoDivorcioVo.getConjuge2()));
				giaITCDSeparacaoDivorcioVo.getConjuge2().setConsultaCompleta(true);
				(new ConjugeBemTributavelBe(conn)).listarConjugeBemTributavel(giaITCDSeparacaoDivorcioVo.getConjuge2());
				
				// força a atribuição manualmente para garantir os dados do Bem Tributável e não entrar em Loop Infinito 
				for(Iterator iteCon = giaITCDSeparacaoDivorcioVo.getConjuge2().getCollVO().iterator(); iteCon.hasNext();)
				{
					ConjugeBemTributavelVo conjuge = (ConjugeBemTributavelVo) iteCon.next();
					
					if(giaITCDSeparacaoDivorcioVo.getBemTributavelVo().getCollVO().contains(conjuge.getBemTributavelVo())) 
					{
						List listaBens = (List) giaITCDSeparacaoDivorcioVo.getBemTributavelVo().getCollVO();
						conjuge.setBemTributavelVo((BemTributavelVo) listaBens.get(listaBens.indexOf(conjuge.getBemTributavelVo())));
					}
				}
			   giaITCDSeparacaoDivorcioVo.getConjuge2().setPessoaConjuge(new CadastroBe(conn).obterContribuinte(conjuge2Consulta));
			}         
		}
		return giaITCDSeparacaoDivorcioVo;
	}

	/**
	 * Método que lista uma GIA ITCD Separação Divórcio no banco de dados.
	 * @param giaITCDSeparacaoDivorcioVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @return
	 * @implemented by Daniel Balieiro
	 * @implemented by Thiago de Castilho Pacheco
	 */
	public GIAITCDSeparacaoDivorcioVo listarGIAITCDSeparacaoDivorcio(final GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo) throws ObjetoObrigatorioException, 
			  ConsultaException
	{
		Validador.validaObjeto(giaITCDSeparacaoDivorcioVo);
		(new GIAITCDSeparacaoDivorcioQDao(conn)).listGIAITCDSeparacaoDivorcio(giaITCDSeparacaoDivorcioVo);
		return giaITCDSeparacaoDivorcioVo;
	}

	/**
	 * Remove da Collection passar a que contiver os bem com descrição e tipo de classificação de bem iguais.
	 * @param conjugeBemTributavel
	 * @param bem
	 * @implemented by Lucas Nascimento
	 */
	public static void removeConjugeBem(Collection conjugeBemTributavel, BemTributavelVo bem)
	{
		int i = 0;
		for (Iterator iteConjugeBem = conjugeBemTributavel.iterator(); iteConjugeBem.hasNext(); )
		{
			ConjugeBemTributavelVo conjugeAtual = (ConjugeBemTributavelVo) iteConjugeBem.next();
			if (conjugeAtual.getBemTributavelVo().getDescricaoBemTributavel().equals(bem.getDescricaoBemTributavel()) && 
						conjugeAtual.getBemTributavelVo().getBemVo().getClassificacaoTipoBem().is((bem.getBemVo().getClassificacaoTipoBem().getValorCorrente())) && 
						conjugeAtual.getBemTributavelVo().getBemVo().getDescricaoTipoBem().equals(bem.getBemVo().getDescricaoTipoBem()))
			{
				((ArrayList) conjugeBemTributavel).remove(i);
				break;
			}
			i++;
		}
	}

	/**
	 * Método responsável por validar os dados do declarante, e caso tudo esteja correto atribuir à GIA.
	 * @param declaranteVo
	 * @param giaITCDSeparacaoDivorcioVo
	 * @throws ObjetoObrigatorioException
	 * @throws RegistroNaoPodeSerUtilizadoException
	 * @throws ParametroObrigatorioException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public void atribuirDeclarante(final ContribuinteIntegracaoVo declaranteVo, final GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo) throws ObjetoObrigatorioException, 
			  RegistroNaoPodeSerUtilizadoException, DadoNecessarioInexistenteException, ParametroObrigatorioException
	{
		Validador.validaObjeto(declaranteVo);
		Validador.validaObjeto(giaITCDSeparacaoDivorcioVo);
		validaCPFDeclarante(declaranteVo, giaITCDSeparacaoDivorcioVo);
		validaCEPContribuinte(declaranteVo);
		giaITCDSeparacaoDivorcioVo.setResponsavelVo(declaranteVo);
		giaITCDSeparacaoDivorcioVo.getConjuge1().setPessoaConjuge(declaranteVo);
	}

	/**
	 * Método responsável por validar os dados do Cônjuge 2, e caso tudo esteja correto atribuir à GIA.
	 * @param conjuge2Vo
	 * @param giaITCDSeparacaoDivorcioVo
	 * @throws ObjetoObrigatorioException
	 * @throws DadoNecessarioInexistenteException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public void atribuirConjuge2(final ContribuinteIntegracaoVo conjuge2Vo, final GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo) throws ObjetoObrigatorioException, 
			  DadoNecessarioInexistenteException, ParametroObrigatorioException, RegistroNaoPodeSerUtilizadoException
	{
		Validador.validaObjeto(conjuge2Vo);
		Validador.validaObjeto(giaITCDSeparacaoDivorcioVo);
		validaCPFConjuge2(conjuge2Vo, giaITCDSeparacaoDivorcioVo);
		validaCEPContribuinte(conjuge2Vo);
	   giaITCDSeparacaoDivorcioVo.getConjuge2().setPessoaConjuge(conjuge2Vo);		
	}
	
	private static void validaCPFConjuge2(final ContribuinteIntegracaoVo conjuge2Vo, final GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo ) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException, RegistroNaoPodeSerUtilizadoException
	{
		Validador.validaObjeto(conjuge2Vo);
		Validador.validaObjeto(giaITCDSeparacaoDivorcioVo);
		
		if(!Validador.isNumericoValido(conjuge2Vo.getNumrContribuinte()))
		{
			throw new ParametroObrigatorioException(MensagemErro.DADOS_CONJUGE2_DEVE_SER_INFORMADO);
		}
		if(Validador.isNumericoValido(giaITCDSeparacaoDivorcioVo.getProcuradorVo().getNumrContribuinte()))
		{
			if((conjuge2Vo.getNumrDocumento().equals(giaITCDSeparacaoDivorcioVo.getProcuradorVo().getNumrDocumento())))
			{
				throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_ITCD_SEPARACAO_DIVORCIO_COMPARA_CPF_CONJUGE_2_PROCURADOR);
			}
		}
		if(Validador.isNumericoValido(giaITCDSeparacaoDivorcioVo.getConjuge1().getPessoaConjuge().getNumrContribuinte()))
		{
		   if((conjuge2Vo.getNumrDocumento().equals(giaITCDSeparacaoDivorcioVo.getConjuge1().getPessoaConjuge().getNumrDocumento())))
		   {
		      throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_ITCD_SEPARACAO_DIVORCIO_COMPARA_CPF_CONJUGE_2_CONJUGE_1);            
		   }			
		}
		
	}


	/**
	 * Método para validar se o CPF do Declarante não é igual ao CPF do procurador / cônjuge 2.
	 * @param declaranteVo
	 * @param giaITCDSeparacaoDivorcioVo
	 * @throws ObjetoObrigatorioException
	 * @throws RegistroNaoPodeSerUtilizadoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private static void validaCPFDeclarante(final ContribuinteIntegracaoVo declaranteVo, final GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo) throws ObjetoObrigatorioException, 
			  RegistroNaoPodeSerUtilizadoException, ParametroObrigatorioException
	{
	   Validador.validaObjeto(giaITCDSeparacaoDivorcioVo);
	   Validador.validaObjeto(declaranteVo);
		if(!Validador.isNumericoValido(declaranteVo.getNumrContribuinte()))
		{
			throw new ParametroObrigatorioException(MensagemErro.DADOS_DECLARANTE_DEVE_SER_INFORMADO);
		}
		if(Validador.isNumericoValido(giaITCDSeparacaoDivorcioVo.getConjuge2().getPessoaConjuge().getNumrContribuinte()))			
		{
		   if ((declaranteVo.getNumrDocumento().equals(giaITCDSeparacaoDivorcioVo.getConjuge2().getPessoaConjuge().getNumrDocumento())))
		   {
		      throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_ITCD_SEPARACAO_DIVORCIO_COMPARA_CPF_DECLARANTE_CONJUGE2);
		   }			
		}
		if(Validador.isNumericoValido(giaITCDSeparacaoDivorcioVo.getProcuradorVo().getNumrContribuinte()))		
		{
		   if( (declaranteVo.getNumrDocumento().equals(giaITCDSeparacaoDivorcioVo.getProcuradorVo().getNumrDocumento())))
		   {
		      throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_ITCD_SEPARACAO_DIVORCIO_COMPARA_CPF_DECLARANTE_PROCURADOR);
		   }  			
		}
	}

	/**
	 * Método responsável por verificar se o contribuinte informado possui CEP.
	 * @param contribuinteIntegracaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws DadoNecessarioInexistenteException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void validaCEPContribuinte(final ContribuinteIntegracaoVo contribuinteIntegracaoVo) throws ObjetoObrigatorioException, 
			  DadoNecessarioInexistenteException
	{
		Validador.validaObjeto(contribuinteIntegracaoVo);
		if(!Validador.isNumericoValido(contribuinteIntegracaoVo.getEnderecoCEP()))
		{
			throw new DadoNecessarioInexistenteException(MensagemErro.DADOS_CADASTRAIS_DECLARANTE_DESATUALIZADO);
		}
	}

	/**
	 * Método responsável por atribuir o município para protocolar.
	 * @param giaITCDSeparacaoDivorcioVo
	 * @throws ObjetoObrigatorioException
	 * @throws IntegracaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public void atribuirMunicipioProtocolar(final GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo) throws ObjetoObrigatorioException, 
			  IntegracaoException
	{
		Validador.validaObjeto(giaITCDSeparacaoDivorcioVo);
	   if(Validador.isNumericoValido(giaITCDSeparacaoDivorcioVo.getProcuradorVo().getNumrContribuinte()))
	   {
	      try
	      {
	         giaITCDSeparacaoDivorcioVo.setMunicipioProtocolar((new CadastroBe(conn)).obterMunicipioPorCep(new CepIntegracaoVo(new Integer(giaITCDSeparacaoDivorcioVo.getProcuradorVo().getEnderecoCEP().intValue()))));                    
	      }
	      catch(IntegracaoException e)
	      {
	         giaITCDSeparacaoDivorcioVo.setMunicipioProtocolar((new CadastroBe(conn)).obterMunicipioPorCep(new CepIntegracaoVo(new Integer(giaITCDSeparacaoDivorcioVo.getResponsavelVo().getEnderecoCEP().intValue()))));                            
	      }
	   }
	   else
	   {
	      giaITCDSeparacaoDivorcioVo.setMunicipioProtocolar((new CadastroBe(conn)).obterMunicipioPorCep(new CepIntegracaoVo(new Integer(giaITCDSeparacaoDivorcioVo.getResponsavelVo().getEnderecoCEP().intValue()))));                
	   }		
	}

	/**
	 * Método responsável por verificar se o procurador informado é um contribuinte válido e caso afirmativo adicioná-lo à gia de separação.
	 * @param procuradorVo
	 * @param giaITCDSeparacaoDivorcioVo
	 * @throws ObjetoObrigatorioException
	 * @throws RegistroNaoPodeSerUtilizadoException
	 * @throws DadoNecessarioInexistenteException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public void atribuirProcurador(final ContribuinteIntegracaoVo procuradorVo, final GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo) throws ObjetoObrigatorioException, 
			  RegistroNaoPodeSerUtilizadoException, DadoNecessarioInexistenteException
	{
	   Validador.validaObjeto(procuradorVo);
	   Validador.validaObjeto(giaITCDSeparacaoDivorcioVo);
	   validaCPFProcurador(procuradorVo, giaITCDSeparacaoDivorcioVo);
	   if(Validador.isNumericoValido(procuradorVo.getNumrContribuinte()))
	   {
	      validaCEPContribuinte(procuradorVo);      
	   }     
	    giaITCDSeparacaoDivorcioVo.setProcuradorVo(procuradorVo);    	
	}

	/**
	 * Método responsável por verificar se o CPF do procurador informado é um CPF válido.
	 * @param procuradorVo
	 * @param giaITCDSeparacaoDivorcioVo
	 * @throws ObjetoObrigatorioException
	 * @throws RegistroNaoPodeSerUtilizadoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private static void validaCPFProcurador(final ContribuinteIntegracaoVo procuradorVo, final GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo) throws ObjetoObrigatorioException, 
			  RegistroNaoPodeSerUtilizadoException
	{
	   Validador.validaObjeto(procuradorVo);
	   Validador.validaObjeto(giaITCDSeparacaoDivorcioVo);
		if(Validador.isNumericoValido(giaITCDSeparacaoDivorcioVo.getResponsavelVo().getNumrContribuinte()) &&
				Validador.isNumericoValido(giaITCDSeparacaoDivorcioVo.getConjuge2().getPessoaConjuge().getNumrContribuinte()) &&
				Validador.isNumericoValido(procuradorVo.getNumrContribuinte()))
		{
		   if ((procuradorVo.getNumrDocumento().equals(giaITCDSeparacaoDivorcioVo.getResponsavelVo().getNumrDocumento())))
		   {
		      throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_ITCD_SEPARACAO_DIVORCIO_COMPARA_CPF_PROCURADOR_DECLARANTE);
		   }
		   if ((procuradorVo.getNumrDocumento().equals(giaITCDSeparacaoDivorcioVo.getConjuge2().getPessoaConjuge().getNumrDocumento())))
		   {
		      throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_ITCD_SEPARACAO_DIVORCIO_COMPARA_CPF_PROCURADOR_CONJUGE2);
		   }     			
		}
	}

	/**
	 * Método responsável por solicitar a atualização das abas Bens Tributáveis e Beneficiários.
	 * @param giaITCDSeparacaoDivorcioVo
	 * @throws ObjetoObrigatorioException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public static void atualizarAbasGiaITCD(final GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo) throws ObjetoObrigatorioException, ParametroObrigatorioException
	{
		Validador.validaObjeto(giaITCDSeparacaoDivorcioVo);
		atualizaAbaBensTributaveis(giaITCDSeparacaoDivorcioVo);
		atualizaAbaConjuge(giaITCDSeparacaoDivorcioVo);
	}
	
	/**
	 * Método responsável por solicitar a atualização da aba Bens Tributáveis, dado possíveis alterações na Aba Dados Gerais que implicam na regra 
	 * da aba Bens Tributáveis.
	 * @param giaITCDSeparacaoDivorcioVo
	 * @throws ObjetoObrigatorioException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public static void atualizaAbaBensTributaveis(final GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(giaITCDSeparacaoDivorcioVo);
		if(Validador.isCollectionValida(giaITCDSeparacaoDivorcioVo.getBemTributavelVo().getCollVO()))
		{
			if ( !giaITCDSeparacaoDivorcioVo.getRegimeCasamento().is(DomnRegimeCasamento.COMUNHAO_PARCIAL_DE_BENS))
			{
				BemTributavelBe.ocultaOpcaoBemParticular(giaITCDSeparacaoDivorcioVo.getBemTributavelVo());            
			}
		}
		calculaValorTotalBensTributaveis(giaITCDSeparacaoDivorcioVo);
	}	

	/**
	 * Método responsável por realizar a chamada ao método que obtém a base de cálculo tributável, e
	 * calcula valor total dos bens, a título informativo.
	 * @param giaITCDSeparacaoDivorcioVo
	 * @throws ObjetoObrigatorioException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public static void calculaValorTotalBensTributaveis(final GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo) throws ObjetoObrigatorioException
	{
		calculaBaseCalculoTributavel(giaITCDSeparacaoDivorcioVo);
		giaITCDSeparacaoDivorcioVo.setValorTotalBensDeclarados(giaITCDSeparacaoDivorcioVo.getValorTotalBensDeclaradosAnteriorCasamento() + giaITCDSeparacaoDivorcioVo.getValorTotalBensDeclaradosPosteriorCasamento());
	}	

	/**
	 * Método responsável por obter a Base de Cálculo Tributável dos bens, 
	 * obtendo os totais dos bens sendo classificados como anterior ao casamento e posterior ao casamento.
	 * @param giaITCDSeparacaoDivorcioVo
	 * @throws ObjetoObrigatorioException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private static void calculaBaseCalculoTributavel(final GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(giaITCDSeparacaoDivorcioVo);
		double valorBensAnteriorCasamento = 0;
		double valorBensAnteriorCasamentoConcordado = 0;
		double valorBensPosteriorCasamento = 0;
		double valorBensPosteriorCasamentoConcordado = 0;
		double valorTotalBensArbritradao = 0;
		double valorTotalBensConcordado = 0;
		double valorPercentual = 0;
		int quantidadeBemConcordado = 0;
		if (Validador.isObjetoValido(giaITCDSeparacaoDivorcioVo.getBemTributavelVo()) && Validador.isCollectionValida(giaITCDSeparacaoDivorcioVo.getBemTributavelVo().getCollVO()))
		{
			//TODO Versão 1.3 
			if ((giaITCDSeparacaoDivorcioVo.getNumeroVersaoGIAITCD().is(DomnVersaoGIAITCD.VERSAO_1_3)) || (giaITCDSeparacaoDivorcioVo.getNumeroVersaoGIAITCD().is(DomnVersaoGIAITCD.VERSAO_1_4)))
			{
				for (Iterator iteBem = giaITCDSeparacaoDivorcioVo.getBemTributavelVo().getCollVO().iterator(); iteBem.hasNext(); )
				{
					BemTributavelVo bem = (BemTributavelVo) iteBem.next();
					if (bem.getBemParticular().is(DomnSimNao.SIM))
					{
						valorBensAnteriorCasamento += BemTributavelBe.getValorBemTributavel(bem);
					}
					else
					{
						valorBensPosteriorCasamento += BemTributavelBe.getValorBemTributavel(bem);
					}
					if (bem.getConcordaComValorArbitrado().is(DomnSimNao.SIM))
					{
						++quantidadeBemConcordado;
						if (bem.getBemParticular().is(DomnSimNao.SIM))
						{
							valorBensAnteriorCasamentoConcordado += BemTributavelBe.getValorBemTributavel(bem);
						}
						else
						{
							valorBensPosteriorCasamentoConcordado += BemTributavelBe.getValorBemTributavel(bem);
						}
					}
					else
					{
						if (bem.getBemParticular().is(DomnSimNao.SIM))
						{
							valorBensAnteriorCasamentoConcordado += BemTributavelBe.getValorBemInformado(bem);
						}
						else
						{
							valorBensPosteriorCasamentoConcordado += BemTributavelBe.getValorBemInformado(bem);
						}
					}
				}
				valorTotalBensArbritradao = NumeroUtil.arredondaNumero(valorBensAnteriorCasamento + valorBensPosteriorCasamento);
				valorTotalBensConcordado = NumeroUtil.arredondaNumero(valorBensAnteriorCasamentoConcordado + valorBensPosteriorCasamentoConcordado);
				valorPercentual = NumeroUtil.arredondaNumero((valorTotalBensConcordado / valorTotalBensArbritradao) * 100);
				if (valorPercentual >= giaITCDSeparacaoDivorcioVo.getPorcentagemAconsiderar() || quantidadeBemConcordado == giaITCDSeparacaoDivorcioVo.getBemTributavelVo().getCollVO().size())
				{
					giaITCDSeparacaoDivorcioVo.setTipoProtocoloGIA(new DomnTipoProtocolo(DomnTipoProtocolo.PROTOCOLO_AUTOMATICO));
					giaITCDSeparacaoDivorcioVo.setValorTotalBensDeclaradosAnteriorCasamento(valorBensAnteriorCasamentoConcordado);
					giaITCDSeparacaoDivorcioVo.setValorTotalBensDeclaradosPosteriorCasamento(valorBensPosteriorCasamentoConcordado);
				}
				else
				{
					giaITCDSeparacaoDivorcioVo.setTipoProtocoloGIA(new DomnTipoProtocolo(DomnTipoProtocolo.PROTOCOLO_MANUAL));
					giaITCDSeparacaoDivorcioVo.setValorTotalBensDeclaradosAnteriorCasamento(valorBensAnteriorCasamento);
					giaITCDSeparacaoDivorcioVo.setValorTotalBensDeclaradosPosteriorCasamento(valorBensPosteriorCasamento);
				}
			}
			else
			{
				for (Iterator iteBem = giaITCDSeparacaoDivorcioVo.getBemTributavelVo().getCollVO().iterator(); iteBem.hasNext(); )
				{
					BemTributavelVo bem = (BemTributavelVo) iteBem.next();
					if (bem.getBemParticular().is(DomnSimNao.SIM))
					{
						valorBensAnteriorCasamento += BemTributavelBe.getValorBemTributavel(bem);
					}
					else
					{
						valorBensPosteriorCasamento += BemTributavelBe.getValorBemTributavel(bem);
					}
				}
				giaITCDSeparacaoDivorcioVo.setValorTotalBensDeclaradosAnteriorCasamento(valorBensAnteriorCasamento);
				giaITCDSeparacaoDivorcioVo.setValorTotalBensDeclaradosPosteriorCasamento(valorBensPosteriorCasamento);
			}
			giaITCDSeparacaoDivorcioVo.setValorBaseCalculoTributavel(0);
		}
	}

	/**
	 * Método reponsável por solicitar a atualização da aba Beneficiários, dado possíveis alterações na Aba Dados Gerais e Aba Bens Tributáveis que 
	 * implicam na regra da aba Beneficiários.
	 * @param giaITCDSeparacaoDivorcioVo
	 * @throws ObjetoObrigatorioException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public static void atualizaAbaConjuge(final GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo) throws ObjetoObrigatorioException, ParametroObrigatorioException
	{
		Validador.validaObjeto(giaITCDSeparacaoDivorcioVo);
		if(giaITCDSeparacaoDivorcioVo.getRegimeCasamento().is(DomnRegimeCasamento.SEPARACAO_TOTAL_DE_BENS))
		{
			giaITCDSeparacaoDivorcioVo.getConjuge1().setCollVO(null);
			giaITCDSeparacaoDivorcioVo.getConjuge2().setCollVO(null);
		}
	   
      if(giaITCDSeparacaoDivorcioVo.getAux() == 0){
            giaITCDSeparacaoDivorcioVo.setTipoProtocoloAnterior(giaITCDSeparacaoDivorcioVo.getTipoProtocoloGIA().getTextoCorrente());
            giaITCDSeparacaoDivorcioVo.setAux(1);
      }
      
      if(!giaITCDSeparacaoDivorcioVo.getTipoProtocoloGIA().getTextoCorrente().equals(giaITCDSeparacaoDivorcioVo.getTipoProtocoloAnterior())){
         giaITCDSeparacaoDivorcioVo.getConjuge1().getCollVO().removeAll(giaITCDSeparacaoDivorcioVo.getConjuge1().getCollVO());            
         giaITCDSeparacaoDivorcioVo.getConjuge2().getCollVO().removeAll(giaITCDSeparacaoDivorcioVo.getConjuge2().getCollVO());   
         giaITCDSeparacaoDivorcioVo.setTipoProtocoloAnterior(giaITCDSeparacaoDivorcioVo.getTipoProtocoloGIA().getTextoCorrente());         
         throw new ParametroObrigatorioException(MensagemErro.GIA_ITCD_SEPARACAO_VALIDAR_MUDANCA_DE_PROTOCOLO);
      }
	}

	public static void validarIncluirServidorAvaliacao(GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo, ServidorSefazIntegracaoVo servidorVo) throws ObjetoObrigatorioException, 
			  RegistroNaoPodeSerUtilizadoException
	{
		Validador.validaObjeto(giaITCDSeparacaoDivorcioVo);
		Validador.validaObjeto(servidorVo);
		
		if(servidorVo.getNumrCPF().toString().equals(giaITCDSeparacaoDivorcioVo.getConjuge1().getPessoaConjuge().getNumrDocumento()))
		{
		   throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.SERVIDOR_PARTICIPANTE_NAO_PODE_SER_AVALIADOR);
		}
	   if(servidorVo.getNumrCPF().toString().equals(giaITCDSeparacaoDivorcioVo.getConjuge2().getPessoaConjuge().getNumrDocumento()))
	   {
	      throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.SERVIDOR_PARTICIPANTE_NAO_PODE_SER_AVALIADOR);
	   }
		for(Iterator it = giaITCDSeparacaoDivorcioVo.getBeneficiarioVo().getCollVO().iterator(); it.hasNext();  )
		{
			BeneficiarioVo beneficiarioVo = (BeneficiarioVo) it.next();
		   if(servidorVo.getNumrCPF().toString().equals(giaITCDSeparacaoDivorcioVo.getConjuge2().getPessoaConjuge().getNumrDocumento()))
		   {
		      throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.SERVIDOR_PARTICIPANTE_NAO_PODE_SER_AVALIADOR);
		   }
		}
	}
   
   public void atualizarValorRecebidoAposAvaliacao(final GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo)
   {
      for( Iterator it = giaITCDSeparacaoDivorcioVo.getConjuge1().getCollVO().iterator(); it.hasNext() ;)
      {
         ConjugeBemTributavelVo conjuge1 = (ConjugeBemTributavelVo) it.next();
         conjuge1.setPessoaConjuge( giaITCDSeparacaoDivorcioVo.getConjuge1().getPessoaConjuge()  );
         conjuge1.setLogSefazVo( giaITCDSeparacaoDivorcioVo.getLogSefazVo() );       
         atualizarValorRecebidoAposAvaliacaoAuxilar(conjuge1);
         
      }

      for( Iterator it = giaITCDSeparacaoDivorcioVo.getConjuge2().getCollVO().iterator(); it.hasNext() ;)
      {
         ConjugeBemTributavelVo conjuge2 = (ConjugeBemTributavelVo) it.next();
         conjuge2.setPessoaConjuge( giaITCDSeparacaoDivorcioVo.getConjuge2().getPessoaConjuge()  );
         conjuge2.setLogSefazVo( giaITCDSeparacaoDivorcioVo.getLogSefazVo() );
         atualizarValorRecebidoAposAvaliacaoAuxilar(conjuge2);
      }
   }
   
   /**
    * Método auxiliar do método atualizarValorRecebidoAposAvaliacao(final GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo);
    * @param conjugeVo
    */
   private void atualizarValorRecebidoAposAvaliacaoAuxilar( ConjugeBemTributavelVo conjugeVo )
   {
      // Montagem do objeto ConjugeBemTributavelVo para consulta no BD.         
      ConjugeBemTributavelVo  conjugeConsulta = new ConjugeBemTributavelVo();     
      conjugeConsulta.setBemTributavelVo(new BemTributavelVo( conjugeVo.getBemTributavelVo().getCodigo() ) );
      conjugeConsulta.setGiaITCDVo( new GIAITCDVo(conjugeVo.getGiaITCDVo().getCodigo()) );     
      ContribuinteIntegracaoVo contribuinte = new ContribuinteIntegracaoVo();
      contribuinte.setNumrContribuinte(conjugeVo.getPessoaConjuge().getNumrContribuinte() );       
      conjugeConsulta.setPessoaConjuge( contribuinte );        
      ConjugeBemTributavelVo  conjuge = new ConjugeBemTributavelVo(conjugeConsulta);

      try
      {
         ConjugeBemTributavelBe conjugeBe = new ConjugeBemTributavelBe(conn);
         conjugeBe.consultarConjugeBemTributavel(conjuge);
         conjuge.setValorConjugeAvaliacao( StringUtil.monetarioToDouble( conjugeVo.getValorConjugeFormatado()  ) );
         conjuge.setLogSefazVo(conjugeVo.getLogSefazVo() );
         conjugeBe.alterarConjugeBemTributavel(conjuge);   
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }
		
	public static boolean isValorBensConjugesIguais(GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo)
	{
		return CalculoITCD.valorBensConjugesIguais(giaITCDSeparacaoDivorcioVo.getValorTotalConjuge1() , giaITCDSeparacaoDivorcioVo.getValorTotalConjuge2());
	}
	
}
