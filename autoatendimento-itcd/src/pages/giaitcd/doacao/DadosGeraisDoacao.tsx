import React, { useState, useEffect } from 'react';
import FormSection from '../../../components/common/FormSection';
import ContribuinteSearch from '../../../components/common/ContribuinteSearch';
import {
  GIAITCDDoacao,
  Contribuinte,
  NaturezaOperacao,
} from '../../../types';
import { listarNaturezasOperacao } from '../../../services/giaitcdService';

interface Props {
  gia: GIAITCDDoacao;
  onChange: (updates: Partial<GIAITCDDoacao>) => void;
  onNext: () => void;
}

const DadosGeraisDoacao: React.FC<Props> = ({ gia, onChange, onNext }) => {
  const [naturezas, setNaturezas] = useState<NaturezaOperacao[]>([]);
  const [errors, setErrors] = useState<string[]>([]);

  useEffect(() => {
    listarNaturezasOperacao('DOACAO')
      .then(setNaturezas)
      .catch(() => {});
  }, []);

  const isDoacaoReservaUsufruto = gia.naturezaOperacaoVo?.codigo === 23;

  const handleSalvar = () => {
    const errs: string[] = [];
    if (!gia.responsavelVo?.numrDocumento) {
      errs.push('Informe o CPF/CNPJ do Doador Declarante.');
    }
    if (!gia.naturezaOperacaoVo?.codigo) {
      errs.push('Selecione a Natureza da Operacao.');
    }
    if (gia.fracaoIdeal <= 0) {
      errs.push('O percentual da fracao ideal deve ser maior que zero.');
    }
    if (gia.fracaoIdeal > 100) {
      errs.push('O percentual da fracao ideal nao pode ser maior que 100%.');
    }
    setErrors(errs);
    if (errs.length === 0) {
      onNext();
    }
  };

  return (
    <div>
      {errors.length > 0 && (
        <div className="sefaz-msg sefaz-msg--error">
          <ul style={{ margin: '0', paddingLeft: '16px' }}>
            {errors.map((e, i) => (
              <li key={i}>{e}</li>
            ))}
          </ul>
        </div>
      )}

      {/* Doador */}
      <FormSection title="Doador">
        <ContribuinteSearch
          label="CPF / CNPJ Doador Declarante"
          value={gia.responsavelVo?.numrDocumento ? gia.responsavelVo : undefined}
          onChange={(c) => onChange({ responsavelVo: c || ({} as Contribuinte) })}
          required
        />
        {gia.codigo > 0 && (
          <div className="sefaz-form-row">
            <div className="sefaz-td-rotulo">N. da GIA:</div>
            <div className="sefaz-td-campo-saida">{gia.codigo}</div>
          </div>
        )}
      </FormSection>

      {/* Procurador */}
      <FormSection title="Dados Procurador">
        <ContribuinteSearch
          label="CPF Procurador"
          value={gia.procuradorVo?.numrDocumento ? gia.procuradorVo : undefined}
          onChange={(c) => onChange({ procuradorVo: c })}
        />
      </FormSection>

      {/* Natureza e percentual */}
      <FormSection title="Natureza e percentual de Doacao">
        <div className="sefaz-form-row">
          <div className="sefaz-td-rotulo-entrada">
            Natureza Operacao:<span className="sefaz-required">*</span>
          </div>
          <div className="sefaz-td-campo">
            <select
              className="sefaz-select"
              value={gia.naturezaOperacaoVo?.codigo || ''}
              onChange={(e) => {
                const nat = naturezas.find((n) => n.codigo === Number(e.target.value));
                if (nat) onChange({ naturezaOperacaoVo: nat });
              }}
            >
              <option value="">Selecione...</option>
              {naturezas.map((nat) => (
                <option key={nat.codigo} value={nat.codigo}>
                  {nat.descricaoNaturezaOperacao}
                </option>
              ))}
            </select>
          </div>
        </div>

        <div className="sefaz-form-row">
          <div className="sefaz-td-rotulo-entrada">
            Percentual da Doacao / Transmissao (%):<span className="sefaz-required">*</span>
          </div>
          <div className="sefaz-td-campo">
            <input
              type="number"
              className="sefaz-input-text"
              style={{ width: '120px' }}
              value={gia.fracaoIdeal}
              onChange={(e) => onChange({ fracaoIdeal: parseFloat(e.target.value) || 0 })}
              min="0.01"
              max="100"
              step="0.01"
            />
          </div>
        </div>

        {isDoacaoReservaUsufruto && (
          <div style={{ marginTop: '10px' }}>
            <div className="sefaz-tr-titulo" style={{ marginBottom: '10px' }}>
              Base de Calculo
            </div>
            <div style={{ marginLeft: '20px' }}>
              <div style={{ marginBottom: '10px' }}>
                <label>
                  <input
                    type="radio"
                    name="baseCalculoReduzida"
                    value={70}
                    checked={gia.baseCalculoReduzida !== 100}
                    onChange={() => onChange({ baseCalculoReduzida: 70 })}
                  />{' '}
                  70% - Sem encerramento da tributacao com recolhimento na renuncia ou extincao,
                  conforme o caso, nos termos do inciso I, par. 2, do Art. 11 do Decreto n. 2.125/2003.
                </label>
              </div>
              <div>
                <label>
                  <input
                    type="radio"
                    name="baseCalculoReduzida"
                    value={100}
                    checked={gia.baseCalculoReduzida === 100}
                    onChange={() => onChange({ baseCalculoReduzida: 100 })}
                  />{' '}
                  100% - Com encerramento da tributacao, opcional, nos termos do inciso III, par. 3 do
                  Art. 28 - Decreto n. 2125/2003.
                </label>
              </div>
            </div>
          </div>
        )}
      </FormSection>

      {/* Botões */}
      <div className="sefaz-btn-group">
        <button type="button" className="sefaz-btn" onClick={() => window.history.back()}>
          Cancelar
        </button>
        <button type="button" className="sefaz-btn sefaz-btn--primary" onClick={handleSalvar}>
          Salvar
        </button>
        <button type="button" className="sefaz-btn sefaz-btn--primary" onClick={onNext}>
          Proximo &raquo;
        </button>
      </div>
      <div style={{ marginTop: '8px' }}>
        <span className="sefaz-required-legend">* Campos Obrigatorios</span>
      </div>
    </div>
  );
};

export default DadosGeraisDoacao;
