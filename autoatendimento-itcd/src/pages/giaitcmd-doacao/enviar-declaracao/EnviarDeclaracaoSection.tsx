import React, { useState } from 'react';
import FormSection from '../../../components/common/FormSection';
import Modal from '../../../components/common/Modal';
import { GIAITCMD, StatusGIAITCMD, Pendencia } from '../../../types/giaitcmd';

interface Props {
  gia: GIAITCMD;
  onUpdate: (updates: Partial<GIAITCMD>) => void;
}

const EnviarDeclaracaoSection: React.FC<Props> = ({ gia, onUpdate }) => {
  const [pendenciasChecked, setPendenciasChecked] = useState(false);
  const [showRetificar, setShowRetificar] = useState(false);
  const [showCancelar, setShowCancelar] = useState(false);
  const [motivoCancelamento, setMotivoCancelamento] = useState('');
  const [motivoRetificacao, setMotivoRetificacao] = useState('');
  const [tipoRetificacao, setTipoRetificacao] = useState<'INICIATIVA_PROPRIA' | 'AUTORREGULARIZACAO'>('INICIATIVA_PROPRIA');

  const isEmElaboracao = gia.status === StatusGIAITCMD.EM_ELABORACAO;
  const isEnviada = gia.status !== StatusGIAITCMD.EM_ELABORACAO;

  const verificarPendencias = () => {
    const pendencias: Pendencia[] = [];
    if (!gia.dadosDeclarante.email) pendencias.push({ secao: 'Dados Declarante', descricao: 'E-mail nao informado.', tipo: 'ERRO' });
    if (gia.doadores.length === 0) pendencias.push({ secao: 'Partes Interessadas', descricao: 'Nenhum doador cadastrado.', tipo: 'ERRO' });
    if (gia.donatarios.length === 0) pendencias.push({ secao: 'Partes Interessadas', descricao: 'Nenhum donatario cadastrado.', tipo: 'ERRO' });
    if (gia.bensDireitos.length === 0) pendencias.push({ secao: 'Bens e Direitos', descricao: 'Nenhum bem ou direito cadastrado.', tipo: 'ERRO' });
    if (gia.fatoGerador.situacaoDoacao === 'JA_REALIZADA' && !gia.fatoGerador.dataDoacao) {
      pendencias.push({ secao: 'Fato Gerador', descricao: 'Data da doacao nao informada.', tipo: 'ERRO' });
    }
    // Verificar divisao
    const divisaoIncompleta = gia.bensDireitos.some((bem) => {
      const total = gia.divisaoBens.filter((d) => d.bemId === bem.id).reduce((s, d) => s + d.percentual, 0);
      return total === 0;
    });
    if (divisaoIncompleta) pendencias.push({ secao: 'Divisao dos Bens', descricao: 'Distribuicao dos bens entre donatarios incompleta.', tipo: 'ERRO' });

    onUpdate({ pendencias });
    setPendenciasChecked(true);
  };

  const enviarDeclaracao = () => {
    if (gia.pendencias.filter((p) => p.tipo === 'ERRO').length > 0) {
      alert('Existem pendencias que impedem o envio. Corrija-as antes de enviar.');
      return;
    }
    if (window.confirm('Deseja realmente enviar esta GIA-ITCMD?')) {
      onUpdate({ status: StatusGIAITCMD.ENVIADA, dataEnvio: new Date().toISOString() });
    }
  };

  const handleCancelar = () => {
    if (!motivoCancelamento.trim()) { alert('Informe os motivos do cancelamento.'); return; }
    onUpdate({ status: StatusGIAITCMD.PEDIDO_CANCELAMENTO_ANALISE });
    setShowCancelar(false);
  };

  return (
    <div>
      {isEmElaboracao ? (
        <FormSection title="Envio da GIA-ITCMD">
          <div className="sefaz-msg sefaz-msg--info" style={{ marginBottom: '12px' }}>
            Antes de enviar sua Declaracao, revise atentamente todos os campos preenchidos e os
            documentos anexados. Enquanto a Declaracao estiver com o status "EM ELABORACAO", voce
            podera alterar qualquer dado. Utilize a opcao "Verificar Pendencias" antes do envio.
          </div>

          <div className="sefaz-btn-group">
            <button type="button" className="sefaz-btn sefaz-btn--primary" onClick={verificarPendencias}>
              Verificar Pendencias
            </button>
            {pendenciasChecked && (
              <button type="button" className="sefaz-btn sefaz-btn--success" onClick={enviarDeclaracao}
                disabled={gia.pendencias.filter((p) => p.tipo === 'ERRO').length > 0}>
                Enviar Declaracao
              </button>
            )}
            <button type="button" className="sefaz-btn">Imprimir Declaracao</button>
            <button type="button" className="sefaz-btn sefaz-btn--danger"
              onClick={() => {
                if (window.confirm('Deseja realmente excluir esta declaracao?')) {
                  onUpdate({ status: StatusGIAITCMD.CANCELADA });
                }
              }}>
              Excluir Declaracao
            </button>
          </div>

          {/* Pendências */}
          {pendenciasChecked && gia.pendencias.length > 0 && (
            <div style={{ marginTop: '12px' }}>
              <div className="sefaz-tr-subtitulo">Pendencias Encontradas</div>
              <table className="sefaz-table">
                <thead>
                  <tr><th>Tipo</th><th>Secao</th><th>Descricao</th></tr>
                </thead>
                <tbody>
                  {gia.pendencias.map((p, i) => (
                    <tr key={i}>
                      <td style={{ color: p.tipo === 'ERRO' ? '#CC0000' : '#FF9800', fontWeight: 'bold' }}>
                        {p.tipo === 'ERRO' ? 'ERRO' : 'AVISO'}
                      </td>
                      <td>{p.secao}</td>
                      <td>{p.descricao}</td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          )}
          {pendenciasChecked && gia.pendencias.length === 0 && (
            <div className="sefaz-msg sefaz-msg--success" style={{ marginTop: '12px' }}>
              Nenhuma pendencia encontrada. Voce pode enviar a declaracao.
            </div>
          )}
        </FormSection>
      ) : (
        <FormSection title="GIA-ITCMD Enviada">
          <div className="sefaz-msg sefaz-msg--info" style={{ marginBottom: '12px' }}>
            Depois de enviada, a GIA ITCMD somente pode ser retificada ou excluida apos analise
            da unidade competente da SEFAZ MT.
          </div>

          <div className="sefaz-btn-group">
            <button type="button" className="sefaz-btn" onClick={() => setShowCancelar(true)}>
              Solicitar Cancelamento
            </button>
            <button type="button" className="sefaz-btn sefaz-btn--primary" onClick={() => setShowRetificar(true)}>
              Retificar Declaracao
            </button>
            <button type="button" className="sefaz-btn">Imprimir Declaracao</button>
            <button type="button" className="sefaz-btn sefaz-btn--success">Imprimir DAR</button>
          </div>
        </FormSection>
      )}

      {/* Modal Cancelamento */}
      <Modal isOpen={showCancelar} onClose={() => setShowCancelar(false)} title="Solicitar Cancelamento">
        <div style={{ padding: '10px' }}>
          <div className="sefaz-form-row">
            <div className="sefaz-td-rotulo-entrada">Motivos do cancelamento:<span className="sefaz-required">*</span></div>
            <div className="sefaz-td-campo">
              <textarea className="sefaz-textarea" rows={4} style={{ width: '100%' }}
                value={motivoCancelamento} onChange={(e) => setMotivoCancelamento(e.target.value)} />
            </div>
          </div>
          <div className="sefaz-btn-group">
            <button type="button" className="sefaz-btn" onClick={() => setShowCancelar(false)}>Voltar</button>
            <button type="button" className="sefaz-btn sefaz-btn--danger" onClick={handleCancelar}>
              Confirmar pedido de cancelamento
            </button>
          </div>
        </div>
      </Modal>

      {/* Modal Retificação */}
      <Modal isOpen={showRetificar} onClose={() => setShowRetificar(false)} title="Retificar Declaracao">
        <div style={{ padding: '10px' }}>
          <div className="sefaz-form-row">
            <div className="sefaz-td-rotulo-entrada">Motivo da retificacao:</div>
            <div className="sefaz-td-campo">
              <label style={{ marginRight: '12px' }}>
                <input type="radio" checked={tipoRetificacao === 'INICIATIVA_PROPRIA'}
                  onChange={() => setTipoRetificacao('INICIATIVA_PROPRIA')} /> Iniciativa propria
              </label>
              <label>
                <input type="radio" checked={tipoRetificacao === 'AUTORREGULARIZACAO'}
                  onChange={() => setTipoRetificacao('AUTORREGULARIZACAO')} /> Autorregularizacao apos Notificacao
              </label>
            </div>
          </div>
          <div className="sefaz-form-row">
            <div className="sefaz-td-rotulo-entrada">Descricao:</div>
            <div className="sefaz-td-campo">
              <textarea className="sefaz-textarea" rows={4} style={{ width: '100%' }}
                value={motivoRetificacao} onChange={(e) => setMotivoRetificacao(e.target.value)} />
            </div>
          </div>
          <div className="sefaz-btn-group">
            <button type="button" className="sefaz-btn" onClick={() => setShowRetificar(false)}>Voltar</button>
            <button type="button" className="sefaz-btn sefaz-btn--primary"
              onClick={() => {
                onUpdate({ status: StatusGIAITCMD.PEDIDO_RETIFICACAO_ANALISE });
                setShowRetificar(false);
              }}>
              Abrir GIA-ITCMD para Retificacao
            </button>
          </div>
        </div>
      </Modal>
    </div>
  );
};

export default EnviarDeclaracaoSection;
