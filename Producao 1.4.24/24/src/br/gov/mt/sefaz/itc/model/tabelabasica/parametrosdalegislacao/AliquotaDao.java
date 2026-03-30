package br.gov.mt.sefaz.itc.model.tabelabasica.parametrosdalegislacao;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposParametroLegislacaoAliquota;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import sefaz.mt.log.GeradorLogSefazMT;
import sefaz.mt.log.util.dominios.DomnOperacao;
import sefaz.mt.log.util.excecao.LogSefazException;
import sefaz.mt.util.AlteraException;
import sefaz.mt.util.ExcluiException;
import sefaz.mt.util.IncluiException;
import sefaz.mt.util.SefazSequencia;
import sefaz.mt.util.UtilStmt;


/**
 * Classe de acesso a dados (Data Access Object).
 * @author Daniel Balieiro
 * @version $Revision: 1.1.1.1 $
 */
public class AliquotaDao extends AbstractDao implements TabelasITC, SequencesITC, CamposParametroLegislacaoAliquota
{

	/**
	 * Construtor que recebe a conexão quer será utilizada para a manutenção dos dados
	 * @param conexao Conexão com o Banco de Dados
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	public AliquotaDao(Connection conexao)
	{
		super(conexao);
		utilStmt = new UtilStmt(TABELA_PARAMETRO_LEGISLACAO_ALIQUOTA);
	}

	/**
	 * Retorna os Campos da Tabela de Aliquota
	 * @return String[] - Campos da Tabela de Aliquota
	 * @implemented by Daniel Balieiro
	 */
	public String[] getCamposAliquota()
	{
		return new String[] { CAMPO_CODIGO_ALIQUOTA, CAMPO_ITCTB09_CODIGO_PARAMETRO_LEGISLACAO, CAMPO_TIPO_FATOR_GERADOR, CAMPO_TIPO_ISENCAO, CAMPO_PERCENTUAL_LEGISLACAO_ALIQUOTA, CAMPO_QUANTIDADE_UPF_INICIAL, CAMPO_QUANTIDADE_UPF_FINAL };
	}

