package br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao;

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

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelBe;
import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelVo;
import br.gov.mt.sefaz.itc.model.generico.beneficiario.BeneficiarioBe;
import br.gov.mt.sefaz.itc.model.generico.beneficiario.BeneficiarioVo;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.GIAITCDDoacaoBeneficiarioBe;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.GIAITCDDoacaoBeneficiarioVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.aliquota.GIAITCDDoacaoBeneficiarioAliquotaBe;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.aliquota.GIAITCDDoacaoBeneficiarioAliquotaVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.doacaosucessiva.GIAITCDDoacaoSucessivaBe;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.doacaosucessiva.GIAITCDDoacaoSucessivaVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.configuracaogerencialparametros.ConfiguracaoGerencialParametrosBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.configuracaogerencialparametros.ConfiguracaoGerencialParametrosVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.naturezadaoperacao.NaturezaOperacaoVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.parametrosdalegislacao.AliquotaVo;
import br.gov.mt.sefaz.itc.util.NumeroUtil;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusGIAITCD;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoBem;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoDoacao;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoDocumento;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoGIA;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProtocolo;
import br.gov.mt.sefaz.itc.util.dominio.DomnVersaoGIAITCD;
import br.gov.mt.sefaz.itc.util.facade.ConfiguracaoITCD;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.CadastroBe;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.CepIntegracaoVo;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.ContribuinteIntegracaoVo;
import br.gov.mt.sefaz.itc.util.integracao.gestaopessoas.ServidorSefazIntegracaoVo;

import java.sql.Connection;
import java.sql.SQLException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


/**
 * Classe que encapsula a regra de negócio da entidade GIA ITCD Doação
 * 
 * @author Daniel Balieiro
 * @version $Revision: 1.12 $
 */
