// ==================== GIA-ITCMD-e Doação - Autorregularização ====================

// ==================== Enums ====================

export enum QualidadeDeclarante {
  DOADOR = 'DOADOR',
  DONATARIO = 'DONATARIO',
  DEFENSOR_PUBLICO = 'DEFENSOR_PUBLICO',
  TERCEIRO_PROCURACAO = 'TERCEIRO_PROCURACAO',
  CONTABILISTA = 'CONTABILISTA',
  ADVOGADO = 'ADVOGADO',
  REP_PJ_PRIVADA = 'REP_PJ_PRIVADA',
  REP_PJ_PUBLICA = 'REP_PJ_PUBLICA',
}

export enum MotivoFatoGerador {
  INVENTARIO_ARROLAMENTO = 'INVENTARIO_ARROLAMENTO',
  DOACAO = 'DOACAO',
  SEPARACAO_DIVORCIO = 'SEPARACAO_DIVORCIO',
  OUTRAS_SITUACOES = 'OUTRAS_SITUACOES',
}

export enum TipoDoacao {
  SIMPLES = 'SIMPLES',
  RESERVA_USUFRUTO = 'RESERVA_USUFRUTO',
  NUA_PROPRIEDADE = 'NUA_PROPRIEDADE',
  ISENCAO_LEGITIMACAO_POSSE = 'ISENCAO_LEGITIMACAO_POSSE',
  RENUNCIA_USUFRUTO = 'RENUNCIA_USUFRUTO',
  EXTINCAO_USUFRUTO = 'EXTINCAO_USUFRUTO',
  DIREITO_HABITACAO = 'DIREITO_HABITACAO',
  DIREITO_SUPERFICIE = 'DIREITO_SUPERFICIE',
  DIREITO_USO = 'DIREITO_USO',
  OUTROS = 'OUTROS',
}

export enum SituacaoDoacao {
  PREVISTA = 'PREVISTA',
  JA_REALIZADA = 'JA_REALIZADA',
}

export enum TipoPessoa {
  PF_COM_CPF = 'PF_COM_CPF',
  PF_SEM_CPF = 'PF_SEM_CPF',
  PJ_CNPJ = 'PJ_CNPJ',
}

export enum RegimeBens {
  COMUNHAO_PARCIAL = 'COMUNHAO_PARCIAL',
  COMUNHAO_UNIVERSAL = 'COMUNHAO_UNIVERSAL',
  SEPARACAO_CONVENCIONAL = 'SEPARACAO_CONVENCIONAL',
  SEPARACAO_OBRIGATORIA = 'SEPARACAO_OBRIGATORIA',
  PARTICIPACAO_AQUESTOS = 'PARTICIPACAO_AQUESTOS',
}

export enum TipoBemDireito {
  MOVEL = 'MOVEL',
  IMOVEL = 'IMOVEL',
}

export enum SubtipoBemMovel {
  AERONAVE = 'AERONAVE',
  EMBARCACAO = 'EMBARCACAO',
  OUTROS_MOVEIS = 'OUTROS_MOVEIS',
  SEMOVENTE = 'SEMOVENTE',
  PARTICIPACAO_SOCIETARIA = 'PARTICIPACAO_SOCIETARIA',
  VALOR_MONETARIO = 'VALOR_MONETARIO',
  VEICULO_AUTOMOTOR = 'VEICULO_AUTOMOTOR',
}

export enum SubtipoBemImovel {
  IMOVEL_RURAL = 'IMOVEL_RURAL',
  IMOVEL_URBANO = 'IMOVEL_URBANO',
}

