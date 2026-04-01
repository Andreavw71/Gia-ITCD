import React, { useState } from 'react';
import { Contribuinte } from '../../types';
import { pesquisarContribuinte } from '../../services/giaitcdService';
import { formatCPFOrCNPJ, formatCEP, formatPhone } from '../../utils/formatters';
import { isValidCPFOrCNPJ } from '../../utils/validators';

interface ContribuinteSearchProps {
  label: string;
  value?: Contribuinte;
  onChange: (contribuinte: Contribuinte | undefined) => void;
  required?: boolean;
  disabled?: boolean;
}

const ContribuinteSearch: React.FC<ContribuinteSearchProps> = ({
  label,
  value,
  onChange,
  required = false,
  disabled = false,
}) => {
  const [cpfCnpj, setCpfCnpj] = useState('');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  const handleSearch = async () => {
    setError('');
    const digits = cpfCnpj.replace(/\D/g, '');

    if (!isValidCPFOrCNPJ(digits)) {
      setError('CPF/CNPJ inválido.');
      return;
    }

    setLoading(true);
    try {
      const contribuinte = await pesquisarContribuinte(digits);
      onChange(contribuinte);
    } catch {
      setError('Contribuinte não encontrado no cadastro.');
      onChange(undefined);
    } finally {
      setLoading(false);
    }
  };

  const handleClear = () => {
    setCpfCnpj('');
    onChange(undefined);
    setError('');
  };

  return (
    <div style={{ marginBottom: '10px' }}>
      {!value ? (
        <div className="sefaz-form-row">
          <div className="sefaz-td-rotulo-entrada">
            {label}:{required && <span className="sefaz-required">*</span>}
          </div>
          <div className="sefaz-td-campo" style={{ display: 'flex', gap: '4px', alignItems: 'center' }}>
            <input
              type="text"
              className="sefaz-input-text"
              value={cpfCnpj}
              onChange={(e) => setCpfCnpj(e.target.value)}
              placeholder="CPF ou CNPJ"
              maxLength={18}
              disabled={disabled || loading}
              style={{ width: '200px' }}
            />
            <button
              type="button"
              className="sefaz-btn sefaz-btn--primary"
              onClick={handleSearch}
              disabled={disabled || loading}
            >
              {loading ? 'Pesquisando...' : 'Pesquisar'}
            </button>
            {error && <span style={{ color: 'red', fontSize: '11px' }}>{error}</span>}
          </div>
        </div>
      ) : (
        <div className="sefaz-contribuinte-card">
          <div className="sefaz-contribuinte-card__grid">
            <div className="sefaz-td-rotulo">Nome:</div>
            <div className="sefaz-td-campo-saida">{value.nomeContribuinte}</div>
            <div className="sefaz-td-rotulo">CPF/CNPJ:</div>
            <div className="sefaz-td-campo-saida">{formatCPFOrCNPJ(value.numrDocumento)}</div>

            <div className="sefaz-td-rotulo">Logradouro:</div>
            <div className="sefaz-td-campo-saida">{value.endereco}</div>
            <div className="sefaz-td-rotulo">Numero:</div>
            <div className="sefaz-td-campo-saida">{value.enderecoNumero}</div>

            <div className="sefaz-td-rotulo">Complemento:</div>
            <div className="sefaz-td-campo-saida">{value.enderecoComplemento}</div>
            <div className="sefaz-td-rotulo">Referencia:</div>
            <div className="sefaz-td-campo-saida">{value.pontoReferencia}</div>

            <div className="sefaz-td-rotulo">Bairro:</div>
            <div className="sefaz-td-campo-saida">{value.enderecoBairro}</div>
            <div className="sefaz-td-rotulo">CEP:</div>
            <div className="sefaz-td-campo-saida">{value.enderecoCEP ? formatCEP(value.enderecoCEP) : ''}</div>

            <div className="sefaz-td-rotulo">Municipio:</div>
            <div className="sefaz-td-campo-saida">{value.municipio}</div>
            <div className="sefaz-td-rotulo">UF:</div>
            <div className="sefaz-td-campo-saida">{value.siglaUF}</div>

            <div className="sefaz-td-rotulo">DDD:</div>
            <div className="sefaz-td-campo-saida">{value.numrDdd}</div>
            <div className="sefaz-td-rotulo">Telefone:</div>
            <div className="sefaz-td-campo-saida">{value.numrTelefone}</div>

            <div className="sefaz-td-rotulo">E-mail:</div>
            <div className="sefaz-td-campo-saida">{value.email}</div>
            <div className="sefaz-td-rotulo">&nbsp;</div>
            <div className="sefaz-td-campo-saida">
              {!disabled && (
                <button type="button" className="sefaz-btn" onClick={handleClear}>
                  Alterar
                </button>
              )}
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default ContribuinteSearch;
