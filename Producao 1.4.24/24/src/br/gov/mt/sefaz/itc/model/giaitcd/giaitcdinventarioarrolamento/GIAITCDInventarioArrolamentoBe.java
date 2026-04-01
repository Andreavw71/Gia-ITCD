 /**
  * Ábaco Tecnologia de Informação - LTDA
  * Arquivo: GIAITCDInventarioArrolamentoBe.java
  * Revisão:
  * Data revisão:
  * $Id: GIAITCDInventarioArrolamentoBe.java,v 1.17 2009/05/05 20:14:01 ricardo.moraes Exp $
  */
 package br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento;

 import br.com.abaco.log5.GenericoLogAnotacaoDao;
 import br.com.abaco.log5.util.excecoes.AnotacaoException;
 import br.com.abaco.log5.util.excecoes.LogException;
 import br.com.abaco.log5.util.excecoes.PersistenciaException;
 import br.com.abaco.util.Validador;
 import br.com.abaco.util.data.AbacoData;
 import br.com.abaco.util.exceptions.ConexaoException;
 import br.com.abaco.util.exceptions.ConsultaException;
 import br.com.abaco.util.exceptions.DadoNecessarioInexistenteException;
 import br.com.abaco.util.exceptions.IntegracaoException;
 import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
 import br.com.abaco.util.exceptions.ParametroObrigatorioException;
 import br.com.abaco.util.exceptions.RegistroExistenteException;
 import br.com.abaco.util.exceptions.RegistroNaoPodeSerUtilizadoException;

 import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
 import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelBe;
 import br.gov.mt.sefaz.itc.model.generico.bemtributavel.BemTributavelVo;
 import br.gov.mt.sefaz.itc.model.generico.beneficiario.BeneficiarioBe;
 import br.gov.mt.sefaz.itc.model.generico.beneficiario.BeneficiarioVo;
 import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDBe;
 import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
 import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.beneficiario.GIAITCDInventarioArrolamentoBeneficiarioBe;
 import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.beneficiario.GIAITCDInventarioArrolamentoBeneficiarioVo;
 import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdinventarioarrolamento.beneficiario.aliquota.GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo;
 import br.gov.mt.sefaz.itc.model.giaitcd.giaitcdseparacaodivorcio.GIAITCDSeparacaoDivorcioVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.configuracaogerencialparametros.ConfiguracaoGerencialParametrosBe;
import br.gov.mt.sefaz.itc.model.tabelabasica.configuracaogerencialparametros.ConfiguracaoGerencialParametrosVo;
import br.gov.mt.sefaz.itc.model.tabelabasica.parametrosdalegislacao.AliquotaVo;
 import br.gov.mt.sefaz.itc.model.tabelabasica.parametrosdalegislacao.MultaVo;
 import br.gov.mt.sefaz.itc.model.tabelabasica.parametrosdalegislacao.ParametroLegislacaoBe;
 import br.gov.mt.sefaz.itc.model.tabelabasica.parametrosdalegislacao.ParametroLegislacaoVo;
 import br.gov.mt.sefaz.itc.util.NumeroUtil;
 import br.gov.mt.sefaz.itc.util.data.DataUtil;
 import br.gov.mt.sefaz.itc.util.dominio.DomnEstadoCivil;
 import br.gov.mt.sefaz.itc.util.dominio.DomnRegimeCasamento;
 import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusGIAITCD;
import br.gov.mt.sefaz.itc.util.dominio.DomnTipoBem;
 import br.gov.mt.sefaz.itc.util.dominio.DomnTipoGIA;
 import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcesso;
 import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProcessoInventario;
 import br.gov.mt.sefaz.itc.util.dominio.DomnTipoProtocolo;
 import br.gov.mt.sefaz.itc.util.dominio.DomnVersaoGIAITCD;
 import br.gov.mt.sefaz.itc.util.facade.ConfiguracaoITCD;
 import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
 import br.gov.mt.sefaz.itc.util.integracao.cadastro.CadastroBe;
 import br.gov.mt.sefaz.itc.util.integracao.cadastro.CepIntegracaoVo;
 import br.gov.mt.sefaz.itc.util.integracao.cadastro.ContribuinteIntegracaoVo;
 import br.gov.mt.sefaz.itc.util.integracao.cadastro.UFIntegracaoVO;
 import br.gov.mt.sefaz.itc.util.integracao.gestaopessoas.ServidorSefazIntegracaoVo;
 import br.gov.mt.sefaz.itc.util.log.ExibirLOG;

 import java.sql.Connection;
 import java.sql.SQLException;

