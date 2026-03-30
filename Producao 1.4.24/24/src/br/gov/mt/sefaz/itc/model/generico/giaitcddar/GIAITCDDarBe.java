/**
 * Ábaco Tecnologia de Informação - LTDA
 * Arquivo: GIAITCDDarBe.java
 * Revisão: Leandro Dorileo
 * Data revisão: 17/03/2008
 * $Id: GIAITCDDarBe.java,v 1.5 2008/08/08 21:37:45 lucas.nascimento Exp $
 */
package br.gov.mt.sefaz.itc.model.generico.giaitcddar;

import br.com.abaco.log5.GenericoLogAnotacaoDao;
import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.LogException;
import br.com.abaco.log5.util.excecoes.PersistenciaException;
import br.com.abaco.sefaz.integracao.arrecadacao.DarEmitidoIntegracaoVo;
import br.com.abaco.sefaz.integracao.arrecadacao.DocumentoArrecadacaoBe;
import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ConexaoException;
import br.com.abaco.util.exceptions.ConsultaException;
import br.com.abaco.util.exceptions.IntegracaoException;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.exceptions.RegistroExistenteException;
import br.com.abaco.util.exceptions.RegistroNaoPodeSerUtilizadoException;

import br.gov.mt.sefaz.generico.util.sql.AbstractBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDBe;
import br.gov.mt.sefaz.itc.model.generico.giaitcd.GIAITCDVo;
import br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd.StatusGIAITCDBe;
import br.gov.mt.sefaz.itc.model.giaitcd.statusgiaitcd.StatusGIAITCDVo;
import br.gov.mt.sefaz.itc.util.dominio.DomnCodigoTributacaoITCD;
import br.gov.mt.sefaz.itc.util.dominio.DomnSimNao;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatDar;
import br.gov.mt.sefaz.itc.util.dominio.DomnStatusGIAITCD;
import br.gov.mt.sefaz.itc.util.facade.ConfiguracaoITCD;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.facade.MensagemSucesso;
import br.gov.mt.sefaz.itc.util.integracao.arrecadacao.DarQuitadoIntegracaoVo;

import br.gov.mt.sefaz.itc.util.log.ExibirLOG;

import java.io.IOException;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.Date;
import java.util.Iterator;


/**
 * Classe de negócio para a entidade GIA-ITCD Dar
 * @author Daniel Balieiro
 * @implemented by Daniel Balieiro
 */
public class GIAITCDDarBe extends AbstractBe
{

	/**
	 * Construtor genérico sem parâmetro.
	 *
	 * @throws SQLException
	 * @implemented by Leandro Dorileo
	 */
	public GIAITCDDarBe() throws SQLException
	{
		super();
	}

	/**
	 * Construtor recebendo uma conexao.
	 * @param connection conexão com o banco de dados
	 * @implemented by Leandro Dorileo
	 */
	public GIAITCDDarBe(Connection connection)
	{
		super(connection);
	}

