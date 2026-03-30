/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: ContaCorrenteBe.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 09/11/2007
 */
 package br.gov.mt.sefaz.itc.util.integracao.contacorrente;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.IntegracaoErro;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;

import java.sql.Connection;
import java.sql.SQLException;

import sefaz.mt.divdpublica.intg.IntgLegadosCotcIndc;


/**
 * Be de integração com o sistema de Conta Corrente Fiscal. (no pacote sefaz.mt.divdpublica.intg)
 *
 * @author Leandro Dorileo
 * @version $Revision: 1.2 $
 */
public class ContaCorrenteBe extends AbstractBe
{

	/** objeto de integração */
	private IntgLegadosCotcIndc objIntegracao;

	/**
	 * Construtor público padrão
	 * @throws SQLException
	 */
	public ContaCorrenteBe() throws SQLException
	{
		super();
      //TODO Não utliza a conexão do ITCD.
		objIntegracao = new IntgLegadosCotcIndc();
	}
   
   /**
    * Construtor público padrão
    * @throws SQLException
    */
   public ContaCorrenteBe(Connection con) throws SQLException
   {
      super(con);
      objIntegracao = new IntgLegadosCotcIndc(con);
   }

	/**
	 * Invoca o método de integração do sistema de dívida pública <code>obterCotacaoUPFMT</code>
	 * e retorna encapsulado no objeto do tipo <code>ContaCorrenteIntegracaoVo</code> o valor
	 * da UPFMT.
	 * 
	 * @param contaCorrenteVo		encapsula a data do valorUPFMT desejado. o atriuto usado é 
	 * 									<code>dataCotacaoUPF</code>
	 * @return valor do UPFMT para a data informada
	 */
	public ContaCorrenteIntegracaoVo getValorUPF(ContaCorrenteIntegracaoVo contaCorrenteVo) throws ParametroObrigatorioException, 
			  IntegracaoException
	{
		ContaCorrenteIntegracaoVo retorno = null;
		try
		{
			if (Validador.isDataValida(contaCorrenteVo.getDataCotacaoUPF()))
			{
				retorno = new ContaCorrenteIntegracaoVo();
				Double cotacao = objIntegracao.obterCotacaoUPFMT(contaCorrenteVo.getDataCotacaoUPF().toUtilData());
            if(!Validador.isNumericoValido(cotacao))
            {
               throw new ParametroObrigatorioException(" Valor UPF não cadastrado ou inválido. ");
            }
				retorno.setValorUPF(cotacao);
				return retorno;
			}
			else
			{
				throw new ParametroObrigatorioException("A data da cotacao UPF deve ser informada.");
			}
		}
		catch (Exception e)
		{
			throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_CONTA_CORRENTE, e);
		}
		catch (Error e)
		{
			throw IntegracaoErro.getInstance(IntegracaoErro.OPERACAO_CONSULTAR, IntegracaoErro.NOME_SISTEMA_INTEGRACAO_CONTA_CORRENTE, e);
		}
	}

	/**
	 * Invoca o método de integração do sistema de dívida pública <code>obterIndcCorrecaoFaz</code>
	 * e retorna encapsulado no objeto do tipo <code>ContaCorrenteIntegracaoVo</code> o valor de
	 * Incide de Correcao sefaz para o intervalo de datas informados no parametro contaCorrenteVo.
	 * 
	 * @param contaCorrenteVo		dataInicio e dataFim do intervalo encapsulado em um objeto do tipo
	 * 									<code>ContaCorrenteIntegracaoVo</code>. Ambas as datas são obrigatórias
	 *                            para a chamada desse método
	 * @return Indice de Correcao sefaz encapsulado em um objeto do tipo <code>ContaCorrenteIntegracaoVo</code>
	 * 									no atributo <code>indcCorrecaoSefaz</code>
	 * @throws ParametroObrigatorioException
	 */
	public ContaCorrenteIntegracaoVo getIndcCorrecaoSefaz(ContaCorrenteIntegracaoVo contaCorrenteVo) throws ParametroObrigatorioException, 
			  IntegracaoException
	{
		ContaCorrenteIntegracaoVo retorno = null;
		try
		{
			if (Validador.isDataValida(contaCorrenteVo.getDataInicio().toUtilData()) && 
						Validador.isDataValida(contaCorrenteVo.getDataFim().toUtilData()))
			{
				retorno = new ContaCorrenteIntegracaoVo();
				Double correcao = 
							  objIntegracao.obterIndcCorrecaoFaz(contaCorrenteVo.getDataInicio().toUtilData(), contaCorrenteVo.getDataFim().toUtilData());
				retorno.setIndcCorrecaoSefaz(correcao);
				return retorno;
			}
			else
			{
				throw new ParametroObrigatorioException("DataInicio e DataFim são atributos obrigatórios");
			}
		}
		catch (Exception e)
		{
			throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_CONTA_CORRENTE, e);
		}
		catch (Error e)
		{
			throw IntegracaoErro.getInstance(IntegracaoErro.OPERACAO_CONSULTAR, IntegracaoErro.NOME_SISTEMA_INTEGRACAO_CONTA_CORRENTE, e);
		}
	}

	/**
	 * Invoca o método de integração do sistema de dívida pública <code>obterPercentualJurosSefaz</code>
	 * e retorna encapsulado no objeto do tipo <code>ContaCorrenteIntegracaoVo</code> o valor do
	 * percentual de juros da sefaz para o intervalo de datas informados no parametro contaCorrenteVo.
	 * 
	 * @param contaCorrenteVo     dataInicio e dataFim do intervalo encapsulado em um objeto do tipo
	 *                            <code>ContaCorrenteIntegracaoVo</code>. Ambas as datas são obrigatórias
	 *                            para a chamada desse método
	 * @return  Percentual de Juros fazendário encapsulado em um objeto do tipo <code>ContaCorrenteIntegracaoVo</code>
	 *                            no atributo <code>percentualJurosFaz</code>
	 * @throws ParametroObrigatorioException
	 */
	public ContaCorrenteIntegracaoVo getPercentualJurosFaz(ContaCorrenteIntegracaoVo contaCorrenteVo) throws ParametroObrigatorioException, 
			  IntegracaoException
	{
		ContaCorrenteIntegracaoVo retorno = null;
		try
		{
			if (Validador.isDataValida(contaCorrenteVo.getDataInicio().toUtilData()) && 
						Validador.isDataValida(contaCorrenteVo.getDataFim().toUtilData()))
			{
				retorno = new ContaCorrenteIntegracaoVo();
				Double percentualJurosFaz = 
							  objIntegracao.obterPercentualJurosFaz(contaCorrenteVo.getDataInicio().toUtilData(), contaCorrenteVo.getDataFim().toUtilData());
				retorno.setPercentualJurosFaz(percentualJurosFaz);
				return retorno;
			}
			else
			{
				throw new ParametroObrigatorioException("DataInicio e DataFim são atributos obrigatórios");
			}
		}
		catch (Exception e)
		{
			throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_CONTA_CORRENTE, e);
		}
		catch (Error e)
		{
			throw IntegracaoErro.getInstance(IntegracaoErro.OPERACAO_CONSULTAR, IntegracaoErro.NOME_SISTEMA_INTEGRACAO_CONTA_CORRENTE, e);
		}
	}
}
