import React from 'react';
import CrudTabelaBasica from '../CrudTabelaBasica';
import { listarBenfeitorias, incluirBenfeitoria, alterarBenfeitoria } from '../../../services/tabelasBasicasService';

const BenfeitoriaPage: React.FC = () => {
  return (
    <CrudTabelaBasica
      title="Benfeitoria"
      columns={[
        { key: 'descricao', label: 'Descricao', type: 'text' },
        { key: 'valorUnitario', label: 'Valor Unitario (R$)', type: 'number' },
        { key: 'ativo', label: 'Situacao', type: 'boolean' },
      ]}
      fetchData={listarBenfeitorias}
      onSave={incluirBenfeitoria}
      onUpdate={alterarBenfeitoria}
    />
  );
};

export default BenfeitoriaPage;
