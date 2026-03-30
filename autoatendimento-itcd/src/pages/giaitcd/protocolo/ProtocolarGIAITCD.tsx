import React, { useState } from 'react';
import FormSection from '../../../components/common/FormSection';
import Message from '../../../components/common/Message';

const ProtocolarGIAITCD: React.FC = () => {
  const [codigoGIA, setCodigoGIA] = useState('');
  const [senha, setSenha] = useState('');
  const [message, setMessage] = useState({ type: '' as any, text: '' });
  const [loading, setLoading] = useState(false);

  const handleProtocolar = async () => {
    if (!codigoGIA || !senha) {
      setMessage({ type: 'error', text: 'Preencha todos os campos obrigatorios.' });
      return;
    }
    setLoading(true);
    setMessage({ type: '', text: '' });
    try {
      // await protocolarGIAITCD(Number(codigoGIA), 'AUTOMATICO');
      setMessage({ type: 'success', text: 'GIA-ITCD protocolada com sucesso!' });
    } catch {
      setMessage({ type: 'error', text: 'Erro ao protocolar. Verifique os dados.' });
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="sefaz-form">
      {message.text && (
        <Message type={message.type} text={message.text} onClose={() => setMessage({ type: '', text: '' })} />
      )}

      <FormSection title="Preencher Processo / Protocolar GIA-ITCD">
        <div className="sefaz-form-row">
          <div className="sefaz-td-rotulo-entrada">
            Numero da GIA:<span className="sefaz-required">*</span>
          </div>
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
          <div className="sefaz-td-rotulo-entrada">
            Senha:<span className="sefaz-required">*</span>
          </div>
          <div className="sefaz-td-campo">
            <input
              type="password"
              className="sefaz-input-text"
              style={{ width: '200px' }}
              value={senha}
              onChange={(e) => setSenha(e.target.value)}
            />
          </div>
        </div>
        <div className="sefaz-btn-group">
          <button type="button" className="sefaz-btn" onClick={() => window.history.back()}>
            Voltar
          </button>
          <button
            type="button"
            className="sefaz-btn sefaz-btn--primary"
            onClick={handleProtocolar}
            disabled={loading}
          >
            {loading ? 'Protocolando...' : 'Protocolar'}
          </button>
        </div>
      </FormSection>

      <FormSection title="Validar Protocolo">
        <div className="sefaz-form-row">
          <div className="sefaz-td-rotulo-entrada">Numero do Protocolo:</div>
          <div className="sefaz-td-campo">
            <input type="text" className="sefaz-input-text" style={{ width: '300px' }} />
          </div>
        </div>
        <div className="sefaz-btn-group">
          <button type="button" className="sefaz-btn sefaz-btn--primary">
            Validar
          </button>
        </div>
      </FormSection>
    </div>
  );
};

export default ProtocolarGIAITCD;
