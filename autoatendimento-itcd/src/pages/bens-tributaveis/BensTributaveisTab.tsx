import React, { useState, useEffect } from 'react';
import FormSection from '../../components/common/FormSection';
import Modal from '../../components/common/Modal';
import { BemTributavel, Bem, TipoBem, SimNao } from '../../types';
import { formatCurrency } from '../../utils/formatters';
import { listarBens } from '../../services/tabelasBasicasService';

interface Props {
  bensTributaveis: BemTributavel[];
  giaItcdCodigo: number;
  onChange: (bens: BemTributavel[]) => void;
  onNext: () => void;
  onPrevious: () => void;
}

const BensTributaveisTab: React.FC<Props> = ({
  bensTributaveis,
  giaItcdCodigo,
  onChange,
  onNext,
  onPrevious,
}) => {
  const [bens, setBens] = useState<Bem[]>([]);
  const [showForm, setShowForm] = useState(false);
  const [editIndex, setEditIndex] = useState<number | null>(null);
  const [formData, setFormData] = useState<Partial<BemTributavel>>({});

  useEffect(() => {
    listarBens().then(setBens).catch(() => {});
  }, []);

  const handleAdd = () => {
    setFormData({
      giaItcdCodigo,
      bemVo: undefined,
      valorMercado: 0,
      valorInformadoContribuinte: 0,
      descricaoBemTributavel: '',
      bemParticular: SimNao.NAO,
      isencaoPrevista: SimNao.NAO,
      concordaComValorArbitrado: SimNao.NAO,
    });
    setEditIndex(null);
    setShowForm(true);
  };

  const handleEdit = (index: number) => {
    setFormData({ ...bensTributaveis[index] });
    setEditIndex(index);
    setShowForm(true);
  };

  const handleRemove = (index: number) => {
    const updated = bensTributaveis.filter((_, i) => i !== index);
    onChange(updated);
  };

  const handleSave = () => {
    if (!formData.bemVo || !formData.descricaoBemTributavel) {
      alert('Preencha o tipo do bem e a descricao.');
      return;
    }
    if (editIndex !== null) {
      const updated = [...bensTributaveis];
      updated[editIndex] = { ...updated[editIndex], ...formData } as BemTributavel;
      onChange(updated);
    } else {
      onChange([...bensTributaveis, { codigo: Date.now(), ...formData } as BemTributavel]);
    }
    setShowForm(false);
  };

  const totalDeclarado = bensTributaveis.reduce((s, b) => s + b.valorInformadoContribuinte, 0);

  return (
    <div>
      <FormSection title="Bens Tributaveis">
        <div style={{ marginBottom: '10px' }}>
          <button type="button" className="sefaz-btn sefaz-btn--primary" onClick={handleAdd}>
            + Incluir Bem Tributavel
          </button>
        </div>

        {bensTributaveis.length === 0 ? (
          <div style={{ padding: '20px', textAlign: 'center', color: '#666' }}>
            Nenhum bem tributavel cadastrado. Clique em "Incluir Bem Tributavel" para adicionar.
          </div>
        ) : (
          <>
            <table className="sefaz-table">
              <thead>
                <tr>
                  <th>Tipo do Bem</th>
                  <th>Descricao</th>
                  <th>Valor Informado</th>
                  <th>Valor de Mercado</th>
                  <th>Bem Particular</th>
                  <th>Acoes</th>
                </tr>
              </thead>
              <tbody>
                {bensTributaveis.map((bem, index) => (
                  <tr key={index}>
                    <td>{bem.bemVo?.descricao || '-'}</td>
                    <td>{bem.descricaoBemTributavel}</td>
                    <td>{formatCurrency(bem.valorInformadoContribuinte)}</td>
                    <td>{formatCurrency(bem.valorMercado)}</td>
                    <td>{bem.bemParticular === SimNao.SIM ? 'Sim' : 'Nao'}</td>
                    <td>
                      <button
                        type="button"
                        className="sefaz-btn"
                        onClick={() => handleEdit(index)}
                        style={{ marginRight: '4px' }}
                      >
                        Editar
                      </button>
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
              <tfoot>
                <tr>
                  <td colSpan={2} style={{ fontWeight: 'bold', textAlign: 'right' }}>
                    Total:
                  </td>
                  <td style={{ fontWeight: 'bold' }}>{formatCurrency(totalDeclarado)}</td>
                  <td colSpan={3}>&nbsp;</td>
                </tr>
              </tfoot>
            </table>
          </>
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

      {/* Modal de inclusão/edição */}
      <Modal
        isOpen={showForm}
        onClose={() => setShowForm(false)}
        title={editIndex !== null ? 'Alterar Bem Tributavel' : 'Incluir Bem Tributavel'}
      >
        <div style={{ padding: '10px' }}>
          <div className="sefaz-form-row">
            <div className="sefaz-td-rotulo-entrada">
              Tipo do Bem:<span className="sefaz-required">*</span>
            </div>
            <div className="sefaz-td-campo">
              <select
                className="sefaz-select"
                style={{ width: '100%' }}
                value={formData.bemVo?.codigo || ''}
                onChange={(e) => {
                  const bem = bens.find((b) => b.codigo === Number(e.target.value));
                  setFormData({ ...formData, bemVo: bem });
                }}
              >
                <option value="">Selecione...</option>
                {bens.map((b) => (
                  <option key={b.codigo} value={b.codigo}>
                    {b.descricao}
                  </option>
                ))}
              </select>
            </div>
          </div>

          <div className="sefaz-form-row">
            <div className="sefaz-td-rotulo-entrada">
              Descricao:<span className="sefaz-required">*</span>
            </div>
            <div className="sefaz-td-campo">
              <textarea
                className="sefaz-textarea"
                style={{ width: '100%' }}
                rows={3}
                value={formData.descricaoBemTributavel || ''}
                onChange={(e) => setFormData({ ...formData, descricaoBemTributavel: e.target.value })}
              />
            </div>
          </div>

          <div className="sefaz-form-row">
            <div className="sefaz-td-rotulo-entrada">
              Valor Informado (R$):<span className="sefaz-required">*</span>
            </div>
            <div className="sefaz-td-campo">
              <input
                type="number"
                className="sefaz-input-text"
                style={{ width: '200px' }}
                value={formData.valorInformadoContribuinte || ''}
                onChange={(e) =>
                  setFormData({ ...formData, valorInformadoContribuinte: parseFloat(e.target.value) || 0 })
                }
                step="0.01"
                min="0"
              />
            </div>
          </div>

          <div className="sefaz-form-row">
            <div className="sefaz-td-rotulo-entrada">Bem Particular:</div>
            <div className="sefaz-td-campo">
              <select
                className="sefaz-select"
                value={formData.bemParticular || SimNao.NAO}
                onChange={(e) =>
                  setFormData({ ...formData, bemParticular: e.target.value as SimNao })
                }
              >
                <option value={SimNao.NAO}>Nao</option>
                <option value={SimNao.SIM}>Sim</option>
              </select>
            </div>
          </div>

          <div className="sefaz-form-row">
            <div className="sefaz-td-rotulo-entrada">Isencao Prevista em Lei:</div>
            <div className="sefaz-td-campo">
              <select
                className="sefaz-select"
                value={formData.isencaoPrevista || SimNao.NAO}
                onChange={(e) =>
                  setFormData({ ...formData, isencaoPrevista: e.target.value as SimNao })
                }
              >
                <option value={SimNao.NAO}>Nao</option>
                <option value={SimNao.SIM}>Sim</option>
              </select>
            </div>
          </div>

          <div className="sefaz-btn-group">
            <button type="button" className="sefaz-btn" onClick={() => setShowForm(false)}>
              Cancelar
            </button>
            <button type="button" className="sefaz-btn sefaz-btn--primary" onClick={handleSave}>
              Salvar
            </button>
          </div>
        </div>
      </Modal>
    </div>
  );
};

export default BensTributaveisTab;
