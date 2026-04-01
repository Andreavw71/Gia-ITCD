/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: ConjugeBemTributavelBe.java
 * Revisão:
 * Data revisão:
 * $Id: ConjugeBemTributavelBe.java,v 1.4 2009/05/05 20:01:14 ricardo.moraes Exp $
 */
package br.gov.mt.sefaz.itc.model.generico.conjuge;

import br.com.abaco.log5.GenericoLogAnotacaoDao;
import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.log.LogUtil;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelBe;
import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdseparacaodivorcio.GIAITCDSeparacaoDivorcioVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoConjuge;
import br.gov.mt.sefaz.itc.util.facade.ConfiguracaoITCD;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.CadastroBe;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.ContribuinteIntegracaoVo;

import java.io.IOException;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


/**
 * Classe de negócio de relacionamento de conjuge com bems tributáveis.
 *
 * @author Leandro Dorileo
 * @version $Revision: 1.4 $
 */
public class ConjugeBemTributavelBe extends AbstractBe
{

	/**
	 * Contrutor Padrão (Vazio).
	 * 
	 * @throws SQLException
	 * @implemented by Lucas Nascimento
	 */
	public ConjugeBemTributavelBe() throws SQLException
	{
		super();
	}

	/**
	 * Construtor público recebendo uma conexão.
	 * @param conexao
	 * @implemented by Daniel Balieiro
	 */
	public ConjugeBemTributavelBe(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * @param conjugeBemTributavelVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void incluir(final ConjugeBemTributavelVo conjugeBemTributavelVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(conjugeBemTributavelVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).insert(conjugeBemTributavelVo);
	}

	/**
	 * @param conjugeBemTributavelVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void alterar(final ConjugeBemTributavelVo conjugeBemTributavelVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(conjugeBemTributavelVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).update(conjugeBemTributavelVo);
	}

	/**
	 * @param conjugeBemTributavelVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void excluir(final ConjugeBemTributavelVo conjugeBemTributavelVo) throws ObjetoObrigatorioException, 
			  LogException, AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(conjugeBemTributavelVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).delete(conjugeBemTributavelVo);
	}  	

	/**
	 * Inclui os dados de um determinado conjuge para um bem tributável no banco de dados
	 * @param conjugeBemTributavelVo
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @throws ConexaoException
	 * @implemented by Daniel Balieiro
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void incluirConjugeBemTributavel(final ConjugeBemTributavelVo conjugeBemTributavelVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException, LogException, PersistenciaException, AnotacaoException, ConexaoException
	{
		Validador.validaObjeto(conjugeBemTributavelVo);
		try
		{
			try
			{
				synchronized (ConjugeBemTributavelVo.class)
				{
					validarIncluirConjugeBemTributavel(conjugeBemTributavelVo);
					incluir(conjugeBemTributavelVo);
				}
			}			
			catch(ObjetoObrigatorioException e)
			{
				conn.rollback();
				throw e;
			}
			catch (ParametroObrigatorioException e)
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
	 * Altera os dados de um conjuge relacionado a um bem tributavel no banco de dados
	 * @param conjugeBemTributavelVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @throws ConexaoException
	 * @implemented by Daniel Balieiro
	 * @implemented by Leandro Dorileo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public void alterarConjugeBemTributavel(final ConjugeBemTributavelVo conjugeBemTributavelVo) throws ObjetoObrigatorioException, 
			  LogException, PersistenciaException, AnotacaoException, ConexaoException
	{
		Validador.validaObjeto(conjugeBemTributavelVo);
		try
		{
			try
			{
				synchronized (ConjugeBemTributavelVo.class)
				{
					alterar(conjugeBemTributavelVo);
				}
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
	 * Consulta as informações de um conjuge relacionado com um Bem Tributável no banco de dados.
	 * @param conjugeBemTributavelVo
	 * @return ConjugeBemTributavelVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws ParametroObrigatorioException
	 * @implemented by Daniel Balieiro
	 * @implemented by Leandro Dorileo
	 * @implemented by Lucas Nascimento
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public ConjugeBemTributavelVo consultarConjugeBemTributavel(final ConjugeBemTributavelVo conjugeBemTributavelVo) throws ObjetoObrigatorioException, 
			  ConsultaException, ConexaoException, ParametroObrigatorioException, SQLException, IOException
   {
		Validador.validaObjeto(conjugeBemTributavelVo);
		boolean consultaCompleta = conjugeBemTributavelVo.isConsultaCompleta();
		(new ConjugeBemTributavelQDao(conn)).findConjugeBemTributavel(conjugeBemTributavelVo);
		try
		{
			if (consultaCompleta)
			{
				if (Validador.isNumericoValido(conjugeBemTributavelVo.getPessoaConjuge().getNumrContribuinte()))
				{
					conjugeBemTributavelVo.setPessoaConjuge((new CadastroBe(conn)).obterContribuinte(conjugeBemTributavelVo.getPessoaConjuge()));
				}
				if (Validador.isNumericoValido(conjugeBemTributavelVo.getBemTributavelVo().getCodigo()))
				{
					(new BemTributavelBe(conn)).consultarBemTributavel(conjugeBemTributavelVo.getBemTributavelVo());
				}
			}
		}
		catch (IntegracaoException e)
		{
			new ConsultaException(e.getMessage());
		}
		return conjugeBemTributavelVo;
	}

	/**
	 * Lista todos os registros de conjuges relacionados a bens tributávels com os parâmetros
	 * fornecidos em <b>conjugeBemTributavelVo</b>.
	 * @param conjugeBemTributavelVo
	 * @return ConjugeBemTributavelVo
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @throws IntegracaoException
	 * @throws ConexaoException
	 * @throws ParametroObrigatorioException
	 * @implemented by Daniel Balieiro
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public ConjugeBemTributavelVo listarConjugeBemTributavel(final ConjugeBemTributavelVo conjugeBemTributavelVo) throws ObjetoObrigatorioException, 
			  ConsultaException, IntegracaoException, ConexaoException, ParametroObrigatorioException, SQLException, IOException
   {
		Validador.validaObjeto(conjugeBemTributavelVo);
		(new ConjugeBemTributavelQDao(conn)).listConjugeBemTributavel(conjugeBemTributavelVo);
		if (conjugeBemTributavelVo.isConsultaCompleta())
		{
			for (Iterator iteCon = conjugeBemTributavelVo.getCollVO().iterator(); iteCon.hasNext(); )
			{
				ConjugeBemTributavelVo conjuge = (ConjugeBemTributavelVo) iteCon.next();
				if (Validador.isNumericoValido(conjuge.getPessoaConjuge().getNumrContribuinte()))
				{
					conjuge.setPessoaConjuge(new ContribuinteIntegracaoVo(conjuge.getPessoaConjuge()));
					conjugeBemTributavelVo.setPessoaConjuge((new CadastroBe(conn)).obterContribuinte(conjuge.getPessoaConjuge()));
				}
				if (Validador.isNumericoValido(conjuge.getBemTributavelVo().getCodigo()))
				{
					BemTributavelVo bemTributavelParametroVo = new BemTributavelVo();
					bemTributavelParametroVo.setCodigo(conjuge.getBemTributavelVo().getCodigo());
					BemTributavelVo bemTributavelConsultaVo = new BemTributavelVo(bemTributavelParametroVo);
				   bemTributavelConsultaVo.setConsultaCompleta(true);
					(new BemTributavelBe(conn)).consultarBemTributavel(bemTributavelConsultaVo);
					conjuge.setBemTributavelVo(bemTributavelConsultaVo);
				}
			}
		}
		return conjugeBemTributavelVo;
	}

	/**
	 * Valida um conjuge relacionado a um bem tributável antes de efetuar alguma operação
	 * @param conjugeBemTributavelVo
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @implemented by Daniel Balieiro
	 * @implemented by Lucas Nascimento
	 */
	private void validarIncluirConjugeBemTributavel(final ConjugeBemTributavelVo conjugeBemTributavelVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException
	{
		Validador.validaObjeto(conjugeBemTributavelVo);
		if (conjugeBemTributavelVo.getValorConjuge() == 0)
		{
			throw new ParametroObrigatorioException(MensagemErro.GIA_ITCD_SEPARACAO_VALIDAR_VALOR_BEM_CONJUGE_NAO_INFORMADO);
		}
		if (!(conjugeBemTributavelVo.getValorConjuge() > 0))
		{
			throw new ParametroObrigatorioException(MensagemErro.GIA_ITCD_SEPARACAO_VALIDAR_VALOR_BEM_CONJUGE_ZERO_OU_MENOR);
		}
		if (conjugeBemTributavelVo.getValorConjuge() > conjugeBemTributavelVo.getBemTributavelVo().getValorMercado())
		{
			throw new ParametroObrigatorioException(MensagemErro.GIA_ITCD_SEPARACAO_VALIDAR_VALOR_BEM_CONJUGE_MAIOR_VALOR_MERCADO);
		}
	}

	/**
	 * Confirma se o Código do Bem Tributavel não existe e percorre a lista encontrando o código válido.
	 * @param giaITCDSeparacaoDivorcioVo
	 * @param conjuge
	 * @implemented by Lucas Nascimento
	 */
	private void confirmaCodigoBemTributavel(final GIAITCDSeparacaoDivorcioVo giaITCDSeparacaoDivorcioVo, final ConjugeBemTributavelVo conjuge)
	{
		if (!Validador.isNumericoValido(conjuge.getBemTributavelVo().getCodigo()))
		{
			for (Iterator iteBens = 
							giaITCDSeparacaoDivorcioVo.getBemTributavelVo().getCollVO().iterator(); iteBens.hasNext(); )
			{
				BemTributavelVo bemAtual = (BemTributavelVo) iteBens.next();
				if (bemAtual.getDescricaoBemTributavel().equals(conjuge.getBemTributavelVo().getDescricaoBemTributavel()) && 
							  bemAtual.getFichaImovelVo().getClass().getName().equals(conjuge.getBemTributavelVo().getFichaImovelVo().getClass().getName()))
				{
					conjuge.setBemTributavelVo(bemAtual);
				}
			}
		}
	}	

	/**
	 * Método responsável por preparar e solicitar a inclusão dos bens de um conjuge para inclusão de uma GIA ITCD Separação Divórcio.
	 * @param conjugeBemTributavelVo
	 * @param tipoConjuge
	 * @throws ObjetoObrigatorioException
	 * @throws ConexaoException
	 * @throws ParametroObrigatorioException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public void incluirConjugeBemTributavelIncluirGIASeparacaoDivorcio(final ConjugeBemTributavelVo conjugeBemTributavelVo, DomnTipoConjuge tipoConjuge) throws ObjetoObrigatorioException, 
			  ConexaoException, ParametroObrigatorioException, LogException, PersistenciaException, AnotacaoException
	{
		Validador.validaObjeto(conjugeBemTributavelVo);
		Validador.validaObjeto(tipoConjuge);		
		try
		{
			try
			{
			   synchronized(ConjugeBemTributavelVo.class)
			   {
			      for (Iterator itConjugeBemTrib = conjugeBemTributavelVo.getCollVO().iterator(); itConjugeBemTrib.hasNext(); )
			      {
			         ConjugeBemTributavelVo conjuge = (ConjugeBemTributavelVo) itConjugeBemTrib.next();
			         confirmaCodigoBemTributavel((GIAITCDSeparacaoDivorcioVo)conjugeBemTributavelVo.getGiaITCDVo(), conjuge);
			         if (!Validador.isNumericoValido(conjuge.getPessoaConjuge().getNumrContribuinte().longValue()))
			         {
			            conjuge.setPessoaConjuge(conjugeBemTributavelVo.getPessoaConjuge());
			            conjuge.setTipoConjuge(tipoConjuge);
			            conjuge.setGiaITCDVo(conjugeBemTributavelVo.getGiaITCDVo());
			         }
			         conjuge.setLogSefazVo(conjugeBemTributavelVo.getLogSefazVo());
			         incluirConjugeBemTributavel(conjuge);
			      }           
			   }				
			}
		   catch(ParametroObrigatorioException e)
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
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}		
	}

	/**
	 * Método responsável por alterar um Cônjuge Bem Tributável no alterar de uma GIA ITCD Separação.
	 * @param conjugeBemTributavelVo
	 * @param tipoConjuge
	 * @throws ObjetoObrigatorioException
	 * @throws ConexaoException
	 * @throws ConsultaException
	 * @throws ParametroObrigatorioException
	 * @throws IntegracaoException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public void alterarConjugeBemTributavelAlterarGIASeparacaoDivorcio(final ConjugeBemTributavelVo conjugeBemTributavelVo, final DomnTipoConjuge tipoConjuge) throws ObjetoObrigatorioException, 
			  ConexaoException, ConsultaException, ParametroObrigatorioException, IntegracaoException, LogException, 
			  PersistenciaException, AnotacaoException, IOException
   {
		Validador.validaObjeto(conjugeBemTributavelVo);
		Validador.validaObjeto(tipoConjuge);
		try
		{
			try
			{
				synchronized(ConjugeBemTributavelVo.class)
				{
					conjugeBemTributavelVo.setTipoConjuge(tipoConjuge);
					prepararConjugeBemTributavelParaAlterar(conjugeBemTributavelVo);
					ConjugeBemTributavelVo conjugeBemTributavelParametroVo = new ConjugeBemTributavelVo();
				   conjugeBemTributavelParametroVo.getGiaITCDVo().setCodigo(conjugeBemTributavelVo.getGiaITCDVo().getCodigo());
				   conjugeBemTributavelParametroVo.setTipoConjuge(tipoConjuge);
				   ConjugeBemTributavelVo conjugeBemTributavelConsultaVo = new ConjugeBemTributavelVo(conjugeBemTributavelParametroVo);
				   conjugeBemTributavelConsultaVo.setConsultaCompleta(false);
					listarConjugeBemTributavel(conjugeBemTributavelConsultaVo);
					Collection conjugeOriginal = conjugeBemTributavelConsultaVo.getCollVO();
					Collection conjugeIncluir = new ArrayList();
					Collection conjugeAlterar = new ArrayList();
					Collection conjugeExcluir = new ArrayList();
					
					conjugeIncluir = LogUtil.obterListaObjetosParaInclusao(conjugeOriginal, conjugeBemTributavelVo.getCollVO());
					if(Validador.isCollectionValida(conjugeIncluir))
					{
						for(Iterator it = conjugeIncluir.iterator(); it.hasNext(); )
						{
							ConjugeBemTributavelVo conjugeAtual = (ConjugeBemTributavelVo) it.next();
							conjugeAtual.setLogSefazVo(conjugeBemTributavelVo.getLogSefazVo());
							incluir(conjugeAtual);
						}
					}
					conjugeAlterar = LogUtil.obterListaObjetosParaAlteracao(conjugeOriginal, conjugeBemTributavelVo.getCollVO());
					if(Validador.isCollectionValida(conjugeAlterar))
					{
					   for(Iterator it = conjugeAlterar.iterator(); it.hasNext(); )
					   {
					      ConjugeBemTributavelVo conjugeAtual = (ConjugeBemTributavelVo) it.next();
					      conjugeAtual.setLogSefazVo(conjugeBemTributavelVo.getLogSefazVo());
							alterar(conjugeAtual);
					   }						
					}
					conjugeExcluir = LogUtil.obterListaObjetosParaExclusao(conjugeOriginal, conjugeBemTributavelVo.getCollVO());
					if(Validador.isCollectionValida(conjugeExcluir))
					{
					   for(Iterator it = conjugeExcluir.iterator(); it.hasNext(); )
					   {
					      ConjugeBemTributavelVo conjugeAtual = (ConjugeBemTributavelVo) it.next();
					      conjugeAtual.setLogSefazVo(conjugeBemTributavelVo.getLogSefazVo());
							excluir(conjugeAtual);
					   }                 						
					}
				}
			}
			catch(ConsultaException e)
			{
				conn.rollback();
				throw e;
			}
			catch(ParametroObrigatorioException e)
			{
				conn.rollback();
				throw e;
			}
			catch(IntegracaoException e)
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
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}
	}

	/**
	 * Método responsável por preparar um Cônjuge Bem Tributável para alterar.
	 * @param conjugeBemTributavelVo
	 * @throws ObjetoObrigatorioException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private void prepararConjugeBemTributavelParaAlterar(final ConjugeBemTributavelVo conjugeBemTributavelVo) throws ObjetoObrigatorioException
	{
		Validador.validaObjeto(conjugeBemTributavelVo);
		
		if(Validador.isCollectionValida(conjugeBemTributavelVo.getCollVO()))
		{
			for(Iterator it = conjugeBemTributavelVo.getCollVO().iterator(); it.hasNext(); )
			{
				ConjugeBemTributavelVo conjugeAtual = (ConjugeBemTributavelVo) it.next();
			   confirmaCodigoBemTributavel((GIAITCDSeparacaoDivorcioVo)conjugeBemTributavelVo.getGiaITCDVo(), conjugeAtual);
				conjugeAtual.setTipoConjuge(conjugeBemTributavelVo.getTipoConjuge());
				conjugeAtual.setPessoaConjuge(conjugeBemTributavelVo.getPessoaConjuge());
				conjugeAtual.setLogSefazVo(conjugeBemTributavelVo.getLogSefazVo());
				conjugeAtual.setGiaITCDVo(conjugeBemTributavelVo.getGiaITCDVo());				
			}
		}
	}
}
