/*
* Secretaria de Estado de Fazenda de Mato Grosso – Sefaz-MT
* Arquivo : ContaCorrenteIntegracaoVo.java
* Criação : Outubro de 2007
* Revisão :
* Log :
*/
package br.gov.mt.sefaz.itc.util.integracao.contacorrente;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.ProibidoMudarNomeSistemaException;
import br.com.abaco.util.facade.EntidadeFacade;
import br.com.abaco.util.facade.VoIntegrador;

import br.gov.mt.sefaz.itc.util.EntidadeVo;

import java.util.Collection;
import java.util.Date;

import sefaz.mt.util.SefazDataHora;


/**
 * VO de integração com o sistema de Conta Corrente Fiscal. A integraçao com o
 * sistema de conta corrente(no pacote sefaz.mt.divdpublica.intg) e feita com
 * metodos que retonam objetos de tipos do java api e nao usam VO`s, por isso,
 * nao herdamos nenhum outro VO.
 *
 * @author Leandro Dorileo
 * @version $Revision: 1.3 $
 */
public class ContaCorrenteIntegracaoVo implements EntidadeFacade, VoIntegrador
{
	private Collection collVO;
	private boolean consultaCompleta;
	private EntidadeFacade parametroConsulta;
	private long usuarioLogado;
	private String mensagem;
	private String nomeSistema;
	private Double valorUPF;
	private Double indcCorrecaoSefaz;
	private Double percentualJurosFaz;
	private SefazDataHora dataInicio;
	private SefazDataHora dataFim;
	private SefazDataHora dataCotacaoUPF;
	private static final long serialVersionUID = Long.MAX_VALUE;

	/**
	 * Construtor público padrão(sem parametros)
	 */
	public ContaCorrenteIntegracaoVo()
	{
		setNomeSistema(EntidadeVo.NOME_SISTEMA);
	}

	/**
	 * @param dataCotacaoUPF		data da cotacao UPF
	 * @throws ParametroObrigatorioException	caso a data informada não seja válida
	 */
	public ContaCorrenteIntegracaoVo(SefazDataHora dataCotacaoUPF) throws ParametroObrigatorioException
	{
		if (Validador.isDataValida(dataCotacaoUPF))
			this.dataCotacaoUPF = dataCotacaoUPF;
		else
			throw new ParametroObrigatorioException("A data de cotão é inválida");
	}

	/**
	 * @param dataInicio		data inicial do intervalo
	 * @param dataFim			data final do intervalo
	 * @throws ParametroObrigatorioException	caso os parametros informados sejam inválidos
	 */
	public ContaCorrenteIntegracaoVo(Date dataInicio, Date dataFim) throws ParametroObrigatorioException
	{
		this();
		boolean dataIniStd = Validador.isDataValida(dataInicio);
		boolean dataFimStd = Validador.isDataValida(dataFim);
		if (!(dataIniStd & dataFimStd))
			throw new ParametroObrigatorioException("DataInicio e dataFim devem ser válidas");
	}

	/**
	 * @param valorUPF					valor da cotacao UPF
	 * @param indcCorrecaoSefaz		indice de correcao da sefaz
	 * @param percentualJurosFaz		percentual de juros fazendário
	 * @throws ParametroObrigatorioException	caso nenhum dos parametros tenha sido informado
	 */
	public ContaCorrenteIntegracaoVo(Double valorUPF, Double indcCorrecaoSefaz, Double percentualJurosFaz) throws ParametroObrigatorioException
	{
		boolean valorUPFStd = Validador.isNumericoValido(valorUPF);
		boolean indcCorrecaoSefazStd = Validador.isNumericoValido(indcCorrecaoSefaz);
		boolean percentualJurosFazStd = Validador.isNumericoValido(percentualJurosFaz);
		if (!(valorUPFStd | indcCorrecaoSefazStd | percentualJurosFazStd))
			throw new ParametroObrigatorioException("Pelo menos algum dos valores devem ser enviados");
	}

	public int compare(Object t, Object t1)
	{
		return ((ContaCorrenteIntegracaoVo) t).compareTo(t1);
	}

	public int compareTo(Object t)
	{
		return (this.hashCode() == t.hashCode()) ? 1 : 0;
	}

