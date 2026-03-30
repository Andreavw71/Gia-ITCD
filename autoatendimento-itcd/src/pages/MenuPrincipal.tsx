import React from 'react';
import { Link } from 'react-router-dom';

const MenuPrincipal: React.FC = () => {
  return (
    <div>
      <div className="sefaz-menu-section">
        <div className="sefaz-menu-section__title">Manuais</div>
        <ul className="sefaz-menu-section__items">
          <li>
            <a
              href="https://www5.sefaz.mt.gov.br/documents/d/sefaz/manual-da-gia-itcd-e-inventario_arrolamento-1"
              target="_blank"
              rel="noopener noreferrer"
            >
              Manual da GIA-ITCD Inventario Arrolamento
            </a>
          </li>
          <li>
            <a
              href="https://www5.sefaz.mt.gov.br/documents/d/sefaz/manual-da-gia-itcd-e-doacao_outros-1"
              target="_blank"
              rel="noopener noreferrer"
            >
              Manual da GIA-ITCD Doacao / Outros
            </a>
          </li>
          <li>
            <a
              href="https://www5.sefaz.mt.gov.br/documents/d/sefaz/manual-da-gia-itcd-e-separacao_divorcio_partilha-1"
              target="_blank"
              rel="noopener noreferrer"
            >
              Manual da GIA-ITCD Separacao/Divorcio/Partilha
            </a>
          </li>
        </ul>
      </div>

      <div className="sefaz-menu-section">
        <div className="sefaz-menu-section__title">GIA-ITCD</div>
        <ul className="sefaz-menu-section__items">
          <li>
            <Link to="/giaitcd/incluir">Incluir GIA-ITCD</Link>
            <ul className="sefaz-menu-section__items" style={{ paddingLeft: '20px' }}>
              <li>
                <Link to="/giaitcd/alterar">Alterar GIA-ITCD</Link>
              </li>
              <li>
                <Link to="/giaitcd/consultar">Consulta GIA-ITCD</Link>
              </li>
            </ul>
          </li>
          <li>
            <Link to="/giaitcd/imprimir">Imprimir GIA-ITCD</Link>
          </li>
          <li>
            <Link to="/giaitcd/protocolo">Preencher Processo</Link>
          </li>
          <li>
            <Link to="/giaitcd/validar-processo">Validar Processo</Link>
          </li>
          <li>
            <Link to="/giaitcd/consultar-processo">Consultar Processo</Link>
          </li>
          <li>
            <Link to="/giaitcd/imprimir-dar">Imprimir DAR/Declaracoes</Link>
          </li>
        </ul>
      </div>

      <div className="sefaz-menu-section">
        <div className="sefaz-menu-section__title">Autenticidade</div>
        <ul className="sefaz-menu-section__items">
          <li>
            <Link to="/autenticidade">Autenticidade</Link>
          </li>
        </ul>
      </div>
    </div>
  );
};

export default MenuPrincipal;
