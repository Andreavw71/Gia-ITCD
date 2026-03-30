package br.gov.mt.sefaz.itc.util.http;

import br.com.abaco.util.Validador;
import br.com.abaco.util.exceptions.ObjetoObrigatorioException;
import br.com.abaco.util.exceptions.ParametroObrigatorioException;
import br.com.abaco.util.facade.FormAbaco;
import br.com.abaco.util.http.AbacoBuffer;
import br.com.abaco.util.logsefaz.LogSefazVo;

import br.gov.mt.sefaz.itc.util.facade.FormITC;
import br.gov.mt.sefaz.itc.util.facade.MensagemErro;
import br.gov.mt.sefaz.itc.util.log.ExibirLOG;

import javax.servlet.http.HttpServletRequest;

public class HttpUtil
{
   private static String URL_AMBIENTE_DESENV = "desenv.sefaz.mt.gov.br";
   private static String URL_AMBIENTE_HOMOLOCAGAO = "homologa.sefaz.mt.gov.br";
   private static String URL_AMBIENTE_PRODUCAO = "www.sefaz.mt.gov.br";

   private static String URL_REDIRECIONAMENTO_DESENV = "http://desenv.sefaz.mt.gov.br";
   private static String URL_REDIRECIONAMENTO_HOMOLOCAGAO = "http://homologa.sefaz.mt.gov.br";
   private static String URL_REDIRECIONAMENTO_PRODUCAO = "http://www.sefaz.mt.gov.br";

	/** Metodo utilizado para validar os caracteres informados na tela do usuário.
	 * <BR><br>Exemplo:  <BR>
	 * HttpUtil.validarCaracteresInformados(getBuffer(request), request.getParameter(Form.CAMPO_IMAGEM) );
	 * @param buffer
	 * @param campoImagemInformado
	 * @throws ObjetoObrigatorioException
	 * @throws ParametroObrigatorioException
	 */
	public static void validarCaracteresInformados(AbacoBuffer buffer , String campoImagemInformado ) throws ObjetoObrigatorioException, 
																											 ParametroObrigatorioException {
		 String codgCaracteres = (String) buffer.getAttribute(FormAbaco.CODIGO_IMAGEM);
		 Validador.validaObjeto(codgCaracteres);            
		 if( !Validador.isStringValida( campoImagemInformado ) )
		 {
			  throw new ParametroObrigatorioException(MensagemErro.CARACTERES_IMAGEM_DEVEM_SER_INFORMADOS);
		 }
		 if(!campoImagemInformado.equalsIgnoreCase(codgCaracteres))
		 {
			 throw new ParametroObrigatorioException(MensagemErro.CARACTERES_IMAGEM_INVALIDO, FormITC.CAMPO_IMAGEM);
		 }
	}

	public static void validarCaracteresInformadosModAberto(AbacoBuffer buffer , String campoImagemInformado, LogSefazVo logSefazVo ) throws ObjetoObrigatorioException, ParametroObrigatorioException 
	{
		if( !Validador.isObjetoValido( logSefazVo ) || !Validador.isObjetoValido( logSefazVo.getUsuario() ) || !Validador.isNumericoValido( logSefazVo.getUsuario().getCodigo() ) )
		{
			validarCaracteresInformados( buffer, campoImagemInformado );	
		}
		else if( Validador.isStringValida( (String) buffer.getAttribute(FormAbaco.CODIGO_IMAGEM) ) )
		{
			System.out.println( "--------------------------------------------------ERRO--------------------------------------------------" );
			System.out.println( "Usuário logado não deve possuir o Campo de Caracteres (regra do sistema). Esse campo não foi validado!!!!!!" );
		   System.out.println( "--------------------------------------------------ERRO--------------------------------------------------" );
		}
	}
   
   
   /**
    * <b>Objetivo:</b> este método tem por objetivo
    * redirecinar a URL de acordo com o ambiente
    * autal do sistema.
    * 
    * 
    * @param request
    * @return URL do ambiente em que o sistema está implantado.
    * @throws ParametroObrigatorioException caso o request seje NULO.
    */
   public static String redirecionarAmbiente(HttpServletRequest request) throws ParametroObrigatorioException
   {
      if(!Validador.isObjetoValido(request))
      {
         throw new ParametroObrigatorioException("Não foi possível fazer o redirecionamento de ambiente");
      }
      String url = request.getRequestURL().toString();
      
      if (url.contains(URL_AMBIENTE_DESENV))
      {
         url = URL_REDIRECIONAMENTO_DESENV;
      }
      else if (url.contains(URL_AMBIENTE_HOMOLOCAGAO))
      {
         url = URL_REDIRECIONAMENTO_HOMOLOCAGAO;
      }
      else if (url.contains(URL_AMBIENTE_PRODUCAO))
      {
         url = URL_REDIRECIONAMENTO_PRODUCAO;
      }
      ExibirLOG.exibirLog( url );
      return url;
   }
   

}
