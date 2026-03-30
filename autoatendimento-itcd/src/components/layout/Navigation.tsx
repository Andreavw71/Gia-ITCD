import React from 'react';
import { NavLink } from 'react-router-dom';

const Navigation: React.FC = () => {
  return (
    <nav className="sefaz-nav">
      <ul className="sefaz-nav__list">
        <li className="sefaz-nav__item">
          <NavLink
            to="/"
            className={({ isActive }) =>
              `sefaz-nav__link ${isActive ? 'sefaz-nav__link--active' : ''}`
            }
            end
          >
            Menu Principal
          </NavLink>
        </li>
        <li className="sefaz-nav__item">
          <NavLink
            to="/giaitcd/incluir"
            className={({ isActive }) =>
              `sefaz-nav__link ${isActive ? 'sefaz-nav__link--active' : ''}`
            }
          >
            Incluir GIA-ITCD
          </NavLink>
        </li>
        <li className="sefaz-nav__item">
          <NavLink
            to="/giaitcd/consultar"
            className={({ isActive }) =>
              `sefaz-nav__link ${isActive ? 'sefaz-nav__link--active' : ''}`
            }
          >
            Consultar GIA-ITCD
          </NavLink>
        </li>
        <li className="sefaz-nav__item">
          <NavLink
            to="/giaitcd/imprimir"
            className={({ isActive }) =>
              `sefaz-nav__link ${isActive ? 'sefaz-nav__link--active' : ''}`
            }
          >
            Imprimir GIA-ITCD
          </NavLink>
        </li>
        <li className="sefaz-nav__item">
          <NavLink
            to="/giaitcd/protocolo"
            className={({ isActive }) =>
              `sefaz-nav__link ${isActive ? 'sefaz-nav__link--active' : ''}`
            }
          >
            Protocolo
          </NavLink>
        </li>
        <li className="sefaz-nav__item">
          <NavLink
            to="/giaitcd/imprimir-dar"
            className={({ isActive }) =>
              `sefaz-nav__link ${isActive ? 'sefaz-nav__link--active' : ''}`
            }
          >
            DAR/Declarações
          </NavLink>
        </li>
        <li className="sefaz-nav__item">
          <NavLink
            to="/autenticidade"
            className={({ isActive }) =>
              `sefaz-nav__link ${isActive ? 'sefaz-nav__link--active' : ''}`
            }
          >
            Autenticidade
          </NavLink>
        </li>
      </ul>
    </nav>
  );
};

export default Navigation;
