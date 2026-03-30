/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: GIAITCDTemporarioDao.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 10/12/2007
 */
package br.gov.mt.sefaz.itc.model.generico.giaitcdtemporario;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.GeraCodigoAutenticacao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposGIAITCDTemporario;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.io.StringReader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;

import sefaz.mt.log.GeradorLogSefazMT;
import sefaz.mt.log.util.dominios.DomnOperacao;
import sefaz.mt.util.AlteraException;
import sefaz.mt.util.ExcluiException;
import sefaz.mt.util.IncluiException;
import sefaz.mt.util.SefazDataHora;
import sefaz.mt.util.SefazSequencia;
import sefaz.mt.util.UtilStmt;


/**
 * Classe utilizada para realizar manutenção no banco de dados
 * @author Daniel Balieiro
 * @version $Revision: 1.1.1.1 $
 */
public class GIAITCDTemporarioDao extends AbstractDao implements TabelasITC, SequencesITC, CamposGIAITCDTemporario
{
	/**
	 * Construtor que recebe a Conexão com o Banco de Dados.
	 * @param conexao objeto de conexão com o banco de dados.
	 * @implemented by Daniel Balieiro
	 */
	public GIAITCDTemporarioDao(Connection conexao)
	{
		super(conexao);
		utilStmt = new UtilStmt(TABELA_GIA_ITCD_TEMPORARIO);
	}

