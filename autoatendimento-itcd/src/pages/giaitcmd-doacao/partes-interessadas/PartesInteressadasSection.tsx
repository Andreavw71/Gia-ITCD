import React, { useState } from 'react';
import Tabs from '../../../components/common/Tabs';
import FormSection from '../../../components/common/FormSection';
import Modal from '../../../components/common/Modal';
import {
  Doador, Donatario, TipoPessoa, RegimeBens, QualidadeDeclarante,
  PessoaFisica, Endereco,
} from '../../../types/giaitcmd';

const emptyEndereco: Endereco = {
  cep: '', logradouro: '', numero: '', complemento: '', bairro: '',
  cidade: '', estado: '', pais: 'Brasil', naoSeAplica: false,
};

const emptyPessoaFisica: PessoaFisica = {
  tipoPessoa: TipoPessoa.PF_COM_CPF,
  cpf: '', nome: '', dataNascimento: '',
  endereco: { ...emptyEndereco },
  emails: [''], telefones: [{ prefixoPais: '+55', numero: '' }],
  representadoPorProcurador: false, possuiConjuge: false,
};

interface Props {
  doadores: Doador[];
  donatarios: Donatario[];
  qualidadeDeclarante: QualidadeDeclarante;
  onChangeDoadores: (d: Doador[]) => void;
  onChangeDonatarios: (d: Donatario[]) => void;
  disabled: boolean;
}

