package br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoAtributoExterno;
import br.com.abaco.log5.anotacoes.AnotacaoColunaExterna;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoSequencia;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.log5.anotacoes.AnotacaoValorPadrao;
import br.com.abaco.log5.util.dominio.DomnTipoSequencia;
import br.com.abaco.log5.util.dominio.DomnTipoValorPadrao;
import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;

import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;

import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelVo;
import br.gov.mt.sefaz.itc.model.generico.fichaimovel.FichaImovelVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.benfeitoria.FichaImovelRuralBenfeitoriaVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.construcao.FichaImovelRuralConstrucaoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.cultura.FichaImovelRuralCulturaVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.rebanho.FichaImovelRuralRebanhoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.GIAITCDInventarioArrolamentoVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.basecalculoimovelrural.BaseCalculoImovelRuralVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.criteriomunicipio.CriterioMunicipioVo;
import br.gov.mt.sefaz.itc.util.calculo.CalculoITCD;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoGIA;
import br.gov.mt.sefaz.itc.util.dominio.DomnVersaoGIAITCD;
import br.gov.mt.sefaz.itc.util.facade.ConfiguracaoITCD;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposFichaImovelRural;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.EnderecoIntegracaoVo;

import java.sql.SQLException;

import java.util.Collection;
import java.util.Iterator;


/**
 * Classe responsável por encapsular os valores do objeto Ficha Imóvel Rural (Value Object).
 * 
 * @author Daniel Balieiro
 * @version $Revision: 1.3 $
 */
 @AnotacaoTabelaClasse
 (
     nomeTabela = TabelasITC.TABELA_FICHA_IMOVEL_RURAL
     ,nomeAmigavel = "Ficha Imóvel Rural"
     ,identificadores =
     {
			@AnotacaoIdentificador
			(
			    nomeAtributo = "codigo"
			    ,nomeColuna = CamposFichaImovelRural.CAMPO_CODIGO_IMOVEL_RURAL
				 ,sequencia = @AnotacaoSequencia
				 (
				     nomeSequencia = SequencesITC.SEQUENCE_FICHA_IMOVEL_RURAL 
				     ,tipoSequencia = DomnTipoSequencia.SEQUENCE
				 )
			)
     }
 )
public class FichaImovelRuralVo extends FichaImovelVo
{
	private static final long serialVersionUID = Long.MAX_VALUE;
	private String descricaoDenominacao;
	private double quantidadeDistancia;
	private double areaTotal;
	private long numericoIndea;
	private long codigoReceitaFederal;
	private DomnSimNao situacaoPastagem;
	private double areaPastagem;
    private double distanciaAsfalto;
	private double valorPastagem;
	private DomnSimNao situacaoAcessaoNatural;
	private double valorAcessaoNatural;
	private double valorMercadoImovel;
	private double valorMaquinaEquipamento;
	private double valorOutro;
	private double valorITR;
	private FichaImovelRuralConstrucaoVo fichaImovelRuralConstrucaoVo;
	private FichaImovelRuralBenfeitoriaVo fichaImovelRuralBenfeitoriaVo;
	private FichaImovelRuralCulturaVo fichaImovelRuralCulturaVo;
	private FichaImovelRuralRebanhoVo fichaImovelRuralRebanhoVo;
    private CriterioMunicipioVo criterioMunicipioVo;
    private BaseCalculoImovelRuralVo baseCalculoImovelRuralVo;
	private transient DomnSimNao flagConcordaValorArbitrado;
	
	private transient boolean vlrTributavelCalculado;
   
   //campo de informação para validação no jsp 
   private DomnVersaoGIAITCD numeroVersaoGIAITCD; 

