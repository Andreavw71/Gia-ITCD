/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: IPTUVo.java
 * Revisão: Dherkyan Ribeiro da Silva
 * Data criação : 24/11/2015
 * Data ultima revisão : 24/11/2015
 */
package br.gov.mt.sefaz.itc.util.integracao.lpm;

import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.ProibidoMudarNomeSistemaException;
import br.com.abaco.util.facade.EntidadeFacade;
import br.com.abaco.util.facade.VoIntegrador;

import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.integracao.contacorrente.ContaCorrenteIntegracaoVo;
import br.gov.mt.sefaz.lpm.model.produtoncm.ProdutoNcmVo;

import java.util.Collection;

public class ProdutoNcmIntegracaoVo implements EntidadeFacade, VoIntegrador
{

   private int codigo;
   private String descricaoProduto;
   private double valorProdutoUnitario;
   // Atributos ValorVenalIntegracaoVo
   private String mensagem;
   private String nomeSistema;
   private transient Collection collVO;
   private boolean consultaCompleta;
   private EntidadeFacade parametroConsulta;
   private long usuarioLogado;

   public ProdutoNcmIntegracaoVo()
   {
      super();
   }
   
   public ProdutoNcmIntegracaoVo(int codigo)
   {
      super();
      this.codigo = codigo;
   }

   public ProdutoNcmIntegracaoVo(ProdutoNcmVo produtoNcmVo) throws ParametroObrigatorioException
   {
      super();
      if (Validador.isObjetoValido(produtoNcmVo))
      {
         this.codigo = produtoNcmVo.getCodgProdutoSeqc();
         this.descricaoProduto = produtoNcmVo.getDescProduto();
         this.valorProdutoUnitario = produtoNcmVo.getValorProduto();
         this.collVO = produtoNcmVo.getProdutos();
      }
   }

   /**
    * Passa todos os dados do objeto ProdutoNcmVo
    * para o objeto atual.
    * 
    * @param produtoNcmVo
    */
   public void setProdutoNcmVo(ProdutoNcmVo produtoNcmVo)
   {
      if (Validador.isObjetoValido(produtoNcmVo))
      {
         this.codigo = produtoNcmVo.getCodgProdutoSeqc();
         this.descricaoProduto = produtoNcmVo.getDescProduto();
         this.valorProdutoUnitario = produtoNcmVo.getValorProduto();
         this.collVO = produtoNcmVo.getProdutos();
      }
   }

   public void setNomeSistema(String nomeSistema) throws ProibidoMudarNomeSistemaException
   {
      if (Validador.isStringValida(nomeSistema) && nomeSistema.equals(EntidadeVo.NOME_SISTEMA))
      {
         this.nomeSistema = nomeSistema;
      }
      else
         throw new ProibidoMudarNomeSistemaException();
   }

   /**
    * Retorna apropriadamente o nome do sistema
    * @return      nome do sistema
    * @implemented by Dherkyan Ribeiro da Silva
    */
   public String getNomeSistema()
   {
      return (Validador.isStringValida(this.nomeSistema))? this.nomeSistema: STRING_VAZIA;
   }

   /**
    * Configura apropriadamente o atributo <code>collVO</code> usando o parametro
    * <code>collVO</code>.
    * @param collVO        collecao de VO`s a ser configurada como collVO do VO
    * @implemented by Dherkyan Ribeiro da Silva
    */
   public void setCollVO(Collection collVO)
   {
      this.collVO = collVO;
   }

   /**
    * Caso não tenha sido criado um collVO e se precise de um antes de manipula-lo
    * criamos-o aqui.
    * @return      cria e set collVO to collVO attribute
    * @implemented by Dherkyan Ribeiro da Silva
    */
   private Collection createCollVO()
   {
      this.setCollVO(collVO);
      return this.collVO;
   }

   /**
    * Retorna a collVO do VO em questão
    * @return  collVO do VO em questão
    * @implemented by Dherkyan Ribeiro da Silva
    */
   public Collection getCollVO()
   {
      return (Validador.isCollectionValida(this.collVO))? this.collVO: createCollVO();
   }

   /**
    * @implemented by Dherkyan Ribeiro da Silva
    */
   public boolean isExiste()
   {
      return !this.equals(new ContaCorrenteIntegracaoVo());
   }

   /**
    * @implemented by Dherkyan Ribeiro da Silva
    */
   public boolean isExisteCollVO()
   {
      return Validador.isCollectionValida(this.collVO);
   }

   public boolean isConsultaCompleta()
   {
      return consultaCompleta;
   }

   /**
    * @implemented by Dherkyan Ribeiro da Silva
    */
   public boolean isConsultaParametrizada()
   {
      return (this.getParametroConsulta() != null);
   }

   /**
    * @implemented by Dherkyan Ribeiro da Silva
    */
   public void setUsuarioLogado(long usuarioLogado)
   {
      this.usuarioLogado = usuarioLogado;
   }

   /**
    * @implemented by Dherkyan Ribeiro da Silva
    */
   public long getUsuarioLogado()
   {
      return this.usuarioLogado;
   }

   /**
    * @implemented by Dherkyan Ribeiro da Silva
    */
   public void setMensagem(String mensagem)
   {
      this.mensagem = mensagem;
   }

   /**
    * @implemented by Dherkyan Ribeiro da Silva
    */
   public String getMensagem()
   {
      return this.mensagem;
   }

   public int compareTo(Object o)
   {
      return 0;
   }

   public int compare(Object o1, Object o2)
   {
      return 0;
   }

   /**
    * @implemented by Dherkyan Ribeiro da Silva
    */
   public EntidadeFacade getParametroConsulta()
   {
      return parametroConsulta;
   }

   public void setCodigo(int codigo)
   {
      this.codigo = codigo;
   }

   public int getCodigo()
   {
      return codigo;
   }

   public void setDescricaoProduto(String descricaoProduto)
   {
      this.descricaoProduto = descricaoProduto;
   }

   public String getDescricaoProduto()
   {
      return descricaoProduto;
   }

   public void setValorProdutoUnitario(double valorProdutoUnitario)
   {
      this.valorProdutoUnitario = valorProdutoUnitario;
   }
   public String getValorProdutoUnitarioFormatado()
   {
      if (!Validador.isNumericoValido(this.valorProdutoUnitario))
      {
         return STRING_VAZIA;
      }
      return StringUtil.doubleToMonetario(this.valorProdutoUnitario);
   }
   public double getValorProdutoUnitario()
   {
      return valorProdutoUnitario;
   }
}
