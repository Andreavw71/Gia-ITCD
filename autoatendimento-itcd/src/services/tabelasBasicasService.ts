import api from './api';
import {
  Bem,
  Construcao,
  Benfeitoria,
  Rebanho,
  NaturezaOperacao,
  ParametroLegislacao,
  Distancia,
} from '../types';

// ==================== Bem ====================

export async function listarBens(): Promise<Bem[]> {
  const { data } = await api.get('/tabelabasica/bem/listar');
  return data;
}

export async function incluirBem(bem: Partial<Bem>): Promise<Bem> {
  const { data } = await api.post('/tabelabasica/bem/incluir', bem);
  return data;
}

export async function alterarBem(bem: Bem): Promise<Bem> {
  const { data } = await api.put(`/tabelabasica/bem/${bem.codigo}`, bem);
  return data;
}

// ==================== Construção ====================

export async function listarConstrucoes(): Promise<Construcao[]> {
  const { data } = await api.get('/tabelabasica/construcao/listar');
  return data;
}

export async function incluirConstrucao(c: Partial<Construcao>): Promise<Construcao> {
  const { data } = await api.post('/tabelabasica/construcao/incluir', c);
  return data;
}

export async function alterarConstrucao(c: Construcao): Promise<Construcao> {
  const { data } = await api.put(`/tabelabasica/construcao/${c.codigo}`, c);
  return data;
}

// ==================== Benfeitoria ====================

export async function listarBenfeitorias(): Promise<Benfeitoria[]> {
  const { data } = await api.get('/tabelabasica/benfeitoria/listar');
  return data;
}

export async function incluirBenfeitoria(b: Partial<Benfeitoria>): Promise<Benfeitoria> {
  const { data } = await api.post('/tabelabasica/benfeitoria/incluir', b);
  return data;
}

export async function alterarBenfeitoria(b: Benfeitoria): Promise<Benfeitoria> {
  const { data } = await api.put(`/tabelabasica/benfeitoria/${b.codigo}`, b);
  return data;
}

// ==================== Rebanho ====================

export async function listarRebanhos(): Promise<Rebanho[]> {
  const { data } = await api.get('/tabelabasica/rebanho/listar');
  return data;
}

export async function incluirRebanho(r: Partial<Rebanho>): Promise<Rebanho> {
  const { data } = await api.post('/tabelabasica/rebanho/incluir', r);
  return data;
}

export async function alterarRebanho(r: Rebanho): Promise<Rebanho> {
  const { data } = await api.put(`/tabelabasica/rebanho/${r.codigo}`, r);
  return data;
}

// ==================== Natureza Operação ====================

export async function listarNaturezasOperacao(): Promise<NaturezaOperacao[]> {
  const { data } = await api.get('/tabelabasica/naturezadaoperacao/listar');
  return data;
}

export async function incluirNaturezaOperacao(n: Partial<NaturezaOperacao>): Promise<NaturezaOperacao> {
  const { data } = await api.post('/tabelabasica/naturezadaoperacao/incluir', n);
  return data;
}

export async function alterarNaturezaOperacao(n: NaturezaOperacao): Promise<NaturezaOperacao> {
  const { data } = await api.put(`/tabelabasica/naturezadaoperacao/${n.codigo}`, n);
  return data;
}

// ==================== Parâmetros da Legislação ====================

export async function listarParametrosLegislacao(): Promise<ParametroLegislacao[]> {
  const { data } = await api.get('/tabelabasica/parametrosdalegislacao/listar');
  return data;
}

export async function incluirParametroLegislacao(p: Partial<ParametroLegislacao>): Promise<ParametroLegislacao> {
  const { data } = await api.post('/tabelabasica/parametrosdalegislacao/incluir', p);
  return data;
}

export async function alterarParametroLegislacao(p: ParametroLegislacao): Promise<ParametroLegislacao> {
  const { data } = await api.put(`/tabelabasica/parametrosdalegislacao/${p.codigo}`, p);
  return data;
}

// ==================== Distância ====================

export async function consultarDistancia(
  municipioOrigem: string,
  municipioDestino: string
): Promise<Distancia> {
  const { data } = await api.get('/tabelabasica/distancia/consultar', {
    params: { municipioOrigem, municipioDestino },
  });
  return data;
}

export async function incluirDistancia(d: Partial<Distancia>): Promise<Distancia> {
  const { data } = await api.post('/tabelabasica/distancia/incluir', d);
  return data;
}
