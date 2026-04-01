import React from 'react';

interface ModalProps {
  isOpen: boolean;
  onClose: () => void;
  title: string;
  children: React.ReactNode;
}

const Modal: React.FC<ModalProps> = ({ isOpen, onClose, title, children }) => {
  if (!isOpen) return null;

  return (
    <div className="sefaz-modal-overlay" onClick={onClose}>
      <div className="sefaz-modal" onClick={(e) => e.stopPropagation()}>
        <div className="sefaz-tr-subtitulo" style={{ marginBottom: '10px' }}>
          {title}
        </div>
        {children}
        <div className="sefaz-modal__close">
          <button type="button" className="sefaz-btn" onClick={onClose}>
            Fechar
          </button>
        </div>
      </div>
    </div>
  );
};

export default Modal;
