/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: ConjugeBemTributavelDao.java
 * Revisão:
 * Data revisão:
 * $Id: ConjugeBemTributavelDao.java,v 1.2 2009/05/05 19:57:22 ricardo.moraes Exp $
 */
package br.gov.mt.sefaz.itc.model.generico.conjuge;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposConjuge;
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
import sefaz.mt.util.UtilStmt;


/**
 * Classe para manutenção de dados do ConjugeBemTributável
 * 
 * @author Marlo Eichenberg Motta
 * @implemented by Lucas Nascimento
 * @version $Revision: 1.2 $
 */
public class ConjugeBemTributavelDao extends AbstractDao implements CamposConjuge, TabelasITC, SequencesITC
{

	/**
	 * Construtor público da classe.
	 * @param conexao objeto de conexão com o banco de dados.
	 * @implemented by Daniel Balieiro
	 */
	public ConjugeBemTributavelDao(Connection conexao)
	{
		super(conexao);
		utilStmt = new UtilStmt(TABELA_CONJUGE_BEM_TRIBUTAVEL);
	}

	/**
	 * Retorna os Campos da Tabela de Conjuge
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	public String[] getCamposBemTributavel()
	{
		return new String[]
					{
						CAMPO_ITCTB18_CODG_ITCD_BEM_TRBT
						, CAMPO_ITCTB17_ITCTB14_CODG_ITCD
						, CAMPO_TIPO_CONJUGE
						, CAMPO_ACCTB01_NUMR_PESS_CONJUGE
						, CAMPO_VALOR_CONJUGE
					};
	}

	/**
	 * Incluir informacoes sobre um conjugem relacionado a um bem tributável no banco de dados.
	 * 
	 * @param conjugeBemTributavelVo VO do conjuge relacionado a um bem tributável
	 * @throws ObjetoObrigatorioException
	 * @throws IncluiException
	 * @implemented by Daniel Balieiro
	 */
	public void insertConjugeBemTributavel(final ConjugeBemTributavelVo conjugeBemTributavelVo) throws ObjetoObrigatorioException, 
			  IncluiException
	{
		Validador.validaObjeto(conjugeBemTributavelVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraInsr(getCamposBemTributavel());
		try
		{
			GeradorLogSefazMT.gerar(conjugeBemTributavelVo, DomnOperacao.OPERACAO_INSERT, conjugeBemTributavelVo.getNumeroParticao(), conjugeBemTributavelVo.getCodigoTransacao(), conjugeBemTributavelVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementConjugeBemTributavelIncluir(ps, conjugeBemTributavelVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (ObjetoObrigatorioException e)
		{
			e.printStackTrace();
			throw e;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_CONJUGE_BEM_TRIBUTAVEL);
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
	 * Metodo responsavel por adicionar os parametros validos na instrucao SQL(java.sql.PreparedStatement)
	 * 
	 * @param ps (java.sql.PreparedStatement)
	 * @param conjugeBemTributavelVo	Vo de Conjugem->Bem Tributável
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @throws ParametroObrigatorioException
	 * @implemented by Lucas Nascimento
	 */
	public void preparedStatementConjugeBemTributavelIncluir(final PreparedStatement ps, final ConjugeBemTributavelVo conjugeBemTributavelVo) throws ObjetoObrigatorioException, 
			  SQLException, ParametroObrigatorioException
	{
		Validador.validaObjeto(conjugeBemTributavelVo);
		int contador = 0;
	   if (Validador.isObjetoValido(conjugeBemTributavelVo.getBemTributavelVo()))
	   {
	      ps.setLong(++contador, conjugeBemTributavelVo.getBemTributavelVo().getCodigo());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.NUMERIC);
	   }
	   if (Validador.isObjetoValido(conjugeBemTributavelVo.getGiaITCDVo()))
	   {
	      ps.setLong(++contador, conjugeBemTributavelVo.getGiaITCDVo().getCodigo());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.NUMERIC);
	   }		
		if (Validador.isDominioNumericoValido(conjugeBemTributavelVo.getTipoConjuge()))
		{
			ps.setLong(++contador, conjugeBemTributavelVo.getTipoConjuge().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		if (Validador.isObjetoValido(conjugeBemTributavelVo.getPessoaConjuge()))
		{
			ps.setLong(++contador, conjugeBemTributavelVo.getPessoaConjuge().getNumrContribuinte().longValue());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	   if (Validador.isNumericoValido(conjugeBemTributavelVo.getValorConjuge()))
	   {
	      ps.setDouble(++contador, conjugeBemTributavelVo.getValorConjuge());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.NUMERIC);
	   }		
	}
	
	/**
	 * Metodo responsavel por adicionar os parametros validos na instrucao SQL(java.sql.PreparedStatement)
	 * 
	 * @param ps (java.sql.PreparedStatement)
	 * @param conjugeBemTributavelVo Vo de Conjugem->Bem Tributável
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @throws ParametroObrigatorioException
	 * @implemented by Lucas Nascimento
	 */
	public void preparedStatementConjugeBemTributavelAlterar(final PreparedStatement ps, final ConjugeBemTributavelVo conjugeBemTributavelVo) throws ObjetoObrigatorioException, 
			  SQLException, ParametroObrigatorioException
	{
		Validador.validaObjeto(conjugeBemTributavelVo);
		int contador = 0;
		if (Validador.isDominioNumericoValido(conjugeBemTributavelVo.getTipoConjuge()))
		{
			ps.setLong(++contador, conjugeBemTributavelVo.getTipoConjuge().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	   if (Validador.isNumericoValido(conjugeBemTributavelVo.getValorConjuge()))
	   {
	      ps.setDouble(++contador, conjugeBemTributavelVo.getValorConjuge());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.NUMERIC);
	   }		
		if (Validador.isObjetoValido(conjugeBemTributavelVo.getBemTributavelVo()))
		{
			ps.setLong(++contador, conjugeBemTributavelVo.getBemTributavelVo().getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		if (Validador.isObjetoValido(conjugeBemTributavelVo.getGiaITCDVo()))
		{
			ps.setLong(++contador, conjugeBemTributavelVo.getGiaITCDVo().getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	   if (Validador.isObjetoValido(conjugeBemTributavelVo.getPessoaConjuge()))
	   {
	      ps.setLong(++contador, conjugeBemTributavelVo.getPessoaConjuge().getNumrContribuinte().longValue());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.NUMERIC);
	   }		
	}	

	/**
	 * Metodo responsavel por adicionar os parametros validos na instrucao REMOVE SQL(java.sql.PreparedStatement)
	 * 
	 * @param ps (java.sql.PreparedStatement)
	 * @param conjugeBemTributavelVo Vo de Conjugem->Bem Tributável
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @throws ParametroObrigatorioException
	 * @implemented by Lucas Nascimento
	 */
	public void preparedStatementRemoveConjugeBemTributavel(final PreparedStatement ps, final ConjugeBemTributavelVo conjugeBemTributavelVo) throws ObjetoObrigatorioException, 
			  SQLException, ParametroObrigatorioException
	{
		Validador.validaObjeto(conjugeBemTributavelVo);
		int contador = 0;
		if (Validador.isNumericoValido(conjugeBemTributavelVo.getBemTributavelVo().getCodigo()))
		{
			ps.setLong(++contador, conjugeBemTributavelVo.getBemTributavelVo().getCodigo());
		}
		else
		{
			throw new ParametroObrigatorioException(MensagemErro.EXCLUIR_CONJUGE_BEM_TRIBUTAVEL);
		}
		if (Validador.isNumericoValido(conjugeBemTributavelVo.getGiaITCDVo().getCodigo()))
		{
			ps.setLong(++contador, conjugeBemTributavelVo.getGiaITCDVo().getCodigo());
		}
		else
		{
			throw new ParametroObrigatorioException(MensagemErro.EXCLUIR_CONJUGE_BEM_TRIBUTAVEL);
		}
	   if (Validador.isObjetoValido(conjugeBemTributavelVo.getPessoaConjuge()))
	   {
	      ps.setLong(++contador, conjugeBemTributavelVo.getPessoaConjuge().getNumrContribuinte().longValue());
	   }
	   else
	   {
	      throw new ParametroObrigatorioException(MensagemErro.EXCLUIR_CONJUGE_BEM_TRIBUTAVEL);
	   }     		
	}

	/**
	 * Atualiza informações sobre um Conjuge relacionado a um bem tributável no banco de dados.
	 * 
	 * @param conjugeBemTributavelVo VO de um Conjuge relacionado a um bem tributável
	 * @throws ObjetoObrigatorioException
	 * @throws AlteraException
	 * @implemented by Daniel Balieiro
	 */
	public void updateConjugeBemTributavel(ConjugeBemTributavelVo conjugeBemTributavelVo) throws ObjetoObrigatorioException, 
			  AlteraException
	{
		Validador.validaObjeto(conjugeBemTributavelVo);
		PreparedStatement ps = null;
		String sql = 
				 utilStmt.geraUpdt(getCamposBemTributavel(), new String[] { CAMPO_ITCTB18_CODG_ITCD_BEM_TRBT, CAMPO_ITCTB17_ITCTB14_CODG_ITCD, CAMPO_ACCTB01_NUMR_PESS_CONJUGE });
		try
		{
			ps = conn.prepareStatement(sql);
			preparedStatementConjugeBemTributavelAlterar(ps, conjugeBemTributavelVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (ObjetoObrigatorioException e)
		{
			e.printStackTrace();
			throw e;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_CONJUGE_BEM_TRIBUTAVEL);
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
	 * Método responsável por remover um registro da tabela de conjuge relacionado a 
	 * bens tributávels
	 *
	 * @param conjugeBemTributavelVo VO usado para 
	 * @throws ObjetoObrigatorioException
	 * @throws ExcluiException
	 */
	public void removeConjugeBemTributavel(final ConjugeBemTributavelVo conjugeBemTributavelVo) throws ObjetoObrigatorioException, 
			  ExcluiException
	{
		Validador.validaObjeto(conjugeBemTributavelVo);
		PreparedStatement ps = null;
		String sql = 
				 utilStmt.geraDelt(new String[] { CAMPO_ITCTB18_CODG_ITCD_BEM_TRBT, CAMPO_ITCTB17_ITCTB14_CODG_ITCD, CAMPO_ACCTB01_NUMR_PESS_CONJUGE });
		try
		{
			ps = conn.prepareStatement(sql);
			preparedStatementRemoveConjugeBemTributavel(ps, conjugeBemTributavelVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (ObjetoObrigatorioException e)
		{
			e.printStackTrace();
			throw e;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new ExcluiException(MensagemErro.EXCLUIR_CONJUGE_BEM_TRIBUTAVEL);
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
}
