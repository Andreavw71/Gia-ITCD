/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: ConfiguracaoGerencialParametrosBe.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 06/11/2007
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.configuracaogerencialparametros;

import br.com.abaco.log5.GenericoLogAnotacaoDao;
import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoConfiguracaoGerencialParametros;
import br.gov.mt.sefaz.itc.util.facade.ConfiguracaoITCD;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.MensagemSucesso;

import br.gov.mt.sefaz.itc.util.log.ExibirLOG;

import java.sql.Connection;
import java.sql.SQLException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import sefaz.mt.util.AlteraException;


/**
 * Classe responsável por implementar a regra de negócio da entidade "ConfiguracaoGerencialParametros".
 * @author Daniel Balieiro
 * @version $Revision: 1.2 $
 */
public class ConfiguracaoGerencialParametrosBe extends AbstractBe
{
	/**
	 * Construtor padrão.
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	public ConfiguracaoGerencialParametrosBe() throws SQLException
	{
		super();
	}

	/**
	 * Construtor que recebe a conexão com o Banco de Dados.
	 * @param conexao objeto de conexão com o banco de dados.
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	public ConfiguracaoGerencialParametrosBe(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Método para validar o ConfiguracaoGerencialParametrosVo.
	 * @param configuracaoGerencialParametrosVo Configuração Gerencial de Parâmetros (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ParseException Esta exceção deve ser lançada caso ocorra algum problema durante a conversão de uma String em data ou em valores primitivos.
	 * @throws ParametroObrigatorioException Esta exceção deve ser lançada quando o usuário não informa um campo que é obrigatório para o sistema.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	private void validaParametrosIncluirConfiguracaoGerencialParametros(final ConfiguracaoGerencialParametrosVo configuracaoGerencialParametrosVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException
	{
		Validador.validaObjeto(configuracaoGerencialParametrosVo);
		if (!Validador.isCollectionValida(configuracaoGerencialParametrosVo.getCollVO()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_COLECAO_CONFIGURACAO_GERENCIAL_PARAMETROS);
		}
		for (Iterator iteConfiguracaoGerencialParametros = configuracaoGerencialParametrosVo.getCollVO().iterator(); iteConfiguracaoGerencialParametros.hasNext(); )
		{
			ConfiguracaoGerencialParametrosVo configuracaoGerencialParametrosAtualVo= (ConfiguracaoGerencialParametrosVo) iteConfiguracaoGerencialParametros.next();
			if(!ignoraValidacaoValorItem(configuracaoGerencialParametrosAtualVo.getDescricaoItem()))
			{
				validaValorItem(configuracaoGerencialParametrosAtualVo.getTipoItem(), configuracaoGerencialParametrosAtualVo.getValorItem());	
			}			
		}
	}

	/**
	 * Método para Preparar um ConfiguracaoGerencialParametrosVo para inserir.<br>
	 * Este método deve ser chamado antes de inserir/atualizar uma ConfiguracaoGerencialParametrosVo, para que somente
	 * os valores válidos passem pela validação.
	 * 
	 * @param configuracaoGerencialParametrosVo
	 * @throws ObjetoObrigatorioException
	 * @return ConfiguracaoGerencialParametrosVo
	 * @implemented by Daniel Balieiro
	 */
	public static ConfiguracaoGerencialParametrosVo preparaConfiguracaoGerencialParametros(final ConfiguracaoGerencialParametrosVo configuracaoGerencialParametrosVo) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(configuracaoGerencialParametrosVo);
		ConfiguracaoGerencialParametrosVo configuracaoGerencialParametrosNovoVo = new ConfiguracaoGerencialParametrosVo();
		for (Iterator iteCGP = configuracaoGerencialParametrosVo.getCollVO().iterator(); iteCGP.hasNext(); )
		{
			ConfiguracaoGerencialParametrosVo configuracaoGerencialParametrosAtualVo = (ConfiguracaoGerencialParametrosVo) iteCGP.next();
			if (Validador.isStringValida(configuracaoGerencialParametrosAtualVo.getValorItem()))
			{
				configuracaoGerencialParametrosNovoVo.getCollVO().add(configuracaoGerencialParametrosAtualVo);
			}
		}
		return configuracaoGerencialParametrosNovoVo;
	}

	/**
	 * Método utilizado para incluir as Configurações Gerenciais Parametros no Banco de Dados.
	 * @param configuracaoGerencialParametrosVo Configuração Gerencial de Parâmetros (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws AlteraException Esta exceção deve ser lançada quando o sistema tenta ALTERAR um registro no banco de dados e não consegue.
	 * @throws ParseException Esta exceção deve ser lançada caso ocorra algum problema durante a conversão de uma String em data ou em valores primitivos.
	 * @throws ParametroObrigatorioException Esta exceção deve ser lançada quando o usuário não informa um campo que é obrigatório para o sistema.
	 * @throws ConexaoException Esta exceção deverá ser lançada para substibuir o SQLException na camada de negócio e na camada de apresentação.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	public synchronized void incluirConfiguracaoGerencialParametros(final ConfiguracaoGerencialParametrosVo configuracaoGerencialParametrosVo) throws ObjetoObrigatorioException, 
			  AlteraException, ParseException, ParametroObrigatorioException, ConexaoException, LogException, 
			  PersistenciaException, AnotacaoException
	{
		manter(configuracaoGerencialParametrosVo, MensagemSucesso.INCLUIR);
	}

	/**
	 * Método utilizado para alterar as Configurações Gerenciais Parametros no Banco de Dados.
	 * @param configuracaoGerencialParametrosVo Configuração Gerencial de Parâmetros (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws AlteraException Esta exceção deve ser lançada quando o sistema tenta ALTERAR um registro no banco de dados e não consegue.
	 * @throws ParseException Esta exceção deve ser lançada caso ocorra algum problema durante a conversão de uma String em data ou em valores primitivos.
	 * @throws ParametroObrigatorioException Esta exceção deve ser lançada quando o usuário não informa um campo que é obrigatório para o sistema.
	 * @throws ConexaoException Esta exceção deverá ser lançada para substibuir o SQLException na camada de negócio e na camada de apresentação.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	public synchronized void alterarConfiguracaoGerencialParametros(final ConfiguracaoGerencialParametrosVo configuracaoGerencialParametrosVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException, ConexaoException, LogException, PersistenciaException, AnotacaoException
	{
		manter(configuracaoGerencialParametrosVo, MensagemSucesso.ALTERAR);
	}

	/**
	 * Método chamado pelo incluirConfiguracaoGerencialParametros e alterarConfiguracaoGerencialParametros para alterar os dados
	 * do Configuração Gerencial Parametros
	 * 
	 * @param configuracaoGerencialParametrosVo Configuração Gerencial de Parâmetros (Value Object).
	 * @param mensagem
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws AlteraException Esta exceção deve ser lançada quando o sistema tenta ALTERAR um registro no banco de dados e não consegue.
	 * @throws ParseException Esta exceção deve ser lançada caso ocorra algum problema durante a conversão de uma String em data ou em valores primitivos.
	 * @throws ParametroObrigatorioException Esta exceção deve ser lançada quando o usuário não informa um campo que é obrigatório para o sistema.
	 * @throws ConexaoException Esta exceção deverá ser lançada para substibuir o SQLException na camada de negócio e na camada de apresentação.
	 * @implemented by Daniel Balieiro
	 */
	private synchronized void manter(final ConfiguracaoGerencialParametrosVo configuracaoGerencialParametrosVo, String mensagem) throws ObjetoObrigatorioException, 
			 ParametroObrigatorioException, ConexaoException, LogException, PersistenciaException, AnotacaoException
	{
		Validador.validaObjeto(configuracaoGerencialParametrosVo);
		try
		{
			try
			{
				synchronized (ConfiguracaoGerencialParametrosVo.class)
				{
					validaParametrosIncluirConfiguracaoGerencialParametros(configuracaoGerencialParametrosVo);
					
					for (Iterator iteConfiguracaoGerencialParametros = configuracaoGerencialParametrosVo.getCollVO().iterator(); iteConfiguracaoGerencialParametros.hasNext(); )
					{
						ConfiguracaoGerencialParametrosVo configuracaoGerencialParametrosAtualVo = (ConfiguracaoGerencialParametrosVo) iteConfiguracaoGerencialParametros.next();
						if (!configuracaoGerencialParametrosAtualVo.getValorItem().equals(""))
						{
							configuracaoGerencialParametrosAtualVo.setStatusCadastrado(new DomnSimNao(DomnSimNao.SIM));
							if (configuracaoGerencialParametrosAtualVo.getTipoItem().getValorCorrente() == DomnTipoConfiguracaoGerencialParametros.DATA)
							{
								configuracaoGerencialParametrosAtualVo.setValorItem(new SimpleDateFormat("dd/MM/yyyy").format(StringUtil.toUtilData(configuracaoGerencialParametrosAtualVo.getValorItem())));
							}
							configuracaoGerencialParametrosAtualVo.setDataAtualizacao(new Date());
							alterar(configuracaoGerencialParametrosAtualVo);
						}
					}
					commit();
					configuracaoGerencialParametrosVo.setMensagem(mensagem);
				}
			}
			catch (ParametroObrigatorioException e)
			{
				conn.rollback();
				throw e;
			}
			catch (ObjetoObrigatorioException e)
			{
				conn.rollback();
				throw e;
			}
			catch(LogException e)
			{
				conn.rollback();
				throw e;
			}
			catch(PersistenciaException e)
			{
				conn.rollback();
				throw e;
			}
			catch(AnotacaoException e)
			{
				conn.rollback();
				throw e;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}
	}

	/**
	 * @param configuracaoGerencialParametrosVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void alterar(final ConfiguracaoGerencialParametrosVo configuracaoGerencialParametrosVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(configuracaoGerencialParametrosVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).update(configuracaoGerencialParametrosVo);
	}

	/**
	 * Método utilizado para Listar as Configurações Gerenciais Parametros não cadastrados.
	 * @param configuracaoGerencialParametrosVo Configuração Gerencial de Parâmetros (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConexaoException Esta exceção deverá ser lançada para substibuir o SQLException na camada de negócio e na camada de apresentação.
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	public ConfiguracaoGerencialParametrosVo listarConfiguracaoGerencialParametrosNaoCadastrados(final ConfiguracaoGerencialParametrosVo configuracaoGerencialParametrosVo) throws ObjetoObrigatorioException, 
			  ConexaoException, ConsultaException
	{
		Validador.validaObjeto(configuracaoGerencialParametrosVo);
		configuracaoGerencialParametrosVo.setStatusCadastrado(new DomnSimNao(DomnSimNao.NAO));
		(new ConfiguracaoGerencialParametrosQDao(conn)).listConfiguracaoGerencialParametros(configuracaoGerencialParametrosVo);
		return configuracaoGerencialParametrosVo;
	}

	/**
	 * Método utilizado para Listar as Configurações Gerenciais Parametros cadastrados.
	 * @param configuracaoGerencialParametrosVo Configuração Gerencial de Parâmetros (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConexaoException Esta exceção deverá ser lançada para substibuir o SQLException na camada de negócio e na camada de apresentação.
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	public ConfiguracaoGerencialParametrosVo listarConfiguracaoGerencialParametrosCadastrados(final ConfiguracaoGerencialParametrosVo configuracaoGerencialParametrosVo) throws ObjetoObrigatorioException, 
			  ConexaoException, ConsultaException
	{
		Validador.validaObjeto(configuracaoGerencialParametrosVo);
		configuracaoGerencialParametrosVo.setStatusCadastrado(new DomnSimNao(DomnSimNao.SIM));
		(new ConfiguracaoGerencialParametrosQDao(conn)).listConfiguracaoGerencialParametros(configuracaoGerencialParametrosVo);
		return configuracaoGerencialParametrosVo;
	}

	/**
	 * Método utilizado para Consultar uma Configurações Gerenciais Parametros.
	 * @param configuracaoGerencialParametrosVo Configuração Gerencial de Parâmetros (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConexaoException Esta exceção deverá ser lançada para substibuir o SQLException na camada de negócio e na camada de apresentação.
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	public ConfiguracaoGerencialParametrosVo consultarConfiguracaoGerencialParametros(final ConfiguracaoGerencialParametrosVo configuracaoGerencialParametrosVo) throws ObjetoObrigatorioException, 
			  ConexaoException, ConsultaException
	{
		Validador.validaObjeto(configuracaoGerencialParametrosVo);
		(new ConfiguracaoGerencialParametrosQDao(conn)).findConfiguracaoGerencialParametros(configuracaoGerencialParametrosVo);
      if(!Validador.isNumericoValido(configuracaoGerencialParametrosVo.getCodigo()))
      {
         ExibirLOG.exibirLog("Configuração Genrencial NÃO encontrada: "+configuracaoGerencialParametrosVo.getValorItem());
      }
		return configuracaoGerencialParametrosVo;
	}

	/**
	 * @param configuracaoGerencialParametrosVo
	 * @param codigo
	 * @param valor
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public static void adicionarItemAlterar(ConfiguracaoGerencialParametrosVo configuracaoGerencialParametrosVo, int codigo, String valor) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException
	{
		Validador.validaObjeto(configuracaoGerencialParametrosVo);
		if(Validador.isNumericoValido(codigo))
		{
		   ConfiguracaoGerencialParametrosVo configuracaoGerencialParametrosAtualVo = new ConfiguracaoGerencialParametrosVo(codigo); 
		   int indice = ((List) configuracaoGerencialParametrosVo.getCollVO()).indexOf(configuracaoGerencialParametrosAtualVo);
		   configuracaoGerencialParametrosAtualVo = (ConfiguracaoGerencialParametrosVo) ((List) configuracaoGerencialParametrosVo.getCollVO()).get(indice);
			if(!ignoraValidacaoValorItem(configuracaoGerencialParametrosAtualVo.getDescricaoItem()))				
			{
			   validaValorItem(configuracaoGerencialParametrosAtualVo.getTipoItem(), valor);
			}
		   configuracaoGerencialParametrosAtualVo.setValorItem(valor);
		   configuracaoGerencialParametrosAtualVo.setStatusCadastrado(new DomnSimNao(DomnSimNao.NAO));
			
		}
	}

	/**
	 * @param listaValores
	 * @param valor
	 * @return
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public static <T extends Object> boolean verificaValorContidoLista(T[] listaValores, T valor)
	{
		if(listaValores != null && valor != null )
		{
			for(T atual : listaValores)
			{
				if(atual.equals(valor))
				{
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @param descricaoParametro
	 * @return
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private static boolean ignoraValidacaoValorItem(String descricaoParametro)
	{
		Collection<String> listaIgnorados = new ArrayList<String>();
		listaIgnorados.add(ConfiguracaoITCD.PARAMETRO_LIMITE_MINIMO_AREA_CONSTRUIDA_IMOVEL_URBANO);
		listaIgnorados.add(ConfiguracaoITCD.PARAMETRO_AREA_MINIMA_HECTARES_IMOVEL_RURAL);
		listaIgnorados.add(ConfiguracaoITCD.PARAMETRO_DISTANCIA_KM_PERIMETRO_URBANO);
		return verificaValorContidoLista(listaIgnorados.toArray(), descricaoParametro);
	}

	/**
	 * @param tipoItem
	 * @param item
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public static void validaValorItem(DomnTipoConfiguracaoGerencialParametros tipoItem, String item) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException
	{
		Validador.validaObjeto(tipoItem);
		Validador.validaObjeto(item);
		if(!Validador.isStringValida(item))
		{
		   throw new ParametroObrigatorioException(MensagemErro.VALIDAR_ITEMS_CONFIGURACAO_GERENCIAL_PARAMETROS);
		}
	   switch(tipoItem.getValorCorrente())
	   {
	      case DomnTipoConfiguracaoGerencialParametros.NUMERO:
	      {
	         if(!Validador.isNumericoValido(StringUtil.toInt(item)))
	         {
	            throw new ParametroObrigatorioException(MensagemErro.VALIDAR_NUMERICO_CONFIGURACAO_GERENCIAL_PARAMETROS);   
	         }                             
	         break;
	      }
	      case DomnTipoConfiguracaoGerencialParametros.DECIMAL:
	      {
	         if(!Validador.isNumericoValido(StringUtil.monetarioToDouble(item)))
	         {
	            throw new ParametroObrigatorioException(MensagemErro.VALIDAR_NUMERICO_CONFIGURACAO_GERENCIAL_PARAMETROS);   
	         }              
	         break;
	      }
	      case DomnTipoConfiguracaoGerencialParametros.TEXTO:
	      {
	         if(!Validador.isStringValida(item))
	         {
	            throw new ParametroObrigatorioException(MensagemErro.VALIDAR_ITEMS_CONFIGURACAO_GERENCIAL_PARAMETROS);   
	         }              
	         break;
	      }
	      case DomnTipoConfiguracaoGerencialParametros.DATA:
	      {
	         if(!Validador.isDataValida(StringUtil.toUtilData(item)))
	         {
	            throw new ParametroObrigatorioException(MensagemErro.VALIDAR_ITEMS_CONFIGURACAO_GERENCIAL_PARAMETROS);   
	         }              
	         break;
	      }
	      case DomnTipoConfiguracaoGerencialParametros.VALOR:
	      {
	         if(!Validador.isNumericoValido(StringUtil.monetarioToDouble(item)))
	         {
	            throw new ParametroObrigatorioException(MensagemErro.VALIDAR_VALOR_CONFIGURACAO_GERENCIAL_PARAMETROS);   
	         }              
	         break;
	      }     
	   }		
	}

	/**
	 * @param item
	 * @return
	 * @throws ObjetoObrigatorioException
	 * @throws ConexaoException
	 * @throws ConsultaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public ConfiguracaoGerencialParametrosVo obterValorItem(String item) throws ObjetoObrigatorioException, 
			  ConexaoException, ConsultaException
	{
		Validador.validaObjeto(item);
	   ConfiguracaoGerencialParametrosVo parametro = new ConfiguracaoGerencialParametrosVo();
	   ConfiguracaoGerencialParametrosVo consulta = new ConfiguracaoGerencialParametrosVo();
		parametro.setDescricaoItem(item);
		consulta.setParametroConsulta(parametro);
		return consultarConfiguracaoGerencialParametros(consulta);
	}
}
