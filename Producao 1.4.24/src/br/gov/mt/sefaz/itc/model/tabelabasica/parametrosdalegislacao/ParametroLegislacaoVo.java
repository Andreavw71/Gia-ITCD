package br.gov.mt.sefaz.itc.model.tabelabasica.parametrosdalegislacao;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoSequencia;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.log5.util.dominio.DomnTipoSequencia;
import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposParametroLegislacao;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import sefaz.mt.util.SefazDataHora;


/**
 * Classe responsável por encapsular os valores do objeto ParametroLegislacao (Value Object).
 * @author Daniel Balieiro
 * @version $Revision: 1.1.1.1 $
 */
 @AnotacaoTabelaClasse
 (
     nomeTabela = TabelasITC.TABELA_PARAMETRO_LEGISLACAO
     ,nomeAmigavel = "Parâmetro da Legislação"
     ,identificadores =
     {
			@AnotacaoIdentificador
			(
			    nomeAtributo = "codigo"
			    ,nomeColuna = CamposParametroLegislacao.CAMPO_CODIGO_PARAMETRO_LEGISLACAO
				 ,sequencia = @AnotacaoSequencia
				 (
				     nomeSequencia = SequencesITC.SEQUENCE_PARAMETRO_LEGISLACAO
				     ,tipoSequencia = DomnTipoSequencia.SEQUENCE
				 )
			)
     }
 )
public class ParametroLegislacaoVo extends EntidadeVo
{
	private AliquotaVo aliquotaVo;
	private DomnSimNao statusGia;
	private DomnStatusRegistro statusLegislacao;
	private java.util.Date dataAtualizacao;
	private java.util.Date dataFimVigencia;
	private java.util.Date dataInicioVigencia;
	private static final long serialVersionUID = Long.MAX_VALUE;
	private MultaVo multaVo;
	private int numeroLegislacao;
	private int anoLegislacao;
	private int numeroLegislacaoPrincipal;
	private int anoLegislacaoPrincipal;
	private DomnSimNao calculoCascata;
	private transient GIAITCDVo giaItcdVo;
	private transient boolean existeGia;

	/**
	 * Construtor Padrão (Vazio)
	 * @implemented by Daniel Balieiro
	 */
	public ParametroLegislacaoVo()
	{
		super();
	}

	/**
	 * Método que retorna a Chave Primária de um Parametro Legislação
	 * @return ParametroLegislacaoPk - Chave Primária
	 * @implemented by Daniel Balieiro
	 */
	public ParametroLegislacaoPk getPk()
	{
		return new ParametroLegislacaoPk(getCodigo());
	}

	/**
	 * Construtor que recebe um Parametro de Consulta
	 * @param parametroLegislacaoVo ParametroLegislacaoVo que será usado como Parametro de Consulta
	 * @implemented by Daniel Balieiro
	 */
	public ParametroLegislacaoVo(ParametroLegislacaoVo parametroLegislacaoVo)
	{
		super();
		setParametroConsulta(parametroLegislacaoVo);
	}

	/**
	 * Construtor que recebe uma Collection de ParametroLegislacaoVo
	 * @param collVo Coleção de ParametroLegislacaoVo
	 * @implemented by Daniel Balieiro
	 */
	public ParametroLegislacaoVo(Collection collVo)
	{
		super(collVo);
	}

	/**
	 * Construtor que recebe a Chave Primária
	 * @param codigo Chave Primária
	 * @implemented by Daniel Balieiro
	 */
	public ParametroLegislacaoVo(long codigo)
	{
		super(codigo);
	}
	
	public long getCodigo()
	{
		return super.getCodigo();
	}

	/**
	 * Retorna uma aliquota caso exista uma de isenção cadastrada. E null caso contrário.
	 * 
	 * @return  aliquota de isenção
	 * @implemented by Leandro Dorileo
	 */
	public AliquotaVo getAliquotaIsento()
	{
		AliquotaVo retorno = null;
		for (Iterator it = this.aliquotaVo.getCollVO().iterator(); it.hasNext(); )
		{
			AliquotaVo atual = (AliquotaVo) it.next();
			if (atual.getTipoIsencao().is(DomnSimNao.SIM))
			{
				retorno = atual;
				break;
			}
		}
		return retorno;
	}

