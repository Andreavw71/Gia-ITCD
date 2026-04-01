package br.gov.mt.sefaz.itc.util.dominio;

import br.com.abaco.util.dominio.util.AbstractDominioNumericoAbaco;


/**
 *  Dominio de Status do GIA ITCD
 * @author Daniel Balieiro
 * @version $Revision: 1.2 $
 */
public class DomnStatusGIAITCD extends AbstractDominioNumericoAbaco
{
	private static final long serialVersionUID = Long.MAX_VALUE;
	public static final int PENDENTE_DE_PROTOCOLO = 1;
	public static final int PROTOCOLADO = 2;
	public static final int PROTOCOLO_AUTORIZADO_PELA_GIOR = 3;
	public static final int AVALIADO = 4;
	public static final int ISENTO = 5;
	public static final int NAO_OCORRENCIA_DE_FATO_GERADOR = 6;
	public static final int RETIFICADO = 7;
	public static final int NOTIFICADO = 8;
	public static final int IMPUGNADO = 9;
	public static final int PARCELADO = 10;
	public static final int QUITADO = 11;
	public static final int PARA_INSCRICAO_EM_DIVIDA_ATIVA = 12;
	public static final int REMETIDO_PARA_DIVIDA_ATIVA = 13;
	public static final int INATIVO = 14;
	public static final int RATIFICADO = 15;
	public static final int SEGUNDA_RETIFICACAO = 16;
	public static final int ALTERADO_PELO_SERVIDOR = 17;
	public static final int QUITADO_MANUALMENTE = 18;
	public static final int INATIVADO_AUTOMATICAMENTE = 19;
	public static final int EM_ELABORACAO = 20;
	public static final int NOTIFICADO_CIENTE = 21;
	public static final int RETIFICADO_CIENTE = 22;
	public static final int SEGUNDA_RETIFICACAO_CIENTE = 23;
	public static final int RATIFICADO_CIENTE = 24;
	public static final int REATIVADO = 25;
   public static final int ENVIADO_CCF = 26;
   public static final int QUITADO_CCF = 27;
   public static final int AVALIACAO_EXCLUIDA = 28;
   public static final int ALTERADO_PELA_GIOR = 29;
   public static final int QUITADO_PELA_GIOR = 30;
   public static final int AVALIACAO_REABERTA = 31;
   public static final int ISENTO_IMPOSTO_ATE_1_UPF = 32;
   public static final int DISPENSADO_RECOLHIMENTO = 33;
   public static final int DISPENSA_E_ISENCAO = 34;
   
	public static final String DESC_PENDENTE_DE_PROTOCOLO = "Pendente de Protocolo";
	public static final String DESC_PROTOCOLADO = "Protocolada";
	public static final String DESC_PROTOCOLO_AUTORIZADO_PELA_GIOR = "Protocolo Autorizado pela GIOR";
	public static final String DESC_AVALIADO = "Avaliada";
	public static final String DESC_ISENTO = "Isenta";
	public static final String DESC_NAO_OCORRENCIA_DE_FATO_GERADOR = "Não Ocorrência de Fato Gerador";
	public static final String DESC_RETIFICADO = "Retificada";
	public static final String DESC_NOTIFICADO = "Notificada";
	public static final String DESC_IMPUGNADO = "Impugnada";
	public static final String DESC_PARCELADO = "Parcelada";
	public static final String DESC_QUITADO = "Quitada";
	public static final String DESC_PARA_INSCRICAO_EM_DIVIDA_ATIVA = "Para Inscrição em Dívida Ativa";
	public static final String DESC_REMETIDO_PARA_DIVIDA_ATIVA = "Remetida para Dídiva Ativa";
	public static final String DESC_INATIVO = "Inativa";
	public static final String DESC_RATIFICADO = "Ratificada";
	public static final String DESC_SEGUNDA_RETIFICACAO = "Segunda Retificação";
	public static final String DESC_ALTERADO_PELO_SERVIDOR = "Alterada pelo servidor";
	public static final String DESC_QUITADO_MANUALMENTE = "Quitada Manualmente";
	public static final String DESC_INATIVADO_AUTOMATICAMENTE = "Inativada Automaticamente";
	public static final String DESC_EM_ELABORACAO = "Em Elaboração";
	public static final String DESC_NOTIFICADO_CIENTE = "Notificada / Ciente";
	public static final String DESC_RETIFICADO_CIENTE = "Retificada / Ciente";
	public static final String DESC_SEGUNDA_RETIFICACAO_CIENTE = "Segunda Retificação / Ciente";
	public static final String DESC_RATIFICADO_CIENTE = "Ratificada / Ciente";
	public static final String DESC_REATIVADO = "Reativada";
   public static final String DESC_ENVIADO_CCF = "Enviado ao CCF";
   public static final String DESC_QUITADO_CCF = "Quitado CCF";
   public static final String DESC_AVALIACAO_EXCLUIDA = "Avaliação Excluida";
   public static final String DESC_ALTERADO_PELA_GIOR = "Alterado pela GIOR";
   public static final String DESC_QUITADO_PELA_GIOR = "Quitado pela GIOR";
   public static final String DESC_AVALIACAO_REABAERTA = "Avaliação Reaberta";
   public static final String DESC_ISENTO_IMPOSTO_ATE_1_UPF = "ISENTO – IMPOSTO INFERIOR A 1 UPF/MT";
   public static final String DESC_DISPENSADO_RECOLHIMENTO = "Dispensa do Recolhimento do Imposto";
   public static final String DESC_DISPENSA_E_ISENCAO = "Dispensa e Isencão do Imposto";
	
