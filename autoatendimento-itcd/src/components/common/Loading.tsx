import React from 'react';

interface LoadingProps {
  message?: string;
}

const Loading: React.FC<LoadingProps> = ({ message = 'Carregando...' }) => {
  return (
    <div className="sefaz-loading">
      <div className="sefaz-loading__spinner" />
      <p style={{ marginTop: '8px' }}>{message}</p>
    </div>
  );
};

export default Loading;
