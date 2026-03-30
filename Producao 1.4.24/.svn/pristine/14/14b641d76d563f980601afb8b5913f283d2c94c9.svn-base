package br.gov.mt.sefaz.itc.model.log;

import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelVo;
import br.gov.mt.sefaz.itc.model.generico.beneficiario.BeneficiarioVo;
import br.gov.mt.sefaz.itc.model.generico.conjuge.ConjugeBemTributavelVo;
import br.gov.mt.sefaz.itc.model.generico.fichaimovel.FichaImovelVo;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.FichaImovelRuralVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.benfeitoria.FichaImovelRuralBenfeitoriaVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.construcao.FichaImovelRuralConstrucaoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.cultura.FichaImovelRuralCulturaVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelrural.rebanho.FichaImovelRuralRebanhoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelurbano.FichaImovelUrbanoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.fichaimovelurbano.benfeitoria.FichaImovelUrbanoBenfeitoriaVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.GIAITCDDoacaoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.GIAITCDDoacaoBeneficiarioVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.GIAITCDInventarioArrolamentoVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.beneficiario.GIAITCDInventarioArrolamentoBeneficiarioVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.beneficiario.aliquota.GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo;
import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdseparacaodivorcio.GIAITCDSeparacaoDivorcioVo;
import br.gov.mt.sefaz.itc.model.log.comparador.ComparadorAvaliacaoBemTributavelVo;
import br.gov.mt.sefaz.itc.model.log.comparador.ComparadorBemTributavelVo;
import br.gov.mt.sefaz.itc.model.log.comparador.ComparadorBeneficiarioVo;
import br.gov.mt.sefaz.itc.model.log.comparador.ComparadorConjugeBemTributavelVo;
import br.gov.mt.sefaz.itc.model.log.comparador.ComparadorEnderecoIntegracaoVo;
import br.gov.mt.sefaz.itc.model.log.comparador.ComparadorFichaImovelRuralBenfeitoriaVo;
import br.gov.mt.sefaz.itc.model.log.comparador.ComparadorFichaImovelRuralConstrucaoVo;
import br.gov.mt.sefaz.itc.model.log.comparador.ComparadorFichaImovelRuralCulturaVo;
import br.gov.mt.sefaz.itc.model.log.comparador.ComparadorFichaImovelRuralRebanhoVo;
import br.gov.mt.sefaz.itc.model.log.comparador.ComparadorFichaImovelRuralVo;
import br.gov.mt.sefaz.itc.model.log.comparador.ComparadorFichaImovelUrbanoBenfeitoriaVo;
import br.gov.mt.sefaz.itc.model.log.comparador.ComparadorFichaImovelUrbanoVo;
import br.gov.mt.sefaz.itc.model.log.comparador.ComparadorGIAITCDDoacaoBeneficiarioVo;
import br.gov.mt.sefaz.itc.model.log.comparador.ComparadorGIAITCDDoacaoVo;
import br.gov.mt.sefaz.itc.model.log.comparador.ComparadorGIAITCDInventarioArrolamentoBeneficiarioAliquotaVo;
import br.gov.mt.sefaz.itc.model.log.comparador.ComparadorGIAITCDInventarioArrolamentoBeneficiarioVo;
import br.gov.mt.sefaz.itc.model.log.comparador.ComparadorGIAITCDInventarioArrolamentoVo;
import br.gov.mt.sefaz.itc.model.log.comparador.ComparadorGIAITCDSeparacaoDivorcioVo;
import br.gov.mt.sefaz.itc.model.log.comparador.ComparadorGIAITCDVo;
import br.gov.mt.sefaz.itc.model.log.comparador.ComparadorPessoaConjuge2;
import br.gov.mt.sefaz.itc.model.log.comparador.ComparadorPessoaConjugeSobrevivente;
import br.gov.mt.sefaz.itc.model.log.comparador.ComparadorPessoaDeCujus;
import br.gov.mt.sefaz.itc.model.log.comparador.ComparadorPessoaDeclarante;
import br.gov.mt.sefaz.itc.model.log.historicolog.HistoricoLogVo;
import br.gov.mt.sefaz.itc.model.log.log.LogITCDVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoOperacao;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.ContribuinteIntegracaoVo;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.EnderecoIntegracaoVo;

import java.util.Collection;


public class LogUtilComparador
{

   private static final long serialVersionUID = Long.MAX_VALUE;
   LogITCDVo logITCDVo;
   HistoricoLogVo historicoLogITCD;
   final DomnTipoOperacao operacaoInclusao;
   final DomnTipoOperacao operacaoExclusao;

   public LogUtilComparador()
   {
      historicoLogITCD = new HistoricoLogVo();
      logITCDVo = new LogITCDVo();
      logITCDVo.setHistoricoLogVo(historicoLogITCD);
      operacaoInclusao = new DomnTipoOperacao(DomnTipoOperacao.INCLUSAO);
      operacaoExclusao = new DomnTipoOperacao(DomnTipoOperacao.EXCLUSAO);
   }


