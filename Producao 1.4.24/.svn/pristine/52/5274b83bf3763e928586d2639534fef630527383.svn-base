/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: BemVo.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 05/10/2007
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.bem;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoSequencia;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.log5.util.dominio.DomnTipoSequencia;
import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoBem;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProtocolo;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoVerificacao;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposBem;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.util.Collection;
import java.util.Date;

import sefaz.mt.util.SefazDataHora;


/**
 * Classe responsável por encapsular os valores do objeto Bem (Value Object).
 * @author Marlo Eichenberg Motta
 * @version $Revision: 1.3 $
 */
 @AnotacaoTabelaClasse
 (
     nomeTabela = TabelasITC.TABELA_BEM
     ,nomeAmigavel = "Bem"
     ,identificadores =
     {
			@AnotacaoIdentificador
			(
			    nomeAtributo = "codigo"
			    ,nomeColuna = CamposBem.CAMPO_CODIGO_BEM
				 ,sequencia = @AnotacaoSequencia
				 (
				     nomeSequencia = SequencesITC.SEQUENCE_BEM
				     ,tipoSequencia = DomnTipoSequencia.SEQUENCE
				 )
			)
     }
 )
public class BemVo extends EntidadeVo
{
	private Date dataAtualizacao;
	private DomnStatusRegistro statusBem;
	private DomnSimNao possuiConstrucao;
	private DomnTipoBem classificacaoTipoBem;
	private static final long serialVersionUID = Long.MAX_VALUE;
	private String descricaoTipoBem;
   private DomnTipoVerificacao tipoVerificacao;
   private DomnTipoProtocolo tipoProtocoloGIA;

	/**
	 * Construtor da classe.
	 * @param codigo Chave primária.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	public BemVo(long codigo)
	{
		super(codigo);
	}

	/**
	 * Construtor Vazio.
	 * @implemented by Daniel Balieiro
	 */
	public BemVo()
	{
		super();
	}

	/**
	 * Construtor que recebe um Parametro de Consulta.
	 * @param bemVo Bem (Value Object).
	 * @implemented by Daniel Balieiro
	 */
	public BemVo(BemVo bemVo)
	{
		super();
		setParametroConsulta(bemVo);
	}

	/**
	 * Construtor que recebe uma Collection.
	 * @param collVo Coleção de Value Objects.
	 * @implemented by Daniel Balieiro
	 */
	public BemVo(Collection collVo)
	{
		super(collVo);
	}

	/**
	 * @return
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	public long getCodigo()
	{
		return super.getCodigo();
	}

	/**
	 * Atribui uma descrição ao tipo de bem.
	 * @param descricaoTipoBem Descrição do tipo do Bem.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	public void setDescricaoTipoBem(String descricaoTipoBem)
	{
		if (Validador.isStringValida(descricaoTipoBem))
		{
			this.descricaoTipoBem = descricaoTipoBem.trim().toUpperCase();
		}
		else
		{
			this.descricaoTipoBem = descricaoTipoBem;
		}
	}

	/**
	 * Retorna a descrição do tipo do bem.
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposBem.CAMPO_DESCRICAO_BEM
	     ,obrigatorio = true
	 )
	public String getDescricaoTipoBem()
	{
		if (Validador.isStringValida(descricaoTipoBem))
		{
			return descricaoTipoBem;
		}
		else
		{
			return STRING_VAZIA;
		}
	}

	/**
	 * Retorna o status do bem.
	 * @return DomnStatusRegistro
	 * @implemented by Daniel Balieiro
	 * @implemented by Marlo Eichenberg Motta
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposBem.CAMPO_STATUS_BEM
	     ,obrigatorio = true
	 )
	public DomnStatusRegistro getStatusBem()
	{
		if (!Validador.isDominioNumericoValido(statusBem))
		{
			setStatusBem(new DomnStatusRegistro(-1));
		}
		return statusBem;
	}

	/**
	 * Atribui o status do bem.
	 * @param statusBem Status do Bem (DomnStatusRegistro).
	 * @implemented by Daniel Balieiro
	 * @implemented by Marlo Eichenberg Motta
	 */
	public void setStatusBem(DomnStatusRegistro statusBem)
	{
		this.statusBem = statusBem;
	}

