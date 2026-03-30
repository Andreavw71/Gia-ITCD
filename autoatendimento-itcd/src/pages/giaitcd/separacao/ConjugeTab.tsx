import React from 'react';
import FormSection from '../../../components/common/FormSection';
import { GIAITCDSeparacaoDivorcio, BemTributavel } from '../../../types';
import { formatCurrency, formatCPFOrCNPJ } from '../../../utils/formatters';

interface Props {
  gia: GIAITCDSeparacaoDivorcio;
  onChange: (updates: Partial<GIAITCDSeparacaoDivorcio>) => void;
  onNext: () => void;
  onPrevious: () => void;
}

const ConjugeTab: React.FC<Props> = ({ gia, onChange, onNext, onPrevious }) => {
  const renderBensTable = (bens: BemTributavel[], conjugeLabel: string) => (
    <div style={{ marginBottom: '16px' }}>
      <div className="sefaz-tr-subtitulo">Bens Recebidos - {conjugeLabel}</div>
      {bens.length === 0 ? (
        <div style={{ padding: '10px', textAlign: 'center', color: '#666', border: '1px solid #ccc' }}>
          Nenhum bem atribuido a este conjuge.
        </div>
      ) : (
        <table className="sefaz-table">
          <thead>
            <tr>
              <th>Bem</th>
              <th>Descricao</th>
              <th>Valor</th>
            </tr>
          </thead>
          <tbody>
            {bens.map((bem, i) => (
              <tr key={i}>
                <td>{bem.bemVo?.descricao}</td>
                <td>{bem.descricaoBemTributavel}</td>
                <td>{formatCurrency(bem.valorMercado)}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );

  return (
    <div>
      {/* Cônjuge 1 */}
      <FormSection title={`Conjuge 1 - ${gia.conjuge1?.pessoaConjuge?.nomeContribuinte || ''}`}>
        {gia.conjuge1?.pessoaConjuge?.numrDocumento && (
          <div className="sefaz-form-row">
            <div className="sefaz-td-rotulo">CPF:</div>
            <div className="sefaz-td-campo-saida">
              {formatCPFOrCNPJ(gia.conjuge1.pessoaConjuge.numrDocumento)}
            </div>
            <div className="sefaz-td-rotulo">Valor Total Recebido:</div>
            <div className="sefaz-td-campo-saida" style={{ fontWeight: 'bold' }}>
              {formatCurrency(gia.conjuge1.valorTotalRecebido)}
            </div>
          </div>
        )}
        {renderBensTable(gia.conjuge1?.bensRecebidos || [], 'Conjuge 1')}
      </FormSection>

      {/* Cônjuge 2 */}
      <FormSection title={`Conjuge 2 - ${gia.conjuge2?.pessoaConjuge?.nomeContribuinte || ''}`}>
        {gia.conjuge2?.pessoaConjuge?.numrDocumento && (
          <div className="sefaz-form-row">
            <div className="sefaz-td-rotulo">CPF:</div>
            <div className="sefaz-td-campo-saida">
              {formatCPFOrCNPJ(gia.conjuge2.pessoaConjuge.numrDocumento)}
            </div>
            <div className="sefaz-td-rotulo">Valor Total Recebido:</div>
            <div className="sefaz-td-campo-saida" style={{ fontWeight: 'bold' }}>
              {formatCurrency(gia.conjuge2.valorTotalRecebido)}
            </div>
          </div>
        )}
        {renderBensTable(gia.conjuge2?.bensRecebidos || [], 'Conjuge 2')}
      </FormSection>

      <div className="sefaz-msg sefaz-msg--info">
        Para atribuir bens a cada conjuge, utilize a aba "Bens Tributaveis" e selecione o destinatario de cada bem.
      </div>

      <div className="sefaz-btn-group">
        <button type="button" className="sefaz-btn" onClick={onPrevious}>
          &laquo; Anterior
        </button>
        <button type="button" className="sefaz-btn sefaz-btn--primary" onClick={onNext}>
          Proximo &raquo;
        </button>
      </div>
    </div>
  );
};

export default ConjugeTab;
