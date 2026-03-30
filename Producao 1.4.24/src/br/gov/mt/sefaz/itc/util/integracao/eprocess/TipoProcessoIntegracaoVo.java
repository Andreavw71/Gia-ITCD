package br.gov.mt.sefaz.itc.util.integracao.eprocess;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ProibidoMudarNomeSistemaException;
import br.com.abaco.util.facade.EntidadeFacade;
import br.com.abaco.util.facade.VoIntegrador;

import br.gov.mt.sefaz.eprocess.integracao.TipoProcessoVO;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.integracao.contacorrente.ContaCorrenteIntegracaoVo;

import java.util.ArrayList;
import java.util.Collection;


public class TipoProcessoIntegracaoVo implements EntidadeFacade, VoIntegrador
{
   
   private transient Collection collVO;
   private boolean consultaCompleta;
   private EntidadeFacade parametroConsulta;
   private Long usuarioLogado;
   private String mensagem;
   private String nomeSistema;
   private static final long serialVersionUID = Long.MAX_VALUE;
   //Atributos TipoProcessoVo
   private Long codgTipoProcesso;
   private Long codgTipoAssunto;
   private String descricao;
   private String assunto;
   
   
   public TipoProcessoIntegracaoVo()
   {
      setNomeSistema(EntidadeVo.NOME_SISTEMA);
   }

   public TipoProcessoIntegracaoVo(TipoProcessoVO tipoProcessoVO)
   {
      this();
      this.setCodgTipoProcesso(tipoProcessoVO.getCodigo());
      this.setDescricao(tipoProcessoVO.getDescricao());
      this.setAssunto(tipoProcessoVO.getAssuntoVO().getDescricao());
      this.setCodgTipoAssunto(tipoProcessoVO.getAssuntoVO().getCodigo());
   }

   public int compare(Object t, Object t1)
   {
      return ((TipoProcessoIntegracaoVo) t).getCodgTipoProcesso().compareTo(((TipoProcessoIntegracaoVo) t1).getCodgTipoProcesso());
   }

   public int compareTo(Object municipioVo)
   {
      return this.getCodgTipoProcesso().compareTo(((TipoProcessoIntegracaoVo) municipioVo).getCodgTipoProcesso());
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

   public String getNomeSistema()
   {
      return (Validador.isStringValida(this.nomeSistema)) ? this.nomeSistema : STRING_VAZIA;
   }

   public void setCollVO(Collection collVO)
   {
      this.collVO = collVO;
   }

   private Collection createCollVO()
   {
      this.setCollVO( new ArrayList<TipoProcessoIntegracaoVo>() );
      return this.getCollVO();
   }

   public Collection getCollVO()
   {
      if(!Validador.isObjetoValido(this.collVO))
      {
         this.collVO =new ArrayList<TipoProcessoIntegracaoVo>();
      }
      return this.collVO;
   }

   public boolean isExiste()
   {
      return !this.equals(new ContaCorrenteIntegracaoVo());
   }

   public boolean isExisteCollVO()
   {
      return Validador.isCollectionValida(this.getCollVO());
   }

   public boolean isConsultaParametrizada()
   {
      return (this.getParametroConsulta() != null);
   }

   public boolean isConsultaCompleta()
   {
      return consultaCompleta;
   }

   public void setConsultaCompleta(boolean consultaCompleta)
   {
      this.consultaCompleta = consultaCompleta;
   }

   public void setUsuarioLogado(long usuarioLogado)
   {
      this.usuarioLogado = usuarioLogado;
   }

   public long getUsuarioLogado()
   {
      return this.usuarioLogado;
   }

   public void setMensagem(String mensagem)
   {
      this.mensagem = mensagem;
   }

   public String getMensagem()
   {
      return this.mensagem;
   }

   public void setParametroConsulta(EntidadeFacade parametroConsulta)
   {
      this.parametroConsulta = parametroConsulta;
   }

   public EntidadeFacade getParametroConsulta()
   {
      return parametroConsulta;
   }
   
   //DADOS VO
   public Long getCodgTipoProcesso()
   {
      return codgTipoProcesso;
   }

   public void setCodgTipoProcesso(Long codgTipoProcesso)
   {
      this.codgTipoProcesso = codgTipoProcesso;
   }

   public String getDescricao()
   {
      return descricao;
   }

   public void setDescricao(String descricao)
   {
      this.descricao = descricao;
   }

   public String getAssunto()
   {
      return assunto;
   }

   public void setAssunto(String assunto)
   {
      this.assunto = assunto;
   }

   public Long getCodgTipoAssunto()
   {
      return codgTipoAssunto;
   }

   public void setCodgTipoAssunto(Long codgTipoAssunto)
   {
      this.codgTipoAssunto = codgTipoAssunto;
   }
}
