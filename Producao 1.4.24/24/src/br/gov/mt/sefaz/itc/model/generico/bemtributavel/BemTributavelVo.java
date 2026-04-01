package br.gov.mt.sefaz.itc.model.generico.bemtributavel;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoAtributoExterno;
import br.com.abaco.log5.anotacoes.AnotacaoColunaExterna;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoSequencia;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.log5.util.dominio.DomnTipoSequencia;
import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.facade.EntidadeFacade;

import br.gov.mt.sefaz.itc.model.generico.avaliacao.AvaliacaoBemTributavelVo;
import br.gov.mt.sefaz.itc.model.generico.ficha.AbstractFichaVo;
import br.gov.mt.sefaz.itc.model.generico.fichaimovel.FichaImovelVo;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.FichaImovelRuralVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelurbano.FichaImovelUrbanoVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.bem.BemVo;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.NumeroUtil;
import br.gov.mt.sefaz.itc.util.calculo.CalculoITCD;
import br.gov.mt.sefaz.itc.util.dominio.DomnAtivoInativo;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoBem;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoUsuario;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposBemTributavel;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposFichaImovelUrbano;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.math.BigDecimal;

import java.math.RoundingMode;

import java.text.NumberFormat;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 * Classe responsável por encapsular	os valores do objeto BemTributavel (Value Object)
 * @author Lucas Nascimento
 * @version $Revision: 1.12 $
 */
@AnotacaoTabelaClasse(nomeTabela = TabelasITC.TABELA_GIA_ITCD_BEM_TRIBUTAVEL, nomeAmigavel = "GIA-ITCD - Bem Tributável", identificadores = { @AnotacaoIdentificador(nomeAtributo = "codigo", nomeColuna = CamposBemTributavel.CAMPO_CODIGO_ITCD_BEM_TRIBUTAVEL, sequencia = @AnotacaoSequencia(nomeSequencia = SequencesITC.SEQUENCE_GIA_ITCD_BEM_TRIBUTAVEL, tipoSequencia = DomnTipoSequencia.SEQUENCE)
			)
			} )
public class BemTributavelVo extends EntidadeVo
{
	private static final long serialVersionUID = Long.MAX_VALUE;
	private GIAITCDVo giaITCDVo;
	private BemVo bemVo;
	private double valorMercado;
   private double valorArbitradoAux;

	/** representa o valor informado pelo contribuinte*/
	private double valorInformadoContribuinte;
	private DomnSimNao isencaoPrevista;
	private String descricaoBemTributavel;
	private DomnSimNao bemParticular;
	private DomnTipoUsuario tipoUsuario;
	private DomnAtivoInativo statusBemTributavel;
	private AvaliacaoBemTributavelVo avaliacaoBemTributavelVo;
	private FichaImovelVo fichaImovelVo;
	private AbstractFichaVo fichaVo;
	private transient boolean alterar;
	private transient boolean exibirBemParticular;
	private transient boolean exibirIsencaoPrevistaEmLei;
	private transient boolean bemPassivelAvaliacao;
	private transient String motivoBemNaoPodeSerAvaliado;
	private transient boolean exibirTipoOutrosBens;
	private transient boolean existeBemAvaliado;
	private transient boolean habilitarClassificacaoBem;
	private transient boolean habilitarTipoBem;
	
	//Evolução 1.3  - Contribuinte concorda com valor arbitrado
	private DomnSimNao concordaComValorArbitrado;
      

	/**
	 * Construtor vazio
	 * @implemented by Lucas Nascimento
	 */
	public BemTributavelVo()
	{
		super();
	}

	/**
	 * Construtor padrï¿½o
	 * @param codigo Chave Primï¿½ria
	 * @implemented by Lucas Nascimento
	 */
	public BemTributavelVo(long codigo)
	{
		super(codigo);
	}

	/**
	 * Construtor que recebe um parametro de consulta
	 * @param bemTributavelVo(Value Object)
	 * @implemented by Lucas Nascimento
	 */
	public BemTributavelVo(BemTributavelVo bemTributavelVo)
	{
		super();
		bemTributavelVo.setStatusBemTributavel(new DomnAtivoInativo(DomnAtivoInativo.ATIVO));
		setParametroConsulta(bemTributavelVo);
	}

	/**
	 * Construtor que recebe uma Collection
	 * @param collVo Coleï¿½ï¿½o de Value Objects
	 * @implemented by Lucas Nascimento
	 */
	public BemTributavelVo(Collection collVo)
	{
		super(collVo);
	}

	public long getCodigo()
	{
		return super.getCodigo();
	}