	/**
	 * Construtor Padrão
	 * 
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralVo()
	{
		super();
	}

	/**
	 * Construtor que recebe o Código (Chave Primária) como parametro
	 * 
	 * @param codigo Chave Primária
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralVo(long codigo)
	{
		super(codigo);
	}

	/**
	 * Retorna a Chave Primária
	 * 
	 * @return FichaImovelRuralPk
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralPk getPk()
	{
		return new FichaImovelRuralPk(getCodigo());
	}

	/**
	 * Construtor que recebe o Parametro de Consulta
	 * 
	 * @param parametroConsulta
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralVo(FichaImovelRuralVo parametroConsulta)
	{
		super();
		setParametroConsulta(parametroConsulta);
	}

	/**
	 * Construtor que recebe uma Collection como parametro
	 * 
	 * @param collVo
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralVo(Collection collVo)
	{
		super(collVo);
	}
	
	public long getCodigo()
	{
		return super.getCodigo();
	}
	
	@AnotacaoAtributoExterno
	(
	    obrigatorio = true
	    ,colunaReferencia =
	    {
	        @AnotacaoColunaExterna
	        (
	            nomeColuna = CamposFichaImovelRural.CAMPO_ACCTB06_CODIGO_ENDERECO
	            , nomeAtributo = "codgEndereco"
	        )
	    }
	)	
	public EnderecoIntegracaoVo getEnderecoIntegracaoVo()
	{
		return super.getEnderecoVo();
	}
	
	@AnotacaoAtributoExterno
	(
	    obrigatorio = true
	    ,colunaReferencia =
	    {
	        @AnotacaoColunaExterna
	        (
	            nomeColuna = CamposFichaImovelRural.CAMPO_ITCTB18_CODIGO_ITCD_BEM_TRIBUTARIO
	            , nomeAtributo = "codigo"
	        )
	    }
	)
	public BemTributavelVo getBemTributavelVo()
	{
		return super.getBemTributavelVo();
	}

	/**
	 * Atribui o Valor Acessão Natural
	 * 
	 * @param valorAcessaoNatural
	 * @implemented by Daniel Balieiro
	 */
	public void setValorAcessaoNatural(double valorAcessaoNatural)
	{
		this.valorAcessaoNatural = valorAcessaoNatural;
	}

	/**
	 * Retorna o Valor Acessão Natural
	 * 
	 * @return double
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposFichaImovelRural.CAMPO_VALOR_ACESSAO_NATURAL
	     ,obrigatorio = false
	 )
	public double getValorAcessaoNatural()
	{
		return valorAcessaoNatural;
	}

	public String getValorAcessaoNaturalFormatado()
	{
		if (!Validador.isNumericoValido(getValorAcessaoNatural()))
			return STRING_VAZIA;
		return StringUtil.doubleToMonetario(getValorAcessaoNatural());
	}

	/**
	 * Atribui o Valor do ITR
	 * 
	 * @param valorITR
	 * @implemented by Daniel Balieiro
	 */
	public void setValorITR(double valorITR)
	{
		this.valorITR = valorITR;
	}

	/**
	 * Retorna o Valor do ITR
	 * 
	 * @return double
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposFichaImovelRural.CAMPO_VALOR_ITR
	     ,obrigatorio = false
	 )
	public double getValorITR()
	{
		return valorITR;
	}

	/**
	 * Retorna o Valor do ITR Formatado
	 * 
	 * @return String
	 * @implemented by João Batista Padilha e Silva
	 */
	public String getValorITRFormatado()
	{
		if (!Validador.isNumericoValido(getValorITR()))
			return STRING_VAZIA;
		return StringUtil.doubleToMonetario(getValorITR());
	}

	/**
	 * Atribui o Valor da Máquina Equipamento
	 * 
	 * @param valorMaquinaEquipamento
	 * @implemented by Daniel Balieiro
	 */
	public void setValorMaquinaEquipamento(double valorMaquinaEquipamento)
	{
		this.valorMaquinaEquipamento = valorMaquinaEquipamento;
	}

