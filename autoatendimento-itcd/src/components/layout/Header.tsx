import React from 'react';
import { Link } from 'react-router-dom';

const Header: React.FC = () => {
  return (
    <header className="sefaz-header">
      <div className="sefaz-header__logo">
        <div>
          <div className="sefaz-header__title">SEFAZ-MT</div>
          <div className="sefaz-header__subtitle">
            Secretaria de Estado de Fazenda de Mato Grosso
          </div>
        </div>
      </div>
      <div style={{ textAlign: 'center' }}>
        <div style={{ fontSize: '14px', fontWeight: 'bold' }}>
          GIA-ITCD - Autoatendimento
        </div>
        <div style={{ fontSize: '10px' }}>
          Guia de Informação e Apuração do ITCD
        </div>
      </div>
      <div className="sefaz-header__user">
        <Link to="/" style={{ color: '#FFFFFF', fontSize: '11px' }}>
          Menu Principal
        </Link>
      </div>
    </header>
  );
};

export default Header;
