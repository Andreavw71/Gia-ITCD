import React, { useState } from 'react';
import FormSection from '../../components/common/FormSection';
import Modal from '../../components/common/Modal';
import ContribuinteSearch from '../../components/common/ContribuinteSearch';
import { Beneficiario, Contribuinte } from '../../types';
import { formatCurrency, formatCPFOrCNPJ } from '../../utils/formatters';

interface Props {
  beneficiarios: Beneficiario[];
  giaItcdCodigo: number;
  onChange: (beneficiarios: Beneficiario[]) => void;
  onNext: () => void;
  onPrevious: () => void;
}

const BeneficiariosTab: React.FC<Props> = ({
  beneficiarios,
  giaItcdCodigo,
  onChange,
  onNext,
  onPrevious,
}) => {
  const [showForm, setShowForm] = useState(false);
  const [editIndex, setEditIndex] = useState<number | null>(null);
  const [formContribuinte, setFormContribuinte] = useState<Contribuinte | undefined>();

  const handleAdd = () => {
    setFormContribuinte(undefined);
    setEditIndex(null);
    setShowForm(true);
  };

  const handleRemove = (index: number) => {
    onChange(beneficiarios.filter((_, i) => i !== index));
  };

  const handleSave = () => {
    if (!formContribuinte?.numrDocumento) {
      alert('Pesquise e selecione o beneficiario.');
      return;
    }

    const newBenef: Beneficiario = {
      codigo: Date.now(),
      giaItcdCodigo,
      pessoaBeneficiaria: formContribuinte,
      valorRecebido: 0,
      valorRecebidoAvaliacao: 0,
      valorItcdBeneficiario: 0,
      valorRecebidoDoacaoSucessiva: 0,
      valorItcdDoacaoSucessiva: 0,
      flagDoacaoSucessiva: 0,
    };

    if (editIndex !== null) {
      const updated = [...beneficiarios];
      updated[editIndex] = { ...updated[editIndex], pessoaBeneficiaria: formContribuinte };
      onChange(updated);
    } else {
      onChange([...beneficiarios, newBenef]);
    }
    setShowForm(false);
  };

  return (
    <div>
      <FormSection title="Beneficiarios">
        <div style={{ marginBottom: '10px' }}>
          <button type="button" className="sefaz-btn sefaz-btn--primary" onClick={handleAdd}>
            + Incluir Beneficiario
          </button>
        </div>

        {beneficiarios.length === 0 ? (
          <div style={{ padding: '20px', textAlign: 'center', color: '#666' }}>
            Nenhum beneficiario cadastrado. Clique em "Incluir Beneficiario" para adicionar.
          </div>
        ) : (
          <table className="sefaz-table">
            <thead>
              <tr>
                <th>CPF/CNPJ</th>
                <th>Nome</th>
                <th>Valor Recebido</th>
                <th>Valor ITCD</th>
                <th>Acoes</th>
              </tr>
            </thead>
            <tbody>
              {beneficiarios.map((benef, index) => (
                <tr key={index}>
                  <td>{formatCPFOrCNPJ(benef.pessoaBeneficiaria.numrDocumento)}</td>
                  <td>{benef.pessoaBeneficiaria.nomeContribuinte}</td>
                  <td>{formatCurrency(benef.valorRecebido)}</td>
                  <td>{formatCurrency(benef.valorItcdBeneficiario || 0)}</td>
                  <td>
                    <button
                      type="button"
                      className="sefaz-btn sefaz-btn--danger"
                      onClick={() => handleRemove(index)}
                    >
                      Excluir
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </FormSection>

      <div className="sefaz-btn-group">
        <button type="button" className="sefaz-btn" onClick={onPrevious}>
          &laquo; Anterior
        </button>
        <button type="button" className="sefaz-btn sefaz-btn--primary" onClick={onNext}>
          Proximo &raquo;
        </button>
      </div>

      <Modal
        isOpen={showForm}
        onClose={() => setShowForm(false)}
        title="Incluir Beneficiario"
      >
        <div style={{ padding: '10px' }}>
          <ContribuinteSearch
            label="CPF / CNPJ Beneficiario"
            value={formContribuinte}
            onChange={setFormContribuinte}
            required
          />
          <div className="sefaz-btn-group">
            <button type="button" className="sefaz-btn" onClick={() => setShowForm(false)}>
              Cancelar
            </button>
            <button type="button" className="sefaz-btn sefaz-btn--primary" onClick={handleSave}>
              Confirmar
            </button>
          </div>
        </div>
      </Modal>
    </div>
  );
};

export default BeneficiariosTab;
