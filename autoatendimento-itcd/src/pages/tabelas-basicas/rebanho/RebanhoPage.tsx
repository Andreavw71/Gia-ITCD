import React from 'react';
import CrudTabelaBasica from '../CrudTabelaBasica';
import { listarRebanhos, incluirRebanho, alterarRebanho } from '../../../services/tabelasBasicasService';

const RebanhoPage: React.FC = () => {
  return (
    <CrudTabelaBasica
      title="Rebanho"
      columns={[
        { key: 'especie', label: 'Especie', type: 'text' },
        { key: 'valorUnitario', label: 'Valor Unitario (R$)', type: 'number' },
        { key: 'ativo', label: 'Situacao', type: 'boolean' },
      ]}
      fetchData={listarRebanhos}
      onSave={incluirRebanho}
      onUpdate={alterarRebanho}
    />
  );
};

export default RebanhoPage;