	/**
	 * Retorna as aliquotas que não sejam de isenção(caso exista). E null caso não exista nenhuma
	 * aliquota normal.
	 *
	 * @return  lista de aliquotas normais(que não sejam de isenção)
	 * @implemented by Leandro Dorileo
	 */
	public Collection getAliquotaNormal()
	{
		Collection retorno = null;
		for (Iterator it = this.aliquotaVo.getCollVO().iterator(); it.hasNext(); )
		{
			AliquotaVo atual = (AliquotaVo) it.next();
			if (atual.getTipoIsencao().is(DomnSimNao.NAO))
			{
				if (retorno == null)
					retorno = new ArrayList();
				retorno.add(atual);
			}
		}
		return retorno;
	}

	/**
	 * Método para alterar o Número da Legislação
	 * @param numeroLegislacao Número da Legislação
	 * @implemented by Daniel Balieiro
	 */
	public void setNumeroLegislacao(int numeroLegislacao)
	{
		this.numeroLegislacao = numeroLegislacao;
	}

	/**
	 * Método que retorna o Número da Legislação
	 * @return long - Número da Legislação
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposParametroLegislacao.CAMPO_NUMERO_PARAMETRO_LEGISLACAO
	     ,obrigatorio = true
	 )
	public int getNumeroLegislacao()
	{
		return numeroLegislacao;
	}

	/**
	 *  Método que retorna o Número da Legislação Formatado
	 * @return String
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	public String getNumeroLegislacaoFormatado()
	{
		if (!Validador.isNumericoValido(getNumeroLegislacao()))
		{
			return STRING_VAZIA;
		}
		return String.valueOf(getNumeroLegislacao());
	}

	/**
	 * Método para alterar a Data de Inicio da Vigência
	 * @param dataInicioVigencia - Data de Inicio da Vigência
	 * @implemented by Daniel Balieiro
	 */
	public void setDataInicioVigencia(Date dataInicioVigencia)
	{
		this.dataInicioVigencia = dataInicioVigencia;
	}

	/**
	 * Método que retorna a Data de Inicio da Vigência
	 * @return Date - Data de Inicio da Vigência
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposParametroLegislacao.CAMPO_DATA_INICIO_VIGENCIA
	     ,obrigatorio = true
		  ,timestamp = false
	 )
	public Date getDataInicioVigencia()
	{
		return dataInicioVigencia;
	}

	/**
	 * Método utilizado para retornar a data de DataInicioVigencia formatada no formato dia/mês/ano.
	 * Retorna uma string contendo a data formatada em formato dia / mês / ano. Ex.: 27/02/2007 ou uma string vazia caso não seja uma data válida.
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 */
	public String getDataInicioVigenciaFormatada()
	{
		if (Validador.isDataValida(getDataInicioVigencia()))
		{
			return new SefazDataHora(getDataInicioVigencia()).formata("dd/MM/yyyy");
		}
		else
		{
			return "";
		}
	}

	/**
	 * Método para alterar a Data final da Vigência
	 * @param dataFimVigencia Data final da Vigência
	 * @implemented by Daniel Balieiro
	 */
	public void setDataFimVigencia(Date dataFimVigencia)
	{
		this.dataFimVigencia = dataFimVigencia;
	}

	/**
	 *  Método que retorna da Data final da Vigência
	 * @return Date - Data final da Vigência
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposParametroLegislacao.CAMPO_DATA_FINAL_VIGENCIA
	     ,obrigatorio = false
		  ,timestamp = false
	 )
	public Date getDataFimVigencia()
	{
		return dataFimVigencia;
	}

	/**
	 * Método utilizado para retornar a data de DataFimVigencia formatada no formato dia/mês/ano.
	 * Retorna uma string contendo a data formatada em formato dia / mês / ano. Ex.: 27/02/2007 ou uma string vazia caso não seja uma data válida.
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 */
	public String getDataFimVigenciaFormatada()
	{
		if (Validador.isDataValida(getDataFimVigencia()))
		{
			return new SefazDataHora(getDataFimVigencia()).formata("dd/MM/yyyy");
		}
		else
		{
			return "";
		}
	}