import java.text.DateFormat;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
 import java.util.Calendar;
 import java.util.Collection;
 import java.util.Collections;
 import java.util.Date;
 import java.util.Iterator;
 import java.util.List;

 /**
  * Classe responsável por implementar a regra de negócio da entidade GIAITCDIventarioArrolamento
  * @author Lucas Nascimento
  * @version $Revision: 1.17 $
  */
 public class GIAITCDInventarioArrolamentoBe extends AbstractBe
 {

    /**
     * Construtor da classe.
     * Este construtor não abre uma conexão com o banco de dados, mas utiliza a conexão recebida como parâmetro para realizar suas validações.
     * @param conexao objeto de conexão com o banco de dados.
     * @implemented by Lucas Nascimento
     */
    public GIAITCDInventarioArrolamentoBe(Connection conexao)
    {
       super(conexao);
    }

    /**
     * Construtor padrão da classe.
     * Este construtor abre uma conexão com o banco de dados do ITCD para realizar suas validações.
     * @throws SQLException Esta exceção deve ser lançada para prover informações de problemas/erros durante o acesso a um banco de dados.
     * @implemented by Lucas Nascimento
     */
    public GIAITCDInventarioArrolamentoBe() throws SQLException
    {
       super();
    }

    /**
     * Este metodo altera os dados de uma GIA do tipo Inventario e arrolamento
     * @param giaITCDInventarioArrolamentoVo
     * @throws ObjetoObrigatorioException
     * @throws ParametroObrigatorioException
     * @throws LogException
     * @throws PersistenciaException
     * @throws AnotacaoException
     * @throws RegistroExistenteException
     * @throws IntegracaoException
     * @throws ConsultaException
     * @throws ConexaoException
     * @throws RegistroNaoPodeSerUtilizadoException
     * @implemented by Leandro Dorileo
     * @implemented by Lucas nascimento
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    public synchronized void alterarGIAITCDInventarioArrolamento(final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ObjetoObrigatorioException, 
             ParametroObrigatorioException, LogException, 
             PersistenciaException, AnotacaoException, 
             RegistroExistenteException, IntegracaoException, 
             ConsultaException, ConexaoException, 
             RegistroNaoPodeSerUtilizadoException
    {
       Validador.validaObjeto(giaITCDInventarioArrolamentoVo);
       try
       {
          try
          {
             synchronized (GIAITCDInventarioArrolamentoVo.class)
             {
                validaParametrosIncluirGIAITCDInventarioArrolamento(giaITCDInventarioArrolamentoVo);
                giaITCDInventarioArrolamentoVo.setDataAtualizacaoBD(new Date());
                alterar(giaITCDInventarioArrolamentoVo);
                //adicionando o meerio como beneficiario
                BeneficiarioVo beneficiarios = giaITCDInventarioArrolamentoVo.getBeneficiarioVo();
                if (giaITCDInventarioArrolamentoVo.getFlagMeeiroBeneficiario().is(DomnSimNao.SIM))
                {
                   beneficiarios = new BeneficiarioVo(beneficiarios.getCollVO());
                   beneficiarios.getCollVO().add(giaITCDInventarioArrolamentoVo.getMeeiroBeneficiario());
                }
                GIAITCDInventarioArrolamentoBeneficiarioVo beneficiarioArrolamento = new GIAITCDInventarioArrolamentoBeneficiarioVo();
                beneficiarioArrolamento.setCollVO(beneficiarios.getCollVO());
                beneficiarioArrolamento.setGiaITCDVo(giaITCDInventarioArrolamentoVo);
                beneficiarioArrolamento.setLogSefazVo(giaITCDInventarioArrolamentoVo.getLogSefazVo());
                new GIAITCDInventarioArrolamentoBeneficiarioBe(conn).alterarGIAITCDInventarioArrolamentoBeneficiarioAlterarGIAInventarioArrolamento(beneficiarioArrolamento);
             }
          }
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
          catch (RegistroNaoPodeSerUtilizadoException e)
          {
             conn.rollback();
             throw e;
          }
          catch (LogException e)
          {
             conn.rollback();
             throw e;
          }
          catch (PersistenciaException e)
          {
             conn.rollback();
             throw e;
          }
          catch (AnotacaoException e)
          {
             conn.rollback();
             throw e;
          }
          catch (RegistroExistenteException e)
          {
             conn.rollback();
             throw e;
          }
          catch (IntegracaoException e)
          {
             conn.rollback();
             throw e;
          }
          catch (ConsultaException e)
          {
             conn.rollback();
             throw e;
          }
          catch (ConexaoException e)
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
     * Consulta os dados de um inventário e arrolamento.
     * 
     * @param giaITCDInventarioArrolamentoVo
     * @return
     * @throws ObjetoObrigatorioException
     * @throws ConsultaException
     * @throws IntegracaoException
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    public GIAITCDInventarioArrolamentoVo consultarGIAITCDInventarioArrolamento(final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ObjetoObrigatorioException, 
             ConsultaException, IntegracaoException, SQLException
    {
       Validador.validaObjeto(giaITCDInventarioArrolamentoVo);
       (new GIAITCDInventarioArrolamentoQDao(conn)).findGIAITCDInventarioArrolamento(giaITCDInventarioArrolamentoVo);
       
       if (giaITCDInventarioArrolamentoVo.isConsultaCompleta())
       {
          BeneficiarioVo beneficiarioConsulta = new BeneficiarioVo();
          beneficiarioConsulta.setGiaITCDVo(new GIAITCDVo(giaITCDInventarioArrolamentoVo.getCodigo()));
          beneficiarioConsulta = new BeneficiarioVo(beneficiarioConsulta);
          Collection collBeneficiario = new ArrayList();
          Collection listaDemonstrativoVo = new ArrayList();
          new BeneficiarioBe(conn).listaBeneficiario(beneficiarioConsulta);
          for (Iterator iteBeneficiario = beneficiarioConsulta.getCollVO().iterator(); iteBeneficiario.hasNext(); )
          {
             BeneficiarioVo beneficiarioAtual = (BeneficiarioVo) iteBeneficiario.next();
             GIAITCDInventarioArrolamentoBeneficiarioVo beneficiarioInventarioConsulta = new GIAITCDInventarioArrolamentoBeneficiarioVo(beneficiarioAtual.getCodigo());
             beneficiarioInventarioConsulta = new GIAITCDInventarioArrolamentoBeneficiarioVo(beneficiarioInventarioConsulta);
             beneficiarioInventarioConsulta.setConsultaCompleta(true);
             beneficiarioConsulta = (new GIAITCDInventarioArrolamentoBeneficiarioBe(conn)).consultaGIAITCDInventarioArrolamentoBeneficiario(beneficiarioInventarioConsulta);
             if (((GIAITCDInventarioArrolamentoBeneficiarioVo) beneficiarioConsulta).getFlagBeneficiarioMeeiro().is(DomnSimNao.NAO))
             {
                collBeneficiario.add(beneficiarioConsulta);
             }
             else
             {
                giaITCDInventarioArrolamentoVo.setMeeiroBeneficiario((GIAITCDInventarioArrolamentoBeneficiarioVo) beneficiarioConsulta);
             }                         
             
          }
          
          if (Validador.isCollectionValida(collBeneficiario))
          {
             giaITCDInventarioArrolamentoVo.getBeneficiarioVo().setCollVO(collBeneficiario);
          }              
       
          if (Validador.isNumericoValido(giaITCDInventarioArrolamentoVo.getPessoaDeCujus().getNumrContribuinte().longValue()))
          {
             ContribuinteIntegracaoVo pessoaConsulta = new ContribuinteIntegracaoVo(giaITCDInventarioArrolamentoVo.getPessoaDeCujus());
             giaITCDInventarioArrolamentoVo.setPessoaDeCujus((new CadastroBe(conn)).obterContribuinte(pessoaConsulta));
          }
          if (Validador.isNumericoValido(giaITCDInventarioArrolamentoVo.getPessoaMeeiro().getNumrContribuinte().longValue()))
          {
             ContribuinteIntegracaoVo pessoaConsulta = new ContribuinteIntegracaoVo(giaITCDInventarioArrolamentoVo.getPessoaMeeiro());
             giaITCDInventarioArrolamentoVo.setPessoaMeeiro((new CadastroBe(conn)).obterContribuinte(pessoaConsulta));
          }
          UFIntegracaoVO ufConsulta = new UFIntegracaoVO();
          ufConsulta.setCollVO((new CadastroBe(conn)).listarUf());
          for (Iterator iteUF = ufConsulta.getCollVO().iterator(); iteUF.hasNext(); )
          {
             UFIntegracaoVO ufAtual = (UFIntegracaoVO) iteUF.next();
             if (ufAtual.getSiglUf().equals(giaITCDInventarioArrolamentoVo.getUfAbertura().getSiglUf()))
             {
                giaITCDInventarioArrolamentoVo.setUfAbertura(ufAtual);
                giaITCDInventarioArrolamentoVo.getUfAbertura().setCollVO(ufConsulta.getCollVO());
                break;
             }
          }            
          
       }
       return giaITCDInventarioArrolamentoVo;
    }

    /**
     * @param giaITCDInventarioArrolamentoVo
     * @throws ObjetoObrigatorioException
     * @throws LogException
     * @throws AnotacaoException
     * @throws PersistenciaException
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    private synchronized void incluir(final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ObjetoObrigatorioException, 
             LogException, AnotacaoException, 
             PersistenciaException
    {
       Validador.validaObjeto(giaITCDInventarioArrolamentoVo);
       new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).insert(giaITCDInventarioArrolamentoVo);
    }

    /**
     * @param giaITCDInventarioArrolamentoVo
     * @throws ObjetoObrigatorioException
     * @throws LogException
     * @throws AnotacaoException
     * @throws PersistenciaException
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    private synchronized void alterar(final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ObjetoObrigatorioException, 
             LogException, AnotacaoException, 
             PersistenciaException
    {
       Validador.validaObjeto(giaITCDInventarioArrolamentoVo);
       new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).update(giaITCDInventarioArrolamentoVo);
    }

    /**
     * Inclui informações sobre um Inventário / Arrolamento no banco de dados
     * Este método é o responsável por tratar das regras de negócio da inclusão de um Inventário / Arrolamento.
     * @param giaITCDInventarioArrolamentoVo (Value Object)
     * @throws ObjetoObrigatorioException
     * @throws RegistroExistenteException
     * @throws RegistroNaoPodeSerUtilizadoException
     * @throws ParametroObrigatorioException
     * @throws LogException
     * @throws PersistenciaException
     * @throws AnotacaoException
     * @throws ConexaoException
     * @throws ConsultaException
     * @implemented by Lucas Nascimento
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    public synchronized void incluirGIAITCDInventarioArrolamento(final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ObjetoObrigatorioException, 
             RegistroExistenteException, 
             RegistroNaoPodeSerUtilizadoException, 
             ParametroObrigatorioException, LogException, 
             PersistenciaException, AnotacaoException, 
             ConexaoException
    {
       Validador.validaObjeto(giaITCDInventarioArrolamentoVo);
       try
       {
          try
          {
             synchronized (GIAITCDInventarioArrolamentoVo.class)
             {
                validaParametrosIncluirGIAITCDInventarioArrolamento(giaITCDInventarioArrolamentoVo);
                incluir(giaITCDInventarioArrolamentoVo);
                //adicionando o meerio como beneficiario
                BeneficiarioVo beneficiarios = giaITCDInventarioArrolamentoVo.getBeneficiarioVo();
                if (giaITCDInventarioArrolamentoVo.getFlagMeeiroBeneficiario().is(DomnSimNao.SIM))
                {
                   beneficiarios = new BeneficiarioVo(beneficiarios.getCollVO());
                   beneficiarios.getCollVO().add(giaITCDInventarioArrolamentoVo.getMeeiroBeneficiario());
                }
                //adicionando os beneficiarios.
                GIAITCDInventarioArrolamentoBeneficiarioBe beneficiarioBe = new GIAITCDInventarioArrolamentoBeneficiarioBe(conn);
                GIAITCDInventarioArrolamentoBeneficiarioVo beneficiarioArrolamento = new GIAITCDInventarioArrolamentoBeneficiarioVo();
                beneficiarioArrolamento.setCollVO(beneficiarios.getCollVO());
                beneficiarioArrolamento.setGiaITCDVo(giaITCDInventarioArrolamentoVo);
                beneficiarioArrolamento.setLogSefazVo(giaITCDInventarioArrolamentoVo.getLogSefazVo());
                beneficiarioBe.incluirGIAITCDInventarioArrolamentoBeneficiarioIncluirGIAInventario(beneficiarioArrolamento);
                                                           
                                           

                /*
                if(giaITCDInventarioArrolamentoVo.getFlagMeeiroBeneficiario().is(DomnSimNao.SIM))
                {
                   giaITCDInventarioArrolamentoVo.getMeeiroBeneficiario().setLogSefazVo(giaITCDInventarioArrolamentoVo.getLogSefazVo());
                   giaITCDInventarioArrolamentoVo.getMeeiroBeneficiario().setGiaITCDVo(giaITCDInventarioArrolamentoVo);
                   formAcesso.dadosLog(FuncionalidadeLog.INCLUIR_GIAITCD_INVENTARIO_ARROLAMENTO_BENEFICIARIO, giaITCDInventarioArrolamentoVo.getMeeiroBeneficiario());
                   beneficiarioBe.incluirGIAITCDInventarioArrolamentoBeneficiario(giaITCDInventarioArrolamentoVo.getMeeiroBeneficiario());                
                }
                */
                //(new GIAITCDBe(conn)).alterarGIAITCDDataAtualizacaoBD((GIAITCDVo) giaITCDInventarioArrolamentoVo);              
                //commit();
                  
                
                
             }
          }
          catch (ObjetoObrigatorioException e)
          {
             conn.rollback();
             throw e;
          }
          catch (ParametroObrigatorioException e)
          {
             conn.rollback();
             throw e;
          }
          catch (RegistroExistenteException e)
          {
             conn.rollback();
             throw e;
          }
          catch (RegistroNaoPodeSerUtilizadoException e)
          {
             conn.rollback();
             throw e;
          }
          catch (LogException e)
          {
             conn.rollback();
             throw e;
          }
          catch (PersistenciaException e)
          {
             conn.rollback();
             throw e;
          }
          catch (AnotacaoException e)
          {
             conn.rollback();
             throw e;
          }
          catch (ConexaoException e)
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
     * Método que valida o cpf de: de cujus, inventariante, meeiro e procurador
     * @param gia
     * @throws ObjetoObrigatorioException
     * @throws RegistroNaoPodeSerUtilizadoException
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    public static void validaCPFPessoas(final GIAITCDInventarioArrolamentoVo gia) throws ObjetoObrigatorioException, 
             RegistroNaoPodeSerUtilizadoException
    {
       GIAITCDInventarioArrolamentoBe.validaCPFDeCujus(gia.getPessoaDeCujus(), gia);
       GIAITCDInventarioArrolamentoBe.validaCPFInventariante(gia.getResponsavelVo(), gia);
       GIAITCDInventarioArrolamentoBe.validaCPFMeeiro(gia.getPessoaMeeiro(), gia);
       GIAITCDInventarioArrolamentoBe.validaCPFProcurador(gia.getProcuradorVo(), gia);
    }

    /**
     * Método para validar se o CPF do Inventariante não é igual ao CPF De Cujus
     * 
     * @param inventarianteVo
     * @param giaITCDInventarioArrolamentoVo
     * @throws ObjetoObrigatorioException
     * @throws RegistroNaoPodeSerUtilizadoException
     * @implemented by Daniel Balieiro
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    public static void validaCPFInventariante(ContribuinteIntegracaoVo inventarianteVo, final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ObjetoObrigatorioException, 
             RegistroNaoPodeSerUtilizadoException
    {
       Validador.validaObjeto(giaITCDInventarioArrolamentoVo);
       Validador.validaObjeto(inventarianteVo);
       if (Validador.isNumericoValido(giaITCDInventarioArrolamentoVo.getPessoaDeCujus().getNumrContribuinte()))
       {
          if ((inventarianteVo.getNumrDocumento().equals(giaITCDInventarioArrolamentoVo.getPessoaDeCujus().getNumrDocumento())))
          {
             throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_COMPARA_CPF_INVENTARIANTE_DE_CUJUS);
          }
       }
       if (Validador.isNumericoValido(giaITCDInventarioArrolamentoVo.getProcuradorVo().getNumrContribuinte()))
       {
          if ((inventarianteVo.getNumrDocumento().equals(giaITCDInventarioArrolamentoVo.getProcuradorVo().getNumrDocumento())))
          {
             throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_COMPARA_CPF_INVENTARIANTE_PROCURADOR);
          }
       }
    }

    /**
     * Método para validar se o CPF do De Cujus não é igual ao CPF do Inventariante ou do Meeiro
     * 
     * @param deCujusVo
     * @param giaITCDInventarioArrolamentoVo
     * @throws RegistroNaoPodeSerUtilizadoException
     * @throws ObjetoObrigatorioException
     * @implemented by Daniel Balieiro
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    public static void validaCPFDeCujus(ContribuinteIntegracaoVo deCujusVo, final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws RegistroNaoPodeSerUtilizadoException, 
             ObjetoObrigatorioException
    {
       Validador.validaObjeto(deCujusVo);
       Validador.validaObjeto(giaITCDInventarioArrolamentoVo);
       if ((deCujusVo.getNumrDocumento().equals(giaITCDInventarioArrolamentoVo.getResponsavelVo().getNumrDocumento())))
       {
          throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_CPF_CUJUS_RESPONSAVEL);
       }
       if ((deCujusVo.getNumrDocumento().equals(giaITCDInventarioArrolamentoVo.getPessoaMeeiro().getNumrDocumento())))
       {
          throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_CPF_CUJUS_MEEIRO);
       }
       if ((deCujusVo.getNumrDocumento().equals(giaITCDInventarioArrolamentoVo.getProcuradorVo().getNumrDocumento())))
       {
          throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_CPF_CUJUS_PROCURADOR);
       }
    }

    /**
     * @param beneficiarioVo
     * @param giaITCDInventarioArrolamentoVo
     * @throws ObjetoObrigatorioException
     * @throws RegistroNaoPodeSerUtilizadoException
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    public static void validaCPFBeneficiario(ContribuinteIntegracaoVo beneficiarioVo, final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ObjetoObrigatorioException, 
             RegistroNaoPodeSerUtilizadoException
    {
       Validador.validaObjeto(beneficiarioVo);
       Validador.validaObjeto(giaITCDInventarioArrolamentoVo);
       if ((beneficiarioVo.getNumrDocumento().equals(giaITCDInventarioArrolamentoVo.getPessoaDeCujus().getNumrDocumento())))
       {
          throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_CPF_BENEFICIARIO_CUJUS);
       }
       switch (giaITCDInventarioArrolamentoVo.getNaturezaOperacaoVo().getTipoProcesso().getValorCorrente())
       {
          case DomnTipoProcesso.INVENTARIO_ARROLAMENTO:
             {
                switch (giaITCDInventarioArrolamentoVo.getEstadoCivilDeCujus().getValorCorrente())
                {
                   case DomnEstadoCivil.CASADO:
                      {
                         switch (giaITCDInventarioArrolamentoVo.getRegimeCasamento().getValorCorrente())
                         {
                            case DomnRegimeCasamento.COMUNHAO_PARCIAL_DE_BENS:
                               {
                                  if ((beneficiarioVo.getNumrDocumento().equals(giaITCDInventarioArrolamentoVo.getPessoaMeeiro().getNumrDocumento())))
                                  {
                                     throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_CPF_BENEFICIARIO_MEEIRO_CASADO_COMUNHAO_PARCIAL);
                                  }
                                  break;
                               }
                            case DomnRegimeCasamento.COMUNHAO_UNIVERSAL_DE_BENS:
                               {
                                  if ((beneficiarioVo.getNumrDocumento().equals(giaITCDInventarioArrolamentoVo.getPessoaMeeiro().getNumrDocumento())))
                                  {
                                     throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_CPF_BENEFICIARIO_MEEIRO_CASADO_COMUNHAO_UNIVERSAL);
                                  }
                                  break;
                               }
                            case DomnRegimeCasamento.SEPARACAO_TOTAL_DE_BENS:
                               {
                                  if ((beneficiarioVo.getNumrDocumento().equals(giaITCDInventarioArrolamentoVo.getPessoaMeeiro().getNumrDocumento())))
                                  {
                                     throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_CPF_BENEFICIARIO_MEEIRO_CASADO_SEPARACAO_TOTAL);
                                  }
                                  break;
                               }
                         }
                         break;
                      }
                   case DomnEstadoCivil.CONVIVENTE:
                      {
                         if ((beneficiarioVo.getNumrDocumento().equals(giaITCDInventarioArrolamentoVo.getPessoaMeeiro().getNumrDocumento())))
                         {
                            throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_CPF_BENEFICIARIO_MEEIRO_CASADO_COMUNHAO_PARCIAL);
                         }
                         break;
                      }
                }
                break;
             }
       }
    }

    /**
     * Método para validar se o CPF do Meeiro não é igual ao CPF do De Cujus
     * 
     * @param meeiroVo
     * @param giaITCDInventarioArrolamentoVo
     * @throws ObjetoObrigatorioException
     * @throws RegistroNaoPodeSerUtilizadoException
     * @implemented by Daniel Balieiro
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    public static void validaCPFMeeiro(ContribuinteIntegracaoVo meeiroVo, final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ObjetoObrigatorioException, 
             RegistroNaoPodeSerUtilizadoException
    {
       Validador.validaObjeto(giaITCDInventarioArrolamentoVo);
       Validador.validaObjeto(meeiroVo);
       if ((meeiroVo.getNumrDocumento().equals(giaITCDInventarioArrolamentoVo.getPessoaDeCujus().getNumrDocumento())))
       {
          throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_COMPARA_CPF_MEEIRO_DE_CUJUS);
       }
       else
       {
          for (Iterator<GIAITCDInventarioArrolamentoBeneficiarioVo> it = giaITCDInventarioArrolamentoVo.getBeneficiarioVo().getCollVO().iterator(); it.hasNext(); )
          {
             GIAITCDInventarioArrolamentoBeneficiarioVo atual = it.next();
             if (meeiroVo.getNumrContribuinte().equals(atual.getPessoaBeneficiaria().getNumrContribuinte()))
             {
                throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_CPF_MEEIRO_BENEFICIARIO);
             }
          }
       }
    }

    /**
     * Método para validar o CPF do Procurador
     * 
     * @param procuradorVo
     * @param giaITCDInventarioArrolamentoVo
     * @throws ObjetoObrigatorioException
     * @throws RegistroNaoPodeSerUtilizadoException
     * @implemented by Daniel Balieiro
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    public static void validaCPFProcurador(ContribuinteIntegracaoVo procuradorVo, final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ObjetoObrigatorioException, 
             RegistroNaoPodeSerUtilizadoException
    {
       Validador.validaObjeto(procuradorVo);
       Validador.validaObjeto(giaITCDInventarioArrolamentoVo);
       if ((procuradorVo.getNumrDocumento().equals(giaITCDInventarioArrolamentoVo.getPessoaDeCujus().getNumrDocumento())))
       {
          throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_CPF_PROCURADOR_CUJUS);
       }
       if ((procuradorVo.getNumrDocumento().equals(giaITCDInventarioArrolamentoVo.getResponsavelVo().getNumrDocumento())))
       {
          throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_CPF_PROCURADOR_INVENTARIANTE);
       }
    }

    /**
     * Método utilizado para validar se o objeto do tipo GIAITCDInventarioArrolamentoVo possui todos os parâmetros obrigatórios.
     * @param giaITCDInventarioArrolamentoVo
     * @throws ObjetoObrigatorioException
     * @throws RegistroExistenteException
     * @throws RegistroNaoPodeSerUtilizadoException
     * @throws ParametroObrigatorioException
     * @implemented by Lucas Nascimento
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    public void validaParametrosIncluirGIAITCDInventarioArrolamento(final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ObjetoObrigatorioException, 
             RegistroExistenteException, 
             RegistroNaoPodeSerUtilizadoException, 
             ParametroObrigatorioException
    {
       Validador.validaObjeto(giaITCDInventarioArrolamentoVo);
       GIAITCDInventarioArrolamentoBe.validaDadosBasicos(giaITCDInventarioArrolamentoVo);
       GIAITCDBe.validaBemTributavel(giaITCDInventarioArrolamentoVo);
       GIAITCDBe.validaBeneficiario(giaITCDInventarioArrolamentoVo);
       validaHerdeirosBeneficiarios(giaITCDInventarioArrolamentoVo);
       GIAITCDBe.validaSenha(giaITCDInventarioArrolamentoVo);
    }

    /**
     * Este método valida os dados básicos de uma GIA de inventário arrolamento
     * 
     * @param giaITCDInventarioArrolamentoVo
     * @throws ParametroObrigatorioException
     * @throws ObjetoObrigatorioException
     * @throws RegistroNaoPodeSerUtilizadoException
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    public static void validaDadosBasicos(final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ParametroObrigatorioException, 
             ObjetoObrigatorioException, 
             RegistroNaoPodeSerUtilizadoException
    {
       GIAITCDBe.validaResponsavel(giaITCDInventarioArrolamentoVo);
       if (!Validador.isStringValida(giaITCDInventarioArrolamentoVo.getPessoaDeCujus().getNumrDocumento()))
       {
          //******** CASO DE USO - FE2                                                               |********************************|
          // *******VERIFICAR SE O CUJUS NÂO ESTA NO CADASTRO DE CONTRIBUINTES EVENTUAIS
          //********                                                                                  |********************************|
          throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_CPF);
       }
       validaEstadoCivilDeCujus(giaITCDInventarioArrolamentoVo);
       validaRegimeCasamentoDeCujus(giaITCDInventarioArrolamentoVo);
       validaMeeiro(giaITCDInventarioArrolamentoVo);
       validaCPFPessoas(giaITCDInventarioArrolamentoVo);
       validaDataIventarioFalecimento(giaITCDInventarioArrolamentoVo);
       validaUfAbertura(giaITCDInventarioArrolamentoVo);
       validaHerdeiros(giaITCDInventarioArrolamentoVo);
       GIAITCDBe.validaNaturezaOperacao(giaITCDInventarioArrolamentoVo);
       validaDataFalecimento(giaITCDInventarioArrolamentoVo);
       //validaDataInventario(giaITCDInventarioArrolamentoVo);
       validaTipoProcessoInvetario(giaITCDInventarioArrolamentoVo);
       validaRenuncia(giaITCDInventarioArrolamentoVo);
       GIAITCDBe.validaJustificativaAlteracao(giaITCDInventarioArrolamentoVo);
    }

    /**
     * Verifica se a opção de renúncia foi preenchida.
     * 
     * @param giaITCDInventarioArrolamentoVo
     * @throws ParametroObrigatorioException
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    public static void validaRenuncia(final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ParametroObrigatorioException
    {
       if (!Validador.isDominioNumericoValido(giaITCDInventarioArrolamentoVo.getRenuncia()))
       {
          throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_SELECT_RENUNCIA);
       }
       if (giaITCDInventarioArrolamentoVo.getRenuncia().is(DomnSimNao.SIM))
       {
          if (!Validador.isDominioNumericoValido(giaITCDInventarioArrolamentoVo.getTipoRenuncia()))
          {
             throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_SELECT_TIPO_RENUNCIA);
          }
       }
    }

    /**
     * Verifica se o estado civil do deCujos é valido
     * 
     * @param giaITCDInventarioArrolamentoVo
     * @throws ParametroObrigatorioException
     * @implemented by Leandro Dorileo
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    public static void validaEstadoCivilDeCujus(final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ParametroObrigatorioException
    {
       //ESTADO CIVIL
       if (!Validador.isDominioNumericoValido(giaITCDInventarioArrolamentoVo.getEstadoCivilDeCujus()))
       {
          throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_ESTADO_CIVIL);
       }
    }

    /**
     * Verifica se o regime do casamento do deCujos é válido
     * @param giaITCDInventarioArrolamentoVo
     * @throws ParametroObrigatorioException
     * @implemented by Leandro Dorileo
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    public static void validaRegimeCasamentoDeCujus(final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ParametroObrigatorioException
    {
       //REGIME DE CASAMENTO
       if ((giaITCDInventarioArrolamentoVo.getEstadoCivilDeCujus().getValorCorrente() == DomnEstadoCivil.CASADO) && (!Validador.isDominioNumericoValido(giaITCDInventarioArrolamentoVo.getRegimeCasamento())))
       {
          throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_REGIME_CASAMENTO);
       }
    }

    /**
     * Verifica se o Meeiro foi informado. Caso o estado civil do de cujus seja casado
     * deve existir um meeiro.
     * 
     * @param giaITCDInventarioArrolamentoVo
     * @throws ParametroObrigatorioException
     * @implemented by Leandro Dorileo
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    public static void validaMeeiro(final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ParametroObrigatorioException
    {
       //CPF do meeiro
       // aqui verificamos se foi informado o cpf - simplesmente se foi informado
       if (giaITCDInventarioArrolamentoVo.getEstadoCivilDeCujus().getValorCorrente() == DomnEstadoCivil.CASADO && !Validador.isStringValida(giaITCDInventarioArrolamentoVo.getPessoaMeeiro().getNumrDocumento()))
       {
          throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_CPF_MEEIRO_BRANCO);
       }
       // aqui verificamos se o cpf é válido - veja... a mensagem de erro é diferente para os casos
       if (giaITCDInventarioArrolamentoVo.getEstadoCivilDeCujus().getValorCorrente() == DomnEstadoCivil.CASADO && !Validador.isCPFValido(giaITCDInventarioArrolamentoVo.getPessoaMeeiro().getNumrDocumento()))
       {
          throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_CPF_MEEIRO_INVALIDO);
       }
    }

    /**
     * Verifica se a data do inventário e arrolamento é uma data válida. Verifica se a data do falecimento
     * é uma data valida, se a data do falecimento é anterior à data do inventário e arrolamento e se a
     * data do inventário e arrolamento é posterior à data do falecimento.
     * 
     * @param giaITCDInventarioArrolamentoVo
     * @throws ParametroObrigatorioException
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    public static void validaDataIventarioFalecimento(final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ParametroObrigatorioException
    {
       //FE9:1 - Data do falecimento
       if (!Validador.isDataValida(giaITCDInventarioArrolamentoVo.getDataFalecimento()))
       {
          throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_DATA_FALECIMENTO);
       }
       //Data do inventario/Arrolamento
       if (Validador.isDataValida(giaITCDInventarioArrolamentoVo.getDataInventario()))
       {
          //FE7:2 - Data do inventario/Arrolamento
          if (giaITCDInventarioArrolamentoVo.getDataFalecimento().after(giaITCDInventarioArrolamentoVo.getDataInventario()))
          {
             throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_DATA_INVENTARIO_MAIOR_DATA_FALECIMENTO);
          }
          //FE9:2 - Data do falecimento - seria uma verificação duplicada?
          if (giaITCDInventarioArrolamentoVo.getDataInventario().before(giaITCDInventarioArrolamentoVo.getDataFalecimento()))
          {
             throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_DATA_FALECIMENTO_MENOR_DATA_INVENTARIO);
          }
       }
    }

    /**
     * Valida se do Inventario e maior que a data atual.
     * @param giaITCDInventarioArrolamentoVo
     * @throws ParametroObrigatorioException
     * @implemented by Lucas Nascimento
     */
    public static void validaDataInventario(final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ParametroObrigatorioException
    {
       if (Validador.isDataValida(giaITCDInventarioArrolamentoVo.getDataInventario()))
       {
          if (giaITCDInventarioArrolamentoVo.getDataInventario().after(new Date()))
          {
             throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_DATA_INVENTARIO_MAIOR_DATA_ATUAL);
          }
       }
    }

    /**
     * Valida o campo tipo de processo e campos dependentes.
     * 
     * 
     * @param giaITCDInventarioArrolamentoVo
     * @throws ParametroObrigatorioException
     * @implemented Dherkyan Ribeiro da Silva
     */
    public static void validaTipoProcessoInvetario(final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ParametroObrigatorioException
    {
       if (Validador.isDominioNumericoValido(giaITCDInventarioArrolamentoVo.getTipoProcessoInventario()))
       {
          if (giaITCDInventarioArrolamentoVo.getTipoProcessoInventario().is(DomnTipoProcessoInventario.PROCESO_JUDICIAL))
          {
             if (!Validador.isDataValida(giaITCDInventarioArrolamentoVo.getDataInventario()))
             {
                throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_DATA_INVENTARIO);
             }
             if (giaITCDInventarioArrolamentoVo.getDataInventario().after(new Date()))
             {
                throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_DATA_INVENTARIO_MAIOR_DATA_ATUAL);
             }
             if (!Validador.isNumericoValido(giaITCDInventarioArrolamentoVo.getNumeroProcesso()))
             {
                throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_NUMERO_PROCESSO);
             }
             if (!Validador.isStringValida(giaITCDInventarioArrolamentoVo.getDescricaoJuizoComarca()))
             {
                throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_DATA_JUIZO_COMARCA);
             }
          }
          else
          {
             giaITCDInventarioArrolamentoVo.setDataInventario(new Date());
             giaITCDInventarioArrolamentoVo.setNumeroProcesso(0);
             giaITCDInventarioArrolamentoVo.setDescricaoJuizoComarca(null);
          }
       }
    }

    /**
     * Valida se do Falecimento e maior que a data atual.
     * @param giaITCDInventarioArrolamentoVo
     * @throws ParametroObrigatorioException
     * @implemented by Lucas Nascimento
     */
    public static void validaDataFalecimento(final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ParametroObrigatorioException
    {
       if (giaITCDInventarioArrolamentoVo.getDataFalecimento().after(new Date()))
       {
          throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_DATA_FALECIMENTO_MAIOR_DATA_ATUAL);
       }
    }

    /**
     * Valida o UF da abertura do processo de Inventário e Arrolamento.
     *
     * @param giaITCDInventarioArrolamentoVo     VO da GIA ITC Inventário e Arrolamento
     * @throws ParametroObrigatorioException
     */
    public static void validaUfAbertura(final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ParametroObrigatorioException
    {
       //UF de abertura do inventario/arrolamento
       if (!Validador.isStringValida(giaITCDInventarioArrolamentoVo.getUfAbertura().getSiglUf()))
       {
          throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_UF_ABERTURA);
       }
    }

    /**
     * Valida os herdeiros informados no processo de Inventário e Arrolamento.
     *
     * @param giaITCDInventarioArrolamentoVo     VO da GIA ITC Inventário e Arrolamento
     * @throws ParametroObrigatorioException
     */
    public static void validaHerdeiros(final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ParametroObrigatorioException
    {
       if (!Validador.isDominioNumericoValido(giaITCDInventarioArrolamentoVo.getHerdeirosDescendentes()))
       {
          throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_SELECT_HERDEIROS_DESCENDENTES);
       }
       if (giaITCDInventarioArrolamentoVo.getHerdeirosDescendentes().is(DomnSimNao.SIM))
       {
          if (!Validador.isNumericoValido(giaITCDInventarioArrolamentoVo.getNumeroHerdeirosDescendentes()))
          {
             throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_CAMPO_QUANTIDADE_HERDEIROS_DESCENDENTES);
          }
          giaITCDInventarioArrolamentoVo.setNumeroHerdeiros(giaITCDInventarioArrolamentoVo.getNumeroHerdeirosDescendentes());
       }
       else
       {
          if (!Validador.isDominioNumericoValido(giaITCDInventarioArrolamentoVo.getHerdeirosAscendentes()))
          {
             throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_SELECT_HERDEIROS_ASCENDENTES);
          }
          if (giaITCDInventarioArrolamentoVo.getHerdeirosAscendentes().is(DomnSimNao.SIM))
          {
             if (!Validador.isNumericoValido(giaITCDInventarioArrolamentoVo.getNumeroHerdeirosAscendentes()))
             {
                throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_CAMPO_QUANTIDADE_HERDEIROS_ASCENDENTES);
             }
             if (giaITCDInventarioArrolamentoVo.getNumeroHerdeirosAscendentes() > 2)
             {
                throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_CAMPO_QUANTIDADE_HERDEIROS_ASCENDENTES_MAIOR_QUE_DOIS);
             }
             giaITCDInventarioArrolamentoVo.setNumeroHerdeiros(giaITCDInventarioArrolamentoVo.getNumeroHerdeirosAscendentes());
          }
          else
          {
             if (!(giaITCDInventarioArrolamentoVo.getEstadoCivilDeCujus().is(DomnEstadoCivil.CASADO) || (giaITCDInventarioArrolamentoVo.getEstadoCivilDeCujus().is(DomnEstadoCivil.CONVIVENTE))))
             {
                if (!Validador.isNumericoValido(giaITCDInventarioArrolamentoVo.getNumeroOutrosHerdeiros()))
                {
                   throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_CAMPO_QUANTIDADE_OUTROS_HERDEIROS);
                }
                giaITCDInventarioArrolamentoVo.setNumeroHerdeiros(giaITCDInventarioArrolamentoVo.getNumeroOutrosHerdeiros());
             }
             else
             {
                giaITCDInventarioArrolamentoVo.setNumeroHerdeiros(0);
             }
          }
       }
    }

    /**
     *
     * @param giaITCDInventarioArrolamentoVo
     * @throws ParametroObrigatorioException
     * @implemented by Lucas Nascimento
     */
    public static void validaHerdeirosBeneficiarios(final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ParametroObrigatorioException
    {
       if (giaITCDInventarioArrolamentoVo.getNumeroHerdeiros() != giaITCDInventarioArrolamentoVo.getBeneficiarioVo().getCollVO().size() && !giaITCDInventarioArrolamentoVo.getBeneficiarioVo().getCollVO().isEmpty())
       {
          throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_VALIDA_NUMERO_HERDEIROS_DIFERENTE_BENEFICIARIOS);
       }
       else if (giaITCDInventarioArrolamentoVo.getNumeroHerdeiros() > 0 && giaITCDInventarioArrolamentoVo.getBeneficiarioVo().getCollVO().isEmpty())
       {
          throw new ParametroObrigatorioException(MensagemErro.BENEFICIARIO_NAO_INFORMADO);
       }
    }

    /**
     * Invocado pelo gerarDeministartivoCalculo do GIAITCD genérico. Responsávelo por implementar
     * as particularidades de calculo de demonstrativo para as especificidades do Inventário e arrolamento.
     * 
     * @param giaITCDInventarioArrolamentoVo
     * @throws ObjetoObrigatorioException
     * @throws ConsultaException
     * @throws ConexaoException
     * @throws ParametroObrigatorioException
     * @implemented by Leandro Dorileo
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    public void gerarDemonstrativoCalculo(final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ObjetoObrigatorioException, 
             ConsultaException, ConexaoException, 
             ParametroObrigatorioException
    {
       Validador.validaObjeto(giaITCDInventarioArrolamentoVo);
       // sem data de falecimento nao tem como continuar
       if (!Validador.isDataValida(giaITCDInventarioArrolamentoVo.getDataFalecimento()))
       {
          ExibirLOG.exibirLog("Erro: " + MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_DATA_FALECIMENTO_OBRIGATORIO, giaITCDInventarioArrolamentoVo.getCodigo());
          throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_INVENTARIO_ARROLAMENTO_DATA_FALECIMENTO_OBRIGATORIO);
       }
       /* FP:2-4 tem meeiro? 50%... caso contrário 100% */
       if (giaITCDInventarioArrolamentoVo.getEstadoCivilDeCujus().is(DomnEstadoCivil.CASADO))
       {
          if (giaITCDInventarioArrolamentoVo.getRegimeCasamento().is(DomnRegimeCasamento.COMUNHAO_PARCIAL_DE_BENS))
          {
             if (giaITCDInventarioArrolamentoVo.getBemTributavelVo().isExisteApenasBemParticular())
             {
                giaITCDInventarioArrolamentoVo.setFracaoIdeal(100);
             }
             else
             {
                giaITCDInventarioArrolamentoVo.setFracaoIdeal(50);
             }
          }
          else if (giaITCDInventarioArrolamentoVo.getRegimeCasamento().is(DomnRegimeCasamento.SEPARACAO_TOTAL_DE_BENS))
          {
             giaITCDInventarioArrolamentoVo.setFracaoIdeal(100);
          }
          else
          {
             giaITCDInventarioArrolamentoVo.setFracaoIdeal(50);
          }
       }
       else if (giaITCDInventarioArrolamentoVo.getEstadoCivilDeCujus().is(DomnEstadoCivil.CONVIVENTE))
       {
          if (giaITCDInventarioArrolamentoVo.getBemTributavelVo().isExisteApenasBemParticular())
          {
             giaITCDInventarioArrolamentoVo.setFracaoIdeal(100);
          }
          else
          {
             giaITCDInventarioArrolamentoVo.setFracaoIdeal(50);
          }
       }
       else
       {
          giaITCDInventarioArrolamentoVo.setFracaoIdeal(100);
       }
       /* FP:2-4 Parametro Legislacao vigente na data do obito */
       ParametroLegislacaoVo parametroLegislacaoVo = (new ParametroLegislacaoBe(conn)).consultarParametroLegislacaoAtualPorData(giaITCDInventarioArrolamentoVo.getDataFalecimento(), true);
       giaITCDInventarioArrolamentoVo.setParametroLegislacaoVo(parametroLegislacaoVo);
       /* FP:2-7 calcula o valor da multa */
       double percMulta = encontraPercentualMulta(giaITCDInventarioArrolamentoVo);
       giaITCDInventarioArrolamentoVo.setPercentualMulta(percMulta);
       /* FP:2-9 valor total dos bens declarados */
       calculaValorTotalBensTributaveis(giaITCDInventarioArrolamentoVo);
       calculaValorRecebidoBeneficiario(giaITCDInventarioArrolamentoVo);
       calculaValorRecebidoMeeiro(giaITCDInventarioArrolamentoVo);
       /* FP:2-10 Valor do Demonstrativo de Calculo */
       giaITCDInventarioArrolamentoVo.setValorCalculoDemonstrativo(giaITCDInventarioArrolamentoVo.getValorBaseCalculoTributavel());
       /** total - o valor separado para o meeiro(caso tenha) */
       double valorRecebido = 0;
       valorRecebido = giaITCDInventarioArrolamentoVo.getValorBaseCalculoTributavel();
       /** valor recebido por cada beneficiário */
       double valorRecebidoPorBeneficiario = 0;
       for (Iterator it = giaITCDInventarioArrolamentoVo.getBeneficiarioVo().getCollVO().iterator(); it.hasNext(); )
       {
          GIAITCDInventarioArrolamentoBeneficiarioVo beneficiario = (GIAITCDInventarioArrolamentoBeneficiarioVo) it.next();
          valorRecebidoPorBeneficiario = beneficiario.getValorRecebido();
          break;
       }
       AliquotaVo aliquotaVo = new AliquotaVo();
       GIAITCDInventarioArrolamentoBeneficiarioVo giaITCDInventarioArrolamentoBeneficiarioVo;
       GIAITCDInventarioArrolamentoBeneficiarioVo giaITCDInventarioArrolamentoMeeiroBeneficiarioVo;
       if (giaITCDInventarioArrolamentoVo.getParametroLegislacaoVo().isLegislacaoCascata())
       {
          // Selecionando apenas aliquotas do tipo Causa Mortis
          for (Iterator ite = giaITCDInventarioArrolamentoVo.getParametroLegislacaoVo().getAliquotaVo().getCollVO().iterator(); ite.hasNext(); )
          {
             AliquotaVo atual = (AliquotaVo) ite.next();
             if (atual.getTipoFatoGerador().is(DomnTipoGIA.CAUSA_MORTIS))
             {
                aliquotaVo.getCollVO().add(atual);
             }
          }
          Collections.sort((ArrayList) aliquotaVo.getCollVO());
          giaITCDInventarioArrolamentoBeneficiarioVo = calculaCascata(aliquotaVo.getCollVO(), valorRecebidoPorBeneficiario, giaITCDInventarioArrolamentoVo.getValorUPF());
          giaITCDInventarioArrolamentoMeeiroBeneficiarioVo = calculaCascata(aliquotaVo.getCollVO(), giaITCDInventarioArrolamentoVo.getMeeiroBeneficiario().getValorRecebido(), giaITCDInventarioArrolamentoVo.getValorUPF());
       }
       else
       {
          aliquotaVo = GIAITCDBe.encontraAliquotaNormal(giaITCDInventarioArrolamentoVo.getParametroLegislacaoVo().getAliquotaVo().getCollVO(), valorRecebidoPorBeneficiario, giaITCDInventarioArrolamentoVo.getValorUPF(), DomnTipoGIA.CAUSA_MORTIS);
          giaITCDInventarioArrolamentoBeneficiarioVo = calculaNormal(aliquotaVo, valorRecebidoPorBeneficiario);
          aliquotaVo = GIAITCDBe.encontraAliquotaNormal(giaITCDInventarioArrolamentoVo.getParametroLegislacaoVo().getAliquotaVo().getCollVO(), giaITCDInventarioArrolamentoVo.getMeeiroBeneficiario().getValorRecebido(), giaITCDInventarioArrolamentoVo.getValorUPF(), DomnTipoGIA.CAUSA_MORTIS);
          giaITCDInventarioArrolamentoMeeiroBeneficiarioVo = calculaNormal(aliquotaVo, giaITCDInventarioArrolamentoVo.getMeeiroBeneficiario().getValorRecebido());
       }
       double valorRecolherPorBeneficiario = 0;
       for (Iterator it = giaITCDInventarioArrolamentoVo.getBeneficiarioVo().getCollVO().iterator(); it.hasNext(); )
       {
          GIAITCDInventarioArrolamentoBeneficiarioVo atual = (GIAITCDInventarioArrolamentoBeneficiarioVo) it.next();
          atual.setGiaITCDVo(giaITCDInventarioArrolamentoVo);
          atual.getGiaITCDInventarioArrolamentoBeneficiarioAliquotaVo().getCollVO().clear();
          valorRecolherPorBeneficiario = 0;
          for (Iterator itBeneAliq = giaITCDInventarioArrolamentoBeneficiarioVo.getGiaITCDInventarioArrolamentoBeneficiarioAliquotaVo().getCollVO().iterator(); itBeneAliq.hasNext(); )
          {
             GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo aliquota = (GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo) itBeneAliq.next();
             GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo aliquotaClone = new GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo();
             //Clonando HARDCODE pois a implementação de HASH e EQUAL não permite usar o .CLONE de object
             aliquotaClone.setGiaITCDIventarioArrolamentoBeneficiarioVo(atual);
             aliquotaClone.setCodigoAliquota(aliquota.getCodigoAliquota());
             aliquotaClone.setValorBaseCalculo(aliquota.getValorBaseCalculo());
             aliquotaClone.setPercentualAliquota(aliquota.getPercentualAliquota());
             aliquotaClone.setValorRecolher(aliquota.getValorRecolher());
             valorRecolherPorBeneficiario += aliquota.getValorRecolher();
             atual.getGiaITCDInventarioArrolamentoBeneficiarioAliquotaVo().getCollVO().add(aliquotaClone);
          }
          atual.setValorITCDBeneficiario(valorRecolherPorBeneficiario);
          atual.setValorMultaRecolher(valorRecolherPorBeneficiario * percMulta / 100);
          atual.setValorRecolher(valorRecolherPorBeneficiario + atual.getValorMultaRecolher());
       }
       if (Validador.isNumericoValido(giaITCDInventarioArrolamentoVo.getMeeiroBeneficiario().getPessoaBeneficiaria().getNumrContribuinte()))
       {
          double valorRecolherMeeiroBeneficiario = 0;
          giaITCDInventarioArrolamentoVo.getMeeiroBeneficiario().setGiaITCDVo(giaITCDInventarioArrolamentoVo);
          giaITCDInventarioArrolamentoVo.getMeeiroBeneficiario().getGiaITCDInventarioArrolamentoBeneficiarioAliquotaVo().getCollVO().clear();
          for (Iterator itBeneMeeiroAliq = giaITCDInventarioArrolamentoMeeiroBeneficiarioVo.getGiaITCDInventarioArrolamentoBeneficiarioAliquotaVo().getCollVO().iterator(); itBeneMeeiroAliq.hasNext(); )
          {
             GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo aliquotaMeeiro = (GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo) itBeneMeeiroAliq.next();
             GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo aliquotaMeeiroClone = new GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo();
             aliquotaMeeiroClone.setGiaITCDIventarioArrolamentoBeneficiarioVo(giaITCDInventarioArrolamentoMeeiroBeneficiarioVo);
             aliquotaMeeiroClone.setCodigoAliquota(aliquotaMeeiro.getCodigoAliquota());
             aliquotaMeeiroClone.setValorBaseCalculo(aliquotaMeeiro.getValorBaseCalculo());
             aliquotaMeeiroClone.setPercentualAliquota(aliquotaMeeiro.getPercentualAliquota());
             aliquotaMeeiroClone.setValorRecolher(aliquotaMeeiro.getValorRecolher());
             valorRecolherMeeiroBeneficiario += aliquotaMeeiro.getValorRecolher();
             giaITCDInventarioArrolamentoVo.getMeeiroBeneficiario().getGiaITCDInventarioArrolamentoBeneficiarioAliquotaVo().getCollVO().add(aliquotaMeeiroClone);
          }
          giaITCDInventarioArrolamentoVo.getMeeiroBeneficiario().setValorITCDBeneficiario(valorRecolherMeeiroBeneficiario);
          giaITCDInventarioArrolamentoVo.getMeeiroBeneficiario().setValorMultaRecolher(valorRecolherMeeiroBeneficiario * percMulta / 100);
          giaITCDInventarioArrolamentoVo.getMeeiroBeneficiario().setValorRecolher(valorRecolherMeeiroBeneficiario + giaITCDInventarioArrolamentoVo.getMeeiroBeneficiario().getValorMultaRecolher());
       }
       
       if(giaITCDInventarioArrolamentoVo.getStatusVo().getStatusGIAITCD().getDomnValr() == DomnStatusGIAITCD.EM_ELABORACAO || giaITCDInventarioArrolamentoVo.getStatusVo().getStatusGIAITCD().getDomnValr() == DomnStatusGIAITCD.PENDENTE_DE_PROTOCOLO){
          verificarIsencaoImpostoMenorQueUmaUpfPorBeneficiario(giaITCDInventarioArrolamentoVo);
       }
       
       totalizaDemonstrativoCalculo(giaITCDInventarioArrolamentoVo);
    }

   public void verificarIsencaoImpostoMenorQueUmaUpfPorBeneficiario(final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo){
      ConfiguracaoGerencialParametrosVo configuracaoConsultaDataInicial = new ConfiguracaoGerencialParametrosVo();
      ConfiguracaoGerencialParametrosVo configuracaoConsultaDataFinal = new ConfiguracaoGerencialParametrosVo();
      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      configuracaoConsultaDataInicial.setCodigo(63);
      configuracaoConsultaDataFinal.setCodigo(64);

      configuracaoConsultaDataInicial = new ConfiguracaoGerencialParametrosVo(configuracaoConsultaDataInicial);
      configuracaoConsultaDataFinal = new ConfiguracaoGerencialParametrosVo(configuracaoConsultaDataFinal);
      ConfiguracaoGerencialParametrosBe configuracaoGerencialParametrosBe = null;

      Date dateInicial = null;
      Date dateFinal = null;
      try
      {
         configuracaoGerencialParametrosBe = new ConfiguracaoGerencialParametrosBe(conn);
         configuracaoGerencialParametrosBe.consultarConfiguracaoGerencialParametros(configuracaoConsultaDataInicial);
         configuracaoGerencialParametrosBe.consultarConfiguracaoGerencialParametros(configuracaoConsultaDataFinal);

         if (configuracaoConsultaDataInicial != null){
            dateInicial = df.parse(configuracaoConsultaDataInicial.getValorItem());
         }

         if (configuracaoConsultaDataFinal.getValorItem().equals(""))
         {
            // Como não está decidido a Data Final para isenções é criado uma Data controle para verificação 
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_YEAR, 1);
            String dataFormatada = df.format(cal.getTime());
            dateFinal = df.parse(dataFormatada);
         }
         else
         {
            dateFinal = df.parse(configuracaoConsultaDataFinal.getValorItem());
         }

      }
      catch (Exception e)
      {
         System.out.println("Erro ao buscar Parametros Gerenciais" + e.getStackTrace());
      }

      if (giaITCDInventarioArrolamentoVo.getDataFalecimento().getTime() >= dateInicial.getTime() && giaITCDInventarioArrolamentoVo.getDataFalecimento().before(dateFinal)){

         for (Iterator it = giaITCDInventarioArrolamentoVo.getBeneficiarioVo().getCollVO().iterator(); it.hasNext(); ){
            GIAITCDInventarioArrolamentoBeneficiarioVo giaInventarioBeneficiario = (GIAITCDInventarioArrolamentoBeneficiarioVo) it.next();
            
            if(giaInventarioBeneficiario.getValorITCDBeneficiario() <= giaITCDInventarioArrolamentoVo.getValorUPF() && giaInventarioBeneficiario.getValorITCDBeneficiario() > 0){
            
               giaITCDInventarioArrolamentoVo.setValorRecolhimento(giaITCDInventarioArrolamentoVo.getValorRecolhimento() - giaInventarioBeneficiario.getValorITCDBeneficiario());
               giaInventarioBeneficiario.setValorRecolher(0);
               giaInventarioBeneficiario.setValorMultaRecolher(0);
               giaInventarioBeneficiario.setInfoIsencao(1);
               giaInventarioBeneficiario.setInfoDispensaRecolhimento(2);
               for (Iterator iteAliquotas = giaInventarioBeneficiario.getGiaITCDInventarioArrolamentoBeneficiarioAliquotaVo().getCollVO().iterator(); iteAliquotas.hasNext();){
                  GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo giaITCDInventarioArrolamentoBeneficiarioAliquotaVo = (GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo) iteAliquotas.next();
                  giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.setValorRecolher(0);
               }  
               
            }else if(giaInventarioBeneficiario.getValorITCDBeneficiario() == 0){
               giaInventarioBeneficiario.setInfoIsencao(2);
               giaInventarioBeneficiario.setInfoDispensaRecolhimento(1);
            }else{
               giaInventarioBeneficiario.setInfoIsencao(1);
               giaInventarioBeneficiario.setInfoDispensaRecolhimento(1);
            }
         }
         
         // Aqui vou verificar todos os beneficiarios e preencher os campos de info isento e info dispensa e retirar o valor deles do valor a Recolher geral e deixar somente o valor Itcd Beneficiario.

         if (giaITCDInventarioArrolamentoVo.getMeeiroBeneficiario() != null && (giaITCDInventarioArrolamentoVo.getMeeiroBeneficiario().getValorRecolher() < giaITCDInventarioArrolamentoVo.getValorUPF())){
            giaITCDInventarioArrolamentoVo.getMeeiroBeneficiario().setValorRecolher(0);
            giaITCDInventarioArrolamentoVo.getMeeiroBeneficiario().setValorMultaRecolher(0);
            for (Iterator iteAliquotas = giaITCDInventarioArrolamentoVo.getMeeiroBeneficiario().getGiaITCDInventarioArrolamentoBeneficiarioAliquotaVo().getCollVO().iterator(); iteAliquotas.hasNext(); )
            {
               GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo giaITCDInventarioArrolamentoBeneficiarioAliquotaVo = (GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo) iteAliquotas.next();
               giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.setValorRecolher(0);
            }
            giaITCDInventarioArrolamentoVo.setValorRecolhimento(0);
         }
         
      }
   }
   

    /**
     * Este método faz o calculo de aliquota em cascata
     *
     * @param aliquotas        listagem de aliquotas aplicáveis à legislacao em questão
     * @param valorRecebido    valor recebido por cada beneficiário
     * @param valorUPF         valor corrente da UPF
     * @return
     */
    public GIAITCDInventarioArrolamentoBeneficiarioVo calculaCascata(Collection aliquotas, double valorRecebido, double valorUPF)
    {
       GIAITCDInventarioArrolamentoBeneficiarioVo giaITCDInventarioArrolamentoBeneficiarioVo = new GIAITCDInventarioArrolamentoBeneficiarioVo();
       for (Iterator it = aliquotas.iterator(); it.hasNext(); )
       {
          AliquotaVo atual = (AliquotaVo) it.next();
          GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo giaITCDInventarioArrolamentoBeneficiarioAliquotaVo = new GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo();
          giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.setCodigoAliquota(atual.getCodigo());
          giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.setPercentualAliquota(atual.getPercentualLegislacaoAliquota());
          double valorUPFFinal = (atual.getQuantidadeUPFFinal()) * valorUPF;
          double valorUPFInicial = (atual.getQuantidadeUPFInicial() - 1) * valorUPF;
          double valorFaixa = 0;
          if (valorUPFFinal != 0)
          {
             valorFaixa = (atual.getQuantidadeUPFFinal() - atual.getQuantidadeUPFInicial() + 1) * valorUPF;
          }
          else
          {
             valorFaixa = valorRecebido - valorUPFInicial;
          }
          if (valorRecebido > valorUPFInicial)
          {
             if (valorRecebido > valorUPFFinal)
             {
                giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.setValorBaseCalculo(valorFaixa);
                giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.setValorRecolher(valorFaixa * atual.getPercentualLegislacaoAliquota() / 100);
             }
             else
             {
                giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.setValorBaseCalculo(valorRecebido - valorUPFInicial);
                giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.setValorRecolher(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getValorBaseCalculo() * atual.getPercentualLegislacaoAliquota() / 100);
             }
          }
          else
          {
             break;
          }
          giaITCDInventarioArrolamentoBeneficiarioVo.getGiaITCDInventarioArrolamentoBeneficiarioAliquotaVo().getCollVO().add(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo);
       }
       return giaITCDInventarioArrolamentoBeneficiarioVo;
    }

    /**
     * Este método faz o calculo de aliquota em cascata
     *
     * @param aliquota        listagem de aliquotas aplicáveis à legislacao em questão
     * @param valorRecebido    valor recebido por cada beneficiário
     * @return
     */
    public GIAITCDInventarioArrolamentoBeneficiarioVo calculaNormal(AliquotaVo aliquota, double valorRecebido)
    {
       GIAITCDInventarioArrolamentoBeneficiarioVo giaITCDInventarioArrolamentoBeneficiarioVo = new GIAITCDInventarioArrolamentoBeneficiarioVo();
       GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo giaITCDInventarioArrolamentoBeneficiarioAliquotaVo = new GIAITCDInventarioArrolamentoBeneficiarioAliquotaVo();
       giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.setCodigoAliquota(aliquota.getCodigo());
       giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.setPercentualAliquota(aliquota.getPercentualLegislacaoAliquota());
       giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.setValorBaseCalculo(valorRecebido);
       giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.setValorRecolher(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getValorBaseCalculo() * giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.getPercentualAliquota() / 100);
       giaITCDInventarioArrolamentoBeneficiarioAliquotaVo.setGiaITCDIventarioArrolamentoBeneficiarioVo(giaITCDInventarioArrolamentoBeneficiarioVo);
       giaITCDInventarioArrolamentoBeneficiarioVo.getGiaITCDInventarioArrolamentoBeneficiarioAliquotaVo().getCollVO().add(giaITCDInventarioArrolamentoBeneficiarioAliquotaVo);
       return giaITCDInventarioArrolamentoBeneficiarioVo;
    }

    /**
     * Gera os dados de totalizacao do demonstrativo de calculo
     *
     * @param gia        -     dados da gia a serem usados no calculo
     * @implemented by Leandro Dorileo
     */
    private void totalizaDemonstrativoCalculo(final GIAITCDInventarioArrolamentoVo gia)
    {
       double valorTotalITCD = 0;
       double valorTotalMulta = 0;
       double valorRecolhimento = 0;
       // ele ta somando o valor a recolher porem ele já está somado com o valor da multa.
       for (Iterator it = gia.getBeneficiarioVo().getCollVO().iterator(); it.hasNext(); )
       {
          GIAITCDInventarioArrolamentoBeneficiarioVo atual = (GIAITCDInventarioArrolamentoBeneficiarioVo) it.next();
          valorTotalITCD += atual.getValorITCDBeneficiario();
          valorTotalMulta += atual.getValorMultaRecolher();
          valorRecolhimento += (atual.getValorRecolher() - atual.getValorMultaRecolher());
       }
       if (Validador.isNumericoValido(gia.getMeeiroBeneficiario().getPessoaBeneficiaria().getNumrContribuinte()))
       {
          valorTotalITCD += gia.getMeeiroBeneficiario().getValorITCDBeneficiario();
          valorTotalMulta += gia.getMeeiroBeneficiario().getValorMultaRecolher();
          valorRecolhimento += gia.getMeeiroBeneficiario().getValorITCDBeneficiario();
       }
       
       gia.setValorITCD(valorTotalITCD);
       gia.setValorMulta(valorTotalMulta);
       gia.setValorRecolhimento(valorRecolhimento + valorTotalMulta);
       
       System.out.println("Valor total ITCD : " + gia.getValorITCDFormatado());
       System.out.println("Valor multa ITCD : " + gia.getValorMultaFormatado());
       System.out.println("Valor a recolher ITCD : " + gia.getValorRecolhimentoFormatado());
    }

    /**
     * Encontra o percentual de multa para uma determinada gia de acordo com o parametro legislaçao.
     * Busca as leis da tada do falecimento e da data de abertura do inventario e encontra o percentual mais ameno/benefico - mais baixo.
     * 
     * @param gia - dados da gia usados no calculo
     * @return percentual de multa para a gia informada
     * @throws ObjetoObrigatorioException
     * @throws ConsultaException
     * @throws ConexaoException
     * @throws ParametroObrigatorioException
     * @implemented by Leandro Dorileo
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    public double encontraPercentualMulta(final GIAITCDInventarioArrolamentoVo gia) throws ObjetoObrigatorioException, 
             ConsultaException, ConexaoException, 
             ParametroObrigatorioException
    {
       ParametroLegislacaoVo leiFalecimento = null; // legislação vigente na data do falecimento
       ParametroLegislacaoVo leiAtual = null; // legislação vigente 
       MultaVo multaLeiFalecimento = null; // multa que se enquadre no atraso de acordo com a lei vigente na data do falecimento
       MultaVo multaLeiAtual = null; // multa que se enquadre no atraso de acordo com a lei vigente 
       MultaVo multaUsada = new MultaVo(); // multa mais benéfica
       double percentualMultaFalecimento = 0; // percentual de multa que se enquadre no atraso de acordo com a lei vigente na data do falecimento
       double percentualMultaAtual = 0; // percentual de multa que se enquadre no atraso de acordo com a lei vigente 
       int totalAtraso = 0;
       // lei vigente na data do falecimento
       leiFalecimento = (new ParametroLegislacaoBe(conn)).consultarParametroLegislacaoAtualPorData(gia.getDataFalecimento(), true);
       // lei vigente na data do inventario
       leiAtual = new ParametroLegislacaoVo();
       leiAtual.setConsultaCompleta(true);
       leiAtual = (new ParametroLegislacaoBe(conn)).consultarParametroLegislacaoAtual(leiAtual);
       /** qual a multa aplicada para cada lei? */
       totalAtraso = obterDiasAtraso(leiFalecimento.getMultaVo(), gia);
       multaLeiFalecimento = encontraMultaPorAtraso(leiFalecimento.getMultaVo().getCollVO(), totalAtraso);
       multaUsada = multaLeiAtual = encontraMultaPorAtraso(leiAtual.getMultaVo().getCollVO(), totalAtraso);
       /** qual o percentual de multa aplicado para cada multa de cada lei? */
       percentualMultaFalecimento = (multaLeiFalecimento == null) ? 0 : multaLeiFalecimento.getPercentualMulta();
       percentualMultaAtual = (multaLeiAtual == null) ? 0 : multaLeiAtual.getPercentualMulta();
       if (percentualMultaFalecimento < percentualMultaAtual)
       {
          multaUsada = multaLeiFalecimento;
       }
       return multaUsada.getPercentualMulta();
    }

    /**Método responsável por obter código do município do procurador ou responsável.
     * @param gia
     * @return
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    private int obtemCodigoMunicipio(final GIAITCDInventarioArrolamentoVo gia)
    {
       ContribuinteIntegracaoVo pessoa = null;
       if (Validador.isNumericoValido(gia.getProcuradorVo().getNumrContribuinte()))
       {
          pessoa = gia.getProcuradorVo();
       }
       else
       {
          pessoa = gia.getResponsavelVo();
       }
       if (pessoa != null)
       {
          if (Validador.isNumericoValido(pessoa.getCodgMunicipio()))
          {
             return pessoa.getCodgMunicipio().intValue();
          }
          else
          {
             try
             {
                ContribuinteIntegracaoVo consulta = new ContribuinteIntegracaoVo();
                ContribuinteIntegracaoVo parametro = new ContribuinteIntegracaoVo();
                parametro.setNumrContribuinte(pessoa.getNumrContribuinte());
                consulta.setParametroConsulta(parametro);
                consulta = new CadastroBe(conn).obterContribuinte(consulta);
                return Validador.isNumericoValido(consulta.getCodgMunicipio().intValue()) ? consulta.getCodgMunicipio().intValue() : 0;
             }
             catch (Exception e)
             {
                return 0;
             }
          }
       }
       return 0;
    }

    /**
     * @return
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    private int obterCodigoMunicipioCapital()
    {
       try
       {
          return new CadastroBe(conn).obterCodigoMunicipioCapital();
       }
       catch (Exception e)
       {
          return 0;
       }
       finally{
       
          if (this.conn != null)
          {
             this.close();
          }
       }
       
    }

    /**
     * @param gia
     * @param diasParaVencido
     * @return
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    private Date obterVencimentoGiaLeiFalecimento(final GIAITCDInventarioArrolamentoVo gia, int diasParaVencido)
    {
       Date dataVencimento = DataUtil.adicionaValoresData(gia.getDataFalecimento(), Calendar.DATE, diasParaVencido);
       AbacoData abacoData = new AbacoData();
       int codigoMunicipio = obtemCodigoMunicipio(gia);
       if (!Validador.isNumericoValido(codigoMunicipio))
       {
          codigoMunicipio = obterCodigoMunicipioCapital();
       }
       try
       {
          if (!abacoData.isDiaUtil(dataVencimento, codigoMunicipio))
          {
             dataVencimento = abacoData.getProximoDiaUtil(dataVencimento, codigoMunicipio);
          }
       }
       catch (Exception e)
       {
          try
          {
             dataVencimento = AbacoData.getProximoDiaUtil(dataVencimento);
          }
          catch (Exception ex)
          {
             ;
          }
       }
       return dataVencimento;
    }

    /**
     * @param multa
     * @param gia
     * @return
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    private int obterDiasAtraso(final MultaVo multa, final GIAITCDInventarioArrolamentoVo gia)
    {
       int diasAtraso = 0;
       for (Iterator<MultaVo> it = multa.getCollVO().iterator(); it.hasNext(); )
       {
          MultaVo atual = it.next();
          if (atual.getPercentualMulta() == 0.0d)
          {
             Date dataVencimento = obterVencimentoGiaLeiFalecimento(gia, atual.getQuantidadeDiasFinal());
             dataVencimento = AbacoData.converteDataComPrimeiroMinutoDia(dataVencimento);
             Date dataInicioContagem = AbacoData.converteDataComPrimeiroMinutoDia(gia.getDataInventario());
             Date dataVencimentoCorrido = DataUtil.adicionaValoresData(gia.getDataFalecimento(), Calendar.DATE, atual.getQuantidadeDiasFinal());
             diasAtraso = atual.getQuantidadeDiasFinal() - DataUtil.converteMilissegundosDataEmDias(dataVencimento.getTime() - dataVencimentoCorrido.getTime());
             if (dataVencimento.compareTo(dataInicioContagem) < 0)
             {
                diasAtraso += DataUtil.converteMilissegundosDataEmDias(dataInicioContagem.getTime() - dataVencimento.getTime());
             }
             break;
          }
       }
       return diasAtraso;
    }

    /**
     * Encontra em uma lista de multas a que se enquadre no atraso especificado.
     *
     * @param listaMulta    lista de multas a serem percorridas
     * @param totalAtraso   praso de atrazo a ser levado em consideração
     * @return
     */
    private MultaVo encontraMultaPorAtraso(final Collection listaMulta, int totalAtraso)
    {
       MultaVo retorno = new MultaVo();
       for (Iterator it = listaMulta.iterator(); it.hasNext(); )
       {
          MultaVo atual = (MultaVo) it.next();
          if ((totalAtraso >= atual.getQuantidadeDiasInicial()) && (totalAtraso <= atual.getQuantidadeDiasFinal() || atual.getQuantidadeDiasFinal() == 0))
          {
             retorno = atual;
             break;
          }
       }
       return retorno;
    }

    /**
     * Método responsável por verificar se o contribuinte informado possui CEP.
     * @param contribuinteIntegracaoVo
     * @throws ObjetoObrigatorioException
     * @throws DadoNecessarioInexistenteException
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    public static void validaCEPContribuinte(ContribuinteIntegracaoVo contribuinteIntegracaoVo) throws ObjetoObrigatorioException, 
             DadoNecessarioInexistenteException
    {
       Validador.validaObjeto(contribuinteIntegracaoVo);
       if (!Validador.isNumericoValido(contribuinteIntegracaoVo.getEnderecoCEP()))
       {
          throw new DadoNecessarioInexistenteException(MensagemErro.DADOS_CADASTRAIS_INVENTARIANTE_DESATUALIZADO);
       }
    }

    /**
     * Método responsável por validar os dados do invetariante, e caso tudo esteja correto atribuir à GIA.
     * @param inventarianteVo
     * @param giaITCDInventarioArrolamentoVo
     * @throws ObjetoObrigatorioException
     * @throws RegistroNaoPodeSerUtilizadoException
     * @throws ParametroObrigatorioException
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    public static void atribuirInventariante(ContribuinteIntegracaoVo inventarianteVo, final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ObjetoObrigatorioException, 
             RegistroNaoPodeSerUtilizadoException, 
             DadoNecessarioInexistenteException
    {
       Validador.validaObjeto(inventarianteVo);
       Validador.validaObjeto(giaITCDInventarioArrolamentoVo);
       validaCPFInventariante(inventarianteVo, giaITCDInventarioArrolamentoVo);
       validaCEPContribuinte(inventarianteVo);
       giaITCDInventarioArrolamentoVo.setResponsavelVo(inventarianteVo);
    }

    /**
     * Método responsável por validar os dados do De Cujus, e caso tudo esteja correto atribuir à GIA.
     * @param deCujusVo
     * @param giaITCDInventarioArrolamentoVo
     * @throws ObjetoObrigatorioException
     * @throws RegistroNaoPodeSerUtilizadoException
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    public static void atribuirDeCujus(ContribuinteIntegracaoVo deCujusVo, final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ObjetoObrigatorioException, 
             RegistroNaoPodeSerUtilizadoException
    {
       Validador.validaObjeto(deCujusVo);
       Validador.validaObjeto(giaITCDInventarioArrolamentoVo);
       validaCPFDeCujus(deCujusVo, giaITCDInventarioArrolamentoVo);
       giaITCDInventarioArrolamentoVo.setPessoaDeCujus(deCujusVo);
    }

    /**
     * Método responsável por validar os dados do Meeiro, e caso tudo esteja correto atribuir à GIA.
     * @param meeiroVo
     * @param giaITCDInventarioArrolamentoVo
     * @throws ObjetoObrigatorioException
     * @throws RegistroNaoPodeSerUtilizadoException
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    public static void atribuirMeeiro(ContribuinteIntegracaoVo meeiroVo, final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ObjetoObrigatorioException, 
             RegistroNaoPodeSerUtilizadoException
    {
       Validador.validaObjeto(meeiroVo);
       Validador.validaObjeto(giaITCDInventarioArrolamentoVo);
       validaCPFMeeiro(meeiroVo, giaITCDInventarioArrolamentoVo);
       giaITCDInventarioArrolamentoVo.setPessoaMeeiro(meeiroVo);
    }

    /**
     * Método responsável por validar os dados do Procurador, e caso tudo esteja correto atribuir à GIA.
     * @param procuradorVo
     * @param giaITCDInventarioArrolamentoVo
     * @throws ObjetoObrigatorioException
     * @throws RegistroNaoPodeSerUtilizadoException
     * @throws ParametroObrigatorioException
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    public static void atribuirProcurador(ContribuinteIntegracaoVo procuradorVo, final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ObjetoObrigatorioException, 
             RegistroNaoPodeSerUtilizadoException, 
             DadoNecessarioInexistenteException
    {
       Validador.validaObjeto(procuradorVo);
       Validador.validaObjeto(giaITCDInventarioArrolamentoVo);
       validaCPFProcurador(procuradorVo, giaITCDInventarioArrolamentoVo);
       if (Validador.isNumericoValido(procuradorVo.getNumrContribuinte()))
       {
          validaCEPContribuinte(procuradorVo);
       }
       giaITCDInventarioArrolamentoVo.setProcuradorVo(procuradorVo);
    }

    /**
     * Método responsável por validar os dados do Beneficiário, e caso tudo esteja correto atribuir a lista de beneficiários.
     * @param beneficiarioVo
     * @param giaITCDInventarioArrolamentoVo
     * @throws ObjetoObrigatorioException
     * @throws DadoNecessarioInexistenteException
     * @throws RegistroExistenteException
     * @throws RegistroNaoPodeSerUtilizadoException
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    public static void atribuirBeneficiario(final ContribuinteIntegracaoVo beneficiarioVo, final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ObjetoObrigatorioException, 
             DadoNecessarioInexistenteException, 
             RegistroExistenteException, 
             RegistroNaoPodeSerUtilizadoException
    {
       Validador.validaObjeto(beneficiarioVo);
       Validador.validaObjeto(giaITCDInventarioArrolamentoVo);
       if (Validador.isNumericoValido(beneficiarioVo.getNumrContribuinte()))
       {
          validaCPFBeneficiario(beneficiarioVo, giaITCDInventarioArrolamentoVo);
          validarBeneficiarioJaInserido(beneficiarioVo, giaITCDInventarioArrolamentoVo.getBeneficiarioVo().getCollVO());
          validaCEPContribuinte(beneficiarioVo);
          GIAITCDInventarioArrolamentoBeneficiarioVo giaITCDInventarioArrolamentoBeneficiarioVo = new GIAITCDInventarioArrolamentoBeneficiarioVo();
          giaITCDInventarioArrolamentoBeneficiarioVo.setGiaITCDVo(giaITCDInventarioArrolamentoVo);
          giaITCDInventarioArrolamentoBeneficiarioVo.setPessoaBeneficiaria(beneficiarioVo);
          giaITCDInventarioArrolamentoBeneficiarioVo.setFlagBeneficiarioMeeiro(new DomnSimNao(DomnSimNao.NAO));
          giaITCDInventarioArrolamentoVo.getBeneficiarioVo().getCollVO().add(giaITCDInventarioArrolamentoBeneficiarioVo);
       }
    }

    /**
     * Método responsável por verificar na collection de beneficiários, se o beneficiário que está inserido já existe na lista de beneficiários.
     * @param beneficiarioVo
     * @param collVOBeneficiario
     * @throws ObjetoObrigatorioException
     * @throws RegistroExistenteException
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    private static void validarBeneficiarioJaInserido(final ContribuinteIntegracaoVo beneficiarioVo, Collection collVOBeneficiario) throws ObjetoObrigatorioException, 
             RegistroExistenteException
    {
       Validador.validaObjeto(beneficiarioVo);
       Validador.validaObjeto(collVOBeneficiario);
       for (Iterator it = collVOBeneficiario.iterator(); it.hasNext(); )
       {
          GIAITCDInventarioArrolamentoBeneficiarioVo beneficiarioAtual = (GIAITCDInventarioArrolamentoBeneficiarioVo) it.next();
          if (beneficiarioAtual.getPessoaBeneficiaria().getNumrDocumento().equals(beneficiarioVo.getNumrDocumento()))
          {
             throw new RegistroExistenteException(MensagemErro.VALIDAR_BENEFICIARIO_CPF_DUPLICADO);
          }
       }
    }

    /**
     * Método responsável por buscar o município do inventariante, para posterior definição do local de protocolo.
     * @param giaITCDInventarioArrolamentoVo
     * @throws ObjetoObrigatorioException
     * @throws IntegracaoException
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    public void atribuirMunicipioProtocolar(final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ObjetoObrigatorioException, 
             IntegracaoException, 
             DadoNecessarioInexistenteException
    {
       Validador.validaObjeto(giaITCDInventarioArrolamentoVo);
       if (Validador.isNumericoValido(giaITCDInventarioArrolamentoVo.getProcuradorVo().getNumrContribuinte()))
       {
          try
          {
             giaITCDInventarioArrolamentoVo.setMunicipioProtocolar((new CadastroBe(conn)).obterMunicipioPorCep(new CepIntegracaoVo(new Integer(giaITCDInventarioArrolamentoVo.getProcuradorVo().getEnderecoCEP().intValue()))));
          }
          catch (IntegracaoException e)
          {
             try
             {
                giaITCDInventarioArrolamentoVo.setMunicipioProtocolar((new CadastroBe(conn)).obterMunicipioPorCep(new CepIntegracaoVo(new Integer(giaITCDInventarioArrolamentoVo.getResponsavelVo().getEnderecoCEP().intValue()))));
             }
             catch (IntegracaoException exception)
             {
                throw new DadoNecessarioInexistenteException(MensagemErro.DADOS_CADASTRAIS_INVENTARIANTE_DESATUALIZADO);
             }
          }
       }
       else
       {
          try
          {
             giaITCDInventarioArrolamentoVo.setMunicipioProtocolar((new CadastroBe(conn)).obterMunicipioPorCep(new CepIntegracaoVo(new Integer(giaITCDInventarioArrolamentoVo.getResponsavelVo().getEnderecoCEP().intValue()))));
          }
          catch (IntegracaoException exception)
          {
             throw new DadoNecessarioInexistenteException(MensagemErro.DADOS_CADASTRAIS_INVENTARIANTE_DESATUALIZADO);
          }
       }
    }

    /**
     * Método responsável por obter a Base de Cálculo Tributável dos bens, 
     * obtendo os totais dos bens sendo classificados como anterior ao casamento e posterior ao casamento.
     * @param giaITCDInventarioArrolamentoVo
     * @throws ObjetoObrigatorioException
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    private static void calculaBaseCalculoTributavel(final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo, boolean ignoraAvaliacao) throws ObjetoObrigatorioException
    {
       Validador.validaObjeto(giaITCDInventarioArrolamentoVo);
       double valorBensAnteriorCasamento = 0;
       double valorBensAnteriorCasamentoConcordado = 0;
       double valorBensPosteriorCasamento = 0;
       double valorBensPosteriorCasamentoConcordado = 0;
       double valorTotalBensArbritradao = 0;
       double valorTotalBensConcordado = 0;
       double valorPercentual = 0;
       int BensComIsencaoPrevista = 0;
       int quantidadeBemConcordado = 0;
       if (Validador.isObjetoValido(giaITCDInventarioArrolamentoVo.getBemTributavelVo()) && Validador.isCollectionValida(giaITCDInventarioArrolamentoVo.getBemTributavelVo().getCollVO()))
       {
          //TODO Versão 1.3 
          if ((giaITCDInventarioArrolamentoVo.getNumeroVersaoGIAITCD().is(DomnVersaoGIAITCD.VERSAO_1_3)) || (giaITCDInventarioArrolamentoVo.getNumeroVersaoGIAITCD().is(DomnVersaoGIAITCD.VERSAO_1_4)))
          {
             for (Iterator iteBem = giaITCDInventarioArrolamentoVo.getBemTributavelVo().getCollVO().iterator(); iteBem.hasNext(); )
             {
                BemTributavelVo bem = (BemTributavelVo) iteBem.next();
                
                if (bem.getIsencaoPrevistaFormatada().is(DomnSimNao.NAO))
                {
                   if (bem.getBemVo().getClassificacaoTipoBem().is(DomnTipoBem.OUTROS_BENS))
                   {
                      if (giaITCDInventarioArrolamentoVo.getUfAbertura().getSiglUf().equals(ConfiguracaoITCD.SIGLA_ESTADO_MATO_GROSSO))
                      {
                         if (bem.getBemParticular().is(DomnSimNao.SIM))
                         {
                            valorBensAnteriorCasamento += BemTributavelBe.getValorBemTributavel(bem, ignoraAvaliacao);
                         }
                         else
                         {
                            valorBensPosteriorCasamento += BemTributavelBe.getValorBemTributavel(bem, ignoraAvaliacao);
                         }
                         if (bem.getConcordaComValorArbitrado().is(DomnSimNao.SIM))
                         {
                            ++quantidadeBemConcordado;
                            if (bem.getBemParticular().is(DomnSimNao.SIM))
                            {
                               valorBensAnteriorCasamentoConcordado += BemTributavelBe.getValorBemTributavel(bem, ignoraAvaliacao);
                            }
                            else
                            {
                               valorBensPosteriorCasamentoConcordado += BemTributavelBe.getValorBemTributavel(bem, ignoraAvaliacao);
                            }
                         }else {
                           if(bem.getBemParticular().is(DomnSimNao.SIM)){
                              valorBensAnteriorCasamentoConcordado += BemTributavelBe.getValorBemInformado(bem, ignoraAvaliacao);
                           }
                           else{
                              valorBensPosteriorCasamentoConcordado += BemTributavelBe.getValorBemInformado(bem, ignoraAvaliacao);
                           }
                        }                        
                      }
                      else
                      {
                         if (bem.getBemParticular().is(DomnSimNao.SIM))
                         {
                            valorBensAnteriorCasamentoConcordado += BemTributavelBe.getValorBemInformado(bem, ignoraAvaliacao);
                         }
                         else
                         {
                            valorBensPosteriorCasamentoConcordado += BemTributavelBe.getValorBemInformado(bem, ignoraAvaliacao);
                         }
                      }
                   }
                   else
                   {
                      if (bem.getBemParticular().is(DomnSimNao.SIM))
                      {
                         valorBensAnteriorCasamento += BemTributavelBe.getValorBemTributavel(bem, ignoraAvaliacao);
                      }
                      else
                      {
                         valorBensPosteriorCasamento += BemTributavelBe.getValorBemTributavel(bem, ignoraAvaliacao);
                      }
                      if (bem.getConcordaComValorArbitrado().is(DomnSimNao.SIM))
                      {
                         ++quantidadeBemConcordado;
                         if (bem.getBemParticular().is(DomnSimNao.SIM))
                         {
                            valorBensAnteriorCasamentoConcordado += BemTributavelBe.getValorBemTributavel(bem, ignoraAvaliacao);
                         }
                         else
                         {
                            valorBensPosteriorCasamentoConcordado += BemTributavelBe.getValorBemTributavel(bem, ignoraAvaliacao);
                         }
                      }
                      else
                      {
                         if (bem.getBemParticular().is(DomnSimNao.SIM))
                         {
                            valorBensAnteriorCasamentoConcordado += BemTributavelBe.getValorBemInformado(bem, ignoraAvaliacao);
                         }
                         else
                         {
                            valorBensPosteriorCasamentoConcordado += BemTributavelBe.getValorBemInformado(bem, ignoraAvaliacao);
                         }
                      }
                   }
                }else{
                   BensComIsencaoPrevista++;
                }
             }             
             giaITCDInventarioArrolamentoVo.setValorTotalArbitrado(valorBensAnteriorCasamento + valorBensPosteriorCasamento);
             valorTotalBensArbritradao = NumeroUtil.arredondaNumero(valorBensAnteriorCasamento + valorBensPosteriorCasamento);
             valorTotalBensConcordado = NumeroUtil.arredondaNumero(valorBensAnteriorCasamentoConcordado + valorBensPosteriorCasamentoConcordado);
             
             if(valorTotalBensArbritradao == 0 && valorTotalBensConcordado == 0){
               // caso os valores sejam zero ele seta o valor percentual para zero para ir para o protocolo manual e não ter erro de divisão por zero
                valorPercentual = 0;
             }else{
             
                if(valorTotalBensArbritradao == 0){
                   valorTotalBensArbritradao = valorTotalBensConcordado;                
                }
                
                valorPercentual = NumeroUtil.arredondaNumero((valorTotalBensConcordado / valorTotalBensArbritradao) * 100);
             }
             
             if ((valorPercentual >= giaITCDInventarioArrolamentoVo.getPorcentagemAconsiderar() || quantidadeBemConcordado == giaITCDInventarioArrolamentoVo.getBemTributavelVo().getCollVO().size()) && BensComIsencaoPrevista == 0)
             {
                giaITCDInventarioArrolamentoVo.setTipoProtocoloGIA(new DomnTipoProtocolo(DomnTipoProtocolo.PROTOCOLO_AUTOMATICO));
                giaITCDInventarioArrolamentoVo.setValorTotalBensDeclaradosAnteriorCasamento(valorBensAnteriorCasamentoConcordado);
                giaITCDInventarioArrolamentoVo.setValorTotalBensDeclaradosPosteriorCasamento(valorBensPosteriorCasamentoConcordado);
                if (giaITCDInventarioArrolamentoVo.isEstadoCivilCasado() || giaITCDInventarioArrolamentoVo.isEstadoCivilConvivente())
                {
                   if (giaITCDInventarioArrolamentoVo.getRegimeCasamento().is(DomnRegimeCasamento.SEPARACAO_TOTAL_DE_BENS))
                   {
                      giaITCDInventarioArrolamentoVo.setValorBaseCalculoTributavel(valorBensAnteriorCasamentoConcordado + valorBensPosteriorCasamentoConcordado);
                   }
                   else
                   {
                      giaITCDInventarioArrolamentoVo.setValorBaseCalculoTributavel((valorBensAnteriorCasamentoConcordado + valorBensPosteriorCasamentoConcordado) / 2);
                   }
                }
                else
                {
                   giaITCDInventarioArrolamentoVo.setValorBaseCalculoTributavel(valorBensAnteriorCasamentoConcordado + valorBensPosteriorCasamentoConcordado);
                }
             }
             else
             {               
                giaITCDInventarioArrolamentoVo.setTipoProtocoloGIA(new DomnTipoProtocolo(DomnTipoProtocolo.PROTOCOLO_MANUAL));
                giaITCDInventarioArrolamentoVo.setValorTotalBensDeclaradosAnteriorCasamento(valorBensAnteriorCasamento);
                giaITCDInventarioArrolamentoVo.setValorTotalBensDeclaradosPosteriorCasamento(valorBensPosteriorCasamento);
                if (giaITCDInventarioArrolamentoVo.isEstadoCivilCasado() || giaITCDInventarioArrolamentoVo.isEstadoCivilConvivente())
                {
                   if (giaITCDInventarioArrolamentoVo.getRegimeCasamento().is(DomnRegimeCasamento.SEPARACAO_TOTAL_DE_BENS))
                   {
                      giaITCDInventarioArrolamentoVo.setValorBaseCalculoTributavel(valorBensAnteriorCasamento + valorBensPosteriorCasamento);
                   }
                   else
                   {
                      giaITCDInventarioArrolamentoVo.setValorBaseCalculoTributavel(valorBensAnteriorCasamento + valorBensPosteriorCasamento / 2);
                   }
                }
                else
                {
                   giaITCDInventarioArrolamentoVo.setValorBaseCalculoTributavel(valorBensAnteriorCasamento + valorBensPosteriorCasamento);
                }
             }
          }
          else
          {
             for (Iterator iteBem = giaITCDInventarioArrolamentoVo.getBemTributavelVo().getCollVO().iterator(); iteBem.hasNext(); )
             {
                BemTributavelVo bem = (BemTributavelVo) iteBem.next();
                if (bem.getIsencaoPrevistaFormatada().is(DomnSimNao.NAO))
                {
                   if (bem.getBemVo().getClassificacaoTipoBem().is(DomnTipoBem.OUTROS_BENS))
                   {
                      if (giaITCDInventarioArrolamentoVo.getUfAbertura().getSiglUf().equals(ConfiguracaoITCD.SIGLA_ESTADO_MATO_GROSSO))
                      {
                         if (bem.getBemParticular().is(DomnSimNao.SIM))
                         {
                            valorBensAnteriorCasamento += BemTributavelBe.getValorBemTributavel(bem, ignoraAvaliacao);
                         }
                         else
                         {
                            valorBensPosteriorCasamento += BemTributavelBe.getValorBemTributavel(bem, ignoraAvaliacao);
                         }
                      }
                   }
                   else
                   {
                      if (bem.getBemParticular().is(DomnSimNao.SIM))
                      {
                         valorBensAnteriorCasamento += BemTributavelBe.getValorBemTributavel(bem, ignoraAvaliacao);
                      }
                      else
                      {
                         valorBensPosteriorCasamento += BemTributavelBe.getValorBemTributavel(bem, ignoraAvaliacao);
                      }
                   }
                }
             }
             giaITCDInventarioArrolamentoVo.setValorTotalBensDeclaradosAnteriorCasamento(valorBensAnteriorCasamento);
             giaITCDInventarioArrolamentoVo.setValorTotalBensDeclaradosPosteriorCasamento(valorBensPosteriorCasamento);
             if (giaITCDInventarioArrolamentoVo.isEstadoCivilCasado() || giaITCDInventarioArrolamentoVo.isEstadoCivilConvivente())
             {
                if (giaITCDInventarioArrolamentoVo.getRegimeCasamento().is(DomnRegimeCasamento.SEPARACAO_TOTAL_DE_BENS))
                {
                   giaITCDInventarioArrolamentoVo.setValorBaseCalculoTributavel(valorBensAnteriorCasamento + valorBensPosteriorCasamento);
                }
                else
                {
                   giaITCDInventarioArrolamentoVo.setValorBaseCalculoTributavel(valorBensAnteriorCasamento + valorBensPosteriorCasamento / 2);
                }
             }
             else
             {
                giaITCDInventarioArrolamentoVo.setValorBaseCalculoTributavel(valorBensAnteriorCasamento + valorBensPosteriorCasamento);
             }
          }
       }
    }

    /**
     * Método responsável por realizar a chamada ao método que obtém a base de cálculo tributável, e
     * calcula valor total dos bens, a título informativo.
     * @param giaITCDInventarioArrolamentoVo
     * @throws ObjetoObrigatorioException
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    public static void calculaValorTotalBensTributaveis(final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ObjetoObrigatorioException
    {    
       calculaBaseCalculoTributavel(giaITCDInventarioArrolamentoVo, true);
       giaITCDInventarioArrolamentoVo.setValorTotalInformadoBensDeclarados(giaITCDInventarioArrolamentoVo.getValorTotalBensDeclaradosAnteriorCasamento() + giaITCDInventarioArrolamentoVo.getValorTotalBensDeclaradosPosteriorCasamento());
       calculaBaseCalculoTributavel(giaITCDInventarioArrolamentoVo, false);
       giaITCDInventarioArrolamentoVo.setValorTotalBensDeclarados(giaITCDInventarioArrolamentoVo.getValorTotalBensDeclaradosAnteriorCasamento() + giaITCDInventarioArrolamentoVo.getValorTotalBensDeclaradosPosteriorCasamento());
    }

    /**
     * Método responsável por configurar o Meeiro como beneficiário.
     * @param giaITCDInventarioArrolamentoVo
     * @throws ObjetoObrigatorioException
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    public static void configurarMeeiroComoBeneficiario(final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ObjetoObrigatorioException
    {
       Validador.validaObjeto(giaITCDInventarioArrolamentoVo);
       exibeMeeiroBeneficiario(giaITCDInventarioArrolamentoVo);
       if (giaITCDInventarioArrolamentoVo.isExibeMeeiroBeneficiario())
       {
          if (giaITCDInventarioArrolamentoVo.getFlagMeeiroBeneficiario().is(DomnSimNao.SIM))
          {
             giaITCDInventarioArrolamentoVo.getMeeiroBeneficiario().setPessoaBeneficiaria(giaITCDInventarioArrolamentoVo.getPessoaMeeiro());
             giaITCDInventarioArrolamentoVo.getMeeiroBeneficiario().setFlagBeneficiarioMeeiro(new DomnSimNao(DomnSimNao.SIM));
          }
       }
    }

    /**
     * Método responsável por configurar a visualização da opção de seleção de Meeiro como beneficiário, 
     * bem como limpar o atributo meeiroBeneficiário.
     * @param giaITCDInventarioArrolamentoVo
     * @throws ObjetoObrigatorioException
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    public static void exibeMeeiroBeneficiario(final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ObjetoObrigatorioException
    {
       Validador.validaObjeto(giaITCDInventarioArrolamentoVo);
       if (giaITCDInventarioArrolamentoVo.getEstadoCivilDeCujus().is(DomnEstadoCivil.CASADO) || giaITCDInventarioArrolamentoVo.getEstadoCivilDeCujus().is(DomnEstadoCivil.CONVIVENTE))
       {
          if (giaITCDInventarioArrolamentoVo.getRegimeCasamento().is(DomnRegimeCasamento.COMUNHAO_PARCIAL_DE_BENS))
          {
             if (giaITCDInventarioArrolamentoVo.getBemTributavelVo().isExisteBemParticular())
             {
                giaITCDInventarioArrolamentoVo.setExibeMeeiroBeneficiario(true);
                giaITCDInventarioArrolamentoVo.setFlagMeeiroBeneficiario(new DomnSimNao(DomnSimNao.SIM));
             }
             else if (!giaITCDInventarioArrolamentoVo.isExisteHerdeiros())
             {
                giaITCDInventarioArrolamentoVo.setExibeMeeiroBeneficiario(true);
                giaITCDInventarioArrolamentoVo.setFlagMeeiroBeneficiario(new DomnSimNao(DomnSimNao.SIM));
             }
             else if (giaITCDInventarioArrolamentoVo.isExisteHerdeirosAscendentes())
             {
                giaITCDInventarioArrolamentoVo.setExibeMeeiroBeneficiario(true);
                giaITCDInventarioArrolamentoVo.setFlagMeeiroBeneficiario(new DomnSimNao(DomnSimNao.SIM));
             }
             else
             {
                giaITCDInventarioArrolamentoVo.setExibeMeeiroBeneficiario(false);
                giaITCDInventarioArrolamentoVo.setFlagMeeiroBeneficiario(new DomnSimNao(DomnSimNao.NAO));
                giaITCDInventarioArrolamentoVo.setMeeiroBeneficiario(null);
             }
          }
          else if (giaITCDInventarioArrolamentoVo.getRegimeCasamento().is(DomnRegimeCasamento.SEPARACAO_TOTAL_DE_BENS))
          {
             if (giaITCDInventarioArrolamentoVo.getHerdeirosDescendentes().is(DomnSimNao.NAO))
             {
                giaITCDInventarioArrolamentoVo.setExibeMeeiroBeneficiario(true);
                giaITCDInventarioArrolamentoVo.setFlagMeeiroBeneficiario(new DomnSimNao(DomnSimNao.SIM));
             }
             else
             {
                giaITCDInventarioArrolamentoVo.setExibeMeeiroBeneficiario(false);
                giaITCDInventarioArrolamentoVo.setFlagMeeiroBeneficiario(new DomnSimNao(DomnSimNao.NAO));
                giaITCDInventarioArrolamentoVo.setMeeiroBeneficiario(null);
             }
          }
          else if (giaITCDInventarioArrolamentoVo.getRegimeCasamento().is(DomnRegimeCasamento.COMUNHAO_UNIVERSAL_DE_BENS))
          {
             if (giaITCDInventarioArrolamentoVo.getHerdeirosDescendentes().is(DomnSimNao.NAO))
             {
                giaITCDInventarioArrolamentoVo.setExibeMeeiroBeneficiario(true);
                giaITCDInventarioArrolamentoVo.setFlagMeeiroBeneficiario(new DomnSimNao(DomnSimNao.SIM));
             }
             else
             {
                giaITCDInventarioArrolamentoVo.setExibeMeeiroBeneficiario(false);
                giaITCDInventarioArrolamentoVo.setFlagMeeiroBeneficiario(new DomnSimNao(DomnSimNao.NAO));
                giaITCDInventarioArrolamentoVo.setMeeiroBeneficiario(null);
             }
          }
          else if (!giaITCDInventarioArrolamentoVo.isExisteHerdeiros())
          {
             giaITCDInventarioArrolamentoVo.setExibeMeeiroBeneficiario(true);
          }
          else
          {
             giaITCDInventarioArrolamentoVo.setExibeMeeiroBeneficiario(false);
             giaITCDInventarioArrolamentoVo.setFlagMeeiroBeneficiario(new DomnSimNao(DomnSimNao.NAO));
             giaITCDInventarioArrolamentoVo.setMeeiroBeneficiario(null);
          }
       }
       else
       {
          giaITCDInventarioArrolamentoVo.setExibeMeeiroBeneficiario(false);
          giaITCDInventarioArrolamentoVo.setFlagMeeiroBeneficiario(new DomnSimNao(DomnSimNao.NAO));
          giaITCDInventarioArrolamentoVo.setMeeiroBeneficiario(null);
       }
    }

    /**
     * Método responsável por limpar a lista de beneficiários, caso na aba Dados Gerais, seja alterado a opção dos selects de forma a 
     * não se ter mais beneficiários.
     * @param giaITCDInventarioArrolamentoVo
     * @throws ObjetoObrigatorioException
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    public static void existeBeneficiario(final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ObjetoObrigatorioException
    {
       Validador.validaObjeto(giaITCDInventarioArrolamentoVo);
       if (!Validador.isNumericoValido(giaITCDInventarioArrolamentoVo.getNumeroHerdeiros()))
       {
          giaITCDInventarioArrolamentoVo.getBeneficiarioVo().setCollVO(null);
       }
       else if (Validador.isCollectionValida(giaITCDInventarioArrolamentoVo.getBeneficiarioVo().getCollVO()))
       {
          if (giaITCDInventarioArrolamentoVo.getNumeroHerdeiros() < giaITCDInventarioArrolamentoVo.getBeneficiarioVo().getCollVO().size())
          {
             giaITCDInventarioArrolamentoVo.getBeneficiarioVo().setCollVO(null);
          }
       }
    }

    /**
     * Método responsável por direcionar de acordo com estado civil e regime de casamento, qual forma de cálculo de divisão de bens 
     * será utilizada, inserindo o valor respectivo a cada beneficiário constante na lista de beneficiários.
     * @param giaITCDInventarioArrolamentoVo
     * @throws ObjetoObrigatorioException
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    public static void calculaValorRecebidoBeneficiario(final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ObjetoObrigatorioException
    {
       Validador.validaObjeto(giaITCDInventarioArrolamentoVo);
       if (Validador.isNumericoValido(giaITCDInventarioArrolamentoVo.getNumeroHerdeiros()))
       {
          if (Validador.isCollectionValida(giaITCDInventarioArrolamentoVo.getBeneficiarioVo().getCollVO()))
          {
             Iterator it = giaITCDInventarioArrolamentoVo.getBeneficiarioVo().getCollVO().iterator();
             while (it.hasNext())
             {
                GIAITCDInventarioArrolamentoBeneficiarioVo beneficiarioVo = (GIAITCDInventarioArrolamentoBeneficiarioVo) it.next();
                beneficiarioVo.setValorRecebidoAuxiliar(beneficiarioVo.getValorRecebido());
                switch (giaITCDInventarioArrolamentoVo.getEstadoCivilDeCujus().getValorCorrente())
                {
                   case DomnEstadoCivil.CASADO:
                      {
                         switch (giaITCDInventarioArrolamentoVo.getRegimeCasamento().getValorCorrente())
                         {
                            case DomnRegimeCasamento.COMUNHAO_PARCIAL_DE_BENS:
                               {
                                  beneficiarioVo.setValorRecebido(calculaValorAReceberBeneficiarioCasadoRegimeComunhaoParcialBens(giaITCDInventarioArrolamentoVo));
                                  break;
                               }
                            case DomnRegimeCasamento.COMUNHAO_UNIVERSAL_DE_BENS:
                               {
                                  beneficiarioVo.setValorRecebido(calculaValorAReceberBeneficiarioCasadoRegimeComunhaoUniversalBens(giaITCDInventarioArrolamentoVo));
                                  break;
                               }
                            case DomnRegimeCasamento.SEPARACAO_TOTAL_DE_BENS:
                               {
                                  beneficiarioVo.setValorRecebido(calculaValorAReceberBeneficiarioCasadoRegimeSeparacaoTotalBens(giaITCDInventarioArrolamentoVo));
                                  break;
                               }
                         }
                         break;
                      }
                   case DomnEstadoCivil.CONVIVENTE:
                      {
                         beneficiarioVo.setValorRecebido(calculaValorAReceberBeneficiarioCasadoRegimeComunhaoParcialBens(giaITCDInventarioArrolamentoVo));
                         break;
                      }
                   default:
                      {
                         beneficiarioVo.setValorRecebido(calculaValorAReceberBeneficiario(giaITCDInventarioArrolamentoVo));
                         break;
                      }
                }
             }
          }
       }
    }

    /**
     * Método responsável por direcionar de acordo com estado civil e regime de casamento, qual forma de cálculo de divisão de bens 
     * será utilizada para Meeiro Beneficiário (caso o mesmo exista), inserindo para o mesmo o valor respectivo.
     * @param giaITCDInventarioArrolamentoVo
     * @throws ObjetoObrigatorioException
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    public static void calculaValorRecebidoMeeiro(final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ObjetoObrigatorioException
    {
       Validador.validaObjeto(giaITCDInventarioArrolamentoVo);
       if (giaITCDInventarioArrolamentoVo.getFlagMeeiroBeneficiario().is(DomnSimNao.SIM))
       {
          if (Validador.isObjetoValido(giaITCDInventarioArrolamentoVo.getMeeiroBeneficiario().getPessoaBeneficiaria()))
          {
             switch (giaITCDInventarioArrolamentoVo.getEstadoCivilDeCujus().getValorCorrente())
             {
                case DomnEstadoCivil.CASADO:
                   {
                      switch (giaITCDInventarioArrolamentoVo.getRegimeCasamento().getValorCorrente())
                      {
                         case DomnRegimeCasamento.COMUNHAO_PARCIAL_DE_BENS:
                            {
                               giaITCDInventarioArrolamentoVo.getMeeiroBeneficiario().setValorRecebido(calculaValorAReceberMeeiroCasadoComunhaoParcialBens(giaITCDInventarioArrolamentoVo));
                               break;
                            }
                         case DomnRegimeCasamento.COMUNHAO_UNIVERSAL_DE_BENS:
                            {
                               giaITCDInventarioArrolamentoVo.getMeeiroBeneficiario().setValorRecebido(calculaValorAReceberMeeiroCasadoComunhaoUniversalBens(giaITCDInventarioArrolamentoVo));
                               break;
                            }
                         case DomnRegimeCasamento.SEPARACAO_TOTAL_DE_BENS:
                            {
                               giaITCDInventarioArrolamentoVo.getMeeiroBeneficiario().setValorRecebido(calculaValorAReceberMeeiroCasadoSeparacaoTotalBens(giaITCDInventarioArrolamentoVo));
                               break;
                            }
                      }
                      break;
                   }
                case DomnEstadoCivil.CONVIVENTE:
                   {
                      giaITCDInventarioArrolamentoVo.getMeeiroBeneficiario().setValorRecebido(calculaValorAReceberMeeiroCasadoComunhaoParcialBens(giaITCDInventarioArrolamentoVo));
                      break;
                   }
                default:
                   {
                      giaITCDInventarioArrolamentoVo.getMeeiroBeneficiario().setValorRecebido(calculaValorAReceberMeeiro(giaITCDInventarioArrolamentoVo));
                      break;
                   }
             }
          }
       }
    }

    /**
     * Método responsável por calcular a divisão de bens de acordo com o Estado Civil Casado ou Convivente dado Regime de Casamento
     *  Comunhão Parcial de Bens.
     * @param giaITCDInventarioArrolamentoVo
     * @throws ObjetoObrigatorioException
     * @return double
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    public static double calculaValorAReceberBeneficiarioCasadoRegimeComunhaoParcialBens(final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ObjetoObrigatorioException
    {
       Validador.validaObjeto(giaITCDInventarioArrolamentoVo);
       double valorBensAnteriorCasamento;
       double valorBensPosteriorCasamento;
       int quantidadeHerdeiros;
       boolean isMeeiroBeneficiario;
       valorBensAnteriorCasamento = Validador.isNumericoValido(giaITCDInventarioArrolamentoVo.getValorTotalBensDeclaradosAnteriorCasamento()) ? giaITCDInventarioArrolamentoVo.getValorTotalBensDeclaradosAnteriorCasamento() : 0;
       valorBensPosteriorCasamento = Validador.isNumericoValido(giaITCDInventarioArrolamentoVo.getValorTotalBensDeclaradosPosteriorCasamento()) ? giaITCDInventarioArrolamentoVo.getValorTotalBensDeclaradosPosteriorCasamento() / 2 : 0;
       isMeeiroBeneficiario = giaITCDInventarioArrolamentoVo.getFlagMeeiroBeneficiario().is(DomnSimNao.SIM);
       quantidadeHerdeiros = giaITCDInventarioArrolamentoVo.getNumeroHerdeiros();
       if (Validador.isNumericoValido(quantidadeHerdeiros))
       {
          if (Validador.isNumericoValido(valorBensAnteriorCasamento))
          {
             if (giaITCDInventarioArrolamentoVo.getHerdeirosAscendentes().is(DomnSimNao.SIM) || isMeeiroBeneficiario)
             {
                valorBensAnteriorCasamento = valorBensAnteriorCasamento / (quantidadeHerdeiros + 1);
             }
             else
             {
                valorBensAnteriorCasamento = valorBensAnteriorCasamento / (quantidadeHerdeiros);
             }
          }
          if (Validador.isNumericoValido(valorBensPosteriorCasamento))
          {
             if (giaITCDInventarioArrolamentoVo.getHerdeirosAscendentes().is(DomnSimNao.SIM))
             {
                valorBensPosteriorCasamento = valorBensPosteriorCasamento / (quantidadeHerdeiros + 1);
             }
             else
             {
                valorBensPosteriorCasamento = valorBensPosteriorCasamento / quantidadeHerdeiros;
             }
          }
          return valorBensAnteriorCasamento + valorBensPosteriorCasamento;
       }
       return 0;
    }

    /**
     * Método responsável por calcular a divisão de bens de acordo com o Estado Civil Casado dado Regime de Casamento
     *  Comunhão Universal de Bens.
     * @param giaITCDInventarioArrolamentoVo
     * @throws ObjetoObrigatorioException
     * @return double
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    public static double calculaValorAReceberBeneficiarioCasadoRegimeComunhaoUniversalBens(final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ObjetoObrigatorioException
    {
       Validador.validaObjeto(giaITCDInventarioArrolamentoVo);
       double valorBensAnteriorCasamento;
       double valorBensPosteriorCasamento;
       int quantidadeHerdeiros;
       boolean isMeeiroBeneficiario;
       valorBensAnteriorCasamento = Validador.isNumericoValido(giaITCDInventarioArrolamentoVo.getValorTotalBensDeclaradosAnteriorCasamento()) ? giaITCDInventarioArrolamentoVo.getValorTotalBensDeclaradosAnteriorCasamento() : 0;
       valorBensPosteriorCasamento = Validador.isNumericoValido(giaITCDInventarioArrolamentoVo.getValorTotalBensDeclaradosPosteriorCasamento()) ? giaITCDInventarioArrolamentoVo.getValorTotalBensDeclaradosPosteriorCasamento() / 2 : 0;
       isMeeiroBeneficiario = giaITCDInventarioArrolamentoVo.getFlagMeeiroBeneficiario().is(DomnSimNao.SIM);
       quantidadeHerdeiros = giaITCDInventarioArrolamentoVo.getNumeroHerdeiros();
       if (Validador.isNumericoValido(quantidadeHerdeiros))
       {
          if (giaITCDInventarioArrolamentoVo.getHerdeirosAscendentes().is(DomnSimNao.SIM))
          {
             return (valorBensAnteriorCasamento + valorBensPosteriorCasamento) / (quantidadeHerdeiros + 1);
          }
          else
          {
             return (valorBensAnteriorCasamento + valorBensPosteriorCasamento) / quantidadeHerdeiros;
          }
       }
       return 0;
    }

    /**
     * Método responsável por calcular a divisão de bens de acordo com o Estado Civil Casado dado Regime de Casamento
     *  Separação Total de Bens.
     * @param giaITCDInventarioArrolamentoVo
     * @throws ObjetoObrigatorioException
     * @return double
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    public static double calculaValorAReceberBeneficiarioCasadoRegimeSeparacaoTotalBens(final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ObjetoObrigatorioException
    {
       Validador.validaObjeto(giaITCDInventarioArrolamentoVo);
       double valorBensAnteriorCasamento;
       double valorBensPosteriorCasamento;
       int quantidadeHerdeiros;
       boolean isMeeiroBeneficiario;
       valorBensAnteriorCasamento = Validador.isNumericoValido(giaITCDInventarioArrolamentoVo.getValorTotalBensDeclaradosAnteriorCasamento()) ? giaITCDInventarioArrolamentoVo.getValorTotalBensDeclaradosAnteriorCasamento() : 0;
       valorBensPosteriorCasamento = Validador.isNumericoValido(giaITCDInventarioArrolamentoVo.getValorTotalBensDeclaradosPosteriorCasamento()) ? giaITCDInventarioArrolamentoVo.getValorTotalBensDeclaradosPosteriorCasamento() : 0;
       isMeeiroBeneficiario = giaITCDInventarioArrolamentoVo.getFlagMeeiroBeneficiario().is(DomnSimNao.SIM);
       quantidadeHerdeiros = giaITCDInventarioArrolamentoVo.getNumeroHerdeiros();
       if (Validador.isNumericoValido(quantidadeHerdeiros))
       {
          if (giaITCDInventarioArrolamentoVo.getHerdeirosAscendentes().is(DomnSimNao.SIM))
          {
             return (valorBensAnteriorCasamento + valorBensPosteriorCasamento) / (quantidadeHerdeiros + 1);
          }
          else
          {
             return (valorBensAnteriorCasamento + valorBensPosteriorCasamento) / quantidadeHerdeiros;
          }
       }
       return 0;
    }

    /**
     * Método responsável por calcular a divisão de bens de acordo com o Estado Civil diferente de Casado ou Convivente.
     * @param giaITCDInventarioArrolamentoVo
     * @throws ObjetoObrigatorioException
     * @return double
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    public static double calculaValorAReceberBeneficiario(final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ObjetoObrigatorioException
    {
       Validador.validaObjeto(giaITCDInventarioArrolamentoVo);
       double valorBensAnteriorCasamento;
       double valorBensPosteriorCasamento;
       int quantidadeHerdeiros;
       boolean isMeeiroBeneficiario;
       valorBensAnteriorCasamento = Validador.isNumericoValido(giaITCDInventarioArrolamentoVo.getValorTotalBensDeclaradosAnteriorCasamento()) ? giaITCDInventarioArrolamentoVo.getValorTotalBensDeclaradosAnteriorCasamento() : 0;
       valorBensPosteriorCasamento = Validador.isNumericoValido(giaITCDInventarioArrolamentoVo.getValorTotalBensDeclaradosPosteriorCasamento()) ? giaITCDInventarioArrolamentoVo.getValorTotalBensDeclaradosPosteriorCasamento() : 0;
       isMeeiroBeneficiario = giaITCDInventarioArrolamentoVo.getFlagMeeiroBeneficiario().is(DomnSimNao.SIM);
       quantidadeHerdeiros = giaITCDInventarioArrolamentoVo.getNumeroHerdeiros();
       if (Validador.isNumericoValido(quantidadeHerdeiros))
       {
          return (valorBensAnteriorCasamento + valorBensPosteriorCasamento) / quantidadeHerdeiros;
       }
       return 0;
    }

    /**
     * Método responsável por calcular a divisão de bens para Meeiro de acordo com o Estado Civil Casado ou Convivente dado Regime de Casamento
     *  Comunhão Parcial de Bens.
     * @param giaITCDInventarioArrolamentoVo
     * @throws ObjetoObrigatorioException
     * @return double
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    public static double calculaValorAReceberMeeiroCasadoComunhaoParcialBens(final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ObjetoObrigatorioException
    {
       Validador.validaObjeto(giaITCDInventarioArrolamentoVo);
       double valorBensAnteriorCasamento;
       double valorBensPosteriorCasamento;
       int quantidadeHerdeiros;
       valorBensAnteriorCasamento = Validador.isNumericoValido(giaITCDInventarioArrolamentoVo.getValorTotalBensDeclaradosAnteriorCasamento()) ? giaITCDInventarioArrolamentoVo.getValorTotalBensDeclaradosAnteriorCasamento() : 0;
       valorBensPosteriorCasamento = Validador.isNumericoValido(giaITCDInventarioArrolamentoVo.getValorTotalBensDeclaradosPosteriorCasamento()) ? giaITCDInventarioArrolamentoVo.getValorTotalBensDeclaradosPosteriorCasamento() / 2 : 0;
       quantidadeHerdeiros = giaITCDInventarioArrolamentoVo.getNumeroHerdeiros();
       if (Validador.isNumericoValido(quantidadeHerdeiros))
       {
          if (Validador.isNumericoValido(valorBensAnteriorCasamento))
          {
             valorBensAnteriorCasamento = valorBensAnteriorCasamento / (quantidadeHerdeiros + 1);
          }
       }
       if (giaITCDInventarioArrolamentoVo.getHerdeirosDescendentes().is(DomnSimNao.SIM))
       {
          return valorBensAnteriorCasamento;
       }
       else if (giaITCDInventarioArrolamentoVo.getHerdeirosAscendentes().is(DomnSimNao.SIM))
       {
          if (Validador.isNumericoValido(valorBensPosteriorCasamento))
          {
             valorBensPosteriorCasamento = valorBensPosteriorCasamento / (quantidadeHerdeiros + 1);
          }
          return valorBensAnteriorCasamento + valorBensPosteriorCasamento;
       }
       else
       {
          return valorBensAnteriorCasamento + valorBensPosteriorCasamento;
       }
    }

    /**
     * Método responsável por calcular a divisão de bens para Meeiro de acordo com o Estado Civil Casado dado Regime de Casamento
     *  Comunhão Universal de Bens. 
     * @param giaITCDInventarioArrolamentoVo
     * @throws ObjetoObrigatorioException
     * @return double
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    public static double calculaValorAReceberMeeiroCasadoComunhaoUniversalBens(final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ObjetoObrigatorioException
    {
       Validador.validaObjeto(giaITCDInventarioArrolamentoVo);
       double valorBensAnteriorCasamento;
       double valorBensPosteriorCasamento;
       int quantidadeHerdeiros;
       valorBensAnteriorCasamento = Validador.isNumericoValido(giaITCDInventarioArrolamentoVo.getValorTotalBensDeclaradosAnteriorCasamento()) ? giaITCDInventarioArrolamentoVo.getValorTotalBensDeclaradosAnteriorCasamento() : 0;
       valorBensPosteriorCasamento = Validador.isNumericoValido(giaITCDInventarioArrolamentoVo.getValorTotalBensDeclaradosPosteriorCasamento()) ? giaITCDInventarioArrolamentoVo.getValorTotalBensDeclaradosPosteriorCasamento() / 2 : 0;
       quantidadeHerdeiros = giaITCDInventarioArrolamentoVo.getNumeroHerdeiros();
       if (Validador.isNumericoValido(quantidadeHerdeiros))
       {
          if (giaITCDInventarioArrolamentoVo.getHerdeirosAscendentes().is(DomnSimNao.SIM))
          {
             return (valorBensAnteriorCasamento + valorBensPosteriorCasamento) / (quantidadeHerdeiros + 1);
          }
          else
          {
             return (valorBensAnteriorCasamento + valorBensPosteriorCasamento) / (quantidadeHerdeiros);
          }
       }
       return valorBensAnteriorCasamento + valorBensPosteriorCasamento;
    }

    /**
     * Método responsável por calcular a divisão de bens para Meeiro de acordo com o Estado Civil Casado dado Regime de Casamento
     *  Separação Total de Bens.
     * @param giaITCDInventarioArrolamentoVo
     * @throws ObjetoObrigatorioException
     * @return double
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    public static double calculaValorAReceberMeeiroCasadoSeparacaoTotalBens(final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ObjetoObrigatorioException
    {
       Validador.validaObjeto(giaITCDInventarioArrolamentoVo);
       double valorBensAnteriorCasamento;
       double valorBensPosteriorCasamento;
       int quantidadeHerdeiros;
       valorBensAnteriorCasamento = Validador.isNumericoValido(giaITCDInventarioArrolamentoVo.getValorTotalBensDeclaradosAnteriorCasamento()) ? giaITCDInventarioArrolamentoVo.getValorTotalBensDeclaradosAnteriorCasamento() : 0;
       valorBensPosteriorCasamento = Validador.isNumericoValido(giaITCDInventarioArrolamentoVo.getValorTotalBensDeclaradosPosteriorCasamento()) ? giaITCDInventarioArrolamentoVo.getValorTotalBensDeclaradosPosteriorCasamento() : 0;
       quantidadeHerdeiros = giaITCDInventarioArrolamentoVo.getNumeroHerdeiros();
       if (Validador.isNumericoValido(quantidadeHerdeiros))
       {
          if (giaITCDInventarioArrolamentoVo.getHerdeirosAscendentes().is(DomnSimNao.SIM))
          {
             return (valorBensAnteriorCasamento + valorBensPosteriorCasamento) / (quantidadeHerdeiros + 1);
          }
          else
          {
             return 0;
          }
       }
       return valorBensAnteriorCasamento + valorBensPosteriorCasamento;
    }

    /**
     * Método responsável por calcular a divisão de bens para Meeiro de acordo com o Estado Civil diferente Casado ou Convivente.
     * @param giaITCDInventarioArrolamentoVo
     * @throws ObjetoObrigatorioException
     * @return double
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    public static double calculaValorAReceberMeeiro(final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ObjetoObrigatorioException
    {
       Validador.validaObjeto(giaITCDInventarioArrolamentoVo);
       return 0;
    }

    /**
     * Método responsável por solicitar a atualização da aba Bens Tributáveis, dado possíveis alterações na Aba Dados Gerais que implicam na regra 
     * da aba Bens Tributáveis.
     * @param giaITCDInventarioArrolamentoVo
     * @throws ObjetoObrigatorioException
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    public static void atualizaAbaBensTributaveis(final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ObjetoObrigatorioException
    {
       Validador.validaObjeto(giaITCDInventarioArrolamentoVo);
       if (Validador.isCollectionValida(giaITCDInventarioArrolamentoVo.getBemTributavelVo().getCollVO()))
       {
          if (!giaITCDInventarioArrolamentoVo.getEstadoCivilDeCujus().is(DomnEstadoCivil.CASADO))
          {
             if (!giaITCDInventarioArrolamentoVo.getEstadoCivilDeCujus().is(DomnEstadoCivil.CONVIVENTE))
             {
                BemTributavelBe.ocultaOpcaoBemParticular(giaITCDInventarioArrolamentoVo.getBemTributavelVo());
             }
          }
          else if (!giaITCDInventarioArrolamentoVo.getRegimeCasamento().is(DomnRegimeCasamento.COMUNHAO_PARCIAL_DE_BENS))
          {
             BemTributavelBe.ocultaOpcaoBemParticular(giaITCDInventarioArrolamentoVo.getBemTributavelVo());
          }
       }
       calculaValorTotalBensTributaveis(giaITCDInventarioArrolamentoVo);
    }

    /**
     * Método reponsável por solicitar a atualização da aba Beneficiários, dado possíveis alterações na Aba Dados Gerais e Aba Bens Tributáveis que 
     * implicam na regra da aba Beneficiários.
     * @param giaITCDInventarioArrolamentoVo
     * @throws ObjetoObrigatorioException
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    public static void atualizaAbaBeneficiarios(final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ObjetoObrigatorioException
    {
       Validador.validaObjeto(giaITCDInventarioArrolamentoVo);
       configurarMeeiroComoBeneficiario(giaITCDInventarioArrolamentoVo);
       existeBeneficiario(giaITCDInventarioArrolamentoVo);
       calculaValorRecebidoBeneficiario(giaITCDInventarioArrolamentoVo);
       calculaValorRecebidoMeeiro(giaITCDInventarioArrolamentoVo);
    }

    /**
     * Método responsável por solicitar a atualização das abas Bens Tributáveis e Beneficiários.
     * @param giaITCDInventarioArrolamentoVo
     * @throws ObjetoObrigatorioException
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    public static void atualizarAbasGiaITCD(final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ObjetoObrigatorioException
    {
       Validador.validaObjeto(giaITCDInventarioArrolamentoVo);
       atualizaAbaBensTributaveis(giaITCDInventarioArrolamentoVo);
       atualizaAbaBeneficiarios(giaITCDInventarioArrolamentoVo);
    }

    /**
     * Método responsável por 
     * @param giaITCDInventarioArrolamentoVo
     * @throws ObjetoObrigatorioException
     * @implemented by Ricardo Vitor de Oliveira Moraes
     */
    public static void restaurarQuantidadeBeneficiario(final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo) throws ObjetoObrigatorioException
    {
       Validador.validaObjeto(giaITCDInventarioArrolamentoVo);
       if (giaITCDInventarioArrolamentoVo.getHerdeirosDescendentes().is(DomnSimNao.SIM))
       {
          giaITCDInventarioArrolamentoVo.setNumeroHerdeirosDescendentes(giaITCDInventarioArrolamentoVo.getNumeroHerdeiros());
       }
       else if (giaITCDInventarioArrolamentoVo.getHerdeirosAscendentes().is(DomnSimNao.SIM))
       {
          giaITCDInventarioArrolamentoVo.setNumeroHerdeirosAscendentes(giaITCDInventarioArrolamentoVo.getNumeroHerdeiros());
       }
       else
       {
          giaITCDInventarioArrolamentoVo.setNumeroOutrosHerdeiros(giaITCDInventarioArrolamentoVo.getNumeroHerdeiros());
       }
    }

    public static void validarIncluirServidorAvaliacao(final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo, final ServidorSefazIntegracaoVo servidorVo) throws ObjetoObrigatorioException, 
             RegistroNaoPodeSerUtilizadoException
    {
       Validador.validaObjeto(giaITCDInventarioArrolamentoVo);
       Validador.validaObjeto(servidorVo);
       if (servidorVo.getNumrCPF().toString().equals(giaITCDInventarioArrolamentoVo.getResponsavelVo().getNumrDocumento()))
       {
          throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.SERVIDOR_PARTICIPANTE_NAO_PODE_SER_AVALIADOR);
       }
       if (servidorVo.getNumrCPF().toString().equals(giaITCDInventarioArrolamentoVo.getPessoaDeCujus().getNumrDocumento()))
       {
          throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.SERVIDOR_PARTICIPANTE_NAO_PODE_SER_AVALIADOR);
       }
       if (servidorVo.getNumrCPF().toString().equals(giaITCDInventarioArrolamentoVo.getPessoaMeeiro().getNumrDocumento()))
       {
          throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.SERVIDOR_PARTICIPANTE_NAO_PODE_SER_AVALIADOR);
       }
       if (servidorVo.getNumrCPF().toString().equals(giaITCDInventarioArrolamentoVo.getProcuradorVo().getNumrDocumento()))
       {
          throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.SERVIDOR_PARTICIPANTE_NAO_PODE_SER_AVALIADOR);
       }
       for (Iterator it = giaITCDInventarioArrolamentoVo.getBeneficiarioVo().getCollVO().iterator(); it.hasNext(); )
       {
          BeneficiarioVo beneficiarioAtual = (BeneficiarioVo) it.next();
          if (servidorVo.getNumrCPF().toString().equals(beneficiarioAtual.getPessoaBeneficiaria().getNumrDocumento()))
          {
             throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.SERVIDOR_PARTICIPANTE_NAO_PODE_SER_AVALIADOR);
          }
       }
    }

    public void atualizarValorRecebidoAposAvaliacao(final GIAITCDInventarioArrolamentoVo giaITCDInventarioArrolamentoVo)
    {
       List<BeneficiarioVo> beneficiarios = (List<BeneficiarioVo>) giaITCDInventarioArrolamentoVo.getBeneficiarioVo().getCollVO();
       BeneficiarioBe beneficiarioBe = new BeneficiarioBe(conn);
       if (giaITCDInventarioArrolamentoVo.getFlagMeeiroBeneficiario().is(DomnSimNao.SIM))
       {
          beneficiarios.add(giaITCDInventarioArrolamentoVo.getMeeiroBeneficiario());
       }
       for (BeneficiarioVo beneficiarioVo: beneficiarios)
       {
          try
          {
             beneficiarioVo.setLogSefazVo(giaITCDInventarioArrolamentoVo.getLogSefazVo());
             beneficiarioVo.setValorRecebidoAvaliacao(beneficiarioVo.getValorRecebido());
             beneficiarioVo.setValorRecebido(beneficiarioVo.getValorRecebidoAuxiliar());
             beneficiarioBe.alterarBeneficiario(beneficiarioVo);
          }
          catch (Exception e)
          {
             e.printStackTrace();
          }
       }
    }
    
 }
