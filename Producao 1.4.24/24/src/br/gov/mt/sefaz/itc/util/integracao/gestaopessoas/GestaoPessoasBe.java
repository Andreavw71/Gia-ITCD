package br.gov.mt.sefaz.itc.util.integracao.gestaopessoas;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.IntegracaoErro;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.util.facade.ConfiguracaoITCD;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.CadastroBe;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.MunicipioIntegracaoVo;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import sefaz.mt.cadastro.integracao.IntegracaoCadastro;
import sefaz.mt.cadastro.integracao.MunicipioVO;
import sefaz.mt.gestaopessoas.integracao.IntegracaoGestaoPessoas;
import sefaz.mt.gestaopessoas.integracao.ServidorSefazVO;
import sefaz.mt.gestaopessoas.integracao.UnidadeSefazVO;


/**
 * Classe responsável por fazer a integração com o sistema de Gestï¿½o de Pessoas.
 * @author Rogério Sanches de Oliveira
 * @version $Revision: 1.6 $
 */
public class GestaoPessoasBe extends AbstractBe
{
	public GestaoPessoasBe() throws SQLException
	{
		super();
	}

	public GestaoPessoasBe(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Método de integração com o sistema de gestï¿½o de pessoas. busca um servidor da sefaz
	 * pelo seu número de matrï¿½cula.
	 * 
	 * @param servidorSefaz			objeto do tipo <code>ServidorSefazIntegracaoVo</code> que encapsula
	 * 									os dados do servidor desejado. Atributo obrigatório: <code>numrMatricula</code>
	 * @return							servidor sefaz desejado de acordo com o número de matrícula informado no
	 * 									parametro servidorSefaz.
	 * @throws IntegracaoException
	 * @implemented by Leandro Dorileo
	 */
	public ServidorSefazIntegracaoVo buscarServidorSefazPorNumrMatricula(ServidorSefazIntegracaoVo servidorSefaz) throws IntegracaoException, 
			  ParametroObrigatorioException
	{
		IntegracaoGestaoPessoas objIntegracao = null;
		if (servidorSefaz == null || !Validador.isNumericoValido(servidorSefaz.getNumrMatricula()))
			throw new ParametroObrigatorioException("O número de matrícula deve ser informado e válido.");
		try
		{
			if(!servidorSefaz.getNumrMatricula().equals(new Long(ConfiguracaoITCD.CODIGO_USUARIO)))
			{
            //TODO Não utiliza conexão do ITCD.
			   objIntegracao = new IntegracaoGestaoPessoas(conn);
			   ServidorSefazVO servidor = new ServidorSefazVO();
			   servidor.setNumrMatricula(servidorSefaz.getNumrMatricula());
            /** método alterado de buscarServidorSefazPorNumrMatricula para buscarServidorSefazPorNumrMatriculaTAD
             por ser desnecessário o endereço, além também ocorrer o erro de estouro de cursor.
			   */
            ServidorSefazVO servidorPesquisado = objIntegracao.buscarServidorSefazPorNumrMatriculaTAD(servidor.getNumrMatricula().intValue());
			   if (Validador.isObjetoValido(servidorPesquisado) && Validador.isNumericoValido(servidorPesquisado.getNumrMatricula()))
			   {
			      return new ServidorSefazIntegracaoVo(servidorPesquisado);
			   }
			   else
			   {
			      throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_AVALIACAO_MATRICULA);
			   }				
			}
			else
			{
				ServidorSefazIntegracaoVo retorno = new ServidorSefazIntegracaoVo();
				retorno.setNumrMatricula(new Long(ConfiguracaoITCD.CODIGO_USUARIO));
				retorno.setNomeServidor(ConfiguracaoITCD.NOME_USUARIO);
				return retorno;
			}
		}
		catch (ParametroObrigatorioException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_GESTAO_PESSOAS, e);
		}
		catch (Error e)
		{
			throw IntegracaoErro.getInstance(IntegracaoErro.OPERACAO_CONSULTAR, IntegracaoErro.NOME_SISTEMA_INTEGRACAO_GESTAO_PESSOAS, e);
		}
		finally
		{
			if (this.conn != null)
			{
				this.close();
			}
		}
	}

	/**
	 * Método de integração com o sistema de gestï¿½o de pessoas. obtï¿½m uma unidade
	 * fazendï¿½ria(agenfa/agencia fazendï¿½ria) de acordo com o mï¿½nicï¿½pio informado.
	 * 
	 * @param codgMunicipio			codigo do mï¿½nicipio
	 * @return
	 * @throws IntegracaoException
	 * @implemented by Leandro Dorileo
	 */
	public UnidadeSefazIntegracaoVo buscarAgenfaPorCodgMunicipio(int codgMunicipio) throws IntegracaoException, 
			  ParametroObrigatorioException
	{
		IntegracaoGestaoPessoas objIntegracao = new IntegracaoGestaoPessoas(conn);
		UnidadeSefazIntegracaoVo retorno = new UnidadeSefazIntegracaoVo();
		try
		{
			UnidadeSefazIntegracaoVo consulta = new UnidadeSefazIntegracaoVo(objIntegracao.listUnidSefazAtivaPorCodgMunicipio(codgMunicipio));
			for(Iterator it = consulta.getCollVO().iterator(); it.hasNext(); )
			{
				UnidadeSefazIntegracaoVo atual = (UnidadeSefazIntegracaoVo) it.next();
				if(atual.getTipoUnidade().getCodgTipoUnidade().equals(ConfiguracaoITCD.CODIGO_TIPO_UNIDADE_SEFAZ_AGENFA))
				{
					retorno = atual;
					break;
				}
			}
			return retorno;
		}
		catch (Exception e)
		{
			throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_GESTAO_PESSOAS, e);
		}
		catch (Error e)
		{
			throw IntegracaoErro.getInstance(IntegracaoErro.OPERACAO_CONSULTAR, IntegracaoErro.NOME_SISTEMA_INTEGRACAO_GESTAO_PESSOAS, e);
		}
		finally
		{
			if (this.conn != null)
				this.close();
		}
	}

	/**
	 * Método responsável por listar os tipos de unidades sefaz atravï¿½s do código informado.
	 * @param tipoUnidadeSefazIntegracaoVo
	 * @throws SQLException
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @throws IntegracaoException
	 * @return TipoUnidadeSefazVO - Tipo de Unidade Sefaz encontrado
	 * @implemented by Rogério Sanches de Oliveira
	 * 
	 */
	public TipoUnidadeSefazIntegracaoVo buscarTipoUnidadeSefazPorCodg(TipoUnidadeSefazIntegracaoVo tipoUnidadeSefazIntegracaoVo) throws SQLException, 
			  ObjetoObrigatorioException, ParametroObrigatorioException, IntegracaoException
	{
		final String CODIGO_TIPO_UNIDADE_SEFAZ_OBRIGATORIO = "O código do tipo da Unidade Sefaz ï¿½ obrigatório.";
		Validador.validaObjeto(tipoUnidadeSefazIntegracaoVo);
		try
		{
			if (tipoUnidadeSefazIntegracaoVo.isConsultaParametrizada())
			{
				if (Validador.isNumericoValido(tipoUnidadeSefazIntegracaoVo.getParametroConsulta().getCodgTipoUnidade()))
				{
					IntegracaoGestaoPessoas integracaoGestaoPessoas = new IntegracaoGestaoPessoas(conn);
					return new TipoUnidadeSefazIntegracaoVo(integracaoGestaoPessoas.buscarTipoUnidadeSefazPorCodg(tipoUnidadeSefazIntegracaoVo.getParametroConsulta().getCodgTipoUnidade().intValue()));
				}
				else
				{
					throw new ParametroObrigatorioException(CODIGO_TIPO_UNIDADE_SEFAZ_OBRIGATORIO);
				}
			}
			else
			{
				throw new ParametroObrigatorioException(CODIGO_TIPO_UNIDADE_SEFAZ_OBRIGATORIO);
			}
		}
		catch (Exception e)
		{
			throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_GESTAO_PESSOAS, e);
		}
		catch (Error e)
		{
			throw IntegracaoErro.getInstance(IntegracaoErro.OPERACAO_CONSULTAR, IntegracaoErro.NOME_SISTEMA_INTEGRACAO_GESTAO_PESSOAS, e);
		}
		finally
		{
			if (this.conn != null)
				this.close();
		}
	}

	/**
	 * Método responsável por listar as unidades sefaz atravï¿½s do código informado.
	 * 
	 * @param unidadeSefazIntegracaoVo
	 * @throws Exception
	 * @return UnidadeSefazVO - Unidades Sefaz encontradas
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public UnidadeSefazIntegracaoVo buscarUnidadeSefazPorCodigo(UnidadeSefazIntegracaoVo unidadeSefazIntegracaoVo) throws ObjetoObrigatorioException, 
			  IntegracaoException
	{
		final String CODIGO_UNIDADE_SEFAZ_OBRIGATORIO = "O código da Unidade Sefaz ï¿½ obrigatório.";
		Validador.validaObjeto(unidadeSefazIntegracaoVo);
		try
		{
			if (unidadeSefazIntegracaoVo.isConsultaParametrizada())
			{
				if (Validador.isNumericoValido(unidadeSefazIntegracaoVo.getParametroConsulta().getCodgUnidade()))
				{
					IntegracaoGestaoPessoas integracaoGestaoPessoas = new IntegracaoGestaoPessoas(conn);
					return new UnidadeSefazIntegracaoVo(integracaoGestaoPessoas.buscarUnidadeSefazPorCodigo(((UnidadeSefazIntegracaoVo)unidadeSefazIntegracaoVo.getParametroConsulta()).toUnidadeSefazVO()));
				}
				else
				{
					throw new ParametroObrigatorioException(CODIGO_UNIDADE_SEFAZ_OBRIGATORIO);
				}
			}
			else
			{
				throw new ParametroObrigatorioException(CODIGO_UNIDADE_SEFAZ_OBRIGATORIO);
			}
		}
		catch (Exception e)
		{
			throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_GESTAO_PESSOAS, e);
		}
		catch (Error e)
		{
			throw IntegracaoErro.getInstance(IntegracaoErro.OPERACAO_CONSULTAR, IntegracaoErro.NOME_SISTEMA_INTEGRACAO_GESTAO_PESSOAS, e);
		}
		finally
		{
			if (this.conn != null)
				this.close();
		}
	}

	/**
	 * Método responsável por listar todas os tipos de Unidades Sefaz Ativos. 
	 * 
	 * @throws SQLException
	 * @throws ParametroObrigatorioException
	 * @return TipoUnidadeSefazIntegracaoVo - que possui uma Collection de TipoUnidadeSefazIntegracaoVo
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public TipoUnidadeSefazIntegracaoVo listarTipoUnidadeSefazAtivo() throws SQLException, ParametroObrigatorioException, 
			  IntegracaoException
	{
		try
		{
			IntegracaoGestaoPessoas integracaoGestaoPessoas = new IntegracaoGestaoPessoas(conn);
			return new TipoUnidadeSefazIntegracaoVo(integracaoGestaoPessoas.listarTipoUnidadeSefazAtivo());
		}
		catch (Exception e)
		{
			throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_GESTAO_PESSOAS, e);
		}
		catch (Error e)
		{
			throw IntegracaoErro.getInstance(IntegracaoErro.OPERACAO_CONSULTAR, IntegracaoErro.NOME_SISTEMA_INTEGRACAO_GESTAO_PESSOAS, e);
		}
		finally
		{
			if (this.conn != null)
				this.close();
		}
	}

	/**
	 * Método responsável por listar todas as Unidades Sefaz de acordo com o Tipo da Unidade informado
	 * 
	 * @param unidadeSefazIntegracaoVo
	 * @throws SQLException
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @throws IntegracaoException
	 * @return UnidadeSefazIntegracaoVo - Unidades Sefaz encontradas
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public UnidadeSefazIntegracaoVo listUnidSefazAtivaPorCodgTipoUnid(UnidadeSefazIntegracaoVo unidadeSefazIntegracaoVo) throws ObjetoObrigatorioException, 
			  IntegracaoException
	{
		final String CODIGO_TIPO_UNIDADE_SEFAZ_OBRIGATORIO = "O código do Tipo Unidade Sefaz é obrigatório.";
		Validador.validaObjeto(unidadeSefazIntegracaoVo);
		try
		{
			if (unidadeSefazIntegracaoVo.isConsultaParametrizada())
			{
				if (unidadeSefazIntegracaoVo.getParametroConsulta().getTipoUnidade() != null && 
							  Validador.isNumericoValido(unidadeSefazIntegracaoVo.getParametroConsulta().getTipoUnidade().getCodgTipoUnidade()))
				{
					IntegracaoGestaoPessoas integracaoGestaoPessoas = new IntegracaoGestaoPessoas(conn);
					return new UnidadeSefazIntegracaoVo(integracaoGestaoPessoas.listUnidSefazAtivaPorCodgTipoUnid(unidadeSefazIntegracaoVo.getParametroConsulta().getTipoUnidade().getCodgTipoUnidade().longValue()));
				}
				else
				{
					throw new ParametroObrigatorioException(CODIGO_TIPO_UNIDADE_SEFAZ_OBRIGATORIO);
				}
			}
			else
			{
				throw new ParametroObrigatorioException(CODIGO_TIPO_UNIDADE_SEFAZ_OBRIGATORIO);
			}
		}
		catch (Exception e)
		{
			throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_GESTAO_PESSOAS, e);
		}
		catch (Error e)
		{
			throw IntegracaoErro.getInstance(IntegracaoErro.OPERACAO_CONSULTAR, IntegracaoErro.NOME_SISTEMA_INTEGRACAO_GESTAO_PESSOAS, e);
		}
		finally
		{
			if (this.conn != null)
				this.close();
		}
	}

	/**
	 * Método responsável por listar todas as Unidades Sefaz de acordo com o Tipo da Unidade informado
	 * 
	 * @param unidadeSefazIntegracaoVo
	 * @throws SQLException
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @throws IntegracaoException
	 * @return UnidadeSefazIntegracaoVo - Unidades Sefaz encontradas
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public UnidadeSefazIntegracaoVo listUnidSefazAtivaPorSiglUnidSuperior(UnidadeSefazIntegracaoVo unidadeSefazIntegracaoVo) throws SQLException, 
			  ObjetoObrigatorioException, ParametroObrigatorioException, IntegracaoException
	{
		final String SIGLA_UNIDADE_SEFAZ_OBRIGATORIO = "A sigla da Unidade é obrigatório.";
		Validador.validaObjeto(unidadeSefazIntegracaoVo);
		try
		{
			if (unidadeSefazIntegracaoVo.isConsultaParametrizada())
			{
				if (Validador.isStringValida(unidadeSefazIntegracaoVo.getParametroConsulta().getSiglaUnidade()))
				{
					IntegracaoGestaoPessoas integracaoGestaoPessoas = new IntegracaoGestaoPessoas(conn);
					return new UnidadeSefazIntegracaoVo(integracaoGestaoPessoas.buscarUnidadeSefazPorSiglaUnidSefazSuperior(unidadeSefazIntegracaoVo.getParametroConsulta().getSiglaUnidade()));
				}
				else
				{
					throw new ParametroObrigatorioException(SIGLA_UNIDADE_SEFAZ_OBRIGATORIO);
				}
			}
			else
			{
				throw new ParametroObrigatorioException(SIGLA_UNIDADE_SEFAZ_OBRIGATORIO);
			}
		}
		catch (Exception e)
		{
			throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_GESTAO_PESSOAS, e);
		}
		catch (Error e)
		{
			throw IntegracaoErro.getInstance(IntegracaoErro.OPERACAO_CONSULTAR, IntegracaoErro.NOME_SISTEMA_INTEGRACAO_GESTAO_PESSOAS, e);
		}
		finally
		{
			if (this.conn != null)
				this.close();
		}
	}	

	/**
	 * Lista todas as Unidades Fazendárias ativas cadastracas no sistema de gestão de pessoas.
	 * Encontra também os dados do município de cada Unidade Sefaz utilizando o método <b>obterMunicipioPorCodigo</b>
	 * de integração do sistema de cadastro. O código informado é aquele retornado em cada VO de Unidade Fazendária.
	 *
	 * @return				lista de todas as unidades fazendárias ativas
	 * @throws Exception
	 * @implemented by Leandro Dorileo
	 */
	public Collection listarUnidadesSefazAtivas() throws IntegracaoException
	{
	   Collection retorno = new ArrayList();
		try
		{		   
		   Collection listaUnidadeSefaz = (new IntegracaoGestaoPessoas(conn)).listUnidSefazAtivaPorCodgTipoUnid(1);

		   for (Iterator it = listaUnidadeSefaz.iterator(); it.hasNext(); )
		   {

		      UnidadeSefazVO atual = (UnidadeSefazVO)it.next();
		      retorno = (retorno == null) ? new ArrayList() : retorno;
		      UnidadeSefazIntegracaoVo unidadeSefazIntegracaoVo = new UnidadeSefazIntegracaoVo(atual);

		      if (atual.getCodgMunicipio() != null)
		      {
		         MunicipioVO municipio = new IntegracaoCadastro(conn).obterMunicipioPorCodigo(atual.getCodgMunicipio());
		         MunicipioIntegracaoVo municipioIntegracao = new MunicipioIntegracaoVo(municipio);
		         unidadeSefazIntegracaoVo.setMunicipio(municipioIntegracao);
		      }
		      else
		      {
		         unidadeSefazIntegracaoVo.setMunicipio(new MunicipioIntegracaoVo());
		      }

		      retorno.add(unidadeSefazIntegracaoVo);
		   }
		   return retorno;			
		}
	   catch (Exception e)
	   {
	      throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_GESTAO_PESSOAS, e);
	   }
	   catch (Error e)
	   {
	      throw IntegracaoErro.getInstance(IntegracaoErro.OPERACAO_CONSULTAR, IntegracaoErro.NOME_SISTEMA_INTEGRACAO_GESTAO_PESSOAS, e);
	   }		
	}

	/**
	 * @return
	 * @throws IntegracaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public UnidadeSefazIntegracaoVo listarUnidadesSefaz() throws IntegracaoException
	{
	   UnidadeSefazIntegracaoVo retorno = new UnidadeSefazIntegracaoVo();
		try
		{
		   Collection listaUnidadeSefaz = new IntegracaoGestaoPessoas(conn).listarUnidadesSefaz();

		   for (Iterator it = listaUnidadeSefaz.iterator(); it.hasNext(); )
		   {

		      UnidadeSefazVO atual = (UnidadeSefazVO)it.next();
		      UnidadeSefazIntegracaoVo unidadeSefazIntegracaoVo = new UnidadeSefazIntegracaoVo(atual);
		      if (atual.getCodgMunicipio() != null)
		      {
		         MunicipioVO municipio = new IntegracaoCadastro(conn).obterMunicipioPorCodigo(atual.getCodgMunicipio());
		         MunicipioIntegracaoVo municipioIntegracao = new MunicipioIntegracaoVo(municipio);
		         unidadeSefazIntegracaoVo.setMunicipio(municipioIntegracao);
		      }
		      else
		      {
		         unidadeSefazIntegracaoVo.setMunicipio(new MunicipioIntegracaoVo());
		      }

		      retorno.getCollVO().add(unidadeSefazIntegracaoVo);
		   }
		   return retorno;			
		}
	   catch (Exception e)
	   {
	      throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_GESTAO_PESSOAS, e);
	   }
	   catch (Error e)
	   {
	      throw IntegracaoErro.getInstance(IntegracaoErro.OPERACAO_CONSULTAR, IntegracaoErro.NOME_SISTEMA_INTEGRACAO_GESTAO_PESSOAS, e);
	   }
		
	}	


	/**
	 * Este método utiliza o método <b>listaAgenfa</b> para obter as agencias fazendárias.
	 * De fato ele consulta as Unidades Fazendárias cujo o tipo de unidade seja 1.
	 * 
	 * @return lista de unidades fazendárias encontradas de acorco com os critérios especificados
	 * @throws IntegracaoException
	 * @implemented by Leandro Dorileo
	 */
	public Collection listarAgenciaFazendaria() throws IntegracaoException
	{
		try
		{
			Collection listaAgencia = (new IntegracaoGestaoPessoas(conn)).listUnidSefazAtivaPorCodgTipoUnid(1);
			Collection retorno = new ArrayList();
			CadastroBe cadastroBe = new CadastroBe(conn);
			for (Iterator it = listaAgencia.iterator(); it.hasNext(); )
			{
				UnidadeSefazIntegracaoVo atual = new UnidadeSefazIntegracaoVo((UnidadeSefazVO) it.next());
				MunicipioIntegracaoVo municipio = new MunicipioIntegracaoVo();
				municipio.setCodgMunicipio(atual.getCodgMunicipio());
				atual.setMunicipio(cadastroBe.obterMunicipioPorCodigo(municipio));
				retorno.add(atual);
			}
			return retorno;
		}
		catch (SQLException e)
		{
			throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_GESTAO_PESSOAS, e);
		}
		catch (ParametroObrigatorioException e)
		{
			throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_CADASTRO, e);
		}
		catch (ObjetoObrigatorioException e)
		{
			throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_CADASTRO, e);
		}
	}
	
	public static void main(String[] args)
	{
		GestaoPessoasBe gestao = null;
		try
		{
			gestao = new GestaoPessoasBe();
			ServidorSefazIntegracaoVo servidor = gestao.buscarServidorSefazPorNumrMatricula(999999997);
			System.out.println(servidor.getDadosServidorFormatado());

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(gestao!= null)
			{
				gestao.close();
				gestao = null;
			}
		}
	}

	public UnidadeSefazIntegracaoVo buscarUnidadeSefazPorCodigo(int codigoUnidade) throws IntegracaoException
	{
		if(Validador.isNumericoValido(codigoUnidade))
		{
		   try
		   {
		      UnidadeSefazIntegracaoVo consulta = new UnidadeSefazIntegracaoVo();
		      UnidadeSefazIntegracaoVo parametro = new UnidadeSefazIntegracaoVo();
		      parametro.setCodgUnidade(new Integer(codigoUnidade));
		      consulta.setParametroConsulta(parametro);
		      return buscarUnidadeSefazPorCodigo(consulta);         
		   }
		   catch(Exception e)
		   {
		      throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_GESTAO_PESSOAS, e);
		   }			
		}
		return null;
	}

	public ServidorSefazIntegracaoVo buscarServidorSefazPorNumrMatricula(long numeroMatricula) throws IntegracaoException
	{
		try
		{
			ServidorSefazIntegracaoVo consulta = new ServidorSefazIntegracaoVo();
			consulta.setNumrMatricula(new Long(numeroMatricula));
			return buscarServidorSefazPorNumrMatricula(consulta);
		}
		catch (Exception e)
		{
		   throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_GESTAO_PESSOAS, e);
		}
	}
}