   public LogITCDVo rotinaProcessamentoLogITCD(GIAITCDVo giaITCDVoModificada, GIAITCDVo giaITCDVoOriginal)
   {


      if (Validador.isObjetoValido(giaITCDVoModificada) & Validador.isObjetoValido(giaITCDVoOriginal))
      {

         //------------------------- GIAITCDVo GERAL-------------------------
         HistoricoLogVo h1 = new ComparadorGIAITCDVo().rotinaComparacao(giaITCDVoModificada, giaITCDVoOriginal);
         if (Validador.isObjetoValido(h1))
         {
            addItemHistoricoLogVo(h1);
         }

         if (Validador.isNumericoValido(giaITCDVoModificada.getResponsavelVo().getNumrContribuinte()) & Validador.isNumericoValido(giaITCDVoOriginal.getResponsavelVo().getNumrContribuinte()))
         {
            if (!giaITCDVoModificada.getResponsavelVo().getNumrContribuinte().equals(giaITCDVoOriginal.getResponsavelVo().getNumrContribuinte()))
            {
               addItemHistoricoLogVo(new ComparadorPessoaDeclarante(operacaoInclusao).rotinaComparacao(giaITCDVoModificada.getResponsavelVo(), giaITCDVoOriginal.getResponsavelVo()));
               addItemHistoricoLogVo(new ComparadorPessoaDeclarante(operacaoExclusao).rotinaComparacao(giaITCDVoModificada.getResponsavelVo(), giaITCDVoOriginal.getResponsavelVo()));
            }
         }


         //--------------- GIAITCDVo Inventario e Arrolamento ----------------
         if (giaITCDVoModificada instanceof GIAITCDInventarioArrolamentoVo)
         {
            GIAITCDInventarioArrolamentoVo giaInventarioModificada = (GIAITCDInventarioArrolamentoVo) giaITCDVoModificada;
            GIAITCDInventarioArrolamentoVo giaInventarioOriginal = (GIAITCDInventarioArrolamentoVo) giaITCDVoOriginal;
            HistoricoLogVo h2 = new ComparadorGIAITCDInventarioArrolamentoVo().rotinaComparacao(giaInventarioModificada, giaInventarioOriginal);
            if (Validador.isObjetoValido(h2))
            {
               addItemHistoricoLogVo(h2);
            }
            if (!giaInventarioModificada.getPessoaDeCujus().getNumrContribuinte().equals(giaInventarioOriginal.getPessoaDeCujus().getNumrContribuinte()))
            {
               addItemHistoricoLogVo(new ComparadorPessoaDeCujus(operacaoInclusao).rotinaComparacao(giaInventarioModificada.getPessoaDeCujus(), giaInventarioOriginal.getPessoaDeCujus()));
               addItemHistoricoLogVo(new ComparadorPessoaDeCujus(operacaoExclusao).rotinaComparacao(giaInventarioModificada.getPessoaDeCujus(), giaInventarioOriginal.getPessoaDeCujus()));
            }

            if (!giaInventarioModificada.getPessoaMeeiro().getNumrContribuinte().equals(giaInventarioOriginal.getPessoaMeeiro().getNumrContribuinte()))
            {
               addItemHistoricoLogVo(new ComparadorPessoaConjugeSobrevivente(operacaoInclusao).rotinaComparacao(giaInventarioModificada.getPessoaMeeiro(), giaInventarioOriginal.getPessoaMeeiro()));
               addItemHistoricoLogVo(new ComparadorPessoaConjugeSobrevivente(operacaoExclusao).rotinaComparacao(giaInventarioModificada.getPessoaMeeiro(), giaInventarioOriginal.getPessoaMeeiro()));
            }

         }
         //------------------------- GIAITCDVo Doacao -----------------------   
         if (giaITCDVoModificada instanceof GIAITCDDoacaoVo)
         {
            GIAITCDDoacaoVo giaDoacaoModificada = (GIAITCDDoacaoVo) giaITCDVoModificada;
            GIAITCDDoacaoVo giaDoacaoOriginal = (GIAITCDDoacaoVo) giaITCDVoOriginal;
            HistoricoLogVo h3 = new ComparadorGIAITCDDoacaoVo().rotinaComparacao(giaDoacaoModificada, giaDoacaoOriginal);
            if (Validador.isObjetoValido(h3))
            {
               addItemHistoricoLogVo(h3);
            }

         }
         //------------------------ GIAITCDVo Separacao ----------------------   
         if (giaITCDVoModificada instanceof GIAITCDSeparacaoDivorcioVo)
         {
            GIAITCDSeparacaoDivorcioVo giaSeparacaoModificada = (GIAITCDSeparacaoDivorcioVo) giaITCDVoModificada;
            GIAITCDSeparacaoDivorcioVo giaSeparacaoOriginal = (GIAITCDSeparacaoDivorcioVo) giaITCDVoOriginal;
            HistoricoLogVo h4 = new ComparadorGIAITCDSeparacaoDivorcioVo().rotinaComparacao(giaSeparacaoModificada, giaSeparacaoOriginal);
            if (Validador.isObjetoValido(h4))
            {
               addItemHistoricoLogVo(h4);
               logConjugeBemTributavelVo(giaSeparacaoModificada.getConjuge1(), giaSeparacaoOriginal.getConjuge1());
               logConjugeBemTributavelVo(giaSeparacaoModificada.getConjuge2(), giaSeparacaoOriginal.getConjuge2());
            }

            if (Validador.isNumericoValido(giaSeparacaoModificada.getConjuge2().getPessoaConjuge().getNumrContribuinte()) & Validador.isNumericoValido(giaSeparacaoOriginal.getConjuge2().getPessoaConjuge().getNumrContribuinte()))
            {
               if (!giaSeparacaoModificada.getConjuge2().getPessoaConjuge().getNumrContribuinte().equals(giaSeparacaoOriginal.getConjuge2().getPessoaConjuge().getNumrContribuinte()))
               {
                  addItemHistoricoLogVo(new ComparadorPessoaConjuge2(operacaoInclusao).rotinaComparacao(giaSeparacaoModificada.getConjuge2().getPessoaConjuge(), giaSeparacaoOriginal.getConjuge2().getPessoaConjuge()));
                  addItemHistoricoLogVo(new ComparadorPessoaConjuge2(operacaoExclusao).rotinaComparacao(giaSeparacaoModificada.getConjuge2().getPessoaConjuge(), giaSeparacaoOriginal.getConjuge2().getPessoaConjuge()));
               }
            }


         }

         //----------------------------------------  BemTributavelVo ----------------------------------------
         BemTributavelVo bemTributavelVoModificado = giaITCDVoModificada.getBemTributavelVo();
         BemTributavelVo bemTributavelVoOriginal = giaITCDVoOriginal.getBemTributavelVo();
         //------------  BemTributavelVo ALTERACAO & INCLUSAO ----------------
         for (BemTributavelVo bemModificado: (Collection<BemTributavelVo>) bemTributavelVoModificado.getCollVO())
         {
            boolean novoBem = true;
            BemTributavelVo bemOriginalTemp = null;
            for (BemTributavelVo bemOriginal: (Collection<BemTributavelVo>) bemTributavelVoOriginal.getCollVO())
            {
               bemOriginalTemp = bemOriginal;
               if (bemOriginal.getCodigo() == bemModificado.getCodigo())
               {
                  HistoricoLogVo temp = new ComparadorBemTributavelVo().rotinaComparacao(bemModificado, bemOriginal);
                  if (Validador.isObjetoValido(temp))
                  {
                     addItemHistoricoLogVo(temp);
                  }
                  logFichaImovel(bemModificado.getFichaImovelVo(), bemOriginal.getFichaImovelVo(), null);
                  logAvaliacaoBemTributavel(bemModificado, bemOriginal);
                  novoBem = false;
                  bemOriginalTemp = bemOriginal;
               }
            }
            if (novoBem)
            {
               HistoricoLogVo temp = new ComparadorBemTributavelVo(operacaoInclusao).rotinaComparacao(bemModificado, bemOriginalTemp);
               if (Validador.isObjetoValido(temp))
               {
                  addItemHistoricoLogVo(temp);
                  logFichaImovel(bemModificado.getFichaImovelVo(), bemOriginalTemp.getFichaImovelVo(), operacaoInclusao);
               }
            }
         }

         //---------------------------------  BemTributavelVo EXCLUSAO --------------------------------------
         for (BemTributavelVo bemOriginal: (Collection<BemTributavelVo>) bemTributavelVoOriginal.getCollVO())
         {
            Boolean bemExcluido = true;
            BemTributavelVo bemModificadoTemp = null;
            for (BemTributavelVo bemModificado: (Collection<BemTributavelVo>) bemTributavelVoModificado.getCollVO())
            {
               bemModificadoTemp = bemModificado;
               if (bemOriginal.getCodigo() == bemModificado.getCodigo())
               {
                  bemExcluido = false;
               }
            }
            if (bemExcluido)
            {
               HistoricoLogVo temp = new ComparadorBemTributavelVo(operacaoExclusao).rotinaComparacao(bemModificadoTemp, bemOriginal);
               if (Validador.isObjetoValido(temp))
               {
                  addItemHistoricoLogVo(temp);
                  logFichaImovel(bemModificadoTemp.getFichaImovelVo(), bemOriginal.getFichaImovelVo(), operacaoExclusao);
               }
            }
         }

         //----------------------------------- BeneficiarioVo --------------------------------
         BeneficiarioVo beneficiarioVoModificado = giaITCDVoModificada.getBeneficiarioVo();
         BeneficiarioVo beneficiarioVoOriginal = giaITCDVoOriginal.getBeneficiarioVo();
         for (BeneficiarioVo beneficiarioModificado: (Collection<BeneficiarioVo>) beneficiarioVoModificado.getCollVO())
         {
            boolean beneficiarioNovo = true;
            BeneficiarioVo beneficiarioVoOriginalTemp = null;
            for (BeneficiarioVo beneficiarioOriginal: (Collection<BeneficiarioVo>) beneficiarioVoOriginal.getCollVO())
            {
               beneficiarioVoOriginalTemp = beneficiarioOriginal;
               if (beneficiarioModificado.getCodigo() == beneficiarioOriginal.getCodigo())
               {
                  HistoricoLogVo temp = new ComparadorBeneficiarioVo().rotinaComparacao(beneficiarioModificado, beneficiarioOriginal);
                  if (Validador.isObjetoValido(temp))
                  {
                     addItemHistoricoLogVo(temp);
                     logBeneficiario(beneficiarioModificado, beneficiarioOriginal, null);
                  }
                  beneficiarioNovo = false;
               }
            }
            if(beneficiarioModificado instanceof GIAITCDInventarioArrolamentoBeneficiarioVo)
            {
               GIAITCDInventarioArrolamentoBeneficiarioVo beneficiarioVoInventarioArrolamento = (GIAITCDInventarioArrolamentoBeneficiarioVo) beneficiarioModificado;
               if(beneficiarioVoInventarioArrolamento != null & beneficiarioVoInventarioArrolamento.getFlagBeneficiarioMeeiro().is(DomnSimNao.NAO))
               {
                  if (beneficiarioNovo)
                  {
                     HistoricoLogVo temp = new ComparadorBeneficiarioVo(operacaoInclusao).rotinaComparacao(beneficiarioModificado, beneficiarioVoOriginalTemp);
                     if (Validador.isObjetoValido(temp))
                     {
                        addItemHistoricoLogVo(temp);
                        logBeneficiario(beneficiarioModificado, beneficiarioVoOriginalTemp, operacaoInclusao);
                     }
                  }
               }
            }else if (beneficiarioNovo)
            {
               HistoricoLogVo temp = new ComparadorBeneficiarioVo(operacaoInclusao).rotinaComparacao(beneficiarioModificado, beneficiarioVoOriginalTemp);
               if (Validador.isObjetoValido(temp))
               {
                  addItemHistoricoLogVo(temp);
                  logBeneficiario(beneficiarioModificado, beneficiarioVoOriginalTemp, operacaoInclusao);
               }
            }
         }

         //----------------------------- Exclusão BeneficiarioVo ------------------------
         for (BeneficiarioVo beneficiarioOriginal: (Collection<BeneficiarioVo>) beneficiarioVoOriginal.getCollVO())
         {
            boolean beneficiarioExcluir = true;
            BeneficiarioVo beneficiarioVoModificadoTemp = null;
            for (BeneficiarioVo beneficiarioModificado: (Collection<BeneficiarioVo>) beneficiarioVoModificado.getCollVO())
            {
               beneficiarioVoModificadoTemp = beneficiarioModificado;
               if (beneficiarioModificado.getCodigo() == beneficiarioOriginal.getCodigo())
               {
                  beneficiarioExcluir = false;
               }
            }
            if (beneficiarioExcluir)
            {
               HistoricoLogVo temp = new ComparadorBeneficiarioVo(operacaoExclusao).rotinaComparacao(beneficiarioVoModificadoTemp, beneficiarioOriginal);
               if (Validador.isObjetoValido(temp))
               {
                  addItemHistoricoLogVo(temp);
                  logBeneficiario(beneficiarioVoModificadoTemp, beneficiarioVoModificadoTemp, operacaoExclusao);
               }
            }
         }

      }
      return logITCDVo;
   }