public class GIAITCDDoacaoBe extends AbstractBe
{
	/**
	 * Construtor Padrão
	 * 
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	public GIAITCDDoacaoBe() throws SQLException
	{
		super();
	}

	/**
	 * Construtor que recebe a conexão com o banco de dados
	 * 
	 * @param conexao
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	public GIAITCDDoacaoBe(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Método para validar os dados Gerais da GIA ITCD
	 * @param giaITCDDoacaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @throws IntegracaoException
	 * @throws RegistroExistenteException
	 * @implemented by Daniel Balieiro
	 */
	public static void validarDadosGeraisGIAITCDDoacao(final GIAITCDDoacaoVo giaITCDDoacaoVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException, IntegracaoException, RegistroExistenteException
	{
		Validador.validaObjeto(giaITCDDoacaoVo);
		for (Iterator iteBeneficiario = 
					 giaITCDDoacaoVo.getBeneficiarioVo().getCollVO().iterator(); iteBeneficiario.hasNext(); )
		{
			if (giaITCDDoacaoVo.getResponsavelVo().getNumrDocumento().equals(((BeneficiarioVo) iteBeneficiario.next()).getPessoaBeneficiaria().getNumrDocumento()))
			{
				throw new ParametroObrigatorioException(MensagemErro.GIA_ITCD_DOACAO_VALIDAR_CPF_DECLARANTE_CPF_BENEFICIARIO);
			}
		}
		if (giaITCDDoacaoVo.getProcuradorVo().getNumrDocumento().equals(giaITCDDoacaoVo.getResponsavelVo().getNumrDocumento()))
		{
			throw new ParametroObrigatorioException(MensagemErro.GIA_ITCD_DOACAO_VALIDAR_CPF_PROCURADOR_CPF_DECLARANTE);
		}
		if (!Validador.isDominioNumericoValido(giaITCDDoacaoVo.getResponsavelVo().getTipoDocumento()))
		{
			throw new ParametroObrigatorioException(MensagemErro.GIA_ITCD_DOACAO_VALIDAR_TIPO_DOCUMENTO);
		}
		if (!Validador.isStringValida(giaITCDDoacaoVo.getResponsavelVo().getNumrDocumento()))
		{
			throw new ParametroObrigatorioException(MensagemErro.GIA_ITCD_DOACAO_VALIDAR_NUMERO_DOCUMENTO);
		}
		if (giaITCDDoacaoVo.getResponsavelVo().getTipoDocumento().is(DomnTipoDocumento.CPF) && 
				 (!Validador.isCPFValido(giaITCDDoacaoVo.getResponsavelVo().getNumrDocumento())))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_CPF_INVALIDO);
		}
		else if (giaITCDDoacaoVo.getResponsavelVo().getTipoDocumento().is(DomnTipoDocumento.CNPJ) && 
				 (!Validador.isCNPJValido(giaITCDDoacaoVo.getResponsavelVo().getNumrDocumento())))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_CNPJ_INVALIDO);
		}
		if (Validador.isStringValida(giaITCDDoacaoVo.getProcuradorVo().getNumrDocumento()))
		{
			ContribuinteIntegracaoVo procurador = new ContribuinteIntegracaoVo();
			procurador.setNumrDocumento(giaITCDDoacaoVo.getProcuradorVo().getNumrDocumento());
			procurador = new ContribuinteIntegracaoVo(procurador);
			CadastroBe cadastroBe = null;
			try
			{
				cadastroBe = new CadastroBe();
				procurador = cadastroBe.obterContribuintePorCpf(procurador, true);
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
			if (!procurador.isExiste())
			{
				throw new RegistroExistenteException(MensagemErro.VALIDAR_CPF_INVALIDO);
			}
		}
		if (!giaITCDDoacaoVo.getNaturezaOperacaoVo().isExiste())
		{
			throw new ParametroObrigatorioException(MensagemErro.GIA_ITCD_DOACAO_VALIDAR_NATUREZA_OPERACAO);
		}
		if (!Validador.isNumericoValido(giaITCDDoacaoVo.getFracaoIdeal()))
		{
			throw new ParametroObrigatorioException(MensagemErro.GIA_ITCD_DOACAO_VALIDAR_FRACAO_IDEAL);
		}
		if (giaITCDDoacaoVo.getFracaoIdeal() <= 0)
		{
			throw new ParametroObrigatorioException(MensagemErro.GIA_ITCD_DOACAO_VALIDAR_FRACAO_IDEAL_MENOR_ZERO);
		}
		else if (giaITCDDoacaoVo.getFracaoIdeal() > 100)
		{
			throw new ParametroObrigatorioException(MensagemErro.GIA_ITCD_DOACAO_VALIDAR_FRACAO_IDEAL_MENOR_CEM);
		}
		GIAITCDBe.validaJustificativaAlteracao(giaITCDDoacaoVo);
	}

	/**
	 * Método para validar os dados dos Bens Tributáveis de uma GIA-ITCD
	 * @param giaITCDDoacaoVo
	 * @throws RegistroExistenteException
	 * @throws ParametroObrigatorioException
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws IntegracaoException
	 * @implemented by Daniel Balieiro
	 */
	public static void validaBensTributaveisGIAITCDDoacao(final GIAITCDDoacaoVo giaITCDDoacaoVo) throws RegistroExistenteException, 
			  ParametroObrigatorioException, ObjetoObrigatorioException, ConsultaException, ConexaoException, 
			  IntegracaoException
	{
		if (Validador.isCollectionValida(giaITCDDoacaoVo.getBemTributavelVo().getCollVO()))
		{
			for (Iterator iteBem = giaITCDDoacaoVo.getBemTributavelVo().getCollVO().iterator(); iteBem.hasNext(); )
			{
				BemTributavelVo bem = (BemTributavelVo) iteBem.next();
				for (Iterator iteBemConsulta =  giaITCDDoacaoVo.getBemTributavelVo().getCollVO().iterator(); iteBemConsulta.hasNext(); )
				{
					BemTributavelVo bemConsulta = (BemTributavelVo) iteBemConsulta.next();
					if (!bem.equals(bemConsulta) && 
									 bem.getBemVo().getClassificacaoTipoBem().getValorCorrente() == bemConsulta.getBemVo().getClassificacaoTipoBem().getValorCorrente() && 
									 bem.getBemVo().getDescricaoTipoBem().equals(bemConsulta.getBemVo().getDescricaoTipoBem()) && 
									 bem.getDescricaoBemTributavel().equals(bemConsulta.getDescricaoBemTributavel()))
					{
						throw new RegistroExistenteException(MensagemErro.GIA_ITCD_DOACAO_VALIDAR_DESCRICAO_BEM_EXISTENTE);
					}
					switch (bemConsulta.getBemVo().getClassificacaoTipoBem().getValorCorrente())
					{
						case DomnTipoBem.IMOVEL_RURAL:
							if (!Validador.isObjetoValido(bemConsulta.getFichaImovelVo()))
							{
								throw new ParametroObrigatorioException(MensagemErro.GIA_ITCD_DOACAO_VALIDAR_TIPO_BEM);
							}
							break;
						case DomnTipoBem.IMOVEL_URBANO:
							if (!Validador.isObjetoValido(bemConsulta.getFichaImovelVo()))
							{
								throw new ParametroObrigatorioException(MensagemErro.GIA_ITCD_DOACAO_VALIDAR_TIPO_BEM);
							}
							break;
						case DomnTipoBem.OUTROS_BENS:
							if (!Validador.isStringValida(bemConsulta.getDescricaoBemTributavel()))
							{
								throw new ParametroObrigatorioException(MensagemErro.GIA_ITCD_DOACAO_VALIDAR_TIPO_BEM);
							}
							break;
						default:
							throw new ParametroObrigatorioException(MensagemErro.GIA_ITCD_DOACAO_VALIDAR_CLASSIFICACAO_BEM);
					}
				}
			}
		}
		else
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_BEM_TRIBUTAVEL);
		}
	}

	/**
	 * Método para validar os dados dos Beneficiários de uma GIA-ITCD
	 * @param giaITCDDoacaoVo
	 * @throws ParametroObrigatorioException
	 * @throws RegistroExistenteException
	 * @throws ObjetoObrigatorioException
	 * @throws IntegracaoException
	 * @implemented by Daniel Balieiro
	 */
	public static void validaBeneficiariosGIAITCDDoacao(final GIAITCDDoacaoVo giaITCDDoacaoVo) throws ParametroObrigatorioException, 
			  RegistroExistenteException, ObjetoObrigatorioException, IntegracaoException
	{
		double totalPercentual = 0d;
		CadastroBe cadastroBe = null;							
		if (giaITCDDoacaoVo.getBeneficiarioVo().getCollVO().size() <= 0)
		{
			throw new ParametroObrigatorioException(MensagemErro.BENEFICIARIO_NAO_INFORMADO);
		}
		for (Iterator iteBen = giaITCDDoacaoVo.getBeneficiarioVo().getCollVO().iterator(); iteBen.hasNext(); )
		{
			GIAITCDDoacaoBeneficiarioVo beneficiarioVo = (GIAITCDDoacaoBeneficiarioVo) iteBen.next();
			if (!Validador.isDominioNumericoValido(beneficiarioVo.getPessoaBeneficiaria().getTipoDocumento()))
			{
				throw new ParametroObrigatorioException(MensagemErro.GIA_ITCD_DOACAO_VALIDAR_TIPO_DOCUMENTO_BENEFICIARIO);
			}
			if (!Validador.isStringValida(beneficiarioVo.getPessoaBeneficiaria().getNumrDocumento()))
			{
				throw new ParametroObrigatorioException(MensagemErro.GIA_ITCD_DOACAO_VALIDAR_DOCUMENTO_BENEFICIARIO);
			}
			if (beneficiarioVo.getPessoaBeneficiaria().getTipoDocumento().is(DomnTipoDocumento.CPF))
			{
				String cpf = beneficiarioVo.getPessoaBeneficiaria().getNumrDocumento();
				if (Validador.isCPFValido(cpf))
				{
					ContribuinteIntegracaoVo contribuinteIntegracao;
					try
					{
						cadastroBe = new CadastroBe();
						contribuinteIntegracao = cadastroBe.obterContribuintePorTipoDocumento(beneficiarioVo.getPessoaBeneficiaria().getNumrDocumento(), new Integer(beneficiarioVo.getPessoaBeneficiaria().getTipoDocumento().getValorCorrente()));
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
					if (!Validador.isObjetoValido(contribuinteIntegracao) || !Validador.isCollectionValida(contribuinteIntegracao.getCollVO()))
					{
						throw new ParametroObrigatorioException(MensagemErro.GIA_ITCD_DOACAO_VALIDAR_DOCUMENTO_BENEFICIARIO);
					}
					for (Iterator iteBenAtual = 
										 giaITCDDoacaoVo.getBeneficiarioVo().getCollVO().iterator(); iteBenAtual.hasNext(); )
					{
						BeneficiarioVo beneficiario = (BeneficiarioVo) iteBenAtual.next();
						if (beneficiario.getPessoaBeneficiaria().getNumrDocumento().equals(cpf) && 
											beneficiarioVo != beneficiario)
						{
							throw new RegistroExistenteException(MensagemErro.GIA_ITCD_DOACAO_VALIDAR_CPF_JA_CADASTRADO);
						}
					}
				}
				else
				{
					throw new ParametroObrigatorioException(MensagemErro.VALIDAR_CPF_INVALIDO);
				}
			}
			else if (beneficiarioVo.getPessoaBeneficiaria().getTipoDocumento().is(DomnTipoDocumento.CNPJ))
			{
				String cnpj = beneficiarioVo.getPessoaBeneficiaria().getNumrDocumento();
				if (Validador.isCNPJValido(cnpj))
				{
					ContribuinteIntegracaoVo contribuinteIntegracao;
					try
					{
						cadastroBe = new CadastroBe();
						contribuinteIntegracao = cadastroBe.obterContribuintePorTipoDocumento(beneficiarioVo.getPessoaBeneficiaria().getNumrDocumento(), new Integer(beneficiarioVo.getPessoaBeneficiaria().getTipoDocumento().getValorCorrente()));
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
					if ( !Validador.isObjetoValido(contribuinteIntegracao) || !Validador.isCollectionValida(contribuinteIntegracao.getCollVO()))
					{
						throw new ParametroObrigatorioException(MensagemErro.GIA_ITCD_DOACAO_VALIDAR_DOCUMENTO_BENEFICIARIO);
					}
					for (Iterator iteBenAtual = 
										 giaITCDDoacaoVo.getBeneficiarioVo().getCollVO().iterator(); iteBenAtual.hasNext(); )
					{
						BeneficiarioVo beneficiario = (BeneficiarioVo) iteBenAtual.next();
						if (beneficiario.getPessoaBeneficiaria().getNumrDocumento().equals(cnpj) && 
											beneficiarioVo != beneficiario)
						{
							throw new RegistroExistenteException(MensagemErro.GIA_ITCD_DOACAO_VALIDAR_CPF_JA_CADASTRADO);
						}
					}
				}
				else
				{
					throw new ParametroObrigatorioException(MensagemErro.VALIDAR_CNPJ_INVALIDO);
				}
			}
			else
			{
				throw new ParametroObrigatorioException(MensagemErro.GIA_ITCD_DOACAO_VALIDAR_DOCUMENTO_BENEFICIARIO);
			}
			if (beneficiarioVo.getPercentualBemRecebido() <= 0)
			{
				throw new ParametroObrigatorioException(MensagemErro.GIA_ITCD_DOACAO_VALIDAR_PERCENTUAL_RECEBIDO);
			}
			totalPercentual += beneficiarioVo.getPercentualBemRecebido();
		}
		totalPercentual = NumeroUtil.arredondaNumero(totalPercentual, 2);
		if (totalPercentual > 100d)
		{
			throw new ParametroObrigatorioException(MensagemErro.GIA_ITCD_DOACAO_VALIDAR_PERCENTUAL_RECEBIDO_MAIOR_100);
		}
		if (totalPercentual != 100d) // verifica se o total é diferente a 100%
		{
			throw new ParametroObrigatorioException(MensagemErro.GIA_ITCD_DOACAO_VALIDAR_PERCENTUAL_RECEBIDO_DIFERENTE_100);
		}
	}

	/**
	 * Método para validar as regras sobre a possibilidade de inserir ou alterar um GIA ITCD Doação
	 * 
	 * @param giaITCDDoacaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @throws IntegracaoException
	 * @throws SQLException
	 * @throws ConsultaException
	 * @throws RegistroExistenteException
	 * @throws ConexaoException
	 * @implemented by Daniel Balieiro
	 */
	public void validarIncluirGIAITCDDoacao(final GIAITCDDoacaoVo giaITCDDoacaoVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException, IntegracaoException, ConsultaException, RegistroExistenteException, 
			  ConexaoException
	{
		// Valida os dados da Aba Dados Gerais
		validarDadosGeraisGIAITCDDoacao(giaITCDDoacaoVo);
		// Valida os dados da Aba Bens Tributaveis
		validaBensTributaveisGIAITCDDoacao(giaITCDDoacaoVo);
		// Valida os dados da Aba Beneficiários
		validaBeneficiariosGIAITCDDoacao(giaITCDDoacaoVo);
	}
	
	private double obterValorTotalAtualBensDeclarados(final GIAITCDDoacaoVo giaITCDVo) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(giaITCDVo);
		return obterValorTotalAtualBensDeclarados(giaITCDVo, false);
	}
	
	private double obterValorTotalAtualBensDeclarados(final GIAITCDDoacaoVo giaITCDVo, boolean ignoraAvaliacao) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(giaITCDVo);
		double valorBensArbitrado = 0;
		double valorBemConcordado = 0;
		double valorPercentual = 0;
	   double valorTotal = 0;
		int quantidadeBemConcordado = 0;
	   if(Validador.isObjetoValido(giaITCDVo.getBemTributavelVo()) && Validador.isCollectionValida(giaITCDVo.getBemTributavelVo().getCollVO()))
	   {
			//TODO Versão 1.3 
			if(giaITCDVo.getNumeroVersaoGIAITCD().is(DomnVersaoGIAITCD.VERSAO_1_3) || giaITCDVo.getNumeroVersaoGIAITCD().is(DomnVersaoGIAITCD.VERSAO_1_4))
			{
			    for(Iterator it = giaITCDVo.getBemTributavelVo().getCollVO().iterator(); it.hasNext(); )
			    {
			       BemTributavelVo bem = (BemTributavelVo) it.next();
			       valorBensArbitrado+= BemTributavelBe.getValorBemTributavel(bem, ignoraAvaliacao);
			        if(bem.getConcordaComValorArbitrado().is(DomnSimNao.SIM))
			        {
			            ++quantidadeBemConcordado;
			            valorBemConcordado +=  BemTributavelBe.getValorBemTributavel(bem, ignoraAvaliacao);
			        }
			        else
			        {
			            valorBemConcordado +=  BemTributavelBe.getValorBemInformado(bem, ignoraAvaliacao); 
			        }
			    }
			     
			     if(quantidadeBemConcordado == giaITCDVo.getBemTributavelVo().getCollVO().size())
			     {  
			         return  valorBensArbitrado;
			     }
			     else
			     {
			         valorPercentual = (valorBemConcordado/valorBensArbitrado)*100;
			         if(valorPercentual >= giaITCDVo.getPorcentagemAconsiderar())
			         {
			            return  valorBemConcordado;
			         }
			         else
			         {
			             return valorBensArbitrado;
			         }
			         
			     }
			}
			else
			{
            for(Iterator it = giaITCDVo.getBemTributavelVo().getCollVO().iterator(); it.hasNext(); ){
			          BemTributavelVo bem = (BemTributavelVo) it.next();
			          valorTotal+= BemTributavelBe.getValorBemTributavel(bem, ignoraAvaliacao);
			       }
			   return valorTotal;
			}
	   }
      return 0;
	   		
	}

	/**
	 * Método para gerar o demonstrativo de calculo
	 * 
	 * @param giaITCDDoacaoVo
	 * @throws ObjetoObrigatorioException
	 * @implemented by Daniel Balieiro
	 */
	public void gerarDemonstrativoCalculo(final GIAITCDDoacaoVo giaITCDDoacaoVo) throws ObjetoObrigatorioException, 
			   ConexaoException, ConsultaException
	{
		Validador.validaObjeto(giaITCDDoacaoVo);
		giaITCDDoacaoVo.setValorTotalBensDeclarados(obterValorTotalAtualBensDeclarados(giaITCDDoacaoVo));
		giaITCDDoacaoVo.setValorTotalInformadoBensDeclarados(obterValorTotalAtualBensDeclarados(giaITCDDoacaoVo, true));
      
		double totalBens = giaITCDDoacaoVo.getValorTotalBensDeclarados();
		for (Iterator iteNatureza = giaITCDDoacaoVo.getNaturezaOperacaoVo().getCollVO().iterator(); iteNatureza.hasNext(); )
		{
			NaturezaOperacaoVo naturezaAtual = (NaturezaOperacaoVo) iteNatureza.next();
			if (giaITCDDoacaoVo.getNaturezaOperacaoVo().getCodigo() == naturezaAtual.getCodigo())
			{
				naturezaAtual.setCollVO(giaITCDDoacaoVo.getNaturezaOperacaoVo().getCollVO());
				giaITCDDoacaoVo.setNaturezaOperacaoVo(naturezaAtual);
				break;
			}
		}
		giaITCDDoacaoVo.setValorCalculoDemonstrativo(totalBens * (giaITCDDoacaoVo.getFracaoIdeal() / 100));
      
	   if(giaITCDDoacaoVo.getNaturezaOperacaoVo().getCodigo() == 23){
	         giaITCDDoacaoVo.getNaturezaOperacaoVo().setPercentualBaseCalculo(giaITCDDoacaoVo.getBaseCalculoReduzida());
	   }else{
	         giaITCDDoacaoVo.setBaseCalculoReduzida(giaITCDDoacaoVo.getNaturezaOperacaoVo().getPercentualBaseCalculo());
	   }
      
		if (giaITCDDoacaoVo.getNaturezaOperacaoVo().getTipoBaseCalculo().is(DomnSimNao.SIM))
		{
			giaITCDDoacaoVo.setValorCalculoDemonstrativo(giaITCDDoacaoVo.getValorCalculoDemonstrativo() * (giaITCDDoacaoVo.getNaturezaOperacaoVo().getPercentualBaseCalculo() / 100));			
		}    
      
		giaITCDDoacaoVo.setValorCalculoDemonstrativo(NumeroUtil.arredondaNumero(giaITCDDoacaoVo.getValorCalculoDemonstrativo()));
		if (giaITCDDoacaoVo.getFracaoIdeal() < 100)
		{
			giaITCDDoacaoVo.setTipoDoacao(new DomnTipoDoacao(DomnTipoDoacao.PARCIAL));
		}
		else
		{
			giaITCDDoacaoVo.setTipoDoacao(new DomnTipoDoacao(DomnTipoDoacao.TOTAL));
		}
		double valorITCD = 0;
		//Calcular o valor para cada Beneficiario de acordo com o seu percentual               
		for (Iterator iteBenef = giaITCDDoacaoVo.getBeneficiarioVo().getCollVO().iterator(); iteBenef.hasNext(); )
		{
			GIAITCDDoacaoBeneficiarioVo giaITCDDoacaoBeneficiarioVo = (GIAITCDDoacaoBeneficiarioVo) iteBenef.next();
         //Preencher o responsavelVo Aqui que vem da propia gia que resolve o problema.
         giaITCDDoacaoBeneficiarioVo.getGiaITCDVo().setResponsavelVo(giaITCDDoacaoVo.getResponsavelVo());
		   giaITCDDoacaoBeneficiarioVo.setValorItcdDoacaoSucessiva(giaITCDDoacaoBeneficiarioVo.getGiaitcdDoacaoSucessivaVo().getSomaValorITCDAnterior());		         
         giaITCDDoacaoBeneficiarioVo.setValorRecebidoAuxiliar(giaITCDDoacaoBeneficiarioVo.getValorRecebido());       
         giaITCDDoacaoBeneficiarioVo.setValorRecebido(giaITCDDoacaoVo.getValorCalculoDemonstrativo() * (giaITCDDoacaoBeneficiarioVo.getPercentualBemRecebido() / 100));			
			             
         if(giaITCDDoacaoVo.getStatusVo().getStatusGIAITCD().getDomnValr() == DomnStatusGIAITCD.EM_ELABORACAO){
            giaITCDDoacaoBeneficiarioVo.setValorRecolher(0.00);
            giaITCDDoacaoBeneficiarioVo.setValorItcdBeneficiario(0.00);
         }
         
         if (Validador.isNumericoValido(giaITCDDoacaoVo.getValorUPF()))
			{
				AliquotaVo aliquotaVo = null;
				if (giaITCDDoacaoVo.getParametroLegislacaoVo().isLegislacaoCascata())
				{
               if(giaITCDDoacaoBeneficiarioVo.getFlagDoacaoSucessiva() == 1){                  
                  calculoEmCascataDoacaoSucessiva(giaITCDDoacaoVo, giaITCDDoacaoBeneficiarioVo);
               }else{                  
                  calculoEmCascata(giaITCDDoacaoVo, giaITCDDoacaoBeneficiarioVo);
               }
					
				}
				else if (giaITCDDoacaoVo.getValorCalculoDemonstrativo() > 0)
				{
					aliquotaVo = GIAITCDBe.encontraAliquotaNormal(giaITCDDoacaoVo.getParametroLegislacaoVo().getAliquotaVo().getCollVO(), giaITCDDoacaoBeneficiarioVo.getValorRecebido(), giaITCDDoacaoVo.getValorUPF(), DomnTipoGIA.INTER_VIVOS);
					giaITCDDoacaoBeneficiarioVo.setPercentualAliquota(aliquotaVo.getPercentualLegislacaoAliquota());
               
				   giaITCDDoacaoBeneficiarioVo.setValorRecolher(((giaITCDDoacaoBeneficiarioVo.getValorRecebido()) * (giaITCDDoacaoBeneficiarioVo.getPercentualAliquota() / 100)) - giaITCDDoacaoBeneficiarioVo.getValorItcdDoacaoSucessiva());
               
               if(giaITCDDoacaoBeneficiarioVo.getValorItcdBeneficiario() == 0.0){
                  giaITCDDoacaoBeneficiarioVo.setValorItcdBeneficiario((giaITCDDoacaoBeneficiarioVo.getValorRecebido()) * (giaITCDDoacaoBeneficiarioVo.getPercentualAliquota() / 100));
               }
               
					valorITCD += giaITCDDoacaoBeneficiarioVo.getValorRecolher();
				}
				else
				{
					giaITCDDoacaoBeneficiarioVo.setPercentualAliquota(0);
					giaITCDDoacaoBeneficiarioVo.setValorRecolher(0);
				}
			}
		}
      
	   if(giaITCDDoacaoVo.getStatusVo().getStatusGIAITCD().getDomnValr() == DomnStatusGIAITCD.EM_ELABORACAO || giaITCDDoacaoVo.getStatusVo().getStatusGIAITCD().getDomnValr() == DomnStatusGIAITCD.PENDENTE_DE_PROTOCOLO){
         verificarIsencaoImpostoMenorQueUmaUpfPorBeneficiario(giaITCDDoacaoVo);
      }
      
		if (giaITCDDoacaoVo.getParametroLegislacaoVo().isLegislacaoCascata())
		{
			totalizaDemonstrativoCalculo(giaITCDDoacaoVo);
		}
		else
		{
			giaITCDDoacaoVo.setValorITCD(valorITCD);
			giaITCDDoacaoVo.setValorRecolhimento(valorITCD);
		}  
		
	}
   
   public void verificarIsencaoImpostoMenorQueUmaUpfPorBeneficiario(final GIAITCDDoacaoVo giaITCDDoacaoVo) {

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
         // Como não está decidido a Data Final para isenções é criado uma Data controle para verificação 
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
         
     if ((giaITCDDoacaoVo.getDataCriacao().after(dateInicial)) && (giaITCDDoacaoVo.getDataCriacao().before(dateFinal))) {
         double valorRecolherGia = 0.0;  
         
      GIAITCDDoacaoSucessivaBe doacaoSucessivaBe = null;
         for (Iterator it = giaITCDDoacaoVo.getBeneficiarioVo().getCollVO().iterator(); it.hasNext();) {
           GIAITCDDoacaoBeneficiarioVo giaDoacaoBeneficiario = (GIAITCDDoacaoBeneficiarioVo) it.next();      
            giaDoacaoBeneficiario.getGiaITCDVo().setDataCriacao(giaITCDDoacaoVo.getDataCriacao());
           try{                   
              if(giaDoacaoBeneficiario.getFlagDoacaoSucessiva() == 2){
                 if(giaDoacaoBeneficiario.getValorItcdBeneficiario() == 0.0){  
                    giaDoacaoBeneficiario.setInfoDispensaRecolhimento(1);
                    giaDoacaoBeneficiario.setInfoIsencao(2);
                    
                 }else if(giaDoacaoBeneficiario.getValorItcdBeneficiario() <= giaITCDDoacaoVo.getValorUPF() && giaDoacaoBeneficiario.getValorItcdBeneficiario() > 0.0){
                    giaDoacaoBeneficiario.setValorRecolher(0);              
                    giaDoacaoBeneficiario.setInfoIsencao(1);
                    giaDoacaoBeneficiario.setInfoDispensaRecolhimento(2);
                    for (Iterator iteAliquotas = giaDoacaoBeneficiario.getGiaItcdDoacaoBeneficiarioAliquotaVo().getCollVO().iterator(); iteAliquotas.hasNext();) {
                      GIAITCDDoacaoBeneficiarioAliquotaVo giaITCDDoacaoBeneficiarioAliquotaVo = (GIAITCDDoacaoBeneficiarioAliquotaVo) iteAliquotas.next();
                      giaITCDDoacaoBeneficiarioAliquotaVo.setValorRecolher(0);
                    }  
                 }
                 else{
                    valorRecolherGia += giaDoacaoBeneficiario.getValorRecolher();
                    giaDoacaoBeneficiario.setInfoDispensaRecolhimento(1);
                    giaDoacaoBeneficiario.setInfoIsencao(1);
                    valorRecolherGia = 0.0;
                 } 
              }else{    
                 if(giaDoacaoBeneficiario.getGiaitcdDoacaoSucessivaVo().getCollVO().size() == 0){
                    doacaoSucessivaBe = new GIAITCDDoacaoSucessivaBe(conn);
                    giaDoacaoBeneficiario.setGiaitcdDoacaoSucessivaVo(doacaoSucessivaBe.consultaDoacoesSucessivasDoBenef(giaDoacaoBeneficiario));
                 }
                  double somatoriaValorItcdBeneficiarioDoacaoSucessiva = 0.00;
                  boolean isTodosNaoDispensadoRecolhimentoOuIsento = false;
                     for(Iterator iterator = giaDoacaoBeneficiario.getGiaitcdDoacaoSucessivaVo().getCollVO().iterator(); iterator.hasNext();){
                        GIAITCDDoacaoSucessivaVo doacaoSucessiva = (GIAITCDDoacaoSucessivaVo) iterator.next();
                        GIAITCDDoacaoBeneficiarioVo beneficiarioDoacaoSucessiva = (GIAITCDDoacaoBeneficiarioVo) doacaoSucessiva.getBeneficiarioVo();
                        somatoriaValorItcdBeneficiarioDoacaoSucessiva += doacaoSucessiva.getValorITCDBeneficiario();
                        if (Integer.valueOf(1).equals(beneficiarioDoacaoSucessiva.getInfoDispensaRecolhimento()) && Integer.valueOf(1).equals(beneficiarioDoacaoSucessiva.getInfoIsencao())) {
                              isTodosNaoDispensadoRecolhimentoOuIsento = true;
                        }                        
                     }
                  double somatoriaValorItcdBeneficiario = somatoriaValorItcdBeneficiarioDoacaoSucessiva + giaDoacaoBeneficiario.getValorItcdBeneficiario();
                    if(somatoriaValorItcdBeneficiario == 0.0){  
                       giaDoacaoBeneficiario.setInfoDispensaRecolhimento(1);
                       giaDoacaoBeneficiario.setInfoIsencao(2);
                       giaITCDDoacaoVo.setGiaIsentaUpf(true);
                    }else if(somatoriaValorItcdBeneficiario <= giaITCDDoacaoVo.getValorUPF() && somatoriaValorItcdBeneficiario > 0.0){
                       giaDoacaoBeneficiario.setValorRecolher(0);              
                       giaDoacaoBeneficiario.setInfoIsencao(1);
                       giaDoacaoBeneficiario.setInfoDispensaRecolhimento(2);
                       for (Iterator iteAliquotas = giaDoacaoBeneficiario.getGiaItcdDoacaoBeneficiarioAliquotaVo().getCollVO().iterator(); iteAliquotas.hasNext();) {
                         GIAITCDDoacaoBeneficiarioAliquotaVo giaITCDDoacaoBeneficiarioAliquotaVo = (GIAITCDDoacaoBeneficiarioAliquotaVo) iteAliquotas.next();
                         giaITCDDoacaoBeneficiarioAliquotaVo.setValorRecolher(0);
                       } 
                    giaITCDDoacaoVo.setGiaIsentaUpf(true);
                 }else{                 
                    if(isTodosNaoDispensadoRecolhimentoOuIsento){                                          
                       giaDoacaoBeneficiario.setInfoDispensaRecolhimento(1);
                       giaDoacaoBeneficiario.setInfoIsencao(1);                       
                    }else{
                       valorRecolherGia += somatoriaValorItcdBeneficiarioDoacaoSucessiva + giaDoacaoBeneficiario.getValorRecolher();
                       giaDoacaoBeneficiario.setValorRecolher(valorRecolherGia);
                       giaDoacaoBeneficiario.setInfoDispensaRecolhimento(1);
                       giaDoacaoBeneficiario.setInfoIsencao(1);    
                       valorRecolherGia = 0.0;
                    }
                 }  
                 
              }
            }catch(Exception e){
               e.printStackTrace();
            }finally{
               if(doacaoSucessivaBe != null){
                  doacaoSucessivaBe.close();
                  doacaoSucessivaBe = null;
               }
            }
         }           

       }   

   }

   /**
    * 
    * 
    * 
    */
   private void calculoEmCascata(final GIAITCDDoacaoVo giaITCDDoacaoVo, GIAITCDDoacaoBeneficiarioVo giaITCDDoacaoBeneficiarioVo)
   {
      AliquotaVo aliquotasInterVivos = new AliquotaVo();
      double valorUPF = giaITCDDoacaoVo.getValorUPF();
      double valorRecebido = giaITCDDoacaoBeneficiarioVo.getValorRecebido();
      double valorRecolherPorBeneficiario = 0;
      
      for(Iterator ite = giaITCDDoacaoVo.getParametroLegislacaoVo().getAliquotaVo().getCollVO().iterator(); ite.hasNext();)
      {
         AliquotaVo atual = (AliquotaVo) ite.next();
         if (atual.getTipoFatoGerador().is(DomnTipoGIA.INTER_VIVOS))
         {
            aliquotasInterVivos.getCollVO().add(atual);
         }
      }
      
      GIAITCDDoacaoBeneficiarioVo giaItcdDoacaoBeneficiarioVoAux = new GIAITCDDoacaoBeneficiarioVo();

      for (Iterator it = aliquotasInterVivos.getCollVO().iterator(); it.hasNext(); )
      {
         AliquotaVo atual = (AliquotaVo) it.next();
         GIAITCDDoacaoBeneficiarioAliquotaVo giaItcdDoacaoBeneficiarioAliquotaVo = new GIAITCDDoacaoBeneficiarioAliquotaVo();
         giaItcdDoacaoBeneficiarioAliquotaVo.setCodigoAliquota(atual.getCodigo());
         giaItcdDoacaoBeneficiarioAliquotaVo.setPercentualAliquota(atual.getPercentualLegislacaoAliquota());
         double valorUPFFinal = (atual.getQuantidadeUPFFinal()) * valorUPF;
         double valorUPFInicial = (atual.getQuantidadeUPFInicial()-1) * valorUPF;
         double valorFaixa = 0;
         
         double qtdUpfFaixa = ((atual.getQuantidadeUPFFinal() - atual.getQuantidadeUPFInicial() + 1));
         
         if (valorUPFFinal != 0)
         {
            valorFaixa = (atual.getQuantidadeUPFFinal() - atual.getQuantidadeUPFInicial() + 1) * valorUPF;
         }
         else
         {
            valorFaixa = valorRecebido - valorUPFInicial;
         }            
                  
         
         if (valorRecebido > valorUPFInicial){
         
            if (valorRecebido > valorUPFFinal && valorUPFFinal > 0.0)
            {
               giaItcdDoacaoBeneficiarioAliquotaVo.setValorBaseCalculo(valorFaixa);               
               giaItcdDoacaoBeneficiarioAliquotaVo.setValorRecolher(valorFaixa * atual.getPercentualLegislacaoAliquota() / 100);
               giaItcdDoacaoBeneficiarioAliquotaVo.setNumrUpfDisponivel(0.00);
               giaItcdDoacaoBeneficiarioAliquotaVo.setNumrUpfUtilizada(qtdUpfFaixa);
            }
            else
            {
               giaItcdDoacaoBeneficiarioAliquotaVo.setValorBaseCalculo(valorRecebido - valorUPFInicial);               
               giaItcdDoacaoBeneficiarioAliquotaVo.setValorRecolher(giaItcdDoacaoBeneficiarioAliquotaVo.getValorBaseCalculo() * atual.getPercentualLegislacaoAliquota() / 100);
               giaItcdDoacaoBeneficiarioAliquotaVo.setNumrUpfUtilizada((valorRecebido - valorUPFInicial) / valorUPF);
               if(atual.getQuantidadeUPFFinal() != 0){
                  giaItcdDoacaoBeneficiarioAliquotaVo.setNumrUpfDisponivel(qtdUpfFaixa - giaItcdDoacaoBeneficiarioAliquotaVo.getNumrUpfUtilizada());
               }               
            }
            
         }
         else
         {
            break;
         }
         
         giaItcdDoacaoBeneficiarioVoAux.getGiaItcdDoacaoBeneficiarioAliquotaVo().getCollVO().add(giaItcdDoacaoBeneficiarioAliquotaVo);
         valorRecolherPorBeneficiario += giaItcdDoacaoBeneficiarioAliquotaVo.getValorRecolher();         
      }
      giaITCDDoacaoBeneficiarioVo.setGiaItcdDoacaoBeneficiarioAliquotaVo(giaItcdDoacaoBeneficiarioVoAux.getGiaItcdDoacaoBeneficiarioAliquotaVo());

      if(giaITCDDoacaoBeneficiarioVo.getValorItcdBeneficiario() == null){
         giaITCDDoacaoBeneficiarioVo.setValorItcdBeneficiario(valorRecolherPorBeneficiario);
      }
      
      if(valorRecolherPorBeneficiario != 0.0){
         giaITCDDoacaoBeneficiarioVo.setValorItcdBeneficiario(valorRecolherPorBeneficiario);
         giaITCDDoacaoBeneficiarioVo.setValorRecolher(valorRecolherPorBeneficiario);
      }            
      
   }


   private void calculoEmCascataDoacaoSucessiva(final GIAITCDDoacaoVo giaITCDDoacaoVo, GIAITCDDoacaoBeneficiarioVo giaITCDDoacaoBeneficiarioVo)
   {
      AliquotaVo aliquotasInterVivos = new AliquotaVo();
      double valorUPF = giaITCDDoacaoVo.getValorUPF();
      double valorRecebido = giaITCDDoacaoBeneficiarioVo.getValorRecebido();
      double valorRecolherPorBeneficiario = 0;
      
      for(Iterator ite = giaITCDDoacaoVo.getParametroLegislacaoVo().getAliquotaVo().getCollVO().iterator(); ite.hasNext();)
      {
         AliquotaVo atual = (AliquotaVo) ite.next();
         if (atual.getTipoFatoGerador().is(DomnTipoGIA.INTER_VIVOS))
         {
            aliquotasInterVivos.getCollVO().add(atual);
         }
      }
      
      GIAITCDDoacaoBeneficiarioVo teste = (GIAITCDDoacaoBeneficiarioVo) giaITCDDoacaoVo.getBeneficiarioVo();      
                  
      GIAITCDDoacaoBeneficiarioVo giaItcdDoacaoBeneficiarioVoAux = new GIAITCDDoacaoBeneficiarioVo();
      GIAITCDDoacaoBeneficiarioAliquotaBe giaItcdDoacaoBeneficiarioAliquotaBe = null;
      
      if(giaITCDDoacaoBeneficiarioVo.getGiaItcdDoacaoBeneficiarioAliquotaVo() != null){
         giaITCDDoacaoBeneficiarioVo.getGiaItcdDoacaoBeneficiarioAliquotaVo().getCollVO().clear();
      }
      
      try{
         giaItcdDoacaoBeneficiarioAliquotaBe = new GIAITCDDoacaoBeneficiarioAliquotaBe();           
         giaItcdDoacaoBeneficiarioAliquotaBe.consultarGiaItcdAliquotaDoacaoSucessiva(giaITCDDoacaoBeneficiarioVo.getGiaitcdDoacaoSucessivaVo(), giaITCDDoacaoBeneficiarioVo.getPessoaBeneficiaria().getNumrContribuinte());           
         giaItcdDoacaoBeneficiarioAliquotaBe.consultaAliquotaDoacaoBeneficiarioGiaPermanente(giaITCDDoacaoBeneficiarioVo);
      }catch(Exception e){
         e.printStackTrace();				
      }finally{
         if(giaItcdDoacaoBeneficiarioAliquotaBe != null){
            giaItcdDoacaoBeneficiarioAliquotaBe.close();
         }
      }                  
      
      double valorRecebidoAux = giaITCDDoacaoBeneficiarioVo.getValorRecebido();
      if(giaITCDDoacaoBeneficiarioVo.getGiaItcdDoacaoBeneficiarioAliquotaVo().getCollVO().isEmpty()){
         for (Iterator it = aliquotasInterVivos.getCollVO().iterator(); it.hasNext();){
            AliquotaVo atual = (AliquotaVo) it.next();
            GIAITCDDoacaoBeneficiarioAliquotaVo giaItcdDoacaoBeneficiarioAliquotaVo = new GIAITCDDoacaoBeneficiarioAliquotaVo();
            giaItcdDoacaoBeneficiarioAliquotaVo.setCodigoAliquota(atual.getCodigo());
            giaItcdDoacaoBeneficiarioAliquotaVo.setPercentualAliquota(atual.getPercentualLegislacaoAliquota());
            double valorUPFFinal = (atual.getQuantidadeUPFFinal()) * valorUPF;
            double valorUPFInicial = (atual.getQuantidadeUPFInicial() - 1) * valorUPF;   
            
            double qtdUpfFaixa = ((atual.getQuantidadeUPFFinal() - atual.getQuantidadeUPFInicial() + 1));
            
            double valorFaixa = 0;                 
            
            if (valorUPFFinal != 0)
            {
               valorFaixa = (atual.getQuantidadeUPFFinal() - atual.getQuantidadeUPFInicial() + 1) * valorUPF;
            }
            else
            {
               valorFaixa = valorRecebido - valorUPFInicial;            
            }
   
            double qtdUpfUtilizadaParaFaixaDoacaoSucessiva = 0.00;
            for(Iterator iterator = giaITCDDoacaoBeneficiarioVo.getGiaitcdDoacaoSucessivaVo().getCollVO().iterator(); iterator.hasNext();){
               GIAITCDDoacaoSucessivaVo doacaoSucessiva = (GIAITCDDoacaoSucessivaVo) iterator.next(); 
               GIAITCDDoacaoBeneficiarioVo beneficiarioDoacao = (GIAITCDDoacaoBeneficiarioVo) doacaoSucessiva.getBeneficiarioVo();
               
               for(Iterator ite = beneficiarioDoacao.getGiaItcdDoacaoBeneficiarioAliquotaVo().getCollVO().iterator(); ite.hasNext();){
                  GIAITCDDoacaoBeneficiarioAliquotaVo aliquotaDoacaoSucessiva = (GIAITCDDoacaoBeneficiarioAliquotaVo) ite.next();
                  if(atual.getCodigo() == aliquotaDoacaoSucessiva.getCodigoAliquota()){
                     qtdUpfUtilizadaParaFaixaDoacaoSucessiva += aliquotaDoacaoSucessiva.getNumrUpfUtilizada();
                  }                  
               }            
            }
            
            if(atual.getQuantidadeUPFFinal() == 0){
               qtdUpfFaixa = 999999999.0;
            }
            
            double qtdUpfDisponivelParaFaixaDoacaoSucessiva = 0.00;
            if(qtdUpfUtilizadaParaFaixaDoacaoSucessiva <= atual.getQuantidadeUPFFinal() || atual.getQuantidadeUPFFinal() == 0.00){         
               qtdUpfDisponivelParaFaixaDoacaoSucessiva = qtdUpfFaixa - qtdUpfUtilizadaParaFaixaDoacaoSucessiva;
               
               if(qtdUpfDisponivelParaFaixaDoacaoSucessiva < 0){
                  qtdUpfDisponivelParaFaixaDoacaoSucessiva = 0.00;
               }
            }     
            
            double valorDisponivelFaixa = qtdUpfDisponivelParaFaixaDoacaoSucessiva * valorUPF;
                     
            if(valorDisponivelFaixa != 0.00){  
               if(valorRecebidoAux != 0.00){
                  if(valorRecebidoAux <= valorDisponivelFaixa){ 
                     giaItcdDoacaoBeneficiarioAliquotaVo.setNumrUpfUtilizada(valorRecebidoAux / valorUPF);                       
                     if(atual.getQuantidadeUPFFinal() != 0){
                        giaItcdDoacaoBeneficiarioAliquotaVo.setNumrUpfDisponivel(qtdUpfDisponivelParaFaixaDoacaoSucessiva - giaItcdDoacaoBeneficiarioAliquotaVo.getNumrUpfUtilizada());
                     }                  
                     giaItcdDoacaoBeneficiarioAliquotaVo.setValorBaseCalculo(valorRecebidoAux);              
                     giaItcdDoacaoBeneficiarioAliquotaVo.setValorRecolher(valorRecebidoAux * atual.getPercentualLegislacaoAliquota() / 100);               
                     valorRecebidoAux = 0.0;
                  }else{
                     valorRecebidoAux = (valorRecebidoAux - valorDisponivelFaixa);
                     giaItcdDoacaoBeneficiarioAliquotaVo.setNumrUpfUtilizada(qtdUpfFaixa);               
                     giaItcdDoacaoBeneficiarioAliquotaVo.setNumrUpfDisponivel(0.00);
                     giaItcdDoacaoBeneficiarioAliquotaVo.setValorBaseCalculo(valorDisponivelFaixa);              
                     giaItcdDoacaoBeneficiarioAliquotaVo.setValorRecolher(valorDisponivelFaixa * atual.getPercentualLegislacaoAliquota() / 100);               
                  }
               }else{
                  break;
               }
            }else{
               giaItcdDoacaoBeneficiarioAliquotaVo.setNumrUpfUtilizada(qtdUpfFaixa);               
               giaItcdDoacaoBeneficiarioAliquotaVo.setNumrUpfDisponivel(0.00);
               giaItcdDoacaoBeneficiarioAliquotaVo.setInfoUpfRefGiaAntr(1);
               giaItcdDoacaoBeneficiarioVoAux.getGiaItcdDoacaoBeneficiarioAliquotaVo().getCollVO().add(giaItcdDoacaoBeneficiarioAliquotaVo);
               valorRecolherPorBeneficiario += giaItcdDoacaoBeneficiarioAliquotaVo.getValorRecolher();   
               continue;
            }
                  
            giaItcdDoacaoBeneficiarioVoAux.getGiaItcdDoacaoBeneficiarioAliquotaVo().getCollVO().add(giaItcdDoacaoBeneficiarioAliquotaVo);
            valorRecolherPorBeneficiario += giaItcdDoacaoBeneficiarioAliquotaVo.getValorRecolher();         
         }         
         giaITCDDoacaoBeneficiarioVo.setGiaItcdDoacaoBeneficiarioAliquotaVo(giaItcdDoacaoBeneficiarioVoAux.getGiaItcdDoacaoBeneficiarioAliquotaVo());
         
         if(giaITCDDoacaoBeneficiarioVo.getValorItcdBeneficiario() == null){
            giaITCDDoacaoBeneficiarioVo.setValorItcdBeneficiario(valorRecolherPorBeneficiario);
         }
         
         if(valorRecolherPorBeneficiario != 0.0){
            giaITCDDoacaoBeneficiarioVo.setValorItcdBeneficiario(valorRecolherPorBeneficiario);
            giaITCDDoacaoBeneficiarioVo.setValorRecolher(valorRecolherPorBeneficiario);
         }         
      }
   }

   /**
    * 
    * 
    * 
    * 
    * @param gia
    */
   private void totalizaDemonstrativoCalculo(final GIAITCDDoacaoVo gia)
   {
      double valorTotalITCD = 0;
      double valorTotalRecolhimento = 0;
      double valorTotalMulta= 0;
      
      for(Iterator it = gia.getBeneficiarioVo().getCollVO().iterator(); it.hasNext();)
      {
         GIAITCDDoacaoBeneficiarioVo atual = (GIAITCDDoacaoBeneficiarioVo) it.next();
         valorTotalITCD += atual.getValorItcdBeneficiario();
         valorTotalRecolhimento += atual.getValorRecolher();
         //valorTotalMulta += atual.getValorMultaRecolher();
      }
      gia.setValorITCD(valorTotalITCD);
      //gia.setValorMulta(valorTotalMulta);      
      gia.setValorRecolhimento(valorTotalRecolhimento);
   }

	/**
	 * @param giaITCDDoacaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void incluir(final GIAITCDDoacaoVo giaITCDDoacaoVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(giaITCDDoacaoVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).insert(giaITCDDoacaoVo);
	}

	/**
	 * @param giaITCDDoacaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void alterar(final GIAITCDDoacaoVo giaITCDDoacaoVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(giaITCDDoacaoVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).update(giaITCDDoacaoVo);
	}	

	/**
	 * Método que insere uma GIA ITCD Doação no banco de dados
	 * 
	 * @param giaITCDDoacaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws RegistroExistenteException
	 * @throws ConsultaException
	 * @throws ParametroObrigatorioException
	 * @throws IntegracaoException
	 * @throws ConexaoException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @implemented by Daniel Balieiro
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void incluirGIAITCDDoacao(final GIAITCDDoacaoVo giaITCDDoacaoVo) throws ObjetoObrigatorioException, 
			  RegistroExistenteException, ConsultaException, ParametroObrigatorioException, IntegracaoException, 
			  ConexaoException, LogException, PersistenciaException, AnotacaoException
	{
		Validador.validaObjeto(giaITCDDoacaoVo);
		try
		{
			try
			{
				validarIncluirGIAITCDDoacao(giaITCDDoacaoVo);
				synchronized (GIAITCDDoacaoVo.class)
				{
					incluir(giaITCDDoacaoVo);
					for (Iterator iteBenef = giaITCDDoacaoVo.getBeneficiarioVo().getCollVO().iterator(); iteBenef.hasNext(); )
					{
						GIAITCDDoacaoBeneficiarioVo giaDoacao = (GIAITCDDoacaoBeneficiarioVo) iteBenef.next();
						giaDoacao.setLogSefazVo(giaITCDDoacaoVo.getLogSefazVo());
						(new GIAITCDDoacaoBeneficiarioBe(conn)).incluirGIAITCDDoacaoBeneficiario(giaDoacao);
					}               				   
				}
			}
			catch (RegistroExistenteException e)
			{
				conn.rollback();
				throw e;
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
			catch (ObjetoObrigatorioException e)
			{
				conn.rollback();
				throw e;
			}
			catch (IntegracaoException e)
			{
				conn.rollback();
				throw e;
			}
			catch (ConexaoException e)
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
	 * Método que altera uma GIA ITCD Doação do banco de dados
	 * 
	 * @implemented by Daniel Balieiro
	 */
	public synchronized void alterarGIAITCDDoacao(final GIAITCDDoacaoVo giaITCDDoacaoVo) throws ObjetoObrigatorioException, 
			  RegistroExistenteException, ConsultaException, ParametroObrigatorioException, IntegracaoException, 
			  ConexaoException, LogException, PersistenciaException, AnotacaoException
	{
		Validador.validaObjeto(giaITCDDoacaoVo);
		try
		{
			try
			{
				validarIncluirGIAITCDDoacao(giaITCDDoacaoVo);
				synchronized (GIAITCDDoacaoVo.class)
				{
					giaITCDDoacaoVo.setDataAtualizacaoBD(new Date());
					alterar(giaITCDDoacaoVo);
					
					//alterando beneficiários
					GIAITCDDoacaoBeneficiarioVo beneficiariosDoacao = new GIAITCDDoacaoBeneficiarioVo();
				   beneficiariosDoacao.setCollVO(giaITCDDoacaoVo.getBeneficiarioVo().getCollVO());
					beneficiariosDoacao.setGiaITCDVo(giaITCDDoacaoVo);		
					beneficiariosDoacao.setLogSefazVo(giaITCDDoacaoVo.getLogSefazVo());
					new GIAITCDDoacaoBeneficiarioBe(conn).alterarGIAITCDDoacaoBeneficiarioAlterarGiaDoacao( beneficiariosDoacao );
				}
			}
			catch (RegistroExistenteException e)
			{
				conn.rollback();
				throw e;
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
			catch (ObjetoObrigatorioException e)
			{
				conn.rollback();
				throw e;
			}
			catch (IntegracaoException e)
			{
				conn.rollback();
				throw e;
			}
			catch(ConexaoException e)
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
	 * Método para consultar uma GIA ITCD Doaï¿½ï¿½o
	 * 
	 * @param gIAITCDDoacaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @return GIAITCDDoacaoVo
	 * @implemented by Daniel Balieiro
	 */
	public GIAITCDDoacaoVo consultarGIAITCDDoacao(final GIAITCDDoacaoVo gIAITCDDoacaoVo) throws ObjetoObrigatorioException, 
			  ConsultaException, IntegracaoException, SQLException
   {
		Validador.validaObjeto(gIAITCDDoacaoVo);
		(new GIAITCDDoacaoQDao(conn)).findGIAITCDDoacao(gIAITCDDoacaoVo);	   
		if (gIAITCDDoacaoVo.isConsultaCompleta())
		{
			BeneficiarioVo beneficiarioConsulta = new BeneficiarioVo();
			beneficiarioConsulta.setGiaITCDVo(new GIAITCDVo(gIAITCDDoacaoVo.getCodigo()));
			beneficiarioConsulta = new BeneficiarioVo(beneficiarioConsulta);
			new BeneficiarioBe(conn).listaBeneficiario(beneficiarioConsulta);
			for (Iterator iteBeneficiario = beneficiarioConsulta.getCollVO().iterator(); iteBeneficiario.hasNext(); )
			{
				BeneficiarioVo beneficiarioAtual = (BeneficiarioVo) iteBeneficiario.next();
				GIAITCDDoacaoBeneficiarioVo beneficiarioDoacaoConsulta = new GIAITCDDoacaoBeneficiarioVo(beneficiarioAtual.getCodigo());
				beneficiarioDoacaoConsulta = new GIAITCDDoacaoBeneficiarioVo(beneficiarioDoacaoConsulta);
				beneficiarioDoacaoConsulta.setConsultaCompleta(true);
				beneficiarioConsulta =(new GIAITCDDoacaoBeneficiarioBe(conn)).consultaGIAITCDDoacaoBeneficiario(beneficiarioDoacaoConsulta);
				
            GIAITCDDoacaoSucessivaVo doacaoSucessivaVo = new GIAITCDDoacaoSucessivaVo();
            doacaoSucessivaVo.setBeneficiarioVo(beneficiarioConsulta);            
            doacaoSucessivaVo = new GIAITCDDoacaoSucessivaBe(conn).listaDoacoesSucessivas(doacaoSucessivaVo);            
            beneficiarioDoacaoConsulta.setGiaitcdDoacaoSucessivaVo(doacaoSucessivaVo);
            
            gIAITCDDoacaoVo.getBeneficiarioVo().getCollVO().add(beneficiarioDoacaoConsulta);
			              
			 }	     
		}
		return gIAITCDDoacaoVo;
	}

	public void atribuirMunicipioProtocolarResponsavel(final GIAITCDDoacaoVo gIAITCDDoacaoVo) throws ObjetoObrigatorioException, 
			  IntegracaoException
	{
	   Validador.validaObjeto(gIAITCDDoacaoVo);
		if(!Validador.isNumericoValido(gIAITCDDoacaoVo.getProcuradorVo().getNumrContribuinte()))
		{
			gIAITCDDoacaoVo.setMunicipioProtocolar((new CadastroBe(conn)).obterMunicipioPorCep(new CepIntegracaoVo(new Integer(gIAITCDDoacaoVo.getResponsavelVo().getEnderecoCEP().intValue()))));	
		}
	}
	
	public void atribuirMunicipioProtocolarProcurador(final GIAITCDDoacaoVo gIAITCDDoacaoVo) throws ObjetoObrigatorioException, 
			  IntegracaoException
	{
		Validador.validaObjeto(gIAITCDDoacaoVo);
		gIAITCDDoacaoVo.setMunicipioProtocolar((new CadastroBe(conn)).obterMunicipioPorCep(new CepIntegracaoVo(new Integer(gIAITCDDoacaoVo.getProcuradorVo().getEnderecoCEP().intValue()))));
	}	

	public static void atualizaAbaBeneficiarios(final GIAITCDDoacaoVo giaITCDDoacaoVo) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(giaITCDDoacaoVo);
		calculaBaseCalculoTributavel(giaITCDDoacaoVo);
	}
	
	public static void atualizaAbaBensTributaveis(final GIAITCDDoacaoVo giaITCDDoacaoVo) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(giaITCDDoacaoVo);
		
	}
	
	public static void atualizaAbasGiaDoacao(final GIAITCDDoacaoVo giaITCDDoacaoVo) throws ObjetoObrigatorioException
	{
	   Validador.validaObjeto(giaITCDDoacaoVo);
	   //atualizaAbaBensTributaveis(giaITCDDoacaoVo);
	    atualizaAbaBeneficiarios(giaITCDDoacaoVo);
	}

	private static void calculaBaseCalculoTributavel(final GIAITCDDoacaoVo giaITCDDoacaoVo) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(giaITCDDoacaoVo);
		double valorTotalBens = 0;
		double valorBensArbitrado = 0;
		double valorBemConcordado = 0;
		double valorPercentual = 0;
		int quantidadeBemConcordado = 0;
		if (Validador.isObjetoValido(giaITCDDoacaoVo.getBemTributavelVo()) && Validador.isCollectionValida(giaITCDDoacaoVo.getBemTributavelVo().getCollVO()))
		{
			if (giaITCDDoacaoVo.getNumeroVersaoGIAITCD().is(DomnVersaoGIAITCD.VERSAO_1_3))
			{
				for (Iterator iteBem = giaITCDDoacaoVo.getBemTributavelVo().getCollVO().iterator(); iteBem.hasNext(); )
				{
					BemTributavelVo bem = (BemTributavelVo) iteBem.next();
					valorBensArbitrado += getValorBemTributavel(bem);
					if (bem.getConcordaComValorArbitrado().is(DomnSimNao.SIM))
					{
						++quantidadeBemConcordado;
						valorBemConcordado += getValorBemTributavel(bem);
					}
					else
					{
						valorBemConcordado += getValorBemInformado(bem);
					}
				}
				//TODO Versão 1.3 
				if (quantidadeBemConcordado == giaITCDDoacaoVo.getBemTributavelVo().getCollVO().size())
				{
					giaITCDDoacaoVo.setProtocoAutomatico(Boolean.TRUE);
					giaITCDDoacaoVo.setTipoProtocoloGIA(new DomnTipoProtocolo(DomnTipoProtocolo.PROTOCOLO_AUTOMATICO));
					valorTotalBens = valorBensArbitrado;
				}
				else
				{
					valorPercentual = (valorBemConcordado / valorBensArbitrado) * 100;
					if (valorPercentual >= giaITCDDoacaoVo.getPorcentagemAconsiderar())
					{
						giaITCDDoacaoVo.setProtocoAutomatico(Boolean.TRUE);
						giaITCDDoacaoVo.setTipoProtocoloGIA(new DomnTipoProtocolo(DomnTipoProtocolo.PROTOCOLO_AUTOMATICO));
						valorTotalBens = valorBemConcordado;
					}
					else
					{
						giaITCDDoacaoVo.setProtocoAutomatico(Boolean.FALSE);
						giaITCDDoacaoVo.setTipoProtocoloGIA(new DomnTipoProtocolo(DomnTipoProtocolo.PROTOCOLO_MANUAL));
						valorTotalBens = valorBensArbitrado;
					}
				}
			}
			else
			{
				for (Iterator iteBem = giaITCDDoacaoVo.getBemTributavelVo().getCollVO().iterator(); iteBem.hasNext(); )
				{
					BemTributavelVo bem = (BemTributavelVo) iteBem.next();
					valorTotalBens += getValorBemTributavel(bem);
				}
			}
			giaITCDDoacaoVo.setValorBaseCalculoTributavel(valorTotalBens * (giaITCDDoacaoVo.getFracaoIdeal() / 100));
			giaITCDDoacaoVo.setValorTotalBensDeclarados(valorTotalBens);
		}
	}

	private static double getValorBemTributavel(final BemTributavelVo bemTributavelVo) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(bemTributavelVo);
		if (Validador.isNumericoValido(bemTributavelVo.getAvaliacaoBemTributavelVo().getCodigo()) && bemTributavelVo.getAvaliacaoBemTributavelVo().getIsento().is(DomnSimNao.NAO))
		{
			return  bemTributavelVo.getAvaliacaoBemTributavelVo().getValorAvaliacao();
		}
		else
		{
			return  bemTributavelVo.getValorMercado();
		}
	}
	
	private static double getValorBemInformado(final BemTributavelVo bemTributavelVo) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(bemTributavelVo);
		if (Validador.isNumericoValido(bemTributavelVo.getAvaliacaoBemTributavelVo().getCodigo()) && bemTributavelVo.getAvaliacaoBemTributavelVo().getIsento().is(DomnSimNao.NAO))
		{
			return  bemTributavelVo.getAvaliacaoBemTributavelVo().getValorAvaliacao();
		}
		else
		{
			return  bemTributavelVo.getValorInformadoContribuinte();
		}
	}

	public static void validarIncluirServidorAvaliacao(final GIAITCDDoacaoVo giaITCDDoacaoVo, final ServidorSefazIntegracaoVo servidorVo) throws ObjetoObrigatorioException, 
			  RegistroNaoPodeSerUtilizadoException
	{
		Validador.validaObjeto(giaITCDDoacaoVo);
		Validador.validaObjeto(servidorVo);
		
		if(servidorVo.getNumrCPF().toString().equals(giaITCDDoacaoVo.getResponsavelVo().getNumrDocumento()))
		{
		   throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.SERVIDOR_PARTICIPANTE_NAO_PODE_SER_AVALIADOR);
		}
	   if(servidorVo.getNumrCPF().toString().equals(giaITCDDoacaoVo.getProcuradorVo().getNumrDocumento()))
	   {
	      throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.SERVIDOR_PARTICIPANTE_NAO_PODE_SER_AVALIADOR);
	   }
		for(Iterator it = giaITCDDoacaoVo.getBeneficiarioVo().getCollVO().iterator(); it.hasNext(); )
		{
			BeneficiarioVo beneficiarioAtual = (BeneficiarioVo) it.next();
		   if(servidorVo.getNumrCPF().toString().equals(beneficiarioAtual.getPessoaBeneficiaria().getNumrDocumento()))
		   {
		      throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.SERVIDOR_PARTICIPANTE_NAO_PODE_SER_AVALIADOR);
		   }			
		}
	}

	public void atribuirProcurador(GIAITCDDoacaoVo giaITCDDoacaoVo, ContribuinteIntegracaoVo procuradorVo) throws ObjetoObrigatorioException, 
			  RegistroNaoPodeSerUtilizadoException, DadoNecessarioInexistenteException
	{
		Validador.validaObjeto(giaITCDDoacaoVo);
		Validador.validaObjeto(procuradorVo);
		validaProcurador(procuradorVo, giaITCDDoacaoVo);
		if(Validador.isNumericoValido(procuradorVo.getNumrContribuinte()))
		{
			validaCEPContribuinte(procuradorVo);
		}
		giaITCDDoacaoVo.setProcuradorVo(procuradorVo);
	}

	public void atribuirDeclarante(GIAITCDDoacaoVo giaITCDDoacaoVo, ContribuinteIntegracaoVo declaranteVo) throws ObjetoObrigatorioException, 
			  DadoNecessarioInexistenteException, RegistroNaoPodeSerUtilizadoException
	{
	   Validador.validaObjeto(giaITCDDoacaoVo);
	   Validador.validaObjeto(declaranteVo);
		validaDeclarante(declaranteVo, giaITCDDoacaoVo);
		if(Validador.isNumericoValido(declaranteVo.getNumrContribuinte()))
		{
			validaCEPContribuinte(declaranteVo);
		}
	   giaITCDDoacaoVo.setResponsavelVo(declaranteVo);
	}
	
	public void atribuirBeneficiario(GIAITCDDoacaoVo giaITCDDoacaoVo, ContribuinteIntegracaoVo beneficiarioVo) throws ObjetoObrigatorioException, 
			  DadoNecessarioInexistenteException, RegistroExistenteException, RegistroNaoPodeSerUtilizadoException
	{
	   Validador.validaObjeto(giaITCDDoacaoVo);
	   Validador.validaObjeto(beneficiarioVo);
		if(Validador.isNumericoValido(beneficiarioVo.getNumrContribuinte()))
		{
			validaCEPContribuinte(beneficiarioVo);
		}
		validaBeneficiario(beneficiarioVo, giaITCDDoacaoVo);
		giaITCDDoacaoVo.getBeneficiarioVo().setPessoaBeneficiaria(beneficiarioVo);
		GIAITCDBe.validaBeneficiarioDuplicado(giaITCDDoacaoVo);
	
	}

	private void validaProcurador(ContribuinteIntegracaoVo procuradorVo, GIAITCDDoacaoVo giaITCDDoacaoVo) throws ObjetoObrigatorioException, 
			  RegistroNaoPodeSerUtilizadoException
	{
	   Validador.validaObjeto(giaITCDDoacaoVo);
	   Validador.validaObjeto(procuradorVo);
		if(procuradorVo.getNumrDocumento().equals(giaITCDDoacaoVo.getResponsavelVo().getNumrDocumento()))	
		{
			throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.GIA_ITCD_DOACAO_VALIDAR_CPF_PROCURADOR_CPF_DECLARANTE);
		}
		verificaDocumentoBeneficiarios(procuradorVo, giaITCDDoacaoVo, ConfiguracaoITCD.PROCURADOR);		
	}

	private void validaDeclarante(ContribuinteIntegracaoVo declaranteVo, GIAITCDDoacaoVo giaITCDDoacaoVo) throws ObjetoObrigatorioException, 
			  RegistroNaoPodeSerUtilizadoException
	{
	   Validador.validaObjeto(giaITCDDoacaoVo);
	   Validador.validaObjeto(declaranteVo);	
	   if(declaranteVo.getNumrDocumento().equals(giaITCDDoacaoVo.getProcuradorVo().getNumrDocumento()))  
	   {
	      throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.GIA_ITCD_DOACAO_VALIDAR_CPF_DECLARANTE_CPF_PROCURADOR);
	   }
		verificaDocumentoBeneficiarios(declaranteVo, giaITCDDoacaoVo, ConfiguracaoITCD.DECLARANTE);
	}
	

	/**
	 * Método responsável por verificar se o contribuinte informado possui CEP.
	 * @param contribuinteIntegracaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws DadoNecessarioInexistenteException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public static void validaCEPContribuinte(ContribuinteIntegracaoVo contribuinteIntegracaoVo) throws ObjetoObrigatorioException, 
			  DadoNecessarioInexistenteException
	{
		Validador.validaObjeto(contribuinteIntegracaoVo);
		if(!Validador.isNumericoValido(contribuinteIntegracaoVo.getEnderecoCEP()))
		{        
			throw new DadoNecessarioInexistenteException(MensagemErro.DADOS_CADASTRAIS_INVENTARIANTE_DESATUALIZADO);
		}     
	}

	private void verificaDocumentoBeneficiarios(ContribuinteIntegracaoVo contribuinte, GIAITCDDoacaoVo giaITCDDoacaoVo, String tipoContribuinte) throws ObjetoObrigatorioException, 
			  RegistroNaoPodeSerUtilizadoException
	{
		Validador.validaObjeto(contribuinte);
		Validador.validaObjeto(giaITCDDoacaoVo);
		if(Validador.isCollectionValida(giaITCDDoacaoVo.getBeneficiarioVo().getCollVO()))
		{
			for(Iterator it = giaITCDDoacaoVo.getBeneficiarioVo().getCollVO().iterator(); it.hasNext();  )
			{
				BeneficiarioVo atual = (BeneficiarioVo) it.next();
				if(atual.getPessoaBeneficiaria().getNumrDocumento().equals(contribuinte.getNumrDocumento()))
				{
					if(tipoContribuinte.equals(ConfiguracaoITCD.DECLARANTE))
					{
						throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.GIA_ITCD_DOACAO_VALIDAR_CPF_DECLARANTE_CPF_BENEFICIARIO);	
					}
					else if(tipoContribuinte.equals(ConfiguracaoITCD.PROCURADOR))
					{
					   throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.GIA_ITCD_DOACAO_VALIDAR_CPF_PROCURADOR_CPF_BENEFICIARIO);  
					}				
				}
			}
		}
	}

	private void validaBeneficiario(ContribuinteIntegracaoVo beneficiarioVo, GIAITCDDoacaoVo giaITCDDoacaoVo) throws ObjetoObrigatorioException, 
			  RegistroNaoPodeSerUtilizadoException
	{
	   Validador.validaObjeto(giaITCDDoacaoVo);
	   Validador.validaObjeto(beneficiarioVo);
		if(beneficiarioVo.getNumrDocumento().equals(giaITCDDoacaoVo.getResponsavelVo().getNumrDocumento()))
		{
		   throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.GIA_ITCD_DOACAO_VALIDAR_CPF_BENEFICIARIO_CPF_DECLARANTE);
		}
		if(beneficiarioVo.getNumrDocumento().equals(giaITCDDoacaoVo.getProcuradorVo().getNumrDocumento()))
		{
			throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.GIA_ITCD_DOACAO_VALIDAR_CPF_BENEFICIARIO_CPF_PROCURADOR);
		}
	
	}
   
   public void atualizarValorRecebidoAposAvaliacao(final GIAITCDDoacaoVo giaITCDDoacaoVo)
   {
      List<BeneficiarioVo> beneficiarios = (List<BeneficiarioVo>) giaITCDDoacaoVo.getBeneficiarioVo().getCollVO();
      BeneficiarioBe beneficiarioBe = new BeneficiarioBe(conn);
      for (BeneficiarioVo beneficiarioVo: beneficiarios)
      {
         try
         {
            beneficiarioVo.setLogSefazVo(giaITCDDoacaoVo.getLogSefazVo());
            beneficiarioVo.setValorRecebidoAvaliacao( beneficiarioVo.getValorRecebido()  );
            beneficiarioVo.setValorRecebido( beneficiarioVo.getValorRecebidoAuxiliar() );
            beneficiarioBe.alterarBeneficiario(beneficiarioVo);
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
      }
   }	
}
