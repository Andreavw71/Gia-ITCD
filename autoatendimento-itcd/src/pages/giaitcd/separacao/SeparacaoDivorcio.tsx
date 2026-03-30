import React, { useState } from 'react';
import Tabs from '../../../components/common/Tabs';
import DadosGeraisSeparacao from './DadosGeraisSeparacao';
import BensTributaveisTab from '../../bens-tributaveis/BensTributaveisTab';
import ConjugeTab from './ConjugeTab';
import DemonstrativoCalculoSeparacao from './DemonstrativoCalculoSeparacao';
import AcompanhamentoTab from '../../acompanhamento/AcompanhamentoTab';
import Message from '../../../components/common/Message';
import {
  GIAITCDSeparacaoDivorcio,
  TipoGIA,
  SimNao,
  RegimeCasamento,
  TipoProtocolo,
} from '../../../types';

const emptyGia: GIAITCDSeparacaoDivorcio = {
  codigo: 0,
  tipoGIA: TipoGIA.SEPARACAO_DIVORCIO,
  responsavelVo: {} as any,
  naturezaOperacaoVo: { codigo: 0, descricaoNaturezaOperacao: '', ativo: true },
  dataCriacao: '',
  senha: '',
  codigoAutenticidade: '',
  valorTotalBensDeclarados: 0,
  valorTotalArbitrado: 0,
  valorTotalInformadoBensDeclarados: 0,
  valorUPF: 0,
  valorCalculoDemonstrativo: 0,
  valorITCD: 0,
  valorRecolhimento: 0,
  valorTSE: 0,
  valorBaseCalculoTributavel: 0,
  valorCorrecaoMonetaria: 0,
  valorJuros: 0,
  valorMulta: 0,
  statusVo: { codigo: 0, descricao: '', dataStatus: '', observacao: '' },
  justificativaAlteracao: '',
  giaConfirmada: SimNao.NAO,
  fracaoIdeal: 100,
  tipoProtocoloGIA: TipoProtocolo.AUTOMATICO,
  baseCalculoReduzida: 100,
  bensTributaveis: [],
  beneficiarios: [],
  conjuge1: { codigo: 0, pessoaConjuge: {} as any, bensRecebidos: [], valorTotalRecebido: 0 },
  conjuge2: { codigo: 0, pessoaConjuge: {} as any, bensRecebidos: [], valorTotalRecebido: 0 },
  dataCasamento: '',
  regimeCasamento: RegimeCasamento.COMUNHAO_PARCIAL,
};

const SeparacaoDivorcio: React.FC = () => {
  const [gia, setGia] = useState<GIAITCDSeparacaoDivorcio>(emptyGia);
  const [activeTab, setActiveTab] = useState('dados-gerais');
  const [message, setMessage] = useState({ type: '' as any, text: '' });

  const handleGiaChange = (updates: Partial<GIAITCDSeparacaoDivorcio>) => {
    setGia((prev) => ({ ...prev, ...updates }));
  };

  const tabs = [
    {
      id: 'dados-gerais',
      label: 'Dados Gerais',
      content: (
        <DadosGeraisSeparacao
          gia={gia}
          onChange={handleGiaChange}
          onNext={() => setActiveTab('bens-tributaveis')}
        />
      ),
    },
    {
      id: 'bens-tributaveis',
      label: 'Bens Tributaveis',
      content: (
        <BensTributaveisTab
          bensTributaveis={gia.bensTributaveis}
          giaItcdCodigo={gia.codigo}
          onChange={(bens) => handleGiaChange({ bensTributaveis: bens })}
          onNext={() => setActiveTab('conjuge')}
          onPrevious={() => setActiveTab('dados-gerais')}
        />
      ),
      disabled: gia.codigo === 0,
    },
    {
      id: 'conjuge',
      label: 'Conjuges',
      content: (
        <ConjugeTab
          gia={gia}
          onChange={handleGiaChange}
          onNext={() => setActiveTab('demonstrativo')}
          onPrevious={() => setActiveTab('bens-tributaveis')}
        />
      ),
      disabled: gia.codigo === 0,
    },
    {
      id: 'demonstrativo',
      label: 'Demonstrativo de Calculo',
      content: (
        <DemonstrativoCalculoSeparacao
          gia={gia}
          onPrevious={() => setActiveTab('conjuge')}
        />
      ),
      disabled: gia.codigo === 0,
    },
    {
      id: 'acompanhamento',
      label: 'Acompanhamento',
      content: <AcompanhamentoTab giaItcdCodigo={gia.codigo} />,
      disabled: gia.codigo === 0,
    },
  ];

  return (
    <div className="sefaz-form">
      <div className="sefaz-form-section">
        <div className="sefaz-form-section__title">
          GIA-ITCD - Separacao/Divorcio/Partilha
          {gia.codigo > 0 && ` - N. ${gia.codigo}`}
        </div>
      </div>

      {message.text && (
        <Message type={message.type} text={message.text} onClose={() => setMessage({ type: '', text: '' })} />
      )}

      <Tabs tabs={tabs} activeTab={activeTab} onTabChange={setActiveTab} />
    </div>
  );
};

export default SeparacaoDivorcio;