	public static int domnIndice[] = 
	{
		PENDENTE_DE_PROTOCOLO
		, PROTOCOLADO
		, PROTOCOLO_AUTORIZADO_PELA_GIOR
		, AVALIADO
		, ISENTO
		, NAO_OCORRENCIA_DE_FATO_GERADOR
		, RETIFICADO
		, NOTIFICADO
		, IMPUGNADO
		, PARCELADO
		, QUITADO
		, PARA_INSCRICAO_EM_DIVIDA_ATIVA
		, REMETIDO_PARA_DIVIDA_ATIVA
		, INATIVO
		, RATIFICADO
		, SEGUNDA_RETIFICACAO
		, ALTERADO_PELO_SERVIDOR
		, QUITADO_MANUALMENTE
		, INATIVADO_AUTOMATICAMENTE
		, EM_ELABORACAO
		, NOTIFICADO_CIENTE
		, RETIFICADO_CIENTE
		, SEGUNDA_RETIFICACAO_CIENTE
		, RATIFICADO_CIENTE
		, REATIVADO
      , ENVIADO_CCF
      , QUITADO_CCF
      , AVALIACAO_EXCLUIDA
      , ALTERADO_PELA_GIOR
      , QUITADO_PELA_GIOR
      , AVALIACAO_REABERTA
      , ISENTO_IMPOSTO_ATE_1_UPF
      , DISPENSADO_RECOLHIMENTO
      , DISPENSA_E_ISENCAO
	};
	
	public static String domnDescricao[] = 
	{
		DESC_PENDENTE_DE_PROTOCOLO
		, DESC_PROTOCOLADO
		, DESC_PROTOCOLO_AUTORIZADO_PELA_GIOR
		, DESC_AVALIADO
		, DESC_ISENTO
		, DESC_NAO_OCORRENCIA_DE_FATO_GERADOR
		, DESC_RETIFICADO
		, DESC_NOTIFICADO
		, DESC_IMPUGNADO
		, DESC_PARCELADO
		, DESC_QUITADO
		, DESC_PARA_INSCRICAO_EM_DIVIDA_ATIVA
		, DESC_REMETIDO_PARA_DIVIDA_ATIVA
		, DESC_INATIVO
		, DESC_RATIFICADO
		, DESC_SEGUNDA_RETIFICACAO
		, DESC_ALTERADO_PELO_SERVIDOR
		, DESC_QUITADO_MANUALMENTE
		, DESC_INATIVADO_AUTOMATICAMENTE
		, DESC_EM_ELABORACAO
		, DESC_NOTIFICADO_CIENTE
		, DESC_RETIFICADO_CIENTE
		, DESC_SEGUNDA_RETIFICACAO_CIENTE
		, DESC_RATIFICADO_CIENTE
		, DESC_REATIVADO
      , DESC_ENVIADO_CCF
      , DESC_QUITADO_CCF
      , DESC_AVALIACAO_EXCLUIDA
      , DESC_ALTERADO_PELA_GIOR
      , DESC_QUITADO_PELA_GIOR
      , DESC_AVALIACAO_REABAERTA
      , DESC_ISENTO_IMPOSTO_ATE_1_UPF
      , DESC_DISPENSADO_RECOLHIMENTO
      , DESC_DISPENSA_E_ISENCAO
	};

	/**
	 * Instancia o domínio sem valor corrente
	 */
	public DomnStatusGIAITCD()
	{
		super(-1);
	}

	/**
	 * Instancia o domínio com o valor corrente do domínio de acordo com a constante informada.
	 * @param  valorConstante O valor do domínio.
	 */
	public DomnStatusGIAITCD(int valorConstante)
	{
		super(valorConstante);
	}

	/**
	 * Obtém o array com todas as constantes deste domínio.
	 * <b>Este método é utilizado pelo tagsgenerico.jar, logo é obrigatório para todos os domínios.</b>
	 * @return int[] - array de inteiro com as constantes deste domínio.
	 */
	public int[] getDomnIndice()
	{
		return domnIndice;
	}

	/**
	 * Obtém o array com todas as descrições das constantes deste domínio.
	 * <b>Este método é utilizado pelo tagsgenerico.jar, logo é obrigatório para todos os domínios.</b>
	 * @return String[] - array de String com as constantes deste domínio.
	 */
	public String[] getDomnDescricao()
	{
		return domnDescricao;
	}

	public int getKey()
	{
		return super.getValorCorrente();
	}	
	
	public static void main(String[] args)
	{
		DomnStatusGIAITCD stat = new DomnStatusGIAITCD();
		for(int valor : stat.getDomnIndice())
		{
			System.out.print(valor + "- " + stat.getTexto(valor) + "; ");
		}
	}
	
}