	/**
	 * Retorna a classificação do tipo do Bem.
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposBem.CAMPO_TIPO_BEM
	     ,obrigatorio = true
	 )
	public DomnTipoBem getClassificacaoTipoBem()
	{
		if (!Validador.isDominioNumericoValido(classificacaoTipoBem))
		{
			setClassificacaoTipoBem(new DomnTipoBem(-1));
		}
		return classificacaoTipoBem;
	}

	/**
	 * Atribui uma classificação ao tipo do Bem.
	 * @param classificacaoTipoBem Classificacao do Tipo do Bem (DomnTipoBem).
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	public void setClassificacaoTipoBem(DomnTipoBem classificacaoTipoBem)
	{
		this.classificacaoTipoBem = classificacaoTipoBem;
	}

	/**
	 * Retorna a chave primária (PK).
	 * @return BemPk
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	public BemPk getPk()
	{
		return new BemPk(getCodigo());
	}

	/**
	 * Retorna a data de atualização da entidade.
	 * @return Date
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposBem.CAMPO_DATA_ATUALIZACAO_BD
	     ,obrigatorio = true
		  ,timestamp = true
	 )
	public Date getDataAtualizacao()
	{
		return dataAtualizacao;
	}

	/**
	 * Atribui a data de atualização da entidade.
	 * @param dataAtualizacao Data de atualização do registro no banco de dados.
	 * @implemented by Marlo Eichenberg Motta
	 * @implemented by Daniel Balieiro
	 */
	public void setDataAtualizacao(Date dataAtualizacao)
	{
		this.dataAtualizacao = dataAtualizacao;
	}

	/**
	 * Método utilizado para retornar a data de atualização formatada no formato dia/mês/ano.
	 * Retorna uma string contendo a data formatada em formato dia / mês / ano. Ex.: 27/02/2007 ou uma string vazia caso não seja uma data válida.
	 * @return
	 * @implemented by Marlo Eichenberg Motta
	 */
	public String getDataAtualizacaoFormatada()
	{
		if (Validador.isDataValida(getDataAtualizacao()))
		{
			return new SefazDataHora(getDataAtualizacao()).formata("dd/MM/yyyy");
		}
		else
		{
			return STRING_VAZIA;
		}
	}

	public void setPossuiConstrucao(DomnSimNao temConstrucao)
	{
		this.possuiConstrucao = temConstrucao;
	}

	@AnotacaoAtributo
	(
	    nomeColuna = CamposBem.CAMPO_POSSUI_CONSTRUCAO
	    ,obrigatorio = true
	)
	public DomnSimNao getPossuiConstrucao()
	{
		if(!Validador.isDominioNumericoValido(possuiConstrucao))
		{
			setPossuiConstrucao(new DomnSimNao(-1));
		}
		return possuiConstrucao;
	}
   
   @AnotacaoAtributo
   (
       nomeColuna = CamposBem.CAMPO_TIPO_VERIFICACAO
       ,obrigatorio = false
   )
   public DomnTipoVerificacao getTipoVerificacao()
   {
      if(!Validador.isDominioNumericoValido(tipoVerificacao))
      {
         setTipoVerificacao(new DomnTipoVerificacao(-1));
      }
      return tipoVerificacao;
   }  

   public void setTipoVerificacao(DomnTipoVerificacao tipoVerificacao)
   {
      this.tipoVerificacao = tipoVerificacao;
   }
   
   @AnotacaoAtributo
   (
       nomeColuna = CamposBem.CAMPO_TIPO_PROTOCOLO_GIA
       ,obrigatorio = false
   )
   public DomnTipoProtocolo getTipoProtocoloGIA()
   {
      if(!Validador.isDominioNumericoValido(tipoProtocoloGIA))
      {
         setTipoProtocoloGIA(new DomnTipoProtocolo(-1));
      }
      return tipoProtocoloGIA;
   }  

   public void setTipoProtocoloGIA(DomnTipoProtocolo tipoProtocoloGIA)
   {
      this.tipoProtocoloGIA = tipoProtocoloGIA;
   }
	
	public int hashCode()
	{
		int hashAtual = 0;
		int hashCodigo = (int) getCodigo();
		int hashPossuiConstrucao = getPossuiConstrucao().getValorCorrente();
		int hashClassificacaoTipoBem = getClassificacaoTipoBem().getValorCorrente();
//		int hashStatusBem = getStatusBem().getValorCorrente();
		int hashDescricaoBem = getDescricaoTipoBem().length();
		hashAtual += hashCodigo;
		hashAtual += hashPossuiConstrucao;
		hashAtual += hashClassificacaoTipoBem;
//		hashAtual += hashStatusBem;
	   hashAtual += hashDescricaoBem;
	   hashAtual *= MULTIPLICADOR_HASH_CODE;
		return hashAtual;
	}

	public boolean isExiste()
	{
		return !this.equals(new BemVo());
	}
	
	
}