	/**
	 * Retorna o Valor da Máquina do Equipamento
	 * 
	 * @return double
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposFichaImovelRural.CAMPO_VALOR_MAQUINA_EQUIPAMENTO
	     ,obrigatorio = false
	 )
	public double getValorMaquinaEquipamento()
	{
		return valorMaquinaEquipamento;
	}

	/**
	 * Retorna o Valor da Máquina do Equipamento Formatado
	 * 
	 * @return String
	 * @implemented by João Batista Padilha e Silva
	 */
	public String getValorMaquinaEquipamentoFormatado()
	{
		if (!Validador.isNumericoValido(getValorMaquinaEquipamento()))
			return STRING_VAZIA;
		return StringUtil.doubleToMonetario(valorMaquinaEquipamento);
	}

	/**
	 * Atribui o Valor de Mercado do Imóvel
	 * 
	 * @param valorMercadoImovel
	 * @implemented by Daniel Balieiro
	 */
	public void setValorMercadoImovel(double valorMercadoImovel)
	{
		this.valorMercadoImovel = valorMercadoImovel;
	}

	/**
	 * Retorna o Valor de Mercado de Imóvel
	 * 
	 * @return double
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposFichaImovelRural.CAMPO_VALOR_MERCADO_IMOVEL
	     ,obrigatorio = true
	 )
	public double getValorMercadoImovel()
	{
		return valorMercadoImovel;
	}

	/**
	 * Retorna o Valor Tributável
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	public double getValorTributavel()
      throws SQLException, ConsultaException, ObjetoObrigatorioException, 
             ParametroObrigatorioException, ConexaoException
   {
	  double valorTributavel = 0.0;
   
      if(getAreaTotal() >= 0 && getDistanciaAsfalto() >= 0 && getQuantidadeDistancia() >= 0 && getEnderecoIntegracaoVo() != null && getValorMercadoImovel() != 0)
         valorTributavel = CalculoITCD.calcularValorTributavelFichaImovelRural(this);
		 
      return valorTributavel;
	}

	/**
	 * Retorna o Valor Tributável Formatado
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	public String getValorTributavelFormatado()
      throws SQLException, ConsultaException, ObjetoObrigatorioException, 
             ParametroObrigatorioException, ConexaoException
   {
      if(getValorTributavel() != 0)
         return StringUtil.doubleToMonetario(getValorTributavel());
         
      return STRING_VAZIA;
	}

	/**
	 * Retorna o Valor Total da Ficha
	 * 
	 * @return double
	 * @implemented by Daniel Balieiro
	 */
	public double getValorTotalMercado()
	{
		double total = 0;
		if (!(getBemTributavelVo().getGiaITCDVo().getTipoGIA().is(DomnTipoGIA.CAUSA_MORTIS) && 
				 (!((GIAITCDInventarioArrolamentoVo) getBemTributavelVo().getGiaITCDVo()).getUfAbertura().getSiglUf().equals(ConfiguracaoITCD.SIGLA_ESTADO_MATO_GROSSO))))
		{
			total += getFichaImovelRuralRebanhoVo().getValorTotalRebanhos();
			total += getValorMaquinaEquipamento();
			total += getValorOutro();
		}
		total += getFichaImovelRuralConstrucaoVo().getValorTotalBens();
		total += getFichaImovelRuralCulturaVo().getValorTotalCulturas();
		total += getValorAcessaoNatural();
		total += getValorPastagem();
		total += getValorMercadoImovel();
		getBemTributavelVo().setValorMercado(total);
      if(getValorITR() > total){
         getBemTributavelVo().setValorMercado(getValorITR());
		 setVlrTributavelCalculado(true);
         return getValorITR();
      }
      return total;
	}

	public String getValorTotalMercadoFormatado()
	{
		if (!Validador.isNumericoValido(getValorTotalMercado()))
			return STRING_VAZIA;
		return StringUtil.doubleToMonetario(getValorTotalMercado());
	}

