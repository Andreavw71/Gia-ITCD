/*************************************************************************
Função para comparar se a data inicial de um período é maior que 
a data final de outro período

Parâmetros:
dtInicial - string da data inicial
dtFinal - string da data final
Formato: 
dd/mm/aaaa

Válido para: 
IE, Opera, Netscape, Mozilla, Firefox

Exemplo: 
v_retorno = comparaDatas(p_form.dtInicial.value, p_form.dtFinal.value);
if (!v_retorno)
return v_retorno;

*************************************************************************/
function comparaDatasPeriodo(dtInicial, dtFinal) {
	// Seta as variáveis com dia, mês e ano separados
	v_diainicio = dtInicial.substr(0,2);
	v_mesinicio = dtInicial.substr(3,2);
	v_anoinicio = dtInicial.substr(6,4);
	v_diafim = dtFinal.substr(0,2);
	v_mesfim = dtFinal.substr(3,2);
	v_anofim = dtFinal.substr(6,4);
	
	
	// Depois concatena no formato YYYYMMDD para depois comparar
	v_dtinicio = v_anoinicio + v_mesinicio + v_diainicio;
	v_dtfim = v_anofim + v_mesfim + v_diafim;
	
	// Compara as datas no formato YYYYMMDD, se a inicio for maior que a fim, dá mensagem de erro ao usuário
	if (v_dtinicio > v_dtfim) 
	{
		return false;
	}
	return true;
}
