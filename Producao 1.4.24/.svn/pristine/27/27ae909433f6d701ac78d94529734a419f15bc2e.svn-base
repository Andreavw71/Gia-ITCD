package br.gov.mt.sefaz.itc.model.log.log;

import br.com.abaco.log5.anotacoes.AnotacaoAtributo;
import br.com.abaco.log5.anotacoes.AnotacaoIdentificador;
import br.com.abaco.log5.anotacoes.AnotacaoTabelaClasse;
import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.model.log.historicolog.HistoricoLogVo;
import br.gov.mt.sefaz.itc.util.EntidadeVo;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposLog;
import br.gov.mt.sefaz.itc.util.facade.tabelas.TabelasITC;

import java.util.Date;


/**
 * Classe LogVo utilizada para representar a tabela
 * ITCTB48_LOG contento todos os atributos da tabela
 * e servindo como um registro para cada tupla.
 * @author Dherkyan Ribeiro da Silva
 * @version $Revision: 1.0 $
 */

@AnotacaoTabelaClasse(nomeTabela = TabelasITC.TABELA_GIA_ITCD_LOG, nomeAmigavel = "LOG-ITCD", identificadores =
      { @AnotacaoIdentificador(nomeAtributo = "codigo", nomeColuna = CamposLog.CAMPO_NUM_LOG_SEQC)
         } )
public class LogITCDVo extends EntidadeVo<LogITCDVo>
{
   // ----------------------------------------------------------------------- Atributos ------------------------------------------------------------------------
   private static final long serialVersionUID = Long.MAX_VALUE;
   
   /** Endereço IP do computador que está realizando as operações.*/
   private String enderecoIP;
   
   /** Data de registro das operações. */
   private Date dataTransacao;
   
   /** Registros de tabelas alteradas. */
   private HistoricoLogVo historicoLogVo;


   // ------------------------------------------------------------------- Construtores -------------------------------------------------------------------------

   public LogITCDVo()
   {
      super();
   }

   public LogITCDVo(long codigo)
   {
      super(codigo);
   }

   public LogITCDVo(LogITCDVo logITCDVo)
   {
      super();
      setParametroConsulta(logITCDVo);
   }

   // ------------------------------------------------------------------- Getters & Setters Utilitarios --------------------------------------------------------

   // --------------------------------------------------------------------- Getters & Setters Básicos----------------------------------------------------------- 

   public long getCodigo()
   {
      return super.getCodigo();
   }

   public void setEnderecoIP(String enderecoIP)
   {
      this.enderecoIP = enderecoIP;
   }

   @AnotacaoAtributo(nomeColuna = CamposLog.CAMPO_ENDERECO_IP, obrigatorio = false)
   public String getEnderecoIP()
   {
      return enderecoIP;
   }

   public void setDataTransacao(Date dataTransacao)
   {
      this.dataTransacao = dataTransacao;
   }

   @AnotacaoAtributo(nomeColuna = CamposLog.CAMPO_DATA_TRANSACAO, obrigatorio = false, timestamp = true)
   public Date getDataTransacao()
   {
      return dataTransacao;
   }

   public void setHistoricoLogVo(HistoricoLogVo historicoLogVo)
   {
      this.historicoLogVo = historicoLogVo;
   }

   public HistoricoLogVo getHistoricoLogVo()
   {
      if (!Validador.isObjetoValido(historicoLogVo))
      {
         setHistoricoLogVo(new HistoricoLogVo());
      }
      return historicoLogVo;
   }

}
