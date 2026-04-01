/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: GIAITCDTemporarioVo.java
 * Revisão: Marlo Einchenberg Motta
 * Data revisão: 10/12/2007
 */
package br.gov.mt.sefaz.itc.model.generico.giaitcdtemporario;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoAtributoExterno;
import br.com.abaco.log5.anotacoes.AnotacaoColunaExterna;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoSequencia;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.log5.util.dominio.DomnTipoSequencia;
import br.com.abaco.util.Validador;

import br.com.abaco.util.exceptions.ParametroObrigatorioException;

import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd.StatusGIAITCDVo;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnSituacaoProcessamento;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProtocolo;
import br.gov.mt.sefaz.itc.util.dominio.DomnVersaoGIAITCD;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposGIAITCDTemporario;
import br.gov.mt.sefaz.itc.util.facade.tabelas.SequencesITC;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.UFIntegracaoVO;

import br.gov.mt.sefaz.itc.util.log.ExibirLOG;

import br.gov.mt.sefaz.itc.util.servico.automatico.ProtocolarGerarDarAvaliacaoAutomatico;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import com.thoughtworks.xstream.mapper.Mapper;
import com.thoughtworks.xstream.mapper.MapperWrapper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.Collection;
import java.util.Date;

import sefaz.mt.util.SefazDataHora;


/**
 * Classe responsável por encapsular os valores temporários do objeto GIA ITCD (Value Object).
 * @author Daniel Balieiro
 * @version $Revision: 1.1.1.1 $
 */
 @AnotacaoTabelaClasse
 (
     nomeTabela = TabelasITC.TABELA_GIA_ITCD_TEMPORARIO
     ,nomeAmigavel = "GIA-ITCD Temporário"
     ,identificadores =
     {
			@AnotacaoIdentificador
			(
			    nomeAtributo = "codigo"
			    ,nomeColuna = CamposGIAITCDTemporario.CAMPO_CODIGO_GIA_ITCD_TEMPORARIO
				 ,sequencia = @AnotacaoSequencia
				 (
				     nomeSequencia = SequencesITC.SEQUENCE_GIA_ITCD
				     ,tipoSequencia = DomnTipoSequencia.SEQUENCE
				 )
			)
     }
 )
public class GIAITCDTemporarioVo extends EntidadeVo<GIAITCDTemporarioVo>
{
	private static final long serialVersionUID = Long.MAX_VALUE;
	private Date dataGIAITCDTemporario;
	private GIAITCDVo giaitcdVo;
	private InputStream inputStream;
	private int sizeInputStream;
	private StatusGIAITCDVo statusITCD;
	private String senhaGIAITCD;
	private String codigoAutenticidade;
	private Date prazoProtocolar;
	private long codigoResponsavel;
	private DomnSimNao giaConfirmada;
	private Date dataAtualizacao;
   private String giaTempXML;
   private DomnVersaoGIAITCD numeroVersaoGIAITCD;
   private DomnTipoProtocolo tipoProtocoloGIA;
   private transient DomnSimNao alterarParaPendenteProtocolo;
   private DomnSituacaoProcessamento situacaoProcessamento;
   private String descricaoMensagemSituacaoErrro;
   
   private double porcentagemAconsiderar;
   
   
	/**
	 * Construtor Padrão.
	 * @implemented by Daniel Balieiro
	 */
	public GIAITCDTemporarioVo()
	{
		super();
	}

	/**
	 * Construtor que recebe a Chave Primária.
	 * @param codigo Chave primária.
	 * @implemented by Daniel Balieiro
	 */
	public GIAITCDTemporarioVo(long codigo)
	{
		super(codigo);
	}

	/**
	 * Construtor que recebe o Parametro de Consulta.
	 * @param giaitcdVo
	 * @implemented by Daniel Balieiro
	 */
	public GIAITCDTemporarioVo(GIAITCDTemporarioVo giaitcdVo)
	{
		super();
		setParametroConsulta(giaitcdVo);
	}