	/**
	 * Método para alterar o Status do Registro
	 * @param statusLegislacao Status do Registro
	 * @implemented by Daniel Balieiro
	 */
	public void setStatusLegislacao(DomnStatusRegistro statusLegislacao)
	{
		this.statusLegislacao = statusLegislacao;
	}

	/**
	 * Método que retorna o Status do Registro
	 * @return DomnStatusRegistro - Status do Registro
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposParametroLegislacao.CAMPO_STATUS_PARAMETRO_LEGISLACAO
	     ,obrigatorio = true
	 )
	public DomnStatusRegistro getStatusLegislacao()
	{
		if (!Validador.isDominioNumericoValido(statusLegislacao))
		{
			setStatusLegislacao(new DomnStatusRegistro(-1));
		}
		return statusLegislacao;
	}

	/**
	 * Método para modificar a Data de Atualização
	 *<br><b>  - Essa data é controlada pelo sistema </b>
	 * @param dataAtualizacao - Data de Atualização
	 * @implemented by Daniel Balieiro
	 */
	public void setDataAtualizacao(Date dataAtualizacao)
	{
		this.dataAtualizacao = dataAtualizacao;
	}

	/**
	 * Método que retorna a Data de Atualização
	 * @return Date - Data de Atualização
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposParametroLegislacao.CAMPO_DATA_ATUALIZACAO_BD
	     ,obrigatorio = true
		  ,timestamp = true
	 )
	public Date getDataAtualizacao()
	{
		return dataAtualizacao;
	}
	
	public String getDataAtualizacaoFormatada() 
	{
		if(Validador.isDataValida(getDataAtualizacao()))
		{
			return new SefazDataHora(getDataAtualizacao()).formata("dd/MM/yyyy");
		}
		else
		{
			return "";
		}
	}

	/**
	 * Atribui um MultaVo
	 * @param multaVo MultaVo
	 * @implemented by Daniel Balieiro
	 */
	public void setMultaVo(MultaVo multaVo)
	{
		this.multaVo = multaVo;
		if (Validador.isNumericoValido(getCodigo()))
		{
			for (Iterator iteMulta = multaVo.getCollVO().iterator(); iteMulta.hasNext(); )
			{
				MultaVo multaAtualVo = (MultaVo) iteMulta.next();
				multaAtualVo.setCodigoParametroLegislacao(getCodigo());
				if (Validador.isNumericoValido(getUsuarioLogado()))
				{
					multaAtualVo.setUsuarioLogado(getUsuarioLogado());
				}
			}
		}
	}

	/**
	 * Retorna uma MultaVo
	 * @return MultaVo 
	 * @implemented by Daniel Balieiro
	 */
	public MultaVo getMultaVo()
	{
		if (multaVo == null)
		{
			setMultaVo(new MultaVo());
		}
		return multaVo;
	}

	/**
	 * Atribui uma AliquotaVO
	 * @param aliquotaVo AliquotaVo
	 * @implemented by Daniel Balieiro
	 */
	public void setAliquotaVo(AliquotaVo aliquotaVo)
	{
		this.aliquotaVo = aliquotaVo;
		if (Validador.isNumericoValido(getCodigo()))
			;
		{
			for (Iterator iteAliquota = aliquotaVo.getCollVO().iterator(); iteAliquota.hasNext(); )
			{
				AliquotaVo aliquotaAtualVo = (AliquotaVo) iteAliquota.next();
				aliquotaAtualVo.setCodigoParametroLegislacao(getCodigo());
				if (Validador.isNumericoValido(getUsuarioLogado()))
				{
					aliquotaAtualVo.setUsuarioLogado(getUsuarioLogado());
				}
			}
		}
	}

	/**
	 * Retorna a AliquotaVo
	 * @return AliquotaVo
	 * @implemented by Daniel Balieiro
	 */
	public AliquotaVo getAliquotaVo()
	{
		if (aliquotaVo == null)
		{
			setAliquotaVo(new AliquotaVo());
		}
		return aliquotaVo;
	}

	/**
	 * Atribui um Status Gia
	 * @param statusGia
	 * @implemented by Daniel Balieiro
	 */
	public void setStatusGia(DomnSimNao statusGia)
	{
		this.statusGia = statusGia;
	}

