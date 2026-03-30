/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: GIAITCDDarVo.java
 * Revisão:
 * Data revisão: $Revision: 1.3 $
 * $Id: GIAITCDDarVo.java,v 1.3 2008/08/08 21:37:45 lucas.nascimento Exp $
 */
package br.gov.mt.sefaz.itc.model.generico.giaitcddar;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoAtributoExterno;
import br.com.abaco.log5.anotacoes.AnotacaoColunaExterna;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoSequencia;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.log5.util.dominio.DomnTipoSequencia;
import br.com.abaco.sefaz.integracao.arrecadacao.DarEmitidoIntegracaoVo;
import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.data.AbacoData;

import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd.StatusGIAITCDVo;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatDar;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposGIAITCDDar;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import sefaz.mt.util.SefazDataHora;


/**
 * Representacao da entidade de relacionamento entre uma giaITCD e um dar.
 * @author Leandro Dorileo
 */
 @AnotacaoTabelaClasse
 (
     nomeTabela = TabelasITC.TABELA_GIA_ITCD_DAR
     ,nomeAmigavel = "GIA-ITCD DAR"
     ,identificadores =
     {
			@AnotacaoIdentificador
			(
			    nomeAtributo = "codigo"
			    ,nomeColuna = CamposGIAITCDDar.CAMPO_CODG_ITCD_DAR
				 ,sequencia = @AnotacaoSequencia
				 (
				     nomeSequencia = SequencesITC.SEQUENCE_GIA_ITCD_DAR
				     ,tipoSequencia = DomnTipoSequencia.SEQUENCE
				 )
			)
			,@AnotacaoIdentificador
			(
			    nomeAtributo = "gia.codigo"
			    ,nomeColuna = CamposGIAITCDDar.CAMPO_CODG_ITCD
			)
			,@AnotacaoIdentificador
			(
			    nomeAtributo = "darEmitido.numrDarSeqc"
			    ,nomeColuna = CamposGIAITCDDar.CAMPO_NUMR_DAR_SEQUENCIAL
			)
     }
 )
public class GIAITCDDarVo extends EntidadeVo<GIAITCDDarVo>
{
	private static final long serialVersionUID = Long.MAX_VALUE;
	private GIAITCDVo gia;
	private DarEmitidoIntegracaoVo darEmitido;
	private Date dataAtualizacao;
	private DomnSimNao substituido;
	private int numeroParcela;
	private StatusGIAITCDVo statusGIAITCDVo;
	private transient long codigoUofSequencial;
	
	/**
	 * Construtor que recebe um Parametro de Consulta.
	 * @param giaITCDDarVo (Value Object).
	 * @implemented by Leandro Dorileo
	 */
	public GIAITCDDarVo(GIAITCDDarVo giaITCDDarVo)
	{
		super();
		setParametroConsulta(giaITCDDarVo);
	}

	/**
	 * Construtor padrão.
	 * @implemented by Leandro Dorileo
	 */
	public GIAITCDDarVo()
	{
		super();
	}

	/**
	 * Construtor que recebe a Chave Primária do Value Object.
	 * @param codigo Chave Primária
	 * @implemented by Leandro Dorileo
	 */
	public GIAITCDDarVo(long codigo)
	{
		super(codigo);
	}

	/**
	 * Construtor que recebe a Gia para formar uma gia para parametro de consulta.
	 * @param giaITCDVo Parametro de Consulta
	 * @implemented by Leandro Dorileo
	 */
	public GIAITCDDarVo(GIAITCDVo giaITCDVo)
	{
		this.setGia(giaITCDVo);
	}

	/**
	 * Construtor que recebe uma Collection de GIA ITCD Value Object.
	 * @param collVO Coleção de GIAITCDVo
	 * @implemented by Leandro Dorileo
	 */
	public GIAITCDDarVo(Collection collVO)
	{
		super(collVO);
	}
	
	public long getCodigo()
	{
		return super.getCodigo();
	}

	/**
	 * Este metodo verifica se existe um dar valido
	 *
	 * @return
	 */
	public boolean isExisteDarEmitido()
	{
		return this.getDarEmitido().isExiste();
	}

