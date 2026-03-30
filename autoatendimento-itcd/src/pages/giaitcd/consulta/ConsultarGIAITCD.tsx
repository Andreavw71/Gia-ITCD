import React, { useState } from 'react';
import FormSection from '../../../components/common/FormSection';
import Loading from '../../../components/common/Loading';
import { GIAITCD, StatusGIAITCD, TipoGIA } from '../../../types';
import { pesquisarGIAITCD } from '../../../services/giaitcdService';
import { formatCPFOrCNPJ, formatCurrency, formatDate } from '../../../utils/formatters';

const statusLabels: Record<string, string> = {
  PENDENTE_PREENCHIMENTO: 'Pendente de Preenchimento',
  PENDENTE_PROTOCOLO: 'Pendente de Protocolo',
  PROTOCOLADA: 'Protocolada',
  EM_AVALIACAO: 'Em Avaliacao',
  AVALIADA: 'Avaliada',
  CANCELADA: 'Cancelada',
  INATIVA: 'Inativa',
};

const tipoGIALabels: Record<string, string> = {
  INVENTARIO_ARROLAMENTO: 'Inventario/Arrolamento',
  DOACAO: 'Doacao/Outros',
  SEPARACAO_DIVORCIO: 'Separacao/Divorcio/Partilha',
};

const ConsultarGIAITCD: React.FC = () => {
  const [codigoGIA, setCodigoGIA] = useState('');
  const [cpfDeclarante, setCpfDeclarante] = useState('');
  const [resultados, setResultados] = useState<GIAITCD[]>([]);
  const [loading, setLoading] = useState(false);
  const [searched, setSearched] = useState(false);

  const handlePesquisar = async () => {
    setLoading(true);
    setSearched(true);
    try {
      const params: any = {};
      if (codigoGIA) params.codigo = Number(codigoGIA);
      if (cpfDeclarante) params.cpfDeclarante = cpfDeclarante.replace(/\D/g, '');
      const data = await pesquisarGIAITCD(params);
      setResultados(data);
    } catch {
      setResultados([]);
    } finally {
      setLoading(false);
    }
  };

  const handleLimpar = () => {
    setCodigoGIA('');
    setCpfDeclarante('');
    setResultados([]);
    setSearched(false);
  };

  return (
    <div className="sefaz-form">
      <FormSection title="Consultar GIA-ITCD">
        <div className="sefaz-form-row">
          <div className="sefaz-td-rotulo-entrada">Numero da GIA:</div>
          <div className="sefaz-td-campo">
            <input
              type="text"
              className="sefaz-input-text"
              style={{ width: '200px' }}
              value={codigoGIA}
              onChange={(e) => setCodigoGIA(e.target.value)}
            />
          </div>
        </div>
        <div className="sefaz-form-row">
          <div className="sefaz-td-rotulo-entrada">CPF/CNPJ Declarante:</div>
          <div className="sefaz-td-campo">
            <input
              type="text"
              className="sefaz-input-text"
              style={{ width: '200px' }}
              value={cpfDeclarante}
              onChange={(e) => setCpfDeclarante(e.target.value)}
              placeholder="CPF ou CNPJ"
            />
          </div>
        </div>
        <div className="sefaz-btn-group">
          <button type="button" className="sefaz-btn" onClick={handleLimpar}>
            Limpar
          </button>
          <button type="button" className="sefaz-btn sefaz-btn--primary" onClick={handlePesquisar}>
            Pesquisar
          </button>
        </div>
      </FormSection>

      {loading && <Loading />}

      {searched && !loading && (
        <FormSection title="Resultado da Pesquisa">
          {resultados.length === 0 ? (
            <div style={{ padding: '20px', textAlign: 'center', color: '#666' }}>
              Nenhuma GIA-ITCD encontrada com os criterios informados.
            </div>
          ) : (
            <table className="sefaz-table">
              <thead>
                <tr>
                  <th>N. GIA</th>
                  <th>Tipo</th>
                  <th>Declarante</th>
                  <th>CPF/CNPJ</th>
                  <th>Data Criacao</th>
                  <th>Valor ITCD</th>
                  <th>Status</th>
                </tr>
              </thead>
              <tbody>
                {resultados.map((gia) => (
                  <tr key={gia.codigo}>
                    <td>
                      <a href={`/giaitcd/detalhe/${gia.codigo}`}>{gia.codigo}</a>
                    </td>
                    <td>{tipoGIALabels[gia.tipoGIA] || gia.tipoGIA}</td>
                    <td>{gia.responsavelVo?.nomeContribuinte}</td>
                    <td>{gia.responsavelVo?.numrDocumento ? formatCPFOrCNPJ(gia.responsavelVo.numrDocumento) : ''}</td>
                    <td>{formatDate(gia.dataCriacao)}</td>
                    <td>{formatCurrency(gia.valorITCD)}</td>
                    <td>{statusLabels[gia.statusVo?.descricao] || gia.statusVo?.descricao}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          )}
        </FormSection>
      )}
    </div>
  );
};

export default ConsultarGIAITCD;