export enum StatusGIAITCMD {
  EM_ELABORACAO = 'EM_ELABORACAO',
  ENVIADA = 'ENVIADA',
  PROCESSADA_CONCLUIDA = 'PROCESSADA_CONCLUIDA',
  AGUARDANDO_AUTORREGULARIZACAO = 'AGUARDANDO_AUTORREGULARIZACAO',
  PEDIDO_RETIFICACAO_ANALISE = 'PEDIDO_RETIFICACAO_ANALISE',
  RETIFICADA = 'RETIFICADA',
  RETIFICADA_AUTORREGULARIZACAO = 'RETIFICADA_AUTORREGULARIZACAO',
  PEDIDO_CANCELAMENTO_ANALISE = 'PEDIDO_CANCELAMENTO_ANALISE',
  CANCELADA = 'CANCELADA',
  AGUARDANDO_HOMOLOGACAO = 'AGUARDANDO_HOMOLOGACAO',
  HOMOLOGADA = 'HOMOLOGADA',
  HOMOLOGADA_TACITAMENTE = 'HOMOLOGADA_TACITAMENTE',
}

export enum EstadoConservacao {
  OTIMO = 'OTIMO',
  BOM = 'BOM',
  REGULAR = 'REGULAR',
  RUIM = 'RUIM',
}

export enum MaterialConstrucao {
  ADOBE = 'ADOBE',
  ALVENARIA = 'ALVENARIA',
  ESTRUTURA_METALICA = 'ESTRUTURA_METALICA',
  PEDRA = 'PEDRA',
  PREMOLDADO = 'PREMOLDADO',
  TABUA = 'TABUA',
  TAIPA = 'TAIPA',
  OUTROS = 'OUTROS',
}

// ==================== Interfaces ====================

export interface DadosDeclarante {
  cpf: string;
  nome: string;
  endereco: Endereco;
  email: string;
  qualidade: QualidadeDeclarante;
}

export interface Endereco {
  cep: string;
  logradouro: string;
  numero: string;
  complemento: string;
  bairro: string;
  cidade: string;
  estado: string;
  pais: string;
  naoSeAplica: boolean; // residente no exterior
}

export interface FatoGerador {
  motivo: MotivoFatoGerador;
  tipoDoacao: TipoDoacao;
  situacaoDoacao: SituacaoDoacao;
  denunciaEspontanea: boolean;
  dataDoacao: string;
}

export interface PessoaFisica {
  tipoPessoa: TipoPessoa;
  cpf: string;
  nome: string;
  dataNascimento: string;
  endereco: Endereco;
  emails: string[];
  telefones: Telefone[];
  representadoPorProcurador: boolean;
  possuiConjuge: boolean;
}

export interface PessoaJuridica {
  tipoPessoa: TipoPessoa.PJ_CNPJ;
  cnpj: string;
  razaoSocial: string;
  endereco: Endereco;
  emails: string[];
  telefones: Telefone[];
  representadoPorProcurador: boolean;
}

export interface Telefone {
  prefixoPais: string;
  numero: string;
}

export interface Doador {
  id: number;
  dados: PessoaFisica | PessoaJuridica;
  regimeBens?: RegimeBens; // apenas para 2o doador (conjuge)
}

export interface Donatario {
  id: number;
  dados: PessoaFisica | PessoaJuridica;
  menorDeIdade: boolean;
  responsavelSolidario?: {
    cpf: string;
    nome: string;
    endereco?: Endereco;
  };
}

export interface BemDireito {
  id: number;
  tipo: TipoBemDireito;
  subtipo: string;
  especie: string;
  descricao: string;
  valorTotalDeclarado: number;
  valorEstimado: number | null;
  concordaValorEstimado: boolean | null;
  valorArbitrado: number | null;
  concordaValorArbitrado: boolean | null;
  bemComumCasal: boolean;
  percentualTransmitidoDoador1: number;
  percentualTransmitidoDoador2: number;
  percentualTotalTransmitido: number;
  valorBemTransmitido: number;
  valorBemTransmitidoDoador1: number;
  valorBemTransmitidoDoador2: number;
  pertenceEmpresaDoacao: boolean;
  empresaProprietaria?: string;
  dadosEspecificos: Record<string, any>; // campos especificos por subtipo
}

