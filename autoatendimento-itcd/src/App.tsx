import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Layout from './components/layout/Layout';
import MenuPrincipal from './pages/MenuPrincipal';
import IncluirGIAITCD from './pages/giaitcd/IncluirGIAITCD';
import InventarioArrolamento from './pages/giaitcd/inventario/InventarioArrolamento';
import DoacaoOutros from './pages/giaitcd/doacao/DoacaoOutros';
import SeparacaoDivorcio from './pages/giaitcd/separacao/SeparacaoDivorcio';
import ConsultarGIAITCD from './pages/giaitcd/consulta/ConsultarGIAITCD';
import ImprimirGIAITCD from './pages/giaitcd/consulta/ImprimirGIAITCD';
import ImprimirDAR from './pages/giaitcd/consulta/ImprimirDAR';
import ProtocolarGIAITCD from './pages/giaitcd/protocolo/ProtocolarGIAITCD';
import Autenticidade from './pages/autenticidade/Autenticidade';
import GIAITCMDDoacao from './pages/giaitcmd-doacao/GIAITCMDDoacao';
import './styles/sefaz.css';

const App: React.FC = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<MenuPrincipal />} />

          {/* GIA-ITCD */}
          <Route path="giaitcd/incluir" element={<IncluirGIAITCD />} />
          <Route path="giaitcd/inventario/novo" element={<InventarioArrolamento />} />
          <Route path="giaitcd/inventario/:id" element={<InventarioArrolamento />} />
          <Route path="giaitcd/doacao/novo" element={<DoacaoOutros />} />
          <Route path="giaitcd/doacao/:id" element={<DoacaoOutros />} />
          <Route path="giaitcd/separacao/novo" element={<SeparacaoDivorcio />} />
          <Route path="giaitcd/separacao/:id" element={<SeparacaoDivorcio />} />

          {/* Consulta / Alteração */}
          <Route path="giaitcd/consultar" element={<ConsultarGIAITCD />} />
          <Route path="giaitcd/alterar" element={<ConsultarGIAITCD />} />

          {/* Impressão */}
          <Route path="giaitcd/imprimir" element={<ImprimirGIAITCD />} />
          <Route path="giaitcd/imprimir-dar" element={<ImprimirDAR />} />

          {/* Protocolo */}
          <Route path="giaitcd/protocolo" element={<ProtocolarGIAITCD />} />
          <Route path="giaitcd/validar-processo" element={<ProtocolarGIAITCD />} />
          <Route path="giaitcd/consultar-processo" element={<ConsultarGIAITCD />} />

          {/* GIA-ITCMD-e Doação - Autorregularização (novo módulo) */}
          <Route path="giaitcmd/doacao/novo" element={<GIAITCMDDoacao />} />
          <Route path="giaitcmd/doacao/:id" element={<GIAITCMDDoacao />} />

          {/* Autenticidade */}
          <Route path="autenticidade" element={<Autenticidade />} />
        </Route>
      </Routes>
    </Router>
  );
};

export default App;