   // ------------------- Métodos Auxiliares ---------------------------------------

   /**
    * Especialista em validar e adicionar
    * objetos HistoricoLogVo na coleção
    * de registros modificados, incluidos e excluidos.
    * 
    * 
    * @param historico
    * @return boolean true caso seja adicionado o item na 
    * lista de LOG caso contrario retorna false.
    * 
    */
   private boolean addItemHistoricoLogVo(HistoricoLogVo historico)
   {
      if (Validador.isObjetoValido(historico))
      {
         historicoLogITCD.getCollVO().add(historico);
         return true;
      }
      return false;
   }

   private void logBeneficiario(BeneficiarioVo modificado, BeneficiarioVo original, DomnTipoOperacao operacaoInclusao)
   {
      if (original instanceof GIAITCDInventarioArrolamentoBeneficiarioVo)
      {
         logGIAITCDInventarioArrolamentoBeneficiarioVo(modificado, original, operacaoInclusao);
      }
      if (original instanceof GIAITCDDoacaoBeneficiarioVo)
      {
         logGIAITCDDoacaoBeneficiarioVo(modificado, original, operacaoInclusao);
      }

   }

   /**
    * LOG Tabela TABELA_GIA_ITCD_INVENTARIO_BENEFICIARIO = ITCTB42_BENEF_INVENTARIO
    * @param modificado
    * @param original
    * @param tipoOperacao
    */
   private void logGIAITCDInventarioArrolamentoBeneficiarioVo(BeneficiarioVo modificado, BeneficiarioVo original, DomnTipoOperacao tipoOperacao)
   {
      GIAITCDInventarioArrolamentoBeneficiarioVo voModificado = null;
      GIAITCDInventarioArrolamentoBeneficiarioVo voOriginal = null;
      HistoricoLogVo hist = null;
      if (original instanceof GIAITCDInventarioArrolamentoBeneficiarioVo)
      {
         voModificado = (GIAITCDInventarioArrolamentoBeneficiarioVo) modificado;
         voOriginal = (GIAITCDInventarioArrolamentoBeneficiarioVo) original;

         if (Validador.isObjetoValido(tipoOperacao))
         {
            hist = new ComparadorGIAITCDInventarioArrolamentoBeneficiarioVo(tipoOperacao).rotinaComparacao(voModificado, voOriginal);
            logAliquota(voModificado, voOriginal);
         } else
         {
            hist = new ComparadorGIAITCDInventarioArrolamentoBeneficiarioVo().rotinaComparacao(voModificado, voOriginal);
            logAliquota(voModificado, voOriginal);
         }
         addItemHistoricoLogVo(hist);

      }
   }

