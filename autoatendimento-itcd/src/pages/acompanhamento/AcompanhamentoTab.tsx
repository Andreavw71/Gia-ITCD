import React, { useState, useEffect } from 'react';
import FormSection from '../../components/common/FormSection';
import { HistoricoLog } from '../../types';
import { formatDate } from '../../utils/formatters';

interface Props {
  giaItcdCodigo: number;
}

const AcompanhamentoTab: React.FC<Props> = ({ giaItcdCodigo }) => {
  const [historico, setHistorico] = useState<HistoricoLog[]>([]);

  // In a real app, this would fetch from the API
  useEffect(() => {
    // fetchHistorico(giaItcdCodigo).then(setHistorico);
  }, [giaItcdCodigo]);

  return (
    <div>
      <FormSection title="Acompanhamento / Historico">
        {historico.length === 0 ? (
          <div style={{ padding: '20px', textAlign: 'center', color: '#666' }}>
            Nenhum registro de acompanhamento disponivel.
          </div>
        ) : (
          <table className="sefaz-table">
            <thead>
              <tr>
                <th>Data</th>
                <th>Descricao</th>
                <th>Usuario</th>
              </tr>
            </thead>
            <tbody>
              {historico.map((log, index) => (
                <tr key={index}>
                  <td>{formatDate(log.dataAlteracao)}</td>
                  <td>{log.descricaoAlteracao}</td>
                  <td>{log.usuario}</td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </FormSection>
    </div>
  );
};

export default AcompanhamentoTab;