	/**
	 * Retorna o Total de Valores que foi retirado do Total de Mercado, caso a GIA ITCD for do tipo Causa Mortis e o Estado de Abertura não for Mato Grosso
	 * 
	 * @return double
	 * @implemented by Daniel Balieiro
	 */
	public double getValorTotalIsentos()
	{
		double total = 0;
		if ((getBemTributavelVo().getGiaITCDVo().getTipoGIA().is(DomnTipoGIA.CAUSA_MORTIS) && 
				 (!((GIAITCDInventarioArrolamentoVo) getBemTributavelVo().getGiaITCDVo()).getUfAbertura().getSiglUf().equals("MT"))))
		{
			total += getFichaImovelRuralRebanhoVo().getValorTotalRebanhos();
			total += getValorMaquinaEquipamento();
			total += getValorOutro();
		}
		return total;
	}

	public String getValorTotalIsentosFormatado()
	{
		if (!Validador.isNumericoValido(getValorTotalIsentos()))
			return STRING_VAZIA;
		return StringUtil.doubleToMonetario(getValorTotalIsentos());
	}

	/**
	 * Retorna o Valor de Mercado de Imóvel Formatado
	 * 
	 * @return String
	 * @implemented by João Batista Padilha e Silva
	 */
	public String getValorMercadoImovelFormatado()
	{
		if (!Validador.isNumericoValido(getValorMercadoImovel()))
			return STRING_VAZIA;
		return StringUtil.doubleToMonetario(getValorMercadoImovel());
	}

	/**
	 * Atribui o Valor do Outro
	 * 
	 * @param valorOutro
	 * @implemented by Daniel Balieiro
	 */
	public void setValorOutro(double valorOutro)
	{
		this.valorOutro = valorOutro;
	}

	/**
	 * Retorna o Valor do Outro
	 * 
	 * @return double
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposFichaImovelRural.CAMPO_VALOR_OUTRO
	     ,obrigatorio = false
	 )
	public double getValorOutro()
	{
		return valorOutro;
	}

	/**
	 * Retorna o Valor do Outro Formatado
	 * 
	 * @return String
	 * @implemented by João Batista Padilha e Silva
	 */
	public String getValorOutroFormatado()
	{
		if (!Validador.isNumericoValido(getValorOutro()))
			return STRING_VAZIA;
		return StringUtil.doubleToMonetario(valorOutro);
	}

	/**
	 * Atribui o Valor de Pastagem
	 * 
	 * @param valorPastagem
	 * @implemented by Daniel Balieiro
	 */
	public void setValorPastagem(double valorPastagem)
	{
		this.valorPastagem = valorPastagem;
	}

	/**
	 * Retorna o Valor da Pastagem
	 * 
	 * @return double
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposFichaImovelRural.CAMPO_VALOR_PASTAGEM
	     ,obrigatorio = false
	 )
	public double getValorPastagem()
	{
		return valorPastagem;
	}

	/**
	 * Retorna o Valor da Pastagem Formatado
	 * 
	 * @return String
	 * @implemented by João Batista Padilha e Silva
	 */
	public String getValorPastagemFormatado()
	{
		if (!Validador.isNumericoValido(getValorPastagem()))
			return STRING_VAZIA;
		return StringUtil.doubleToMonetario(valorPastagem);
	}

	/**
	 * Atribui a Ficha de Imóvel Rural - Bem
	 * 
	 * @param fichaImovelRuralConstrucaoVo
	 * @implemented by Daniel Balieiro
	 */
	public void setFichaImovelRuralConstrucaoVo(FichaImovelRuralConstrucaoVo fichaImovelRuralConstrucaoVo)
	{
		if (Validador.isObjetoValido(fichaImovelRuralConstrucaoVo))
		{
			fichaImovelRuralConstrucaoVo.setUsuarioLogado(this.getUsuarioLogado());
			for (Iterator iteConstrucao = fichaImovelRuralConstrucaoVo.getCollVO().iterator(); iteConstrucao.hasNext(); )
			{
				((FichaImovelRuralConstrucaoVo) iteConstrucao.next()).setUsuarioLogado(this.getUsuarioLogado());
			}
		}
		this.fichaImovelRuralConstrucaoVo = fichaImovelRuralConstrucaoVo;
	}

