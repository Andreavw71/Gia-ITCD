import React from 'react';
import FormSection from '../../../components/common/FormSection';
import { BemDireito, Doador, Donatario, DivisaoBemDonatario, PessoaFisica } from '../../../types/giaitcmd';

interface Props {
  bensDireitos: BemDireito[];
  doadores: Doador[];
  donatarios: Donatario[];
  divisao: DivisaoBemDonatario[];
  onChange: (d: DivisaoBemDonatario[]) => void;
  disabled: boolean;
}

const DivisaoBensSection: React.FC<Props> = ({ bensDireitos, doadores, donatarios, divisao, onChange, disabled }) => {
  const getPercentual = (bemId: number, doadorId: number, donatarioId: number): number => {
    return divisao.find((d) => d.bemId === bemId && d.doadorId === doadorId && d.donatarioId === donatarioId)?.percentual || 0;
  };

  const setPercentual = (bemId: number, doadorId: number, donatarioId: number, percentual: number) => {
    const existing = divisao.findIndex((d) => d.bemId === bemId && d.doadorId === doadorId && d.donatarioId === donatarioId);
    const updated = [...divisao];
    if (existing >= 0) {
      updated[existing] = { ...updated[existing], percentual };
    } else {
      updated.push({ bemId, doadorId, donatarioId, percentual });
    }
    onChange(updated);
  };

  const getTotalDoador = (bemId: number, doadorId: number): number => {
    return divisao
      .filter((d) => d.bemId === bemId && d.doadorId === doadorId)
      .reduce((s, d) => s + d.percentual, 0);
  };

  if (bensDireitos.length === 0 || donatarios.length === 0) {
    return (
      <FormSection title="Divisao dos Bens e Direitos Doados">
        <div style={{ padding: '20px', textAlign: 'center', color: '#666' }}>
          Cadastre os Bens e Direitos e os Donatarios antes de configurar a divisao.
        </div>
      </FormSection>
    );
  }

  // Auto-fill 100% if only one donatario
  if (donatarios.length === 1 && divisao.length === 0) {
    const autoDivisao: DivisaoBemDonatario[] = [];
    bensDireitos.forEach((bem) => {
      doadores.forEach((doador) => {
        autoDivisao.push({ bemId: bem.id, doadorId: doador.id, donatarioId: donatarios[0].id, percentual: 100 });
      });
    });
    onChange(autoDivisao);
  }

  return (
    <div>
      <FormSection title="Divisao dos Bens e Direitos Doados">
        <div className="sefaz-msg sefaz-msg--info" style={{ marginBottom: '12px' }}>
          Distribua o percentual de cada bem ou direito entre os donatarios.
          O somatório deve ser igual a 100% para cada doador em cada bem.
        </div>

        {bensDireitos.map((bem, bi) => (
          <div key={bem.id} style={{ marginBottom: '16px', border: '1px solid #ccc', padding: '10px' }}>
            <div className="sefaz-tr-titulo" style={{ marginBottom: '8px' }}>
              Bem {bi + 1}: {bem.especie} - {bem.descricao || 'Sem descricao'}
            </div>

            {doadores.map((doador, di) => {
              const total = getTotalDoador(bem.id, doador.id);
              const isValid = Math.abs(total - 100) < 0.01;
              return (
                <div key={doador.id} style={{ marginLeft: '16px', marginBottom: '8px' }}>
                  <div style={{ fontWeight: 'bold', fontSize: '11px', marginBottom: '4px' }}>
                    Doador {di + 1}: {(doador.dados as PessoaFisica).nome}
                  </div>
                  <table className="sefaz-table" style={{ maxWidth: '500px' }}>
                    <thead>
                      <tr>
                        <th>Donatario</th>
                        <th style={{ textAlign: 'right', width: '120px' }}>Percentual (%)</th>
                      </tr>
                    </thead>
                    <tbody>
                      {donatarios.map((donatario) => (
                        <tr key={donatario.id}>
                          <td>{(donatario.dados as PessoaFisica).nome || `Donatario ${donatario.id}`}</td>
                          <td style={{ textAlign: 'right' }}>
                            <input type="number" className="sefaz-input-text"
                              style={{ width: '80px', textAlign: 'right' }}
                              value={getPercentual(bem.id, doador.id, donatario.id)}
                              onChange={(e) => setPercentual(bem.id, doador.id, donatario.id, parseFloat(e.target.value) || 0)}
                              disabled={disabled} step="0.01" min="0" max="100" />
                          </td>
                        </tr>
                      ))}
                    </tbody>
                    <tfoot>
                      <tr>
                        <td style={{ fontWeight: 'bold', textAlign: 'right' }}>Total:</td>
                        <td style={{ textAlign: 'right', fontWeight: 'bold', color: isValid ? '#006633' : '#CC0000' }}>
                          {total.toFixed(2)}%
                          {!isValid && ' (deve ser 100%)'}
                        </td>
                      </tr>
                    </tfoot>
                  </table>
                </div>
              );
            })}
          </div>
        ))}
      </FormSection>
    </div>
  );
};

export default DivisaoBensSection;
