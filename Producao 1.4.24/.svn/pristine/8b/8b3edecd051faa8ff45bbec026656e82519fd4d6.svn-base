package br.gov.mt.sefaz.itc.model.tabelabasica.distancia;

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
import br.gov.mt.sefaz.itc.model.tabelabasica.distancia.util.DistanciaComparator;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.MensagemSucesso;
import br.gov.mt.sefaz.itc.util.validador.ValidadorDistancia;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class DistanciaBe extends AbstractBe
{
   public DistanciaBe() throws SQLException
   {
      super();
   }

   public DistanciaBe(Connection conexao) throws SQLException
   {
      super(conexao);
   }

   /* ---------------------------------------------------------------------------------------------
    *                                Métodos Negocio DistanciaBe
    * --------------------------------------------------------------------------------------------- */

   public void inativarDistancia(final DistanciaVo distanciaVo) throws RegistroExistenteException, ObjetoObrigatorioException, 
         ParametroObrigatorioException, ConsultaException, ConexaoException, LogException, AnotacaoException, 
         PersistenciaException
   {
      validaParametrosInativarDistancia(distanciaVo);
      distanciaVo.setParametroConsulta(distanciaVo);
      consultarDistancia(distanciaVo);
      distanciaVo.setStatusResgistroDistancia(new DomnStatusRegistro(DomnStatusRegistro.INATIVO));
      alterarDistancia(distanciaVo);
   }

   /* ---------------------------------------------------------------------------------------------
   *                                Métodos Validacao DistanciaBe
   * --------------------------------------------------------------------------------------------- */

   /**
    * 
    * 
    * @param distanciaVo
    * @throws ParametroObrigatorioException
    */
   private void validarDadosParaInclusao(final DistanciaVo distanciaVo) throws ParametroObrigatorioException, 
         ConsultaException, ObjetoObrigatorioException
   {
      ValidadorDistancia.isCampoPreenchido(distanciaVo.getDistanciaInicialPerimetroUrbano(), MensagemErro.VALIDAR_DISTANCIA_INICIAL_PERIMETRO_URBANO);
      ValidadorDistancia.distanciaInicialPerimetroUrbanoMaiorQueZero(distanciaVo.getDistanciaInicialPerimetroUrbano(), MensagemErro.VALIDAR_DISTANCIA_INICIAL_PERIMETRO_URBANO_NAO_PODE_SER_MENOR_QUE_ZERO);
      //ValidadorDistancia.isCampoPreenchido(distanciaVo.getDistanciaFinalPerimetroUrbano(), MensagemErro.VALIDAR_DISTANCIA_FINAL_PERIMETRO_URBANO);
      if( Validador.isNumericoValido(distanciaVo.getDistanciaFinalPerimetroUrbano()))
      {
         ValidadorDistancia.isdistanciaInicialMenorQueFinal(distanciaVo.getDistanciaInicialPerimetroUrbano(), distanciaVo.getDistanciaFinalPerimetroUrbano(), MensagemErro.VALIDAR_DISTANCIA_FINAL_MENOR_QUE_INICIAL_PERIMETRO_URBANO);
      }
      if( Validador.isNumericoValido(distanciaVo.getDistanciaFinalRodoviaPavimentada()))
      {
         ValidadorDistancia.isdistanciaInicialMenorQueFinal(distanciaVo.getDistanciaInicialRodoviaPavimentada(), distanciaVo.getDistanciaFinalRodoviaPavimentada(), MensagemErro.VALIDAR_DISTANCIA_FINAL_MENOR_QUE_INICIAL_RODOVIA_PAVIMENTADA);
      }
      ValidadorDistancia.isCampoPreenchido(distanciaVo.getDistanciaInicialRodoviaPavimentada(), MensagemErro.VALIDAR_DISTANCIA_INICIAL_RODOVIA_PAVIMENTADA_NAO_PODE_SER_MENOR_QUE_ZERO);
      ValidadorDistancia.isTipoDistanciaValida(distanciaVo.getTipoDistancia());
      
      //DistanciaVo distanciaConsulta = new DistanciaVo();
      //distanciaConsulta.setTipoDistancia( distanciaVo.getTipoDistancia() );
      //distanciaConsulta = new DistanciaVo(distanciaConsulta);
      validarDistanciaPerimetroUrbano(distanciaVo, listarDistanciaAtiva(distanciaVo));
      validarDistanciaPerimetroRodoviaPavimentada(distanciaVo);
   }

   private void validarDistanciaPerimetroUrbano(final DistanciaVo distanciaVo, final DistanciaVo distancias) throws ParametroObrigatorioException
   {
      List<DistanciaVo> faixas = listarMaiorFaixaDistanciaFinalPerimetroUrbano(distancias);
      Collections.sort(faixas);
      DistanciaVo ultimaFaixa = null;
      if (Validador.isCollectionValida(faixas))
      {
         ultimaFaixa = faixas.get(faixas.size() - 1);

         if (distanciaVo.getDistanciaInicialPerimetroUrbano() == 0)
         {
            if (distanciaVo.getDistanciaFinalPerimetroUrbano() != ultimaFaixa.getDistanciaFinalPerimetroUrbano())
            {
               throw new ParametroObrigatorioException(MensagemErro.VALIDAR_FAIXA_DE_DISTANCIA_JA_FOI_INCLUIDA);
            }
         }
         else if (distanciaVo.getDistanciaInicialPerimetroUrbano() == ultimaFaixa.getDistanciaInicialPerimetroUrbano())
         {
            if (distanciaVo.getDistanciaFinalPerimetroUrbano() != ultimaFaixa.getDistanciaFinalPerimetroUrbano())
            {
               throw new ParametroObrigatorioException(MensagemErro.VALIDAR_FAIXA_DE_DISTANCIA_JA_FOI_INCLUIDA);
            }
         }
         else if (distanciaVo.getDistanciaInicialPerimetroUrbano() != ultimaFaixa.getDistanciaFinalPerimetroUrbano() + 1)
         {
            throw new ParametroObrigatorioException(MensagemErro.VALIDAR_DISTTANCIA_INICIAL_PERIMETRO_URBANO_DEVE_SER_MAIOR_QUE_DISTANCIA_FINAL_ANTERIOR);
         }else if(!ValidadorDistancia.isDistanciaEmAberto(ultimaFaixa.getDistanciaFinalRodoviaPavimentada()))
         {
            throw new ParametroObrigatorioException(MensagemErro.VALIDAR_DISTANCIA_FINAL_RODOVIA_PAVIMENTADA_EM_ABERTO );
         }
      }
   }

   private void validarDistanciaPerimetroRodoviaPavimentada(final DistanciaVo distanciaVo) throws ConsultaException, 
         ObjetoObrigatorioException, ParametroObrigatorioException
   {
      DistanciaVo rodoviaPavimentada = new DistanciaVo();
      DistanciaVo param = new DistanciaVo();
      param.setDistanciaFinalPerimetroUrbano(distanciaVo.getDistanciaFinalPerimetroUrbano());
      //param.setTipoDistancia(distanciaVo.getTipoDistancia());
      rodoviaPavimentada.setParametroConsulta(param);
      List<DistanciaVo> faixasPerimetroUrbano = new ArrayList(listarDistanciaAtiva(rodoviaPavimentada).getCollVO());

      DistanciaVo ultimaFaixa = null;

      if (Validador.isCollectionValida(faixasPerimetroUrbano))
      {
         // Ordena a lista enviando VO com a maior DistanciaFinalRodoviaPavimentada para final da lista.
         Collections.sort(faixasPerimetroUrbano, new DistanciaComparator());
         // Retorna o último Vo da lista.
         ultimaFaixa = faixasPerimetroUrbano.get(faixasPerimetroUrbano.size() - 1);

         if (distanciaVo.getDistanciaInicialRodoviaPavimentada() != ultimaFaixa.getDistanciaFinalRodoviaPavimentada() + 1)
         {
            throw new ParametroObrigatorioException(MensagemErro.VALIDAR_DISTTANCIA_INICIAL_RODOVIA_PAVIMENTADA_DEVE_SER_MAIOR_QUE_DISTANCIA_FINAL_ANTERIOR);
         }
         else if(distanciaVo.getDistanciaInicialRodoviaPavimentada() == ultimaFaixa.getDistanciaInicialRodoviaPavimentada())
         {
            throw new ParametroObrigatorioException(MensagemErro.VALIDAR_FAIXA_DE_DISTANCIA_RODOVIA_PAVIMENTADA_JA_FOI_INCLUIDA);
         }
      }
   }

   private void removerPerimetroUrbano()
   {
      
   }
   
   private void validaParametrosInativarDistancia(DistanciaVo distanciaVo)
   {
   }

   private boolean permiteAlterar(DistanciaVo distanciaVo, DistanciaVo param)
   {
      return true;
   }

   /* ---------------------------------------------------------------------------------------------
    *                                Métodos Utilitarios DistanciaBe
    * --------------------------------------------------------------------------------------------- */

   /**
    * Este metodo tem por objetivo ordenar e separar
    * em blocos  as distancias para serem exibidas na view
    *
    *
    * @param distanciaVo
    */
   public void ordernarListaParaExibirNaView(final DistanciaVo distanciaVo)
   {
      List<DistanciaVo> distancias = new ArrayList(distanciaVo.getCollVO());
      Collections.sort(distancias, new DistanciaComparator());
      definirUltimoVoDaFaixa(distanciaVo);
      DistanciaVo listaDefaixas = new DistanciaVo();
      //List<Integer> faixasDeDistancia = listaDeFaixaDistanciaFinalPerimetroUrbano(distanciaVo);
      List<Integer> faixasDeDistancia = listaDeFaixaDistanciaInicialPerimetroUrbano(distanciaVo);
      for (int i: faixasDeDistancia)
      {
         DistanciaVo faixa = new DistanciaVo();
         for (DistanciaVo distanciaTemp: distancias)
         {
            if (distanciaTemp.getDistanciaInicialPerimetroUrbano() == i)
            {
               faixa.getCollVO().add(distanciaTemp);
            }
         }
         if(Validador.isCollectionValida(faixa.getCollVO()))
         {
            listaDefaixas.getCollVO().add(faixa);
         }     
      }
      distanciaVo.setCollVO(listaDefaixas.getCollVO());
      //definirUltimoVoDaFaixa(distanciaVo);
   }

   /**
    * Este método tem por objetivo definir se o VO é o último
    * de suas faixa de valor.
    * 
    * <ol>
    *    <li>Para representar que o VO é o último de sua faixa o atributo
    *     <b>isMaiorDistanciaFinalPerimetroUrbanoDaFaixa</b> é alterado para <b>true</b>.
    *    </li>
    * </ol>
    * 
    * <ol>
    *    <li></li>
    * </ol>
    * 
    * 
    * @param distanciaVo
    */
   private void definirUltimoVoDaFaixa(final DistanciaVo distanciaVo)
   {
      List<DistanciaVo> distancias = new ArrayList(distanciaVo.getCollVO());
      Collections.sort(distancias, new DistanciaComparator());
      for (DistanciaVo d: distancias)
      {
         d.setIsMaiorDistanciaFinalPerimetroUrbano(false);
      }
      if(Validador.isCollectionValida(distancias))
      {
          distancias.get(distancias.size() -1).setIsMaiorDistanciaFinalPerimetroUrbano(true);
      }
   }

   private void definirUltimoDistancia(final DistanciaVo distanciaVo)
   {
      List<DistanciaVo> distancias = new ArrayList(distanciaVo.getCollVO());
      DistanciaVo d = distancias.get(distancias.size() - 1);
      List<DistanciaVo> dx = new ArrayList(d.getCollVO());
      DistanciaVo c = dx.get(dx.size() - 1);
      if (Validador.isObjetoValido(c))
      {
         c.setIsMaiorDistanciaFinalPerimetroUrbano(true);
      }
   }

   /**
    * Este método tem por objetivo retornar o maior valor das faixas de distancia Final PerimetroUrbano.
    * 
    * 
    * @param distanciaVo
    * @return
    */
   private List<Integer> listaDeFaixaDistanciaFinalPerimetroUrbano(final DistanciaVo distanciaVo)
   {
      List<DistanciaVo> distancias = new ArrayList(distanciaVo.getCollVO());
      List<Integer> faixasDeDistancia = new ArrayList();
      for (int x = 0; x < distancias.size(); x++)
      {
         if (!faixasDeDistancia.contains(distancias.get(x).getDistanciaFinalPerimetroUrbano()))
         {
            faixasDeDistancia.add(distancias.get(x).getDistanciaFinalPerimetroUrbano());
         }
      }
      return faixasDeDistancia;
   }
   
   /**
    * Este método tem por objetivo retornar o maior valor das faixas de distancia Final PerimetroUrbano.
    * 
    * 
    * @param distanciaVo
    * @return
    */
   private List<Integer> listaDeFaixaDistanciaInicialPerimetroUrbano(final DistanciaVo distanciaVo)
   {
      List<DistanciaVo> distancias = new ArrayList(distanciaVo.getCollVO());
      List<Integer> faixasDeDistanciaInicial = new ArrayList();
      for (int x = 0; x < distancias.size(); x++)
      {
         if (!faixasDeDistanciaInicial.contains(distancias.get(x).getDistanciaInicialPerimetroUrbano()))
         {
            faixasDeDistanciaInicial.add(distancias.get(x).getDistanciaInicialPerimetroUrbano());
         }
      }
      Collections.sort(faixasDeDistanciaInicial);
      //Inverter ordem da lista
      //Collections.reverse(faixasDeDistanciaInicial);
      return faixasDeDistanciaInicial;
   }

   /**
    * Este método tem por objetivo retornar o último VO de cada das faixas de distancia Final PerimetroUrbano.
    * 
    * 
    * @param distanciaVo
    * @return Uma lista de DistanciaVo contendo.<br>
    * Cada Vo na lista representa o registro com a maior DistanciaFinalPerimetroUrbano de cada faixa.
    */
   private List<DistanciaVo> listarMaiorFaixaDistanciaFinalPerimetroUrbano(final DistanciaVo distanciaVo)
   {
      List<DistanciaVo> distancias = new ArrayList(distanciaVo.getCollVO());
      List<DistanciaVo> faixasDeDistancia = new ArrayList();
      for (int x = 0; x < distancias.size(); x++)
      {
         if (!faixasDeDistancia.contains(distancias.get(x).getDistanciaFinalPerimetroUrbano()))
         {
            faixasDeDistancia.add(distancias.get(x));
         }
      }
      return faixasDeDistancia;
   }

   /* ---------------------------------------------------------------------------------------------
    *                                Métodos DISTANCIABE DAO
    * --------------------------------------------------------------------------------------------- */

   public synchronized void incluirDistancia(final DistanciaVo distanciaVo) throws LogException, ObjetoObrigatorioException, 
         AnotacaoException, PersistenciaException, SQLException, ParametroObrigatorioException, RegistroExistenteException, 
         ConexaoException, ConsultaException
   {
      try
      {
         validarDadosParaInclusao(distanciaVo);
         distanciaVo.setDataAtualizacaoBD(new Date());
         incluir(distanciaVo);
         commit();
      }
      /*     catch (RegistroExistenteException e)
        {
           conn.rollback();
           throw e;
        }
     */
      catch (ParametroObrigatorioException e)
      {
         conn.rollback();
         throw e;
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

   public void alterarDistancia(final DistanciaVo distanciaVo) throws RegistroExistenteException, ObjetoObrigatorioException, 
         ParametroObrigatorioException, ConsultaException, ConexaoException, LogException, AnotacaoException, 
         PersistenciaException
   {
      Validador.validaObjeto(distanciaVo);
      try
      {
         try
         {
            synchronized (DistanciaVo.class)
            {
               alterar(distanciaVo);
               distanciaVo.setDataAtualizacaoBD(new Date());
               commit();
               distanciaVo.setMensagem(MensagemSucesso.ALTERAR);
            }
         }
         /*         catch (ParametroObrigatorioException e)
         {
            conn.rollback();
            throw e;
         }
         catch (ConsultaException e)
         {
            conn.rollback();
            throw e;
         }
*/
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

   /**
    * 
    * @param distanciaVo
    * @return
    * @throws ConsultaException
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    */
   public DistanciaVo listarDistanciaAtiva(final DistanciaVo distanciaVo) throws ConsultaException, 
         ObjetoObrigatorioException, ParametroObrigatorioException
   {
      (new DistanciaQDao(conn)).listarDistanciaAtiva(distanciaVo);
      Validador.validaObjeto(distanciaVo);
      Validador.isCollectionValida(distanciaVo.getCollVO());
      return distanciaVo;
   }
   
   public DistanciaVo selecionaDistanciaVoIntervaloAsfaltoPrmtUrbano(DistanciaVo distanciaVo)
      throws ConsultaException, ObjetoObrigatorioException
   {
      new DistanciaQDao(conn).findDistanciaIntervaloPrmtUrbanoRodovia(distanciaVo);
      Validador.validaObjeto(distanciaVo);
      return distanciaVo;
   }

   /* ---------------------------------------------------------------------------------------------
    *                                Métodos GENERIC LOG DAO
    * --------------------------------------------------------------------------------------------- */

   private synchronized void incluir(final DistanciaVo distanciaVo) throws LogException, ObjetoObrigatorioException, 
         AnotacaoException, PersistenciaException
   {
      Validador.isObjetoValido(distanciaVo);
      new GenericoLogAnotacaoDao(conn, false).insert(distanciaVo);
   }

   private synchronized void alterar(final DistanciaVo distanciaVo) throws ObjetoObrigatorioException, LogException, 
         AnotacaoException, PersistenciaException
   {
      Validador.validaObjeto(distanciaVo);
      //ConfiguracaoITCD.NAO_GERA_LOG;
      new GenericoLogAnotacaoDao(conn, true).update(distanciaVo);
   }

   public DistanciaVo consultarDistancia(final DistanciaVo distanciaVo) throws ConsultaException, ObjetoObrigatorioException, 
         ParametroObrigatorioException
   {
      Validador.validaObjeto(distanciaVo);
      (new DistanciaQDao(conn)).findDistancia(distanciaVo);
      return distanciaVo;
   }

}
