package br.gov.mt.sefaz.itc.model.tabelabasica.parametrosdalegislacao;

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
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoGIA;
import br.gov.mt.sefaz.itc.util.facade.ConfiguracaoITCD;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.MensagemSucesso;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


/**
 * Classe responsável por implementar a regra de negócio da entidade "ParametroLegislacao".
 * @author Daniel Balieiro
 * @version $Revision: 1.2 $
 */
public class ParametroLegislacaoBe extends AbstractBe
{

	/**
	 * Construtor da classe.
	 * Este construtor não abre uma conexão com o banco de dados, mas utiliza a conexão recebida como parâmetro
	 * para realizar suas validações.
	 * @param conexao Conexão com o Banco de Dados
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	public ParametroLegislacaoBe(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Construtor padrão da classe.
	 * Este construtor abre uma conexão com o banco de dados do ITC para realizar suas validações.
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	public ParametroLegislacaoBe() throws SQLException
	{
		super();
	}

	/**
	 * Método utilizado para Validar as informações que serão inseridas no Banco de Dados
	 * @param aliquotaVo AliquotaVo que será validado
	 * @param parametroLegislacaoVo Parametro Legislação que contém essa Aliquota
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @throws SQLException
	 * @throws ConsultaException
	 * @throws RegistroExistenteException
	 * @implemented by Daniel Balieiro
	 */
	public void validaParametrosIncluirAliquota(final AliquotaVo aliquotaVo, final ParametroLegislacaoVo parametroLegislacaoVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException, ConsultaException, RegistroExistenteException
	{
		Validador.validaObjeto(aliquotaVo);
		// validacoes de TIPO ISENCAO e PERCENTUAL DE ALIQUOTA
		if (!Validador.isDominioNumericoValido(aliquotaVo.getTipoIsencao()))
		{
			// se PERCENTUAL DE ALIQUOTA é valido
			if (aliquotaVo.getPercentualLegislacaoAliquota() > 100 || aliquotaVo.getPercentualLegislacaoAliquota() < 0)
			{
				throw new ParametroObrigatorioException(MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_PERCENTUAL_ALIQUOTA_INVALIDO);
			}
			else if (!Validador.isNumericoValido(aliquotaVo.getPercentualLegislacaoAliquota()))
			{
				throw new ParametroObrigatorioException(MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_ALIQUOTA);
			}
		}
		// se TIPO FATO GERADOR é valido
		if (!Validador.isDominioNumericoValido(aliquotaVo.getTipoFatoGerador()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_FATO_GERADOR);
		}
		else if (Validador.isNumericoValido(aliquotaVo.getPercentualLegislacaoAliquota()) || 
				 Validador.isDominioNumericoValido(aliquotaVo.getTipoIsencao()))
		{
			for (Iterator iteAliquota = 
							parametroLegislacaoVo.getAliquotaVo().getCollVO().iterator(); iteAliquota.hasNext(); )
			{
				AliquotaVo aliquotaAtualVo = (AliquotaVo) iteAliquota.next();
				// valida se o PERCENTUAL DE ALIQUOTA ja esta cadastrado
				if (aliquotaAtualVo != aliquotaVo && 
							  aliquotaVo.getPercentualLegislacaoAliquota() == aliquotaAtualVo.getPercentualLegislacaoAliquota() && 
							  aliquotaVo.getTipoFatoGerador().getValorCorrente() == 
							  aliquotaAtualVo.getTipoFatoGerador().getValorCorrente())
				{
					throw new RegistroExistenteException(MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_PERCENTUAL_ALIQUOTA_JA_CADASTRADO);
				}
			}
		}
		// validacoes sobre a 	QUANTIDADE UPF INICIAL
		if (aliquotaVo.getQuantidadeUPFInicial() != 0 && Validador.isNumericoValido(aliquotaVo.getQuantidadeUPFFinal()) && 
				 aliquotaVo.getQuantidadeUPFInicial() > aliquotaVo.getQuantidadeUPFFinal())
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_UPF_INICIAL_MENOR_UPF_FINAL);
		}
		else if (aliquotaVo.getQuantidadeUPFInicial() < 0)
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_UPF_INICIAL_MENOR_ZERO);
		}
		else if (!Validador.isNumericoValido(aliquotaVo.getQuantidadeUPFInicial()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_UPF_INICIAL);
		}
		else
		{
			for (Iterator iteAliquota = 
							parametroLegislacaoVo.getAliquotaVo().getCollVO().iterator(); iteAliquota.hasNext(); )
			{
				AliquotaVo aliquotaAtualVo = (AliquotaVo) iteAliquota.next();
				if (aliquotaAtualVo.getQuantidadeUPFFinal() == 0 && aliquotaVo != aliquotaAtualVo && 
							  aliquotaVo.getTipoFatoGerador().equals(aliquotaAtualVo.getTipoFatoGerador()) && 
							  aliquotaVo.getQuantidadeUPFInicial() >= aliquotaAtualVo.getQuantidadeUPFInicial())
				{
					throw new RegistroExistenteException(MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_UPF_INICIAL_JA_CADASTRADO_FAIXA);
				}
				else if (aliquotaVo != aliquotaAtualVo && 
							  aliquotaVo.getTipoFatoGerador().equals(aliquotaAtualVo.getTipoFatoGerador()) && 
							  aliquotaVo.getQuantidadeUPFInicial() >= aliquotaAtualVo.getQuantidadeUPFInicial() && 
							  aliquotaVo.getQuantidadeUPFInicial() <= aliquotaAtualVo.getQuantidadeUPFFinal())
				{
					throw new RegistroExistenteException(MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_UPF_INICIAL_JA_CADASTRADO_FAIXA);
				}
			}
		}
		if (aliquotaVo.getQuantidadeUPFFinal() == 0)
		{
			List listAliquotaVo = new LinkedList(parametroLegislacaoVo.getAliquotaVo().getCollVO());
			List listAliquotaGerador = new LinkedList();
			Collections.sort(listAliquotaVo);
			// filtrando manualmente as aliquotas do mesmo fato gerador da aliquota passada
			for (Iterator iteAliquotas = listAliquotaVo.iterator(); iteAliquotas.hasNext(); )
			{
				AliquotaVo aliquotaAtualVo = (AliquotaVo) iteAliquotas.next();
				if (aliquotaAtualVo.getTipoFatoGerador().equals(aliquotaVo.getTipoFatoGerador()))
				{
					listAliquotaGerador.add(aliquotaAtualVo);
				}
			}
			if (listAliquotaGerador.size() > 0)
			{
				for (Iterator iteAliquotas = listAliquotaGerador.iterator(); iteAliquotas.hasNext(); )
				{
					AliquotaVo aliquotaAtualVo = (AliquotaVo) iteAliquotas.next();
					if (iteAliquotas.hasNext() && (aliquotaAtualVo.getQuantidadeUPFFinal() == 0))
					{
						throw new ParametroObrigatorioException(MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_UPF_FINAL_AINDA_ABERTA);
					}
				}
			}
		}
		if (Validador.isNumericoValido(aliquotaVo.getQuantidadeUPFInicial()) && 
				 Validador.isNumericoValido(aliquotaVo.getQuantidadeUPFFinal()) && 
				 aliquotaVo.getQuantidadeUPFFinal() < aliquotaVo.getQuantidadeUPFInicial())
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_UPF_FINAL_MAIOR_UPF_INICIAL);
		}
		if (Validador.isNumericoValido(aliquotaVo.getQuantidadeUPFFinal()) && aliquotaVo.getQuantidadeUPFFinal() < 0)
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_UPF_FINAL_MENOR_ZERO);
		}
		if (Validador.isNumericoValido(aliquotaVo.getQuantidadeUPFFinal()))
		{
			for (Iterator iteAliquota = 
							parametroLegislacaoVo.getAliquotaVo().getCollVO().iterator(); iteAliquota.hasNext(); )
			{
				AliquotaVo aliquotaAtualVo = (AliquotaVo) iteAliquota.next();
				if (aliquotaVo != aliquotaAtualVo && 
							  aliquotaVo.getTipoFatoGerador().equals(aliquotaAtualVo.getTipoFatoGerador()) && 
							  aliquotaVo.getQuantidadeUPFFinal() >= aliquotaAtualVo.getQuantidadeUPFInicial() && 
							  aliquotaVo.getQuantidadeUPFFinal() <= aliquotaAtualVo.getQuantidadeUPFFinal())
				{
					throw new RegistroExistenteException(MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_UPF_FINAL_JA_CADASTRADO_FAIXA);
				}
			}
		}
	}

	/**
	 * Método utilizado para Validar as informações que serão inseridas no Banco de Dados
	 * @param multaVo MultaVo que será validado
	 * @param parametroLegislacaoVo Parametro Legislação que contém essa Multa
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @throws RegistroExistenteException
	 * @throws ConsultaException
	 * @implemented by Daniel Balieiro
	 */
	public void validaParametrosIncluirMulta(final MultaVo multaVo, final ParametroLegislacaoVo parametroLegislacaoVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException, RegistroExistenteException, ConsultaException
	{
		Validador.validaObjeto(multaVo);
		if (multaVo.getQuantidadeDiasInicial() < 0)
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_DIAS_INICIAL_MENOR_ZERO);
		}
		else if (!Validador.isNumericoValido(multaVo.getQuantidadeDiasInicial()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_DIAS_INICIAL);
		}
		else if (Validador.isNumericoValido(multaVo.getQuantidadeDiasFinal()) && 
				 multaVo.getQuantidadeDiasInicial() >= multaVo.getQuantidadeDiasFinal())
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_DIAS_INICIAL_MENOR_DIAS_FINAL);
		}
		else
		{
			for (Iterator iteMulta = parametroLegislacaoVo.getMultaVo().getCollVO().iterator(); iteMulta.hasNext(); )
			{
				MultaVo multaAtualVo = (MultaVo) iteMulta.next();
				if (multaVo != multaAtualVo && multaVo.getQuantidadeDiasInicial() >= multaAtualVo.getQuantidadeDiasInicial() && 
							  multaVo.getQuantidadeDiasInicial() <= multaAtualVo.getQuantidadeDiasFinal())
				{
					throw new RegistroExistenteException(MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_DIAS_INICIAL_JA_CADASTRADA);
				}
			}
		}
		LinkedList listMultaVo = new LinkedList(parametroLegislacaoVo.getMultaVo().getCollVO());
		listMultaVo.add(multaVo);
		Collections.sort(listMultaVo);
		int controle = 0;
		for (Iterator iteMultas = listMultaVo.iterator(); iteMultas.hasNext(); )
		{
			MultaVo multaAtualVo = (MultaVo) iteMultas.next();
			if (iteMultas.hasNext() && (multaAtualVo.getQuantidadeDiasFinal() == 0))
			{
				controle++;
			}
			if (controle > 1)
			{
				throw new ParametroObrigatorioException(MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_DIAS_FINAL_EM_ABERTO);
			}
		}
		if (Validador.isNumericoValido(multaVo.getQuantidadeDiasFinal()))
		{
			if (multaVo.getQuantidadeDiasFinal() < 0)
			{
				throw new ParametroObrigatorioException(MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_DIAS_FINAL_MENOR_ZERO);
			}
			else if (Validador.isNumericoValido(multaVo.getQuantidadeDiasInicial()) && 
						multaVo.getQuantidadeDiasFinal() <= multaVo.getQuantidadeDiasInicial())
			{
				throw new ParametroObrigatorioException(MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_DIAS_FINAL_MAIOR_DIAS_INICIAL);
			}
			else
			{
				for (Iterator iteMulta = parametroLegislacaoVo.getMultaVo().getCollVO().iterator(); iteMulta.hasNext(); )
				{
					MultaVo multaConsultaVo = (MultaVo) iteMulta.next();
					if (multaVo != multaConsultaVo && 
									 multaVo.getQuantidadeDiasFinal() >= multaConsultaVo.getQuantidadeDiasInicial() && 
									 multaVo.getQuantidadeDiasFinal() <= multaConsultaVo.getQuantidadeDiasFinal())
					{
						throw new RegistroExistenteException(MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_DIAS_FINAL_JA_CADASTRADA);
					}
				}
			}
		}
		else
		{
			LinkedList listaMulta = new LinkedList(parametroLegislacaoVo.getMultaVo().getCollVO());
			listaMulta.add(multaVo);
			controle = 0;
			for (Iterator iteMulta = listaMulta.iterator(); iteMulta.hasNext(); )
			{
				MultaVo multaConsultaVo = (MultaVo) iteMulta.next();
				//if(iteMulta.hasNext() && !Validador.isNumericoValido(multaConsultaVo.getQuantidadeDiasFinal()))
				if (iteMulta.hasNext() && (multaConsultaVo.getQuantidadeDiasFinal() == 0))
				{
					controle++;
				}
				if (controle > 1)
				{
					throw new RegistroExistenteException(MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_DIAS_FINAL_EM_ABERTO);
				}
			}
		}
		/*		if (!Validador.isNumericoValido(multaVo.getPercentualMulta()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_PERCENTUAL_MULTA);
		}
		else*/
		if (multaVo.getPercentualMulta() < 0 || multaVo.getPercentualMulta() > 100)
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_PERCENTUAL_MULTA_INVALIDO);
		}
		else
		{
			for (Iterator iteMulta = parametroLegislacaoVo.getMultaVo().getCollVO().iterator(); iteMulta.hasNext(); )
			{
				MultaVo multaConsultaVo = (MultaVo) iteMulta.next();
				if (multaVo != multaConsultaVo && multaVo.getPercentualMulta() == multaConsultaVo.getPercentualMulta())
				{
					throw new RegistroExistenteException(MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_PERCENTUAL_MULTA_JA_CADASTRADO);
				}
			}
		}
	}

	/**
	 * Método para validar a faixa de UPF das Aliquotas, tipo Causa Mortis, de um Parametro Legislação
	 * @param parametroLegislacaoVo
	 * @throws ParametroObrigatorioException
	 * @implemented by Daniel Balieiro
	 */
	public void validaParametrosIncluirAliquotaFaixaUPFCausaMortis(final ParametroLegislacaoVo parametroLegislacaoVo) throws ParametroObrigatorioException
	{
		int faixaUpf = 1;
		if (Validador.isCollectionValida(parametroLegislacaoVo.getAliquotaVo().getCollVO()))
		{
			List listAliquotaVo = new LinkedList(parametroLegislacaoVo.getAliquotaVo().getCollVO());
			Collections.sort(listAliquotaVo);
			for (Iterator iteAliquotas = listAliquotaVo.iterator(); iteAliquotas.hasNext(); )
			{
				AliquotaVo aliquotaVo = (AliquotaVo) iteAliquotas.next();
				if (aliquotaVo.getTipoFatoGerador().equals(new DomnTipoGIA(DomnTipoGIA.CAUSA_MORTIS)))
				{
					if (aliquotaVo.getQuantidadeUPFInicial() == faixaUpf)
					{
						faixaUpf = aliquotaVo.getQuantidadeUPFFinal() + 1;
					}
					else
					{
						throw new ParametroObrigatorioException(MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_EXISTE_FAIXA_UPF_CAUSA_MORTIS);
					}
				}
			}
		}
	}

	/**
	 * Método para validar a faixa de UPF das Aliquotas, tipo Inter Vivos, de um Parametro Legislação
	 * @param parametroLegislacaoVo
	 * @throws ParametroObrigatorioException
	 * @implemented by Daniel Balieiro
	 */
	public void validaParametrosIncluirAliquotaFaixaUPFInterVivos(final ParametroLegislacaoVo parametroLegislacaoVo) throws ParametroObrigatorioException
	{
		int faixaUpf = 1;
		if (Validador.isCollectionValida(parametroLegislacaoVo.getAliquotaVo().getCollVO()))
		{
			List listAliquotaVo = new LinkedList(parametroLegislacaoVo.getAliquotaVo().getCollVO());
			Collections.sort(listAliquotaVo);
			for (Iterator iteAliquotas = listAliquotaVo.iterator(); iteAliquotas.hasNext(); )
			{
				AliquotaVo aliquotaVo = (AliquotaVo) iteAliquotas.next();
				if (aliquotaVo.getTipoFatoGerador().equals(new DomnTipoGIA(DomnTipoGIA.INTER_VIVOS)))
				{
					if (aliquotaVo.getQuantidadeUPFInicial() == faixaUpf)
					{
						faixaUpf = aliquotaVo.getQuantidadeUPFFinal() + 1;
					}
					else
					{
						throw new ParametroObrigatorioException(MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_EXISTE_FAIXA_UPF_INTER_VIVOS);
					}
				}
			}
		}
	}

	/**
	 * Método para validar um grupo de Aliquotas, do tipo Inter Vivos, de um determinado Parametro Legislação
	 * @param parametroLegislacaoVo
	 * @throws ParametroObrigatorioException
	 * @implemented by Daniel Balieiro
	 */
	private void validaParametrosIncluirAliquotaExisteInterVivos(final ParametroLegislacaoVo parametroLegislacaoVo) throws ParametroObrigatorioException
	{
		boolean isFatoGeradorInterVivosValido = false;
		for (Iterator iteAliquotas = 
					 parametroLegislacaoVo.getAliquotaVo().getCollVO().iterator(); iteAliquotas.hasNext(); )
		{
			AliquotaVo aliquotaVo = (AliquotaVo) iteAliquotas.next();
			if (aliquotaVo.getTipoFatoGerador().equals(new DomnTipoGIA(DomnTipoGIA.INTER_VIVOS)))
			{
				isFatoGeradorInterVivosValido = true;
			}
		}
		if (!isFatoGeradorInterVivosValido)
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_ALIQUOTA_INTER_VIVOS);
		}
	}

	/**
	 * Método para validar um grupo de Aliquotas, do tipo Causa Mortis, de um determinado Parametro Legislação
	 * @param parametroLegislacaoVo
	 * @throws ParametroObrigatorioException
	 * @implemented by Daniel Balieiro
	 */
	private void validaParametrosIncluirAliquotaExisteCausaMortis(final ParametroLegislacaoVo parametroLegislacaoVo) throws ParametroObrigatorioException
	{
		boolean isFatoGeradorCausaMortisValido = false;
		for (Iterator iteAliquotas = 
					 parametroLegislacaoVo.getAliquotaVo().getCollVO().iterator(); iteAliquotas.hasNext(); )
		{
			AliquotaVo aliquotaVo = (AliquotaVo) iteAliquotas.next();
			if (aliquotaVo.getTipoFatoGerador().getValorCorrente() == DomnTipoGIA.CAUSA_MORTIS)
			{
				isFatoGeradorCausaMortisValido = true;
				break;
			}
		}
		if (!isFatoGeradorCausaMortisValido)
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_ALIQUOTA_CAUSA_MORTIS);
		}
	}

	/**
	 * Método para validar a Coleção de MultaVo
	 * @param parametroLegislacaoVo
	 * @throws ParametroObrigatorioException
	 * @implemented by Daniel Balieiro
	 */
	public void validaParametrosIncluirMultaGrupo(final ParametroLegislacaoVo parametroLegislacaoVo) throws ParametroObrigatorioException
	{
		List listMultaVo = new LinkedList(parametroLegislacaoVo.getMultaVo().getCollVO());
		Collections.sort(listMultaVo);
		int quantidadeMulta = 1;
		for (Iterator iteListMultaVo = listMultaVo.iterator(); iteListMultaVo.hasNext(); )
		{
			MultaVo multaAtualVo = (MultaVo) iteListMultaVo.next(); //MORREU AQUI.
			if (quantidadeMulta == multaAtualVo.getQuantidadeDiasInicial())
			{
				quantidadeMulta = multaAtualVo.getQuantidadeDiasFinal() + 1;
			}
			else
			{
				throw new ParametroObrigatorioException(MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_PERIODO_SEM_MULTA);
			}
		}
	}

	/**
	 * Método utilizado para Validar as informações que serão inseridas no Banco de Dados
	 * @param parametroLegislacaoVo Parametro Legislação que será validado
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @throws SQLException
	 * @throws ConsultaException
	 * @throws RegistroExistenteException
	 * @implemented by Daniel Balieiro
	 */
	public void validaParametrosIncluirAlterarParametroLegislacao(final ParametroLegislacaoVo parametroLegislacaoVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException, ConsultaException, RegistroExistenteException, ConexaoException, 
			  RegistroNaoPodeSerUtilizadoException
	{
		Validador.validaObjeto(parametroLegislacaoVo);
		// verifica se o numero/ano foi digitado
		if (!Validador.isNumericoValido(parametroLegislacaoVo.getNumeroLegislacao()) || 
				 (!Validador.isNumericoValido(parametroLegislacaoVo.getAnoLegislacao())))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_NUMERO_ANO);
		}
		// validacoes referentes a DATA DE INICIO DA VIGENCIA
		if (!Validador.isDataValida(parametroLegislacaoVo.getDataInicioVigencia()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_DATA_VIGENCIA_INICIAL);
		}
		else if (Validador.isDataValida(parametroLegislacaoVo.getDataFimVigencia()) && 
				 parametroLegislacaoVo.getDataInicioVigencia().after(parametroLegislacaoVo.getDataFimVigencia()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_DATA_VIGENCIA_INICIAL_MENOS_FINAL);
		}
		else if (!Validador.isDataValida(parametroLegislacaoVo.getDataFimVigencia()))
		{
			// verificando se a ultima lei cadastrada possui data de vigência final
			ParametroLegislacaoVo parametroLegislacaoConsultaVo = 
						(new ParametroLegislacaoQDao(conn)).findParametroLegislacaoUltima(new ParametroLegislacaoVo());
			if (Validador.isObjetoValido(parametroLegislacaoConsultaVo) && 
						!Validador.isDataValida(parametroLegislacaoConsultaVo.getDataFimVigencia()) && 
						(parametroLegislacaoConsultaVo.getCodigo() != parametroLegislacaoVo.getCodigo()))
			{
				throw new ParametroObrigatorioException(MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_AINDA_ABERTA);
			}
		}
		// validacoes referentes a DATA DE FIM DA VIGENCIA
		if (Validador.isDataValida(parametroLegislacaoVo.getDataFimVigencia()) && 
				 Validador.isDataValida(parametroLegislacaoVo.getDataInicioVigencia()) && 
				 parametroLegislacaoVo.getDataFimVigencia().before(parametroLegislacaoVo.getDataInicioVigencia()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_DATA_VIGENCIA_FINAL_MAIOR_INICIAL);
		}
		// validacoes de ALIQUOTA
		if (Validador.isCollectionValida(parametroLegislacaoVo.getAliquotaVo().getCollVO()))
		{
			// validacoes referentes a ALIQUOTA frente as outras aliquotas
			for (Iterator iteAliquotas = 
							parametroLegislacaoVo.getAliquotaVo().getCollVO().iterator(); iteAliquotas.hasNext(); )
			{
				validaParametrosIncluirAliquota((AliquotaVo) iteAliquotas.next(), parametroLegislacaoVo);
			}
			validaParametrosIncluirAliquotaExisteCausaMortis(parametroLegislacaoVo);
			validaParametrosIncluirAliquotaFaixaUPFCausaMortis(parametroLegislacaoVo);
			validaParametrosIncluirAliquotaExisteInterVivos(parametroLegislacaoVo);
			validaParametrosIncluirAliquotaFaixaUPFInterVivos(parametroLegislacaoVo);
		}
		else
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_CADASTRAR_ALIQUOTA);
		}
		if (Validador.isCollectionValida(parametroLegislacaoVo.getMultaVo().getCollVO()))
		{
			for (Iterator iteMultas = parametroLegislacaoVo.getMultaVo().getCollVO().iterator(); iteMultas.hasNext(); )
			{
				MultaVo multaVo = (MultaVo) iteMultas.next();
				validaParametrosIncluirMulta(multaVo, parametroLegislacaoVo);
				validaParametrosIncluirMultaGrupo(parametroLegislacaoVo);
			}
		}
		else
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_MULTA);
		}
	}

	/**
	 * Método utilizado para alterar um Parametro Legislação no banco de dados.
	 * @param parametroLegislacaoVo Parametro Legislação que será alterado
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ParametroObrigatorioException
	 * @throws RegistroExistenteException
	 * @implemented by Daniel Balieiro
	 */
	public synchronized void alterarParametroLegislacao(final ParametroLegislacaoVo parametroLegislacaoVo) throws ObjetoObrigatorioException, 
			  ConexaoException, RegistroExistenteException, RegistroNaoPodeSerUtilizadoException, LogException, 
			  PersistenciaException, AnotacaoException, DadoNecessarioInexistenteException, IntegracaoException, 
			  ConsultaException, ParametroObrigatorioException
	{
		Validador.validaObjeto(parametroLegislacaoVo);
		try
		{
			try
			{
				synchronized (ParametroLegislacaoVo.class)
				{
					if (parametroLegislacaoVo.getStatusGia().is(DomnSimNao.NAO))
					{
						validaParametrosIncluirAlterarParametroLegislacao(parametroLegislacaoVo);
						ParametroLegislacaoVo parametroConsultaLegislacaoVo = new ParametroLegislacaoVo(parametroLegislacaoVo.getCodigo());
						parametroConsultaLegislacaoVo = new ParametroLegislacaoVo(parametroConsultaLegislacaoVo);
						parametroConsultaLegislacaoVo.setConsultaCompleta(true);
						parametroConsultaLegislacaoVo.setUsuarioLogado(parametroLegislacaoVo.getUsuarioLogado());						
						parametroConsultaLegislacaoVo.setAliquotaVo(parametroConsultaLegislacaoVo.getAliquotaVo());
						consultarParametroLegislacao(parametroConsultaLegislacaoVo);
						Validador.validaObjeto(parametroConsultaLegislacaoVo);
						excluirAliquotaPorAlterarParametroLegislacao(parametroLegislacaoVo, parametroConsultaLegislacaoVo);
						excluirMultaPorAlterarParametroLegislacao(parametroLegislacaoVo, parametroConsultaLegislacaoVo);
						solicitarAlterarParametroLegislacao(parametroLegislacaoVo);
						incluirAliquotaPorAlterarParametroLegislacao(parametroLegislacaoVo, parametroConsultaLegislacaoVo);
						incluirMultaPorAlterarParametroLegislacao(parametroLegislacaoVo, parametroConsultaLegislacaoVo);
						commit();
						parametroLegislacaoVo.setMensagem(MensagemSucesso.ALTERAR);
					}
					else
					{
						throw new RegistroExistenteException(MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_JA_EXISTE_GIA);
					}
				}
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
			catch(RegistroExistenteException e)
			{
				conn.rollback();
				throw e;
			}
			catch(RegistroNaoPodeSerUtilizadoException e)
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
			catch(DadoNecessarioInexistenteException e)
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
	 * Inclui informações sobre um Parametro Legislação no banco de dados.
	 * @param parametroLegislacaoVo Parametro Legislação que será incluido no Banco de Dados
	 * @throws ObjetoObrigatorioException
	 * @throws RegistroExistenteException
	 * @throws ParametroObrigatorioException
	 * @throws ConsultaException
	 * @implemented by Daniel Balieiro
	 */
	public synchronized void incluirParametroLegislacao(final ParametroLegislacaoVo parametroLegislacaoVo) throws ObjetoObrigatorioException, 
			  RegistroExistenteException, ParametroObrigatorioException, ConsultaException, ConexaoException, RegistroNaoPodeSerUtilizadoException, 
			  IntegracaoException, LogException, AnotacaoException, 
			  PersistenciaException
	{
		Validador.validaObjeto(parametroLegislacaoVo);
		try
		{
			try
			{
				synchronized (ParametroLegislacaoVo.class)
				{
					validaParametrosIncluirAlterarParametroLegislacao(parametroLegislacaoVo);
					ParametroLegislacaoVo parametroLegislacaoConsultaVo = new ParametroLegislacaoVo();
					parametroLegislacaoConsultaVo.setNumeroLegislacao(parametroLegislacaoVo.getNumeroLegislacao());
					parametroLegislacaoConsultaVo = new ParametroLegislacaoVo(parametroLegislacaoConsultaVo);
					consultarParametroLegislacao(parametroLegislacaoConsultaVo);
					if (parametroLegislacaoConsultaVo.isExiste() && Validador.isNumericoValido(parametroLegislacaoConsultaVo.getCodigo()))
					{
						throw new RegistroExistenteException(MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_NUMERO_JA_EXISTE);
					}
					else
					{
						parametroLegislacaoVo.setStatusLegislacao(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
						parametroLegislacaoVo.setStatusGia(new DomnSimNao(DomnSimNao.NAO));
						incluir(parametroLegislacaoVo);
						AliquotaBe aliquotaBe = new AliquotaBe(conn);
						for (Iterator iteAliquota = parametroLegislacaoVo.getAliquotaVo().getCollVO().iterator(); iteAliquota.hasNext(); )
						{
							AliquotaVo aliquotaVo = (AliquotaVo) iteAliquota.next();							
							aliquotaVo.setLogSefazVo(parametroLegislacaoVo.getLogSefazVo());
							aliquotaVo.setCodigoParametroLegislacao(parametroLegislacaoVo.getCodigo());
							aliquotaBe.incluirAliquota(aliquotaVo);
						}
						MultaBe multaBe = new MultaBe(conn);
						for (Iterator iteMulta = parametroLegislacaoVo.getMultaVo().getCollVO().iterator(); iteMulta.hasNext(); )
						{
							MultaVo multaVo = (MultaVo) iteMulta.next();
							multaVo.setLogSefazVo(parametroLegislacaoVo.getLogSefazVo());
							multaVo.setCodigoParametroLegislacao(parametroLegislacaoVo.getCodigo());
							multaBe.incluirMulta(multaVo);
						}
						commit();
						parametroLegislacaoVo.setMensagem(MensagemSucesso.INCLUIR);
					}
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
	 * @param parametroLegislacaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void incluir(final ParametroLegislacaoVo parametroLegislacaoVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(parametroLegislacaoVo);
	   parametroLegislacaoVo.setDataAtualizacao( new Date() );
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).insert(parametroLegislacaoVo);
	}

	/**
	 * Método utilizado para efetuar a consulta de um Parametro Legislação
	 * @param parametroLegislacaoVo Parametro Legislação que será consultado.
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ParametroObrigatorioException
	 * @return ParametroLegislacaoVo - Parametro Legislação que foi encontrando
	 * @implemented by Daniel Balieiro
	 */
	public ParametroLegislacaoVo consultarParametroLegislacao(final ParametroLegislacaoVo parametroLegislacaoVo) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, ParametroObrigatorioException, IntegracaoException
	{
		Validador.validaObjeto(parametroLegislacaoVo);
		(new ParametroLegislacaoQDao(conn)).findParametroLegislacao(parametroLegislacaoVo);
		if (parametroLegislacaoVo.isConsultaCompleta())
		{
			AliquotaVo aliquotaConsultaVo = new AliquotaVo();
			aliquotaConsultaVo.setCodigoParametroLegislacao(parametroLegislacaoVo.getCodigo());
			aliquotaConsultaVo = new AliquotaVo(aliquotaConsultaVo);
			parametroLegislacaoVo.setAliquotaVo(listarAliquota(aliquotaConsultaVo));
			MultaVo multaConsultaVo = new MultaVo();
			multaConsultaVo.setCodigoParametroLegislacao(parametroLegislacaoVo.getCodigo());
			multaConsultaVo = new MultaVo(multaConsultaVo);
			parametroLegislacaoVo.setMultaVo(listarMulta(multaConsultaVo));
		   if(!Validador.isObjetoValido(parametroLegislacaoVo.getGiaItcdVo())){
            obterGiaItcdVo(parametroLegislacaoVo);
         }
		}
		return parametroLegislacaoVo;
	}

	/**
	 * Método para consultar o Parametro de Legislação Atual
	 * @param parametroLegislacaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws ParametroObrigatorioException
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	public ParametroLegislacaoVo consultarParametroLegislacaoAtual(final ParametroLegislacaoVo parametroLegislacaoVo) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, ParametroObrigatorioException
	{
		Validador.validaObjeto(parametroLegislacaoVo);
		(new ParametroLegislacaoQDao(conn)).findParametroLegislacaoAtual(parametroLegislacaoVo);
		if (parametroLegislacaoVo.isConsultaCompleta())
		{
			AliquotaVo aliquotaConsultaVo = new AliquotaVo();
			aliquotaConsultaVo.setCodigoParametroLegislacao(parametroLegislacaoVo.getCodigo());
			aliquotaConsultaVo = new AliquotaVo(aliquotaConsultaVo);
			parametroLegislacaoVo.setAliquotaVo(listarAliquota(aliquotaConsultaVo));
			MultaVo multaConsultaVo = new MultaVo();
			multaConsultaVo.setCodigoParametroLegislacao(parametroLegislacaoVo.getCodigo());
			multaConsultaVo = new MultaVo(multaConsultaVo);
			parametroLegislacaoVo.setMultaVo(listarMulta(multaConsultaVo));
		}
		return parametroLegislacaoVo;
	}

	/**
	 * Método para consultar a última Parametro de Legislação cadastrada.
	 * 
	 * @param parametroLegislacaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws ParametroObrigatorioException
	 * @return ParametroLegislacaoVo
	 * @implemented by Daniel Balieiro
	 */
	public ParametroLegislacaoVo consultarParametroLegislacaoUltima(final ParametroLegislacaoVo parametroLegislacaoVo) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, ParametroObrigatorioException
	{
		(new ParametroLegislacaoQDao(conn)).findParametroLegislacaoUltima(parametroLegislacaoVo);
		if (parametroLegislacaoVo.isConsultaCompleta())
		{
			AliquotaVo aliquotaConsultaVo = new AliquotaVo();
			aliquotaConsultaVo.setCodigoParametroLegislacao(parametroLegislacaoVo.getCodigo());
			aliquotaConsultaVo = new AliquotaVo(aliquotaConsultaVo);
			parametroLegislacaoVo.setAliquotaVo(listarAliquota(aliquotaConsultaVo));
			MultaVo multaConsultaVo = new MultaVo();
			multaConsultaVo.setCodigoParametroLegislacao(parametroLegislacaoVo.getCodigo());
			multaConsultaVo = new MultaVo(multaConsultaVo);
			parametroLegislacaoVo.setMultaVo(listarMulta(multaConsultaVo));
		}
		return parametroLegislacaoVo;
	}

	/**
	 * Método usado para consultar um Parametro de Legislacao vigente em uma determinada data
	 * @param data							-		data de vigência
	 * @param consultaCompleta			- 		determina se deve ser consultado os demais dados de um parametro de legislacao
	 * @return
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws ParametroObrigatorioException
	 */
	public ParametroLegislacaoVo consultarParametroLegislacaoAtualPorData(final Date data, boolean consultaCompleta) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, ParametroObrigatorioException
	{
		if (!Validador.isDataValida(data))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_PARAMETRO_LEGISLACAO_DATA);
		}
		ParametroLegislacaoVo parametroLegislacaoVo = 
				 (new ParametroLegislacaoQDao(conn)).findParametroLegislacaoAtualByData(data);
		if (consultaCompleta)
		{
			AliquotaVo aliquotaConsultaVo = new AliquotaVo();
			aliquotaConsultaVo.setCodigoParametroLegislacao(parametroLegislacaoVo.getCodigo());
			aliquotaConsultaVo = new AliquotaVo(aliquotaConsultaVo);
			parametroLegislacaoVo.setAliquotaVo(listarAliquota(aliquotaConsultaVo));
			MultaVo multaConsultaVo = new MultaVo();
			multaConsultaVo.setCodigoParametroLegislacao(parametroLegislacaoVo.getCodigo());
			multaConsultaVo = new MultaVo(multaConsultaVo);
			parametroLegislacaoVo.setMultaVo(listarMulta(multaConsultaVo));
		}
		return parametroLegislacaoVo;
	}

	/**
	 * Método utilizado para Listar os Parametros Legislação
	 * @param parametroLegislacaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ParametroObrigatorioException
	 * @throws ConexaoException
	 * @return ParametroLegislacaoVo
	 * @implemented by Daniel Balieiro
	 */
	public ParametroLegislacaoVo listarParametroLegislacao(final ParametroLegislacaoVo parametroLegislacaoVo) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException
	{
		Validador.validaObjeto(parametroLegislacaoVo);
		(new ParametroLegislacaoQDao(conn)).listParametroLegislacao(parametroLegislacaoVo);
		return parametroLegislacaoVo;
	}

	/**
	 * Método utilizado para consultar uma Aliquota
	 * @param aliquotaVo Aliquota que será consultada
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @throws ConsultaException
	 * @return AliquotaVo - Aliquota encontrada.
	 * @implemented by Daniel Balieiro
	 */
	public AliquotaVo consultarAliquota(final AliquotaVo aliquotaVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException, ConsultaException, ConexaoException
	{
		Validador.validaObjeto(aliquotaVo);
		(new AliquotaQDao(conn)).findAliquotaVo(aliquotaVo);
		return aliquotaVo;
	}

	/**
	 * Método utilizado para listar Aliquotas
	 * @param aliquotaVo Aliquota que será consultada
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @throws ConsultaException
	 * @return AliquotaVo - Aliquota que contém as Aliquotas encontradas
	 * @implemented by Daniel Balieiro
	 */
	public AliquotaVo listarAliquota(final AliquotaVo aliquotaVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException, ConsultaException, ConexaoException
	{
		Validador.validaObjeto(aliquotaVo);
		(new AliquotaQDao(conn)).listAliquotaVo(aliquotaVo);
		return aliquotaVo;
	}

	/**
	 * Método utilizado para consultar uma Multa
	 * @param multaVo (Value Object).
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @return MultaVo - Multa que foi encontrada
	 * @implemented by Daniel Balieiro
	 */
	public MultaVo consultarMulta(final MultaVo multaVo) throws ObjetoObrigatorioException, ConsultaException, 
			  ConexaoException
	{
		Validador.validaObjeto(multaVo);
		(new MultaQDao(conn)).findMultaVo(multaVo);
		return multaVo;
	}

	/**
	 * Método utilizado para Listar as Multas
	 * @param multaVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	public MultaVo listarMulta(final MultaVo multaVo) throws ObjetoObrigatorioException, ConsultaException, 
			  ConexaoException
	{
		Validador.validaObjeto(multaVo);
		(new MultaQDao(conn)).listMultaVo(multaVo);
		return multaVo;
	}

	/**
	 * Método para reativar um Parametro de Legislação
	 * @param parametroLegislacaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ParametroObrigatorioException
	 * @throws RegistroExistenteException
	 * @throws ConexaoException
	 * @throws RegistroNaoPodeSerUtilizadoException
	 * @implemented by Daniel Balieiro
	 */
	public void reativarParametroLegislacao(final ParametroLegislacaoVo parametroLegislacaoVo) throws ObjetoObrigatorioException, 
			  ConexaoException, RegistroExistenteException, RegistroNaoPodeSerUtilizadoException, LogException, 
			  PersistenciaException, AnotacaoException, DadoNecessarioInexistenteException, IntegracaoException, 
			  ConsultaException, ParametroObrigatorioException
	{
		Validador.validaObjeto(parametroLegislacaoVo);
		parametroLegislacaoVo.setStatusLegislacao(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		alterarParametroLegislacao(parametroLegislacaoVo);
	}

	/**
	 * Obtem a gia relacionada com parametroLegislacaoVo.
	 * @param parametroLegislacaoVo
	 * @throws ConsultaException
	 * @throws ObjetoObrigatorioException
	 * @throws ConexaoException
	 * @throws ParametroObrigatorioException
	 * @throws IntegracaoException
	 */
	private void obterGiaItcdVo(ParametroLegislacaoVo parametroLegislacaoVo) throws ConsultaException, 
			  ObjetoObrigatorioException, ConexaoException, ParametroObrigatorioException, IntegracaoException
	{
		GIAITCDBe gIAITCDBe = new GIAITCDBe(conn);
		if(parametroLegislacaoVo.isExiste())
		{
			GIAITCDVo parametroConsulta = new GIAITCDVo();
			parametroConsulta.setParametroLegislacaoVo(parametroLegislacaoVo);
			parametroLegislacaoVo.setGiaItcdVo(gIAITCDBe.listarGIAITCD(new GIAITCDVo(parametroConsulta)));
		}	
	}

	/**
	 * Compara a lista original com a atual e apaga os elementos que não estão mais na atual.
	 * apaga as aliquotas.
	 * @param parametroLegislacaoVo
	 * @param parametroConsultaLegislacaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConexaoException
	 * @implemented by Fábio Vanzella
	 */
	private void excluirAliquotaPorAlterarParametroLegislacao(ParametroLegislacaoVo parametroLegislacaoVo, ParametroLegislacaoVo parametroConsultaLegislacaoVo) throws ObjetoObrigatorioException, 
			  ConexaoException, LogException, PersistenciaException, AnotacaoException, DadoNecessarioInexistenteException
	{
		Collection conteudoOriginal = parametroConsultaLegislacaoVo.getAliquotaVo().getCollVO();
		Collection conteudoAtual = parametroLegislacaoVo.getAliquotaVo().getCollVO();
		Collection listaResultante = obterListaObjetosParaExclusao(conteudoOriginal, conteudoAtual);
		AliquotaBe aliquotaBe = new AliquotaBe(conn);
		for (Iterator it = listaResultante.iterator(); it.hasNext(); )
		{							
			AliquotaVo aliquotaVo = (AliquotaVo) it.next();
			aliquotaVo.setLogSefazVo(parametroLegislacaoVo.getLogSefazVo());
			aliquotaBe.excluirAliquota(aliquotaVo);
		}
	}

	/**
	 * Compara a lista original com a atual e apaga os elementos que não estão mais na atual.
	 * apaga as multas.
	 * @param parametroLegislacaoVo
	 * @param parametroConsultaLegislacaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConexaoException
	 * @implemented by Fábio Vanzella
	 */
	private void excluirMultaPorAlterarParametroLegislacao(ParametroLegislacaoVo parametroLegislacaoVo, ParametroLegislacaoVo parametroConsultaLegislacaoVo) throws ObjetoObrigatorioException, 
			  ConexaoException, LogException, PersistenciaException, AnotacaoException, DadoNecessarioInexistenteException
	{
		Collection conteudoOriginal = parametroConsultaLegislacaoVo.getMultaVo().getCollVO();
		Collection conteudoAtual = parametroLegislacaoVo.getMultaVo().getCollVO();
		Collection listaResultante = obterListaObjetosParaExclusao(conteudoOriginal, conteudoAtual);
		MultaBe multaBe = new MultaBe(conn);
		for (Iterator it = listaResultante.iterator(); it.hasNext(); )
		{
			MultaVo multaVo = (MultaVo) it.next();
			multaVo.setLogSefazVo(parametroLegislacaoVo.getLogSefazVo());
			multaBe.excluirMulta(multaVo);
		}
	}

	/**
	 * Solicita a inclusão de parametros de legislação.
	 * @param parametroLegislacaoVo
	 * @throws ObjetoObrigatorioException
	 * @implemented by Fábio Vanzella
	 */
	private void solicitarAlterarParametroLegislacao(ParametroLegislacaoVo parametroLegislacaoVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		parametroLegislacaoVo.setDataAtualizacao(new Date());
		alterar(parametroLegislacaoVo);
	}

	/**
	 * @param parametroLegislacaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void alterar(final ParametroLegislacaoVo parametroLegislacaoVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(parametroLegislacaoVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).update(parametroLegislacaoVo);
	}

	/**
	 * Compara a lista original com a atual e inclui os elementos que não estão na lista original.
	 * inclui aliquotas.
	 * @param parametroLegislacaoVo
	 * @param parametroConsultaLegislacaoVo
	 * @throws ObjetoObrigatorioException
	 * @implemented by Fábio Vanzella
	 */
	private void incluirAliquotaPorAlterarParametroLegislacao(ParametroLegislacaoVo parametroLegislacaoVo, ParametroLegislacaoVo parametroConsultaLegislacaoVo) throws ObjetoObrigatorioException, 
			  ConexaoException, LogException, PersistenciaException, AnotacaoException
	{
		Collection conteudoOriginal = parametroConsultaLegislacaoVo.getAliquotaVo().getCollVO();
		Collection conteudoAtual = parametroLegislacaoVo.getAliquotaVo().getCollVO();		
		Collection listaResultante = obterListaObjetosParaInclusao(conteudoOriginal, conteudoAtual);
		AliquotaBe aliquotaBe = new AliquotaBe(conn);
		for (Iterator it = listaResultante.iterator(); it.hasNext(); )
		{
			AliquotaVo aliquotaVo = (AliquotaVo) it.next();
			aliquotaVo.setCodigoParametroLegislacao(parametroLegislacaoVo.getCodigo());
			aliquotaVo.setLogSefazVo(parametroLegislacaoVo.getLogSefazVo());
			aliquotaBe.incluirAliquota(aliquotaVo);
		}
	}

	/**
	 * Compara a lista original com a atual e inclui os elementos que não estão na lista original.
	 * inclui multas.
	 * @param parametroLegislacaoVo
	 * @param parametroConsultaLegislacaoVo
	 * @throws ObjetoObrigatorioException
	 * @implemented by Fábio Vanzella
	 */
	private void incluirMultaPorAlterarParametroLegislacao(ParametroLegislacaoVo parametroLegislacaoVo, ParametroLegislacaoVo parametroConsultaLegislacaoVo) throws ObjetoObrigatorioException, 
			  ConexaoException, PersistenciaException, AnotacaoException, LogException
	{
		Collection conteudoOriginal = parametroConsultaLegislacaoVo.getMultaVo().getCollVO();
		Collection conteudoAtual = parametroLegislacaoVo.getMultaVo().getCollVO();
		Collection listaResultante = obterListaObjetosParaInclusao(conteudoOriginal, conteudoAtual);
		MultaBe multaBe = new MultaBe(conn);
		for (Iterator it = listaResultante.iterator(); it.hasNext(); )
		{
			MultaVo multaVo = (MultaVo) it.next();
			multaVo.setCodigoParametroLegislacao(parametroLegislacaoVo.getCodigo());
			multaVo.setLogSefazVo(parametroLegislacaoVo.getLogSefazVo());
			multaBe.incluirMulta(multaVo);
		}
	}

	/**
	 * Retorna a lista de objetos para ser excluida.
	 * @param collectionOriginal
	 * @param collectionAtual
	 * @throws ObjetoObrigatorioException
	 * @return Collection
	 * @implemented by Fábio Vanzella
	 */
	public static Collection obterListaObjetosParaExclusao(Collection collectionOriginal, Collection collectionAtual) throws ObjetoObrigatorioException
	{ 
		Validador.validaObjeto(collectionOriginal);
		Validador.validaObjeto(collectionAtual);
		Collection coll = new HashSet(collectionOriginal);
		coll.removeAll(collectionAtual);
		return coll;
	}

	/**
	 * Retorna a lista de objetos para ser incluida.
	 * @param collectionOriginal
	 * @param collectionAtual
	 * @throws ObjetoObrigatorioException
	 * @return Collection
	 * @implemented by Fábio Vanzella
	 */
	public static Collection obterListaObjetosParaInclusao(Collection collectionOriginal, Collection collectionAtual) throws ObjetoObrigatorioException
	{ 
		Validador.validaObjeto(collectionOriginal);
		Validador.validaObjeto(collectionAtual);
		Collection coll = new HashSet(collectionAtual);
		coll.removeAll(collectionOriginal);
		return coll;
	}

	/**
 	 * Retorna a lista de objetos para ser alterada.
	 * @param collectionOriginal
	 * @param collectionAtual
	 * @throws ObjetoObrigatorioException
	 * @return Collection
	 * @implemented by Fábio Vanzella
	 */
	public static Collection obterListaObjetosParaAlteracao(Collection collectionOriginal, Collection collectionAtual) throws ObjetoObrigatorioException
	{ 
		Validador.validaObjeto(collectionOriginal);
		Validador.validaObjeto(collectionAtual);
		Collection coll = new HashSet();
		coll.addAll(collectionAtual);
		coll.removeAll(obterListaObjetosParaExclusao(collectionOriginal, collectionAtual));
		coll.removeAll(obterListaObjetosParaInclusao(collectionOriginal, collectionAtual));
		return coll;
	}
}
