package br.gov.mt.sefaz.itc.model.generico.beneficiario;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposBeneficiario;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;
import br.gov.mt.sefaz.itc.util.sql.PreparedStatementUtils;

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
 * Classe para manutenção de dados da entidade Beneficiario no Banco de Dados
 * @author Daniel Balieiro
 * @version $Revision: 1.4 $
 */
public class BeneficiarioDao extends AbstractDao implements TabelasITC, SequencesITC, CamposBeneficiario
{
	/**
	 * Construtor que recebe a Conexão com o Banco de Dados
	 * @param conexao
	 * @implemented by Daniel Balieiro
	 */
	public BeneficiarioDao(Connection conexao)
	{
		super(conexao);
		utilStmt = new UtilStmt(TABELA_BENEFICIARIO);
	}

	/**
	 * Retorna os Campos da Tabela de Beneficiario
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	private String[] getCampos()
	{
		return new String[]
		{ 
			CAMPO_CODIGO_BENEFICIARIO
			,CAMPO_ITCTB14_CODIGO_GIA_ITCD
			,CAMPO_ACCTB01_NUMERO_PESSOA_BENEFICIARIA
			,CAMPO_VALOR_RECEBIDO
         ,CAMPO_INFO_DOACAO_SUCESSIVA
         ,CAMPO_VALR_RECB_DOACAO_SUCESSIVA
         ,CAMPO_INFO_DOACAO_ANTR_PROT_MANUAL
         ,CAMPO_VALR_ITCD_BENF
		};
	}	

	/**
	 * Insere um BeneficiarioVo no Banco de Dados
	 * @param beneficiarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws IncluiException
	 * @implemented by Daniel Balieiro
	 */
	public void insertBeneficiario(final BeneficiarioVo beneficiarioVo) throws ObjetoObrigatorioException, 
			  IncluiException
	{
		Validador.validaObjeto(beneficiarioVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraInsr(getCampos());
		try
		{
			SefazSequencia sequence = new SefazSequencia(conn);
			beneficiarioVo.setCodigo(sequence.next(SEQUENCE_BENEFICIARIO));
			GeradorLogSefazMT.gerar(beneficiarioVo, DomnOperacao.OPERACAO_INSERT, beneficiarioVo.getNumeroParticao(), beneficiarioVo.getCodigoTransacao(), beneficiarioVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
		   preparedStatementUpdateInsetBeneficiario(ps, beneficiarioVo, 1);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_BENEFICIARIO);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_BENEFICIARIO);
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
	 * Altera um BeneficiarioVo no Banco de Dados
	 * @param beneficiarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws IncluiException
	 * @implemented by Daniel Balieiro
	 */
	public void updateBeneficiario(final BeneficiarioVo beneficiarioVo) throws ObjetoObrigatorioException, 
			  AlteraException
	{
		Validador.validaObjeto(beneficiarioVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraUpdt(getCampos(), new String[] { CAMPO_CODIGO_BENEFICIARIO });
		try
		{
			GeradorLogSefazMT.gerar(beneficiarioVo, DomnOperacao.OPERACAO_UPDATE, beneficiarioVo.getNumeroParticao(), beneficiarioVo.getCodigoTransacao(), beneficiarioVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
		   preparedStatementUpdateInsetBeneficiario(ps, beneficiarioVo, 2);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_BENEFICIARIO);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_BENEFICIARIO);
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

	 private void preparedStatementUpdateInsetBeneficiario(final PreparedStatement ps, final BeneficiarioVo beneficiarioVo, Integer tipo) throws ObjetoObrigatorioException, SQLException {
         Validador.validaObjeto(ps);
         Validador.validaObjeto(beneficiarioVo);

         int contador = 0;
         if (tipo == 1) { // Insert
            PreparedStatementUtils.setLongOrNull(ps, ++contador, beneficiarioVo.getCodigo());  
         }
         PreparedStatementUtils.setLongOrNull(ps, ++contador, beneficiarioVo.getGiaITCDVo().getCodigo());
         PreparedStatementUtils.setLongOrNull(ps, ++contador, beneficiarioVo.getPessoaBeneficiaria().getNumrContribuinte().longValue());
         PreparedStatementUtils.setDoubleOrNull(ps, ++contador, beneficiarioVo.getValorRecebidoSemDoacaoSucessiva());
         PreparedStatementUtils.setIntOrNull(ps, ++contador, beneficiarioVo.getFlagDoacaoSucessiva());
         PreparedStatementUtils.setDoubleOrNull(ps, ++contador, beneficiarioVo.getValorRecebidoDoacaoSucessiva());
         PreparedStatementUtils.setIntOrNull(ps, ++contador, beneficiarioVo.getFlagDoacaoAnteriorManualEprocess());
         PreparedStatementUtils.setDoubleOrNull(ps, ++contador, beneficiarioVo.getValorItcdBeneficiario());
         if (tipo == 2) { // Update
            PreparedStatementUtils.setLongOrNull(ps, ++contador, beneficiarioVo.getCodigo());   
         }	    
	    }

	public void deleteBeneficiario(final BeneficiarioVo beneficiarioVo) throws ObjetoObrigatorioException, 
			  ExcluiException
	{
		Validador.validaObjeto(beneficiarioVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraDelt(new String[] { CAMPO_CODIGO_BENEFICIARIO });
		try
		{
			GeradorLogSefazMT.gerar(beneficiarioVo, DomnOperacao.OPERACAO_DELETE, beneficiarioVo.getNumeroParticao(), beneficiarioVo.getCodigoTransacao(), beneficiarioVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementDeleteBeneficiario(ps, beneficiarioVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ExcluiException(MensagemErro.ALTERAR_BENEFICIARIO);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new ExcluiException(MensagemErro.ALTERAR_BENEFICIARIO);
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

	private void preparedStatementDeleteBeneficiario(final PreparedStatement ps, final BeneficiarioVo beneficiarioVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(beneficiarioVo);
		int contador = 0;
		// CODIGO
		if (Validador.isNumericoValido(beneficiarioVo.getCodigo()))
		{
			ps.setLong(++contador, beneficiarioVo.getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	}	
}
