import React, { useState, useEffect } from 'react';
import FormSection from '../../../components/common/FormSection';
import ContribuinteSearch from '../../../components/common/ContribuinteSearch';
import {
  GIAITCDSeparacaoDivorcio,
  Contribuinte,
  NaturezaOperacao,
  RegimeCasamento,
} from '../../../types';
import { listarNaturezasOperacao } from '../../../services/giaitcdService';

interface Props {
  gia: GIAITCDSeparacaoDivorcio;
  onChange: (updates: Partial<GIAITCDSeparacaoDivorcio>) => void;
  onNext: () => void;
}

const DadosGeraisSeparacao: React.FC<Props> = ({ gia, onChange, onNext }) => {
  const [naturezas, setNaturezas] = useState<NaturezaOperacao[]>([]);
  const [errors, setErrors] = useState<string[]>([]);

  useEffect(() => {
    listarNaturezasOperacao('SEPARACAO_DIVORCIO')
      .then(setNaturezas)
      .catch(() => {});
  }, []);

  const handleSalvar = () => {
    const errs: string[] = [];
    if (!gia.responsavelVo?.numrDocumento) {
      errs.push('Informe o CPF/CNPJ do Declarante.');
    }
    if (!gia.conjuge1?.pessoaConjuge?.numrDocumento) {
      errs.push('Informe o CPF do Conjuge 1.');
    }
    if (!gia.conjuge2?.pessoaConjuge?.numrDocumento) {
      errs.push('Informe o CPF do Conjuge 2.');
    }
    if (!gia.naturezaOperacaoVo?.codigo) {
      errs.push('Selecione a Natureza da Operacao.');
    }
    if (!gia.dataCasamento) {
      errs.push('Informe a Data do Casamento.');
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

      {/* Declarante */}
      <FormSection title="Declarante">
        <ContribuinteSearch
          label="CPF / CNPJ Declarante"
          value={gia.responsavelVo?.numrDocumento ? gia.responsavelVo : undefined}
          onChange={(c) => onChange({ responsavelVo: c || ({} as Contribuinte) })}
          required
        />
      </FormSection>

      {/* Cônjuge 1 */}
      <FormSection title="Conjuge 1">
        <ContribuinteSearch
          label="CPF Conjuge 1"
          value={gia.conjuge1?.pessoaConjuge?.numrDocumento ? gia.conjuge1.pessoaConjuge : undefined}
          onChange={(c) =>
            onChange({
              conjuge1: { ...gia.conjuge1, pessoaConjuge: c || ({} as Contribuinte) },
            })
          }
          required
        />
      </FormSection>

      {/* Cônjuge 2 */}
      <FormSection title="Conjuge 2">
        <ContribuinteSearch
          label="CPF Conjuge 2"
          value={gia.conjuge2?.pessoaConjuge?.numrDocumento ? gia.conjuge2.pessoaConjuge : undefined}
          onChange={(c) =>
            onChange({
              conjuge2: { ...gia.conjuge2, pessoaConjuge: c || ({} as Contribuinte) },
            })
          }
          required
        />
      </FormSection>

      {/* Procurador */}
      <FormSection title="Dados Procurador">
        <ContribuinteSearch
          label="CPF Procurador"
          value={gia.procuradorVo?.numrDocumento ? gia.procuradorVo : undefined}
          onChange={(c) => onChange({ procuradorVo: c })}
        />
      </FormSection>

      {/* Dados do Casamento */}
      <FormSection title="Dados do Casamento">
        <div className="sefaz-form-row">
          <div className="sefaz-td-rotulo-entrada">
            Data do Casamento:<span className="sefaz-required">*</span>
          </div>
          <div className="sefaz-td-campo">
            <input
              type="date"
              className="sefaz-input-text"
              value={gia.dataCasamento}
              onChange={(e) => onChange({ dataCasamento: e.target.value })}
            />
          </div>
        </div>
        <div className="sefaz-form-row">
          <div className="sefaz-td-rotulo-entrada">
            Regime de Casamento:<span className="sefaz-required">*</span>
          </div>
          <div className="sefaz-td-campo">
            <select
              className="sefaz-select"
              value={gia.regimeCasamento}
              onChange={(e) =>
                onChange({ regimeCasamento: Number(e.target.value) as RegimeCasamento })
              }
            >
              <option value={RegimeCasamento.COMUNHAO_PARCIAL}>Comunhao Parcial de Bens</option>
              <option value={RegimeCasamento.COMUNHAO_UNIVERSAL}>Comunhao Universal de Bens</option>
              <option value={RegimeCasamento.SEPARACAO_TOTAL}>Separacao Total de Bens</option>
              <option value={RegimeCasamento.PARTICIPACAO_AQUESTOS}>
                Participacao Final nos Aquestos
              </option>
            </select>
          </div>
        </div>
      </FormSection>

      {/* Natureza da Operação */}
      <FormSection title="Natureza da Operacao">
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

export default DadosGeraisSeparacao;