	/**
	 * Atribui o Bem
	 * @param bemVo
	 * @implemented by Lucas Nascimento
	 */
	public void setBemVo(BemVo bemVo)
	{
		this.bemVo = bemVo;
	}

	@AnotacaoAtributo(nomeColuna = CamposBemTributavel.CAMPO_STAT_BEM_TRBT, obrigatorio = true)
	public DomnAtivoInativo getStatusBemTributavel()
	{
		if (!Validador.isObjetoValido(statusBemTributavel))
		{
			setStatusBemTributavel(new DomnAtivoInativo(DomnAtivoInativo.ATIVO));
		}
		return statusBemTributavel;
	}

	public void setStatusBemTributavel(DomnAtivoInativo statusBemTributave)
	{
		this.statusBemTributavel = statusBemTributave;
	}

	/**
	 * Retorna o Bem
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	@AnotacaoAtributoExterno(obrigatorio = true, colunaReferencia = { @AnotacaoColunaExterna(nomeColuna = CamposBemTributavel.CAMPO_ITCTB06_CODIGO_BEM, nomeAtributo = "codigo")
				} )
	public BemVo getBemVo()
	{
		if (!Validador.isObjetoValido(this.bemVo))
		{
			setBemVo(new BemVo());
		}
		return bemVo;
	}

	/**
	 * Atribui a Isenção Prevista
	 * @param isencaoPrevista
	 * @implemented by Lucas Nascimento
	 */
	public void setIsencaoPrevista(DomnSimNao isencaoPrevista)
	{
		this.isencaoPrevista = isencaoPrevista;
	}

	/**
	 * Retorna a Isenção Prevista
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	@AnotacaoAtributo(nomeColuna = CamposBemTributavel.CAMPO_ISENCAO_PREVISTA, obrigatorio = true)
	public DomnSimNao getIsencaoPrevista()
	{
		if (!Validador.isDominioNumericoValido(isencaoPrevista))
		{
			setIsencaoPrevista(new DomnSimNao(-1));
		}
		return isencaoPrevista;
	}

	/**
	 * Atribui o Valor de Mercado
	 * @param valorMercado
	 * @implemented by Lucas Nascimento
	 */
	public void setValorMercado(double valorMercado)
	{
		this.valorMercado = valorMercado;	   
	}

	/**
	 * Retorna o Valor de Mercado
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	@AnotacaoAtributo(nomeColuna = CamposBemTributavel.CAMPO_VALOR_MERCADO, obrigatorio = true)
	public double getValorMercado()
	{
		return NumeroUtil.arredondaNumero(valorMercado);
	}

	/**
	 * Atribui a Ficha de Imovel
	 * @param fichaImovelVo
	 * @implemented by Lucas Nascimento
	 */
	public void setFichaImovelVo(FichaImovelVo fichaImovelVo)
	{
		this.fichaImovelVo = fichaImovelVo;
	}

	/**
	 * Retorna a Ficha de Imovel
	 * @return
	 */
	public FichaImovelVo getFichaImovelVo()
	{
		if (!Validador.isObjetoValido(fichaImovelVo))
		{
			if (getBemVo().getClassificacaoTipoBem().is(DomnTipoBem.IMOVEL_RURAL))
			{
				setFichaImovelVo(new FichaImovelRuralVo());
			}
			else if (getBemVo().getClassificacaoTipoBem().is(DomnTipoBem.IMOVEL_URBANO))
			{
				setFichaImovelVo(new FichaImovelUrbanoVo());
			}
			else
			{
				setFichaImovelVo(new FichaImovelVo());
			}
		}
		return fichaImovelVo;
	}

	/**
	 * Retorna a GiaITCD
	 * @param giaITCDVo
	 * @implemented by Lucas Nascimento
	 */
	public void setGiaITCDVo(GIAITCDVo giaITCDVo)
	{
		this.giaITCDVo = giaITCDVo;
	}

	/**
	 * Atribui a GiaITCD
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	@AnotacaoAtributoExterno(obrigatorio = true, colunaReferencia = { @AnotacaoColunaExterna(nomeColuna = CamposBemTributavel.CAMPO_ITCTB14_CODIGO_ITCD, nomeAtributo = "codigo")
				} )
	public GIAITCDVo getGiaITCDVo()
	{
		if (!Validador.isObjetoValido(giaITCDVo))
		{
			setGiaITCDVo(new GIAITCDVo());
		}
		return giaITCDVo;
	}

	/**
	 * Atribui a Descrição do Bem
	 * @param descricaoBemTributavel
	 * @implemented by Lucas Nascimento
	 */
	public void setDescricaoBemTributavel(String descricaoBemTributavel)
	{
		this.descricaoBemTributavel = descricaoBemTributavel;
	}

