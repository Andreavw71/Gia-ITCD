import React, { useState } from 'react';
import FormSection from '../../../components/common/FormSection';
import Message from '../../../components/common/Message';
import Loading from '../../../components/common/Loading';

const ImprimirDAR: React.FC = () => {
  const [codigoGIA, setCodigoGIA] = useState('');
  const [senha, setSenha] = useState('');
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState({ type: '' as any, text: '' });

  const handleImprimirDAR = async () => {
    if (!codigoGIA || !senha) {
      setMessage({ type: 'error', text: 'Informe o numero da GIA e a senha.' });
      return;
    }
    setLoading(true);
    try {
      // const blob = await imprimirDAR(Number(codigoGIA));
      // window.open(URL.createObjectURL(blob));
      setMessage({ type: 'success', text: 'DAR gerado com sucesso.' });
    } catch {
      setMessage({ type: 'error', text: 'Erro ao gerar DAR.' });
    } finally {
      setLoading(false);
    }
  };

  const handleImprimirDeclaracao = async () => {
    if (!codigoGIA || !senha) {
      setMessage({ type: 'error', text: 'Informe o numero da GIA e a senha.' });
      return;
    }
    setLoading(true);
    try {
      // const blob = await imprimirDeclaracao(Number(codigoGIA));
      // window.open(URL.createObjectURL(blob));
      setMessage({ type: 'success', text: 'Declaracao gerada com sucesso.' });
    } catch {
      setMessage({ type: 'error', text: 'Erro ao gerar Declaracao.' });
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="sefaz-form">
      {message.text && (
        <Message type={message.type} text={message.text} onClose={() => setMessage({ type: '', text: '' })} />
      )}

      <FormSection title="Imprimir DAR / Declaracoes">
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
            onClick={handleImprimirDAR}
            disabled={loading}
          >
            Imprimir DAR
          </button>
          <button
            type="button"
            className="sefaz-btn sefaz-btn--success"
            onClick={handleImprimirDeclaracao}
            disabled={loading}
          >
            Imprimir Declaracao
          </button>
        </div>
      </FormSection>

      {loading && <Loading message="Gerando documento..." />}
    </div>
  );
};

export default ImprimirDAR;
