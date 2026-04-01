import React from 'react';

interface FormSectionProps {
  title: string;
  children: React.ReactNode;
}

const FormSection: React.FC<FormSectionProps> = ({ title, children }) => {
  return (
    <div className="sefaz-form-section">
      <div className="sefaz-form-section__title">{title}</div>
      <div className="sefaz-form-section__content">{children}</div>
    </div>
  );
};

export default FormSection;
