package br.gov.mt.sefaz.itc.util.integracao.ipva;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.ProibidoMudarNomeSistemaException;
import br.com.abaco.util.facade.EntidadeFacade;
import br.com.abaco.util.facade.VoIntegrador;

import br.gov.mt.sefaz.ipva.integracao.ValorVenalVo;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.integracao.contacorrente.ContaCorrenteIntegracaoVo;

import java.util.Collection;

public class ValorVenalIntegracaoVo implements EntidadeFacade, VoIntegrador
{
   // Atributos ValorVenalVo
   private CategoriaIntegracaoVo categoriaVo;
   private int anoReferencia;
   private int anoFabricacao;
   private double valrVeiculo;
   private String nomeProprietario;
   private MarcaModeloIntegracaoVo marca;
   private String placa;

   // Atributos ValorVenalIntegracaoVo
   private String mensagem;
   private String nomeSistema;
   private transient Collection collVO;
   private boolean consultaCompleta;
   private EntidadeFacade parametroConsulta;
   private long usuarioLogado;

   public ValorVenalIntegracaoVo()
   {
      super();
   }

   public ValorVenalIntegracaoVo(ValorVenalVo valorVenalVo) throws ParametroObrigatorioException
   {
      super();
      if (Validador.isObjetoValido(valorVenalVo.getCategoria()))
      {
         categoriaVo = new CategoriaIntegracaoVo(valorVenalVo.getCategoria());
      }
      if (Validador.isNumericoValido(valorVenalVo.getAnoReferencia()))
      {
         setAnoReferencia(valorVenalVo.getAnoReferencia());
      }
      if (Validador.isNumericoValido(valorVenalVo.getAnoFabricacao()))
      {
         setAnoFabricacao(valorVenalVo.getAnoFabricacao());
      }
      if (Validador.isNumericoValido(valorVenalVo.getValrVeiculo()))
      {
         setValrVeiculo(valorVenalVo.getValrVeiculo());
      }
      if (Validador.isStringValida(valorVenalVo.getNomeProprietario()))
      {
         setNomeProprietario(valorVenalVo.getNomeProprietario());
      }

      if (Validador.isObjetoValido(valorVenalVo.getMarcaModelo()))
      {
         marca = new MarcaModeloIntegracaoVo(valorVenalVo.getMarcaModelo());
      }
      
      if(Validador.isStringValida(valorVenalVo.getPlaca()))
      {
         setPlaca(valorVenalVo.getPlaca());
      }
   }

   public void setCategoria(CategoriaIntegracaoVo categoriaVo)
   {
      this.categoriaVo = categoriaVo;
   }

   public CategoriaIntegracaoVo getCategoria()
   {
      return this.categoriaVo;
   }

   public void setAnoReferencia(int anoReferencia)
   {
      this.anoReferencia = anoReferencia;
   }

   public int getAnoReferencia()
   {
      return anoReferencia;
   }

   public void setAnoFabricacao(int anoFabricacao)
   {
      this.anoFabricacao = anoFabricacao;
   }

   public int getAnoFabricacao()
   {
      return anoFabricacao;
   }

   public void setValrVeiculo(double valrVeiculo)
   {
      this.valrVeiculo = valrVeiculo;
   }

   public double getValrVeiculo()
   {
      return valrVeiculo;
   }

   public void setNomeProprietario(String nomeProprietario)
   {
      this.nomeProprietario = nomeProprietario;
   }

   public String getNomeProprietario()
   {
      return nomeProprietario;
   }

   public void setMarca(MarcaModeloIntegracaoVo marca)
   {
      this.marca = marca;
   }

   public MarcaModeloIntegracaoVo getMarca()
   {
      return marca;
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


   public void setPlaca(String placa)
   {
      this.placa = placa;
   }

   public String getPlaca()
   {
      return placa;
   }
}