	/**
	 * Construtor que recebe uma Coleção de GIAITCDVo.
	 * @param collVO
	 * @implemented by Daniel Balieiro
	 */
	public GIAITCDTemporarioVo(Collection collVO)
	{
		super(collVO);
	}

	/**
	 * Retorna a Chave Primária.
	 * @return GIAITCDTemporarioPk
	 * @implemented by Daniel Balieiro
	 */
	public GIAITCDTemporarioPk getPk()
	{
		return new GIAITCDTemporarioPk(getCodigo());
	}

	/**
	 * Atribui a Data do GIA ITCD Temporário.
	 * @param dataGIAITCDTemporario
	 * @implemented by Daniel Balieiro
	 */
	public void setDataGIAITCDTemporario(Date dataGIAITCDTemporario)
	{
		this.dataGIAITCDTemporario = dataGIAITCDTemporario;
	}

	/**
	 * Retorna a Data do GIA ITCD Temporário.
	 * @return Date
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposGIAITCDTemporario.CAMPO_DATA_CRIACAO
	     ,obrigatorio = true
	 )
	public Date getDataGIAITCDTemporario()
	{
		return dataGIAITCDTemporario;
	}

	/**
	 * Retorna a Data do GIA ITCD Temporário Formatada.
	 * @return String
	 * @implemented by Daniel Balieiro
	 */
	public String getDataGIAITCDTemporarioFormatada()
	{
		if (Validador.isDataValida(getDataGIAITCDTemporario()))
		{
			return new SefazDataHora(getDataGIAITCDTemporario()).formata("dd/MM/yyyy");
		}
		else
		{
			return STRING_VAZIA;
		}
	}

	/**
	 * Atribui a Senha do GIA ITCD.
	 * @param senhaGIAITCD
	 * @implemented by Daniel Balieiro
	 */
	public void setSenhaGIAITCD(String senhaGIAITCD)
	{
		this.senhaGIAITCD = senhaGIAITCD;
	}