	/**
	 * Retorna a Ficha de Imovel Rural - Bem
	 * 
	 * @return FichaImovelRuralBemVo
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralConstrucaoVo getFichaImovelRuralConstrucaoVo()
	{
		if (fichaImovelRuralConstrucaoVo == null)
		{
			setFichaImovelRuralConstrucaoVo(new FichaImovelRuralConstrucaoVo());
		}
		return fichaImovelRuralConstrucaoVo;
	}

	/**
	 * Atribui a Ficha de Imóvel Rural - Benfeitoria
	 * 
	 * @param fichaImovelRuralBenfeitoriaVo
	 * @implemented by Daniel Balieiro
	 */
	public void setFichaImovelRuralBenfeitoriaVo(FichaImovelRuralBenfeitoriaVo fichaImovelRuralBenfeitoriaVo)
	{
		if (Validador.isObjetoValido(fichaImovelRuralBenfeitoriaVo))
		{
			fichaImovelRuralBenfeitoriaVo.setUsuarioLogado(this.getUsuarioLogado());
			for (Iterator iteBenfeitoria = 
							fichaImovelRuralBenfeitoriaVo.getCollVO().iterator(); iteBenfeitoria.hasNext(); )
			{
				((FichaImovelRuralBenfeitoriaVo) iteBenfeitoria.next()).setUsuarioLogado(this.getUsuarioLogado());
			}
		}
		this.fichaImovelRuralBenfeitoriaVo = fichaImovelRuralBenfeitoriaVo;
	}

	/**
	 * Retorna a Ficha de Imóvel Rural - Benfeitoria
	 * 
	 * @return FichaImovelRuralBenfeitoriaVo
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralBenfeitoriaVo getFichaImovelRuralBenfeitoriaVo()
	{
		if (fichaImovelRuralBenfeitoriaVo == null)
		{
			setFichaImovelRuralBenfeitoriaVo(new FichaImovelRuralBenfeitoriaVo());
		}
		return fichaImovelRuralBenfeitoriaVo;
	}

	/**
	 * Atribui a Ficha Imóvel Rural - Cultura
	 * 
	 * @param fichaImovelRuralCulturaVo
	 * @implemented by Daniel Balieiro
	 */
	public void setFichaImovelRuralCulturaVo(FichaImovelRuralCulturaVo fichaImovelRuralCulturaVo)
	{
		if (Validador.isObjetoValido(fichaImovelRuralCulturaVo))
		{
			fichaImovelRuralCulturaVo.setUsuarioLogado(this.getUsuarioLogado());
			for (Iterator iteCultura = fichaImovelRuralCulturaVo.getCollVO().iterator(); iteCultura.hasNext(); )
			{
				((FichaImovelRuralCulturaVo) iteCultura.next()).setUsuarioLogado(this.getUsuarioLogado());
			}
		}
		this.fichaImovelRuralCulturaVo = fichaImovelRuralCulturaVo;
	}

	/**
	 * Retornar a Ficha Imóvel Rural - Cultura
	 * 
	 * @return FichaImovelRuralCulturaVo
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralCulturaVo getFichaImovelRuralCulturaVo()
	{
		if (fichaImovelRuralCulturaVo == null)
		{
			setFichaImovelRuralCulturaVo(new FichaImovelRuralCulturaVo());
		}
		return fichaImovelRuralCulturaVo;
	}

	/**
	 * Atribui a Ficha Imóvel Rural - Rebanho
	 * 
	 * @param fichaImovelRuralRebanhoVo
	 * @implemented by Daniel Balieiro
	 */
	public void setFichaImovelRuralRebanhoVo(FichaImovelRuralRebanhoVo fichaImovelRuralRebanhoVo)
	{
		if (Validador.isObjetoValido(fichaImovelRuralRebanhoVo))
		{
			fichaImovelRuralRebanhoVo.setUsuarioLogado(this.getUsuarioLogado());
			for (Iterator iteRebanho = fichaImovelRuralRebanhoVo.getCollVO().iterator(); iteRebanho.hasNext(); )
			{
				((FichaImovelRuralRebanhoVo) iteRebanho.next()).setUsuarioLogado(this.getUsuarioLogado());
			}
		}
		this.fichaImovelRuralRebanhoVo = fichaImovelRuralRebanhoVo;
	}

