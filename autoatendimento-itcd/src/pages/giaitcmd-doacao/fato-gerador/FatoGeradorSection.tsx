import React, { useState } from 'react';
import FormSection from '../../../components/common/FormSection';
import Modal from '../../../components/common/Modal';
import { FatoGerador, TipoDoacao, SituacaoDoacao, MotivoFatoGerador } from '../../../types/giaitcmd';

const tipoDoacaoLabels: Record<TipoDoacao, string> = {
  [TipoDoacao.SIMPLES]: 'Doacao Simples',
  [TipoDoacao.RESERVA_USUFRUTO]: 'Doacao com Reserva de Usufruto',
  [TipoDoacao.NUA_PROPRIEDADE]: 'Doacao da Nua Propriedade',
  [TipoDoacao.ISENCAO_LEGITIMACAO_POSSE]: 'Doacao com Isencao em legitimacao de Posse',
  [TipoDoacao.RENUNCIA_USUFRUTO]: 'Renuncia do Usufruto',
  [TipoDoacao.EXTINCAO_USUFRUTO]: 'Extincao do Usufruto',
  [TipoDoacao.DIREITO_HABITACAO]: 'Direito de Habitacao',
  [TipoDoacao.DIREITO_SUPERFICIE]: 'Direito de Superficie',
  [TipoDoacao.DIREITO_USO]: 'Direito de Uso',
  [TipoDoacao.OUTROS]: 'Outros tipos de doacao',
};

interface Props {
  fatoGerador: FatoGerador;
  onChange: (fg: FatoGerador) => void;
  disabled: boolean;
}

const FatoGeradorSection: React.FC<Props> = ({ fatoGerador, onChange, disabled }) => {
  const [showDoacaoInfo, setShowDoacaoInfo] = useState(false);
  const [showPrevistaInfo, setShowPrevistaInfo] = useState(false);
  const [showRealizadaInfo, setShowRealizadaInfo] = useState(false);
  const [showDataInfo, setShowDataInfo] = useState(false);

  const handleChange = (field: keyof FatoGerador, value: any) => {
    const updated = { ...fatoGerador, [field]: value };
    if (field === 'situacaoDoacao') {
      updated.denunciaEspontanea = value === SituacaoDoacao.JA_REALIZADA;
      if (value === SituacaoDoacao.PREVISTA) {
        updated.dataDoacao = new Date().toISOString().split('T')[0];
        setShowPrevistaInfo(true);
      } else {
        updated.dataDoacao = '';
        setShowRealizadaInfo(true);
      }
    }
    onChange(updated);
  };

  return (
    <div>
      <FormSection title="Fato Gerador">
        <div className="sefaz-form-row">
          <div className="sefaz-td-rotulo-entrada">Motivo do Fato Gerador:</div>
          <div className="sefaz-td-campo">
            <select className="sefaz-select" value={fatoGerador.motivo} disabled style={{ width: '300px', background: '#eee' }}>
              <option value={MotivoFatoGerador.DOACAO}>Doacao</option>
            </select>
          </div>
        </div>
      </FormSection>

      <FormSection title="Detalhes do Fato Gerador">
        <div className="sefaz-form-row">
          <div className="sefaz-td-rotulo-entrada">Tipo de Doacao:<span className="sefaz-required">*</span></div>
          <div className="sefaz-td-campo">
            <select className="sefaz-select" value={fatoGerador.tipoDoacao}
              onChange={(e) => handleChange('tipoDoacao', e.target.value)}
              disabled={disabled} style={{ width: '400px' }}>
              <option value="">Selecione...</option>
              {Object.entries(tipoDoacaoLabels).map(([key, label]) => (
                <option key={key} value={key}>{label}</option>
              ))}
            </select>
          </div>
        </div>

        <div className="sefaz-form-row">
          <div className="sefaz-td-rotulo-entrada">Situacao da Doacao:<span className="sefaz-required">*</span></div>
          <div className="sefaz-td-campo">
            <select className="sefaz-select" value={fatoGerador.situacaoDoacao}
              onChange={(e) => handleChange('situacaoDoacao', e.target.value)}
              disabled={disabled} style={{ width: '300px' }}>
              <option value={SituacaoDoacao.PREVISTA}>Doacao Prevista</option>
              <option value={SituacaoDoacao.JA_REALIZADA}>Doacao Ja Realizada</option>
            </select>
          </div>
        </div>

        <div className="sefaz-form-row">
          <div className="sefaz-td-rotulo-entrada">Denuncia Espontanea:</div>
          <div className="sefaz-td-campo">
            <input type="text" className="sefaz-input-text"
              value={fatoGerador.denunciaEspontanea ? 'SIM' : 'NAO'}
              disabled style={{ width: '80px', background: '#eee', textAlign: 'center', fontWeight: 'bold' }} />
          </div>
        </div>

        <div className="sefaz-form-row">
          <div className="sefaz-td-rotulo-entrada">Data da Doacao:<span className="sefaz-required">*</span></div>
          <div className="sefaz-td-campo" style={{ display: 'flex', gap: '8px', alignItems: 'center' }}>
            <input type="date" className="sefaz-input-text" value={fatoGerador.dataDoacao}
              onChange={(e) => handleChange('dataDoacao', e.target.value)}
              disabled={disabled || fatoGerador.situacaoDoacao === SituacaoDoacao.PREVISTA}
              onFocus={() => setShowDataInfo(true)} />
            {fatoGerador.situacaoDoacao === SituacaoDoacao.PREVISTA && (
              <span style={{ fontSize: '10px', color: '#666' }}>
                (preenchido automaticamente com a data de hoje)
              </span>
            )}
          </div>
        </div>
      </FormSection>

      {/* Modais informativos */}
      <Modal isOpen={showPrevistaInfo} onClose={() => setShowPrevistaInfo(false)} title="Doacao Prevista">
        <p style={{ padding: '10px', fontSize: '11px', textAlign: 'justify' }}>
          Considerando que a doacao ainda nao ocorreu, para efeitos fiscais, sera considerada como
          data da doacao, a data da Declaracao que esta sendo cadastrada.
        </p>
      </Modal>

      <Modal isOpen={showRealizadaInfo} onClose={() => setShowRealizadaInfo(false)} title="Doacao Ja Realizada">
        <p style={{ padding: '10px', fontSize: '11px', textAlign: 'justify' }}>
          Nos termos do art. 21, inciso II, combinado com o art. 22 da Lei Estadual n. 7.850/2002,
          o ITCMD incidente sobre a doacao deve ser recolhido antes da realizacao do ato de doacao.
          O descumprimento caracteriza a mora, com incidencia dos acrescimos legais previstos nos
          arts. 23 e 24 da mesma Lei. A denuncia espontanea afasta exclusivamente a penalidade pela
          falta de recolhimento (art. 138 CTN), mas nao elimina juros e atualizacao monetaria.
        </p>
      </Modal>

      <Modal isOpen={showDataInfo} onClose={() => setShowDataInfo(false)} title="Data da Doacao">
        <p style={{ padding: '10px', fontSize: '11px', textAlign: 'justify' }}>
          Para doacao ja ocorrida, informar a data contida no contrato assinado entre as partes,
          ou a data contida no documento que formalizou e/ou registrou a doacao, ou na ausencia de
          tais documentos considerar-se-a a data da efetiva doacao. Caso a doacao ainda nao tenha
          sido efetivada, sera considerada como data da doacao a data da Declaracao que esta sendo cadastrada.
        </p>
      </Modal>
    </div>
  );
};

export default FatoGeradorSection;
