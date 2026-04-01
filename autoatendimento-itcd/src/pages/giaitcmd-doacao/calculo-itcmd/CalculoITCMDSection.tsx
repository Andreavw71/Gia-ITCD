import React from 'react';
import FormSection from '../../../components/common/FormSection';
import { GIAITCMD, FAIXAS_TRIBUTACAO_ITCMD, PessoaFisica } from '../../../types/giaitcmd';
import { formatCurrency } from '../../../utils/formatters';

interface Props {
  gia: GIAITCMD;
}

const CalculoITCMDSection: React.FC<Props> = ({ gia }) => {
  const valorUPF = 256.04; // valor vigente - seria buscado da API

  if (gia.bensDireitos.length === 0 || gia.donatarios.length === 0 || gia.pendencias.filter(p => p.tipo === 'ERRO').length > 0) {
    return (
      <FormSection title="Calculo do ITCMD">
        <div className="sefaz-msg sefaz-msg--warning">
          O calculo do ITCMD somente sera realizado apos verificar e sanar todas as pendencias.
          Va para a secao "Enviar Declaracao" e clique em "Verificar Pendencias".
        </div>
      </FormSection>
    );
  }

  // Calcular base por donatário
  const calculosPorDonatario = gia.donatarios.map((donatario) => {
    const dados = donatario.dados as PessoaFisica;

    // Bens atribuídos a este donatário
    const bensDonatario = gia.bensDireitos.map((bem) => {
      const divisoes = gia.divisaoBens.filter((d) => d.bemId === bem.id && d.donatarioId === donatario.id);
      const percentualTotal = divisoes.reduce((s, d) => s + d.percentual, 0);
      const baseCalculo = bem.valorBemTransmitido * (percentualTotal / 100);
      return {
        bemId: bem.id,
        especie: bem.especie,
        valorBemTransmitido: bem.valorBemTransmitido,
        percentualAtribuido: percentualTotal,
        baseCalculoBem: baseCalculo,
      };
    }).filter((b) => b.percentualAtribuido > 0);

    const baseCalculoTotal = bensDonatario.reduce((s, b) => s + b.baseCalculoBem, 0);
    const baseEmUPF = baseCalculoTotal / valorUPF;

    // Aplicar faixas progressivas
    let restanteUPF = baseEmUPF;
    const faixasAplicadas = FAIXAS_TRIBUTACAO_ITCMD.map((faixa) => {
      const tamanhoFaixa = faixa.limiteSuperiorUPF != null
        ? faixa.limiteSuperiorUPF - faixa.limiteInferiorUPF
        : Infinity;
      const qtdNaFaixa = Math.min(restanteUPF, tamanhoFaixa);
      restanteUPF = Math.max(0, restanteUPF - tamanhoFaixa);
      const valorRS = qtdNaFaixa * valorUPF;
      const imposto = valorRS * (faixa.aliquota / 100);
      return {
        faixa: faixa.descricao,
        valorUPF: qtdNaFaixa,
        valorRS,
        aliquota: faixa.aliquota,
        imposto,
      };
    });

    const valorNominalITCMD = faixasAplicadas.reduce((s, f) => s + f.imposto, 0);

    // Juros e multa (se denúncia espontânea)
    const denEsp = gia.fatoGerador.denunciaEspontanea;
    const percentualJuros = denEsp ? 12.5 : 0;
    const jurosMora = denEsp ? valorNominalITCMD * (percentualJuros / 100) : 0;
    const percentualMulta = denEsp ? 10 : 0;
    const multaMora = denEsp ? valorNominalITCMD * (percentualMulta / 100) : 0;
    const valorAtualizado = valorNominalITCMD + jurosMora + multaMora;

    return {
      donatarioId: donatario.id,
      nomeDonatario: dados.nome || `Donatario ${donatario.id}`,
      bensRecebidos: bensDonatario,
      baseCalculoITCMD: baseCalculoTotal,
      faixasAplicadas,
      valorNominalITCMD,
      percentualJurosMora: percentualJuros,
      jurosMora,
      percentualMultaMora: percentualMulta,
      multaMora,
      valorITCMDAtualizado: valorAtualizado,
    };
  });

  const totalNominal = calculosPorDonatario.reduce((s, c) => s + c.valorNominalITCMD, 0);
  const totalAtualizado = calculosPorDonatario.reduce((s, c) => s + c.valorITCMDAtualizado, 0);

  return (
    <div>
      <FormSection title="Calculo do ITCMD">
        <div className="demo-calc-info-grid">
          <div className="demo-calc-info-grid__row">
            <span className="demo-calc-info-grid__label">Motivo do Fato Gerador:</span>
            <span className="demo-calc-info-grid__value">Doacao</span>
            <span className="demo-calc-info-grid__label">Data da GIA:</span>
            <span className="demo-calc-info-grid__value">{new Date().toLocaleDateString('pt-BR')}</span>
          </div>
          <div className="demo-calc-info-grid__row">
            <span className="demo-calc-info-grid__label">Lei vigente:</span>
            <span className="demo-calc-info-grid__value">Lei 7.850/2002</span>
            <span className="demo-calc-info-grid__label">Valor UPF-MT:</span>
            <span className="demo-calc-info-grid__value">{formatCurrency(valorUPF)}</span>
          </div>
          <div className="demo-calc-info-grid__row">
            <span className="demo-calc-info-grid__label">Situacao da Doacao:</span>
            <span className="demo-calc-info-grid__value">
              {gia.fatoGerador.situacaoDoacao === 'PREVISTA' ? 'Doacao Prevista' : 'Doacao Ja Realizada'}
            </span>
            <span className="demo-calc-info-grid__label">Denuncia Espontanea:</span>
            <span className="demo-calc-info-grid__value">{gia.fatoGerador.denunciaEspontanea ? 'SIM' : 'NAO'}</span>
          </div>
        </div>
      </FormSection>

      {/* Cálculo por donatário */}
      {calculosPorDonatario.map((calc) => (
        <FormSection key={calc.donatarioId} title={`Donatario: ${calc.nomeDonatario}`}>
          {/* Bens recebidos */}
          <table className="sefaz-table" style={{ marginBottom: '12px' }}>
            <thead>
              <tr>
                <th>Especie</th>
                <th style={{ textAlign: 'right' }}>Valor Transmitido</th>
                <th style={{ textAlign: 'right' }}>% Atribuido</th>
                <th style={{ textAlign: 'right' }}>Base de Calculo</th>
              </tr>
            </thead>
            <tbody>
              {calc.bensRecebidos.map((bem, i) => (
                <tr key={i}>
                  <td>{bem.especie}</td>
                  <td style={{ textAlign: 'right' }}>{formatCurrency(bem.valorBemTransmitido)}</td>
                  <td style={{ textAlign: 'right' }}>{bem.percentualAtribuido.toFixed(2)}%</td>
                  <td style={{ textAlign: 'right' }}>{formatCurrency(bem.baseCalculoBem)}</td>
                </tr>
              ))}
            </tbody>
            <tfoot>
              <tr>
                <td colSpan={3} style={{ fontWeight: 'bold', textAlign: 'right' }}>Base de Calculo do ITCMD:</td>
                <td style={{ fontWeight: 'bold', textAlign: 'right' }}>{formatCurrency(calc.baseCalculoITCMD)}</td>
              </tr>
            </tfoot>
          </table>

          {/* Faixas de tributação */}
          <div style={{ fontSize: '11px', fontWeight: 'bold', marginBottom: '4px' }}>Faixa de Tributacao (progressiva em cascata)</div>
          <table className="sefaz-table">
            <thead>
              <tr>
                <th>Faixa</th>
                <th style={{ textAlign: 'right' }}>Qtd UPF</th>
                <th style={{ textAlign: 'right' }}>Valor (R$)</th>
                <th style={{ textAlign: 'right' }}>Aliquota</th>
                <th style={{ textAlign: 'right' }}>Imposto</th>
              </tr>
            </thead>
            <tbody>
              {calc.faixasAplicadas.map((f, i) => (
                <tr key={i}>
                  <td>{f.faixa}</td>
                  <td style={{ textAlign: 'right' }}>{f.valorUPF.toFixed(2)}</td>
                  <td style={{ textAlign: 'right' }}>{formatCurrency(f.valorRS)}</td>
                  <td style={{ textAlign: 'right' }}>{f.aliquota}%</td>
                  <td style={{ textAlign: 'right', fontWeight: f.imposto > 0 ? 'bold' : 'normal' }}>
                    {formatCurrency(f.imposto)}
                  </td>
                </tr>
              ))}
            </tbody>
            <tfoot>
              <tr>
                <td colSpan={4} style={{ fontWeight: 'bold', textAlign: 'right' }}>Valor Nominal do ITCMD:</td>
                <td style={{ fontWeight: 'bold', textAlign: 'right' }}>{formatCurrency(calc.valorNominalITCMD)}</td>
              </tr>
              {gia.fatoGerador.denunciaEspontanea && (
                <>
                  <tr>
                    <td colSpan={4} style={{ textAlign: 'right' }}>Juros de Mora ({calc.percentualJurosMora.toFixed(2)}%):</td>
                    <td style={{ textAlign: 'right' }}>{formatCurrency(calc.jurosMora)}</td>
                  </tr>
                  <tr>
                    <td colSpan={4} style={{ textAlign: 'right' }}>Multa de Mora ({calc.percentualMultaMora.toFixed(2)}%):</td>
                    <td style={{ textAlign: 'right' }}>{formatCurrency(calc.multaMora)}</td>
                  </tr>
                  <tr>
                    <td colSpan={4} style={{ fontWeight: 'bold', textAlign: 'right', color: '#CC0000' }}>
                      Valor do ITCMD atualizado:
                    </td>
                    <td style={{ fontWeight: 'bold', textAlign: 'right', color: '#CC0000' }}>
                      {formatCurrency(calc.valorITCMDAtualizado)}
                    </td>
                  </tr>
                </>
              )}
            </tfoot>
          </table>
        </FormSection>
      ))}

      {/* Totais */}
      <FormSection title="Resumo Geral">
        <div className="sefaz-data-grid" style={{ gridTemplateColumns: '1fr 1fr' }}>
          <div className="sefaz-td-rotulo" style={{ fontWeight: 'bold' }}>Valor Total Nominal do ITCMD:</div>
          <div className="sefaz-td-campo-saida" style={{ fontWeight: 'bold' }}>{formatCurrency(totalNominal)}</div>
          {gia.fatoGerador.denunciaEspontanea && (
            <>
              <div className="sefaz-td-rotulo" style={{ fontWeight: 'bold', color: '#CC0000' }}>Valor Total do ITCMD atualizado:</div>
              <div className="sefaz-td-campo-saida" style={{ fontWeight: 'bold', color: '#CC0000' }}>{formatCurrency(totalAtualizado)}</div>
            </>
          )}
        </div>
        {gia.fatoGerador.denunciaEspontanea && (
          <div className="sefaz-msg sefaz-msg--warning" style={{ marginTop: '12px', fontSize: '10px' }}>
            *Em razao do nao recolhimento do ITCMD no prazo legal, sobre o valor principal do imposto
            passaram a incidir multa e juros moratorios, nos termos do art. 21, inciso II, c/c Art. 22
            ao 24, todos da Lei Estadual n. 7.850/2002.
          </div>
        )}
      </FormSection>
    </div>
  );
};

export default CalculoITCMDSection;