	/**
	 * Retorna a Ficha Imóvel Rural - Rebanho
	 * 
	 * @return FichaImovelRuralRebanhoVo
	 * @implemented by Daniel Balieiro
	 */
	public FichaImovelRuralRebanhoVo getFichaImovelRuralRebanhoVo()
	{
		if (fichaImovelRuralRebanhoVo == null)
		{
			setFichaImovelRuralRebanhoVo(new FichaImovelRuralRebanhoVo());
		}
		return fichaImovelRuralRebanhoVo;
	}

	/**
	 * Atribui a Área de Pastagem
	 * 
	 * @param areaPastagem
	 * @implemented by Daniel Balieiro
	 */
	public void setAreaPastagem(double areaPastagem)
	{
		this.areaPastagem = areaPastagem;
	}

	/**
	 * Retorna a Área de Pastagem
	 * 
	 * @return long
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposFichaImovelRural.CAMPO_AREA_PASTAGEM
	     ,obrigatorio = false
	 )
	public double getAreaPastagem()
	{
		return areaPastagem;
	}

	public String getAreaPastagemFormatado()
	{
		if (!Validador.isNumericoValido(getAreaPastagem()))
		{
			return STRING_VAZIA;
		}
		return StringUtil.doubleToMonetario(getAreaPastagem(),4);
	}
   
   /**
    * Retorna a a Distancia de Via Asfaltada
    * 
    * @return long
    * @implemented by Dherkyan Ribeiro
    */
    @AnotacaoAtributo
    (
        nomeColuna = CamposFichaImovelRural.CAMPO_QTDE_DISTANCIA_ASFALTO
        ,obrigatorio = false
    )
   public double getDistanciaAsfalto()
   {
      return distanciaAsfalto;
   }
   
   /**
    * Atribui a Distancia de Via Asfaltada
    * 
    * @param distanciaAsfalto
    * @implemented by Dherkyan Ribeiro
    */
   public void setDistanciaAsfalto(double distanciaAsfalto)
   {
      this.distanciaAsfalto = distanciaAsfalto;
   }
   
   /**
    * Retorna a a Distancia de Via Asfaltada Formatado.
    * 
    * @return String
    * @implemented by Dherkyan Ribeiro
    */
   public String getDistanciaAsfaltoFormatado()
   {
      //if (!Validador.isNumericoValido(getDistanciaAsfalto()))
      //return STRING_VAZIA;
      return StringUtil.doubleToMonetario(getDistanciaAsfalto(),4);
   }
   
   
	/**
	 * Atribui a Área Total
	 * 
	 * @param areaTotal
	 * @implemented by Daniel Balieiro
	 */
	public void setAreaTotal(double areaTotal)
	{
		this.areaTotal = areaTotal;
	}

	/**
	 * Retorna a Área Total
	 * 
	 * @return long
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposFichaImovelRural.CAMPO_AREA_TOTAL
	     ,obrigatorio = true
	 )
	public double getAreaTotal()
	{
		return areaTotal;
	}

	public String getAreaTotalFormatado()
	{
		if (!Validador.isNumericoValido(getAreaTotal()))
			return STRING_VAZIA;
		return StringUtil.doubleToMonetario(getAreaTotal(),4);
	}

	/**
	 * Atribui o Código da Receita Federal
	 * 
	 * @param codigoReceitaFederal
	 * @implemented by Daniel Balieiro
	 */
	public void setCodigoReceitaFederal(long codigoReceitaFederal)
	{
		this.codigoReceitaFederal = codigoReceitaFederal;
	}

