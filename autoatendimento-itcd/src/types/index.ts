// ==================== Enums / Domínios ====================

export enum TipoGIA {
  INVENTARIO_ARROLAMENTO = 'INVENTARIO_ARROLAMENTO',
  DOACAO = 'DOACAO',
  SEPARACAO_DIVORCIO = 'SEPARACAO_DIVORCIO',
}

export enum StatusGIAITCD {
  PENDENTE_PREENCHIMENTO = 'PENDENTE_PREENCHIMENTO',
  PENDENTE_PROTOCOLO = 'PENDENTE_PROTOCOLO',
  PROTOCOLADA = 'PROTOCOLADA',
  EM_AVALIACAO = 'EM_AVALIACAO',
  AVALIADA = 'AVALIADA',
  CANCELADA = 'CANCELADA',
  INATIVA = 'INATIVA',
}

export enum EstadoCivil {
  SOLTEIRO = 1,
  CASADO = 2,
  DIVORCIADO = 3,
  VIUVO = 4,
  SEPARADO = 5,
  UNIAO_ESTAVEL = 6,
}

export enum RegimeCasamento {
  COMUNHAO_PARCIAL = 1,
  COMUNHAO_UNIVERSAL = 2,
  SEPARACAO_TOTAL = 3,
  PARTICIPACAO_AQUESTOS = 4,
}

export enum TipoRenuncia {
  ABDICATIVA = 1,
  TRANSLATIVA = 2,
}

export enum SimNao {
  SIM = 'S',
  NAO = 'N',
}

export enum TipoBem {
  IMOVEL_URBANO = 1,
  IMOVEL_RURAL = 2,
  VEICULO = 3,
  REBANHO = 4,
  OUTROS = 5,
}

export enum TipoProcessoInventario {
  JUDICIAL = 1,
  EXTRAJUDICIAL = 2,
}

export enum TipoProtocolo {
  MANUAL = 'MANUAL',
  AUTOMATICO = 'AUTOMATICO',
}

// ==================== Contribuinte ====================

export interface Contribuinte {
  numrDocumento: string;
  nomeContribuinte: string;
  endereco: string;
  enderecoNumero: string;
  enderecoComplemento: string;
  enderecoBairro: string;
  enderecoCEP: string;
  municipio: string;
  siglaUF: string;
  numrDdd: string;
  numrTelefone: string;
  email: string;
  pontoReferencia: string;
}

// ==================== Natureza da Operação ====================

export interface NaturezaOperacao {
  codigo: number;
  descricaoNaturezaOperacao: string;
  ativo: boolean;
}

// ==================== Parâmetro Legislação ====================

export interface ParametroLegislacao {
  codigo: number;
  descricao: string;
  valor: number;
  dataInicio: string;
  dataFim: string;
  ativo: boolean;
}

// ==================== Bem ====================

export interface Bem {
  codigo: number;
  descricao: string;
  tipoBem: TipoBem;
  ativo: boolean;
}

// ==================== Bem Tributável ====================

export interface BemTributavel {
  codigo: number;
  giaItcdCodigo: number;
  bemVo: Bem;
  valorMercado: number;
  valorInformadoContribuinte: number;
  descricaoBemTributavel: string;
  bemParticular: SimNao;
  isencaoPrevista: SimNao;
  concordaComValorArbitrado: SimNao;
  fichaImovelRural?: FichaImovelRural;
  fichaImovelUrbano?: FichaImovelUrbano;
  fichaVeiculo?: FichaVeiculo;
  fichaRebanho?: FichaRebanho;
}

// ==================== Fichas de Bens ====================

export interface FichaImovelUrbano {
  inscricaoImobiliaria: string;
  areaTerreno: number;
  areaConstrucao: number;
  municipio: string;
  uf: string;
  endereco: string;
  numero: string;
  bairro: string;
  cep: string;
  valorVenalIPTU: number;
}

export interface FichaImovelRural {
  incra: string;
  nirf: string;
  denominacao: string;
  municipio: string;
  uf: string;
  areaTotal: number;
  areaUtilizada: number;
  valorTerraDeclarado: number;
  construcoes: FichaImovelRuralConstrucao[];
  benfeitorias: FichaImovelRuralBenfeitoria[];
}

export interface FichaImovelRuralConstrucao {
  codigo: number;
  descricao: string;
  area: number;
  valorUnitario: number;
  valorTotal: number;
}

export interface FichaImovelRuralBenfeitoria {
  codigo: number;
  descricao: string;
  quantidade: number;
  valorUnitario: number;
  valorTotal: number;
}

export interface FichaVeiculo {
  placa: string;
  renavam: string;
  marca: string;
  modelo: string;
  anoFabricacao: number;
  anoModelo: number;
  valorFIPE: number;
}

