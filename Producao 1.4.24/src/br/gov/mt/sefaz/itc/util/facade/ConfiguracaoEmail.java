package br.gov.mt.sefaz.itc.util.facade;

import br.com.abaco.util.smtp.AbacoEmail;


/**
 * Interface de configuração de parâmetros de envio de email.
 * @author Ricardo Vitor de Oliveira Moraes
 * @version $Revision: 1.1 $
 */
public interface ConfiguracaoEmail
{
	public static final String EMAIL_REMETENTE = "itcd@sefaz.mt.gov.br";
	public static final String NOME_REMETENTE = "SEFAZ - ITCD";
	public static final String PORTA = AbacoEmail.PORTA_SEFAZ;
	public static final String SMTP = AbacoEmail.SMTP_SEFAZ;
}
