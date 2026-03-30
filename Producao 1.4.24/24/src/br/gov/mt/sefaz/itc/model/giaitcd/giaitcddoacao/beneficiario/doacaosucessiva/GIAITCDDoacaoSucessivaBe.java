package br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.doacaosucessiva;

import br.com.abaco.log5.util.excecoes.AnotacaoException;
import br.com.abaco.log5.util.excecoes.IncluiException;
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
import br.gov.mt.sefaz.itc.util.facade.MensagemSucesso;

import br.gov.mt.sefaz.itc.model.giaitcd.giaitcddoacao.beneficiario.GIAITCDDoacaoBeneficiarioVo;

import br.gov.mt.sefaz.itc.util.log.ExibirLOG;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.Date;

public class GIAITCDDoacaoSucessivaBe extends AbstractBe
{
   public GIAITCDDoacaoSucessivaBe() throws SQLException
   {
      super();
   }
   
   public GIAITCDDoacaoSucessivaBe(Connection connection)
   {
      super(connection);
   }
   
   public GIAITCDDoacaoSucessivaVo consultaDoacoesSucessivasDoBenef(final GIAITCDDoacaoBeneficiarioVo beneficiarioVo) throws ObjetoObrigatorioException, 
           ConsultaException
   {
      Validador.validaObjeto(beneficiarioVo);
      GIAITCDDoacaoSucessivaVo doacaoSucessivaVo = new GIAITCDDoacaoSucessivaVo();     
      return (new GIAITCDDoacaoSucessivaQDao(conn)).findAllGIAITCDDoacaoSucessivaVo(beneficiarioVo, doacaoSucessivaVo);       
   }
   
   public GIAITCDDoacaoSucessivaVo consultaDoacoesSucessivasDoBenefNaoUtilizadasParaCalculo(final GIAITCDDoacaoBeneficiarioVo beneficiarioVo) throws ObjetoObrigatorioException, 
           ConsultaException
   {
      Validador.validaObjeto(beneficiarioVo);
      GIAITCDDoacaoSucessivaVo doacaoSucessivaVo = new GIAITCDDoacaoSucessivaVo();
      return (new GIAITCDDoacaoSucessivaQDao(conn)).findAllGIAITCDDoacaoSucessivaVoNaoUtilizadaParaCalculo(beneficiarioVo, doacaoSucessivaVo);       
   }
   
   public synchronized void incluirDoacaoSucessivaVo(final GIAITCDDoacaoSucessivaVo doacaoSucessivaVo) throws ObjetoObrigatorioException, 
           IntegracaoException, RegistroExistenteException, ConsultaException, ParametroObrigatorioException, 
           ConexaoException, LogException, PersistenciaException, AnotacaoException, IncluiException {

       Validador.validaObjeto(doacaoSucessivaVo);

       try {
           processarInclusaoDoacaoSucessiva(doacaoSucessivaVo);
       } catch (SQLException e) {
           ExibirLOG.exibirLogSimplificado("Erro incluirGIAITCDDoacaoSucessivaVo - SQL:" + e.getMessage());
           throw new ConexaoException(ConexaoException.CONEXAO_FECHADA);
       }
   }

   private void processarInclusaoDoacaoSucessiva(GIAITCDDoacaoSucessivaVo doacaoSucessivaVo) throws PersistenciaException, IncluiException, SQLException, RegistroExistenteException, ParametroObrigatorioException, ObjetoObrigatorioException {
       synchronized (GIAITCDDoacaoSucessivaVo.class) {
           try {
               doacaoSucessivaVo.setDataAtualizacao(new Date());
               ExibirLOG.exibirLogSimplificado("DATA INCLUSAO:" + doacaoSucessivaVo.getDataAtualizacao());
               new GIAITCDDoacaoSucessivaDao(conn).insertGIAITCDoacaoSucessiva(doacaoSucessivaVo);
               doacaoSucessivaVo.setMensagem(MensagemSucesso.INCLUIR);
           } catch (IncluiException e) {
               conn.rollback();
               ExibirLOG.exibirLogSimplificado("Erro: - processarInclusaoDoacaoSucessiva" + e.getMessage());
               throw new PersistenciaException("Não foi possível incluir Doacao Sucessiva");
           }
       }
   }
   
   public GIAITCDDoacaoSucessivaVo listaDoacoesSucessivas(final GIAITCDDoacaoSucessivaVo doacaoSucessivaVo) throws ObjetoObrigatorioException, 
           ConsultaException
   {
      Validador.validaObjeto(doacaoSucessivaVo);
      (new GIAITCDDoacaoSucessivaQDao(conn)).listaDoacoesSucessivas(doacaoSucessivaVo);
      return doacaoSucessivaVo;
   }
   
   public boolean verificaSeGiaPresenteEmDoacaoSucessiva(long codgGia)
      throws ObjetoObrigatorioException, ConsultaException{
      return (new GIAITCDDoacaoSucessivaQDao(conn)).verificaSeGiaPresenteEmDoacaoSucessiva(codgGia);
   }
  
}
