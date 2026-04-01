import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import Tabs from '../../../components/common/Tabs';
import DadosGeraisInventario from './DadosGeraisInventario';
import BensTributaveisTab from '../../bens-tributaveis/BensTributaveisTab';
import BeneficiariosTab from '../../beneficiarios/BeneficiariosTab';
import DemonstrativoCalculoInventario from './DemonstrativoCalculoInventario';
import AcompanhamentoTab from '../../acompanhamento/AcompanhamentoTab';
import Message from '../../../components/common/Message';
import {
  GIAITCDInventarioArrolamento,
  TipoGIA,
  SimNao,
  EstadoCivil,
  TipoProcessoInventario,
  TipoProtocolo,
  StatusGIAITCD,
} from '../../../types';

const emptyGia: GIAITCDInventarioArrolamento = {
  codigo: 0,
  tipoGIA: TipoGIA.INVENTARIO_ARROLAMENTO,
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
  deCujusVo: {} as any,
  dataObito: '',
  estadoCivil: EstadoCivil.SOLTEIRO,
  tipoProcessoInventario: TipoProcessoInventario.JUDICIAL,
  possuiMeeiro: SimNao.NAO,
};

const InventarioArrolamento: React.FC = () => {
  const navigate = useNavigate();
  const [gia, setGia] = useState<GIAITCDInventarioArrolamento>(emptyGia);
  const [activeTab, setActiveTab] = useState('dados-gerais');
  const [message, setMessage] = useState({ type: '' as any, text: '' });

  const handleGiaChange = (updates: Partial<GIAITCDInventarioArrolamento>) => {
    setGia((prev) => ({ ...prev, ...updates }));
  };

  const tabs = [
    {
      id: 'dados-gerais',
      label: 'Dados Gerais',
      content: (
        <DadosGeraisInventario
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
          onNext={() => setActiveTab('beneficiarios')}
          onPrevious={() => setActiveTab('dados-gerais')}
        />
      ),
      disabled: gia.codigo === 0,
    },
    {
      id: 'beneficiarios',
      label: 'Beneficiarios',
      content: (
        <BeneficiariosTab
          beneficiarios={gia.beneficiarios}
          giaItcdCodigo={gia.codigo}
          onChange={(beneficiarios) => handleGiaChange({ beneficiarios })}
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
        <DemonstrativoCalculoInventario
          gia={gia}
          onPrevious={() => setActiveTab('beneficiarios')}
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
          GIA-ITCD - Inventario/Arrolamento
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

export default InventarioArrolamento;
