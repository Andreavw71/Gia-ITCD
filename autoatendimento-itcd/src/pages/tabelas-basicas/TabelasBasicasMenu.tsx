import React from 'react';
import { Link } from 'react-router-dom';

const TabelasBasicasMenu: React.FC = () => {
  return (
    <div>
      <div className="sefaz-menu-section">
        <div className="sefaz-menu-section__title">Tabelas Basicas</div>
        <ul className="sefaz-menu-section__items">
          <li><Link to="/tabelas-basicas/bem">Bem</Link></li>
          <li><Link to="/tabelas-basicas/construcao">Construcao</Link></li>
          <li><Link to="/tabelas-basicas/benfeitoria">Benfeitoria</Link></li>
          <li><Link to="/tabelas-basicas/rebanho">Rebanho</Link></li>
          <li><Link to="/tabelas-basicas/natureza-operacao">Natureza da Operacao</Link></li>
          <li><Link to="/tabelas-basicas/parametros-legislacao">Parametros da Legislacao</Link></li>
          <li><Link to="/tabelas-basicas/distancia">Distancia entre Municipios</Link></li>
        </ul>
      </div>
    </div>
  );
};

export default TabelasBasicasMenu;
