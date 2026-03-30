import React from 'react';
import FormSection from '../../../components/common/FormSection';
import { GIAITCDDoacao, TipoProtocolo } from '../../../types';
import { formatCurrency } from '../../../utils/formatters';

interface Props {
  gia: GIAITCDDoacao;
  onPrevious: () => void;
}

const DemonstrativoCalculoDoacao: React.FC<Props> = ({ gia, onPrevious }) => {
  return (
    <div>
      <FormSection title="Demonstrativo de Calculo">
        <div className="sefaz-data-grid">
          <div className="sefaz-td-rotulo">Valor Total dos Bens Declarados:</div>
          <div className="sefaz-td-campo-saida">{formatCurrency(gia.valorTotalBensDeclarados)}</div>
          <div className="sefaz-td-rotulo">Valor Total Arbitrado:</div>
          <div className="sefaz-td-campo-saida">{formatCurrency(gia.valorTotalArbitrado)}</div>

          <div className="sefaz-td-rotulo">Base de Calculo Tributavel:</div>
          <div className="sefaz-td-campo-saida">{formatCurrency(gia.valorBaseCalculoTributavel)}</div>
          <div className="sefaz-td-rotulo">Percentual Doacao:</div>
          <div className="sefaz-td-campo-saida">{gia.fracaoIdeal}%</div>

          <div className="sefaz-td-rotulo">Valor do ITCD:</div>
          <div className="sefaz-td-campo-saida" style={{ fontWeight: 'bold' }}>
            {formatCurrency(gia.valorITCD)}
          </div>
          <div className="sefaz-td-rotulo">Valor TSE:</div>
          <div className="sefaz-td-campo-saida">{formatCurrency(gia.valorTSE)}</div>

          <div className="sefaz-td-rotulo">Correcao Monetaria:</div>
          <div className="sefaz-td-campo-saida">{formatCurrency(gia.valorCorrecaoMonetaria)}</div>
          <div className="sefaz-td-rotulo">Juros:</div>
          <div className="sefaz-td-campo-saida">{formatCurrency(gia.valorJuros)}</div>

          <div className="sefaz-td-rotulo">Multa:</div>
          <div className="sefaz-td-campo-saida">{formatCurrency(gia.valorMulta)}</div>
          <div className="sefaz-td-rotulo">Valor para Recolhimento:</div>
          <div className="sefaz-td-campo-saida" style={{ fontWeight: 'bold', color: '#CC0000' }}>
            {formatCurrency(gia.valorRecolhimento)}
          </div>
        </div>
      </FormSection>

      <div className="sefaz-btn-group">
        <button type="button" className="sefaz-btn" onClick={onPrevious}>
          &laquo; Anterior
        </button>
        <button type="button" className="sefaz-btn sefaz-btn--success">
          Confirmar GIA-ITCD
        </button>
      </div>
    </div>
  );
};

export default DemonstrativoCalculoDoacao;
