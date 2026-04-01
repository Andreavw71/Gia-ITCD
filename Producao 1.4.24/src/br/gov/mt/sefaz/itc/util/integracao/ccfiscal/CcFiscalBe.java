package br.gov.mt.sefaz.itc.util.integracao.ccfiscal;

import br.com.abaco.log5.util.excecoes.IncluiException;
import br.com.abaco.util.StringUtil;
import br.com.abaco.util.Validador;
import br.com.abaco.util.data.AbacoData;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.DadoNecessarioInexistenteException;
import br.com.abaco.util.exceptions.IntegracaoErro;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;

import br.com.abaco.util.exceptions.RegistroNaoPodeSerUtilizadoException;

import br.com.abaco.util.numero.AbacoNumero;

import br.gov.mt.sefaz.ccfiscal.integracao.DebitoVO;
import br.gov.mt.sefaz.ccfiscal.integracao.DomnDecadencia;
import br.gov.mt.sefaz.ccfiscal.integracao.DomnFlagLogico;
import br.gov.mt.sefaz.ccfiscal.integracao.DomnGrauVinculacaoDebito;
import br.gov.mt.sefaz.ccfiscal.integracao.DomnModalidadeLancamento;
import br.gov.mt.sefaz.ccfiscal.integracao.DomnPrescricao;
import br.gov.mt.sefaz.ccfiscal.integracao.DomnTipoLancamento;
import br.gov.mt.sefaz.ccfiscal.integracao.FiltroConsultaDebitoVO;
import br.gov.mt.sefaz.ccfiscal.integracao.IntegracaoCCFiscal;
//import br.gov.mt.sefaz.eprocess.util.integracao.sos.SosBe;
import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.model.generico.beneficiario.BeneficiarioVo;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd.StatusGIAITCDVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.configuracaogerencialparametros.ConfiguracaoGerencialParametrosBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.configuracaogerencialparametros.ConfiguracaoGerencialParametrosVo;
import br.gov.mt.sefaz.itc.util.data.DataUtil;
import br.gov.mt.sefaz.itc.util.dominio.DomnCodigoTributacaoITCD;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusGIAITCD;
import br.gov.mt.sefaz.itc.util.facade.ConfiguracaoITCD;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;

import br.gov.mt.sefaz.itc.util.integracao.cadastro.CadastroBe;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.ContribuinteIntegracaoVo;

//import br.gov.mt.sefaz.itc.util.integracao.sos.SosBe;

import java.sql.Connection;
import java.sql.SQLException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collection;

import java.util.Date;

import java.util.Iterator;

import sefaz.mt.util.SefazDataHora;


public class CcFiscalBe extends AbstractBe
{
	public CcFiscalBe(Connection conn)
	{
		super(conn);
	}

	public CcFiscalBe() throws SQLException
	{
		super();
	}

