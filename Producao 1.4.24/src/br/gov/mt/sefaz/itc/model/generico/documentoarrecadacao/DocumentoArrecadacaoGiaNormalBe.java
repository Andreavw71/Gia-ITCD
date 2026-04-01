/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: DocumentoArrecadacaoGiaNormalBe.java
 * Revisão: Leandro Dorileo
 * Data revisão: 25/02/2008
 * $Id: DocumentoArrecadacaoGiaNormalBe.java,v 1.3 2008/07/30 21:27:25 lucas.nascimento Exp $
 */
package br.gov.mt.sefaz.itc.model.generico.documentoarrecadacao;

import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.DadoNecessarioInexistenteException;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;

import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.generico.giaitcddar.GIAITCDDarVo;

import java.sql.Connection;

import java.util.Date;


/**
 * @author Leandro Dorileo
 * @version $Revision: 1.3 $
 */
public class DocumentoArrecadacaoGiaNormalBe extends DocumentoArrecadacaoITCDBe
{

	/**
	 * Construtor que recebe a conexão com o bancod de dados
	 * @param conn
	 * @implemented by Daniel Balieiro
	 */
	public DocumentoArrecadacaoGiaNormalBe(Connection conn)
	{
		super(conn);
	}

	/**
	 * Este metodo determina a data de vencimento de um dar a ser gerado
	 * 
	 * @param vencimentoOriginal
	 * @param giaItcdVo dados da gia
	 * @return java.util.Date
	 * @throws ConsultaException
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @throws ConexaoException
	 * @throws IntegracaoException
	 * @throws DadoNecessarioInexistenteException
	 * @implemented by Leandro Dorileo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	protected Date calculaVencimentoDar(Date vencimentoOriginal, GIAITCDVo giaItcdVo) throws ConsultaException, 
			  ObjetoObrigatorioException, ParametroObrigatorioException, ConexaoException, IntegracaoException, 
			  DadoNecessarioInexistenteException
	{
		return vencimentoOriginal;
	}

	/**
	 * Este metodo configura o valor de juros a ser aplicado a um DAR com zero uma vez que
	 * nao existe DAR em atraso.
	 * 
	 * @param giaDar
	 * @param vencimentoOriginal
	 * @param dataVencimentoDAR
	 * @return double
	 * @implemented by Leandro Dorileo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	protected double calculaJurosDAR(GIAITCDDarVo giaDar, Date vencimentoOriginal, Date dataVencimentoDAR)
	{
		return 0.0;
	}

	/**
	 * Este metodo configura o valor de multa de mora a ser aplicada a um DAR com zero uma vez que
	 * nao existe DAR em atraso.
	 * 
	 * @param giaDar
	 * @param dataReferenciaDAR
	 * @param dataVencimentoOriginalDar
	 * @param dataVencimentoDAR
	 * @return double
	 * @implemented by Leandro Dorileo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	protected double calculaMultaMoraDAR(GIAITCDDarVo giaDar, Date dataReferenciaDAR, Date dataVencimentoOriginalDar, Date dataVencimentoDAR)
	{
		return 0.0;
	}

	/**
	 * Este metodo configura o valor de correcao monetaria a ser aplicada a um DAR com zero uma vez que
	 * nao existe DAR e atraso.
	 * 
	 * @param giaDar
	 * @param dataVencimentoOriginalDar
	 * @param dataVencimentoDAR
	 * @return double
	 * @throws ConsultaException
	 * @throws ParametroObrigatorioException
	 * @implemented by Leandro DOrileo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	protected double calculaCorrecaoMonetariaDAR(GIAITCDDarVo giaDar, Date dataVencimentoOriginalDar, Date dataVencimentoDAR) throws ConsultaException, 
			  ParametroObrigatorioException
	{
		return 0.0;
	}
	
	/**
	 * 
	 * @param dataReferenciaDAR
	 * @param dataVencimentoOriginalDar
	 * @return String
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ParametroObrigatorioException
	 * @implemented by Lucas Nascimento
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	protected String montaMensagemMultaMora(Date dataReferenciaDAR, Date dataVencimentoOriginalDar) throws ObjetoObrigatorioException, ConsultaException, 
			  ParametroObrigatorioException
	{
		return "";
	}


}