	/**
	 * Este metodo verifica se o dar esta pendente
	 *
	 * @return
	 */
	public boolean isDarPendente()
	{
		return this.getDarEmitido().getStatDar().is(DomnStatDar.PENDENTE);
	}
	
	public boolean isDarQuitado()
	{
		return this.getDarEmitido().getStatDar().is(DomnStatDar.QUITADO);
	}

	/**
	 * Este metodo verifica se o dar esta atrasado
	 *
	 * @return
	 */
	public boolean isDarAtrasado()
	{
		return AbacoData.converteDataComUltimoMinutoDia(this.getDarEmitido().getDataVencimento()).before(new Date());
	}

	/**
	 * Este metodo verifica se o dar foi substituido por algum outro
	 *
	 * @return
	 */
	public boolean isDarSubstituido()
	{
		return this.getSubstituido().is(DomnSimNao.SIM);
	}

	/**
	 * Configura o atributo gia(que faz referencia a uma GIAITCD) com o valor
	 * do parametro de entrada
	 * @param gia   novo valor a ser configurado no membro gia
	 */
	public void setGia(GIAITCDVo gia)
	{
		this.gia = gia;
	}

	/**
	 * Metodo acessor do atributo gia.
	 * @return  a gia relacionada a um determinado dar
	 */
	public GIAITCDVo getGia()
	{
		if (!Validador.isObjetoValido(this.gia))
			setGia(new GIAITCDVo());
		return gia;
	}

	/**
	 * Configura o membro <b>darEmitido</b> com o valor do parametro de entrada.
	 *
	 * @param darEmitido    novo valor a ser configurado no atributo <b>darEmitido</b>
	 */
	public void setDarEmitido(DarEmitidoIntegracaoVo darEmitido)
	{
		this.darEmitido = darEmitido;
	}

	/**
	 * Metodo acessor do atributo <b>darEmitido</b>.
	 *
	 * @return  valor atualmente configurado no atributo <b>darEmitido</b>
	 */
	public DarEmitidoIntegracaoVo getDarEmitido()
	{
		if (!Validador.isObjetoValido(this.darEmitido))
			setDarEmitido(new DarEmitidoIntegracaoVo());
		return darEmitido;
	}

	/**
	 * Configura o atributo substituido que indica se um determinado dar foi
	 * substituido ou nao.
	 *
	 * @param substituido   novo valor a ser atribuido ao menbro substituido
	 */
	public void setSubstituido(DomnSimNao substituido)
	{
		this.substituido = substituido;
	}

	/**
	 * Metodo acesso do atributo substituido que indica se um determinado dar
	 * foi substituido ou nao.
	 * @return  flag de substituicao de dar
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposGIAITCDDar.CAMPO_FLAG_SUBSTITUIDO
	     ,obrigatorio = true
	 )
	public DomnSimNao getSubstituido()
	{
		if (!Validador.isDominioNumericoValido(this.substituido))
			setSubstituido(new DomnSimNao());
		return this.substituido;
	}

	/**
	 * Atribui a Data de Atualização
	 * @param dataAtualizacao
	 * @implemented by Lucas Nascimento
	 */
	public void setDataAtualizacao(Date dataAtualizacao)
	{
		this.dataAtualizacao = dataAtualizacao;
	}

