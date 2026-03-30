package br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelurbano;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposFichaImovelUrbano;
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
 * Classe de acesso a dados (Data Access Object) para a tabela de Imï¿½vel Urbano.
 * @author Lucas Nascimento
 * @version $Revision: 1.3 $
 */
public class FichaImovelUrbanoDao extends AbstractDao implements CamposFichaImovelUrbano, TabelasITC, SequencesITC
{

	/**
	 * Construtor pï¿½blico da classe.
	 * @param conexao objeto de conexão com o banco de dados.
	 */
	public FichaImovelUrbanoDao(Connection conexao)
	{
		super(conexao);
		utilStmt = new UtilStmt(TABELA_IMOVEL_URBANO);
	}

	/**
	 * Retorna os campos da Tabela de Imï¿½vel Urbano
	 * @return String[]
	 * @implemented by Lucas Nascimento
	 */
	private String[] getCamposFichaImovelUrbano()
	{
		return new String[] { CAMPO_CODIGO_IMOVEL_URBANO, CAMPO_ACCTB06_CODIGO_ENDERECO, CAMPO_ITCTB13_CODIGO_CONSTRUCAO, CAMPO_ITCTB18_CODIGO_ITCD_BEM_TRBT, CAMPO_TIPO_CONSERVACAO, CAMPO_QUANTIDADE_AREA_TOTAL, CAMPO_QUANTIDADE_AREA_CONSTRUIDA, CAMPO_TIPO_ACESSO, CAMPO_VALOR_MERCADO_TOTAL, CAMPO_VALOR_VENAL_IPTU, CAMPO_ITCTB54_IPTU, CAMPO_ITCTB57_IPTU_PREFEITURA, CAMPO_VALR_PERC_TRANSMITIDO, CAMPO_VALR_INFORMADO };
	}

