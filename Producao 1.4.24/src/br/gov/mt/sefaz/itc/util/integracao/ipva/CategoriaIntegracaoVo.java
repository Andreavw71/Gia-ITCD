package br.gov.mt.sefaz.itc.util.integracao.ipva;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ProibidoMudarNomeSistemaException;
import br.com.abaco.util.facade.EntidadeFacade;
import br.com.abaco.util.facade.VoIntegrador;

import br.gov.mt.sefaz.ipva.integracao.CategoriaVo;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.integracao.contacorrente.ContaCorrenteIntegracaoVo;

import java.util.Collection;

public class CategoriaIntegracaoVo implements EntidadeFacade, VoIntegrador
{

   private int codgCategoria;
   private String descCategoria;
   
   private String mensagem;
   private String nomeSistema;
   private transient Collection collVO;
   private boolean consultaCompleta;
   private EntidadeFacade parametroConsulta;
   private long usuarioLogado;

   public CategoriaIntegracaoVo()
   {
      super();
   }
   
   public CategoriaIntegracaoVo( CategoriaVo categoriaVo)
   {
      setCodgCategoria( categoriaVo.getCodgCategoria() );
      setDescCategoria( categoriaVo.getDescCategoria());
   }

   public int getCodgCategoria()
   {
      return this.codgCategoria;
   }

   public void setCodgCategoria(int codgCategoria)
   {
      this.codgCategoria = codgCategoria;
   }

   public String getDescCategoria()
   {
      return this.descCategoria;
   }

   public void setDescCategoria(String descCategoria)
   {
      this.descCategoria = descCategoria;
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
}