	/**
	 * Retorna o Código da Receita Federal
	 * 
	 * @return long
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposFichaImovelRural.CAMPO_CODIGO_RECEITA_FEDERAL
	     ,obrigatorio = false
	 )
	public long getCodigoReceitaFederal()
	{
		return codigoReceitaFederal;
	}

	public String getCodigoReceitaFederalFormatado()
	{
		if (!Validador.isNumericoValido(getCodigoReceitaFederal()))
			return STRING_VAZIA;
		return String.valueOf(getCodigoReceitaFederal());
	}

	/**
	 * Atribui o Número Indea
	 * 
	 * @param numeroIndea
	 * @implemented by Daniel Balieiro
	 */
	public void setNumericoIndea(long numeroIndea)
	{
		this.numericoIndea = numeroIndea;
	}

	/**
	 * Retorna o Número Indea
	 * 
	 * @return long
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposFichaImovelRural.CAMPO_NUMERICO_INDEA
	     ,obrigatorio = false
	 )
	public long getNumericoIndea()
	{
		return numericoIndea;
	}

	public String getNumericoIndeaFormatado()
	{
		if (!Validador.isNumericoValido(getNumericoIndea()))
			return STRING_VAZIA;
		return String.valueOf(getNumericoIndea());
	}

	/**
	 * Atribui a Descrição de Denominação
	 * 
	 * @param descricaoDenominacao
	 * @implemented by Daniel Balieiro
	 */
	public void setDescricaoDenominacao(String descricaoDenominacao)
	{
		if (Validador.isStringValida(descricaoDenominacao))
			this.descricaoDenominacao = descricaoDenominacao.trim().toUpperCase();
		else
			this.descricaoDenominacao = descricaoDenominacao;
	}

	/**
	 * Retorna a Descrição de Denominação
	 * 
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposFichaImovelRural.CAMPO_DESCRICAO_DENOMINACAO
	     ,obrigatorio = true
	 )
	public String getDescricaoDenominacao()
	{
		if (!Validador.isStringValida(descricaoDenominacao))
			return STRING_VAZIA;
		return descricaoDenominacao;
	}

	/**
	 * Atribui a Quantidade da Distancia
	 * 
	 * @param quantidadeDistancia
	 * @implemented by Daniel Balieiro
	 */
	public void setQuantidadeDistancia(double quantidadeDistancia)
	{
		this.quantidadeDistancia = quantidadeDistancia;
	}

	/**
	 * Retorna a Quantidade da Distancia
	 * 
	 * @return int
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposFichaImovelRural.CAMPO_QUANTIDADE_DISTANCIA
	     ,obrigatorio = false, valorPadrao = @AnotacaoValorPadrao(tipoValorPadrao = DomnTipoValorPadrao.DOUBLE)
	 )
	public double getQuantidadeDistancia()
	{
		return quantidadeDistancia;
	}

	public String getQuantidadeDistanciaFormatado()
	{
		//if (!Validador.isNumericoValido(getQuantidadeDistancia()))
      //return STRING_VAZIA;
		return StringUtil.doubleToMonetario(getQuantidadeDistancia(),4);
	}

	/**
	 * Atribui a Situação de Acessão Natural
	 * 
	 * @param situacaoAcessaoNatural
	 * @implemented by Daniel Balieiro
	 */
	public void setSituacaoAcessaoNatural(DomnSimNao situacaoAcessaoNatural)
	{
		this.situacaoAcessaoNatural = situacaoAcessaoNatural;
	}