	public InstrumentoConstitutivoIntegracaoVo selecionarInstrumentoConstitutivoPorSigla(InstrumentoConstitutivoIntegracaoVo instrumentoConstitutivoIntegracaoVo) throws ObjetoObrigatorioException, 
			  IntegracaoException
	{
		Validador.validaObjeto(instrumentoConstitutivoIntegracaoVo);
		try
		{
			if (!Validador.isStringValida(instrumentoConstitutivoIntegracaoVo.getParametroConsulta().getSiglaInstrumentoConstitutivo()))
			{
				throw new ParametroObrigatorioException(MensagemErro.A_SIGLA_DO_INSTRUMENTO_CONSTITUTIVO_OBRIGATORIO); 
			}
			String siglaInstrumentoConstitutivo = instrumentoConstitutivoIntegracaoVo.getParametroConsulta().getSiglaInstrumentoConstitutivo();
			InstrumentoConstitutivoIntegracaoVo instrumentoConstitutivoIntegracaoRetorno = new InstrumentoConstitutivoIntegracaoVo(new IntegracaoCCFiscal(conn).selecionarInstrumentoConstitutivoPorSigla(siglaInstrumentoConstitutivo));
			if (instrumentoConstitutivoIntegracaoRetorno == null || !Validador.isNumericoValido(instrumentoConstitutivoIntegracaoRetorno.getCodigoInstrumentoConstitutivo())) 
			{
				throw new Exception(MensagemErro.INSTRUMENTO_CONSTITUTIVO_NAO_CADASTRADO_NO_CCF);		
			}
			return instrumentoConstitutivoIntegracaoRetorno;
		}
		catch (Exception e)
		{
			throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_CONTA_CORRENTE, e);
		}
		catch (Error e)
		{
			throw IntegracaoErro.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_CONTA_CORRENTE, e);
		}
	}

	
	public long obterCodigoInstrumentoConstitutivoPorGIA() throws ObjetoObrigatorioException, IntegracaoException
	{
		InstrumentoConstitutivoIntegracaoVo instrumentoConstitutivoIntegracaoVo = new InstrumentoConstitutivoIntegracaoVo();
		instrumentoConstitutivoIntegracaoVo.setSiglaInstrumentoConstitutivo(ConfiguracaoITCD.SIGLA_INSTRUMENTO_CONSTITUTIVO_ITCD);
		instrumentoConstitutivoIntegracaoVo = new InstrumentoConstitutivoIntegracaoVo(instrumentoConstitutivoIntegracaoVo);
		return selecionarInstrumentoConstitutivoPorSigla(instrumentoConstitutivoIntegracaoVo).getCodigoInstrumentoConstitutivo();
	}

   public FiltroConsultaDebitoIntegracaoVo prepararFiltroConsultaDebito(final GIAITCDVo giaItcdVo) throws ObjetoObrigatorioException, 
           IntegracaoException, ParseException, ParametroObrigatorioException
   {
      FiltroConsultaDebitoIntegracaoVo filtroParamConsultaDebitoIntegracaoVo = new FiltroConsultaDebitoIntegracaoVo();
      //numero contribuinte
      Collection<Long> listaContribuinte = new ArrayList<Long>();
      for ( Object object : giaItcdVo.getBeneficiarioVo().getCollVO())
      {
         BeneficiarioVo beneficiarioAtualVo = (BeneficiarioVo) object;
         listaContribuinte.add(beneficiarioAtualVo.getPessoaBeneficiaria().getNumrContribuinte());
      }
      filtroParamConsultaDebitoIntegracaoVo.setNumeroPessoaContribuinte(listaContribuinte);      
		//numero documento credito
      filtroParamConsultaDebitoIntegracaoVo.setNumeroDocumentoCreditoTributario(giaItcdVo.getCodigoFormatado());
		//codigo instrumento constitutivo
		filtroParamConsultaDebitoIntegracaoVo.setCodigoInstrumentoConstitutivo(obterCodigoInstrumentoConstitutivoPorGIA());
      //período de referência (campo obrigatório no ccf)
      SefazDataHora periodoReferencia = null;
      if(Validador.isDataValida(giaItcdVo.getStatusVo().getDataEmissaoRetificacao()))
      {
         periodoReferencia = new SefazDataHora(giaItcdVo.getStatusVo().getDataEmissaoRetificacao());
      }
      else if(Validador.isDataValida(giaItcdVo.getStatusVo().getDataNotificacao()))
      {
         periodoReferencia = new SefazDataHora(giaItcdVo.getStatusVo().getDataNotificacao());
      }
      else
      {
         //Regra interna, este campo deve estar preenchido.
         throw new ParametroObrigatorioException("Data notificação ou data emissão retificação é obrigatório.");
      }
      int mes = periodoReferencia.getMes();
      int ano = periodoReferencia.getAno();
      filtroParamConsultaDebitoIntegracaoVo.setPeriodoReferenciaInicial(new SimpleDateFormat("dd/MM/yyyy").parse("01/"+mes+"/"+ano));
      filtroParamConsultaDebitoIntegracaoVo.setPeriodoReferenciaFinal(new SimpleDateFormat("dd/MM/yyyy").parse("01/"+mes+"/"+ano));
      //
      FiltroConsultaDebitoIntegracaoVo filtroConsultaDebitoIntegracaoVo = new FiltroConsultaDebitoIntegracaoVo(filtroParamConsultaDebitoIntegracaoVo);				
      return filtroConsultaDebitoIntegracaoVo;
   }
   
   public DebitoIntegracaoVo consultarDebito(FiltroConsultaDebitoIntegracaoVo filtroConsultaDebitoIntegracaoVo) throws ObjetoObrigatorioException, 
           IntegracaoException
   {
      Validador.validaObjeto(filtroConsultaDebitoIntegracaoVo);
      FiltroConsultaDebitoVO filtroConsultaDebitoVO = null;
      try
      {
         filtroConsultaDebitoVO = new FiltroConsultaDebitoVO();
         //LISTA DE CONTRIBUINTES
         if (!Validador.isCollectionValida(filtroConsultaDebitoIntegracaoVo.getParametroConsulta().getNumeroPessoaContribuinte()))
         {
            throw new ParametroObrigatorioException(MensagemErro.O_NUMERO_PESSOA_E_OBRIGATORIO); 
         }
         filtroConsultaDebitoVO.setNumeroPessoaContribuinte(filtroConsultaDebitoIntegracaoVo.getParametroConsulta().getNumeroPessoaContribuinte());
         //NUMERO DOCUMENTO CREDITO TRIBUTARIO
         if (!Validador.isStringValida(filtroConsultaDebitoIntegracaoVo.getParametroConsulta().getNumeroDocumentoCreditoTributario()))
         {
            throw new ParametroObrigatorioException(MensagemErro.O_NUMERO_DOCUMENTO_CREDITO_TRIBUTARIO_OBRIGATORIO); 
         }
         filtroConsultaDebitoVO.setNumeroDocumentoCreditoTributario(filtroConsultaDebitoIntegracaoVo.getParametroConsulta().getNumeroDocumentoCreditoTributario());
         //INSTRUMENTO CONSTITUTIVO
         filtroConsultaDebitoVO.setCodigoInstrumentoConstitutivo(filtroConsultaDebitoIntegracaoVo.getParametroConsulta().getCodigoInstrumentoConstitutivo());
         //PERIODO REFERENCIA INICIAL
         if (!Validador.isDataValida(filtroConsultaDebitoIntegracaoVo.getParametroConsulta().getPeriodoReferenciaInicial()))
         {
            throw new ParametroObrigatorioException(MensagemErro.O_PERIODO_REFERENCIA_OBRIGATORIO); 
         }
         filtroConsultaDebitoVO.setPeriodoReferenciaInicial(filtroConsultaDebitoIntegracaoVo.getParametroConsulta().getPeriodoReferenciaInicial());
         //PERIODO REFERENCIA FINAL
         if (!Validador.isDataValida(filtroConsultaDebitoIntegracaoVo.getParametroConsulta().getPeriodoReferenciaFinal()))
         {
            throw new ParametroObrigatorioException(MensagemErro.O_PERIODO_REFERENCIA_OBRIGATORIO); 
         }
         filtroConsultaDebitoVO.setPeriodoReferenciaFinal(filtroConsultaDebitoIntegracaoVo.getParametroConsulta().getPeriodoReferenciaFinal());

         return new DebitoIntegracaoVo(new IntegracaoCCFiscal(conn).consultarDebito(filtroConsultaDebitoVO));
      }
      catch (Exception e)
      {
         throw IntegracaoException.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_CONTA_CORRENTE, e);
      }
      catch (Error e)
      {
         throw IntegracaoErro.getInstance(IntegracaoException.OPERACAO_CONSULTAR, IntegracaoException.NOME_SISTEMA_INTEGRACAO_CONTA_CORRENTE, e);
      }
   }
   
   private Date determinaPeriodoRef(final GIAITCDVo giaItcd) throws ConsultaException, ObjetoObrigatorioException, 
           ParametroObrigatorioException
   {  
      Validador.validaObjeto(giaItcd);
      StatusGIAITCDVo atual = giaItcd.getStatusVo();
      StatusGIAITCDVo statusAnterior = new GIAITCDBe(conn).obterStatusAnteriorGIAITCD(giaItcd);
      
      if(statusAnterior.getStatusGIAITCD().is(DomnStatusGIAITCD.REATIVADO))
      {
         return AbacoData.converteDataComPrimeiroMinutoDia(statusAnterior.getDataAtualizacao());
      }
      else
      {
         if(atual.getStatusGIAITCD().is(DomnStatusGIAITCD.RETIFICADO))
         {
            return atual.getDataEmissaoRetificacao();
         }
         else if(atual.getStatusGIAITCD().is(DomnStatusGIAITCD.NOTIFICADO))
         {
            return atual.getDataNotificacao();
         }
         else if(atual.getStatusGIAITCD().is(DomnStatusGIAITCD.NOTIFICADO_CIENTE))
         {
            return atual.getDataCienciaNotificacao();
         }
         else if(atual.getStatusGIAITCD().is(DomnStatusGIAITCD.RETIFICADO_CIENTE))
         {
            return atual.getDataCienciaRetificacao();
         }
         else if(atual.getStatusGIAITCD().is(DomnStatusGIAITCD.PARA_INSCRICAO_EM_DIVIDA_ATIVA))
         {           
            if(statusAnterior.isStatusIn(new int[] {DomnStatusGIAITCD.NOTIFICADO, DomnStatusGIAITCD.RETIFICADO, DomnStatusGIAITCD.NOTIFICADO_CIENTE, DomnStatusGIAITCD.RETIFICADO_CIENTE}))
            {
               if(statusAnterior.getStatusGIAITCD().is(DomnStatusGIAITCD.RETIFICADO))
               {
                  return statusAnterior.getDataEmissaoRetificacao();
               }
               else if(statusAnterior.getStatusGIAITCD().is(DomnStatusGIAITCD.NOTIFICADO))
               {
                  return statusAnterior.getDataNotificacao();
               }
               else if(statusAnterior.getStatusGIAITCD().is(DomnStatusGIAITCD.NOTIFICADO_CIENTE))
               {
                  return statusAnterior.getDataCienciaNotificacao();
               }
               else if(statusAnterior.getStatusGIAITCD().is(DomnStatusGIAITCD.RETIFICADO_CIENTE))
               {
                  return statusAnterior.getDataCienciaRetificacao();
               }              
            }
         }        
      }
      throw new ConsultaException(MensagemErro.VALIDAR_GIA_ITCD_VINCULAR_DAR_NAO_ENCONTRADO_STATUS_DE_NOTIFICADO_OU_RETIFICADO);
   }

   private Date obtemDataVencimento(Date periodoRefDar, GIAITCDVo giaItcdVo, int numeroParcela ) throws ObjetoObrigatorioException, IntegracaoException, 
           DadoNecessarioInexistenteException, ConexaoException, ConsultaException, 
           ParametroObrigatorioException
   {
 
      AbacoData abacoData = new AbacoData();
      int codgMunicipioContribuinte = obtemCodigoMunicipioContribuinte(giaItcdVo);
      Date dataInicial = periodoRefDar;
      Date proximoDiaDataInicial = DataUtil.adicionaDia(dataInicial, 1);
      Date dataVencimento = null;
      if(numeroParcela > 1)
      {
         int adicionaMes = numeroParcela - 1;
         proximoDiaDataInicial = DataUtil.adicionaMes(dataInicial, adicionaMes);
      }
      dataVencimento = DataUtil.dataUltimoDiaMes(proximoDiaDataInicial);
      if(!abacoData.isDiaUtil(dataVencimento, codgMunicipioContribuinte))
      {
         dataVencimento = abacoData.getProximoDiaUtil(proximoDiaDataInicial, codgMunicipioContribuinte);
      
      }
      return dataVencimento;     
   }
   
   private int obtemCodigoMunicipioContribuinte(GIAITCDVo giaITCDVo) throws ObjetoObrigatorioException, 
           IntegracaoException, DadoNecessarioInexistenteException
   {
      Validador.validaObjeto(giaITCDVo);
      CadastroBe cadastroBe = new CadastroBe(conn);
      ContribuinteIntegracaoVo consulta = new ContribuinteIntegracaoVo(giaITCDVo.getContribuinteNotificacaoDar().getNumrContribuinte());
      consulta = new ContribuinteIntegracaoVo(consulta);
      consulta = cadastroBe.obterContribuinte(consulta);
      if(consulta.getSiglaUF().equals(ConfiguracaoITCD.SIGLA_ESTADO_MATO_GROSSO))
      {
         if(Validador.isNumericoValido(consulta.getCodgMunicipio()))
         {
            return consulta.getCodgMunicipio().intValue();
         }
         else
         {
            throw new DadoNecessarioInexistenteException(MensagemErro.NAO_FOI_POSSIVEL_CONSULTAR_MUNICIPIO_CONTRIBUINTE);
         }        
      }
      else if (Validador.isNumericoValido(giaITCDVo.getAgenciaProtocolo().getCodgMunicipio()))
      {
         return giaITCDVo.getAgenciaProtocolo().getCodgMunicipio().intValue();         
      }
      else
      {
         throw new DadoNecessarioInexistenteException(MensagemErro.NAO_FOI_POSSIVEL_CONSULTAR_MUNICIPIO_CONTRIBUINTE);
      }              
      
   }
   
   private void gerarDAR()
   {
      //new IntegracaoCCFiscal(conn).
   }

}
