import React from 'react';

type MessageType = 'error' | 'success' | 'warning' | 'info';

interface MessageProps {
  type: MessageType;
  text: string;
  onClose?: () => void;
}

const Message: React.FC<MessageProps> = ({ type, text, onClose }) => {
  if (!text) return null;

  return (
    <div className={`sefaz-msg sefaz-msg--${type}`}>
      {text}
      {onClose && (
        <button
          type="button"
          onClick={onClose}
          style={{
            float: 'right',
            background: 'none',
            border: 'none',
            cursor: 'pointer',
            fontWeight: 'bold',
          }}
        >
          X
        </button>
      )}
    </div>
  );
};

export default Message;
