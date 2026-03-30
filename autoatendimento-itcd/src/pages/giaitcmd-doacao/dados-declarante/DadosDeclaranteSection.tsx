import React from 'react';
import FormSection from '../../../components/common/FormSection';
import { DadosDeclarante, QualidadeDeclarante } from '../../../types/giaitcmd';

const qualidadeLabels: Record<QualidadeDeclarante, string> = {
  [QualidadeDeclarante.DOADOR]: 'Doador',
  [QualidadeDeclarante.DONATARIO]: 'Donatario',
  [QualidadeDeclarante.DEFENSOR_PUBLICO]: 'Defensor Publico',
  [QualidadeDeclarante.TERCEIRO_PROCURACAO]: 'Terceiro com Procuracao',
  [QualidadeDeclarante.CONTABILISTA]: 'Profissional habilitado (Contabilista)',
  [QualidadeDeclarante.ADVOGADO]: 'Profissional habilitado (Advogado)',
  [QualidadeDeclarante.REP_PJ_PRIVADA]: 'Representante de PJ de Direito Privado',
  [QualidadeDeclarante.REP_PJ_PUBLICA]: 'Representante de PJ de Direito Publico',
};

interface Props {
  dados: DadosDeclarante;
  onChange: (dados: DadosDeclarante) => void;
  disabled: boolean;
}

const DadosDeclaranteSection: React.FC<Props> = ({ dados, onChange, disabled }) => {
  const handleChange = (field: keyof DadosDeclarante, value: any) => {
    onChange({ ...dados, [field]: value });
  };

  const handleEnderecoChange = (field: string, value: string) => {
    onChange({ ...dados, endereco: { ...dados.endereco, [field]: value } });
  };

  return (
    <div>
      <FormSection title="Dados do Declarante">
        <div className="sefaz-msg sefaz-msg--info" style={{ marginBottom: '12px' }}>
          Os campos CPF e Nome do declarante, fornecidos pelo sistema de login, estao bloqueados para edicao.
          Atualize obrigatoriamente o Endereco e o E-mail.
        </div>

        <div className="sefaz-form-row">
          <div className="sefaz-td-rotulo-entrada">CPF:</div>
          <div className="sefaz-td-campo">
            <input type="text" className="sefaz-input-text" value={dados.cpf} disabled style={{ width: '200px', background: '#eee' }} />
          </div>
        </div>
        <div className="sefaz-form-row">
          <div className="sefaz-td-rotulo-entrada">Nome:</div>
          <div className="sefaz-td-campo">
            <input type="text" className="sefaz-input-text" value={dados.nome} disabled style={{ width: '400px', background: '#eee' }} />
          </div>
        </div>

        <div className="sefaz-form-row">
          <div className="sefaz-td-rotulo-entrada">CEP:<span className="sefaz-required">*</span></div>
          <div className="sefaz-td-campo">
            <input type="text" className="sefaz-input-text" value={dados.endereco.cep}
              onChange={(e) => handleEnderecoChange('cep', e.target.value)}
              disabled={disabled} style={{ width: '120px' }} placeholder="00000-000" />
          </div>
        </div>
        <div className="sefaz-form-row">
          <div className="sefaz-td-rotulo-entrada">Endereco:<span className="sefaz-required">*</span></div>
          <div className="sefaz-td-campo">
            <input type="text" className="sefaz-input-text" value={dados.endereco.logradouro}
              onChange={(e) => handleEnderecoChange('logradouro', e.target.value)}
              disabled={disabled} style={{ width: '400px' }} />
          </div>
        </div>
        <div className="sefaz-form-row">
          <div className="sefaz-td-rotulo-entrada">Numero:</div>
          <div className="sefaz-td-campo">
            <input type="text" className="sefaz-input-text" value={dados.endereco.numero}
              onChange={(e) => handleEnderecoChange('numero', e.target.value)}
              disabled={disabled} style={{ width: '80px' }} />
          </div>
        </div>
        <div className="sefaz-form-row">
          <div className="sefaz-td-rotulo-entrada">Complemento:</div>
          <div className="sefaz-td-campo">
            <input type="text" className="sefaz-input-text" value={dados.endereco.complemento}
              onChange={(e) => handleEnderecoChange('complemento', e.target.value)}
              disabled={disabled} style={{ width: '300px' }} />
          </div>
        </div>
        <div className="sefaz-form-row">
          <div className="sefaz-td-rotulo-entrada">Cidade:</div>
          <div className="sefaz-td-campo">
            <input type="text" className="sefaz-input-text" value={dados.endereco.cidade}
              onChange={(e) => handleEnderecoChange('cidade', e.target.value)}
              disabled={disabled} style={{ width: '200px' }} />
          </div>
        </div>
        <div className="sefaz-form-row">
          <div className="sefaz-td-rotulo-entrada">Estado:</div>
          <div className="sefaz-td-campo">
            <input type="text" className="sefaz-input-text" value={dados.endereco.estado}
              onChange={(e) => handleEnderecoChange('estado', e.target.value)}
              disabled={disabled} style={{ width: '60px' }} />
          </div>
        </div>

        <div className="sefaz-form-row">
          <div className="sefaz-td-rotulo-entrada">E-mail:<span className="sefaz-required">*</span></div>
          <div className="sefaz-td-campo">
            <input type="email" className="sefaz-input-text" value={dados.email}
              onChange={(e) => handleChange('email', e.target.value)}
              disabled={disabled} style={{ width: '300px' }} />
          </div>
        </div>
      </FormSection>

      <FormSection title="Qualidade do Declarante">
        <div className="sefaz-msg sefaz-msg--info" style={{ marginBottom: '12px' }}>
          Selecione a opcao correspondente ao seu vinculo com as partes interessadas.
        </div>
        <div className="sefaz-form-row">
          <div className="sefaz-td-rotulo-entrada">Qualidade:<span className="sefaz-required">*</span></div>
          <div className="sefaz-td-campo">
            <select className="sefaz-select" value={dados.qualidade}
              onChange={(e) => handleChange('qualidade', e.target.value)}
              disabled={disabled} style={{ width: '400px' }}>
              {Object.entries(qualidadeLabels).map(([key, label]) => (
                <option key={key} value={key}>{label}</option>
              ))}
            </select>
          </div>
        </div>
      </FormSection>
    </div>
  );
};

export default DadosDeclaranteSection;
