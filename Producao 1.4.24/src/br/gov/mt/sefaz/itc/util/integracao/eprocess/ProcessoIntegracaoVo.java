package br.gov.mt.sefaz.itc.util.integracao.eprocess;

import br.com.abaco.util.facade.EntidadeFacade;
import br.com.abaco.util.facade.VoIntegrador;

import br.gov.mt.sefaz.eprocess.integracao.DomnStatusProcesso;
import br.gov.mt.sefaz.eprocess.integracao.ProcessoVO;

import java.util.Collection;
import java.util.Date;

public class ProcessoIntegracaoVo implements EntidadeFacade, VoIntegrador
{
   
   private static final long serialVersionUID = Long.MAX_VALUE;
   private long numeroProcesso;
   private long codigoProcesso;
   private int anoProcesso;
   private long numrGiaIcd;
   private TipoProcessoIntegracaoVo tipoProcessoIntegracaoVo;
   private Date dataProtocolo;
   private DomnStatusProcesso statusProcesso;
   private transient Collection collVO;

   public ProcessoIntegracaoVo()
   {
   }

   public ProcessoIntegracaoVo(ProcessoVO processoIntegracao) 
   {
      setCodigoProcesso(processoIntegracao.getCodigoProcesso());
      setNumeroProcesso(processoIntegracao.getNumeroProcesso());
      setAnoProcesso(processoIntegracao.getAnoProcesso());
      setNumrGiaIcd(processoIntegracao.getNumrGiaItcd());
      setTipoProcessoIntegracaoVo(new TipoProcessoIntegracaoVo(processoIntegracao.getTipoProcessoVO()));
      setDataProtocolo(processoIntegracao.getDataProtocolo());
      setStatusProcesso(processoIntegracao.getStatusProcesso());
   }

   public void setNomeSistema(String string)
   {
   }

   public String getNomeSistema()
   {
      return null;
   }

   public void setCollVO(Collection collection)
   {
   }

   public Collection getCollVO()
   {
      return null;
   }

   public boolean isExiste()
   {
      return false;
   }

   public boolean isExisteCollVO()
   {
      return false;
   }

   public boolean isConsultaCompleta()
   {
      return false;
   }

   public boolean isConsultaParametrizada()
   {
      return false;
   }

   public void setUsuarioLogado(long l)
   {
   }

   public long getUsuarioLogado()
   {
      return 0l;
   }

   public void setMensagem(String string)
   {
   }

   public String getMensagem()
   {
      return null;
   }

   public int compareTo(Object o)
   {
      return 0;
   }

   public int compare(Object o1, Object o2)
   {
      return 0;
   }

   public long getNumeroProcesso()
   {
      return numeroProcesso;
   }

   public void setNumeroProcesso(long numeroProcesso)
   {
      this.numeroProcesso = numeroProcesso;
   }

   public long getCodigoProcesso()
   {
      return codigoProcesso;
   }

   public void setCodigoProcesso(long codigoProcesso)
   {
      this.codigoProcesso = codigoProcesso;
   }

   public int getAnoProcesso()
   {
      return anoProcesso;
   }

   public void setAnoProcesso(int anoProcesso)
   {
      this.anoProcesso = anoProcesso;
   }

   public long getNumrGiaIcd()
   {
      return numrGiaIcd;
   }

   public void setNumrGiaIcd(long numrGiaIcd)
   {
      this.numrGiaIcd = numrGiaIcd;
   }

   public TipoProcessoIntegracaoVo getTipoProcessoIntegracaoVo()
   {
      return tipoProcessoIntegracaoVo;
   }

   public void setTipoProcessoIntegracaoVo(TipoProcessoIntegracaoVo tipoProcessoIntegracaoVo)
   {
      this.tipoProcessoIntegracaoVo = tipoProcessoIntegracaoVo;
   }

   public Date getDataProtocolo()
   {
      return dataProtocolo;
   }

   public void setDataProtocolo(Date dataProtocolo)
   {
      this.dataProtocolo = dataProtocolo;
   }

   public DomnStatusProcesso getStatusProcesso()
   {
      return statusProcesso;
   }

   public void setStatusProcesso(DomnStatusProcesso statusProcesso)
   {
      this.statusProcesso = statusProcesso;
   }
}
