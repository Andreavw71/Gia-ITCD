/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: CadastroBe.java
 * Revisão: Leandro Dorileo
 * Data revisão: 17/03/2008
 * $Id: CadastroBe.java,v 1.8 2009/03/23 14:30:18 ricardo.moraes Exp $
 */
package br.gov.mt.sefaz.itc.util.integracao.cadastro;

import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.IntegracaoErro;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoPess;
import br.gov.mt.sefaz.itc.util.facade.ConfiguracaoITCD;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import sefaz.mt.cadastro.integracao.ContribuinteVO;
import sefaz.mt.cadastro.integracao.EnderecoVO;
import sefaz.mt.cadastro.integracao.IntegracaoCadastro;
import sefaz.mt.cadastro.integracao.MunicipioVO;
import sefaz.mt.cadastro.integracao.UfVO;


/**
 * Classe responsável por fazer a integração com o sistema de cadastro.
 * @author Wanderlúcio Alves de Oliveira
 * @author Rogério Sanches de Oliveira
 * @version $Revision: 1.8 $
 */
public class CadastroBe extends AbstractBe
{
	public CadastroBe() throws SQLException
	{
		super();
	}

	/**
	 * Este construtor não abre uma conexão com o banco de dados, mas utiliza a conexão recebida como parâmetro
	 * para realizar suas validações.
	 * @param conexao
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public CadastroBe(Connection conexao)
	{
		super(conexao);
	}

	/**
	 * Método utilizado por buscar dados do contribuinte pelo número do contribuinte, que é obrigatório.
	 * @param contribuinteIntegracaoVo
	 * @throws IntegracaoException
	 * @throws ObjetoObrigatorioException
	 * @return ContribuinteIntegracaoVo - O contribuinte encontrado.
	 * @implemented by Fábio Vanzella
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public ContribuinteIntegracaoVo obterContribuinte(ContribuinteIntegracaoVo contribuinteIntegracaoVo) throws IntegracaoException, 
			  ObjetoObrigatorioException
	{
		final String NUMERO_CONTRIBUINTE_E_OBRIGATORIO = " O número do contribuinte é obrigatório.";
		EnderecoIntegracaoVo endereco = null;
		IntegracaoCadastro integracaoCadastro = null;
		Validador.validaObjeto(contribuinteIntegracaoVo);
		try
		{
			if (contribuinteIntegracaoVo.isConsultaParametrizada())
			{
				integracaoCadastro = new IntegracaoCadastro(conn);
				if (Validador.isNumericoValido(contribuinteIntegracaoVo.getParametroConsulta().getNumrContribuinte()))
				{
					ContribuinteIntegracaoVo contribuinte = 
									 new ContribuinteIntegracaoVo(integracaoCadastro.obterContribuinte(contribuinteIntegracaoVo.getParametroConsulta().getNumrContribuinte().longValue()));
					if (Validador.isNumericoValido(contribuinte.getCodgEndereco()))
					{
						endereco = new EnderecoIntegracaoVo();
						endereco.setCodgEndereco(contribuinte.getCodgEndereco());
						endereco = new EnderecoIntegracaoVo(endereco);
						contribuinte.atribuiEndereco(buscarEndereco(endereco));
					}
					if(Validador.isStringValida(contribuinte.getSiglaUF()) && contribuinte.getSiglaUF().equals(ConfiguracaoITCD.SIGLA_ESTADO_MATO_GROSSO))
					{
					   if(Validador.isNumericoValido(contribuinte.getEnderecoCEP()))
					   {
					      MunicipioIntegracaoVo municipio = obterMunicipioPorCep(new CepIntegracaoVo(new Integer(contribuinte.getEnderecoCEP().intValue())));
					      contribuinte.setCodgMunicipio(municipio.getCodgMunicipio());
					      contribuinte.setMunicipio(municipio.getNomeMunicipio());
					   }						
					}
					return contribuinte;
				}
				else
				{
					throw new ParametroObrigatorioException(NUMERO_CONTRIBUINTE_E_OBRIGATORIO);
				}
			}
			else
			{
				throw new ParametroObrigatorioException(NUMERO_CONTRIBUINTE_E_OBRIGATORIO);
			}
		}
		catch (Exception e)
		{
			throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_CADASTRO, e);
		}
		catch (Error e)
		{
			throw IntegracaoErro.getInstance(IntegracaoErro.OPERACAO_CONSULTAR, IntegracaoErro.NOME_SISTEMA_INTEGRACAO_CADASTRO, e);
		}finally
      { 
         if(integracaoCadastro != null)
         {
            integracaoCadastro = null;
         }
         
      }
	}

	/**
	 * Integração com o sistema de cadastro de contribuinte para consultar um município por seu código
	 * @param municipioIntegracaoVo encapsula o municipio a ser consultado
	 * @throws IntegracaoException
	 * @throws ObjetoObrigatorioException
	 * @return MunicipioIntegracaoVo
	 * @implemented by Fábio Vanzella
	 */
	public MunicipioIntegracaoVo obterMunicipioPorCodigo(MunicipioIntegracaoVo municipioIntegracaoVo) throws IntegracaoException, 
			  ObjetoObrigatorioException
	{
		final String CODIGO_MUNICIPIO_OBRIGATORIO = " O codigo do municipio e obrigatório.";
		MunicipioIntegracaoVo retorno = null;
		IntegracaoCadastro objIntegracao = null;
		Validador.validaObjeto(municipioIntegracaoVo);
		try
		{
			if (Validador.isNumericoValido(municipioIntegracaoVo.getCodgMunicipio()))
			{
				objIntegracao = new IntegracaoCadastro();
				retorno = 
        new MunicipioIntegracaoVo(objIntegracao.obterMunicipioPorCodigo(municipioIntegracaoVo.getCodgMunicipio()));
				return retorno;
			}
			else
			{
				throw new ParametroObrigatorioException(CODIGO_MUNICIPIO_OBRIGATORIO);
			}
		}
		catch (SQLException e)
		{
			throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_CADASTRO, e);
		}
		catch (Exception e)
		{
			throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_CADASTRO, e);
		}
		catch (Error e)
		{
			throw IntegracaoErro.getInstance(IntegracaoErro.OPERACAO_CONSULTAR, IntegracaoErro.NOME_SISTEMA_INTEGRACAO_CADASTRO, e);
		}
	   finally
      { 
         if(objIntegracao != null)
         {
            objIntegracao = null;
         }
         
      }
	}

	/**
	 * Integração com o sistema de cadastro de contribuinte para consultar um município por seu código
	 * @param cepIntegracaoVo encapsula o municipio a ser consultado
	 * @throws IntegracaoException
	 * @throws ObjetoObrigatorioException
	 * @return MunicipioIntegracaoVo
	 * @implemented by Fábio Vanzella
	 */
	public MunicipioIntegracaoVo obterMunicipioPorCep(CepIntegracaoVo cepIntegracaoVo) throws IntegracaoException, 
			  ObjetoObrigatorioException
	{
		final String CODIGO_CEP_OBRIGATORIO = " O codigo do CEP e obrigatório.";
		MunicipioIntegracaoVo retorno = null;
		IntegracaoCadastro objIntegracao = null;
		Validador.validaObjeto(cepIntegracaoVo);
		try
		{
			if (Validador.isNumericoValido(cepIntegracaoVo.getCodgCep()))
			{
				objIntegracao = new IntegracaoCadastro(conn);
				retorno = new MunicipioIntegracaoVo(objIntegracao.obterMunicipioPorCep(cepIntegracaoVo.getCodgCep()));
				return retorno;
			}
			else
			{
				throw new ParametroObrigatorioException(CODIGO_CEP_OBRIGATORIO);
			}
		}
		catch (SQLException e)
		{
			throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_CADASTRO, e);
		}
		catch (Exception e)
		{
			throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_CADASTRO, e);
		}
		catch (Error e)
		{
			throw IntegracaoErro.getInstance(IntegracaoErro.OPERACAO_CONSULTAR, IntegracaoErro.NOME_SISTEMA_INTEGRACAO_CADASTRO, e);
		}
	   finally
	   { 
	      if(objIntegracao != null)
	      {
	         objIntegracao = null;
	      }
	      
	   }
	}

	/**
	 * Método responsável por consultar o sistema de cadastro e devolver o código da Unidade Fazendária
	 * qual o munícipio pertence.
	 * @param municipioIntegracaoVo
	 * @return MunicipioIntegracaoVo - Integer codgUnidadeFazendaria
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 * @throws IntegracaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public MunicipioIntegracaoVo obterAgenciaFazendaria(final MunicipioIntegracaoVo municipioIntegracaoVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException, IntegracaoException
	{
	   final String CODIGO_MUNICIPIO_OBRIGATORIO = " O codigo do municipio e obrigatório.";
		Validador.validaObjeto(municipioIntegracaoVo);
		IntegracaoCadastro objIntegracao = null;
		try
		{
			if(municipioIntegracaoVo.isConsultaParametrizada())
			{
				if(Validador.isNumericoValido(((MunicipioIntegracaoVo)municipioIntegracaoVo.getParametroConsulta()).getCodgMunicipio()))
				{
					objIntegracao = new IntegracaoCadastro(conn);
					int codgMunicipio = ((MunicipioIntegracaoVo)municipioIntegracaoVo.getParametroConsulta()).getCodgMunicipio().intValue();
					int codgAgenfa = objIntegracao.obterAgenciaFazendaria(codgMunicipio, conn);
					municipioIntegracaoVo.setCodgUnidadeFazendaria(new Integer(codgAgenfa));
					return municipioIntegracaoVo;
				}
				else
				{
					throw new ParametroObrigatorioException(CODIGO_MUNICIPIO_OBRIGATORIO);					
				}
			}
			else
			{
				throw new ParametroObrigatorioException(CODIGO_MUNICIPIO_OBRIGATORIO);
			}
		}
	   catch (SQLException e)
	   {
	      throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_CADASTRO, e);
	   }
	   catch (Exception e)
	   {
	      throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_CADASTRO, e);
	   }
	   catch (Error e)
	   {
	      throw IntegracaoErro.getInstance(IntegracaoErro.OPERACAO_CONSULTAR, IntegracaoErro.NOME_SISTEMA_INTEGRACAO_CADASTRO, e);
	   }
	   finally
	   { 
	      if(objIntegracao != null)
	      {
	         objIntegracao = null;
	      }
	      
	   }
	}


	/**
	 * Método utilizado para buscar os contribuintes pelo número do CNPJ, que é obrigatório.
	 * @param contribuinteIntegracaoVo
	 * @throws IntegracaoException
	 * @throws ObjetoObrigatorioException
	 * @return ContribuinteIntegracaoVo - com uma lista dos contribuintes encontrados.
	 * @implemented by Fábio Vanzella
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public ContribuinteIntegracaoVo obterContribuintePorCnpj(ContribuinteIntegracaoVo contribuinteIntegracaoVo) throws IntegracaoException, 
			  ObjetoObrigatorioException
	{
		final String CNPJ_CONTRIBUINTE_OBRIGATORIO = " O número do CNPJ do contribuinte é obrigatório.";
		Validador.validaObjeto(contribuinteIntegracaoVo);
	   IntegracaoCadastro integracaoCadastro = null;
		try
		{
			if (contribuinteIntegracaoVo.isConsultaParametrizada())
			{
				if (Validador.isStringValida(contribuinteIntegracaoVo.getParametroConsulta().getNumrDocumento()))
				{
				   if(contribuinteIntegracaoVo.getParametroConsulta().getNumrDocumento().length() > 14)
				   {
				      contribuinteIntegracaoVo.getParametroConsulta().setNumrDocumento(StringUtil.retiraMascara(contribuinteIntegracaoVo.getParametroConsulta().getNumrDocumento()));
				   }				
				   integracaoCadastro = new IntegracaoCadastro(conn);
				   ContribuinteIntegracaoVo contribuinte = new ContribuinteIntegracaoVo(integracaoCadastro.obterContribuintePorCnpj(contribuinteIntegracaoVo.getParametroConsulta().getNumrDocumento()));
				   if(Validador.isCollectionValida(contribuinte.getCollVO()))
				   {
				      Collection listaContribuintesValidos = new ArrayList();
				      for( Object atual : contribuinte.getCollVO() )
				      {
				         if(String.valueOf(((ContribuinteIntegracaoVo)atual).getNumrInscEstadual()).length() != 10)
				         {
				            listaContribuintesValidos.add(atual);
				         }
				      }
				      contribuinte.setCollVO(listaContribuintesValidos);
				   }              
					return contribuinte;
				}
				else
				{
					throw new ParametroObrigatorioException(CNPJ_CONTRIBUINTE_OBRIGATORIO);
				}
			}
			else
			{
				throw new ParametroObrigatorioException(CNPJ_CONTRIBUINTE_OBRIGATORIO);
			}
		}
		catch (Exception e)
		{
			throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_CADASTRO, e);
		}
		catch (Error e)
		{
			throw IntegracaoErro.getInstance(IntegracaoErro.OPERACAO_CONSULTAR, IntegracaoErro.NOME_SISTEMA_INTEGRACAO_CADASTRO, e);
		}
	   finally
	   { 
	      if(integracaoCadastro != null)
	      {
	         integracaoCadastro = null;
	      }
	      
	   }
	}

	/**
	 * Método utilizado para buscar os contribuintes pelo número do CPF, que é obrigatório.
	 * @param contribuinteIntegracaoVo
	 * @throws IntegracaoException
	 * @throws ObjetoObrigatorioException
	 * @return ContribuinteIntegracaoVo - com uma lista dos contribuintes encontrados.
	 * @implemented by Fábio Vanzella
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public ContribuinteIntegracaoVo obterContribuintePorCpf(ContribuinteIntegracaoVo contribuinteIntegracaoVo, boolean isNaoExibeInscritos) throws IntegracaoException, 
			  ObjetoObrigatorioException
	{
		final String CPF_CONTRIBUINTE_OBRIGATORIO = " O número do CPF do contribuinte é obrigatório.";
		Validador.validaObjeto(contribuinteIntegracaoVo);
	   IntegracaoCadastro integracaoCadastro = null;
		try
		{
			if (contribuinteIntegracaoVo.isConsultaParametrizada())
			{
				if (Validador.isStringValida(contribuinteIntegracaoVo.getParametroConsulta().getNumrDocumento()))
				{
					if(contribuinteIntegracaoVo.getParametroConsulta().getNumrDocumento().length() > 11)
					{
						contribuinteIntegracaoVo.getParametroConsulta().setNumrDocumento(StringUtil.retiraMascara(contribuinteIntegracaoVo.getParametroConsulta().getNumrDocumento()));
					}
					integracaoCadastro = new IntegracaoCadastro(conn);
					ContribuinteIntegracaoVo contribuinte = new ContribuinteIntegracaoVo(integracaoCadastro.obterContribuintePorCpf(contribuinteIntegracaoVo.getParametroConsulta().getNumrDocumento()));
					if(isNaoExibeInscritos && Validador.isCollectionValida(contribuinte.getCollVO()))
					{
						Collection novaLista = new ArrayList();
						for(Iterator it = contribuinte.getCollVO().iterator(); it.hasNext(); )
						{
							ContribuinteIntegracaoVo atual = (ContribuinteIntegracaoVo) it.next();
							if(atual.getTipoContribuinte().equals(new Integer(DomnTipoPess.FISICA)))
							{
								novaLista.add(atual);
							}
						}
						contribuinte.setCollVO(novaLista);
					}
					return contribuinte;
				}
				else
				{
					throw new ParametroObrigatorioException(CPF_CONTRIBUINTE_OBRIGATORIO);
				}
			}
			else
			{
				throw new ParametroObrigatorioException(CPF_CONTRIBUINTE_OBRIGATORIO);
			}
		}
		catch (Exception e)
		{
			throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_CADASTRO, e);
		}
		catch (Error e)
		{
			throw IntegracaoErro.getInstance(IntegracaoErro.OPERACAO_CONSULTAR, IntegracaoErro.NOME_SISTEMA_INTEGRACAO_CADASTRO, e);
		}
	   finally
	   { 
	      if(integracaoCadastro != null)
	      {
	         integracaoCadastro = null;
	      }	      
	   }
	}

	/**
	 * Método utilizado para buscar um CEP pelo código do CEP, que é obrigatório.
	 * @param cepVo
	 * @throws IntegracaoException
	 * @throws ObjetoObrigatorioException
	 * @return CepIntegracaoVo
	 * @implemented by Fábio Vanzella
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public CepIntegracaoVo buscarCep(CepIntegracaoVo cepVo) throws IntegracaoException, ObjetoObrigatorioException
	{
		final String CODIGO_CEP_OBRIGATORIO = " O código do CEP é obrigatório.";
		Validador.validaObjeto(cepVo);
	   IntegracaoCadastro integracaoCadastro = null;
		try
		{
			if (Validador.isNumericoValido(cepVo.getCodgCep()))
			{
				integracaoCadastro = new IntegracaoCadastro(conn);
				return new CepIntegracaoVo(integracaoCadastro.buscarCep(cepVo.getCodgCep().intValue()));
			}
			else
			{
				throw new ParametroObrigatorioException(CODIGO_CEP_OBRIGATORIO);
			}
		}
		catch (Exception e)
		{
			throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_CADASTRO, e);
		}
		catch (Error e)
		{
			throw IntegracaoErro.getInstance(IntegracaoErro.OPERACAO_CONSULTAR, IntegracaoErro.NOME_SISTEMA_INTEGRACAO_CADASTRO, e);
		}
	   finally
	   { 
	      if(integracaoCadastro != null)
	      {
	         integracaoCadastro = null;
	      }        
	   }
	}

	/**
	 * Método utilizado para consultar um endereço pelo código do endereço, que é obrigatório.
	 * @param enderecoIntegracaoVo
	 * @throws IntegracaoException
	 * @throws ObjetoObrigatorioException
	 * @return EnderecoIntegracaoVo - O endereço encontrado.
	 * @implemented by Fábio Vanzella
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public EnderecoIntegracaoVo buscarEndereco(EnderecoIntegracaoVo enderecoIntegracaoVo) throws IntegracaoException, 
			  ObjetoObrigatorioException
	{
		final String CODIGO_ENDERECO_OBRIGATORIO = " O código do endereço é obrigatório.";
		Validador.validaObjeto(enderecoIntegracaoVo);
		EnderecoIntegracaoVo retorno = null;
	   IntegracaoCadastro integracaoCadastro = null;
		try
		{
			if (enderecoIntegracaoVo.isConsultaParametrizada())
			{
				if (Validador.isNumericoValido(enderecoIntegracaoVo.getParametroConsulta().getCodgEndereco()))
				{
					integracaoCadastro = new IntegracaoCadastro(conn);
					EnderecoVO endereco = 
									 integracaoCadastro.buscarEndereco(enderecoIntegracaoVo.getParametroConsulta().getCodgEndereco());
					retorno = new EnderecoIntegracaoVo(endereco);
					
					/* Listando as UFs para encontrar um especifica*/
					Collection ufs = new ArrayList(integracaoCadastro.listarUf());
					for (Iterator iteUfs = ufs.iterator(); iteUfs.hasNext();)
					{
					   UfVO ufAtual = (UfVO) iteUfs.next();
						if (ufAtual.getSiglUf().equals(retorno.getUf().getSiglUf()))
						{
							retorno.setUf(new UFIntegracaoVO(ufAtual));
							break;
						}
					}
