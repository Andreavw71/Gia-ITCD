/*
* Ábaco Tecnologia de Informação - LTDA
* Arquivo: ItemHistoricoVo.java
* Revisão: Dherkyan Ribeiro da Silva
* Data revisão: 08/08/2014
*/
package br.gov.mt.sefaz.itc.model.log.itemhistorico;

import br.com.abaco.log5.GenericoLogAnotacaoDao;
import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd.StatusGIAITCDVo;
import br.gov.mt.sefaz.itc.model.log.historicolog.HistoricoLogVo;

import java.sql.Connection;

import java.util.List;


/**
 * Esta classe faz parte do sistema de LOG do sistema ITCD.
 * </br>
 * O <code>ItemHistoricoVo</code> representa os dados
 * do LOG serão persistidos no BD quando ocorrer alguma
 * alteração como Incluir, Excluir ou Alterar alguma informação. 
 * 
 * @author Dherkya Ribeiro da Silva
 * @implemented by Dherkyan Ribeiro da Silva dherkyan@hotmail.com
 * @version 1.0, 08/08/2014
 * @see br.gov.mt.sefaz.itc.model.log.historicolog.HistoricoLogVo
 * @see br.gov.mt.sefaz.itc.model.log.log.LogITCDVo
 * @see br.gov.mt.sefaz.itc.util.facade.tabelas.NomeAmigavel
 * @since JDK1.0
 */
public class ItemHistoricoBe extends AbstractBe
{
   // ------------------------------ Construtores --------------------------------

   public ItemHistoricoBe(Connection connection)
   {
      super(connection);
   }


   //---------------------------- Metodos Service --------------------------------

   public ItemHistoricoVo obterItemHistoricoLogVoPorStatusITCD(StatusGIAITCDVo statusGIAITCDVo) throws ObjetoObrigatorioException, 
                                                                                                       ConsultaException
   {
      ItemHistoricoVo itemHistoricoVo = null;
      ItemHistoricoVo itemHistoricoVoParametrizado = null;
      
      List<StatusGIAITCDVo> statusCollection = (List<StatusGIAITCDVo>) statusGIAITCDVo.getCollVO();
      
         for (StatusGIAITCDVo  status : statusCollection)
         {
            for( HistoricoLogVo hist : status.getLogITCDVo().getHistoricoLogVo().getCollVO() )
            {
               itemHistoricoVoParametrizado = new ItemHistoricoVo();
               itemHistoricoVoParametrizado.setHistoricoLogVo(hist);
               itemHistoricoVo = new ItemHistoricoVo();
               itemHistoricoVo.setParametroConsulta(itemHistoricoVoParametrizado);
               
               new ItemHistoricoQDao(conn).findItemHistoricoVo(itemHistoricoVo);
               if(Validador.isCollectionValida(itemHistoricoVo.getCollVO()))
               {
                  hist.setItemLog(itemHistoricoVo);
               }
            }
         }
      
      return itemHistoricoVo;
   }

   //---------------------------- Metodos DAO Avançado ----------------------------

   public synchronized void persistirItemHistoricoVo(final ItemHistoricoVo ItemVo) throws ObjetoObrigatorioException, 
                                                                                          LogException, 
                                                                                          AnotacaoException, 
                                                                                          PersistenciaException
   {
      incluir(ItemVo);
   }

   /**
    * Metodo utilizado para atualizar  um objeto do tipo ItemHistoricoVo
    * no BD.
    * 
    * @param ItemVo
    * @throws ObjetoObrigatorioException caso o objeto tenha valor <b>null</b>.
    * @throws LogException
    * @throws AnotacaoException
    * @throws PersistenciaException
    */
   public synchronized void alterarItemHistoricoVo(final ItemHistoricoVo ItemVo) throws LogException, 
                                                                                        ObjetoObrigatorioException, 
                                                                                        AnotacaoException, 
                                                                                        PersistenciaException
   {
      alterar(ItemVo);
   }


   //----------------------------- Metodos DAO Basicos ---------------------------------

   /**
    * Metodo utilizado para salvar um objeto do tipo ItemHistoricoVo
    * no BD.
    * 
    * @param ItemVo
    * @throws ObjetoObrigatorioException caso o objeto tenha valor <b>null</b>.
    * @throws LogException
    * @throws AnotacaoException
    * @throws PersistenciaException
    */
   private synchronized void incluir(final ItemHistoricoVo ItemVo) throws ObjetoObrigatorioException, 
                                                                          LogException, 
                                                                          AnotacaoException, 
                                                                          PersistenciaException
   {
      new GenericoLogAnotacaoDao(conn, true).insert(ItemVo, ItemHistoricoVo.class);
   }


   /**
    * Metodo utilizado para atualizar  um objeto do tipo ItemHistoricoVo
    * no BD.
    * 
    * @param ItemVo
    * @throws ObjetoObrigatorioException caso o objeto tenha valor <b>null</b>.
    * @throws LogException
    * @throws AnotacaoException
    * @throws PersistenciaException
    */
   private synchronized void alterar(final ItemHistoricoVo ItemVo) throws LogException, 
                                                                          ObjetoObrigatorioException, 
                                                                          AnotacaoException, 
                                                                          PersistenciaException
   {
      new GenericoLogAnotacaoDao(conn, true).update(ItemVo, ItemHistoricoVo.class);
   }


}
