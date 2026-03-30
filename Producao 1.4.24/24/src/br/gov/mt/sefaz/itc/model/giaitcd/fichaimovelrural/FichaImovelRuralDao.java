package br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposFichaImovelRural;
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
 * Classe para acesso a dados do Ficha Imóvel Rural (Data Access Object)
 * 
 * @author Daniel Balieiro
 * @version $Revision: 1.3 $
 */
public class FichaImovelRuralDao extends AbstractDao implements TabelasITC, CamposFichaImovelRural, SequencesITC
{
	/**
	 * Construtor que recebe a conexão com o banco dados
	 * 
	 * @param conexao
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralDao(Connection conexao)
	{
		super(conexao);
		utilStmt = new UtilStmt(TABELA_FICHA_IMOVEL_RURAL);
	}

	/**
	 * Retorna os Campos da Tabela de Ficha Imóvel Rural
	 * 
	 * @return String[]
	 * @implemented by Daniel Balieiro
	 */
	private String[] getCampos()
	{
		return new String[] { CAMPO_CODIGO_IMOVEL_RURAL, CAMPO_ACCTB06_CODIGO_ENDERECO, CAMPO_ITCTB18_CODIGO_ITCD_BEM_TRIBUTARIO, CAMPO_DESCRICAO_DENOMINACAO, CAMPO_QUANTIDADE_DISTANCIA, CAMPO_AREA_TOTAL, CAMPO_NUMERICO_INDEA, CAMPO_CODIGO_RECEITA_FEDERAL, CAMPO_SITUACAO_PASTAGEM, CAMPO_AREA_PASTAGEM, CAMPO_VALOR_PASTAGEM, CAMPO_SITUACAO_ACESSAO_NATURAL, CAMPO_VALOR_ACESSAO_NATURAL, CAMPO_VALOR_MERCADO_IMOVEL, CAMPO_VALOR_MAQUINA_EQUIPAMENTO, CAMPO_VALOR_OUTRO, CAMPO_VALOR_ITR,CAMPO_QTDE_DISTANCIA_ASFALTO };
	}

