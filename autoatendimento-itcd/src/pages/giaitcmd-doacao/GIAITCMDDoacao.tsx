import React, { useState } from 'react';
import Tabs from '../../components/common/Tabs';
import Message from '../../components/common/Message';
import DadosDeclaranteSection from './dados-declarante/DadosDeclaranteSection';
import FatoGeradorSection from './fato-gerador/FatoGeradorSection';
import PartesInteressadasSection from './partes-interessadas/PartesInteressadasSection';
import BensDireitosSection from './bens-direitos/BensDireitosSection';
import DivisaoBensSection from './divisao-bens/DivisaoBensSection';
import AnexarDocumentosSection from './anexar-documentos/AnexarDocumentosSection';
import EnviarDeclaracaoSection from './enviar-declaracao/EnviarDeclaracaoSection';
import CalculoITCMDSection from './calculo-itcmd/CalculoITCMDSection';
import {
  GIAITCMD,
  StatusGIAITCMD,
  MotivoFatoGerador,
  SituacaoDoacao,
  TipoDoacao,
  QualidadeDeclarante,
} from '../../types/giaitcmd';

const statusLabels: Record<StatusGIAITCMD, string> = {
  [StatusGIAITCMD.EM_ELABORACAO]: 'Em Elaboracao',
  [StatusGIAITCMD.ENVIADA]: 'Enviada',
  [StatusGIAITCMD.PROCESSADA_CONCLUIDA]: 'Processada/Concluida',
  [StatusGIAITCMD.AGUARDANDO_AUTORREGULARIZACAO]: 'Aguardando Autorregularizacao',
  [StatusGIAITCMD.PEDIDO_RETIFICACAO_ANALISE]: 'Pedido de Retificacao em Analise',
  [StatusGIAITCMD.RETIFICADA]: 'Retificada',
  [StatusGIAITCMD.RETIFICADA_AUTORREGULARIZACAO]: 'Retificada por Autorregularizacao',
  [StatusGIAITCMD.PEDIDO_CANCELAMENTO_ANALISE]: 'Pedido de Cancelamento em Analise',
  [StatusGIAITCMD.CANCELADA]: 'Cancelada',
  [StatusGIAITCMD.AGUARDANDO_HOMOLOGACAO]: 'Aguardando Homologacao (auditoria)',
  [StatusGIAITCMD.HOMOLOGADA]: 'Homologada',
  [StatusGIAITCMD.HOMOLOGADA_TACITAMENTE]: 'Homologada Tacitamente',
};

const emptyGia: GIAITCMD = {
  id: 0,
  status: StatusGIAITCMD.EM_ELABORACAO,
  dataCriacao: new Date().toISOString(),
  dadosDeclarante: {
    cpf: '',
    nome: '',
    endereco: { cep: '', logradouro: '', numero: '', complemento: '', bairro: '', cidade: '', estado: '', pais: 'Brasil', naoSeAplica: false },
    email: '',
    qualidade: QualidadeDeclarante.DOADOR,
  },
  fatoGerador: {
    motivo: MotivoFatoGerador.DOACAO,
    tipoDoacao: TipoDoacao.SIMPLES,
    situacaoDoacao: SituacaoDoacao.PREVISTA,
    denunciaEspontanea: false,
    dataDoacao: new Date().toISOString().split('T')[0],
  },
  doadores: [],
  donatarios: [],
  bensDireitos: [],
  divisaoBens: [],
  documentos: [],
  pendencias: [],
};