	/**
	 * Configura o valor do atributo <code>nomeSistema</code> com o valor do parametro
	 * <code>nomeSistema</code>
	 *
	 * @param nomeSistema       Nome a ser configurado como do sistema
	 * @implememented by Leandro Dorileo
	 */
	public void setNomeSistema(String nomeSistema) throws ProibidoMudarNomeSistemaException
	{
		if (Validador.isStringValida(nomeSistema) && nomeSistema.equals(EntidadeVo.NOME_SISTEMA))
		{
			this.nomeSistema = nomeSistema;
		}
		else
			throw new ProibidoMudarNomeSistemaException();
	}

	/**
	 * Retorna apropriadamente o nome do sistema
	 * @return      nome do sistema
	 * @implemented by Leandro Dorileo
	 */
	public String getNomeSistema()
	{
		return (Validador.isStringValida(this.nomeSistema)) ? this.nomeSistema : STRING_VAZIA;
	}

	/**
	 * Configura apropriadamente o atributo <code>collVO</code> usando o parametro
	 * <code>collVO</code>.
	 * @param collVO        collecao de VO`s a ser configurada como collVO do VO
	 * @implemented by Leandro Dorileo
	 */
	public void setCollVO(Collection collVO)
	{
		this.collVO = collVO;
	}

	/**
	 * Caso não tenha sido criado um collVO e se precise de um antes de manipula-lo
	 * criamos-o aqui.
	 * @return      just created and set collVO to collVO attribute
	 * @implemented by Leandro Dorileo
	 */
	private Collection createCollVO()
	{
		this.setCollVO(collVO);
		return this.collVO;
	}

	/**
	 * Retorna a collVO do VO em questão
	 * @return  collVO do VO em questão
	 */
	public Collection getCollVO()
	{
		return (Validador.isCollectionValida(this.collVO)) ? this.collVO : createCollVO();
	}

	public boolean isExiste()
	{
		return !this.equals(new ContaCorrenteIntegracaoVo());
	}

	/**
	 * Verifica se a collVO existe e se é válida
	 * @return      true caso a <code>collVO</code> seja válida e false caso contrário
	 */
	public boolean isExisteCollVO()
	{
		return Validador.isCollectionValida(this.collVO);
	}

	/**
	 * Verifica se a consulta é parametrizada ou não. A consulta pode ser parametrizada
	 * ou completa, portanto, pode ser necessário verificar o tipo da consulta.
	 *
	 * @return      true caso <code>parametroConsulta</code> seja um objeto válido e false caso contrário
	 */
	public boolean isConsultaParametrizada()
	{
		return (this.getParametroConsulta() != null);
	}

	/**
	 * Verifica se a consulta é completa ou não.
	 *
	 * @return 		estado de <code>consultaCompleta</code>
	 */
	public boolean isConsultaCompleta()
	{
		return consultaCompleta;
	}

	/**
	 * Configura o atributo <code>consultaCompleta</code> com o valor do parametro
	 * <code>consultaCompleta</code>.
	 *
	 * @param consultaCompleta    valor a ser configurado no atributo <code>consultaCompleta</code>
	 */
	public void setConsultaCompleta(boolean consultaCompleta)
	{
		this.consultaCompleta = consultaCompleta;
	}

	/**
	 * Configura o atributo <code>usuarioLogado</code> com o valor do parametro
	 * <code>usuarioLogado</code>.
	 *
	 * @param usuarioLogado		valor a ser configurado no atributo <code>usuarioLogado</code>
	 */
	public void setUsuarioLogado(long usuarioLogado)
	{
		this.usuarioLogado = usuarioLogado;
	}

	/**
	 * Retorna o valor configurado no atributo <code>usuarioLogado</code>
	 *
	 * @return		valor configurado no atributo <code>usuarioLogado</code>
	 */
	public long getUsuarioLogado()
	{
		return this.usuarioLogado;
	}

	/**
	 * Configura o valor do atributo <code>mensagem</code> com o valor do parâmetro
	 * <code>mensagem</code>
	 *
	 * @param mensagem	String contendo a mensagem a ser configurada
	 */
	public void setMensagem(String mensagem)
	{
		this.mensagem = mensagem;
	}

	/**
	 * Retorna o valor configurado no atributo <code>mensagem</code>
	 *
	 * @return	valor configurado no atributo <code>mensagem</code>
	 */
	public String getMensagem()
	{
		return this.mensagem;
	}

	/**
	 * Configura o valor do atributo <code>parametro</code>  com o valor do parâmetro
	 * <code>parametroConsulta</code>
	 *
	 * @param parametroConsulta	instancia de <code>EntidadeFacade</code> a ser configurada
	 */
	public void setParametroConsulta(EntidadeFacade parametroConsulta)
	{
		this.parametroConsulta = parametroConsulta;
	}

