package br.gov.mt.sefaz.itc.model.tabelabasica.parametrosdalegislacao;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposParametroLegislacao;
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
 * Classe de acesso a dados (Data Access Object).
 * @author Daniel Balieiro
 * @version $Revision: 1.1.1.1 $
 */
public class ParametroLegislacaoDao extends AbstractDao implements TabelasITC, SequencesITC, CamposParametroLegislacao
{
	/**
	 * Construtor Padrão
	 * @param conexao Conexão com o Banco de Dados que será utilizada para manutenção dos do Parametro Legislação
	 * @implemented by Daniel Balieiro
	 */
	public ParametroLegislacaoDao(Connection conexao)
	{
		super(conexao);
		utilStmt = new UtilStmt(TABELA_PARAMETRO_LEGISLACAO);
	}

	/**
	 * Método que retorna os Campos da tabela de Parametro Legislação
	 * @return String[] Campos da tabela de Parametro Legislação
	 * @implemented by Daniel Balieiro
	 */
	private String[] getCamposParametroLegislacao()
	{
		return new String[] { CAMPO_CODIGO_PARAMETRO_LEGISLACAO, CAMPO_NUMERO_PARAMETRO_LEGISLACAO, CAMPO_ANO_LEGISLACAO, CAMPO_DATA_INICIO_VIGENCIA, CAMPO_DATA_FINAL_VIGENCIA, CAMPO_STATUS_PARAMETRO_LEGISLACAO, CAMPO_DATA_ATUALIZACAO_BD, CAMPO_STATUS_GIA, CAMPO_CALCULO_CASCATA, CAMPO_NUMERO_LEGISLACAO_PRINCIPAL, CAMPO_ANO_LEGISLACAO_PRINCIPAL };
	}