   /**
    * LOG Tabela TABELA_GIA_ITCD_IVENTARIO_BENEFICIARIO_ALIQUOTA = ITCTB44_BENEF_INVENT_ALIQUOTA
    * 
    * 
    * 
    * @param modificado
    * @param original
    */
   private void logAliquota(GIAITCDInventarioArrolamentoBeneficiarioVo modificado, GIAITCDInventarioArrolamentoBeneficiarioVo original)
   {
      Collection<GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo> modificados = modificado.getGiaITCDInventarioArrolamentoBeneficiarioAliquotaVo().getCollVO();
      Collection<GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo> originais = original.getGiaITCDInventarioArrolamentoBeneficiarioAliquotaVo().getCollVO();
      HistoricoLogVo hist = null;
      for (GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo voModificado: modificados)
      {
         boolean novo = true;
         for (GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo voOriginal: originais)
         {
            if (voModificado.getCodigo() == voOriginal.getCodigo())
            {
               hist = new ComparadorGIAITCDInventarioArrolamentoBeneficiarioAliquotaVo().rotinaComparacao(voModificado, voOriginal);
               addItemHistoricoLogVo(hist);
               novo = false;
            }
         }
         if (novo)
         {
            hist = new ComparadorGIAITCDInventarioArrolamentoBeneficiarioAliquotaVo(operacaoInclusao).rotinaComparacao(voModificado, voModificado);
            addItemHistoricoLogVo(hist);
         }
      }

      for (GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo voOriginal: originais)
      {
         boolean excluir = true;
         for (GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo voModificado: modificados)
         {
            if (voModificado.getCodigo() == voOriginal.getCodigo())
            {
               excluir = false;
            }
         }
         if (excluir)
         {
            hist = new ComparadorGIAITCDInventarioArrolamentoBeneficiarioAliquotaVo(operacaoInclusao).rotinaComparacao(voOriginal, voOriginal);
            addItemHistoricoLogVo(hist);
         }
      }
   }

   /**
    * LOG Tabela TABELA_GIA_ITCD_DOACAO_BENEFICIARIO = ITCTB41_BENEF_DOACAO
    * 
    * 
    * 
    * @param modificado
    * @param original
    * @param tipoOperacao
    */
   private void logGIAITCDDoacaoBeneficiarioVo(BeneficiarioVo modificado, BeneficiarioVo original, DomnTipoOperacao tipoOperacao)
   {
      GIAITCDDoacaoBeneficiarioVo voModificado = null;
      GIAITCDDoacaoBeneficiarioVo voOriginal = null;
      HistoricoLogVo hist = null;
      if (original instanceof GIAITCDDoacaoBeneficiarioVo)
      {
         voModificado = (GIAITCDDoacaoBeneficiarioVo) modificado;
         voOriginal = (GIAITCDDoacaoBeneficiarioVo) original;

         if (Validador.isObjetoValido(tipoOperacao))
         {
            if (tipoOperacao.is(DomnTipoOperacao.INCLUSAO))
            {
               hist = new ComparadorGIAITCDDoacaoBeneficiarioVo(tipoOperacao).rotinaComparacao(voModificado, voModificado);
            } else if (tipoOperacao.is(DomnTipoOperacao.EXCLUSAO))
            {
               hist = new ComparadorGIAITCDDoacaoBeneficiarioVo(tipoOperacao).rotinaComparacao(voOriginal, voOriginal);
            }
         } else
         {
            hist = new ComparadorGIAITCDDoacaoBeneficiarioVo().rotinaComparacao(voModificado, voOriginal);
         }
         addItemHistoricoLogVo(hist);
      }
   }

