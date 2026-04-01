package br.gov.mt.sefaz.itc.util.calculo;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.RegistroNaoPodeSerUtilizadoException;

import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelVo;
import br.gov.mt.sefaz.itc.model.generico.fichaimovel.FichaImovelVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichabem.ficharebanholpm.FichaRebanhoLPMVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelurbano.FichaImovelUrbanoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichabem.fichaveiculo.FichaVeiculoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.FichaImovelRuralVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.cultura.FichaImovelRuralCulturaVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.basecalculoimovelrural.BaseCalculoImovelRuralBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.basecalculoimovelrural.BaseCalculoImovelRuralVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.criteriomunicipio.CriterioMunicipioBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.criteriomunicipio.CriterioMunicipioVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.distancia.DistanciaBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.distancia.DistanciaVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnCriterioBaseCalculo;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoAtividade;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoDistancia;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoIPTU;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.CadastroBe;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.EnderecoIntegracaoVo;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.MunicipioIntegracaoVo;

import br.gov.mt.sefaz.itc.util.log.ExibirLOG;

import java.math.BigDecimal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * 
 * 
 * @author Dherkyan Ribeiro da Silva
 */
public class CalculoITCD
{

   /**
    * Este valor foi definido para resolver o problema
    * de centavos impares, caso em que não é possível a divisão
    * exata do valor total dos bens entre os conjuges.
    * <br>
    * Exemplo
    * <br>
    * Valor total bens = 10,05
    * <br>
    * Conjuge1 = 5,03
    * <br>
    * Conjuge2  = 5,02
    * 
    */
   public static BigDecimal VALOR_MINIMO_DIFERENCA_ENTRE_CONJUGES = new BigDecimal(0.01d);
   
   public CalculoITCD()
   {
   }

   //-----------------------------------------------------------------------------------------------------------
   // -------------------------------------- INICIO Calculos FichaImovelUrbanoVo -------------------------------
   //-----------------------------------------------------------------------------------------------------------
   /**
    * 
    * 
    * 
    * 
    * @param fichaImovelUrbanoVo
    * @return
    * @implemented by Dherkyan Ribeiro
    */
   public static double calcularValorTributavelImovelUrbano(FichaImovelUrbanoVo fichaImovelUrbanoVo)
   {
      if (fichaImovelUrbanoVo.getIptuVo().getTipoITPU().is(DomnTipoIPTU.INTEGRADO))
      {
         return calcularValorTributavelImovelUrbanoIntegrado(fichaImovelUrbanoVo);
      }
      else if (fichaImovelUrbanoVo.getIptuVo().getTipoITPU().is(DomnTipoIPTU.ESTIMATIVA))
      {
         
         return calcularValorTributavelImovelUrbanoEstimado(fichaImovelUrbanoVo);
      }
      return 0;
   }
   
   
   
   
   /**
    * 
    * Este método tem por objetivo calcular o <b>valor tributavel do imovel urbano com IPTU estimado</b>
    * 
    * 
    * 
    * @param fichaImovelUrbanoVo
    * @return
    * @implemented by Dherkyan Ribeiro
    */
   private static double calcularValorTributavelImovelUrbanoEstimado(FichaImovelUrbanoVo fichaImovelUrbanoVo)
   {
      return calcularMaiorValorTributavelImovelUrbano(fichaImovelUrbanoVo);
   }

