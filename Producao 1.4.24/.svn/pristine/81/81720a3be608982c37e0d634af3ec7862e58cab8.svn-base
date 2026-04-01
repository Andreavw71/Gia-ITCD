/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: GIAITCDDao.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 10/12/2007
 */
package br.gov.mt.sefaz.itc.model.generico.giaitcd;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposGIAITCD;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

import java.util.Date;

import sefaz.mt.log.GeradorLogSefazMT;
import sefaz.mt.log.util.dominios.DomnOperacao;
import sefaz.mt.util.AlteraException;
import sefaz.mt.util.IncluiException;
import sefaz.mt.util.SefazSequencia;
import sefaz.mt.util.UtilStmt;


/**
 * Classe para acesso a dados do GIA ITCD (Data Access Object).
 * @author Daniel Balieiro
 * @version $Revision: 1.2 $
 */
public class GIAITCDDao extends AbstractDao implements CamposGIAITCD, TabelasITC, SequencesITC
{
	/**
	 * Construtor que recebe a Conexão com o Banco de Dados.
	 * @param conexao objeto de conexão com o banco de dados.
	 * @implemented by Daniel Balieiro
	 */
	public GIAITCDDao(Connection conexao)
	{
		super(conexao);
		utilStmt = new UtilStmt(TABELA_GIA_ITCD);
	}

	/**
	 * Retorna os Campos do GIA ITCD.
	 * @return String[]
	 * @implemented by Daniel Balieiro
	 */
	public String[] getGIAITCDCampos()
	{
		return new String[] 
		{ 
			CAMPO_CODIGO_GIA_ITCD
			,CAMPO_ACCTB01_NUMERO_PESSOA_RESPONSAVEL
			,CAMPO_ACCTB01_NUMERO_PESSOA_PROCURADOR
			,CAMPO_ITCTB03_CODIGO_NATUREZA_OPERACAO
			,CAMPO_ITCTB09_CODIGO_LEGISLACAO
			,CAMPO_DATA_CRIACAO
			,CAMPO_TIPO_ITCD
			,CAMPO_INFO_SENHA
			,CAMPO_CODIGO_AUTENTICIDADE
			,CAMPO_VALOR_TOTAL_BENS
			,CAMPO_VALOR_UPF
			,CAMPO_VALOR_CALCULO_DEMONSTRATIVO
			,CAMPO_VALOR_ITCD
			,CAMPO_VALOR_TOTAL_RECOLHER
			,CAMPO_VALOR_TSE
			,CAMPO_MUNICIPIO_PROTOCOLAR
			,CAMPO_POSSUI_CPF
			,CAMPO_NUMR_DECL_FATO_GERADOR
			,CAMPO_NUMR_DECL_ISENCAO
			,CAMPO_SERVIDOR_RESPONSAVEL_ALTERACAO
			,CAMPO_JUSTIFICATIVA_ALTERACAO			
			,CAMPO_DATA_ATUALIZACAO_BD 
		};
	}

