import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import Modal from '../../components/common/Modal';

const IncluirGIAITCD: React.FC = () => {
  const [showInventarioInfo, setShowInventarioInfo] = useState(false);
  const [showDoacaoInfo, setShowDoacaoInfo] = useState(false);
  const [showSeparacaoInfo, setShowSeparacaoInfo] = useState(false);

  return (
    <div className="sefaz-incluir-gia">
      <div className="sefaz-form-section">
        <div className="sefaz-form-section__title">
          Incluir GIA-ITCD
        </div>
      </div>

      {/* Causa Mortis */}
      <div className="sefaz-incluir-gia__section">
        <div className="sefaz-incluir-gia__section-title">Causa Mortis</div>
        <div className="sefaz-incluir-gia__link">
          <Link to="/giaitcd/inventario/novo">Inventario/Arrolamento</Link>
          {' '}
          <button
            type="button"
            className="sefaz-btn"
            onClick={() => setShowInventarioInfo(true)}
            style={{ fontSize: '10px', padding: '2px 6px' }}
          >
            ?
          </button>
        </div>
      </div>

      {/* Inter Vivos */}
      <div className="sefaz-incluir-gia__section">
        <div className="sefaz-incluir-gia__section-title">Inter Vivos</div>
        <div className="sefaz-incluir-gia__link">
          <Link to="/giaitcd/doacao/novo">Doacao/Outros</Link>
          {' '}
          <button
            type="button"
            className="sefaz-btn"
            onClick={() => setShowDoacaoInfo(true)}
            style={{ fontSize: '10px', padding: '2px 6px' }}
          >
            ?
          </button>
        </div>
        <div className="sefaz-incluir-gia__link">
          <Link to="/giaitcd/separacao/novo">Separacao/Divorcio/Partilha</Link>
          {' '}
          <button
            type="button"
            className="sefaz-btn"
            onClick={() => setShowSeparacaoInfo(true)}
            style={{ fontSize: '10px', padding: '2px 6px' }}
          >
            ?
          </button>
        </div>
      </div>

      <div className="sefaz-btn-group">
        <Link to="/" className="sefaz-btn">
          Voltar
        </Link>
      </div>

      {/* Modal Inventário */}
      <Modal
        isOpen={showInventarioInfo}
        onClose={() => setShowInventarioInfo(false)}
        title="CASOS EM QUE NAO SE DEVE UTILIZAR A GIA ITCD INVENTARIO"
      >
        <div style={{ padding: '10px', fontSize: '11px', textAlign: 'justify' }}>
          <p style={{ marginBottom: '10px' }}>
            Nao deve ser utilizada a GIA ITCD para apuracao do imposto devido nas
            transmissoes causa mortis, uma vez que o Sistema nao comporta a forma de divisao dos bens
            (meacoes, quinhoes hereditarios e/ou legados), nos casos em que o de cujus:
          </p>
          <ul style={{ marginLeft: '20px', lineHeight: '1.8' }}>
            <li>houver deixado testamento;</li>
            <li>
              tinha companheiro(a)/convivente (cuja uniao estavel devera ser comprovada atraves de
              declaracao em cartorio ou declaracao dos herdeiros legitimos);
            </li>
            <li>
              a data de falecimento do de cujus e anterior a 11 de janeiro de 2003, o mesmo era
              casado no regime da comunhao parcial de bens e tinha bens particulares;
            </li>
            <li>
              a data de falecimento do de cujus e igual ou posterior a 11 de janeiro de 2003, o
              mesmo era casado no regime da comunhao parcial de bens, tinha bens particulares e 4
              (quatro) ou mais descendentes;
            </li>
            <li>
              a data de falecimento do de cujus e igual ou posterior a 11 de janeiro de 2003, o
              mesmo era casado no regime da comunhao parcial de bens, tinha bens particulares e o
              conjuge sobrevivo desejar renunciar abdicativamente a heranca;
            </li>
            <li>nos casos de sobrepartilha, em que ja houve recolhimento parcial do ITCD.</li>
          </ul>
          <p style={{ marginTop: '10px' }}>
            Deve-se, para os casos acima elencados, preencher em substituicao a GIA ITCD a
            "Declaracao de ITCD", Anexo VI, da Portaria n. 177/2018, realizando o protocolo da
            mesma atraves de e-process "PROTOCOLO DE GIA ITCD".
          </p>
        </div>
      </Modal>

      {/* Modal Doação */}
      <Modal
        isOpen={showDoacaoInfo}
        onClose={() => setShowDoacaoInfo(false)}
        title="CASOS EM QUE NAO SE DEVE UTILIZAR A GIA ITCD DOACAO/OUTROS"
      >
        <div style={{ padding: '10px', fontSize: '11px', textAlign: 'justify' }}>
          <p style={{ marginBottom: '10px' }}>
            Na Renuncia de Usufruto, se houver grandes melhorias feitas no bem pelo usufrutuario, a
            transmissao das mesmas ao nu-proprietario correspondera doacao plena (base de calculo
            100%), devendo ser apresentado o Anexo VI, da Portaria n. 177/2018, em substituicao a GIA
            ITCD.
          </p>
          <p style={{ marginTop: '10px' }}>
            Com relacao a debitos de cruzamento de dados SEFAZ x Receita Federal:
          </p>
          <ul style={{ marginLeft: '20px', lineHeight: '1.8' }}>
            <li>
              Se a doacao recebida ate dezembro do ano passado for de bens moveis (dinheiro em especie,
              por exemplo) e ainda nao houve a emissao de Aviso de Cobranca Fazendaria, faca a denuncia
              espontanea.
            </li>
          </ul>
        </div>
      </Modal>

      {/* Modal Separação */}
      <Modal
        isOpen={showSeparacaoInfo}
        onClose={() => setShowSeparacaoInfo(false)}
        title="CASOS EM QUE NAO SE DEVE UTILIZAR A GIA ITCD SEPARACAO/DIVORCIO PARTILHA"
      >
        <div style={{ padding: '10px', fontSize: '11px', textAlign: 'justify' }}>
          <p style={{ marginBottom: '10px' }}>
            Nao deve ser utilizada a GIA ITCD para apuracao do imposto devido nas transmissoes
            decorrentes da dissolucao da sociedade conjugal, nos casos em que:
          </p>
          <ul style={{ marginLeft: '20px', lineHeight: '1.8' }}>
            <li>
              houver partilha desigual de bens, com excesso de meacao para um dos conjuges ou nao,
              e no patrimonio do casal existirem bens cuja competencia para cobranca do imposto de
              transmissao sejam de outras Unidades da Federacao.
            </li>
          </ul>
          <p style={{ marginTop: '10px' }}>
            Deve-se, para os casos acima elencados, preencher em substituicao a GIA ITCD a
            "Declaracao de ITCD", Anexo VI, da Portaria n. 177/2018, realizando o protocolo da mesma
            atraves de e-process "PROTOCOLO DE GIA ITCD (E ANEXO VI DA PORTARIA n. 177/2018)".
          </p>
        </div>
      </Modal>
    </div>
  );
};

export default IncluirGIAITCD;