   /**
    * Este método tem por objetivo calcular o <b>valor tributavel do imovel urbano com IPTU integrado</b>
    * 
    * 
    * 
    * @param fichaImovelUrbanoVo
    * @return O maior valor.
    * @implemented by Dherkyan Ribeiro
    */
   private static double calcularValorTributavelImovelUrbanoIntegrado(FichaImovelUrbanoVo fichaImovelUrbanoVo)
   {
      //*** 05/2019 metodo alterado para atender ticket 83315 - citsmart
      LinkedList<Double> valores = new LinkedList();
      // Valor declarado pelo Contribuinte.
      double valorInformado = 0.0;
      if (fichaImovelUrbanoVo.getValorInfomado() > 0)
      {
         valorInformado = fichaImovelUrbanoVo.getValorInfomado();
      }
      else
      {
         valorInformado = fichaImovelUrbanoVo.getValorMercadoTotal();
      }
      double valorVenalIPTU = 0.0;
      if (fichaImovelUrbanoVo.getValorPercentualTransmitido() > 0)
      {
         valorVenalIPTU = (fichaImovelUrbanoVo.getIptuPrefeituraVo().getValrVenal() * fichaImovelUrbanoVo.getValorPercentualTransmitido()) / 100;
      }
      else
      {
         fichaImovelUrbanoVo.setValorPercentualTransmitido(100);
         valorVenalIPTU = (fichaImovelUrbanoVo.getIptuPrefeituraVo().getValrVenal() * fichaImovelUrbanoVo.getValorPercentualTransmitido()) / 100;
      }

      valores.add(valorInformado);
      // Valor Venal IPTU Informado pela prefeitura.
      valores.add(valorVenalIPTU);
      // ordena os valores em ordem crescente os valores.
      Collections.sort(valores);
      // retorna o valor da ultima posicao(o maior valor depois da ordenacao).
  
      fichaImovelUrbanoVo.setValorInfomado(valorInformado);
      fichaImovelUrbanoVo.setValorMercadoTotal(valores.getLast());


      return fichaImovelUrbanoVo.getValorMercadoTotal();
   }

   /**
    * Este método tem por obejtivo calcular o valor da <b>Area Total Imovel Urbano</b>
    * 
    * <br><br>
    * <b>Calculo Efefutado</b>
    * <br>
    * X = AreaTotal * ValorMetroTerritorial
    * 
    * 
    * @param fichaImovelUrbanoVo
    * @return
    * @implemented by Dherkyan Ribeiro
    */
   private static double calcularValorAreaTotalImovelUrbano(FichaImovelUrbanoVo fichaImovelUrbanoVo)
   {
      return fichaImovelUrbanoVo.getQuantidadeAreaTotal() * fichaImovelUrbanoVo.getIptuVo().getValorMetroTerritorial();
   }
   
   /**
    * Este método tem por objetivo calcular o valor da Área construída.
    * 
    * <br><br>
    * <b>Calculo Efefutado</b>
    * <br>
    * X = AreaConstruida * ValorMetroPredial
    * 
    * @param fichaImovelUrbanoVo
    * @return
    * @implemented by Dherkyan Ribeiro
    */
   private static double calcularValorAreaConstruidaImovelUrbano(FichaImovelUrbanoVo fichaImovelUrbanoVo)
   {
      return fichaImovelUrbanoVo.getQuantidadeAreaConstruida() * fichaImovelUrbanoVo.getIptuVo().getValorMetroPredial();
   }
   
   /**
    * Este método tem por objetivo calcular o valor vaenal
    * do imovel acrescido do percentual definido para o municipio.
    * <br><br>
    * <b>Calculo Efefutado</b>
    * <br>
    * X = valorVenal + ( (valorVenal * percentual) / 100 )
    * @param fichaImovelUrbanoVo
    * @return
    * @implemented by Dherkyan Ribeiro
    */
   private static double calcularValorTributavelPorPercentualEstimado(FichaImovelUrbanoVo fichaImovelUrbanoVo)
   {
      return fichaImovelUrbanoVo.getValorVenalIptu() + 
         ((fichaImovelUrbanoVo.getValorVenalIptu() * fichaImovelUrbanoVo.getIptuVo().getValorPercentualEstimado()) / 100);
   }

   /**
    * Este método tem por objetivo calcular o valor total do imovel.
    * 
    * <br><br>
    * <b>Calculo Efefutado</b>
    * <br>
    * X = ValorAreaTotal + ValorAreaConstruida.
    * 
    * @param fichaImovelUrbanoVo
    * @return
    * @implemented by Dherkyan Ribeiro
    */
   private static double calcularValorTotalImovel(FichaImovelUrbanoVo fichaImovelUrbanoVo)
   {
      return calcularValorAreaTotalImovelUrbano(fichaImovelUrbanoVo) + 
         calcularValorAreaConstruidaImovelUrbano(fichaImovelUrbanoVo);
   }

