import React from 'react';
import CrudTabelaBasica from '../CrudTabelaBasica';
import {
  listarParametrosLegislacao,
  incluirParametroLegislacao,
  alterarParametroLegislacao,
} from '../../../services/tabelasBasicasService';

const ParametrosLegislacaoPage: React.FC = () => {
  return (
    <CrudTabelaBasica
      title="Parametros da Legislacao"
      columns={[
        { key: 'descricao', label: 'Descricao', type: 'text' },
        { key: 'valor', label: 'Valor', type: 'number' },
        { key: 'dataInicio', label: 'Data Inicio', type: 'text' },
        { key: 'dataFim', label: 'Data Fim', type: 'text' },
        { key: 'ativo', label: 'Situacao', type: 'boolean' },
      ]}
      fetchData={listarParametrosLegislacao}
      onSave={incluirParametroLegislacao}
      onUpdate={alterarParametroLegislacao}
    />
  );
};

export default ParametrosLegislacaoPage;
