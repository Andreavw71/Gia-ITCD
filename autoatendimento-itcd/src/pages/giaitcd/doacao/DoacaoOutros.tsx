import React, { useState } from 'react';
import Tabs from '../../../components/common/Tabs';
import DadosGeraisDoacao from './DadosGeraisDoacao';
import BensTributaveisTab from '../../bens-tributaveis/BensTributaveisTab';
import DemonstrativoCalculoDoacao from './DemonstrativoCalculoDoacao';
import DoacaoSucessivaTab from './DoacaoSucessivaTab';
import AcompanhamentoTab from '../../acompanhamento/AcompanhamentoTab';
import Message from '../../../components/common/Message';
import {
  GIAITCDDoacao,
  TipoGIA,
  SimNao,
  TipoProtocolo,
} from '../../../types';

const emptyGia: GIAITCDDoacao = {
  codigo: 0,
  tipoGIA: TipoGIA.DOACAO,
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
  doacaoSucessiva: [],
};

const DoacaoOutros: React.FC = () => {
  const [gia, setGia] = useState<GIAITCDDoacao>(emptyGia);
  const [activeTab, setActiveTab] = useState('dados-gerais');
  const [message, setMessage] = useState({ type: '' as any, text: '' });

  const handleGiaChange = (updates: Partial<GIAITCDDoacao>) => {
    setGia((prev) => ({ ...prev, ...updates }));
  };

  const tabs = [
    {
      id: 'dados-gerais',
      label: 'Dados Gerais',
      content: (
        <DadosGeraisDoacao
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
          onNext={() => setActiveTab('demonstrativo')}
          onPrevious={() => setActiveTab('dados-gerais')}
        />
      ),
      disabled: gia.codigo === 0,
    },
    {
      id: 'demonstrativo',
      label: 'Demonstrativo de Calculo',
      content: (
        <DemonstrativoCalculoDoacao
          gia={gia}
          onPrevious={() => setActiveTab('bens-tributaveis')}
        />
      ),
      disabled: gia.codigo === 0,
    },
    {
      id: 'doacao-sucessiva',
      label: 'Doacao Sucessiva',
      content: (
        <DoacaoSucessivaTab
          doacaoSucessiva={gia.doacaoSucessiva}
          onChange={(items) => handleGiaChange({ doacaoSucessiva: items })}
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
          GIA-ITCD - Doacao/Outros
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

export default DoacaoOutros;