export interface FichaRebanho {
  especie: string;
  quantidade: number;
  valorUnitario: number;
  valorTotal: number;
}

// ==================== Beneficiário ====================

export interface Beneficiario {
  codigo: number;
  giaItcdCodigo: number;
  pessoaBeneficiaria: Contribuinte;
  valorRecebido: number;
  valorRecebidoAvaliacao: number;
  valorItcdBeneficiario: number;
  valorRecebidoDoacaoSucessiva: number;
  valorItcdDoacaoSucessiva: number;
  flagDoacaoSucessiva: number;
}

// ==================== Cônjuge (Separação/Divórcio) ====================

export interface Conjuge {
  codigo: number;
  pessoaConjuge: Contribuinte;
  bensRecebidos: BemTributavel[];
  valorTotalRecebido: number;
}

// ==================== Status GIA-ITCD ====================

export interface StatusGIAITCDInfo {
  codigo: number;
  descricao: string;
  dataStatus: string;
  observacao: string;
}

// ==================== GIA-ITCD Principal ====================

export interface GIAITCD {
  codigo: number;
  tipoGIA: TipoGIA;
  responsavelVo: Contribuinte;
  procuradorVo?: Contribuinte;
  naturezaOperacaoVo: NaturezaOperacao;
  parametroLegislacaoVo?: ParametroLegislacao;
  dataCriacao: string;
  senha: string;
  codigoAutenticidade: string;
  valorTotalBensDeclarados: number;
  valorTotalArbitrado: number;
  valorTotalInformadoBensDeclarados: number;
  valorUPF: number;
  valorCalculoDemonstrativo: number;
  valorITCD: number;
  valorRecolhimento: number;
  valorTSE: number;
  valorBaseCalculoTributavel: number;
  valorCorrecaoMonetaria: number;
  valorJuros: number;
  valorMulta: number;
  statusVo: StatusGIAITCDInfo;
  justificativaAlteracao: string;
  giaConfirmada: SimNao;
  fracaoIdeal: number;
  tipoProtocoloGIA: TipoProtocolo;
  baseCalculoReduzida: number;
  bensTributaveis: BemTributavel[];
  beneficiarios: Beneficiario[];
}

// ==================== Inventário / Arrolamento ====================

export interface GIAITCDInventarioArrolamento extends GIAITCD {
  deCujusVo: Contribuinte;
  meeiroVo?: Contribuinte;
  dataObito: string;
  estadoCivil: EstadoCivil;
  regimeCasamento?: RegimeCasamento;
  tipoRenuncia?: TipoRenuncia;
  tipoProcessoInventario: TipoProcessoInventario;
  possuiMeeiro: SimNao;
}

// ==================== Doação ====================

export interface GIAITCDDoacao extends GIAITCD {
  doacaoSucessiva: BemTributavel[];
}

// ==================== Separação / Divórcio ====================

export interface GIAITCDSeparacaoDivorcio extends GIAITCD {
  conjuge1: Conjuge;
  conjuge2: Conjuge;
  dataCasamento: string;
  regimeCasamento: RegimeCasamento;
}

// ==================== Avaliação ====================

export interface Avaliacao {
  codigo: number;
  giaItcdCodigo: number;
  bemTributavelCodigo: number;
  valorAvaliado: number;
  dataAvaliacao: string;
  servidorResponsavel: string;
  observacao: string;
  ativo: boolean;
}

// ==================== Relatório ====================

export interface PedidoRelatorio {
  codigo: number;
  descricao: string;
  parametros: ParametroRelatorio[];
}

export interface ParametroRelatorio {
  codigo: number;
  nome: string;
  valor: string;
}

// ==================== Tabelas Básicas ====================

export interface Construcao {
  codigo: number;
  descricao: string;
  valorUnitario: number;
  ativo: boolean;
}

export interface Benfeitoria {
  codigo: number;
  descricao: string;
  valorUnitario: number;
  ativo: boolean;
}

export interface Rebanho {
  codigo: number;
  especie: string;
  valorUnitario: number;
  ativo: boolean;
}

export interface Distancia {
  codigo: number;
  municipioOrigem: string;
  municipioDestino: string;
  distanciaKm: number;
}

// ==================== DAR ====================

export interface GIAITCDDar {
  codigo: number;
  giaItcdCodigo: number;
  numeroDar: string;
  valorDar: number;
  dataVencimento: string;
  dataPagamento?: string;
  pago: boolean;
}

// ==================== Log / Histórico ====================

export interface HistoricoLog {
  codigo: number;
  giaItcdCodigo: number;
  dataAlteracao: string;
  descricaoAlteracao: string;
  usuario: string;
}
