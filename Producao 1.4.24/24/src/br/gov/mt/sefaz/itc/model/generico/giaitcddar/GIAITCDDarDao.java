/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: GIAITCDDarDao.java
 * Revisão: Leandro Dorileo
 * Data revisão: 17/03/2008
 * $Id: GIAITCDDarDao.java,v 1.2 2008/07/30 21:27:27 lucas.nascimento Exp $
 */
package br.gov.mt.sefaz.itc.model.generico.giaitcddar;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposGIAITCDDar;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import sefaz.mt.log.GeradorLogSefazMT;
import sefaz.mt.log.util.dominios.DomnOperacao;
import sefaz.mt.util.AlteraException;
import sefaz.mt.util.IncluiException;
import sefaz.mt.util.SefazDataHora;
import sefaz.mt.util.SefazSequencia;
import sefaz.mt.util.UtilStmt;


/**
 * GIAITCDDarVo
 * Classe de persistência de dados(Operações: Insert/Update) para a tabela de ligação
 * de uma GIAITCD com um DAR(Documento de Arrecadação).
 *
 * @author Leandro Dorileo
 */
public class GIAITCDDarDao extends AbstractDao implements CamposGIAITCDDar, TabelasITC, SequencesITC
{

	/**
	 * Construtor que recebe a Conexão com o Banco de Dados.
	 * @param conexao objeto de conexão com o banco de dados.
	 * @implemented by Leandro Dorileo
	 */
	public GIAITCDDarDao(Connection conexao)
	{
		super(conexao);
		utilStmt = new UtilStmt(TABELA_GIA_ITCD_DAR);
	}

	/**
	 * Retorna os Campos do GIA ITCD.
	 * @return String[]
	 * @implemented by Leandro Dorileo
	 */
	public String[] getGIAITCDarCampos()
	{
		return new String[] { CAMPO_CODG_ITCD_DAR, CAMPO_CODG_ITCD, CAMPO_NUMR_DAR_SEQUENCIAL, CAMPO_FLAG_SUBSTITUIDO, CAMPO_DATA_ATUALIZACAO_BD, CAMPO_NUMR_PARCELA_DAR };
	}

