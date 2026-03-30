import React, { useState, useEffect } from 'react';
import FormSection from '../../components/common/FormSection';
import Modal from '../../components/common/Modal';
import Message from '../../components/common/Message';
import Loading from '../../components/common/Loading';

interface Column {
  key: string;
  label: string;
  type?: 'text' | 'number' | 'boolean';
}

interface CrudTabelaBasicaProps {
  title: string;
  columns: Column[];
  fetchData: () => Promise<any[]>;
  onSave: (item: any) => Promise<any>;
  onUpdate: (item: any) => Promise<any>;
}

const CrudTabelaBasica: React.FC<CrudTabelaBasicaProps> = ({
  title,
  columns,
  fetchData,
  onSave,
  onUpdate,
}) => {
  const [data, setData] = useState<any[]>([]);
  const [loading, setLoading] = useState(false);
  const [showForm, setShowForm] = useState(false);
  const [editItem, setEditItem] = useState<any | null>(null);
  const [formData, setFormData] = useState<any>({});
  const [message, setMessage] = useState({ type: '' as any, text: '' });

  const loadData = async () => {
    setLoading(true);
    try {
      const result = await fetchData();
      setData(result);
    } catch {
      setMessage({ type: 'error', text: 'Erro ao carregar dados.' });
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadData();
  }, []);

  const handleAdd = () => {
    const initial: any = {};
    columns.forEach((col) => {
      initial[col.key] = col.type === 'number' ? 0 : col.type === 'boolean' ? true : '';
    });
    setFormData(initial);
    setEditItem(null);
    setShowForm(true);
  };

  const handleEdit = (item: any) => {
    setFormData({ ...item });
    setEditItem(item);
    setShowForm(true);
  };

  const handleSave = async () => {
    try {
      if (editItem) {
        await onUpdate(formData);
      } else {
        await onSave(formData);
      }
      setShowForm(false);
      setMessage({ type: 'success', text: 'Registro salvo com sucesso.' });
      loadData();
    } catch {
      setMessage({ type: 'error', text: 'Erro ao salvar registro.' });
    }
  };

  return (
    <div className="sefaz-form">
      {message.text && (
        <Message type={message.type} text={message.text} onClose={() => setMessage({ type: '', text: '' })} />
      )}

      <FormSection title={title}>
        <div style={{ marginBottom: '10px' }}>
          <button type="button" className="sefaz-btn sefaz-btn--primary" onClick={handleAdd}>
            + Incluir
          </button>
          <button type="button" className="sefaz-btn" onClick={() => window.history.back()} style={{ marginLeft: '8px' }}>
            Voltar
          </button>
        </div>

        {loading ? (
          <Loading />
        ) : data.length === 0 ? (
          <div style={{ padding: '20px', textAlign: 'center', color: '#666' }}>
            Nenhum registro encontrado.
          </div>
        ) : (
          <table className="sefaz-table">
            <thead>
              <tr>
                <th>Codigo</th>
                {columns.map((col) => (
                  <th key={col.key}>{col.label}</th>
                ))}
                <th>Acoes</th>
              </tr>
            </thead>
            <tbody>
              {data.map((item, index) => (
                <tr key={index}>
                  <td>{item.codigo}</td>
                  {columns.map((col) => (
                    <td key={col.key}>
                      {col.type === 'boolean'
                        ? item[col.key]
                          ? 'Ativo'
                          : 'Inativo'
                        : col.type === 'number'
                        ? Number(item[col.key]).toLocaleString('pt-BR', { minimumFractionDigits: 2 })
                        : item[col.key]}
                    </td>
                  ))}
                  <td>
                    <button type="button" className="sefaz-btn" onClick={() => handleEdit(item)}>
                      Editar
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </FormSection>

      <Modal isOpen={showForm} onClose={() => setShowForm(false)} title={editItem ? `Alterar ${title}` : `Incluir ${title}`}>
        <div style={{ padding: '10px' }}>
          {columns.map((col) => (
            <div className="sefaz-form-row" key={col.key}>
              <div className="sefaz-td-rotulo-entrada">{col.label}:</div>
              <div className="sefaz-td-campo">
                {col.type === 'boolean' ? (
                  <select
                    className="sefaz-select"
                    value={formData[col.key] ? 'true' : 'false'}
                    onChange={(e) => setFormData({ ...formData, [col.key]: e.target.value === 'true' })}
                  >
                    <option value="true">Ativo</option>
                    <option value="false">Inativo</option>
                  </select>
                ) : (
                  <input
                    type={col.type === 'number' ? 'number' : 'text'}
                    className="sefaz-input-text sefaz-input-text--full"
                    value={formData[col.key] || ''}
                    onChange={(e) =>
                      setFormData({
                        ...formData,
                        [col.key]: col.type === 'number' ? parseFloat(e.target.value) || 0 : e.target.value,
                      })
                    }
                    step={col.type === 'number' ? '0.01' : undefined}
                  />
                )}
              </div>
            </div>
          ))}
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

export default CrudTabelaBasica;
