import React, { useState } from 'react';
import FormSection from '../../../components/common/FormSection';
import Modal from '../../../components/common/Modal';
import {
  BemDireito, TipoBemDireito, SubtipoBemMovel, SubtipoBemImovel, getEspecies,
} from '../../../types/giaitcmd';
import { formatCurrency } from '../../../utils/formatters';

interface Props {
  bensDireitos: BemDireito[];
  temSegundoDoador: boolean;
  onChange: (bens: BemDireito[]) => void;
  disabled: boolean;
}

const subtiposMovel = [
  { value: SubtipoBemMovel.AERONAVE, label: 'Aeronave' },
  { value: SubtipoBemMovel.EMBARCACAO, label: 'Embarcacao' },
  { value: SubtipoBemMovel.OUTROS_MOVEIS, label: 'Outros bens moveis' },
  { value: SubtipoBemMovel.SEMOVENTE, label: 'Semovente' },
  { value: SubtipoBemMovel.PARTICIPACAO_SOCIETARIA, label: 'Participacao Societaria' },
  { value: SubtipoBemMovel.VALOR_MONETARIO, label: 'Valor monetario' },
  { value: SubtipoBemMovel.VEICULO_AUTOMOTOR, label: 'Veiculo automotor' },
];

const subtiposImovel = [
  { value: SubtipoBemImovel.IMOVEL_RURAL, label: 'Imovel Rural' },
  { value: SubtipoBemImovel.IMOVEL_URBANO, label: 'Imovel Urbano' },
];

