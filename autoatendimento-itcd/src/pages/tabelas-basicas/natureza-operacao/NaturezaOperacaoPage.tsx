import React from 'react';
import CrudTabelaBasica from '../CrudTabelaBasica';
import {
  listarNaturezasOperacao,
  incluirNaturezaOperacao,
  alterarNaturezaOperacao,
} from '../../../services/tabelasBasicasService';

const NaturezaOperacaoPage: React.FC = () => {
  return (
    <CrudTabelaBasica
      title="Natureza da Operacao"
      columns={[
        { key: 'descricaoNaturezaOperacao', label: 'Descricao', type: 'text' },
        { key: 'ativo', label: 'Situacao', type: 'boolean' },
      ]}
      fetchData={listarNaturezasOperacao}
      onSave={incluirNaturezaOperacao}
      onUpdate={alterarNaturezaOperacao}
    />
  );
};

export default NaturezaOperacaoPage;