	/**
	 * Método responsável por inserir um ParametroLegislacaoVo no banco de dados
	 * @param parametroLegislacaoVo Que será inserido no Banco de Dados.
	 * @throws ObjetoObrigatorioException
	 * @throws IncluiException
	 * @implemented by Daniel Balieiro
	 */
	public void insertParametroLegislacaoVo(final ParametroLegislacaoVo parametroLegislacaoVo) throws ObjetoObrigatorioException, 
			  IncluiException
	{
		Validador.validaObjeto(parametroLegislacaoVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraInsr(getCamposParametroLegislacao());
		try
		{
			SefazSequencia sequence = new SefazSequencia(conn);
			parametroLegislacaoVo.setCodigo(sequence.next(SEQUENCE_PARAMETRO_LEGISLACAO));
			parametroLegislacaoVo.setAliquotaVo(parametroLegislacaoVo.getAliquotaVo());
			parametroLegislacaoVo.setMultaVo(parametroLegislacaoVo.getMultaVo());
			GeradorLogSefazMT.gerar(parametroLegislacaoVo, DomnOperacao.OPERACAO_INSERT, parametroLegislacaoVo.getNumeroParticao(), parametroLegislacaoVo.getCodigoTransacao(), parametroLegislacaoVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			prepareStatementInsertParametroLegislacao(ps, parametroLegislacaoVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_PARAMETRO_LEGISLACAO);
		}
		//catch (LogSefazException e)
		// {
		//  e.printStackTrace();
		//  throw new IncluiException(MensagemErro.INCLUIR_PARAMETRO_LEGISLACAO);
		// }
		catch (Exception e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_PARAMETRO_LEGISLACAO);
		}
		finally
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

	/**
	 *  Método utilizado para adicionar no PreparedStatement somente valores válidos no momento
	 *  de inclusão de um Parametro Legislacao. 
	 *  Os valores não válidos serão adicionados como Types.NULL para economizar recursos no Banco de Dados
	 * @param ps PreparedStatement que será montado
	 * @param parametroLegislacaoVo Que será usado para montar o PreparedStatement
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void prepareStatementInsertParametroLegislacao(final PreparedStatement ps, final ParametroLegislacaoVo parametroLegislacaoVo) throws ObjetoObrigatorioException, 
			  ConexaoException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(parametroLegislacaoVo);
		int contador = 0;
		try
		{
			// CODIGO
			if (Validador.isNumericoValido(parametroLegislacaoVo.getCodigo()))
			{
				ps.setLong(++contador, parametroLegislacaoVo.getCodigo());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// NUMERO PARAMETRO LEGISLACAO
			if (Validador.isNumericoValido(parametroLegislacaoVo.getNumeroLegislacao()))
			{
				ps.setInt(++contador, parametroLegislacaoVo.getNumeroLegislacao());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// ANO PARAMETRO LEGISLACAO
			if (Validador.isNumericoValido(parametroLegislacaoVo.getAnoLegislacao()))
			{
				ps.setInt(++contador, parametroLegislacaoVo.getAnoLegislacao());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// DATA INICIO VIGENCIA
			if (Validador.isDataValida(parametroLegislacaoVo.getDataInicioVigencia()))
			{
				ps.setDate(++contador, new java.sql.Date(parametroLegislacaoVo.getDataInicioVigencia().getTime()));
			}
			else
			{
				ps.setNull(++contador, Types.DATE);
			}
			// DATA FIM VIGENCIA
			if (Validador.isDataValida(parametroLegislacaoVo.getDataFimVigencia()))
			{
				ps.setDate(++contador, new java.sql.Date(parametroLegislacaoVo.getDataFimVigencia().getTime()));
			}
			else
			{
				ps.setNull(++contador, Types.DATE);
			}
			// STATUS PARAMETRO LEGISLACAO
			if (Validador.isDominioNumericoValido(parametroLegislacaoVo.getStatusLegislacao()))
			{
				ps.setInt(++contador, parametroLegislacaoVo.getStatusLegislacao().getValorCorrente());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// DATA ATUALIZACAO
			ps.setTimestamp(++contador, SefazDataHora.getDataHoraAtual().toSqlTimestamp());
			// STATUS GIA
			if (Validador.isDominioNumericoValido(parametroLegislacaoVo.getStatusGia()))
			{
				ps.setInt(++contador, parametroLegislacaoVo.getStatusGia().getValorCorrente());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// CALCULO CASCATA
			if (Validador.isDominioNumericoValido(parametroLegislacaoVo.getCalculoCascata()))
			{
				ps.setInt(++contador, parametroLegislacaoVo.getCalculoCascata().getValorCorrente());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// NUMERO PARAMETRO LEGISLACAO PRINCIPAL
			if (Validador.isNumericoValido(parametroLegislacaoVo.getNumeroLegislacaoPrincipal()))
			{
				ps.setInt(++contador, parametroLegislacaoVo.getNumeroLegislacaoPrincipal());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// ANO PARAMETRO LEGISLACAO PRINCIPAL
			if (Validador.isNumericoValido(parametroLegislacaoVo.getAnoLegislacaoPrincipal()))
			{
				ps.setInt(++contador, parametroLegislacaoVo.getAnoLegislacaoPrincipal());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
		}
		catch (SQLException e)
		{
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}
	}

	/**
	 * Método responsável por alterar um Parametro Legislação no Banco de Dados.
	 * @param parametroLegislacaoVo Parametro Legislação que será atualizado.
	 * @throws ObjetoObrigatorioException
	 * @throws AlteraException
	 * @implemented by Daniel Balieiro
	 */
	public void updateParametroLegislacao(final ParametroLegislacaoVo parametroLegislacaoVo) throws ObjetoObrigatorioException, 
			  AlteraException
	{
		Validador.validaObjeto(parametroLegislacaoVo);
		PreparedStatement ps = null;
		String sql = 
				 utilStmt.geraUpdt(getCamposParametroLegislacao(), new String[] { CAMPO_CODIGO_PARAMETRO_LEGISLACAO });
		try
		{
			GeradorLogSefazMT.gerar(parametroLegislacaoVo, DomnOperacao.OPERACAO_UPDATE, parametroLegislacaoVo.getNumeroParticao(), parametroLegislacaoVo.getCodigoTransacao(), parametroLegislacaoVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			prepareStatementUpdateParametroLegislacao(ps, parametroLegislacaoVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_PARAMETRO_LEGISLACAO);
		}
		//catch (LogSefazException e)
		//{
		// e.printStackTrace();
		// throw new AlteraException(MensagemErro.ALTERAR_PARAMETRO_LEGISLACAO);
		// }
		catch (Exception e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_PARAMETRO_LEGISLACAO);
		}
		finally
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

	/**
	 * Monta o Prepared Statement de alteração de um Parametro de Legislação
	 * @param ps
	 * @param parametroLegislacaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConexaoException
	 * @implemented by Daniel Balieiro
	 */
	private void prepareStatementUpdateParametroLegislacao(final PreparedStatement ps, final ParametroLegislacaoVo parametroLegislacaoVo) throws ObjetoObrigatorioException, 
			  ConexaoException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(parametroLegislacaoVo);
		int contador = 0;
		try
		{
			// NUMERO PARAMETRO LEGISLACAO
			if (Validador.isNumericoValido(parametroLegislacaoVo.getNumeroLegislacao()))
			{
				ps.setInt(++contador, parametroLegislacaoVo.getNumeroLegislacao());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// ANO PARAMETRO LEGISLACAO
			if (Validador.isNumericoValido(parametroLegislacaoVo.getAnoLegislacao()))
			{
				ps.setInt(++contador, parametroLegislacaoVo.getAnoLegislacao());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// DATA INICIO VIGENCIA
			if (Validador.isDataValida(parametroLegislacaoVo.getDataInicioVigencia()))
			{
				ps.setDate(++contador, new java.sql.Date(parametroLegislacaoVo.getDataInicioVigencia().getTime()));
			}
			else
			{
				ps.setNull(++contador, Types.DATE);
			}
			// DATA FIM VIGENCIA
			if (Validador.isDataValida(parametroLegislacaoVo.getDataFimVigencia()))
			{
				ps.setDate(++contador, new java.sql.Date(parametroLegislacaoVo.getDataFimVigencia().getTime()));
			}
			else
			{
				ps.setNull(++contador, Types.DATE);
			}
			// STATUS PARAMETRO LEGISLACAO
			if (Validador.isDominioNumericoValido(parametroLegislacaoVo.getStatusLegislacao()))
			{
				ps.setInt(++contador, parametroLegislacaoVo.getStatusLegislacao().getValorCorrente());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// DATA ATUALIZACAO
			ps.setTimestamp(++contador, SefazDataHora.getDataHoraAtual().toSqlTimestamp());
			// STATUS GIA
			if (Validador.isDominioNumericoValido(parametroLegislacaoVo.getStatusGia()))
			{
				ps.setInt(++contador, parametroLegislacaoVo.getStatusGia().getValorCorrente());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// CALCULO CASCATA
			if (Validador.isDominioNumericoValido(parametroLegislacaoVo.getCalculoCascata()))
			{
				ps.setLong(++contador, parametroLegislacaoVo.getCalculoCascata().getValorCorrente());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// NUMERO PARAMETRO LEGISLACAO PRINCIPAL
			if (Validador.isNumericoValido(parametroLegislacaoVo.getNumeroLegislacaoPrincipal()))
			{
				ps.setInt(++contador, parametroLegislacaoVo.getNumeroLegislacaoPrincipal());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// ANO PARAMETRO LEGISLACAO PRINCIPAL
			if (Validador.isNumericoValido(parametroLegislacaoVo.getAnoLegislacaoPrincipal()))
			{
				ps.setInt(++contador, parametroLegislacaoVo.getAnoLegislacaoPrincipal());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// CODIGO
			if (Validador.isNumericoValido(parametroLegislacaoVo.getCodigo()))
			{
				ps.setLong(++contador, parametroLegislacaoVo.getCodigo());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
		}
		catch (SQLException e)
		{
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}
	}
}
