import React from 'react';
import CrudTabelaBasica from '../CrudTabelaBasica';
import { listarBens, incluirBem, alterarBem } from '../../../services/tabelasBasicasService';

const BemPage: React.FC = () => {
  return (
    <CrudTabelaBasica
      title="Bem"
      columns={[
        { key: 'descricao', label: 'Descricao', type: 'text' },
        { key: 'tipoBem', label: 'Tipo do Bem', type: 'text' },
        { key: 'ativo', label: 'Situacao', type: 'boolean' },
      ]}
      fetchData={listarBens}
      onSave={incluirBem}
      onUpdate={alterarBem}
    />
  );
};

export default BemPage;
