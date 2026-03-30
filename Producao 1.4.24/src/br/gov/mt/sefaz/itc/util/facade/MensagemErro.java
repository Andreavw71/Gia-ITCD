 /**
  * Ábaco Tecnologia de Informação - LTDA
  * Arquivo: MensagemErro.java
  * Revisão: 
  * Data revisão: 
 * $Id: MensagemErro.java,v 1.25 2009/05/05 19:54:45 ricardo.moraes Exp $
  */
 package br.gov.mt.sefaz.itc.util.facade;

import br.gov.mt.sefaz.itc.util.dominio.DomnCodigoTributacaoITCD;

/**
 * Interface utilizada para conter as mensagens de erro do sistema ITCD.
 * As mensagens de erro podem ser de 02 tipos:
 * - Mensagens de validação - criadas de acordo com o caso de uso;
 * - Mensagens de banco de dados - criadas de acordo com as ações de inclusão, alteração, consulta e listagem.
 * @author Marlo Eichenberg Motta
 * @version $Revision: 1.25 $
 */
 public interface MensagemErro extends br.com.abaco.util.facade.MensagemErro
 {
		// CONTA CORRENTE
		public static final String CONSULTA_CORRECAO = "Não foi possível consultar a correção monetária";
		public static final String CONSULTA_PERCENTUAL_JUROS = "Não foi possível consultar o percentual de juros";
      public static final String INSTRUMENTO_CONSTITUTIVO_NAO_CADASTRADO_NO_CCF = "Instrumento constitutivo não cadastrado no Sistema de Conta Corrente Fiscal.";
      public static final String A_SIGLA_DO_INSTRUMENTO_CONSTITUTIVO_OBRIGATORIO = "A sigla do instrumento constitutivo é obrigatório.";
      public static final String O_NUMERO_DOCUMENTO_CREDITO_TRIBUTARIO_OBRIGATORIO = "O número de documento do crédito tributário é obrigatório.";
      public static final String O_NUMERO_PESSOA_E_OBRIGATORIO = "O número pessoa é obrigatório";
      public static final String O_PERIODO_REFERENCIA_OBRIGATORIO = "O período de referência é obrigatório";
      public static final String NAO_EXISTE_LANCAMENTO_DEBITO_DOS_BENEFICARIOS_NO_CCF = "Não existe lançamento de débito do(s) beneficiário(s) no CCF.";
      public static final String NAO_EXISTE_LANCAMENTO_DEBITO_DE_ALGUNS_BENEFICARIOS_NO_CCF = "Não existe lançamento de débito de algum(uns) beneficiário(s) no CCF.";

		//DAR
		public static final String CONSULTA_DAR = "Não foi possível consultar o documento de arrecadação da gia informada";
		public static final String GERAR_DAR = "Problemas ao gerar o Documento de arrecadação.";
		public static final String IMPRIMIR_DAR = "Não foi possível imprimir o dar.";
		public static final String DAR_GIA_ITCD_INATIVA = "GIA-ITCD INATIVADA Não pode ser Gerado DAR para essa GIA.";
		public static final String DAR_GIA_ITCD_PARCELADO = 
			"Essa GIA-ITCD possui dar parcelado, Não é possível gerar um novo DAR.";
		public static final String DAR_PROCURAR_AGENFA = 
			"Não e possível gerar DAR para essa GIA-ITCD! Procurar a Agência Fazendária de Protocolo.";
		public static final String VALIDAR_GIA_ITCD_VINCULAR_DAR_CODIGO_GIA = "O número da GIA-ITCD deve ser informado.";
		public static final String VALIDAR_GIA_ITCD_VINCULAR_DAR_NUMERO = "O número do DAR deve ser informado.";
		public static final String VALIDAR_GIA_ITCD_VINCULAR_DAR_NUMERO_PARCELA = "Número da Parcela deve ser informado.";
		public static final String VALIDAR_GIA_ITCD_VINCULAR_DAR_STATUS = "Não é possível vincular DAR a essa GIA-ITCD.";
		public static final String VALIDAR_GIA_ITCD_VINCULAR_DAR_NUMERO_PARCELA_ZERO = 
			"Número da Parcela deve ser maior que zero.";
		public static final String VALIDAR_GIA_ITCD_VINCULAR_DAR_PARCELADO = "GIA-ITCD já possui um DAR quitado vinculado.";
		public static final String VALIDAR_GIA_ITCD_VINCULRA_DAR_INSCRICAO = "Não é possível vincular DAR a essa GIA-ITCD.";
		public static final String VALIDAR_GIA_ITCD_VINCULAR_DAR_NUMERO_PARCELA_DIFERENTE = 
			"Número da parcela diferente ao Numero de Parcela correspondente do DAR.";
		public static final String VALIDAR_DATA_FINAL_MAIOR_INICIAL = "Data final deve ser maior que data inicial.";
		public static final String VALIDAR_DATA_INICIAL_MAIOR_ATUAL = "Data inicial não pode ser maior que a data atual";
		public static final String VALIDAR_DATA_FINAL_MAIOR_ATUAL = "Data final não pode ser maior que a data atual";
		public static final String VALIDAR_GIA_ITCD_VINCULAR_DAR_NAO_ENCONTRADO_STATUS_DE_NOTIFICADO_OU_RETIFICADO = "Não foi encontrada Notificação ou Retificação para esta GIA-ITCD";
		public static final String VALIDAR_GIA_ITCD_VINCULAR_DAR_JA_VINCULADO = "Este DAR já esta vinculado a uma GIA-ITCD.";
		public static final String VALIDAR_GIA_ITCD_VINCULAR_DAR_E_NECESSARIO_INOFRMAR_PARCELAS = "GIA-ITCD Parcelada, informe o número da parcela que esta sendo vinculada.";
		public static final String VALIDAR_GIA_ITCD_VINCULAR_DAR_NAO_EXISTE = "DAR Informado não encontrado no sistema de Arrecadação.";
		
		//Acessoweb
		public static final String CONSULTA_PERMISSAO_USUARIO = 
			"Não foi possível consultar a lista de permissões do usuário";
		//Geral
		public static final String VALIDAR_CPF_INVALIDO = "CPF inválido";
		public static final String VALIDAR_CNPJ_INVALIDO = "CNPJ inválido";
		public static final String VALIDAR_PARAMETRO_CODIGO_ITC = "Codigo da GIA ITCD Não informado ou inválido.";
		public static final String VALIDAR_DADOS_SERVIDOR_PARA_ALTERAR_GIA_ITCD = "O número da matrícula do servidor que está realizando a alteração deve ser informado. Favor contate o analista responsável.";
		public static final String VALIDAR_SENHA = "senha";
		public static final String VALIDAR_PARAMETRO_CONSULTA = 
			"A consulta de GIA-ITCD deve ser parametrizada."; //TODO Verificar qual a mensagem correta
		public static final String VALIDAR_GIA_ITCD_MUNICIPIO = "O Município de Arrecadação deve ser selecionado.";
		public static final String VALIDAR_GIA_ITCD_CONSULTA_PRAZO_PROTOCOLAR = 
			"Não foi possível consultar a Configuração Gerencial que determina o Prazo de Protocolação da GIA-ITCD.";
		public static final String VALIDAR_GIA_ITCD_CONSULTA_PRAZO_AVALIACAO_JUDICIAL = 
			"Não foi possível consultar a Configuração Gerencial que determina o Prazo de Avaliação Judicial da GIA-ITCD.";
		public static final String VALIDAR_GIA_ITCD_ALTERAR_GIAITCD_PROTOCOLADA = "GIA-ITCD com situação diferente de Pendente de Procotolo e não pode ser alterada.";
		public static final String VALIDAR_GIA_ITCD_ALTERAR_GIAITCD_NAO_PROTOCOLADA = "GIA-ITCD com situação diferente de Procotolada e diferente de Protocolo autorizado pela GIOR e não pode ser alterada.";
      public static final String VALIDAR_GIA_ITCD_IMPRESSA_CONFIRMADA = "GIA-ITCD já foi confirmada e impressa e não pode mais ser alterada.";
		public static final String VALIDAR_GIA_ITCD_ALTERAR_GIAITCD_GIA_ALTERADA_POR_OUTRO_SERVIDOR = "GIA-ITCD já foi alterada por outro servidor.";
		public static final String VALIDAR_GIA_ITCD_ALTERAR_GIAITCD_GIA_STATUS_INVALIDO_ALTERACAO = "Esta GIA-ITCD não pode ser alterada.";
		public static final String VALIDAR_GIA_ITCD_ALTERAR_GIAITCD_GIA_JA_PROTOCOLADA = "GIA-ITCD não pode ser protocolada pois seu status é diferente de Pendente de Protocolo.";
      public static final String VALIDAR_GIA_ITCD_ALTERAR_GIAITCD_PENDENTE_PROTOCOLO = "GIA-ITCD ainda não Protocolada";
		// Conjuge
		public static final String INCLUIR_CONJUGE_BEM_TRIBUTAVEL = "Não foi possível incluir o Bem Conjuge.";
		public static final String ALTERAR_CONJUGE_BEM_TRIBUTAVEL = "Não foi possível alterar o Bem Conjuge.";
		public static final String EXCLUIR_CONJUGE_BEM_TRIBUTAVEL = "Não foi possível excluir o Bem Conjuge.";
		public static final String VALIDAR_CONJUGE_EXISTE = "O Conjuge já existe para o bem tributável";
		public static final String LISTAR_CONJUGE_BEM_TRIBUTAVEL = 
			"Não foi possível listar os Conjuges relacionados com o Bem Tributável.";
		// Avaliação Bem Tributavel
		public static final String INCLUIR_AVALIACAO_BEM_TRIBUTAVEL = 
			"Não foi possível incluir a Avaliação do Bem Tributável.";
		public static final String ALTERAR_AVALIACAO_BEM_TRIBUTAVEL = 
			"Não foi possível alterar a Avaliação do Bem Tributável.";
		public static final String LISTAR_AVALIACAO_BEM_TRIBUTAVEL = 
			"Não foi possível listar as Avaliações dos Bens Tributáveis.";
		// Avaliação Bem Tributavel Servidor
		public static final String INCLUIR_AVALIACAO_BEM_TRIBUTAVEL_SERVIDOR = 
			"Não foi possível incluir a Avaliação do Bem Tributável - Servidor.";
		public static final String ALTERAR_AVALIACAO_BEM_TRIBUTAVEL_SERVIDOR = 
			"Não foi possível alterar a Avaliação do Bem Tributável - Servidor.";
		public static final String LISTAR_AVALIACAO_BEM_TRIBUTAVEL_SERVIDOR = 
			"Não foi possível listar as Avaliações dos Bens Tributáveis - Servidor.";
		//Bem tributavel
		public static final String INCLUIR_BEM_TRIBUTAVEL = "Não foi possível incluir o Bem Tributável.";
		public static final String ALTERAR_BEM_TRIBUTAVEL = "Não foi possível alterar o Bem Tributável.";
		public static final String EXCLUIR_BEM_TRIBUTAVEL = "Não foi possível excluir o Bem Tributável.";
		public static final String EXCLUIR_BEM_TRIBUTAVEL_CHAVE = 
			"Não foi possível excluir o Bem Tributavel - a chave primaria Não foi informada.";
		public static final String VALIDAR_BEM_TRIBUTAVEL_PARAMETRO_CODIGO = 
			"Codigo do Bem Tributável Não informado."; // TODO Verificar mensagem de erro
		public static final String VALIDAR_BEM_TRIBUTAVEL_PARAMETRO_CODIGO_ITC = 
			MensagemErro.VALIDAR_PARAMETRO_CODIGO_ITC; // TODO Verificar mensagem de erro
		public static final String VALIDAR_BEM_TRIBUTAVEL_PARAMETRO_VALOR_MERCADO = 
			"Valor de mercado deve   ser informado.";
		public static final String VALIDAR_BEM_TRIBUTAVEL_PARAMETRO_DESCRICAO = "Favor informar a descrição do bem.";
		public static final String VALIDAR_BEM_TRIBUTAVEL_PARAMETRO_ISENCAO_PREVISTA = 
			"Campo Isenção Prevista do Bem Tributável não informado."; // TODO Verificar mensagem de erro
		public static final String VALIDAR_BEM_TRIBUTAVEL_PARAMETRO_TIPO = "Favor informar o tipo do bem.";
		public static final String VALIDAR_BEM_TRIBUTAVEL_PARAMETRO_CLASSIFICACAO = 
			"Favor informar a classificação do bem.";
		public static final String VALIDAR_BEM_TRIBUTAVEL_PARAMETRO_VALOR_MERCADO_MAIOR_ZERO = 
			"Valor de mercado deve ser maior que zero.";
		public static final String VALIDAR_BEM_TRIBUTAVEL = "Favor informar primeiro o bem tributável.";
		public static final String LISTAR_BEM_TRIBUTAVEL = "Não foi possível listar os Bens Tributáveis.";
		public static final String VALIDAR_BEM_TRIBUTAVEL_INCLUIR_DESCRICAO_E_GIA = "A descrição informada já existe.";
		//Rebanho - Banco de Dados
		public static final String INCLUIR_REBANHO = "Não foi possível incluir o Rebanho.";
		public static final String ALTERAR_REBANHO = "Não foi possível alterar o Rebanho.";
		public static final String CONSULTAR_REBANHO = "Não foi possível consultar o Rebanho.";
		public static final String LISTAR_REBANHO = "Não foi possível listar os Rebanhos.";
		//Rebanho - Validação
		public static final String VALIDAR_REBANHO_PARAMETRO_DESCRICAO = "Favor informar a descrição do Rebanho.";
		public static final String VALIDAR_REBANHO_PARAMETRO_STATUS = "Favor informar um status válido para o Rebanho.";
		public static final String VALIDAR_REBANHO_PARAMETRO_TIPO_SELECAO = "Favor selecionar o Tipo de Pesquisa.";
		public static final String VALIDAR_REBANHO_PARAMETRO_CODIGO = "Favor informar o código do Rebanho.";
		public static final String VALIDAR_REBANHO_INCLUIR_DESCRICAO = "A descrição informada já existe.";
		public static final String VALIDAR_REBANHO_LISTAR_DESCRICAO = 
			"Não foi encontrado registro de rebanho com essa descrição.";
      public static final String VALIDAR_REBANHO_LISTAR_CODIGO = "Código informado não existe.";
      //Veículo - Banco de Dados
      public static final String INCLUIR_VEICULO = "Não foi possível incluir o Veículo.";
      public static final String ALTERAR_VEICULO = "Não foi possível alterar o Veículo.";
      public static final String CONSULTAR_VEICULO = "Não foi possível consultar o Veículo.";
      public static final String LISTAR_VEICULO = "Não foi possível listar os Veículos.";
		public static final String VALIDAR_VEICULO_LISTAR_CODIGO = "Código informado não existe.";
      //Rebanho LPM - Banco de Dados
      public static final String INCLUIR_REBANHO_LPM = "Não foi possível incluir o Rebanho.";
      public static final String ALTERAR_REBANHO_LPM = "Não foi possível alterar o Rebanho.";
      public static final String CONSULTAR_REBANHO_LPM = "Não foi possível consultar o Rebanho.";
      public static final String LISTAR_REBANHO_LPM = "Não foi possível listar os Veículos.";
      public static final String VALIDAR_REBANHO_LPM_LISTAR_CODIGO = "Código informado não existe.";
		//Cultura - Banco de Dados
		public static final String INCLUIR_CULTURA = "Não foi possível incluir a Cultura.";
		public static final String ALTERAR_CULTURA = "Não foi possível alterar a Cultura.";
		public static final String CONSULTAR_CULTURA = "Não foi possível consultar a Cultura.";
		public static final String LISTAR_CULTURA = "Não foi possível listar as Culturas.";
      //Cultura - Validação
      public static final String VALIDAR_CULTURA_PARAMETRO_DESCRICAO = "Favor informar a descrição da Cultura.";
      public static final String VALIDAR_CULTURA_PARAMETRO_STATUS = "Favor informar um status válido para a Cultura.";
      public static final String VALIDAR_CULTURA_PARAMETRO_TIPO_SELECAO = "A cultura deve ser selecionada.";
      public static final String VALIDAR_CULTURA_PARAMETRO_CODIGO = "Favor informar o código da Cultura.";
      public static final String VALIDAR_CULTURA_INCLUIR_DESCRICAO = "A descrição informada já existe.";
      public static final String VALIDAR_CULTURA_LISTAR_DESCRICAO = 
         "Não foi encontrado nenhum registro de cultura com essa descrição.";
      public static final String VALIDAR_CULTURA_LISTAR_CODIGO = "Código informado não existe.";
      //Distancia
      public static final String CONSULTAR_DISTANCIA = "Não foi possível consultar a Distância.";
      public static final String LISTAR_DISTANCIA = "Não foi possível listar a Distância.";
      public static final String VALIDAR_DISTANCIA_INICIAL_PERIMETRO_URBANO = "Favor informar a Distância Inicial do Imóvel Rural até o Perímetro Urbano.";
      public static final String VALIDAR_DISTANCIA_FINAL_PERIMETRO_URBANO = "Favor informar a Distância Final do Imóvel Rural até o Perímetro Urbano.";
      public static final String VALIDAR_DISTANCIA_INICIAL_PERIMETRO_RODOVIA_PAVIMENTADA = "Favor informar a Distância Inicial do Imóvel Rural até a Rodovia pavimentada.";
      public static final String VALIDAR_DISTANCIA_INICIAL_PERIMETRO_URBANO_NAO_PODE_SER_MENOR_QUE_ZERO = "Distância Inicial do Imóvel Rural até o Perímetro Urbano não pode ser menor que Zero.";
      public static final String VALIDAR_DISTANCIA_INICIAL_RODOVIA_PAVIMENTADA_NAO_PODE_SER_MENOR_QUE_ZERO ="Distância Inicial do Imóvel Rural até a Rodovia pavimentada não pode ser menor que Zero.";
      public static final String VALIDAR_DISTANCIA_FINAL_MENOR_QUE_INICIAL_PERIMETRO_URBANO =  "Distância Final do Imóvel Rural até o Perímetro Urbano deve ser maior que Distância Inicial do Imóvel Rural até o Perímetro Urbano";
      public static final String VALIDAR_DISTANCIA_FINAL_MENOR_QUE_INICIAL_RODOVIA_PAVIMENTADA =  "Distância Final do Imóvel Rural até a Rodovia pavimentada deve ser maior que Distância Inicial do Imóvel Rural até a Rodovia pavimentada";
      public static final String VALIDAR_FAIXA_DE_DISTANCIA_JA_FOI_INCLUIDA = "Distância Final do Imóvel Rural até o Perímetro Urbano já está cadastrada para outra faixa de Distância.";
      public static final String VALIDAR_DISTANCIA_FINAL_RODOVIA_PAVIMENTADA_EM_ABERTO = "A Distância Inicial do Imóvel Rural não pode ser incluída até que seja finalizado o cadastro da Rodovia Pavimentada do registro anterior.";
      public static final String VALIDAR_FAIXA_DE_DISTANCIA_RODOVIA_PAVIMENTADA_JA_FOI_INCLUIDA = "Distância Final do Imóvel Rural até a Rodovia pavimentada já está cadastrada para outra faixa de Distância.";
      public static final String VALIDAR_DISTTANCIA_INICIAL_RODOVIA_PAVIMENTADA_DEVE_SER_MAIOR_QUE_DISTANCIA_FINAL_ANTERIOR = "A Distância Inicial do Imóvel Rural até a Rodovia pavimentada deverá ser o valor da Distância Final do Imóvel Rural até a Rodovia pavimentada do registro anterior acrescido de 1(um)";
      public static final String VALIDAR_DISTTANCIA_INICIAL_PERIMETRO_URBANO_DEVE_SER_MAIOR_QUE_DISTANCIA_FINAL_ANTERIOR="A Distância Inicial do Imóvel Rural até o Perímetro Urbano deverá ser o valor da Distância Final do Imóvel Rural até o Perímetro Urbano do registro anterior acrescido de 1(um).";
      public static final String VALIDAR_SELECIONAR_TIPO_DISTANCIA = "Favor selecionar Distância a Utilizar.";
      public static final String SELECIONAR_INTERVALO_DISTANCIA = "Erro ao selecionar o intervalo distância, contactar a gerência Sistema ITCD";
      public static final String SELECIONAR_INTERVALO_DISTANCIA_INEXISTENTE = "Distância não encontrada, entrar em contato com a gerência Sistema ITCD";
      //Criterio Municipio
      public static final String CONSULTAR_CRITERIO_MUNICIPIO= "Não foi possível consultar o Critério Município.";
      public static final String LISTAR_CRITERIO_MUNICIPIO= "Não foi possível listar Critério de Municípios.";
      public static final String VALIDAR_CRITERIO_MUNICIPIO_VALOR_MINIMO = "Favor informar a Valor Total Mínimo do Imóvel por hectare.";
      public static final String VALIDAR_CRITERIO_MUNICIPIO_VALOR_MEDIO = "Favor informar a Valor Total Médio do Imóvel por hectare.";
      public static final String VALIDAR_CRITERIO_MUNICIPIO_VALOR_MAXIMO = "Favor informar a Valor Total Máximo do Imóvel por hectare.";
      public static final String VALIDAR_SELECIONAR_MUNICIPIO = "Favor selecione um múnicipio.";
      // Base de Calculo Imovel Rural
      public static final String CONSULTAR_BASE_CALCULO_IMOVEL_RURAL = "Não foi possível consultar a Base de Calculo Imóvel Rural.";
      public static final String LISTAR_BASE_CALCULO_IMOVEL_RURAL = "Não foi possível listar Base de Calculo Imóvel Rural.";
      public static final String VALIDAR_SELECIONAR_TIPO_ATIVIDADE = "selecionar o Tipo da Atividade.";
      public static final String VALIDAR_INFORMAR_DISTANCIA_INICIAL = "Favor informar a Distância Inicial.";
      public static final String VALIDAR_DISTANCIA_INICIAL_MAIOR_QUE_ZERO = "Distância Inicial não pode ser menor que Zero.";
      public static final String VALIDAR_INFORMAR_DISTANCIA_FINAL = "Favor informar a Distância Inicial.";
      public static final String VALIDAR_DISTANCIA_INICIAL_DIFERENTE_DISTANCIA_FINAL ="A Distância Inicial deverá ser igual a Distância Final do registro anterior";
      public static final String VALIDAR_DISTANCIA_INICIAL_MAIOR_DISTANCIA_FINAL ="A Distância Inicial deverá ser uma unidade maior que a Distância Final do registro anterior";
      public static final String VALIDAR_ATIVIDADE_INICIAL_DIFERENTE_DISTANCIA_FINAL ="O Percentual da Atividade Inicial deverá ser igual ao Percentual da Atividade Final do registro anterior";
      public static final String VALIDAR_AREA_EXPLORADA_INICIAL_DIFERENTE_DISTANCIA_FINAL ="O Percentual da Área explorada Inicial deverá ser igual ao Área explorada Final do registro anterior";
      public static final String VALIDAR_INFORMAR_ATIVIDADE_INICIAL = "Favor informar o Percentual da Atividade Inicial";
      public static final String VALIDAR_INFORMAR_AREA_EXPLORADA_INICIAL = "Favor informar o Percentual da Área Explorada Inicial";
      public static final String VALIDAR_INFORMAR_NUMERO_ITEM ="Favor informar o Número do Item";
      public static final String VALIDAR_INFORMAR_BASE_CALCULO_MINIMO ="Favor informar o Percentual da Base de Cálculo Mínima";
      public static final String VALIDAR_INFORMAR_CRITERIO_BASE_CALCULO ="Favor informar o Critério da Base de Cálculo";
      public static final String VALIDAR_DISTANCIA_FINAL_JA_CADASTRADA = "Distância Final já está cadastrada";
      public static final String BASE_DE_CALCULO_NAO_ENCONTRADA = "Base de Cálculo não encontrada, entrar em contato com gerência Sistema ITCD";
      //IPTU
       public static final String LISTAR_IPTU = "Não foi possível listar IPTU.";
       public static final String VALIDAR_SELECIONE_TIPO = "Por favor, selecione o tipo.";
       public static final String VALIDAR_INFORMAR_PERCENTUAL_ESTIMADO = "Favor, informar o Percentutal Estimado.";
       public static final String VALIDAR_INFORMAR_VALOR_TERRITORIAL = "Favor, informar o Valor m² Territorial.";
       public static final String VALIDAR_INFORMAR_VALOR_PREDIAL = "Favor, informar o Valor m² Prediall.";
       
      //IPTU IMPORTAR ARQUIVO PREFEITURA 
       public static final String VALIDAR_ARQUIVO_PREFEITURA = "Favor, anexar arquivo.";
       public static final String VALIDAR_FORMATO_ARQUIVO = "Favor, anexar arquivo no formato Excel.";
       public static final String VALIDAR_TAMANHO_ARQUIVO = "Favor, diminua o nome do arquivo deve conter no máximo 45 Caracteres.";
       
       //IPTU PREFEITURA
        public static final String LISTAR_PREFEITURA = "Não foi possivel listar dados iptu prefeitura.";
      //Benfeitoria - Banco de dados
		public static final String INCLUIR_BENFEITORIA = "Não foi possível incluir a Benfeitoria.";
		public static final String ALTERAR_BENFEITORIA = "Não foi possível alterar a Benfeitoria.";
		public static final String CONSULTAR_BENFEITORIA = "Não foi possível consultar a Benfeitoria.";
		public static final String LISTAR_BENFEITORIA = "Não foi possível listar as Benfeitorias.";
		//Benfeitoria - Validação
		public static final String VALIDAR_BENFEITORIA_INCLUIR_ALTERAR_DESCRICAO = "A descrição informada já existe.";
		public static final String VALIDAR_BENFEITORIA_PARAMETRO_DESCRICAO = "Favor informar a descrição.";
		// CONSTRUCAO - Banco de Dados
		public static final String ALTERAR_CONSTRUCAO = "Não foi possível alterar a Construção.";
		public static final String INCLUIR_CONSTRUCAO = "Não foi possível incluir a Construção.";
		public static final String CONSULTAR_CONSTRUCAO = "Não foi possível consultar a Construção.";
		public static final String LISTAR_CONSTRUCAO = "Não foi possível listar as Construções.";
		// Construcao Validação
		public static final String VALIDAR_CONSTRUCAO_PARAMETRO_DESCRICAO = "Favor informar a descrição da Construção .";
		public static final String VALIDAR_CONSTRUCAO_PARAMETRO_DESCRICAO_EXISTENTE = "A descrição informada já existe.";
		public static final String VALIDAR_CONSTRUCAO_LISTAR_DESCRICAO = 
			"Não foi encontrado nenhuma Construção com esta descrição.";
		public static final String VALIDAR_CONSTRUCAO_UTILIZACAO = "A utilização da Construção deve ser selecionada";
		//Bem - Validação
		public static final String VALIDAR_BEM_PARAMETRO_DESCRICAO = "Favor informar a descrição do Bem.";
		public static final String VALIDAR_BEM_PARAMETRO_TIPO = "Favor selecionar o tipo de Bem.";
      public static final String VALIDAR_BEM_PARAMETRO_TIPO_VERIFICACAO = "Favor selecionar o tipo de Verificação.";
      public static final String VALIDAR_BEM_PARAMETRO_TIPO_PROTOCOLO = "Favor selecionar o tipo de Protocolo.";
		public static final String VALIDAR_BEM_LISTAR_DESCRICAO = "Não foi encontrado nenhum Bem com esta descrição.";
		public static final String VALIDAR_BEM_INCLUIR_DESCRICAO_E_TIPO = 
			"A descrição informada já existe para este tipo de Bem.";
		public static final String VALIDAR_BEM_ALTERAR_DESCRICAO_E_TIPO = "A descrição informada já existe.";
		public static final String FLAG_POSSUI_CONTRUCAO_DEVE_SER_INFORMADO = "Deve ser informado se o bem possui edificação/construção.";
		//Bem - Banco de Dados
		public static final String INCLUIR_BEM = "Não foi possível incluir o Bem.";
		public static final String ALTERAR_BEM = "Não foi possível alterar o Bem.";
		public static final String CONSULTAR_BEM = "Não foi possível consultar o Bem.";
		public static final String LISTAR_BEM = "Não foi possível listar os Bens.";
		// PARAMETRO LEGISLACAO - Validação
		public static final String VALIDAR_PARAMETRO_LEGISLACAO_DATA = 
			"A data de vigêngia do Parametro Legislação deve ser informada.";
		public static final String VALIDAR_PARAMETRO_LEGISLACAO_ALIQUOTA = "Favor informar a alíquota.";
		public static final String VALIDAR_PARAMETRO_BENFEITORIA = "Favor selecionar uma benfeitoria.";
		public static final String VALIDAR_PARAMETRO_TIPO_LOGRADOURO = "Favor informar o tipo do logradouro.";
		public static final String VALIDAR_PARAMETRO_LOGRADOURO = "Favor informar o logradouro.";
		public static final String VALIDAR_PARAMETRO_NUMERO = "Favor informar o número do logradouro.";
		public static final String VALIDAR_PARAMETRO_BAIRRO = "Favor informar o bairro.";
		public static final String VALIDAR_PARAMETRO_TIPO_IMOVEL = "Favor informar o tipo do imóvel.";
		public static final String VALIDAR_PARAMETRO_ESTADO_CONSERVACAO = "O estado de Conservação deve ser selecionado.";
		public static final String VALIDAR_PARAMETRO_AREA_TOTAL_IMOVEL = "Favor informar a área total.";
		public static final String VALIDAR_PARAMETRO_AREA_CONSTRUIDA = "Favor informar a área construída.";
		public static final String VALIDAR_PARAMETRO_TIPO_CONSTRUCAO = "A Construção deve ser selecionado.";
		public static final String VALIDAR_PARAMETRO_TIPO_ACESSO = "Favor informar o tipo de acesso.";
		public static final String VALIDAR_PARAMETRO_VALOR_TOTAL = "Favor informar o valor de mercado de imóvel.";
		public static final String VALIDAR_PARAMETRO_VALOR_VENAL = "Favor informar o valor venal para fins de IPTU.";
		public static final String VALIDAR_AREA_TOTAL_IMOVEL_MAIOR_QUE_ZERO = "A área total deve ser maior que zero.";
		public static final String VALIDAR_AREA_CONSTRUIDA_MAIOR_QUE_ZERO = " A área construída deve ser maior que zero.";
		public static final String VALIDAR_VALOR_TOTAL_DE_MERCADO_MAIOR_QUE_ZERO = 
			"O valor  total de mercado deve ser maior que zero.";
		public static final String VALIDAR_VALOR_VENAL_IPTU_MAIOR_QUE_ZERO = 
			"O valor venal do IPTU deve ser maior que zero.";
		public static final String VALIDAR_PARAMETRO_CEP = "Favor informar o CEP.";
		public static final String VALIDAR_PARAMETRO_LEGISLACAO_ALIQUOTA_CAUSA_MORTIS = 
			"Cadastrar Alíquota para o fato gerador Causa Mortis.";
		public static final String VALIDAR_PARAMETRO_LEGISLACAO_ALIQUOTA_INTER_VIVOS = 
			"Cadastrar Alíquota para o fato gerador Inter Vivos.";
		public static final String VALIDAR_PARAMETRO_LEGISLACAO_CADASTRAR_ALIQUOTA = "Alíquota deve ser cadastrada.";
		public static final String VALIDAR_PARAMETRO_LEGISLACAO_DATA_VIGENCIA_FINAL_MAIOR_INICIAL = 
			"Data de vigência final deve ser maior que a data de vigência inicial.";
		public static final String VALIDAR_PARAMETRO_LEGISLACAO_DATA_VIGENCIA_INICIAL = 
			"Favor informar a data da vigência inicial.";
		public static final String VALIDAR_PARAMETRO_LEGISLACAO_DATA_VIGENCIA_INICIAL_MENOS_FINAL = 
			"Data de vigência inicial deve ser menor que a data de vigência final.";
		public static final String VALIDAR_PARAMETRO_LEGISLACAO_AINDA_ABERTA = 
			"Existe lei com data de vigência em aberto. Favor verificar as datas de vigência das leis cadastradas, através da funcionalidade Alterar Parâmetros de Legislação.";
		public static final String VALIDAR_PARAMETRO_LEGISLACAO_DIAS_FINAL = 
			"Favor informar a quantidade de dias final de atraso.";
		public static final String VALIDAR_PARAMETRO_LEGISLACAO_DIAS_FINAL_JA_CADASTRADA = 
			"A Quantidade de Dias Final já está cadastrada para outro Período.";
		public static final String VALIDAR_PARAMETRO_LEGISLACAO_DIAS_FINAL_EM_ABERTO = 
			"Existe multa com faixa de dias em aberto. Favor verificar as multas para essa lei através da funcionalidade: Alterar Parâmetro de Legislação.";
		public static final String VALIDAR_PARAMETRO_LEGISLACAO_DIAS_FINAL_MAIOR_DIAS_INICIAL = 
			"Quantidade de Dias Final deve ser maior que a Quantidade de Dias Inicial.";
		public static final String VALIDAR_PARAMETRO_LEGISLACAO_DIAS_FINAL_MENOR_ZERO = 
			"Quantidade de Dias Final não pode ser menor ou igual a Zero.";
		public static final String VALIDAR_PARAMETRO_LEGISLACAO_DIAS_INICIAL_MENOR_ZERO = 
			"Quantidade de dias inicial não pode ser menor ou igual a Zero.";
		public static final String VALIDAR_PARAMETRO_LEGISLACAO_DIAS_INICIAL = 
			"Favor informar a quantidade de dias inicial de atraso.";
		public static final String VALIDAR_PARAMETRO_LEGISLACAO_DIAS_INICIAL_JA_CADASTRADA = 
			"A quantidade de Dias Inicial já está cadastrada para outro Período.";
		public static final String VALIDAR_PARAMETRO_LEGISLACAO_DIAS_INICIAL_MENOR_DIAS_FINAL = 
			"Quantidade de dias Inicial deve ser menor que a Quantidade de dias Final.";
		public static final String VALIDAR_PARAMETRO_LEGISLACAO_EXISTE_FAIXA_UPF_CAUSA_MORTIS = 
			"Existe faixa de UPF sem alíquota cadastrada para o fato gerador Causa Mortis.";
		public static final String VALIDAR_PARAMETRO_LEGISLACAO_EXISTE_FAIXA_UPF_INTER_VIVOS = 
			"Existe faixa de UPF sem alíquota cadastrada para o fato gerador Inter Vivos.";
		public static final String VALIDAR_PARAMETRO_LEGISLACAO_FATO_GERADOR = "Favor selecionar o fato gerador.";
		public static final String VALIDAR_PARAMETRO_LEGISLACAO_JA_EXISTE_GIA = 
			"Não é possível alterar um Parametro Legislação que já tenha sido utilizado em uma GIA.";
		public static final String VALIDAR_PARAMETRO_LEGISLACAO_MULTA = "Multa deve ser cadastrada.";
		public static final String VALIDAR_PARAMETRO_LEGISLACAO_NUMERO_ANO = 
			"Favor informar o número / ano da Legislação. ";
		public static final String VALIDAR_PARAMETRO_LEGISLACAO_NUMERO_JA_EXISTE = "O número da Legislação já existe";
		public static final String VALIDAR_PARAMETRO_LEGISLACAO_PERCENTUAL_ALIQUOTA_INVALIDO = 
			"Percentual da Alíquota deve ser maior que zero e menor ou igual a cem.";
		public static final String VALIDAR_PARAMETRO_LEGISLACAO_PERCENTUAL_ALIQUOTA_JA_CADASTRADO = 
			"Percentual de Alíquota já cadastrado para esse tipo de fato gerador e para essa lei.";
		public static final String VALIDAR_PARAMETRO_LEGISLACAO_PERCENTUAL_MULTA = "Favor informar o percentual da Multa.";
		public static final String VALIDAR_PARAMETRO_LEGISLACAO_PERCENTUAL_MULTA_INVALIDO = 
			"Percentual da Multa deve ser maior que zero e menor ou igual a cem.";
		public static final String VALIDAR_PARAMETRO_LEGISLACAO_PERCENTUAL_MULTA_JA_CADASTRADO = 
			"Já existe registro com esse percentual de multa para essa legislação.";
		public static final String VALIDAR_PARAMETRO_LEGISLACAO_PERIODO_SEM_MULTA = 
			"Existe período de multa sem multa cadastrada.";
		public static final String VALIDAR_PARAMETRO_LEGISLACAO_UPF_INICIAL_MENOR_UPF_FINAL = 
			"Quantidade de UPF Inicial deve ser menor que a Quantidade de UPF Final.";
		public static final String VALIDAR_PARAMETRO_LEGISLACAO_UPF_INICIAL_MENOR_ZERO = 
			"Quantidade de UPF Inicial não de pode ser menor ou igual a Zero.";
		public static final String VALIDAR_PARAMETRO_LEGISLACAO_UPF_INICIAL = "Favor informar a quantidade UPF inicial.";
		public static final String VALIDAR_PARAMETRO_LEGISLACAO_UPF_INICIAL_JA_CADASTRADO_FAIXA = 
			"A quantidade de UPF Inicial já está cadastrada para outra faixa de UPF.";
		public static final String VALIDAR_PARAMETRO_LEGISLACAO_UPF_FINAL = "Favor informar a quantidade UPF final.";
		public static final String VALIDAR_PARAMETRO_LEGISLACAO_UPF_FINAL_MAIOR_UPF_INICIAL = 
			"Quantidade UPF Final deve ser maior que a Quantidade UPF Inicial.";
		public static final String VALIDAR_PARAMETRO_LEGISLACAO_UPF_FINAL_MENOR_ZERO = 
			"Quantidade UPF Final não pode ser menor ou igual a Zero.";
		public static final String VALIDAR_PARAMETRO_LEGISLACAO_UPF_FINAL_AINDA_ABERTA = 
			"Existe faixa de UPF Final em aberto. Favor verificar para essa lei as faixas de UPF através da funcionalidade: Alterar Parâmetro de Legislação.";
		public static final String VALIDAR_PARAMETRO_LEGISLACAO_UPF_FINAL_JA_CADASTRADO_FAIXA = 
			"A quantidade de UPF Final já está cadastrada para outra faixa de UPF.";
		public static final String VALIDAR_PARAMETRO_LEGISLACAO_NUMERO_LEGISLACAO = 
			"Favor informar o número da Legislação.";
		public static final String VALIDAR_PARAMETRO_LEGISLACAO_EXISTE_LEI_ABERTO = 
			"Existe lei com data de vigência em aberto. Favor verificar as datas de vigência das leis cadstradas através da funcionalidade Alterar Parâmetros de Legislação.";
		public static final String VALIDAR_PARAMETRO_LEGISLACAO_ANO_CONTER_QUATRO_DIGITOS = 
			"O ano deve conter quatro dígitos.";
		public static final String VALIDAR_PARAMETRO_LEGISLACAO_EXCLUIR_ALIQUOTA = "Deseja realmente excluir a alíquota?";
		public static final String VALIDAR_PARAMETRO_LEGISLACAO_EXCLUIR_MULTA = "Deseja realmente excluir a multa?";
		// PARAMETRO LEGISLACAO - Banco de Dados  
		public static final String ALTERAR_PARAMETRO_LEGISLACAO = "Não foi possível alterar o  Parametro Legislação.";
		public static final String CONSULTAR_PARAMETRO_LEGISLACAO = "Não foi possível consultar o Parametro Legislação.";
		public static final String CONSULTAR_PARAMETRO_LEGISLACAO_ALIQUOTA = "Não foi possível consultar a Aliquota.";
		public static final String CONSULTAR_PARAMETRO_LEGISLACAO_MULTA = "Não foi possível consultar a Multa.";
		public static final String EXCLUIR_PARAMETRO_LEGISLACAO_ALIQUOTA = "Não foi possível excluir Aliquota.";
		public static final String EXCLUIR_PARAMETRO_LEGISLACAO_MULTA = "Não foi possível excluir Multa.";
		public static final String INCLUIR_PARAMETRO_LEGISLACAO = "Não foi possível incluir  Parametro Legislação.";
		public static final String INCLUIR_PARAMETRO_LEGISLACAO_ALIQUOTA = "Não foi possível incluir Aliquota.";
		public static final String ALTERAR_PARAMETRO_LEGISLACAO_ALIQUOTA = "Não foi possível alterar Aliquota.";
		public static final String INCLUIR_PARAMETRO_LEGISLACAO_MULTA = "Não foi possível incluir Multa.";
		public static final String ALTERAR_PARAMETRO_LEGISLACAO_MULTA = "Não foi possível alterar Multa.";
		public static final String LISTAR_PARAMETRO_LEGISLACAO_ALIQUOTA = "Não foi possível listar as Aliquota.";
		public static final String LISTAR_PARAMETRO_LEGISLACAO_MULTA = "Não foi possível listar as Multas.";
		// NATUREZA OPERACAO -  Banco de Dados
		public static final String INCLUIR_NATUREZA_OPERACAO = "Não foi possível incluir a Natureza da Operação";
		public static final String ALTERAR_NATUREZA_OPERACAO = "Não foi possível alterar a Natureza da Operação";
		public static final String CONSULTAR_NATUREZA_OPERACAO = "Não foi possível consultar a Natureza da Operação";
		public static final String LISTAR_NATUREZA_OPERACAO = "Não foi possível listar a Natureza da Operação";
		// NATUREZA OPERACAO - Validação
		public static final String VALIDAR_NATUREZA_OPERACAO_CODIGO_NATUREZA = "Código da Natureza da Operação que é obrigatório não foi encontrado.";
		public static final String VALIDAR_NATUREZA_OPERACAO_TIPO_GIA = "Favor informar o tipo de GIA.";
		public static final String VALIDAR_NATUREZA_OPERACAO_TIPO_PROCESSO = "Favor informar o tipo de processo.";
		public static final String VALIDAR_NATUREZA_OPERACAO_DESCRICAO = "Favor informar a descrição da natureza da operação.";
		public static final String VALIDAR_NATUREZA_OPERACAO_JA_EXISTE_DESCRICAO = "A descrição informada já existe.";
		public static final String VALIDAR_NATUREZA_OPERACAO_BASE_CALCULO_REDUZIDO = "Selecione uma opção da Base de cálculo reduzido";
		public static final String VALIDAR_NATUREZA_OPERACAO_PERCENTUAL_BASE_CALCULO = "Favor Informar percentual de base de cálculo reduzido.";
		public static final String VALIDAR_NATUREZA_OPERACAO_VALOR_BASE_CALCULO_REDUZIDA_MAIOR_ZERO_MENOR_CEM = "Valor da Base de Cálculo reduzida deve ser  maior que zero e menor que cem";
		public static final String VALIDAR_NATUREZA_OPERCAO_FLAG_ISENCAO_PREVISTA = "Favor informar se a natureza da Operação possui Isenção prevista em Lei.";
		// CONFIGURACAO GERENCIAL PARAMETROS - Banco de Dados
		public static final String INCLUIR_CONFIGURACAO_GERENCIAL_PARAMETROS = 
			"Não foi possível incluir a Configuração Gerencial de Parametros";
		public static final String ALTERAR_CONFIGURACAO_GERENCIAL_PARAMETROS = 
			"Não foi possível alterar a Configuração Gerencial de Parametros";
		public static final String CONSULTAR_CONFIGURACAO_GERENCIAL_PARAMETROS = 
			"Não foi possível consultar a Configuração Gerencial de Parametros";
		public static final String LISTAR_CONFIGURACAO_GERENCIAL_PARAMETROS = 
			"Não foi possível listar a Configuração Gerencial de Parametros";
		// CONFIGURACAO GERENCIAL PARAMETROS - Validação
		public static final String VALIDAR_ITEMS_CONFIGURACAO_GERENCIAL_PARAMETROS = 
			"Favor Preencher o campo do item selecionado.";
		public static final String VALIDAR_COLECAO_CONFIGURACAO_GERENCIAL_PARAMETROS = 
			"Favor Selecionar um Item para preenchimento do campo.";
		public static final String VALIDAR_NUMERICO_CONFIGURACAO_GERENCIAL_PARAMETROS = 
			"O Valor informado deve ser maior que zero.";
		public static final String VALIDAR_VALOR_CONFIGURACAO_GERENCIAL_PARAMETROS = 
			"O Valor informado deve ser maior que zero.";
		// MULTA DE MORA
		public static final String INCLUIR_MULTA_MORA = "Não foi possível incluir a Multa de Mora.";
		public static final String ALTERAR_MULTA_MORA = "Não foi possível alterar a Multa de Mora.";
		public static final String CONSULTAR_MULTA_MORA = "Não foi possível consultar a Multa de Mora.";
		// MULTA DE MORA - Validação
		public static final String VALIDAR_MULTA_MORA = "Favor informar a multa de mora.";
		public static final String VALIDAR_MENOR_ZERO_MULTA_MORA = 
			"O Percentual de multa de mora deve ser maior do que zero.";
		public static final String VALIDAR_PERCENTUAL_MULTA_MORA = "Favor informar o percentual máximo da multa de mora.";
		public static final String VALIDAR_MULTA_MORA_MENOR_PERCENTUAL = 
			"Percentual de multa de mora deve ser menor que o percentual máximo de multa de mora.";
		public static final String VALIDAR_PERCENTUAL_MULTA_MORA_MENOR_ZERO = 
			"O Percentual máximo de multa de mora deve ser maior do que zero.";
		// AJUDA - Banco de Dados
		//public static final String  = " ";
		// AJUDA
		public static final String INCLUIR_AJUDA = "Não foi possível incluir Ajuda.";
		public static final String ALTERAR_AJUDA = "Não foi possível alterar Ajuda.";
		public static final String CONSULTAR_AJUDA = "Não foi possível consultar Ajuda.";
		// AJUDA - Validação
		public static final String VALIDAR_PARAMETRO_MODULO_AJUDA = "O módulo dever ser selecionado.";
		public static final String VALIDAR_PARAMETRO_SUBMODULO_AJUDA = "O submódulo deve ser selecionado.";
		public static final String VALIDAR_PARAMETRO_FUNCIONALIDADE_AJUDA = "A funcionalidade deve ser selecionado.";
		public static final String VALIDAR_PARAMETRO_TELA_AJUDA = "A Tela deve ser selecionada.";
		public static final String VALIDAR_PARAMETRO_AJUDA_TELA_AJUDA = "A ajuda para a tela deve ser preenchida.";
		public static final String VALIDAR_PARAMETRO_TITULO_JANELA_AJUDA = "O titulo da tela deve ser informado.";
		public static final String VALIDAR_PARAMETRO_ROTULO_AJUDA = 
			"O rótulo para preenchimento da ajuda deve ser selecionado.";
		public static final String VALIDAR_PARAMETRO_AJUDA_PARA_ROTULO_AJUDA = "Informar descrição da ajuda para o rótulo.";
		public static final String VALIDAR_PARAMETRO_AJUDA_PARA_ROTULO_CONFIRMADO = 
			"A ajuda para o rótulo deve ser confirmada.";
		public static final String VALIDAR_PARAMETRO_DESCRICAO_AJUDA_PARA_ROTULO_AJUDA = 
			"A ajuda para o rótulo selecionado deve ser preenchida.";
		//Ajuda - CampoAjuda
		public static final String INCLUIR_CAMPO_AJUDA = "Não foi possível incluir Campo Ajuda.";
		public static final String ALTERAR_CAMPO_AJUDA = "Não foi possível alterar Campo Ajuda.";
		public static final String LISTAR_CAMPO_AJUDA = "Não foi possível listar Campo Ajuda.";
		public static final String CONSULTAR_CAMPO_AJUDA = "Não foi possível consultar Campo Ajuda.";
		public static final String EXCLUIR_CAMPO_AJUDA = "Deseja realmente excluir a ajuda?";
		//Ajuda - Funcionalidade
		public static final String INCLUIR_FUNCIONALIDADE = "Não foi possível incluir Funcionalidade.";
		public static final String ALTERAR_FUNCIONALIDADE = "Não foi possível alterar Funcionalidade.";
		public static final String LISTAR_FUNCIONALIDADE = "Não foi possível listar Funcionalidade.";
		public static final String CONSULTAR_FUNCIONALIDADE = "Não foi possível consultar Funcionalidade.";
		//Ajuda - TelaAjuda
		public static final String INCLUIR_TELA_AJUDA = "Não foi possível incluir Tela Ajuda.";
		public static final String ALTERAR_TELA_AJUDA = "Não foi possível alterar Tela Ajuda.";
		public static final String LISTAR_TELA_AJUDA = "Não foi possível listar Tela Ajuda.";
		public static final String CONSULTAR_TELA_AJUDA = "Não foi possível consultar Tela  Ajuda.";
		//Ajuda - TelaCampoAjuda
		public static final String INCLUIR_TELA_CAMPO_AJUDA = "Não foi possível incluir Tela Campo Ajuda.";
		public static final String ALTERAR_TELA_CAMPO_AJUDA = "Não foi possível alterar Tela Campo Ajuda.";
		public static final String LISTAR_TELA_CAMPO_AJUDA = "Não foi possível listar Tela Campo Ajuda.";
		public static final String CONSULTAR_TELA_CAMPO_AJUDA = "Não foi possível consultar Tela  Campo Ajuda.";
		public static final String EXCLUIR_TELA_CAMPO_AJUDA = "Não foi possível excluir Tela  Campo Ajuda.";
		public static final String NAO_INFORMADO_AJUDA_CAMPOS_TELA = "Não foi informado ajuda para os campos da tela.";
		//Ajuda - TelaFuncionalidade
		public static final String INCLUIR_TELA_FUNCIONALIDADE = "Não foi possível incluir Tela Funcionalidade.";
		public static final String ALTERAR_TELA_FUNCIONALIDADE = "Não foi possível alterar Tela Funcionalidade.";
		public static final String LISTAR_TELA_FUNCIONALIDADE = "Não foi possível listar Tela Funcionalidade.";
		public static final String CONSULTAR_TELA_FUNCIONALIDADE = "Não foi possível consultar Tela Funcionalidade.";
		//Ajuda - ModuloAjuda
		public static final String INCLUIR_MODULO_AJUDA = "Não foi possível incluir Modulo Ajuda.";
		public static final String ALTERAR_MODULO_AJUDA = "Não foi possível alterar Modulo Ajuda.";
		public static final String LISTAR_MODULO_AJUDA = "Não foi possível listar Modulo Ajuda.";
		public static final String CONSULTAR_MODULO_AJUDA = "Não foi possível consultar Modulo Ajuda.";
		public static final String CONSULTAR_SUBMODULO_AJUDA = "Não foi possível consultar Submodulo Ajuda.";
		public static final String LISTAR_SUBMODULO_AJUDA = "Não foi possível listar Submodulo Ajuda.";
		//  GIA ITCD
		public static final String INCLUIR_GIA_ITCD = "Não foi possível incluir a GIA ITCD";
		public static final String VALIDAR_GIA_ITCD_NAO_PERTENCE_AO_CONTRIBUINTE = 
			"Essa GIA-ITCD não pertence ao contribuinte informado.";
		public static final String ALTERAR_GIA_ITCD = "Não foi possível alterar a  GIA ITCD";
		public static final String CONSULTAR_GIA_ITCD = "Não foi possível consultar a GIA ITCD";
		public static final String VALIDAR_PARAMETRO_CODIGO_GIA = "O Número da GIA-ITCD deve ser informado.";
		public static final String LISTAR_GIA_ITCD = "Numero da GIA-ITCD não existe.";
		public static final String VALIDAR_SENHA_GIA = "Favor informar a senha.";
		public static final String VALIDAR_CONFIRMACAO_SENHA = "Favor  confirmar a senha.";
		public static final String VALIDAR_SENHA_INFORMADA_INVALIDA = "Senha não confere.";
		public static final String VALIDAR_ITCD_RESPONSAVEL_NAO_INFORMADO = "Contribuinte invalido ou Não informado.";
		public static final String VALIDAR_ITCD_NAT_OPERACAO_NAO_INFORMADA = 
			"Natureza da operação inválida ou não informada.";
		public static final String VALIDAR_ITCD_TIPO_GIA_NAO_INFORMADO = "O Tipo da gia inválido ou não informado.";
		public static final String VALIDAR_ITCD_SENHA_NA0_INFORMADA = "Senha inválida ou não informada.";
		public static final String VALIDAR_ITCD_NUMR_PROTOCOLO = "O Número do Protocolo é inválido ou não foi informado.";
		public static final String VALIDAR_ITCD_DATA_PROTOCOLO = "A data do Protocolo é inválida ou não foi informada.";
		public static final String VALIDAR_GIA_ITCD_NAO_CONFIRMADA = "Esta GIA-ITCD não está confirmada.";
		// GIA ITCD TEMPORARIO
		public static final String INCLUIR_GIA_ITCD_TEMPORARIO = "Não foi possível incluir a GIA ITCD Temporário.";
		public static final String ALTERAR_GIA_ITCD_TEMPORARIO = "Não foi possível alterar a GIA ITCD Temporário.";
		public static final String CONSULTAR_GIA_ITCD_TEMPORARIO = "Não foi possível consultar a GIA ITCD Temporário.";
		public static final String DELETAR_GIA_ITCD_TEMPORARIO = "Não foi possível consultar a GIA ITCD Temporário";
		//GIA Generico
		public static final String VALIDAR_GIA_ITCD_CPF_MEEIRO_INVALIDO = "CPF do Meeiro é inválido.";
		public static final String VALIDAR_GIA_ITCD_CODIGO = "Favor informar o número da GIA-ITCD.";
		public static final String VALIDAR_GIA_ITCD_NAO_EXISTE = "Número da GIA-ITCD não existe.";
		public static final String VALIDAR_GIA_ITCD_SEM_DATA_PROTOCOLO = 
			"Não é possível fazer a avaliação da GIA-ITCD, a mesma não possui data de protocolo.";
		public static final String VALIDAR_GIA_ITCD_SEM_AVALIACAO = "GIA-ITCD com status que não pode ser avaliada.";
		public static final String VALIDAR_GIA_ITCD_INATIVAR_STATUS_DAR = 
			"Essa GIA-ITCD não pode ser Inativada  pois possui DAR quitado.";
		public static final String VALIDAR_NAO_EXISTE_CONTRIBUINTE = "Não existe Contribuinte com esses dados no Cadastro.";
		public static final String VALIDAR_GIA_ITCD_NAO_EXISTE_CONTRIBUINTE = 
			"Não existe Cadastro de GIA-ITCD para esse contribuinte";
		public static final String VALIDAR_GIA_ITCD_VALIDA_NATUREZA_OPERACAO = "Favor selecionar a natureza da operação.";
		public static final String VALIDAR_GIA_ITCD_VALIDA_CPF = "CPF deve ser informado.";
		public static final String VALIDAR_GIA_ITCD_VALIDA_CPF_DECLARANTE = "CPF do Declarante deve ser informado.";
		public static final String VALIDAR_GIA_ITCD_VALIDA_CPF_PROCURADOR = "CPF do Procurador deve ser informado.";
		public static final String VALIDAR_GIA_ITCD_VALIDA_CPF_BENEFICIARIO = "CPF do Benefíciario deve ser informado.";
		public static final String VALIDAR_CONSULTAR_GIA_ITCD_CODIGO = "Número da GIA_ITCD deve ser informado.";
		public static final String VALIDAR_CONSULTAR_GIA_ITCD_RESPONSAVEL = 
			"Essa GIA-ITCD não pertence ao contribuinte informado.";
		public static final String VALIDAR_CONSULTAR_GIA_ITCD_STATUS_INATIVO = "GIA-ITCD se encontra inativada.";
		public static final String VALIDAR_CONSULTAR_GIA_ITCD_ALTERAR_STATUS_GIA_ITCD_INATIVO = "GIA-ITCD Inativa, esse status da GIA-ITCD não pode ser alterado manualmente.";
		public static final String VALIDAR_CONSULTAR_GIA_ITCD_STATUS_NOTIFICADO = "Não foi gerada notificação para essa GIA-ITCD";
		public static final String VALIDAR_CONSULTAR_GIA_ITCD_STATUS_DIFERENTE_NOTIFICADO = "Não foi possível imprimir a Notificação. O Status atual da GIA-ITCD é diferente de Notificado.";
		public static final String VALIDAR_CONSULTAR_GIA_ITCD_STATUS_RETIFICADO = "Não foi gerada retificação para essa GIA-ITCD";
		public static final String VALIDAR_CONSULTAR_GIA_ITCD_STATUS_DIFERENTE_RETIFICADO = "Não foi possível imprimir a Retificação. O Status atual da GIA-ITCD é diferente de Retificado.";
		
		public static final String VALIDAR_CONSULTAR_GIA_ITCD_STATUS_NAO_INATIVO = "Essa GIA - ITCD não está Inativa.";
		public static final String VALIDAR_CONSULTAR_GIA_ITCD_STATUS_NAO_PODE_SER_REATIVADO = "GIA-ITCD não pode ser reativada.";
		public static final String VALIDAR_GIA_ITCD_VALIDA_MUNICIPIO_ARRECADACAO = 
			"O Município de Domicílio deve ser selecionado.";
		// GIA AVALIACAO
		public static final String VALIDAR_GIA_ITCD_AVALIACAO_DATA_JUDICIAL = 
			"Favor preencher o campo Data da avaliação judicial.";
		public static final String VALIDAR_GIA_ITCD_AVALIACAO_VALOR_AVALIACAO = 
			"Favor preencher o campo valor da avaliação judicial.";
		public static final String VALIDAR_GIA_ITCD_AVALIACAO_VALOR_MAIOR_ZERO = 
			"O Valor da Avaliação deve ser maior que zero.";
		public static final String VALIDAR_GIA_ITCD_AVALIACAO_JUDICIAL_VALOR_MAIOR_ZERO = 
			"O Valor da Avaliação Judicial deve ser maior que zero.";
		public static final String VALIDAR_GIA_ITCD_AVALIACAO_VALOR = "Favor informar o valor da avaliação.";
		public static final String VALIDAR_GIA_ITCD_AVALIACAO_VALOR_MENOR = 
			"O Valor da Avaliação é menor que o valor declarado pelo contribuinte.";
		public static final String VALIDAR_GIA_ITCD_AVALIACAO_VALOR_JUDICIAL = "Favor informar o valor da avaliação judicial.";
		public static final String VALIDAR_GIA_ITCD_AVALIACAO_VALOR_MENOR_JUDICIAL = 
			"O Valor da Avaliação Judicial é menor que o valor declarado pelo contribuinte.";
			
		public static final String VALIDAR_GIA_ITCD_AVALIACAO_ALTERAR_DATA = "Favor preencher o campo Data da avaliação.";
		public static final String VALIDA_GIA_ITCD_AVALIACAO_DATA_MAIOR_ATUAL = "A data da avaliação não deve maior que a data atual.";
		public static final String VALIDA_GIA_ITCD_AVALIACAO_DATA_MAIOR_ATUAL_JUDICIAL = "A data da avaliação judicial não deve maior que a data atual.";
		
		public static final String VALIDA_GIA_ITCD_AVALIACAO_DATA_AVALIACAO_DATA_PROTOCOLO = "Data de avaliação deve ser maior que a data de protocolo da GIA-ITCD.";
		public static final String VALIDAR_GIA_ITCD_AVALIACAO_ALTERAR_DATA_JUDICIAL = 
			"Favor preencher o campo Data da avaliação judicial.";
		public static final String VALIDAR_GIA_ITCD_AVALIACAO_OBSERVACAO = "Favor informar a Observação.";
		public static final String VALIDAR_GIA_ITCD_AVALIACAO_PRAZO = 
			"Avaliação judicial ultrapassou o prazo previsto no ato normativo. Favor refazer a avaliação.";
		public static final String VALIDAR_GIA_ITCD_AVALIACAO_SERVIDOR = "Favor informar a matrícula do servidor.";
		public static final String VALIDAR_GIA_ITCD_AVALIACAO_MATRICULA = "Matrícula inválida.";
		public static final String VALIDAR_GIA_ITCD_AVALIACAO_SERVIDOR_OBRIGATORIO = "Servidor deve ser informado.";
		public static final String VALIDAR_GIA_ITCD_AVALIACAO_IMOVEIS_NAO_AVALIADOS = 
			"Existem imóveis declarados que ainda não foram avaliados.";
		public static final String VALIDAR_GIA_ITCD_AVALIACAO_STATUS_PROTOCOLADO = "A GIA precisa estar com status igual a Protocolada para ser Avaliada.";
		public static final String VALIDAR_GIA_ITCD_AVALIACAO_STATUS_AVALIADO = "A GIA precisa estar com status igual a Avaliada para ser re-avaliada.";   
		public static final String VALIDAR_GIA_ITCD_AVALIACAO_AGENCIA = "Agencia Sefaz de Avaliação deve ser informada.";
		public static final String VALIDAR_GIA_ITCD_AVALIACAO_BEM_NAO_ISENTO = "Bem não declarado como isento.";
		public static final String VALIDAR_GIA_ITCD_AVALIACAO_ALTERAR = "Não é permitido alterar essa avaliação, pois Status da GIA-ITCD é diferente de Avaliado.";
		public static final String VALIDAR_GIA_ITCD_AVALIACAO_NAO_AVALIADO = 
			"Não é permitido gerar documento para essa avaliação, pois Status da GIA-ITCD é diferente de Avaliado.";
		public static final String VALIDAR_GIA_ITCD_SERVIDOR_AVALIADOR_GIA_ITCD = "Servidor não tem acesso à impressão de documentos da GIA-ITCD informada.";
		public static final String VALIDAR_GIA_ITCD_AVALIACAO_NAO_ALTERAR = "Essa Avaliação não pode ser alterada";
		public static final String VALIDAR_GIA_ITCD_INCLUIR_AVALIACAO_GIA_AVALIADA = "GIA-ITCD já possui avaliação.";
		public static final String VALIDAR_GIA_ITCD_AVALIACAO_EXCLUIR_SERVIDOR = 
			"Deseja realmente excluir o servidor selecionado?";
		public static final String VALIDAR_GIA_ITCD_JA_FOI_AVALIADA = "Essa GIA-ITCD já foi avaliada";
		
		//GIA ITCD Inventario Arrolamento - Validação   
		public static final String INCLUIR_GIA_ITCD_INVENTARIO_ARROLAMENTO = 
			"Não foi possível incluir GIA ITCD Inventario e Arrolamento";
		public static final String ALTERAR_GIA_ITCD_INVENTARIO_ARROLAMENTO = 
			"Não foi possível alterar GIA ITCD Inventario e Arrolamento";
		public static final String DELETAR_GIA_ITCD_INVENTARIO_ARROLAMENTPO = 
			"Não foi possível deletar GIA ITCD Inventário e Arrolamento.";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_CPF = "CPF deve ser informado.";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_CPF_INVENTARIANTE = 
			"CPF do Inventariante deve ser informado.";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_CPF_DE_CUJUS = 
			"CPF do de Cujus deve ser informado.";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_CPF_DE_CUJUS_DOCUMENTO = 
			"Favor informar  o número do documento do de Cujus.";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_ESTADO_CIVIL = 
			"Favor informar o estado civil do de Cujus.";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_REGIME_CASAMENTO = 
			"Favor informar o regime de casamento.";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_CPF_MEEIRO_BRANCO = 
			"Cônjuge sobrevivente deve ser informado.";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_NUMERO_PROCESSO = 
			"Favor digitar o número do processo de Inventário / Arrolamento.";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_NUMERO_PROCESSO_MAIOR_ZERO = 
			"Número do processo deve ser maior que zero.";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_DATA_INVENTARIO = 
			"Favor informar a data do inventário.";
      public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_DATA_JUIZO_COMARCA = 
      "O campo Juízo/Comarca é obrigatório.";
      public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_TIPO_PROCESSO = 
      "Favor selecionar o tipo de processo.";
      public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_TIPO_PROCESSO_SELECIONADO = 
      "tipo Processo Inventário inválido.";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_DATA_INVENTARIO_MENO_DATA_ATUAL = 
			 "Data de Inventário não pode ser maior que a data atual.";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_DATA_INVENTARIO_MAIOR_DATA_FALECIMENTO = 
			"Data do Inventário deve ser maior que a data do falecimento.";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_UF_ABERTURA = 
			"Favor informar a UF de abertura do Inventário/Arrolamento.";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_DATA_FALECIMENTO = 
			"Favor informar a data de falecimento.";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_DATA_FALECIMENTO_OBRIGATORIO = 
			"A data do falecimento é obrigatória."; //usada somente no Be
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_DATA_FALECIMENTO_MENOR_DATA_INVENTARIO = 
			"A data do falecimento não pode ser maior que a data do inventário .";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_NUMERO_HERDEIROS = 
			"Favor digitar a quantidade de herdeiros.";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_NUMERO_HERDEIROS_MAIOR_ZERO = 
			"Quantidade de herdeiros deve ser maior que zero.";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_NUMERO_HERDEIROS_DIFERENTE_BENEFICIARIOS = 
			"A quantidade de herdeiros declarados na aba Dados Gerais está diferente com a quantidade de herdeiros declarados na aba Beneficiários.";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_CPF_RESPONSAVEL = 
			"CPF deve ser informado.";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_NATUREZA_OPERACAO = 
			"Favor selecionar a natureza da operação.";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_SENHA = "Favor informar a senha.";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_COMPARA_CPF_INVENTARIANTE_DE_CUJUS = 
			"O CPF do Inventariante não pode ser igual ao CPF do De Cujus.";
		public static final String VALIDAR_GIA_ITCD_SEPARACAO_DIVORCIO_COMPARA_CPF_DECLARANTE_CONJUGE2 = "CPF do Declarante não pode ser igual ao conjuge2";
		public static final String VALIDAR_GIA_ITCD_SEPARACAO_DIVORCIO_COMPARA_CPF_PROCURADOR_CONJUGE2 = "CPF do Procurador não pode ser igual ao conjuge2";
		public static final String VALIDAR_GIA_ITCD_SEPARACAO_DIVORCIO_COMPARA_CPF_PROCURADOR_DECLARANTE = "CPF do Procurador não pode ser igual ao declarante"; 
		public static final String VALIDAR_GIA_ITCD_SEPARACAO_DIVORCIO_COMPARA_CPF_CONJUGE_2_CONJUGE_1 = "CPF do Cônjuge 2 não pode ser igual ao cônjuge 1";
		public static final String VALIDAR_GIA_ITCD_SEPARACAO_DIVORCIO_COMPARA_CPF_CONJUGE_2_PROCURADOR = "CPF do Cônjuge 2 não pode ser igual ao procurador.";   
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_COMPARA_CPF_DE_CUJUS_INVENTARIANTE_MEEIRO = 
			"CPF informado não pode ser igual ao CPF do Inventariante e não pode ser  Igual ao CPF do Meeiro.";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_COMPARA_CPF_INVENTARIANTE_PROCURADOR = 
			"O CPF do Inventariante não pode ser igual ao CPF do procurador.";
		public static final String VALIDAR_GIA_ITCD_SEPARACAO_DIVORCIO_COMPARA_CPF_DECLARANTE_PROCURADOR = 
			"O CPF do Declarante não pode ser igual ao CPF do procurador.";      
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_COMPARA_CPF_MEEIRO_DE_CUJUS = 
			"CPF do Cônjuge sobrevivente não pode ser igual ao do De Cujus.";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_COMPARA_CPF_RESPONSAVEL = 
			"CPF do responsável deve ser igual ao CPF do criador da GIA.";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_CPF_RESPONSAVEL_CUJUS = 
			"O CPF do Inventariante não pode ser igual ao CPF do De Cujus.";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_CPF_CUJUS_RESPONSAVEL_MEEIRO = 
			"CPF informado não pode ser igual ao CPF do Inventariante, do Procurador e não pode ser igual ao CPF do Meeiro.";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_CPF_MEEIRO_CUJUS = 
			"CPF do Meeiro não pode ser igual ao CPF do De Cujus.";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_CPF_PROCURADOR_INVENTARIANTE = 
			"CPF do Procurador não pode ser igual ao CPF do Inventariante.";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_CPF_PROCURADOR_CUJUS = 
			"CPF do Procurador não pode ser igual ao CPF do De Cujus.";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_CPF_CUJUS_RESPONSAVEL = 
			"O CPF do De cujus  não pode ser igual ao CPF do Inventariante.";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_CPF_CUJUS_MEEIRO = 
			"O CPF do De Cujus não pode ser igual ao CPF do meeiro.";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_CPF_CUJUS_PROCURADOR = 
			"O CPF do De cujus não pode ser igual ao CPF do procurador.";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_CPF_BENEFICIARIO_CUJUS = "CPF do beneficiário não pode ser igual ao do de Cujus.";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_CPF_BENEFICIARIO_MEEIRO_CASADO_COMUNHAO_PARCIAL = "CPF cadastrado como cônjuge sobrevivente e não pode ser beneficiário na comunhão parcial de bens.";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_CPF_BENEFICIARIO_MEEIRO_CASADO_COMUNHAO_UNIVERSAL = "CPF cadastrado como cônjuge sobrevivente e não pode ser beneficiário na comunhão universal de bens.";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_CPF_BENEFICIARIO_MEEIRO_CASADO_SEPARACAO_TOTAL = "CPF cadastrado como cônjuge sobrevivente e não pode ser beneficiário na separação total de bens.";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_REGISTRO = 
			"GIA ITCD Inventario Arrolamento não encontrado.";
		public static final String INCLUIR_GIA_ITCD_INVENTARIO_ARROLAMENTO_BENEFICIARIO = 
			"Não foi possível incluir a GIA ITCD Inventário/Arrolamento Beneficiário.";
		public static final String ALTERAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_BENEFICIARIO = 
			"Não foi possível alterar a GIA ITCD Inventário/Arrolamento Beneficiário.";
		public static final String CONSULTAR_GIA_ITCD_INVENTARIO_ARROMANENTO_BENEFICIARIO = 
			"Não foi possível consultar a GIA ITCD Inventario Arrolamento Beneficiário.";
		public static final String VALIDAR_GIA_ITCD_VALIDA_BEM_TIPO_DO_BEM = "Favor informar o tipo do bem";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_DATA_INVENTARIO_MAIOR_DATA_ATUAL = 
			"Data Inventário não pode ser maior que a data atual.";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_DATA_FALECIMENTO_MAIOR_DATA_ATUAL = 
			"Data Falecimento não pode ser maior que a data atual."; 
      public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_CPF_MEEIRO_BENEFICIARIO = "CPF cadastrado como Beneficiário não pode ser Cônjuge sobrevivente.";
      public static final String GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDAR_CLASSIFICACAO_BEM = "Favor informar a classificação do bem.";
      public static final String GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDAR_TIPO_BEM = "Favor informar o tipo do bem.";
      
		// BENEFICIARIO
		public static final String INCLUIR_BENEFICIARIO = "Não foi possível incluir Beneficiário.";
		public static final String ALTERAR_BENEFICIARIO = "Não foi possível alterar o Beneficiário.";
		public static final String BENEFICIARIO_NAO_INFORMADO = "Os beneficiários devem ser informados.";
		public static final String CONSULTAR_BENEFICIARIO = "Não foi possível consultar Beneficiário.";
		public static final String VALIDAR_BENEFICIARIO_PESSOA_NAO_INFORMADA = "Pessoa Beneficiária não informada ou inválida.";
		public static final String VALIDAR_BENEFICIARIO_VALOR_RECEBIDO = "Valor recebido do bem não informado ou inválido.";
		public static final String VALIDAR_BENEFICIARIO_CODIGO_NAO_INFORMADO = "Codigo do beneficiario Não informado ou inválido.";
		public static final String VALIDAR_BENEFICIARIO_CPF_DUPLICADO = "Esse Beneficiário já foi cadastrado.";
		public static final String VALIDAR_BENEFICIARIO_VALOR_PERCENTUAL_RECEBIDO = "Favor informar o Percentual Transmitido ao beneficiário.";
		public static final String VALIDAR_BENEFICIARIO_VALOR_PERCENTUAL_RECEBIDO_ZERO = "Percentual Transmitido não pode ser menor ou igual à zero.";
		public static final String VALIDAR_BENEFICIARIO_VALOR_PERCENTUAL_RECEBIDO_MAIOR = "Percentual transmitido não pode ser maior que 100%.";
		public static final String GIA_ITCD_DOACAO_VALIDAR_CPF_BENEFICIARIO_CPF_DECLARANTE = "CPF do beneficiário não pode ser igual ao CPF do declarante";
		//FichaImovelUrbanoDAO
		public static final String VALIDAR_IMOVEL_URBANO_PARAMETRO_CODIGO = "Código Imóvel Urbano não informado.";
		public static final String VALIDAR_IMOVEL_URBANO_PARAMETRO_CODIGO_ENDERECO = "Código Endereço não informado.";
		public static final String VALIDAR_IMOVEL_URBANO_PARAMETRO_CODIGO_CONSTRUCAO = "Código Endereço não informado.";
		public static final String VALIDAR_IMOVEL_URBANO_PARAMETRO_CODIGO_BEM_TRBT = "Código Bem Tributavel não informado.";
		public static final String VALIDAR_IMOVEL_URBANO_PARAMETRO_CODIGO_BEM = "Código Bem não informado.";
		public static final String VALIDAR_IMOVEL_URBANO_PARAMETRO_ESTADO_CONSERVACAO = 
			"Estado de conservação não informado.";
		public static final String VALIDAR_IMOVEL_URBANO_PARAMETRO_QUANTIDADE_AREA_TOTAL = "Área total não informada.";
		public static final String VALIDAR_IMOVEL_URBANO_PARAMETRO_QUANTIDADE_AREA_CONSTRUIDA = 
			"Área construida não informada.";
		public static final String VALIDAR_IMOVEL_URBANO_PARAMETRO_TIPO_ACESSO = "Tipo acesso não informado.";
		public static final String VALIDAR_IMOVEL_URBANO_PARAMETRO_VALOR_MERCADO_TOTAL = 
			"Valor mercado total não informado.";
		public static final String VALIDAR_IMOVEL_URBANO_PARAMETRO_VALOR_VENAL_IPTU = "Valor venal IPTU não informado.";
		public static final String INCLUIR_IMOVEL_URBANO = "Não foi possível incluir a Ficha de Imóvel Urbano.";
		public static final String VALIDAR_IMOVEL_URBANO_PARAMETRO_TIPO_LOGRADOURO = 
			"Favor selecionar o tipo de logradouro.";
		public static final String VALIDAR_IMOVEL_URBANO_PARAMETRO_LOGRADOURO = "Favor informar o logradouro.";
		public static final String VALIDAR_IMOVEL_URBANO_PARAMETRO_NUMERO_LOGRADOURO = 
			"Favor informar o número do logradouro.";
		public static final String VALIDAR_IMOVEL_URBANO_PARAMETRO_BAIRRO = "Favor informar o bairro.";
		public static final String VALIDAR_IMOVEL_URBANO_PARAMETRO_CEP_NAO_INFORMADO = "Favor informar o CEP.";
		public static final String VALIDAR_IMOVEL_URBANO_PARAMETRO_CEP_INVALIDO = "CEP Inválido.";
		public static final String VALIDAR_IMOVEL_URBANO_PARAMETRO_CEP_NAO_CADASTRADO = "CEP não cadastrado.";
		public static final String VALIDAR_IMOVEL_URBANO_PARAMETRO_CEP_NAO_PERMITIDO = 
		"O CEP informado não pertence a um Município do Estado de Mato Grosso, só podem ser declarados imóveis pertencentes ao Estado de Mato Grosso."; 
		public static final String VALIDAR_IMOVEL_URBANO_PARAMETRO_TIPO_IMOVEL = "Favor selecionar um tipo de imóvel.";
		public static final String VALIDAR_IMOVEL_URBANO_PARAMETRO_INFORMAR_ESTADO_CONSERVACAO = 
			"Favor selecionar o estado de conservação.";
		public static final String VALIDAR_IMOVEL_URBANO_PARAMETRO_AREA_TOTAL = "Favor informar a área total.";
		public static final String VALIDAR_IMOVEL_URBANO_PARAMETRO_AREA_CONSTRUIDA = "Favor informar a área construída.";
		public static final String VALIDAR_IMOVEL_URBANO_PARAMETRO_TIPO_CONSTRUCAO = "Favor informar o tipo de construção.";
		public static final String VALIDAR_IMOVEL_URBANO_PARAMETRO_INFORMAR_TIPO_ACESSO = 
			"Favor informar o tipo de acesso.";
		public static final String VALIDAR_IMOVEL_URBANO_PARAMETRO_VALOR_MERCADO = 
			"Favor informar o valor de mercado do imóvel.";
		public static final String VALIDAR_IMOVEL_URBANO_PARAMETRO_VALOR_VENAL = 
			"Favor informar o valor do IPTU do imóvel.";
		public static final String VALIDAR_IMOVEL_URBANO_AREA_CONSTRUIDA_MAIOR_TOTAL = 
			"Área construída não pode ser maior que área total.";
		public static final String NAO_EXISTE_UF_COM_SIGLA_MT = "Não existe UF com sigla MT. Entre em contato com o analista responsável.";
      public static final String VALIDAR_NUMERO_INSCRICAO_IMOBILIARIA = "Favor informar o número Inscrição Imobiliária do IPTU.";
		//FichaImovelUrbanoBenfeitoriaDAO
		public static final String VALIDAR_IMOVEL_URBANO_BENFEITORIA_PARAMETRO_CODIGO = 
			"Código do imovel urbano benfeitoria não informado.";
		public static final String VALIDAR_IMOVEL_URBANO_BENFEITORIA_PARAMETRO_CODIGO_IMOVEL = 
			"Código do imovel urbano não informado.";
		public static final String VALIDAR_IMOVEL_URBANO_BENFEITORIA_PARAMETRO_CODIGO_BENFEITORIA = 
			"Código da benfeitoria não informado.";
		public static final String INCLUIR_IMOVEL_URBANO_BENFEITORIA = 
			"Não foi possível incluir a Benfeitoria do Imóvel Urbano.";
		public static final String LISTAR_IMOVEL_URBANO = "Não foi possível listar os Imóveis Urbano.";
		public static final String CONSULTAR_IMOVEL_URBANO = "Não foi possível listar os Imóveis Urbano.";
		public static final String CONSULTAR_FICHA_IMOVEL_URBANO = "Impossível consultar a ficha de imovel urbano";
		public static final String LISTAR_IMOVEL_URBANO_BENFEITORIA = 
			"Não foi possível listar os Imóveis Urbano Benfeitoria.";
		public static final String VALIDAR_FICHA_IMOVEL_URBANO_BENFEITORIA_DESCRICAO = 
			"A descrição da Benfeitoria deve ser selecionada.";
		public static final String VALIDAR_FICHA_IMOVEL_URBANO_BENFEITORIA_DESCRICAO_EXISTENTE = 
			"Descrição da Benfeitoria já cadastrada para essa Benfeitoria selecionada para essa ficha de imóvel Urbano.";
		public static final String VALIDAR_FICHA_IMOVEL_URBANO_BENFEITORIA_DESCRICAO_VAZIA_EXISTENTE = 
			"Não é permitido cadastrar a mesma benfeitoria mais de uma vez sem descrição.";       
      public static final String VALIDAR_FICHA_IMOVEL_URBANO_PERCENTUAL_TRANSMITIDO_NAO_INFORMADO = "Favor informar o percentual transmitido do imóvel.";   
		public static final String VALIDAR_FICHA_IMOVEL_URBANO_PERCENTUAL_TRANSMITIDO_INVALIDO = "Percentual informado inválido.";   	
		// FICHA IMOVEL RURAL 
		public static final String INCLUIR_FICHA_IMOVEL_RURAL = "Não foi possível incluir a Ficha Imóvel Rural.";
		public static final String INCLUIR_FICHA_IMOVEL_RURAL_CONSTRUCAO = 
			"Não foi possível incluir a Ficha Imóvel Rural - Construção.";
		public static final String INCLUIR_FICHA_IMOVEL_RURAL_BENFEITORIA = 
			"A Benfeitoria deve ser selecionada.";
		public static final String INCLUIR_FICHA_IMOVEL_RURAL_CULTURA = 
			"Não foi possível incluir a Ficha Imóvel Rural - Cultura.";
		public static final String INCLUIR_FICHA_IMOVEL_RURAL_REBANHO = 
			"Não foi possível incluir a Ficha Imóvel Rural - Rebanho.";
		public static final String ALTERAR_FICHA_IMOVEL_RURAL = "Não foi possível alterar a Ficha Imóvel Rural.";
		public static final String ALTERAR_FICHA_IMOVEL_RURAL_CONSTRUCAO = 
			"Não foi possível alterar a Ficha Imóvel Rural - Construção.";
		public static final String ALTERAR_FICHA_IMOVEL_RURAL_BENFEITORIA = 
			"Não foi possível alterar a Ficha Imóvel Rural - Benfeitoria.";
		public static final String ALTERAR_FICHA_IMOVEL_RURAL_CULTURA = 
			"Não foi possível alterar a Ficha Imóvel Rural - Cultura.";
		public static final String ALTERAR_FICHA_IMOVEL_RURAL_REBANHO = 
			"Não foi possível alterar a Ficha Imóvel Rural - Rebanho.";
		public static final String DELETAR_FICHA_IMOVEL_RURAL = "Não foi possível deletar a Ficha Imóvel Rural.";
		public static final String DELETAR_FICHA_IMOVEL_RURAL_CONSTRUCAO = 
			"Não foi possível deletar a Ficha Imóvel Rural - Construção.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_CULTURA_DESCRICAO_EXISTENTE = 
			"Descrição da Cultura já cadastrada para essa Cultura.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_CULTURA_DESCRICAO_VAZIA_EXISTENTE = 
			"Não é permitido cadastrar a mesma Cultura mais de uma vez, sem descrição.";        
		public static final String DELETAR_FICHA_IMOVEL_RURAL_BENFEITORIA = 
			"Não foi possível deletar a Ficha Imóvel Rural - Benfeitoria.";
		public static final String DELETAR_FICHA_IMOVEL_RURAL_CULTURA = 
			"Não foi possível deletar a Ficha Imóvel Rural - Cultura.";
		public static final String DELETAR_FICHA_IMOVEL_RURAL_REBANHO = 
			"Não foi possível deletar a Ficha Imóvel Rural - Rebanho.";
		public static final String CONSULTAR_FICHA_IMOVEL_RURAL = "Não foi possível consultar a Ficha Imóvel Rural.";
		public static final String CONSULTAR_FICHA_IMOVEL_RURAL_CONSTRUCAO = 
			"Não foi possível consultar a Ficha Imóvel Rural - Construção.";
		public static final String CONSULTAR_FICHA_IMOVEL_RURAL_BENFEITORIA = 
			"Não foi possível consultar a Ficha Imóvel Rural - Benfeitoria.";
		public static final String CONSULTAR_FICHA_IMOVEL_RURAL_CULTURA = 
			"Não foi possível consultar a Ficha Imóvel Rural - Cultura.";
		public static final String CONSULTAR_FICHA_IMOVEL_RURAL_REBANHO = 
			"Não foi possível consultar a Ficha Imóvel Rural - Rebanho.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_CULTURA_VALOR_MERCADO = 
			"Valor de mercado deve ser informado.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_CULTURA_VALOR_MERCADO_ZERO = 
			"Valor de mercado deve ser maior que zero.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_CULTURA_VALOR_HECTARE = "Valor do Hectare deve ser informado.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_CULTURA_VALOR_HECTARE_ZERO = "Valor do Hectare deve ser maior que Zero.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_CULTURA_AREA_CULTIVADA = 
			"A área cultivada deve ser informada.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_CULTURA_AREA_CULTIVADA_MAIOR_AREA_TOTAL = 
			"A área cultivada não pode ser maior que a área total do imóvel.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_CULTURA_AREA_CULTIVADA_ZERO = 
			"A área cultivada deve ser maior que zero.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_DENOMINACAO_VALIDA = 
			"Favor informar a denominação do imóvel.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_TIPO_LOGRADOURO_VALIDO = 
			"Favor informar o tipo de logradouro.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_LOGRADOURO_VALIDO = "Favor informar o endereço do imóvel.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_PONTO_REFERENCIA_VALIDO = 
			"Favor informar o ponto de referência.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_CEP = "Favor informar o CEP.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_CEP_NAO_CADASTRADO = "CEP não cadastrado.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_CEP_VALIDO = "CEP Inválido.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_CEP_NAO_PERMITIDO = 
		"O CEP informado não pertence a um Município do Estado de Mato Grosso, só podem ser declarados imóveis pertencentes ao Estado de Mato Grosso.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_QUANTIDADE_DISTANCIA = 
			"Favor informar distância em quilômetros do perímetro urbano.";
   public static final String VALIDAR_FICHA_IMOVEL_RURAL_QUANTIDADE_DISTANCIA_ASFALTO = 
      "Favor informar distância em quilômetros até a rodovia pavimentada.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_QUANTIDADE_DISTANCIA_ZERO = "A distância em quilômetros do perímetro urbano deve ser maior que zero.";			
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_AREA_TOTAL = "Favor informar a área total do imóvel.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_AREA_TOTAL_ZERO = 
			"Área Total do Imóvel deve ser maior que zero.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_AREA_TOTAL_MENOR_SOMA_AREAS = 
			"A área total do imóvel não pode ser menor que a soma da área cultivada e área das pastagens naturais.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_AREA_PASTAGENS = "Favor informar o tamanho das pastagens.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_AREA_PASTAGENS_ZERO = 
			"Tamanho das pastagens deve ser maior que zero.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_AREA_PASTAGENS_MAIOR_AREA_TOTAL = 
			"A área das pastagens não pode ser maior que a área total do imóvel.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_VALOR_PASTAGENS = "Favor informar o valor das pastagens.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_VALOR_PASTAGENS_MAIOR_ZERO = 
			"O valor das pastagens deve ser maior que zero.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_VALOR_DA_ACESSOES_NATURAIS_MAIOR_ZERO = 
			"O valor das acessões naturais deve ser maior que zero.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_BENFEITORIA_DESCRICAO = 
			"A descrição da Benfeitoria deve ser selecionada.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_BENFEITORIA_DESCRICAO_EXISTENTE = 
			"Descrição da Benfeitoria já cadastrada para esse tipo de benfeitoria.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_BENFEITORIA_DESCRICAO_VAZIA_EXISTENTE = 
			"Não é permitido cadastrar a mesma benfeitoria mais de uma vez, sem descrição.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_BENFEITORIA_TAMANHO_MAXIMO_DESCRICAO = 
			"A descrição da benfeitoria deve conter no máximo "+ConfiguracaoITCD.TAMANHO_MAXIMO_DESCRICAO_COMPLEMENTAR_BENFEITORIA + " caracteres. Por favor verifique a descrição informada.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_BENFEITORIA_DESEJA_EXCLUIR = 
			"Deseja excluir essa benfeitoria.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_CONSTRUCAO_DESCRICAO_EXISTENTE = 
			"Descrição de construção já cadastrada para essa contrução.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_CONSTRUCAO_DESCRICAO_VAZIA_EXISTENTE = 
			"Não é permitido cadastrar a mesma construção mais de uma vez, sem descrição.";        
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_REBANHO_QUANTIDADE = 
			"A quantidade do Rebanho deve ser informada.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_REBANHO_VALOR_MERCADO = 
			"Valor de mercado deve ser informado.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_REBANHO_VALOR_MERCADO_ZERO = 
			"Valor de mercado deve ser maior que zero.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_REBANHO_QUANTIDADE_MAIOR_ZERO = 
			"A quantidade do Rebanho deve ser maior que zero.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_CONSTRUCAO_CONSERVACAO = 
			"O Estado de Conservação deve ser selecionado.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_REBANHO_DESCRICAO = 
			"A Descrição do Rebanho deve ser informada.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_REBANHO_DESCRICAO_EXISTENTE = 
			"Descrição de Rebanho já cadastrada para esse tipo de Rebanho.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_REBANHO_DESCRICAO_VAZIA_EXISTENTE = 
			"Não é permitido cadastrar o mesmo Rebanho mais de uma vez, sem descrição.";        
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_REBANHO_TIPO = "O tipo de Rebanho deve ser selecionado.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_CONSTRUCAO_VALOR_MERCADO = 
			"Valor de mercado deve ser informado.";
		public static final String CODIGO_FICHA_IMOVEL_RURAL_NECESSARIO_PARA_ALTERAR = "O código da ficha de imóvel é necessário para sua alteração.";
		public static final String CODIGO_FICHA_IMOVEL_RURAL_NECESSARIO_PARA_EXCLUIR = "O código da ficha de imóvel é necessário para sua exclusão.";   
		public static final String CODIGO_FICHA_IMOVEL_RURAL_CONSTRUCAO_NECESSARIO_PARA_ALTERAR = "O código da ficha de imóvel rural construção que é necessário para alterar não foi encontrado.";
		public static final String CODIGO_FICHA_IMOVEL_RURAL_CONSTRUCAO_NECESSARIO_PARA_EXCLUIR = "O código da ficha de imóvel rural construção que é necessário para excluir não foi encontrado.";  
		public static final String CODIGO_FICHA_IMOVEL_RURAL_BENFEITORIA_NECESSARIO_PARA_ALTERAR = "O código da ficha de imóvel rural benfeitoria que é necessário para alterar não foi encontrado.";
		public static final String CODIGO_FICHA_IMOVEL_RURAL_BENFEITORIA_NECESSARIO_PARA_EXCLUIR = "O código da ficha de imóvel rural benfeitoria que é necessário para excluir não foi encontrado.";   
		public static final String CODIGO_FICHA_IMOVEL_RURAL_CULTURA_NECESSARIO_PARA_ALTERAR = "O código da ficha de imóvel rural cultura que é necessário para alterar não foi encontrado.";
		public static final String CODIGO_FICHA_IMOVEL_RURAL_CULTURA_NECESSARIO_PARA_EXCLUIR = "O código da ficha de imóvel rural cultura que é necessário para excluir não foi encontrado.";  
		public static final String CODIGO_FICHA_IMOVEL_RURAL_REBANHO_NECESSARIO_PARA_ALTERAR = "O código da ficha de imóvel rural rebanho que é necessário para alterar não foi encontrado.";  
		public static final String CODIGO_FICHA_IMOVEL_RURAL_REBANHO_NECESSARIO_PARA_EXCLUIR = "O código da ficha de imóvel rural rebanho que é necessário para excluir não foi encontrado.";     
		public static final String CODIGO_FICHA_IMOVEL_URBANO_NECESSARIO_PARA_ALTERAR = "O código da ficha de imóvel é necessário para sua alteração."; 
		public static final String CODIGO_FICHA_IMOVEL_URBANO_NECESSARIO_PARA_EXCLUIR = "O código da ficha de imóvel é necessário para sua exclusão.";     
		public static final String CODIGO_FICHA_IMOVEL_URBANO_BENFEITORIA_NECESSARIO_PARA_ALTERAR = "O código da ficha de imóvel urbano benfeitoria que é necessário para alterar não foi encontrado."; 
		public static final String CODIGO_FICHA_IMOVEL_URBANO_BENFEITORIA_NECESSARIO_PARA_EXCLUIR = "O código da ficha de imóvel urbano benfeitoria que é necessário para excluir não foi encontrado.";    
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_CONSTRUCAO_VALOR_MERCADO_ZERO = 
			"Valor de mercado deve ser maior que zero.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_CONSTRUCAO_VALOR_MAQUINAS_IMPLEMENTOS_AGRICOLAS_ZERO = 
			"Valor de máquinas e implementos agrícolas deve ser maior que zero.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_CONSTRUCAO_VALOR_OUTROS_VALORES_ZERO = 
			"O campo Outros Valores deve ser maior que zero.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_VALOR_ACESSAO_NATURAL = 
			"Favor informar o valor das acessões naturais.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_VALOR_IMOVEL = "Favor informar o valor da terra Nua.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_VALOR_IMOVEL_ZERO = 
			"O valor da terra Nua deve ser maior que zero.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_VALOR_ITR = "Favor informar o valor venal do ITR.";
		public static final String VALIDAR_FICHA_IMOVEL_RURAL_VALOR_ITR_ZERO = 
			"O valor venal do ITR deve ser maior que zero.";
		public static final String EXCLUIR_IMOVEL_URBANO_BENFEITORIA = 
			"Não foi possível excluir a Benfeitoria do Imóvel Urbano.";
		public static final String EXCLUIR_IMOVEL_URBANO = "Não foi possível excluir o Imóvel Urbano.";
		public static final String ALTERAR_FICHA_IMOVEL_URBANO = "Não foi possível alterar a Ficha Imóvel Urbano.";
		public static final String ALTERAR_FICHA_IMOVEL_URBANO_BENFEITORIA = 
			"Não foi possível alterar a Ficha Imóvel Rural - Benfeitoria.";
		// FICHA OUTRO
		public static final String INCLUIR_FICHA_OUTRO = "Não foi possível incluir a Ficha Outro.";
		public static final String EXCLUIR_FICHA_OUTRO = "Não foi possível excluir a Ficha Outro.";
		public static final String CONSULTA_FICHA_OUTRO = "Não foi possível consultar a Ficha Outro.";
		public static final String LISTAR_FICHA_OUTRO = "Não foi possível listar a Ficha Outro.";
		// GIA ITCD Doacao
		public static final String INCLUIR_GIA_ITCD_DOACAO = "Não foi possível incluir a GIA ITCD Doação.";
		public static final String ALTERAR_GIA_ITCD_DOACAO = "Não foi possível alterar a GIA ITCD Doação.";
		public static final String DELETAR_GIA_ITCD_DOACAO = "Não foi possível deletar a GIA ITCD Doação.";
		public static final String CONSULTAR_GIA_ITCD_DOACAO = "Não foi possível consultar a GIA ITCD Doação.";
		public static final String LISTAR_GIA_ITCD_DOACAO = "Não foi possível listar a GIA ITCD Doação.";
		public static final String VALIDAR_GIA_ITCD_DOACAO_REGISTRO = "GIA ITCD Doação Não encontrado.";
		public static final String VALIDAR_GIA_ITCD_DOACAO_COMPARA_CPF_RESPONSAVEL = 
			"CPF do responsável deve ser igual ao CPF do criador da GIA Doação.";
		public static final String INCLUIR_GIA_ITCD_DOACAO_BENEFICIARIO = 
			"Não foi possível incluir a GIA ITCD Doação Beneficiário.";
		public static final String ALTERAR_GIA_ITCD_DOACAO_BENEFICIARIO = 
			"Não foi possível alterar a GIA ITCD Doação Beneficiário.";
		public static final String CONSULTAR_GIA_ITCD_DOACAO_BENEFICIARIO = 
			"Não foi possível consultar a GIA ITCD Doação Beneficiário.";
		public static final String GIA_ITCD_DOACAO_VALIDAR_TIPO_DOCUMENTO = "Favor informar o tipo de documento.";
		public static final String GIA_ITCD_DOACAO_VALIDAR_CPF_DECLARANTE_CPF_BENEFICIARIO = 
			"O CPF do Declarante não pode ser igual ao CPF do Beneficiário.";
	 public static final String GIA_ITCD_DOACAO_VALIDAR_CPF_BENEFICIARIO_CPF_PROCURADOR = 
		 "O CPF do Beneficiário não pode ser igual ao CPF do Procurador.";
			
	 public static final String GIA_ITCD_DOACAO_VALIDAR_CPF_PROCURADOR_CPF_BENEFICIARIO = 
		 "O CPF do Procurador não pode ser igual ao CPF do Beneficiário.";			
		public static final String GIA_ITCD_DOACAO_VALIDAR_CPF_PROCURADOR_CPF_DECLARANTE = 
			"O CPF do Procurador não pode ser igual ao CPF do Declarante.";
		public static final String GIA_ITCD_DOACAO_VALIDAR_CPF_DECLARANTE_CPF_PROCURADOR = 
			"O CPF do Declarante não pode ser igual ao CPF do Procurador.";
		public static final String GIA_ITCD_DOACAO_VALIDAR_NUMERO_DOCUMENTO = "Favor informar o número do documento.";
		public static final String GIA_ITCD_DOACAO_VALIDAR_NATUREZA_OPERACAO = "Favor selecionar a natureza da operação.";
		public static final String GIA_ITCD_DOACAO_VALIDAR_FRACAO_IDEAL = "Favor informar o percentual Doação/transmissão.";
		public static final String GIA_ITCD_DOACAO_VALIDAR_FRACAO_IDEAL_MENOR_ZERO = "Percentual Doação/transmissão deve ser maior que zero.";
		public static final String GIA_ITCD_DOACAO_VALIDAR_FRACAO_IDEAL_MENOR_CEM = "Percentual Doação/transmissão deve ser menor que 100%.";
		public static final String GIA_ITCD_DOACAO_VALIDAR_CLASSIFICACAO_BEM = "Favor informar a classificação do bem.";
		public static final String GIA_ITCD_DOACAO_VALIDAR_TIPO_BEM = "Favor informar o tipo do bem.";
		public static final String GIA_ITCD_DOACAO_VALIDAR_DESCRICAO_BEM = "Favor informar a descrição do bem.";
		public static final String GIA_ITCD_DOACAO_VALIDAR_DESCRICAO_BEM_EXISTENTE = 
			"Já existem Bem Tributável cadastrado com a mesma descrição para essa GIA-ITCD.";
		public static final String GIA_ITCD_DOACAO_VALIDAR_JA_EXISTE_BEM = "A descrição informada já existe.";
		public static final String GIA_ITCD_DOACAO_VALIDAR_TIPO_DOCUMENTO_BENEFICIARIO = 
			"Favor selecionar o tipo de documento.";
		public static final String GIA_ITCD_DOACAO_VALIDAR_DOCUMENTO_BENEFICIARIO = 
			"Favor informar o documento do Beneficiário.";
		public static final String GIA_ITCD_DOACAO_VALIDAR_CPF_JA_CADASTRADO = "Esse CPF já foi cadastrado.";
		public static final String GIA_ITCD_DOACAO_VALIDAR_CNPJ_JA_CADASTRADO = "Esse CNPJ já foi cadastrado.";
		public static final String GIA_ITCD_DOACAO_VALIDAR_PERCENTUAL_RECEBIDO = 
			"Percentual Atribuído não pode ser menor ou igual Zero.";
		public static final String GIA_ITCD_DOACAO_VALIDAR_PERCENTUAL_RECEBIDO_DIFERENTE_100 = 
			"Verificar a distribuição dos percentuais atribuídos, soma dos bens distribuídos diferente do valor do percentual da doação / transmissão.";
		public static final String GIA_ITCD_DOACAO_VALIDAR_PERCENTUAL_RECEBIDO_MAIOR_100 = 
			"Percentual atribuído não pode ser maior que 100%.";
		public static final String GIA_ITCD_DOACAO_VALIDAR_IMOVEL_RURAL = "Favor preencher a ficha do imóvel rural.";
		// GIA ITCD SEPARAÇÃO/DIVORCIO
		public static final String INCLUIR_GIA_ITCD_SEPARACAO_DIVORCIO = 
			"Não foi possível incluir a GIA ITCD Separação Divócio.";
		public static final String DELETAR_GIA_ITCD_SEPARACAO_DIVORCIO = 
			"Não foi possível deletar a GIA ITCD Separação Divórcio.";
		public static final String ALTERAR_GIA_ITCD_SEPARACAO_DIVORCIO = 
			"Não foi possível alterar a GIA ITCD Separação Divócio.";
		public static final String CONSULTAR_GIA_ITCD_SEPARACAO_DIVORCIO = 
			"Não foi possível consultar a GIA ITCD Separação Divócio.";
		public static final String LISTAR_GIA_ITCD_SEPARACAO_DIVORCIO = 
			"Não foi possível listar a GIA ITCD Separação Divócio.";
		public static final String VALIDAR_GIA_ITCD_SEPARACAO_DIVORCIO_REGISTRO = 
			"GIA ITCD Separação Divórcio não encontrado.";
		public static final String VALIDAR_GIA_ITCD_SEPARACAO_VALIDA_CPF = "CPF deve ser informado.";
		public static final String VALIDAR_GIA_ITCD_SEPARACAO_VALIDA_CONJUGE = "O Cônjuge deve ser informado.";
		public static final String VALIDAR_GIA_ITCD_SEPARACAO_VALIDA_CPF_DECLARANTE = 
			"O CPF do Declarante não pode ser igual ao Procurador.";
		public static final String VALIDAR_GIA_ITCD_SEPARACAO_VALIDA_CPF_DECLARANTE_CONJUGE2 = 
			"O CPF do Declarante não pode ser igual ao Conjuge 2.";
		public static final String VALIDAR_GIA_ITCD_SEPARACAO_VALIDA_CPF_CONJUGE = 
			"O CPF do Conjuge não pode ser igual ao CPF do Procurador.";
		public static final String VALIDAR_GIA_ITCD_SEPARACAO_VALIDA_CPF_PROCURADOR = 
			"O CPF do Procurador não pode ser igual ao Declarante.";
		public static final String VALIDAR_GIA_ITCD_SEPARACAO_VALIDA_CPF_PROCURADOR_CONJUGE2 = 
			"O CPF do Procurador não pode ser igual ao Conjuge 2.";
		public static final String VALIDAR_GIA_ITCD_SEPARACAO_VALIDA_CPF_CONJUGE2_CONJUGE1 = 
			"O CPF do Conjuge 2 não pode ser igual ao CPF do Conjuge 1.";
		public static final String VALIDAR_GIA_ITCD_SEPARACAO_VALIDA_CPF_CONJUGE1_PROCURADOR = 
			"O CPF do Conjuge 1 não pode ser igual ao CPF do Procurador.";
		public static final String VALIDAR_GIA_ITCD_SEPARACAO_VALIDA_CPF_CONJUGE2_PROCURADOR = 
			"O CPF do Conjuge 2 não pode ser igual ao CPF do Procurador.";
		public static final String VALIDAR_GIA_ITCD_SEPARACAO_VALIDA_CPF_CONJUGE_DECLARANTE_PROCURADOR = 
			"O CPF do Declarante não pode ser igual ao CPF Cônjuge2, e não pode ser igual ao CPF do Procurador";
		public static final String VALIDAR_GIA_ITCD_SEPARACAO_VALIDA_ESSE_CPF_JA_CADASTRADO = "Esse CPF já cadastrado.";
		public static final String VALIDAR_GIA_ITCD_SEPARACAO_DIVORCIO_COMPARA_CPF_RESPONSAVEL = 
			"CPF do responsável deve ser igual ao CPF do criador da GIA Separação Divórcio.";
		public static final String VALIDAR_GIA_ITCD_SEPARACAO_DIVORCIO_INFORMAR_PRIMEIRO_BEM_TRIBUTAVEL = 
			"Favor informar primeiro o bem tributável.";
		public static final String VALIDAR_GIA_ITCD_SEPARACAO_DIVORCIO_SELECIONAR_O_BEM_RECEBIDO = 
			"Favor selecionar o bem recebido.";
		public static final String VALIDAR_GIA_ITCD_SEPARACAO_DIVORCIO_DATA_SEPARACAO = 
			"Data da Separação deve ser informada.";
		public static final String VALIDAR_GIA_ITCD_INFORMAR_PRIMEIRO_BEM_TRIBUTAVEL = 
			"Favor informar primeiro o bem tributável.";
		//TODO UNIR as mensagens de Valida Número Processo
		public static final String VALIDAR_GIA_ITCD_SEPARACAO_DIVORCIO_VALIDA_NUMERO_PROCESSO = 
			"Favor digitar o número do processo de Inventário / Arrolamento.";
		public static final String VALIDAR_GIA_ITCD_SEPARACAO_DIVORCIO_VALIDA_NUMERO_PROCESSO_MAIOR_ZERO = 
			"Número do processo deve ser maior que zero.";
		public static final String VALIDAR_REGIME_CASAMENTO = "Favor selecionar o regime de casamento.";
		public static final String GIA_ITCD_SEPARACAO_VALIDAR_NATUREZA_OPERACAO = 
			"Favor selecionar a natureza da operação.";
		public static final String GIA_ITCD_SEPARACAO_VALIDAR_CLASSIFICACAO_BEM = "Favor informar a classificação do bem.";
		public static final String GIA_ITCD_SEPARACAO_VALIDAR_TIPO_BEM = "Favor informar o tipo do bem.";
		public static final String GIA_ITCD_SEPARACAO_VALIDAR_DESCRICAO_BEM = "Favor informar a descrição do bem.";
		public static final String GIA_ITCD_SEPARACAO_VALIDAR_JA_EXISTE_BEM = "A descrição informada já existe.";
		public static final String GIA_ITCD_SEPARACAO_VALIDAR_VALOR_BEM_CONJUGE_NAO_INFORMADO = 
			"Valor Atribuído ao Cônjuge deve ser informado.";
		public static final String GIA_ITCD_SEPARACAO_VALIDAR_VALOR_BEM_CONJUGE_ZERO_OU_MENOR = 
			"Valor Atribuído ao Cônjuge deve se maior que zero.";
		public static final String GIA_ITCD_SEPARACAO_VALIDAR_VALOR_BEM_CONJUGE_MAIOR_VALOR_MERCADO = 
			//"Valor atribuído não pode ser maior que o Valor de Mercado.";
         "O Valor atribuído não pode ser maior que o Valor do Bem.";
		public static final String GIA_ITCD_SEPARACAO_VALIDAR_SOMA_VALORES_BEM_CONJUGE_MAIOR_VALOR_MERCADO = 
			"Valores atribuídos não podem ser maiores que os Informados nos Valores de Mercado.";
		public static final String GIA_ITCD_SEPARACAO_VALIDAR_BEM_NAO_RELACIONADO_A_NENHUM_CONJUGE = 
			"Existem bens sem relacionamento com os cônjuges.";
		public static final String GIA_ITCD_SEPARACAO_VALIDAR_SOMA_VALORES_BEM_CONJUGE_DIFERENTE_VALOR_MERCADO = 
		    "A soma dos valores do bem atribuídos aos cônjuges deve ser igual ao valor total do bem.";
			//"Valor do bem atribuído aos cônjuges deve ser igual ao valor de mercado do bem.";
      public static final String GIA_ITCD_SEPARACAO_VALIDAR_MUDANCA_DE_PROTOCOLO = 
      "Devido a alteração dos bens tributaveis é necessario redistribuir os bens para os cônjuge!";
		public static final String GIA_ITCD_SEPARACAO_EXCLUIR_CONJUGE2 = "Deseja realmente excluir o Cônjuge?";
		public static final String GIA_ITCD_SEPARACAO_EXCLUIR_BEM_CONJUGE = 
			"Deseja realmente excluir o bem para o Cônjuge?";
		public static final String VALIDAR_DATA_CASAMENTO_NAO_INFORMADA = "Data do casamento deve ser informada.";
		public static final String GIA_ITCD_SEPARACAO_VALIDAR_DATA_SEPARACAO = "Data da Separação Inválida";
		public static final String GIA_ITCD_SEPARACAO_VALIDAR_DATA_SEPARACAO_MAIOR_DATA_ATUAL = 
			"A Data da Separação não pode ser maior que a data atual.";
		// GIA ITCD AUTENTICIDADE
		public static final String VALIDAR_CAMPO_SELECT_STATUS = "O parâmetro de pesquisa deve ser selecionado.";
		public static final String VALIDAR_PARAMETRO_PESQUISA = "Favor informar o parâmetro de pesquisa.";
		public static final String VALIDAR_CAMPO_CODIGO_AUTENTICIDADE = "Favor informar o código de autenticidade.";
		public static final String VALIDAR_CAMPO_SELECT_DECLARACAO_DE_NAO_OCORRENCIA_DE_FATO_GERADOR = 
			"Número da GIA-ITCD/Declaração Informado não existe.";
		public static final String VALIDAR_CODIGO_AUTENTICIDADE = "Código de autenticidade inválido.";
		// ALTERAR STATUS GIA
		public static final String INCLUIR_STATUS_GIA_ITCD = "Não foi possível incluir o Status da GIA ITCD";
		public static final String CONSULTAR_STATUS_GIA_ITCD = "Não foi possível consultar o Status da GIA ITCD";
		public static final String VALIDAR_GIA_ITCD_ALTERAR_STATUS_REMETENTE = 
			"Essa GIA-TCD não pertence ao Inventariante/Declarante.";
		public static final String VALIDAR_GIA_ITCD_ALTERAR_STATUS_NAO_CONFIRMADA = 
			"Não é possível alterar o Status da GIA-ITCD, a mesma não foi concluída e confirmada.";
		public static final String VALIDAR_GIA_ITCD_ALTERAR_STATUS_NUMERO_PROTOCOLO = 
			"Favor preencher número de Protocolo.";
		public static final String VALIDAR_GIA_ITCD_ALTERAR_STATUS_AVALIADO = 
			"GIA-ITCD Avaliada. Esse status da GIA-ITCD não pode ser alterado manualmente.";
		public static final String VALIDAR_GIA_ITCD_ALTERAR_STATUS_ISENTO = 
			"GIA-ITCD Isenta, Esse status da GIA-ITCD não pode ser alterado manualmente.";
   public static final String VALIDAR_GIA_ITCD_ALTERAR_STATUS_ISENTO_IMPOSTO_ATE_1_UPF = 
      "GIA-ITCD Isenta - Imposto até 1 UPF/MT, Esse status da GIA-ITCD não pode ser alterado manualmente.";
		public static final String VALIDAR_GIA_ITCD_ALTERAR_STATUS_NAO_OCORRENCIA_FATO_GERADOR = 
			"GIA-ITCD com status de Não ocorrência de fato gerador, Esse status da GIA-ITCD não pode ser alterado manualmente.";
		public static final String VALIDAR_GIA_ITCD_ALTERAR_STATUS_PARCELADO = 
			"GIA-ITCD Parcelada, Esse status da GIA-ITCD não pode ser alterado manualmente.";
		public static final String VALIDAR_GIA_ITCD_ALTERAR_STATUS_QUITADO = 
			"GIA-ITCD Quitada, Esse status da GIA-ITCD não pode ser alterado manualmente.";
		public static final String VALIDAR_GIA_ITCD_ALTERAR_STATUS_REMETIDO_DIVIDA_ATIVA = 
			"GIA-ITCD Remetido para a divida ativa, Esse status da GIA-ITCD não pode ser alterado manualmente.";
		public static final String VALIDAR_GIA_ITCD_ALTERAR_STATUS_INATIVO = 
			"GIA-ITCD Inativa, Esse status da GIA-ITCD não pode ser alterado manualmente";
		public static final String VALIDAR_GIA_ITCD_ALTERAR_STATUS_SEGUNDA_RETIFICACAO = 
			"GIA-ITCD com status segunda Retificação, Esse status da GIA-ITCD não pode ser alterado manualmente.";
		public static final String VALIDAR_GIA_ITCD_ALTERAR_STATUS_PROTOCOLO = 
			"GIA-ITCD Protocolada. Esse status da GIA-ITCD não pode ser alterado manualmente.";
		public static final String VALIDAR_GIA_ITCD_ALTERADO_SERVIDOR = 
		 "Esse status da GIA-ITCD não pode ser alterado manualmente.";
		public static final String VALIDAR_GIA_ITCD_EM_ELABORACAO = "GIA-ITCD não está finalizada e confirmada, e não pode ser alterada manualmente.";
		public static final String VALIDAR_GIA_ITCD_ALTERAR_STATUS_DATA_PROTOCOLO = "Favor preencher data de Protocolo.";
		public static final String VALIDAR_GIA_ITCD_ALTERAR_STATUS_AGENFA_PROTOCOLO = 
			"Favor selecionar a Agenfa de Protocolo.";
		public static final String VALIDAR_GIA_ITCD_ALTERAR_STATUS_DATA_PERMISSAO = "Favor preencher a Data de permissão.";
		public static final String VALIDAR_GIA_ITCD_ALTERAR_STATUS_OBSERVACAO = "O campo Observação deve ser preenchido.";
		public static final String VALIDAR_GIA_ITCD_ALTERAR_STATUS_MUNICIPIO_AGENFA = 
			"O Protocolo da GIA-ITCD deve ser efetuado na agência indicada na impressão da GIA-ITCD.";
		public static final String VALIDAR_GIA_ITCD_ALTERAR_STATUS_DATA_PROTOCOLO_FUTURA = 
			"A Data de Protocolo não pode ser maior que a Data Atual.";
		public static final String VALIDAR_GIA_ITCD_ALTERAR_STATUS_DATA_PROTOCOLO_ANTIGA = 
			"A Data de Protocolo deve ser maior que a data de criação da GIA.";        
		public static final String VALIDAR_GIA_ITCD_ALTERAR_STATUS_DATA_DESISTENCIA = 
			"A data da inativação deve ser informada.";
		public static final String VALIDAR_GIA_ITCD_ALTERAR_STATUS_PROTOCOLO_DESISTENCIA = 
			"Número do protocolo da inativação deve ser informado.";
		public static final String VALIDAR_GIA_ITCD_ALTERAR_STATUS_DATA_DESISTENCIA_FUTURA = 
			"A data de inativação não pode ser maior que a data atual.";
		public static final String VALIDAR_GIA_ITCD_ALTERAR_STATUS_NUMERO_PROTOCOLO_PARCELAMENTO = 
			"Favor informar Número de Protocolo de Parcelamento.";
		public static final String VALIDAR_GIA_ITCD_ALTERAR_STATUS_QUANTIDADE_PARCELAS = 
			"Favor informar a quantidade de parcelas.";
		public static final String VALIDAR_GIA_ITCD_ALTERAR_STATUS_QUANTIDADE_MINIMA_PARCELAS = 
			"A quantidade de parcelas informadas deve ser maior que 1.";
		public static final String VALIDAR_GIA_ITCD_ALTERAR_STATUS_DATA_PARCELAMENTO = 
			"Favor informar a Data do parcelamento.";
		public static final String VALIDAR_GIA_ITCD_ALTERAR_STATUS_DATA_PARCELAMENTO_INFERIOR_PROTOCOLO = 
			"A Data do parcelamento não pode ser inferior a data de protocolo.";      
		public static final String VALIDAR_GIA_ITCD_ALTERAR_STATUS_DATA_PARCELAMENTO_FUTURA = 
			"A Data do parcelamento não pode ser superior a data de hoje.";    
		public static final String VALIDAR_GIA_ITCD_ALTERAR_STATUS_NUMERO_PROTOCOLO_IMPUGNADO = 
			"Favor preencher o Número de Protocolo de Impugnação.";
		public static final String VALIDAR_GIA_ITCD_ALTERAR_STATUS_DATA_IMPUGNADO = "Favor preencher a Data da Impugnação.";
		public static final String VALIDAR_GIA_ITCD_ALTERAR_STATUS_DATA_IMPUGNADO_FUTURA = "A Data da Impugnação não pode ser superior a data atual.";
		public static final String VALIDAR_GIA_ITCD_ALTERAR_STATUS_PRAZO_IMPUGNACAO = 
			"O prazo para impugação da GIA-ITCD está vencido.";
		public static final String VALIDAR_GIA_ITCD_ALTERAR_STATUS_DATA_IMPUGNACAO_INFERIOR_PROTOCOLO = 
			"A Data do impugnação não pode ser superior a data de hoje.";              
		public static final String VALIDAR_GIA_ITCD_ALTERAR_STATUS_DATA_EMISSAO_RATIFICACAO = 
			"A data de emissão da Ratificação deve ser informada.";
		public static final String VALIDAR_GIA_ITCD_ALTERAR_STATUS_NUMERO_DAR_RATIFICACAO = 
			"O número do DAR de Ratificação deve ser informado.";
		public static final String VALIDAR_GIA_ITCD_ALTERAR_STATUS_NUMERO_DAR_RATIFICACAO_DIFERENTE = 
			"Número de DAR e Data de emissão do DAR inválidos.";
		public static final String VALIDAR_GIA_ITCD_ALTERAR_STATUS_NUMERO_DAR_SEGUNDA_RETIFICACAO_DIFERENTE = 
			"Número de DAR e Data de emissão do DAR Inválidos.";
		public static final String VALIDAR_GIA_ITCD_DATA_EMISSAO_DAR_SEGUNDA_RETIFICACAO_RATIFICACAO_DIFERENTE_DAR = 
			"Data de emissão do DAR diferente da data informada.";
		public static final String VALIDAR_GIA_ITCD_ALTERAR_STATUS_DATA_EMISSAO_SEGUNDA_RETIFICACAO = 
			"A data de emissão da Segunda Retificação deve ser informada.";
		public static final String VALIDAR_GIA_ITCD_ALTERAR_STATUS_NUMERO_DAR_SEGUNDA_RETIFICACAO = 
			"Número do DAR da segunda retificação deve ser informado.";
		public static final String VALIDAR_GIA_ITCD_ALTERAR_STATUS_DATA_CIENCIA_RETIFICACAO = 
			"Data de Ciência da Retificação deve ser preenchida.";
		public static final String VALIDAR_GIA_ITCD_ALTERAR_STATUS_DATA_CIENCIA_NOTIFICACAO = 
			"Data de Ciência da Notificação deve ser preenchida.";
		public static final String VALIDAR_GIA_ITCD_ALTERAR_STATUS_DATA_ENVIO_DIVIDA_ATIVA = 
			"Data de Envio Para Divida Ativa deve ser preenchida.";
      public static final String FAVOR_INFORMAR_DATA_ENVIO_CCF = "Favor informar a Data do Envio ao CCF";         

		// STATUS GIA
		public static final String VALIDAR_STATUS_GIA_ITCD_CODIGO_GIA = "Favor informar o código da GIA-ITCD.";
		public static final String VALIDAR_GIA_ITCD_STATUS_JUSTIFICATIVA_REATIVACAO = 
			"Favor informar o campo justificativa.";
		public static final String VALIDAR_GIA_ITCD_REATIVAR = "GIA-ITCD não pode ser Reativada!";
		//AVALIAÇÃO
		public static final String VALIDAR_PESQUISA_SERVIDOR = "Favor informar a matricula do servidor.";
		public static final String VALIDAR_DATA_AVALIACAO_JUDICIAL = "Favor preencher o campo Data da Avaliação Judicial.";
		public static final String VALIDAR_VALOR_AVALIACAO_JUDICIAL = 
			"Favor preencher o campo Valor da Avaliação Judicial.";
		public static final String VALIDAR_DATA_AVALIACAO = "Favor preencher o campo Data da Avaliação.";
		public static final String VALIDAR_VALOR_AVALIACAO = "Favor preencher o campo Valor da Avaliação.";
		public static final String VALIDAR_CAMPO_OBSERVACAO = "Favor informar a Observação.";
		public static final String VALIDAR_AVALIACAO_GIA_ITCD_PESQUISAR_CONTRIBUINTE = "Contribuinte não informado.";
		public static final String VALIDAR_AVALIACAO_PARAMETRO_TIPO_SELECAO = "Favor seleciona um tipo de consulta.";
		public static final String VALIDAR_AVALIACAO_DATA_INICIAL = 
			"Favor informar o período - data inicial e data final para a pesquisa da consulta.";
		//Inventário Arrolamento Beneficiario Aliquota
		public static final String INCLUIR_GIA_ITCD_INVENTARIO_ARROLAMENTO_BENEFICIARIO_ALIQUOTA = 
			"Não foi possível incluir a Aliquota para o beneficiario da GIA ITCD Inventario e Arrolamento";
		public static final String ALTERAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_BENEFICIARIO_ALIQUOTA = 
			"Não foi possível alterar a Aliquota para o beneficiario da GIA ITCD Inventario e Arrolamento";
		public static final String EXCLUIR_GIA_ITCD_INVENTARIO_ARROLAMENTO_BENEFICIARIO_ALIQUOTA = 
			"Não foi possível excluir a Aliquota para o beneficiario da GIA ITCD Inventario e Arrolamento";
		public static final String CONSULTA_GIA_ITCD_INVENTARIO_ARROLAMENTO_BENEFICIARIO_ALIQUOTA = 
			"Não foi possível consultar a Aliquota para o beneficiario da GIA ITCD Inventario e Arrolamento";
      //Doação Beneficiario Aliquota
      public static final String INCLUIR_GIA_DOACAO_BENEFICIARIO_ALIQUOTA = 
         "Não foi possível incluir a Aliquota para GIA ITCD Separação/Divórcio";
      public static final String ALTERAR_GIA_ITCD_DOACAO_BENEFICIARIO_ALIQUOTA = 
         "Não foi possível alterar a Aliquota para a GIA ITCD Separação/Divórcio";
      public static final String EXCLUIR_GIA_ITCD_DOACAO_BENEFICIARIO_ALIQUOTA = 
         "Não foi possível excluir a Aliquota para a GIA ITCD Separação/Divórcio";
      public static final String CONSULTA_GIA_ITCD_DOACAO_BENEFICIARIO_ALIQUOTA = 
         "Não foi possível consultar a Aliquota para a GIA ITCD Separação/Divórcio";
   //Separacação Aliquota
   public static final String INCLUIR_GIA_SEPARACAO_ALIQUOTA = 
      "Não foi possível incluir a Aliquota para o beneficiario da GIA ITCD Doação";
   public static final String ALTERAR_GIA_ITCD_SEPARACAO_ALIQUOTA = 
      "Não foi possível alterar a Aliquota para o beneficiario da GIA ITCD Doação";
   public static final String EXCLUIR_GIA_ITCD_SEPARACAO_ALIQUOTA = 
      "Não foi possível excluir a Aliquota para o beneficiario da GIA ITCD Doação";
   public static final String CONSULTA_GIA_ITCD_SEPARACAO__ALIQUOTA = 
      "Não foi possível consultar a Aliquota para o beneficiario da GIA ITCD Doação";
		public static final String GIA_ITCD_DOACAO_VALIDAR_CPF = "";
		public static final String GIA_ITCD_DOACAO_VALIDAR_CPF_PROCURADOR = "";
		public static final String VALIDAR_REIMPRIMIR_TIPO_PROCESSO = "GIA_ITCD não pertence ao processo selecionado.";
		public static final String VALIDAR_REIMPRIMIR_TIPO_PROCESSO_NAO_INFORMADO = "Favor selecionar o tipo de Processo.";
		public static final String VALIDAR_REIMPRIMIR_CONTRIBUINTE_NAO_INFORMADO = "Favor localizar o contribuinte.";
		public static final String VALIDAR_REIMPRIMIR_NUMERO_GIAITCD_NAO_INFORMADO = "Favor informar o numero da GIA-ITCD.";
		public static final String VALIDAR_REIMPRIMIR_NUMERO_DECLARACAO_NAO_INFORMADO = "Favor informar o numero da declaração.";
		public static final String VALIDAR_IMPRIMIR_DOCUMENTOS_AVALIACAO_GIA_NAO_AVALIADA = "Não é permitido gerar documento para essa avaliação, pois Status da GIA-ITCD é diferente de Avaliado.";
		public static final String VALIDAR_REIMPRIMIR_GIA_E_RETIFICADA = "Não é possível reimprimir GIA-ITCD com situação Retificada! Reimprimir Retificação da GIA - ITCD";
		public static final String VALIDAR_REIMPRIMIR_GIA_NAO_OCORRENCIA = "Não foi gerado Declaração de Não ocorrência de fato gerador para essa GIA.";
		public static final String VALIDAR_REIMPRIMIR_GIA_ISENTO = "Não foi gerado Declaração de isenção para essa GIA.";      
		public static final String DADOS_CADASTRAIS_INVENTARIANTE_DESATUALIZADO = "O cadastro está desatualizado, favor entrar em contato com a agência fazendária mais próxima para atualizar os dados pessoais.";
		public static final String DADOS_CADASTRAIS_DECLARANTE_DESATUALIZADO = DADOS_CADASTRAIS_INVENTARIANTE_DESATUALIZADO;
		public static final String DADOS_CADASTRAIS_PROCURADOR_DESATUALIZADO = "O cadastro está desatualizado, favor entrar em contato com a agência fazendária mais próxima para atualizar os dados pessoais."; 
		public static final String INFORME_QUANTIDADE_CORRETA_DE_HERDEIROS = "Informar a quantidade correta de herdeiros descendentes ou ascendentes conforme estipulado no documento de inventário.";
		public static final String MENSAGEM_RENUNCIA_TRANSLATIVA = "Para a Renuncia Translativa é necessário que após a declaração da GIA - ITCD Inventário e Arrolamento seja declarado a GIA - ITCD Doação. "+INFORME_QUANTIDADE_CORRETA_DE_HERDEIROS;
		public static final String MENSAGEM_RENUNCIA_TRANSLATIVA_IMPRESSAO_GIA = "Essa GIA-ITCD possui o tipo de renuncia translativa, para protocolar essa GIA-ITCD é necessário que seja protocolado em conjunto a GIA-ITCD Doação/Outros.";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_SELECT_RENUNCIA = "Selecione se houve ou não renuncia.";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_SELECT_TIPO_RENUNCIA = "O tipo de Renuncia deve ser informado.";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_SELECT_HERDEIROS_DESCENDENTES = "Selecione se De Cujus possui herdeiros descendentes.";  
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_CAMPO_QUANTIDADE_HERDEIROS_DESCENDENTES = "Favor informar a quantidade de herdeiros descendentes.";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_SELECT_HERDEIROS_ASCENDENTES = "Selecione se De Cujus possui herdeiros ascendentes.";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_CAMPO_QUANTIDADE_HERDEIROS_ASCENDENTES = "Favor informar a quantidade de herdeiros ascendentes.";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_CAMPO_QUANTIDADE_HERDEIROS_DESCENDENTES_MAIOR_QUE_ZERO = "Quantidade de herdeiros descendentes deve ser maior que zero.";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_CAMPO_QUANTIDADE_HERDEIROS_ASCENDENTES_MAIOR_QUE_ZERO = "Quantidade de herdeiros ascendentes deve ser maior que zero.";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_CAMPO_QUANTIDADE_HERDEIROS_ASCENDENTES_MAIOR_QUE_DOIS = "Quantidade de herdeiros ascendentes não pode ser maior que dois.";  
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_SELECT_OUTROS_HERDEIROS = "Selecione se De Cujus possui outros herdeiros.";  
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_CAMPO_QUANTIDADE_OUTROS_HERDEIROS = "Favor informar a quantidade de herdeiros.";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_CAMPO_QUANTIDADE_OUTROS_HERDEIROS_MAIOR_QUE_ZERO = "Quantidade de herdeiros deve ser maior que zero.";
		public static final String VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_SELECT_MEEIRO_BENEFICIARIO = "Favor selecionar se Cônjuge Sobrevivente (Meeiro/Outro) é ou não beneficiário.";
       public static final String VALIDAR_EMAIL_CONTRIBUINTE_INVALIDO = "Contribuinte selecionado não pertence a GIA informada.";   
      public static final String VALIDAR_EMAIL_CONTRIBUINTE_ENVIAR_SENHA = "Contribuinte não possui endereço eletrônico cadastrado.";
		public static final String VALIDAR_ACESSO_USUARIO_FUNCIONALIDADE = "Servidor não possui permissão de acesso à esta funcionalidade. Favor consultar analista responsável.";
		public static final String VALIDAR_ACESSO_USUARIO_FUNCIONAIDADE_ALTERAR_STATUS = "Servidor não possui permissão para alterar o Status dessa GIA.";
		public static final String VALIDAR_ACESSO_USUARIO_NAO_LOTADO_AGENFA = "Servidor não possui permissão para alterar status desta GIA-ITCD.";
		public static final String VALIDAR_CONTRIBUINTE_PESQUISA_GIA_ITCD = "O contribuinte responsável pela GIA-ITCD deve ser informado.";
		public static final String VALIDAR_BEM_TRIBUTAVEL_PARAMETRO_BEM_PARTICULAR_GIA_ARROLAMENTO = "Favor informar se o bem é um bem particular.";
		public static final String DATA_CASAMENTO_MAIOR_DATA_SEPARACAO = "Data do casamento não pode ser maior que a data da separação.";   
		public static final String DATA_CASAMENTO_MAIOR_DATA_ATUAL = "Data do casamento não pode ser maior que a data atual.";
		public static final String DATA_SEPARACAO_MENOR_DATA_CASAMENTO = "Data da separação não pode ser menor que a data de casamento.";
		public static final String DADOS_DECLARANTE_DEVE_SER_INFORMADO = "CPF do Declarante deve ser informado.";
		public static final String DADOS_CONJUGE2_DEVE_SER_INFORMADO = "CPF do segundo Cônjuge deve ser informado.";
		public static final String DATA_CASAMENTO_IGUAL_DATA_SEPARACAO = "Data do casamento não pode ser igual a data de separação.";
		public static final String DATA_CASAMENTO_IGUAL_DATA_ATUAL = "Data do casametno não pode ser igual a data atual.";
		public static final String DATA_SEPARACAO_IGUAL_DATA_CASAMENTO = "Data da separação não pode ser igual a data do casamento.";
		public static final String DATA_SEPARACAO_IGUAL_DATA_ATUAL = "Data da separação não pode ser igual a data atual.";
		public static final String GIA_DEVERA_SER_ALTERADA_PELA_AGENFA_PROTOCOLO ="GIA- ITCD deverá ser alterada pela agenfa de  protocolo da GIA-ITCD";
		public static final String SERVIDOR_NAO_TEM_ACESSO_AVALIACAO = "Servidor não tem acesso a avaliação dessa GIA-ITCD";
		public static final String SERVIDOR_NAO_PERTENCE_UNIDADE_FAZENDARIA = "Servidor não pertence às unidades fazendárias definidas para avaliação.";
		public static final String SERVIDOR_PARTICIPANTE_NAO_PODE_SER_AVALIADOR = "Servidor informado é integrante da GIA-TICD, favor informar outro avaliador.";
		public static final String SERVIDOR_NAO_TEM_ACESSO_A_IMPRESSAO = "Servidor não tem acesso à impressão de documentos da GIA-ITCD informada";  
		public static final String GERENCIA_EXECUCAO_SERVICO_DEVE_SER_INFORMADO = "Gerência de execução de serviço deve ser informada.";  
		public static final String VALIDAR_GIA_ITCD_CAMPO_JUSTIFICATIVA_ALTERACAO = "O campo Justificativa da alteração deve ser informado.";
		public static final String VALIDAR_GIA_ITCD_ALTERAR_STATUS_MANUAL_GIA_PROTOCOLADA = "GIA - ITCD Protocolada. Esse status da GIA - ITCD não pode ser alterado manualmente.";
		public static final String VALIDAR_GIA_ITCD_ALTERAR_STATUS_MANUAL = "Esse status da GIA - ITCD não pode ser alterado manualmente.";
		public static final String VALIDAR_CONSULTAR_GIA_ITCD_REATIVAR_STATUS_PENDENTE_PROTOCOLO = "GIA - ITCD Pendente de Protocolo, favor realizar um novo cadastro.";
		public static final String VALIDAR_PARAMETRO_TIPO_DOCUMENTO = "Favor selecionar o tipo de documento para reimpressão.";
		public static final String VALIDAR_SERVIDOR_USUARIO_ACESSO_WEB = "Servidor informado não cadastrado no sistema de Acesso Web. Favor consultar o analista responsável.";
		public static final String VALIDAR_CAMPO_SELECT_AGENFA = "A agenfa de avaliação para consulta deve ser informada.";
		public static final String VALIDAR_CAMPO_SELECT_GERENCIA_EXECUCAO = "A gerência de avaliação para consulta deve ser informada.";
		public static final String VALIDAR_CAMPO_SELECT_UNIDADE_FAZENDARIA = "A unidade fazendária de avaliação para consulta deve ser informada.";
		public static final String VALIDAR_PARAMETRO_CAMPO_DATA_INICIAL = "Data inicial deve ser informada.";
		public static final String VALIDAR_PARAMETRO_CAMPO_DATA_FINAL = "Data final deve ser informada.";
		public static final String VALIDAR_LISTA_UNIDADE_FAZENDARIA = "Não foi possível listar unidades fazendárias. Favor consultar o analsita responsável.";
		public static final String VALIDAR_GIA_ITCD_NAO_AVALIADA = "Não foi possível consultar a avaliação. GIA-ITCD com status diferente de AVALIADA.";
		public static final String VALIDAR_GIA_ITCD_NAO_ENCONTRADA = "GIA-ITCD não pode ser encontrada ou com status igual a Inativa.";
		public static final String VALIDAR_TIPO_CONSULTA_AVALIACAO = "O Tipo de Consulta da avaliação deve ser selecionado.";
		public static final String VALIDAR_SELECAO_UNIDADE_FAZENDARIA = "Favor selecionar uma unidade fazendária para consulta.";
		public static final String VALIDAR_PARAMETRO_LISTA_GIA_NAO_ENCONTRADA = "Não foi encontrada nenhuma GIA-ITCD avaliada com os parâmetros informados.";
		public static final String NAO_FOI_POSSIVEL_EXCLUIR_PROCURADOR = "O Procurador já se encontra excluído para esta GIA-ITCD.";
		public static final String VALIDAR_GIA_ITCD_REATIVAR_GIA_PENDENTE_PROTOCOLO = "GIA-ITCD pendente de protocolo, favor realizar novo cadastro.";
		public static final String VALIDAR_GIA_ITCD_ALTERAR_SERVIDOR_NAO_TEM_PERMISSAO = "Servidor não tem permissão para alterar a GIA-ITCD.";
		public static final String VALIDAR_GIA_ITCD_NAO_PODE_SER_IMPRESSA_STATUS_DIFERENTE_PENDENTE_PROTOCOLO = "GIA-ITCD informada não pode ser impressa, pois possui status diferente de Pendente de Protocolo.";
		public static final String VALIDAR_GIA_CONFIRMADA = "A GIA-ITCD-e  informada está incompleta e o contribuinte deverá concluir o seu  preenchimento para realizar essa operação.";
		public static final String NAO_FOI_POSSIVEL_CONSULTAR_MUNICIPIO_CONTRIBUINTE = "Naõ foi possível obter o munícipio do contribuinte do DAR. Favor contate analista responsável.";
		public static final String EXISTE_DAR_QUITADO_GIA_ITCD = "Existe DAR quitado para esta GIA-ITCD, aguarde alguns minutos e realize nova consulta da GIA-ITCD ou procure a Agência Fazendária.";
		public static final String ERRO_GERAR_DAR_DAR_SUBSTITUIDO = "Erro ao gerar o DAR. O DAR encontra-se substituído, favor contatar analista responsável.";
		public static final String STATUS_GIA_ITCD_INVALIDO_GERACAO_DAR = "Status da GIA-ITCD inválido para geração do DAR.";
		public static final String ERRO_GERAR_PDF_REIMPRESSAO = "Não foi possível reimprimir a GIA-ITCD. Favor contate o analista responsável.";
		public static final String CODIGO_PARAMETRO_LEGISLACAO_ALIQUOTA_OBRIGATORIO = "O código do parâmetro legislação alíquota que é obrigatório não foi informado.";
		public static final String CODIGO_PARAMETRO_LEGISLACAO_MULTA_OBRIGATORIO = "O código do parâmetro legislação multa que é obrigatório não foi informado.";
		public static final String GIA_ITCD_NAO_PODE_SER_INATIVADA = "GIA-ITCD não pode ser Inativada.";
		public static final String SERVIDOR_SEM_PERMISSAO_PARA_ALTERAR_GIA = "Servidor não tem permissão para Inativar GIA-ITCD.";
		public static final String CODIGO_GIA_TEMPORARIO_OBRIGATORIO = "O Código da GIA-ITCD Temporária que é obrigatório não foi informado.";
		public static final String DATA_CIENCIA_NOTIFICACAO_DEVE_SER_INFORMADA = "Data da Ciência da Notificação deve ser informada.";
		public static final String DATA_CIENCIA_NOTIFICACAO_MAIOR_DATA_ATUAL = "Data da Ciência da Notificação não pode ser maior que a data atual. ";
		public static final String DATA_CIENCIA_NOTIFICACAO_MENOR_DATA_NOTIFICACAO = "Data da ciencia da notificação deve ser maior que a data da notificação.";
		public static final String JUSTIFICATIVA_REPARCELAMENTO_DEVE_SER_INFORMADO = "Justificativa para retornar o parcelamento deve ser informada.";
		public static final String DATA_QUITACAO_DEVE_SER_INFORMADA = "Data da Quitação deve ser informada.";
		public static final String DATA_QUITACAO_MAIOR_DATA_ATUAL = "Data da Quitação não deve ser maior que a data atual.";
		public static final String QUANTIDADE_PARCELAS_DEVE_SER_MAIOR_ZERO = "A Quantidade de Parcelas deve ser maior que zero.";
		public static final String DATA_ENVIO_INSCRICAO_DIVIDA_ATIVA_DEVE_SER_INFORMADA = "Data de alteração para inscrição em dívida ativa deve ser informada.";
		public static final String DATA_ENVIO_INSCRICAO_DIVIDA_ATIVA_DEVE_SER_MAIOR_DATA_ATUAL = "Data de alteração para inscrição em dívida ativa não pode ser maior que a data atual.";
		public static final String JUSTIFICATIVA_ENVIO_INSCRICAO_DIVIDA_ATIVA_DEVE_SER_INFORMADA = "Justificativa para inscrição em dívida ativa deve ser informada.";
		public static final String DATA_ENVIO_DIVIDA_ATIVA_DEVE_SER_INFORMADA = "Data de envio para dívida ativa deve ser informada.";
		public static final String DATA_ENVIO_DIVIDA_ATIVA_MAIOR_DATA_ATUAL = "Data de envio para dívida ativa não deve ser maior que a data atual.";
		public static final String JUSTIFICATIVA_ENVIO_DIVIDA_ATIVA_DEVE_SER_INFORMADA = "Justificativa de envio para dívida ativa deve ser informada.";
		public static final String DATA_CIENCIA_RETIFICACAO_DEVE_SER_INFORMADA = "Data de Ciência da Retificação deve ser informada.";
		public static final String DATA_CIENCIA_RETIFICACAO_MAIOR_DATA_ATUAL = "Data de Ciência da Retificação não pode ser maior que a data atual.";
		public static final String DATA_CIENCIA_RETIFICACAO_ANTERIOR_DATA_EMISSAO_RETIFICACAO = "Data de Ciência da Retificação deve ser maior que a data de emissão da Retificação.";
		public static final String MOTIVO_IMPUGNACAO_DEVE_SER_INFORMADO = "O motivo da Impugnação deve ser informado.";
		public static final String DATA_SEGUNDA_RETIFICACAO_MAIOR_DATA_ATUAL = "Data da Segunda Retificação não pode ser maior que a data atual.";
		public static final String VALOR_IMPOSTO_DEVE_SER_INFORMADO_E_MAIOR_ZERO = "O Valor do Imposto deve ser informado e maior que zero.";
		public static final String DATA_SEGUNDA_RETIFICACAO_ANTERIOR_DATA_IMPUGNACAO = "Data da Segunda Retificação não pode ser menor que a data da Impugnação.";
		public static final String DATA_RATIFICACAO_MAIOR_DATA_ATUAL = "Data da Ratificação não deve ser maior que a data atual.";
		public static final String DATA_RATIFICACAO_ANTERIOR_DATA_IMPUGNACAO = "Data da Ratificação não pode ser menor que a data da Impugnação.";
		public static final String DATA_CIENCIA_SEGUNDA_RETIFICACAO_DEVE_SER_INFORMADA = "A data de ciência da Segunda Retificação deve ser informada.";
		public static final String DATA_CIENCIA_SEGUNDA_RETIFICACAO_MAIOR_DATA_ATUAL = "Data da ciência da Segunda Retificação não pode ser maior que a data atual.";
		public static final String DATA_CIENCIA_SEGUNDA_RETIFICACAO_INFERIOR_DATA_SEGUNDA_RETIFICACAO = "Data de ciência da Segunda Retificação não pode ser menor que a data da Segunda Retificação.";
		public static final String DATA_CIENCIA_RATIFICACAO_DEVE_SER_INFORMADA = "A data de ciência da Ratificação deve ser informada.";
		public static final String DATA_CIENCIA_RATIFICACAO_MAIOR_DATA_ATUAL = "Data da ciência da Ratificação não pode ser maior que a data atual.";
		public static final String DATA_CIENCIA_RATIFICACAO_INFERIOR_DATA_RATIFICACAO = "Data de ciência da Ratificação não pode ser menor que a data da Ratificação.";
		public static final String NUMERO_DAR_DEVE_SER_INFORMADO = "Número do DAR deve ser informado.";
		public static final String QUANTIDADE_PARCELAS_DIFERENTE_QUANTIDADE_DAR_INFORMADO = "Quantidade de Parcelas diferente da quantidade de DAR(s) informados.";
		public static final String DAR_NAO_INFORMADO = "Um ou mais DAR(s) devem ser informados para quitar a GIA.";
		public static final String SOMATORIO_VALOR_TRIBUTO_DAR_INFORMADO_INFERIOR_VALOR_IMPOSTO = "Valor(es) do(s) DAR(s) informado(s) não pode(m) ser menor que o valor do imposto da GIA-ITCD.";
		public static final String CODIGO_STATUS_NECESSARIO_ALTERAR_STATUS = "O código do Status necessário para alterar Status da GIA-ITCD.";
		public static final String SERVIDOR_SEM_PERMISSAO_INATIVAR_GIA_ITCD = "Servidor não tem permissão para Inativar GIA-ITCD";
		public static final String NUMERO_GIA_SUBSTITUTA_NAO_INFORMADO = "O número da GIA-ITCD substituta deve ser informado";
		public static final String VALIDAR_CODIGO_TRIBUTO_DAR_ARRECADACAO = "Código do tributo do DAR informado inválido. Somente poderão ser vinculados DAR cujo código do tributo seja " + DomnCodigoTributacaoITCD.ITCD_NORMAL.getCodigo() + ", " + DomnCodigoTributacaoITCD.ITCD_PARCELAMENTO.getCodigo() + " ou " + DomnCodigoTributacaoITCD.ITCD_LANCAMENTO_OFICIO_PARCELAMENTO.getCodigo() + ".";
		public static final String VALOR_IMPOSTO_SEGUNDA_RETIFICACAO_DEVE_SER_IGUAL_MAIOR_VALOR_ITCD = "Valor do imposto da segunda retificação não deve ser menor que o valor do imposto calculado sobre os valores declarados";
		public static final String VALOR_IMPOSTO_RATIFICACAO_DEVE_SER_IGUAL_MAIOR_VALOR_ITCD = "Valor do imposto da ratificação não deve ser menor que o valor do imposto calculado sobre os valores declarados";
		public static final String ALTERAR_GIA_ITCD_ATUALIZAR_VALOR_RETIFICACAO_CODIGO_GIA_NAO_INFORMADO = "Não foi possível atualizar o valor do imposto posterior a avaliação. Código da GIA-ITCD que é obrigatório não foi informado.";
      public static final String CONSULTAR_LOG_ITCD = "Não foi possível consultar o Log da GIA ITCD";
		//Gerador de Caracteres de Imagem
		public static final String CARACTERES_IMAGEM_DEVEM_SER_INFORMADOS = "Os caracteres da imagem devem ser informados.";
		public static final String CARACTERES_IMAGEM_INVALIDO = "Caracteres incorretos.";
      
      // SQL
      public static final String FALHAR_AO_GERAR_SEQUENCE = "Falha ao gerar a Sequence Nº .";
       
      public static final String SERVIDOR_NAO_TEM_ACESSO_ALTERACAO_AVALIACAO = "Servidor não tem acesso à alteração da avaliação dessa GIA-ITCD";
      
      
      //EPROCESS
      public static final String ERRO_VALIDACAO_EPROCESS = "Erro ao validar os dados";
      public static final String TIPO_PROCESSO_EPROCESS_ERRO = "Não foi possível consultar tipo processo eprocess";
      public static final String TIPO_PROCESSO_EPROCESS_ERRO_CODG_FK = "Código Eprocess inválido";
      public static final String TIPO_PROCESSO_EPROCESS_ERRO_DESC_TIPO = "Descrição inválida!";
      public static final String TIPO_PROCESSO_EPROCESS_ERRO_DOMN = "Domínio do tipo de processo inválido";
      public static final String TIPO_PROCESSO_EPROCESS_ERRO_REGISTRO_EXISTE = "Registro deste tipo de processo já existe";
      
      //VEICULO
       public static final String FAVOR_INFORMA_UF_VEICULO = "Favor selecionar a UF do veiculo.";
       public static final String FAVOR_INFORMA_O_RENAVAN = "Favor informar o Renavan do veiculo.";
       public static final String FAVOR_INFORMA_A_PLACA = "Favor informar a Placa do veiculo.";
       public static final String FAVOR_INFORMA_O_VALOR_DECLARADO = "Favor informar o valor do veiculo.";
       public static final String FAVOR_INFORMA_O_INSECAO = "Favor informe se tem Insenção Prevista em lei.";
       public static final String NAO_EXISTE_VALORES_PLACA_RENAVAN = "Não encontrado valores para esse RENAVAM";
       public static final String NAO_EXISTE_VALORES_MODELO_VEICULO = "Não existe valores para esse Modelo de Veiculo.";
       public static final String ERRO_MARCA_VEICULO = "Não foi possivel Lista as Marca dos veiculos..";
       public static final String ERRO_CATEGORIA_VEICULO = "Não foi possivel Lista as Categoria dos veiculos..";
       public static final String FAVOR_INFORMAR_MARCA = "Favor informar a Marca do veiculo.";
       public static final String FAVOR_INFORMAR_MODELO = "Favor informar o Modelo do veiculo.";
       
       //Rebanho
        public static final String FAVOR_SELECIONE_UM_REBANHO = "Favor selecionar Um tipo de Rebanho.";
        public static final String FAVOR_IFORME_QUANTIDADE = "Favor informe a quantidade de Rebanho.";
        public static final String FAVOR_IFORMAR_VALOR_DECLARADO= "Favor informe o R$ valor total do Rebanho.";
        
      //PROCESSO ITCTB55
      public static final String FAVOR_INFORMAR_DESCRICAO_PROCESSO = "Favor informar a descrição do processo.";
      public static final String FAVOR_SELECIONAR_TIPO_PROCESSO = "Favor selecionar o Tipo de processo.";
      public static final String FAVOR_SELECIONAR_TIPO_PROCESSO_E_PROCESS = "Favor selecionar o Tipo de processo E-process.";
      public static final String FAVOR_SELECIONAR_ASSUNTO = "Favor selecionar o Assunto.";
      
      // ITCTB60_PEDIDO_RELATORIO
      public static final String CONSULTAR_PEDIDO_RELATORIO = "Não foi possível consultar o Relatório.";
      public static final String LISTAR_PEDIDO_RELATORIO = "Não foi possível listar os Relatórios.";
      public static final String VALIDAR_SELECIONAR_TIPO_RELATORIO = "Por favor, selecione o tipo de relatório.";
   
         
      // ITCTB61_PARAMETRO_RELATORIO
      public static final String CONSULTAR_PARAMETRO_RELATORIO = "Não foi possível consultar os parâmetros do relatório.";
      public static final String LISTAR_PARAMETRO_RELATORIO = "Não foi possível listar os Parâmetros dos Relatório.";
      
      
      public static final String VALIDAR_DATA_INICIAL_RELATORIO = "Favor preencher a data inicial.";
      public static final String VALIDAR_DATA_FINAL_RELATORIO = "Favor preencher a data final.";
      
      public static final String INCLUIR_GIA_ITCD_DOACAO_SUCESSIVA = "Não foi possível incluir a GIA ITCD Doação Sucessiva.";
           
}