   /**
    * Este método tem por objetivo calcular o <b> maior valor tributavel para imovel urbano</b>
    * para definir o maior valor são feitos 3 calculos diferentes.
    * 
    * 
    * @param fichaImovelUrbanoVo
    * @return O maior valor encontrado entre os calculos efetuados.
    * @implemented by Dherkyan Ribeiro
    */
   private static double calcularMaiorValorTributavelImovelUrbano(FichaImovelUrbanoVo fichaImovelUrbanoVo)
   {
   
      // Valor declarado pelo Contribuinte.
      double valorInformado = 0.0;
      if (fichaImovelUrbanoVo.getValorInfomado() > 0)
      {
         fichaImovelUrbanoVo.setValorInfomado(fichaImovelUrbanoVo.getValorInfomado());
		 valorInformado = fichaImovelUrbanoVo.getValorInfomado();
      }
      else
      {
         fichaImovelUrbanoVo.setValorInfomado(fichaImovelUrbanoVo.getValorMercadoTotal());
		 valorInformado = fichaImovelUrbanoVo.getValorMercadoTotal();
      }
      
   
      LinkedList<Double> valores = new LinkedList();

      // Valor declarado pelo Contribuinte.
      valores.add(valorInformado);
      // Valor Venal IPTU.
      valores.add(calcularValorTributavelPorPercentualEstimado(fichaImovelUrbanoVo));
      // Valor Total
      valores.add(calcularValorTotalImovel(fichaImovelUrbanoVo));
      // ordena os valores em ordem crescente os valores.
      Collections.sort(valores);
      // retorna o valor da ultima posicao(o maior valor depois da ordenacao).
      return valores.getLast();
   }
   
    //-----------------------------------------------------------------------------------------------------------
    // -------------------------------------- INICIO Calculos FichaVeiculoVo ------------------------------------
    //-----------------------------------------------------------------------------------------------------------
     /**
      * Este método tem por objetivo calcular o <b> maior valor tributavel para FichaVeiculoVo</b>
      * .
      * 
      * 
      * @param doubleomaiorvalorentrevalorInformado e valorVenal;
      * @return O maior valor encontrado entre os calculos efetuados.
      * @implemented by Dherkyan Ribeiro
      */
    public static double calcularValorTributavelVeiculo(FichaVeiculoVo veiculo)
    {
       LinkedList<Double> valores = new LinkedList();

       valores.add(veiculo.getValorInformado());

       valores.add(veiculo.getValorVenal());

       Collections.sort(valores);
       // retorna o valor da ultima posicao(o maior valor depois da ordenacao).
       return valores.getLast();
    }
   //-----------------------------------------------------------------------------------------------------------
   // -------------------------------------- INICIO Calculos FichaRebanhoLPMVo ---------------------------------
   //-----------------------------------------------------------------------------------------------------------
   public static double calcularValorTributavelRebanho(FichaRebanhoLPMVo rebanho)
   {
     double valorTributavelIpm = 0d;
     valorTributavelIpm = rebanho.getValorUnitario() * rebanho.getQuantidade();
     
      LinkedList<Double> valores = new LinkedList();
      
      valores.add(valorTributavelIpm);

      valores.add(rebanho.getValorInformado());

      Collections.sort(valores);
      // retorna o valor da ultima posicao(o maior valor depois da ordenacao).
      return valores.getLast();
   }
   
   //-----------------------------------------------------------------------------------------------------------
   // ----------------------------------------- INICIO Calculos BemTributavelVo --------------------------------
   //-----------------------------------------------------------------------------------------------------------
   
   /**
    * <b>Objetivo: </b> este método tem por objetivo calcular o valor total dos bens
    * utilizando somente os valores informados/declarados pelo contribuinte.
    * Desconsiderando totalmente os outros valores e calculos do sistema.
    * 
    * 
    * @param bemTributavelVo - com todos os bens a serem utilizados no calculo dentro do atributo CollVO.
    * @return soma total de valorInformadoContribuinte ou 0(zero) caso não haja nehum BemTributavelVo ou seje nulo. 
    * @implemented by Dherkyan Ribeiro
    */
   public static double calcularTotalValorInformadoContribuinte(BemTributavelVo bemTributavelVo)
   {
      double valorTotal = 0d;
      if(Validador.isObjetoValido(bemTributavelVo) && Validador.isCollectionValida(bemTributavelVo.getCollVO()))
      { 
        List<BemTributavelVo> bens =  new ArrayList(bemTributavelVo.getCollVO());
        for(BemTributavelVo bem : bens)
        {
           FichaImovelVo fichaImovelVo = bem.getFichaImovelVo();
           if(fichaImovelVo instanceof FichaImovelRuralVo)
           {
              FichaImovelRuralVo fichaImovelRuralVo = (FichaImovelRuralVo) fichaImovelVo;
              valorTotal += fichaImovelRuralVo.getValorTotalMercado();
           }
           else 
           {
              valorTotal += bem.getValorInformadoContribuinte();  
           }
        }
      }
      //System.out.println("VALOR: "+valorTotal);
      return valorTotal;
   }
   
