package br.gov.mt.sefaz.itc.util.integracao.eprocess;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.IntegracaoErro;
import br.com.abaco.util.exceptions.IntegracaoException;

import br.gov.mt.sefaz.eprocess.integracao.IntegracaoEProcess;
import br.gov.mt.sefaz.eprocess.integracao.ProcessoVO;
import br.gov.mt.sefaz.eprocess.integracao.TipoProcessoVO;
import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class EprocessBe extends AbstractBe
{

   public EprocessBe()
      throws SQLException
   {
      super();
   }
   
   public EprocessBe(Connection conn)
   {
      super(conn);
   }
   
   public Collection<ProcessoIntegracaoVo> listarProcessoPorCodigoGiaITCD(long codigo)
      throws IntegracaoException
   {
      Collection<ProcessoIntegracaoVo> colecaoDeProcessos = null;
      Collection<ProcessoVO> colecaoDeProcessosIntegracao = null;
      IntegracaoEProcess integracaoEprocess = null;
      try 
      {
         integracaoEprocess = new IntegracaoEProcess(conn);
         colecaoDeProcessosIntegracao = integracaoEprocess.consultarRelacaoProcessoPorNumrGiaItcd(codigo);
         colecaoDeProcessos = new ArrayList<ProcessoIntegracaoVo>();
         for(ProcessoVO processoIntegracao : colecaoDeProcessosIntegracao) 
         {
            ProcessoIntegracaoVo processoIntegracaoVo = new ProcessoIntegracaoVo(processoIntegracao);
            colecaoDeProcessos.add(processoIntegracaoVo);
         }
      }
      catch (Exception e)
      {
         throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_EPROCESS, e);
      }
      return colecaoDeProcessos;
   }
   
   /**
    * <b>Objetivo: </b> Este método tem por objetivo listar todos
    * os tipos de processos do sistema E-Process que estão ativos e possuem
    * integração com o ITCD.
    * 
    * 
    * @param tipoProcessoIntegracaoVo
    * @return tipoProcessoIntegracaoVo
    * @implemented by Dherkyan Ribeiro
    */
   public TipoProcessoIntegracaoVo listarTipoProcessoAtivoEprocessComITCD(TipoProcessoIntegracaoVo tipoProcessoIntegracaoVo) throws IntegracaoException
   {
      IntegracaoEProcess integracaoEprocess = new IntegracaoEProcess(conn);
      Collection<TipoProcessoVO> processos;
      try
      { 
         processos = integracaoEprocess.consultarRelacaoTipoProcessoAtivoComIntgGiaItcd();
         for( TipoProcessoVO tipoProcessoVO : processos)
         {
            TipoProcessoIntegracaoVo tpVo =  new TipoProcessoIntegracaoVo(tipoProcessoVO);
            tipoProcessoIntegracaoVo.getCollVO().add( tpVo );
         }
         return tipoProcessoIntegracaoVo;
      }
      catch (Exception e)
      {
         throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_EPROCESS , e);
      }
      catch (Error e)
      {
         throw IntegracaoErro.getInstance(IntegracaoErro.OPERACAO_CONSULTAR, IntegracaoErro.NOME_SISTEMA_INTEGRACAO_EPROCESS, e);
      }
   }
   
   /**
    * <b>Objetivo:</b> este método tem por objetivo verificar se o protocolo da gia esta válidado
    * <b>Protocolo Valido: </b> é considerado validado o protocolo que tiver ano e número do processo.
    * 
    * @param codigoGIA
    * @return true se e somente o úlitmo protocolo feito para GIA contiver número do processo e ano.
    */
   public boolean isProcessoGIAEprocessValidado(long codigoGIA) throws IntegracaoException
   {
      boolean valido = false;
      try
      {
       Collection<ProcessoIntegracaoVo> processos = new EprocessBe().listarProcessoPorCodigoGiaITCD(codigoGIA);
       for(ProcessoIntegracaoVo processo : processos)
       {
          valido = (Validador.isNumericoValido(processo.getAnoProcesso()) & Validador.isNumericoValido(processo.getNumeroProcesso()));
       }
      }
      catch (Exception e)
      {
         throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_EPROCESS , e);
      }
      catch (Error e)
      {
         throw IntegracaoErro.getInstance(IntegracaoErro.OPERACAO_CONSULTAR, IntegracaoErro.NOME_SISTEMA_INTEGRACAO_EPROCESS, e);
      }
      return valido;
   }
   
   
   /**
    * <b>Objetivo:</b> este método tem por objetivo verificar se a processo, não válidado, para gia no sistema E-PROCESS,
    *
    * <b>Protocolo Valido: </b> considera-se que há processo quando houver um processo no sistema E-PROCESS, porém sem número e sem ano.
    * 
    * @param codigoGIA
    * @return true se e somente o úlitmo processo feito para GIA não tiver número do processo nem ano.
    */
   public boolean isGIAComProcessoNoEprocess(long codigoGIA) throws IntegracaoException
   {
      boolean valido = false;
      try
      {
       Collection<ProcessoIntegracaoVo> processos = new EprocessBe().listarProcessoPorCodigoGiaITCD(codigoGIA);
       for(ProcessoIntegracaoVo processo : processos)
       {
          valido = (!Validador.isNumericoValido(processo.getAnoProcesso()) & !Validador.isNumericoValido(processo.getNumeroProcesso()));
       }
      }
      catch (Exception e)
      {
         throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_EPROCESS , e);
      }
      catch (Error e)
      {
         throw IntegracaoErro.getInstance(IntegracaoErro.OPERACAO_CONSULTAR, IntegracaoErro.NOME_SISTEMA_INTEGRACAO_EPROCESS, e);
      }
      return valido;
   }

   public String exibirStatusProcessoGiaEprocess(long codigoGia)
   {
      String msg = "";
      EprocessBe processoBe = null;
      try
      {
         processoBe = new EprocessBe();
         Collection<ProcessoIntegracaoVo> processos = processoBe.listarProcessoPorCodigoGiaITCD(codigoGia);
         msg = "Pendente";
         for (ProcessoIntegracaoVo processo: processos)
         {
            if (Validador.isNumericoValido(processo.getAnoProcesso()) & 
               Validador.isNumericoValido(processo.getNumeroProcesso()))
            {
               //msg = "" + processo.getNumeroProcesso() + "/" + processo.getAnoProcesso();
               msg = "Protocolado";
            }
            else if (!Validador.isNumericoValido(processo.getAnoProcesso()) & 
               !Validador.isNumericoValido(processo.getNumeroProcesso()))
            {
               msg = "Protocolo não validado";
            }
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      finally{      
         if (processoBe != null)
         {
            processoBe.close();
            processoBe = null;
         }      
      }
      
      return msg;
   }
   
   
   public String getNumeroProcessoEprocess(long codigoGia)
   {
      String numero = "";
      EprocessBe processoBe = null;
      try
      {
         processoBe = new EprocessBe();
         Collection<ProcessoIntegracaoVo> processos = processoBe.listarProcessoPorCodigoGiaITCD(codigoGia);
         for (ProcessoIntegracaoVo processo: processos)
         {
            if (Validador.isNumericoValido(processo.getAnoProcesso()) & 
               Validador.isNumericoValido(processo.getNumeroProcesso()))
            {
               numero = "" + processo.getNumeroProcesso() + "/" + processo.getAnoProcesso();
            }
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      finally
      {
         if(processoBe != null)
         {
            processoBe.close();
            processoBe = null;
         }
      }
      return numero;
   }
   
   
   public static void main(String[] args)
   {
         System.out.println("EprocessBe - Main executado");
         
      EprocessBe ep;
      try
      {
      /*
         ep = new EprocessBe();
         String str =  ep.exibirStatusProcessoGiaEprocess(2529);
         System.out.println(str);
      */
       String pt ="\\d+/\\d{4}";
       String msg = "123/2016";
       if(msg.matches(pt))
       {
          System.out.println("SIM");
       }else
       {
          System.out.println("NÃO");
       }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

 
   
}
