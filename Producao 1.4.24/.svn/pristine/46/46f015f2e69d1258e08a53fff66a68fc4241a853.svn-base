package br.gov.mt.sefaz.itc.model.log.comparador;

import br.com.abaco.util.Validador;

import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.log.historicolog.HistoricoLogVo;
import br.gov.mt.sefaz.itc.util.facade.tabelas.CamposGIAITCD;

import sefaz.mt.util.SefazDataHora;


public class ComparadorGIAITCDVo extends Comparador<GIAITCDVo> implements CamposGIAITCD

{

   public ComparadorGIAITCDVo()
   {
      super(TABELA_GIA_ITCD);
   }

   public HistoricoLogVo rotinaComparacao(GIAITCDVo giaITCDVoModificada, GIAITCDVo giaITCDVoOriginal)
   {
      comparar(giaITCDVoModificada, giaITCDVoOriginal);
      if (Validador.isCollectionValida(items.getCollVO()))
      {
         historicoLogVo.setItemLog(items);
         return historicoLogVo;
      } else
      {
         return null;
      }

   }


   protected void comparar(GIAITCDVo giaITCDVoModificada, GIAITCDVo giaITCDVoOriginal)
   {
      // DATA CRIACAO.
      valorDiferente = !giaITCDVoModificada.getDataCriacao().equals(giaITCDVoOriginal.getDataCriacao());
      registroLogVo(CAMPO_DATA_CRIACAO, new SefazDataHora(giaITCDVoModificada.getDataCriacao()).formata(MASCARA_DATA_TIMESTEMP), new SefazDataHora(giaITCDVoOriginal.getDataCriacao()).formata(MASCARA_DATA_TIMESTEMP));


      // TIPO GIA
      valorDiferente = giaITCDVoModificada.getTipoGIA().getDomnValr() != giaITCDVoOriginal.getTipoGIA().getDomnValr();
      registroLogVo(CAMPO_TIPO_ITCD, giaITCDVoModificada.getTipoGIA().getTextoCorrente(), giaITCDVoOriginal.getTipoGIA().getTextoCorrente());

      //  SENHA
      valorDiferente = !giaITCDVoModificada.getSenha().equals(giaITCDVoOriginal.getSenha());
      //registroLogVo(CAMPO_INFO_SENHA, giaITCDVoModificada.getSenha(), giaITCDVoOriginal.getSenha());


      // CODIGO DE AUTENTICIDADE
      valorDiferente = !giaITCDVoModificada.getCodigoAutenticidade().equals(giaITCDVoOriginal.getCodigoAutenticidade());
      registroLogVo(CAMPO_CODIGO_AUTENTICIDADE, giaITCDVoModificada.getCodigoAutenticidade(), giaITCDVoOriginal.getCodigoAutenticidade());


      // DATA ATUALIZACAO
      valorDiferente = giaITCDVoModificada.getDataAtualizacaoBD() != giaITCDVoOriginal.getDataAtualizacaoBD();
      registroLogVo(CAMPO_DATA_ATUALIZACAO_BD, new SefazDataHora(giaITCDVoModificada.getDataAtualizacaoBD()).formata(MASCARA_DATA_TIMESTEMP), new SefazDataHora(giaITCDVoOriginal.getDataAtualizacaoBD()).formata(MASCARA_DATA_TIMESTEMP));

      // JUSTIFICATIVA DE ALTERACAO
      valorDiferente = !giaITCDVoModificada.getJustificativaAlteracao().equals(giaITCDVoOriginal.getJustificativaAlteracao());
      //registroLogVo(CAMPO_JUSTIFICATIVA_ALTERACAO, giaITCDVoModificada.getJustificativaAlteracao(), giaITCDVo.getJustificativaAlteracao());


      // NATUREZA DA OPERACAO
      valorDiferente = giaITCDVoModificada.getNaturezaOperacaoVo().getCodigo() != giaITCDVoOriginal.getNaturezaOperacaoVo().getCodigo();
      registroLogVo(CAMPO_ITCTB03_CODIGO_NATUREZA_OPERACAO, giaITCDVoModificada.getNaturezaOperacaoVo().getCodigoFormatado(), giaITCDVoOriginal.getNaturezaOperacaoVo().getCodigoFormatado());


      // NUMERO DECLARACAO FATOR GERADOR
      valorDiferente = giaITCDVoModificada.getNumrDeclaracaoFatoGerador() != giaITCDVoOriginal.getNumrDeclaracaoFatoGerador();
      registroLogVo(CAMPO_ITCTB03_CODIGO_NATUREZA_OPERACAO, giaITCDVoModificada.getNumrDeclaracaoFatoGeradorFormatado(), giaITCDVoOriginal.getNumrDeclaracaoFatoGeradorFormatado());


      // NUMERO DECLARACAO ISENCAO
      valorDiferente = giaITCDVoModificada.getNumrDeclaracaoIsencao() != giaITCDVoOriginal.getNumrDeclaracaoIsencao();
      registroLogVo(CAMPO_NUMR_DECL_ISENCAO, giaITCDVoModificada.getNumrDeclaracaoIsencaoFormatado(), giaITCDVoOriginal.getNumrDeclaracaoIsencaoFormatado());


      // PARAMETRO LEGISLACAO
      valorDiferente = giaITCDVoModificada.getParametroLegislacaoVo().getCodigo() != giaITCDVoOriginal.getParametroLegislacaoVo().getCodigo();
      registroLogVo(CAMPO_ITCTB09_CODIGO_LEGISLACAO, giaITCDVoModificada.getParametroLegislacaoVo().getCodigoFormatado(), giaITCDVoOriginal.getParametroLegislacaoVo().getCodigoFormatado());


      // VALOR ITCD
      valorDiferente = giaITCDVoModificada.getValorITCD() != giaITCDVoOriginal.getValorITCD();
      registroLogVo(CAMPO_VALOR_ITCD, giaITCDVoModificada.getValorITCDFormatado() , giaITCDVoOriginal.getValorITCDFormatado() );


      // VALOR TSE
      valorDiferente = giaITCDVoModificada.getValorTSE() != giaITCDVoOriginal.getValorTSE();
      registroLogVo(CAMPO_VALOR_TSE, giaITCDVoModificada.getValorTSEFormatado(), giaITCDVoOriginal.getValorTSEFormatado());


      // VALOR RECOLHIMENTO
      valorDiferente = giaITCDVoModificada.getValorRecolhimento() != giaITCDVoOriginal.getValorRecolhimento();
      registroLogVo(CAMPO_VALOR_TOTAL_RECOLHER, giaITCDVoModificada.getValorRecolhimentoFormatado(), giaITCDVoOriginal.getValorRecolhimentoFormatado());


      // VALOR VALOR TOTAL DOS BENS
      valorDiferente = giaITCDVoModificada.getValorTotalBensDeclarados() != giaITCDVoOriginal.getValorTotalBensDeclarados();
      registroLogVo(CAMPO_VALOR_TOTAL_BENS, giaITCDVoModificada.getValorTotalBensDeclaradosFormatado() , giaITCDVoOriginal.getValorTotalBensDeclaradosFormatado());


      // VALOR UPF
      valorDiferente = giaITCDVoModificada.getValorUPF() != giaITCDVoOriginal.getValorUPF();
      registroLogVo(CAMPO_VALOR_UPF, giaITCDVoModificada.getValorUPFFormatado(), giaITCDVoOriginal.getValorUPFFormatado());


      // VALOR CALCULO DEMONSTRATIVO
      valorDiferente = giaITCDVoModificada.getValorCalculoDemonstrativo() != giaITCDVoOriginal.getValorCalculoDemonstrativo();
      registroLogVo(CAMPO_VALOR_CALCULO_DEMONSTRATIVO, giaITCDVoModificada.getValorCalculoDemonstrativoFormatado(), giaITCDVoOriginal.getValorCalculoDemonstrativoFormatado());


      // MUNICIPIO PROTOCOLAR
      valorDiferente = !giaITCDVoModificada.getMunicipioProtocolar().getCodgMunicipio().equals(giaITCDVoOriginal.getMunicipioProtocolar().getCodgMunicipio());
      registroLogVo(CAMPO_MUNICIPIO_PROTOCOLAR, giaITCDVoModificada.getMunicipioProtocolar().getCodgMunicipio().toString(), giaITCDVoOriginal.getMunicipioProtocolar().getCodgMunicipio().toString());


      // POSSUI CPF
      valorDiferente = giaITCDVoModificada.getPossuiCPF().getDomnValr() != giaITCDVoOriginal.getPossuiCPF().getDomnValr();
      registroLogVo(CAMPO_POSSUI_CPF, giaITCDVoModificada.getPossuiCPF().getTextoCorrente(), giaITCDVoOriginal.getPossuiCPF().getTextoCorrente());


      // MATRICULA SERVIDOR
      valorDiferente = !giaITCDVoModificada.getServidorSefazResponsavelAlteracaoVo().getNumrMatricula().equals(giaITCDVoOriginal.getServidorSefazResponsavelAlteracaoVo().getNumrMatricula());
      registroLogVo(CAMPO_SERVIDOR_RESPONSAVEL_ALTERACAO, giaITCDVoModificada.getServidorSefazResponsavelAlteracaoVo().getNumrMatriculaFormatado(), giaITCDVoOriginal.getServidorSefazResponsavelAlteracaoVo().getNumrMatriculaFormatado());


      // VALOR ITCD RETIFICADO
      valorDiferente = giaITCDVoModificada.getValorITCDRetificado() != giaITCDVoOriginal.getValorITCDRetificado();
      registroLogVo(CAMPO_VALOR_ITCD_RETIFICADO, "" + giaITCDVoModificada.getValorITCDRetificado(), "" + giaITCDVoOriginal.getValorITCDRetificado());


      if (dadoObrigatorio)
      {
         valorDiferente = true;
         historicoLogVo.setInfoChavePrimaria("" +giaITCDVoOriginal.getCodigo() );

      }

   }
}
