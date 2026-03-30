package br.gov.mt.sefaz.itc.util.dominio;

import br.com.abaco.util.dominio.util.AbstractDominioNumericoAbaco;


/**
 * Dominio usado para representar os dados de uma permissao de acesso do acessoweb.
 * Aqui tempo representado a desc que se refere ao nome do grupo de permissao e o codg
 * que representa o codigo do grupo de permissao.
 * 
 * @author Leandro Dorileo
 */
public class DomnGrupoPermissao extends AbstractDominioNumericoAbaco
{


		private static final long serialVersionUID = Long.MAX_VALUE;
		
		public static final int CODG_GRUPO_PERM_GIOR = 1;
		public static final int CODG_GRUPO_PERM_AGENFA = 2;
		public static final int CODG_GRUPO_PERM_SUED = 3;
		public static final int CODG_GRUPO_PERM_GESTOR = 4;
		public static final int CODG_GRUPO_PERM_AVALIADOR_AGENFA = 5;
		public static final int CODG_GRUPO_PERM_AVALIADOR_GERENCIA = 6;
		public static final int CODG_GRUPO_PERM_AVALIADOR_GIOR = 7;
		public static final int CODG_GRUPO_PERM_ITCDCONSULTA = 8;
      public static final int CODG_GRUPO_PERM_CCF = 9;
      public static final int CODG_GRUPO_ATEND_AGENFA = 10;
		
		public static final String DESC_GRUPO_PERM_GIOR = "ITCDGIOR";
		public static final String DESC_GRUPO_PERM_AGENFA = "ITCDAGENFA";
		public static final String DESC_GRUPO_PERM_SUED = "ITCDSUED";
		public static final String DESC_GRUPO_PERM_GESTOR = "ITCDGESTOR";
		public static final String DESC_GRUPO_PERM_AVALIADOR_AGENFA = "ITCDAVALIADORAGENFA";
		public static final String DESC_GRUPO_PERM_AVALIADOR_GERENCIA = "ITCDAVALIADORGERENCI";
		public static final String DESC_GRUPO_PERM_AVALIADOR_GIOR = "ITCDAVALIADORGIOR";
		public static final String DESC_GRUPO_PERM_ITCDCONSULTA = "ITCDCONSULTA";
      public static final String DESC_GRUPO_PERM_CCF = "ITCDCCF";
      public static final String DESC_GRUPO_ATEND_AGENFA = "ATEND_AGENFA";

		 /**
		  * Construtor público padrao
		  */
		 public DomnGrupoPermissao()
		 {
					super(-1);
		 }
		 
		 /** índices numéricos do domínio */
		 private static int domnIndice[] = 
		 {
				CODG_GRUPO_PERM_GIOR
				,CODG_GRUPO_PERM_AGENFA
				,CODG_GRUPO_PERM_SUED
				,CODG_GRUPO_PERM_GESTOR
				,CODG_GRUPO_PERM_AVALIADOR_AGENFA
				,CODG_GRUPO_PERM_AVALIADOR_GERENCIA
				,CODG_GRUPO_PERM_AVALIADOR_GIOR
				,CODG_GRUPO_PERM_ITCDCONSULTA
            ,CODG_GRUPO_PERM_CCF
            ,CODG_GRUPO_ATEND_AGENFA
		 };
			  
		 /** descriçoes do domínio */
		 private static String domnDescricao[] = 
		 {
				DESC_GRUPO_PERM_GIOR
				,DESC_GRUPO_PERM_AGENFA
				,DESC_GRUPO_PERM_SUED
				,DESC_GRUPO_PERM_GESTOR
				,DESC_GRUPO_PERM_AVALIADOR_AGENFA
				,DESC_GRUPO_PERM_AVALIADOR_GERENCIA
				,DESC_GRUPO_PERM_AVALIADOR_GIOR
				,DESC_GRUPO_PERM_ITCDCONSULTA
            ,DESC_GRUPO_PERM_CCF
            ,DESC_GRUPO_ATEND_AGENFA
		 };

		 /**
		 * Construtor
		 * Instancia o domínio iniciando com o valor informado.
		 * @param   <B>parmDomnValr</B> O valor do domínio.
		 */
		 public DomnGrupoPermissao(int parmDomnValr)
		 {
					super(parmDomnValr);
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
}