	/**
	 * Retorna a Descrição
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	@AnotacaoAtributo(nomeColuna = CamposBemTributavel.CAMPO_DESCRICAO_BEM_TRIBUTAVEL, obrigatorio = true)
	public String getDescricaoBemTributavel()
	{
		if (!Validador.isStringValida(descricaoBemTributavel))
		{
			setDescricaoBemTributavel(STRING_VAZIA);
		}
		return descricaoBemTributavel;
	}

	/**
	 * Reorna o Valor de Mercado Formatado
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	public String getValorMercadoFormatado()
	{
		String retorno = STRING_VAZIA;
		if (Validador.isNumericoValido(getAvaliacaoBemTributavelVo().getValorAvaliacao()))
		{
			return getAvaliacaoBemTributavelVo().getValorAvaliacaoFormatado();
		}
		if (Validador.isNumericoValido(getValorMercado()))
		{
         String aux = StringUtil.doubleToMonetario(getValorMercado());
			return aux;
		}
		return retorno;
	}
   
   public String getValorArbitradoFormatado(){     
   
   return StringUtil.doubleToMonetario(getValorMercado());  
   
   }

	public DomnSimNao getIsencaoPrevistaFormatada()
	{
		if (Validador.isDominioNumericoValido(getAvaliacaoBemTributavelVo().getIsento()))
		{
			return getAvaliacaoBemTributavelVo().getIsento();
		}
		return getIsencaoPrevista();
	}

	/**
	 * Reorna o Valor de Mercado Formatado Informado
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	public String getValorMercadoInformadoFormatado()
	{
		String retorno = STRING_VAZIA;
		if (Validador.isNumericoValido(getValorMercado()))
		{
			return StringUtil.doubleToMonetario(getValorMercado());
		}
		return retorno;
	}

	/**
	 * Atribui a Avaliação ao Bem Tibutavel
	 * @param avaliacaoBemTributavelVo
	 * @implemented by Lucas Nascimento
	 */
	public void setAvaliacaoBemTributavelVo(AvaliacaoBemTributavelVo avaliacaoBemTributavelVo)
	{
		this.avaliacaoBemTributavelVo = avaliacaoBemTributavelVo;
	}

	/**
	 * Retorna a avaliação do bem tributavel
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	public AvaliacaoBemTributavelVo getAvaliacaoBemTributavelVo()
	{
		if (!Validador.isObjetoValido(avaliacaoBemTributavelVo))
		{
			setAvaliacaoBemTributavelVo(new AvaliacaoBemTributavelVo());
		}
		if (!Validador.isNumericoValido(this.avaliacaoBemTributavelVo.getBemTributavel().getCodigo()) && !this.equals(this.avaliacaoBemTributavelVo.getBemTributavel()))
		{
			this.avaliacaoBemTributavelVo.setBemTributavel(this);
		}
		return avaliacaoBemTributavelVo;
	}

	/**
	 * Atribui alterar
	 * @param alterar
	 * @implemented by Lucas Nascimento
	 */
	public void setAlterar(boolean alterar)
	{
		this.alterar = alterar;
	}

