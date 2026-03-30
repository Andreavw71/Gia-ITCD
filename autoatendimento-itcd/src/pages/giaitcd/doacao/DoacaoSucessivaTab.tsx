import React from 'react';
import FormSection from '../../../components/common/FormSection';
import { BemTributavel } from '../../../types';
import { formatCurrency } from '../../../utils/formatters';

interface Props {
  doacaoSucessiva: BemTributavel[];
  onChange: (items: BemTributavel[]) => void;
}

const DoacaoSucessivaTab: React.FC<Props> = ({ doacaoSucessiva }) => {
  return (
    <div>
      <FormSection title="Doacao Sucessiva">
        {doacaoSucessiva.length === 0 ? (
          <div style={{ padding: '20px', textAlign: 'center', color: '#666' }}>
            Nenhuma doacao sucessiva registrada para esta GIA-ITCD.
          </div>
        ) : (
          <table className="sefaz-table">
            <thead>
              <tr>
                <th>Codigo</th>
                <th>Descricao do Bem</th>
                <th>Valor de Mercado</th>
                <th>Valor Informado</th>
              </tr>
            </thead>
            <tbody>
              {doacaoSucessiva.map((bem, index) => (
                <tr key={index}>
                  <td>{bem.codigo}</td>
                  <td>{bem.descricaoBemTributavel}</td>
                  <td>{formatCurrency(bem.valorMercado)}</td>
                  <td>{formatCurrency(bem.valorInformadoContribuinte)}</td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </FormSection>
    </div>
  );
};

export default DoacaoSucessivaTab;