	/**
	 * Inclui informações sobre um GIAITCDDar no banco de dados.
	 *
	 * @param giaITCDDarVo (Value Object).
	 * @throws ObjetoObrigatorioException Esta Exception deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws RegistroExistenteException Esta Exception deve ser lançada quando o sistema tenta INCLUIR um registro no banco de dados mas o registro já existe.
	 * @throws ParametroObrigatorioException Esta Exception deve ser lançada quando o usuário não informa um campo que é obrigatório para o sistema.
	 * @throws ConsultaException Esta Exception deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @throws ConexaoException Esta exceção deverá ser lançada para substibuir o SQLException na camada de negócio e na camada de apresentação.
	 * @implemented by Leandro DOrileo
	 */
	public synchronized void incluirGIAITCDDar(final GIAITCDDarVo giaITCDDarVo) throws ObjetoObrigatorioException, 
			  ConexaoException, IntegracaoException, ConsultaException, ParametroObrigatorioException, LogException, 
			  PersistenciaException, AnotacaoException, IOException
   {
		Validador.validaObjeto(giaITCDDarVo);
		try
		{
			try
			{
				synchronized (GIAITCDDarVo.class)
				{
					validaParametrosIncluirAlterar(giaITCDDarVo);
					GIAITCDDarVo giaITCDDarConsultaVo = new GIAITCDDarVo();
					giaITCDDarConsultaVo.getGia().setCodigo(giaITCDDarVo.getGia().getCodigo());
					giaITCDDarConsultaVo.getDarEmitido().setNumrDarSeqc(giaITCDDarVo.getDarEmitido().getNumrDarSeqc());
					giaITCDDarConsultaVo = new GIAITCDDarVo(giaITCDDarConsultaVo);
					consultarGIAITCDDar(giaITCDDarConsultaVo);
					if (!giaITCDDarConsultaVo.isExiste())
					{
						giaITCDDarVo.setSubstituido(new DomnSimNao(DomnSimNao.NAO));
					   giaITCDDarVo.setDataAtualizacao(new Date());
						incluir(giaITCDDarVo);
						commit();
						giaITCDDarVo.setMensagem(MensagemSucesso.INCLUIR);
					}
				}
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
			catch(ConexaoException e)
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
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
		}
	}

	/**
	 * Método utilizado para validar se o objeto do tipo GIAITCDDarVo possui todos os parâmetros obrigatórios.
	 *
	 * @param giaITCDDarVo (Value Object).
	 * @throws ObjetoObrigatorioException Esta Exception deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ParametroObrigatorioException Esta Exception deve ser lançada quando o usuário não informa um campo que é obrigatório para o sistema.
	 * @implemented by Daniel Balieiro
	 */
	private void validaParametrosIncluirAlterar(final GIAITCDDarVo giaITCDDarVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException, ConsultaException
	{
		Validador.validaObjeto(giaITCDDarVo);
		if (!Validador.isNumericoValido(giaITCDDarVo.getGia().getCodigo()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_VINCULAR_DAR_CODIGO_GIA);
		}
		if (!Validador.isNumericoValido(giaITCDDarVo.getDarEmitido().getNumrDarSeqc()))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_VINCULAR_DAR_NUMERO);
		}
//		if (giaITCDDarVo.getGia().getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.PARCELADO))
//		{
//			GIAITCDDarVo giaDarQuitado = obterDARQuitado(giaITCDDarVo);
//			if (Validador.isNumericoValido(giaDarQuitado.getCodigo()))
//			{
//				if ((giaITCDDarVo.getDarEmitido().getNumrParcela().intValue() % 100) == 0)
//				{
//					throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_VINCULAR_DAR_NUMERO_PARCELA_ZERO);
//				}
//				else if (!Validador.isNumericoValido((giaITCDDarVo.getDarEmitido().getNumrParcela().intValue() % 100)))
//				{
//					throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_VINCULAR_DAR_NUMERO_PARCELA);
//				}
//			}
//		}
//		validarGIAITCDDarVincularStatus(giaITCDDarVo);
	}

	/**
	 * Método de validações de Status para a Vinculação do DAR à GIA-ITCD
	 * @param giaDar
	 * @throws ParametroObrigatorioException
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @implemented by Daniel Balieiro
	 */
	private void validarGIAITCDDarVincularStatus(final GIAITCDDarVo giaDar) throws ParametroObrigatorioException, 
			  ObjetoObrigatorioException, ConsultaException
	{
		if (!giaDar.getGia().getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.PARCELADO) && 
				 !giaDar.getGia().getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.SEGUNDA_RETIFICACAO) && 
				 !giaDar.getGia().getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.RATIFICADO) && 
				 !giaDar.getGia().getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.PARA_INSCRICAO_EM_DIVIDA_ATIVA) &&
				 !giaDar.getGia().getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.RETIFICADO) &&
				 !giaDar.getGia().getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.NOTIFICADO) &&
				 !giaDar.getGia().getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.IMPUGNADO))
		{
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_VINCULAR_DAR_STATUS);
		}