const PartesInteressadasSection: React.FC<Props> = ({
  doadores, donatarios, qualidadeDeclarante,
  onChangeDoadores, onChangeDonatarios, disabled,
}) => {
  const [showEstrangeiroInfo, setShowEstrangeiroInfo] = useState(false);

  const renderEnderecoFields = (
    endereco: Endereco,
    onEndChange: (field: string, value: any) => void,
    disabledFields: boolean,
  ) => (
    <>
      <div className="sefaz-form-row">
        <div className="sefaz-td-rotulo-entrada">CEP:</div>
        <div className="sefaz-td-campo" style={{ display: 'flex', gap: '8px', alignItems: 'center' }}>
          <input type="text" className="sefaz-input-text" value={endereco.cep}
            onChange={(e) => onEndChange('cep', e.target.value)}
            disabled={disabledFields || endereco.naoSeAplica} style={{ width: '120px' }} placeholder="00000-000" />
          <label style={{ fontSize: '10px' }}>
            <input type="checkbox" checked={endereco.naoSeAplica}
              onChange={(e) => onEndChange('naoSeAplica', e.target.checked)}
              disabled={disabledFields} /> Nao se aplica (residente no exterior)
          </label>
        </div>
      </div>
      <div className="sefaz-form-row">
        <div className="sefaz-td-rotulo-entrada">Endereco:</div>
        <div className="sefaz-td-campo">
          <input type="text" className="sefaz-input-text" value={endereco.logradouro}
            onChange={(e) => onEndChange('logradouro', e.target.value)}
            disabled={disabledFields} style={{ width: '350px' }} />
        </div>
      </div>
      <div className="sefaz-form-row">
        <div className="sefaz-td-rotulo-entrada">Numero:</div>
        <div className="sefaz-td-campo">
          <input type="text" className="sefaz-input-text" value={endereco.numero}
            onChange={(e) => onEndChange('numero', e.target.value)}
            disabled={disabledFields} style={{ width: '80px' }} />
        </div>
      </div>
      <div className="sefaz-form-row">
        <div className="sefaz-td-rotulo-entrada">Complemento:</div>
        <div className="sefaz-td-campo">
          <input type="text" className="sefaz-input-text" value={endereco.complemento}
            onChange={(e) => onEndChange('complemento', e.target.value)}
            disabled={disabledFields} style={{ width: '250px' }} />
        </div>
      </div>
      <div className="sefaz-form-row">
        <div className="sefaz-td-rotulo-entrada">Estado:</div>
        <div className="sefaz-td-campo">
          <input type="text" className="sefaz-input-text" value={endereco.estado}
            onChange={(e) => onEndChange('estado', e.target.value)}
            disabled={disabledFields && !endereco.naoSeAplica} style={{ width: '60px' }} />
        </div>
      </div>
      <div className="sefaz-form-row">
        <div className="sefaz-td-rotulo-entrada">Cidade:</div>
        <div className="sefaz-td-campo">
          <input type="text" className="sefaz-input-text" value={endereco.cidade}
            onChange={(e) => onEndChange('cidade', e.target.value)}
            disabled={disabledFields && !endereco.naoSeAplica} style={{ width: '200px' }} />
        </div>
      </div>
      <div className="sefaz-form-row">
        <div className="sefaz-td-rotulo-entrada">Pais:</div>
        <div className="sefaz-td-campo">
          <input type="text" className="sefaz-input-text" value={endereco.pais}
            onChange={(e) => onEndChange('pais', e.target.value)}
            disabled={disabledFields && !endereco.naoSeAplica} style={{ width: '200px' }} />
        </div>
      </div>
    </>
  );

  const renderDoadorForm = (doador: Doador, index: number) => {
    const dados = doador.dados as PessoaFisica;
    const updateDoador = (updates: Partial<PessoaFisica>) => {
      const newDoadores = [...doadores];
      newDoadores[index] = { ...doador, dados: { ...dados, ...updates } };
      onChangeDoadores(newDoadores);
    };
    const updateEndereco = (field: string, value: any) => {
      updateDoador({ endereco: { ...dados.endereco, [field]: value } });
    };

    return (
      <FormSection title={`${index + 1}o Doador`} key={doador.id}>
        <div className="sefaz-form-row">
          <div className="sefaz-td-rotulo-entrada">Tipo de Pessoa:<span className="sefaz-required">*</span></div>
          <div className="sefaz-td-campo">
            <select className="sefaz-select" value={dados.tipoPessoa}
              onChange={(e) => updateDoador({ tipoPessoa: e.target.value as TipoPessoa })}
              disabled={disabled} style={{ width: '280px' }}>
              <option value={TipoPessoa.PF_COM_CPF}>Pessoa fisica com CPF</option>
              <option value={TipoPessoa.PF_SEM_CPF}>Pessoa fisica sem CPF (estrangeiro)</option>
              <option value={TipoPessoa.PJ_CNPJ}>Pessoa juridica (CNPJ)</option>
            </select>
          </div>
        </div>

        {dados.tipoPessoa !== TipoPessoa.PF_SEM_CPF && (
          <div className="sefaz-form-row">
            <div className="sefaz-td-rotulo-entrada">
              {dados.tipoPessoa === TipoPessoa.PJ_CNPJ ? 'CNPJ' : 'CPF'}:<span className="sefaz-required">*</span>
            </div>
            <div className="sefaz-td-campo" style={{ display: 'flex', gap: '4px' }}>
              <input type="text" className="sefaz-input-text" value={dados.cpf}
                onChange={(e) => updateDoador({ cpf: e.target.value })}
                disabled={disabled} style={{ width: '180px' }} />
              <button type="button" className="sefaz-btn sefaz-btn--primary" disabled={disabled}>Pesquisar</button>
            </div>
          </div>
        )}

        <div className="sefaz-form-row">
          <div className="sefaz-td-rotulo-entrada">
            {dados.tipoPessoa === TipoPessoa.PJ_CNPJ ? 'Razao Social' : 'Nome'}:<span className="sefaz-required">*</span>
          </div>
          <div className="sefaz-td-campo">
            <input type="text" className="sefaz-input-text" value={dados.nome}
              onChange={(e) => updateDoador({ nome: e.target.value })}
              disabled={disabled} style={{ width: '400px' }} />
          </div>
        </div>

        {dados.tipoPessoa !== TipoPessoa.PJ_CNPJ && (
          <div className="sefaz-form-row">
            <div className="sefaz-td-rotulo-entrada">Data de Nascimento:</div>
            <div className="sefaz-td-campo">
              <input type="date" className="sefaz-input-text" value={dados.dataNascimento}
                onChange={(e) => updateDoador({ dataNascimento: e.target.value })}
                disabled={disabled} />
            </div>
          </div>
        )}

        {renderEnderecoFields(dados.endereco, updateEndereco, disabled)}

        <div className="sefaz-form-row">
          <div className="sefaz-td-rotulo-entrada">E-mail:<span className="sefaz-required">*</span></div>
          <div className="sefaz-td-campo">
            {dados.emails.map((email, ei) => (
              <div key={ei} style={{ marginBottom: '4px', display: 'flex', gap: '4px' }}>
                <input type="email" className="sefaz-input-text" value={email}
                  onChange={(e) => {
                    const newEmails = [...dados.emails];
                    newEmails[ei] = e.target.value;
                    updateDoador({ emails: newEmails });
                  }}
                  disabled={disabled} style={{ width: '280px' }} />
              </div>
            ))}
            <button type="button" className="sefaz-btn" disabled={disabled}
              onClick={() => updateDoador({ emails: [...dados.emails, ''] })}
              style={{ fontSize: '10px' }}>+ adicionar outro e-mail</button>
          </div>
        </div>

        <div className="sefaz-form-row">
          <div className="sefaz-td-rotulo-entrada">Telefone:</div>
          <div className="sefaz-td-campo">
            {dados.telefones.map((tel, ti) => (
              <div key={ti} style={{ marginBottom: '4px', display: 'flex', gap: '4px' }}>
                <input type="text" className="sefaz-input-text" value={tel.prefixoPais}
                  disabled style={{ width: '50px', background: '#eee' }} />
                <input type="text" className="sefaz-input-text" value={tel.numero}
                  onChange={(e) => {
                    const newTels = [...dados.telefones];
                    newTels[ti] = { ...tel, numero: e.target.value };
                    updateDoador({ telefones: newTels });
                  }}
                  disabled={disabled} style={{ width: '150px' }} />
              </div>
            ))}
            <button type="button" className="sefaz-btn" disabled={disabled}
              onClick={() => updateDoador({ telefones: [...dados.telefones, { prefixoPais: '+55', numero: '' }] })}
              style={{ fontSize: '10px' }}>+ adicionar outro telefone</button>
          </div>
        </div>

        <div className="sefaz-form-row">
          <div className="sefaz-td-rotulo-entrada">Representado por procurador?</div>
          <div className="sefaz-td-campo">
            <label style={{ marginRight: '12px' }}>
              <input type="radio" name={`procurador-doador-${index}`} checked={dados.representadoPorProcurador}
                onChange={() => updateDoador({ representadoPorProcurador: true })} disabled={disabled} /> SIM
            </label>
            <label>
              <input type="radio" name={`procurador-doador-${index}`} checked={!dados.representadoPorProcurador}
                onChange={() => updateDoador({ representadoPorProcurador: false })} disabled={disabled} /> NAO
            </label>
          </div>
        </div>

        {index === 0 && dados.tipoPessoa !== TipoPessoa.PJ_CNPJ && (
          <div className="sefaz-form-row">
            <div className="sefaz-td-rotulo-entrada">Possui conjuge ou companheiro(a)?</div>
            <div className="sefaz-td-campo">
              <label style={{ marginRight: '12px' }}>
                <input type="radio" name="possuiConjuge" checked={dados.possuiConjuge}
                  onChange={() => updateDoador({ possuiConjuge: true })} disabled={disabled} /> SIM
              </label>
              <label>
                <input type="radio" name="possuiConjuge" checked={!dados.possuiConjuge}
                  onChange={() => updateDoador({ possuiConjuge: false })} disabled={disabled} /> NAO
              </label>
            </div>
          </div>
        )}

        {index === 1 && (
          <div className="sefaz-form-row">
            <div className="sefaz-td-rotulo-entrada">Regime de Bens:<span className="sefaz-required">*</span></div>
            <div className="sefaz-td-campo">
              <select className="sefaz-select" value={doador.regimeBens || ''}
                onChange={(e) => {
                  const newDoadores = [...doadores];
                  newDoadores[index] = { ...doador, regimeBens: e.target.value as RegimeBens };
                  onChangeDoadores(newDoadores);
                }}
                disabled={disabled} style={{ width: '300px' }}>
                <option value="">Selecione...</option>
                <option value={RegimeBens.COMUNHAO_PARCIAL}>Comunhao parcial de bens</option>
                <option value={RegimeBens.COMUNHAO_UNIVERSAL}>Comunhao universal de bens</option>
                <option value={RegimeBens.SEPARACAO_CONVENCIONAL}>Separacao convencional de bens</option>
                <option value={RegimeBens.SEPARACAO_OBRIGATORIA}>Separacao obrigatoria de bens</option>
                <option value={RegimeBens.PARTICIPACAO_AQUESTOS}>Participacao final nos aquestos</option>
              </select>
            </div>
          </div>
        )}
      </FormSection>
    );
  };

  const addDoador = () => {
    const newDoador: Doador = { id: Date.now(), dados: { ...emptyPessoaFisica } };
    onChangeDoadores([...doadores, newDoador]);
  };

  const addDonatario = () => {
    const newDonatario: Donatario = {
      id: Date.now(),
      dados: { ...emptyPessoaFisica },
      menorDeIdade: false,
    };
    onChangeDonatarios([...donatarios, newDonatario]);
  };

  const add2oDoador = () => {
    const newDoador: Doador = { id: Date.now(), dados: { ...emptyPessoaFisica } };
    onChangeDoadores([...doadores, newDoador]);
  };

  const partesTab = [
    {
      id: 'doador',
      label: 'Doador',
      content: (
        <div>
          {doadores.length === 0 && (
            <div style={{ padding: '16px', textAlign: 'center' }}>
              <button type="button" className="sefaz-btn sefaz-btn--primary" onClick={addDoador} disabled={disabled}>
                + Adicionar 1o Doador
              </button>
            </div>
          )}
          {doadores.map((d, i) => renderDoadorForm(d, i))}
          {doadores.length === 1 && (doadores[0].dados as PessoaFisica).possuiConjuge && doadores.length < 2 && (
            <div style={{ padding: '8px', textAlign: 'center' }}>
              <button type="button" className="sefaz-btn sefaz-btn--primary" onClick={add2oDoador} disabled={disabled}>
                + Adicionar 2o DOADOR (conjuge/companheiro)
              </button>
            </div>
          )}
        </div>
      ),
    },
    {
      id: 'donatario',
      label: 'Donatario',
      content: (
        <div>
          {donatarios.length === 0 && (
            <div style={{ padding: '16px', textAlign: 'center' }}>
              <button type="button" className="sefaz-btn sefaz-btn--primary" onClick={addDonatario} disabled={disabled}>
                + Adicionar 1o Donatario
              </button>
            </div>
          )}
          {donatarios.map((donatario, index) => (
            <FormSection title={`${index + 1}o Donatario`} key={donatario.id}>
              {/* Same structure as Doador but for Donatario - simplified for brevity */}
              <div className="sefaz-form-row">
                <div className="sefaz-td-rotulo-entrada">Tipo de Pessoa:<span className="sefaz-required">*</span></div>
                <div className="sefaz-td-campo">
                  <select className="sefaz-select" disabled={disabled} style={{ width: '280px' }}
                    value={(donatario.dados as PessoaFisica).tipoPessoa}
                    onChange={(e) => {
                      const newDons = [...donatarios];
                      newDons[index] = { ...donatario, dados: { ...donatario.dados, tipoPessoa: e.target.value as TipoPessoa } as any };
                      onChangeDonatarios(newDons);
                    }}>
                    <option value={TipoPessoa.PF_COM_CPF}>Pessoa fisica com CPF</option>
                    <option value={TipoPessoa.PF_SEM_CPF}>Pessoa fisica sem CPF (estrangeiro)</option>
                    <option value={TipoPessoa.PJ_CNPJ}>Pessoa juridica (CNPJ)</option>
                  </select>
                </div>
              </div>
              <div className="sefaz-form-row">
                <div className="sefaz-td-rotulo-entrada">CPF/CNPJ:<span className="sefaz-required">*</span></div>
                <div className="sefaz-td-campo" style={{ display: 'flex', gap: '4px' }}>
                  <input type="text" className="sefaz-input-text"
                    value={(donatario.dados as PessoaFisica).cpf}
                    onChange={(e) => {
                      const newDons = [...donatarios];
                      newDons[index] = { ...donatario, dados: { ...donatario.dados, cpf: e.target.value } as any };
                      onChangeDonatarios(newDons);
                    }}
                    disabled={disabled} style={{ width: '180px' }} />
                  <button type="button" className="sefaz-btn sefaz-btn--primary" disabled={disabled}>Pesquisar</button>
                </div>
              </div>
              <div className="sefaz-form-row">
                <div className="sefaz-td-rotulo-entrada">Nome:<span className="sefaz-required">*</span></div>
                <div className="sefaz-td-campo">
                  <input type="text" className="sefaz-input-text"
                    value={(donatario.dados as PessoaFisica).nome}
                    onChange={(e) => {
                      const newDons = [...donatarios];
                      newDons[index] = { ...donatario, dados: { ...donatario.dados, nome: e.target.value } as any };
                      onChangeDonatarios(newDons);
                    }}
                    disabled={disabled} style={{ width: '400px' }} />
                </div>
              </div>
              <div className="sefaz-form-row">
                <div className="sefaz-td-rotulo-entrada">Data de Nascimento:</div>
                <div className="sefaz-td-campo">
                  <input type="date" className="sefaz-input-text"
                    value={(donatario.dados as PessoaFisica).dataNascimento}
                    onChange={(e) => {
                      const newDons = [...donatarios];
                      const nasc = e.target.value;
                      const age = nasc ? Math.floor((Date.now() - new Date(nasc).getTime()) / (365.25 * 24 * 60 * 60 * 1000)) : 99;
                      newDons[index] = {
                        ...donatario,
                        dados: { ...donatario.dados, dataNascimento: nasc } as any,
                        menorDeIdade: age < 18,
                      };
                      onChangeDonatarios(newDons);
                    }}
                    disabled={disabled} />
                </div>
              </div>
              {donatario.menorDeIdade && (
                <div className="sefaz-msg sefaz-msg--warning" style={{ margin: '8px 0' }}>
                  Sujeito passivo e menor de idade, favor informar o nome do responsavel legal.
                  <div className="sefaz-form-row" style={{ marginTop: '8px' }}>
                    <div className="sefaz-td-rotulo-entrada">CPF Responsavel Solidario:</div>
                    <div className="sefaz-td-campo">
                      <input type="text" className="sefaz-input-text"
                        value={donatario.responsavelSolidario?.cpf || ''}
                        onChange={(e) => {
                          const newDons = [...donatarios];
                          newDons[index] = {
                            ...donatario,
                            responsavelSolidario: { ...donatario.responsavelSolidario, cpf: e.target.value, nome: donatario.responsavelSolidario?.nome || '' },
                          };
                          onChangeDonatarios(newDons);
                        }}
                        disabled={disabled} style={{ width: '180px' }} />
                    </div>
                  </div>
                  <div className="sefaz-form-row">
                    <div className="sefaz-td-rotulo-entrada">Nome Responsavel Solidario:</div>
                    <div className="sefaz-td-campo">
                      <input type="text" className="sefaz-input-text"
                        value={donatario.responsavelSolidario?.nome || ''}
                        onChange={(e) => {
                          const newDons = [...donatarios];
                          newDons[index] = {
                            ...donatario,
                            responsavelSolidario: { ...donatario.responsavelSolidario, nome: e.target.value, cpf: donatario.responsavelSolidario?.cpf || '' },
                          };
                          onChangeDonatarios(newDons);
                        }}
                        disabled={disabled} style={{ width: '350px' }} />
                    </div>
                  </div>
                </div>
              )}
            </FormSection>
          ))}
          {donatarios.length > 0 && (
            <div style={{ padding: '8px', textAlign: 'center' }}>
              <button type="button" className="sefaz-btn sefaz-btn--primary" onClick={addDonatario} disabled={disabled}>
                + Adicionar outro Donatario
              </button>
            </div>
          )}
        </div>
      ),
    },
  ];

  return <Tabs tabs={partesTab} />;
};

export default PartesInteressadasSection;