   /**
    * LOG Tabela TABELA_FICHA_IMOVEL_RURAL = ITCTB21_IMOVEL_RURAL
    * LOG TABELA TABELA_IMOVEL_URBANO = ITCTB19_IMOVEL_URBANO
    * 
    * @param modificado
    * @param original
    * @param tipoOperacao
    */
   private void logFichaImovel(FichaImovelVo modificado, FichaImovelVo original, DomnTipoOperacao tipoOperacao)
   {
      HistoricoLogVo hist = null;
      FichaImovelRuralVo voRuralModificado = null;
      FichaImovelRuralVo voRuralOriginal = null;
      FichaImovelUrbanoVo voModificado = null;
      FichaImovelUrbanoVo voOriginal = null;
      FichaImovelRuralVo voRuralTemp = new FichaImovelRuralVo();

      // Caso seja informado o tipo de operação (INCLUSÃO|ALTERAÇÃO).
      if (Validador.isObjetoValido(tipoOperacao))
      {
         //---------------------------- IMOVEL URBANO ------------------------------------------
         if (modificado instanceof FichaImovelUrbanoVo && tipoOperacao.is(DomnTipoOperacao.INCLUSAO))
         {
            voModificado = (FichaImovelUrbanoVo) modificado;
            voOriginal = (FichaImovelUrbanoVo) modificado;
            hist = new ComparadorFichaImovelUrbanoVo(tipoOperacao).rotinaComparacao(voModificado, voOriginal);
            addItemHistoricoLogVo(hist);
            logEnderecoIntegracaoVo(voModificado.getEnderecoVo(), voModificado.getEnderecoVo(), tipoOperacao);
         }
         if (original instanceof FichaImovelUrbanoVo && tipoOperacao.is(DomnTipoOperacao.EXCLUSAO))
         {
            voModificado = (FichaImovelUrbanoVo) original;
            voOriginal = (FichaImovelUrbanoVo) original;
            hist = new ComparadorFichaImovelUrbanoVo(tipoOperacao).rotinaComparacao(voModificado, voOriginal);
            addItemHistoricoLogVo(hist);
            logEnderecoIntegracaoVo(voOriginal.getEnderecoVo(), voOriginal.getEnderecoVo(), tipoOperacao);
         }
         //--------------------------------- IMOVEL RURAL -----------------------------------------------
         if (modificado instanceof FichaImovelRuralVo && tipoOperacao.is(DomnTipoOperacao.INCLUSAO))
         { //Inclusão de Fichas Imovel Rural.
            voRuralModificado = (FichaImovelRuralVo) modificado;
            voRuralOriginal = (FichaImovelRuralVo) modificado;
            hist = new ComparadorFichaImovelRuralVo(tipoOperacao).rotinaComparacao(voRuralModificado, voRuralOriginal);
            addItemHistoricoLogVo(hist);
            logFichaImovelRuralBenfeitoriaVo(voRuralModificado.getFichaImovelRuralBenfeitoriaVo(), voRuralTemp.getFichaImovelRuralBenfeitoriaVo());
            logFichaImovelRuralConstrucaoVo(voRuralModificado.getFichaImovelRuralConstrucaoVo(), voRuralTemp.getFichaImovelRuralConstrucaoVo());
            logFichaImovelRuralCulturaVo(voRuralModificado.getFichaImovelRuralCulturaVo(), voRuralTemp.getFichaImovelRuralCulturaVo());
            logFichaImovelRuralRebanhoVo(voRuralModificado.getFichaImovelRuralRebanhoVo(), voRuralTemp.getFichaImovelRuralRebanhoVo());
         }
         if (original instanceof FichaImovelRuralVo && tipoOperacao.is(DomnTipoOperacao.EXCLUSAO))
         { //Exclusão de Fichas Imovel Rural.
            voRuralModificado = (FichaImovelRuralVo) original;
            voRuralOriginal = (FichaImovelRuralVo) original;
            hist = new ComparadorFichaImovelRuralVo(tipoOperacao).rotinaComparacao(voRuralModificado, voRuralOriginal);
            addItemHistoricoLogVo(hist);
            logFichaImovelRuralBenfeitoriaVo(voRuralTemp.getFichaImovelRuralBenfeitoriaVo(), voRuralOriginal.getFichaImovelRuralBenfeitoriaVo());
            logFichaImovelRuralConstrucaoVo(voRuralTemp.getFichaImovelRuralConstrucaoVo(), voRuralOriginal.getFichaImovelRuralConstrucaoVo());
            logFichaImovelRuralCulturaVo(voRuralTemp.getFichaImovelRuralCulturaVo(), voRuralOriginal.getFichaImovelRuralCulturaVo());
            logFichaImovelRuralRebanhoVo(voRuralTemp.getFichaImovelRuralRebanhoVo(), voRuralOriginal.getFichaImovelRuralRebanhoVo());
         }
      }

      // Caso NÃO seja informado o tipo de operação ou seja do tipo ALTERAÇÃO.
      if (!Validador.isObjetoValido(tipoOperacao) || tipoOperacao.is(DomnTipoOperacao.ALTERACAO))
      {
         if (modificado instanceof FichaImovelUrbanoVo && original instanceof FichaImovelUrbanoVo)
         {
            voModificado = (FichaImovelUrbanoVo) modificado;
            voOriginal = (FichaImovelUrbanoVo) original;
            hist = new ComparadorFichaImovelUrbanoVo().rotinaComparacao(voModificado, voOriginal);
            addItemHistoricoLogVo(hist);
            logFichaImovelUrbanoBenfeitoriaVo(voModificado.getFichaImovelUrbanoBenfeitoriaVo(), voOriginal.getFichaImovelUrbanoBenfeitoriaVo());
            logEnderecoIntegracaoVo(voModificado.getEnderecoVo(), voOriginal.getEnderecoVo(), null);
         }

         if (modificado instanceof FichaImovelRuralVo && original instanceof FichaImovelRuralVo)
         {
            voRuralModificado = (FichaImovelRuralVo) modificado;
            voRuralOriginal = (FichaImovelRuralVo) original;

            if (Validador.isObjetoValido(voRuralModificado) && Validador.isObjetoValido(voRuralOriginal))
            {
               hist = new ComparadorFichaImovelRuralVo().rotinaComparacao(voRuralModificado, voRuralOriginal);
               addItemHistoricoLogVo(hist);
            }
            logFichaImovelRuralBenfeitoriaVo(voRuralModificado.getFichaImovelRuralBenfeitoriaVo(), voRuralOriginal.getFichaImovelRuralBenfeitoriaVo());
            logFichaImovelRuralConstrucaoVo(voRuralModificado.getFichaImovelRuralConstrucaoVo(), voRuralOriginal.getFichaImovelRuralConstrucaoVo());
            logFichaImovelRuralCulturaVo(voRuralModificado.getFichaImovelRuralCulturaVo(), voRuralOriginal.getFichaImovelRuralCulturaVo());
            logFichaImovelRuralRebanhoVo(voRuralModificado.getFichaImovelRuralRebanhoVo(), voRuralOriginal.getFichaImovelRuralRebanhoVo());
            logEnderecoIntegracaoVo(voRuralModificado.getEnderecoVo(), voRuralOriginal.getEnderecoVo(), null);
         }
      }
   }