	/**
	 * Inclui informações de uma Imï¿½vel Urbano no banco de dados.
	 * @param fichaImovelUrbanoVo VO de Imï¿½vel Urbano
	 * @throws ObjetoObrigatorioException
	 * @throws IncluiException
	 * @implemented by Daniel Balieiro
	 * @implemented by Lucas Nascimento
	 */
	public void insertFichaImovelUrbano(final FichaImovelUrbanoVo fichaImovelUrbanoVo) throws ObjetoObrigatorioException, 
			  IncluiException
	{
		Validador.validaObjeto(fichaImovelUrbanoVo);
		PreparedStatement ps = null;
		String[] campos = getCamposFichaImovelUrbano();
		String sql = utilStmt.geraInsr(campos);
		try
		{
			GeradorLogSefazMT.gerar(fichaImovelUrbanoVo, DomnOperacao.OPERACAO_INSERT, fichaImovelUrbanoVo.getNumeroParticao(), fichaImovelUrbanoVo.getCodigoTransacao(), fichaImovelUrbanoVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			SefazSequencia sequence = new SefazSequencia(conn);
			fichaImovelUrbanoVo.setCodigo(sequence.next(SEQUENCE_IMOVEL_URBANO));
			ps = conn.prepareStatement(sql);
			preparedStatementInsertFichaImovelUrbano(ps, fichaImovelUrbanoVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_IMOVEL_URBANO);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_IMOVEL_URBANO);
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
	 * Método responï¿½vel por adicionar os parâmetros vï¿½lidos na instruï¿½ï¿½o SQL 
	 * @param ps (java.sql.PreparedStatement)
	 * @param fichaImovelUrbanoVo Value Object (VO)
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @throws ParametroObrigatorioException 
	 * @implemented by Lucas Nascimento
	 */
	private void preparedStatementInsertFichaImovelUrbano(final PreparedStatement ps, final FichaImovelUrbanoVo fichaImovelUrbanoVo) throws ObjetoObrigatorioException, 
			  SQLException, ParametroObrigatorioException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(fichaImovelUrbanoVo);
		int contador = 0;
		//	CAMPO_CODIGO_IMOVEL_URBANO,
		if (Validador.isNumericoValido(fichaImovelUrbanoVo.getCodigo()))
		{
			ps.setLong(++contador, fichaImovelUrbanoVo.getCodigo());
		}
		else
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_IMOVEL_URBANO_PARAMETRO_CODIGO);
		}
		//	CAMPO_ACCTB06_CODIGO_ENDERECO,
		if (Validador.isNumericoValido(fichaImovelUrbanoVo.getEnderecoVo().getCodgEndereco()))
		{
			ps.setLong(++contador, fichaImovelUrbanoVo.getEnderecoVo().getCodgEndereco());
		}
		else
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_IMOVEL_URBANO_PARAMETRO_CODIGO_ENDERECO);
		}
		//	CAMPO_ITCTB13_CODIGO_CONSTRUCAO,
		if (Validador.isNumericoValido(fichaImovelUrbanoVo.getConstrucaoVo().getCodigo()))
		{
			ps.setLong(++contador, fichaImovelUrbanoVo.getConstrucaoVo().getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		//	CAMPO_ITCTB18_CODIGO_ITCD_BEM_TRBT,
		if (Validador.isNumericoValido(fichaImovelUrbanoVo.getBemTributavelVo().getCodigo()))
		{
			ps.setLong(++contador, fichaImovelUrbanoVo.getBemTributavelVo().getCodigo());
		}
		else
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_IMOVEL_URBANO_PARAMETRO_CODIGO_BEM_TRBT);
		}
		//	CAMPO_ESTADO_CONSERVACAO,
		if (Validador.isDominioNumericoValido(fichaImovelUrbanoVo.getEstadoConservacao()))
		{
			ps.setLong(++contador, fichaImovelUrbanoVo.getEstadoConservacao().getValorCorrente());
		}
		else
		{
		   ps.setNull(++contador, Types.NUMERIC);
		}
		//	CAMPO_QUANTIDADE_AREA_TOTAL,
		if (Validador.isNumericoValido(fichaImovelUrbanoVo.getQuantidadeAreaTotal()))
		{
			ps.setDouble(++contador, fichaImovelUrbanoVo.getQuantidadeAreaTotal());
		}
		else
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_IMOVEL_URBANO_PARAMETRO_QUANTIDADE_AREA_TOTAL);
		}
		//	CAMPO_QUANTIDADE_AREA_CONSTRUIDA,
		if (Validador.isNumericoValido(fichaImovelUrbanoVo.getQuantidadeAreaConstruida()))
		{
			ps.setDouble(++contador, fichaImovelUrbanoVo.getQuantidadeAreaConstruida());
		}
		else
		{
		   ps.setNull(++contador, Types.NUMERIC);
		}
		//	CAMPO_TIPO_ACESSO,
		if (Validador.isDominioNumericoValido(fichaImovelUrbanoVo.getTipoAcesso()))
		{
			ps.setLong(++contador, fichaImovelUrbanoVo.getTipoAcesso().getValorCorrente());
		}
		else
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_IMOVEL_URBANO_PARAMETRO_TIPO_ACESSO);
		}
		//	CAMPO_VALOR_MERCADO_TOTAL,
		if (Validador.isNumericoValido(fichaImovelUrbanoVo.getValorMercadoTotal()))
		{
			ps.setDouble(++contador, fichaImovelUrbanoVo.getValorMercadoTotal());
		}
		else
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_IMOVEL_URBANO_PARAMETRO_VALOR_MERCADO_TOTAL);
		}
		//	CAMPO_VALOR_VENAL_IPTU
		if (Validador.isNumericoValido(fichaImovelUrbanoVo.getValorVenalIptu()))
		{
			ps.setDouble(++contador, fichaImovelUrbanoVo.getValorVenalIptu());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC );
		}
	   //CAMPO_ITCTB54_IPTU
	   if (Validador.isNumericoValido(fichaImovelUrbanoVo.getIptuVo().getCodigo()))
	   {
	      ps.setLong(++contador, fichaImovelUrbanoVo.getIptuVo().getCodigo());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.NUMERIC);
	   }
      // CAMPO_ITCTB57_IPTU_PREFEITURA
       if (Validador.isNumericoValido(fichaImovelUrbanoVo.getIptuPrefeituraVo().getCodigo()))
       {
          ps.setLong(++contador, fichaImovelUrbanoVo.getIptuPrefeituraVo().getCodigo());
       }
       else
       {
          ps.setNull(++contador, Types.NUMERIC);
       }
      //VALR_PERC_TRANSMITIDO
      if (Validador.isNumericoValido(fichaImovelUrbanoVo.getValorPercentualTransmitido()))
      {
         ps.setDouble(++contador, fichaImovelUrbanoVo.getValorPercentualTransmitido());
      }
      else
      {
         ps.setNull(++contador, Types.NUMERIC);
      }
      //VALR_TOTAL
      if (Validador.isNumericoValido(fichaImovelUrbanoVo.getValorInfomado()))
      {
         ps.setDouble(++contador, fichaImovelUrbanoVo.getValorInfomado());
      }
      else
      {
         ps.setNull(++contador, Types.NUMERIC);
      }
       
	}

	/**
	 * Método para atualizar uma Ficha Imï¿½vel Urbano no banco de dados.
	 * 
	 * @param fichaImovelUrbanoVo
	 * @throws ObjetoObrigatorioException
	 * @throws AlteraException
	 * @implemented by Daniel Balieiro
	 * @implemented by Lucas Nascimento
	 */
	public void updateFichaImovelUrbano(FichaImovelUrbanoVo fichaImovelUrbanoVo) throws ObjetoObrigatorioException, 
			  AlteraException
	{
		Validador.validaObjeto(fichaImovelUrbanoVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraUpdt(getCamposFichaImovelUrbano(), new String[] { CAMPO_CODIGO_IMOVEL_URBANO });
		try
		{
			GeradorLogSefazMT.gerar(fichaImovelUrbanoVo, DomnOperacao.OPERACAO_UPDATE, fichaImovelUrbanoVo.getNumeroParticao(), fichaImovelUrbanoVo.getCodigoTransacao(), fichaImovelUrbanoVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementUpdateFichaImovelUrbano(ps, fichaImovelUrbanoVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_FICHA_IMOVEL_URBANO);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_FICHA_IMOVEL_URBANO);
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
	 * Método que monta o PreparedStatement de acordo com os dados vï¿½lidos da FichaImovelUrbanoVo
	 * 
	 * @param ps
	 * @param fichaImovelUrbanoVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 * @implemented by Lucas Nascimento
	 */
	private void preparedStatementUpdateFichaImovelUrbano(final PreparedStatement ps, final FichaImovelUrbanoVo fichaImovelUrbanoVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(fichaImovelUrbanoVo);
		int contador = 0;
		//	CAMPO_ACCTB06_CODIGO_ENDERECO,
		if (Validador.isNumericoValido(fichaImovelUrbanoVo.getEnderecoVo().getCodgEndereco()))
		{
			ps.setLong(++contador, fichaImovelUrbanoVo.getEnderecoVo().getCodgEndereco());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		//	CAMPO_ITCTB13_CODIGO_CONSTRUCAO,
		if (Validador.isNumericoValido(fichaImovelUrbanoVo.getConstrucaoVo().getCodigo()))
		{
			ps.setLong(++contador, fichaImovelUrbanoVo.getConstrucaoVo().getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		//	CAMPO_ITCTB18_CODIGO_ITCD_BEM_TRBT,
		if (Validador.isNumericoValido(fichaImovelUrbanoVo.getBemTributavelVo().getCodigo()))
		{
			ps.setLong(++contador, fichaImovelUrbanoVo.getBemTributavelVo().getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		//	CAMPO_ESTADO_CONSERVACAO,
		if (Validador.isDominioNumericoValido(fichaImovelUrbanoVo.getEstadoConservacao()))
		{
			ps.setLong(++contador, fichaImovelUrbanoVo.getEstadoConservacao().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		//	CAMPO_QUANTIDADE_AREA_TOTAL,
		if (Validador.isNumericoValido(fichaImovelUrbanoVo.getQuantidadeAreaTotal()))
		{
			ps.setDouble(++contador, fichaImovelUrbanoVo.getQuantidadeAreaTotal());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		//	CAMPO_QUANTIDADE_AREA_CONSTRUIDA,
		if (Validador.isNumericoValido(fichaImovelUrbanoVo.getQuantidadeAreaConstruida()))
		{
			ps.setDouble(++contador, fichaImovelUrbanoVo.getQuantidadeAreaConstruida());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		//	CAMPO_TIPO_ACESSO,
		if (Validador.isDominioNumericoValido(fichaImovelUrbanoVo.getTipoAcesso()))
		{
			ps.setLong(++contador, fichaImovelUrbanoVo.getTipoAcesso().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		//	CAMPO_VALOR_MERCADO_TOTAL,
		if (Validador.isNumericoValido(fichaImovelUrbanoVo.getValorMercadoTotal()))
		{
			ps.setDouble(++contador, fichaImovelUrbanoVo.getValorMercadoTotal());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		//	CAMPO_VALOR_VENAL_IPTU
		if (Validador.isNumericoValido(fichaImovelUrbanoVo.getValorVenalIptu()))
		{
			ps.setDouble(++contador, fichaImovelUrbanoVo.getValorVenalIptu());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		//	CAMPO_CODIGO_IMOVEL_URBANO,
		if (Validador.isNumericoValido(fichaImovelUrbanoVo.getCodigo()))
		{
			ps.setLong(++contador, fichaImovelUrbanoVo.getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
      //CAMPO_ITCTB54_IPTU
       if (Validador.isNumericoValido(fichaImovelUrbanoVo.getIptuVo().getCodigo()))
       {
          ps.setLong(++contador, fichaImovelUrbanoVo.getIptuVo().getCodigo());
       }
       else
       {
          ps.setNull(++contador, Types.NUMERIC);
       }
	    //VALR_PERC_TRANSMITIDO
	    if (Validador.isNumericoValido(fichaImovelUrbanoVo.getValorPercentualTransmitido()))
	    {
	      ps.setDouble(++contador, fichaImovelUrbanoVo.getValorPercentualTransmitido());
	    }
	    else
	    {
	      ps.setNull(++contador, Types.NUMERIC);
	    }
	    //VALR_TOTAL
	    if (Validador.isNumericoValido(fichaImovelUrbanoVo.getValorInfomado()))
	    {
	      ps.setDouble(++contador, fichaImovelUrbanoVo.getValorInfomado());
	    }
	    else
	    {
	      ps.setNull(++contador, Types.NUMERIC);
	    }
	}

	/**
	 * Método para deletar uma Ficha Imï¿½vel Urbano do banco de dados
	 * 
	 * @param fichaImovelUrbanoVo
	 * @throws ObjetoObrigatorioException
	 * @throws ExcluiException
	 * @implemented by Daniel Balieiro
	 * @implemented by Lucas Nascimento
	 */
	public void deleteFichaImovelUrbano(final FichaImovelUrbanoVo fichaImovelUrbanoVo) throws ObjetoObrigatorioException, 
			  ExcluiException
	{
		Validador.validaObjeto(fichaImovelUrbanoVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraDelt(new String[] { CAMPO_CODIGO_IMOVEL_URBANO });
		try
		{
			GeradorLogSefazMT.gerar(fichaImovelUrbanoVo, DomnOperacao.OPERACAO_DELETE, fichaImovelUrbanoVo.getNumeroParticao(), fichaImovelUrbanoVo.getCodigoTransacao(), fichaImovelUrbanoVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementDeleteFichaImovelUrbano(ps, fichaImovelUrbanoVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ExcluiException(MensagemErro.EXCLUIR_IMOVEL_URBANO);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new ExcluiException(MensagemErro.EXCLUIR_IMOVEL_URBANO);
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
	 * Método que monta o PreparedStatement de acordo com os dados vï¿½lidos do FichaImovelUrbanoVo
	 * 
	 * @param ps
	 * @param fichaImovelUrbanoVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @throws ParametroObrigatorioException
	 * @implemented by Daniel Balieiro
	 * @implemented by Lucas Nascimento
	 */
	private void preparedStatementDeleteFichaImovelUrbano(final PreparedStatement ps, final FichaImovelUrbanoVo fichaImovelUrbanoVo) throws ObjetoObrigatorioException, 
			  SQLException, ParametroObrigatorioException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(fichaImovelUrbanoVo);
		int contador = 0;
		//	CAMPO_CODIGO_IMOVEL_URBANO_BENFEITORIA,
		if (Validador.isNumericoValido(fichaImovelUrbanoVo.getCodigo()))
		{
			ps.setLong(++contador, fichaImovelUrbanoVo.getCodigo());
		}
		else
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_IMOVEL_URBANO_PARAMETRO_CODIGO);
		}
	}
}