//					TODO O método buscarUf - não consta no documento de Integração					
//					retorno.setUf(new UFIntegracaoVO(integracaoCadastro.buscarUf(retorno.getUf().getSiglUf())));
					return retorno;
				}
				else
				{
					throw new ParametroObrigatorioException(CODIGO_ENDERECO_OBRIGATORIO);
				}
			}
			else
			{
				throw new ParametroObrigatorioException(CODIGO_ENDERECO_OBRIGATORIO);
			}
		}
		catch (Exception e)
		{
			throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_CADASTRO, e);
		}
		catch (Error e)
		{
			throw IntegracaoErro.getInstance(IntegracaoErro.OPERACAO_CONSULTAR, IntegracaoErro.NOME_SISTEMA_INTEGRACAO_CADASTRO, e);
		}
	   finally
	   { 
	      if(integracaoCadastro != null)
	      {
	         integracaoCadastro = null;
	      }        
	   }
	}

	/**
	 * Método utilizado para incluir um endereço no sistema de cadastro.
	 * @param enderecoIntegracaoVo
	 * @throws IntegracaoException
	 * @throws ObjetoObrigatorioException
	 * @return EnderecoIntegracaoVo
	 * @implemented by Fábio Vanzella
	 * @implemented by Rogério Sanches de Oliveira
	 */
	public EnderecoIntegracaoVo incluirEndereco(EnderecoIntegracaoVo enderecoIntegracaoVo) throws IntegracaoException, 
			  ObjetoObrigatorioException
	{
		final String CODIGO_CEP_OBRIGATORIO = " O código do CEP é obrigatório.";
		Validador.validaObjeto(enderecoIntegracaoVo);
	   IntegracaoCadastro integracaoCadastro = null;
		try
		{
			if (Validador.isNumericoValido(enderecoIntegracaoVo.getCep().getCodgCep()))
			{
				integracaoCadastro = new IntegracaoCadastro(conn);
				return new EnderecoIntegracaoVo(integracaoCadastro.incluirEndereco(enderecoIntegracaoVo.toEnderecoVo(), conn));
			}
			else
			{
				throw new ParametroObrigatorioException(CODIGO_CEP_OBRIGATORIO);
			}
		}
		catch (Exception e)
		{
			throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_INCLUIR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_CADASTRO, e);
		}
		catch (Error e)
		{
			throw IntegracaoErro.getInstance(IntegracaoErro.OPERACAO_INCLUIR, IntegracaoErro.NOME_SISTEMA_INTEGRACAO_CADASTRO, e);
		}
	   finally
	   { 
	      if(integracaoCadastro != null)
	      {
	         integracaoCadastro = null;
	      }        
	   }
      
	}

	/**
	 * Método utilizado por alterar um endereço no sistema de cadastro.
	 * @param enderecoIntegracaoVo
	 * @throws IntegracaoException
	 * @throws ObjetoObrigatorioException
	 * @implemented by Rogério Sanches de Oliveira
	 * @implemented by Fábio Vanzella
	 */
	public void alterarEndereco(EnderecoIntegracaoVo enderecoIntegracaoVo) throws IntegracaoException, 
			  ObjetoObrigatorioException
	{
		final String CODIGO_CEP_OBRIGATORIO = " O código do CEP é obrigatório.";
		final String CODIGO_ENDERECO_OBRIGATORIO = "O código do endereço é obrigatório.";
		Validador.validaObjeto(enderecoIntegracaoVo);
		try
		{
			if (Validador.isNumericoValido(enderecoIntegracaoVo.getCep().getCodgCep()))
			{
				if (Validador.isNumericoValido(enderecoIntegracaoVo.getCodgEndereco()))
				{
					new IntegracaoCadastro(conn).alterarEndereco(enderecoIntegracaoVo.toEnderecoVo(), conn);
				}
				else
				{
					throw new ParametroObrigatorioException(CODIGO_ENDERECO_OBRIGATORIO);
				}
			}
			else
			{
				throw new ParametroObrigatorioException(CODIGO_CEP_OBRIGATORIO);
			}
		}
		catch (Exception e)
		{
			throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_INCLUIR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_CADASTRO, e);
		}
		catch (Error e)
		{
			throw IntegracaoErro.getInstance(IntegracaoErro.OPERACAO_INCLUIR, IntegracaoErro.NOME_SISTEMA_INTEGRACAO_CADASTRO, e);
		}	   
	}

	/**
	 * Este método é usado para listar todos as Unidades Federativas cadastradas no sistema
	 * de Cadastro.
	 * @throws IntegracaoException
	 * @return lista de todas as Unidades Federativas
	 * @implemented by Fábio Vanzella
	 */
	public Collection listarUf() throws IntegracaoException
	{
		Collection retorno = new ArrayList();
	   IntegracaoCadastro integracao = null;
		try
		{
			integracao = new IntegracaoCadastro(conn);
			Collection listaConsultada = integracao.listarUf();
			if (Validador.isCollectionValida(listaConsultada))
			{
				Iterator it = listaConsultada.iterator();
				while (it.hasNext())
				{
					UfVO atual = (UfVO) it.next();
					UFIntegracaoVO novoUf = new UFIntegracaoVO(atual);
					retorno.add(novoUf);
				}
			}
		}
		catch (Exception e)
		{
			throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_INCLUIR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_CADASTRO, e);
		}
		catch (Error e)
		{
			throw IntegracaoErro.getInstance(IntegracaoErro.OPERACAO_INCLUIR, IntegracaoErro.NOME_SISTEMA_INTEGRACAO_CADASTRO, e);
		}finally
      {
         if(integracao != null)
         {
            integracao = null;
         } 
      }
		return retorno;
	}

	/**
	 * Este método é usado para listar todos os municípios do estado de Mato Grosso
	 * que estejam ativos.
	 * @throws IntegracaoException
	 * @return lista de todos os municípios do estado de mato Grosso
	 * @implemented by Fábio Vanzella
	 */
	public Collection listarMunicipiosAtivos() throws IntegracaoException
	{
		Collection retorno = new ArrayList();
	   IntegracaoCadastro integracao = null;
		try
		{
			integracao = new IntegracaoCadastro();
			Collection listaMunicipio = integracao.listarMunicipioPorUfOrdenadoPorNome(ConfiguracaoITCD.SIGLA_ESTADO_MATO_GROSSO);
			Iterator it = listaMunicipio.iterator();
			while (it.hasNext())
			{
				MunicipioVO atual = (MunicipioVO) it.next();
				MunicipioIntegracaoVo municipio = new MunicipioIntegracaoVo(atual);
				retorno.add(municipio);
			}
		}
		catch (Exception e)
		{
			throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_INCLUIR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_CADASTRO, e);
		}
		catch (Error e)
		{
			throw IntegracaoErro.getInstance(IntegracaoErro.OPERACAO_INCLUIR, IntegracaoErro.NOME_SISTEMA_INTEGRACAO_CADASTRO, e);
		}
	   finally
      {
         if(integracao != null)
         {
            integracao = null;
         } 
      }
		return retorno;
	}
   
   /**
    * Este método tem por objeticvo listar todos os municipios sede.
    * que estejam ativos. NÃO listando distritos.
    * @throws IntegracaoException
    * @return lista de todos os municípios do estado de mato Grosso
    * @implemented by Dherkyan Ribeiro
    */
   public Collection listarMunicipiosSede() throws IntegracaoException
   {
      Collection retorno = new ArrayList();
      IntegracaoCadastro integracao = null;
      try
      {
         integracao = new IntegracaoCadastro(conn);
         Collection listaMunicipio = integracao.listarMunicipiosAtivos();
         Iterator it = listaMunicipio.iterator();
         while (it.hasNext())
         {
            MunicipioVO atual = (MunicipioVO) it.next();
            if(!Validador.isNumericoValido( atual.getCodgMunicipioSede() ))
            {
                 MunicipioIntegracaoVo municipio = new MunicipioIntegracaoVo(atual);
                 retorno.add(municipio);
            }
         }
      }
      catch (Exception e)
      {
         throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_INCLUIR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_CADASTRO, e);
      }
      catch (Error e)
      {
         throw IntegracaoErro.getInstance(IntegracaoErro.OPERACAO_INCLUIR, IntegracaoErro.NOME_SISTEMA_INTEGRACAO_CADASTRO, e);
      }
      finally
      {
         if(integracao != null)
         {
            integracao = null;
         } 
      }
      return retorno;
   }

	/**
	 * Este metodo usa a integracao com o sistema de cadastro de contribuinte e retorna
	 * uma lista de sefaz.mt.cadastro.integracao.TipoDocumentoVO. Neste caso nao precisamos
	 * de um VO que seja *espelho* do externo pois nao sera persistido dados usando este
	 * VO, ele e usado somente para fazer pesquisa de contribuinte.
	 * @throws IntegracaoException
	 * @return Collection
	 * @implemented by Fábio Vanzella
	 */
	public Collection listarTipoDocumento() throws IntegracaoException
	{
		Collection retorno = null; 
	   IntegracaoCadastro integracao = null;
		try
		{
			integracao = new IntegracaoCadastro(conn);
			retorno = integracao.listarTipoDocumento();
		}
		catch (SQLException e)
		{
			throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_CADASTRO, e);
		}
		catch (Exception e)
		{
			throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_INCLUIR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_CADASTRO, e);
		}
		catch (Error e)
		{
			throw IntegracaoErro.getInstance(IntegracaoErro.OPERACAO_INCLUIR, IntegracaoErro.NOME_SISTEMA_INTEGRACAO_CADASTRO, e);
		}	   
      finally
      {
         if(integracao != null)
         {
            integracao = null;
         } 
      }
		return retorno;
	}

	/**
	 * Consulta no sistema de cadastro um contribuinte de acordo com o número e tipo de documento informados
	 * @param parmNumrDocumento número do documento
	 * @param parmTipoDocumento tipo do documento
	 * @throws ParametroObrigatorioException
	 * @throws IntegracaoException
	 * @return ContribuinteIntegracaoVo
	 * @implemented by Fábio Vanzella
	 * @implemented by Leandro Dorileo
	 */
	public ContribuinteIntegracaoVo obterContribuintePorTipoDocumento(String parmNumrDocumento, Integer parmTipoDocumento) throws ParametroObrigatorioException, 
			  IntegracaoException
	{
	   IntegracaoCadastro integracao = null;
      if (!Validador.isStringValida(parmNumrDocumento))
		{
			throw new ParametroObrigatorioException(MensagemErro.GIA_ITCD_DOACAO_VALIDAR_NUMERO_DOCUMENTO);
		}
		if (!Validador.isNumericoValido(parmTipoDocumento))
		{
			throw new ParametroObrigatorioException(MensagemErro.GIA_ITCD_DOACAO_VALIDAR_TIPO_DOCUMENTO);
		}
		try
		{
			integracao = new IntegracaoCadastro(conn);
			return new ContribuinteIntegracaoVo(integracao.obterContribuinteSimplicado(parmNumrDocumento, parmTipoDocumento));
		}
		catch (Exception e)
		{
			throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_INCLUIR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_CADASTRO, e);
		}
		catch (Error e)
		{
			throw IntegracaoErro.getInstance(IntegracaoErro.OPERACAO_INCLUIR, IntegracaoErro.NOME_SISTEMA_INTEGRACAO_CADASTRO, e);
		}
	   finally
	   {
	      if(integracao != null)
	      {
	         integracao = null;
	      } 
	   }
	}

	/**
	 * @param contribuinteIntegracaoVo
	 * @throws ObjetoObrigatorioException
	 * @throws IntegracaoException
	 * @return ContribuinteIntegracaoVo
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public ContribuinteIntegracaoVo extraiContribuinte(ContribuinteIntegracaoVo contribuinteIntegracaoVo) throws ObjetoObrigatorioException, 
			  IntegracaoException
	{
		Validador.validaObjeto(contribuinteIntegracaoVo);
		contribuinteIntegracaoVo = (ContribuinteIntegracaoVo) ((ArrayList) contribuinteIntegracaoVo.getCollVO()).remove(0);
		ContribuinteIntegracaoVo contribuinteIntegracaoAuxVo = new ContribuinteIntegracaoVo();
		contribuinteIntegracaoAuxVo.setParametroConsulta(contribuinteIntegracaoVo);
		return obterContribuinte(contribuinteIntegracaoAuxVo);		
	}

	/**
	 * @return
	 * @throws IntegracaoException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public int obterCodigoMunicipioCapital() throws IntegracaoException
	{
		try
		{
			
			MunicipioIntegracaoVo municipio = new MunicipioIntegracaoVo(new IntegracaoCadastro(conn).obterMunicipioPorCep(new Integer(ConfiguracaoITCD.CODIGO_CEP_CAPITAL_MATO_GROSSO)));
			return municipio.getCodgMunicipio().intValue();
		}
	   catch (Exception e)
	   {
	      throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_CADASTRO, e);
	   }
	   catch (Error e)
	   {
	      throw IntegracaoErro.getInstance(IntegracaoErro.OPERACAO_CONSULTAR, IntegracaoErro.NOME_SISTEMA_INTEGRACAO_CADASTRO, e);
	   }
	   
	}
	
	public UFIntegracaoVO buscarUf( UFIntegracaoVO uFIntegracaoVO) throws ObjetoObrigatorioException, IntegracaoException
	{
	   final String SIGLA_UF_OBRIGATORIA = "A sigla da UF é obrigatória.";
	   Validador.validaObjeto( uFIntegracaoVO );
	   EnderecoIntegracaoVo retorno = null;
	   IntegracaoCadastro integracaoCadastro = null;
	   try
	   {	      
			if (Validador.isStringValida(uFIntegracaoVO.getSiglUf()))
			{
				integracaoCadastro = new IntegracaoCadastro(conn);
				return new UFIntegracaoVO(integracaoCadastro.buscarUf( uFIntegracaoVO.getSiglUf() ));
			}
			else
			{
				throw new ParametroObrigatorioException(SIGLA_UF_OBRIGATORIA);
			}
	   }
	   catch (Exception e)
	   {
	      throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_CADASTRO, e);
	   }
	   catch (Error e)
	   {
	      throw IntegracaoErro.getInstance(IntegracaoErro.OPERACAO_CONSULTAR, IntegracaoErro.NOME_SISTEMA_INTEGRACAO_CADASTRO, e);
	   }	
	   finally
	   {
	      if(integracaoCadastro != null)
	      {
	         integracaoCadastro = null;
	      } 
	   }
	}
   
   
   
   public static void main(String[] args) throws SQLException
   {
      
   //    IntegracaoCadastro objIntegracao = new IntegracaoCadastro();
   //    Collection listMunicipio = objIntegracao.listarMunicipiosAtivos();
   //    Integer i = new Integer(0);
   //    MunicipioVO municipioConsulta = objIntegracao.obterMunicipioPorCep(new Integer(78005300));
   //    System.out.println(municipioConsulta.getCodgMunicipio());
      try
      {
        /* 
         IntegracaoCadastro integracaoCadastro = new IntegracaoCadastro();       
         ContribuinteVO contribuinte = integracaoCadastro.obterContribuinte(597705);
         ContribuinteIntegracaoVo cont = new ContribuinteIntegracaoVo(contribuinte);
         System.out.println(cont.getCodgMunicipio());
         System.out.println(cont.getMunicipio());
         */
      
         
         IntegracaoCadastro integracaoCadastroBe = new IntegracaoCadastro();
        // Collection listMunicipio = objIntegracao.listarMunicipiosAtivos();
      
         MunicipioVO municipioConsulta = integracaoCadastroBe.obterMunicipioPorCep(new Integer(78088140));
         System.out.println(municipioConsulta.getCodgMunicipio());
      }
      catch(Exception e)
      {
         e.printStackTrace();
      }
   }
   
}