	/**
	 * Inclui um Aliquota no Banco de dados
	 * @param aliquotaVo AliquotaVo que será incluido ao banco de dados
	 * @throws ObjetoObrigatorioException
	 * @throws IncluiException
	 * @implemented by Daniel Balieiro
	 */
	public void insertAliquota(final AliquotaVo aliquotaVo) throws ObjetoObrigatorioException, IncluiException
	{
		Validador.validaObjeto(aliquotaVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraInsr(getCamposAliquota());
		try
		{
			SefazSequencia sequence = new SefazSequencia(conn);
			aliquotaVo.setCodigo(sequence.next(SEQUENCE_PARAMETRO_LEGISLACAO_ALIQUOTA));
			GeradorLogSefazMT.gerar(aliquotaVo, DomnOperacao.OPERACAO_INSERT, aliquotaVo.getNumeroParticao(), aliquotaVo.getCodigoTransacao(), aliquotaVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementInsertAliquota(ps, aliquotaVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_PARAMETRO_LEGISLACAO_ALIQUOTA);
		}
		//catch (LogSefazException e)
		//{
		//  e.printStackTrace();
		// throw new IncluiException(MensagemErro.INCLUIR_PARAMETRO_LEGISLACAO_ALIQUOTA);
		//}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_PARAMETRO_LEGISLACAO_ALIQUOTA);
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
	 * Alimenta o PreparedStatement para a inclusão da Aliquota
	 * @param ps PreparedStatement que será alimentado
	 * @param aliquotaVo Que contém a informação para alimentar o PreparedStatement
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void preparedStatementInsertAliquota(final PreparedStatement ps, final AliquotaVo aliquotaVo) throws ObjetoObrigatorioException, 
			  ConexaoException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(aliquotaVo);
		try
		{
			int contador = 0;
			// CODIGO
			if (Validador.isNumericoValido(aliquotaVo.getCodigo()))
			{
				ps.setLong(++contador, aliquotaVo.getCodigo());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// CODIGO PARAMETRO LEGISLACAO
			if (Validador.isNumericoValido(aliquotaVo.getCodigoParametroLegislacao()))
			{
				ps.setLong(++contador, aliquotaVo.getCodigoParametroLegislacao());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// TIPO FATOR GERADOR
			if (Validador.isDominioNumericoValido(aliquotaVo.getTipoFatoGerador()))
			{
				ps.setInt(++contador, aliquotaVo.getTipoFatoGerador().getValorCorrente());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// TIPO ISENCAO
			if (Validador.isDominioNumericoValido(aliquotaVo.getTipoIsencao()))
			{
				ps.setInt(++contador, aliquotaVo.getTipoIsencao().getValorCorrente());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// PERCENTUAL LEGISLACAO ALIQUOTA
			if (Validador.isNumericoValido(aliquotaVo.getPercentualLegislacaoAliquota()))
			{
				ps.setDouble(++contador, aliquotaVo.getPercentualLegislacaoAliquota());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// QUANTIDADE UPF INICIAL
			if (Validador.isNumericoValido(aliquotaVo.getQuantidadeUPFInicial()))
			{
				ps.setInt(++contador, aliquotaVo.getQuantidadeUPFInicial());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// QUANTIDADE UPF FINAL
			if (Validador.isNumericoValido(aliquotaVo.getQuantidadeUPFFinal()))
			{
				ps.setInt(++contador, aliquotaVo.getQuantidadeUPFFinal());
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
	 * Alimenta o PreparedStatement para a alteração da Aliquota
	 * @param ps PreparedStatement que será alimentado
	 * @param aliquotaVo Que contém a informação para alimentar o PreparedStatement
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void preparedStatementAlterarAliquota(final PreparedStatement ps, final AliquotaVo aliquotaVo) throws ObjetoObrigatorioException, 
			  ConexaoException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(aliquotaVo);
		try
		{
			int contador = 0;			
			// CODIGO PARAMETRO LEGISLACAO
			if (Validador.isNumericoValido(aliquotaVo.getCodigoParametroLegislacao()))
			{
				ps.setLong(++contador, aliquotaVo.getCodigoParametroLegislacao());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// TIPO FATOR GERADOR
			if (Validador.isDominioNumericoValido(aliquotaVo.getTipoFatoGerador()))
			{
				ps.setInt(++contador, aliquotaVo.getTipoFatoGerador().getValorCorrente());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// TIPO ISENCAO
			if (Validador.isDominioNumericoValido(aliquotaVo.getTipoIsencao()))
			{
				ps.setInt(++contador, aliquotaVo.getTipoIsencao().getValorCorrente());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// PERCENTUAL LEGISLACAO ALIQUOTA
			if (Validador.isNumericoValido(aliquotaVo.getPercentualLegislacaoAliquota()))
			{
				ps.setDouble(++contador, aliquotaVo.getPercentualLegislacaoAliquota());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// QUANTIDADE UPF INICIAL
			if (Validador.isNumericoValido(aliquotaVo.getQuantidadeUPFInicial()))
			{
				ps.setInt(++contador, aliquotaVo.getQuantidadeUPFInicial());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			// QUANTIDADE UPF FINAL
			if (Validador.isNumericoValido(aliquotaVo.getQuantidadeUPFFinal()))
			{
				ps.setInt(++contador, aliquotaVo.getQuantidadeUPFFinal());
			}
			else
			{
				ps.setNull(++contador, Types.NUMERIC);
			}
			if (Validador.isNumericoValido(aliquotaVo.getCodigo()))
			{
				ps.setLong(++contador, aliquotaVo.getCodigo());
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
	 * Altera uma Aliquota no Banco de dados
	 * @param aliquotaVo
	 * @throws ObjetoObrigatorioException
	 * @throws AlteraException
	 * @implemented by Fábio Vanzella
	 */
	public void updateAliquota(final AliquotaVo aliquotaVo) throws ObjetoObrigatorioException, AlteraException
	{
		Validador.validaObjeto(aliquotaVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraUpdt(getCamposAliquota(), new String[]{CAMPO_CODIGO_ALIQUOTA});
		try
		{
			GeradorLogSefazMT.gerar(aliquotaVo, DomnOperacao.OPERACAO_UPDATE, aliquotaVo.getNumeroParticao(), aliquotaVo.getCodigoTransacao(), aliquotaVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementAlterarAliquota(ps, aliquotaVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_PARAMETRO_LEGISLACAO_ALIQUOTA);
		}
		//catch (LogSefazException e)
		//{
		//  e.printStackTrace();
		// throw new IncluiException(MensagemErro.ALTERAR_PARAMETRO_LEGISLACAO_ALIQUOTA);
		//}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_PARAMETRO_LEGISLACAO_ALIQUOTA);
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
	 * Método para deletar uma Aliquota do Banco de dados
	 * @param aliquotaVo Aliquota que será deletada
	 * @throws ObjetoObrigatorioException
	 * @throws ExcluiException
	 * @throws LogSefazException
	 * @implemented by Daniel Balieiro
	 */
	public void deleteAliquota(final AliquotaVo aliquotaVo) throws ObjetoObrigatorioException, ExcluiException, 
			  LogSefazException
	{
		Validador.validaObjeto(aliquotaVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraDelt(new String[] { CAMPO_CODIGO_ALIQUOTA });
		try
		{
			GeradorLogSefazMT.gerar(aliquotaVo, DomnOperacao.OPERACAO_DELETE, aliquotaVo.getNumeroParticao(), aliquotaVo.getCodigoTransacao(), aliquotaVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			prepareStatementDeleteAliquota(ps, aliquotaVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ExcluiException(MensagemErro.EXCLUIR_PARAMETRO_LEGISLACAO_ALIQUOTA);
		}
		//catch (LogSefazException e)
		//{
		// e.printStackTrace();
		//throw new ExcluiException(MensagemErro.EXCLUIR_PARAMETRO_LEGISLACAO_ALIQUOTA);
		//	}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new ExcluiException(MensagemErro.EXCLUIR_PARAMETRO_LEGISLACAO_ALIQUOTA);
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
	 * Alimenta o PreparedStatement com os dados da AliquotaVo
	 * @param ps PreparedStatement que será alimentado
	 * @param aliquotaVo AliquotaVo que contém os dados para alimentar o PreparedStatement
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void prepareStatementDeleteAliquota(final PreparedStatement ps, final AliquotaVo aliquotaVo) throws ObjetoObrigatorioException, 
			  ConexaoException
	{
		Validador.validaObjeto(aliquotaVo);
		Validador.validaObjeto(ps);
		int contador = 0;
		try
		{
			if (Validador.isNumericoValido(aliquotaVo.getCodigo()))
			{
				ps.setLong(++contador, aliquotaVo.getCodigo());
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