	/**
	 * Método para Inserir o GIA ITCD.
	 * @param giaitcdVo
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws IncluiException Esta exceção deve ser lançada quando o sistema tenta INCLUIR um registro no banco de dados e não consegue.
	 * @implemented by Daniel Balieiro
	 */
	public void insertGIAITCD(final GIAITCDVo giaitcdVo) throws ObjetoObrigatorioException, IncluiException
	{
		Validador.validaObjeto(giaitcdVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraInsr(getGIAITCDCampos());
		try
		{
			if (!Validador.isNumericoValido(giaitcdVo.getCodigo()))
			{
				if (Validador.isNumericoValido(giaitcdVo.getTemporarioVo().getCodigo()))
				{
					giaitcdVo.setCodigo(giaitcdVo.getTemporarioVo().getCodigo());
				}
				else
				{
					throw new IncluiException(MensagemErro.INCLUIR_GIA_ITCD);
				}
			}
			giaitcdVo.setDataAtualizacaoBD(new Date());
			GeradorLogSefazMT.gerar(giaitcdVo, DomnOperacao.OPERACAO_INSERT, giaitcdVo.getNumeroParticao(), giaitcdVo.getCodigoTransacao(), giaitcdVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementInsertGIAITCD(ps, giaitcdVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			e.getCause().printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_GIA_ITCD);
		}
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
	 * Monta o Prepare Statemant para Inserir um GIA ITCD.
	 * @param ps (java.sql.PreparedStatement).
	 * @param giaitcdVo
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Daniel Balieiro
	 */
	private void preparedStatementInsertGIAITCD(final PreparedStatement ps, final GIAITCDVo giaitcdVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(giaitcdVo);
		int contador = 0;
		// CODIGO
		if (Validador.isNumericoValido(giaitcdVo.getCodigo()))
		{
			ps.setLong(++contador, giaitcdVo.getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// PESSOA RESPONSAVEL
		if (Validador.isNumericoValido(giaitcdVo.getResponsavelVo().getNumrContribuinte()))
		{
			ps.setLong(++contador, giaitcdVo.getResponsavelVo().getNumrContribuinte().longValue());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// PESSOA PROCURADOR
		if (Validador.isNumericoValido(giaitcdVo.getProcuradorVo().getNumrContribuinte()))
		{
			ps.setLong(++contador, giaitcdVo.getProcuradorVo().getNumrContribuinte().longValue());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// NATUREZA DA OPERACAO
		if (Validador.isNumericoValido(giaitcdVo.getNaturezaOperacaoVo().getCodigo()))
		{
			ps.setLong(++contador, giaitcdVo.getNaturezaOperacaoVo().getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// PARAMETRO LEGISLACAO
		if (Validador.isNumericoValido(giaitcdVo.getParametroLegislacaoVo().getCodigo()))
		{
			ps.setLong(++contador, giaitcdVo.getParametroLegislacaoVo().getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// DATA CRIACAO
		if (Validador.isDataValida(giaitcdVo.getDataCriacao()))
		{
			ps.setDate(++contador, new java.sql.Date(giaitcdVo.getDataCriacao().getTime()));
		}
		else
		{
			ps.setNull(++contador, Types.DATE);
		}
		// TIPO ITCD
		if (Validador.isDominioNumericoValido(giaitcdVo.getTipoGIA()))
		{
			ps.setInt(++contador, giaitcdVo.getTipoGIA().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// INFO SENHA
		if (Validador.isStringValida(giaitcdVo.getSenha()))
		{
			ps.setString(++contador, giaitcdVo.getSenha());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// CODIGO AUTENTICIDADE
		if (Validador.isStringValida(giaitcdVo.getCodigoAutenticidade()))
		{
			ps.setString(++contador, giaitcdVo.getCodigoAutenticidade());
		}
		else
		{
			ps.setNull(++contador, Types.VARCHAR);
		}
		// VALOR TOTAL BENS
		if (Validador.isNumericoValido(giaitcdVo.getValorTotalBensDeclarados()))
		{
			ps.setDouble(++contador, giaitcdVo.getValorTotalBensDeclarados());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// VALOR UPF
		if (Validador.isNumericoValido(giaitcdVo.getValorUPF()))
		{
			ps.setDouble(++contador, giaitcdVo.getValorUPF());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// VALOR CALCULO DEMONSTRATIVO
		if (Validador.isNumericoValido(giaitcdVo.getValorCalculoDemonstrativo()))
		{
			ps.setDouble(++contador, giaitcdVo.getValorCalculoDemonstrativo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// VALOR ITCD
		if (Validador.isNumericoValido(giaitcdVo.getValorITCD()))
		{
			ps.setDouble(++contador, giaitcdVo.getValorITCD());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// VALOR TOTAL A RECEBER
		if (Validador.isNumericoValido(giaitcdVo.getValorRecolhimento()))
		{
			ps.setDouble(++contador, giaitcdVo.getValorRecolhimento());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// VALOR TSE
		if (Validador.isNumericoValido(giaitcdVo.getValorTSE()))
		{
			ps.setDouble(++contador, giaitcdVo.getValorTSE());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// MUNICIPIO PROTOCOLAR
		if (Validador.isNumericoValido(giaitcdVo.getMunicipioProtocolar().getCodgMunicipio()))
		{
			ps.setInt(++contador, giaitcdVo.getMunicipioProtocolar().getCodgMunicipio().intValue());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// POSSUI CPF
		if (Validador.isDominioNumericoValido(giaitcdVo.getPossuiCPF()))
		{
			ps.setInt(++contador, giaitcdVo.getPossuiCPF().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// NUMR_DECL_FATO_GERADOR
		if (Validador.isNumericoValido(giaitcdVo.getNumrDeclaracaoFatoGerador()))
		{
			ps.setLong(++contador, giaitcdVo.getNumrDeclaracaoFatoGerador());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// NUMR_DECL_ISENCAO
		if (Validador.isNumericoValido(giaitcdVo.getNumrDeclaracaoIsencao()))
		{
			ps.setLong(++contador, giaitcdVo.getNumrDeclaracaoIsencao());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	   if (Validador.isNumericoValido(giaitcdVo.getServidorSefazResponsavelAlteracaoVo().getNumrMatricula()))
	   {
	      ps.setLong(++contador, giaitcdVo.getServidorSefazResponsavelAlteracaoVo().getNumrMatricula().longValue());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.NUMERIC);
	   }
		if(Validador.isStringValida(giaitcdVo.getJustificativaAlteracao()))
		{
			ps.setString(++contador, giaitcdVo.getJustificativaAlteracao());
		}
		else
		{
			ps.setNull(++contador, Types.VARCHAR);
		}
		// DATA ATUALIAZAO BD
		ps.setTimestamp(++contador, new Timestamp(giaitcdVo.getDataAtualizacaoBD().getTime()));
	}

	/**
	 * Método responsável por atualizar a GIAITCD.
	 * @param giaitcdVo
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws IncluiException Esta exceção deve ser lançada quando o sistema tenta INCLUIR um registro no banco de dados e não consegue.
	 * @implemented by Marlo Eichenberg Motta
	 */
	public void updateGIAITCD(GIAITCDVo giaitcdVo) throws ObjetoObrigatorioException, AlteraException
	{
		Validador.validaObjeto(giaitcdVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraUpdt(getGIAITCDCampos(), new String[] { CAMPO_CODIGO_GIA_ITCD });
		try
		{
			if (!Validador.isNumericoValido(giaitcdVo.getCodigo()))
			{
				throw new AlteraException(MensagemErro.INCLUIR_GIA_ITCD);
			}
			GeradorLogSefazMT.gerar(giaitcdVo, DomnOperacao.OPERACAO_UPDATE, giaitcdVo.getNumeroParticao(), giaitcdVo.getCodigoTransacao(), giaitcdVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			giaitcdVo.setDataAtualizacaoBD(new Date());
			ps = conn.prepareStatement(sql);
			preparedStatementUpdateGIAITCD(ps, giaitcdVo);
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
	 * Monta o Prepare Statemant para Alterar uma GIA ITCD.
	 * @param ps (java.sql.PreparedStatement).
	 * @param giaitcdVo
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Daniel Balieiro
	 */
	private void preparedStatementUpdateGIAITCD(final PreparedStatement ps, final GIAITCDVo giaitcdVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(giaitcdVo);
		int contador = 0;
		// PESSOA RESPONSAVEL
		if (Validador.isNumericoValido(giaitcdVo.getResponsavelVo().getNumrContribuinte()))
		{
			ps.setLong(++contador, giaitcdVo.getResponsavelVo().getNumrContribuinte().longValue());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// PESSOA PROCURADOR
		if (Validador.isNumericoValido(giaitcdVo.getProcuradorVo().getNumrContribuinte()))
		{
			ps.setLong(++contador, giaitcdVo.getProcuradorVo().getNumrContribuinte().longValue());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// NATUREZA DA OPERACAO
		if (Validador.isNumericoValido(giaitcdVo.getNaturezaOperacaoVo().getCodigo()))
		{
			ps.setLong(++contador, giaitcdVo.getNaturezaOperacaoVo().getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// PARAMETRO LEGISLACAO
		if (Validador.isNumericoValido(giaitcdVo.getParametroLegislacaoVo().getCodigo()))
		{
			ps.setLong(++contador, giaitcdVo.getParametroLegislacaoVo().getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// DATA CRIACAO
		if (Validador.isDataValida(giaitcdVo.getDataCriacao()))
		{
			ps.setDate(++contador, new java.sql.Date(giaitcdVo.getDataCriacao().getTime()));
		}
		else
		{
			ps.setNull(++contador, Types.DATE);
		}
		// TIPO ITCD
		if (Validador.isDominioNumericoValido(giaitcdVo.getTipoGIA()))
		{
			ps.setInt(++contador, giaitcdVo.getTipoGIA().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// INFO SENHA
		if (Validador.isStringValida(giaitcdVo.getSenha()))
		{
			ps.setString(++contador, giaitcdVo.getSenha());
		}
		else
		{
			ps.setNull(++contador, Types.VARCHAR);
		}
		// CODIGO AUTENTICIDADE
		if (Validador.isStringValida(giaitcdVo.getCodigoAutenticidade()))
		{
			ps.setString(++contador, giaitcdVo.getCodigoAutenticidade());
		}
		else
		{
			ps.setNull(++contador, Types.VARCHAR);
		}
		// VALOR TOTAL BENS
		if (Validador.isNumericoValido(giaitcdVo.getValorTotalBensDeclarados()))
		{
			ps.setDouble(++contador, giaitcdVo.getValorTotalBensDeclarados());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// VALOR UPF
		if (Validador.isNumericoValido(giaitcdVo.getValorUPF()))
		{
			ps.setDouble(++contador, giaitcdVo.getValorUPF());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// VALOR CALCULO DEMONSTRATIVO
		if (Validador.isNumericoValido(giaitcdVo.getValorCalculoDemonstrativo()))
		{
			ps.setDouble(++contador, giaitcdVo.getValorCalculoDemonstrativo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// VALOR ITCD
		if (Validador.isNumericoValido(giaitcdVo.getValorITCD()))
		{
			ps.setDouble(++contador, giaitcdVo.getValorITCD());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// VALOR TOTAL A RECEBER
		if (Validador.isNumericoValido(giaitcdVo.getValorRecolhimento()))
		{
			ps.setDouble(++contador, giaitcdVo.getValorRecolhimento());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// VALOR TSE
		if (Validador.isNumericoValido(giaitcdVo.getValorTSE()))
		{
			ps.setDouble(++contador, giaitcdVo.getValorTSE());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// MUNICIPIO PROTOCOLAR
		if (Validador.isNumericoValido(giaitcdVo.getMunicipioProtocolar().getCodgMunicipio()))
		{
			ps.setInt(++contador, giaitcdVo.getMunicipioProtocolar().getCodgMunicipio().intValue());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// POSSUI CPF
		if (Validador.isDominioNumericoValido(giaitcdVo.getPossuiCPF()))
		{
			ps.setInt(++contador, giaitcdVo.getPossuiCPF().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// NUMR_DECL_FATO_GERADOR
		if (Validador.isNumericoValido(giaitcdVo.getNumrDeclaracaoFatoGerador()))
		{
			ps.setLong(++contador, giaitcdVo.getNumrDeclaracaoFatoGerador());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// NUMR_DECL_ISENCAO
		if (Validador.isNumericoValido(giaitcdVo.getNumrDeclaracaoIsencao()))
		{
			ps.setLong(++contador, giaitcdVo.getNumrDeclaracaoIsencao());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	   if (Validador.isNumericoValido(giaitcdVo.getServidorSefazResponsavelAlteracaoVo().getNumrMatricula()))
	   {
	      ps.setLong(++contador, giaitcdVo.getServidorSefazResponsavelAlteracaoVo().getNumrMatricula().longValue());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.NUMERIC);
	   }     
		if(Validador.isStringValida(giaitcdVo.getJustificativaAlteracao()))
		{
			ps.setString(++contador, giaitcdVo.getJustificativaAlteracao());
		}
		else
		{
			ps.setNull(++contador, Types.VARCHAR);
		}
		// DATA ATUALIAZAO BD
		ps.setTimestamp(++contador, new Timestamp(giaitcdVo.getDataAtualizacaoBD().getTime()));
		// CODIGO
		if (Validador.isNumericoValido(giaitcdVo.getCodigo()))
		{
			ps.setLong(++contador, giaitcdVo.getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	}

	/**
	 * Método que atualiza a Data de Atualização BD
	 * @param giaitcdVo
	 * @throws ObjetoObrigatorioException
	 * @throws AlteraException
	 * @implemented by Daniel Balieiro
	 */
	public void updateGIAITCDDataAtualizacaoBD(GIAITCDVo giaitcdVo) throws ObjetoObrigatorioException, AlteraException
	{
		Validador.validaObjeto(giaitcdVo);
		PreparedStatement ps = null;
		String sql = 
				 utilStmt.geraUpdt(new String[] { CAMPO_DATA_ATUALIZACAO_BD }, new String[] { CAMPO_CODIGO_GIA_ITCD });
		try
		{
			if (!Validador.isNumericoValido(giaitcdVo.getCodigo()))
			{
				throw new AlteraException(MensagemErro.ALTERAR_GIA_ITCD);
			}
			GeradorLogSefazMT.gerar(giaitcdVo, DomnOperacao.OPERACAO_UPDATE, giaitcdVo.getNumeroParticao(), giaitcdVo.getCodigoTransacao(), giaitcdVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			giaitcdVo.setDataAtualizacaoBD(new Date());
			preparedStatementUpdateDataAtualizacaoGIAITCD(ps, giaitcdVo);
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
	 * Monta o Prepare Statemant para Atualizar a Data de Atualização BD de uma GIA ITCD.
	 * @param ps (java.sql.PreparedStatement).
	 * @param giaitcdVo
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Daniel Balieiro
	 */
	private void preparedStatementUpdateDataAtualizacaoGIAITCD(final PreparedStatement ps, final GIAITCDVo giaitcdVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(giaitcdVo);
		int contador = 0;
		// DATA ATUALIAZAO BD
		ps.setTimestamp(++contador, new Timestamp(giaitcdVo.getDataAtualizacaoBD().getTime()));
		// CODIGO
		if (Validador.isNumericoValido(giaitcdVo.getCodigo()))
		{
			ps.setLong(++contador, giaitcdVo.getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	}

	/**
	 * Método que atualiza o Numero Declaração de Isenção
	 * @param giaitcdVo
	 * @throws ObjetoObrigatorioException
	 * @throws AlteraException
	 * @implemented by Lucas Nascimento
	 */
	public void updateGIAITCDDeclaracaoIsensao(GIAITCDVo giaitcdVo) throws ObjetoObrigatorioException, AlteraException
	{
		Validador.validaObjeto(giaitcdVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraUpdt(new String[] { CAMPO_NUMR_DECL_ISENCAO }, new String[] { CAMPO_CODIGO_GIA_ITCD });
		try
		{
			if (!Validador.isNumericoValido(giaitcdVo.getCodigo()))
			{
				throw new AlteraException(MensagemErro.ALTERAR_GIA_ITCD);
			}
			SefazSequencia sequence = new SefazSequencia(conn);
			giaitcdVo.setNumrDeclaracaoIsencao(sequence.next(SEQUENCE_GIA_ITCD_DECLARACAO_ISENCAO));
			GeradorLogSefazMT.gerar(giaitcdVo, DomnOperacao.OPERACAO_UPDATE, giaitcdVo.getNumeroParticao(), giaitcdVo.getCodigoTransacao(), giaitcdVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementUpdateDeclaracaoIsencaoGIAITCD(ps, giaitcdVo);
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
	 * Monta o Prepare Statemant para Atualizar o Numero de Declaração de Isenção de uma GIA ITCD.
	 * @param ps (java.sql.PreparedStatement).
	 * @param giaitcdVo
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Lucas Nascimento
	 */
	private void preparedStatementUpdateDeclaracaoIsencaoGIAITCD(final PreparedStatement ps, final GIAITCDVo giaitcdVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(giaitcdVo);
		int contador = 0;
		// CAMPO_DECLARACAO_ISENCAO
		if (Validador.isNumericoValido(giaitcdVo.getNumrDeclaracaoIsencao()))
		{
			ps.setLong(++contador, giaitcdVo.getNumrDeclaracaoIsencao());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// CODIGO
		if (Validador.isNumericoValido(giaitcdVo.getCodigo()))
		{
			ps.setLong(++contador, giaitcdVo.getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	}

	/**
	 * Método que atualiza o Numero Declaração de Não Ocorrencia de Fato Gerador
	 * @param giaitcdVo
	 * @throws ObjetoObrigatorioException
	 * @throws AlteraException
	 * @implemented by Lucas Nascimento
	 */
	public void updateGIAITCDDeclaracaoOcorrenciaFatoGerador(GIAITCDVo giaitcdVo) throws ObjetoObrigatorioException, 
			  AlteraException
	{
		Validador.validaObjeto(giaitcdVo);
		PreparedStatement ps = null;
		String sql = 
				 utilStmt.geraUpdt(new String[] { CAMPO_NUMR_DECL_FATO_GERADOR }, new String[] { CAMPO_CODIGO_GIA_ITCD });
		try
		{
			if (!Validador.isNumericoValido(giaitcdVo.getCodigo()))
			{
				throw new AlteraException(MensagemErro.ALTERAR_GIA_ITCD);
			}
			GeradorLogSefazMT.gerar(giaitcdVo, DomnOperacao.OPERACAO_UPDATE, giaitcdVo.getNumeroParticao(), giaitcdVo.getCodigoTransacao(), giaitcdVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			SefazSequencia sequence = new SefazSequencia(conn);
			giaitcdVo.setNumrDeclaracaoFatoGerador(sequence.next(SEQUENCE_GIA_ITCD_DECLARACAO_FATO_GERADOR));
			ps = conn.prepareStatement(sql);
			preparedStatementUpdateDeclaracaoFatoGeradorGIAITCD(ps, giaitcdVo);
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
	 * Monta o Prepare Statemant para Atualizar o Numero de Declaração de Não Ocorrência de Fato Gerador de uma GIA ITCD.
	 * @param ps (java.sql.PreparedStatement).
	 * @param giaitcdVo
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Lucas Nascimento
	 */
	private void preparedStatementUpdateDeclaracaoFatoGeradorGIAITCD(final PreparedStatement ps, final GIAITCDVo giaitcdVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(giaitcdVo);
		int contador = 0;
		// CAMPO_DECLARACAO_FATO_GERADOR
		if (Validador.isNumericoValido(giaitcdVo.getNumrDeclaracaoFatoGerador()))
		{
			ps.setLong(++contador, giaitcdVo.getNumrDeclaracaoFatoGerador());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// CODIGO
		if (Validador.isNumericoValido(giaitcdVo.getCodigo()))
		{
			ps.setLong(++contador, giaitcdVo.getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	}
}
