import React from 'react';
import FormSection from '../../../components/common/FormSection';
import { GIAITCDInventarioArrolamento, TipoProtocolo, Beneficiario } from '../../../types';
import { formatCurrency, formatDate } from '../../../utils/formatters';

interface FaixaAliquota {
  descricao: string;
  aliquota: number;
  qtdUPF: number;
  baseRS: number;
  imposto: number;
}

interface EncargoAtraso {
  descricao: string;
  diasAtraso: number;
  percentualMulta: number;
  valor: number;
}

interface Props {
  gia: GIAITCDInventarioArrolamento;
  onPrevious: () => void;
}

const DemonstrativoCalculoInventario: React.FC<Props> = ({ gia, onPrevious }) => {
  // Tabela progressiva em cascata - baseada na Lei 10.488/2016
  const faixas: FaixaAliquota[] = [
    { descricao: 'Ate 1.500,00', aliquota: 0, qtdUPF: 1500, baseRS: 384060, imposto: 0 },
    { descricao: '1.500,00 a 4.000,00', aliquota: 2, qtdUPF: 29.71, baseRS: 7606.66, imposto: 152.13 },
    { descricao: '4.000,00 a 8.000,00', aliquota: 4, qtdUPF: 0, baseRS: 0, imposto: 0 },
    { descricao: '8.000,00 a 16.000,00', aliquota: 6, qtdUPF: 0, baseRS: 0, imposto: 0 },
    { descricao: 'Acima de 16.000,00', aliquota: 8, qtdUPF: 0, baseRS: 0, imposto: 0 },
  ];

  // Encargos por atraso
  const encargos: EncargoAtraso[] = [
    { descricao: 'Multa', diasAtraso: 3224, percentualMulta: 10, valor: 15.21 },
  ];

  const totalApurado = faixas.reduce((s, f) => s + f.imposto, 0) + encargos.reduce((s, e) => s + e.valor, 0);

  return (
    <div>
      {/* Cards de Resumo */}
      <div className="demo-calc-cards">
        <div className="demo-calc-card">
          <div className="demo-calc-card__label">Base de calculo total</div>
          <div className="demo-calc-card__value">{formatCurrency(gia.valorBaseCalculoTributavel || 391666.66)}</div>
        </div>
        <div className="demo-calc-card">
          <div className="demo-calc-card__label">Imposto Original</div>
          <div className="demo-calc-card__value">{formatCurrency(gia.valorITCD || 152.13)}</div>
        </div>
        <div className="demo-calc-card demo-calc-card--warning">
          <div className="demo-calc-card__label">Multa</div>
          <div className="demo-calc-card__value">{formatCurrency(gia.valorMulta || 0)}</div>
        </div>
        <div className="demo-calc-card demo-calc-card--highlight">
          <div className="demo-calc-card__label">Total a Recolher</div>
          <div className="demo-calc-card__value demo-calc-card__value--total">
            {formatCurrency(gia.valorRecolhimento || 0)}
          </div>
        </div>
      </div>

      {/* Cabeçalho Herança / Inventário */}
      <div className="demo-calc-header">
        <div className="demo-calc-header__title">
          <span>Heranca / Inventario</span>
          <span className="demo-calc-header__status demo-calc-header__status--dispensado">
            DISPENSADO
          </span>
        </div>
        <div className="demo-calc-header__info">
          <div className="demo-calc-header__row">
            <span className="demo-calc-header__label">Data do Obito</span>
            <span className="demo-calc-header__value">{formatDate(gia.dataObito) || '01/06/2017'}</span>
          </div>
          <div className="demo-calc-header__row">
            <span className="demo-calc-header__label">UPF Vigente</span>
            <span className="demo-calc-header__value">{formatCurrency(gia.valorUPF || 256.04)}</span>
          </div>
        </div>
      </div>

      {/* Legislação Aplicada */}
      <div className="demo-calc-legislacao">
        <div className="demo-calc-legislacao__title">LEGISLACAO APLICADA</div>
        <div className="demo-calc-legislacao__item">
          <span className="demo-calc-legislacao__label">Aliquota:</span>
          <span>Lei 10.488/2016</span>
        </div>
        <div className="demo-calc-legislacao__item">
          <span className="demo-calc-legislacao__label">Multa:</span>
          <span>Lei 10.488/2016 (Art. 40) - Retroativa (mais benefica)</span>
        </div>
      </div>

      {/* Demonstrativo de Calculo - Tabela Progressiva */}
      <FormSection title="Demonstrativo de Calculo">
        <div className="demo-calc-subtitle">Tabela Aplicada (progressiva em cascata)</div>
        <table className="sefaz-table demo-calc-table">
          <thead>
            <tr>
              <th>Faixa Aplicada</th>
              <th style={{ textAlign: 'right' }}>Aliq.</th>
              <th style={{ textAlign: 'right' }}>Qtd UPF (base)</th>
              <th style={{ textAlign: 'right' }}>Base (R$)</th>
              <th style={{ textAlign: 'right' }}>Imposto</th>
            </tr>
          </thead>
          <tbody>
            {faixas.map((faixa, i) => (
              <tr key={i} className={i % 2 === 0 ? 'demo-calc-table__row--even' : ''}>
                <td>{faixa.descricao}</td>
                <td style={{ textAlign: 'right' }}>{faixa.aliquota.toFixed(2)}%</td>
                <td style={{ textAlign: 'right' }}>
                  {faixa.qtdUPF > 0 ? faixa.qtdUPF.toLocaleString('pt-BR', { minimumFractionDigits: 2 }) : '0'}
                </td>
                <td style={{ textAlign: 'right' }}>{formatCurrency(faixa.baseRS)}</td>
                <td style={{ textAlign: 'right', fontWeight: faixa.imposto > 0 ? 'bold' : 'normal', color: faixa.imposto > 0 ? '#003366' : '#666' }}>
                  {formatCurrency(faixa.imposto)}
                </td>
              </tr>
            ))}
          </tbody>
        </table>

        {/* Encargos por atraso */}
        <div className="demo-calc-subtitle" style={{ marginTop: '16px' }}>
          Encargos por atraso na abertura do Inventario
        </div>
        <table className="sefaz-table demo-calc-table">
          <thead>
            <tr>
              <th>Descricao</th>
              <th style={{ textAlign: 'right' }}>Dias em Atraso</th>
              <th style={{ textAlign: 'right' }}>% Multa</th>
              <th style={{ textAlign: 'right' }}>Valor</th>
            </tr>
          </thead>
          <tbody>
            {encargos.map((enc, i) => (
              <tr key={i}>
                <td>{enc.descricao}</td>
                <td style={{ textAlign: 'right' }}>{enc.diasAtraso.toLocaleString('pt-BR')}</td>
                <td style={{ textAlign: 'right' }}>{enc.percentualMulta.toFixed(2)}%</td>
                <td style={{ textAlign: 'right' }}>
                  <span className="demo-calc-valor-destaque">{formatCurrency(enc.valor)}</span>
                </td>
              </tr>
            ))}
          </tbody>
          <tfoot>
            <tr className="demo-calc-table__total">
              <td colSpan={3} style={{ textAlign: 'right', fontWeight: 'bold' }}>Total Apurado</td>
              <td style={{ textAlign: 'right', fontWeight: 'bold' }}>{formatCurrency(totalApurado)}</td>
            </tr>
          </tfoot>
        </table>
      </FormSection>

      {/* Dados Completos do Demonstrativo (tela 2) */}
      <FormSection title="Demonstrativo de Calculo - Detalhamento">
        <div className="demo-calc-info-grid">
          <div className="demo-calc-info-grid__row">
            <span className="demo-calc-info-grid__label">Tipo da GIA:</span>
            <span className="demo-calc-info-grid__value">CAUSA MORTIS</span>
            <span className="demo-calc-info-grid__label">Data falecimento:</span>
            <span className="demo-calc-info-grid__value">{formatDate(gia.dataObito) || '01/03/2026'}</span>
          </div>
          <div className="demo-calc-info-grid__row">
            <span className="demo-calc-info-grid__label">Data do Inventario:</span>
            <span className="demo-calc-info-grid__value">{formatDate(gia.dataCriacao) || '26/03/2026'}</span>
            <span className="demo-calc-info-grid__label">Fracao Ideal:</span>
            <span className="demo-calc-info-grid__value">{gia.fracaoIdeal || 100}%</span>
          </div>
          <div className="demo-calc-info-grid__row">
            <span className="demo-calc-info-grid__label">Data da GIA:</span>
            <span className="demo-calc-info-grid__value">{formatDate(gia.dataCriacao) || '26/03/2026'}</span>
            <span className="demo-calc-info-grid__label">Lei:</span>
            <span className="demo-calc-info-grid__value">10488</span>
          </div>
          <div className="demo-calc-info-grid__row">
            <span className="demo-calc-info-grid__label">Percentual multa:</span>
            <span className="demo-calc-info-grid__value">0,00%</span>
            <span className="demo-calc-info-grid__label">Valor UPF:</span>
            <span className="demo-calc-info-grid__value">{formatCurrency(gia.valorUPF || 256.04)}</span>
          </div>
          <div className="demo-calc-info-grid__row">
            <span className="demo-calc-info-grid__label">Valor de Incidencia ITCD:</span>
            <span className="demo-calc-info-grid__value">{formatCurrency(gia.valorTotalBensDeclarados || 1538000)}</span>
            <span className="demo-calc-info-grid__label">Valor total bens arbitrados:</span>
            <span className="demo-calc-info-grid__value">{formatCurrency(gia.valorTotalArbitrado || 1538000)}</span>
          </div>
          <div className="demo-calc-info-grid__row">
            <span className="demo-calc-info-grid__label">Valor total bens informados contribuinte:</span>
            <span className="demo-calc-info-grid__value">{formatCurrency(gia.valorTotalInformadoBensDeclarados || 1538000)}</span>
            <span className="demo-calc-info-grid__label">Valor para calculo demonstrativo:</span>
            <span className="demo-calc-info-grid__value">{formatCurrency(gia.valorCalculoDemonstrativo || 1538000)}</span>
          </div>
          <div className="demo-calc-info-grid__row">
            <span className="demo-calc-info-grid__label">Valor total Bens Acordados:</span>
            <span className="demo-calc-info-grid__value">{formatCurrency(1538000)}</span>
            <span className="demo-calc-info-grid__label">&nbsp;</span>
            <span className="demo-calc-info-grid__value">&nbsp;</span>
          </div>
        </div>

        {/* Mensagem de aviso */}
        <div className="demo-calc-aviso">
          GIA-ITCD ainda nao foi avaliada.
          <br />
          A GIA-ITCD-e esta incompleta ou nao foi confirmada.
          <br />
          Favor concluir seu preenchimento e confirma-la para dar continuidade ao processo.
        </div>
      </FormSection>

      {/* Tabela de Beneficiários com Cálculo */}
      <FormSection title="Calculo por Beneficiario">
        <table className="sefaz-table demo-calc-table">
          <thead>
            <tr>
              <th>Nome</th>
              <th style={{ textAlign: 'right' }}>Valor do Quinhao / Recebido</th>
              <th style={{ textAlign: 'right' }}>Valor da Isencao</th>
              <th style={{ textAlign: 'right' }}>2,00 %</th>
              <th style={{ textAlign: 'right' }}>Valor do ITCD</th>
              <th style={{ textAlign: 'right' }}>Valor da Multa</th>
              <th style={{ textAlign: 'right' }}>Valor a Recolher Devido</th>
            </tr>
          </thead>
          <tbody>
            {(gia.beneficiarios.length > 0 ? gia.beneficiarios : [
              { pessoaBeneficiaria: { nomeContribuinte: 'AMORA HOMOLOGA' }, valorRecebido: 384500, valorRecebidoAvaliacao: 384060, valorItcdBeneficiario: 8.80 },
              { pessoaBeneficiaria: { nomeContribuinte: 'TOM HOMOLOGA' }, valorRecebido: 384500, valorRecebidoAvaliacao: 384060, valorItcdBeneficiario: 8.80 },
              { pessoaBeneficiaria: { nomeContribuinte: 'JANUARIO HOMOLOGA' }, valorRecebido: 384500, valorRecebidoAvaliacao: 384060, valorItcdBeneficiario: 8.80 },
            ] as any[]).map((benef: any, i: number) => (
              <tr key={i}>
                <td style={{ fontWeight: 'bold' }}>{benef.pessoaBeneficiaria?.nomeContribuinte}</td>
                <td style={{ textAlign: 'right' }}>{formatCurrency(benef.valorRecebido || 384500)}</td>
                <td style={{ textAlign: 'right' }}>{formatCurrency(benef.valorRecebidoAvaliacao || 384060)}</td>
                <td style={{ textAlign: 'right' }}>{formatCurrency(440)}</td>
                <td style={{ textAlign: 'right' }}>{formatCurrency(benef.valorItcdBeneficiario || 8.80)}</td>
                <td style={{ textAlign: 'right' }}>{formatCurrency(0)}</td>
                <td style={{ textAlign: 'right' }}>{formatCurrency(0)}</td>
              </tr>
            ))}
          </tbody>
        </table>

        {/* Cônjuge Sobrevivente (Meeiro) */}
        {gia.meeiroVo?.nomeContribuinte && (
          <>
            <div className="demo-calc-subtitle" style={{ marginTop: '12px' }}>
              Dados do Conjuge sobrevivente (Meeiro) beneficiario
            </div>
            <table className="sefaz-table demo-calc-table">
              <tbody>
                <tr>
                  <td style={{ fontWeight: 'bold' }}>{gia.meeiroVo.nomeContribuinte || 'AGOSTINHO HOMOLOGA'}</td>
                  <td style={{ textAlign: 'right' }}>{formatCurrency(384500)}</td>
                  <td style={{ textAlign: 'right' }}>{formatCurrency(384060)}</td>
                  <td style={{ textAlign: 'right' }}>{formatCurrency(440)}</td>
                  <td style={{ textAlign: 'right' }}>{formatCurrency(8.80)}</td>
                  <td style={{ textAlign: 'right' }}>{formatCurrency(0)}</td>
                  <td style={{ textAlign: 'right' }}>{formatCurrency(0)}</td>
                </tr>
              </tbody>
            </table>
          </>
        )}

        {/* Total Geral */}
        <div className="demo-calc-total-geral">
          <span>Total Geral: R$</span>
          <span style={{ fontWeight: 'bold', marginLeft: '8px' }}>
            {formatCurrency(gia.valorTotalBensDeclarados || 1538000)}
          </span>
        </div>
      </FormSection>

      {/* Resumo Explicativo */}
      <FormSection title="Resumo Explicativo">
        <div className="demo-calc-resumo-grid">
          <div className="demo-calc-resumo-grid__row">
            <span>Valor ITCD: R$</span>
            <span style={{ fontWeight: 'bold' }}>{formatCurrency(gia.valorITCD || 35.20)}</span>
          </div>
          <div className="demo-calc-resumo-grid__row">
            <span>Multa: R$</span>
            <span>{formatCurrency(gia.valorMulta || 0)}</span>
          </div>
          <div className="demo-calc-resumo-grid__row demo-calc-resumo-grid__row--total">
            <span>Valor total a recolher: R$</span>
            <span style={{ fontWeight: 'bold' }}>{formatCurrency(gia.valorRecolhimento || 8.80)}</span>
          </div>
        </div>
      </FormSection>

      {/* Resumo de Cálculo */}
      <FormSection title="Resumo de Calculo">
        <table className="sefaz-table demo-calc-table">
          <tbody>
            <tr>
              <td style={{ width: '70%' }}>
                Soma da base de calculo apurada pelo Fisco de valores arbitrados aceitos e da
                base de calculo declarada pelo interessado de valores arbitrados e refutados(A):
              </td>
              <td style={{ textAlign: 'right', fontWeight: 'bold' }}>
                {formatCurrency(gia.valorTotalBensDeclarados || 1538000)}
              </td>
            </tr>
            <tr>
              <td>Total da Base de Calculo apurada pelo Fisco(B):</td>
              <td style={{ textAlign: 'right', fontWeight: 'bold' }}>
                {formatCurrency(gia.valorTotalArbitrado || 1538000)}
              </td>
            </tr>
            <tr>
              <td>
                Indice Percentual da relacao entre os itens A e B, em conformidade com a
                Portaria-SEFAZ/MT em vigor:
              </td>
              <td style={{ textAlign: 'right', fontWeight: 'bold', fontSize: '13px' }}>
                100,00%
              </td>
            </tr>
          </tbody>
        </table>
      </FormSection>

      {/* Tipo de Protocolo */}
      <FormSection title="Tipo de Protocolo">
        <div className="sefaz-form-row">
          <div className="sefaz-td-rotulo-entrada">Tipo de Protocolo:</div>
          <div className="sefaz-td-campo">
            <label style={{ marginRight: '16px' }}>
              <input
                type="radio"
                name="tipoProtocolo"
                value={TipoProtocolo.AUTOMATICO}
                checked={gia.tipoProtocoloGIA === TipoProtocolo.AUTOMATICO}
                readOnly
              />{' '}
              Automatico
            </label>
            <label>
              <input
                type="radio"
                name="tipoProtocolo"
                value={TipoProtocolo.MANUAL}
                checked={gia.tipoProtocoloGIA === TipoProtocolo.MANUAL}
                readOnly
              />{' '}
              Manual
            </label>
          </div>
        </div>
      </FormSection>

      {/* Botões */}
      <div className="sefaz-btn-group">
        <button type="button" className="sefaz-btn" onClick={onPrevious}>
          &laquo; Anterior
        </button>
        <button type="button" className="sefaz-btn sefaz-btn--success">
          Confirmar GIA-ITCD
        </button>
      </div>
    </div>
  );
};

export default DemonstrativoCalculoInventario;
