package br.gov.mt.sefaz.itc.util.facade.tabelas;

import java.util.HashMap;


public class NomeAmigavel
{
   public NomeAmigavel()
   {
   }

   public String coluna(String nomeRealcoluna)
   {
      String nomeAmigavel = null;
      HashMap<String, String> map = new HashMap<String, String>();


      // CamposAvaliacaoBemtributavel
      map.put(CamposAvaliacaoBemtributavel.CAMPO_AVALIACAO_BEMTRIBUTAVEL_CODIGO, CamposAvaliacaoBemtributavel.NOME_AMIGAVEL_AVALIACAO_BEMTRIBUTAVEL_CODIGO);
      map.put(CamposAvaliacaoBemtributavel.CAMPO_ITCTB18_CODIGO_BEMTRIBUTAVEL, CamposAvaliacaoBemtributavel.NOME_AMIGAVEL_ITCTB18_CODIGO_BEMTRIBUTAVEL);
      map.put(CamposAvaliacaoBemtributavel.CAMPO_AVALIACAO_JUDICIAL, CamposAvaliacaoBemtributavel.NOME_AMIGAVEL_AVALIACAO_JUDICIAL);
      map.put(CamposAvaliacaoBemtributavel.CAMPO_VALOR_AVALIACAO, CamposAvaliacaoBemtributavel.NOME_AMIGAVEL_VALOR_AVALIACAO);
      map.put(CamposAvaliacaoBemtributavel.CAMPO_DATA_AVALIACAO, CamposAvaliacaoBemtributavel.NOME_AMIGAVEL_DATA_AVALIACAO);
      map.put(CamposAvaliacaoBemtributavel.CAMPO_DATA_CADASTRO, CamposAvaliacaoBemtributavel.NOME_AMIGAVEL_DATA_CADASTRO);
      map.put(CamposAvaliacaoBemtributavel.CAMPO_OBSERVACAO, CamposAvaliacaoBemtributavel.NOME_AMIGAVEL_OBSERVACAO);
      map.put(CamposAvaliacaoBemtributavel.CAMPO_INFO_ISENTO, CamposAvaliacaoBemtributavel.NOME_AMIGAVEL_INFO_ISENTO);
      map.put(CamposAvaliacaoBemtributavel.CAMPO_DATA_ATUALIZACAO_BD, CamposAvaliacaoBemtributavel.NOME_AMIGAVEL_DATA_ATUALIZACAO_BD);
      map.put(CamposAvaliacaoBemtributavel.CAMPO_CODIGO_UNIDADE_AVALIACAO, CamposAvaliacaoBemtributavel.NOME_AMIGAVEL_CODIGO_UNIDADE_AVALIACAO);
      map.put(CamposAvaliacaoBemtributavel.CAMPO_STATUS_AVALIACAO, CamposAvaliacaoBemtributavel.NOME_AMIGAVEL_STATUS_AVALIACAO);


      // CamposAvaliacaoBemtributavelServidor
      map.put(CamposAvaliacaoBemtributavelServidor.CAMPO_ITCTB28_CODIGO_AVALIACAO_BEM_TRIBUTAVEL, CamposAvaliacaoBemtributavelServidor.NOME_AMIGAVEL_ITCTB28_CODIGO_AVALIACAO_BEM_TRIBUTAVEL);
      map.put(CamposAvaliacaoBemtributavelServidor.CAMPO_SGPTB06_NUMERO_MATRICULA_SERVIDOR, CamposAvaliacaoBemtributavelServidor.NOME_AMIGAVEL_SGPTB06_NUMERO_MATRICULA_SERVIDOR);


      // CamposBem
      map.put(CamposBem.CAMPO_CODIGO_BEM, CamposBem.NOME_AMIGAVEL_CODIGO_BEM);
      map.put(CamposBem.CAMPO_DESCRICAO_BEM, CamposBem.NOME_AMIGAVEL_DESCRICAO_BEM);
      map.put(CamposBem.CAMPO_STATUS_BEM, CamposBem.NOME_AMIGAVEL_STATUS_BEM);
      map.put(CamposBem.CAMPO_TIPO_BEM, CamposBem.NOME_AMIGAVEL_TIPO_BEM);
      map.put(CamposBem.CAMPO_POSSUI_CONSTRUCAO, CamposBem.NOME_AMIGAVEL_POSSUI_CONSTRUCAO);
      map.put(CamposBem.CAMPO_DATA_ATUALIZACAO_BD, CamposBem.NOME_AMIGAVEL_DATA_ATUALIZACAO_BD);
      map.put(CamposBem.CAMPO_TIPO_VERIFICACAO, CamposBem.NOME_AMIGAVEL_TIPO_VERIFICACAO);
      map.put(CamposBem.CAMPO_TIPO_PROTOCOLO_GIA, CamposBem.NOME_AMIGAVEL_TIPO_PROTOCOLO_GIA);


      //CamposBemTributavel
      map.put(CamposBemTributavel.CAMPO_CODIGO_ITCD_BEM_TRIBUTAVEL, CamposBemTributavel.NOME_AMIGAVEL_CODIGO_ITCD_BEM_TRIBUTAVEL);
      map.put(CamposBemTributavel.CAMPO_ITCTB14_CODIGO_ITCD, CamposBemTributavel.NOME_AMIGAVEL_ITCTB14_CODIGO_ITCD);
      map.put(CamposBemTributavel.CAMPO_VALOR_MERCADO, CamposBemTributavel.NOME_AMIGAVEL_VALOR_MERCADO);
      map.put(CamposBemTributavel.CAMPO_DESCRICAO_BEM_TRIBUTAVEL, CamposBemTributavel.NOME_AMIGAVEL_DESCRICAO_BEM_TRIBUTAVEL);
      map.put(CamposBemTributavel.CAMPO_ISENCAO_PREVISTA, CamposBemTributavel.NOME_AMIGAVEL_ISENCAO_PREVISTA);
      map.put(CamposBemTributavel.CAMPO_FLAG_BEM_PARTICULAR, CamposBemTributavel.NOME_AMIGAVEL_FLAG_BEM_PARTICULAR);
      map.put(CamposBemTributavel.CAMPO_TIPO_USUARIO_INCLUSAO, CamposBemTributavel.NOME_AMIGAVEL_TIPO_USUARIO_INCLUSAO);
      map.put(CamposBemTributavel.CAMPO_ITCTB06_CODIGO_BEM, CamposBemTributavel.NOME_AMIGAVEL_ITCTB06_CODIGO_BEM);


      // CamposBeneficiario
      map.put(CamposBeneficiario.CAMPO_CODIGO_BENEFICIARIO, CamposBeneficiario.NOME_AMIGAVEL_CODIGO_BENEFICIARIO);
      map.put(CamposBeneficiario.CAMPO_ITCTB14_CODIGO_GIA_ITCD, CamposBeneficiario.NOME_AMIGAVEL_ITCTB14_CODIGO_GIA_ITCD);
      map.put(CamposBeneficiario.CAMPO_ACCTB01_NUMERO_PESSOA_BENEFICIARIA, CamposBeneficiario.NOME_AMIGAVEL_ACCTB01_NUMERO_PESSOA_BENEFICIARIA);
      map.put(CamposBeneficiario.CAMPO_VALOR_RECEBIDO, CamposBeneficiario.NOME_AMIGAVEL_VALOR_RECEBIDO);


      // CamposGIAITCDInventarioArrolamentoBeneficiario
      map.put(CamposGIAITCDInventarioArrolamentoBeneficiario.CAMPO_ITCTB05_CODIGO_BENEFICIARIO , CamposGIAITCDInventarioArrolamentoBeneficiario.NOME_AMIGAVEL_ITCTB05_CODIGO_BENEFICIARIO );
      map.put(CamposGIAITCDInventarioArrolamentoBeneficiario.CAMPO_INFO_QTD_UPF , CamposGIAITCDInventarioArrolamentoBeneficiario.NOME_AMIGAVEL_INFO_QTD_UPF );
      map.put(CamposGIAITCDInventarioArrolamentoBeneficiario.CAMPO_VALR_RECOLHER , CamposGIAITCDInventarioArrolamentoBeneficiario.NOME_AMIGAVEL_VALR_RECOLHER );
      map.put(CamposGIAITCDInventarioArrolamentoBeneficiario.CAMPO_FLAG_BENEF_MEEIRO , CamposGIAITCDInventarioArrolamentoBeneficiario.NOME_AMIGAVEL_FLAG_BENEF_MEEIRO );
      map.put(CamposGIAITCDInventarioArrolamentoBeneficiario.CAMPO_VALOR_ITCD_BENEFICIARIO , CamposGIAITCDInventarioArrolamentoBeneficiario.NOME_AMIGAVEL_VALOR_ITCD_BENEFICIARIO );
      map.put(CamposGIAITCDInventarioArrolamentoBeneficiario.CAMPO_VALOR_MULTA_RECOLHER , CamposGIAITCDInventarioArrolamentoBeneficiario.NOME_AMIGAVEL_VALOR_MULTA_RECOLHER );


      // CamposBenfeitoria
      map.put(CamposBenfeitoria.CAMPO_CODIGO_BENFEITORIA, CamposBenfeitoria.NOME_AMIGAVEL_CODIGO_BENFEITORIA);
      map.put(CamposBenfeitoria.CAMPO_DESCRICAO_BENFEITORIA, CamposBenfeitoria.NOME_AMIGAVEL_DESCRICAO_BENFEITORIA);
      map.put(CamposBenfeitoria.CAMPO_STATUS_BENFEITORIA, CamposBenfeitoria.NOME_AMIGAVEL_STATUS_BENFEITORIA);
      map.put(CamposBenfeitoria.CAMPO_DATA_ATUALIZACAO_BD, CamposBenfeitoria.NOME_AMIGAVEL_DATA_ATUALIZACAO_BD);


      // CamposFichaImovelRural
      map.put( CamposFichaImovelRural.CAMPO_CODIGO_IMOVEL_RURAL  , CamposFichaImovelRural.NOME_AMIGAVEL_CODIGO_IMOVEL_RURAL);
      map.put( CamposFichaImovelRural.CAMPO_ACCTB06_CODIGO_ENDERECO  , CamposFichaImovelRural.NOME_AMIGAVEL_ACCTB06_CODIGO_ENDERECO);
      map.put( CamposFichaImovelRural.CAMPO_ITCTB18_CODIGO_ITCD_BEM_TRIBUTARIO  , CamposFichaImovelRural.NOME_AMIGAVEL_ITCTB18_CODIGO_ITCD_BEM_TRIBUTARIO);
      map.put( CamposFichaImovelRural.CAMPO_DESCRICAO_DENOMINACAO  , CamposFichaImovelRural.NOME_AMIGAVEL_DESCRICAO_DENOMINACAO);
      map.put( CamposFichaImovelRural.CAMPO_QUANTIDADE_DISTANCIA  , CamposFichaImovelRural.NOME_AMIGAVEL_QUANTIDADE_DISTANCIA);
      map.put( CamposFichaImovelRural.CAMPO_AREA_TOTAL  , CamposFichaImovelRural.NOME_AMIGAVEL_AREA_TOTAL);
      map.put( CamposFichaImovelRural.CAMPO_NUMERICO_INDEA  , CamposFichaImovelRural.NOME_AMIGAVEL_NUMERICO_INDEA);
      map.put( CamposFichaImovelRural.CAMPO_CODIGO_RECEITA_FEDERAL  , CamposFichaImovelRural.NOME_AMIGAVEL_CODIGO_RECEITA_FEDERAL);
      map.put( CamposFichaImovelRural.CAMPO_AREA_PASTAGEM  , CamposFichaImovelRural.NOME_AMIGAVEL_AREA_PASTAGEM);
      map.put( CamposFichaImovelRural.CAMPO_SITUACAO_PASTAGEM  , CamposFichaImovelRural.NOME_AMIGAVEL_SITUACAO_PASTAGEM);
      map.put( CamposFichaImovelRural.CAMPO_VALOR_PASTAGEM  , CamposFichaImovelRural.NOME_AMIGAVEL_VALOR_PASTAGEM);
      map.put( CamposFichaImovelRural.CAMPO_SITUACAO_ACESSAO_NATURAL  , CamposFichaImovelRural.NOME_AMIGAVEL_SITUACAO_ACESSAO_NATURAL);
      map.put( CamposFichaImovelRural.CAMPO_VALOR_ACESSAO_NATURAL  , CamposFichaImovelRural.NOME_AMIGAVEL_VALOR_ACESSAO_NATURAL);
      map.put( CamposFichaImovelRural.CAMPO_VALOR_MERCADO_IMOVEL  , CamposFichaImovelRural.NOME_AMIGAVEL_VALOR_MERCADO_IMOVEL);
      map.put( CamposFichaImovelRural.CAMPO_VALOR_MAQUINA_EQUIPAMENTO  , CamposFichaImovelRural.NOME_AMIGAVEL_VALOR_MAQUINA_EQUIPAMENTO);
      map.put( CamposFichaImovelRural.CAMPO_VALOR_OUTRO , CamposFichaImovelRural.NOME_AMIGAVEL_VALOR_OUTRO);
      map.put( CamposFichaImovelRural.CAMPO_VALOR_ITR  , CamposFichaImovelRural.NOME_AMIGAVEL_VALOR_ITR);
      map.put( CamposFichaImovelRural.CAMPO_QTDE_DISTANCIA_ASFALTO , CamposFichaImovelRural.NOME_AMIGAVEL_QTDE_DISTANCIA_ASFALTO);
      
      
      //  CamposFichaImovelRuralBem
      map.put( CamposFichaImovelRuralBem.CAMPO_CODIGO_FICHA_IMOVEL_RURAL_BEM  ,  CamposFichaImovelRuralBem.NOME_AMIGAVEL_CODIGO_FICHA_IMOVEL_RURAL_BEM);
      map.put( CamposFichaImovelRuralBem.CAMPO_ITCTB06_CODIGO_TIPO_BEM  ,  CamposFichaImovelRuralBem.NOME_AMIGAVEL_ITCTB06_CODIGO_TIPO_BEM);
      map.put( CamposFichaImovelRuralBem.CAMPO_ITCTB21_CODIGO_IMOVEL_RURAL  ,  CamposFichaImovelRuralBem.NOME_AMIGAVEL_ITCTB21_CODIGO_IMOVEL_RURAL);
      map.put( CamposFichaImovelRuralBem.CAMPO_DESCRICAO_BEM  ,  CamposFichaImovelRuralBem.NOME_AMIGAVEL_DESCRICAO_BEM);
      map.put( CamposFichaImovelRuralBem.CAMPO_SITUACAO_ESTADO_CONSERVACAO  ,  CamposFichaImovelRuralBem.NOME_AMIGAVEL_SITUACAO_ESTADO_CONSERVACAO);
      map.put( CamposFichaImovelRuralBem.CAMPO_VALOR_MERCADO  ,  CamposFichaImovelRuralBem.NOME_AMIGAVEL_VALOR_MERCADO);


      // CamposFichaImovelRuralBenfeitoria
      map.put( CamposFichaImovelRuralBenfeitoria.CAMPO_CODIGO_IMOVEL_RURAL_BENFEITORIA , CamposFichaImovelRuralBenfeitoria.NOME_AMIGAVEL_CODIGO_IMOVEL_RURAL_BENFEITORIA);
      map.put( CamposFichaImovelRuralBenfeitoria.CAMPO_ITCTB21_CODIGO_IMOVEL_RURAL , CamposFichaImovelRuralBenfeitoria.NOME_AMIGAVEL_ITCTB21_CODIGO_IMOVEL_RURAL);
      map.put( CamposFichaImovelRuralBenfeitoria.CAMPO_ITCTB07_CODIGO_BENFEITORIA , CamposFichaImovelRuralBenfeitoria.NOME_AMIGAVEL_ITCTB07_CODIGO_BENFEITORIA);
      map.put( CamposFichaImovelRuralBenfeitoria.CAMPO_DESCRICAO_COMPLEMENTAR_BENFEITORIA , CamposFichaImovelRuralBenfeitoria.NOME_AMIGAVEL_DESCRICAO_COMPLEMENTAR_BENFEITORIA);
      

      // CamposFichaImovelRuralConstrucao
      map.put( CamposFichaImovelRuralConstrucao.CAMPO_CODIGO_FICHA_IMOVEL_RURAL_CONSTRUCAO  ,  CamposFichaImovelRuralConstrucao.NOME_AMIGAVEL_CODIGO_FICHA_IMOVEL_RURAL_CONSTRUCAO);
      map.put( CamposFichaImovelRuralConstrucao.CAMPO_ITCTB13_CODIGO_TIPO_CONSTRUCAO  ,  CamposFichaImovelRuralConstrucao.NOME_AMIGAVEL_ITCTB13_CODIGO_TIPO_CONSTRUCAO);
      map.put( CamposFichaImovelRuralConstrucao.CAMPO_ITCTB21_CODIGO_IMOVEL_RURAL  ,  CamposFichaImovelRuralConstrucao.NOME_AMIGAVEL_ITCTB21_CODIGO_IMOVEL_RURAL);
      map.put( CamposFichaImovelRuralConstrucao.CAMPO_DESCRICAO_CONSTRUCAO  ,  CamposFichaImovelRuralConstrucao.NOME_AMIGAVEL_DESCRICAO_CONSTRUCAO );
      map.put( CamposFichaImovelRuralConstrucao.CAMPO_SITUACAO_ESTADO_CONSERVACAO  ,  CamposFichaImovelRuralConstrucao.NOME_AMIGAVEL_SITUACAO_ESTADO_CONSERVACAO);
      map.put( CamposFichaImovelRuralConstrucao.CAMPO_VALOR_MERCADO  ,  CamposFichaImovelRuralConstrucao.NOME_AMIGAVEL_VALOR_MERCADO);
      
      
      // CamposFichaImovelRuralCultura
      map.put( CamposFichaImovelRuralCultura.CAMPO_CODIGO_IMOVEL_RURAL_CULTURA , CamposFichaImovelRuralCultura.NOME_AMIGAVEL_CODIGO_IMOVEL_RURAL_CULTURA);
      map.put( CamposFichaImovelRuralCultura.CAMPO_ITCTB08_CODIGO_CULTURA , CamposFichaImovelRuralCultura.NOME_AMIGAVEL_ITCTB08_CODIGO_CULTURA);
      map.put( CamposFichaImovelRuralCultura.CAMPO_ITCTB21_CODIGO_IMOVEL_RURAL , CamposFichaImovelRuralCultura.NOME_AMIGAVEL_ITCTB21_CODIGO_IMOVEL_RURAL);
      map.put( CamposFichaImovelRuralCultura.CAMPO_DESCRICAO_COMPLEMENTAR_CULTURA , CamposFichaImovelRuralCultura.NOME_AMIGAVEL_DESCRICAO_COMPLEMENTAR_CULTURA);
      map.put( CamposFichaImovelRuralCultura.CAMPO_AREA_CULTIVADA , CamposFichaImovelRuralCultura.NOME_AMIGAVEL_AREA_CULTIVADA);
      map.put( CamposFichaImovelRuralCultura.CAMPO_VALOR_HECTARE , CamposFichaImovelRuralCultura.NOME_AMIGAVEL_VALOR_HECTARE);
      map.put( CamposFichaImovelRuralCultura.CAMPO_VALOR_MERCADO , CamposFichaImovelRuralCultura.NOME_AMIGAVEL_VALOR_MERCADO);
      
      
      // CamposFichaImovelRuralRebanho
      map.put(CamposFichaImovelRuralRebanho.CAMPO_CODIGO_IMOVEL_RURAL_REBANHO   ,  CamposFichaImovelRuralRebanho.NOME_AMIGAVEL_CODIGO_IMOVEL_RURAL_REBANHO);
      map.put(CamposFichaImovelRuralRebanho.CAMPO_ITCTB21_CODIGO_IMOVEL_RURAL   ,  CamposFichaImovelRuralRebanho.NOME_AMIGAVEL_ITCTB21_CODIGO_IMOVEL_RURAL);
      map.put(CamposFichaImovelRuralRebanho.CAMPO_ITCTB10_CODG_REBANHO   ,  CamposFichaImovelRuralRebanho.NOME_AMIGAVEL_ITCTB10_CODG_REBANHO);
      map.put(CamposFichaImovelRuralRebanho.CAMPO_QUANTIDADE_REBANHO   ,  CamposFichaImovelRuralRebanho.NOME_AMIGAVEL_QUANTIDADE_REBANHO);
      map.put(CamposFichaImovelRuralRebanho.CAMPO_DESCRICAO_REBANHO   ,  CamposFichaImovelRuralRebanho.NOME_AMIGAVEL_DESCRICAO_REBANHO);
      map.put(CamposFichaImovelRuralRebanho.CAMPO_VALOR_MERCADO   ,  CamposFichaImovelRuralRebanho.NOME_AMIGAVEL_VALOR_MERCADO);


      // CamposFichaImovelUrbano
      map.put( CamposFichaImovelUrbano.CAMPO_CODIGO_IMOVEL_URBANO  , CamposFichaImovelUrbano.NOME_AMIGAVEL_CODIGO_IMOVEL_URBANO);
      map.put( CamposFichaImovelUrbano.CAMPO_ACCTB06_CODIGO_ENDERECO  , CamposFichaImovelUrbano.NOME_AMIGAVEL_ACCTB06_CODIGO_ENDERECO);
      map.put( CamposFichaImovelUrbano.CAMPO_ITCTB13_CODIGO_CONSTRUCAO  , CamposFichaImovelUrbano.NOME_AMIGAVEL_ITCTB13_CODIGO_CONSTRUCAO);
      map.put( CamposFichaImovelUrbano.CAMPO_ITCTB18_CODIGO_ITCD_BEM_TRBT  , CamposFichaImovelUrbano.NOME_AMIGAVEL_ITCTB18_CODIGO_ITCD_BEM_TRBT);
      map.put( CamposFichaImovelUrbano.CAMPO_TIPO_CONSERVACAO  , CamposFichaImovelUrbano.NOME_AMIGAVEL_TIPO_CONSERVACAO);
      map.put( CamposFichaImovelUrbano.CAMPO_QUANTIDADE_AREA_TOTAL  , CamposFichaImovelUrbano.NOME_AMIGAVEL_QUANTIDADE_AREA_TOTAL);
      map.put( CamposFichaImovelUrbano.CAMPO_QUANTIDADE_AREA_CONSTRUIDA  , CamposFichaImovelUrbano.NOME_AMIGAVEL_QUANTIDADE_AREA_CONSTRUIDA);
      map.put( CamposFichaImovelUrbano.CAMPO_TIPO_ACESSO  , CamposFichaImovelUrbano.NOME_AMIGAVEL_TIPO_ACESSO);
      map.put( CamposFichaImovelUrbano.CAMPO_VALOR_MERCADO_TOTAL  , CamposFichaImovelUrbano.NOME_AMIGAVEL_VALOR_MERCADO_TOTAL);
      map.put( CamposFichaImovelUrbano.CAMPO_VALOR_VENAL_IPTU  , CamposFichaImovelUrbano.NOME_AMIGAVEL_VALOR_VENAL_IPTU);
      
      
      // CamposFichaImovelUrbanoBenfeitoria
      map.put( CamposFichaImovelUrbanoBenfeitoria.CAMPO_CODIGO_IMOVEL_URBANO_BENFEITORIA , CamposFichaImovelUrbanoBenfeitoria.NOME_AMIGAVEL_CODIGO_IMOVEL_URBANO_BENFEITORIA);
      map.put( CamposFichaImovelUrbanoBenfeitoria.CAMPO_CODIGO_IMOVEL_URBANO_BENFEITORIA , CamposFichaImovelUrbanoBenfeitoria.NOME_AMIGAVEL_ITCTB07_CODIGO_BENFEITORIA);
      map.put( CamposFichaImovelUrbanoBenfeitoria.CAMPO_ITCTB07_CODIGO_BENFEITORIA , CamposFichaImovelUrbanoBenfeitoria.NOME_AMIGAVEL_ITCTB07_CODIGO_BENFEITORIA);
      map.put( CamposFichaImovelUrbanoBenfeitoria.CAMPO_DESCRICAO_COMPLEMENTAR_BENFEITORIA , CamposFichaImovelUrbanoBenfeitoria.NOME_AMIGAVEL_DESCRICAO_COMPLEMENTAR_BENFEITORIA);
      
      
      // CamposFichaOutro
      map.put( CamposFichaOutro.CAMPO_ITCTB06_CODIGO_TIPO_BEM , CamposFichaOutro.NOME_AMIGAVEL_ITCTB06_CODIGO_TIPO_BEM);
      map.put( CamposFichaOutro.CAMPO_ITCTB18_CODIGO_ITCD_BEM_TRBT , CamposFichaOutro.CAMPO_ITCTB18_CODIGO_ITCD_BEM_TRBT);
      
      // CamposCampoAjuda
      map.put(CamposCampoAjuda.CAMPO_CODIGO_CAMPO_AJUDA, CamposCampoAjuda.NOME_AMIGAVEL_CODIGO_NOME_AMIGAVEL_AJUDA);
      map.put(CamposCampoAjuda.CAMPO_DESCRICAO_ROTULO, CamposCampoAjuda.NOME_AMIGAVEL_DESCRICAO_ROTULO);


      // CamposGIAITCD
      map.put(CamposGIAITCD.CAMPO_CODIGO_GIA_ITCD, CamposGIAITCD.NOME_AMIGAVEL_CODIGO_GIA_ITCD);
      map.put(CamposGIAITCD.CAMPO_ACCTB01_NUMERO_PESSOA_RESPONSAVEL, CamposGIAITCD.NOME_AMIGAVEL_ACCTB01_NUMERO_PESSOA_RESPONSAVEL);
      map.put(CamposGIAITCD.CAMPO_ACCTB01_NUMERO_PESSOA_PROCURADOR, CamposGIAITCD.NOME_AMIGAVEL_ACCTB01_NUMERO_PESSOA_PROCURADOR);
      map.put(CamposGIAITCD.CAMPO_ITCTB03_CODIGO_NATUREZA_OPERACAO, CamposGIAITCD.NOME_AMIGAVEL_ITCTB03_CODIGO_NATUREZA_OPERACAO);
      map.put(CamposGIAITCD.CAMPO_ITCTB09_CODIGO_LEGISLACAO, CamposGIAITCD.NOME_AMIGAVEL_ITCTB09_CODIGO_LEGISLACAO);
      map.put(CamposGIAITCD.CAMPO_DATA_CRIACAO, CamposGIAITCD.NOME_AMIGAVEL_DATA_CRIACAO);
      map.put(CamposGIAITCD.CAMPO_TIPO_ITCD, CamposGIAITCD.NOME_AMIGAVEL_TIPO_ITCD);
      map.put(CamposGIAITCD.CAMPO_INFO_SENHA, CamposGIAITCD.NOME_AMIGAVEL_INFO_SENHA);
      map.put(CamposGIAITCD.CAMPO_CODIGO_AUTENTICIDADE, CamposGIAITCD.NOME_AMIGAVEL_CODIGO_AUTENTICIDADE);
      map.put(CamposGIAITCD.CAMPO_VALOR_TOTAL_BENS, CamposGIAITCD.NOME_AMIGAVEL_VALOR_TOTAL_BENS);
      map.put(CamposGIAITCD.CAMPO_VALOR_UPF, CamposGIAITCD.NOME_AMIGAVEL_VALOR_UPF);
      map.put(CamposGIAITCD.CAMPO_VALOR_CALCULO_DEMONSTRATIVO, CamposGIAITCD.NOME_AMIGAVEL_VALOR_CALCULO_DEMONSTRATIVO);
      map.put(CamposGIAITCD.CAMPO_VALOR_ITCD, CamposGIAITCD.NOME_AMIGAVEL_VALOR_ITCD);
      map.put(CamposGIAITCD.CAMPO_VALOR_ITCD_RETIFICADO, CamposGIAITCD.NOME_AMIGAVEL_VALOR_ITCD_RETIFICADO);
      map.put(CamposGIAITCD.CAMPO_VALOR_TOTAL_RECOLHER, CamposGIAITCD.NOME_AMIGAVEL_VALOR_TOTAL_RECOLHER);
      map.put(CamposGIAITCD.CAMPO_VALOR_TSE, CamposGIAITCD.NOME_AMIGAVEL_VALOR_TSE);
      map.put(CamposGIAITCD.CAMPO_MUNICIPIO_PROTOCOLAR, CamposGIAITCD.NOME_AMIGAVEL_MUNICIPIO_PROTOCOLAR);
      map.put(CamposGIAITCD.CAMPO_STATUS_ATUAL, CamposGIAITCD.NOME_AMIGAVEL_STATUS_ATUAL);
      map.put(CamposGIAITCD.CAMPO_POSSUI_CPF, CamposGIAITCD.NOME_AMIGAVEL_POSSUI_CPF);
      map.put(CamposGIAITCD.CAMPO_NUMR_DECL_FATO_GERADOR, CamposGIAITCD.NOME_AMIGAVEL_NUMR_DECL_FATO_GERADOR);
      map.put(CamposGIAITCD.CAMPO_NUMR_DECL_ISENCAO, CamposGIAITCD.NOME_AMIGAVEL_NUMR_DECL_ISENCAO);
      map.put(CamposGIAITCD.CAMPO_DATA_ATUALIZACAO_BD, CamposGIAITCD.NOME_AMIGAVEL_DATA_ATUALIZACAO_BD);
      map.put(CamposGIAITCD.CAMPO_SERVIDOR_RESPONSAVEL_ALTERACAO, CamposGIAITCD.NOME_AMIGAVEL_SERVIDOR_RESPONSAVEL_ALTERACAO);
      map.put(CamposGIAITCD.CAMPO_JUSTIFICATIVA_ALTERACAO, CamposGIAITCD.NOME_AMIGAVEL_JUSTIFICATIVA_ALTERACAO);


      // CamposGIAITCDDoacaoBeneficiario
      map.put(CamposGIAITCDDoacaoBeneficiario.CAMPO_ITCTB05_CODIGO_BENEFICIARIO, CamposGIAITCDDoacaoBeneficiario.NOME_AMIGAVEL_ITCTB05_CODIGO_BENEFICIARIO);
      map.put(CamposGIAITCDDoacaoBeneficiario.CAMPO_PERCENTUAL_ALIQUOTA, CamposGIAITCDDoacaoBeneficiario.NOME_AMIGAVEL_PERCENTUAL_ALIQUOTA);
      map.put(CamposGIAITCDDoacaoBeneficiario.CAMPO_PERCENTUAL_BEM_RECEBIDO, CamposGIAITCDDoacaoBeneficiario.NOME_AMIGAVEL_PERCENTUAL_BEM_RECEBIDO);
      map.put(CamposGIAITCDDoacaoBeneficiario.CAMPO_VALOR_RECOLHER, CamposGIAITCDDoacaoBeneficiario.NOME_AMIGAVEL_VALOR_RECOLHER);


      // CamposGIAITCDInventarioArrolamento
      map.put(CamposGIAITCDInventarioArrolamento.CAMPO_ITCTB14_CODIGO_GIA_ITCD, CamposGIAITCDInventarioArrolamento.NOME_AMIGAVEL_ITCTB14_CODIGO_GIA_ITCD);
      map.put(CamposGIAITCDInventarioArrolamento.CAMPO_ACCTB01_NUMERO_PESSOA_MEEIRO, CamposGIAITCDInventarioArrolamento.NOME_AMIGAVEL_ACCTB01_NUMERO_PESSOA_MEEIRO);
      map.put(CamposGIAITCDInventarioArrolamento.CAMPO_ACCTB01_NUMERO_PESSOA_DE_CUJUS, CamposGIAITCDInventarioArrolamento.NOME_AMIGAVEL_ACCTB01_NUMERO_PESSOA_DE_CUJUS);
      map.put(CamposGIAITCDInventarioArrolamento.CAMPO_SIGLA_UF_ABERTURA, CamposGIAITCDInventarioArrolamento.NOME_AMIGAVEL_SIGLA_UF_ABERTURA);
      map.put(CamposGIAITCDInventarioArrolamento.CAMPO_SITUACAO_ESTADO_CIVIL, CamposGIAITCDInventarioArrolamento.NOME_AMIGAVEL_SITUACAO_ESTADO_CIVIL);
      map.put(CamposGIAITCDInventarioArrolamento.CAMPO_REGIMENTO_CASAMENTO, CamposGIAITCDInventarioArrolamento.NOME_AMIGAVEL_REGIMENTO_CASAMENTO);
      map.put(CamposGIAITCDInventarioArrolamento.CAMPO_NUMERO_PROCESSO, CamposGIAITCDInventarioArrolamento.NOME_AMIGAVEL_NUMERO_PROCESSO);
      map.put(CamposGIAITCDInventarioArrolamento.CAMPO_NUMERO_HERDEIROS, CamposGIAITCDInventarioArrolamento.NOME_AMIGAVEL_NUMERO_HERDEIROS);
      map.put(CamposGIAITCDInventarioArrolamento.CAMPO_DATA_INVENTARIO, CamposGIAITCDInventarioArrolamento.NOME_AMIGAVEL_DATA_INVENTARIO);
      map.put(CamposGIAITCDInventarioArrolamento.CAMPO_DATA_FALECIMENTO, CamposGIAITCDInventarioArrolamento.NOME_AMIGAVEL_DATA_FALECIMENTO);
      map.put(CamposGIAITCDInventarioArrolamento.CAMPO_DESCRICAO_JUIZO_COMARCA, CamposGIAITCDInventarioArrolamento.NOME_AMIGAVEL_DESCRICAO_JUIZO_COMARCA);
      map.put(CamposGIAITCDInventarioArrolamento.CAMPO_FRAC_IDEAL, CamposGIAITCDInventarioArrolamento.NOME_AMIGAVEL_FRAC_IDEAL);
      map.put(CamposGIAITCDInventarioArrolamento.CAMPO_PERC_MULTA, CamposGIAITCDInventarioArrolamento.NOME_AMIGAVEL_PERC_MULTA);
      map.put(CamposGIAITCDInventarioArrolamento.CAMPO_VALR_MULTA, CamposGIAITCDInventarioArrolamento.NOME_AMIGAVEL_VALR_MULTA);
      map.put(CamposGIAITCDInventarioArrolamento.CAMPO_DATA_PROCESSO, CamposGIAITCDInventarioArrolamento.NOME_AMIGAVEL_DATA_PROCESSO);
      map.put(CamposGIAITCDInventarioArrolamento.CAMPO_TIPO_RENUNCIA, CamposGIAITCDInventarioArrolamento.NOME_AMIGAVEL_TIPO_RENUNCIA);
      map.put(CamposGIAITCDInventarioArrolamento.CAMPO_FLAG_RENUNCIA, CamposGIAITCDInventarioArrolamento.NOME_AMIGAVEL_FLAG_RENUNCIA);
      map.put(CamposGIAITCDInventarioArrolamento.CAMPO_FLAG_HERDEIROS_DESCENDENTES, CamposGIAITCDInventarioArrolamento.NOME_AMIGAVEL_FLAG_HERDEIROS_DESCENDENTES);
      map.put(CamposGIAITCDInventarioArrolamento.CAMPO_FLAG_HERDEIROS_ASCENDENTES, CamposGIAITCDInventarioArrolamento.NOME_AMIGAVEL_FLAG_HERDEIROS_ASCENDENTES);
      map.put(CamposGIAITCDInventarioArrolamento.CAMPO_FLAG_MEEIRO_BENEFICIARIO, CamposGIAITCDInventarioArrolamento.NOME_AMIGAVEL_FLAG_MEEIRO_BENEFICIARIO);


      // CamposGIAITCDSeparacaoDivorcio
      map.put(CamposGIAITCDSeparacaoDivorcio.CAMPO_ITCBTB14_CODIGO_ITCD, CamposGIAITCDSeparacaoDivorcio.NOME_AMIGAVEL_ITCBTB14_CODIGO_ITCD);
      map.put(CamposGIAITCDSeparacaoDivorcio.CAMPO_REGIME_CASAMENTO, CamposGIAITCDSeparacaoDivorcio.NOME_AMIGAVEL_REGIME_CASAMENTO);
      map.put(CamposGIAITCDSeparacaoDivorcio.CAMPO_NUMERO_PROCESSO, CamposGIAITCDSeparacaoDivorcio.NOME_AMIGAVEL_NUMERO_PROCESSO);
      map.put(CamposGIAITCDSeparacaoDivorcio.CAMPO_DATA_SEPARACAO, CamposGIAITCDSeparacaoDivorcio.NOME_AMIGAVEL_DATA_SEPARACAO);
      map.put(CamposGIAITCDSeparacaoDivorcio.CAMPO_PESSOA_CONJUGE1, CamposGIAITCDSeparacaoDivorcio.NOME_AMIGAVEL_PESSOA_CONJUGE1);
      map.put(CamposGIAITCDSeparacaoDivorcio.CAMPO_PESSOA_CONJUGE2, CamposGIAITCDSeparacaoDivorcio.NOME_AMIGAVEL_PESSOA_CONJUGE2);
      map.put(CamposGIAITCDSeparacaoDivorcio.CAMPO_VALOR_ALIQUOTA, CamposGIAITCDSeparacaoDivorcio.NOME_AMIGAVEL_VALOR_ALIQUOTA);
      map.put(CamposGIAITCDSeparacaoDivorcio.CAMPO_VALOR_TOTAL_CONJUGE1, CamposGIAITCDSeparacaoDivorcio.NOME_AMIGAVEL_VALOR_TOTAL_CONJUGE1);
      map.put(CamposGIAITCDSeparacaoDivorcio.CAMPO_VALOR_TOTAL_CONJUGE2, CamposGIAITCDSeparacaoDivorcio.NOME_AMIGAVEL_VALOR_TOTAL_CONJUGE2);
      map.put(CamposGIAITCDSeparacaoDivorcio.CAMPO_VALOR_INCIDENCIA, CamposGIAITCDSeparacaoDivorcio.NOME_AMIGAVEL_VALOR_INCIDENCIA);
      map.put(CamposGIAITCDSeparacaoDivorcio.CAMPO_DATA_SEPARACAO, CamposGIAITCDSeparacaoDivorcio.NOME_AMIGAVEL_DATA_SEPARACAO);

      // CamposGIAITCDDoacao      
      map.put(CamposGIAITCDDoacao.CAMPO_ITCBTB14_CODIGO_ITCD , CamposGIAITCDDoacao.NOME_AMIGAVEL_ITCBTB14_CODIGO_ITCD );
      map.put(CamposGIAITCDDoacao.CAMPO_FRAC_IDEAL , CamposGIAITCDDoacao.NOME_AMIGAVEL_FRAC_IDEAL );
      map.put(CamposGIAITCDDoacao.CAMPO_BASE_CALCULO_REDUZIDA , CamposGIAITCDDoacao.NOME_AMIGAVEL_BASE_CALCULO_REDUZIDA );
      map.put(CamposGIAITCDDoacao.CAMPO_TIPO_DOACAO , CamposGIAITCDDoacao.NOME_AMIGAVEL_TIPO_DOACAO );
      
      
      // CamposEndereco
      map.put(CamposEndereco.CAMPO_CODG_ENDERECO , CamposEndereco.NOME_AMIGAVEL_CODG_ENDERECO);
      map.put(CamposEndereco.CAMPO_NOME_LOGRADOURO , CamposEndereco.NOME_AMIGAVEL_NOME_LOGRADOURO);
      map.put(CamposEndereco.CAMPO_NUMR_LOGRADOURO , CamposEndereco.NOME_AMIGAVEL_NUMR_LOGRADOURO);
      map.put(CamposEndereco.CAMPO_ENDR_COMPLEMENTO , CamposEndereco.NOME_AMIGAVEL_ENDR_COMPLEMENTO);
      map.put(CamposEndereco.CAMPO_ENDR_BAIRRO , CamposEndereco.NOME_AMIGAVEL_ENDR_BAIRRO);
      map.put(CamposEndereco.CAMPO_ENDR_PONTO_REFERENCIA , CamposEndereco.NOME_AMIGAVEL_ENDR_PONTO_REFERENCIA);
      map.put(CamposEndereco.CAMPO_NUMR_DDD_TELEFONE , CamposEndereco.NOME_AMIGAVEL_NUMR_DDD_TELEFONE);
      map.put(CamposEndereco.CAMPO_NUMR_TELEFONE , CamposEndereco.NOME_AMIGAVEL_NUMR_TELEFONE);
      map.put(CamposEndereco.CAMPO_NOME_LOCALIDADE , CamposEndereco.NOME_AMIGAVEL_NOME_LOCALIDADE);
      map.put(CamposEndereco.CAMPO_DESC_TIPO_LOGRADOURO , CamposEndereco.NOME_AMIGAVEL_DESC_TIPO_LOGRADOURO);
      map.put(CamposEndereco.CAMPO_NUMR_CEP , CamposEndereco.NOME_AMIGAVEL_NUMR_CEP);
      
      
      // CamposGIAITCDInventarioArrolamentoBeneficiarioAliquota
      map.put(CamposGIAITCDInventarioArrolamentoBeneficiarioAliquota.CAMPO_CODIGO_BENEFICIARIO , CamposGIAITCDInventarioArrolamentoBeneficiarioAliquota.NOME_AMIGAVEL_CODIGO_BENEFICIARIO);
      map.put(CamposGIAITCDInventarioArrolamentoBeneficiarioAliquota.CAMPO_CODIGO_ALIQUOTA , CamposGIAITCDInventarioArrolamentoBeneficiarioAliquota.NOME_AMIGAVEL_CODIGO_ALIQUOTA);
      map.put(CamposGIAITCDInventarioArrolamentoBeneficiarioAliquota.CAMPO_BASE_CALCULO , CamposGIAITCDInventarioArrolamentoBeneficiarioAliquota.NOME_AMIGAVEL_BASE_CALCULO);
      map.put(CamposGIAITCDInventarioArrolamentoBeneficiarioAliquota.CAMPO_PERCENTUAL_ALIQUOTA , CamposGIAITCDInventarioArrolamentoBeneficiarioAliquota.NOME_AMIGAVEL_PERCENTUAL_ALIQUOTA);
      map.put(CamposGIAITCDInventarioArrolamentoBeneficiarioAliquota.CAMPO_VALOR_RECOLHER , CamposGIAITCDInventarioArrolamentoBeneficiarioAliquota.NOME_AMIGAVEL_VALOR_RECOLHER);
       
       
      // CamposContribuinteIntegracaoVo
      map.put( CamposContribuinteIntegracaoVo.CAMPO_NUMR_PESSOA , CamposContribuinteIntegracaoVo.NOME_AMIGAVEL_NUMR_PESSOA);
      map.put( CamposContribuinteIntegracaoVo.CAMPO_NOME_PESSOA , CamposContribuinteIntegracaoVo.NOME_AMIGAVEL_NOME_PESSOA);
       
      
      //CamposConjuge
      map.put( CamposConjuge.CAMPO_ACCTB01_NUMR_PESS_CONJUGE , CamposConjuge.NOME_AMIGAVEL_ACCTB01_NUMR_PESS_CONJUGE );
      map.put( CamposConjuge.CAMPO_ITCTB18_CODG_ITCD_BEM_TRBT , CamposConjuge.NOME_AMIGAVEL_ITCTB18_CODG_ITCD_BEM_TRBT );
      map.put( CamposConjuge.CAMPO_TIPO_CONJUGE , CamposConjuge.NOME_AMIGAVEL_TIPO_CONJUGE );
      map.put( CamposConjuge.CAMPO_ITCTB17_ITCTB14_CODG_ITCD , CamposConjuge.NOME_AMIGAVEL_ITCTB17_ITCTB14_CODG_ITCD );      
      map.put( CamposConjuge.CAMPO_VALOR_CONJUGE , CamposConjuge.NOME_AMIGAVEL_VALOR_CONJUGE );
       
      nomeAmigavel = map.get(nomeRealcoluna);

      return nomeAmigavel;
   }


