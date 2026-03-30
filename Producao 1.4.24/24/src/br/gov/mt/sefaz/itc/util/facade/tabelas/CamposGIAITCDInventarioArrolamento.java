package br.gov.mt.sefaz.itc.util.facade.tabelas;

public interface CamposGIAITCDInventarioArrolamento
{
	public static final String CAMPO_ITCTB14_CODIGO_GIA_ITCD = "ITCTB14_CODG_ITCD"; //pk
	public static final String CAMPO_ACCTB01_NUMERO_PESSOA_MEEIRO = "ACCTB01_NUMR_PESS_MEEIRO"; //fk
	public static final String CAMPO_ACCTB01_NUMERO_PESSOA_DE_CUJUS = "ACCTB01_NUMR_PESS_DE_CUJUS"; //fk
	public static final String CAMPO_SIGLA_UF_ABERTURA = "ACCTB14_SIGL_UF_ABERTURA"; //fk
	public static final String CAMPO_SITUACAO_ESTADO_CIVIL = "SITC_ESTADO_CIVIL"; //ok
	public static final String CAMPO_REGIMENTO_CASAMENTO = "REGM_CASAMENTO"; //ok
	public static final String CAMPO_NUMERO_PROCESSO = "NUMR_PROCESSO"; //ok
	public static final String CAMPO_DATA_INVENTARIO = "DATA_INVENTARIO"; //ok
	public static final String CAMPO_DATA_FALECIMENTO = "DATA_FALECIMENTO"; //ok
	public static final String CAMPO_NUMERO_HERDEIROS = "NUMR_HERDEIROS"; //ok
	public static final String CAMPO_DESCRICAO_JUIZO_COMARCA = "DESC_JUIZO_COMARCA"; //ok
	public static final String CAMPO_FRAC_IDEAL = "FRAC_IDEAL";
	public static final String CAMPO_PERC_MULTA = "PERC_MULTA";
	public static final String CAMPO_VALR_MULTA = "VALR_MULTA";
	public static final String CAMPO_DATA_PROCESSO = "DATA_PROCESSO";
	public static final String CAMPO_TIPO_RENUNCIA = "TIPO_RENUNCIA";
	public static final String CAMPO_FLAG_RENUNCIA = "FLAG_RENUNCIA";
	public static final String CAMPO_FLAG_HERDEIROS_DESCENDENTES = "FLAG_POSS_HERD_DESCENDENTE";
	public static final String CAMPO_FLAG_HERDEIROS_ASCENDENTES = "FLAG_POSS_HERD_ASCENDENTE";
	public static final String CAMPO_FLAG_MEEIRO_BENEFICIARIO = "FLAG_MEEIRO_BENEFICIARIO";
   public static final String CAMPO_TIPO_PROCESSO_INVENTARIO = "TIPO_PROCESSO_INVENTARIO";
   
   
   
   //----------------------------- NOME AMIGUAVEL ---------------------------------------------
    public static final String NOME_AMIGAVEL_ITCTB14_CODIGO_GIA_ITCD = "Número GIA-ITCD"; //pk
    public static final String NOME_AMIGAVEL_ACCTB01_NUMERO_PESSOA_MEEIRO = "Número Pessoa Meeiro"; //fk
    public static final String NOME_AMIGAVEL_ACCTB01_NUMERO_PESSOA_DE_CUJUS = "Número Pessoa De Cujus"; //fk
    public static final String NOME_AMIGAVEL_SIGLA_UF_ABERTURA = "UF abertura"; //fk
    public static final String NOME_AMIGAVEL_SITUACAO_ESTADO_CIVIL = "Estado Civil"; //ok
    public static final String NOME_AMIGAVEL_REGIMENTO_CASAMENTO = "Regime de Casamento"; //ok
    public static final String NOME_AMIGAVEL_NUMERO_PROCESSO = "Número do Processo"; //ok
    public static final String NOME_AMIGAVEL_DATA_INVENTARIO = "Data Inventário"; //ok
    public static final String NOME_AMIGAVEL_DATA_FALECIMENTO = "Data Falecimento"; //ok
    public static final String NOME_AMIGAVEL_NUMERO_HERDEIROS = "Número de Herdeiros"; //ok
    public static final String NOME_AMIGAVEL_DESCRICAO_JUIZO_COMARCA = "Juízo/Comarca"; //ok
    public static final String NOME_AMIGAVEL_FRAC_IDEAL = "Fração Ideal";
    public static final String NOME_AMIGAVEL_PERC_MULTA = "Percentual Multa";
    public static final String NOME_AMIGAVEL_VALR_MULTA = "Valor Multa";
    public static final String NOME_AMIGAVEL_DATA_PROCESSO = "Data Processo";
    public static final String NOME_AMIGAVEL_TIPO_RENUNCIA = "Tipo Renúncia";
    public static final String NOME_AMIGAVEL_FLAG_RENUNCIA = "Renúncia";
    public static final String NOME_AMIGAVEL_FLAG_HERDEIROS_DESCENDENTES = "Herdeiro Descendente";
    public static final String NOME_AMIGAVEL_FLAG_HERDEIROS_ASCENDENTES = "Herdeiro Ascendente";
    public static final String NOME_AMIGAVEL_FLAG_MEEIRO_BENEFICIARIO = "Meeiro";
   public static final String NOME_AMIGAVEL__TIPO_PROCESSO_INVENTARIO = "Tipo de Processo Inventário";
	
}
