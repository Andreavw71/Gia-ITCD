import React, { useState } from 'react';
import Tabs from '../../../components/common/Tabs';
import FormSection from '../../../components/common/FormSection';
import {
  DocumentoAnexado, QualidadeDeclarante, Doador, Donatario, BemDireito, PessoaFisica,
} from '../../../types/giaitcmd';

const DOC_TIPOS_FATO_GERADOR = [
  'Contrato particular com assinatura das partes',
  'Escritura Publica de Doacao',
  'Extrato Bancario',
  'Comprovante de Transferencia Bancaria',
  'Outros documentos que comprovem a doacao',
];

const DOC_TIPOS_PARTES = [
  'Documento de identificacao com foto',
  'Comprovante de Inscricao e Situacao Cadastral no CPF',
  'Comprovante de Endereco',
  'Certidao de nascimento ou casamento',
  'Escritura Publica de Uniao Estavel',
  'Outros documentos',
];

interface Props {
  documentos: DocumentoAnexado[];
  qualidadeDeclarante: QualidadeDeclarante;
  doadores: Doador[];
  donatarios: Donatario[];
  bensDireitos: BemDireito[];
  onChange: (docs: DocumentoAnexado[]) => void;
  disabled: boolean;
}

const AnexarDocumentosSection: React.FC<Props> = ({
  documentos, qualidadeDeclarante, doadores, donatarios, bensDireitos, onChange, disabled,
}) => {
  const [tipoDoc, setTipoDoc] = useState('');
  const [observacoes, setObservacoes] = useState('');

  const addDocumento = (secao: DocumentoAnexado['secao'], parteId?: number, bemId?: number) => {
    if (!tipoDoc) { alert('Selecione o tipo de documento.'); return; }
    const doc: DocumentoAnexado = {
      id: Date.now(),
      tipoDocumento: tipoDoc,
      nomeArquivo: 'documento_anexado.pdf',
      tamanhoKB: 150,
      observacoes,
      dataHoraInsercao: new Date().toISOString(),
      secao,
      parteInteressadaId: parteId,
      bemDireitoId: bemId,
    };
    onChange([...documentos, doc]);
    setTipoDoc('');
    setObservacoes('');
  };

  const removeDoc = (id: number) => {
    onChange(documentos.filter((d) => d.id !== id));
  };

  const renderDocList = (docs: DocumentoAnexado[]) => (
    docs.length > 0 ? (
      <table className="sefaz-table" style={{ marginTop: '8px' }}>
        <thead>
          <tr><th>Tipo</th><th>Arquivo</th><th>Obs</th><th>Data/Hora</th><th>Acoes</th></tr>
        </thead>
        <tbody>
          {docs.map((doc) => (
            <tr key={doc.id}>
              <td>{doc.tipoDocumento}</td>
              <td>{doc.nomeArquivo} ({doc.tamanhoKB}KB)</td>
              <td>{doc.observacoes || '-'}</td>
              <td>{new Date(doc.dataHoraInsercao).toLocaleString('pt-BR')}</td>
              <td>
                <button type="button" className="sefaz-btn sefaz-btn--danger" onClick={() => removeDoc(doc.id)} disabled={disabled}>
                  Excluir
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    ) : <p style={{ color: '#666', padding: '8px' }}>Nenhum documento anexado.</p>
  );

  const tabs = [
    {
      id: 'fato-gerador',
      label: 'Fato Gerador',
      content: (
        <FormSection title="Documentos do Fato Gerador">
          <div className="sefaz-form-row">
            <div className="sefaz-td-rotulo-entrada">Tipo de documento:</div>
            <div className="sefaz-td-campo">
              <select className="sefaz-select" value={tipoDoc} onChange={(e) => setTipoDoc(e.target.value)}
                disabled={disabled} style={{ width: '400px' }}>
                <option value="">Selecione...</option>
                {DOC_TIPOS_FATO_GERADOR.map((t) => <option key={t} value={t}>{t}</option>)}
              </select>
            </div>
          </div>
          <div className="sefaz-form-row">
            <div className="sefaz-td-rotulo-entrada">Documento:</div>
            <div className="sefaz-td-campo">
              <button type="button" className="sefaz-btn sefaz-btn--primary" disabled={disabled}>Anexar Documento</button>
            </div>
          </div>
          <div className="sefaz-form-row">
            <div className="sefaz-td-rotulo-entrada">Observacoes:</div>
            <div className="sefaz-td-campo">
              <textarea className="sefaz-textarea" rows={2} style={{ width: '400px' }}
                value={observacoes} onChange={(e) => setObservacoes(e.target.value)} disabled={disabled} />
            </div>
          </div>
          <div className="sefaz-btn-group">
            <button type="button" className="sefaz-btn sefaz-btn--primary"
              onClick={() => addDocumento('FATO_GERADOR')} disabled={disabled}>Salvar</button>
          </div>
          {renderDocList(documentos.filter((d) => d.secao === 'FATO_GERADOR'))}
        </FormSection>
      ),
    },
    {
      id: 'declarante',
      label: 'Declarante',
      disabled: [QualidadeDeclarante.DOADOR, QualidadeDeclarante.DONATARIO].includes(qualidadeDeclarante),
      content: (
        <FormSection title="Documentos do Declarante">
          <p style={{ padding: '8px', color: '#666' }}>
            Documentos que comprovem a capacidade postulatoria do Declarante.
          </p>
          {renderDocList(documentos.filter((d) => d.secao === 'DECLARANTE'))}
        </FormSection>
      ),
    },
    {
      id: 'partes',
      label: 'Partes Interessadas',
      content: (
        <FormSection title="Documentos das Partes Interessadas">
          {[...doadores.map((d, i) => ({ tipo: 'Doador', index: i + 1, id: d.id, nome: (d.dados as PessoaFisica).nome })),
            ...donatarios.map((d, i) => ({ tipo: 'Donatario', index: i + 1, id: d.id, nome: (d.dados as PessoaFisica).nome })),
          ].map((parte) => (
            <div key={`${parte.tipo}-${parte.id}`} style={{ marginBottom: '12px', border: '1px solid #eee', padding: '8px' }}>
              <div style={{ fontWeight: 'bold', marginBottom: '4px' }}>
                {parte.tipo} {parte.index}: {parte.nome || 'Nao informado'}
              </div>
              <button type="button" className="sefaz-btn sefaz-btn--primary" disabled={disabled}
                style={{ fontSize: '10px' }}>ANEXAR DOCUMENTOS</button>
              {renderDocList(documentos.filter((d) => d.secao === 'PARTES_INTERESSADAS' && d.parteInteressadaId === parte.id))}
            </div>
          ))}
        </FormSection>
      ),
    },
    {
      id: 'bens',
      label: 'Bens e Direitos',
      content: (
        <FormSection title="Documentos dos Bens e Direitos">
          {bensDireitos.map((bem, i) => (
            <div key={bem.id} style={{ marginBottom: '12px', border: '1px solid #eee', padding: '8px' }}>
              <div style={{ fontWeight: 'bold', marginBottom: '4px' }}>
                Bem {i + 1}: {bem.especie} - {bem.descricao || 'Sem descricao'}
              </div>
              <button type="button" className="sefaz-btn sefaz-btn--primary" disabled={disabled}
                style={{ fontSize: '10px' }}>ANEXAR DOCUMENTOS</button>
              {renderDocList(documentos.filter((d) => d.secao === 'BENS_DIREITOS' && d.bemDireitoId === bem.id))}
            </div>
          ))}
          {bensDireitos.length === 0 && (
            <p style={{ padding: '16px', color: '#666', textAlign: 'center' }}>Nenhum bem ou direito cadastrado.</p>
          )}
        </FormSection>
      ),
    },
  ];

  return <Tabs tabs={tabs} />;
};

export default AnexarDocumentosSection;
