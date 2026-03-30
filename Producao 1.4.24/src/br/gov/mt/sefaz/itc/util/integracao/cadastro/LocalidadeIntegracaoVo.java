package br.gov.mt.sefaz.itc.util.integracao.cadastro;

import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.util.EntidadeVo;

import sefaz.mt.cadastro.integracao.LocalidadeVO;


/**
 * Classe utilizada como vo de integração.
 * Representa o adapter do vo de localidade para o sistema ITC.
 *
 * @author Thiago de Castilho Pacheco
 * @version $Revision: 1.1.1.1 $
 */
public class LocalidadeIntegracaoVo extends EntidadeVo
{
	private static final long serialVersionUID = Long.MAX_VALUE;
	private Integer codgLocalidade;
	private String nomeLocalidade;
	private CepIntegracaoVo cep;
	private UFIntegracaoVO uf;
	private LocalidadeIntegracaoVo localidadeSede;

	public LocalidadeIntegracaoVo()
	{
	}

	public LocalidadeVO toLocalidadeVO()
	{
		LocalidadeVO retorno = new LocalidadeVO();
		if (Validador.isNumericoValido(getCodgLocalidade()))
		{
			retorno.setCodgLocalidade(codgLocalidade);
		}
		if (Validador.isStringValida(getNomeLocalidade()))
		{
			retorno.setNomeLocalidade(getNomeLocalidade());
		}
		if (Validador.isObjetoValido(getCep()))
		{
			retorno.setCep(cep.toCepVO());
		}
		if (Validador.isObjetoValido(getUf()))
		{
			retorno.setUf(getUf().toUfVO());
		}
		if (Validador.isObjetoValido(getLocalidadeSede()))
		{
			retorno.setLocalidadeSede(getLocalidadeSede().toLocalidadeVO());
		}
		return retorno;
	}

	public void setCodgLocalidade(Integer codgLocalidade)
	{
		this.codgLocalidade = codgLocalidade;
	}

	public Integer getCodgLocalidade()
	{
		return codgLocalidade;
	}

	public void setNomeLocalidade(String nomeLocalidade)
	{
		this.nomeLocalidade = nomeLocalidade;
	}

	public String getNomeLocalidade()
	{
		return nomeLocalidade;
	}

	public void setCep(CepIntegracaoVo cep)
	{
		this.cep = cep;
	}

	public CepIntegracaoVo getCep()
	{
		return cep;
	}

	public void setUf(UFIntegracaoVO uf)
	{
		this.uf = uf;
	}

	public UFIntegracaoVO getUf()
	{
		return uf;
	}

	public void setLocalidadeSede(LocalidadeIntegracaoVo localidadeSede)
	{
		this.localidadeSede = localidadeSede;
	}

	public LocalidadeIntegracaoVo getLocalidadeSede()
	{
		return localidadeSede;
	}
	
	public boolean equals(Object objeto)
	{
		if(objeto instanceof LocalidadeIntegracaoVo)
		{
			return this.hashCode() == objeto.hashCode();
		}
		return false;
	}
	
	public int hashCode()
	{
		int hashCode = 0;
		hashCode += Validador.isStringValida(getNomeLocalidade()) ? getNomeLocalidade().hashCode() : 0;
		hashCode += Validador.isObjetoValido(getUf()) ? getUf().hashCode() : 0;
		hashCode += Validador.isNumericoValido(getCodgLocalidade()) ? getCodgLocalidade().hashCode() : 0;
		hashCode *= MULTIPLICADOR_HASH_CODE;
		return hashCode;
	}
}