export interface DivisaoBemDonatario {
  bemId: number;
  doadorId: number;
  donatarioId: number;
  percentual: number;
}

export interface DocumentoAnexado {
  id: number;
  tipoDocumento: string;
  nomeArquivo: string;
  tamanhoKB: number;
  observacoes: string;
  dataHoraInsercao: string;
  secao: 'FATO_GERADOR' | 'DECLARANTE' | 'PARTES_INTERESSADAS' | 'BENS_DIREITOS';
  parteInteressadaId?: number;
  bemDireitoId?: number;
}

export interface Pendencia {
  secao: string;
  descricao: string;
  tipo: 'ERRO' | 'AVISO';
}

// ==================== Faixas de Tributação ====================

export interface FaixaTributacao {
  descricao: string;
  limiteInferiorUPF: number;
  limiteSuperiorUPF: number | null;
  aliquota: number; // percentual
}

export const FAIXAS_TRIBUTACAO_ITCMD: FaixaTributacao[] = [
  { descricao: 'Ate 500 UPF/MT', limiteInferiorUPF: 0, limiteSuperiorUPF: 500, aliquota: 0 },
  { descricao: 'Acima de 500 ate 1.000 UPF/MT', limiteInferiorUPF: 500, limiteSuperiorUPF: 1000, aliquota: 2 },
  { descricao: 'Acima de 1.000 ate 4.000 UPF/MT', limiteInferiorUPF: 1000, limiteSuperiorUPF: 4000, aliquota: 4 },
  { descricao: 'Acima de 4.000 ate 10.000 UPF/MT', limiteInferiorUPF: 4000, limiteSuperiorUPF: 10000, aliquota: 6 },
  { descricao: 'Acima de 10.000 UPF/MT', limiteInferiorUPF: 10000, limiteSuperiorUPF: null, aliquota: 8 },
];

// ==================== Cálculo ITCMD ====================

export interface CalculoDonatario {
  donatarioId: number;
  nomeDonatario: string;
  bensRecebidos: {
    bemId: number;
    especie: string;
    valorBemTransmitido: number;
    percentualAtribuido: number;
    baseCalculoBem: number;
  }[];
  baseCalculoITCMD: number;
  faixasAplicadas: {
    faixa: string;
    valorUPF: number;
    valorRS: number;
    aliquota: number;
    imposto: number;
  }[];
  valorNominalITCMD: number;
  percentualJurosMora: number;
  jurosMora: number;
  percentualMultaMora: number;
  multaMora: number;
  valorITCMDAtualizado: number;
}

export interface CalculoITCMDResult {
  calculosPorDonatario: CalculoDonatario[];
  valorTotalNominalITCMD: number;
  valorTotalITCMDAtualizado: number;
  dataValidadeCalculo: string;
  denunciaEspontanea: boolean;
}

// ==================== GIA-ITCMD-e Principal ====================

export interface GIAITCMD {
  id: number;
  status: StatusGIAITCMD;
  dataCriacao: string;
  dataEnvio?: string;
  dadosDeclarante: DadosDeclarante;
  fatoGerador: FatoGerador;
  doadores: Doador[];
  donatarios: Donatario[];
  bensDireitos: BemDireito[];
  divisaoBens: DivisaoBemDonatario[];
  documentos: DocumentoAnexado[];
  calculo?: CalculoITCMDResult;
  pendencias: Pendencia[];
}

// ==================== Catalogos de Especie ====================

export const ESPECIES_AERONAVE = [
  'Aviao monomotor', 'Aviao bimotor', 'Aviao de grande porte', 'Helicoptero',
  'Planador', 'Ultraleve', 'Drones de uso comercial', 'Outros tipos de aeronaves',
];

export const ESPECIES_EMBARCACAO = [
  'Barco de pesca', 'Canoa', 'Embarcacao artesanal', 'Lancha', 'Iate',
  'Veleiro', 'Jet ski ou Moto Aquatica', 'Navio de carga/transporte', 'Balsa', 'Outras embarcacoes',
];

