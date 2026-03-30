/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: DocumentoArrecadacaoGiaVencidoBe.java
 * Revisão: Leandro Dorileo
 * Data revisão: 25/02/2008
 * $Id: DocumentoArrecadacaoGiaVencidoBe.java,v 1.3 2008/07/30 21:27:25 lucas.nascimento Exp $
 */
package br.gov.mt.sefaz.itc.model.generico.documentoarrecadacao;

import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.DadoNecessarioInexistenteException;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;

import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.generico.giaitcddar.GIAITCDDarVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.multademora.MultaDeMoraBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.multademora.MultaDeMoraVo;
import br.gov.mt.sefaz.itc.util.NumeroUtil;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;

import java.sql.Connection;

import java.util.Date;

import sefaz.mt.util.SefazDataHora;


/**
 * @author Leandro Dorileo
 * @version $Revision: 1.3 $
 */
public class DocumentoArrecadacaoGiaVencidoBe extends DocumentoArrecadacaoITCDBe
{

	/**
	 * Construtor que recebe a conexão com o banco de dados
	 * @param conn
	 * @implemented by Daniel Balieiro
	 */
	public DocumentoArrecadacaoGiaVencidoBe(Connection conn)
	{
		super(conn);
	}

	/**
	 * Este metodo determina a data de vencimento de um dar a ser gerado
	 * 
	 * @param vencimentoOriginal
	 * @param giaItcdVo dados da gia
	 * @return Date
	 * @throws ConsultaException
	 * @throws ObjetoObrigatorioException
	 * @throws IntegracaoException
	 * @throws DadoNecessarioInexistenteException
	 * @throws ParametroObrigatorioException
	 * @implemented by Leandro Dorileo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	protected Date calculaVencimentoDar(Date vencimentoOriginal, GIAITCDVo giaItcdVo) throws ConsultaException, 
			  ObjetoObrigatorioException, IntegracaoException, DadoNecessarioInexistenteException, 
			  ParametroObrigatorioException
	{
		MultaDeMoraVo multaMoraVo = null;
		MultaDeMoraBe multaMoraBe = new MultaDeMoraBe(conn);
		int diasAtraso = 0;
		double percentualMultaMora = 0.0;
		try
		{
			multaMoraVo = multaMoraBe.consultaMultaDeMora();
			diasAtraso = Math.abs(new SefazDataHora(vencimentoOriginal).diferencaDias(SefazDataHora.getDataAtual()));
			percentualMultaMora = diasAtraso * multaMoraVo.getPercentualMultaMora();
			//Se o percentual é maior que o máximo Permitido, o vencimento é o último dia do mês
			if (percentualMultaMora > multaMoraVo.getPercentualMaximoMultaMora())
			{
				SefazDataHora dataAtual = SefazDataHora.getDataAtual();
				return (new SefazDataHora(dataAtual.getAno() , dataAtual.getMes() , dataAtual.getUltimoDiaDoMes())).toUtilData();
			}
			else
			{
				return SefazDataHora.getDataAtual().toUtilData();
			}
		}
		catch (ConexaoException e)
		{
			throw new ConsultaException(MensagemErro.CONSULTAR_MULTA_MORA);
		}
	}

	/**
	 * Este metodo calcula o valor do juros a ser aplicado para o DAR sendo emitido. Consulta-se
	 * o coeficiente de correcao e multiplica ao valor do ITCD e entao multiplica pelo percentual
	 * de juros.
	 * 
	 * @param giaDar
	 * @param vencimentoOriginal
	 * @param dataVencimentoDAR
	 * @return double
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ParametroObrigatorioException
	 * @implemented by Leandro Dorileo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	protected double calculaJurosDAR(GIAITCDDarVo giaDar, Date vencimentoOriginal, Date dataVencimentoDAR) throws ConsultaException, 
			  ParametroObrigatorioException
	{
		double retorno = 0.0;
		double correcaoMonetaria;
		double juros;
		try
		{
			correcaoMonetaria = consultaCorrecaoMonetaria(giaDar.getGia(),vencimentoOriginal, dataVencimentoDAR);
			juros = consultaPercentualJuros(giaDar.getGia(),vencimentoOriginal, dataVencimentoDAR);
		   if (correcaoMonetaria > 0)
		   {
		      retorno = (correcaoMonetaria * giaDar.getGia().getValorITCD()) * juros /100;
		   }
		   else
		   {
		      retorno =  giaDar.getGia().getValorITCD() * juros / 100;
		   }
			giaDar.getGia().setValorJuros(retorno);
		}
		catch (IntegracaoException e)
		{
			new ConsultaException(e.getMessage());
		}
		
		return retorno;
	}

	/**
	 * Este metodo calcula o valor da multa de mora baseado nos dias em atraso sendo que
	 * o percentual de multa de mora nao deve ultrapassar o percentual maximo cadastrado
	 * na tabela basica MultaDeMora.
	 * 
	 * @param giaDar
	 * @param dataReferenciaDAR
	 * @param dataVencimentoOriginalDar
	 * @param dataVencimentoDAR
	 * @return double
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ParametroObrigatorioException
	 * @implemented by Leandro Dorileo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	protected double calculaMultaMoraDAR(GIAITCDDarVo giaDar, Date dataReferenciaDAR, Date dataVencimentoOriginalDar, Date dataVencimentoDAR) throws ObjetoObrigatorioException, 
			  ConsultaException, ParametroObrigatorioException
	{
		double retorno = 0.0;
		double correcaoMonetaria = 0.0;
		double percentualMultaMora = 0.00;
		try
		{
			percentualMultaMora = calculaPercentualMultaMoraDAR(dataReferenciaDAR, dataVencimentoOriginalDar);
			correcaoMonetaria = consultaCorrecaoMonetaria(giaDar.getGia(),dataVencimentoOriginalDar, dataVencimentoDAR);
			if (correcaoMonetaria > 0)
			{
			   retorno = (correcaoMonetaria * giaDar.getGia().getValorITCD()) * percentualMultaMora /100;
			}
			else
			{
				retorno =  giaDar.getGia().getValorITCD() * percentualMultaMora / 100;
			}
			//giaDar.getGia().setValorMulta(retorno);
		}
		catch (IntegracaoException e)
		{
			throw new ConsultaException(MensagemErro.CONSULTAR_MULTA_MORA);
		}
		
		return retorno;
	}
	
	/**
	 * Este metodo calcula o valor da correcao monetaria baseado no valor de correcao
	 * cadastrado no sistema da arrecadacao.
	 * 
	 * @param giaDar
	 * @param dataVencimentoOriginalDar
	 * @param dataVencimentoDAR
	 * @return double
	 * @throws ConsultaException
	 * @throws ParametroObrigatorioException
	 * @implemented by Leandro Dorileo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	protected double calculaCorrecaoMonetariaDAR(GIAITCDDarVo giaDar, Date dataVencimentoOriginalDar, Date dataVencimentoDAR) throws ConsultaException, 
			  ParametroObrigatorioException
	{
		double retorno = 0.0;
		double indcCorrecao = 0.0;
		try
		{
			indcCorrecao = consultaCorrecaoMonetaria(giaDar.getGia(),dataVencimentoOriginalDar, dataVencimentoDAR);
		}
		catch (IntegracaoException e)
		{
			new ConsultaException(e.getMessage());
		}
		retorno = (indcCorrecao * giaDar.getGia().getValorITCD()) - giaDar.getGia().getValorITCD();
		giaDar.getGia().setValorCorrecaoMonetaria(retorno);

		return retorno;
	}

	/**
	 * Método responsável por montar a mensagem de multa de mora.
	 * @param dataReferenciaDAR
	 * @param dataVencimentoOriginalDar
	 * @return String
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ParametroObrigatorioException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	protected String montaMensagemMultaMora(Date dataReferenciaDAR, Date dataVencimentoOriginalDar) throws ObjetoObrigatorioException, ConsultaException, 
			  ParametroObrigatorioException
	{
		double percentualMultaMora = calculaPercentualMultaMoraDAR(dataReferenciaDAR, dataVencimentoOriginalDar);
		if( Validador.isNumericoValido(percentualMultaMora))
		{
			percentualMultaMora = NumeroUtil.arredondaNumero(percentualMultaMora,3);
			return "Multa de Mora: " + StringUtil.doubleToMonetario(percentualMultaMora, 3) + "%";
		}
		return "";
	}

	/**
	 * Método responsável por calcular o percentual de Multa de Mora do DAR.
	 * @param dataReferenciaDAR
	 * @param vencimentoOriginal
	 * @return double
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ParametroObrigatorioException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private double calculaPercentualMultaMoraDAR(Date dataReferenciaDAR, Date vencimentoOriginal) throws ObjetoObrigatorioException, ConsultaException, 
			  ParametroObrigatorioException
	{
		MultaDeMoraBe multaMoraBe = new MultaDeMoraBe(conn);
		MultaDeMoraVo multaMoraVo = null;
		double percentualMultaMora = 0.0;
		try
		{			
			int diferencaDias = Math.abs(new SefazDataHora(vencimentoOriginal).diferencaDias(SefazDataHora.getDataAtual()));
			
			multaMoraVo = multaMoraBe.consultaMultaDeMora();
			percentualMultaMora = (multaMoraVo.getPercentualMultaMora() * diferencaDias);
			
			if (percentualMultaMora > multaMoraVo.getPercentualMaximoMultaMora())
			{
				percentualMultaMora = multaMoraVo.getPercentualMaximoMultaMora();
			}

		}
		catch (ConexaoException e)
		{
			throw new ConsultaException(MensagemErro.CONSULTAR_MULTA_MORA);
		}
		return percentualMultaMora;
	}
	

	
}