	/**
	 * Retorna o valor configurado no atributo <code>parametroConsulta</code>
	 *
	 * @return     valor configurado no atributo <code>parametroConsulta</code>
	 */
	public EntidadeFacade getParametroConsulta()
	{
		return parametroConsulta;
	}

	/**
	 * Configura o valor do atributo <code>valorUFP</code>  com o valor do parâmetro
	 * <code>valorUPF</code>
	 *
	 * @param valorUPF   valor a ser configurado
	 */
	public void setValorUPF(Double valorUPF)
	{
		this.valorUPF = valorUPF;
	}

	/**
	 * Retorna o valor configurado no atributo <code>valorUPF</code>
	 *
	 * @return     valor configurado no atributo <code>valorUPF</code>
	 */
	public Double getValorUPF()
	{
		return valorUPF;
	}

	/**
	 * Configura o valor do atributo <code>dataCotacaoUPF</code>  com o valor do parâmetro
	 * <code>dataCotacaoUPF</code>
	 *
	 * @param dataCotacaoUPF   valor a ser configurado
	 */
	public void setDataCotacaoUPF(SefazDataHora dataCotacaoUPF)
	{
		this.dataCotacaoUPF = dataCotacaoUPF;
	}

	/**
	 * Retorna o valor configurado no atributo <code>dataCotacaoUPF</code>
	 *
	 * @return     valor configurado no atributo <code>dataCotacaoUPF</code>
	 */
	public SefazDataHora getDataCotacaoUPF()
	{
		return dataCotacaoUPF;
	}

	/**
	 * Configura o valor do atributo <code>dataInicio</code>  com o valor do parâmetro
	 * <code>dataInicio</code>
	 *
	 * @param dataInicio   valor a ser configurado
	 */
	public void setDataInicio(SefazDataHora dataInicio)
	{
		this.dataInicio = dataInicio;
	}

	/**
	 * Retorna o valor configurado no atributo <code>dataInicio</code>
	 *
	 * @return     valor configurado no atributo <code>dataInicio</code>
	 */
	public SefazDataHora getDataInicio()
	{
		return dataInicio;
	}

	/**
	 * Configura o valor do atributo <code>dataFim</code>  com o valor do parâmetro
	 * <code>dataFim</code>
	 *
	 * @param dataFim   valor a ser configurado
	 */
	public void setDataFim(SefazDataHora dataFim)
	{
		this.dataFim = dataFim;
	}

	/**
	 * Retorna o valor configurado no atributo <code>dataFim</code>
	 *
	 * @return     valor configurado no atributo <code>dataFim</code>
	 */
	public SefazDataHora getDataFim()
	{
		return dataFim;
	}

	/**
	 * Retorna o valor configurado no atributo <code>indcCorrecaoSefaz</code>
	 *
	 * @return     valor configurado no atributo <code>indcCorrecaoSefaz</code>
	 */
	public Double getIndcCorrecaoSefaz()
	{
		if (!Validador.isNumericoValido(this.indcCorrecaoSefaz))
		{
			return new Double(1);
		}
		return this.indcCorrecaoSefaz;
	}

	/**
	 * Configura o valor do atributo <code>indcCorrecaoSefaz</code>  com o valor do parâmetro
	 * <code>indcCorrecaoSefaz</code>
	 *
	 * @param indcCorrecaoSefaz   valor a ser configurado
	 */
	public void setIndcCorrecaoSefaz(Double indcCorrecaoSefaz)
	{
		this.indcCorrecaoSefaz = indcCorrecaoSefaz;
	}

	/**
	 * Retorna o valor configurado no atributo <code>percentualJurosFaz</code>
	 *
	 * @return     valor configurado no atributo <code>percentualJurosFaz</code>
	 */
	public Double getPercentualJurosFaz()
	{
		if(!Validador.isNumericoValido(this.percentualJurosFaz))
		{
			return new Double(0);
		}
		return this.percentualJurosFaz;
	}

	/**
	 * Configura o valor do atributo <code>percentualJurosFaz</code>  com o valor do parâmetro
	 * <code>percentualJurosFaz</code>
	 *
	 * @param percentualJurosFaz   valor a ser configurado
	 */
	public void setPercentualJurosFaz(Double percentualJurosFaz)
	{
		this.percentualJurosFaz = percentualJurosFaz;
	}
}