const BensDireitosSection: React.FC<Props> = ({ bensDireitos, temSegundoDoador, onChange, disabled }) => {
  const [showForm, setShowForm] = useState(false);
  const [tipo, setTipo] = useState<TipoBemDireito | ''>('');
  const [subtipo, setSubtipo] = useState('');
  const [especie, setEspecie] = useState('');
  const [formData, setFormData] = useState<Partial<BemDireito>>({});
  const [editIndex, setEditIndex] = useState<number | null>(null);

  const especies = subtipo ? getEspecies(subtipo) : [];

  const handleAddBem = () => {
    if (!tipo || !subtipo || !especie) {
      alert('Selecione o Tipo, Subtipo e Especie do bem.');
      return;
    }
    setFormData({
      tipo: tipo as TipoBemDireito,
      subtipo,
      especie,
      descricao: '',
      valorTotalDeclarado: 0,
      valorEstimado: null,
      concordaValorEstimado: null,
      valorArbitrado: null,
      concordaValorArbitrado: null,
      bemComumCasal: false,
      percentualTransmitidoDoador1: 100,
      percentualTransmitidoDoador2: 0,
      percentualTotalTransmitido: 100,
      valorBemTransmitido: 0,
      valorBemTransmitidoDoador1: 0,
      valorBemTransmitidoDoador2: 0,
      pertenceEmpresaDoacao: false,
      dadosEspecificos: {},
    });
    setEditIndex(null);
    setShowForm(true);
  };

  const handleSave = () => {
    const bem: BemDireito = {
      id: editIndex !== null ? bensDireitos[editIndex].id : Date.now(),
      ...formData as BemDireito,
    };
    // Auto-calc valor transmitido
    const baseValue = bem.concordaValorEstimado && bem.valorEstimado
      ? bem.valorEstimado
      : bem.valorTotalDeclarado;
    bem.valorBemTransmitido = baseValue * (bem.percentualTotalTransmitido / 100);
    bem.valorBemTransmitidoDoador1 = baseValue * (bem.percentualTransmitidoDoador1 / 100);
    bem.valorBemTransmitidoDoador2 = baseValue * (bem.percentualTransmitidoDoador2 / 100);

    if (editIndex !== null) {
      const updated = [...bensDireitos];
      updated[editIndex] = bem;
      onChange(updated);
    } else {
      onChange([...bensDireitos, bem]);
    }
    setShowForm(false);
    setTipo('');
    setSubtipo('');
    setEspecie('');
  };

  const handleRemove = (index: number) => {
    onChange(bensDireitos.filter((_, i) => i !== index));
  };

  const handleEdit = (index: number) => {
    const bem = bensDireitos[index];
    setTipo(bem.tipo);
    setSubtipo(bem.subtipo);
    setEspecie(bem.especie);
    setFormData(bem);
    setEditIndex(index);
    setShowForm(true);
  };

  return (
    <div>
      <FormSection title="Bens e Direitos Doados">
        {/* Seleção Tipo / Subtipo / Espécie */}
        <div className="sefaz-form-row">
          <div className="sefaz-td-rotulo-entrada">Tipo do bem ou direito:<span className="sefaz-required">*</span></div>
          <div className="sefaz-td-campo">
            <select className="sefaz-select" value={tipo}
              onChange={(e) => { setTipo(e.target.value as TipoBemDireito); setSubtipo(''); setEspecie(''); }}
              disabled={disabled} style={{ width: '200px' }}>
              <option value="">Selecione...</option>
              <option value={TipoBemDireito.MOVEL}>Movel</option>
              <option value={TipoBemDireito.IMOVEL}>Imovel</option>
            </select>
          </div>
        </div>

        {tipo && (
          <div className="sefaz-form-row">
            <div className="sefaz-td-rotulo-entrada">Subtipo:<span className="sefaz-required">*</span></div>
            <div className="sefaz-td-campo">
              <select className="sefaz-select" value={subtipo}
                onChange={(e) => { setSubtipo(e.target.value); setEspecie(''); }}
                disabled={disabled} style={{ width: '300px' }}>
                <option value="">Selecione...</option>
                {(tipo === TipoBemDireito.MOVEL ? subtiposMovel : subtiposImovel).map((s) => (
                  <option key={s.value} value={s.value}>{s.label}</option>
                ))}
              </select>
            </div>
          </div>
        )}

        {subtipo && (
          <div className="sefaz-form-row">
            <div className="sefaz-td-rotulo-entrada">Especie:<span className="sefaz-required">*</span></div>
            <div className="sefaz-td-campo">
              <select className="sefaz-select" value={especie}
                onChange={(e) => setEspecie(e.target.value)}
                disabled={disabled} style={{ width: '400px' }}>
                <option value="">Selecione...</option>
                {especies.map((esp) => (
                  <option key={esp} value={esp}>{esp}</option>
                ))}
              </select>
            </div>
          </div>
        )}

        {especie && (
          <div style={{ marginTop: '8px', textAlign: 'center' }}>
            <button type="button" className="sefaz-btn sefaz-btn--primary" onClick={handleAddBem} disabled={disabled}>
              + Cadastrar Bem ou Direito
            </button>
          </div>
        )}
      </FormSection>

      {/* Lista de Bens Cadastrados */}
      {bensDireitos.length > 0 && (
        <FormSection title="Bens e Direitos Cadastrados">
          <table className="sefaz-table">
            <thead>
              <tr>
                <th>#</th>
                <th>Especie</th>
                <th>Descricao</th>
                <th style={{ textAlign: 'right' }}>Valor Declarado</th>
                <th style={{ textAlign: 'right' }}>Valor Estimado</th>
                <th style={{ textAlign: 'right' }}>% Transmitido</th>
                <th style={{ textAlign: 'right' }}>Valor Transmitido</th>
                <th>Acoes</th>
              </tr>
            </thead>
            <tbody>
              {bensDireitos.map((bem, i) => (
                <tr key={bem.id}>
                  <td>{i + 1}</td>
                  <td>{bem.especie}</td>
                  <td>{bem.descricao || '-'}</td>
                  <td style={{ textAlign: 'right' }}>{formatCurrency(bem.valorTotalDeclarado)}</td>
                  <td style={{ textAlign: 'right' }}>{bem.valorEstimado != null ? formatCurrency(bem.valorEstimado) : 'Nao realizado'}</td>
                  <td style={{ textAlign: 'right' }}>{bem.percentualTotalTransmitido.toFixed(2)}%</td>
                  <td style={{ textAlign: 'right', fontWeight: 'bold' }}>{formatCurrency(bem.valorBemTransmitido)}</td>
                  <td>
                    <button type="button" className="sefaz-btn" onClick={() => handleEdit(i)} style={{ marginRight: '4px' }}>Editar</button>
                    <button type="button" className="sefaz-btn sefaz-btn--danger" onClick={() => handleRemove(i)}>Excluir</button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </FormSection>
      )}

      {/* Modal de Cadastro do Bem */}
      <Modal isOpen={showForm} onClose={() => setShowForm(false)}
        title={editIndex !== null ? 'Editar Bem ou Direito' : `Cadastrar ${especie}`}>
        <div style={{ padding: '10px' }}>
          <div style={{ background: '#f0f0f0', padding: '6px 10px', marginBottom: '10px', fontSize: '11px' }}>
            <strong>Tipo:</strong> {tipo === TipoBemDireito.MOVEL ? 'Movel' : 'Imovel'} |
            <strong> Subtipo:</strong> {subtipo} |
            <strong> Especie:</strong> {especie}
          </div>

          <div className="sefaz-form-row">
            <div className="sefaz-td-rotulo-entrada">Descricao:<span className="sefaz-required">*</span></div>
            <div className="sefaz-td-campo">
              <textarea className="sefaz-textarea" rows={3} style={{ width: '100%' }}
                value={formData.descricao || ''}
                onChange={(e) => setFormData({ ...formData, descricao: e.target.value })} />
            </div>
          </div>

          <div className="sefaz-form-row">
            <div className="sefaz-td-rotulo-entrada">Valor Total Declarado (R$):<span className="sefaz-required">*</span></div>
            <div className="sefaz-td-campo">
              <input type="number" className="sefaz-input-text" style={{ width: '200px' }}
                value={formData.valorTotalDeclarado || ''}
                onChange={(e) => setFormData({ ...formData, valorTotalDeclarado: parseFloat(e.target.value) || 0 })}
                step="0.01" min="0" />
            </div>
          </div>

          <div className="sefaz-form-row">
            <div className="sefaz-td-rotulo-entrada">Valor Estimado (R$):</div>
            <div className="sefaz-td-campo">
              <span style={{ color: '#999' }}>Nao realizado</span>
            </div>
          </div>

          {temSegundoDoador && (
            <div className="sefaz-form-row">
              <div className="sefaz-td-rotulo-entrada">Bem comum do casal?</div>
              <div className="sefaz-td-campo">
                <label style={{ marginRight: '12px' }}>
                  <input type="radio" checked={formData.bemComumCasal === true}
                    onChange={() => setFormData({ ...formData, bemComumCasal: true })} /> SIM
                </label>
                <label>
                  <input type="radio" checked={formData.bemComumCasal === false}
                    onChange={() => setFormData({ ...formData, bemComumCasal: false })} /> NAO
                </label>
              </div>
            </div>
          )}

          {formData.bemComumCasal ? (
            <>
              <div className="sefaz-form-row">
                <div className="sefaz-td-rotulo-entrada">% Doador 01:</div>
                <div className="sefaz-td-campo">
                  <input type="number" className="sefaz-input-text" style={{ width: '100px' }}
                    value={formData.percentualTransmitidoDoador1 || ''}
                    onChange={(e) => {
                      const v1 = parseFloat(e.target.value) || 0;
                      setFormData({ ...formData, percentualTransmitidoDoador1: v1, percentualTotalTransmitido: v1 + (formData.percentualTransmitidoDoador2 || 0) });
                    }}
                    step="0.01" min="0" max="100" /> %
                </div>
              </div>
              <div className="sefaz-form-row">
                <div className="sefaz-td-rotulo-entrada">% Doador 02:</div>
                <div className="sefaz-td-campo">
                  <input type="number" className="sefaz-input-text" style={{ width: '100px' }}
                    value={formData.percentualTransmitidoDoador2 || ''}
                    onChange={(e) => {
                      const v2 = parseFloat(e.target.value) || 0;
                      setFormData({ ...formData, percentualTransmitidoDoador2: v2, percentualTotalTransmitido: (formData.percentualTransmitidoDoador1 || 0) + v2 });
                    }}
                    step="0.01" min="0" max="100" /> %
                </div>
              </div>
              <div className="sefaz-form-row">
                <div className="sefaz-td-rotulo-entrada">% Total Transmitido:</div>
                <div className="sefaz-td-campo">
                  <strong>{(formData.percentualTotalTransmitido || 0).toFixed(2)}%</strong>
                </div>
              </div>
            </>
          ) : (
            <div className="sefaz-form-row">
              <div className="sefaz-td-rotulo-entrada">% Total do bem a ser transmitido:<span className="sefaz-required">*</span></div>
              <div className="sefaz-td-campo">
                <input type="number" className="sefaz-input-text" style={{ width: '100px' }}
                  value={formData.percentualTotalTransmitido || ''}
                  onChange={(e) => setFormData({ ...formData, percentualTotalTransmitido: parseFloat(e.target.value) || 0, percentualTransmitidoDoador1: parseFloat(e.target.value) || 0 })}
                  step="0.01" min="0" max="100" /> %
              </div>
            </div>
          )}

          <div className="sefaz-btn-group">
            <button type="button" className="sefaz-btn" onClick={() => setShowForm(false)}>Cancelar</button>
            <button type="button" className="sefaz-btn sefaz-btn--primary" onClick={handleSave}>
              {editIndex !== null ? 'Salvar Alteracoes' : 'Incluir Bem ou Direito'}
            </button>
          </div>
        </div>
      </Modal>
    </div>
  );
};

export default BensDireitosSection;