	/**
	 * Retorna os Campos das Tabela de GIA ITCD Temporário.
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	private String[] getCampos()
	{  //XML
		return new String[] { CAMPO_CODIGO_GIA_ITCD_TEMPORARIO, CAMPO_DATA_CRIACAO, CAMPO_INFO_GIA_TEMPORARIO, CAMPO_STATUS_ITCD, CAMPO_INFO_SENHA, CAMPO_CODIGO_AUTENTICIDADE, CAMPO_DATA_PRAZO_PROTOCOLAR, CAMPO_CODIGO_RESPONSAVEL, CAMPO_GIA_CONFIRMADA, CAMPO_DATA_ATUALIZACAO_BD, CAMPO_INFO_GIA_TEMPORARIO_XML,CAMPO_NUMERO_VERSAO_GIAITCD, CAMPO_TIPO_PROTOCOLO_GIA, CAMPO_SITUACAO_PROCESSAMENTO, CAMPO_DESCRICAO_MENSAGEM_SITUACAO_ERRO };
	}

	/**
	 * Monta o PreparedStatement com os dados do GIAITCDTemporarioVo.
	 * @param ps (java.sql.PreparedStatement).
	 * @param giaITCDTemporarioVo
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Daniel Balieiro
	 */
	private void preparedStatementInsertGIAITCDTemporario(final PreparedStatement ps, final GIAITCDTemporarioVo giaITCDTemporarioVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(giaITCDTemporarioVo);
		int contador = 0;
		// CODIGO
		if (Validador.isNumericoValido(giaITCDTemporarioVo.getCodigo()))
		{
			ps.setLong(++contador, giaITCDTemporarioVo.getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// DATA CRIACAO
		if (Validador.isDataValida(giaITCDTemporarioVo.getDataGIAITCDTemporario()))
		{
			ps.setDate(++contador, new java.sql.Date(giaITCDTemporarioVo.getDataGIAITCDTemporario().getTime()));
		}
		else
		{
			ps.setNull(++contador, Types.DATE);
		}
		// INFO GIA TEMP
		if (giaITCDTemporarioVo.getInputStream() != null)
		{
			ps.setBinaryStream(++contador, giaITCDTemporarioVo.getInputStream(), giaITCDTemporarioVo.getSizeInputStream());
		}
		else
		{
			ps.setNull(++contador, Types.BLOB);
		}
		// STATUS TEMPORARIO
		if (Validador.isDominioNumericoValido(giaITCDTemporarioVo.getStatusITCD().getStatusGIAITCD()))
		{
			ps.setInt(++contador, giaITCDTemporarioVo.getStatusITCD().getStatusGIAITCD().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// INFO SENHA
		if (Validador.isStringValida(giaITCDTemporarioVo.getSenhaGIAITCD()))
		{
			ps.setString(++contador, giaITCDTemporarioVo.getSenhaGIAITCD());
		}
		else
		{
			ps.setNull(++contador, Types.VARCHAR);
		}
		// CODIGO AUTENTICIDADE
		if (Validador.isStringValida(giaITCDTemporarioVo.getCodigoAutenticidade()))
		{
			ps.setString(++contador, giaITCDTemporarioVo.getCodigoAutenticidade());
		}
		else
		{
			ps.setNull(++contador, Types.VARCHAR);
		}
		// DATA PRAZO PROTOCOLAR
		if (Validador.isDataValida(giaITCDTemporarioVo.getPrazoProtocolar()))
		{
			ps.setDate(++contador, new java.sql.Date(giaITCDTemporarioVo.getPrazoProtocolar().getTime()));
		}
		else
		{
			ps.setNull(++contador, Types.DATE);
		}
		// CODIGO RESPONSAVEL
		if (Validador.isNumericoValido(giaITCDTemporarioVo.getCodigoResponsavel()))
		{
			ps.setLong(++contador, giaITCDTemporarioVo.getCodigoResponsavel());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// GIA CONFIRMADA
		if (Validador.isDominioNumericoValido(giaITCDTemporarioVo.getGiaConfirmada()))
		{
			ps.setInt(++contador, giaITCDTemporarioVo.getGiaConfirmada().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.INTEGER);
		}
		// DATA ATUALIZACAO
		ps.setTimestamp(++contador, SefazDataHora.getDataHoraAtual().toSqlTimestamp());
	   // XML
	   if(Validador.isObjetoValido(giaITCDTemporarioVo.getGiaTempXML()))
	   {  
	      ps.setCharacterStream(  ++contador,  new StringReader(  giaITCDTemporarioVo.getGiaTempXML()) , giaITCDTemporarioVo.getGiaTempXML().length() );
	   }else
	   {
	      ps.setNull(++contador, Types.CLOB);
	   }
	   // NUMERO VERSAO GIA
	   if (Validador.isDominioNumericoValido(giaITCDTemporarioVo.getNumeroVersaoGIAITCD()))
	   {
	      ps.setInt(++contador, giaITCDTemporarioVo.getNumeroVersaoGIAITCD().getValorCorrente());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.NULL);
	   }
	   // TIPO PROTOCOLO
	   if (Validador.isDominioNumericoValido(giaITCDTemporarioVo.getTipoProtocoloGIA()))
	   {
	      ps.setInt(++contador, giaITCDTemporarioVo.getTipoProtocoloGIA().getValorCorrente());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.NULL);
	   }
	   // SITC_PROCESSAMENTO
	   if (Validador.isDominioNumericoValido(giaITCDTemporarioVo.getSituacaoProcessamento()))
	   {
	      ps.setInt(++contador, giaITCDTemporarioVo.getSituacaoProcessamento().getValorCorrente());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.INTEGER);
	   }
	   // DESC_MENSAGEM_SITUACAO_ERRO
	   if (Validador.isStringValida(giaITCDTemporarioVo.getDescricaoMensagemSituacaoErrro()))
	   {
	      ps.setString(++contador, giaITCDTemporarioVo.getDescricaoMensagemSituacaoErrro());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.VARCHAR);
	   }
	}

	/**
	 * Insere um GIAITCDTemporarioVo no Banco de Dados.
	 * @param giaITCDTemporarioVo
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws IncluiException Esta exceção deve ser lançada quando o sistema tenta INCLUIR um registro no banco de dados e não consegue.
	 * @implemented by Daniel Balieiro
	 */
	public void insertGIAITCDTemporario(final GIAITCDTemporarioVo giaITCDTemporarioVo) throws ObjetoObrigatorioException, 
			  IncluiException
	{
		Validador.validaObjeto(giaITCDTemporarioVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraInsr(getCampos());
		try
		{
			SefazSequencia sequence = new SefazSequencia(conn);
			giaITCDTemporarioVo.setCodigo(sequence.next(SEQUENCE_GIA_ITCD));
			giaITCDTemporarioVo.setCodigoAutenticidade((new GeraCodigoAutenticacao()).geraCodgAutenticacao(giaITCDTemporarioVo.getCodigo()));
			giaITCDTemporarioVo.setCodigoResponsavel(giaITCDTemporarioVo.getGiaitcdVo().getResponsavelVo().getNumrContribuinte().longValue());
			giaITCDTemporarioVo.setGiaitcdVo(giaITCDTemporarioVo.getGiaitcdVo());
			giaITCDTemporarioVo.getGiaitcdVo().setCodigoAutenticidade(giaITCDTemporarioVo.getCodigoAutenticidade());
			//XML
         //GeradorLogSefazMT.gerar(giaITCDTemporarioVo, DomnOperacao.OPERACAO_INSERT, giaITCDTemporarioVo.getNumeroParticao(), giaITCDTemporarioVo.getCodigoTransacao(), giaITCDTemporarioVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementInsertGIAITCDTemporario(ps, giaITCDTemporarioVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_GIA_ITCD_TEMPORARIO);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_GIA_ITCD_TEMPORARIO);
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
	 * Método para remover uma GIA ITCD Temporária do banco de dados
	 * @param giaITCDTemporarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws ExcluiException
	 * @implemented by Daniel Balieiro
	 */
	public void removeGIAITCDTemporario(final GIAITCDTemporarioVo giaITCDTemporarioVo) throws ObjetoObrigatorioException, 
			  ExcluiException
	{
		Validador.validaObjeto(giaITCDTemporarioVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraDelt(new String[] { CAMPO_CODIGO_GIA_ITCD_TEMPORARIO });
		try
		{
			GeradorLogSefazMT.gerar(giaITCDTemporarioVo, DomnOperacao.OPERACAO_DELETE, giaITCDTemporarioVo.getNumeroParticao(), giaITCDTemporarioVo.getCodigoTransacao(), giaITCDTemporarioVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementDeleteGIAITCDTemporario(ps, giaITCDTemporarioVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ExcluiException(MensagemErro.DELETAR_GIA_ITCD_TEMPORARIO);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new ExcluiException(MensagemErro.DELETAR_GIA_ITCD_TEMPORARIO);
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
	 * Método para montar o Prepare Statement de acordo com os dados válidos da GIA-ITCD Temporária
	 * @param ps
	 * @param giaITCDTemporarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void preparedStatementDeleteGIAITCDTemporario(final PreparedStatement ps, final GIAITCDTemporarioVo giaITCDTemporarioVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(giaITCDTemporarioVo);
		int contador = 0;
		// CODIGO
		if (Validador.isNumericoValido(giaITCDTemporarioVo.getCodigo()))
		{
			ps.setLong(++contador, giaITCDTemporarioVo.getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	}

	/**
	 * Atualiza as informações do GIA ITCD Temporário no Banco de Dados.
	 * @param giaITCDTemporarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws AlteraException
	 * @implemented by Daniel Balieiro
	 */
	public void updateGIAITCDTemporario(final GIAITCDTemporarioVo giaITCDTemporarioVo) throws ObjetoObrigatorioException, 
			  AlteraException
	{
		Validador.validaObjeto(giaITCDTemporarioVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraUpdt(getCampos(), new String[] { CAMPO_CODIGO_GIA_ITCD_TEMPORARIO });
		try
		{
			giaITCDTemporarioVo.getGiaitcdVo().setCodigoAutenticidade(giaITCDTemporarioVo.getCodigoAutenticidade());
			//XML
         //GeradorLogSefazMT.gerar(giaITCDTemporarioVo, DomnOperacao.OPERACAO_UPDATE, giaITCDTemporarioVo.getNumeroParticao(), giaITCDTemporarioVo.getCodigoTransacao(), giaITCDTemporarioVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementUpdateGIAITCDTemporario(ps, giaITCDTemporarioVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_GIA_ITCD_TEMPORARIO);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_GIA_ITCD_TEMPORARIO);
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
	 * Monta o PreparedStatement com os dados do GIAITCDTemporarioVo.
	 * @param ps
	 * @param giaITCDTemporarioVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void preparedStatementUpdateGIAITCDTemporario(final PreparedStatement ps, final GIAITCDTemporarioVo giaITCDTemporarioVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(giaITCDTemporarioVo);
		int contador = 0;
		// DATA CRIACAO
		if (Validador.isDataValida(giaITCDTemporarioVo.getDataGIAITCDTemporario()))
		{
			ps.setDate(++contador, new java.sql.Date(giaITCDTemporarioVo.getDataGIAITCDTemporario().getTime()));
		}
		else
		{
			ps.setNull(++contador, Types.DATE);
		}
		// INFO GIA TEMP
		if (giaITCDTemporarioVo.getInputStream() != null)
		{
			ps.setBinaryStream(++contador, giaITCDTemporarioVo.getInputStream(), giaITCDTemporarioVo.getSizeInputStream());
		}
		else
		{
			ps.setNull(++contador, Types.BLOB);
		}
		// STATUS TEMPORARIO
		if (Validador.isDominioNumericoValido(giaITCDTemporarioVo.getStatusITCD().getStatusGIAITCD()))
		{
			ps.setInt(++contador, giaITCDTemporarioVo.getStatusITCD().getStatusGIAITCD().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// INFO SENHA
		if (Validador.isStringValida(giaITCDTemporarioVo.getSenhaGIAITCD()))
		{
			ps.setString(++contador, giaITCDTemporarioVo.getSenhaGIAITCD());
		}
		else
		{
			ps.setNull(++contador, Types.VARCHAR);
		}
		// CODIGO AUTENTICIDADE
		if (Validador.isStringValida(giaITCDTemporarioVo.getCodigoAutenticidade()))
		{
			ps.setString(++contador, giaITCDTemporarioVo.getCodigoAutenticidade());
		}
		else
		{
			ps.setNull(++contador, Types.VARCHAR);
		}
		// DATA PRAZO PROTOCOLAR
		if (Validador.isDataValida(giaITCDTemporarioVo.getPrazoProtocolar()))
		{
			ps.setDate(++contador, new java.sql.Date(giaITCDTemporarioVo.getPrazoProtocolar().getTime()));
		}
		else
		{
			ps.setNull(++contador, Types.DATE);
		}
		// CODIGO RESPONSAVEL
		if (Validador.isNumericoValido(giaITCDTemporarioVo.getCodigoResponsavel()))
		{
			ps.setLong(++contador, giaITCDTemporarioVo.getCodigoResponsavel());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// GIA CONFIRMADA
		if (Validador.isDominioNumericoValido(giaITCDTemporarioVo.getGiaConfirmada()))
		{
			ps.setInt(++contador, giaITCDTemporarioVo.getGiaConfirmada().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.INTEGER);
		}
		// DATA ATUALIZACAO
		ps.setTimestamp(++contador, SefazDataHora.getDataHoraAtual().toSqlTimestamp());
	   // XML
      if(Validador.isObjetoValido(giaITCDTemporarioVo.getGiaTempXML()))
      {
         StringReader sr =  new StringReader(  giaITCDTemporarioVo.getGiaTempXML());
         ps.setCharacterStream(  ++contador, sr, giaITCDTemporarioVo.getGiaTempXML().length() );
      }else
      {
         ps.setNull(++contador, Types.CLOB);
      }
	   // NUMERO VERSAO GIA
	   if (Validador.isDominioNumericoValido(giaITCDTemporarioVo.getNumeroVersaoGIAITCD()))
	   {
	      ps.setInt(++contador, giaITCDTemporarioVo.getNumeroVersaoGIAITCD().getValorCorrente());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.NULL);
	   }
	   // TIPO PROTOCOLO
	   if (Validador.isDominioNumericoValido(giaITCDTemporarioVo.getTipoProtocoloGIA()))
	   {
	      ps.setInt(++contador, giaITCDTemporarioVo.getTipoProtocoloGIA().getValorCorrente());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.NULL);
	   }
	   // SITC_PROCESSAMENTO
	   if (Validador.isDominioNumericoValido(giaITCDTemporarioVo.getSituacaoProcessamento()))
	   {
	      ps.setInt(++contador, giaITCDTemporarioVo.getSituacaoProcessamento().getValorCorrente());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.INTEGER);
	   }
	   // DESC_MENSAGEM_SITUACAO_ERRO
	   if (Validador.isStringValida(giaITCDTemporarioVo.getDescricaoMensagemSituacaoErrro()))
	   {
	      ps.setString(++contador, giaITCDTemporarioVo.getDescricaoMensagemSituacaoErrro());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.VARCHAR);
	   }
	   // CODIGO *Este campo deve estar SEMPRE com último a ser adicionado por causa da geração de SQL do AbstractDao.utilStmt
	   if (Validador.isNumericoValido(giaITCDTemporarioVo.getCodigo()))
	   {
	      ps.setLong(++contador, giaITCDTemporarioVo.getCodigo());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.NUMERIC);
	   }
      
	}
   
   
   
   /**
    * Atualiza as informações, campos CAMPO_SITUACAO_PROCESSAMENTO e CAMPO_DESCRICAO_MENSAGEM_SITUACAO_ERRO, do GIA ITCD Temporário no Banco de Dados.
    * @param giaITCDTemporarioVo
    * @throws ObjetoObrigatorioException
    * @throws AlteraException
    * @implemented by Dherkyan Ribeiro
    */
   public void updateGIAITCDTemporarioSituacao(final GIAITCDTemporarioVo giaITCDTemporarioVo) throws ObjetoObrigatorioException, 
           AlteraException
   {
      Validador.validaObjeto(giaITCDTemporarioVo);
      PreparedStatement ps = null;
      String sql = utilStmt.geraUpdt( new String[] { CAMPO_SITUACAO_PROCESSAMENTO, CAMPO_DESCRICAO_MENSAGEM_SITUACAO_ERRO }, new String[] { CAMPO_CODIGO_GIA_ITCD_TEMPORARIO });
      try
      {
         giaITCDTemporarioVo.getGiaitcdVo().setCodigoAutenticidade(giaITCDTemporarioVo.getCodigoAutenticidade());
         ps = conn.prepareStatement(sql);
         preparedStatementUpdateGIAITCDTemporarioSituacao(ps, giaITCDTemporarioVo);
         if (ps.executeUpdate() != 1)
         {
            throw new SQLException();
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new AlteraException(MensagemErro.ALTERAR_GIA_ITCD_TEMPORARIO);
      }
      catch (Exception e)
      {
         e.printStackTrace();
         throw new AlteraException(MensagemErro.ALTERAR_GIA_ITCD_TEMPORARIO);
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
    * Monta o PreparedStatement com os dados do GIAITCDTemporarioVo
    * para atualizar os campos SITC_PROCESSAMENTO , DESC_MENSAGEM_SITUACAO_ERRO.
    * @param ps
    * @param giaITCDTemporarioVo
    * @throws ObjetoObrigatorioException
    * @throws SQLException
    * @implemented by Dherkyan Ribeiro
    */
   private void preparedStatementUpdateGIAITCDTemporarioSituacao(final PreparedStatement ps, final GIAITCDTemporarioVo giaITCDTemporarioVo) throws ObjetoObrigatorioException, 
           SQLException
   {
      Validador.validaObjeto(ps);
      Validador.validaObjeto(giaITCDTemporarioVo);
      int contador = 0;
      // SITC_PROCESSAMENTO
      if (Validador.isDominioNumericoValido(giaITCDTemporarioVo.getSituacaoProcessamento()))
      {
         ps.setInt(++contador, giaITCDTemporarioVo.getSituacaoProcessamento().getValorCorrente());
      }
      else
      {
         ps.setNull(++contador, Types.INTEGER);
      }
      // DESC_MENSAGEM_SITUACAO_ERRO
      if (Validador.isStringValida(giaITCDTemporarioVo.getDescricaoMensagemSituacaoErrro()))
      {
         ps.setString(++contador, giaITCDTemporarioVo.getDescricaoMensagemSituacaoErrro());
      }
      else
      {
         ps.setNull(++contador, Types.VARCHAR);
      }
      // CODIGO *Este campo deve estar SEMPRE com último a ser adicionado por causa da geração de SQL do AbstractDao.utilStmt
      if (Validador.isNumericoValido(giaITCDTemporarioVo.getCodigo()))
      {
         ps.setLong(++contador, giaITCDTemporarioVo.getCodigo());
      }
      else
      {
         ps.setNull(++contador, Types.NUMERIC);
      }
   }
   
}