   /**
    * LOG TABELA TABELA_IMOVEL_URBANO_BENFEITORIA = ITCTB20_URBANO_BENF
    * 
    * 
    * @param modificado
    * @param original
    */
   private void logFichaImovelUrbanoBenfeitoriaVo(FichaImovelUrbanoBenfeitoriaVo modificado, FichaImovelUrbanoBenfeitoriaVo original)
   {
      Collection<FichaImovelUrbanoBenfeitoriaVo> modificados = (Collection<FichaImovelUrbanoBenfeitoriaVo>) modificado.getCollVO();
      Collection<FichaImovelUrbanoBenfeitoriaVo> originais = (Collection<FichaImovelUrbanoBenfeitoriaVo>) original.getCollVO();
      HistoricoLogVo hist = null;
      if (Validador.isObjetoValido(modificado) || Validador.isObjetoValido(original))
      {
         for (FichaImovelUrbanoBenfeitoriaVo voModificado: modificados)
         {
            boolean novo = true;
            for (FichaImovelUrbanoBenfeitoriaVo voOriginal: originais)
            {
               if (voModificado.getCodigo() == voOriginal.getCodigo())
               {
                  hist = new ComparadorFichaImovelUrbanoBenfeitoriaVo().rotinaComparacao(voModificado, voOriginal);
                  addItemHistoricoLogVo(hist);
                  novo = false;
               }
            }
            if (novo)
            {
               hist = new ComparadorFichaImovelUrbanoBenfeitoriaVo(operacaoInclusao).rotinaComparacao(voModificado, voModificado);
               addItemHistoricoLogVo(hist);
            }
         }
         for (FichaImovelUrbanoBenfeitoriaVo voOriginal: originais)
         {
            boolean excluir = true;
            for (FichaImovelUrbanoBenfeitoriaVo voModificado: modificados)
            {
               if (voOriginal.getCodigo() == voModificado.getCodigo())
               {
                  excluir = false;
               }
            }
            if (excluir)
            {
               hist = new ComparadorFichaImovelUrbanoBenfeitoriaVo(operacaoExclusao).rotinaComparacao(voOriginal, voOriginal);
               addItemHistoricoLogVo(hist);
            }
         }
      }

   }

   /**
    * LOG TABELA TABELA_CONJUGE_BEM_TRIBUTAVEL = ITCTB26_CONJUGE
    * @param modificado
    * @param original
    */
   private void logConjugeBemTributavelVo(ConjugeBemTributavelVo modificado, ConjugeBemTributavelVo original)
   {
      Collection<ConjugeBemTributavelVo> modificados = modificado.getCollVO();
      Collection<ConjugeBemTributavelVo> originais = original.getCollVO();
      HistoricoLogVo hist = null;

      for (ConjugeBemTributavelVo voModificado: modificados)
      {
         boolean novo = true;
         for (ConjugeBemTributavelVo voOriginal: originais)
         {
            if (voModificado.getBemTributavelVo().getCodigo() == voOriginal.getBemTributavelVo().getCodigo())
            {
               hist = new ComparadorConjugeBemTributavelVo().rotinaComparacao(voModificado, voOriginal);
               addItemHistoricoLogVo(hist);
               novo = false;
            }
         }
         if (novo)
         {
            hist = new ComparadorConjugeBemTributavelVo(operacaoInclusao).rotinaComparacao(voModificado, voModificado);
            addItemHistoricoLogVo(hist);
         }
      }

      for (ConjugeBemTributavelVo voOriginal: originais)
      {
         boolean excluir = true;
         for (ConjugeBemTributavelVo voModificado: modificados)
         {
            if (voOriginal.getBemTributavelVo().getCodigo() == voModificado.getBemTributavelVo().getCodigo())
            {
               excluir = false;
            }
         }
         if (excluir)
         {
            hist = new ComparadorConjugeBemTributavelVo(operacaoExclusao).rotinaComparacao(voOriginal, voOriginal);
            addItemHistoricoLogVo(hist);
         }
      }
   }