	/**
	 * Método para inserir uma Ficha Imóvel Rural
	 * 
	 * @param fichaImovelRuralVo
	 * @throws ObjetoObrigatorioException
	 * @throws IncluiException
	 * @implemented by Daniel Balieiro
	 */
	public void insertFichaImovelRural(final FichaImovelRuralVo fichaImovelRuralVo) throws ObjetoObrigatorioException, 
			  IncluiException
	{
		Validador.validaObjeto(fichaImovelRuralVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraInsr(getCampos());
		try
		{
			SefazSequencia sequence = new SefazSequencia(conn);
			fichaImovelRuralVo.setCodigo(sequence.next(SEQUENCE_FICHA_IMOVEL_RURAL));
			GeradorLogSefazMT.gerar(fichaImovelRuralVo, DomnOperacao.OPERACAO_INSERT, fichaImovelRuralVo.getNumeroParticao(), fichaImovelRuralVo.getCodigoTransacao(), fichaImovelRuralVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementInsertFichaImovelRural(ps, fichaImovelRuralVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_FICHA_IMOVEL_RURAL);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new IncluiException(MensagemErro.INCLUIR_FICHA_IMOVEL_RURAL);
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
	 * Método para montar o PreparedStatement de acordo com os dados válidos do FichaImovelRuralVo
	 * 
	 * @param ps
	 * @param fichaImovelRuralVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void preparedStatementInsertFichaImovelRural(final PreparedStatement ps, final FichaImovelRuralVo fichaImovelRuralVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(fichaImovelRuralVo);
		int contador = 0;
		// CODIGO
		if (Validador.isNumericoValido(fichaImovelRuralVo.getCodigo()))
		{
			ps.setLong(++contador, fichaImovelRuralVo.getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// CODIGO ENDERECO
		if (Validador.isNumericoValido(fichaImovelRuralVo.getEnderecoVo().getCodgEndereco()))
		{
			ps.setLong(++contador, fichaImovelRuralVo.getEnderecoVo().getCodgEndereco());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// BEM TRIBUTAVEL
		if (Validador.isNumericoValido(fichaImovelRuralVo.getBemTributavelVo().getCodigo()))
		{
			ps.setLong(++contador, fichaImovelRuralVo.getBemTributavelVo().getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// DENOMINACAO
		if (Validador.isStringValida(fichaImovelRuralVo.getDescricaoDenominacao()))
		{
			ps.setString(++contador, fichaImovelRuralVo.getDescricaoDenominacao());
		}
		else
		{
			ps.setNull(++contador, Types.VARCHAR);
		}
		// DISTANCIA
		if (Validador.isNumericoValido(fichaImovelRuralVo.getQuantidadeDistancia()))
		{
			ps.setDouble(++contador, fichaImovelRuralVo.getQuantidadeDistancia());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// AREA TOTAL
		if (Validador.isNumericoValido(fichaImovelRuralVo.getAreaTotal()))
		{
			ps.setDouble(++contador, fichaImovelRuralVo.getAreaTotal());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// INDEA
		if (Validador.isNumericoValido(fichaImovelRuralVo.getNumericoIndea()))
		{
			ps.setLong(++contador, fichaImovelRuralVo.getNumericoIndea());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// CODIGO RECEITA FEDERAL
		if (Validador.isNumericoValido(fichaImovelRuralVo.getCodigoReceitaFederal()))
		{
			ps.setLong(++contador, fichaImovelRuralVo.getCodigoReceitaFederal());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// SITUACAO PASTAGEM
		if (Validador.isDominioNumericoValido(fichaImovelRuralVo.getSituacaoPastagem()))
		{
			ps.setInt(++contador, fichaImovelRuralVo.getSituacaoPastagem().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// AREA PASTAGEM
		if (Validador.isNumericoValido(fichaImovelRuralVo.getAreaPastagem()))
		{
			ps.setDouble(++contador, fichaImovelRuralVo.getAreaPastagem());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// VALOR PASTAGEM
		if (Validador.isNumericoValido(fichaImovelRuralVo.getValorPastagem()))
		{
			ps.setDouble(++contador, fichaImovelRuralVo.getValorPastagem());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// SITUACAO ACESSO NATURAL
		if (Validador.isDominioNumericoValido(fichaImovelRuralVo.getSituacaoAcessaoNatural()))
		{
			ps.setInt(++contador, fichaImovelRuralVo.getSituacaoAcessaoNatural().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// VALOR ACESSO NATURAL
		if (Validador.isNumericoValido(fichaImovelRuralVo.getValorAcessaoNatural()))
		{
			ps.setDouble(++contador, fichaImovelRuralVo.getValorAcessaoNatural());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// VALOR MERCADO IMOVEL
		if (Validador.isNumericoValido(fichaImovelRuralVo.getValorMercadoImovel()))
		{
			ps.setDouble(++contador, fichaImovelRuralVo.getValorMercadoImovel());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// VALOR MAQUINA EQUIPAMENTO
		if (Validador.isNumericoValido(fichaImovelRuralVo.getValorMaquinaEquipamento()))
		{
			ps.setDouble(++contador, fichaImovelRuralVo.getValorMaquinaEquipamento());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// VALOR OUTRO
		if (Validador.isNumericoValido(fichaImovelRuralVo.getValorOutro()))
		{
			ps.setDouble(++contador, fichaImovelRuralVo.getValorOutro());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// VALOR ITR
		if (Validador.isNumericoValido(fichaImovelRuralVo.getValorITR()))
		{
			ps.setDouble(++contador, fichaImovelRuralVo.getValorITR());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	   // DISTANCIA ASFALTO
	   if (Validador.isNumericoValido(fichaImovelRuralVo.getDistanciaAsfalto()))
	   {
	      ps.setDouble(++contador, fichaImovelRuralVo.getDistanciaAsfalto());
	   }
	   {
	      ps.setNull(++contador, Types.NUMERIC);
	   }
	}

	/**
	 * Método para atualizar o Ficha Imóvel Rural
	 * 
	 * @param fichaImovelRuralVo
	 * @throws ObjetoObrigatorioException
	 * @throws AlteraException
	 * @implemented by Daniel Balieiro
	 */
	public void updateFichaImovelRural(FichaImovelRuralVo fichaImovelRuralVo) throws ObjetoObrigatorioException, 
			  AlteraException
	{
		Validador.validaObjeto(fichaImovelRuralVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraUpdt(getCampos(), new String[] { CAMPO_CODIGO_IMOVEL_RURAL });
		try
		{
			GeradorLogSefazMT.gerar(fichaImovelRuralVo, DomnOperacao.OPERACAO_UPDATE, fichaImovelRuralVo.getNumeroParticao(), fichaImovelRuralVo.getCodigoTransacao(), fichaImovelRuralVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementUpdateFichaImovelRural(ps, fichaImovelRuralVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_FICHA_IMOVEL_RURAL);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new AlteraException(MensagemErro.ALTERAR_FICHA_IMOVEL_RURAL);
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
	 * Método para montar o PreparedStatement de acordo com os dados válidos do FichaImovelRuralVo
	 * 
	 * @param ps
	 * @param fichaImovelRuralVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void preparedStatementUpdateFichaImovelRural(final PreparedStatement ps, final FichaImovelRuralVo fichaImovelRuralVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(fichaImovelRuralVo);
		int contador = 0;
		// CODIGO ENDERECO
		if (Validador.isNumericoValido(fichaImovelRuralVo.getEnderecoVo().getCodgEndereco()))
		{
			ps.setLong(++contador, fichaImovelRuralVo.getEnderecoVo().getCodgEndereco());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// BEM TRIBUTAVEL
		if (Validador.isNumericoValido(fichaImovelRuralVo.getBemTributavelVo().getCodigo()))
		{
			ps.setLong(++contador, fichaImovelRuralVo.getBemTributavelVo().getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// DENOMINACAO
		if (Validador.isStringValida(fichaImovelRuralVo.getDescricaoDenominacao()))
		{
			ps.setString(++contador, fichaImovelRuralVo.getDescricaoDenominacao());
		}
		else
		{
			ps.setNull(++contador, Types.VARCHAR);
		}
		// DISTANCIA
		if (Validador.isNumericoValido(fichaImovelRuralVo.getQuantidadeDistancia()))
		{
			ps.setDouble(++contador, fichaImovelRuralVo.getQuantidadeDistancia());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// AREA TOTAL
		if (Validador.isNumericoValido(fichaImovelRuralVo.getAreaTotal()))
		{
			ps.setDouble(++contador, fichaImovelRuralVo.getAreaTotal());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// INDEA
		if (Validador.isNumericoValido(fichaImovelRuralVo.getNumericoIndea()))
		{
			ps.setLong(++contador, fichaImovelRuralVo.getNumericoIndea());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// CODIGO RECEITA FEDERAL
		if (Validador.isNumericoValido(fichaImovelRuralVo.getCodigoReceitaFederal()))
		{
			ps.setLong(++contador, fichaImovelRuralVo.getCodigoReceitaFederal());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// SITUACAO PASTAGEM
		if (Validador.isDominioNumericoValido(fichaImovelRuralVo.getSituacaoPastagem()))
		{
			ps.setInt(++contador, fichaImovelRuralVo.getSituacaoPastagem().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// AREA PASTAGEM
		if (Validador.isNumericoValido(fichaImovelRuralVo.getAreaPastagem()))
		{
			ps.setDouble(++contador, fichaImovelRuralVo.getAreaPastagem());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// VALOR PASTAGEM
		if (Validador.isNumericoValido(fichaImovelRuralVo.getValorPastagem()))
		{
			ps.setDouble(++contador, fichaImovelRuralVo.getValorPastagem());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// SITUACAO ACESSO NATURAL
		if (Validador.isDominioNumericoValido(fichaImovelRuralVo.getSituacaoAcessaoNatural()))
		{
			ps.setInt(++contador, fichaImovelRuralVo.getSituacaoAcessaoNatural().getValorCorrente());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// VALOR ACESSO NATURAL
		if (Validador.isNumericoValido(fichaImovelRuralVo.getValorAcessaoNatural()))
		{
			ps.setDouble(++contador, fichaImovelRuralVo.getValorAcessaoNatural());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// VALOR MERCADO IMOVEL
		if (Validador.isNumericoValido(fichaImovelRuralVo.getValorMercadoImovel()))
		{
			ps.setDouble(++contador, fichaImovelRuralVo.getValorMercadoImovel());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// VALOR MAQUINA EQUIPAMENTO
		if (Validador.isNumericoValido(fichaImovelRuralVo.getValorMaquinaEquipamento()))
		{
			ps.setDouble(++contador, fichaImovelRuralVo.getValorMaquinaEquipamento());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// VALOR OUTRO
		if (Validador.isNumericoValido(fichaImovelRuralVo.getValorOutro()))
		{
			ps.setDouble(++contador, fichaImovelRuralVo.getValorOutro());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// VALOR ITR
		if (Validador.isNumericoValido(fichaImovelRuralVo.getValorITR()))
		{
			ps.setDouble(++contador, fichaImovelRuralVo.getValorITR());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		// CODIGO
		if (Validador.isNumericoValido(fichaImovelRuralVo.getCodigo()))
		{
			ps.setLong(++contador, fichaImovelRuralVo.getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	   // DISTANCIA ASFALTO
	   if (Validador.isNumericoValido(fichaImovelRuralVo.getDistanciaAsfalto()))
	   {
	      ps.setDouble(++contador, fichaImovelRuralVo.getDistanciaAsfalto());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.NUMERIC);
	   }
	}

	/**
	 * Método para deletar uma Ficha Imóvel Rural
	 * 
	 * @param fichaImovelRuralVo
	 * @throws ObjetoObrigatorioException
	 * @throws ExcluiException
	 * @implemented by Daniel Balieiro
	 */
	public void deleteFichaImovelRural(final FichaImovelRuralVo fichaImovelRuralVo) throws ObjetoObrigatorioException, 
			  ExcluiException
	{
		Validador.validaObjeto(fichaImovelRuralVo);
		PreparedStatement ps = null;
		String sql = utilStmt.geraDelt(new String[] { CAMPO_CODIGO_IMOVEL_RURAL });
		try
		{
			if (!Validador.isNumericoValido(fichaImovelRuralVo.getCodigo()))
			{
				throw new ExcluiException(MensagemErro.DELETAR_FICHA_IMOVEL_RURAL);
			}
			GeradorLogSefazMT.gerar(fichaImovelRuralVo, DomnOperacao.OPERACAO_DELETE, fichaImovelRuralVo.getNumeroParticao(), fichaImovelRuralVo.getCodigoTransacao(), fichaImovelRuralVo.getLogSefazVo().getUsuario().getCodigo(), conn);
			ps = conn.prepareStatement(sql);
			preparedStatementDeleteFichaImovelRural(ps, fichaImovelRuralVo);
			if (ps.executeUpdate() != 1)
			{
				throw new SQLException();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ExcluiException(MensagemErro.DELETAR_FICHA_IMOVEL_RURAL);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new ExcluiException(MensagemErro.DELETAR_FICHA_IMOVEL_RURAL);
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
	 * Método para montar o PreparedStatement de acordo com os dados válidos do FichaImovelRuralVo
	 * 
	 * @param ps
	 * @param fichaImovelRuralVo
	 * @throws ObjetoObrigatorioException
	 * @throws SQLException
	 * @implemented by Daniel Balieiro
	 */
	private void preparedStatementDeleteFichaImovelRural(final PreparedStatement ps, final FichaImovelRuralVo fichaImovelRuralVo) throws ObjetoObrigatorioException, 
			  SQLException
	{
		Validador.validaObjeto(ps);
		Validador.validaObjeto(fichaImovelRuralVo);
		int contador = 0;
		if (Validador.isNumericoValido(fichaImovelRuralVo.getCodigo()))
		{
			ps.setLong(++contador, fichaImovelRuralVo.getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
	}
}
