/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: CriterioMunicipioBe.java
 * Revisão: Dherkyan Ribeiro da Silva
 * Data criação : 21/09/2015
 * Data ultima revisão : 21/09/2015
 */
package br.gov.mt.sefaz.itc.model.tabelabasica.criteriomunicipio;

import br.com.abaco.log5.GenericoLogAnotacaoDao;
import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.RegistroExistenteException;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusRegistro;
import br.gov.mt.sefaz.itc.util.facade.MensagemSucesso;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.CadastroBe;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.MunicipioIntegracaoVo;
import br.gov.mt.sefaz.itc.util.log.ExibirLOG;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class CriterioMunicipioBe extends AbstractBe
{
   public CriterioMunicipioBe() throws SQLException
   {
      super();
   }

   public CriterioMunicipioBe(Connection conexao)
   {
      super(conexao);
   }

   /* ---------------------------------------------------------------------------------------------
    *                                Métodos Negocio
    * --------------------------------------------------------------------------------------------- */

   /* ---------------------------------------------------------------------------------------------
    *                                Métodos Validacao
    * --------------------------------------------------------------------------------------------- */

   private void validarDadosParaInclusao(CriterioMunicipioVo criterioMunicipioVo)
   {
   }
   /* ---------------------------------------------------------------------------------------------
    *                               Métodos Utilitarios
    * --------------------------------------------------------------------------------------------- */

   /**
    * Este método tem por objetivo preparar a lista de municipios para exibir na view
    * listando tanto os critpérios de municipios já inseridos no ITCD como os que ainda não foram inseridos
    * no ITCD.
    * 
    * @param criterioMunicipioVo
    */
   private void prepararListaDeMunicipiosParaExibirNaView(CriterioMunicipioVo criterioMunicipioVo) throws IntegracaoException, 
         SQLException
   {
      if(!Validador.isCollectionValida( criterioMunicipioVo.getMunicipio().getCollVO() ))
      {System.out.println("CONULSTA CADASTRO --------------------");
         criterioMunicipioVo.getMunicipio().setCollVO(new CadastroBe(conn).listarMunicipiosSede());
      }
      List<CriterioMunicipioVo> criterios = new ArrayList(criterioMunicipioVo.getCollVO());
      Map<Integer, CriterioMunicipioVo> codigosMunicipios = null;
      
      if (Validador.isCollectionValida(criterios))
      {
         codigosMunicipios = new HashMap<Integer, CriterioMunicipioVo>();
         for (CriterioMunicipioVo cm : criterios)
         {
            codigosMunicipios.put(cm.getMunicipio().getCodgMunicipio(), cm);
         }
      }
      
      mesclarCriterioMunicipioComMunicipio(criterioMunicipioVo , codigosMunicipios);
      
   }
   
   /**
    * <b>Objetivo:</b> Tem por objetivo  juntar a lista de CriterioMunicipioVo
    * com a lista MunicipioIntegracaoVo, resultando em uma nova lista que contem os dois
    * objetos.
    * 
    * @param criterioMunicipioVo
    * @param codigosMunicipios
    */
   private void mesclarCriterioMunicipioComMunicipio(CriterioMunicipioVo criterioMunicipioVo , Map<Integer, CriterioMunicipioVo> codigosMunicipios )
   {
      List<CriterioMunicipioVo> criterioComMunicipio = new ArrayList();
      List<MunicipioIntegracaoVo> municipios = new ArrayList(criterioMunicipioVo.getMunicipio().getCollVO());
      for (MunicipioIntegracaoVo municipio: municipios)
      {
         CriterioMunicipioVo cm = null;
         if (Validador.isObjetoValido(codigosMunicipios))
         {
            if (codigosMunicipios.containsKey(municipio.getCodgMunicipio()))
            {
               cm = codigosMunicipios.get(municipio.getCodgMunicipio());
               cm.setMunicipio(municipio);
               criterioComMunicipio.add(cm);
            }
            else
            {
               cm = new CriterioMunicipioVo();
               cm.setMunicipio(municipio);
               criterioComMunicipio.add(cm);
            }
         }
         else
         {
            // executado somente quando não houver nenhum CriterioMunicipioVo ativo no BD.
            cm = new CriterioMunicipioVo();
            cm.setMunicipio(municipio);
            criterioComMunicipio.add(cm);
         }
      }
      criterioMunicipioVo.setCollVO(criterioComMunicipio);
   }

   /* ---------------------------------------------------------------------------------------------
     *                                Métodos  DAO
     * --------------------------------------------------------------------------------------------- */

   public void inativarCriterioMunicipio(CriterioMunicipioVo criterioMunicipioVo) throws ObjetoObrigatorioException, 
         LogException, AnotacaoException, PersistenciaException, ConexaoException, ConsultaException, 
         ParametroObrigatorioException
   {
      consultarCriterioMunicipio(criterioMunicipioVo);
      criterioMunicipioVo.setStatusResgistroCriterioMunicipioVo(new DomnStatusRegistro(DomnStatusRegistro.INATIVO));
      alterarCriterioMunicipio(criterioMunicipioVo);
   }

   public void prepararParaAlterarCriterioMunicipio(CriterioMunicipioVo criterioMunicipioVo) throws ObjetoObrigatorioException, 
         LogException, AnotacaoException, PersistenciaException, ConexaoException, ConsultaException, 
         ParametroObrigatorioException
   {
      consultarCriterioMunicipio(criterioMunicipioVo);

      if (Validador.isNumericoValido(criterioMunicipioVo.getCodigo()))
      {

      }
      else
      {

      }
      try
      {
         criterioMunicipioVo.setMunicipio(new CadastroBe(conn).obterMunicipioPorCodigo(criterioMunicipioVo.getParametroConsulta().getMunicipio()));
      }
      catch (IntegracaoException e)
      {
         e.printStackTrace();
      }
      criterioMunicipioVo.setStatusResgistroCriterioMunicipioVo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
      // alterarCriterioMunicipio(criterioMunicipioVo);
   }

   public synchronized void incluirCriterioMunicipio(final CriterioMunicipioVo criterioMunicipioVo) throws LogException, 
         ObjetoObrigatorioException, AnotacaoException, PersistenciaException, SQLException, ParametroObrigatorioException, 
         RegistroExistenteException, ConexaoException, ConsultaException
   {
      try
      {
         synchronized (CriterioMunicipioVo.class)
         {
            CriterioMunicipioVo criterioMunicipioVoConsulta = new CriterioMunicipioVo();
            criterioMunicipioVoConsulta.setMunicipio(criterioMunicipioVo.getParametroConsulta().getMunicipio());
            criterioMunicipioVoConsulta.setStatusResgistroCriterioMunicipioVo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
            criterioMunicipioVoConsulta = new CriterioMunicipioVo(criterioMunicipioVoConsulta);
            consultarCriterioMunicipio(criterioMunicipioVoConsulta);
            // Caso já exista um registro entra neste boloco para inativar antes de inserir um novo.
            if (criterioMunicipioVoConsulta.isExiste())
            {
               criterioMunicipioVoConsulta.setStatusResgistroCriterioMunicipioVo(new DomnStatusRegistro(DomnStatusRegistro.INATIVO));
               criterioMunicipioVoConsulta.setDataAtualizacaoBD(new Date());
               criterioMunicipioVoConsulta.setLogSefazVo(criterioMunicipioVo.getLogSefazVo());
               alterar(criterioMunicipioVoConsulta);
               ExibirLOG.exibirLog("Critério Município Cod : " + criterioMunicipioVoConsulta.getCodigo() + " - INATIVADO");
            }
            criterioMunicipioVo.setStatusResgistroCriterioMunicipioVo(new DomnStatusRegistro(DomnStatusRegistro.ATIVO));
            criterioMunicipioVo.setDataAtualizacaoBD(new Date());
            criterioMunicipioVo.setMunicipio(criterioMunicipioVo.getParametroConsulta().getMunicipio());
            incluir(criterioMunicipioVo);
            commit();
            ExibirLOG.exibirLog("Critério Município Cod : " + criterioMunicipioVo.getCodigo() + " - INSERIDO");

            criterioMunicipioVo.setMensagem(MensagemSucesso.INCLUIR);
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

   public synchronized void alterarCriterioMunicipio(CriterioMunicipioVo criterioMunicipioVo) throws ObjetoObrigatorioException, 
         LogException, AnotacaoException, PersistenciaException, ConexaoException
   {
      Validador.validaObjeto(criterioMunicipioVo);
      try
      {
         try
         {
            synchronized (CriterioMunicipioVo.class)
            {
               alterar(criterioMunicipioVo);
               commit();
               criterioMunicipioVo.setMensagem(MensagemSucesso.ALTERAR);
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

   /**
    * 
    * @param criterioMunicipioVo
    * @return
    * @throws ConsultaException
    * @throws ObjetoObrigatorioException
    * @throws ParametroObrigatorioException
    */
   public CriterioMunicipioVo listarCriterioMunicipioAtiva(final CriterioMunicipioVo criterioMunicipioVo) throws ConsultaException, 
         ObjetoObrigatorioException, ParametroObrigatorioException, IntegracaoException, SQLException
   {
      (new CriterioMunicipioQDao(conn)).listarCriterioMunicipioAtivo(criterioMunicipioVo);
      Validador.validaObjeto(criterioMunicipioVo);
      Validador.isCollectionValida(criterioMunicipioVo.getCollVO());
      prepararListaDeMunicipiosParaExibirNaView(criterioMunicipioVo);
      // buscarNomeMunicipio(criterioMunicipioVo);
      return criterioMunicipioVo;
   }

   /**
    * Este método tem por objetivo buscar o nome dos municipios presentes na lista
    * de CriterioMunicipioVo.
    * 
    */
   private void buscarNomeMunicipio(final CriterioMunicipioVo criterioMunicipioVo) throws IntegracaoException, 
         ObjetoObrigatorioException
   {
      if (Validador.isCollectionValida(criterioMunicipioVo.getCollVO()))
      {
         Iterator it = criterioMunicipioVo.getCollVO().iterator();
         while (it.hasNext())
         {
            CriterioMunicipioVo cm = (CriterioMunicipioVo) it.next();
            MunicipioIntegracaoVo municipio = new CadastroBe(conn).obterMunicipioPorCodigo(cm.getMunicipio());
            cm.setMunicipio(municipio);
            System.out.println(municipio.getNomeMunicipio());
         }
      }

   }

   /* ---------------------------------------------------------------------------------------------
     *                                Métodos GENERIC LOG DAO
     * --------------------------------------------------------------------------------------------- */

   private synchronized void incluir(final CriterioMunicipioVo criterioMunicipioVo) throws LogException, 
         ObjetoObrigatorioException, AnotacaoException, PersistenciaException
   {
      Validador.isObjetoValido(criterioMunicipioVo);
      new GenericoLogAnotacaoDao(conn, false).insert(criterioMunicipioVo);
   }

   private synchronized void alterar(final CriterioMunicipioVo criterioMunicipioVo) throws ObjetoObrigatorioException, 
         LogException, AnotacaoException, PersistenciaException
   {
      Validador.validaObjeto(criterioMunicipioVo);
      new GenericoLogAnotacaoDao(conn, true).update(criterioMunicipioVo);
   }

   public CriterioMunicipioVo consultarCriterioMunicipio(final CriterioMunicipioVo criterioMunicipioVo) throws ConsultaException, 
         ObjetoObrigatorioException, ParametroObrigatorioException
   {
      Validador.validaObjeto(criterioMunicipioVo);
      (new CriterioMunicipioQDao(conn)).findCriterioMunicipio(criterioMunicipioVo);
      return criterioMunicipioVo;
   }

}