   //-----------------------------------------------------------------------------------------------------------
   // ----------------------------------------- INICIO Calculos FichaImovelRuralVo --------------------------------
   //-----------------------------------------------------------------------------------------------------------
   
   /**
    * <b>Objetivo: </b> este método tem o objetivo de calcular o valor final
    * do imóvel rural baseado em regras de negócio e mostrá-lo para o usuário
    * 
    * @param fichaImovelRuralVo - O objeto que será usado para realizar o calculo
    * @return valor tributável do imóvel rural declarado na GIA-ITCD 
    * @implemented by Fernando Zamperin
    */
   public static Double calcularValorTributavelFichaImovelRural(FichaImovelRuralVo fichaImovelRuralVo)
      throws SQLException, ConsultaException, ObjetoObrigatorioException, 
             ParametroObrigatorioException, ConexaoException
   {
      //Objetos de negócio
      DistanciaBe distanciaBe = null;
      BaseCalculoImovelRuralBe baseCalculoImovelRuralBe = null;
      CadastroBe cadastroBe = null;
      CriterioMunicipioBe criterioMunicipioBe = new CriterioMunicipioBe();
      //Objetos Vo
      BaseCalculoImovelRuralVo baseCalculoImovelRuralPecuariaVo = null;
      BaseCalculoImovelRuralVo baseCalculoImovelRuralCulturaVo = null;
      DistanciaVo distanciaVo = null;
      BaseCalculoImovelRuralVo baseCalculoUtilizada = null;
      MunicipioIntegracaoVo municipio = null;
      //Objetos usados para realizar o cálculo
      double areaTotalExplorada = 0;
      double porcentagemAreaExploradaEmRelacaoAoTotal = 0;
      double porcentagemDeAtividadePecuaria = 0;
      double porcentagemDeAtividadeAgricultura = 0;
      double valorHectare = 0;
      double retorno = 0;
      double areaPastagem = 0;
      double areaCultivada = 0;
      double areaTotal = 0;
      try 
      {
         //Busca informações sobre cultura do imóvel rural         
         distanciaBe = new DistanciaBe();
         distanciaVo = new DistanciaVo();
         
         /*if(fichaImovelRuralVo.getFichaImovelRuralCulturaVo().getCodigo() == 0)
         {
            fichaCulturaBe = new FichaImovelRuralCulturaBe();
            fichaImovelRuralCulturaVo = new FichaImovelRuralCulturaVo();
            fichaImovelRuralCulturaVo.setFichaImovelRuralVo(fichaImovelRuralVo);
            fichaImovelRuralVo.setFichaImovelRuralCulturaVo(fichaCulturaBe.listarFichaImovelRuralCultura(new FichaImovelRuralCulturaVo(fichaImovelRuralCulturaVo)));  
         }*/
         
         /* Primeiro passo do calculo categorizar a distância do imóvel rural de acordo com as distâncias
          * entre o perimetro urbano e a distancia entre o imóvel rural e a rodovia pavimentada mais próxima */
         
         //Seta distancia inicial e final do perimitro urbano
         distanciaVo.setDistanciaInicialPerimetroUrbano((int) fichaImovelRuralVo.getQuantidadeDistancia());
         distanciaVo.setDistanciaFinalPerimetroUrbano((int) fichaImovelRuralVo.getQuantidadeDistancia());
         
         //Seta distancia inicial e final até a rodovia pavimentada
         distanciaVo.setDistanciaInicialRodoviaPavimentada((int) fichaImovelRuralVo.getDistanciaAsfalto());
         distanciaVo.setDistanciaFinalRodoviaPavimentada((int) fichaImovelRuralVo.getDistanciaAsfalto());
         distanciaVo = distanciaBe.selecionaDistanciaVoIntervaloAsfaltoPrmtUrbano(new DistanciaVo(distanciaVo));
         
         /* Segundo passo do cálculo fazer o cálculo da area total explorada, da % da area explorada em relação
          * á área total do imóvel, da % da atividade pecuária em relação á area total explorada e da % da área
          * de cultura/agricultura em relação á área total explorada */
         
         //Calcular o valor total das pastagens e culturas
         for(Object objetoFichaImovelRuralVo : fichaImovelRuralVo.getFichaImovelRuralCulturaVo().getCollVO()) 
         {
            FichaImovelRuralCulturaVo fichaImovelRuralCulturaVoAux = (FichaImovelRuralCulturaVo) objetoFichaImovelRuralVo;
            areaCultivada += fichaImovelRuralCulturaVoAux.getAreaCultivada();
         }
            
         areaPastagem = fichaImovelRuralVo.getAreaPastagem();
         
         areaTotal = fichaImovelRuralVo.getAreaTotal();
         
         //Calculando a area total explorada do imóvel rural
         areaTotalExplorada = areaPastagem + areaCultivada;
         
         //Calculando a % da area total explorada em relação a area total do imóvel rural
         porcentagemAreaExploradaEmRelacaoAoTotal = Math.ceil((areaTotalExplorada / areaTotal) * 100);
         
         /* Calculando a % de area de pecuária em relação ao total da área explorada
          * Calculando a % da area de cultura em relação ao total da área explorada*/
         if(areaTotalExplorada == 0) 
         {
            porcentagemDeAtividadeAgricultura = 0;
            porcentagemDeAtividadePecuaria = 0;
         }
         else 
         {
            porcentagemDeAtividadePecuaria = Math.ceil((areaPastagem / areaTotalExplorada) * 100);
            porcentagemDeAtividadeAgricultura = Math.ceil((areaCultivada / areaTotalExplorada) * 100);
         }
         
         /* Terceiro passo determinar critério, percentual e item */
         baseCalculoImovelRuralBe = new BaseCalculoImovelRuralBe();
         if(distanciaVo.getTipoDistancia().is(DomnTipoDistancia.PERIMETRO_URBANO))
         {
            //Preenche o Vo para procurar a Pecuaria e Cultura
            baseCalculoImovelRuralPecuariaVo = new BaseCalculoImovelRuralVo();
            baseCalculoImovelRuralPecuariaVo.setTipoDistancia(new DomnTipoDistancia(DomnTipoDistancia.PERIMETRO_URBANO));
            baseCalculoImovelRuralPecuariaVo.setTipoAtividade(new DomnTipoAtividade(DomnTipoAtividade.PECUARIA));
            baseCalculoImovelRuralPecuariaVo.setQuantidadeDistanciaInicial((int) fichaImovelRuralVo.getQuantidadeDistancia());
            baseCalculoImovelRuralPecuariaVo.setQuantidadeDistanciaFinal((int) fichaImovelRuralVo.getQuantidadeDistancia());
            baseCalculoImovelRuralPecuariaVo.setPercentualAtividadeInicial((int) porcentagemDeAtividadePecuaria);
            baseCalculoImovelRuralPecuariaVo.setPercentualAtividadeFinal((int) porcentagemDeAtividadePecuaria);
            baseCalculoImovelRuralPecuariaVo.setPercentualAreaExploradaInical((int) porcentagemAreaExploradaEmRelacaoAoTotal);
            baseCalculoImovelRuralPecuariaVo.setPercentualAreaExploradaFinal((int) porcentagemAreaExploradaEmRelacaoAoTotal);

            baseCalculoImovelRuralCulturaVo = new BaseCalculoImovelRuralVo();
            baseCalculoImovelRuralCulturaVo.setTipoDistancia(new DomnTipoDistancia(DomnTipoDistancia.PERIMETRO_URBANO));
            baseCalculoImovelRuralCulturaVo.setTipoAtividade(new DomnTipoAtividade(DomnTipoAtividade.AGRICULTURA));
            baseCalculoImovelRuralCulturaVo.setQuantidadeDistanciaInicial((int) fichaImovelRuralVo.getQuantidadeDistancia());
            baseCalculoImovelRuralCulturaVo.setQuantidadeDistanciaFinal((int) fichaImovelRuralVo.getQuantidadeDistancia());
            baseCalculoImovelRuralCulturaVo.setPercentualAtividadeInicial((int) porcentagemDeAtividadeAgricultura);
            baseCalculoImovelRuralCulturaVo.setPercentualAtividadeFinal((int) porcentagemDeAtividadeAgricultura);
            baseCalculoImovelRuralCulturaVo.setPercentualAreaExploradaInical((int) porcentagemAreaExploradaEmRelacaoAoTotal);
            baseCalculoImovelRuralCulturaVo.setPercentualAreaExploradaFinal((int) porcentagemAreaExploradaEmRelacaoAoTotal);
               
            //Buscar a base de calculo para Cultura e Pecuaria
            baseCalculoImovelRuralCulturaVo = baseCalculoImovelRuralBe.consultarCriterioCalculoImovelRuralITCD(new BaseCalculoImovelRuralVo(baseCalculoImovelRuralCulturaVo));
            baseCalculoImovelRuralPecuariaVo = baseCalculoImovelRuralBe.consultarCriterioCalculoImovelRuralITCD(new BaseCalculoImovelRuralVo(baseCalculoImovelRuralPecuariaVo));
         }
         else 
         {
            //Preenche o Vo para procurar a Pecuaria e Cultura
            baseCalculoImovelRuralPecuariaVo = new BaseCalculoImovelRuralVo();
            baseCalculoImovelRuralPecuariaVo.setTipoDistancia(new DomnTipoDistancia(DomnTipoDistancia.RODOVIA_PAVIMENTADA));
            baseCalculoImovelRuralPecuariaVo.setTipoAtividade(new DomnTipoAtividade(DomnTipoAtividade.PECUARIA));
            baseCalculoImovelRuralPecuariaVo.setQuantidadeDistanciaInicial((int) fichaImovelRuralVo.getDistanciaAsfalto());
            baseCalculoImovelRuralPecuariaVo.setQuantidadeDistanciaFinal((int) fichaImovelRuralVo.getDistanciaAsfalto());
            baseCalculoImovelRuralPecuariaVo.setPercentualAtividadeInicial((int) porcentagemDeAtividadePecuaria);
            baseCalculoImovelRuralPecuariaVo.setPercentualAtividadeFinal((int) porcentagemDeAtividadePecuaria);
            baseCalculoImovelRuralPecuariaVo.setPercentualAreaExploradaInical((int) porcentagemAreaExploradaEmRelacaoAoTotal);
            baseCalculoImovelRuralPecuariaVo.setPercentualAreaExploradaFinal((int) porcentagemAreaExploradaEmRelacaoAoTotal);
            
            baseCalculoImovelRuralCulturaVo = new BaseCalculoImovelRuralVo();
            baseCalculoImovelRuralCulturaVo.setTipoDistancia(new DomnTipoDistancia(DomnTipoDistancia.RODOVIA_PAVIMENTADA));
            baseCalculoImovelRuralCulturaVo.setTipoAtividade(new DomnTipoAtividade(DomnTipoAtividade.AGRICULTURA));
            baseCalculoImovelRuralCulturaVo.setQuantidadeDistanciaInicial((int) fichaImovelRuralVo.getDistanciaAsfalto());
            baseCalculoImovelRuralCulturaVo.setQuantidadeDistanciaFinal((int) fichaImovelRuralVo.getDistanciaAsfalto());
            baseCalculoImovelRuralCulturaVo.setPercentualAtividadeInicial((int) porcentagemDeAtividadeAgricultura);
            baseCalculoImovelRuralCulturaVo.setPercentualAtividadeFinal((int) porcentagemDeAtividadeAgricultura);
            baseCalculoImovelRuralCulturaVo.setPercentualAreaExploradaInical((int) porcentagemAreaExploradaEmRelacaoAoTotal);
            baseCalculoImovelRuralCulturaVo.setPercentualAreaExploradaFinal((int) porcentagemAreaExploradaEmRelacaoAoTotal);
               
            //Buscar a base de calculo para Cultura e Pecuária
            baseCalculoImovelRuralCulturaVo = baseCalculoImovelRuralBe.consultarCriterioCalculoImovelRuralITCD(new BaseCalculoImovelRuralVo(baseCalculoImovelRuralCulturaVo));
            baseCalculoImovelRuralPecuariaVo = baseCalculoImovelRuralBe.consultarCriterioCalculoImovelRuralITCD(new BaseCalculoImovelRuralVo(baseCalculoImovelRuralPecuariaVo));
         }
         //Verificação para saber qual base de cálculo será levada em consideração pegando o menor valor pelo NUMR_ITEM
         //Em caso de não existir nenhuma atividade pecuária ou atividade de agricultura sempre utilizar a base de cálculo da pecuária
         if(areaPastagem != 0 && areaCultivada != 0)
         {
            if(baseCalculoImovelRuralCulturaVo.getNumeroItem() < baseCalculoImovelRuralPecuariaVo.getNumeroItem())
            {
               baseCalculoUtilizada = baseCalculoImovelRuralCulturaVo;               
            }
            else
            {
               baseCalculoUtilizada = baseCalculoImovelRuralPecuariaVo;     
            }            
         }
         else if(areaPastagem == 0 && areaCultivada != 0){
         
            baseCalculoUtilizada = baseCalculoImovelRuralCulturaVo;
            
         }else{
            baseCalculoUtilizada = baseCalculoImovelRuralPecuariaVo;  
         } 
         ExibirLOG.exibirLogSimplificado("////////////////////////////////////////////////////////////////////////");
         ExibirLOG.exibirLogSimplificado("BASE CALCULO UTILIZADA Numero Item: " + baseCalculoUtilizada.getNumeroItem());
         ExibirLOG.exibirLogSimplificado("BASE CALCULO UTILIZADA Porcentagem: " + baseCalculoUtilizada.getPercentualBaseCalculoMinimoFormatada());
         ExibirLOG.exibirLogSimplificado("////////////////////////////////////////////////////////////////////////");
         
         fichaImovelRuralVo.setBaseCalculoImovelRuralVo(baseCalculoUtilizada);
         
         /* CRITÉRIO POR MUNICÍPIO */
         
         //Obtem o município a partir do CEP da localidade
         cadastroBe = new CadastroBe();
         
         //Seta o código do município para consultar o critério do município com o cep ou com o código do endereço
         if(fichaImovelRuralVo.getEnderecoVo().getCep().getCodgCep() != 0) 
         {
            municipio = cadastroBe.obterMunicipioPorCep(fichaImovelRuralVo.getEnderecoIntegracaoVo().getCep());
         }
         else 
         {
            EnderecoIntegracaoVo enderecoAuxiliarParametroConsulta = new EnderecoIntegracaoVo();
            enderecoAuxiliarParametroConsulta.setCodgEndereco(fichaImovelRuralVo.getEnderecoIntegracaoVo().getCodgEndereco());
            enderecoAuxiliarParametroConsulta = cadastroBe.buscarEndereco(new EnderecoIntegracaoVo(enderecoAuxiliarParametroConsulta));
            municipio = cadastroBe.obterMunicipioPorCep(enderecoAuxiliarParametroConsulta.getCep());
         }
         
         if(!Validador.isNumericoValido(fichaImovelRuralVo.getCriterioMunicipioVo().getCodigo()))
         {
            ExibirLOG.exibirLogSimplificado("Consultando criterio municipio: "+fichaImovelRuralVo.getCriterioMunicipioVo().getCodigo());
            CriterioMunicipioVo criterioMunicipioVo = new CriterioMunicipioVo();
            criterioMunicipioVo.setMunicipio(municipio);
            criterioMunicipioVo.setStatusResgistroCriterioMunicipioVo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
            fichaImovelRuralVo.setCriterioMunicipioVo(criterioMunicipioBe.consultarCriterioMunicipio(new CriterioMunicipioVo(criterioMunicipioVo)));
           
         }
         ExibirLOG.exibirLog("Calcular por criterio municipio: "+fichaImovelRuralVo.getCriterioMunicipioVo().getCodigo());
         ExibirLOG.exibirLogSimplificado("MINIMO : "+fichaImovelRuralVo.getCriterioMunicipioVo().getValorTotalMinimoFormatado());
         ExibirLOG.exibirLogSimplificado("MEDIO : "+fichaImovelRuralVo.getCriterioMunicipioVo().getValorTotalMedioFormatado());
         ExibirLOG.exibirLogSimplificado("MAXIMO : "+fichaImovelRuralVo.getCriterioMunicipioVo().getValorTotalMaximoFormatado());
         
         
         
         //Busca o critério município para poder categorizar o imóvel rural
         
         
         /* Verificando qual critério de base de calculo será utilizado, MINIMO MÉDIO ou MÁXIMO
          * Após calcula o valor do hectare baseado no valor total e o percentual de base de calculo minimo */
         if(baseCalculoUtilizada.getCriterioBaseCalculo().is(DomnCriterioBaseCalculo.MINIMO))
         {
            valorHectare = (fichaImovelRuralVo.getCriterioMunicipioVo().getValorTotalMinimo() * baseCalculoUtilizada.getPercentualBaseCalculoMinimo()) / 100;
         }
         else
         {
            if(baseCalculoUtilizada.getCriterioBaseCalculo().is(DomnCriterioBaseCalculo.MEDIO)) 
            {
               valorHectare = (fichaImovelRuralVo.getCriterioMunicipioVo().getValorTotalMedio() * baseCalculoUtilizada.getPercentualBaseCalculoMinimo()) / 100;
            }
            else
            {
               valorHectare = (fichaImovelRuralVo.getCriterioMunicipioVo().getValorTotalMaximo() * baseCalculoUtilizada.getPercentualBaseCalculoMinimo()) / 100;
            }
         }
		 double vlrHectareXAreaTotal = valorHectare * areaTotal;
         if(vlrHectareXAreaTotal >= fichaImovelRuralVo.getValorTotalMercado()) 
         {
            if(vlrHectareXAreaTotal >= fichaImovelRuralVo.getValorITR()) 
            {
               retorno = vlrHectareXAreaTotal;			  
			   fichaImovelRuralVo.setVlrTributavelCalculado(true);
            }
            else 
            {
               retorno = fichaImovelRuralVo.getValorITR();
            }
         }
         else 
         {
            if(fichaImovelRuralVo.getValorTotalMercado() >= fichaImovelRuralVo.getValorITR()) 
            {
               retorno = fichaImovelRuralVo.getValorTotalMercado();
			   fichaImovelRuralVo.setVlrTributavelCalculado(true);
            }
            else 
            {
               retorno = fichaImovelRuralVo.getValorITR();			 
            }
         }
		     
      }
      catch (IntegracaoException e)
      {
         e.printStackTrace();
      }
      finally
      {
         if (Validador.isObjetoValido(distanciaBe))
         {
            distanciaBe.close();
         }
         if (Validador.isObjetoValido(baseCalculoImovelRuralBe))
         {
            baseCalculoImovelRuralBe.close();
         }
         if (Validador.isObjetoValido(criterioMunicipioBe))
         {
            criterioMunicipioBe.close();
         }
         if (Validador.isObjetoValido(cadastroBe))
         {
            cadastroBe.close();
         }
      }
      return retorno;
   }
   
   
   /**
    * 
    * 
    * 
    * 
    * 
    * @param valorConjuge1
    * @param valorConjuge2
    * @return
    */
   public static boolean valorBensConjugesIguais(double valorConjuge1 , double valorConjuge2)
   {
      BigDecimal diferenca = BigDecimal.ZERO;

      BigDecimal vConjuge1 = new BigDecimal(valorConjuge1);
      BigDecimal vConjuge2 = new BigDecimal(valorConjuge2);
      diferenca = vConjuge1.subtract(vConjuge2).abs();
      return diferenca.compareTo(VALOR_MINIMO_DIFERENCA_ENTRE_CONJUGES) == 1 ? false : true;

   }
   
   
//   public static void main (String[] args)
//      throws SQLException, ObjetoObrigatorioException, ConsultaException, 
//             ConexaoException, IntegracaoException, 
//             ParametroObrigatorioException
//   {
//      FichaImovelRuralBe fichaImovelBe = new FichaImovelRuralBe();
//      FichaImovelRuralVo fichaImovelRural = new FichaImovelRuralVo();
//      fichaImovelRural.setConsultaCompleta(true);
//      fichaImovelRural = fichaImovelBe.listarFichaImovelRural(new FichaImovelRuralVo(fichaImovelRural));
//      try 
//      {
//         for(Object ficha : fichaImovelRural.getCollVO()) 
//         {
//            FichaImovelRuralVo ficha2 = (FichaImovelRuralVo) ficha;
//            if(ficha2.getCodigo()== 153)
//               System.out.println("O valor é: " + calcularValorTributavelFichaImovelRural(ficha2));
//         }
//      }
//      finally 
//      {
//         fichaImovelBe.close();
//      }
//   }
}
