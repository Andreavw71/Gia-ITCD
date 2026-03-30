package br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelurbano.benfeitoria;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposFichaImovelUrbanoBenfeitoria;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import sefaz.mt.log.GeradorLogSefazMT;
import sefaz.mt.log.util.dominios.DomnOperacao;
import sefaz.mt.util.AlteraException;
import sefaz.mt.util.ExcluiException;
import sefaz.mt.util.IncluiException;
import sefaz.mt.util.SefazSequencia;
import sefaz.mt.util.UtilStmt;


/**
 * Classe de acesso a dados (Data Access Object) para a tabela de Imóvel Urbano Benfeitoria.
 * @author Lucas Nascimento
 * @version $Revision: 1.2 $
 */
public class FichaImovelUrbanoBenfeitoriaDao extends AbstractDao implements CamposFichaImovelUrbanoBenfeitoria, TabelasITC, SequencesITC
{
	/**
	 * Construtor público da classe
	 * @implemented by Lucas Nascimento
	 */
	public FichaImovelUrbanoBenfeitoriaDao(Connection conexao)
	{
		super(conexao);
		utilStmt = new UtilStmt(TABELA_IMOVEL_URBANO_BENFEITORIA);
	}

	/**
	 * Retorna os campos da Tabela Imóvel Urbano Benfeitoria
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	private String[] getCamposFichaImovelUrbanoBenfeitoria()
	{
		return new String[] { CAMPO_CODIGO_IMOVEL_URBANO_BENFEITORIA, CAMPO_ITCTB19_CODIGO_IMOVEL_URBANO, CAMPO_ITCTB07_CODIGO_BENFEITORIA, CAMPO_DESCRICAO_COMPLEMENTAR_BENFEITORIA };
	}

	/**
	 * Método para inserir uma Ficha Imóvel Urbano - Benfeitoria no banco de dados
	 * 
	 * @param fichaImovelUrbanoBenfeitoriaVo
	 * @throws ObjetoObrigatorioException
	 * @throws IncluiException
	 * @implemented by Daniel Balieiro
	 * @implemented by Lucas Nascimento
	 */
	public void insertFichaImovelUrbanoBenfeitoria(final FichaImovelUrbanoBenfeitoriaVo fichaImovelUrbanoBenfeitoriaVo) throws ObjetoObrigatorioException, 
			  IncluiException
	{
		Validador.validaObjeto(fichaImovelUrbanoBenfeitoriaVo);
		PreparedStatement ps = null;
		String[] campos = getCamposFichaImovelUrbanoBenfeitoria();
		String sql = utilStmt.geraInsr(campos);
		try
		{
			GeradorLogSefazMT.gerar(fichaImovelUrbanoBenfeitoriaVo, DomnOperacao.OPERACAO_INSERT, fichaImovelUrbanoBenfeitoriaVo.getNumeroParticao(), fichaImovelUrbanoBenfeitoriaVo.getCodigoTransacao(), fichaImovelUrbanoBenfeitoriaVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			SefazSequencia sequence = new SefazSequencia(conn);
			fichaImovelUrbanoBenfeitoriaVo.setCodigo(sequence.next(SEQUENCE_IMOVEL_URBANO_BENFEITORIA));
			ps = conn.prepareStatement(sql);
			preparedStatementInsertFichaImovelUrbanoBenfeitoria(ps, fichaImovelUrbanoBenfeitoriaVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_IMOVEL_URBANO_BENFEITORIA);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_IMOVEL_URBANO_BENFEITORIA);
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
	 * Método responável por adicionar os parâmetros válidos na instrução SQL 
	 * @param ps (java.sql.PreparedStatement)
	 * @param fichaImovelUrbanoBenfeitoriaVo Value Object (VO)
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @throws ParametroObrigatorioException 
	 * @implemented by Lucas Nascimento
	 */
	private void preparedStatementInsertFichaImovelUrbanoBenfeitoria(final PreparedStatement ps, final FichaImovelUrbanoBenfeitoriaVo fichaImovelUrbanoBenfeitoriaVo) throws ObjetoObrigatorioException, 
			  SQLException, ParametroObrigatorioException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(fichaImovelUrbanoBenfeitoriaVo);
		int contador = 0;
		//	CAMPO_CODIGO_IMOVEL_URBANO_BENFEITORIA,
		if (Validador.isNumericoValido(fichaImovelUrbanoBenfeitoriaVo.getCodigo()))
		{
			ps.setLong(++contador, fichaImovelUrbanoBenfeitoriaVo.getCodigo());
		}
		else
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_IMOVEL_URBANO_BENFEITORIA_PARAMETRO_CODIGO);
		}
		//	CAMPO_ITCTB19_CODIGO_IMOVEL_URBANO,
		if (Validador.isNumericoValido(fichaImovelUrbanoBenfeitoriaVo.getFichaImovelUrbanoVo().getCodigo()))
		{
			ps.setLong(++contador, fichaImovelUrbanoBenfeitoriaVo.getFichaImovelUrbanoVo().getCodigo());
		}
		else
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_IMOVEL_URBANO_BENFEITORIA_PARAMETRO_CODIGO_IMOVEL);
		}
		//	CAMPO_ITCTB07_CODIGO_BENFEITORIA
		if (Validador.isNumericoValido(fichaImovelUrbanoBenfeitoriaVo.getBenfeitoriaVo().getCodigo()))
		{
			ps.setLong(++contador, fichaImovelUrbanoBenfeitoriaVo.getBenfeitoriaVo().getCodigo());
		}
		else
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_IMOVEL_URBANO_BENFEITORIA_PARAMETRO_CODIGO_BENFEITORIA);
		}
		//CAMPO_DESCRICAO_COMPLEMENTAR_BENFEITORIA
		if(Validador.isStringValida(fichaImovelUrbanoBenfeitoriaVo.getDescricaoComplementarBenfeitoria()))
		{
			ps.setString(++contador, fichaImovelUrbanoBenfeitoriaVo.getDescricaoComplementarBenfeitoria());
		}
		else
		{
			ps.setNull(++contador, Types.VARCHAR);
		}
	}

	/**
	 * Método para alterar uma Ficha Imóvel Urbano - Benfeitoria
	 * 
	 * @param fichaImovelUrbanoBenfeitoriaVo
	 * @throws ObjetoObrigatorioException
	 * @throws AlteraException
	 * @implemented by Daniel Balieiro
	 * @implemented by Lucas Nascimento
	 */
	public void updateFichaImovelUrbanoBenfeitoria(final FichaImovelUrbanoBenfeitoriaVo fichaImovelUrbanoBenfeitoriaVo) throws ObjetoObrigatorioException, 
			  AlteraException
	{
		Validador.validaObjeto(fichaImovelUrbanoBenfeitoriaVo);
		PreparedStatement ps = null;
		String sql = 
				 utilStmt.geraUpdt(getCamposFichaImovelUrbanoBenfeitoria(), new String[] { CAMPO_CODIGO_IMOVEL_URBANO_BENFEITORIA });
		try
		{
			GeradorLogSefazMT.gerar(fichaImovelUrbanoBenfeitoriaVo, DomnOperacao.OPERACAO_UPDATE, fichaImovelUrbanoBenfeitoriaVo.getNumeroParticao(), fichaImovelUrbanoBenfeitoriaVo.getCodigoTransacao(), fichaImovelUrbanoBenfeitoriaVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementUpdateFichaImovelUrbanoBenfeitoria(ps, fichaImovelUrbanoBenfeitoriaVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_FICHA_IMOVEL_URBANO_BENFEITORIA);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_FICHA_IMOVEL_URBANO_BENFEITORIA);
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
	 * Método para montar o PreparedStatement de acordo com os dados válidos do FichaImovelUrbanoBenfeitoriaVo
	 * 
	 * @param ps
	 * @param fichaImovelUrbanoBenfeitoriaVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 * @implemented by Lucas Nascimento
	 */
	private void preparedStatementUpdateFichaImovelUrbanoBenfeitoria(final PreparedStatement ps, FichaImovelUrbanoBenfeitoriaVo fichaImovelUrbanoBenfeitoriaVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(fichaImovelUrbanoBenfeitoriaVo);
		int contador = 0;
		//	CAMPO_ITCTB19_CODIGO_IMOVEL_URBANO,
		if (Validador.isNumericoValido(fichaImovelUrbanoBenfeitoriaVo.getFichaImovelUrbanoVo().getCodigo()))
		{
			ps.setLong(++contador, fichaImovelUrbanoBenfeitoriaVo.getFichaImovelUrbanoVo().getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		//	CAMPO_ITCTB07_CODIGO_BENFEITORIA
		if (Validador.isNumericoValido(fichaImovelUrbanoBenfeitoriaVo.getBenfeitoriaVo().getCodigo()))
		{
			ps.setLong(++contador, fichaImovelUrbanoBenfeitoriaVo.getBenfeitoriaVo().getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// CAMPO_DESCRICAO_COMPLEMENTAR_BENFEITORIA
		if(Validador.isStringValida(fichaImovelUrbanoBenfeitoriaVo.getDescricaoComplementarBenfeitoria()))
		{
			ps.setString(++contador, fichaImovelUrbanoBenfeitoriaVo.getDescricaoComplementarBenfeitoria());
		}
		else
		{
			ps.setNull(++contador, Types.VARCHAR);
		}
		//	CAMPO_CODIGO_IMOVEL_URBANO_BENFEITORIA,
		if (Validador.isNumericoValido(fichaImovelUrbanoBenfeitoriaVo.getCodigo()))
		{
			ps.setLong(++contador, fichaImovelUrbanoBenfeitoriaVo.getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	}

	/**
	 * Método para deletar uma Ficha Imóvel Urbano - Benfeitoria
	 * 
	 * @param fichaImovelUrbanoBenfeitoriaVo
	 * @throws ObjetoObrigatorioException
	 * @throws ExcluiException
	 * @implemented by Daniel Balieiro
	 * @implemented by Lucas Nascimento
	 */
	public void deleteFichaImovelUrbanoBenfeitoria(final FichaImovelUrbanoBenfeitoriaVo fichaImovelUrbanoBenfeitoriaVo) throws ObjetoObrigatorioException, 
			  ExcluiException
	{
		Validador.validaObjeto(fichaImovelUrbanoBenfeitoriaVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraDelt(new String[] { CAMPO_CODIGO_IMOVEL_URBANO_BENFEITORIA });
		try
		{
			GeradorLogSefazMT.gerar(fichaImovelUrbanoBenfeitoriaVo, DomnOperacao.OPERACAO_DELETE, fichaImovelUrbanoBenfeitoriaVo.getNumeroParticao(), fichaImovelUrbanoBenfeitoriaVo.getCodigoTransacao(), fichaImovelUrbanoBenfeitoriaVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementDeleteFichaImovelUrbanoBenfeitoria(ps, fichaImovelUrbanoBenfeitoriaVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ExcluiException(MensagemErro.EXCLUIR_IMOVEL_URBANO_BENFEITORIA);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new ExcluiException(MensagemErro.EXCLUIR_IMOVEL_URBANO_BENFEITORIA);
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
	 * Método para montar o PreparedStatement de acordo com os dados válidos do FichaImovelUrbanoBenfeitoriaVo
	 * 
	 * @param ps
	 * @param fichaImovelUrbanoBenfeitoriaVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @throws ParametroObrigatorioException
	 * @implemented by Daniel Balieiro
	 * @implemented by Lucas Nascimento
	 */
	private void preparedStatementDeleteFichaImovelUrbanoBenfeitoria(final PreparedStatement ps, final FichaImovelUrbanoBenfeitoriaVo fichaImovelUrbanoBenfeitoriaVo) throws ObjetoObrigatorioException, 
			  SQLException, ParametroObrigatorioException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(fichaImovelUrbanoBenfeitoriaVo);
		int contador = 0;
		//	CAMPO_CODIGO_IMOVEL_URBANO_BENFEITORIA,
		if (Validador.isNumericoValido(fichaImovelUrbanoBenfeitoriaVo.getCodigo()))
		{
			ps.setLong(++contador, fichaImovelUrbanoBenfeitoriaVo.getCodigo());
		}
		else
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_IMOVEL_URBANO_BENFEITORIA_PARAMETRO_CODIGO);
		}
	}
}