const GIAITCMDDoacao: React.FC = () => {
  const [gia, setGia] = useState<GIAITCMD>(emptyGia);
  const [activeTab, setActiveTab] = useState('dados-declarante');
  const [message, setMessage] = useState({ type: '' as any, text: '' });

  const isEditable = gia.status === StatusGIAITCMD.EM_ELABORACAO;

  const handleUpdate = (updates: Partial<GIAITCMD>) => {
    setGia((prev) => ({ ...prev, ...updates }));
  };

  const handleSalvar = () => {
    setMessage({ type: 'success', text: 'Declaracao salva com sucesso.' });
  };

  const handleExcluir = () => {
    if (window.confirm('Deseja realmente excluir esta declaracao? Confirmada a exclusao, a declaracao sera removida do sistema.')) {
      setGia(emptyGia);
      setMessage({ type: 'info', text: 'Declaracao excluida.' });
    }
  };

  const statusBadgeColor = gia.status === StatusGIAITCMD.EM_ELABORACAO ? '#FF9800' :
    gia.status === StatusGIAITCMD.ENVIADA ? '#2196F3' :
    gia.status === StatusGIAITCMD.PROCESSADA_CONCLUIDA ? '#4CAF50' :
    gia.status === StatusGIAITCMD.CANCELADA ? '#F44336' : '#607D8B';

  const tabs = [
    {
      id: 'dados-declarante',
      label: '1. Dados do Declarante',
      content: (
        <DadosDeclaranteSection
          dados={gia.dadosDeclarante}
          onChange={(d) => handleUpdate({ dadosDeclarante: d })}
          disabled={!isEditable}
        />
      ),
    },
    {
      id: 'fato-gerador',
      label: '2. Fato Gerador',
      content: (
        <FatoGeradorSection
          fatoGerador={gia.fatoGerador}
          onChange={(fg) => handleUpdate({ fatoGerador: fg })}
          disabled={!isEditable}
        />
      ),
    },
    {
      id: 'partes-interessadas',
      label: '3. Partes Interessadas',
      content: (
        <PartesInteressadasSection
          doadores={gia.doadores}
          donatarios={gia.donatarios}
          qualidadeDeclarante={gia.dadosDeclarante.qualidade}
          onChangeDoadores={(d) => handleUpdate({ doadores: d })}
          onChangeDonatarios={(d) => handleUpdate({ donatarios: d })}
          disabled={!isEditable}
        />
      ),
    },
    {
      id: 'bens-direitos',
      label: '4. Bens e Direitos',
      content: (
        <BensDireitosSection
          bensDireitos={gia.bensDireitos}
          temSegundoDoador={gia.doadores.length > 1}
          onChange={(b) => handleUpdate({ bensDireitos: b })}
          disabled={!isEditable}
        />
      ),
    },
    {
      id: 'divisao-bens',
      label: '5. Divisao dos Bens',
      content: (
        <DivisaoBensSection
          bensDireitos={gia.bensDireitos}
          doadores={gia.doadores}
          donatarios={gia.donatarios}
          divisao={gia.divisaoBens}
          onChange={(d) => handleUpdate({ divisaoBens: d })}
          disabled={!isEditable}
        />
      ),
    },
    {
      id: 'anexar-documentos',
      label: '6. Anexar Documentos',
      content: (
        <AnexarDocumentosSection
          documentos={gia.documentos}
          qualidadeDeclarante={gia.dadosDeclarante.qualidade}
          doadores={gia.doadores}
          donatarios={gia.donatarios}
          bensDireitos={gia.bensDireitos}
          onChange={(docs) => handleUpdate({ documentos: docs })}
          disabled={!isEditable}
        />
      ),
    },
    {
      id: 'enviar-declaracao',
      label: '7. Enviar Declaracao',
      content: (
        <EnviarDeclaracaoSection
          gia={gia}
          onUpdate={handleUpdate}
        />
      ),
    },
    {
      id: 'calculo-itcmd',
      label: '8. Calculo do ITCMD',
      content: (
        <CalculoITCMDSection gia={gia} />
      ),
    },
  ];

  return (
    <div className="sefaz-form" style={{ maxWidth: '960px' }}>
      {/* Status Bar */}
      <div className="sefaz-form-section">
        <div className="sefaz-form-section__title" style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
          <span>
            GIA-ITCMD-e - Doacao
            {gia.id > 0 && ` - N. ${gia.id}`}
          </span>
          <span style={{
            background: statusBadgeColor,
            color: '#fff',
            padding: '2px 12px',
            borderRadius: '12px',
            fontSize: '11px',
          }}>
            SITUACAO: {statusLabels[gia.status]}
          </span>
        </div>
      </div>

      {message.text && (
        <Message type={message.type} text={message.text} onClose={() => setMessage({ type: '', text: '' })} />
      )}

      <Tabs tabs={tabs} activeTab={activeTab} onTabChange={setActiveTab} />

      {/* Botões globais na parte inferior */}
      {isEditable && (
        <div className="sefaz-btn-group" style={{ marginTop: '16px' }}>
          <button type="button" className="sefaz-btn sefaz-btn--danger" onClick={handleExcluir}>
            Excluir Declaracao
          </button>
          <button type="button" className="sefaz-btn sefaz-btn--primary" onClick={handleSalvar}>
            Salvar
          </button>
        </div>
      )}
    </div>
  );
};

export default GIAITCMDDoacao;