	/**
	 * Retorna a Data de Atualização
	 * @return
	 * @implemented by Lucas Nascimento
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposGIAITCDDar.CAMPO_DATA_ATUALIZACAO_BD
	     ,obrigatorio = true
		  ,timestamp = true
	 )
	public Date getDataAtualizacao()
	{
		return dataAtualizacao;
	}
	
	/**
	 * Retorna a Data de Atualização Formatada
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	public String getDataAtualizacaoFormatada() 
	{
		if(Validador.isDataValida(dataAtualizacao)) 
		{
			return new SefazDataHora(dataAtualizacao).formata(MASCARA_DATA_FORMATADA);
		}
		else 
		{
			return "";
		}
	}

	public void setNumeroParcela(int numeroParcela)
	{
		this.numeroParcela = numeroParcela;
	}

	@AnotacaoAtributo
	(
	    nomeColuna = CamposGIAITCDDar.CAMPO_NUMR_PARCELA_DAR
	    ,obrigatorio = false
	)
	public int getNumeroParcela()
	{
		return numeroParcela;
	}

	public void setCodigoUofSequencial(long codigoUofSequancial)
	{
		this.codigoUofSequencial = codigoUofSequancial;
	}

	public long getCodigoUofSequencial()
	{
		return codigoUofSequencial;
	}
	
	public double getSomaValorTributo()
	{
		double soma = 0;
		if(Validador.isCollectionValida(this.getCollVO()))
		{
			for(Iterator it = this.getCollVO().iterator(); it.hasNext(); )
			{
				GIAITCDDarVo atual = (GIAITCDDarVo) it.next();
				if(atual.getDarEmitido() != null)
				{
					soma += atual.getValorTotalDar();
				}
			}
		}
		return soma;
	}
	
	public double getValorTotalDar()
	{
		return this.getDarEmitido().getValorCorrMonetaria() 
					+ this.getDarEmitido().getValorJuros() 
					+ this.getDarEmitido().getValorMulta() 
					+ this.getDarEmitido().getValorTributo();
	}
	
	public String getValorTotalDarFormatado()
	{
		return StringUtil.doubleToMonetario(getValorTotalDar(), 2);
	}
	
	public String getSomaValorTributoFormatado()
	{
		return StringUtil.doubleToMonetario(getSomaValorTributo(), 2);
	}

	public void ordenaPorDataEmissaoDar()
	{
		if(Validador.isCollectionValida(this.getCollVO()))
		{
			Collections.sort
			(
				(List<GIAITCDDarVo>)this.getCollVO()
				, new Comparator<GIAITCDDarVo>()
				{
						public int compare(GIAITCDDarVo o1, GIAITCDDarVo o2)
						{
							if(Validador.isDataValida(o1.getDarEmitido().getDataEmissao()) && Validador.isDataValida(o2.getDarEmitido().getDataEmissao()))
							{
								return o1.getDarEmitido().getDataEmissao().compareTo(o2.getDarEmitido().getDataEmissao());
							}
							return 0;
						}
				}
			);
		}
	}

	public void ordenaPorCodigoITCDDarDecrescente()
	{
		if(Validador.isCollectionValida(this.getCollVO()))
		{
			Collections.sort
			(
				(List<GIAITCDDarVo>) this.getCollVO()
				, new Comparator<GIAITCDDarVo>()
				{
						public int compare(GIAITCDDarVo o1, GIAITCDDarVo o2)
						{
							return new Long(o2.getCodigo()).compareTo(new Long(o1.getCodigo()));
						}
				}
			);
		}
	}	

	public void setStatusGIAITCDVo(StatusGIAITCDVo statusGIAITCDVo)
	{
		this.statusGIAITCDVo = statusGIAITCDVo;
	}

	@AnotacaoAtributoExterno
	(
	    obrigatorio = false
	    ,colunaReferencia =
	    {
	        @AnotacaoColunaExterna
	        (
	            nomeColuna = CamposGIAITCDDar.CAMPO_CODG_STATUS_GIA_ITCD
	            , nomeAtributo = "codigo"
	        )
	    }
	)
	public StatusGIAITCDVo getStatusGIAITCDVo()
	{
		if(!Validador.isObjetoValido(statusGIAITCDVo))
		{
			setStatusGIAITCDVo(new StatusGIAITCDVo());
		}
		return statusGIAITCDVo;
	}
	
	public String getNumeroDarFormatado()
	{
		if(Validador.isObjetoValido(getDarEmitido()))
		{
			if(Validador.isNumericoValido(getDarEmitido().getCodgUofSeqc()))
			{
				return getDarEmitido().getCodgUofSeqc().toString();
			}
			else if(Validador.isNumericoValido(getDarEmitido().getNumrDarSeqc()))
			{
				return getDarEmitido().getNumrDarSeqc().toString();
			}
		}
		return STRING_VAZIA;
	}
}
