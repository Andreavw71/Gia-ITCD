package br.gov.mt.sefaz.itc.model.relatorio.parametrorelatorio;

import br.com.abaco.util.Validador;

import java.util.Date;

import sefaz.mt.util.SefazDataHora;

public class ParametroRelatorioValorAposAvaliacaoVo extends ParametroRelatorioVo 
{
   /*
    * Data inicial considerando o campo
    * ITCTB14.DATA_CRIACAO
    * 
    */
   private Date dataInicial;
   
    /*
     * Data final considerando o campo
     * ITCTB14.DATA_CRIACAO
     * 
     */
   private Date dataFinal;
    
   
   
   public ParametroRelatorioValorAposAvaliacaoVo()
   {
   }
   
   
   /**
    * Retorna a Data de Inicial formatada
    * @return
    * @implemented by Dherkyan Ribeiro da Silva
    */
   public String getDataInicialFormatada()
   {
     return (Validador.isDataValida(dataInicial)) ? new SefazDataHora(dataInicial).formata(MASCARA_DATA_FORMATADA) : STRING_VAZIA;
   }
   
   
   /**
    * Retorna a Data de Inicial formatada
    * @return
    * @implemented by Dherkyan Ribeiro da Silva
    */
   public String getDataFinalFormatada()
   {
     return (Validador.isDataValida(dataFinal)) ? new SefazDataHora(dataFinal).formata(MASCARA_DATA_FORMATADA) : STRING_VAZIA;
   }
   
}
