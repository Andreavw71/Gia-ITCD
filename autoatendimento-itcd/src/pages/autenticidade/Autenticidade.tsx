import React, { useState } from 'react';
import FormSection from '../../components/common/FormSection';
import Message from '../../components/common/Message';
import Loading from '../../components/common/Loading';
import { GIAITCD } from '../../types';
import { verificarAutenticidade } from '../../services/giaitcdService';
import { formatCPFOrCNPJ, formatCurrency, formatDate } from '../../utils/formatters';

const Autenticidade: React.FC = () => {
  const [codigoAutenticidade, setCodigoAutenticidade] = useState('');
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState({ type: '' as any, text: '' });
  const [resultado, setResultado] = useState<GIAITCD | null>(null);

  const handleVerificar = async () => {
    if (!codigoAutenticidade) {
      setMessage({ type: 'error', text: 'Informe o codigo de autenticidade.' });
      return;
    }

    setLoading(true);
    setMessage({ type: '', text: '' });
    setResultado(null);

    try {
      const data = await verificarAutenticidade(codigoAutenticidade);
      setResultado(data);
      setMessage({ type: 'success', text: 'GIA-ITCD encontrada. Documento autentico.' });
    } catch {
      setMessage({ type: 'error', text: 'Codigo de autenticidade nao encontrado ou invalido.' });
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="sefaz-form">
      {message.text && (
        <Message type={message.type} text={message.text} onClose={() => setMessage({ type: '', text: '' })} />
      )}

      <FormSection title="Verificar Autenticidade da GIA-ITCD">
        <div className="sefaz-form-row">
          <div className="sefaz-td-rotulo-entrada">
            Codigo de Autenticidade:<span className="sefaz-required">*</span>
          </div>
          <div className="sefaz-td-campo">
            <input
              type="text"
              className="sefaz-input-text"
              style={{ width: '300px' }}
              value={codigoAutenticidade}
              onChange={(e) => setCodigoAutenticidade(e.target.value)}
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
            onClick={handleVerificar}
            disabled={loading}
          >
            {loading ? 'Verificando...' : 'Verificar'}
          </button>
        </div>
      </FormSection>

      {loading && <Loading message="Verificando autenticidade..." />}

      {resultado && (
        <FormSection title="Dados da GIA-ITCD">
          <div className="sefaz-data-grid">
            <div className="sefaz-td-rotulo">N. da GIA:</div>
            <div className="sefaz-td-campo-saida">{resultado.codigo}</div>
            <div className="sefaz-td-rotulo">Data de Criacao:</div>
            <div className="sefaz-td-campo-saida">{formatDate(resultado.dataCriacao)}</div>

            <div className="sefaz-td-rotulo">Declarante:</div>
            <div className="sefaz-td-campo-saida">{resultado.responsavelVo?.nomeContribuinte}</div>
            <div className="sefaz-td-rotulo">CPF/CNPJ:</div>
            <div className="sefaz-td-campo-saida">
              {resultado.responsavelVo?.numrDocumento
                ? formatCPFOrCNPJ(resultado.responsavelVo.numrDocumento)
                : ''}
            </div>

            <div className="sefaz-td-rotulo">Valor ITCD:</div>
            <div className="sefaz-td-campo-saida">{formatCurrency(resultado.valorITCD)}</div>
            <div className="sefaz-td-rotulo">Status:</div>
            <div className="sefaz-td-campo-saida">{resultado.statusVo?.descricao}</div>

            <div className="sefaz-td-rotulo">Codigo Autenticidade:</div>
            <div className="sefaz-td-campo-saida">{resultado.codigoAutenticidade}</div>
            <div className="sefaz-td-rotulo">&nbsp;</div>
            <div className="sefaz-td-campo-saida">&nbsp;</div>
          </div>
        </FormSection>
      )}
    </div>
  );
};

export default Autenticidade;