export const ESPECIES_OUTROS_MOVEIS = [
  'Consorcio nao contemplado', 'Joias, pedras preciosas e assemelhados',
  'Obras de arte ou objetos de colecao', 'Titulos de clube e assemelhados', 'Outros bens moveis',
];

export const ESPECIES_SEMOVENTE = [
  'Gado Asinino', 'Gado Bovino', 'Gado Bubalino', 'Gado Caprino', 'Gado Equino',
  'Gado Ovino', 'Gado Suino', 'Galinaceo', 'Peixes', 'Outros Semoventes',
];

export const ESPECIES_PARTICIPACAO_SOCIETARIA = [
  'Acoes de S/A Capital Aberto negociadas em Bolsa',
  'Acoes de S/A Capital Aberto nao negociadas em Bolsa',
  'Acoes de S/A Capital Fechado',
  'Quotas de Sociedade Limitada (LTDA)',
  'Quotas de Sociedade Limitada Unipessoal (SLU)',
  'Quotas de Sociedade Simples',
  'Quotas de Cooperativa',
  'Quotas de Sociedade em Nome Coletivo',
  'Quotas de Sociedade em Comandita Simples',
  'Quotas de Sociedade em Comandita por Acoes',
  'Quotas de Outras Sociedades Empresarias',
  'Participacao em Holding',
  'Outras Participacoes Societarias',
];

export const ESPECIES_VALOR_MONETARIO = [
  'Aplicacoes e investimentos', 'Creditos a receber',
  'Deposito ou transferencia bancaria (PIX)', 'Dinheiro em especie (moeda nacional)',
  'Moeda estrangeira', 'Titulos de credito', 'Participacoes em consorcios',
  'Previdencia Privada', 'Outros valores monetarios',
];

export const ESPECIES_VEICULO = [
  'Carro e Utilitario Pequeno', 'Moto', 'Caminhao, Reboques e Semirreboques',
  'Onibus e Micro-onibus', 'Trator e Maquinas Agricolas', 'Outros veiculos',
];

export const ESPECIES_IMOVEL_RURAL = [
  'Chacara', 'Sitio', 'Fazenda', 'Estancia', 'Haras',
  'Granja', 'Agroindustria rural', 'Gleba', 'Outros imoveis rurais',
];

export const ESPECIES_IMOVEL_URBANO = [
  'Apartamento', 'Box de Garagem', 'Casa', 'Chacara', 'Flat', 'Galpao',
  'Jazigo', 'Lote ou terreno', 'Predio', 'Quitinete', 'Sala Comercial',
  'Sitio', 'Sobrado', 'Outros imoveis urbanos',
];

export function getEspecies(subtipo: string): string[] {
  switch (subtipo) {
    case SubtipoBemMovel.AERONAVE: return ESPECIES_AERONAVE;
    case SubtipoBemMovel.EMBARCACAO: return ESPECIES_EMBARCACAO;
    case SubtipoBemMovel.OUTROS_MOVEIS: return ESPECIES_OUTROS_MOVEIS;
    case SubtipoBemMovel.SEMOVENTE: return ESPECIES_SEMOVENTE;
    case SubtipoBemMovel.PARTICIPACAO_SOCIETARIA: return ESPECIES_PARTICIPACAO_SOCIETARIA;
    case SubtipoBemMovel.VALOR_MONETARIO: return ESPECIES_VALOR_MONETARIO;
    case SubtipoBemMovel.VEICULO_AUTOMOTOR: return ESPECIES_VEICULO;
    case SubtipoBemImovel.IMOVEL_RURAL: return ESPECIES_IMOVEL_RURAL;
    case SubtipoBemImovel.IMOVEL_URBANO: return ESPECIES_IMOVEL_URBANO;
    default: return [];
  }
}
