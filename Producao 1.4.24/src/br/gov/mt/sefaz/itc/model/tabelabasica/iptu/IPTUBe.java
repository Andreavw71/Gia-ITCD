package br.gov.mt.sefaz.itc.model.tabelabasica.iptu;

import br.com.abaco.log5.GenericoLogAnotacaoDao;
import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.util.dominio.DomnAtivoInativo;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoIPTU;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.MensagemSucesso;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.CadastroBe;
import br.gov.mt.sefaz.itc.util.integracao.cadastro.MunicipioIntegracaoVo;
import br.gov.mt.sefaz.itc.util.log.ExibirLOG;
import br.gov.mt.sefaz.itc.util.validador.ValidadorIPTU;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class IPTUBe extends AbstractBe
{
   public IPTUBe() throws SQLException
   {
      super();
   }

   public IPTUBe(Connection conexao) throws SQLException
   {
      super(conexao);
   }

   public IPTUVo listarIPTU(IPTUVo iptuVo) throws ObjetoObrigatorioException, ConsultaException, IntegracaoException, 
         SQLException
   {
      (new IPTUQDao(conn)).listarIPTUVo(iptuVo);
      iptuVo.getMunicipio().setCollVO(new CadastroBe(conn).listarMunicipiosSede());
      prepararListarDeMunicipiosParaExibirNaView(iptuVo);
      return iptuVo;
   }
   public IPTUVo listarIPTUfind(IPTUVo iptuVo) throws ObjetoObrigatorioException, ConsultaException, IntegracaoException, 
         SQLException
   {
      (new IPTUQDao(conn)).listarIPTUVo(iptuVo);      
      return iptuVo;
   }

   /**
    * Este método tem por objetivo informar se determinado registro
    * na ITCTB54_IPTU esta com valor DOMN_TIPO_IPTU Integrado ou Estimado
    * 
    * 
    * 
    * 
    * 
    * @param iptuVo
    * @return true e somente true se o registro estiver com o valor DOMN_TIPO_IPTU.INTEGRADO
    * false significa que o registro esta com o valor DOMN_TIPO_IPTU.ESTIMATIVA
    * @throws ObjetoObrigatorioException
    * @throws ConsultaException caso não seja possível encontrar o registro
    * @throws ParametroObrigatorioException
    * @throws SQLException
    */
   public IPTUVo obterIptuPorCodigoOuMunicipio(IPTUVo iptuVo) throws ObjetoObrigatorioException, ConsultaException, 
         ParametroObrigatorioException, SQLException
   {
      IPTUVo iptuVoConsulta = new IPTUVo();
      iptuVoConsulta.setStatusResgistroIPTU(new DomnAtivoInativo(DomnAtivoInativo.ATIVO));
      if (Validador.isNumericoValido( iptuVo.getCodigo() ))
      {
         iptuVoConsulta.setCodigo(iptuVo.getCodigo());
         iptuVoConsulta = new IPTUVo(iptuVoConsulta);
         new IPTUBe(conn).consultarIPTU(iptuVoConsulta);
      }else if( Validador.isNumericoValido(iptuVo.getMunicipio().getCodgMunicipio()))
      {
         iptuVoConsulta.setMunicipio(iptuVo.getMunicipio()); 
         iptuVoConsulta = new IPTUVo(iptuVoConsulta);
         new IPTUBe(conn).consultarIPTU(iptuVoConsulta);
      }  
      
      if(!Validador.isDominioNumericoValido(iptuVoConsulta.getTipoITPU()))
      {
         throw new ConsultaException("Não foi possível obter informações sobre o tipo de IPTU.");
      }
      return iptuVoConsulta;  
   }

   /**
    * 
    * @param iptuVo
    * @throws IntegracaoException
    * @throws SQLException
    */
   private void prepararListarDeMunicipiosParaExibirNaView(IPTUVo iptuVo) throws IntegracaoException, SQLException
   {
      if(!Validador.isCollectionValida(iptuVo.getMunicipio().getCollVO()))
      {
          iptuVo.getMunicipio().setCollVO(new CadastroBe().listarMunicipiosSede());
      }  
      List<IPTUVo> criterios = new ArrayList(iptuVo.getCollVO());
      List<IPTUVo> listaIPTURetorno = new ArrayList();
      List<MunicipioIntegracaoVo> municipios = new ArrayList(iptuVo.getMunicipio().getCollVO());
      Map<Integer, IPTUVo> codigosMunicipios = new HashMap();

      listaIPTURetorno = new ArrayList();
      for (IPTUVo iptu: criterios)
      {
         codigosMunicipios.put(iptu.getMunicipio().getCodgMunicipio(), iptu);
      }

      for (MunicipioIntegracaoVo municipio: municipios)
      {
         IPTUVo iptu = null;
         if (codigosMunicipios.containsKey(municipio.getCodgMunicipio()))
         {
            iptu = codigosMunicipios.get(municipio.getCodgMunicipio());
            iptu.setMunicipio(municipio);
            listaIPTURetorno.add(iptu);
         }
         else
         {
            iptu = new IPTUVo();
            iptu.setMunicipio(municipio);
            listaIPTURetorno.add(iptu);
         }
      }
      iptuVo.setCollVO(listaIPTURetorno);
   }

   public void prepararParaAlterarIPTU(IPTUVo iptuVo) throws ConsultaException, ObjetoObrigatorioException, 
         ParametroObrigatorioException
   {
      consultarIPTU(iptuVo);

      try
      {
         iptuVo.setMunicipio(new CadastroBe(conn).obterMunicipioPorCodigo(iptuVo.getParametroConsulta().getMunicipio()));
      }
      catch (IntegracaoException e)
      {
         e.printStackTrace();
      }
      iptuVo.setStatusResgistroIPTU(new DomnAtivoInativo(DomnAtivoInativo.ATIVO));
   }

   public IPTUVo consultarIPTU(final IPTUVo iptuVo) throws ConsultaException, ObjetoObrigatorioException, 
         ParametroObrigatorioException
   {
      Validador.validaObjeto(iptuVo);
      (new IPTUQDao(conn)).finIPTUVo(iptuVo);
      return iptuVo;
   }

   public void incluirIPTU(IPTUVo iptuVo) throws PersistenciaException, AnotacaoException, LogException, 
         ObjetoObrigatorioException, SQLException, ConsultaException, ParametroObrigatorioException
   {
      try
      {
         validadorDadosParaInclusao(iptuVo);
         synchronized (IPTUVo.class)
         {
            IPTUVo iptuVoConsulta = new IPTUVo();
            iptuVoConsulta.setMunicipio(iptuVo.getParametroConsulta().getMunicipio());
            iptuVoConsulta.setStatusResgistroIPTU(new DomnAtivoInativo(DomnAtivoInativo.ATIVO));
            iptuVoConsulta = new IPTUVo(iptuVoConsulta);
            consultarIPTU(iptuVoConsulta);
            // Caso já exista um registro entra neste bloco para inativar antes de inserir um novo.
            if (iptuVoConsulta.isExiste())
            {
               iptuVoConsulta.setStatusResgistroIPTU(new DomnAtivoInativo(DomnAtivoInativo.INATIVO));
               iptuVoConsulta.setDataAtualizacaoBD(new Date());
               iptuVoConsulta.setLogSefazVo(iptuVo.getLogSefazVo());
               alterar(iptuVoConsulta);
               ExibirLOG.exibirLog("IPTU Cod : " + iptuVoConsulta.getCodigo() + " - INATIVADO");
            }
            iptuVo.setStatusResgistroIPTU(new DomnAtivoInativo(DomnAtivoInativo.ATIVO));
            iptuVo.setDataAtualizacaoBD(new Date());
            iptuVo.setMunicipio(iptuVo.getParametroConsulta().getMunicipio());
            incluir(iptuVo);
            commit();
            ExibirLOG.exibirLog("IPTU Cod : " + iptuVo.getCodigo() + " - INSERIDO");
            iptuVo.setMensagem(MensagemSucesso.INCLUIR);
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

   /**
    * 
    * @param iptuVo
    */
   private void validadorDadosParaInclusao(final IPTUVo iptuVo) throws ParametroObrigatorioException
   {
      // É obrigatório informar o tipo de IPTU
      if (Validador.isDominioNumericoValido(iptuVo.getTipoITPU()))
      {
         if (iptuVo.getTipoITPU().is(DomnTipoIPTU.ESTIMATIVA))
         {
            if (!Validador.isNumericoValido(iptuVo.getValorPercentualEstimado()))
            {
               ValidadorIPTU.isCampoPreenchido(null, MensagemErro.VALIDAR_INFORMAR_PERCENTUAL_ESTIMADO);
            }
         }
      }
   }

   private synchronized void incluir(final IPTUVo iptuVo) throws LogException, ObjetoObrigatorioException, AnotacaoException, 
         PersistenciaException
   {
      Validador.isObjetoValido(iptuVo);
      new GenericoLogAnotacaoDao(conn, false).insert(iptuVo, IPTUVo.class);
   }

   private synchronized void alterar(final IPTUVo iptuVo) throws ObjetoObrigatorioException, LogException, AnotacaoException, 
         PersistenciaException
   {
      Validador.validaObjeto(iptuVo);
      new GenericoLogAnotacaoDao(conn, true).update(iptuVo, IPTUVo.class);
   }
}
