package br.gov.mt.sefaz.itc.util.integracao.castor;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.ProibidoMudarNomeSistemaException;
import br.com.abaco.util.facade.EntidadeFacade;
import br.com.abaco.util.facade.VoIntegrador;

import br.gov.mt.sefaz.cas.integracao.ObjetoVo;
import br.gov.mt.sefaz.cas.integracao.PoliticaVo;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.integracao.contacorrente.ContaCorrenteIntegracaoVo;

import java.io.File;

import java.util.Collection;

public class ObjetoIntegracaoVo implements EntidadeFacade, VoIntegrador
{  
   /*
    *
    *
    */
   private long codigo;
   /*
    *
    *
    */
   private String mensagem;
   /*
    * O nome da tabela que possui
    * chave estrangeira (referência) para a tabela CAS.CASTB01_OBJETO;
    */
   private String nomeTabela;

   /*
    *
    *
    */
   private String nomeSistema;

   /*
    * Arquivo que será enviado para
    * o Castor
    */
   private File arquivo;

   /*
    *
    *
    */
   private ObjetoVo objetoVo;

   /*
    *
    *
    */
   private transient Collection collVO;
   /*
    *
    *
    */
   private boolean consultaCompleta;
   /*
    *
    *
    */
   private EntidadeFacade parametroConsulta;
   /*
    *
    *
    */
   private long usuarioLogado;

   public ObjetoIntegracaoVo()
   {
      super();
   }

   public ObjetoIntegracaoVo(long codigo)
   {
      super();
      this.codigo = codigo;
   }

   public ObjetoIntegracaoVo(ObjetoVo objetoVo) throws ParametroObrigatorioException
   {
      super();
      if (Validador.isObjetoValido(objetoVo))
      {

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

   public void setCodigo(long codigo)
   {
      this.codigo = codigo;
   }

   public long getCodigo()
   {
      return codigo;
   }

   public void setObjetoVo(ObjetoVo objetoVo)
   {
      this.objetoVo = objetoVo;
   }

   public ObjetoVo getObjetoVo()
   {
      return objetoVo;
   }

   public void setArquivo(File arquivo)
   {
      this.arquivo = arquivo;
   }

   public File getArquivo()
   {
      return arquivo;
   }

   public void setNomeTabela(String nomeTabela)
   {
      this.nomeTabela = nomeTabela;
   }

   public String getNomeTabela()
   {
      return nomeTabela;
   }
}