	/**
	 * Retorna o Status Gia
	 * @return DomnSimNao
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposParametroLegislacao.CAMPO_STATUS_GIA
	     ,obrigatorio = true
	 )
	public DomnSimNao getStatusGia()
	{
		if (!Validador.isDominioNumericoValido(statusGia))
		{
			setStatusGia(new DomnSimNao(DomnSimNao.NAO));
		}
		return statusGia;
	}

	public void setCalculoCascata(DomnSimNao calculoCascata)
	{
		this.calculoCascata = calculoCascata;
	}

	public boolean isLegislacaoCascata()
	{
		return getCalculoCascata().is(DomnSimNao.SIM);
	}

	public String getCalculoCascataFormatado()
	{
		return calculoCascata.getTextoCorrente();
	}

	@AnotacaoAtributo
	(
		 nomeColuna = CamposParametroLegislacao.CAMPO_CALCULO_CASCATA
		 ,obrigatorio = true
	)
	public DomnSimNao getCalculoCascata()
	{
		if (!Validador.isDominioNumericoValido(calculoCascata))
		{
			calculoCascata = new DomnSimNao(-1);
		}
		return calculoCascata;
	}

	public void setAnoLegislacao(int anoLegislacao)
	{
		this.anoLegislacao = anoLegislacao;
	}

	@AnotacaoAtributo
	(
	    nomeColuna = CamposParametroLegislacao.CAMPO_ANO_LEGISLACAO
	    ,obrigatorio = true
	)
	public int getAnoLegislacao()
	{
		return anoLegislacao;
	}

	/**
	 *  Método que retorna o Ano Legislação Formatado
	 * @return String
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	public String getAnoLegislacaoFormatado()
	{
		if (!Validador.isNumericoValido(getAnoLegislacao()))
		{
			return STRING_VAZIA;
		}
		return String.valueOf(getAnoLegislacao());
	}

	public void setNumeroLegislacaoPrincipal(int numeroLegislacaoPrincipal)
	{
		this.numeroLegislacaoPrincipal = numeroLegislacaoPrincipal;
	}

	@AnotacaoAtributo
	(
	    nomeColuna = CamposParametroLegislacao.CAMPO_NUMERO_LEGISLACAO_PRINCIPAL
	    ,obrigatorio = false
	)
	public int getNumeroLegislacaoPrincipal()
	{
		return numeroLegislacaoPrincipal;
	}

	/**
	 *  Método que retorna o Número da Legislação Principal Formatado
	 * @return String
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	public String getNumeroLegislacaoPrincipalFormatado()
	{
		if (!Validador.isNumericoValido(getNumeroLegislacaoPrincipal()))
		{
			return STRING_VAZIA;
		}
		return String.valueOf(getNumeroLegislacaoPrincipal());
	}

	public void setAnoLegislacaoPrincipal(int anoLegislacaoPrincipal)
	{
		this.anoLegislacaoPrincipal = anoLegislacaoPrincipal;
	}

	@AnotacaoAtributo
	(
	    nomeColuna = CamposParametroLegislacao.CAMPO_ANO_LEGISLACAO_PRINCIPAL
	    ,obrigatorio = false
	)
	public int getAnoLegislacaoPrincipal()
	{
		return anoLegislacaoPrincipal;
	}

	/**
	 *  Método que retorna o Ano Legislação Principal Formatado
	 * @return String
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	public String getAnoLegislacaoPrincipalFormatado()
	{
		if (!Validador.isNumericoValido(getAnoLegislacaoPrincipal()))
		{
			return STRING_VAZIA;
		}
		return String.valueOf(getAnoLegislacaoPrincipal());
	}

	public void setGiaItcdVo(GIAITCDVo giaItcdVo)
	{
		this.giaItcdVo = giaItcdVo;
	}

	public GIAITCDVo getGiaItcdVo()
	{
		if (!Validador.isObjetoValido(giaItcdVo))
		{
			giaItcdVo = new GIAITCDVo();
		}
		return giaItcdVo;
	}

	public void setExisteGia(boolean isExisteGia)
	{
		this.existeGia = isExisteGia;
	}

	public boolean isExisteGia()
	{
		return existeGia;
	}
}