//		if (!giaDar.getGia().getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.PARCELADO))
//		{
//			GIAITCDDarVo giaDarQuitado = obterDARQuitado(giaDar);
//			if (Validador.isNumericoValido(giaDarQuitado.getCodigo()))
//			{
//				throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_VINCULAR_DAR_PARCELADO);
//			}
//		}
		if (giaDar.getGia().getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.PARA_INSCRICAO_EM_DIVIDA_ATIVA))
		{
			StatusGIAITCDVo statusAnterior = 
						(new StatusGIAITCDBe(conn)).obterStatusGIAITCDAnterior(giaDar.getGia().getStatusVo());
			if (!statusAnterior.getStatusGIAITCD().is(DomnStatusGIAITCD.PARCELADO) && 
				 !statusAnterior.getStatusGIAITCD().is(DomnStatusGIAITCD.SEGUNDA_RETIFICACAO) && 
				 !statusAnterior.getStatusGIAITCD().is(DomnStatusGIAITCD.RATIFICADO)&&
				 !statusAnterior.getStatusGIAITCD().is(DomnStatusGIAITCD.IMPUGNADO))
			{
				throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_VINCULRA_DAR_INSCRICAO);
			}
		}
	}

	/**
	 * Obtem o DAR Quitado
	 * @param giaDar
	 * @return
	 * @throws ObjetoObrigatorioException
	 * @throws ConsultaException
	 * @implemented by Daniel Balieiro
	 */
	private GIAITCDDarVo obterDARQuitado(final GIAITCDDarVo giaDar) throws ObjetoObrigatorioException, ConsultaException, 
			  IntegracaoException
	{
		GIAITCDDarVo giaDarConsulta = new GIAITCDDarVo();
		giaDarConsulta.getGia().setCodigo(giaDar.getGia().getCodigo());
		giaDarConsulta = new GIAITCDDarVo(giaDarConsulta);
		giaDarConsulta = listarGIAITCDDar(giaDarConsulta);
		for (Iterator iteDar = giaDarConsulta.getCollVO().iterator(); iteDar.hasNext(); )
		{
			GIAITCDDarVo giaDarAtual = (GIAITCDDarVo) iteDar.next();
			if (giaDarAtual.getDarEmitido().getStatDar().is(DomnStatDar.QUITADO))
			{
				return giaDarAtual;
			}
		}
		return giaDarConsulta;
	}
	
	private DarEmitidoIntegracaoVo validaDarExistenteArrecadacao(final GIAITCDDarVo giaDar) throws ObjetoObrigatorioException, 
			  IntegracaoException, ParametroObrigatorioException
	{
		Validador.validaObjeto(giaDar);
	   DarEmitidoIntegracaoVo darConsulta = new DarEmitidoIntegracaoVo();
	   darConsulta.setNumrDarSeqc(new Long(giaDar.getCodigoUofSequencial()));
	   darConsulta.setParametroConsulta(darConsulta);
	   if (Validador.isNumericoValido(darConsulta.getParametroConsulta().getNumrDarSeqc()))
	   {
	      darConsulta = (new DocumentoArrecadacaoBe(conn)).obterDarEmitidoPorNumrDarOuCodgUofSeq(darConsulta);
	   }	   
	   if (darConsulta.isExiste())
	   {
	      return darConsulta;
	   }
	   else
	   {
	      throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_VINCULAR_DAR_NAO_EXISTE);
	   }
	}
	
   private DarQuitadoIntegracaoVo obterDarQuitadoArrecadacao(final GIAITCDDarVo giaDar) throws ObjetoObrigatorioException, 
           IntegracaoException, ParametroObrigatorioException
   {
      Validador.validaObjeto(giaDar);
      DarQuitadoIntegracaoVo darConsulta = new DarQuitadoIntegracaoVo();
      darConsulta.setNumrDar(new Long(giaDar.getCodigoUofSequencial()));
      darConsulta.setParametroConsulta(darConsulta);
      if (Validador.isNumericoValido(darConsulta.getParametroConsulta().getNumrDar()))
      {
         darConsulta = (new br.gov.mt.sefaz.itc.util.integracao.arrecadacao.DocumentoArrecadacaoBe(conn)).obterDarQuitadoPorNumrDarSequencial(darConsulta);
      }        
      return darConsulta;
   }        
   
	public void validaDarVinculadoITCD(final GIAITCDDarVo giaDar) throws ObjetoObrigatorioException, IntegracaoException, 
			  ParametroObrigatorioException, ConsultaException, ConexaoException, RegistroNaoPodeSerUtilizadoException, 
             SQLException, IOException
   {
		Validador.validaObjeto(giaDar);
	   //Consultando o Dar no Sistema de Arrecadação
	   giaDar.setDarEmitido(validaDarExistenteArrecadacao(giaDar));
	   GIAITCDDarVo giaDarConsulta = new GIAITCDDarVo();
	   giaDarConsulta.getDarEmitido().setNumrDarSeqc(giaDar.getDarEmitido().getNumrDarSeqc());
	   giaDarConsulta = new GIAITCDDarVo(giaDarConsulta);
	   giaDarConsulta =  consultarGIAITCDDar(giaDarConsulta);
	   if (giaDarConsulta.isExiste())
	   {
	      throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_VINCULAR_DAR_JA_VINCULADO);
	   }		
	   else
	   {
	      Integer codgTributo = giaDar.getDarEmitido().getCodgTributo();
	      /*Se o código tributo não for válido para o ITCD, consultar a Dar de quitação, pois quando 
          * o código de tributo é corrigido, a correção só ocorre no dar preliminar ou consolidado 
          * do sistema de arrecadação, ou seja, não corrige o dar emitido*/
	      if ( !DomnCodigoTributacaoITCD.isCodigoTributoValido( codgTributo ) )
	      {
	         DarQuitadoIntegracaoVo darQuitadoVo = obterDarQuitadoArrecadacao(giaDar);
	         if ( Validador.isObjetoValido( darQuitadoVo ) && Validador.isNumericoValido( darQuitadoVo.getCodgTributo() ) )
	         {
	            codgTributo = darQuitadoVo.getCodgTributo();
	         }
	      }
	      if(!DomnCodigoTributacaoITCD.isCodigoTributoValido( codgTributo ))
	      {
	         throw new RegistroNaoPodeSerUtilizadoException(MensagemErro.VALIDAR_CODIGO_TRIBUTO_DAR_ARRECADACAO); 
	      }
	   }
	}
	
	private void validaGIAITCDExistente(final GIAITCDDarVo giaDar) throws ObjetoObrigatorioException, ConsultaException, 
			  ConexaoException, ParametroObrigatorioException, IntegracaoException, SQLException, 
             IOException
   {
	   Validador.validaObjeto(giaDar);
	   GIAITCDBe giaBe = new GIAITCDBe(conn);
	   GIAITCDVo giaConsulta = new GIAITCDVo(giaDar.getGia());
	   giaConsulta = giaBe.consultarGIAITCDUsuarioNaoLotado(giaConsulta);
	   giaDar.setGia(giaConsulta);
	   if (!giaConsulta.isExiste())
	   {
	      throw new ParametroObrigatorioException("Número da GIA-ITCD inexistente.");
	   }
	}
	
	public void vinculaDar(final GIAITCDDarVo giaDar) throws ObjetoObrigatorioException, ConsultaException, 
			  ParametroObrigatorioException, IntegracaoException, ConexaoException, LogException, PersistenciaException, 
			  AnotacaoException, RegistroExistenteException, IOException
   {
	   Validador.validaObjeto(giaDar);
	   GIAITCDDarVo giaDarAtual = new GIAITCDDarVo();
	   giaDarAtual.getGia().setCodigo(giaDar.getGia().getCodigo());
	   giaDarAtual = new GIAITCDDarVo(giaDarAtual);
	   giaDarAtual.setConsultaCompleta(true);
	   giaDarAtual = consultarUltimoGIAITCDDar(giaDarAtual);
	   
	   //Consultando o Status Anterior da GIA-ITCD
	   StatusGIAITCDBe statusBe = new StatusGIAITCDBe(conn);
	   StatusGIAITCDVo statusAnterior = statusBe.obterStatusGIAITCDAnterior(giaDar.getGia().getStatusVo());
	   
	   //Lógica da vinculação manual de DAR a GIA-ITCD
	   if (giaDar.getGia().getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.RATIFICADO) 
	         || giaDar.getGia().getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.SEGUNDA_RETIFICACAO))
	   {  
	      if (!substituirDarPendente(giaDar, giaDarAtual))
	      {
	         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_VINCULAR_DAR_PARCELADO);
	      }
	   }
	   else if (giaDar.getGia().getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.PARCELADO))
	   {
	      vincularDarParcelado(giaDar, giaDarAtual, giaDar.getGia().getStatusVo());
	   }     
	   else if (giaDar.getGia().getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.PARA_INSCRICAO_EM_DIVIDA_ATIVA))
	   {
	      if (statusAnterior.getStatusGIAITCD().is(DomnStatusGIAITCD.RATIFICADO)
	            || statusAnterior.getStatusGIAITCD().is(DomnStatusGIAITCD.SEGUNDA_RETIFICACAO))
	      {
	         if (!substituirDarPendente(giaDar, giaDarAtual))
	         {
	            throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_VINCULAR_DAR_PARCELADO);
	         }
	      }
	      else if (statusAnterior.getStatusGIAITCD().is(DomnStatusGIAITCD.PARCELADO))
	      {
	         vincularDarParcelado(giaDar, giaDarAtual, statusAnterior);
	      }
	      else 
	      {
	         throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_VINCULAR_DAR_STATUS);
	      }
	   }
	   else 
	   {
	      throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_VINCULAR_DAR_STATUS);
	   }		
	}
	
	public void vincularManualGiaDAR(final GIAITCDDarVo giaDar) throws ObjetoObrigatorioException, IntegracaoException, 
			  ParametroObrigatorioException, ConsultaException, ConexaoException, LogException, PersistenciaException, 
			  AnotacaoException, RegistroExistenteException, SQLException, RegistroNaoPodeSerUtilizadoException, IOException
   {
		Validador.validaObjeto(giaDar);
		validaDarVinculadoITCD(giaDar);
		validaGIAITCDExistente(giaDar);
		vinculaDar(giaDar);
		commit();
	}


	private void vincularDarParcelado(final GIAITCDDarVo giaDar, GIAITCDDarVo giaDarAtual, StatusGIAITCDVo statusAnterior) throws ParametroObrigatorioException, 
			  ObjetoObrigatorioException, ConexaoException, IntegracaoException, ConsultaException, LogException, 
			  PersistenciaException, AnotacaoException, RegistroExistenteException, IOException
   {
		if (!Validador.isNumericoValido(giaDar.getNumeroParcela()))
		{
			giaDar.setNumeroParcela(-1);
			throw new ParametroObrigatorioException(MensagemErro.VALIDAR_GIA_ITCD_VINCULAR_DAR_E_NECESSARIO_INOFRMAR_PARCELAS);
		}
	   else if(!Validador.isNumericoValido(giaDarAtual.getDarEmitido().getNumrDarSeqc()))
	   {
	      incluirGIAITCDDar(giaDar);
	   }
		else
		{
			if (giaDar.getNumeroParcela() > statusAnterior.getNumeroParcelas())
			{
				throw new ParametroObrigatorioException("Número da parcela maior que o número informado no momento do Parcelamento.");
			}
			if (giaDar.getNumeroParcela() > giaDarAtual.getNumeroParcela() + 1)
			{
				throw new ParametroObrigatorioException("Número da parcela é maior que a próxima parcela a ser vinculada.");
			}
			if (giaDarAtual.getNumeroParcela() == giaDar.getNumeroParcela())
			{
				if (!substituirDarPendente(giaDar, giaDarAtual))
				{
					throw new ParametroObrigatorioException("O DAR atual referente a parcela informada está quitado, verifique o número da parcela informada.");
				}
			}
			else if ((giaDarAtual.getNumeroParcela() + 1) == giaDar.getNumeroParcela())
			{
				if (giaDarAtual.getDarEmitido().getStatDar().is(DomnStatDar.QUITADO))
				{
					incluirGIAITCDDar(giaDar);
				}
				else
				{
					throw new ParametroObrigatorioException("A parcela anterior não está quitada.");
				}
			}
			else
			{
				throw new ParametroObrigatorioException("O número da parcela não é o número da próxima parcela a ser vinculada.");
			}
		}
	}

	private boolean substituirDarPendente(final GIAITCDDarVo giaDarNovo, GIAITCDDarVo giaDarAtual) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException, ConsultaException, ConexaoException, IntegracaoException, LogException, 
			  PersistenciaException, AnotacaoException, RegistroExistenteException, IOException
   {
	   if (!giaDarAtual.getDarEmitido().getStatDar().is(DomnStatDar.QUITADO))
	   {
			giaDarAtual.setSubstituido(new DomnSimNao(DomnSimNao.SIM));
			giaDarNovo.setNumeroParcela(giaDarAtual.getNumeroParcela());
			alterarGIAITCDDar(giaDarAtual);
	      incluirGIAITCDDar(giaDarNovo);
			return true;
	   }
		return false;
	}

	/**
	 * Método para Validar GIA-ITCD Vinculação
	 * @param giaDar
	 * @throws ConsultaException
	 * @throws ObjetoObrigatorioException
	 * @throws RegistroExistenteException
	 * @throws ParametroObrigatorioException
	 * @throws ConexaoException
	 * @implemented by Daniel Balieiro
	 */
	public void validarGIAITCDDarVincularSubstituido(final GIAITCDDarVo giaDar) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException, ConsultaException, ConexaoException, IntegracaoException, LogException, 
			  PersistenciaException, AnotacaoException, RegistroExistenteException, IOException
   {
		if (Validador.isNumericoValido(giaDar.getCodigo()))
		{
			if (giaDar.getDarEmitido().getStatDar().is(DomnStatDar.PENDENTE) && 
						giaDar.getSubstituido().is(DomnSimNao.NAO))
			{
				giaDar.setSubstituido(new DomnSimNao(DomnSimNao.SIM));
				alterarGIAITCDDar(giaDar);
			}
		}
	}

	/**
	 * Método para Validar GIA-ITCD Vinculação
	 * @param giaITCDDarVo
	 * @throws ConsultaException
	 * @throws ObjetoObrigatorioException
	 * @throws RegistroExistenteException
	 * @throws ParametroObrigatorioException
	 * @throws ConexaoException
	 * @implemented by Daniel Balieiro
	 */
	public void validarGIAITCDDarVincularPendente(final GIAITCDDarVo giaITCDDarVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException, ConsultaException, ConexaoException, IntegracaoException, LogException, 
			  PersistenciaException, AnotacaoException, RegistroExistenteException, SQLException, IOException
   {
		if (giaITCDDarVo.getGia().getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.PARA_INSCRICAO_EM_DIVIDA_ATIVA))
		{
			StatusGIAITCDVo statusAnterior = 
						(new StatusGIAITCDBe(conn)).obterStatusGIAITCDAnterior(giaITCDDarVo.getGia().getStatusVo());
			if (statusAnterior.getStatusGIAITCD().is(DomnStatusGIAITCD.SEGUNDA_RETIFICACAO) || 
						statusAnterior.getStatusGIAITCD().is(DomnStatusGIAITCD.RATIFICADO))
			{
				GIAITCDDarVo giaDar = getDarEmitido(giaITCDDarVo);
				if (giaDar.getDarEmitido().getStatDar().is(DomnStatDar.PENDENTE) && 
							  giaDar.getSubstituido().is(DomnSimNao.NAO))
				{
					giaDar.setSubstituido(new DomnSimNao(DomnSimNao.SIM));
					alterarGIAITCDDar(giaDar);
				}
			}
		}
	}

	/**
	 * Método para Validar GIA-ITCD Vinculação
	 * @param giaITCDDarVo
	 * @return
	 * @throws ConsultaException
	 * @throws ObjetoObrigatorioException
	 * @throws RegistroExistenteException
	 * @throws ParametroObrigatorioException
	 * @throws ConexaoException
	 * @implemented by Daniel Balieiro
	 */
	public boolean validarGIAITCDDarVincularPendenteParcelado(final GIAITCDDarVo giaITCDDarVo) throws ConsultaException, 
			  ObjetoObrigatorioException, ConexaoException, ParametroObrigatorioException, IntegracaoException, SQLException, 
             IOException
   {
		if (giaITCDDarVo.getGia().getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.PARCELADO))
		{
			GIAITCDDarVo giaDar = getDarEmitido(giaITCDDarVo);
			if (giaDar.getDarEmitido().getStatDar().is(DomnStatDar.PENDENTE) && 
						giaDar.getSubstituido().is(DomnSimNao.NAO))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * Método para Validar GIA-ITCD Vinculação
	 * @param giaITCDDarVo
	 * @return
	 * @throws ConsultaException
	 * @throws ObjetoObrigatorioException
	 * @throws RegistroExistenteException
	 * @throws ParametroObrigatorioException
	 * @throws ConexaoException
	 * @implemented by Daniel Balieiro
	 */
	public boolean validarGIAITCDDarVincularQuitado(final GIAITCDDarVo giaITCDDarVo) throws ConsultaException, 
			  ObjetoObrigatorioException, ConexaoException, ParametroObrigatorioException, IntegracaoException, SQLException, 
             IOException
   {
		GIAITCDDarVo giaDar = getDarEmitido(giaITCDDarVo);
		if (giaITCDDarVo.getGia().getStatusVo().getStatusGIAITCD().is(DomnStatusGIAITCD.PARCELADO) && 
				 giaDar.getDarEmitido().getStatDar().is(DomnStatDar.QUITADO))
		{
			return true;
		}
		return false;
	}

	/**
	 * Método para obter o DAR Emitido
	 * @param giaITCDDarVo
	 * @return
	 * @throws ConsultaException
	 * @throws ObjetoObrigatorioException
	 * @implemented by Daniel Balieiro
	 */
	private GIAITCDDarVo getDarEmitido(GIAITCDDarVo giaITCDDarVo) throws ConsultaException, ObjetoObrigatorioException, 
			  ConexaoException, ParametroObrigatorioException, IntegracaoException, SQLException, 
             IOException
   {
		GIAITCDDarVo giaDar = new GIAITCDDarVo();
		giaDar.getGia().setCodigo(giaITCDDarVo.getGia().getCodigo());
	   GIAITCDDarVo giaDarConsulta = new GIAITCDDarVo(giaDar);
		giaDarConsulta.setConsultaCompleta(true);
		return consultarGIAITCDDar(giaDarConsulta);
	}

	/**
	 * @param giaITCDDarVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void alterar(final GIAITCDDarVo giaITCDDarVo) throws ObjetoObrigatorioException, LogException, 
			  AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(giaITCDDarVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).update(giaITCDDarVo);
	}

	/**
	 * @param giaITCDDarVo
	 * @throws ObjetoObrigatorioException
	 * @throws LogException
	 * @throws AnotacaoException
	 * @throws PersistenciaException
	 * @implemented by Ricardo Vitor de Oliveira Moraes
	 */
	private synchronized void incluir(final GIAITCDDarVo giaITCDDarVo) throws ObjetoObrigatorioException, LogException, 
			  AnotacaoException, PersistenciaException
	{
		Validador.validaObjeto(giaITCDDarVo);
		new GenericoLogAnotacaoDao(conn, ConfiguracaoITCD.NAO_GERA_LOG).insert(giaITCDDarVo);
      ExibirLOG.exibirLog("DAR incluso na ITCTB43_ITCD_DAR Nº DAR Sequencial: "+giaITCDDarVo.getDarEmitido().getNumrDarSeqc() , giaITCDDarVo.getGia().getCodigo());
	}	

	/**
	 * Altera informações sobre um GIAITCDDar no banco de dados.
	 *
	 * @param giaITCDDarVo (Value Object).
	 * @throws ObjetoObrigatorioException Esta Exception deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws RegistroExistenteException Esta Exception deve ser lançada quando o sistema tenta INCLUIR um registro no banco de dados mas o registro já existe.
	 * @throws ParametroObrigatorioException Esta Exception deve ser lançada quando o usuário não informa um campo que é obrigatório para o sistema.
	 * @throws ConsultaException Esta Exception deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @throws ConexaoException Esta exceção deverá ser lançada para substibuir o SQLException na camada de negócio e na camada de apresentação.
	 * @implemented by Leandro DOrileo
	 */
	public synchronized void alterarGIAITCDDar(final GIAITCDDarVo giaITCDDarVo) throws ObjetoObrigatorioException, 
			  ParametroObrigatorioException, ConsultaException, ConexaoException, IntegracaoException, LogException, 
			  PersistenciaException, AnotacaoException, RegistroExistenteException, IOException
   {
		Validador.validaObjeto(giaITCDDarVo);
		try
		{
			try
			{
				synchronized (GIAITCDDarVo.class)
				{
					validaParametrosIncluirAlterar(giaITCDDarVo);
					giaITCDDarVo.setParametroConsulta(giaITCDDarVo);
					GIAITCDDarVo giaITCDDarConsultaVo = new GIAITCDDarVo();
					giaITCDDarConsultaVo.getGia().setCodigo(giaITCDDarVo.getCodigo());
					giaITCDDarConsultaVo.getDarEmitido().setNumrDarSeqc(giaITCDDarVo.getDarEmitido().getNumrDarSeqc());
					giaITCDDarConsultaVo = new GIAITCDDarVo(giaITCDDarConsultaVo);
             
               consultarGIAITCDDar(giaITCDDarConsultaVo);
             
             
               if (permiteAlterar(giaITCDDarVo, giaITCDDarConsultaVo))
					{
						giaITCDDarVo.setDataAtualizacao(new Date());
						alterar(giaITCDDarVo);
						commit();
						giaITCDDarVo.setMensagem(MensagemSucesso.ALTERAR);
					}
					else
					{
						throw new RegistroExistenteException(MensagemErro.VALIDAR_BEM_INCLUIR_DESCRICAO_E_TIPO);
					}
				}
			}
			catch (IntegracaoException e)
			{
				conn.rollback();
				throw e;
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
			catch(ConsultaException e)
			{
				conn.rollback();
				throw e;
			}
			catch(ConexaoException e)
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
			catch(RegistroExistenteException e)
			{
				conn.rollback();
				throw e;
			}
		   catch (IOException e)
		   {
            e.printStackTrace();
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
	 * Método que certifica se é permitido alterar determinado GIAITCDDarVo
	 * @param giaITCDDarVo (Value Object).
	 * @param giaITCDDarConsultaVo registro encontrado no Banco de Dados
	 * @return true
	 * @implemented by Daniel Balieiro
	 */
	private boolean permiteAlterar(final GIAITCDDarVo giaITCDDarVo, final GIAITCDDarVo giaITCDDarConsultaVo)
	{
		if (giaITCDDarConsultaVo.isExiste() && 
				 (Validador.isNumericoValido(giaITCDDarConsultaVo.getGia().getCodigo()) && Validador.isNumericoValido(giaITCDDarConsultaVo.getGia().getCodigo())))
		{
			return (giaITCDDarVo.getGia().getCodigo() == giaITCDDarConsultaVo.getGia().getCodigo()) && 
						(giaITCDDarVo.getDarEmitido().getNumrDarSeqc().longValue() == 
						giaITCDDarConsultaVo.getDarEmitido().getNumrDarSeqc().longValue());
		}
		return true;
	}

	/**
	 * Consulta informações sobre um GIAITCDDAr
	 * @param giaITCDDarVo (Value Object).
	 * @throws ConsultaException Esta Exception deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @throws ObjetoObrigatorioException Esta Exception deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @return
	 * @implemented by Leandro Dorileo
	 */
	public GIAITCDDarVo consultarGIAITCDDar(final GIAITCDDarVo giaITCDDarVo) throws ConsultaException, 
			  ObjetoObrigatorioException, ConexaoException, ParametroObrigatorioException, IntegracaoException, SQLException, 
             IOException
   {
		Validador.validaObjeto(giaITCDDarVo);
		(new GIAITCDDarQDao(conn)).findGIAITCDDar(giaITCDDarVo);
		if (giaITCDDarVo.isConsultaCompleta())
		{
			GIAITCDVo giaConsulta = new GIAITCDVo();
			giaConsulta.setCodigo((giaITCDDarVo.getParametroConsulta()).getGia().getCodigo());
			giaConsulta = new GIAITCDVo(giaConsulta);
			giaConsulta.setConsultaCompleta(true);
			GIAITCDVo gia = (new GIAITCDBe(conn)).consultarGIAITCDUsuarioNaoLotado(giaConsulta);
			giaITCDDarVo.setGia(gia);
			
			DarEmitidoIntegracaoVo darConsulta = new DarEmitidoIntegracaoVo();
			darConsulta.setParametroConsulta((giaITCDDarVo.getParametroConsulta()).getDarEmitido());
			
			if(Validador.isNumericoValido((giaITCDDarVo.getParametroConsulta()).getDarEmitido().getNumrDarSeqc()))
			{
				giaITCDDarVo.setDarEmitido((new DocumentoArrecadacaoBe(conn)).obterDarEmitidoPorNumrDarOuCodgUofSeq(darConsulta));
			}
		}
		return giaITCDDarVo;
	}

	/**
	 * Consulta informações sobre um GIAITCDDAr
	 * @param giaITCDDarVo (Value Object).
	 * @throws ConsultaException Esta Exception deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @throws ObjetoObrigatorioException Esta Exception deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @return
	 * @implemented by Leandro Dorileo
	 */
	public GIAITCDDarVo consultarUltimoGIAITCDDar(final GIAITCDDarVo giaITCDDarVo) throws ConsultaException, 
			  ObjetoObrigatorioException, ParametroObrigatorioException, IntegracaoException
	{
		Validador.validaObjeto(giaITCDDarVo);
		if (!Validador.isNumericoValido(((GIAITCDDarVo) giaITCDDarVo.getParametroConsulta()).getGia().getCodigo()))
		{
			throw new ObjetoObrigatorioException();
		}
		(new GIAITCDDarQDao(conn)).findUltimoGIAITCDDar(giaITCDDarVo);
		
		if (giaITCDDarVo.isExiste() && Validador.isNumericoValido(giaITCDDarVo.getDarEmitido().getNumrDarSeqc()))
		{
		   DocumentoArrecadacaoBe documentoArrecadacaoBe = new DocumentoArrecadacaoBe(conn);
		   DarEmitidoIntegracaoVo darEmitidoConsulta = giaITCDDarVo.getDarEmitido();
		   darEmitidoConsulta.setParametroConsulta(darEmitidoConsulta);
		   giaITCDDarVo.setDarEmitido(documentoArrecadacaoBe.obterDarEmitidoPorNumrDarOuCodgUofSeq(darEmitidoConsulta));
		}
		
		return giaITCDDarVo;
	}

	/**
	 * Lista informações sobre um ou mais GIAITCDDar
	 * @param giaITCDDarVo (Value Object).
	 * @throws ObjetoObrigatorioException Esta exceção deve ser lançada quando o sistema recebe como parâmetro em um de seus métodos, um objeto null (nulo) e não poderia ser null (nulo).
	 * @throws ConsultaException Esta exceção deve ser lançada quando o sistema tenta CONSULTAR um registro no banco de dados e não consegue.
	 * @return
	 * @implemented by Leandro Dorileo
	 */
	public GIAITCDDarVo listarGIAITCDDar(final GIAITCDDarVo giaITCDDarVo) throws ObjetoObrigatorioException, 
			  ConsultaException, IntegracaoException
	{
		Validador.validaObjeto(giaITCDDarVo);
		(new GIAITCDDarQDao(conn)).listGIAITCDDar(giaITCDDarVo);
		if(Validador.isCollectionValida(giaITCDDarVo.getCollVO()) && giaITCDDarVo.isConsultaCompleta())
		{
			for(Iterator it = giaITCDDarVo.getCollVO().iterator(); it.hasNext(); )
			{
				GIAITCDDarVo atual = (GIAITCDDarVo) it.next();
				if(Validador.isNumericoValido(atual.getDarEmitido().getNumrDarSeqc()))
				{
				      DocumentoArrecadacaoBe documentoArrecadacaoBe = new DocumentoArrecadacaoBe(conn);
				      DarEmitidoIntegracaoVo darEmitidoConsulta = atual.getDarEmitido();
				      darEmitidoConsulta.setParametroConsulta(darEmitidoConsulta);
				      atual.setDarEmitido(documentoArrecadacaoBe.obterDarEmitidoPorNumrDarOuCodgUofSeq(darEmitidoConsulta));
				}
			}
		}
		Validador.validaObjeto(giaITCDDarVo);
		return giaITCDDarVo;
	}
}
