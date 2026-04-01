package br.gov.mt.sefaz.itc.integracao;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd.StatusGIAITCDBe;
import br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd.StatusGIAITCDVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusGIAITCD;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProtocolo;
import br.gov.mt.sefaz.itc.util.dominio.DomnVersaoGIAITCD;

import java.io.IOException;

import java.sql.Connection;
import java.sql.SQLException;


public class IntegracaoItcd extends AbstractBe
{
   
   public IntegracaoItcd(Connection conexao)
   {
      super(conexao);
   }
   
   public IntegracaoItcd()
      throws SQLException
   {
      super();
   }

   /**
    * @param codgItcdTemp
    * @return verificarValidadeGiaItcdPorNumeroVO
    * @throws ObjetoObrigatorioException
    * Método responsável por verificar a validade da GIA do itcd pelo número
    * e retornar duas propriedades uma caso esteja inválido com uma mensagem de erro
    * ou se for válido sem a mensagem de erro, ambas propriedades estarão dentro do
    * Objeto verificarValidadeGiaItcdPorNumeroVO
    */
      public VerificarValidadeGiaItcdPorNumeroVO verificarValidadeGiaItcdPorNumero(Long codgItcdTemp)
      throws ObjetoObrigatorioException, ConsultaException, ConexaoException, 
             ParametroObrigatorioException, IntegracaoException, SQLException
   {  
      if(!Validador.isNumericoValido(codgItcdTemp))
      {
         return new VerificarValidadeGiaItcdPorNumeroVO(false, "Parâmetro código GIA-ITCD inválido!");
      }
      GIAITCDBe gIAITCDBe = null;
      GIAITCDVo giaITCDVo = null;
      StatusGIAITCDBe statusGIAITCDBe = null;
      VerificarValidadeGiaItcdPorNumeroVO metodoIntegracaoVo = null;
      StatusGIAITCDVo statusGIAITCDVo = null;
      try
      {
         //Instancia os vo's e be's
         metodoIntegracaoVo = new VerificarValidadeGiaItcdPorNumeroVO();
         giaITCDVo = new GIAITCDVo(codgItcdTemp);
         gIAITCDBe = new GIAITCDBe(conn);
         statusGIAITCDBe = new StatusGIAITCDBe(conn);
         
         //Seta o objeto consulta do Vo e Realiza a consulta
         giaITCDVo.setParametroConsulta(giaITCDVo);
         giaITCDVo = gIAITCDBe.consultaGIAITCDBasico(giaITCDVo);
         
         //Recuperando o último status da gia-ITCD
         statusGIAITCDVo = statusGIAITCDBe.procurarUltimoStatusCollectionVo(giaITCDVo.getStatusVo());
         
         //Realizando as verificações para ver se é válida
         if(giaITCDVo.getNumeroVersaoGIAITCD().is(DomnVersaoGIAITCD.VERSAO_1) || giaITCDVo.getTipoProtocoloGIA().is(DomnTipoProtocolo.PROTOCOLO_MANUAL))
         {
            metodoIntegracaoVo.setValido(false);
            metodoIntegracaoVo.setMsgErro("Protocolo da GIA-ITCD deverá ser manual");
         }
         else
         {  //Status da GIA ainda em elaboração
            if(statusGIAITCDVo.getStatusGIAITCD().is(DomnStatusGIAITCD.EM_ELABORACAO))
            {
               metodoIntegracaoVo.setValido(false);
               metodoIntegracaoVo.setMsgErro("Não autorizada, GIA-ITCD não finalizada");
            }
            else 
            {  
               //Caso status da gia seja em elaboracao e pendente de protocolo
               if(!statusGIAITCDVo.getStatusGIAITCD().is(DomnStatusGIAITCD.PENDENTE_DE_PROTOCOLO))
               {
                  metodoIntegracaoVo.setValido(false);
                  metodoIntegracaoVo.setMsgErro("Protocolo não autorizado");
               }
               else
               {
                   metodoIntegracaoVo.setValido(true);
               }
            }
         }
      }
      catch(ParametroObrigatorioException ex)
      {
         metodoIntegracaoVo.setValido(false);
         metodoIntegracaoVo.setMsgErro("GIA-ITCD não existente");
      }
      catch (IOException e)
      {
          metodoIntegracaoVo.setValido(false);
          metodoIntegracaoVo.setMsgErro("GIA-ITCD não existente");
      }
      return metodoIntegracaoVo;
   }
   
   
   public static void main(String[] args)
   {
      IntegracaoItcd i;
      try
      {
         i = new IntegracaoItcd();
        VerificarValidadeGiaItcdPorNumeroVO v = i.verificarValidadeGiaItcdPorNumero(2508l);
        System.out.println("MSG: "+v.getMsgErro());
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }
   
   
}
