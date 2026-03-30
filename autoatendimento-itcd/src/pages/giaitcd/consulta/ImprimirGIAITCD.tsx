import React, { useState } from 'react';
import FormSection from '../../../components/common/FormSection';
import Message from '../../../components/common/Message';
import Loading from '../../../components/common/Loading';

const ImprimirGIAITCD: React.FC = () => {
  const [codigoGIA, setCodigoGIA] = useState('');
  const [senha, setSenha] = useState('');
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState({ type: '' as any, text: '' });

  const handleImprimir = async () => {
    if (!codigoGIA) {
      setMessage({ type: 'error', text: 'Informe o numero da GIA-ITCD.' });
      return;
    }
    if (!senha) {
      setMessage({ type: 'error', text: 'Informe a senha da GIA-ITCD.' });
      return;
    }

    setLoading(true);
    setMessage({ type: '', text: '' });
    try {
      // API call would go here
      // const blob = await imprimirGIAITCD(Number(codigoGIA));
      // window.open(URL.createObjectURL(blob));
      setMessage({ type: 'success', text: 'GIA-ITCD impressa com sucesso.' });
    } catch {
      setMessage({ type: 'error', text: 'Erro ao imprimir GIA-ITCD. Verifique os dados informados.' });
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="sefaz-form">
      {message.text && (
        <Message type={message.type} text={message.text} onClose={() => setMessage({ type: '', text: '' })} />
      )}

      <FormSection title="Imprimir GIA-ITCD">
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
            onClick={handleImprimir}
            disabled={loading}
          >
            {loading ? 'Imprimindo...' : 'Imprimir'}
          </button>
        </div>
      </FormSection>

      {loading && <Loading message="Gerando impressao..." />}
    </div>
  );
};

export default ImprimirGIAITCD;
