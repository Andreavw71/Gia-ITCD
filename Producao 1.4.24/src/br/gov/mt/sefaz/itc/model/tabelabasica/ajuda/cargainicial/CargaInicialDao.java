package br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.cargainicial;

import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.generico.util.sql.AbstractDao;
import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.campoajuda.CampoAjudaVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.funcionalidade.FuncionalidadeVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.moduloajuda.ModuloAjudaVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.telaajuda.TelaAjudaVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.telacampoajuda.TelaCampoAjudaVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.telafuncionalidade.TelaFuncionalidadeVo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;


public class CargaInicialDao extends AbstractDao
{
	
	public CargaInicialDao(Connection conn)
	{
		super(conn);
	}

	public void inserirModulo(ModuloAjudaVo modulo) throws SQLException
	{
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO ITCTB36_MODULO_AJUDA VALUES (?, ?, ?, ?, ?)");
		try
		{
			ps = conn.prepareStatement(sql.toString());	
		   preparedStatementInsertModulo(ps,modulo);
		   if (ps.executeUpdate() != 1)
		   {
		      throw new SQLException();
		   }			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("Erro método inserirModulo, não foi possível inserir modulo: "+modulo.getDescricaoModuloAjuda());
		   throw e;
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
	         throw e;
	      }
	   }		
	}

	private void preparedStatementInsertModulo(PreparedStatement ps, ModuloAjudaVo modulo) throws SQLException
	{
		int contador = 0;
		ps.setLong(++contador, modulo.getCodigo());
		if(Validador.isObjetoValido(modulo.getSubModuloAjuda()) && Validador.isNumericoValido(modulo.getSubModuloAjuda().getCodigo()))
		{
			ps.setLong(++contador, modulo.getSubModuloAjuda().getCodigo());
		}
		else
		{
			ps.setNull(++contador, Types.NUMERIC);
		}
		ps.setString(++contador, modulo.getDescricaoModuloAjuda());
		ps.setInt(++contador, modulo.getStatusModuloAjuda().getValorCorrente());
		ps.setLong(++contador, modulo.getCodigoOrdenacao());
	}

	public void deletarAjuda(String tabela) throws SQLException
	{
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM ").append(tabela);
		try
		{
			ps = conn.prepareStatement(sql.toString()); 
			ps.executeUpdate();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			System.out.println("Não foi possível limpar tabela: "+tabela);
		   throw e;
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
			   throw e;
			}
		}     
	}

	public void inserirFuncionalidade(FuncionalidadeVo funcionalidade) throws SQLException
	{
	   PreparedStatement ps = null;
	   StringBuffer sql = new StringBuffer();
	   sql.append("INSERT INTO ITCTB37_FUNCIONALIDADE VALUES (?, ?, ?, ?)");
	   try
	   {
	      ps = conn.prepareStatement(sql.toString());  
	      preparedStatementInsertFuncionalidade(ps,funcionalidade);
	      if (ps.executeUpdate() != 1)
	      {
	         throw new SQLException();
	      }        
	   }
	   catch(SQLException e)
	   {
	      e.printStackTrace();
	      System.out.println("Erro método inserirFuncionalidade, não foi possível inserir Funcionalidade: "+funcionalidade.getDescricaoFuncionalidade());
	      throw e;
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
	         throw e;
	      }
	   }     
	
	}

	private void preparedStatementInsertFuncionalidade(PreparedStatement ps, FuncionalidadeVo funcionalidade) throws SQLException
	{
		int contador = 0;
	   ps.setLong(++contador, funcionalidade.getCodigo());
	   if(Validador.isObjetoValido(funcionalidade.getModuloAjudaVo()) && Validador.isNumericoValido(funcionalidade.getModuloAjudaVo().getCodigo()))
	   {
	      ps.setLong(++contador, funcionalidade.getModuloAjudaVo().getCodigo());
	   }
	   else
	   {
	      ps.setNull(++contador, Types.NUMERIC);
	   }
	   ps.setString(++contador, funcionalidade.getDescricaoFuncionalidade());
	   ps.setInt(++contador, funcionalidade.getStatusFuncionalidade().getValorCorrente());	
	}

	public void inserirTelas(TelaAjudaVo tela) throws SQLException
	{
	   PreparedStatement ps = null;
	   StringBuffer sql = new StringBuffer();
	   sql.append("INSERT INTO ITCTB34_TELA_AJUDA VALUES (?, ?)");
	   try
	   {
	      ps = conn.prepareStatement(sql.toString());  
	      preparedStatementInsertTela(ps,tela);
	      if (ps.executeUpdate() != 1)
	      {
	         throw new SQLException();
	      }        
	   }
	   catch(SQLException e)
	   {
	      e.printStackTrace();
	      System.out.println("Erro método inserirTelas, não foi possível inserir Tela: "+tela.getDescricaoTelaAjuda());
	      throw e;
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
	         throw e;
	      }
	   }     
	}

	private void preparedStatementInsertTela(PreparedStatement ps, TelaAjudaVo tela) throws SQLException
	{
		int contador = 0;
		ps.setLong(++contador, tela.getCodigo());
		ps.setString(++contador, tela.getDescricaoTelaAjuda());
	}

	public void inserirTelaFuncionalidade(TelaFuncionalidadeVo telaFuncionalidade) throws SQLException
	{
	   PreparedStatement ps = null;
	   StringBuffer sql = new StringBuffer();
	   sql.append("INSERT INTO ITCTB38_TELA_FUNCIONALIDADE VALUES (?, ?, ?, ?, ?, SYSDATE, ?)");
	   try
	   {
	      ps = conn.prepareStatement(sql.toString());  
	      preparedStatementInsertTelaFuncionalidade(ps,telaFuncionalidade);
	      if (ps.executeUpdate() != 1)
	      {
	         throw new SQLException();
	      }        
	   }
	   catch(SQLException e)
	   {
	      e.printStackTrace();
	      System.out.println("Erro método inserirTelaFuncionalidade, não foi possível inserir Relacionamento da Tela: "+telaFuncionalidade.getTelaAjudaVo().getCodigo() + " com funcionalidade: " + telaFuncionalidade.getFuncionalidadeVo().getCodigo());
	      throw e;
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
	         throw e;
	      }
	   }     

	}

	private void preparedStatementInsertTelaFuncionalidade(PreparedStatement ps, TelaFuncionalidadeVo telaFuncionalidade) throws SQLException
	{
	   int contador = 0;
	   ps.setLong(++contador, telaFuncionalidade.getTelaAjudaVo().getCodigo());
	   ps.setLong(++contador, telaFuncionalidade.getFuncionalidadeVo().getCodigo());
		ps.setString(++contador, telaFuncionalidade.getDescricaoTelaFuncionalidade());
		ps.setString(++contador, telaFuncionalidade.getInformacaoTituloTelaFuncionalidade());
		ps.setInt(++contador, telaFuncionalidade.getStatusTelaFuncionalidade().getValorCorrente());	
		ps.setInt(++contador, telaFuncionalidade.getCodigoOrdenacao());
	}

	public void inserirCampo(CampoAjudaVo campoAjudaVo) throws SQLException
	{
	   PreparedStatement ps = null;
	   StringBuffer sql = new StringBuffer();
	   sql.append("INSERT INTO ITCTB33_CAMPO_AJUDA VALUES (?, ?)");
	   try
	   {
	      ps = conn.prepareStatement(sql.toString());  
	      preparedStatementInsertCampo(ps,campoAjudaVo);
	      if (ps.executeUpdate() != 1)
	      {
	         throw new SQLException();
	      }        
	   }
	   catch(SQLException e)
	   {
	      e.printStackTrace();
	      System.out.println("Erro método inserirCampo, não foi possível inserir Campo: "+campoAjudaVo.getDescricaoRotulo());
	      throw e;
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
	         throw e;
	      }
	   }     		
	}

	private void preparedStatementInsertCampo(PreparedStatement ps, CampoAjudaVo campoAjudaVo) throws SQLException
	{
	   int contador = 0;
	   ps.setLong(++contador, campoAjudaVo.getCodigo());
	   ps.setString(++contador, campoAjudaVo.getDescricaoRotulo());
	}

	void inserirTelaCampo(TelaCampoAjudaVo telaCampoAjuda) throws SQLException
	{
	   PreparedStatement ps = null;
	   StringBuffer sql = new StringBuffer();
	   sql.append("INSERT INTO ITCTB35_TELA_CAMPO_AJUDA VALUES (?, ?, ?, ?, SYSDATE, ?)");
	   try
	   {
	      ps = conn.prepareStatement(sql.toString());  
	      preparedStatementInsertTelaCampo(ps,telaCampoAjuda);
	      if (ps.executeUpdate() != 1)
	      {
	         throw new SQLException();
	      }        
	   }
	   catch(SQLException e)
	   {
	      e.printStackTrace();
	      System.out.println("Erro método inserirTelaCampo, não foi possível inserir relacionamento Tela: "+telaCampoAjuda.getTelaAjudaVo().getCodigo()+" com Campo: "+telaCampoAjuda.getCampoAjudaVo().getCodigo());
			throw e;
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
				throw e;
	      }
	   }           	
	}

	private void preparedStatementInsertTelaCampo(PreparedStatement ps, TelaCampoAjudaVo telaCampoAjuda) throws SQLException
	{
	   int contador = 0;
	   ps.setLong(++contador, telaCampoAjuda.getTelaAjudaVo().getCodigo());
		ps.setLong(++contador, telaCampoAjuda.getCampoAjudaVo().getCodigo());
	   ps.setString(++contador, telaCampoAjuda.getDescricaoAjudaCampo());
		ps.setInt(++contador, telaCampoAjuda.getStatusTelaCampo().getValorCorrente());
		ps.setInt(++contador, telaCampoAjuda.getCodigoOrdenacao());		
	}
}
