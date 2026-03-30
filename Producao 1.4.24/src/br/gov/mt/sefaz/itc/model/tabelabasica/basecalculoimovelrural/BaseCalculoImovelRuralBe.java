package br.gov.mt.sefaz.itc.model.tabelabasica.basecalculoimovelrural;

import br.com.abaco.log5.GenericoLogAnotacaoDao;
import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.RegistroExistenteException;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.basecalculoimovelrural.util.BaseCalculoImovelRuralComparator;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoAtividade;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoDistancia;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.MensagemSucesso;
import br.gov.mt.sefaz.itc.util.validador.ValidadorBaseCalculoImovelRural;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class BaseCalculoImovelRuralBe extends AbstractBe
{
   public BaseCalculoImovelRuralBe() throws SQLException
   {
      super();
   }

   public BaseCalculoImovelRuralBe(Connection conexao)
   {
      super(conexao);
   }

   /* ---------------------------------------------------------------------------------------------
    *                                        Métodos Negocio
    * --------------------------------------------------------------------------------------------- */

   /* ---------------------------------------------------------------------------------------------
    *                                        Métodos Validacao
    * --------------------------------------------------------------------------------------------- */

   /**
    * 
    * <b>Regras</b>
    * </br>
    * <ol>
    *    <li>PS 1 - "Distancia inicial" >= 0</li>
    *    <li>PS 1 - "Distancia Inicial" deve ser igual ao último registro "Distancia Final"</li>
    * </ol>
    * 
    * 
    * 
    * @param baseCalculoImovelRuralVo
    * @throws ConsultaException
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    */
   private void validarDadosParaInclusao(BaseCalculoImovelRuralVo baseCalculoImovelRuralVo) throws ConsultaException, 
         ObjetoObrigatorioException, ParametroObrigatorioException
   {
      /**
       * configuração para buscar no BD
       * somente os registros que devem ser utilizados na compração
       * de validade.
       *
       */

      listarBaseCalculoImovelRuralParametrizada(baseCalculoImovelRuralVo);

      if (Validador.isCollectionValida(baseCalculoImovelRuralVo.getCollVO()))
      {
         Collections.sort(baseCalculoImovelRuralVo.getListVo(), new BaseCalculoImovelRuralComparator());

         boolean distanciaAtualIgualAoRegistroAnterior = validarDadosInclusaoDistancia(baseCalculoImovelRuralVo);
         boolean atividadeAtualIgualAoAnterior = validarDadosInclusaoAtividade(baseCalculoImovelRuralVo);
         boolean areaExploradaAtualIgualAoAnterior = validarDadosInclusaoAreaExplora(baseCalculoImovelRuralVo);

         if (distanciaAtualIgualAoRegistroAnterior & atividadeAtualIgualAoAnterior & areaExploradaAtualIgualAoAnterior)
         {
            // Todos retornaram "true" quer dizer que este registro já foi inserido.
            throw new ParametroObrigatorioException("Registro já inserido");
         }
      }
      else
      {
         // Quando nao houver registro ativos na base.
      }
   }

   /**
    * Este método tem por objetivo validar os dados do atributo distancia.
    * <br><br>
    * <b>Validações</b>
    * <br>
    * <ol>
    * <li>A "<b>Distância Inicial</b>" é obrigatória. </li>
    * <li>A "<b>Distância Final</b>" NÃO é obrigatória. </li>
    * <li>A "<b>Distância Inicial</b>" deve ser igual a "<b>Distância Final</b>" do registro anterior, se houver registro anterior; ou </li>
    * <li>A "<b>Distância Inicial</b>" deve ser igual a "<b>Distância Inicial</b>" do registro anterior, neste caso
    *  a  "<b>Distância Final</b>" também dever igual a "<b>Distância Final</b>" do registro anterior</li>
    * </ol>
    * 
    * 
    * @param baseCalculoImovelRuralVo
    * @return true caso 
    * @throws ParametroObrigatorioException
    */
   private boolean validarDadosInclusaoDistancia(BaseCalculoImovelRuralVo baseCalculoImovelRuralVo) throws ParametroObrigatorioException
   {
      BaseCalculoImovelRuralVo ultimoRegistroBase = 
         baseCalculoImovelRuralVo.getListVo().get(baseCalculoImovelRuralVo.getListVo().size() - 1);
      if (baseCalculoImovelRuralVo.getQuantidadeDistanciaInicial() == ultimoRegistroBase.getQuantidadeDistanciaInicial() && 
         baseCalculoImovelRuralVo.getQuantidadeDistanciaFinal() == ultimoRegistroBase.getQuantidadeDistanciaFinal())
      {
         // Distancia Inicial e Distancia final são iguais a anterior.
         //validarDadosInclusaoAtividade(baseCalculoImovelRuralVo);
         return true;
      }
      else if (ValidadorBaseCalculoImovelRural.isDistanciaInicialIgualFinal(baseCalculoImovelRuralVo.getQuantidadeDistanciaInicial(), ultimoRegistroBase.getQuantidadeDistanciaFinal() + 1, MensagemErro.VALIDAR_DISTANCIA_INICIAL_DIFERENTE_DISTANCIA_FINAL))
      {
         /**
          * Distancia inicial com 1 (uma) unidade a mais que o
          * registro anterior. Registro válido.
          */
         //validarDadosInclusaoAtividade(baseCalculoImovelRuralVo);
         return false;
      }
      return false;
   }

   private boolean validarDadosInclusaoAtividade(BaseCalculoImovelRuralVo baseCalculoImovelRuralVo) throws ParametroObrigatorioException
   {
      BaseCalculoImovelRuralVo ultimoRegistroAtivoNaBase = 
         baseCalculoImovelRuralVo.getListVo().get(baseCalculoImovelRuralVo.getListVo().size() - 1);
      if (baseCalculoImovelRuralVo.getPercentualAtividadeInicial() == 
         ultimoRegistroAtivoNaBase.getPercentualAtividadeInicial() && 
         baseCalculoImovelRuralVo.getPercentualAtividadeFinal() == ultimoRegistroAtivoNaBase.getPercentualAtividadeFinal())
      {
         // Atividade Inicial e Atividade final são iguais a anterior.
         return true;
      }
      else if (ValidadorBaseCalculoImovelRural.isDistanciaInicialIgualFinal(baseCalculoImovelRuralVo.getPercentualAtividadeInicial(), ultimoRegistroAtivoNaBase.getPercentualAtividadeFinal() + 1, MensagemErro.VALIDAR_ATIVIDADE_INICIAL_DIFERENTE_DISTANCIA_FINAL))
      {
         // Atividade Inicial igual Atividade final anterior.
         return false;
      }
      return false;
   }

   /**
    * 
    * 
    * @param baseCalculoImovelRuralVo
    * @return true se e somente os valores do registro atual forem iguais ao do registro anterior
    * @throws ParametroObrigatorioException
    */
   private boolean validarDadosInclusaoAreaExplora(BaseCalculoImovelRuralVo baseCalculoImovelRuralVo) throws ParametroObrigatorioException
   {
      BaseCalculoImovelRuralVo ultimoRegistroBase = 
         baseCalculoImovelRuralVo.getListVo().get(baseCalculoImovelRuralVo.getListVo().size() - 1);
      if (baseCalculoImovelRuralVo.getPercentualAreaExploradaInical() == 
         ultimoRegistroBase.getPercentualAreaExploradaInical() && 
         baseCalculoImovelRuralVo.getPercentualAreaExploradaFinal() == ultimoRegistroBase.getPercentualAreaExploradaFinal())
      {
         // Area Explorada Inicial e Area Explorada final são iguais a anterior.
         return true;
      }
      else if (ValidadorBaseCalculoImovelRural.isDistanciaInicialIgualFinal(baseCalculoImovelRuralVo.getPercentualAreaExploradaInical(), ultimoRegistroBase.getPercentualAreaExploradaFinal() + 1, MensagemErro.VALIDAR_AREA_EXPLORADA_INICIAL_DIFERENTE_DISTANCIA_FINAL))
      {
         // Area Explorada Inicial igual Area Explorada final anterior.
         return false;
      }
      return false;
   }

   private void validarDadosParaInativar(BaseCalculoImovelRuralVo baseCalculoImovelRuralVo)
   {
   }

   /**
    * 
    * 
    * 
    * @param baseCalculoImovelRuralVo
    * @param bases
    */
   private void validarDistanciaInicialParaInclusao(BaseCalculoImovelRuralVo baseCalculoImovelRuralVo, final BaseCalculoImovelRuralVo bases)
   {

   }

   /* ---------------------------------------------------------------------------------------------
    *                                      Métodos Utilitarios
    * --------------------------------------------------------------------------------------------- */

   /**
    * Este metodo tem por objetivo remover todos os dados do VO
    * com exceção da collection.
    * 
    * 
    * @param baseCalculoImovelRuralVo
    */
    public static void removerDadosVo(BaseCalculoImovelRuralVo baseCalculoImovelRuralVo)
    {
      BaseCalculoImovelRuralVo base = new BaseCalculoImovelRuralVo();
      base.setCollVO(baseCalculoImovelRuralVo.getCollVO());
       baseCalculoImovelRuralVo = base;
    }
   /**
    * 
    * 
    * 
    * @param baseCalculoImovelRuralVo
    */
   public void ordernarListaParaExibirNaView(final BaseCalculoImovelRuralVo baseCalculoImovelRuralVo)
   {
      ordenacaoPorTipoDistanciaTipoAtividade(baseCalculoImovelRuralVo);

      ordenacaoPorValores(baseCalculoImovelRuralVo);
   }

   /**
    * Método auxiliar de ordenação
    * Este método tem por objetivo fazer a ordenação
    * considerando os valoes numericos.
    * 
    */
   private void ordenacaoPorValores(final BaseCalculoImovelRuralVo baseCalculoImovelRuralVo)
   {
      BaseCalculoImovelRuralVo subLista = new BaseCalculoImovelRuralVo();

      for (BaseCalculoImovelRuralVo base: baseCalculoImovelRuralVo.getCollVO())
      {
         BaseCalculoImovelRuralVo listaDefaixas = new BaseCalculoImovelRuralVo();
         List<Integer> faixasDeDistancia = listaDeFaixaDistanciaInicialPerimetroUrbano(base);
         for (int i: faixasDeDistancia)
         {
            BaseCalculoImovelRuralVo faixa = new BaseCalculoImovelRuralVo();
            for (BaseCalculoImovelRuralVo distanciaTemp: base.getCollVO())
            {
               if (distanciaTemp.getQuantidadeDistanciaInicial() == i)
               {
                  faixa.getCollVO().add(distanciaTemp);
               }
            }
            if (Validador.isCollectionValida(faixa.getCollVO()))
            {
               listaDefaixas.getCollVO().add(faixa);
            }
         }
         subLista.getCollVO().add(listaDefaixas);
      }
      baseCalculoImovelRuralVo.setCollVO(subLista.getCollVO());
   }

   /**
    * Este método tem por objetivo fazer uma ordenação
    * inicial considerando as combinações de DomnTipoDistancia x DomnTipoAtividade.
    * 
    * @param baseCalculoImovelRuralVo
    */
   private void ordenacaoPorTipoDistanciaTipoAtividade(final BaseCalculoImovelRuralVo baseCalculoImovelRuralVo)
   {
      List<BaseCalculoImovelRuralVo> basesDeCalculo = new ArrayList(baseCalculoImovelRuralVo.getCollVO());
      Collections.sort(basesDeCalculo, new BaseCalculoImovelRuralComparator());
      BaseCalculoImovelRuralVo listaPerimetroUrbanoAgriculta = new BaseCalculoImovelRuralVo();
      BaseCalculoImovelRuralVo listaPerimetroUrbanoPecuaria = new BaseCalculoImovelRuralVo();
      BaseCalculoImovelRuralVo listaRodoviaPavimentadaAgriculta = new BaseCalculoImovelRuralVo();
      BaseCalculoImovelRuralVo listaRodoviaPavimentadaPecuaria = new BaseCalculoImovelRuralVo();
      for (BaseCalculoImovelRuralVo base: basesDeCalculo)
      {
         if (base.getTipoDistancia().is(DomnTipoDistancia.PERIMETRO_URBANO))
         {
            if (base.getTipoAtividade().is(DomnTipoAtividade.AGRICULTURA))
            {
               listaPerimetroUrbanoAgriculta.getCollVO().add(base);
            }
            else if (base.getTipoAtividade().is(DomnTipoAtividade.PECUARIA))
            {
               listaPerimetroUrbanoPecuaria.getCollVO().add(base);
            }
         }
         else if (base.getTipoDistancia().is(DomnTipoDistancia.RODOVIA_PAVIMENTADA))
         {
            if (base.getTipoAtividade().is(DomnTipoAtividade.AGRICULTURA))
            {
               listaRodoviaPavimentadaAgriculta.getCollVO().add(base);
            }
            else if (base.getTipoAtividade().is(DomnTipoAtividade.PECUARIA))
            {
               listaRodoviaPavimentadaPecuaria.getCollVO().add(base);
            }
         }
      }
      baseCalculoImovelRuralVo.setCollVO(new ArrayList<BaseCalculoImovelRuralVo>());
      if (Validador.isCollectionValida(listaPerimetroUrbanoAgriculta.getCollVO()))
      {
         definirUltimoVoDaFaixa(listaPerimetroUrbanoAgriculta);
         baseCalculoImovelRuralVo.getCollVO().add(listaPerimetroUrbanoAgriculta);
      }
      if (Validador.isCollectionValida(listaPerimetroUrbanoPecuaria.getCollVO()))
      {
         definirUltimoVoDaFaixa(listaPerimetroUrbanoPecuaria);
         baseCalculoImovelRuralVo.getCollVO().add(listaPerimetroUrbanoPecuaria);
      }
      if (Validador.isCollectionValida(listaRodoviaPavimentadaAgriculta.getCollVO()))
      {
         definirUltimoVoDaFaixa(listaRodoviaPavimentadaAgriculta);
         baseCalculoImovelRuralVo.getCollVO().add(listaRodoviaPavimentadaAgriculta);
      }
      if (Validador.isCollectionValida(listaRodoviaPavimentadaPecuaria.getCollVO()))
      {
         definirUltimoVoDaFaixa(listaRodoviaPavimentadaPecuaria);
         baseCalculoImovelRuralVo.getCollVO().add(listaRodoviaPavimentadaPecuaria);
      }
   }

   /**
    * 
    * 
    * 
    * 
    * @param baseCalculoImovelRuralVo
    * @return Uma lista de inteiros com os valores iniciais da distância
    */
   private List<Integer> listaDeFaixaDistanciaInicialPerimetroUrbano(final BaseCalculoImovelRuralVo baseCalculoImovelRuralVo)
   {
      List<BaseCalculoImovelRuralVo> distancias = new ArrayList(baseCalculoImovelRuralVo.getCollVO());
      List<Integer> faixasDeDistanciaInicial = new ArrayList();
      for (int x = 0; x < distancias.size(); x++)
      {
         if (!faixasDeDistanciaInicial.contains(distancias.get(x).getQuantidadeDistanciaInicial()))
         {
            faixasDeDistanciaInicial.add(distancias.get(x).getQuantidadeDistanciaInicial());
         }
      }
      Collections.sort(faixasDeDistanciaInicial);
      //Inverter ordem da lista
      //Collections.reverse(faixasDeDistanciaInicial);
      return faixasDeDistanciaInicial;
   }

   private void definirUltimoVoDaFaixa(final BaseCalculoImovelRuralVo baseCalculoImovelRuralVo)
   {
      List<BaseCalculoImovelRuralVo> distancias = new ArrayList(baseCalculoImovelRuralVo.getCollVO());
      Collections.sort(distancias, new BaseCalculoImovelRuralComparator());
      for (BaseCalculoImovelRuralVo d: distancias)
      {
         d.setIsMaiorDistanciaFinalPerimetroUrbano(false);
      }
      if (Validador.isCollectionValida(distancias))
      {
         distancias.get(distancias.size() - 1).setIsMaiorDistanciaFinalPerimetroUrbano(true);
      }
   }

   public BaseCalculoImovelRuralVo listarBaseCalculoImovelRuralAtiva(final BaseCalculoImovelRuralVo baseCalculoImovelRuralVo) throws ConsultaException, 
         ObjetoObrigatorioException, ParametroObrigatorioException
   {

      BaseCalculoImovelRuralVo baseConsulta = new BaseCalculoImovelRuralVo();
      baseConsulta.setStatusResgistroBaseCalculoImovelRural(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
      baseConsulta = new BaseCalculoImovelRuralVo(baseConsulta);
      (new BaseCalculoImovelRuralQDao(conn)).listarBaseCalculoImovelRuralVo(baseConsulta);
      baseCalculoImovelRuralVo.setCollVO(baseConsulta.getCollVO());
      Validador.validaObjeto(baseCalculoImovelRuralVo);
      Validador.isCollectionValida(baseCalculoImovelRuralVo.getCollVO());

      return baseCalculoImovelRuralVo;
   }

   public BaseCalculoImovelRuralVo listarBaseCalculoImovelRuralParametrizada(final BaseCalculoImovelRuralVo baseCalculoImovelRuralVo) throws ConsultaException, 
         ObjetoObrigatorioException, ParametroObrigatorioException
   {

      // Configuração dos parametros que devem ser utilizados na busca
      // de registro para retornar somente os registros que devem ser comparados na validacao.
      BaseCalculoImovelRuralVo baseConsulta = new BaseCalculoImovelRuralVo();
      baseConsulta.setStatusResgistroBaseCalculoImovelRural(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
      baseConsulta.setTipoAtividade(baseCalculoImovelRuralVo.getTipoAtividade());
      baseConsulta.setTipoDistancia(baseCalculoImovelRuralVo.getTipoDistancia());

      baseConsulta = new BaseCalculoImovelRuralVo(baseConsulta);

      (new BaseCalculoImovelRuralQDao(conn)).listarBaseCalculoImovelRuralVo(baseConsulta);
      baseCalculoImovelRuralVo.setCollVO(baseConsulta.getCollVO());
      Validador.validaObjeto(baseCalculoImovelRuralVo);
      Validador.isCollectionValida(baseCalculoImovelRuralVo.getCollVO());

      return baseCalculoImovelRuralVo;
   }

   /* ---------------------------------------------------------------------------------------------
    *                                          Métodos DAO
    * --------------------------------------------------------------------------------------------- */

   public void inativarBaseCalculoImovelRural(final BaseCalculoImovelRuralVo baseCalculoImovelRuralVo) throws ConsultaException, 
         ObjetoObrigatorioException, ParametroObrigatorioException, RegistroExistenteException, ConexaoException, 
         LogException, AnotacaoException, PersistenciaException
   {
      validarDadosParaInativar(baseCalculoImovelRuralVo);
      baseCalculoImovelRuralVo.setParametroConsulta(baseCalculoImovelRuralVo);
      consultarBaseCalculoImovelRuralVo(baseCalculoImovelRuralVo);
      baseCalculoImovelRuralVo.setStatusResgistroBaseCalculoImovelRural(new DomnStatusRegistro(DomnStatusRegistro.INATIVO));
      alterarBaseCalculoImovelRuralVo(baseCalculoImovelRuralVo);
   }

   public synchronized void incluirBaseCalculoImovelRuralVo(final BaseCalculoImovelRuralVo baseCalculoImovelRuralVo) throws LogException, 
         ObjetoObrigatorioException, AnotacaoException, PersistenciaException, SQLException, ParametroObrigatorioException, 
         RegistroExistenteException, ConexaoException, ConsultaException
   {
      try
      {
         validarDadosParaInclusao(baseCalculoImovelRuralVo);
         incluir(baseCalculoImovelRuralVo);
         commit();
      }
      catch (ObjetoObrigatorioException e)
      {
         conn.rollback();
         throw e;
      }
      catch (LogException e)
      {
         conn.rollback();
         throw e;
      }
      catch (AnotacaoException e)
      {
         conn.rollback();
         throw e;
      }
      catch (PersistenciaException e)
      {
         conn.rollback();
         throw e;
      }
   }

   public void alterarBaseCalculoImovelRuralVo(final BaseCalculoImovelRuralVo baseCalculoImovelRuralVo) throws RegistroExistenteException, 
         ObjetoObrigatorioException, ParametroObrigatorioException, ConsultaException, ConexaoException, LogException, 
         AnotacaoException, PersistenciaException
   {
      Validador.validaObjeto(baseCalculoImovelRuralVo);
      try
      {
         try
         {
            synchronized (BaseCalculoImovelRuralVo.class)
            {
               alterar(baseCalculoImovelRuralVo);
               commit();
               baseCalculoImovelRuralVo.setMensagem(MensagemSucesso.ALTERAR);
            }
         }
         catch (ObjetoObrigatorioException e)
         {
            conn.rollback();
            throw e;
         }
         catch (LogException e)
         {
            conn.rollback();
            throw e;
         }
         catch (AnotacaoException e)
         {
            conn.rollback();
            throw e;
         }
         catch (PersistenciaException e)
         {
            conn.rollback();
            throw e;
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
         throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
      }
   }

   /* ---------------------------------------------------------------------------------------------
     *                                Métodos GENERIC LOG DAO
     * --------------------------------------------------------------------------------------------- */

   private synchronized void incluir(final BaseCalculoImovelRuralVo baseCalculoImovelRuralVo) throws LogException, 
         ObjetoObrigatorioException, AnotacaoException, PersistenciaException
   {
      Validador.isObjetoValido(baseCalculoImovelRuralVo);
      baseCalculoImovelRuralVo.setStatusResgistroBaseCalculoImovelRural(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
      baseCalculoImovelRuralVo.setDataAtualizacaoBD(new Date());
      new GenericoLogAnotacaoDao(conn, false).insert(baseCalculoImovelRuralVo);
   }

   private synchronized void alterar(final BaseCalculoImovelRuralVo baseCalculoImovelRuralVo) throws ObjetoObrigatorioException, 
         LogException, AnotacaoException, PersistenciaException
   {
      Validador.validaObjeto(baseCalculoImovelRuralVo);
      baseCalculoImovelRuralVo.setDataAtualizacaoBD(new Date());
      //ConfiguracaoITCD.NAO_GERA_LOG;
      new GenericoLogAnotacaoDao(conn, true).update(baseCalculoImovelRuralVo);
   }

   public BaseCalculoImovelRuralVo consultarBaseCalculoImovelRuralVo(final BaseCalculoImovelRuralVo baseCalculoImovelRuralVo) throws ConsultaException, 
         ObjetoObrigatorioException, ParametroObrigatorioException
   {
      Validador.validaObjeto(baseCalculoImovelRuralVo);
      (new BaseCalculoImovelRuralQDao(conn)).findBaseCalculoImovelRuralVo(baseCalculoImovelRuralVo);
      return baseCalculoImovelRuralVo;
   }
   
   public BaseCalculoImovelRuralVo consultarCriterioCalculoImovelRuralITCD(final BaseCalculoImovelRuralVo baseCalculoImovelRuralVo)
      throws ObjetoObrigatorioException, ConsultaException
   {
      Validador.validaObjeto(baseCalculoImovelRuralVo);
      (new BaseCalculoImovelRuralQDao(conn)).findCriterioCalculoITCD(baseCalculoImovelRuralVo);
      return baseCalculoImovelRuralVo;
   }
   
}
