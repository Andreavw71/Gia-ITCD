/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: BemBe.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 08/10/2007
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.bem;

import br.com.abaco.log5.GenericoLogAnotacaoDao;
import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.RegistroExistenteException;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProtocolo;
import br.gov.mt.sefaz.itc.util.facade.ConfiguracaoITCD;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.MensagemSucesso;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.Date;


/**
 * Classe responsável por implementar a regra de negócio da entidade "Bem".
 * @author Marlo Eichenberg Motta
 * @version $Revision: 1.2 $
 */
public class BemBe extends AbstractBe
{
	/**
	 * Construtor da classe.
	 * Este construtor não abre uma conexão com o banco de dados, mas utiliza a conexão recebida como parâmetro para realizar suas validações.
	 * @param conexao objeto de conexão com o banco de dados.
	 * @implemented by Daniel Balieiro
	 * @implemented by Marlo Eichenberg Motta
	 */
	public BemBe(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Construtor padrão da classe.
	 * Este construtor abre uma conexão com o banco de dados do ITCD para realizar suas validações.
	 * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
	 * @implemented by Daniel Balieiro
	 * @implemented by Marlo Eichenberg Motta
	 */
	public BemBe() throws SQLException
	{
		super();
	}

   /* ---------------------------------------------------------------------------------------------
    *                                Métodos Utilitarios DistanciaBe
    * --------------------------------------------------------------------------------------------- */
    
   /**
    * <b>Objetivo:</b>este método tem por objetivo verificar se o BemVo informado
    * passado como parametro contém o mesmo dominio informado como parametro.
    * <br><br>
    * <b><h1>Atenção:</h1></b> este método NÃO verifica dentro do objeto CollVO.
    * 
    * @param bemVo
    * @param domnTipoProtocolo
    * @return true se e somente se o bemVo contiver o dominio informado.
    */
   public static boolean isBemVoTipoProtocolo(final BemVo bemVo, DomnTipoProtocolo domnTipoProtocolo)
   {
      if(Validador.isObjetoValido(bemVo) && Validador.isDominioNumericoValido( domnTipoProtocolo ))
      {
         if(bemVo.getTipoProtocoloGIA().is(domnTipoProtocolo.getValorCorrente()) )
         {
            return true;
         }
      }
      return false;
   }

	/**
	 * Inclui informações sobre um Bem no banco de dados.
	 * Este método é o responsável por tratar das regras de negócio da inclusão de um Bem.
	 * @param bemVo Bem (Value Object).
	 * @throws ObjetoObrigatorioException
	 * @throws RegistroExistenteException
	 * @throws ParametroObrigatorioException
	 * @throws ConsultaException
	 * @throws ConexaoException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void incluirBem(final BemVo bemVo) throws ObjetoObrigatorioException, RegistroExistenteException, 
			  ParametroObrigatorioException, ConsultaException, ConexaoException, LogException, AnotacaoException, 
			  PersistenciaException
	{
		Validador.validaObjeto(bemVo);
		try
		{
			try
			{
				synchronized (BemVo.class)
				{
					validaParametrosIncluirBem(bemVo);
					BemVo bemConsultaVo = new BemVo();
					bemConsultaVo.setDescricaoTipoBem(bemVo.getDescricaoTipoBem());
					bemConsultaVo.setClassificacaoTipoBem(bemVo.getClassificacaoTipoBem());
					bemConsultaVo = new BemVo(bemConsultaVo);
					consultarBem(bemConsultaVo);
					if (bemConsultaVo.isExiste())
					{
						throw new RegistroExistenteException(MensagemErro.VALIDAR_BEM_INCLUIR_DESCRICAO_E_TIPO);
					}
					else
					{
						bemVo.setStatusBem(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
						bemVo.setDataAtualizacao(new Date());
						incluir(bemVo);
						commit();
						bemVo.setMensagem(MensagemSucesso.INCLUIR);
					}
				}
			}
			catch (RegistroExistenteException e)
			{
				conn.rollback();
				throw e;
			}
			catch (ConsultaException e)
			{
				conn.rollback();
				throw e;
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
			catch(AnotacaoException e)
			{
				conn.rollback();
				throw e;				
			}
			catch(PersistenciaException e)
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
	 * @param bemVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void incluir(final BemVo bemVo) throws ObjetoObrigatorioException, LogException, 
			  AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(bemVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).insert(bemVo);
	}

	/**
	 * Método utilizado para validar se o objeto do tipo BemVo possui todos os parâmetros obrigatórios.
	 * @param bemVo Bem (Value Object).
	 * @throws ObjetoObrigatorioException Esta Exception deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ParametroObrigatorioException Esta Exception deve ser lançada quando o usuário não informa um campo que é obrigatório para o sistema.
	 * @implemented by Daniel Balieiro
	 */
	private void validaParametrosIncluirBem(final BemVo bemVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException
	{
		Validador.validaObjeto(bemVo);
		if (!Validador.isDominioNumericoValido(bemVo.getClassificacaoTipoBem()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_BEM_PARAMETRO_TIPO);
		}
		if (!Validador.isStringValida(bemVo.getDescricaoTipoBem()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_BEM_PARAMETRO_DESCRICAO);
		}
		if(bemVo.getDescricaoTipoBem().trim().equals(""))
		{
		   throw new ParametroObrigatorioException(MensagemErro.VALIDAR_BEM_PARAMETRO_DESCRICAO);
		}
		if(!Validador.isDominioNumericoValido(bemVo.getPossuiConstrucao()))
		{
			throw new ParametroObrigatorioException(MensagemErro.FLAG_POSSUI_CONTRUCAO_DEVE_SER_INFORMADO);
		}
	   if (!Validador.isDominioNumericoValido(bemVo.getTipoVerificacao()))
	   {
	      throw new ParametroObrigatorioException(MensagemErro.VALIDAR_BEM_PARAMETRO_TIPO_VERIFICACAO);
	   }
	   if (!Validador.isDominioNumericoValido(bemVo.getTipoProtocoloGIA()))
	   {
	      throw new ParametroObrigatorioException(MensagemErro.VALIDAR_BEM_PARAMETRO_TIPO_PROTOCOLO);
	   }
	}

	/**
	 * Método usado para validar se todos os atributos necessários para a alteração de um Bem, foram passados.
	 * @param bemVo Bem (Value Object).
	 * @throws ObjetoObrigatorioException Esta Exception deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ParametroObrigatorioException Esta Exception deve ser lançada quando o usuário não informa um campo que é obrigatório para o sistema.
	 * @implemented by Daniel Balieiro
	 */
	private void validaParametrosAlterarBem(final BemVo bemVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException
	{
		Validador.validaObjeto(bemVo);
		validaParametrosIncluirBem(bemVo);
	}

	/**
	 * Método que certifica se é permitido alterar determinado BemVo
	 * @param bemVo Bem (Value Object).
	 * @param bemConsultaVo BemVo encontrado no Banco de Dados
	 * @return true
	 * @implemented by Daniel Balieiro
	 */
	private boolean permiteAlterar(final BemVo bemVo, final BemVo bemConsultaVo)
	{
		if (bemConsultaVo.isExiste() && Validador.isNumericoValido(bemConsultaVo.getCodigo()))
		{
			return bemVo.getCodigo() == bemConsultaVo.getCodigo();
		}
		return true;
	}

	/**
	 * Altera informações sobre um Bem no banco de dados.
	 * @param bemVo Bem (Value Object).
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @throws RegistroExistenteException
	 * @throws ConsultaException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @throws ConexaoException
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public synchronized void alterarBem(final BemVo bemVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException, RegistroExistenteException, ConsultaException, LogException, 
			  PersistenciaException, 
			  AnotacaoException, ConexaoException
	{
		Validador.validaObjeto(bemVo);
		validaParametrosAlterarBem(bemVo);
		try
		{
			try
			{
				synchronized (BemVo.class)
				{
					BemVo bemConsultaVo = new BemVo();
					bemConsultaVo.setDescricaoTipoBem(bemVo.getDescricaoTipoBem());
					bemConsultaVo.setClassificacaoTipoBem(bemVo.getClassificacaoTipoBem());
					bemConsultaVo = new BemVo(bemConsultaVo);
					consultarBem(bemConsultaVo);
					if (permiteAlterar(bemVo, bemConsultaVo))
					{
						bemVo.setDataAtualizacao(new Date());
						alterar(bemVo);
						commit();
						bemVo.setMensagem(MensagemSucesso.ALTERAR);
					}
					else
					{
						throw new RegistroExistenteException(MensagemErro.VALIDAR_BEM_INCLUIR_DESCRICAO_E_TIPO);
					}
				}
			}
			catch (ObjetoObrigatorioException e)
			{
				conn.rollback();
				throw e;
			}
			catch(RegistroExistenteException e)
			{
				conn.rollback();
				throw e;
			}
			catch(ConsultaException e)
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
	 * @param bemVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void alterar(final BemVo bemVo) throws ObjetoObrigatorioException, LogException, 
			  AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(bemVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).update(bemVo);
	}

	/**
	 * Consulta informações sobre um Bem no banco de dados.
	 * @param bemVo Bem (Value Object).
	 * @throws ConsultaException Esta Exception deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @throws ObjetoObrigatorioException Esta Exception deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	public BemVo consultarBem(final BemVo bemVo) throws ConsultaException, ObjetoObrigatorioException
	{
		Validador.validaObjeto(bemVo);
		(new BemQDao(conn)).findBem(bemVo);
		return bemVo;
	}

	/**
	 * Lista informações sobre um ou mais Bens no banco de dados.
	 * @param bemVo Bem (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	public BemVo listarBem(final BemVo bemVo) throws ObjetoObrigatorioException, ConsultaException
	{
		Validador.validaObjeto(bemVo);
		(new BemQDao(conn)).listBem(bemVo);
		Validador.validaObjeto(bemVo);
		return bemVo;
	}

	/**
	 * @param bemVo
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @throws RegistroExistenteException
	 * @throws ConsultaException
	 * @throws LogException
	 * @throws PersistenciaException
	 * @throws AnotacaoException
	 * @throws ConexaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public void reativarBem(final BemVo bemVo) throws ObjetoObrigatorioException, ParametroObrigatorioException, 
			  RegistroExistenteException, ConsultaException, LogException, PersistenciaException, AnotacaoException, 
			  ConexaoException
	{
		Validador.validaObjeto(bemVo);
		bemVo.setStatusBem(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
		alterarBem(bemVo);
	}
}