	/**
	 * Retorna a Situação de Acessão Natural
	 * 
	 * @return DomnSimNao
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposFichaImovelRural.CAMPO_SITUACAO_ACESSAO_NATURAL
	     ,obrigatorio = true
	 )
	public DomnSimNao getSituacaoAcessaoNatural()
	{
		if (!Validador.isDominioNumericoValido(situacaoAcessaoNatural))
			setSituacaoAcessaoNatural(new DomnSimNao(-1));
		return situacaoAcessaoNatural;
	}

	/**
	 * Atribui a Situação de Pastagem
	 * 
	 * @param situacaoPastagem
	 * @implemented by Daniel Balieiro
	 */
	public void setSituacaoPastagem(DomnSimNao situacaoPastagem)
	{
		this.situacaoPastagem = situacaoPastagem;
	}

	/**
	 * Retorna a Situação da Pastagem
	 * 
	 * @return DomnSimNao
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposFichaImovelRural.CAMPO_SITUACAO_PASTAGEM
	     ,obrigatorio = true
	 )
	public DomnSimNao getSituacaoPastagem()
	{
		if (!Validador.isDominioNumericoValido(situacaoPastagem))
			setSituacaoPastagem(new DomnSimNao(-1));
		return situacaoPastagem;
	}


   public void setCriterioMunicipioVo(CriterioMunicipioVo criterioMunicipioVo)
   {
      this.criterioMunicipioVo = criterioMunicipioVo;
   }

   @AnotacaoAtributoExterno
   (
       obrigatorio = false
       ,colunaReferencia =
       {
           @AnotacaoColunaExterna
           (
               nomeColuna = CamposFichaImovelRural.CAMPO_NUMR_SEQC_CRIT_MUNC
               , nomeAtributo = "codigo"
           )
       }
   )
   public CriterioMunicipioVo getCriterioMunicipioVo()
   {
      if (!Validador.isObjetoValido(this.criterioMunicipioVo))
      {
         setCriterioMunicipioVo(new CriterioMunicipioVo());
      }
      return criterioMunicipioVo;
   }


   public void setBaseCalculoImovelRuralVo(BaseCalculoImovelRuralVo baseCalculoImovelRuralVo)
   {
      this.baseCalculoImovelRuralVo = baseCalculoImovelRuralVo;
   }

   @AnotacaoAtributoExterno
   (
       obrigatorio = false
       ,colunaReferencia =
       {
           @AnotacaoColunaExterna
           (
               nomeColuna = CamposFichaImovelRural.CAMPO_NUMR_SEQC_BASE_CALC
               , nomeAtributo = "codigo"
           )
       }
   )
   public BaseCalculoImovelRuralVo getBaseCalculoImovelRuralVo()
   {
      if (!Validador.isObjetoValido(this.baseCalculoImovelRuralVo))
      {
         setBaseCalculoImovelRuralVo(new BaseCalculoImovelRuralVo());
      }
      return baseCalculoImovelRuralVo;
   }

	public void setFlagConcordaValorArbitrado(DomnSimNao flagConcordaValorArbitrado)
	{
		this.flagConcordaValorArbitrado = flagConcordaValorArbitrado;
	}

	public DomnSimNao getFlagConcordaValorArbitrado()
	{
		return flagConcordaValorArbitrado;
	}

	public boolean isVlrTributavelCalculado()
	{
		return vlrTributavelCalculado;
	}

	public void setVlrTributavelCalculado(boolean vlrTributavelCalculado)
	{
		this.vlrTributavelCalculado = vlrTributavelCalculado;
	}
   
   public DomnVersaoGIAITCD getNumeroVersaoGIAITCD()
   {
      if (!Validador.isDominioNumericoValido(numeroVersaoGIAITCD))
      {
         setNumeroVersaoGIAITCD( new DomnVersaoGIAITCD(-1));       
      }
      return numeroVersaoGIAITCD;
   }

   public void setNumeroVersaoGIAITCD(DomnVersaoGIAITCD versaoGIAITCD)
   {
      this.numeroVersaoGIAITCD = versaoGIAITCD;
   }
   
}