   public String tabela(String nomeRealColuna)
   {
      String nomeAmigavel = null;
      HashMap<String, String> map = new HashMap<String, String>();

      //map.put(CamposGIAITCD.campo_ , CamposGIAITCD.NOME_AMIGAVEL_ );
      // add campos CamposGIAITCD
      map.put(TabelasITC.TABELA_BEM, TabelasITC.NOME_AMIGAVEL_BEM);
      map.put(TabelasITC.TABELA_BENEFICIARIO, TabelasITC.NOME_AMIGAVEL_BENEFICIARIO);
      map.put(TabelasITC.TABELA_BENFEITORIA, TabelasITC.NOME_AMIGAVEL_BENFEITORIA);
      map.put(TabelasITC.TABELA_CAMPO_AJUDA, TabelasITC.NOME_AMIGAVEL_CAMPO_AJUDA);
      map.put(TabelasITC.TABELA_CONFIGURACAO_GERENCIAL_PARAMETROS, TabelasITC.NOME_AMIGAVEL_CONFIGURACAO_GERENCIAL_PARAMETROS);
      map.put(TabelasITC.TABELA_CONJUGE_BEM_TRIBUTAVEL, TabelasITC.NOME_AMIGAVEL_CONJUGE_BEM_TRIBUTAVEL);
      map.put(TabelasITC.TABELA_CONSTRUCAO, TabelasITC.NOME_AMIGAVEL_CONSTRUCAO);
      map.put(TabelasITC.TABELA_CULTURA, TabelasITC.NOME_AMIGAVEL_CULTURA);
      map.put(TabelasITC.TABELA_FICHA_IMOVEL_RURAL, TabelasITC.NOME_AMIGAVEL_FICHA_IMOVEL_RURAL);
      map.put(TabelasITC.TABELA_FICHA_IMOVEL_RURAL_CONSTRUCAO, TabelasITC.NOME_AMIGAVEL_FICHA_IMOVEL_RURAL_CONSTRUCAO);
      map.put(TabelasITC.TABELA_FICHA_IMOVEL_RURAL_BENFEITORIA, TabelasITC.NOME_AMIGAVEL_FICHA_IMOVEL_RURAL_BENFEITORIA);
      map.put(TabelasITC.TABELA_FICHA_IMOVEL_RURAL_CULTURA, TabelasITC.NOME_AMIGAVEL_FICHA_IMOVEL_RURAL_CULTURA);
      map.put(TabelasITC.TABELA_FICHA_IMOVEL_RURAL_REBANHO, TabelasITC.NOME_AMIGAVEL_FICHA_IMOVEL_RURAL_REBANHO);
      map.put(TabelasITC.TABELA_FICHA_OUTRO, TabelasITC.NOME_AMIGAVEL_FICHA_OUTRO);
      map.put(TabelasITC.TABELA_FUNCIONALIDADE, TabelasITC.NOME_AMIGAVEL_FUNCIONALIDADE);
      map.put(TabelasITC.TABELA_GIA_ITCD, TabelasITC.NOME_AMIGAVEL_GIA_ITCD);
      map.put(TabelasITC.TABELA_GIA_ITCD_BEM_TRIBUTAVEL, TabelasITC.NOME_AMIGAVEL_GIA_ITCD_BEM_TRIBUTAVEL);
      map.put(TabelasITC.TABELA_GIA_ITCD_DOACAO, TabelasITC.NOME_AMIGAVEL_GIA_ITCD_DOACAO);
      map.put(TabelasITC.TABELA_GIA_ITCD_DOACAO_BENEFICIARIO, TabelasITC.NOME_AMIGAVEL_GIA_ITCD_DOACAO_BENEFICIARIO);
      map.put(TabelasITC.TABELA_GIA_ITCD_INVENTARIO_BENEFICIARIO, TabelasITC.NOME_AMIGAVEL_GIA_ITCD_INVENTARIO_BENEFICIARIO);
      map.put(TabelasITC.TABELA_GIA_ITCD_INVENTARIO_ARROLAMENTO, TabelasITC.NOME_AMIGAVEL_GIA_ITCD_INVENTARIO_ARROLAMENTO);
      map.put(TabelasITC.TABELA_GIA_ITCD_SEPARACAO_DIVORCIO, TabelasITC.NOME_AMIGAVEL_GIA_ITCD_SEPARACAO_DIVORCIO);
      map.put(TabelasITC.TABELA_GIA_ITCD_TEMPORARIO, TabelasITC.NOME_AMIGAVEL_GIA_ITCD_TEMPORARIO);
      map.put(TabelasITC.TABELA_GIA_ITCD_DAR, TabelasITC.NOME_AMIGAVEL_GIA_ITCD_DAR);
      map.put(TabelasITC.TABELA_IMOVEL_URBANO, TabelasITC.NOME_AMIGAVEL_IMOVEL_URBANO);
      map.put(TabelasITC.TABELA_IMOVEL_URBANO_BENF, TabelasITC.NOME_AMIGAVEL_IMOVEL_URBANO_BENF);
      map.put(TabelasITC.TABELA_IMOVEL_URBANO_BENFEITORIA, TabelasITC.NOME_AMIGAVEL_IMOVEL_URBANO_BENFEITORIA);
      map.put(TabelasITC.TABELA_MODULO_AJUDA, TabelasITC.NOME_AMIGAVEL_MODULO_AJUDA);
      map.put(TabelasITC.TABELA_MULTA_MORA, TabelasITC.NOME_AMIGAVEL_MULTA_MORA);
      map.put(TabelasITC.TABELA_NATUREZA_OPERACAO, TabelasITC.NOME_AMIGAVEL_NATUREZA_OPERACAO);
      map.put(TabelasITC.TABELA_PARAMETRO_LEGISLACAO, TabelasITC.NOME_AMIGAVEL_PARAMETRO_LEGISLACAO);
      map.put(TabelasITC.TABELA_PARAMETRO_LEGISLACAO_ALIQUOTA, TabelasITC.NOME_AMIGAVEL_PARAMETRO_LEGISLACAO_ALIQUOTA);
      map.put(TabelasITC.TABELA_PARAMETRO_LEGISLACAO_MULTA, TabelasITC.NOME_AMIGAVEL_PARAMETRO_LEGISLACAO_MULTA);
      map.put(TabelasITC.TABELA_REBANHO, TabelasITC.NOME_AMIGAVEL_REBANHO);
      map.put(TabelasITC.TABELA_STATUS_GIA_ITCD, TabelasITC.NOME_AMIGAVEL_STATUS_GIA_ITCD);
      map.put(TabelasITC.TABELA_TELA_AJUDA, TabelasITC.NOME_AMIGAVEL_TELA_AJUDA);
      map.put(TabelasITC.TABELA_TELA_CAMPO_AJUDA, TabelasITC.NOME_AMIGAVEL_TELA_CAMPO_AJUDA);
      map.put(TabelasITC.TABELA_TELA_FUNCIONALIDADE, TabelasITC.NOME_AMIGAVEL_TELA_FUNCIONALIDADE);
      map.put(TabelasITC.TABELA_AVALIACAO_BEMTRIBUTAVEL, TabelasITC.NOME_AMIGAVEL_AVALIACAO_BEMTRIBUTAVEL);
      map.put(TabelasITC.TABELA_AVALIACAO_BEMTRIBUTAVEL_SERVIDOR, TabelasITC.NOME_AMIGAVEL_AVALIACAO_BEMTRIBUTAVEL_SERVIDOR);
      map.put(TabelasITC.TABELA_GIA_ITCD_IVENTARIO_BENEFICIARIO_ALIQUOTA, TabelasITC.NOME_AMIGAVEL_GIA_ITCD_IVENTARIO_BENEFICIARIO_ALIQUOTA);
      
      
      // ----------------------------- TABELAS EXTERNAS ------------------------------------ 
      
      map.put(TabelasITC.TABELA_EXTERNA_CADS_ENDERECO, TabelasITC.NOME_AMIGAVEL_CADS_ENDERECO);
      map.put(TabelasITC.TABELA_EXTERNA_CADS_PESSOA, TabelasITC.NOME_AMIGAVEL_CADS_PESSOA);
      
      map.put(TabelasITC.TABELA_EXTERNA_CADS_PESSOA_CONJUGE1, TabelasITC.NOME_AMIGAVEL_CADS_PESSOA_CONJUGE1);
      map.put(TabelasITC.TABELA_EXTERNA_CADS_PESSOA_CONJUGE2, TabelasITC.NOME_AMIGAVEL_CADS_PESSOA_CONJUGE2);
      map.put(TabelasITC.TABELA_EXTERNA_CADS_PESSOA_DECUJUS, TabelasITC.NOME_AMIGAVEL_CADS_PESSOA_DECUJUS);
      map.put(TabelasITC.TABELA_EXTERNA_CADS_PESSOA_CONJUGE_SOBREVIVENTE, TabelasITC.NOME_AMIGAVEL_CADS_PESSOA_CONJUGE_SOBREVIVENTE);
      map.put(TabelasITC.TABELA_EXTERNA_CADS_PESSOA_BENEFICIARIO, TabelasITC.NOME_AMIGAVEL_CADS_PESSOA_BENEFICIARIO);
      map.put(TabelasITC.TABELA_EXTERNA_CADS_PESSOA_DECLARANTE, TabelasITC.NOME_AMIGAVEL_CADS_PESSOA_DECLARANTE);


      nomeAmigavel = map.get(nomeRealColuna);

      return nomeAmigavel;
   }


}
