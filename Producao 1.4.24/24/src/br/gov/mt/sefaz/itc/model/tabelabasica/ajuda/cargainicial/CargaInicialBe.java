package br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.cargainicial;

import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.campoajuda.CampoAjudaVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.funcionalidade.FuncionalidadeVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.moduloajuda.ModuloAjudaVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.telaajuda.TelaAjudaVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.telacampoajuda.TelaCampoAjudaVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.ajuda.telafuncionalidade.TelaFuncionalidadeVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.Iterator;


public class CargaInicialBe extends AbstractBe implements ModulosAjuda, CamposAjuda,FuncionalidadeAjuda, TelasAjuda, TabelasITC, TelaFuncionalidade
{
	
	public String[] tabelasAjuda = new String[]
	{
		TABELA_TELA_FUNCIONALIDADE
		, TABELA_TELA_CAMPO_AJUDA
		, TABELA_CAMPO_AJUDA
		, TABELA_TELA_AJUDA
		, TABELA_FUNCIONALIDADE
		, TABELA_MODULO_AJUDA
	};
	
	
	public CargaInicialBe() throws SQLException
	{
		super();
	}
	
	public CargaInicialBe(Connection conn)
	{
		super(conn);
	}
	
	public static void main(String[] args) throws ConexaoException, ObjetoObrigatorioException
	{
		
		CargaInicialBe cargaInicial = null;
		try
		{
			cargaInicial = new CargaInicialBe();
			cargaInicial.gerarAjudaITCD(true);		
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.NAO_ABRIU_CONEXAO);
		}
		finally
		{
			if(cargaInicial != null)
			{
			   cargaInicial.close();
			   cargaInicial = null;
			}
		}
		
	}
	
	
	public void gerarAjudaITCD(boolean isNecessarioLimparBanco) throws SQLException
	{
		try
		{
		   if(isNecessarioLimparBanco)
		   {
		      solicitarLimparBanco();
		   }
		   gerarModulos();
		   gerarFuncionalidades();
		   gerarTelas();
		   gerarCampos();			
		   gerarRelacionamentoTelaFuncionalidade();
		   gerarRelacionamentoTelaCampo();
		   
		   commit();
		   System.out.println("Carga realizada com sucesso.");			
		}
		catch(SQLException e)
		{
			conn.rollback();
			throw e;
		}
	}

	private void solicitarLimparBanco() throws SQLException
	{
		CargaInicialDao dao = new CargaInicialDao(conn);
		for(int i = 0; i < tabelasAjuda.length; i++)
		{
			dao.deletarAjuda(tabelasAjuda[i]);	
		}		
	}
	
	
	private void gerarModulos() throws SQLException
	{
		ModuloAjudaVo modulos = new ModuloAjudaVo();
		int contador = 0;
		//MODULO TABELA BASICA
		ModuloAjudaVo moduloTabelaBasica = new ModuloAjudaVo();
		moduloTabelaBasica.setDescricaoModuloAjuda(MODULO_TABELAS_BASICAS);
		moduloTabelaBasica.setCodigo(CODIGO_MODULO_TABELAS_BASICAS);
		moduloTabelaBasica.setStatusModuloAjuda(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   moduloTabelaBasica.setCodigoOrdenacao(++contador);		
		modulos.getCollVO().add(moduloTabelaBasica);
		// MODULO GIA-ITCD
	   ModuloAjudaVo moduloGIAITCD = new ModuloAjudaVo();
	   moduloGIAITCD.setDescricaoModuloAjuda(MODULO_GIA_ITCD);
	   moduloGIAITCD.setCodigo(CODIGO_MODULO_GIA_ITCD);
	   moduloGIAITCD.setStatusModuloAjuda(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   moduloGIAITCD.setCodigoOrdenacao(++contador);
	   modulos.getCollVO().add(moduloGIAITCD);
		
		solicitaCadastrarModulos(modulos);
		
		gerarModuloTabelaBasica(moduloTabelaBasica);
		gerarModuloGIAITCD(moduloGIAITCD);
		
	}
	
	private void solicitaCadastrarModulos(ModuloAjudaVo modulo) throws SQLException
	{
		if(modulo.getCollVO() != null && modulo.getCollVO().size() > 0)
		{
			CargaInicialDao dao = new CargaInicialDao(conn);
			for(Iterator it = modulo.getCollVO().iterator(); it.hasNext(); )
			{
				ModuloAjudaVo moduloAtual = (ModuloAjudaVo) it.next();
				dao.inserirModulo(moduloAtual);
			}
		}
	}

	private void gerarModuloTabelaBasica(ModuloAjudaVo moduloTabelaBasica) throws SQLException
	{
		ModuloAjudaVo modulos = new ModuloAjudaVo();
		int contador = 0;
		//CONSTRUÇÃO		
		ModuloAjudaVo moduloAtual = new ModuloAjudaVo();
		moduloAtual.setCodigo(CODIGO_MODULO_CONSTRUCAO);
		moduloAtual.setDescricaoModuloAjuda(MODULO_CONSTRUCAO);
		moduloAtual.setStatusModuloAjuda(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		moduloAtual.setSubModuloAjuda(moduloTabelaBasica);
		moduloAtual.setCodigoOrdenacao(++contador);
		modulos.getCollVO().add(moduloAtual);
		//BENS
	   moduloAtual = new ModuloAjudaVo();
	   moduloAtual.setCodigo(CODIGO_MODULO_BENS);
	   moduloAtual.setDescricaoModuloAjuda(MODULO_BENS);
	   moduloAtual.setStatusModuloAjuda(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   moduloAtual.setSubModuloAjuda(moduloTabelaBasica);
	   moduloAtual.setCodigoOrdenacao(++contador);
	   modulos.getCollVO().add(moduloAtual);
		//CULTURA
		moduloAtual = new ModuloAjudaVo();
	   moduloAtual.setCodigo(CODIGO_MODULO_CULTURA);
	   moduloAtual.setDescricaoModuloAjuda(MODULO_CULTURA);
	   moduloAtual.setStatusModuloAjuda(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   moduloAtual.setSubModuloAjuda(moduloTabelaBasica);
	   moduloAtual.setCodigoOrdenacao(++contador);
	   modulos.getCollVO().add(moduloAtual);
		//REBANHO
		 moduloAtual = new ModuloAjudaVo();
	   moduloAtual.setCodigo(CODIGO_MODULO_REBANHO);
	   moduloAtual.setDescricaoModuloAjuda(MODULO_REBANHO);
	   moduloAtual.setStatusModuloAjuda(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   moduloAtual.setSubModuloAjuda(moduloTabelaBasica);
	   moduloAtual.setCodigoOrdenacao(++contador);
	   modulos.getCollVO().add(moduloAtual);	
		//NATUREZA DA OPERAÇAO
		moduloAtual = new ModuloAjudaVo();
	   moduloAtual.setCodigo(CODIGO_MODULO_NATUREZA_OPERACAO);
	   moduloAtual.setDescricaoModuloAjuda(MODULO_NATUREZA_OPERACAO);
	   moduloAtual.setStatusModuloAjuda(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   moduloAtual.setSubModuloAjuda(moduloTabelaBasica);
	   moduloAtual.setCodigoOrdenacao(++contador);
	   modulos.getCollVO().add(moduloAtual);
		//CONFIGURACAO GERENCIAL DE PARAMETROS
		 moduloAtual = new ModuloAjudaVo();
	   moduloAtual.setCodigo(CODIGO_MODULO_CONFIGURACAO_GERENCIAL_PARAMETROS);
	   moduloAtual.setDescricaoModuloAjuda(MODULO_CONFIGURACAO_GERENCIAL_PARAMETROS);
	   moduloAtual.setStatusModuloAjuda(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   moduloAtual.setSubModuloAjuda(moduloTabelaBasica);
	   moduloAtual.setCodigoOrdenacao(++contador);
	   modulos.getCollVO().add(moduloAtual);
		//BENFEITORIAS
		 moduloAtual = new ModuloAjudaVo();
	   moduloAtual.setCodigo(CODIGO_MODULO_BENFEITORIAS);
	   moduloAtual.setDescricaoModuloAjuda(MODULO_BENFEITORIAS);
	   moduloAtual.setStatusModuloAjuda(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   moduloAtual.setSubModuloAjuda(moduloTabelaBasica);
	   moduloAtual.setCodigoOrdenacao(++contador);
	   modulos.getCollVO().add(moduloAtual);
		//AJUDA
		 moduloAtual = new ModuloAjudaVo();
	   moduloAtual.setCodigo(CODIGO_MODULO_AJUDA);
	   moduloAtual.setDescricaoModuloAjuda(MODULO_AJUDA);
	   moduloAtual.setStatusModuloAjuda(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   moduloAtual.setSubModuloAjuda(moduloTabelaBasica);
	   moduloAtual.setCodigoOrdenacao(++contador);
	   modulos.getCollVO().add(moduloAtual);
		//PARAMETRO LEGISLACAO
		 moduloAtual = new ModuloAjudaVo();
	   moduloAtual.setCodigo(CODIGO_MODULO_PARAMETRO_LEGISLACAO);
	   moduloAtual.setDescricaoModuloAjuda(MODULO_PARAMETRO_LEGISLACAO);
	   moduloAtual.setStatusModuloAjuda(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   moduloAtual.setSubModuloAjuda(moduloTabelaBasica);
	   moduloAtual.setCodigoOrdenacao(++contador);
	   modulos.getCollVO().add(moduloAtual);
		//INCLUIR MULTA DE MORA
		 moduloAtual = new ModuloAjudaVo();
	   moduloAtual.setCodigo(CODIGO_MODULO_INCLUIR_MULTA_MORA);
	   moduloAtual.setDescricaoModuloAjuda(MODULO_INCLUIR_MULTA_MORA);
	   moduloAtual.setStatusModuloAjuda(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   moduloAtual.setSubModuloAjuda(moduloTabelaBasica);
	   moduloAtual.setCodigoOrdenacao(++contador);
	   modulos.getCollVO().add(moduloAtual);

		solicitaCadastrarModulos(modulos);		
	}

	private void gerarModuloGIAITCD(ModuloAjudaVo moduloGIAITCD) throws SQLException
	{
	   ModuloAjudaVo modulos = new ModuloAjudaVo();
	   int contador = 0;
	   //AUTENTICIDADE      
	   ModuloAjudaVo moduloAtual = new ModuloAjudaVo();
	   moduloAtual.setCodigo(CODIGO_MODULO_AUTENTICIDADE);
	   moduloAtual.setDescricaoModuloAjuda(MODULO_AUTENTICIDADE);
	   moduloAtual.setStatusModuloAjuda(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   moduloAtual.setSubModuloAjuda(moduloGIAITCD);
	   moduloAtual.setCodigoOrdenacao(++contador);
	   modulos.getCollVO().add(moduloAtual);
	   //AVALIACAO
	   moduloAtual = new ModuloAjudaVo();
	   moduloAtual.setCodigo(CODIGO_MODULO_AVALIACAO);
	   moduloAtual.setDescricaoModuloAjuda(MODULO_AVALIACAO);
	   moduloAtual.setStatusModuloAjuda(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   moduloAtual.setSubModuloAjuda(moduloGIAITCD);
	   moduloAtual.setCodigoOrdenacao(++contador);
	   modulos.getCollVO().add(moduloAtual);
	   //STATUS
	   moduloAtual = new ModuloAjudaVo();
	   moduloAtual.setCodigo(CODIGO_MODULO_STATUS);
	   moduloAtual.setDescricaoModuloAjuda(MODULO_STATUS);
	   moduloAtual.setStatusModuloAjuda(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   moduloAtual.setSubModuloAjuda(moduloGIAITCD);
	   moduloAtual.setCodigoOrdenacao(++contador);
	   modulos.getCollVO().add(moduloAtual);
	   //REIMPRIMIR
	    moduloAtual = new ModuloAjudaVo();
	   moduloAtual.setCodigo(CODIGO_MODULO_REIMPRIMIR);
	   moduloAtual.setDescricaoModuloAjuda(MODULO_REIMPRIMIR);
	   moduloAtual.setStatusModuloAjuda(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   moduloAtual.setSubModuloAjuda(moduloGIAITCD);
	   moduloAtual.setCodigoOrdenacao(++contador);
	   modulos.getCollVO().add(moduloAtual);  
	   //CAUSA MORTIS
	   moduloAtual = new ModuloAjudaVo();
	   moduloAtual.setCodigo(CODIGO_MODULO_CAUSA_MORTIS);
	   moduloAtual.setDescricaoModuloAjuda(MODULO_CAUSA_MORTIS);
	   moduloAtual.setStatusModuloAjuda(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   moduloAtual.setSubModuloAjuda(moduloGIAITCD);
	   moduloAtual.setCodigoOrdenacao(++contador);
	   modulos.getCollVO().add(moduloAtual);
	   //INTER VIVOS
	    moduloAtual = new ModuloAjudaVo();
	   moduloAtual.setCodigo(CODIGO_MODULO_INTER_VIVOS);
	   moduloAtual.setDescricaoModuloAjuda(MODULO_INTER_VIVOS);
	   moduloAtual.setStatusModuloAjuda(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   moduloAtual.setSubModuloAjuda(moduloGIAITCD);
	   moduloAtual.setCodigoOrdenacao(++contador);
	   modulos.getCollVO().add(moduloAtual);
	   //DAR
	    moduloAtual = new ModuloAjudaVo();
	   moduloAtual.setCodigo(CODIGO_MODULO_DAR);
	   moduloAtual.setDescricaoModuloAjuda(MODULO_DAR);
	   moduloAtual.setStatusModuloAjuda(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   moduloAtual.setSubModuloAjuda(moduloGIAITCD);
	   moduloAtual.setCodigoOrdenacao(++contador);
	   modulos.getCollVO().add(moduloAtual);
	   //CONSULTAR GIA-ITCD
	    moduloAtual = new ModuloAjudaVo();
	   moduloAtual.setCodigo(CODIGO_MODULO_CONSULTAR_GIA_ITCD);
	   moduloAtual.setDescricaoModuloAjuda(MODULO_CONSULTAR_GIA_ITCD);
	   moduloAtual.setStatusModuloAjuda(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   moduloAtual.setSubModuloAjuda(moduloGIAITCD);
	   moduloAtual.setCodigoOrdenacao(++contador);
	   modulos.getCollVO().add(moduloAtual);

	   solicitaCadastrarModulos(modulos); 
	}

	private void gerarFuncionalidades() throws SQLException
	{
		FuncionalidadeVo funcionalidades = new FuncionalidadeVo();
	   FuncionalidadeVo funcionalidade = new FuncionalidadeVo();
		
	   //TABELAS BÁSICAS	   
	   //BENS
		//INCLUIR
		funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_BENS_INCLUIR);
		funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_BENS_INCLUIR);
		funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_BENS));
	   funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   funcionalidades.getCollVO().add(funcionalidade);
		//ALTERAR
		funcionalidade = new FuncionalidadeVo();
	   funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_BENS_ALTERAR);
	   funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_BENS_ALTERAR);
	   funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_BENS));
	   funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   funcionalidades.getCollVO().add(funcionalidade);
		//CONSULTAR
		funcionalidade = new FuncionalidadeVo();
		funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_BENS_CONSULTAR);
	   funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_BENS_CONSULTAR);
	   funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_BENS));
	   funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   funcionalidades.getCollVO().add(funcionalidade);
		
	   //CONSTRUCAO
	   //INCLUIR
		funcionalidade = new FuncionalidadeVo();
		funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_CONSTRUCAO_INCLUIR);
	   funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_CONSTRUCAO_INCLUIR);
	   funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_CONSTRUCAO));
	   funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   funcionalidades.getCollVO().add(funcionalidade);
		//ALTERAR
		funcionalidade = new FuncionalidadeVo();
		funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_CONSTRUCAO_ALTERAR);
		funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_CONSTRUCAO_ALTERAR);
		funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_CONSTRUCAO));
		funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		funcionalidades.getCollVO().add(funcionalidade);
		//CONSULTAR
		funcionalidade = new FuncionalidadeVo();
		funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_CONSTRUCAO_CONSULTAR);
		funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_CONSTRUCAO_CONSULTAR);
		funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_CONSTRUCAO));
		funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		funcionalidades.getCollVO().add(funcionalidade);
		
		//CULTURA
		//INCLUIR
		funcionalidade = new FuncionalidadeVo();
	   funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_CULTURA_INCLUIR);
	   funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_CULTURA_INCLUIR);
	   funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_CULTURA));
	   funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   funcionalidades.getCollVO().add(funcionalidade);
	   //ALTERAR
	   funcionalidade = new FuncionalidadeVo();
	   funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_CULTURA_ALTERAR);
	   funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_CULTURA_ALTERAR);
	   funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_CULTURA));
	   funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   funcionalidades.getCollVO().add(funcionalidade);
	   //CONSULTAR
	   funcionalidade = new FuncionalidadeVo();
	   funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_CULTURA_CONSULTAR);
	   funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_CULTURA_CONSULTAR);
	   funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_CULTURA));
	   funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   funcionalidades.getCollVO().add(funcionalidade);

	   //INCLUIR MULTA DE MORA
	   //INCLUIR
	   funcionalidade = new FuncionalidadeVo();
	   funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_INCLUIR_MULTA_MORA_INCLUIR);
	   funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_INCLUIR_MULTA_MORA_INCLUIR);
	   funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_INCLUIR_MULTA_MORA));
	   funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   funcionalidades.getCollVO().add(funcionalidade);

	   //REBANHO
	   //INCLUIR
	   funcionalidade = new FuncionalidadeVo();
	   funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_REBANHO_INCLUIR);
	   funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_REBANHO_INCLUIR);
	   funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_REBANHO));
	   funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   funcionalidades.getCollVO().add(funcionalidade);
	   //ALTERAR
	   funcionalidade = new FuncionalidadeVo();
	   funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_REBANHO_ALTERAR);
	   funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_REBANHO_ALTERAR);
	   funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_REBANHO));
	   funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   funcionalidades.getCollVO().add(funcionalidade);
	   //CONSULTAR
	   funcionalidade = new FuncionalidadeVo();
	   funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_REBANHO_CONSULTAR);
	   funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_REBANHO_CONSULTAR);
	   funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_REBANHO));
	   funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   funcionalidades.getCollVO().add(funcionalidade);

	   //PARAMETROS DA LEGISLACAO
	   //INCLUIR
	   funcionalidade = new FuncionalidadeVo();
	   funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_PARAMETROS_LEGISLACAO_INCLUIR);
	   funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_PARAMETROS_LEGISLACAO_INCLUIR);
	   funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_PARAMETRO_LEGISLACAO));
	   funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   funcionalidades.getCollVO().add(funcionalidade);
	   //ALTERAR
	   funcionalidade = new FuncionalidadeVo();
	   funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_PARAMETROS_LEGISLACAO_ALTERAR);
	   funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_PARAMETROS_LEGISLACAO_ALTERAR);
	   funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_PARAMETRO_LEGISLACAO));
	   funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   funcionalidades.getCollVO().add(funcionalidade);
	   //CONSULTAR
	   funcionalidade = new FuncionalidadeVo();
	   funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_PARAMETROS_LEGISLACAO_CONSULTAR);
	   funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_PARAMETROS_LEGISLACAO_CONSULTAR);
	   funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_PARAMETRO_LEGISLACAO));
	   funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   funcionalidades.getCollVO().add(funcionalidade);	
		
	   //NATUREZA DA OPERACAO
	   //INCLUIR
	   funcionalidade = new FuncionalidadeVo();
	   funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_NATUREZA_OPERACAO_INCLUIR);
	   funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_NATUREZA_OPERACAO_INCLUIR);
	   funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_NATUREZA_OPERACAO));
	   funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   funcionalidades.getCollVO().add(funcionalidade);
	   //ALTERAR
	   funcionalidade = new FuncionalidadeVo();
	   funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_NATUREZA_OPERACAO_ALTERAR);
	   funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_NATUREZA_OPERACAO_ALTERAR);
	   funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_NATUREZA_OPERACAO));
	   funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   funcionalidades.getCollVO().add(funcionalidade);
	   //CONSULTAR
	   funcionalidade = new FuncionalidadeVo();
	   funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_NATUREZA_OPERACAO_CONSULTAR);
	   funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_NATUREZA_OPERACAO_CONSULTAR);
	   funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_NATUREZA_OPERACAO));
	   funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   funcionalidades.getCollVO().add(funcionalidade);   

	   //AJUDA
	   //INCLUIR
	   funcionalidade = new FuncionalidadeVo();
	   funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_AJUDA_INCLUIR);
	   funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_AJUDA_INCLUIR);
	   funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_AJUDA));
	   funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   funcionalidades.getCollVO().add(funcionalidade);
	   //ALTERAR
	   funcionalidade = new FuncionalidadeVo();
	   funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_AJUDA_ALTERAR);
	   funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_AJUDA_ALTERAR);
	   funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_AJUDA));
	   funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   funcionalidades.getCollVO().add(funcionalidade);
	   //CONSULTAR
	   funcionalidade = new FuncionalidadeVo();
	   funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_AJUDA_CONSULTAR);
	   funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_AJUDA_CONSULTAR);
	   funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_AJUDA));
	   funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   funcionalidades.getCollVO().add(funcionalidade);   		

	   //CONFIGURACAO_GERENCIAL_PARAMETROS
	   //ALTERAR
	   funcionalidade = new FuncionalidadeVo();
	   funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_CONFIGURACAO_GERENCIAL_PARAMETROS_ALTERAR);
	   funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_CONFIGURACAO_GERENCIAL_PARAMETROS_ALTERAR);
	   funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_CONFIGURACAO_GERENCIAL_PARAMETROS));
	   funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   funcionalidades.getCollVO().add(funcionalidade);
		
	   //BENFEITORIAS
	   //INCLUIR
	   funcionalidade = new FuncionalidadeVo();
	   funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_BENFEITORIAS_INCLUIR);
	   funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_BENFEITORIAS_INCLUIR);
	   funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_BENFEITORIAS));
	   funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   funcionalidades.getCollVO().add(funcionalidade);
	   //ALTERAR
	   funcionalidade = new FuncionalidadeVo();
	   funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_BENFEITORIAS_ALTERAR);
	   funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_BENFEITORIAS_ALTERAR);
	   funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_BENFEITORIAS));
	   funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   funcionalidades.getCollVO().add(funcionalidade);
	   //CONSULTAR
	   funcionalidade = new FuncionalidadeVo();
	   funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_BENFEITORIAS_CONSULTAR);
	   funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_BENFEITORIAS_CONSULTAR);
	   funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_BENFEITORIAS));
	   funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   funcionalidades.getCollVO().add(funcionalidade);         

	   //GIA-ITCD
		//AUTENTICIDADE
	   //IMPRIMIR		
		funcionalidade = new FuncionalidadeVo();
		funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_AUTENTICIDADE_IMPRIMIR);
		funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_AUTENTICIDADE_IMPRIMIR);
		funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_AUTENTICIDADE));
		funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		funcionalidades.getCollVO().add(funcionalidade);
		//CONSULTAR
		funcionalidade = new FuncionalidadeVo();
		funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_AUTENTICIDADE_CONSULTAR);
		funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_AUTENTICIDADE_CONSULTAR);
		funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_AUTENTICIDADE));
		funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		funcionalidades.getCollVO().add(funcionalidade);

	   //AVALIACAO
	   //INCLUIR
	   funcionalidade = new FuncionalidadeVo();
	   funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_AVALIACAO_INCLUIR);
	   funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_AVALIACAO_INCLUIR);
	   funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_AVALIACAO));
	   funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   funcionalidades.getCollVO().add(funcionalidade);
	   //ALTERAR
	   funcionalidade = new FuncionalidadeVo();
	   funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_AVALIACAO_ALTERAR);
	   funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_AVALIACAO_ALTERAR);
	   funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_AVALIACAO));
	   funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   funcionalidades.getCollVO().add(funcionalidade);
	   //CONSULTAR
	   funcionalidade = new FuncionalidadeVo();
	   funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_AVALIACAO_CONSULTAR);
	   funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_AVALIACAO_CONSULTAR);
	   funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_AVALIACAO));
	   funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   funcionalidades.getCollVO().add(funcionalidade);		
	   //IMPRIMIR
	   funcionalidade = new FuncionalidadeVo();
	   funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_AVALIACAO_IMPRIMIR);
	   funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_AVALIACAO_IMPRIMIR);
	   funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_AVALIACAO));
	   funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   funcionalidades.getCollVO().add(funcionalidade);   

	   //ALTERAR STATUS
	   funcionalidade = new FuncionalidadeVo();
	   funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_STATUS_ALTERAR);
	   funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_STATUS_ALTERAR);
	   funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_STATUS));
	   funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   funcionalidades.getCollVO().add(funcionalidade);

		//REIMPRIMIR
	   //REIMPRIMIR GIA-ITCD
	   funcionalidade = new FuncionalidadeVo();
	   funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_REIMPRIMIR_GIA_ITCD);
	   funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_REIMPRIMIR_GIA_ITCD);
	   funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_REIMPRIMIR));
	   funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   funcionalidades.getCollVO().add(funcionalidade);
	   //REIMPRIMIR NOTIFICAÇÃO / RETIFICAÇÃO
	   funcionalidade = new FuncionalidadeVo();
	   funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_REIMPRIMIR_NOTIFICACAO_RETIFICACAO);
	   funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_REIMPRIMIR_NOTIFICACAO_RETIFICACAO);
	   funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_REIMPRIMIR));
	   funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   funcionalidades.getCollVO().add(funcionalidade);

	   //CAUSA MORTIS
	   //GIA-ITCD INVENTARIO ARROLAMENTO
		//INCLUIR
	   funcionalidade = new FuncionalidadeVo();
	   funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_GIA_INVENTARIO_ARROLAMENTO_INCLUIR);
	   funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_GIA_INVENTARIO_ARROLAMENTO_INCLUIR);
	   funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_CAUSA_MORTIS));
	   funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   funcionalidades.getCollVO().add(funcionalidade);
	   //ALTERAR
	   funcionalidade = new FuncionalidadeVo();
	   funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_GIA_INVENTARIO_ARROLAMENTO_ALTERAR);
	   funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_GIA_INVENTARIO_ARROLAMENTO_ALTERAR);
	   funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_CAUSA_MORTIS));
	   funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   funcionalidades.getCollVO().add(funcionalidade);

	   //INTER VIVOS
	   //GIA-ITCD DOACAO
	   //INCLUIR
	   funcionalidade = new FuncionalidadeVo();
	   funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_GIA_DOACAO_INCLUIR);
	   funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_GIA_DOACAO_INCLUIR);
	   funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_INTER_VIVOS));
	   funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   funcionalidades.getCollVO().add(funcionalidade);
	   //ALTERAR
	   funcionalidade = new FuncionalidadeVo();
	   funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_GIA_DOACAO_ALTERAR);
	   funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_GIA_DOACAO_ALTERAR);
	   funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_INTER_VIVOS));
	   funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   funcionalidades.getCollVO().add(funcionalidade);
	   
		//GIA-ITCD SEPARACAO
	   //INCLUIR
	   funcionalidade = new FuncionalidadeVo();
	   funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_GIA_SEPARACAO_INCLUIR);
	   funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_GIA_SEPARACAO_INCLUIR);
	   funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_INTER_VIVOS));
	   funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   funcionalidades.getCollVO().add(funcionalidade);
	   //ALTERAR
	   funcionalidade = new FuncionalidadeVo();
	   funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_GIA_SEPARACAO_ALTERAR);
	   funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_GIA_SEPARACAO_ALTERAR);
	   funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_INTER_VIVOS));
	   funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   funcionalidades.getCollVO().add(funcionalidade);

	   //VINCULAR DAR
	   funcionalidade = new FuncionalidadeVo();
	   funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_VINCULAR_DAR);
	   funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_VINCULAR_DAR);
	   funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_DAR));
	   funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   funcionalidades.getCollVO().add(funcionalidade);
		
	   //CONSULTAR GIA-ITCD
	   //CONSULTAR GENERICO
	   funcionalidade = new FuncionalidadeVo();
	   funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_GIA_ITCD_CONSULTAR);
	   funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_GIA_ITCD_CONSULTAR);
	   funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_CONSULTAR_GIA_ITCD));
	   funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   funcionalidades.getCollVO().add(funcionalidade);

	   //ALTERAR GIA-ITCD
	   funcionalidade = new FuncionalidadeVo();
	   funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_GIA_ITCD_ALTERAR);
	   funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_GIA_ITCD_ALTERAR);
	   funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_TABELAS_BASICAS));
	   funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   funcionalidades.getCollVO().add(funcionalidade);
	   
	   //INATIVAR GIA-ITCD
	   funcionalidade = new FuncionalidadeVo();
	   funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_GIA_ITCD_INATIVAR);
	   funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_GIA_ITCD_INATIVAR);
	   funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_TABELAS_BASICAS));
	   funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   funcionalidades.getCollVO().add(funcionalidade);
	   
	   //REATIVAR GIA-ITCD
	   funcionalidade = new FuncionalidadeVo();
	   funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_GIA_ITCD_REATIVAR);
	   funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_GIA_ITCD_REATIVAR);
	   funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_TABELAS_BASICAS));
	   funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   funcionalidades.getCollVO().add(funcionalidade);
	   
	   //INCLUIR GIA-ITCD
	   funcionalidade = new FuncionalidadeVo();
	   funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_GIA_ITCD_INCLUIR);
	   funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_GIA_ITCD_INCLUIR);
	   funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_TABELAS_BASICAS));
	   funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   funcionalidades.getCollVO().add(funcionalidade);
	   
	   //ENVIAR SENHA GIA-ITCD
	   funcionalidade = new FuncionalidadeVo();
	   funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_GIA_ITCD_SENHA_ENVIAR);
	   funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_GIA_ITCD_SENHA_ENVIAR);
	   funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_TABELAS_BASICAS));
	   funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   funcionalidades.getCollVO().add(funcionalidade);
	   
	   //VINCULAR DAR
	   funcionalidade = new FuncionalidadeVo();
	   funcionalidade.setCodigo(CODIGO_FUNCIONALIDADE_GIA_ITCD_SENHA_ENVIAR_CONTRIBUINTE_PESQUISAR);
	   funcionalidade.setDescricaoFuncionalidade(FUNCIONALIDADE_GIA_ITCD_SENHA_ENVIAR_CONTRIBUINTE_PESQUISAR);
	   funcionalidade.setModuloAjudaVo(new ModuloAjudaVo(CODIGO_MODULO_TABELAS_BASICAS));
	   funcionalidade.setStatusFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   funcionalidades.getCollVO().add(funcionalidade);
	   
	   solicitaCadastrarFuncionalidades(funcionalidades); 

	}

	private void solicitaCadastrarFuncionalidades(FuncionalidadeVo funcionalidades) throws SQLException
	{
	   if(funcionalidades.getCollVO() != null && funcionalidades.getCollVO().size() > 0)
	   {
	      CargaInicialDao dao = new CargaInicialDao(conn);
	      for(Iterator it = funcionalidades.getCollVO().iterator(); it.hasNext(); )
	      {
	         FuncionalidadeVo funcionalidadeAtual = (FuncionalidadeVo) it.next();
	         dao.inserirFuncionalidade(funcionalidadeAtual);
	      }
	   }	
	}

	private void gerarTelas() throws SQLException
	{
		TelaAjudaVo telas = new TelaAjudaVo();
		TelaAjudaVo tela = new TelaAjudaVo();
		
		//TABELAS BÁSICAS
		//BEM
		tela.setCodigo(CODIGO_TELA_BEM_INCLUIR);
		tela.setDescricaoTelaAjuda(TELA_BEM_INCLUIR);
		telas.getCollVO().add(tela);
		tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_BEM_ALTERAR);
	   tela.setDescricaoTelaAjuda(TELA_BEM_ALTERAR);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_BEM_CONSULTAR);
	   tela.setDescricaoTelaAjuda(TELA_BEM_CONSULTAR);
	   telas.getCollVO().add(tela);
		
		//CULTURA
	   tela = new TelaAjudaVo();		
	   tela.setCodigo(CODIGO_TELA_CULTURA_INCLUIR);
	   tela.setDescricaoTelaAjuda(TELA_CULTURA_INCLUIR);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_CULTURA_ALTERAR);
	   tela.setDescricaoTelaAjuda(TELA_CULTURA_ALTERAR);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_CULTURA_CONSULTAR);
	   tela.setDescricaoTelaAjuda(TELA_CULTURA_CONSULTAR);
	   telas.getCollVO().add(tela);
		
		//REBANHO
	   tela = new TelaAjudaVo();				
	   tela.setCodigo(CODIGO_TELA_REBANHO_INCLUIR);
	   tela.setDescricaoTelaAjuda(TELA_REBANHO_INCLUIR);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_REBANHO_ALTERAR);
	   tela.setDescricaoTelaAjuda(TELA_REBANHO_ALTERAR);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_REBANHO_CONSULTAR);
	   tela.setDescricaoTelaAjuda(TELA_REBANHO_CONSULTAR);
	   telas.getCollVO().add(tela);

		//NATUREZA DA OPERAÇÃO
	   tela = new TelaAjudaVo();				
	   tela.setCodigo(CODIGO_TELA_NATUREZA_OPERACAO_INCLUIR);
	   tela.setDescricaoTelaAjuda(TELA_NATUREZA_OPERACAO_INCLUIR);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_NATUREZA_OPERACAO_ALTERAR);
	   tela.setDescricaoTelaAjuda(TELA_NATUREZA_OPERACAO_ALTERAR);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_NATUREZA_OPERACAO_CONSULTAR);
	   tela.setDescricaoTelaAjuda(TELA_NATUREZA_OPERACAO_CONSULTAR);
	   telas.getCollVO().add(tela);

		//CONFIGURACAO GERENCIAL DE PARAMETROS
	   tela = new TelaAjudaVo();           
	   tela.setCodigo(CODIGO_TELA_CONFIGURACAO_GERENCIAL_ALTERAR);
	   tela.setDescricaoTelaAjuda(TELA_CONFIGURACAO_GERENCIAL_ALTERAR);
	   telas.getCollVO().add(tela);

		//BENFEITORIA
	   tela = new TelaAjudaVo();           
	   tela.setCodigo(CODIGO_TELA_BENFEITORIA_INCLUIR);
	   tela.setDescricaoTelaAjuda(TELA_BENFEITORIA_INCLUIR);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_BENFEITORIA_ALTERAR);
	   tela.setDescricaoTelaAjuda(TELA_BENFEITORIA_ALTERAR);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_BENFEITORIA_CONSULTAR);
	   tela.setDescricaoTelaAjuda(TELA_BENFEITORIA_CONSULTAR);
	   telas.getCollVO().add(tela);

		//CONSTRUÇÃO
	   tela = new TelaAjudaVo();           
	   tela.setCodigo(CODIGO_TELA_CONSTRUCAO_INCLUIR);
	   tela.setDescricaoTelaAjuda(TELA_CONSTRUCAO_INCLUIR);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_CONSTRUCAO_ALTERAR);
	   tela.setDescricaoTelaAjuda(TELA_CONSTRUCAO_ALTERAR);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_CONSTRUCAO_CONSULTAR);
	   tela.setDescricaoTelaAjuda(TELA_CONSTRUCAO_CONSULTAR);
	   telas.getCollVO().add(tela);

		//PARAMETROS DA LEGISLACAO
	   tela = new TelaAjudaVo();           
	   tela.setCodigo(CODIGO_TELA_PARAMETRO_LEGISLACAO_INCLUIR);
	   tela.setDescricaoTelaAjuda(TELA_PARAMETRO_LEGISLACAO_INCLUIR);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_PARAMETRO_LEGISLACAO_ALTERAR);
	   tela.setDescricaoTelaAjuda(TELA_PARAMETRO_LEGISLACAO_ALTERAR);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_PARAMETRO_LEGISLACAO_CONSULTAR);
	   tela.setDescricaoTelaAjuda(TELA_PARAMETRO_LEGISLACAO_CONSULTAR);
	   telas.getCollVO().add(tela);

		//AJUDA
	   tela = new TelaAjudaVo();           
	   tela.setCodigo(CODIGO_TELA_AJUDA_INCLUIR);
	   tela.setDescricaoTelaAjuda(TELA_AJUDA_INCLUIR);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_AJUDA_ALTERAR);
	   tela.setDescricaoTelaAjuda(TELA_AJUDA_ALTERAR);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_AJUDA_CONSULTAR);
	   tela.setDescricaoTelaAjuda(TELA_AJUDA_CONSULTAR);
	   telas.getCollVO().add(tela);

	   //MULTA DE MORA INCLUIR
	   tela = new TelaAjudaVo();           
	   tela.setCodigo(CODIGO_TELA_MULTA_MORA_INCLUIR);
	   tela.setDescricaoTelaAjuda(TELA_MULTA_MORA_INCLUIR);
	   telas.getCollVO().add(tela);

		
		//GIA-ITCD
		//INVENTARIO ARROLAMENTO
		//INCLUIR
	   tela = new TelaAjudaVo();           
	   tela.setCodigo(CODIGO_TELA_GIA_INVENTARIO_DADOS_GERAIS_INCLUIR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_INVENTARIO_DADOS_GERAIS_INCLUIR);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_INCLUIR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_INCLUIR);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_GIA_INVENTARIO_BENEFICIARIO_INCLUIR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_INVENTARIO_BENEFICIARIO_INCLUIR);
	   telas.getCollVO().add(tela);
		//ALTERAR
	   tela = new TelaAjudaVo();           
	   tela.setCodigo(CODIGO_TELA_GIA_INVENTARIO_DADOS_GERAIS_ALTERAR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_INVENTARIO_DADOS_GERAIS_ALTERAR);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_ALTERAR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_ALTERAR);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_GIA_INVENTARIO_BENEFICIARIO_ALTERAR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_INVENTARIO_BENEFICIARIO_ALTERAR);
	   telas.getCollVO().add(tela);
		//GERAIS
	   tela = new TelaAjudaVo();           
	   tela.setCodigo(CODIGO_TELA_GIA_INVENTARIO_DEMONSTRATIVO_CALCULO);
	   tela.setDescricaoTelaAjuda(TELA_GIA_INVENTARIO_DEMONSTRATIVO_CALCULO);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_GIA_INVENTARIO_DEMONSTRATIVO_CALCULO_CONSULTAR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_INVENTARIO_DEMONSTRATIVO_CALCULO_CONSULTAR);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_GIA_INVENTARIO_SENHA_CADASTRAR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_INVENTARIO_SENHA_CADASTRAR);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_GIA_INVENTARIO_CONTRIBUINTE_SELECIONAR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_INVENTARIO_CONTRIBUINTE_SELECIONAR);
	   telas.getCollVO().add(tela);

		//DOAÇÃO
	   //INCLUIR
	   tela = new TelaAjudaVo();           
	   tela.setCodigo(CODIGO_TELA_GIA_DOACAO_DADOS_GERAIS_INCLUIR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_DOACAO_DADOS_GERAIS_INCLUIR);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_INCLUIR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_DOACAO_BEM_TRIBUTAVEL_INCLUIR);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_GIA_DOACAO_BENEFICIARIO_INCLUIR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_DOACAO_BENEFICIARIO_INCLUIR);
	   telas.getCollVO().add(tela);
	   //ALTERAR
	   tela = new TelaAjudaVo();           
	   tela.setCodigo(CODIGO_TELA_GIA_DOACAO_DADOS_GERAIS_ALTERAR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_DOACAO_DADOS_GERAIS_ALTERAR);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_ALTERAR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_DOACAO_BEM_TRIBUTAVEL_ALTERAR);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_GIA_DOACAO_BENEFICIARIO_ALTERAR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_DOACAO_BENEFICIARIO_ALTERAR);
	   telas.getCollVO().add(tela);
	   //GERAIS
	   tela = new TelaAjudaVo();           
	   tela.setCodigo(CODIGO_TELA_GIA_DOACAO_DEMONSTRATIVO_CALCULO);
	   tela.setDescricaoTelaAjuda(TELA_GIA_DOACAO_DEMONSTRATIVO_CALCULO);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_GIA_DOACAO_DEMONSTRATIVO_CALCULO_CONSULTAR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_DOACAO_DEMONSTRATIVO_CALCULO_CONSULTAR);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_GIA_DOACAO_SENHA_CADASTRAR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_DOACAO_SENHA_CADASTRAR);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_GIA_DOACAO_CONTRIBUINTE_SELECIONAR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_DOACAO_CONTRIBUINTE_SELECIONAR);
	   telas.getCollVO().add(tela);
	   
		//SEPARAÇÃO
	   //INCLUIR
	   tela = new TelaAjudaVo();           
	   tela.setCodigo(CODIGO_TELA_GIA_SEPARACAO_DADOS_GERAIS_INCLUIR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_SEPARACAO_DADOS_GERAIS_INCLUIR);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_INCLUIR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_INCLUIR);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_GIA_SEPARACAO_CONJUGE_INCLUIR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_SEPARACAO_CONJUGE_INCLUIR);
	   telas.getCollVO().add(tela);
	   //ALTERAR
	   tela = new TelaAjudaVo();           
	   tela.setCodigo(CODIGO_TELA_GIA_SEPARACAO_DADOS_GERAIS_ALTERAR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_SEPARACAO_DADOS_GERAIS_ALTERAR);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_ALTERAR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_ALTERAR);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_GIA_SEPARACAO_CONJUGE_ALTERAR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_SEPARACAO_CONJUGE_ALTERAR);
	   telas.getCollVO().add(tela);
	   //GERAIS
	   tela = new TelaAjudaVo();           
	   tela.setCodigo(CODIGO_TELA_GIA_SEPARACAO_DEMONSTRATIVO_CALCULO);
	   tela.setDescricaoTelaAjuda(TELA_GIA_SEPARACAO_DEMONSTRATIVO_CALCULO);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_GIA_SEPARACAO_DEMONSTRATIVO_CALCULO_CONSULTAR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_SEPARACAO_DEMONSTRATIVO_CALCULO_CONSULTAR);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_GIA_SEPARACAO_SENHA_CADASTRAR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_SEPARACAO_SENHA_CADASTRAR);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_GIA_SEPARACAO_CONTRIBUINTE_SELECIONAR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_SEPARACAO_CONTRIBUINTE_SELECIONAR);
	   telas.getCollVO().add(tela);
		
	   //GIA-ITCD CONSULTAR
	   tela = new TelaAjudaVo();           
	   tela.setCodigo(CODIGO_TELA_GIA_ITCD_CONSULTAR_LIVRE);
	   tela.setDescricaoTelaAjuda(TELA_GIA_ITCD_CONSULTAR_LIVRE);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_GIA_ITCD_CONSULTAR_SELECIONAR_CONTRIBUINTE);
	   tela.setDescricaoTelaAjuda(TELA_GIA_ITCD_CONSULTAR_SELECIONAR_CONTRIBUINTE);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_GIA_ITCD_CONSULTAR_SERVIDOR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_ITCD_CONSULTAR_SERVIDOR);
	   telas.getCollVO().add(tela);

	   //GIA-ITCD REATIVAR
	   tela = new TelaAjudaVo();           
	   tela.setCodigo(CODIGO_TELA_GIA_ITCD_REATIVAR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_ITCD_REATIVAR);
	   telas.getCollVO().add(tela);

	   //GIA-ITCD INATIVAR
	   tela = new TelaAjudaVo();           
	   tela.setCodigo(CODIGO_TELA_GIA_ITCD_INATIVAR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_ITCD_INATIVAR);
	   telas.getCollVO().add(tela);

	   //REIMPRESSAO
	   tela = new TelaAjudaVo();           
	   tela.setCodigo(CODIGO_TELA_GIA_ITCD_REIMPRIMIR_NOTIFICACAO_RETIFICACAO);
	   tela.setDescricaoTelaAjuda(TELA_GIA_ITCD_REIMPRIMIR_NOTIFICACAO_RETIFICACAO);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();           
	   tela.setCodigo(CODIGO_TELA_GIA_ITCD_CONSULTAR_NOTIFICACAO_RETIFICACAO);
	   tela.setDescricaoTelaAjuda(TELA_GIA_ITCD_CONSULTAR_NOTIFICACAO_RETIFICACAO);
	   telas.getCollVO().add(tela);
		
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_GIA_ITCD_REIMPRIMIR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_ITCD_REIMPRIMIR);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_GIA_ITCD_CONSULTAR_REIMPRIMIR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_ITCD_CONSULTAR_REIMPRIMIR);
	   telas.getCollVO().add(tela);

	   //AUTENTICIDADE
	   tela = new TelaAjudaVo();           
	   tela.setCodigo(CODIGO_TELA_GIA_ITCD_CONSULTAR_AUTENTICIDADE);
	   tela.setDescricaoTelaAjuda(TELA_GIA_ITCD_CONSULTAR_AUTENTICIDADE);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_GIA_ITCD_CONSULTAR_AUTENTICIDADE_IMPRIMIR_AUTENTICIDADE);
	   tela.setDescricaoTelaAjuda(TELA_GIA_ITCD_CONSULTAR_AUTENTICIDADE_IMPRIMIR_AUTENTICIDADE);
	   telas.getCollVO().add(tela);

	   //ALTERAR STATUS
	   tela = new TelaAjudaVo();           
	   tela.setCodigo(CODIGO_TELA_GIA_ITCD_STATUS_ALTERAR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_ITCD_STATUS_ALTERAR);
	   telas.getCollVO().add(tela);

	   //AVALIACAO
		//INCLUIR
	   tela = new TelaAjudaVo();           
	   tela.setCodigo(CODIGO_TELA_GIA_ITCD_AVALIACAO_INCLUIR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_ITCD_AVALIACAO_INCLUIR);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_GIA_ITCD_AVALIACAO_INCLUIR_AVALIACAO_BEM);
	   tela.setDescricaoTelaAjuda(TELA_GIA_ITCD_AVALIACAO_INCLUIR_AVALIACAO_BEM);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_GIA_ITCD_AVALIACAO_INCLUIR_AVALIACAO_BEM_SERVIDOR_INCLUIR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_ITCD_AVALIACAO_INCLUIR_AVALIACAO_BEM_SERVIDOR_INCLUIR);
	   telas.getCollVO().add(tela);
	   //ALTERAR
	   tela = new TelaAjudaVo();           
	   tela.setCodigo(CODIGO_TELA_GIA_ITCD_AVALIACAO_ALTERAR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_ITCD_AVALIACAO_ALTERAR);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_GIA_ITCD_AVALIACAO_ALTERAR_AVALIACAO_BEM);
	   tela.setDescricaoTelaAjuda(TELA_GIA_ITCD_AVALIACAO_ALTERAR_AVALIACAO_BEM);
	   telas.getCollVO().add(tela);
	   tela = new TelaAjudaVo();
	   tela.setCodigo(CODIGO_TELA_GIA_ITCD_AVALIACAO_ALTERAR_AVALIACAO_BEM_SERVIDOR_INCLUIR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_ITCD_AVALIACAO_ALTERAR_AVALIACAO_BEM_SERVIDOR_INCLUIR);
	   telas.getCollVO().add(tela);
	   //CONSULTAR
	   tela = new TelaAjudaVo();           
	   tela.setCodigo(CODIGO_TELA_GIA_ITCD_AVALIACAO_CONSULTAR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_ITCD_AVALIACAO_CONSULTAR);
	   telas.getCollVO().add(tela);
	   //IMPRIMIR
	   tela = new TelaAjudaVo();           
	   tela.setCodigo(CODIGO_TELA_GIA_ITCD_AVALIACAO_IMPRIMIR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_ITCD_AVALIACAO_IMPRIMIR);
	   telas.getCollVO().add(tela);

	   //VINCULAR DAR
	   tela = new TelaAjudaVo();           
	   tela.setCodigo(CODIGO_TELA_GIA_ITCD_VINCULAR_DAR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_ITCD_VINCULAR_DAR);
	   telas.getCollVO().add(tela);

	   //VINCULAR DAR
	   tela = new TelaAjudaVo();           
	   tela.setCodigo(CODIGO_TELA_GIA_ITCD_SENHA_ENVIAR);
	   tela.setDescricaoTelaAjuda(TELA_GIA_ITCD_SENHA_ENVIAR);
	   telas.getCollVO().add(tela);
		

	   solicitaCadastrarTelas(telas);
	}

	private void solicitaCadastrarTelas(TelaAjudaVo telas) throws SQLException
	{
		if(telas.getCollVO() != null && telas.getCollVO().size() > 0)
		{
			CargaInicialDao dao = new CargaInicialDao(conn);
			for(Iterator it = telas.getCollVO().iterator(); it.hasNext(); )
			{
				TelaAjudaVo telaAtual = (TelaAjudaVo) it.next();
				dao.inserirTelas(telaAtual);
			}
		}  
	}

	private void gerarRelacionamentoTelaFuncionalidade() throws SQLException
	{
		TelaFuncionalidadeVo telaFuncionalidades = new TelaFuncionalidadeVo();
		TelaFuncionalidadeVo telaFuncionalidade = new TelaFuncionalidadeVo();
		
		//TABELAS BASICAS
		//BEM
		//INCLUIR
		telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_BEM_INCLUIR));
		telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_BENS_INCLUIR));
		telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR INCLUIR INFORMAÇÕES SOBRE OS DADOS GERAIS DA TABELA BÁSICA-ITCD BENS. DESCREVE OS SEGUINTES CAMPOS:\n" + 
		"TIPO DE BEM: CAMPO UTILIZADO PARA CLASSIFICAR EM IMOVEL URBANO,IMOVEL RURAL E OUTROS BENS.\n" + 
		"DESCRIÇÃO: CAMPO UTILIZADO PARA DESCREVER O TIPO DE BEM.");
		telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS DO REGISTRO - INCLUIR BEM");
		telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaFuncionalidade.setCodigoOrdenacao(1);
		telaFuncionalidades.getCollVO().add(telaFuncionalidade);
		//ALTERAR
		telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_BEM_CONSULTAR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_BENS_ALTERAR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR CONSULTAR INFORMAÇÕES SOBRE OS DADOS GERAIS DA TABELA BÁSICA-ITCD BENS. ESTA FUNCIONALIDADE TEM O SEGUINTE FLUXO:\\n\" + \n" + 
	   "	   \"1- SELECIONAR O TIPO DE BEM\\n\" + \n" + 
	   "	   \"2- INFORMAR PARTE DA DESCRIÇÃO DO BEM.EX: FAZ% (PARA    LOCALIZAR TODAS AS FAZENDAS).\\n\" + \n" + 
	   "	   \"3- PRESSIONE O BOTÃO PESQUISAR.SERÁ APRESENTA UMA TABELA COM AS SEGUINTES COLUNAS:CÓDIGO, DESCRIÇÃO DO BEM E STATUS.\n" + 
		"     PARA ALTERAR UM BEM BASTA CLICAR NO CÓDIGO DO BEM QUE SE DESEJA ALTERAR.");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("FILTRO PARA PESQUISA - ALTERAR BEM");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(1);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);
		
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_BEM_ALTERAR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_BENS_ALTERAR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR ALTERAR INFORMAÇÕES SOBRE OS DADOS GERAIS DA TABELA BÁSICA-ITCD BENS. ESTA FUNCIONALIDADE TEM O SEGUINTE FLUXO: \n" + 
	   "1- SELECIONAR O TIPO DE BEM\n" + 
	   "2- INFORMAR PARTE DA DESCRIÇÃO DO BEM.EX: FAZ% (PARA    LOCALIZAR TODAS AS FAZENDAS).\n" + 
	   "3- PRESSIONE O BOTÃO PESQUISAR.SERÁ APRESENTA UMA TABELA COM AS SEGUINTES COLUNAS:CÓDIGO, DESCRIÇÃO DO BEM E STATUS.\n" + 
	   "4- SELECIONE O CÓDIGO DESEJADO NA TABELA.NESTE MOMENTO SERÁ APRESENTADO UMA NOVA JANELA COM OS CAMPOS: DESCRIÇÃO E STATUS. QUE PODERAM SER ALTERADOS.\n" + 
	   "OBS: ESTÁ FUNCIONALIDADE TEM O CONCEITO DE:STATUS ATIVO E STATUS INATIVO. QUE NÃO PERMITEM A EXCLUSÃO DEFINITIVA DE QUALQUER ITEM.");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS DO REGISTRO - ALTERAR BEM");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(2);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);
		//CONSULTAR BEM		
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_BEM_CONSULTAR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_BENS_CONSULTAR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR CONSULTAR INFORMAÇÕES SOBRE OS DADOS GERAIS DA TABELA BÁSICA-ITCD BENS. ESTA FUNCIONALIDADE TEM O SEGUINTE FLUXO:\n" + 
	   "1- SELECIONAR O TIPO DE BEM\n" + 
	   "2- INFORMAR PARTE DA DESCRIÇÃO DO BEM.EX: FAZ% (PARA    LOCALIZAR TODAS AS FAZENDAS).\n" + 
	   "3- PRESSIONE O BOTÃO PESQUISAR.SERÁ APRESENTA UMA TABELA COM AS SEGUINTES COLUNAS:CÓDIGO, DESCRIÇÃO DO BEM E STATUS.");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("FILTRO PARA PESQUISA - CONSULTAR BEM ");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(1);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);
		
		//CULTURA
		//INCLUIR
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_CULTURA_INCLUIR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_CULTURA_INCLUIR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR INCLUIR INFORMAÇÕES SOBRE OS DADOS GERAIS DA TABELA BÁSICA-ITCD CULTURA. ESTA FUNCIONALIDADE TEM O SEGUINTE FLUXO:\n" + 
	   "1- SELECIONAR O TIPO DE PESQUISA\n" + 
	   "2- INFORMAR PARTE DA DESCRIÇÃO OU O CÓDIGO.\n" + 
	   "3- PRESSIONE O BOTÃO PESQUISAR.SERÁ APRESENTA UMA TABELA COM AS SEGUINTES COLUNAS:CÓDIGO, DESCRIÇÃO DO CULTURA E STATUS.\n" + 
	   "4- SELECIONE O CÓDIGO DESEJADO NA TABELA.NESTE MOMENTO SERÁ APRESENTADO UMA NOVA JANELA COM OS CAMPOS: DESCRIÇÃO E STATUS. QUE PODERAM SER ALTERADOS.\n" + 
	   "OBS: ESTÁ FUNCIONALIDADE TEM O CONCEITO DE:STATUS ATIVO E STATUS INATIVO. QUE NÃO PERMITEM A EXCLUSÃO DEFINITIVA DE QUALQUER ITEM.");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - INCLUIR CULTURA");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(1);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);
		//ALTERAR
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_CULTURA_CONSULTAR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_CULTURA_ALTERAR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR CONSULTAR INFORMAÇÕES SOBRE OS DADOS GERAIS DA TABELA BÁSICA-ITCD CULTURA. ESTA FUNCIONALIDADE TEM O SEGUINTE FLUXO:\n" + 
	   "1- SELECIONAR O TIPO DE PESQUISA\n" + 
	   "2- INFORMAR PARTE DA DESCRIÇÃO OU CÓDIGO DO CULTURA.\n" + 
	   "3- PRESSIONE O BOTÃO PESQUISAR.SERÁ APRESENTA UMA TABELA COM AS SEGUINTES COLUNAS:CÓDIGO, DESCRIÇÃO DO CULTURA E STATUS.");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("FILTRO PARA PESQUISA - ALTERAR CULTURA");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(1);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);

	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_CULTURA_ALTERAR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_CULTURA_ALTERAR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR ALTERAR INFORMAÇÕES SOBRE OS DADOS GERAIS DA TABELA BÁSICA-ITCD CULTURA. ESTA FUNCIONALIDADE TEM O SEGUINTE FLUXO:\n" + 
	   "1- SELECIONAR O TIPO DE PESQUISA\n" + 
	   "2- INFORMAR PARTE DA DESCRIÇÃO OU O CÓDIGO.\n" + 
	   "3- PRESSIONE O BOTÃO PESQUISAR.SERÁ APRESENTA UMA TABELA COM AS SEGUINTES COLUNAS:CÓDIGO, DESCRIÇÃO DO CULTURA E STATUS.\n" + 
	   "4- SELECIONE O CÓDIGO DESEJADO NA TABELA.NESTE MOMENTO SERÁ APRESENTADO UMA NOVA JANELA COM OS CAMPOS: DESCRIÇÃO E STATUS. QUE PODERAM SER ALTERADOS.\n" + 
	   "OBS: ESTÁ FUNCIONALIDADE TEM O CONCEITO DE:STATUS ATIVO E STATUS INATIVO. QUE NÃO PERMITEM A EXCLUSÃO DEFINITIVA DE QUALQUER ITEM.");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - ALTERAR CULTURA");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(2);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);
		//CONSULTAR
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_CULTURA_CONSULTAR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_CULTURA_CONSULTAR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR CONSULTAR INFORMAÇÕES SOBRE OS DADOS GERAIS DA TABELA BÁSICA-ITCD CULTURA. ESTA FUNCIONALIDADE TEM O SEGUINTE FLUXO:\n" + 
	   "1- SELECIONAR O TIPO DE PESQUISA\n" + 
	   "2- INFORMAR PARTE DA DESCRIÇÃO OU CÓDIGO DO CULTURA.\n" + 
	   "3- PRESSIONE O BOTÃO PESQUISAR.SERÁ APRESENTA UMA TABELA COM AS SEGUINTES COLUNAS:CÓDIGO, DESCRIÇÃO DO CULTURA E STATUS.");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("FILTRO PARA PESQUISA - CONSULTAR CULTURA");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(1);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);

	   //REBANHO
	   //INCLUIR
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_REBANHO_INCLUIR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_REBANHO_INCLUIR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TTELA RESPONSÁVEL POR INCLUIR INFORMAÇÕES SOBRE OS DADOS GERAIS DA TABELA BÁSICA-ITCD REBANHO. ESTA FUNCIONALIDADE TEM O SEGUINTE FLUXO:\n" + 
	   "1- INFORMAR A DESCRIÇÃO DA REBANHO\n" + 
	   "2- PRESSIONE O BOTÃO INCLUIR.");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS DO REGISTRO - INCLUIR REBANHO");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(1);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);
	   //ALTERAR
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_REBANHO_CONSULTAR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_REBANHO_ALTERAR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR CONSULTAR INFORMAÇÕES SOBRE OS DADOS GERAIS DA TABELA BÁSICA-ITCD REBANHO. ESTA FUNCIONALIDADE TEM O SEGUINTE FLUXO:\n" + 
	   "1- SELECIONAR O TIPO DE REBANHO\n" + 
	   "2- INFORMAR PARTE DA DESCRIÇÃO OU CÓDIGO DO REBANHO.\n" + 
	   "3- PRESSIONE O BOTÃO PESQUISAR.SERÁ APRESENTA UMA TABELA COM AS SEGUINTES COLUNAS:CÓDIGO, DESCRIÇÃO DO REBANHO E STATUS.");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("FILTRO PARA PESQUISA - ALTERAR REBANHO");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(1);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);

	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_REBANHO_ALTERAR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_REBANHO_ALTERAR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR ALTERAR INFORMAÇÕES SOBRE OS DADOS GERAIS DA TABELA BÁSICA-ITCD REBANHO. ESTA FUNCIONALIDADE TEM O SEGUINTE FLUXO:\n" + 
	   "1- SELECIONAR O TIPO DE REBANHO\n" + 
	   "2- INFORMAR PARTE DA DESCRIÇÃO OU O CÓDIGO.\n" + 
	   "3- PRESSIONE O BOTÃO PESQUISAR.SERÁ APRESENTA UMA TABELA COM AS SEGUINTES COLUNAS:CÓDIGO, DESCRIÇÃO DO CULTURA E STATUS.\n" + 
	   "4- SELECIONE O CÓDIGO DESEJADO NA TABELA.NESTE MOMENTO SERÁ APRESENTADO UMA NOVA JANELA COM OS CAMPOS: DESCRIÇÃO E STATUS. QUE PODERAM SER ALTERADOS.\n" + 
	   "OBS: ESTÁ FUNCIONALIDADE TEM O CONCEITO DE:STATUS ATIVO E STATUS INATIVO. QUE NÃO PERMITEM A EXCLUSÃO DEFINITIVA DE QUALQUER ITEM.");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - ALTERAR REBANHO");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(2);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);
	   //CONSULTAR
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_REBANHO_CONSULTAR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_REBANHO_CONSULTAR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR CONSULTAR INFORMAÇÕES SOBRE OS DADOS GERAIS DA TABELA BÁSICA-ITCD REBANHO. ESTA FUNCIONALIDADE TEM O SEGUINTE FLUXO:\n" + 
	   "1- SELECIONAR O TIPO DE REBANHO\n" + 
	   "2- INFORMAR PARTE DA DESCRIÇÃO OU CÓDIGO DO REBANHO.\n" + 
	   "3- PRESSIONE O BOTÃO PESQUISAR.SERÁ APRESENTA UMA TABELA COM AS SEGUINTES COLUNAS:CÓDIGO, DESCRIÇÃO DO REBANHO E STATUS.");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("FILTRO PARA PESQUISA - CONSULTAR REBANHO");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(1);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);

	   //PARAMETRO LEGISLACAO
	   //INCLUIR
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_PARAMETRO_LEGISLACAO_INCLUIR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_PARAMETROS_LEGISLACAO_INCLUIR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR INCLUIR INFORMAÇÕES SOBRE OS DADOS GERAIS DA TABELA BÁSICA-ITCD PARÂMETROS DE LEGISLAÇÃO. ESTA FUNCIONALIDADE TEM O SEGUINTE FLUXO:\n" + 
	   "1- INFORMAR NÚMERO DA LEGISLAÇÃO\n" + 
	   "2- INFORMAR DATA DE VIGÊNCIA INICIAL\n" + 
	   "3- INFORMAR DATA DE VIGÊNCIA FINAL\n" + 
	   "4- SELECIONAR TIPO DE FATO GERADOR\n" + 
	   "5- SELECIONAR ISENÇÃO\n" + 
	   "6- INFORMAR PERCENTUAL\n" + 
	   "7- INFORMAR QUANTIDADE UPF INICIAL\n" + 
	   "8- INFORMAR QUANTIDADE UPF FINAL\n" + 
	   "9- PRESSIONE O BOTÃO ADICIONAR.\n" + 
	   "10- INFORMAR QUANTIDADE DE DIAS INICIAL\n" + 
	   "11- INFORMAR QUANTIDADE DE DIAS FINAL\n" + 
	   "12- INFORMAR PERCENTUAL DA MULTA\n" + 
	   "13- PRESSIONE O BOTÃO ADICIONAR.\n" + 
	   "14- PRESSIONE O BOTÃO CONFIRMAR.PARA OS VALORES ADICIONADOS SEJAM GRAVADOS.");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS DO REGISTRO - INCLUIR PARÂMETROS DE LEGISLAÇÃO");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(1);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);
	   //ALTERAR
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_PARAMETRO_LEGISLACAO_CONSULTAR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_PARAMETROS_LEGISLACAO_ALTERAR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR CONSULTAR INFORMAÇÕES SOBRE OS DADOS GERAIS DA TABELA BÁSICA-ITCD PARÂMETROS DE LEGISLAÇÃO. NESTA FUNCIONALIDADE É APRESENTADO UMA TABELA COM TODOS OS ITENS GRAVADOS.\n" + 
	   "1- PRESSIONE O BOTÃO CANCELAR.PARA RETORNA AO MENU.");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("FILTRO DA PESQUISA - ALTERAR PARÂMETROS DE LEGISLAÇÃO");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(1);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);

	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_PARAMETRO_LEGISLACAO_ALTERAR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_PARAMETROS_LEGISLACAO_ALTERAR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR ALTERAR INFORMAÇÕES SOBRE OS DADOS GERAIS DA TABELA BÁSICA-ITCD PARÂMETROS DE LEGISLAÇÃO.\n" + 
	   "ESTA FUNCIONALIDADE INICIALIZA COM UMA TABELA COMPOSTA PELOS CAMPOS (CÓDIGO,NÚMERO DA LEGISLAÇÃO,DATA DE VIGÊNCIA INICIAL, DATA DE VIGÊNCIA FINAL E STATUS) E PROCEDIMENTO DE USO TEM O SEGUINTE FLUXO:\n" + 
	   "1- SELECIONAR O CÓDIGO.SERÁ APRESENTADO UMA JANELA COMPOSTA COM OS ITENS NECESSÁRIOS PARA ALTERAÇÃO.\n" + 
	   "NO FLUXO LEGISLAÇÃO. É POSSIVEL ALTERAR OS SEGUINTES ITENS:\n" + 
	   "NÚMERO DA LEGISLAÇÃO, DATA DE VIGÊNCIA INICIAL,DATA DE VIGÊNCIA FINAL OU STATUS.\n" + 
	   "NO FLUXO ALÍQUOTA. É POSSIVEL ALTERAR OS SEGUINTES ITENS:\n" + 
	   "TIPO DE FATO GERADOR,ISENÇÃO,PERCENTUAL,QUANTIDADE UPF INICIAL OU\n" + 
	   "QUANTIDADE UPF FINAL.\n" + 
	   "NO FLUXO MULTA. É POSSIVEL ALTERAR OS SEGUINTES ITENS:\n" + 
	   "QUANTIDADE DE DIAS INICIAL, QUANTIDADE DE DIAS FINAL OU PERCENTUAL DA MULTA.\n" + 
	   "2- PRESSIONE O BOTÃO ADICIONAR.PARA ADICIONAR AS ALTERAÇÕES NOS FLUXOS ALÍQUOTA E MULTA.\n" + 
	   "OBS: ESTÁ FUNCIONALIDADE TEM O CONCEITO DE STATUS ATIVO E STATUS INATIVO. QUE NÃO PERMITEM A EXCLUSÃO DEFINITIVA DE QUALQUER ITEM NO FLUXO LEGISLAÇÃO.É POSSÍVEL EXCLUIR OS ITENS NOS FLUXOS ALÍQUOTA E MULTA.BASTA SELECIONAR O ITEM EXCLUIR DA TABELA.\n" + 
	   "3- PRESSIONE O BOTÃO ALTERAR.CONFIRMAR AS ALTERAÇÕES.");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS DO REGISTRO - ALTERAR PARÂMETROS DE LEGISLAÇÃO");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(2);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);
	   //CONSULTAR
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_PARAMETRO_LEGISLACAO_CONSULTAR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_PARAMETROS_LEGISLACAO_CONSULTAR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR CONSULTAR INFORMAÇÕES SOBRE OS DADOS GERAIS DA TABELA BÁSICA-ITCD PARÂMETROS DE LEGISLAÇÃO. NESTA FUNCIONALIDADE É APRESENTADO UMA TABELA COM TODOS OS ITENS GRAVADOS.\n" + 
	   "1- PRESSIONE O BOTÃO CANCELAR.PARA RETORNA AO MENU.");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("FILTRO DA PESQUISA - CONSULTAR PARÂMETROS DE LEGISLAÇÃO");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(1);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);

	   //NATUREZA DA OPERAÇÃO
	   //INCLUIR
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_NATUREZA_OPERACAO_INCLUIR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_NATUREZA_OPERACAO_INCLUIR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR INCLUIR INFORMAÇÕES SOBRE OS DADOS GERAIS DA TABELA BÁSICA-ITCD NATUREZA OPERAÇÃO. ESTA FUNCIONALIDADE TEM O SEGUINTE FLUXO:\n" + 
	   "1- SELECIONAR O TIPO GIA.EX: CAUSA MORTIS OU INTERVIVOS.\n" + 
	   "2- SELECIONAR O TIPO PROCESSO.EX: INVENTÁRIO/ARROLAMENTO.\n" + 
	   "3- INFORMAR A DESCRIÇÃO NATUREZA OPERAÇÃO.\n" + 
	   "4- SELECIONAR A BASE DE CÁLCULO REDUZIDO.EX: SIM OU NÃO.\n" + 
	   "5- INFORMAR O PERCENTUAL BASE DE CÁLCULO.\n" + 
	   "2- PRESSIONE O BOTÃO CONFIRMAR.");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS DO REGISTRO - INCLUIR NATUREZA OPERAÇÃO");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(1);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);
	   //ALTERAR
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_NATUREZA_OPERACAO_CONSULTAR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_NATUREZA_OPERACAO_ALTERAR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR CONSULTAR INFORMAÇÕES SOBRE OS DADOS GERAIS DA TABELA BÁSICA-ITCD NATUREZA OPERAÇÃO.\n" + 
	   "ESTA FUNCIONALIDADE JÁ INICIALIZA COM UMA TABELA APRESENTANDO TODOS OS ITENS ARMAZENADOS COM AS SEGUINTES COLUNAS:\n" + 
	   "CÓDIGO,TIPO GIA,TIPO PROCESSO,DESCRIÇÃO NATUREZA OPERAÇÃO,BASE DE CÁLCULO REDUZIDO,PERCENTUAL E STATUS.");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("FILTRO PARA PESQUISA - ALTERAR NATUREZA OPERAÇÃO");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(1);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);

	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_NATUREZA_OPERACAO_ALTERAR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_NATUREZA_OPERACAO_ALTERAR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR ALTERAR INFORMAÇÕES SOBRE OS DADOS GERAIS DA TABELA BÁSICA-ITCD NATUREZA OPERAÇÃO.ESTA FUNCIONALIDADE JÁ INICIALIZA COM UMA TABELA APRESENTANDO TODOS OS ITENS ARMAZENADOS.DEVEMOS SEGUIR O SEGUINTE FLUXO:\n" + 
	   "1- SELECIONAR O CÓDIGO.SERÁ APRESENTADO UMA JANELA COM OS SEGUINTES CAMPOS:DESCRIÇÃO DA NATUREZA OPERAÇÃO, BASE DE CÁLCULO REDUZIDO, PERCENTUAL BASE DE CÁLCULO E STATUS.QUE PODERAM SER ALTERADOS.\n" + 
	   "OBS: ESTÁ FUNCIONALIDADE TEM O CONCEITO DE STATUS ATIVO E STATUS INATIVO. QUE NÃO PERMITEM A EXCLUSÃO DEFINITIVA DE QUALQUER ITEM.");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - ALTERAR NATUREZA OPERAÇÃO");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(2);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);
	   //CONSULTAR
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_NATUREZA_OPERACAO_CONSULTAR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_NATUREZA_OPERACAO_CONSULTAR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR CONSULTAR INFORMAÇÕES SOBRE OS DADOS GERAIS DA TABELA BÁSICA-ITCD NATUREZA OPERAÇÃO.\n" + 
	   "ESTA FUNCIONALIDADE JÁ INICIALIZA COM UMA TABELA APRESENTANDO TODOS OS ITENS ARMAZENADOS COM AS SEGUINTES COLUNAS:\n" + 
	   "CÓDIGO,TIPO GIA,TIPO PROCESSO,DESCRIÇÃO NATUREZA OPERAÇÃO,BASE DE CÁLCULO REDUZIDO,PERCENTUAL E STATUS.");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("FILTRO PARA PESQUISA - CONSULTAR NATUREZA OPERAÇÃO");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(1);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);

		//CONFIGURACAO GERENCIAL DE PARAMETROS		
	   //ALTERAR
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_CONFIGURACAO_GERENCIAL_ALTERAR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_CONFIGURACAO_GERENCIAL_PARAMETROS_ALTERAR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR ALTERAR INFORMAÇÕES SOBRE OS DADOS GERAIS DA TABELA BÁSICA-ITCD CONFIGURAÇÃO GERENCIAL DE PARÂMETROS.\n" + 
	   "OBS: OS CAMPOS PARA PREENCHIMENTO SÃO GERADOS DINÂMICAMENTE CONFORME O TIPO DO PARÂMETRO(DATA, VALOR NÚMERICO, VALOR MONETÁRIO, TEXTO)\n" + 
	   "PROCEDIMENTOS:\n" + 
	   "1- SELECIONAR O CÓDIGO PARÂMETRO.\n" + 
	   "2- INFORMAR O VALOR SOLICITADO PELO PARÂMETRO.\n" + 
	   "3- PRESSIONE O BOTÃO CONFIRMAR.");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS DO REGISTRO - ALTERAR CONFIGURAÇÃO GERENCIAL DE PARÂMETROS");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(1);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);

	   //BENFEITORIA
	   //INCLUIR
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_BENFEITORIA_INCLUIR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_BENFEITORIAS_INCLUIR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS DO REGISTRO - INCLUIR BENFEITORIA");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(1);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);
	   //ALTERAR
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_BENFEITORIA_CONSULTAR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_BENFEITORIAS_ALTERAR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR CONSULTAR INFORMAÇÕES SOBRE OS DADOS GERAIS DA TABELA BÁSICA-ITCD BENFEITORIA. NESTA FUNCIONALIDADE É APRESENTADO UMA TABELA COM TODOS OS ITENS GRAVADOS.\n" + 
	   "1- PRESSIONE O BOTÃO CANCELAR.PARA RETORNA AO MENU.");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("FILTRO PARA PESQUISA - ALTERAR BENFEITORIA");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(1);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);

	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_BENFEITORIA_ALTERAR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_BENFEITORIAS_ALTERAR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR ALTERAR INFORMAÇÕES SOBRE OS DADOS GERAIS DA TABELA BÁSICA-ITCD BENFEITORIA. ESTA FUNCIONALIDADE INICIALIZA COM UMA TABELA COMPOSTA PELOS CAMPOS (CÓDIGO,DESCRIÇÃO E STATUS) E PROCEDIMENTO DE USO TEM O SEGUINTE FLUXO:\n" + 
	   "1- SELECIONAR O CÓDIGO.SERÁ APRESENTADO UMA JANELA COMPOSTA DOS CAMPOS (DESCRIÇÃO E STATUS).\n" + 
	   "2- INFORMAR O NOVO VALOR DESCRIÇÃO OU DO STATUS.\n" + 
	   "3- PRESSIONE O BOTÃO ALTERAR.\n" + 
	   "OBS: ESTÁ FUNCIONALIDADE TEM O CONCEITO DE:STATUS ATIVO E STATUS INATIVO. QUE NÃO PERMITEM A EXCLUSÃO DEFINITIVA DE QUALQUER ITEM.");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - ALTERAR BENFEITORIA");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(2);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);
	   //CONSULTAR
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_BENFEITORIA_CONSULTAR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_BENFEITORIAS_CONSULTAR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR CONSULTAR INFORMAÇÕES SOBRE OS DADOS GERAIS DA TABELA BÁSICA-ITCD BENFEITORIA. NESTA FUNCIONALIDADE É APRESENTADO UMA TABELA COM TODOS OS ITENS GRAVADOS.\\n\" + \n" + 
	   "	   \"1- PRESSIONE O BOTÃO CANCELAR.PARA RETORNA AO MENU.");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("FILTRO PARA PESQUISA - CONSULTAR BENFEITORIA");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(1);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);

	   //CONSTRUÇÃO
	   //INCLUIR
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_CONSTRUCAO_INCLUIR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_CONSTRUCAO_INCLUIR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS DO REGISTRO - INCLUIR CONSTRUÇÃO");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(1);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);
	   //ALTERAR
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_CONSTRUCAO_CONSULTAR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_CONSTRUCAO_ALTERAR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR CONSULTAR INFORMAÇÕES SOBRE OS DADOS GERAIS DA TABELA BÁSICA-ITCD CONSTRUÇÃO. NESTA FUNCIONALIDADE É APRESENTADO UMA TABELA COM TODOS OS ITENS GRAVADOS.\n" + 
	   "1- PRESSIONE O BOTÃO CANCELAR.PARA RETORNA AO MENU.");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("FILTRO PARA PESQUISA - ALTERAR CONSTRUÇÃO");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(1);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);

	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_CONSTRUCAO_ALTERAR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_CONSTRUCAO_ALTERAR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR ALTERAR INFORMAÇÕES SOBRE OS DADOS GERAIS DA TABELA BÁSICA-ITCD CONSTRUÇÃO. ESTA FUNCIONALIDADE INICIALIZA COM UMA TABELA COMPOSTA PELOS CAMPOS (CÓDIGO,DESCRIÇÃO E STATUS) E PROCEDIMENTO DE USO TEM O SEGUINTE FLUXO:\n" + 
	   "1- SELECIONAR O CÓDIGO.SERÁ APRESENTADO UMA JANELA COMPOSTA DOS CAMPOS (DESCRIÇÃO E STATUS).\n" + 
	   "2- INFORMAR O NOVO VALOR DESCRIÇÃO OU DO STATUS.\n" + 
	   "3- PRESSIONE O BOTÃO ALTERAR.\n" + 
	   "OBS: ESTÁ FUNCIONALIDADE TEM O CONCEITO DE:STATUS ATIVO E STATUS INATIVO. QUE NÃO PERMITEM A EXCLUSÃO DEFINITIVA DE QUALQUER ITEM.");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - ALTERAR CONSTRUÇÃO");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(2);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);
	   //CONSULTAR
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_CONSTRUCAO_CONSULTAR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_CONSTRUCAO_CONSULTAR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR CONSULTAR INFORMAÇÕES SOBRE OS DADOS GERAIS DA TABELA BÁSICA-ITCD CONSTRUÇÃO. NESTA FUNCIONALIDADE É APRESENTADO UMA TABELA COM TODOS OS ITENS GRAVADOS.\n" + 
	   "1- PRESSIONE O BOTÃO CANCELAR.PARA RETORNA AO MENU.");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("FILTRO PARA PESQUISA - CONSULTAR CONSTRUÇÃO");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(1);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);

	   //MULTA DE MORA INCLUIR
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_MULTA_MORA_INCLUIR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_INCLUIR_MULTA_MORA_INCLUIR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR INCLUIR INFORMAÇÕES SOBRE OS DADOS GERAIS DA TABELA BÁSICA-ITCD MULTA DE MORA. ESTA FUNCIONALIDADE TEM O SEGUINTE FLUXO:\n" + 
	   "1- INFORMAR PERCENTUAL DA MULTA DE MORA\n" + 
	   "2- INFORMAR PERCENTUAL MÁXIMO DA MULTA DE MORA\n" + 
	   "3- PRESSIONE O BOTÃO INCLUIR.");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - INCLUIR MULTA DE MORA");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(1);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);

	   //AJUDA
	   //INCLUIR
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_AJUDA_INCLUIR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_AJUDA_INCLUIR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR INCLUIR INFORMAÇÕES SOBRE OS DADOS GERAIS DA TABELA BÁSICA-ITCD AJUDA. ESTA FUNCIONALIDADE TEM O SEGUINTE FLUXO:\n" + 
	   "1- SELECIONAR MÓDULO\n" + 
	   "2- SELECIONAR SUBMODULO\n" + 
	   "3- SELECIONAR FUNCIONALIDADE\n" + 
	   "4- SELECIONAR TELA.SERÁ APRESENTADO OS SEGUINTES CAMPOS(TÍTULO TELA,DESCRIÇÃO AJUDA TELA,DESCRIÇÃO AJUDA RÓTULO E UMA TABELA COM CÓDIGO, RÓTULO E DESCRIÇÃO DOS RÓTULOS INSERIDOS.\n" + 
	   "5- INFORME TÍTULO TELA\n" + 
	   "6- INFORME DESCRIÇÃO AJUDA TELA\n" + 
	   "7- SELECIONAR HABILITAR\n" + 
	   "ESTÁ FUNCIONALIDADE POSSÍBILITA ALTERA E EXCLUIR OS ITENS DO FLUXO CAMPO.\n" + 
	   "OBS: ESTÁ FUNCIONALIDADE TEM O CONCEITO DE STATUS ATIVO E STATUS INATIVO. QUE NÃO PERMITEM A EXCLUSÃO DEFINITIVA DE QUALQUER ITEM.");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS DO REGISTRO - INCLUIR AJUDA");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(1);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);
	   //ALTERAR
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_AJUDA_ALTERAR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_AJUDA_ALTERAR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR ALTERAR INFORMAÇÕES SOBRE OS DADOS GERAIS DA TABELA BÁSICA-ITCD AJUDA. ESTA UNCIONALIDADE TEM O SEGUINTE FLUXO:\n" + 
	   "1- SELECIONAR MÓDULO\n" + 
	   "2- SELECIONAR SUBMODULO\n" + 
	   "3- SELECIONAR FUNCIONALIDADE\n" + 
	   "4- SELECIONAR TELA\n" + 
	   "5- ALTERE OS CAMPOS DESEJADOS.\n" + 
	   "6- PRESSIONE O BOTÃO SALVAR.");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - ALTERAR AJUDA");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(1);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);
	   //CONSULTAR
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_AJUDA_CONSULTAR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_AJUDA_CONSULTAR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR CONSULTAR INFORMAÇÕES SOBRE OS DADOS GERAIS DA TABELA BÁSICA-ITCD AJUDA. ESTA FUNCIONALIDADE TEM O SEGUINTE FLUXO:\n" + 
	   "1- SELECIONAR MÓDULO\n" + 
	   "2- SELECIONAR SUBMODULO\n" + 
	   "3- SELECIONAR FUNCIONALIDADE\n" + 
	   "4- SELECIONAR TELA\n" + 
	   "5- PRESSIONE O BOTÃO PESQUISAR.SERÁ APRESENTADO OS SEGUINTES CAMPOS(TÍTULO TELA,DESCRIÇÃO AJUDA TELA,DESCRIÇÃO AJUDA RÓTULO E UMA TABELA COM CÓDIGO, RÓTULO E DESCRIÇÃO DOS RÓTULOS INSERIDOS.");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("FILTRO PARA PESQUISA - CONSULTAR AJUDA");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(1);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);

		//GIA-ITCD
		int prioridade = 1;
		//ALTERAR
		//CONSULTAR GIA - CONTRIBUINTE
		prioridade = 1;      
		telaFuncionalidade = new TelaFuncionalidadeVo();
		telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_CONSULTAR_LIVRE));
		telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_GIA_ITCD_ALTERAR));
		telaFuncionalidade.setDescricaoTelaFuncionalidade(TELA_FUNCIONALIDADE_CONSULTAR_GIA_ITCD_GERAL_CONTRIBUINTE);
		telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - ALTERAR GIA - ITCD - CONSULTAR GIA PELO CONTRIBUINTE");
		telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaFuncionalidade.setCodigoOrdenacao(prioridade++);
		telaFuncionalidades.getCollVO().add(telaFuncionalidade);          
		//CONSULTAR GIA - SERVIDOR
		telaFuncionalidade = new TelaFuncionalidadeVo();
		telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_CONSULTAR_SERVIDOR));
		telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_GIA_ITCD_ALTERAR));
		telaFuncionalidade.setDescricaoTelaFuncionalidade(TELA_FUNCIONALIDADE_CONSULTAR_GIA_ITCD_GERAL_SERVIDOR);
		telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - ALTERAR GIA - ITCD - CONSULTAR GIA PELO SERVIDOR");
		telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaFuncionalidade.setCodigoOrdenacao(prioridade++);
		telaFuncionalidades.getCollVO().add(telaFuncionalidade);          
		
	   //INVENTARIO ARROLAMENTO
		//INCLUIR
		//ABA DADOS GERAIS
		prioridade = 1;
		telaFuncionalidade = new TelaFuncionalidadeVo();
		telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_DADOS_GERAIS_INCLUIR));
		telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_GIA_INVENTARIO_ARROLAMENTO_INCLUIR));
		telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR INCLUIR OS DADOS GERAIS DA GIA-ITCD INVENTÁRIO ARROLAMENTO.O \n" + 
		"PROCEDIMENTO DE USO TEM O SEGUINTE FLUXO:\n" + 
		"1- LOCALIZE O CPF INVARIANTE.\n" + 
		"2- DEFINA O MUNICIPIO DO INVARIANTE.\n" + 
		"3- DEFINA O TIPO DE DOCUMENTO DE CUJUS.\n" + 
		"4- LOCALIZE DE CUJUS.\n" + 
		"5- DEFINA O ESTADO CIVIL.\n" + 
		"6- CASO O ESTADO CIVIL SEJA CASADO DEFINA O REGIME DO CASAMENTO.\n" + 
		"7- CASO O ESTADO CIVIL SEJA CASADO OU CONVIVENTE LOCALIZE O CPF MEEIRO.\n" + 
		"8- INSIRA O NÚMERO PROCESSO INV./ARROLAMENTO.\n" + 
		"9- INSIRA A DATA INV./ARROLAMENTO.\n" + 
		"10- INSIRA A UF DE ABERTURA INV./ARROLAMENTO.\n" + 
		"11- INSIRA A DATA FALECIMENTO.\n" + 
		"12- INSIRA O NÚMERO HERDEIROS.\n" + 
		"13- INSIRA O JUÍZO/COMARCA.\n" + 
		"14- LOCALIZE O CPF PROCURADOR.\n" + 
		"15- DEFINE A NATUREZA OPERAÇÃO.\n" + 
		"16- EXECUTE A OPERAÇÃO SALVAR.\n" + 
		"OBS: TODOS CAMPOS SÃO OBRIGATÓRIOS EXCETO JUÍZO/COMARCA E CPF PROCURADOR.");
		telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - INCLUIR GIA - ITCD INVENTÁRIO ARROLAMENTO - DADOS GERAIS");
		telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaFuncionalidade.setCodigoOrdenacao(prioridade++);
		telaFuncionalidades.getCollVO().add(telaFuncionalidade);
	   //ABA BENS TRIBUTÁVEIS
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_INCLUIR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_GIA_INVENTARIO_ARROLAMENTO_INCLUIR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade(TELA_FUNCIONALIDADE_ABA_BENS_TRIBUTAVEIS);
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - INCLUIR GIA - ITCD INVENTÁRIO ARROLAMENTO - BENS TRIBUTÁVEIS");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(prioridade++);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);
	   //ABA BENS TRIBUTÁVEIS - IMÓVEL RURAL
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_GIA_INVENTARIO_ARROLAMENTO_INCLUIR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade(TELA_FUNCIONALIDADE_ABA_BENS_TRIBUTAVEIS_FICHA_IMOVEL_RURAL);
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - INCLUIR GIA - ITCD INVENTÁRIO ARROLAMENTO - BENS TRIBUTÁVEIS - FICHA IMÓVEL RURAL");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(prioridade++);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);
	   //ABA BENS TRIBUTÁVEIS - IMÓVEL URBANO
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_GIA_INVENTARIO_ARROLAMENTO_INCLUIR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade(TELA_FUNCIONALIDADE_ABA_BENS_TRIBUTAVEIS_FICHA_IMOVEL_URBANO);
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - INCLUIR GIA - ITCD INVENTÁRIO ARROLAMENTO - BENS TRIBUTÁVEIS - FICHA IMÓVEL URBANO");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(prioridade++);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);
	   //ABA BENEFICIÁRIOS
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BENEFICIARIO_INCLUIR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_GIA_INVENTARIO_ARROLAMENTO_INCLUIR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR INCLUIR BENEFICIÁRIOS A GIA-ITCD .O PROCEDIMENTO DE USO TEM O \n" + 
	   "SEGUINTE FLUXO:\n" + 
	   "1- LOCALIZE CPF BENEFICIÁRIO.\n" + 
	   "2- EXECUTE A OPERAÇÃO SALVAR.\n" + 
	   "OBS: CASO DESEJE EXCLUIR UM BENEFICIÁRIO CLIQUE NO LINK EXCLUIR.\n" + 
	   "OBS: A QUANTIDADE DE BENEFICIÁRIOS DEVE SER A MESMA DE NÚMERO DE HERDEIROS.");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - INCLUIR GIA - ITCD INVENTÁRIO ARROLAMENTO - BENEFICIÁRIOS");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(prioridade++);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);
	   //ABA DEMONSTRATIVO DE CÁLCULO
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_DEMONSTRATIVO_CALCULO));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_GIA_INVENTARIO_ARROLAMENTO_INCLUIR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade(TELA_FUNCIONALIDADE_ABA_DEMONSTRATIVO_CALCULO);
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - INCLUIR GIA - ITCD INVENTÁRIO ARROLAMENTO - DEMONSTRATIVO DE CÁLCULO");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(prioridade++);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);

	   //ALTERAR
	   //ABA DADOS GERAIS
		prioridade = 1;
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_DADOS_GERAIS_ALTERAR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_GIA_INVENTARIO_ARROLAMENTO_ALTERAR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR INCLUIR OS DADOS GERAIS DA GIA-ITCD INVENTÁRIO ARROLAMENTO.O \n" + 
	   "PROCEDIMENTO DE USO TEM O SEGUINTE FLUXO:\n" + 
	   "1- LOCALIZE O CPF INVARIANTE.\n" + 
	   "2- DEFINA O MUNICIPIO DO INVARIANTE.\n" + 
	   "3- DEFINA O TIPO DE DOCUMENTO DE CUJUS.\n" + 
	   "4- LOCALIZE DE CUJUS.\n" + 
	   "5- DEFINA O ESTADO CIVIL.\n" + 
	   "6- CASO O ESTADO CIVIL SEJA CASADO DEFINA O REGIME DO CASAMENTO.\n" + 
	   "7- CASO O ESTADO CIVIL SEJA CASADO OU CONVIVENTE LOCALIZE O CPF MEEIRO.\n" + 
	   "8- INSIRA O NÚMERO PROCESSO INV./ARROLAMENTO.\n" + 
	   "9- INSIRA A DATA INV./ARROLAMENTO.\n" + 
	   "10- INSIRA A UF DE ABERTURA INV./ARROLAMENTO.\n" + 
	   "11- INSIRA A DATA FALECIMENTO.\n" + 
	   "12- INSIRA O NÚMERO HERDEIROS.\n" + 
	   "13- INSIRA O JUÍZO/COMARCA.\n" + 
	   "14- LOCALIZE O CPF PROCURADOR.\n" + 
	   "15- DEFINE A NATUREZA OPERAÇÃO.\n" + 
	   "16- EXECUTE A OPERAÇÃO SALVAR.\n" + 
	   "OBS: TODOS CAMPOS SÃO OBRIGATÓRIOS EXCETO JUÍZO/COMARCA E CPF PROCURADOR.");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - ALTERAR GIA - ITCD INVENTÁRIO ARROLAMENTO - DADOS GERAIS");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(prioridade++);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);
	   //ABA BENS TRIBUTÁVEIS
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_ALTERAR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_GIA_INVENTARIO_ARROLAMENTO_ALTERAR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade(TELA_FUNCIONALIDADE_ABA_BENS_TRIBUTAVEIS);
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - ALTERAR GIA - ITCD INVENTÁRIO ARROLAMENTO - BENS TRIBUTÁVEIS");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(prioridade++);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);
	   //ABA BENS TRIBUTÁVEIS - IMÓVEL RURAL
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_GIA_INVENTARIO_ARROLAMENTO_ALTERAR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade(TELA_FUNCIONALIDADE_ABA_BENS_TRIBUTAVEIS_FICHA_IMOVEL_RURAL);
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - ALTERAR GIA - ITCD INVENTÁRIO ARROLAMENTO - BENS TRIBUTÁVEIS - FICHA IMÓVEL RURAL");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(prioridade++);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);
	   //ABA BENS TRIBUTÁVEIS - IMÓVEL URBANO
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_GIA_INVENTARIO_ARROLAMENTO_ALTERAR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade(TELA_FUNCIONALIDADE_ABA_BENS_TRIBUTAVEIS_FICHA_IMOVEL_URBANO);
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - ALTERAR GIA - ITCD INVENTÁRIO ARROLAMENTO - BENS TRIBUTÁVEIS - FICHA IMÓVEL URBANO");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(prioridade++);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);
	   //ABA BENEFICIÁRIOS
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BENEFICIARIO_ALTERAR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_GIA_INVENTARIO_ARROLAMENTO_ALTERAR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR INCLUIR BENEFICIÁRIOS A GIA-ITCD .O PROCEDIMENTO DE USO TEM O \n" + 
	   "SEGUINTE FLUXO:\n" + 
	   "1- LOCALIZE CPF BENEFICIÁRIO.\n" + 
	   "2- EXECUTE A OPERAÇÃO SALVAR.\n" + 
	   "OBS: CASO DESEJE EXCLUIR UM BENEFICIÁRIO CLIQUE NO LINK EXCLUIR.\n" + 
	   "OBS: A QUANTIDADE DE BENEFICIÁRIOS DEVE SER A MESMA DE NÚMERO DE HERDEIROS.");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - ALTERAR GIA - ITCD INVENTÁRIO ARROLAMENTO - BENEFICIÁRIOS");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(prioridade++);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);
	   //ABA DEMONSTRATIVO DE CÁLCULO
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_DEMONSTRATIVO_CALCULO));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_GIA_INVENTARIO_ARROLAMENTO_ALTERAR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade(TELA_FUNCIONALIDADE_ABA_DEMONSTRATIVO_CALCULO);
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - ALTERAR GIA - ITCD INVENTÁRIO ARROLAMENTO - DEMONSTRATIVO DE CÁLCULO");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(prioridade++);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);


		//DOACAO
		//INCLUIR
		//ABA DADOS GERAIS
		prioridade = 1;
		telaFuncionalidade = new TelaFuncionalidadeVo();
		telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_DADOS_GERAIS_INCLUIR));
		telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_GIA_DOACAO_INCLUIR));
		telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR INCLUIR INFORMAÇÕES SOBRE OS DADOS GERAIS DOAÇÃO. ESTA FUNCIONALIDADE TEM O SEGUINTE FLUXO: \n" + 
		"01- LOCALIZAR CPF DECLARANTE.SERÁ APRESENTADA A FUNCIONALIDADE PESQUISAR CONTRIBUINTE POR, SELECIONE A OPÇÃO DE CONSULTA E CONFIRME A OPERAÇÃO.\n" + 
		"02- LOCALIZAR CPF DO PROCURADOR.SERÁ APRESENTADA A FUNCIONALIDADE PESQUISAR CONTRIBUINTE POR, SELECIONE A OPÇÃO DE CONSULTA E CONFIRME A OPERAÇÃO.\n" + 
		"03- SELECIONE NATUREZA OPERAÇÃO.\n" + 
		"04- INFORME A FRAÇÃO IDEAL.");
		telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - INCLUIR GIA - ITCD DOAÇÃO / OUTROS - DADOS GERAIS");
		telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaFuncionalidade.setCodigoOrdenacao(prioridade++);
		telaFuncionalidades.getCollVO().add(telaFuncionalidade);
		//ABA BENS TRIBUTÁVEIS
		telaFuncionalidade = new TelaFuncionalidadeVo();
		telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_INCLUIR));
		telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_GIA_DOACAO_INCLUIR));
		telaFuncionalidade.setDescricaoTelaFuncionalidade(TELA_FUNCIONALIDADE_ABA_BENS_TRIBUTAVEIS);
		telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - INCLUIR GIA - ITCD DOAÇÃO / OUTROS - BENS TRIBUTÁVEIS");
		telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaFuncionalidade.setCodigoOrdenacao(prioridade++);
		telaFuncionalidades.getCollVO().add(telaFuncionalidade);
		//ABA BENS TRIBUTÁVEIS - IMÓVEL RURAL
		telaFuncionalidade = new TelaFuncionalidadeVo();
		telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
		telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_GIA_DOACAO_INCLUIR));
		telaFuncionalidade.setDescricaoTelaFuncionalidade(TELA_FUNCIONALIDADE_ABA_BENS_TRIBUTAVEIS_FICHA_IMOVEL_RURAL);
		telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - INCLUIR GIA - ITCD DOAÇÃO / OUTROS - BENS TRIBUTÁVEIS - FICHA IMÓVEL RURAL");
		telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaFuncionalidade.setCodigoOrdenacao(prioridade++);
		telaFuncionalidades.getCollVO().add(telaFuncionalidade);
		//ABA BENS TRIBUTÁVEIS - IMÓVEL URBANO
		telaFuncionalidade = new TelaFuncionalidadeVo();
		telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
		telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_GIA_DOACAO_INCLUIR));
		telaFuncionalidade.setDescricaoTelaFuncionalidade(TELA_FUNCIONALIDADE_ABA_BENS_TRIBUTAVEIS_FICHA_IMOVEL_URBANO);
		telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - INCLUIR GIA - ITCD DOAÇÃO / OUTROS - BENS TRIBUTÁVEIS - FICHA IMÓVEL URBANO");
		telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaFuncionalidade.setCodigoOrdenacao(prioridade++);
		telaFuncionalidades.getCollVO().add(telaFuncionalidade);
		//ABA BENEFICIÁRIOS
		telaFuncionalidade = new TelaFuncionalidadeVo();
		telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BENEFICIARIO_INCLUIR));
		telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_GIA_DOACAO_INCLUIR));
		telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR INCLUIR INFORMAÇÕES SOBRE OS BENEFICIÁRIOS DOAÇÃO. ESTA FUNCIONALIDADE TEM O SEGUINTE FLUXO:\n" + 
		"01- LOCALIZAR O CPF BENEFICIÁRIO.SERÁ APRESENTADA A FUNCIONALIDADE PESQUISAR BENEFICIÁRIO POR, SELECIONE A OPÇÃO DE CONSULTA E CONFIRME A OPERAÇÃO.\n" + 
		"02- INFORME O PERCENTUAL ATRÍBUIDO.\n" + 
		"03- REALIZE A OPERAÇÃO ADICIONAR.");
		telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - INCLUIR GIA - ITCD DOAÇÃO / OUTROS - BENEFICIÁRIOS");
		telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaFuncionalidade.setCodigoOrdenacao(prioridade++);
		telaFuncionalidades.getCollVO().add(telaFuncionalidade);
		//ABA DEMONSTRATIVO DE CÁLCULO
		telaFuncionalidade = new TelaFuncionalidadeVo();
		telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_DEMONSTRATIVO_CALCULO));
		telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_GIA_DOACAO_INCLUIR));
		telaFuncionalidade.setDescricaoTelaFuncionalidade(TELA_FUNCIONALIDADE_ABA_DEMONSTRATIVO_CALCULO);
		telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - INCLUIR GIA - ITCD DOAÇÃO / OUTROS - DEMONSTRATIVO DE CÁLCULO");
		telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaFuncionalidade.setCodigoOrdenacao(prioridade++);
		telaFuncionalidades.getCollVO().add(telaFuncionalidade);
		
		//ALTERAR
		//ABA DADOS GERAIS
		prioridade = 1;
		telaFuncionalidade = new TelaFuncionalidadeVo();
		telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_DADOS_GERAIS_ALTERAR));
		telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_GIA_DOACAO_ALTERAR));
		telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR ALTERAR OS DADOS GERAIS DA GIA-ITCD DOAÇÃO .O PROCEDIMENTO DE USO \n" + 
		"TEM O SEGUINTE FLUXO:\n" + 
		"1- FAÇA A ALTERAÇÃO DESEJADA.\n" + 
		"2- EXECUTE A OPERAÇÃO SALVAR.\n" + 
		"OBS: TODOS OS CAMPOS SÃO ALTERÁVEIS.");
		telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - ALTERAR GIA - ITCD DOAÇÃO / OUTROS - DADOS GERAIS");
		telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaFuncionalidade.setCodigoOrdenacao(prioridade++);
		telaFuncionalidades.getCollVO().add(telaFuncionalidade);
		//ABA BENS TRIBUTÁVEIS
		telaFuncionalidade = new TelaFuncionalidadeVo();
		telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_ALTERAR));
		telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_GIA_DOACAO_ALTERAR));
		telaFuncionalidade.setDescricaoTelaFuncionalidade(TELA_FUNCIONALIDADE_ABA_BENS_TRIBUTAVEIS);
		telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - ALTERAR GIA - ITCD DOAÇÃO / OUTROS - BENS TRIBUTÁVEIS");
		telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaFuncionalidade.setCodigoOrdenacao(prioridade++);
		telaFuncionalidades.getCollVO().add(telaFuncionalidade);
		//ABA BENS TRIBUTÁVEIS - IMÓVEL RURAL
		telaFuncionalidade = new TelaFuncionalidadeVo();
		telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
		telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_GIA_DOACAO_ALTERAR));
		telaFuncionalidade.setDescricaoTelaFuncionalidade(TELA_FUNCIONALIDADE_ABA_BENS_TRIBUTAVEIS_FICHA_IMOVEL_RURAL);
		telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - ALTERAR GIA - ITCD DOAÇÃO / OUTROS - BENS TRIBUTÁVEIS - FICHA IMÓVEL RURAL");
		telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaFuncionalidade.setCodigoOrdenacao(prioridade++);
		telaFuncionalidades.getCollVO().add(telaFuncionalidade);
		//ABA BENS TRIBUTÁVEIS - IMÓVEL URBANO
		telaFuncionalidade = new TelaFuncionalidadeVo();
		telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
		telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_GIA_DOACAO_ALTERAR));
		telaFuncionalidade.setDescricaoTelaFuncionalidade(TELA_FUNCIONALIDADE_ABA_BENS_TRIBUTAVEIS_FICHA_IMOVEL_URBANO);
		telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - ALTERAR GIA - ITCD DOAÇÃO / OUTROS - BENS TRIBUTÁVEIS - FICHA IMÓVEL URBANO");
		telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaFuncionalidade.setCodigoOrdenacao(prioridade++);
		telaFuncionalidades.getCollVO().add(telaFuncionalidade);
		//ABA BENEFICIÁRIOS
		telaFuncionalidade = new TelaFuncionalidadeVo();
		telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BENEFICIARIO_ALTERAR));
		telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_GIA_DOACAO_ALTERAR));
		telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR ALTERAR OS BENEFICIÁRIOS DA GIA-ITCD DOAÇÃO.O PROCEDIMENTO DE USO \n" + 
		"TEM O SEGUINTE FLUXO:\n" + 
		"1- FAÇA A ALTERAÇÃO DESEJADA.\n" + 
		"2- EXECUTE A OPERAÇÃO SALVAR.\n" + 
		"OBS: NÃO É POSSÍVEL DEIXA A GIA-ITCD SEM BENEFICIÁRIOS.\n" + 
		"OBS: A SOMA DO PORCENTUAL ATRIBUÍDO AOS BENEFICIÁRIOS DEVE SER IGUAL A 100%. \n");
		telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - ALTERAR GIA - ITCD DOAÇÃO / OUTROS - BENEFICIÁRIOS");
		telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaFuncionalidade.setCodigoOrdenacao(prioridade++);
		telaFuncionalidades.getCollVO().add(telaFuncionalidade);
		//ABA DEMONSTRATIVO DE CÁLCULO
		telaFuncionalidade = new TelaFuncionalidadeVo();
		telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_DEMONSTRATIVO_CALCULO));
		telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_GIA_DOACAO_ALTERAR));
		telaFuncionalidade.setDescricaoTelaFuncionalidade(TELA_FUNCIONALIDADE_ABA_DEMONSTRATIVO_CALCULO);
		telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - ALTERAR GIA - ITCD DOAÇÃO / OUTROS - DEMONSTRATIVO DE CÁLCULO");
		telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaFuncionalidade.setCodigoOrdenacao(prioridade++);
		telaFuncionalidades.getCollVO().add(telaFuncionalidade);

	   //SEPARACAO DIVORCIO PARTILHA
	   //INCLUIR
	   //ABA DADOS GERAIS
	   prioridade = 1;
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_DADOS_GERAIS_INCLUIR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_GIA_SEPARACAO_INCLUIR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR INCLUIR INFORMAÇÕES SOBRE OS DADOS GERAIS SEPARAÇÃO/DIVÓRCIO/PARTILHA. ESTA FUNCIONALIDADE TEM O SEGUINTE FLUXO: \n" + 
	   "01- LOCALIZAR CPF DECLARANTE.SERÁ APRESENTADA A FUNCIONALIDADE PESQUISAR CONTRIBUINTE POR, SELECIONE A OPÇÃO DE CONSULTA E CONFIRME A OPERAÇÃO.\n" + 
	   "02- SELECIONE REGIME DE CASAMENTO.\n" + 
	   "03- INFORME O NÚMERO PROCESSO.\n" + 
	   "04- INFORME A DATA.\n" + 
	   "05- SELECIONE A NATUREZA DA OPERAÇÃO.\n" + 
	   "06- LOCALIZAR CPF DO PROCURADOR.SERÁ APRESENTADA A FUNCIONALIDADE PESQUISAR CONTRIBUINTE POR, SELECIONE A OPÇÃO DE CONSULTA E CONFIRME A OPERAÇÃO.");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - INCLUIR GIA - ITCD SEPARAÇÃO/DIVÓRCIO/PARTILHA - DADOS GERAIS");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(prioridade++);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);
	   //ABA BENS TRIBUTÁVEIS
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_INCLUIR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_GIA_SEPARACAO_INCLUIR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade(TELA_FUNCIONALIDADE_ABA_BENS_TRIBUTAVEIS);
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - INCLUIR GIA - ITCD SEPARAÇÃO/DIVÓRCIO/PARTILHA - BENS TRIBUTÁVEIS");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(prioridade++);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);
	   //ABA BENS TRIBUTÁVEIS - IMÓVEL RURAL
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_GIA_SEPARACAO_INCLUIR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade(TELA_FUNCIONALIDADE_ABA_BENS_TRIBUTAVEIS_FICHA_IMOVEL_RURAL);
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - INCLUIR GIA - ITCD SEPARAÇÃO/DIVÓRCIO/PARTILHA - BENS TRIBUTÁVEIS - FICHA IMÓVEL RURAL");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(prioridade++);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);
	   //ABA BENS TRIBUTÁVEIS - IMÓVEL URBANO
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_GIA_SEPARACAO_INCLUIR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade(TELA_FUNCIONALIDADE_ABA_BENS_TRIBUTAVEIS_FICHA_IMOVEL_URBANO);
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - INCLUIR GIA - ITCD SEPARAÇÃO/DIVÓRCIO/PARTILHA - BENS TRIBUTÁVEIS - FICHA IMÓVEL URBANO");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(prioridade++);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);
	   //ABA CONJUGE
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_CONJUGE_INCLUIR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_GIA_SEPARACAO_INCLUIR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR INCLUIR INFORMAÇÕES SOBRE O CÔNJUGE. ESTA FUNCIONALIDADE TEM O SEGUINTE FLUXO: \n" + 
	   "1- EFETUAR A OPERAÇÃO LOCALIZAR CONTRIBUINTE (CPF CÔNJUGE 1).\n" + 
	   "2- EFETUAR A OPERAÇÃO LOCALIZAR CONTRIBUINTE (CPF CÔNJUGE 2).");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - INCLUIR GIA - ITCD SEPARAÇÃO/DIVÓRCIO/PARTILHA - CÔNJUGE");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(prioridade++);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);
	   //ABA DEMONSTRATIVO DE CÁLCULO
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_DEMONSTRATIVO_CALCULO));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_GIA_SEPARACAO_INCLUIR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade(TELA_FUNCIONALIDADE_ABA_DEMONSTRATIVO_CALCULO);
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - INCLUIR GIA - ITCD SEPARAÇÃO/DIVÓRCIO/PARTILHA - DEMONSTRATIVO DE CÁLCULO");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(prioridade++);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);
	   
	   //ALTERAR
	   //ABA DADOS GERAIS
	   prioridade = 1;
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_DADOS_GERAIS_ALTERAR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_GIA_SEPARACAO_ALTERAR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR INCLUIR INFORMAÇÕES SOBRE OS DADOS GERAIS SEPARAÇÃO/DIVÓRCIO/PARTILHA. ESTA FUNCIONALIDADE TEM O SEGUINTE FLUXO: \n" + 
	   "01- LOCALIZAR CPF DECLARANTE.SERÁ APRESENTADA A FUNCIONALIDADE PESQUISAR CONTRIBUINTE POR, SELECIONE A OPÇÃO DE CONSULTA E CONFIRME A OPERAÇÃO.\n" + 
	   "02- SELECIONE REGIME DE CASAMENTO.\n" + 
	   "03- INFORME O NÚMERO PROCESSO.\n" + 
	   "04- INFORME A DATA.\n" + 
	   "05- SELECIONE A NATUREZA DA OPERAÇÃO.\n" + 
	   "06- LOCALIZAR CPF DO PROCURADOR.SERÁ APRESENTADA A FUNCIONALIDADE PESQUISAR CONTRIBUINTE POR, SELECIONE A OPÇÃO DE CONSULTA E CONFIRME A OPERAÇÃO.");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - ALTERAR GIA - ITCD SEPARAÇÃO/DIVÓRCIO/PARTILHA - DADOS GERAIS");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(prioridade++);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);
	   //ABA BENS TRIBUTÁVEIS
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_ALTERAR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_GIA_SEPARACAO_ALTERAR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade(TELA_FUNCIONALIDADE_ABA_BENS_TRIBUTAVEIS);
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - ALTERAR GIA - ITCD SEPARAÇÃO/DIVÓRCIO/PARTILHA - BENS TRIBUTÁVEIS");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(prioridade++);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);
	   //ABA BENS TRIBUTÁVEIS - IMÓVEL RURAL
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_GIA_SEPARACAO_ALTERAR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade(TELA_FUNCIONALIDADE_ABA_BENS_TRIBUTAVEIS_FICHA_IMOVEL_RURAL);
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - ALTERAR GIA - ITCD SEPARAÇÃO/DIVÓRCIO/PARTILHA - BENS TRIBUTÁVEIS - FICHA IMÓVEL RURAL");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(prioridade++);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);
	   //ABA BENS TRIBUTÁVEIS - IMÓVEL URBANO
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_GIA_SEPARACAO_ALTERAR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade(TELA_FUNCIONALIDADE_ABA_BENS_TRIBUTAVEIS_FICHA_IMOVEL_URBANO);
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - ALTERAR GIA - ITCD SEPARAÇÃO/DIVÓRCIO/PARTILHA - BENS TRIBUTÁVEIS - FICHA IMÓVEL URBANO");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(prioridade++);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);
	   //ABA CONJUGE
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_CONJUGE_ALTERAR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_GIA_SEPARACAO_ALTERAR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR ALTERAR O CÔNJUGE DA GIA-ITCD SEPARAÇÃO/DIVÓRCIO/PARTILHA .O \n" + 
	   "PROCEDIMENTO DE USO TEM O SEGUINTE FLUXO:\n" + 
	   "1- FAÇA A ALTERAÇÃO DESEJADA.\n" + 
	   "2- EXECUTE A OPERAÇÃO SALVAR.\n" + 
	   "OBS: É NECESSÁRIO INCLUIR BENS AOS CÔNJUGES.\n" + 
	   "OBS: VALOR ATRIBUÍDO AOS CÔNJUGUES DEVE SER IGUAL DE MERCADO DO BEM.");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - ALTERAR GIA - ITCD SEPARAÇÃO/DIVÓRCIO/PARTILHA - CONJUGE");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(prioridade++);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);
	   //ABA DEMONSTRATIVO DE CÁLCULO
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_DEMONSTRATIVO_CALCULO));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_GIA_SEPARACAO_ALTERAR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade(TELA_FUNCIONALIDADE_ABA_DEMONSTRATIVO_CALCULO);
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - ALTERAR GIA - ITCD SEPARAÇÃO/DIVÓRCIO/PARTILHA - DEMONSTRATIVO DE CÁLCULO");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(prioridade++);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);
		
		//VINCULAR DAR
		prioridade = 1;
		telaFuncionalidade = new TelaFuncionalidadeVo();
		telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_VINCULAR_DAR));
		telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_VINCULAR_DAR));
		telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR VINCULAR DAR A GIA-ITCD.O PROCEDIMENTO DE USO TEM O SEGUINTE FLUXO:\n" + 
		"1- INSIRA O NÚMERO DA GIA-ITCD.\n" + 
		"2- INSIRA O NÚMERO DO DAR.\n" + 
		"3- EXECUTE A OPERAÇÃO CONFIRMAR.");
		telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("VINCULAR DAR A GIA-ITCD");
		telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaFuncionalidade.setCodigoOrdenacao(prioridade++);
		telaFuncionalidades.getCollVO().add(telaFuncionalidade);
		 
	   //INATIVAR
		prioridade = 1;
		telaFuncionalidade = new TelaFuncionalidadeVo();
		telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_CONSULTAR_SERVIDOR));
		telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_GIA_ITCD_INATIVAR));
		telaFuncionalidade.setDescricaoTelaFuncionalidade(TELA_FUNCIONALIDADE_CONSULTAR_GIA_ITCD_GERAL_SERVIDOR);
		telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("FILTRO PESQUISA - INATIVAR GIA - ITCD");
		telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaFuncionalidade.setCodigoOrdenacao(prioridade++);
		telaFuncionalidades.getCollVO().add(telaFuncionalidade);          
		
		telaFuncionalidade = new TelaFuncionalidadeVo();
		telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_INATIVAR));
		telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_GIA_ITCD_INATIVAR));
		telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR APRESENTAR OS DADOS DA GIA-ITCD PARA INATIVAÇÃO.O PROCEDIMENTO DE USO TEM O SEGUINTE FLUXO:\n" + 
		"1- INSIRA O NÚMERO DO PROTOCOLO DE DESISTÊNCIA DA GIA-ITCD.\n" + 
		"2- INSIRA A DATA DE DESISTÊNCIA DA GIA-ITCD.\n" + 
		"3- CASO NECESSÁRIO INSIRA UMA OBSERVAÇÃO.\n" + 
		"4- EXECUTE A OPERAÇÃO INATIVAR.\n");
		telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("INATIVAR GIA - ITCD");
		telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaFuncionalidade.setCodigoOrdenacao(prioridade++);
		telaFuncionalidades.getCollVO().add(telaFuncionalidade);
		
		//REATIVAR		
	   prioridade = 1;
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_CONSULTAR_SERVIDOR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_GIA_ITCD_REATIVAR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade(TELA_FUNCIONALIDADE_CONSULTAR_GIA_ITCD_GERAL_SERVIDOR);
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("FILTRO PESQUISA - REATIVAR GIA - ITCD");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(prioridade++);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);          
	   
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_REATIVAR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_GIA_ITCD_REATIVAR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR APRESENTAR OS DADOS DA GIA-ITCD PARA REATIVAÇÃO .O PROCEDIMENTO DE \n" + 
	   "USO TEM O SEGUINTE FLUXO:\n" + 
	   "1- INSIRA A JUSTIFICATIVA DE REATIVAÇÃO.\n" + 
	   "2- EXECUTE A OPERAÇÃO REATIVAR.");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("REATIVAR GIA - ITCD");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(prioridade++);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);
		
		
		//AUTENTICIDADE
	   prioridade = 1;
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_CONSULTAR_AUTENTICIDADE));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_AUTENTICIDADE_CONSULTAR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR LOCARIZAR A AUTENTICIDADE PARA IMPRIMIR .O PROCEDIMENTO DE USO TEM \n" + 
	   "O SEGUINTE FLUXO:\n" + 
	   "1- SELECIONE UM TIPO PARA PESQUISA.\n" + 
	   "2- SE O TIPO FOR NÚMERO-GIA-ITCD INSIRA O NÚMERO DA GIA-ITCD.\n" + 
	   "3- SE O TIPO FOR DECLARAÇÃO DE ISENÇÃO PARA VALORES INSIRA O NÚMERO DA DECLARAÇÃO DE \n" + 
	   "ISENÇÃO DE VALORES.\n" + 
	   "4- SE O TIPO FOR DECLARAÇÃO DE NÃO OCERRENCIA DE FATOR GERADOR INSIRA O NÚMERO DA \n" + 
	   "DECLARAÇÃO DE NÃO OCERRENCIA DE FATOR GERADOR .\n" + 
	   "5- INSIRA O CÓDIGO DE AUTENTICIDADE.\n" + 
	   "6- EXECUTE A OPERAÇÃO PESQUISAR.");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("CONSULTAR AUTENTICIDADE");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(prioridade++);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);          
	   
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_CONSULTAR_AUTENTICIDADE_IMPRIMIR_AUTENTICIDADE));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_AUTENTICIDADE_IMPRIMIR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR APRESENTAR OS DADOS DA GIA-ITCD ENCONTRAR E DISPONIBILIZA BOTÃO PARA IMPRESSAO DA AUTENTICIDADE DA GIA-ITCD EM QUESTÃO.");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("IMPRIMIR AUTENTICIDADE");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(prioridade++);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);


		//CONSULTAR GIA - CONTRIBUINTE
	   prioridade = 1;      
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_CONSULTAR_LIVRE));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_GIA_ITCD_CONSULTAR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade(TELA_FUNCIONALIDADE_CONSULTAR_GIA_ITCD_GERAL_CONTRIBUINTE);
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("CONSULTAR GIA - ITCD - CONSULTAR GIA PELO CONTRIBUINTE");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(prioridade++);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);          
	   //CONSULTAR GIA - SERVIDOR
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_CONSULTAR_SERVIDOR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_GIA_ITCD_CONSULTAR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade(TELA_FUNCIONALIDADE_CONSULTAR_GIA_ITCD_GERAL_SERVIDOR);
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("CONSULTAR GIA - ITCD - CONSULTAR GIA PELO SERVIDOR");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(prioridade++);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);          


		//ALTERAR STATUS GIA-ITCD
		prioridade = 1;
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_CONSULTAR_SERVIDOR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_STATUS_ALTERAR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade(TELA_FUNCIONALIDADE_CONSULTAR_GIA_ITCD_GERAL_SERVIDOR);
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("ALTERAR STATUS GIA - ITCD - CONSULTAR GIA PELO SERVIDOR");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(prioridade++);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);          

	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_STATUS_ALTERAR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_STATUS_ALTERAR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR ALETRAR O STATUS DA GIA-ITCD .O PROCEDIMENTO DE USO TEM O SEGUINTE \n" + 
	   "FLUXO:\n" + 
	   "1- DEFINA O STATUS DA GIA-ITCD.\n" + 
	   "OBS: DEPENDENDO DO ESTADO ATUAL DA GIA-ITCD OS POSSÍVEIS ESTADOS PARA ALTERAÇÃO \n" + 
	   "APARECERAM NO CAMPO STATUS.\n" + 
	   "2- INSIRA OS CAMPOS PEPIDOS DE ACORDO COM O STATUS.\n" + 
	   "3- EXECUTE A OPERAÇÃO ALTERAR.");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("ALTERAR STATUS GIA - ITCD");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(prioridade++);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);          

		//REIMPRIMIR GIA-ITCD
		prioridade = 1;
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_CONSULTAR_REIMPRIMIR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_REIMPRIMIR_GIA_ITCD));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR LOCALIZAR UMA GIA-ITCD PARA REIMPRESSÃO.O PROCEDIMENTO DE USO TEM \n" + 
	   "O SEGUINTE FLUXO:\n" + 
	   "1- DEFINA O PROCESSO PARA REIMPRESSÃO.\n" + 
	   "2- INSIRA O NÚMERO DA GIA CASO NECESSÁRIO.\n" + 
	   "3- CASO A O PROCESSO PARA REIMPRESSÃO SEJA DECLARAÇÃO DE NÃO OCORRÊNCIA DE FATO GERADOR \n" + 
	   "OU DECLARAÇÃO DE ISENÇÃO PARA VALORES INSIRA O NÚMERO DA DECLARAÇÃO.\n" + 
	   "4- LOCALIZE O CONTRIBUINTE REFERENTE A GIA-ITCD PARA REIMPRESSÃO.\n" + 
	   "5- EXECUTE A OPERAÇÃO PESQUISAR.");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("FILTRO PESQUISA - REIMPRIMIR GIA - ITCD");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(prioridade++);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);          

	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_REIMPRIMIR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_REIMPRIMIR_GIA_ITCD));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR APRESENTAR OS DADOS DA GIA-ITCD PARA REIMPRESSÃO .O PROCEDIMENTO \n" + 
	   "DE USO TEM O SEGUINTE FLUXO:\n" + 
	   "1- EXECUTE A OPERAÇÃO REIMPRIMIR.");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("REIMPRIMIR GIA - ITCD");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(prioridade++);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);          

		//REIMPRESSAO RETIFICACAO / NOTIFICAÇÃO
		prioridade = 1;
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_CONSULTAR_NOTIFICACAO_RETIFICACAO));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_REIMPRIMIR_NOTIFICACAO_RETIFICACAO));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR CONSULTAR UMA GIA-ITCD PARA REIMPRESSÃO DA NOTIFICAÇÃO/RETIFICAÇÃO \n" + 
	   ".O PROCEDIMENTO DE USO TEM O SEGUINTE FLUXO:\n" + 
	   "1- LOCALIZE O CONTRIBUINTE.\n" + 
	   "2- DEFINA O TIPO DE DOCUMENTO.\n" + 
	   "3- INSIRA O NÚMERO DA GIA-ITCD\n" + 
	   "4- EXECUTE A OPERAÇÃO PESQUISAR.");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("FILTRO PESQUISA - REIMPRESSÃO DA NOTIFICAÇÃO/RETIFICAÇÃO");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(prioridade++);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);          

	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_REIMPRIMIR_NOTIFICACAO_RETIFICACAO));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_REIMPRIMIR_NOTIFICACAO_RETIFICACAO));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR APRESENTAR DADOS DA GIA-ITCD PARA REIMPRESSÃO.O PROCEDIMENTO DE \n" + 
	   "USO TEM O SEGUINTE FLUXO:\n" + 
	   "1- EXECUTE A OPERAÇÃO REIMPRIMIR PARA REIMPRIMIR A GIA-ITCD.");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("REIMPRESSÃO DA NOTIFICAÇÃO/RETIFICAÇÃO");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(prioridade++);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);          

		//AVALIAÇÃO		
		//INCLUIR
		prioridade = 1;
		telaFuncionalidade = new TelaFuncionalidadeVo();
		telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_CONSULTAR_SERVIDOR));
		telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_AVALIACAO_INCLUIR));
		telaFuncionalidade.setDescricaoTelaFuncionalidade(TELA_FUNCIONALIDADE_CONSULTAR_GIA_ITCD_GERAL_SERVIDOR);
		telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("FILTRO PESQUISA - INCLUIR AVALIAÇÃO");
		telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaFuncionalidade.setCodigoOrdenacao(prioridade++);
		telaFuncionalidades.getCollVO().add(telaFuncionalidade);          

	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_AVALIACAO_INCLUIR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_AVALIACAO_INCLUIR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR APRESENTAR DADOS DA GIA-ITCD NA QUAL SERÁ INCLUIDO UMA AVALIAÇÃO.O \n" + 
	   "PROCEDIMENTO DE USO TEM O SEGUINTE FLUXO:\n" + 
	   "1- DEFINA A UNIDADE SEFAZ DA AVALIAÇÃO.\n" + 
	   "2- PARA INCLUIR UMA AVALIAÇÃO CLIQUE NO LINK INCLUIR.\n" + 
	   "3- EXECUTE A OPERAÇÃO SALVAR.\n" + 
	   "OBS: TODOS OS BENS TEM QUE SER AVALIADOS.\n");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - INCLUIR AVALIAÇÃO - GIA-ITCD");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(prioridade++);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);          

	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_AVALIACAO_INCLUIR_AVALIACAO_BEM));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_AVALIACAO_INCLUIR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR INSERIR DADOS DA AVALIAÇÃO DO BEM.O PROCEDIMENTO DE USO TEM O \n" + 
	   "SEGUINTE FLUXO:\n" + 
	   "1- MARQUE O CAMPO AVALIAÇÃO JUDICIAL CASO NECESSÁRIO.\n" + 
	   "2- MARQUE O CAMPO ISENÇÃO PREVISTA EM LEI CASO NECESSÁRIO.\n" + 
	   "3- INSIRA O VALOR DA AVALIAÇÃO.\n" + 
	   "4- INSIRA A DATA DA AVALIAÇÃO.\n" + 
	   "5- LOCALIZE O SERVIDOR.\n" + 
	   "6- INSIRA UMA OBSERVAÇÃO.\n" + 
	   "7- EXECUTE A OPERAÇÃO CONFIRMAR.\n");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - INCLUIR AVALIAÇÃO - AVALIAÇÃO BEM TRIBUTÁVEL");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(prioridade++);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);          

	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_AVALIACAO_INCLUIR_AVALIACAO_BEM_SERVIDOR_INCLUIR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_AVALIACAO_INCLUIR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR LOCALIZAR UM SERVIDOR .O PROCEDIMENTO DE USO TEM O SEGUINTE FLUXO:\n" + 
	   "1- INSIRA A MATRICULA DO SERVIDOR.\n" + 
	   "2- EXECUTE A OPERAÇÃO PESQUISAR.");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - INCLUIR AVALIAÇÃO - INCLUIR AVALIADOR");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(prioridade++);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);          

		//ALTERAR
	   prioridade = 1;
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_CONSULTAR_SERVIDOR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_AVALIACAO_ALTERAR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade(TELA_FUNCIONALIDADE_CONSULTAR_GIA_ITCD_GERAL_SERVIDOR);
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("FILTRO PESQUISA - ALTERAR AVALIAÇÃO");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(prioridade++);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);          

	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_AVALIACAO_ALTERAR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_AVALIACAO_ALTERAR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR APRESENTAR DADOS DA GIA-ITCD NA QUAL SERÁ INCLUIDO UMA AVALIAÇÃO.O \n" + 
	   "PROCEDIMENTO DE USO TEM O SEGUINTE FLUXO:\n" + 
	   "1- DEFINA A UNIDADE SEFAZ DA AVALIAÇÃO.\n" + 
	   "2- PARA INCLUIR UMA AVALIAÇÃO CLIQUE NO LINK INCLUIR.\n" + 
	   "3- EXECUTE A OPERAÇÃO SALVAR.\n" + 
	   "OBS: TODOS OS BENS TEM QUE SER AVALIADOS.\n");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - ALTERAR AVALIAÇÃO - GIA-ITCD");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(prioridade++);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);          

	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_AVALIACAO_ALTERAR_AVALIACAO_BEM));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_AVALIACAO_ALTERAR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR INSERIR DADOS DA AVALIAÇÃO DO BEM.O PROCEDIMENTO DE USO TEM O \n" + 
	   "SEGUINTE FLUXO:\n" + 
	   "1- MARQUE O CAMPO AVALIAÇÃO JUDICIAL CASO NECESSÁRIO.\n" + 
	   "2- MARQUE O CAMPO ISENÇÃO PREVISTA EM LEI CASO NECESSÁRIO.\n" + 
	   "3- INSIRA O VALOR DA AVALIAÇÃO.\n" + 
	   "4- INSIRA A DATA DA AVALIAÇÃO.\n" + 
	   "5- LOCALIZE O SERVIDOR.\n" + 
	   "6- INSIRA UMA OBSERVAÇÃO.\n" + 
	   "7- EXECUTE A OPERAÇÃO CONFIRMAR.\n");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - ALTERAR AVALIAÇÃO - AVALIAÇÃO BEM TRIBUTÁVEL");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(prioridade++);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);          

	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_AVALIACAO_ALTERAR_AVALIACAO_BEM_SERVIDOR_INCLUIR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_AVALIACAO_ALTERAR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR LOCALIZAR UM SERVIDOR .O PROCEDIMENTO DE USO TEM O SEGUINTE FLUXO:\n" + 
	   "1- INSIRA A MATRICULA DO SERVIDOR.\n" + 
	   "2- EXECUTE A OPERAÇÃO PESQUISAR.");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("DADOS REGISTRO - ALTERAR AVALIAÇÃO - INCLUIR AVALIADOR");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(prioridade++);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);          

		//CONSULTAR
		prioridade = 1;
	   telaFuncionalidade = new TelaFuncionalidadeVo();
	   telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_AVALIACAO_CONSULTAR));
	   telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_AVALIACAO_CONSULTAR));
	   telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR FAZER UMA CONSULTA DE GIA-ITCD .O PROCEDIMENTO DE USO TEM O \n" + 
	   "SEGUINTE FLUXO:\n" + 
	   "1- LOCALIZE O CONTRIBUINTE.\n" + 
	   "2- EXECUTE A OPERAÇÃO PESQUISAR.\n" + 
	   "OBS: APARECERÁ UMA LISTA DE TODAS AS GIAS-ITCD REFERENTE AO CONTRIBUINTE.\n" + 
	   "OBS: PARA ESCOLHER A GIA-ITCD CLIQUE NO LINK REFERENTE A GIA-ITCD DESEJADA.");
	   telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("CONSULTAR AVALIAÇÃO");
	   telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaFuncionalidade.setCodigoOrdenacao(prioridade++);
	   telaFuncionalidades.getCollVO().add(telaFuncionalidade);     
		
		//IMPRIMIR		
		prioridade = 1;
		telaFuncionalidade = new TelaFuncionalidadeVo();
		telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_CONSULTAR_SERVIDOR));
		telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_AVALIACAO_IMPRIMIR));
		telaFuncionalidade.setDescricaoTelaFuncionalidade(TELA_FUNCIONALIDADE_CONSULTAR_GIA_ITCD_GERAL_SERVIDOR);
		telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("FILTRO PESQUISA - IMPRIMIR AVALIAÇÃO");
		telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaFuncionalidade.setCodigoOrdenacao(prioridade++);
		telaFuncionalidades.getCollVO().add(telaFuncionalidade);          
		
		telaFuncionalidade = new TelaFuncionalidadeVo();
		telaFuncionalidade.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_AVALIACAO_IMPRIMIR));
		telaFuncionalidade.setFuncionalidadeVo(new FuncionalidadeVo(CODIGO_FUNCIONALIDADE_AVALIACAO_IMPRIMIR));
		telaFuncionalidade.setDescricaoTelaFuncionalidade("TELA RESPONSÁVEL POR MOSTRAR OS DADOS DA AVALIAÇÃO DA GIA-ITCD PARA IMPRESSÃO.O \n" + 
		"PROCEDIMENTO DE USO TEM O SEGUINTE FLUXO:\n" + 
		"1- EXECUTE A OPERAÇÃO IMPRIMIR PARA IMPRIMIR A AVALIAÇÃO.\n");
		telaFuncionalidade.setInformacaoTituloTelaFuncionalidade("IMPRIMIR AVALIAÇÃO");
		telaFuncionalidade.setStatusTelaFuncionalidade(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaFuncionalidade.setCodigoOrdenacao(prioridade++);
		telaFuncionalidades.getCollVO().add(telaFuncionalidade);     
		
		solicitarCadastrarTelaFuncionalidade(telaFuncionalidades);
	}

	private void solicitarCadastrarTelaFuncionalidade(TelaFuncionalidadeVo telaFuncionalidades) throws SQLException
	{
	   if(telaFuncionalidades.getCollVO() != null && telaFuncionalidades.getCollVO().size() > 0)
	   {
	      CargaInicialDao dao = new CargaInicialDao(conn);
	      for(Iterator it = telaFuncionalidades.getCollVO().iterator(); it.hasNext(); )
	      {
	         TelaFuncionalidadeVo telaFuncionalidadeAtual = (TelaFuncionalidadeVo) it.next();
	         dao.inserirTelaFuncionalidade(telaFuncionalidadeAtual);
	      }
	   }  	
	}

	private void gerarCampos() throws SQLException
	{
		CampoAjudaVo campoAjudaVo = new CampoAjudaVo();
		CampoAjudaVo campos = new CampoAjudaVo();
		
		//TABELAS BASICAS
		
		//BEM
		campoAjudaVo = new CampoAjudaVo();
		campoAjudaVo.setCodigo(CODIGO_CAMPO_TIPO_DE_BEM);
		campoAjudaVo.setDescricaoRotulo(CAMPO_TIPO_DE_BEM);
		campos.getCollVO().add(campoAjudaVo);
		 
	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_DESCRICAO);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_DESCRICAO);
	   campos.getCollVO().add(campoAjudaVo);
		 
	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_POSSUI_CONSTRUCAO_EDIFICACAO);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_POSSUI_CONSTRUCAO_EDIFICACAO);
	   campos.getCollVO().add(campoAjudaVo);
		
	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_STATUS);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_STATUS);
	   campos.getCollVO().add(campoAjudaVo);
		
		//CULTURA
	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_TIPO_PESQUISA);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_TIPO_PESQUISA);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_CODIGO);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_CODIGO);
	   campos.getCollVO().add(campoAjudaVo);
		
		//REBANHO

		//NATUREZA DA OPERACAO
		campoAjudaVo = new CampoAjudaVo();
		campoAjudaVo.setCodigo(CODIGO_CAMPO_TIPO_DE_GIA);
		campoAjudaVo.setDescricaoRotulo(CAMPO_TIPO_DE_GIA);
		campos.getCollVO().add(campoAjudaVo);
		
		campoAjudaVo = new CampoAjudaVo();
		campoAjudaVo.setCodigo(CODIGO_CAMPO_BASE_CALCULO_REDUZIDO);
		campoAjudaVo.setDescricaoRotulo(CAMPO_BASE_CALCULO_REDUZIDO);
		campos.getCollVO().add(campoAjudaVo);
		
		//CONSTRUÇÃO
		campoAjudaVo = new CampoAjudaVo();
		campoAjudaVo.setCodigo(CODIGO_CAMPO_UTILIZACAO_DA_COSNTRUCAO);
		campoAjudaVo.setDescricaoRotulo(CAMPO_UTILIZACAO_DA_COSNTRUCAO);
		campos.getCollVO().add(campoAjudaVo);
		
		//MULTA DE MORA
		campoAjudaVo = new CampoAjudaVo();
		campoAjudaVo.setCodigo(CODIGO_CAMPO_PERCENTUAL_MULTA_MORA);
		campoAjudaVo.setDescricaoRotulo(CAMPO_PERCENTUAL_MULTA_MORA);
		campos.getCollVO().add(campoAjudaVo);
		
		campoAjudaVo = new CampoAjudaVo();
		campoAjudaVo.setCodigo(CODIGO_CAMPO_PERCENTUAL_MAXIMO_MULTA_MORA);
		campoAjudaVo.setDescricaoRotulo(CAMPO_PERCENTUAL_MAXIMO_MULTA_MORA);
		campos.getCollVO().add(campoAjudaVo);
		
		//PARAMETROS DA LEGISLACAO
		campoAjudaVo = new CampoAjudaVo();
		campoAjudaVo.setCodigo(CODIGO_CAMPO_NUMERO_ANO_LEGISLACAO);
		campoAjudaVo.setDescricaoRotulo(CAMPO_NUMERO_ANO_LEGISLACAO);
		campos.getCollVO().add(campoAjudaVo);
		
	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_DATA_VIGENCIA_INICIAL);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_DATA_VIGENCIA_INICIAL);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_DATA_VIGENCIA_FINAL);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_DATA_VIGENCIA_FINAL);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_NUMERO_ANO_LEGISLACAO_PRINCIPAL);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_NUMERO_ANO_LEGISLACAO_PRINCIPAL);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_CALCULO_CASCATA);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_CALCULO_CASCATA);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_TIPO_FATO_GERADOR);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_TIPO_FATO_GERADOR);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_ISENCAO);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_ISENCAO);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_PERCENTUAL);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_PERCENTUAL);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_QUANTIDADE_UPF_INICIAL);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_QUANTIDADE_UPF_INICIAL);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_QUANTIDADE_UPF_FINAL);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_QUANTIDADE_UPF_FINAL);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_QUANTIDADE_DIAS_INICIAL);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_QUANTIDADE_DIAS_INICIAL);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_QUANTIDADE_DIAS_FINAL);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_QUANTIDADE_DIAS_FINAL);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_PERCENTUAL_MULTA);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_PERCENTUAL_MULTA);
	   campos.getCollVO().add(campoAjudaVo);
		
		//AJUDA
		campoAjudaVo = new CampoAjudaVo();
		campoAjudaVo.setCodigo(CODIGO_CAMPO_MODULO);
		campoAjudaVo.setDescricaoRotulo(CAMPO_MODULO);
		campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_SUBMODULO);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_SUBMODULO);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_FUNCIONALIDADE);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_FUNCIONALIDADE);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_TELA);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_TELA);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_TITULO_TELA);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_TITULO_TELA);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_DESCRICAO_AJUDA_TELA);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_DESCRICAO_AJUDA_TELA);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_HABILITAR);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_HABILITAR);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_SELECIONAR_ROTULO);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_SELECIONAR_ROTULO);
	   campos.getCollVO().add(campoAjudaVo);
		
	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_DESCRICAO_AJUDA_ROTULO);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_DESCRICAO_AJUDA_ROTULO);
	   campos.getCollVO().add(campoAjudaVo);

		//GIA ITCD
		//INVENTARIO ARROLAMENTO
		//ABA DADOS GERAIS
		campoAjudaVo = new CampoAjudaVo();
		campoAjudaVo.setCodigo(CODIGO_CAMPO_DADOS_INVENTARIANTE);
		campoAjudaVo.setDescricaoRotulo(CAMPO_DADOS_INVENTARIANTE);
		campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_DE_CUJUS_POSSUI_CPF);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_DE_CUJUS_POSSUI_CPF);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_DADOS_DO_DE_CUJUS);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_DADOS_DO_DE_CUJUS);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_ESTADO_CIVIL);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_ESTADO_CIVIL);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_REGIME_CASAMENTO);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_REGIME_CASAMENTO);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_DATA_FALECIMENTO);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_DATA_FALECIMENTO);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_DATA_INVENTARIO_ARROLAMENTO);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_DATA_INVENTARIO_ARROLAMENTO);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_UF_ABERTURA_INVENTARIO);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_UF_ABERTURA_INVENTARIO);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_TEM_RENUNCIA);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_TEM_RENUNCIA);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_TIPO_RENUNCIA);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_TIPO_RENUNCIA);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_TEM_HERDEIROS_DESCENDENTES);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_TEM_HERDEIROS_DESCENDENTES);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_QUANTIDADE_HERDEIROS_DESCENDENTES);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_QUANTIDADE_HERDEIROS_DESCENDENTES);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_TEM_HERDEIROS_ASCENDENTES);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_TEM_HERDEIROS_ASCENDENTES);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_QUANTIDADE_HERDEIROS_ASCENDENTES);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_QUANTIDADE_HERDEIROS_ASCENDENTES);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_QUANTIDADE_OUTROS_HERDEIROS);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_QUANTIDADE_OUTROS_HERDEIROS);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_DADOS_MEEIRO);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_DADOS_MEEIRO);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_DADOS_PROCURADOR);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_DADOS_PROCURADOR);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_NATUREZA_OPERACAO);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_NATUREZA_OPERACAO);
	   campos.getCollVO().add(campoAjudaVo);

		//ABA BEM TRIBUTÁVEL
		campoAjudaVo = new CampoAjudaVo();
		campoAjudaVo.setCodigo(CODIGO_CAMPO_CLASSIFICACAO_BEM);
		campoAjudaVo.setDescricaoRotulo(CAMPO_CLASSIFICACAO_BEM);
		campos.getCollVO().add(campoAjudaVo);

		campoAjudaVo = new CampoAjudaVo();
		campoAjudaVo.setCodigo(CODIGO_CAMPO_BEM_PARTICULAR);
		campoAjudaVo.setDescricaoRotulo(CAMPO_BEM_PARTICULAR);
		campos.getCollVO().add(campoAjudaVo);

		campoAjudaVo = new CampoAjudaVo();
		campoAjudaVo.setCodigo(CODIGO_CAMPO_TEM_ISENCAO);
		campoAjudaVo.setDescricaoRotulo(CAMPO_TEM_ISENCAO);
		campos.getCollVO().add(campoAjudaVo);

		//ABA BEM TRIBUTÁVEL - IMÓVEL URBANO
		campoAjudaVo = new CampoAjudaVo();
		campoAjudaVo.setCodigo(CODIGO_CAMPO_TIPO_LOGRADOURO);
		campoAjudaVo.setDescricaoRotulo(CAMPO_TIPO_LOGRADOURO);
		campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_LOGRADOURO);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_LOGRADOURO);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_NUMERO);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_NUMERO);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_COMPLEMENTO);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_COMPLEMENTO);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_PONTO_REFERENCIA);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_PONTO_REFERENCIA);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_BAIRRO);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_BAIRRO);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_CEP);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_CEP);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_ESTADO_CONSERVACAO);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_ESTADO_CONSERVACAO);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_AREA_TOTAL_IMOVEL);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_AREA_TOTAL_IMOVEL);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_AREA_CONSTRUIDA);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_AREA_CONSTRUIDA);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_TIPO_CONSTRUCAO);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_TIPO_CONSTRUCAO);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_TIPO_ACESSO);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_TIPO_ACESSO);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_BENFEITORIA);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_BENFEITORIA);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_DESCRICAO_BENFEITORIA);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_DESCRICAO_BENFEITORIA);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_VALO_MERCADO);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_VALO_MERCADO);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_VALOR_VENAL);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_VALOR_VENAL);
	   campos.getCollVO().add(campoAjudaVo);
		
		//BEM TRIBUTÁVEL - IMÓVEL RURAL
		campoAjudaVo = new CampoAjudaVo();
		campoAjudaVo.setCodigo(CODIGO_CAMPO_DENOMINCAO_IMOVEL);
		campoAjudaVo.setDescricaoRotulo(CAMPO_DENOMINCAO_IMOVEL);
		campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_ENDERECO_IMOVEL);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_ENDERECO_IMOVEL);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_DISTANCIA_PERIMETRO_URBANO);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_DISTANCIA_PERIMETRO_URBANO);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMP0_AREA_TOTAL_IMOVEL_RURAL);
	   campoAjudaVo.setDescricaoRotulo(CAMP0_AREA_TOTAL_IMOVEL_RURAL);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_NUMERO_INDEA_MT);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_NUMERO_INDEA_MT);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_CODIGO_RECEITA_FEDERAL);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_CODIGO_RECEITA_FEDERAL);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_SELECIONE_CULTURA);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_SELECIONE_CULTURA);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_DESCRICAO_CULTURA);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_DESCRICAO_CULTURA);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_AREA_CULTIVADA);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_AREA_CULTIVADA);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_VALOR_HECTARE);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_VALOR_HECTARE);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_POSSUI_PASTAGENS_NATURAIS_CULTIVADAS);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_POSSUI_PASTAGENS_NATURAIS_CULTIVADAS);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_TAMANHO_PASTAGENS);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_TAMANHO_PASTAGENS);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_VALOR_PASTAGENS);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_VALOR_PASTAGENS);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_POSSUI_JAZIDAS_MINERAIS_FONTES_AGUA_RADIOATIVA_TERMICA_MINERAIS);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_POSSUI_JAZIDAS_MINERAIS_FONTES_AGUA_RADIOATIVA_TERMICA_MINERAIS);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_VALOR_ACESSOES_NATURAIS);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_VALOR_ACESSOES_NATURAIS);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_TIPO_REBANHO);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_TIPO_REBANHO);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_DESCRICAO_REBANHO);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_DESCRICAO_REBANHO);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_QUANTIDADE);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_QUANTIDADE);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_VALOR_CABECA);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_VALOR_CABECA);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_SELECIONE_CONSTRUCAO);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_SELECIONE_CONSTRUCAO);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_DESCRICAO_CONSTRUCAO);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_DESCRICAO_CONSTRUCAO);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_VALOR_TERRA_NUA);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_VALOR_TERRA_NUA);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_VALOR_MERCADO_MAQUINAS_AGRICOLAS);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_VALOR_MERCADO_MAQUINAS_AGRICOLAS);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_OUTROS_VALORES_IMOVEL_RURAL);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_OUTROS_VALORES_IMOVEL_RURAL);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_VALOR_VENAL_ITR);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_VALOR_VENAL_ITR);
	   campos.getCollVO().add(campoAjudaVo);
		
		//ABA BENEFICIARIOS
		campoAjudaVo = new CampoAjudaVo();
		campoAjudaVo.setCodigo(CODIGO_CAMPO_DADOS_BENEFICIARIO);
		campoAjudaVo.setDescricaoRotulo(CAMPO_DADOS_BENEFICIARIO);
		campos.getCollVO().add(campoAjudaVo);
		
		//GIA-ITCD DOAÇÃO
		//INCLUIR
		//ABA DADOS GERAIS
		campoAjudaVo = new CampoAjudaVo();
		campoAjudaVo.setCodigo(CODIGO_CAMPO_DADOS_DECLARANTE);
		campoAjudaVo.setDescricaoRotulo(CAMPO_DADOS_DECLARANTE);
		campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_PERCENTUAL_DOACAO_TRANSMISSAO);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_PERCENTUAL_DOACAO_TRANSMISSAO);
	   campos.getCollVO().add(campoAjudaVo);
		
		//ABA BENS TRIBUTAVEIS
		campoAjudaVo = new CampoAjudaVo();
		campoAjudaVo.setCodigo(CODIGO_CAMPO_VALOR_MERCADO_VALOR_TRIBUTAVEL);
		campoAjudaVo.setDescricaoRotulo(CAMPO_VALOR_MERCADO_VALOR_TRIBUTAVEL);
		campos.getCollVO().add(campoAjudaVo);
		
		//BENEFICIARIOS		
		campoAjudaVo = new CampoAjudaVo();
		campoAjudaVo.setCodigo(CODIGO_CAMPO_INFORME_PERCENTUAL_ATRIBUIDO_BENEFICIARIO);
		campoAjudaVo.setDescricaoRotulo(CAMPO_INFORME_PERCENTUAL_ATRIBUIDO_BENEFICIARIO);
		campos.getCollVO().add(campoAjudaVo);
		
		//GIA-ITCD SEPARACAO DIVORCIO
		//INCLUIR
		//ABA DADOS GERAIS
		campoAjudaVo = new CampoAjudaVo();
		campoAjudaVo.setCodigo(CODIGO_CAMPO_DATA_CASAMENTO);
		campoAjudaVo.setDescricaoRotulo(CAMPO_DATA_CASAMENTO);
		campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_NUMERO_PROCESSO);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_NUMERO_PROCESSO);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_DATA_SEPARACAO);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_DATA_SEPARACAO);
	   campos.getCollVO().add(campoAjudaVo);

		//ABA BENEFICIARIO
		campoAjudaVo = new CampoAjudaVo();
		campoAjudaVo.setCodigo(CODIGO_CAMPO_DADOS_CONJUGE2);
		campoAjudaVo.setDescricaoRotulo(CAMPO_DADOS_CONJUGE2);
		campos.getCollVO().add(campoAjudaVo);
		
		//GIA-ITCD CONSULTAR
		 campoAjudaVo = new CampoAjudaVo();
		 campoAjudaVo.setCodigo(CODIGO_CAMPO_TIPO_CONSULTA);
		 campoAjudaVo.setDescricaoRotulo(CAMPO_TIPO_CONSULTA);
		 campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_DADOS_CONTRIBUINTE);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_DADOS_CONTRIBUINTE);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_NUMERO_GIA);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_NUMERO_GIA);
	   campos.getCollVO().add(campoAjudaVo);
		
	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_SENHA);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_SENHA);
	   campos.getCollVO().add(campoAjudaVo);
		
		//GIA-ITCD REATIVAR
		campoAjudaVo = new CampoAjudaVo();
		campoAjudaVo.setCodigo(CODIGO_CAMPO_JUSTIFICATIVA_REATIVACAO);
		campoAjudaVo.setDescricaoRotulo(CAMPO_JUSTIFICATIVA_REATIVACAO);
		campos.getCollVO().add(campoAjudaVo);

		//GIA-ITCD INATIVAR
		campoAjudaVo = new CampoAjudaVo();
		campoAjudaVo.setCodigo(CODIGO_CAMPO_NUMERO_PROTOCOLO_INATIVACAO);
		campoAjudaVo.setDescricaoRotulo(CAMPO_NUMERO_PROTOCOLO_INATIVACAO);
		campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_DATA_INATIVACAO);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_DATA_INATIVACAO);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_OBSERVACAO);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_OBSERVACAO);
	   campos.getCollVO().add(campoAjudaVo);
		
		//GIA-ITCD CONSULTAR AUTENTICIDADE
		campoAjudaVo = new CampoAjudaVo();
		campoAjudaVo.setCodigo(CODIGO_CAMPO_SELECIONE);
		campoAjudaVo.setDescricaoRotulo(CAMPO_SELECIONE);
		campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_NUMERO_GIA_ITCD);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_NUMERO_GIA_ITCD);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_NUMERO_DECLARACAO_ISENCAO_VALORES);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_NUMERO_DECLARACAO_ISENCAO_VALORES);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_NUMERO_DECLARACAO_NAO_OCORRENCIA_FATO_GERADOR);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_NUMERO_DECLARACAO_NAO_OCORRENCIA_FATO_GERADOR);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_CODIGO_AUTENTICIDADE);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_CODIGO_AUTENTICIDADE);
	   campos.getCollVO().add(campoAjudaVo);

		//VINCULAR DAR
		campoAjudaVo = new CampoAjudaVo();
		campoAjudaVo.setCodigo(CODIGO_CAMPO_NUMERO_DAR);
		campoAjudaVo.setDescricaoRotulo(CAMPO_NUMERO_DAR);
		campos.getCollVO().add(campoAjudaVo);
		
		//REIMPRIMIR GIA-ITCD
		campoAjudaVo = new CampoAjudaVo();
		campoAjudaVo.setCodigo(CODIGO_CAMPO_PROCESSO_REIMPRESSAO);
		campoAjudaVo.setDescricaoRotulo(CAMPO_PROCESSO_REIMPRESSAO);
		campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_NUMERO_GIA_REIMPRIMIR);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_NUMERO_GIA_REIMPRIMIR);
	   campos.getCollVO().add(campoAjudaVo);
		
		//REIMPRIMIR NOTIFICACAO / RETIFICACAO
	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_TIPO_DOCUMENTO);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_TIPO_DOCUMENTO);
	   campos.getCollVO().add(campoAjudaVo);
		
		//ALTERAR STATUS MANUAL
		campoAjudaVo = new CampoAjudaVo();
		campoAjudaVo.setCodigo(CODIGO_CAMPO_NUMERO_PROTOCOLO);
		campoAjudaVo.setDescricaoRotulo(CAMPO_NUMERO_PROTOCOLO);
		campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_DATA_PROTOCOLO);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_DATA_PROTOCOLO);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_AGENFA_PROTOCOLO);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_AGENFA_PROTOCOLO);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_NUMERO_PROTOCOLO_PARCELAMENTO);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_NUMERO_PROTOCOLO_PARCELAMENTO);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_QUANTIDADE_PARCELAS);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_QUANTIDADE_PARCELAS);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_DATA_PARCELAMENTO);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_DATA_PARCELAMENTO);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_NUMERO_PROTOCOLO_IMPUGNACAO);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_NUMERO_PROTOCOLO_IMPUGNACAO);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_DATA_IMPUGNACAO);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_DATA_IMPUGNACAO);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_DATA_EMISSAO_DAR_SEGUNDA_RETIFICACAO);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_DATA_EMISSAO_DAR_SEGUNDA_RETIFICACAO);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_NUMERO_DAR_SEGUNDA_RETIFICACAO);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_NUMERO_DAR_SEGUNDA_RETIFICACAO);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_DATA_CIENCIA_NOTIFICACAO);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_DATA_CIENCIA_NOTIFICACAO);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_DATA_ENVIO_DIVIDA_ATIVA);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_DATA_ENVIO_DIVIDA_ATIVA);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_DATA_CIENCIA_RETIFICACAO);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_DATA_CIENCIA_RETIFICACAO);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_DATA_PERMISSAO);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_DATA_PERMISSAO);
	   campos.getCollVO().add(campoAjudaVo);
		
	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_SELECIONE_STATUS);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_SELECIONE_STATUS);
	   campos.getCollVO().add(campoAjudaVo);

		//AVALIAÇÃO
		campoAjudaVo = new CampoAjudaVo();
		campoAjudaVo.setCodigo(CODIGO_CAMPO_AVALIACAO_JUDICIAL);
		campoAjudaVo.setDescricaoRotulo(CAMPO_AVALIACAO_JUDICIAL);
		campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_ISENCAO_PREVISTA_EM_LEI);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_ISENCAO_PREVISTA_EM_LEI);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_VALOR_AVALIACAO_JUDICIAL);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_VALOR_AVALIACAO_JUDICIAL);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_DATA_AVALIACAO_JUDICIAL);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_DATA_AVALIACAO_JUDICIAL);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_VALOR_AVALIACAO);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_VALOR_AVALIACAO);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_DATA_AVALIACAO);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_DATA_AVALIACAO);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_DATA_CADASTRO_AVALIACAO);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_DATA_CADASTRO_AVALIACAO);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_DATA_ALTERACAO_AVALIACAO);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_DATA_ALTERACAO_AVALIACAO);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_MATRICULA_AVALIADOR);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_MATRICULA_AVALIADOR);
	   campos.getCollVO().add(campoAjudaVo);
		
		//AVALIACAO CONSULTAR
		campoAjudaVo = new CampoAjudaVo();
		campoAjudaVo.setCodigo(CODIGO_CAMPO_AGENFA);
		campoAjudaVo.setDescricaoRotulo(CAMPO_AGENFA);
		campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_GERENCIA_EXECUCAO);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_GERENCIA_EXECUCAO);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_DATA_INICIAL);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_DATA_INICIAL);
	   campos.getCollVO().add(campoAjudaVo);

	   campoAjudaVo = new CampoAjudaVo();
	   campoAjudaVo.setCodigo(CODIGO_CAMPO_DATA_FINAL);
	   campoAjudaVo.setDescricaoRotulo(CAMPO_DATA_FINAL);
	   campos.getCollVO().add(campoAjudaVo);
		
		
	
		solicitarCadastrarCampos(campos);
	}

	private void solicitarCadastrarCampos(CampoAjudaVo campoAjuda) throws SQLException
	{
		if(campoAjuda.getCollVO() != null && campoAjuda.getCollVO().size() > 0)
		{
			CargaInicialDao dao = new CargaInicialDao(conn);
			for(Iterator it = campoAjuda.getCollVO().iterator(); it.hasNext(); )
			{
				CampoAjudaVo campoAtual = (CampoAjudaVo) it.next();
				dao.inserirCampo(campoAtual);
			}
		}     
	}

	private void gerarRelacionamentoTelaCampo() throws SQLException
	{
		TelaCampoAjudaVo telaCampo = new TelaCampoAjudaVo();
		TelaCampoAjudaVo telaCampos = new TelaCampoAjudaVo();
		
		int prioridade = 1;
		//TABEALS BASICAS
		//BEM			
		//INCLUIR
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_BEM_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TIPO_DE_BEM));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_BEM_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_BEM_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_POSSUI_CONSTRUCAO_EDIFICACAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
		
		//ALTERAR
		prioridade = 1;
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_BEM_ALTERAR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TIPO_DE_BEM));
	   telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_BEM_ALTERAR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO));
	   telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_BEM_ALTERAR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_POSSUI_CONSTRUCAO_EDIFICACAO));
	   telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
		
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_BEM_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_STATUS));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

		//CONSULTAR
		prioridade = 1;
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_BEM_CONSULTAR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TIPO_DE_BEM));
	   telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_BEM_CONSULTAR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO));
	   telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
		
	   //CULTURA       
	   //INCLUIR
		prioridade = 1;
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_CULTURA_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
   
	   //ALTERAR
	   prioridade = 1;
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_CULTURA_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_CULTURA_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_STATUS));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   //CONSULTAR
	   prioridade = 1;
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_CULTURA_CONSULTAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TIPO_PESQUISA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_CULTURA_CONSULTAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_CODIGO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
		
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_CULTURA_CONSULTAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
		
	   //REBANHO       
	   //INCLUIR
	   prioridade = 1;
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_REBANHO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   //ALTERAR
	   prioridade = 1;
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_REBANHO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_REBANHO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_STATUS));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   //CONSULTAR
	   prioridade = 1;
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_REBANHO_CONSULTAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TIPO_PESQUISA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_REBANHO_CONSULTAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_CODIGO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_REBANHO_CONSULTAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   //NATUREZA DA OPERAÇÃO       
	   //INCLUIR
	   prioridade = 1;
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_NATUREZA_OPERACAO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TIPO_DE_GIA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_NATUREZA_OPERACAO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_NATUREZA_OPERACAO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_BASE_CALCULO_REDUZIDO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   //ALTERAR
	   prioridade = 1;
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_NATUREZA_OPERACAO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_NATUREZA_OPERACAO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_BASE_CALCULO_REDUZIDO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_NATUREZA_OPERACAO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_STATUS));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   //BENFEITORIA       
	   //INCLUIR
	   prioridade = 1;
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_BENFEITORIA_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   //ALTERAR
	   prioridade = 1;
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_BENFEITORIA_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_BENFEITORIA_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_STATUS));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   //CONSTRUÇÃO       
	   //INCLUIR
	   prioridade = 1;
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_CONSTRUCAO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
		telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_CONSTRUCAO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_UTILIZACAO_DA_COSNTRUCAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   	   
	   //ALTERAR
	   prioridade = 1;
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_CONSTRUCAO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_CONSTRUCAO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_UTILIZACAO_DA_COSNTRUCAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_CONSTRUCAO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_STATUS));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
		
		//MULTA DE MORA INCLUIR
		prioridade = 1;
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_MULTA_MORA_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_PERCENTUAL_MULTA_MORA));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_MULTA_MORA_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_PERCENTUAL_MAXIMO_MULTA_MORA));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		//PARAMETROS DA LEGISLACAO
		//INCLUIR
		prioridade = 1;
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_PARAMETRO_LEGISLACAO_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_NUMERO_ANO_LEGISLACAO));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_PARAMETRO_LEGISLACAO_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DATA_VIGENCIA_INICIAL));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_PARAMETRO_LEGISLACAO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DATA_VIGENCIA_FINAL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_PARAMETRO_LEGISLACAO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_NUMERO_ANO_LEGISLACAO_PRINCIPAL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_PARAMETRO_LEGISLACAO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_CALCULO_CASCATA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_PARAMETRO_LEGISLACAO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TIPO_FATO_GERADOR));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_PARAMETRO_LEGISLACAO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_ISENCAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_PARAMETRO_LEGISLACAO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_PERCENTUAL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_PARAMETRO_LEGISLACAO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_QUANTIDADE_UPF_INICIAL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_PARAMETRO_LEGISLACAO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_QUANTIDADE_UPF_FINAL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_PARAMETRO_LEGISLACAO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_QUANTIDADE_DIAS_INICIAL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_PARAMETRO_LEGISLACAO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_QUANTIDADE_DIAS_FINAL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_PARAMETRO_LEGISLACAO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_PERCENTUAL_MULTA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

		//ALTERAR
		prioridade = 1;
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_PARAMETRO_LEGISLACAO_ALTERAR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_NUMERO_ANO_LEGISLACAO));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_PARAMETRO_LEGISLACAO_ALTERAR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DATA_VIGENCIA_INICIAL));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_PARAMETRO_LEGISLACAO_ALTERAR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DATA_VIGENCIA_FINAL));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_PARAMETRO_LEGISLACAO_ALTERAR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_NUMERO_ANO_LEGISLACAO_PRINCIPAL));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_PARAMETRO_LEGISLACAO_ALTERAR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_CALCULO_CASCATA));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_PARAMETRO_LEGISLACAO_ALTERAR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TIPO_FATO_GERADOR));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_PARAMETRO_LEGISLACAO_ALTERAR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_ISENCAO));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_PARAMETRO_LEGISLACAO_ALTERAR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_PERCENTUAL));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_PARAMETRO_LEGISLACAO_ALTERAR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_QUANTIDADE_UPF_INICIAL));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_PARAMETRO_LEGISLACAO_ALTERAR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_QUANTIDADE_UPF_FINAL));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_PARAMETRO_LEGISLACAO_ALTERAR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_QUANTIDADE_DIAS_INICIAL));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_PARAMETRO_LEGISLACAO_ALTERAR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_QUANTIDADE_DIAS_FINAL));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_PARAMETRO_LEGISLACAO_ALTERAR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_PERCENTUAL_MULTA));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);

		//AJUDA
		//INCLUIR
		prioridade = 1;
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_AJUDA_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_MODULO));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_AJUDA_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_SUBMODULO));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_AJUDA_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_FUNCIONALIDADE));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_AJUDA_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TELA));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_AJUDA_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TITULO_TELA));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_AJUDA_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO_AJUDA_TELA));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_AJUDA_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_HABILITAR));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_AJUDA_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_SELECIONAR_ROTULO));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_AJUDA_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO_AJUDA_ROTULO));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);

	   //ALTERAR
	   prioridade = 1;
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_AJUDA_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_MODULO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_AJUDA_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_SUBMODULO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_AJUDA_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_FUNCIONALIDADE));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_AJUDA_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TELA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_AJUDA_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TITULO_TELA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_AJUDA_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO_AJUDA_TELA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_AJUDA_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_HABILITAR));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_AJUDA_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_SELECIONAR_ROTULO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_AJUDA_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO_AJUDA_ROTULO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   //ALTERAR
	   prioridade = 1;
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_AJUDA_CONSULTAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_MODULO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_AJUDA_CONSULTAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_SUBMODULO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_AJUDA_CONSULTAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_FUNCIONALIDADE));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_AJUDA_CONSULTAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TELA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
		
		//GIA-ITCD
		//INVENTARIO ARROLAMENTO
		//ABA DADOS GERAIS		
		prioridade = 1;
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_DADOS_GERAIS_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DADOS_INVENTARIANTE));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_DADOS_GERAIS_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DE_CUJUS_POSSUI_CPF));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_DADOS_GERAIS_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DADOS_DO_DE_CUJUS));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_DADOS_GERAIS_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_ESTADO_CIVIL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_DADOS_GERAIS_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_REGIME_CASAMENTO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_DADOS_GERAIS_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DATA_FALECIMENTO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_DADOS_GERAIS_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DATA_INVENTARIO_ARROLAMENTO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_DADOS_GERAIS_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_UF_ABERTURA_INVENTARIO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_DADOS_GERAIS_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TEM_RENUNCIA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_DADOS_GERAIS_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TIPO_RENUNCIA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_DADOS_GERAIS_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TEM_HERDEIROS_DESCENDENTES));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_DADOS_GERAIS_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_QUANTIDADE_HERDEIROS_DESCENDENTES));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_DADOS_GERAIS_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TEM_HERDEIROS_ASCENDENTES));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_DADOS_GERAIS_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_QUANTIDADE_HERDEIROS_ASCENDENTES));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_DADOS_GERAIS_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_QUANTIDADE_OUTROS_HERDEIROS));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_DADOS_GERAIS_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DADOS_MEEIRO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_DADOS_GERAIS_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DADOS_PROCURADOR));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_DADOS_GERAIS_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_NATUREZA_OPERACAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

		//ABA BEM TRIBUTÁVEL
		prioridade = 1;
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_CLASSIFICACAO_BEM));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TIPO_DE_BEM));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_MERCADO_VALOR_TRIBUTAVEL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_BEM_PARTICULAR));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);

	   telaCampos.getCollVO().add(telaCampo);
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TEM_ISENCAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

		//ABA BEM TRIBUTÁVEL - IMÓVEL URBANO
		prioridade = 1;
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TIPO_LOGRADOURO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_LOGRADOURO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_NUMERO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_COMPLEMENTO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_PONTO_REFERENCIA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_BAIRRO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_CEP));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_ESTADO_CONSERVACAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_AREA_TOTAL_IMOVEL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_AREA_CONSTRUIDA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TIPO_CONSTRUCAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TIPO_ACESSO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_BENFEITORIA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO_BENFEITORIA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALO_MERCADO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_VENAL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

		//ABA BEM TRIBUTÁVE - FICHA IMÓVEL RURAL
		prioridade = 1;
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DENOMINCAO_IMOVEL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_ENDERECO_IMOVEL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_PONTO_REFERENCIA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_CEP));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DISTANCIA_PERIMETRO_URBANO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMP0_AREA_TOTAL_IMOVEL_RURAL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_NUMERO_INDEA_MT));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_CODIGO_RECEITA_FEDERAL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_SELECIONE_CULTURA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO_CULTURA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_AREA_CULTIVADA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_HECTARE));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_POSSUI_PASTAGENS_NATURAIS_CULTIVADAS));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TAMANHO_PASTAGENS));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_PASTAGENS));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_POSSUI_JAZIDAS_MINERAIS_FONTES_AGUA_RADIOATIVA_TERMICA_MINERAIS));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_ACESSOES_NATURAIS));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TIPO_REBANHO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO_REBANHO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_QUANTIDADE));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_CABECA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_SELECIONE_CONSTRUCAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO_CONSTRUCAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_ESTADO_CONSERVACAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALO_MERCADO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_BENFEITORIA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO_BENFEITORIA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_TERRA_NUA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_MERCADO_MAQUINAS_AGRICOLAS));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_OUTROS_VALORES_IMOVEL_RURAL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_VENAL_ITR));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

		//ABA BENEFICIARIOS
		prioridade = 1;
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BENEFICIARIO_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DADOS_BENEFICIARIO));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		//GIA ITCD DOACAO
		//INCLUIR
		//ABA DADOS GERAIS
		prioridade = 1;
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_DADOS_GERAIS_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DADOS_DECLARANTE));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_DADOS_GERAIS_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DADOS_PROCURADOR));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_DADOS_GERAIS_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_NATUREZA_OPERACAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_DADOS_GERAIS_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_PERCENTUAL_DOACAO_TRANSMISSAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
		
	   //ABA BEM TRIBUTÁVEL
	   prioridade = 1;
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_CLASSIFICACAO_BEM));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TIPO_DE_BEM));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_MERCADO_VALOR_TRIBUTAVEL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
		

	   //ABA BEM TRIBUTÁVEL - IMÓVEL URBANO
	   prioridade = 1;
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TIPO_LOGRADOURO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_LOGRADOURO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_NUMERO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_COMPLEMENTO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_PONTO_REFERENCIA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_BAIRRO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_CEP));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_ESTADO_CONSERVACAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_AREA_TOTAL_IMOVEL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_AREA_CONSTRUIDA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TIPO_CONSTRUCAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TIPO_ACESSO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_BENFEITORIA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO_BENFEITORIA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALO_MERCADO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_VENAL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   //ABA BEM TRIBUTÁVEL - FICHA IMÓVEL RURAL
	   prioridade = 1;
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DENOMINCAO_IMOVEL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_ENDERECO_IMOVEL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_PONTO_REFERENCIA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_CEP));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DISTANCIA_PERIMETRO_URBANO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMP0_AREA_TOTAL_IMOVEL_RURAL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_NUMERO_INDEA_MT));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_CODIGO_RECEITA_FEDERAL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_SELECIONE_CULTURA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO_CULTURA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_AREA_CULTIVADA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_HECTARE));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_POSSUI_PASTAGENS_NATURAIS_CULTIVADAS));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TAMANHO_PASTAGENS));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_PASTAGENS));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_POSSUI_JAZIDAS_MINERAIS_FONTES_AGUA_RADIOATIVA_TERMICA_MINERAIS));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_ACESSOES_NATURAIS));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TIPO_REBANHO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO_REBANHO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_QUANTIDADE));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_CABECA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_SELECIONE_CONSTRUCAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO_CONSTRUCAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_ESTADO_CONSERVACAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALO_MERCADO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_BENFEITORIA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO_BENFEITORIA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_TERRA_NUA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_MERCADO_MAQUINAS_AGRICOLAS));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_OUTROS_VALORES_IMOVEL_RURAL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_VENAL_ITR));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
		
		//ABA BENEFICIARIOS
		prioridade = 1;
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BENEFICIARIO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DADOS_BENEFICIARIO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BENEFICIARIO_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_INFORME_PERCENTUAL_ATRIBUIDO_BENEFICIARIO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
		
		//GIA-ITCD SEPARACAO DIVORCIO
		//INCLUIR
		//ABA DADOS GERAIS
		prioridade = 1;
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_DADOS_GERAIS_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DADOS_DECLARANTE));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_DADOS_GERAIS_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DATA_CASAMENTO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_DADOS_GERAIS_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_REGIME_CASAMENTO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_DADOS_GERAIS_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_NUMERO_PROCESSO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_DADOS_GERAIS_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DATA_SEPARACAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_DADOS_GERAIS_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_NATUREZA_OPERACAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_DADOS_GERAIS_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DADOS_PROCURADOR));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
		
		//ABA BENS TRIBUTÁVEIS
		prioridade = 1;
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_CLASSIFICACAO_BEM));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TIPO_DE_BEM));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_MERCADO_VALOR_TRIBUTAVEL));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_BEM_PARTICULAR));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);

		//ABA BEM TRIBUTÁVEL - IMÓVEL URBANO
		prioridade = 1;
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TIPO_LOGRADOURO));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_LOGRADOURO));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_NUMERO));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_COMPLEMENTO));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_PONTO_REFERENCIA));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_BAIRRO));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_CEP));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_ESTADO_CONSERVACAO));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_AREA_TOTAL_IMOVEL));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_AREA_CONSTRUIDA));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TIPO_CONSTRUCAO));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TIPO_ACESSO));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_BENFEITORIA));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO_BENFEITORIA));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALO_MERCADO));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_VENAL));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		//ABA BEM TRIBUTÁVE - FICHA IMÓVEL RURAL
		prioridade = 1;
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DENOMINCAO_IMOVEL));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_ENDERECO_IMOVEL));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_PONTO_REFERENCIA));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_CEP));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DISTANCIA_PERIMETRO_URBANO));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMP0_AREA_TOTAL_IMOVEL_RURAL));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_NUMERO_INDEA_MT));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_CODIGO_RECEITA_FEDERAL));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_SELECIONE_CULTURA));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO_CULTURA));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_AREA_CULTIVADA));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_HECTARE));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_POSSUI_PASTAGENS_NATURAIS_CULTIVADAS));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TAMANHO_PASTAGENS));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_PASTAGENS));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_POSSUI_JAZIDAS_MINERAIS_FONTES_AGUA_RADIOATIVA_TERMICA_MINERAIS));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_ACESSOES_NATURAIS));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TIPO_REBANHO));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO_REBANHO));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_QUANTIDADE));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_CABECA));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_SELECIONE_CONSTRUCAO));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO_CONSTRUCAO));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_ESTADO_CONSERVACAO));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALO_MERCADO));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_BENFEITORIA));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO_BENFEITORIA));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_TERRA_NUA));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_MERCADO_MAQUINAS_AGRICOLAS));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_OUTROS_VALORES_IMOVEL_RURAL));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_VENAL_ITR));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);

		//ABA BENEFIRICIARIO
		prioridade = 1;
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_CONJUGE_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DADOS_CONJUGE2));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

		

	   //GIA-ITCD
	   //INVENTARIO ARROLAMENTO
	   //ABA DADOS GERAIS      
	   prioridade = 1;
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_DADOS_GERAIS_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DADOS_INVENTARIANTE));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_DADOS_GERAIS_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DE_CUJUS_POSSUI_CPF));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_DADOS_GERAIS_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DADOS_DO_DE_CUJUS));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_DADOS_GERAIS_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_ESTADO_CIVIL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_DADOS_GERAIS_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_REGIME_CASAMENTO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_DADOS_GERAIS_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DATA_FALECIMENTO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_DADOS_GERAIS_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DATA_INVENTARIO_ARROLAMENTO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_DADOS_GERAIS_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_UF_ABERTURA_INVENTARIO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_DADOS_GERAIS_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TEM_RENUNCIA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_DADOS_GERAIS_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TIPO_RENUNCIA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_DADOS_GERAIS_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TEM_HERDEIROS_DESCENDENTES));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_DADOS_GERAIS_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_QUANTIDADE_HERDEIROS_DESCENDENTES));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_DADOS_GERAIS_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TEM_HERDEIROS_ASCENDENTES));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_DADOS_GERAIS_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_QUANTIDADE_HERDEIROS_ASCENDENTES));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_DADOS_GERAIS_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_QUANTIDADE_OUTROS_HERDEIROS));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_DADOS_GERAIS_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DADOS_MEEIRO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_DADOS_GERAIS_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DADOS_PROCURADOR));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_DADOS_GERAIS_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_NATUREZA_OPERACAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   //ABA BEM TRIBUTÁVEL
	   prioridade = 1;
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_CLASSIFICACAO_BEM));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TIPO_DE_BEM));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_MERCADO_VALOR_TRIBUTAVEL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_BEM_PARTICULAR));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);

	   telaCampos.getCollVO().add(telaCampo);
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TEM_ISENCAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   //ABA BEM TRIBUTÁVEL - IMÓVEL URBANO
	   prioridade = 1;
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TIPO_LOGRADOURO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_LOGRADOURO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_NUMERO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_COMPLEMENTO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_PONTO_REFERENCIA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_BAIRRO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_CEP));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_ESTADO_CONSERVACAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_AREA_TOTAL_IMOVEL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_AREA_CONSTRUIDA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TIPO_CONSTRUCAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TIPO_ACESSO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_BENFEITORIA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO_BENFEITORIA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALO_MERCADO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_VENAL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   //ABA BEM TRIBUTÁVE - FICHA IMÓVEL RURAL
	   prioridade = 1;
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DENOMINCAO_IMOVEL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_ENDERECO_IMOVEL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_PONTO_REFERENCIA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_CEP));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DISTANCIA_PERIMETRO_URBANO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMP0_AREA_TOTAL_IMOVEL_RURAL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_NUMERO_INDEA_MT));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_CODIGO_RECEITA_FEDERAL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_SELECIONE_CULTURA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO_CULTURA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_AREA_CULTIVADA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_HECTARE));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_POSSUI_PASTAGENS_NATURAIS_CULTIVADAS));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TAMANHO_PASTAGENS));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_PASTAGENS));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_POSSUI_JAZIDAS_MINERAIS_FONTES_AGUA_RADIOATIVA_TERMICA_MINERAIS));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_ACESSOES_NATURAIS));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TIPO_REBANHO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO_REBANHO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_QUANTIDADE));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_CABECA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_SELECIONE_CONSTRUCAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO_CONSTRUCAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_ESTADO_CONSERVACAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALO_MERCADO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_BENFEITORIA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO_BENFEITORIA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_TERRA_NUA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_MERCADO_MAQUINAS_AGRICOLAS));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_OUTROS_VALORES_IMOVEL_RURAL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_VENAL_ITR));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   //ABA BENEFICIARIOS
	   prioridade = 1;
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_INVENTARIO_BENEFICIARIO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DADOS_BENEFICIARIO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   //GIA ITCD DOACAO
	   //INCLUIR
	   //ABA DADOS GERAIS
	   prioridade = 1;
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_DADOS_GERAIS_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DADOS_DECLARANTE));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_DADOS_GERAIS_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DADOS_PROCURADOR));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_DADOS_GERAIS_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_NATUREZA_OPERACAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_DADOS_GERAIS_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_PERCENTUAL_DOACAO_TRANSMISSAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   //ABA BEM TRIBUTÁVEL
	   prioridade = 1;
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_CLASSIFICACAO_BEM));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TIPO_DE_BEM));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_MERCADO_VALOR_TRIBUTAVEL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   

	   //ABA BEM TRIBUTÁVEL - IMÓVEL URBANO
	   prioridade = 1;
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TIPO_LOGRADOURO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_LOGRADOURO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_NUMERO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_COMPLEMENTO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_PONTO_REFERENCIA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_BAIRRO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_CEP));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_ESTADO_CONSERVACAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_AREA_TOTAL_IMOVEL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_AREA_CONSTRUIDA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TIPO_CONSTRUCAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TIPO_ACESSO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_BENFEITORIA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO_BENFEITORIA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALO_MERCADO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_VENAL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   //ABA BEM TRIBUTÁVEL - FICHA IMÓVEL RURAL
	   prioridade = 1;
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DENOMINCAO_IMOVEL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_ENDERECO_IMOVEL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_PONTO_REFERENCIA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_CEP));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DISTANCIA_PERIMETRO_URBANO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMP0_AREA_TOTAL_IMOVEL_RURAL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_NUMERO_INDEA_MT));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_CODIGO_RECEITA_FEDERAL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_SELECIONE_CULTURA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO_CULTURA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_AREA_CULTIVADA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_HECTARE));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_POSSUI_PASTAGENS_NATURAIS_CULTIVADAS));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TAMANHO_PASTAGENS));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_PASTAGENS));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_POSSUI_JAZIDAS_MINERAIS_FONTES_AGUA_RADIOATIVA_TERMICA_MINERAIS));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_ACESSOES_NATURAIS));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TIPO_REBANHO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO_REBANHO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_QUANTIDADE));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_CABECA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_SELECIONE_CONSTRUCAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO_CONSTRUCAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_ESTADO_CONSERVACAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALO_MERCADO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_BENFEITORIA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO_BENFEITORIA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_TERRA_NUA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_MERCADO_MAQUINAS_AGRICOLAS));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_OUTROS_VALORES_IMOVEL_RURAL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_VENAL_ITR));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   //ABA BENEFICIARIOS
	   prioridade = 1;
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BENEFICIARIO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DADOS_BENEFICIARIO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_DOACAO_BENEFICIARIO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_INFORME_PERCENTUAL_ATRIBUIDO_BENEFICIARIO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   //GIA-ITCD SEPARACAO DIVORCIO
	   //ALTERAR
	   //ABA DADOS GERAIS
	   prioridade = 1;
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_DADOS_GERAIS_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DADOS_DECLARANTE));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_DADOS_GERAIS_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DATA_CASAMENTO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_DADOS_GERAIS_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_REGIME_CASAMENTO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_DADOS_GERAIS_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_NUMERO_PROCESSO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_DADOS_GERAIS_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DATA_SEPARACAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_DADOS_GERAIS_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_NATUREZA_OPERACAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_DADOS_GERAIS_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DADOS_PROCURADOR));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   //ABA BENS TRIBUTÁVEIS
	   prioridade = 1;
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_CLASSIFICACAO_BEM));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TIPO_DE_BEM));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_MERCADO_VALOR_TRIBUTAVEL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_BEM_PARTICULAR));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   //ABA BEM TRIBUTÁVEL - IMÓVEL URBANO
	   prioridade = 1;
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TIPO_LOGRADOURO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_LOGRADOURO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_NUMERO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_COMPLEMENTO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_PONTO_REFERENCIA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_BAIRRO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_CEP));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_ESTADO_CONSERVACAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_AREA_TOTAL_IMOVEL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_AREA_CONSTRUIDA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TIPO_CONSTRUCAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TIPO_ACESSO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_BENFEITORIA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO_BENFEITORIA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALO_MERCADO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_URBANO_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_VENAL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   //ABA BEM TRIBUTÁVE - FICHA IMÓVEL RURAL
	   prioridade = 1;
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DENOMINCAO_IMOVEL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_ENDERECO_IMOVEL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_PONTO_REFERENCIA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_CEP));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DISTANCIA_PERIMETRO_URBANO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMP0_AREA_TOTAL_IMOVEL_RURAL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_NUMERO_INDEA_MT));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_CODIGO_RECEITA_FEDERAL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_SELECIONE_CULTURA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO_CULTURA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_AREA_CULTIVADA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_HECTARE));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_POSSUI_PASTAGENS_NATURAIS_CULTIVADAS));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TAMANHO_PASTAGENS));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_PASTAGENS));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_POSSUI_JAZIDAS_MINERAIS_FONTES_AGUA_RADIOATIVA_TERMICA_MINERAIS));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_ACESSOES_NATURAIS));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TIPO_REBANHO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO_REBANHO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_QUANTIDADE));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_CABECA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_SELECIONE_CONSTRUCAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO_CONSTRUCAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_ESTADO_CONSERVACAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALO_MERCADO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_BENFEITORIA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DESCRICAO_BENFEITORIA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_TERRA_NUA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_MERCADO_MAQUINAS_AGRICOLAS));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_OUTROS_VALORES_IMOVEL_RURAL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
	   
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_BEM_TRIBUTAVEL_IMOVEL_RURAL_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_VENAL_ITR));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   //ABA BENEFICIARIO
	   prioridade = 1;
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_SEPARACAO_CONJUGE_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DADOS_CONJUGE2));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
		
		//GIA-ITCD
		//CONSULTAR GIA-ITCD - SERVIDOR
		prioridade = 1;
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_CONSULTAR_SERVIDOR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TIPO_CONSULTA));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_CONSULTAR_SERVIDOR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DADOS_CONTRIBUINTE));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_CONSULTAR_SERVIDOR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_NUMERO_GIA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
		
		//CONSULTAR GIA-ITCD CONTRIBUINTE
		prioridade = 1;
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_CONSULTAR_LIVRE));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_NUMERO_GIA));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_CONSULTAR_LIVRE));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_SENHA));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		//GIA-ITCD REATIVAR
		prioridade = 1;
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_REATIVAR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_JUSTIFICATIVA_REATIVACAO));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);

	   //GIA-ITCD REATIVAR
	   prioridade = 1;
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_INATIVAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_NUMERO_PROTOCOLO_INATIVACAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_INATIVAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DATA_INATIVACAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_INATIVAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_OBSERVACAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
		
		//GIA-ITCD CONSULTAR AUTENTICIDADE
		prioridade = 1;
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_CONSULTAR_AUTENTICIDADE));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_SELECIONE));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_CONSULTAR_AUTENTICIDADE));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_NUMERO_GIA_ITCD));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_CONSULTAR_AUTENTICIDADE));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_NUMERO_DECLARACAO_ISENCAO_VALORES));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_CONSULTAR_AUTENTICIDADE));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_NUMERO_DECLARACAO_NAO_OCORRENCIA_FATO_GERADOR));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_CONSULTAR_AUTENTICIDADE));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_CODIGO_AUTENTICIDADE));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

		//VINCULAR DAR
		prioridade = 1;
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_VINCULAR_DAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_NUMERO_GIA_ITCD));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_VINCULAR_DAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_NUMERO_DAR));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
		
		//REIMPRIMIR GIA-ITCD
		prioridade = 1;
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_CONSULTAR_REIMPRIMIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DADOS_CONTRIBUINTE));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_CONSULTAR_REIMPRIMIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_PROCESSO_REIMPRESSAO));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_CONSULTAR_REIMPRIMIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_NUMERO_GIA_REIMPRIMIR));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
		
		//REIMPRIMIR NOTIFICAÇAO / RETIFICACAO
		prioridade = 1;
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_CONSULTAR_NOTIFICACAO_RETIFICACAO));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DADOS_CONTRIBUINTE));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_CONSULTAR_NOTIFICACAO_RETIFICACAO));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TIPO_DOCUMENTO));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_CONSULTAR_NOTIFICACAO_RETIFICACAO));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_NUMERO_GIA_REIMPRIMIR));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		//ALTERAR STATUS MANUAL
		prioridade = 1;
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_STATUS_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_SELECIONE_STATUS));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_STATUS_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_NUMERO_PROTOCOLO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_STATUS_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DATA_PROTOCOLO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_STATUS_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_AGENFA_PROTOCOLO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_STATUS_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_NUMERO_PROTOCOLO_PARCELAMENTO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_STATUS_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_QUANTIDADE_PARCELAS));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_STATUS_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DATA_PARCELAMENTO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_STATUS_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_NUMERO_PROTOCOLO_IMPUGNACAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_STATUS_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DATA_IMPUGNACAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_STATUS_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DATA_EMISSAO_DAR_SEGUNDA_RETIFICACAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_STATUS_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_NUMERO_DAR_SEGUNDA_RETIFICACAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_STATUS_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DATA_CIENCIA_NOTIFICACAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_STATUS_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DATA_ENVIO_DIVIDA_ATIVA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_STATUS_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DATA_CIENCIA_RETIFICACAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_STATUS_ALTERAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DATA_PERMISSAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

		//AVALIAÇÃO INCLUIR
		prioridade = 1;
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_AVALIACAO_INCLUIR_AVALIACAO_BEM));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_AVALIACAO_JUDICIAL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_AVALIACAO_INCLUIR_AVALIACAO_BEM));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_ISENCAO_PREVISTA_EM_LEI));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_AVALIACAO_INCLUIR_AVALIACAO_BEM));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_AVALIACAO_JUDICIAL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_AVALIACAO_INCLUIR_AVALIACAO_BEM));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DATA_AVALIACAO_JUDICIAL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_AVALIACAO_INCLUIR_AVALIACAO_BEM));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_AVALIACAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_AVALIACAO_INCLUIR_AVALIACAO_BEM));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DATA_AVALIACAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_AVALIACAO_INCLUIR_AVALIACAO_BEM));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DATA_CADASTRO_AVALIACAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_AVALIACAO_INCLUIR_AVALIACAO_BEM_SERVIDOR_INCLUIR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_MATRICULA_AVALIADOR));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

		//AVALIACAO ALTERAR
		prioridade = 1;
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_AVALIACAO_ALTERAR_AVALIACAO_BEM));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_AVALIACAO_JUDICIAL));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_AVALIACAO_ALTERAR_AVALIACAO_BEM));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_ISENCAO_PREVISTA_EM_LEI));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_AVALIACAO_ALTERAR_AVALIACAO_BEM));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_AVALIACAO_JUDICIAL));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_AVALIACAO_ALTERAR_AVALIACAO_BEM));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DATA_AVALIACAO_JUDICIAL));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_AVALIACAO_ALTERAR_AVALIACAO_BEM));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_VALOR_AVALIACAO));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_AVALIACAO_ALTERAR_AVALIACAO_BEM));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DATA_AVALIACAO));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_AVALIACAO_ALTERAR_AVALIACAO_BEM));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DATA_ALTERACAO_AVALIACAO));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);
		
		telaCampo = new TelaCampoAjudaVo();
		telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_AVALIACAO_ALTERAR_AVALIACAO_BEM_SERVIDOR_INCLUIR));
		telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_MATRICULA_AVALIADOR));
		telaCampo.setDescricaoAjudaCampo("***");
		telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		telaCampo.setCodigoOrdenacao(prioridade++);
		telaCampos.getCollVO().add(telaCampo);

		//AVALIACAO CONSULTAR
		prioridade = 1;
	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_AVALIACAO_CONSULTAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_TIPO_CONSULTA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_AVALIACAO_CONSULTAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_NUMERO_GIA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_AVALIACAO_CONSULTAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DADOS_CONTRIBUINTE));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_AVALIACAO_CONSULTAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_AGENFA));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_AVALIACAO_CONSULTAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_GERENCIA_EXECUCAO));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_AVALIACAO_CONSULTAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DATA_INICIAL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);

	   telaCampo = new TelaCampoAjudaVo();
	   telaCampo.setTelaAjudaVo(new TelaAjudaVo(CODIGO_TELA_GIA_ITCD_AVALIACAO_CONSULTAR));
	   telaCampo.setCampoAjudaVo(new CampoAjudaVo(CODIGO_CAMPO_DATA_FINAL));
	   telaCampo.setDescricaoAjudaCampo("***");
	   telaCampo.setStatusTelaCampo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
	   telaCampo.setCodigoOrdenacao(prioridade++);
	   telaCampos.getCollVO().add(telaCampo);
		

		solicitarCadastrarTelaCampo(telaCampos);
	}

	private void solicitarCadastrarTelaCampo(TelaCampoAjudaVo telaCampoVo) throws SQLException
	{
	   if(telaCampoVo.getCollVO() != null && telaCampoVo.getCollVO().size() > 0)
	   {
	      CargaInicialDao dao = new CargaInicialDao(conn);
	      for(Iterator it = telaCampoVo.getCollVO().iterator(); it.hasNext(); )
	      {
	         TelaCampoAjudaVo telaCampoAtual = (TelaCampoAjudaVo) it.next();
	         dao.inserirTelaCampo(telaCampoAtual);
	      }
	   }     			
	}
}