	/**
	 * Método para Inserir um registro na tabela de ligação entre uma GIAITCD e um DAR(Documento de Arrecadação)
	 * @param giaitcddarVo		-		dados a serem inseridos
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws IncluiException Esta exceção deve ser lançada quando o sistema tenta INCLUIR um registro no banco de dados e não consegue.
	 * @implemented by Leandro Dorileo
	 */
	public void insertGIAITCDar(final GIAITCDDarVo giaitcddarVo) throws ObjetoObrigatorioException, IncluiException
	{
		Validador.validaObjeto(giaitcddarVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraInsr(getGIAITCDarCampos());
		try
		{
			GeradorLogSefazMT.gerar(giaitcddarVo, DomnOperacao.OPERACAO_INSERT, giaitcddarVo.getNumeroParticao(), giaitcddarVo.getCodigoTransacao(), giaitcddarVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			SefazSequencia sequence = new SefazSequencia(conn);
			giaitcddarVo.setCodigo(sequence.next(SEQUENCE_GIA_ITCD_DAR));
			preparedStatementInsertGIAITCDar(ps, (GIAITCDDarVo) giaitcddarVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_GIA_ITCD);
		}
		/*catch (LogSefazException e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_GIA_ITCD);
		}*/
		catch (Exception e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_GIA_ITCD);
		}
		finally
		{
			if (ps != null)
			{
				try
				{
					close(ps);
					ps = null;
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Monta o Prepare Statemant para Inserir um registro na tabela de ligação de GIA e DAR.
	 * @param ps (java.sql.PreparedStatement).
	 * @param giaitcddarVo			-	dados a serem inseridos
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Leandro Dorileo
	 */
	private void preparedStatementInsertGIAITCDar(final PreparedStatement ps, final GIAITCDDarVo giaitcddarVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(giaitcddarVo);
		int contador = 0;
		// CHAVE PRIMARIA
		if (Validador.isNumericoValido(giaitcddarVo.getCodigo()))
		{
			ps.setLong(++contador, giaitcddarVo.getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// CODIGO DA GIA
		if (Validador.isNumericoValido(giaitcddarVo.getGia().getCodigo()))
		{
			ps.setLong(++contador, giaitcddarVo.getGia().getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// NUMERO DO DAR
		if (Validador.isNumericoValido(giaitcddarVo.getDarEmitido().getNumrDarSeqc()))
		{
			ps.setLong(++contador, giaitcddarVo.getDarEmitido().getNumrDarSeqc().longValue());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// FLAG SUBSTITUIDO
		if (Validador.isDominioNumericoValido(giaitcddarVo.getSubstituido()))
		{
			ps.setLong(++contador, giaitcddarVo.getSubstituido().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// DATA ATUALIZACAO
		ps.setTimestamp(++contador, SefazDataHora.getDataHoraAtual().toSqlTimestamp());
		//NUMR_PARCELA_DAR
	   if (Validador.isNumericoValido(giaitcddarVo.getNumeroParcela()))
	   {
	      ps.setInt(++contador, giaitcddarVo.getNumeroParcela());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.NUMERIC);
	   }
	}

	/**
	 * Método responsável por atualizar um registro da tabela de ligação de uma GIAITCD com um DAR(Documento de Arrecadação)
	 * @param giaitcddarVo		-	dados a serem atualizados
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws IncluiException Esta exceção deve ser lançada quando o sistema tenta INCLUIR um registro no banco de dados e não consegue.
	 * @implemented by Leandro Dorileo
	 */
	public void updateGIAITCDDar(GIAITCDDarVo giaitcddarVo) throws ObjetoObrigatorioException, AlteraException
	{
		Validador.validaObjeto(giaitcddarVo);
		PreparedStatement ps = null;
		String sql = 
				 utilStmt.geraUpdt(getGIAITCDarCampos(), new String[] { CAMPO_NUMR_DAR_SEQUENCIAL, CAMPO_CODG_ITCD, CAMPO_CODG_ITCD_DAR });
		try
		{
			GeradorLogSefazMT.gerar(giaitcddarVo, DomnOperacao.OPERACAO_UPDATE, giaitcddarVo.getNumeroParticao(), giaitcddarVo.getCodigoTransacao(), giaitcddarVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementUpdateGIAITCDDar(ps, (GIAITCDDarVo) giaitcddarVo.getParametroConsulta());
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_GIA_ITCD);
		}
		/*catch (LogSefazException e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_GIA_ITCD);
		}*/
		catch (Exception e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_GIA_ITCD);
		}
		finally
		{
			if (ps != null)
			{
				try
				{
					close(ps);
					ps = null;
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Monta o Prepare Statemant para Alterar um registro na tabela de ligação de uma GIAITCD com um DAR(Documento de Arrecadação)
	 * @param ps (java.sql.PreparedStatement).
	 * @param giaitcddarVo		-	dados a serem manipulados
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Leandro Dorileo
	 */
	private void preparedStatementUpdateGIAITCDDar(final PreparedStatement ps, final GIAITCDDarVo giaitcddarVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(giaitcddarVo);
		int contador = 0;
		// FLAG SUBSTITUIDO
		if (Validador.isDominioNumericoValido(giaitcddarVo.getSubstituido()))
		{
			ps.setLong(++contador, giaitcddarVo.getSubstituido().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// DATA ATUALIZACAO
		ps.setTimestamp(++contador, SefazDataHora.getDataHoraAtual().toSqlTimestamp());
		// NUMR_PARCELA_DAR
	   if (Validador.isNumericoValido(giaitcddarVo.getNumeroParcela()))
	   {
	      ps.setInt(++contador, giaitcddarVo.getNumeroParcela());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.NUMERIC);
	   }
		// CHAVE PRIMARIA
		 // NUMERO DO DAR
		 if (Validador.isNumericoValido(giaitcddarVo.getDarEmitido().getNumrDarSeqc()))
		 {
		    ps.setLong(++contador, giaitcddarVo.getDarEmitido().getNumrDarSeqc().longValue());
		 }
		 else
		 {
		    ps.setNull(++contador, Types.NUMERIC);
		 }

		if (Validador.isNumericoValido(giaitcddarVo.getGia().getCodigo()))
		{
			ps.setLong(++contador, giaitcddarVo.getGia().getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		 // CODIGO DA GIAITCD
	   if (Validador.isNumericoValido(giaitcddarVo.getCodigo()))
	   {
	      ps.setLong(++contador, giaitcddarVo.getCodigo());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.NUMERIC);
	   }
	}
}