	/**
	 * Retorna a Senha do GIA ITCD.
	 * @return String
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposGIAITCDTemporario.CAMPO_INFO_SENHA
	     ,obrigatorio = true
	 )
	public String getSenhaGIAITCD()
	{
		if (!Validador.isStringValida(senhaGIAITCD))
		{
			return STRING_VAZIA;
		}
		return senhaGIAITCD;
	}

	/**
	 * Atribui uma GIA ITCD Vo.
	 * @param giaitcdVo
	 * @implemented by Daniel Balieiro
	 */
	public void setGiaitcdVo(GIAITCDVo giaitcdVo)
	{
		this.giaitcdVo = giaitcdVo;
		if (giaitcdVo != null)
		{
			if (Validador.isNumericoValido(this.getCodigo()))
			{
				giaitcdVo.setCodigo(this.getCodigo());
			}
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos;
			try
			{
				oos = new ObjectOutputStream(baos);
				oos.writeObject(giaitcdVo);
				oos.flush();
				oos.close();
				setSizeInputStream(baos.size());
				setInputStream(new ByteArrayInputStream(baos.toByteArray()));
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * Retorn ao GIA ITCD Vo
	 * @return GIAITCDVo
	 * @implemented by Daniel Balieiro
	 */
	public GIAITCDVo getGiaitcdVo()
	{  
	   GIAITCDVo giaXML = null;
      if(giaitcdVo == null)
      {
         giaXML = convertXMLParaGIA();
      }
		if ((inputStream != null || giaXML != null) && giaitcdVo == null)
		{
			ObjectInputStream ois;
			try
			{
            if (!Validador.isObjetoValido(giaitcdVo))
            {  
               if (giaXML != null)
               {  
                  ExibirLOG.exibirLog( "Esta GIA utilizou XML para conversão." , this.getCodigo() );
                  setGiaitcdVo( giaXML );
               }else
               {
                  ExibirLOG.exibirLog( "Esta GIA utilizou BLOB para conversão." , this.getCodigo() );
                  ois = new ObjectInputStream(inputStream);
                  setGiaitcdVo((GIAITCDVo) ois.readObject());
               }
            }
         }             			
			catch (EOFException e)
			{
				if (giaitcdVo.isExiste())
				{
					setGiaitcdVo(giaitcdVo);
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
		}
		if (!Validador.isObjetoValido(giaitcdVo))
		{
			setGiaitcdVo(new GIAITCDVo());
		}
		giaitcdVo.setTemporarioVo(this);
		return giaitcdVo;
	}

	/**
	 * Atribui um Status a GIA ITD Temporária.
	 * @param statusITCD
	 * @implemented by Daniel Balieiro
	 */
	public void setStatusITCD(StatusGIAITCDVo statusITCD)
	{
		this.statusITCD = statusITCD;
	}

	/**
	 * Retorna Status a GIA ITD Temporária.
	 * @return int
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributoExterno
	 (
	     obrigatorio = true
	     ,colunaReferencia =
	     {
	         @AnotacaoColunaExterna
	         (
	             nomeColuna = CamposGIAITCDTemporario.CAMPO_STATUS_ITCD
	             , nomeAtributo = "statusGIAITCD"
	         )
	     }
	 )
	public StatusGIAITCDVo getStatusITCD()
	{
		if (!Validador.isObjetoValido(statusITCD))
		{
			setStatusITCD(new StatusGIAITCDVo());
		}
		return statusITCD;
	}

	/**
	 * Atribui o Input Stream do Objeto Temporário.
	 * @param inputStream
	 * @implemented by Daniel Balieiro
	 */
	public void setInputStream(InputStream inputStream)
	{
		this.inputStream = inputStream;
	}

	/**
	 * Retorna o Input Stream do Objeto Temporário.
	 * @return InputStream
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposGIAITCDTemporario.CAMPO_INFO_GIA_TEMPORARIO
	     ,obrigatorio = true
	 )
	public InputStream getInputStream()
	{
		return inputStream;
	}

	/**
	 * Atribui o Tamanho do Input Stream.
	 * @param sizeInputStream
	 * @implemented by Daniel Balieiro
	 */
	public void setSizeInputStream(int sizeInputStream)
	{
		this.sizeInputStream = sizeInputStream;
	}

	/**
	 * Retorna o Tamanho do Input Stream.
	 * @return int
	 * @implemented by Daniel Balieiro
	 */
	public int getSizeInputStream()
	{
		return sizeInputStream;
	}

	/**
	 * Atribui o Código da Autenticidade
	 * @param codigoAutenticidade
	 * @implemented by Daniel Balieiro
	 */
	public void setCodigoAutenticidade(String codigoAutenticidade)
	{
		this.codigoAutenticidade = codigoAutenticidade;
	}

	/**
	 * Retorna o Código da Autenticidade
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposGIAITCDTemporario.CAMPO_CODIGO_AUTENTICIDADE
	     ,obrigatorio = true
	 )
	public String getCodigoAutenticidade()
	{
		if (!Validador.isStringValida(codigoAutenticidade))
		{
			return STRING_VAZIA;
		}
		return codigoAutenticidade;
	}

	/**
	 * Atribui o Prazo para Protocolar
	 * @param prazoProtocolar
	 * @implemented by Daniel Balieiro
	 */
	public void setPrazoProtocolar(Date prazoProtocolar)
	{
		this.prazoProtocolar = prazoProtocolar;
	}

	/**
	 * Retorna o Prazo para Protocolar
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposGIAITCDTemporario.CAMPO_DATA_PRAZO_PROTOCOLAR
	     ,obrigatorio = false
	 )
	public Date getPrazoProtocolar()
	{
		return prazoProtocolar;
	}

	/**
	 * Método que retorna o Prazo para Protocolar Formatado
	 * @return
	 * @implemented by Anderson Boehler Iglesias Araujo
	 */
	public String getPrazoProtocolarFormatado()
	{
		if (Validador.isDataValida(getPrazoProtocolar()))
		{
			return new SefazDataHora(getPrazoProtocolar()).formata("dd/MM/yyyy");
		}
		else
		{
			return STRING_VAZIA;
		}
	}

	/**
	 * Atribui o Código do Responsável
	 * @param codigoResponsavel
	 * @implemented by Daniel Balieiro
	 */
	public void setCodigoResponsavel(long codigoResponsavel)
	{
		this.codigoResponsavel = codigoResponsavel;
	}

	/**
	 * Retorna o Código do Responsável
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposGIAITCDTemporario.CAMPO_CODIGO_RESPONSAVEL
	     ,obrigatorio = true
	 )
	public long getCodigoResponsavel()
	{
		return codigoResponsavel;
	}

	/**
	 * Atribui se a GIA foi ou não confirmada
	 * @param giaConfirmada
	 * @implemented by Daniel Balieiro
	 */
	public void setGiaConfirmada(DomnSimNao giaConfirmada)
	{
		this.giaConfirmada = giaConfirmada;
	}

	/**
	 * Retorna se a GIA foi ou não confirmada
	 * @return
	 * @implemented by Daniel Balieiro
	 */
	 @AnotacaoAtributo
	 (
	     nomeColuna = CamposGIAITCDTemporario.CAMPO_GIA_CONFIRMADA
	     ,obrigatorio = true
	 )
	public DomnSimNao getGiaConfirmada()
	{
		if (!Validador.isDominioNumericoValido(giaConfirmada))
		{
			setGiaConfirmada(new DomnSimNao(-1));
		}
		return giaConfirmada;
	}

	/**
	 * Atribui a Data de Atualizacao
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
	     nomeColuna = CamposGIAITCDTemporario.CAMPO_DATA_ATUALIZACAO_BD
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
   
   @AnotacaoAtributo
   (
       nomeColuna = CamposGIAITCDTemporario.CAMPO_NUMERO_VERSAO_GIAITCD
       ,obrigatorio = true
   )
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
   
   @AnotacaoAtributo
   (
       nomeColuna = CamposGIAITCDTemporario.CAMPO_TIPO_PROTOCOLO_GIA
       ,obrigatorio = true
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

	public boolean leuGIAITCDSerializada() throws IOException
	{
		GIAITCDVo gia = null;
	   if (inputStream != null)
	   {			      
	      ObjectInputStream ois = new ObjectInputStream(inputStream);
			try
			{			   
			   gia = (GIAITCDVo) ois.readObject();
			}
			catch(Exception e)
			{										
				return false;
			}
	   }
		return true;
	}

	public long getCodigo()
	{
		return super.getCodigo();
	}
   
   public void setGiaTempXML(String giaTempXML)
      {
         this.giaTempXML = giaTempXML;
      }


   /**
   * Retorna a GIA em formato XML
   * @return String
   * @implemented by Dherkyan Ribeio Silva
   */
       
   @AnotacaoAtributo 
   (
      nomeColuna = CamposGIAITCDTemporario.CAMPO_INFO_GIA_TEMPORARIO_XML
      ,obrigatorio = false
   ) 
   public String getGiaTempXML()
   {
      setGiaTempXML(converteGIAparaXML());
      return giaTempXML;
   }                  
      
   public class IgnoreStatusUsuarioMapper extends MapperWrapper {
   
       public IgnoreStatusUsuarioMapper(Mapper wrapped) {
           super(wrapped);
       }
       
       @Override
       public boolean shouldSerializeMember(Class definedIn, String fieldName) {
           if ("statusUsuario".equals(fieldName)) {
               return false;
           }
           return super.shouldSerializeMember(definedIn, fieldName);
       }
   }  
      
   /**
   * <b>Objetivo : </b>Este método tem por objetivo converter uma
   * GIA do formato XML para Objeto Java.
   * 
   * @return GIAITCDVo
   * @implemented by Dherkyan Ribeio Silva
   */
   public GIAITCDVo convertXMLParaGIA(){  
    try {       
        XStream xstream = new XStream(new DomDriver()) {
               @Override
               protected MapperWrapper wrapMapper(MapperWrapper next) {                   
                   return new IgnoreStatusUsuarioMapper(next);
               }
         };   
        xstream.alias("usuario", sefaz.mt.acessoweb.Usuario.class);                
        xstream.alias("br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.GIAITCDDoacaoVo", br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.GIAITCDDoacaoVo.class);        
        return (GIAITCDVo) xstream.fromXML(this.giaTempXML);
        
    } catch(Exception e) {
        e.printStackTrace();
        return null;
    }
   }
   
   
   /**
   * <b>Objetivo : </b>Este método tem por objetivo converter um
   * GIA para  XML ignorando atritubos desnecessários.
   * 
   * @return String  representado a GIA em formato XML.
   */
   private String converteGIAparaXML()
   {  
       try
      { 
         // Remove objetos que sao desnecessarios no XML.
         String xml = "";
         InputStream is = this.inputStream;
         this.inputStream = null;
         UFIntegracaoVO uf = giaitcdVo.getUfIntegracaoVO();
         giaitcdVo.setUfIntegracaoVO(null);
         this.giaTempXML = null;
         
         XStream xstream = new XStream(new DomDriver());
         xstream.omitField(sefaz.mt.acessoweb.Usuario.class, "statusUsuario");
         
         xml = xstream.toXML( this.giaitcdVo );
         
         //Retorna o objetos da GIa. 
         this.inputStream = is;
         giaitcdVo.setUfIntegracaoVO(uf);    
         return xml;
      }catch(Exception e)
      {
         e.printStackTrace();
         return null;
      }
   }
   
   public void setAlterarParaPendenteProtocolo(DomnSimNao alterarParaPendenteProtocolo)
   {
      this.alterarParaPendenteProtocolo = alterarParaPendenteProtocolo;
   }

   public DomnSimNao getAlterarParaPendenteProtocolo()
   {
      if (!Validador.isDominioNumericoValido(alterarParaPendenteProtocolo))
      {
         setAlterarParaPendenteProtocolo(new DomnSimNao(-1));
      }
      return alterarParaPendenteProtocolo;
   }

   public void setSituacaoProcessamento(DomnSituacaoProcessamento situacaoProcessamento)
   {
      this.situacaoProcessamento = situacaoProcessamento;
   }

   /**
   * Status da gia gerado pelo serviço ProtocolarGerarDarAvaliacaoAutomatico
   * @return String
   * @implemented by Dherkyan Ribeio Silva
   */
       
   @AnotacaoAtributo 
   (
      nomeColuna = CamposGIAITCDTemporario.CAMPO_SITUACAO_PROCESSAMENTO
      ,obrigatorio = false
   ) 
   public DomnSituacaoProcessamento getSituacaoProcessamento()
   {
      return situacaoProcessamento;
   }

   public void setDescricaoMensagemSituacaoErrro(String descricaoMensagemSituacaoErrro)
   {
      this.descricaoMensagemSituacaoErrro = descricaoMensagemSituacaoErrro;
   }

   
   /**
   * Representa a mensagem de erro
   * caso ocorra algum erro durante o processamento
   * da gia pelo serviço ProtocolarGerarDarAvaliacaoAutomatico
   * @return String
   * @implemented by Dherkyan Ribeio Silva
   */
       
   @AnotacaoAtributo 
   (
      nomeColuna = CamposGIAITCDTemporario.CAMPO_DESCRICAO_MENSAGEM_SITUACAO_ERRO
      ,obrigatorio = false
   ) 
   public String getDescricaoMensagemSituacaoErrro()
   {
      return descricaoMensagemSituacaoErrro;
   }
      
   public Double getPorcentagemAconsiderar(){
     return porcentagemAconsiderar;
   }
   
   public void setPorcentagemAconsiderar(Double porcentagem){
     this.porcentagemAconsiderar = porcentagem;    
   }
}
