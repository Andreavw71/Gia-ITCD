import React, { useState, useEffect } from 'react';
import FormSection from '../../../components/common/FormSection';
import ContribuinteSearch from '../../../components/common/ContribuinteSearch';
import {
  GIAITCDInventarioArrolamento,
  Contribuinte,
  NaturezaOperacao,
  EstadoCivil,
  RegimeCasamento,
  TipoRenuncia,
  TipoProcessoInventario,
  SimNao,
} from '../../../types';
import { listarNaturezasOperacao } from '../../../services/giaitcdService';

interface Props {
  gia: GIAITCDInventarioArrolamento;
  onChange: (updates: Partial<GIAITCDInventarioArrolamento>) => void;
  onNext: () => void;
}

const DadosGeraisInventario: React.FC<Props> = ({ gia, onChange, onNext }) => {
  const [naturezas, setNaturezas] = useState<NaturezaOperacao[]>([]);
  const [errors, setErrors] = useState<string[]>([]);

  useEffect(() => {
    listarNaturezasOperacao('INVENTARIO_ARROLAMENTO')
      .then(setNaturezas)
      .catch(() => {});
  }, []);

  const isCasado = gia.estadoCivil === EstadoCivil.CASADO;

  const handleSalvar = () => {
    const errs: string[] = [];
    if (!gia.responsavelVo?.numrDocumento) {
      errs.push('Informe o CPF/CNPJ do Inventariante Declarante.');
    }
    if (!gia.deCujusVo?.numrDocumento) {
      errs.push('Informe o CPF do De Cujus.');
    }
    if (!gia.naturezaOperacaoVo?.codigo) {
      errs.push('Selecione a Natureza da Operacao.');
    }
    if (!gia.dataObito) {
      errs.push('Informe a Data do Obito.');
    }
    setErrors(errs);
    if (errs.length === 0) {
      // Save would call API here
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

      {/* Inventariante / Declarante */}
      <FormSection title="Inventariante Declarante">
        <ContribuinteSearch
          label="CPF / CNPJ Inventariante Declarante"
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

      {/* De Cujus */}
      <FormSection title="De Cujus">
        <ContribuinteSearch
          label="CPF De Cujus"
          value={gia.deCujusVo?.numrDocumento ? gia.deCujusVo : undefined}
          onChange={(c) => onChange({ deCujusVo: c || ({} as Contribuinte) })}
          required
        />
        <div className="sefaz-form-row">
          <div className="sefaz-td-rotulo-entrada">
            Data do Obito:<span className="sefaz-required">*</span>
          </div>
          <div className="sefaz-td-campo">
            <input
              type="date"
              className="sefaz-input-text"
              value={gia.dataObito}
              onChange={(e) => onChange({ dataObito: e.target.value })}
            />
          </div>
        </div>
        <div className="sefaz-form-row">
          <div className="sefaz-td-rotulo-entrada">
            Estado Civil:<span className="sefaz-required">*</span>
          </div>
          <div className="sefaz-td-campo">
            <select
              className="sefaz-select"
              value={gia.estadoCivil}
              onChange={(e) =>
                onChange({
                  estadoCivil: Number(e.target.value) as EstadoCivil,
                  possuiMeeiro: Number(e.target.value) === EstadoCivil.CASADO ? SimNao.SIM : SimNao.NAO,
                })
              }
            >
              <option value={EstadoCivil.SOLTEIRO}>Solteiro(a)</option>
              <option value={EstadoCivil.CASADO}>Casado(a)</option>
              <option value={EstadoCivil.DIVORCIADO}>Divorciado(a)</option>
              <option value={EstadoCivil.VIUVO}>Viuvo(a)</option>
              <option value={EstadoCivil.SEPARADO}>Separado(a)</option>
              <option value={EstadoCivil.UNIAO_ESTAVEL}>Uniao Estavel</option>
            </select>
          </div>
        </div>

        {isCasado && (
          <>
            <div className="sefaz-form-row">
              <div className="sefaz-td-rotulo-entrada">
                Regime de Casamento:<span className="sefaz-required">*</span>
              </div>
              <div className="sefaz-td-campo">
                <select
                  className="sefaz-select"
                  value={gia.regimeCasamento || ''}
                  onChange={(e) =>
                    onChange({ regimeCasamento: Number(e.target.value) as RegimeCasamento })
                  }
                >
                  <option value="">Selecione...</option>
                  <option value={RegimeCasamento.COMUNHAO_PARCIAL}>Comunhao Parcial de Bens</option>
                  <option value={RegimeCasamento.COMUNHAO_UNIVERSAL}>Comunhao Universal de Bens</option>
                  <option value={RegimeCasamento.SEPARACAO_TOTAL}>Separacao Total de Bens</option>
                  <option value={RegimeCasamento.PARTICIPACAO_AQUESTOS}>
                    Participacao Final nos Aquestos
                  </option>
                </select>
              </div>
            </div>
          </>
        )}

        <div className="sefaz-form-row">
          <div className="sefaz-td-rotulo-entrada">
            Tipo de Processo:<span className="sefaz-required">*</span>
          </div>
          <div className="sefaz-td-campo">
            <select
              className="sefaz-select"
              value={gia.tipoProcessoInventario}
              onChange={(e) =>
                onChange({
                  tipoProcessoInventario: Number(e.target.value) as TipoProcessoInventario,
                })
              }
            >
              <option value={TipoProcessoInventario.JUDICIAL}>Judicial</option>
              <option value={TipoProcessoInventario.EXTRAJUDICIAL}>Extrajudicial</option>
            </select>
          </div>
        </div>

        <div className="sefaz-form-row">
          <div className="sefaz-td-rotulo-entrada">Tipo de Renuncia:</div>
          <div className="sefaz-td-campo">
            <select
              className="sefaz-select"
              value={gia.tipoRenuncia || ''}
              onChange={(e) =>
                onChange({ tipoRenuncia: e.target.value ? (Number(e.target.value) as TipoRenuncia) : undefined })
              }
            >
              <option value="">Nenhuma</option>
              <option value={TipoRenuncia.ABDICATIVA}>Abdicativa</option>
              <option value={TipoRenuncia.TRANSLATIVA}>Translativa</option>
            </select>
          </div>
        </div>
      </FormSection>

      {/* Meeiro */}
      {isCasado && (
        <FormSection title="Meeiro(a)">
          <ContribuinteSearch
            label="CPF Meeiro(a)"
            value={gia.meeiroVo?.numrDocumento ? gia.meeiroVo : undefined}
            onChange={(c) => onChange({ meeiroVo: c })}
          />
        </FormSection>
      )}

      {/* Procurador */}
      <FormSection title="Dados Procurador">
        <ContribuinteSearch
          label="CPF Procurador"
          value={gia.procuradorVo?.numrDocumento ? gia.procuradorVo : undefined}
          onChange={(c) => onChange({ procuradorVo: c })}
        />
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

export default DadosGeraisInventario;
