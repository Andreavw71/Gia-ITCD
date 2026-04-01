package br.gov.mt.sefaz.itc.model.generico.ficha;

import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelVo;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.EnderecoIntegracaoVo;

import br.gov.mt.sefaz.itc.util.integracao.cadastro.UFIntegracaoVO;

import java.util.Collection;

/**
 * VO Pai de das Fichas 
 * 
 * @author Dherkyan Ribeiro da Silva
 * @version 1.2
 */
public abstract class AbstractFichaVo<T> extends EntidadeVo
{
   private static final long serialVersionUID = Long.MAX_VALUE;

   /**
    * Objeto base de bens tributaveis.
    * 
    */
   private BemTributavelVo bemTributavelVo;

   /**
    * 
    * 
    */
   private EnderecoIntegracaoVo enderecoVo;

   /**
    * Atributo utilizado apenas como suporte para armazenar uma lista de UF's
    * para ser utilizada o sistema ou listada na view.
    * <br>
    * <b>NÃO</b> deve ser utilizado como objeto a ser gravado na base;
    * 
    */
   private transient UFIntegracaoVO ufsVO;
   
   /**
    * Atributo UF para armazenamento no BD.
    * 
    */
   private UFIntegracaoVO ufIntegracaoVO;

   /**
    * Construtor Padrão
    * @implemented by Dherkyan Ribeiro da Silva
    */
   public AbstractFichaVo()
   {
      super();
   }

   /**
    * Construtor que recebe o Parametro de Consulta
    * 
    * @param parametroConsulta
    * @implemented by Dherkyan Ribeiro da Silva
    */
   public AbstractFichaVo(AbstractFichaVo parametroConsulta)
   {
      super();
      setParametroConsulta(parametroConsulta);
   }

   /**
    * Construtor que recebe uma Collection de FichaImovelVo
    * 
    * @param collVo
    * @implemented by Daniel Balieiro
    */
   public AbstractFichaVo(Collection collVo)
   {
      super(collVo);
   }

   /**
    * Construtor que recebe a Chave Primária 
    * 
    * @param codigo
    * @implemented by Dherkyan Ribeiro da Silva
    */
   public AbstractFichaVo(long codigo)
   {
      super(codigo);
   }

   /**
    * Atribui um Bem Tributavel
    * 
    * @param bemTributavelVo
    * @implemented by Dherkyan Ribeiro da Silva
    */
   public void setBemTributavelVo(BemTributavelVo bemTributavelVo)
   {
      this.bemTributavelVo = bemTributavelVo;
   }

   /**
    * Retorna o Bem Tributavel
    * 
    * @return BemTributavelVo
    * @implemented by Dherkyan Ribeiro da Silva
    */
   public BemTributavelVo getBemTributavelVo()
   {
      if (!Validador.isObjetoValido(bemTributavelVo))
      {
         setBemTributavelVo(new BemTributavelVo());
      }
      return bemTributavelVo;
   }

   /**
    * Atribui um Endereço
    * 
    * @param enderecoVo
    * @implemented by Dherkyan Ribeiro da Silva
    */
   public void setEnderecoVo(EnderecoIntegracaoVo enderecoVo)
   {
      this.enderecoVo = enderecoVo;
   }

   /**
    * Retorna o Endereço
    * 
    * @return EnderecoIntegracaoVo
    * @implemented by Dherkyan Ribeiro da Silva
    */
   public EnderecoIntegracaoVo getEnderecoVo()
   {
      if (!Validador.isObjetoValido(enderecoVo))
      {
         setEnderecoVo(new EnderecoIntegracaoVo());
      }
      return enderecoVo;
   }

   public void setUfsVO(UFIntegracaoVO ufIntegracaoVO)
   {
      this.ufsVO = ufIntegracaoVO;
   }

   /**
    * Retorna o UFIntegracaoVO
    * 
    * @return UFIntegracaoVO
    * @implemented by Dherkyan Ribeiro da Silva
    */
   public UFIntegracaoVO getUfsVO()
   {
      if (!Validador.isObjetoValido(ufsVO))
      {
         setUfsVO(new UFIntegracaoVO());
      }
      return ufsVO;
   }

   public void setUfIntegracaoVO(UFIntegracaoVO ufIntegracaoVO)
   {
      this.ufIntegracaoVO = ufIntegracaoVO;
   }

   /**
    * Retorna o UFIntegracaoVO
    * 
    * @return UFIntegracaoVO
    * @implemented by Dherkyan Ribeiro da Silva
    */
   public UFIntegracaoVO getUfIntegracaoVO()
   {
      if (!Validador.isObjetoValido(ufIntegracaoVO))
      {
         setUfIntegracaoVO(new UFIntegracaoVO());
      }
      return ufIntegracaoVO;
   }

}