   /**
    * LOG TABELA TABELA_FICHA_IMOVEL_RURAL_REBANHO = ITCTB24_RURAL_REB
    * 
    * @param modificado
    * @param original
    */
   private void logFichaImovelRuralRebanhoVo(FichaImovelRuralRebanhoVo modificado, FichaImovelRuralRebanhoVo original)
   {
      Collection<FichaImovelRuralRebanhoVo> modificados = modificado.getCollVO();
      Collection<FichaImovelRuralRebanhoVo> originais = original.getCollVO();
      HistoricoLogVo hist = null;

      if (Validador.isObjetoValido(modificado) || Validador.isObjetoValido(original))
      {
         for (FichaImovelRuralRebanhoVo voModificado: modificados)
         {
            boolean novo = true;
            for (FichaImovelRuralRebanhoVo voOriginal: originais)
            {
               if (voModificado.getCodigo() == voOriginal.getCodigo())
               {
                  hist = new ComparadorFichaImovelRuralRebanhoVo().rotinaComparacao(voModificado, voOriginal);
                  addItemHistoricoLogVo(hist);
                  novo = false;
               }
            }
            if (novo)
            {
               hist = new ComparadorFichaImovelRuralRebanhoVo(operacaoInclusao).rotinaComparacao(voModificado, voModificado);
               addItemHistoricoLogVo(hist);
            }
         }

         for (FichaImovelRuralRebanhoVo voOriginal: originais)
         {
            boolean excluir = true;
            for (FichaImovelRuralRebanhoVo voModificado: modificados)
            {
               if (voOriginal.getCodigo() == voModificado.getCodigo())
               {
                  excluir = false;
               }
            }
            if (excluir)
            {
               hist = new ComparadorFichaImovelRuralRebanhoVo(operacaoExclusao).rotinaComparacao(voOriginal, voOriginal);
               addItemHistoricoLogVo(hist);
            }
         }
      }
   }

   /**
    * LOG TABELA TABELA_FICHA_IMOVEL_RURAL_CULTURA = ITCTB23_RURAL_CULT
    * @param modificado
    * @param original
    */
   private void logFichaImovelRuralCulturaVo(FichaImovelRuralCulturaVo modificado, FichaImovelRuralCulturaVo original)
   {
      Collection<FichaImovelRuralCulturaVo> modificados = modificado.getCollVO();
      Collection<FichaImovelRuralCulturaVo> originais = original.getCollVO();
      HistoricoLogVo hist = null;

      if (Validador.isObjetoValido(modificado) || Validador.isObjetoValido(original))
      {
         for (FichaImovelRuralCulturaVo voModificado: modificados)
         {
            boolean novo = true;
            for (FichaImovelRuralCulturaVo voOriginal: originais)
            {
               if (voOriginal.getDescricaoComplementarCultura().equalsIgnoreCase(voModificado.getDescricaoComplementarCultura()))
               {
                  hist = new ComparadorFichaImovelRuralCulturaVo().rotinaComparacao(voModificado, voOriginal);
                  addItemHistoricoLogVo(hist);
                  novo = false;
               }
            }
            if (novo)
            {
               hist = new ComparadorFichaImovelRuralCulturaVo(operacaoInclusao).rotinaComparacao(voModificado, voModificado);
               addItemHistoricoLogVo(hist);
            }
         }

         for (FichaImovelRuralCulturaVo voOriginal: originais)
         {
            boolean excluir = true;
            for (FichaImovelRuralCulturaVo voModificado: modificados)
            {
               if (voOriginal.getDescricaoComplementarCultura().equalsIgnoreCase(voModificado.getDescricaoComplementarCultura()))
               {
                  excluir = false;
               }
            }
            if (excluir)
            {
               hist = new ComparadorFichaImovelRuralCulturaVo(operacaoExclusao).rotinaComparacao(voOriginal, voOriginal);
               addItemHistoricoLogVo(hist);
            }
         }
      }
   }

   /**
    * LOG Tabela TABELA_FICHA_IMOVEL_RURAL_CONSTRUCAO = ITCTB25_RURAL_CONS
    * 
    * @param modificado
    * @param original
    */
   private void logFichaImovelRuralConstrucaoVo(FichaImovelRuralConstrucaoVo modificado, FichaImovelRuralConstrucaoVo original)
   {
      Collection<FichaImovelRuralConstrucaoVo> modificados = modificado.getCollVO();
      Collection<FichaImovelRuralConstrucaoVo> originais = original.getCollVO();
      HistoricoLogVo hist = null;

      if (Validador.isObjetoValido(modificado) || Validador.isObjetoValido(original))
      {
         for (FichaImovelRuralConstrucaoVo voModificado: modificados)
         {
            boolean novo = true;
            for (FichaImovelRuralConstrucaoVo voOriginal: originais)
            {
               if (voModificado.getCodigo() == voOriginal.getCodigo())
               {
                  hist = new ComparadorFichaImovelRuralConstrucaoVo().rotinaComparacao(voModificado, voOriginal);
                  addItemHistoricoLogVo(hist);
                  novo = false;
               }
            }
            if (novo)
            {
               hist = new ComparadorFichaImovelRuralConstrucaoVo(operacaoInclusao).rotinaComparacao(voModificado, voModificado);
               addItemHistoricoLogVo(hist);
            }
         }

         for (FichaImovelRuralConstrucaoVo voOriginal: originais)
         {
            boolean excluir = true;
            for (FichaImovelRuralConstrucaoVo voModificado: modificados)
            {
               if (voOriginal.getCodigo() == voModificado.getCodigo())
               {
                  excluir = false;
               }
            }
            if (excluir)
            {
               hist = new ComparadorFichaImovelRuralConstrucaoVo(operacaoExclusao).rotinaComparacao(voOriginal, voOriginal);
               addItemHistoricoLogVo(hist);
            }
         }
      }
   }

