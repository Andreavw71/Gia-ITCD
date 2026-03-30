import api from './api';
import {
  GIAITCD,
  GIAITCDInventarioArrolamento,
  GIAITCDDoacao,
  GIAITCDSeparacaoDivorcio,
  Contribuinte,
  NaturezaOperacao,
  StatusGIAITCD,
} from '../types';

// ==================== GIA-ITCD ====================

export async function pesquisarGIAITCD(params: {
  codigo?: number;
  cpfDeclarante?: string;
  tipoGIA?: string;
  status?: StatusGIAITCD;
}): Promise<GIAITCD[]> {
  const { data } = await api.get('/generico/giaitcd/pesquisar', { params });
  return data;
}

export async function consultarGIAITCD(codigo: number): Promise<GIAITCD> {
  const { data } = await api.get(`/generico/giaitcd/${codigo}`);
  return data;
}

export async function imprimirGIAITCD(codigo: number): Promise<Blob> {
  const { data } = await api.get(`/generico/giaitcd/${codigo}/imprimir`, {
    responseType: 'blob',
  });
  return data;
}

// ==================== Inventário / Arrolamento ====================

export async function incluirInventarioArrolamento(
  gia: Partial<GIAITCDInventarioArrolamento>
): Promise<GIAITCDInventarioArrolamento> {
  const { data } = await api.post('/giaitcd/giaitcdinventarioarrolamento/incluir', gia);
  return data;
}

export async function alterarInventarioArrolamento(
  gia: GIAITCDInventarioArrolamento
): Promise<GIAITCDInventarioArrolamento> {
  const { data } = await api.put(`/giaitcd/giaitcdinventarioarrolamento/${gia.codigo}`, gia);
  return data;
}

export async function salvarDadosGeraisInventario(
  gia: Partial<GIAITCDInventarioArrolamento>
): Promise<GIAITCDInventarioArrolamento> {
  const { data } = await api.post('/giaitcd/giaitcdinventarioarrolamento/salvarDadosGerais', gia);
  return data;
}

// ==================== Doação ====================

export async function incluirDoacao(
  gia: Partial<GIAITCDDoacao>
): Promise<GIAITCDDoacao> {
  const { data } = await api.post('/giaitcd/giaitcddoacao/incluir', gia);
  return data;
}

export async function alterarDoacao(
  gia: GIAITCDDoacao
): Promise<GIAITCDDoacao> {
  const { data } = await api.put(`/giaitcd/giaitcddoacao/${gia.codigo}`, gia);
  return data;
}

export async function salvarDadosGeraisDoacao(
  gia: Partial<GIAITCDDoacao>
): Promise<GIAITCDDoacao> {
  const { data } = await api.post('/giaitcd/giaitcddoacao/salvarDadosGerais', gia);
  return data;
}

// ==================== Separação / Divórcio ====================

export async function incluirSeparacaoDivorcio(
  gia: Partial<GIAITCDSeparacaoDivorcio>
): Promise<GIAITCDSeparacaoDivorcio> {
  const { data } = await api.post('/giaitcd/giaitcdseparacaodivorcio/incluir', gia);
  return data;
}

export async function alterarSeparacaoDivorcio(
  gia: GIAITCDSeparacaoDivorcio
): Promise<GIAITCDSeparacaoDivorcio> {
  const { data } = await api.put(`/giaitcd/giaitcdseparacaodivorcio/${gia.codigo}`, gia);
  return data;
}

// ==================== Contribuinte ====================

export async function pesquisarContribuinte(cpfCnpj: string): Promise<Contribuinte> {
  const { data } = await api.get('/util/integracao/cadastro/pesquisar', {
    params: { cpfCnpj },
  });
  return data;
}

// ==================== Natureza da Operação ====================

export async function listarNaturezasOperacao(tipoGIA?: string): Promise<NaturezaOperacao[]> {
  const { data } = await api.get('/tabelabasica/naturezadaoperacao/listar', {
    params: { tipoGIA },
  });
  return data;
}

// ==================== Protocolo ====================

export async function protocolarGIAITCD(
  codigo: number,
  tipoProtocolo: string
): Promise<void> {
  await api.post(`/giaitcd/protocolo/protocolar`, { codigo, tipoProtocolo });
}

export async function validarProtocolo(
  codigo: number,
  protocolo: string
): Promise<{ valido: boolean; mensagem: string }> {
  const { data } = await api.post('/giaitcd/protocolo/validar', { codigo, protocolo });
  return data;
}

// ==================== Autenticidade ====================

export async function verificarAutenticidade(
  codigoAutenticidade: string
): Promise<GIAITCD> {
  const { data } = await api.get('/giaitcd/autenticidade/verificar', {
    params: { codigoAutenticidade },
  });
  return data;
}

// ==================== Impressão DAR / Declarações ====================

export async function imprimirDAR(codigoGia: number): Promise<Blob> {
  const { data } = await api.get(`/generico/giaitcd/${codigoGia}/imprimirDar`, {
    responseType: 'blob',
  });
  return data;
}

export async function imprimirDeclaracao(codigoGia: number): Promise<Blob> {
  const { data } = await api.get(`/generico/giaitcd/${codigoGia}/imprimirDeclaracao`, {
    responseType: 'blob',
  });
  return data;
}