	/**
	 * Retorna alterar
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	public boolean isAlterar()
	{
		return alterar;
	}

	public void setBemParticular(DomnSimNao bemParticular)
	{
		this.bemParticular = bemParticular;
	}

	@AnotacaoAtributo(nomeColuna = CamposBemTributavel.CAMPO_FLAG_BEM_PARTICULAR, obrigatorio = true)
	public DomnSimNao getBemParticular()
	{
		if (!Validador.isDominioNumericoValido(bemParticular))
		{
			return new DomnSimNao();
		}
		return bemParticular;
	}

	public void setExibirBemParticular(boolean exibirBemParticular)
	{
		this.exibirBemParticular = exibirBemParticular;
	}

	public boolean isExibirBemParticular()
	{
		return exibirBemParticular;
	}

	public void setExibirIsencaoPrevistaEmLei(boolean exibirIsencaoPrevistaEmLei)
	{
		this.exibirIsencaoPrevistaEmLei = exibirIsencaoPrevistaEmLei;
	}

	public boolean isExibirIsencaoPrevistaEmLei()
	{
		return exibirIsencaoPrevistaEmLei;
	}

	@AnotacaoAtributo(nomeColuna = CamposBemTributavel.CAMPO_TIPO_USUARIO_INCLUSAO, obrigatorio = true)
	public DomnTipoUsuario getTipoUsuario()
	{
		if (!Validador.isDominioNumericoValido(tipoUsuario))
		{
			setTipoUsuario(new DomnTipoUsuario(-1));
		}
		return tipoUsuario;
	}

	public void setTipoUsuario(DomnTipoUsuario tipoUsuario)
	{
		this.tipoUsuario = tipoUsuario;
	}

	public boolean equals(Object object)
	{
		if (object instanceof EntidadeFacade)
		{
			if (this.hashCode() == object.hashCode())
			{
				return true;
			}
			return false;
		}
		else
		{
			return false;
		}
	}

	public int hashCode()
	{
		int hashAtual = 0;
		int hashCodigo = (int) getCodigo();
		int hashBem = getBemVo().hashCode();
		int hashFichaImovel = (new Long(getFichaImovelVo().getCodigo())).intValue();
		int hashDescricaoBem = (getDescricaoBemTributavel().length());
		int hashCollVo = getCollVO().size() * MULTIPLICADOR_HASH_CODE;
		hashAtual += hashCodigo;
		hashAtual += hashBem;
		hashAtual += hashFichaImovel;
		hashAtual += hashDescricaoBem;
		hashAtual += hashCollVo;
		hashAtual *= MULTIPLICADOR_HASH_CODE;
		return hashAtual;
	}

	public int compareTo(Object entidadeVo) throws ClassCastException, 
			   NullPointerException
	{
		int codigo = new Long(this.getCodigo()).compareTo(new Long(((BemTributavelVo) entidadeVo).getCodigo()));
		if (codigo != 0)
		{
			return codigo;
		}
		else
		{
			codigo = new Long(this.getBemVo().getCodigo()).compareTo(new Long(((BemTributavelVo) entidadeVo).getBemVo().getCodigo()));
			if (codigo != 0)
			{
				return codigo;
			}
			else
			{
				codigo = new Long(this.getFichaImovelVo().getCodigo()).compareTo(new Long(((BemTributavelVo) entidadeVo).getFichaImovelVo().getCodigo()));
				if (codigo != 0)
				{
					return codigo;
				}
				else
				{
					return this.getDescricaoBemTributavel().compareTo(((BemTributavelVo) entidadeVo).getDescricaoBemTributavel());
				}
			}
		}
	}

	public boolean isExisteBemParticular()
	{
		boolean existeBemParticular = false;
		if (Validador.isCollectionValida(getCollVO()))
		{
			if (!(getCollVO().isEmpty()) && isExibirBemParticular())
			{
				for (Iterator it = getCollVO().iterator(); it.hasNext(); )
				{
					BemTributavelVo bemAtual = (BemTributavelVo) it.next();
					if (bemAtual.getBemParticular().is(DomnSimNao.SIM))
					{
						existeBemParticular = true;
						break;
					}
				}
			}
		}
		return existeBemParticular;
	}

	public boolean isExisteApenasBemParticular()
	{
		boolean existeApenasBemParticular = true;
		if (Validador.isCollectionValida(getCollVO()))
		{
			if (!getCollVO().isEmpty())
			{
				for (Iterator it = getCollVO().iterator(); it.hasNext(); )
				{
					BemTributavelVo bemAtual = (BemTributavelVo) it.next();
					if (bemAtual.getBemParticular().is(DomnSimNao.NAO))
					{
						existeApenasBemParticular = false;
						break;
					}
				}
			}
		}
		return existeApenasBemParticular;
	}

	public void setBemPassivelAvaliacao(boolean bemPassivelAvaliacao)
	{
		this.bemPassivelAvaliacao = bemPassivelAvaliacao;
	}

	public boolean isBemPassivelAvaliacao()
	{
		return bemPassivelAvaliacao;
	}

	public void setMotivoBemNaoPodeSerAvaliado(String motivoBemNaoPodeSerAvaliado)
	{
		this.motivoBemNaoPodeSerAvaliado = motivoBemNaoPodeSerAvaliado;
	}

	public String getMotivoBemNaoPodeSerAvaliado()
	{
		return motivoBemNaoPodeSerAvaliado;
	}

	public void setExibirTipoOutrosBens(boolean exibirTipoOutrosBens)
	{
		this.exibirTipoOutrosBens = exibirTipoOutrosBens;
	}

	public boolean isExibirTipoOutrosBens()
	{
		return exibirTipoOutrosBens;
	}

	public void setExisteBemAvaliado(boolean existeBemAvaliado)
	{
		this.existeBemAvaliado = existeBemAvaliado;
	}

	public boolean isExisteBemAvaliado()
	{
		return existeBemAvaliado;
	}

	public void setFichaVo(AbstractFichaVo fichaVo)
	{
		this.fichaVo = fichaVo;
	}

	public AbstractFichaVo getFichaVo()
	{
		return fichaVo;
	}

	public void setValorInformadoContribuinte(double valorInformado)
	{
		this.valorInformadoContribuinte = valorInformado;
	}

	/**
	 * Retorna o Valor de Mercado
	 * @return
	 * @implemented by Dherkyan Ribeiro
	 */
	@AnotacaoAtributo(nomeColuna = CamposBemTributavel.CAMPO_VALOR_INFORMADO, obrigatorio = false)
	public double getValorInformadoContribuinte()
	{
		return NumeroUtil.arredondaNumero(valorInformadoContribuinte);
	}  
   