   /**
    * LOG Tabela TABELA_FICHA_IMOVEL_RURAL_BENFEITORIA = ITCTB22_RURAL_BENF
    * 
    * @param modificado
    * @param original
    */
   private void logFichaImovelRuralBenfeitoriaVo(FichaImovelRuralBenfeitoriaVo modificado, FichaImovelRuralBenfeitoriaVo original)
   {
      Collection<FichaImovelRuralBenfeitoriaVo> modificados = modificado.getCollVO();
      Collection<FichaImovelRuralBenfeitoriaVo> originais = original.getCollVO();
      HistoricoLogVo hist = null;

      if (Validador.isObjetoValido(modificado) || Validador.isObjetoValido(original))
      {
         for (FichaImovelRuralBenfeitoriaVo voModificado: modificados)
         {
            boolean novo = true;
            for (FichaImovelRuralBenfeitoriaVo voOriginal: originais)
            {
               if (voModificado.getCodigo() == voOriginal.getCodigo())
               {
                  hist = new ComparadorFichaImovelRuralBenfeitoriaVo().rotinaComparacao(voModificado, voOriginal);
                  addItemHistoricoLogVo(hist);
                  novo = false;
               }
            }
            if (novo)
            {
               hist = new ComparadorFichaImovelRuralBenfeitoriaVo(operacaoInclusao).rotinaComparacao(voModificado, voModificado);
               addItemHistoricoLogVo(hist);
            }
         }

         for (FichaImovelRuralBenfeitoriaVo voOriginal: originais)
         {
            boolean excluir = true;
            for (FichaImovelRuralBenfeitoriaVo voModificado: modificados)
            {
               if (voOriginal.getCodigo() == voModificado.getCodigo())
               {
                  excluir = false;
               }
            }
            if (excluir)
            {
               hist = new ComparadorFichaImovelRuralBenfeitoriaVo(operacaoExclusao).rotinaComparacao(voOriginal, voOriginal);
               addItemHistoricoLogVo(hist);
            }
         }
      }
   }


   /**
    * LOG EnderecoIntegracaoVo
    * 
    * 
    * 
    * @param modificado
    * @param original
    */
   private void logEnderecoIntegracaoVo(EnderecoIntegracaoVo modificado, EnderecoIntegracaoVo original, DomnTipoOperacao tipoOperacao)
   {

      HistoricoLogVo hist = null;

      if (Validador.isObjetoValido(tipoOperacao))
      {
         hist = new ComparadorEnderecoIntegracaoVo(tipoOperacao).rotinaComparacao(modificado, original);
      }
      if (!Validador.isObjetoValido(tipoOperacao))
      {
         hist = new ComparadorEnderecoIntegracaoVo().rotinaComparacao(modificado, original);
      }
      addItemHistoricoLogVo(hist);
   }

   /**
    * 
    * 
    * 
    * @param modificado
    * @param original
    */
   private void logContribuinteIntegracaoVo(ContribuinteIntegracaoVo modificado, ContribuinteIntegracaoVo original)
   {


   }

   /**
    * <b>Objetivo:</b>
    * <br>
    * Este método tem por objetivo tirar LOG da tabela TABELA_AVALIACAO_BEMTRIBUTAVEL = ITCTB28_AVALIACAO_BEM_TRBT
    * quando ocorrer, operações de inclusão alteração e exclusão.
    * 
    * 
    * 
    * 
    * @param bemModificado BemTributavelVo atual que contem a avalição que está sendo alterada, incluida ou excluida.
    * @param bemOriginal BemTributavelVo recuperado do BD.
    * @implemented by Dherkyan Ribeiro da Silva.
    */
   private void logAvaliacaoBemTributavel(BemTributavelVo bemModificado, BemTributavelVo bemOriginal)
   {

      HistoricoLogVo hist = null;

      // Verifica se os códigos são válidos ou seja  codigo != null e codigo != 0
      if (Validador.isNumericoValido(bemModificado.getAvaliacaoBemTributavelVo().getCodigo()) & Validador.isNumericoValido(bemOriginal.getAvaliacaoBemTributavelVo().getCodigo()))
      {
         // Se os codigos são iguais significa que pode estar ocorrendo uma alteração em outras informações da avaliação.
         if (bemModificado.getAvaliacaoBemTributavelVo().getCodigo() == bemOriginal.getAvaliacaoBemTributavelVo().getCodigo())
         {
            hist = new ComparadorAvaliacaoBemTributavelVo().rotinaComparacao(bemModificado.getAvaliacaoBemTributavelVo(), bemOriginal.getAvaliacaoBemTributavelVo());
         }
      }

      // Se o codigo do bemModificado é válido e do bemOriginal é invalido significa que esta ocorrendo uma inclusão de avaliação.
      if (Validador.isNumericoValido(bemModificado.getAvaliacaoBemTributavelVo().getCodigo()) & !Validador.isNumericoValido(bemOriginal.getAvaliacaoBemTributavelVo().getCodigo()))
      {
         hist = new ComparadorAvaliacaoBemTributavelVo(operacaoInclusao).rotinaComparacao(bemModificado.getAvaliacaoBemTributavelVo(), bemOriginal.getAvaliacaoBemTributavelVo());
      }

      // Se o codigo do bemModificado é inválido e do bemOriginal é valido significa que esta ocorrendo uma exclusão de avaliação.
      if (!Validador.isNumericoValido(bemModificado.getAvaliacaoBemTributavelVo().getCodigo()) & Validador.isNumericoValido(bemOriginal.getAvaliacaoBemTributavelVo().getCodigo()))
      {
         hist = new ComparadorAvaliacaoBemTributavelVo(operacaoExclusao).rotinaComparacao(bemModificado.getAvaliacaoBemTributavelVo(), bemOriginal.getAvaliacaoBemTributavelVo());
      }

      addItemHistoricoLogVo(hist);
   }
} // fim da classe(LogUtilComparador).
