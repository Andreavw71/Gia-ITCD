import React from 'react';
import CrudTabelaBasica from '../CrudTabelaBasica';
import { listarConstrucoes, incluirConstrucao, alterarConstrucao } from '../../../services/tabelasBasicasService';

const ConstrucaoPage: React.FC = () => {
  return (
    <CrudTabelaBasica
      title="Construcao"
      columns={[
        { key: 'descricao', label: 'Descricao', type: 'text' },
        { key: 'valorUnitario', label: 'Valor Unitario (R$)', type: 'number' },
        { key: 'ativo', label: 'Situacao', type: 'boolean' },
      ]}
      fetchData={listarConstrucoes}
      onSave={incluirConstrucao}
      onUpdate={alterarConstrucao}
    />
  );
};

export default ConstrucaoPage;