	public String getValorInformadoFormatado()
	{
		System.out.println(getValorInformadoContribuinte());
		if (Validador.isNumericoValido(getValorInformadoContribuinte()))
		{
			return StringUtil.doubleToMonetario(getValorInformadoContribuinte());
		}
		return STRING_VAZIA;
	}

	public double getValorTotalInformadoContribuinte()
	{
		return NumeroUtil.arredondaNumero(CalculoITCD.calcularTotalValorInformadoContribuinte(this));
	}

	public String getValorTotalInformadoContribuinteFormatado()
	{
		if (Validador.isNumericoValido(getValorTotalInformadoContribuinte()))
		{
			return StringUtil.doubleToMonetario(getValorTotalInformadoContribuinte());
		}
		return STRING_VAZIA;
	}

	public void setConcordaComValorArbitrado(DomnSimNao concordaComValorArbitrado)
	{
		this.concordaComValorArbitrado = concordaComValorArbitrado;
	}

	@AnotacaoAtributo(nomeColuna = CamposBemTributavel.CAMPO_FLAG_CONCORDA_VALOR)
	public DomnSimNao getConcordaComValorArbitrado()
	{
		
		if(concordaComValorArbitrado!= null)
		    System.out.println("CAMPO CONCORDA VALOR: " + concordaComValorArbitrado.getTextoCorrente());
		return concordaComValorArbitrado;
	}

	/**
	 * <b>Objetivo:</b> método utilitario para
	 * converter 
	 * @return
	 */
	public List<BemTributavelVo> getListVo()
	{
		return super.getListVo();
	}

	public void setHabilitarClassificacaoBem(boolean habilitarClassificacaoBem)
	{
		this.habilitarClassificacaoBem = habilitarClassificacaoBem;
	}

	public boolean isHabilitarClassificacaoBem()
	{
		return habilitarClassificacaoBem;
	}

	public void setHabilitarTipoBem(boolean habilitarTipoBem)
	{
		this.habilitarTipoBem = habilitarTipoBem;
	}

	public boolean isHabilitarTipoBem()
	{
		return habilitarTipoBem;
	}
   
   public double getCalculoPercentualAcordadoArbitrado(){   
            double valorSomaArbitrado = 0.0;
            double valorSomaConcordado = 0.0;
            double porcentagem = 0.0;
            Iterator iterator = super.getCollVO().iterator();  
            
            while(iterator.hasNext()){
               BemTributavelVo BemVo = (BemTributavelVo) iterator.next();           
               
               if (getGiaITCDVo().isGiaAvaliada()){         
                  valorSomaArbitrado += BemVo.getAvaliacaoBemTributavelVo().getValorAvaliacao();            
               }else{
                  valorSomaArbitrado += BemVo.getValorMercado();
               }
               
               if(BemVo.getConcordaComValorArbitrado().getTextoCorrente() == "SIM"){
                  valorSomaConcordado += BemVo.getValorMercado();
               }else{
                  valorSomaConcordado += BemVo.getValorInformadoContribuinte();
               } 
         }
         porcentagem = ((valorSomaConcordado/valorSomaArbitrado)*100);
         
         return porcentagem;   
   }   
   
   public double getValorBemDeAcordoComPorCentagem(){
   
      if(getCalculoPercentualAcordadoArbitrado() >= 70){
      
         if(getConcordaComValorArbitrado().getTextoCorrente() == "SIM"){
            return getValorMercado();
         }else{
            return getValorInformadoContribuinte();
         }   
      }
      
      return getValorMercado();
      
   }
   
   
   
   public void setValorArbitradoAux(double valorArbitradoAux)
   {
      this.valorArbitradoAux = valorArbitradoAux;      
   }   
   
   public double getValorArbitradoAux()
   {
      return NumeroUtil.arredondaNumero(valorArbitradoAux);
   }
   
   public String getValorArbitradoAuxFormatado()
   {
      return StringUtil.doubleToMonetario(valorArbitradoAux);
   }
}
