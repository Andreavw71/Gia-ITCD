/**
  * Secretaria de Estado de Fazenda de Mato Grosso - Sefaz-MT
  * Arquivo : itcd.js
  * Criacao : Novembro de 2007
  * Revisao : $Revision: 1.1.1.1 $
  * Log :
  * Autor: Leandro Dorileo
  * Descrição : Função interna que retorna sempre o novo objeto.
  * $Id: itcd.js,v 1.1.1.1 2008/05/28 17:55:08 lucas.nascimento Exp $
  */

/**
* @Autor: Leandro Dorileo.
* @Data Criação: Nov/2007.
* @Revisão: Wendell Pereira de Farias - Fev/2008.
* Função interna que retorna sempre o novo objeto.
* Não é necessário fornecer parametros.
*   Exemplo:
*               Object.prototype.clone = function().
*/
Object.prototype.clone = function(){
  newObj = new Object();
  for(posicaoObjAtual in this)
  {
    if(typeof(this[posicaoObjAtual]) == 'object')
    {
      newObj[posicaoObjAtual] = clone(this[posicaoObjAtual]);
    }
    else
    {
      newObj[posicaoObjAtual] = this[posicaoObjAtual];
    }
  }
  return newObj;
};

/**
* @Autor: Leandro Dorileo.
* @Data Criação: Nov/2007.
* @Revisão: Wendell Pereira de Farias - Fev/2008.
* Objeto vertor ou matriz que representa o repositorio da funcionalidade Abas.
*   Exemplo:
*               Abas.add(abaAtual);
*/
var Abas = Abas || {};

/**
* @Autor: Leandro Dorileo.
* @Data Criação: Nov/2007.
* @Revisão: Wendell Pereira de Farias - Fev/2008.
* Constroi uma nova estrutura do tipo Aba e a registra na lista de abas. 
* É necessário fornecer:
* @urlOuFuncaoUtilizada	         url a ser chamada para onde o evento click irão redirecionar ou uma funcao parametrizada ou não,a ser chamada
*			                                 também no evento de click;
* @descricaoOuRotulo	         descrição ou rotúlo da aba(o que vai aparecer escrito na aba);
* @tiposelecao                             determina o tipo de seleção da aba com cor diferente ou não;
* @id			                         identificação, usado na construstrução  das divs
* @abaOuLink		                 determina se a entrada serão uma aba comum ou simplemente um link na mesma barra
* @parametrosFuncao                Representa os parametros enciados.
*
* OBS: Não reenderiza apenas faz o registro.
*   Exemplo:
*               function(urlOuFuncaoUtilizada, descricaoOuRotulo, tiposelecao, id, abaOuLink, parametrosFuncao);
*               Abas.registraAba(solicitarAjuda, "Ajuda",  false,'link-Ajuda', false, ['<c:out value="${requestScope.atributoTituloFuncionalidade}" ><c:out>', '<abaco:contexto></abaco:contexto>']);
*/
Abas.registraAba = function(urlOuFuncaoUtilizada, descricaoOuRotulo, tiposelecao, id, abaOuLink, parametrosFuncao){
  var abaConstruida = Aba.clone();
  abaConstruida.setMembers(urlOuFuncaoUtilizada, descricaoOuRotulo, tiposelecao, id, abaOuLink, parametrosFuncao);
  Abas.add(abaConstruida);
};

/**
* @Autor: Leandro Dorileo.
* @Data Criação: Nov/2007.
* @Revisão: Wendell Pereira de Farias - Fev/2008.
* Funcionalidade responsável por iniciar a apresetação da lista de abas.
* É necessário fornecer:
* @abaAtual		aba a ser adicionada na lista de abas.
*   Exemplo:
*               function(abaAtual).
*               Abas.add(abaConstruida);
*/
Abas.add = function(abaAtual)
{
  if(Abas.first == null)
  {
    Abas.first = abaAtual;
   }
  else
  {
    Abas.add.append(abaAtual);
  }
};

/**
* @Autor: Leandro Dorileo.
* @Data Criação: Nov/2007.
* @Revisão: Wendell Pereira de Farias - Fev/2008.
* Funcionalidade Adiciona uma nova aba proxima da ulltima e configura a prev ( anterior) da atual como a penUltima(Ultima atenção).
* É necessário fornecer:
* @abaAtual		aba a ser adicionada na penultima posição da lista de abas.
*   Exemplo:
*               function(abaAtual).
*               Abas.add.append(abaAtual);
*/
Abas.add.append = function(abaAtual)
{
  var abaAuxiliar = Abas.first;
  while(1)
  {
    if(abaAuxiliar.next == null)
    {
      abaAuxiliar.next = abaAtual;
      abaAtual.prev = abaAuxiliar;
      break;
    }
    else
    {
      abaAuxiliar = abaAuxiliar.next;
    }
  }
};

/**
* @Autor: Leandro Dorileo.
* @Data Criação: Nov/2007.
* @Revisão: Wendell Pereira de Farias - Fev/2008.
* Funcionalidade responsável por enquanto tiver abas a serem percorriadas, vai retornando a proxima.
* É necessário fornecer:
* @ultimaAbaEncontrada		a Ultima aba encontrada.
* Exemplo:
*               function(ultimaAbaEncontrada).
*               Abas.hasnext(ultimaAbaEncontrada);
*/
Abas.hasnext = function(ultimaAbaEncontrada)
{
  while(1)
  {
    if(ultimaAbaEncontrada.next != null)
    {
      return ultimaAbaEncontrada.next;
    }
    else
    {
      return false;
    }
  }
};

/**
* @Autor: Leandro Dorileo.
* @Data Criação: Nov/2007.
* @Revisão: Wendell Pereira de Farias - Fev/2008.
* Funcionalidade responsável por reenderiza as abas em si.(Atualiza apresentação da aba e seus elementos).
* OBS: Não é necessário fornecer parametros.
*.
* Exemplo:
*               function().
*               Abas.render();
*/
Abas.render = function(){
  Abas.render.setStyle();
  var abaAuxiliar = Abas.first;
  var objetoElementoDocumentoAtual = document.getElementById('abas');
  if(objetoElementoDocumentoAtual == null)
  {
    alert('div abas faltando. Abas nao serao exibidas.');
    return false;
  }
  do
  {
   objetoElementoDocumentoAtual.innerHTML += '<div id="'+abaAuxiliar.id+'" class="'+abaAuxiliar.sClass+'">'+abaAuxiliar.descricaoOuRotulo+'</div>';
  }while(abaAuxiliar = Abas.hasnext(abaAuxiliar));
  Abas.setHandlers();
};

/**
* @Autor: Leandro Dorileo.
* @Data Criação: Nov/2007.
* @Revisão: Wendell Pereira de Farias - Fev/2008.
* Funcionalidade responsável por configurar os event handlers de cada aba. Percorre cada aba na lista e registra o seu handler.
* OBS: Não é necessário fornecer parametros.
*.
* Exemplo:
*               function().
*               Abas.setHandlers();
*/
Abas.setHandlers = function(){
  var abaAuxiliar = Abas.first;
  do
  {
    Aba.clickHandler(abaAuxiliar.id);
  }while(abaAuxiliar = Abas.hasnext(abaAuxiliar));
};

/**
* @Autor: Leandro Dorileo.
* @Data Criação: Nov/2007.
* @Revisão: Wendell Pereira de Farias - Fev/2008.
* Funcionalidade responsável por configura os event handlers de cada aba. Percorre cada aba na lista e registra o seu handler.
* É necessário fornecer:
* @id		identificação do objeto selecionado ou clicado.
* Exemplo:
*               function(id).
*              Abas.chamarUrlOuFuncaoUtilizada(this.id);
*/
Abas.chamarUrlOuFuncaoUtilizada = function(id){
  var abaAuxiliar = Abas.first;
  do
  {
    if(abaAuxiliar.id == id)
    {
      if(typeof(abaAuxiliar.urlOuFuncaoUtilizada) == 'string')
      {
	window.location = abaAuxiliar.urlOuFuncaoUtilizada;
      }
      if(typeof(abaAuxiliar.urlOuFuncaoUtilizada) == 'function')
      {
			if(abaAuxiliar.parametrosFuncao!=null)
			{
				abaAuxiliar.urlOuFuncaoUtilizada.apply(null, abaAuxiliar.parametrosFuncao);
			}
			else
			{
				abaAuxiliar.urlOuFuncaoUtilizada.apply();
			}
      }
    }
  }while(abaAuxiliar = Abas.hasnext(abaAuxiliar));
};

/**
* @Autor: Leandro Dorileo.
* @Data Criação: Nov/2007.
* @Revisão: Wendell Pereira de Farias - Fev/2008.
* Funcionalidade responsável por verificar de acordo com a posição de cada aba e do status de selecionado determina o class css para a div de cada aba.
* OBS: Não é necessário fornecer parametros.
*
* Exemplo:
*               function().
*               Abas.render.setStyle();
*/
Abas.render.setStyle = function(){
  var abaAuxiliar = Abas.first;
  var objetoElementoDocumentoAtual = document.getElementById('abas');
  var abaAnterior;
  do
  {
     if(abaAuxiliar.sClass != null)
     {
        continue;
      }
      else if(abaAuxiliar.tiposelecao && abaAuxiliar.prev == null)
      {
        abaAuxiliar.sClass = 'aba-esq-azul-branco';
        abaAuxiliar.next.sClass = 'aba-dir-azul';
      }
      else if(abaAuxiliar.tiposelecao && abaAuxiliar.next == null)
      {
        abaAuxiliar.sClass = 'aba-dir-azul-branco';
      }
      else if(abaAuxiliar.tiposelecao && abaAuxiliar.next != null)
      {
        abaAuxiliar.sClass = 'aba-esq-azul';
        abaAuxiliar.next.sClass = (abaAuxiliar.next.abaOuLink) ? "aba-dir-azul" : "aba-dir-azul-branco";
      }
      else if(!abaAuxiliar.tiposelecao && abaAuxiliar.prev == null)
      {
        abaAuxiliar.sClass = 'aba-esq-cinza-branco';
      }
		else if(abaAuxiliar.abaOuLink == false)
      {
			if(abaAuxiliar.prev.abaOuLink == false)
			{
			  abaAuxiliar.sClass = 'aba-dir-branco-fim';
			}
			else if(abaAuxiliar.prev.tiposelecao)
			{
				abaAuxiliar.sClass = 'aba-dir-branco-fim';
			}
			else
			{
				abaAuxiliar.sClass = 'aba-dir-cinza-fim';
			}
		}
      else
      {
        abaAuxiliar.sClass = 'aba-dir-cinza';
      }
		abaAnterior = abaAuxiliar;
    }while(abaAuxiliar = Abas.hasnext(abaAuxiliar));
};

/**
* @Autor: Leandro Dorileo.
* @Data Criação: Nov/2007.
* @Revisão: Wendell Pereira de Farias - Fev/2008.
* Objeto vertor ou matriz que representa o repositorio da funcionalidade da Aba.
* OBS: Veja funcionalidade Aba.setMembers.
* Exemplo:
*               Aba.setMembers (urlOuFuncaoUtilizada, descricaoOuRotulo, tiposelecao, id, abaOuLink, parametrosFuncao);
*/
var Aba = Aba || {};

/**
* @Autor: Leandro Dorileo.
* @Data Criação: Nov/2007.
* @Revisão: Wendell Pereira de Farias - Fev/2008.
* Funcionalidade responsável por configura os atributos do objeto Aba de acordo com os valores informados nos parametros. 
* É necessário fornecer:
* @urlOuFuncaoUtilizada	         url a ser chamada para onde o evento click irão redirecionar ou uma funcao parametrizada ou não,a ser chamada
*			                                 também no evento de click;
* @descricaoOuRotulo	         descrição ou rotúlo da aba(o que vai aparecer escrito na aba);
* @tiposelecao                             determina o tipo de seleção da aba com cor diferente ou não;
* @id			                         identificação, usado na construstrução  das divs
* @abaOuLink		                 determina se a entrada serão uma aba comum ou simplemente um link na mesma barra
* @parametrosFuncao                Representa os parametros enciados.
*
*   Exemplo:
*               function(urlOuFuncaoUtilizada, descricaoOuRotulo, tiposelecao, id, abaOuLink, parametrosFuncao);
*               Aba.setMembers(solicitarAjuda, "Ajuda",  false,'link-Ajuda', false, ['<c:out value="${requestScope.atributoTituloFuncionalidade}" ><c:out>', '<abaco:contexto></abaco:contexto>']);
*/
Aba.setMembers = function(urlOuFuncaoUtilizada, descricaoOuRotulo, tiposelecao, id, abaOuLink, parametrosFuncao){
  this.urlOuFuncaoUtilizada = urlOuFuncaoUtilizada;
  this.parametrosFuncao = parametrosFuncao;
  this.descricaoOuRotulo = descricaoOuRotulo;
  this.tiposelecao = (abaOuLink) ? tiposelecao : false;
  this.id = id;
  this.next = null;
  this.prev = null;
  this.sClass = null;
  this.background = false;
  this.abaOuLink = abaOuLink;
};

/**
* @Autor: Leandro Dorileo.
* @Data Criação: Nov/2007.
* @Revisão: Wendell Pereira de Farias - Fev/2008.
* Funcionalidade responsável por Event handler das abas. executa a função aninhada abaixo no momento da ocorrencia do evento determinado, no caso onclick.
* É necessário fornecer:
* @identificacaoAbaAtual		identificação da aba, usado para localizar a estrutura
*			                                apropriada e executar a callback de acordo com o solicitado
*			                                no momento do registro da aba.
* Exemplo:
*               function(identificacaoAbaAtual).
*              Aba.clickHandler(identificacaoAbaAtual);
*/
Aba.clickHandler = function(identificacaoAbaAtual){
   var objetoElementoDocumentoAtual = document.getElementById(identificacaoAbaAtual);
   objetoElementoDocumentoAtual.onclick = function()
   {
     Abas.chamarUrlOuFuncaoUtilizada(this.id);
   };
};

/*		
	Função responsável por substituir várias ocorrências
	de um valor proposto pelo usuário.
	@Parametros: conteudo(Conteúdo onde será realizado a permuta de valores)
						             valorConteudo(caracter a ser substituído)
										 novoValor(caracter proposto para substituição)
	@Autor:: Maxwell Rocha
*/
function replaceAll(conteudo, valorConteudo, novoValor ) {
	var valor = conteudo.indexOf( valorConteudo );
	while ( valor > -1 ) {
		conteudo = conteudo.replace(valorConteudo, novoValor );
		valor = conteudo.indexOf( valorConteudo );
	}
	return conteudo;
}

/*
* @Autor: Wendell Pereira de Farias.
* @Data Criação: 28/01/2008.
* Funcionalidade responsável por inserir o BOTAO_AJUDA
* É necessário fornecer:
*            1-   O objeto documento da página. @documento
*            2-   O nome do Botão. @nomeBotao
*            3-   O texto do Botão. @textoBotao
*            4-   O id do Botão. @idBotao
*            5-   Insira uma tag <div> na posição desejada para inserir o botão
*           OBS: O id da tag <div> deve ser o mesmo nome do botão.
*   Exemplo:
*               botaoAjuda(documento,nomeBotao, textoBotao,idBotao,contexto).
*               botaoAjuda(Document,<%=Form.BOTAO_AJUDA%>, <%=Form.TEXTO_BOTAO_AJUDA%>, <c:out value="${requestScope.atributoTituloFuncionalidade}" />)
*/
function botaoAjuda(documento,nomeBotao, textoBotao,idBotao, contexto)
{
    documento.getElementById(nomeBotao).innerHTML = '<input type="button" name="'+nomeBotao+ '" value="'+textoBotao+'" class="SEFAZ-INPUT-Botao" id="'+idBotao+ '" onClick="solicitarAjuda(this.id,'+contexto+');"'+ '/>';
}

/*
* Funcionalidade responsável por gerenciar a janela de apresentação do Modulo Ajuda.
* É necessário fornecer:
*           1- O parametro de pesquisa, identificador do título da funcionalidade
* OBS: Este valor é capturado pela funcionalidade botaoAjuda(documento,nomeBotao, textoBotao,idBotao,contexto). 
*            Atroca do caracter barra "/" pelo numero "11" é utilizado pela necessidade do Internet Explorer de não aceitar caracteres especiais.
*/
function solicitarAjuda(tituloFuncionalidade,contexto)
{
    var url = contexto+'tabelabasica/ajuda/ViewApresentarAjuda.jsp'; 
    tituloFuncionalidadeAux = replaceAll(tituloFuncionalidade, " ", "_");
    tituloFuncionalidadeAux = replaceAll(tituloFuncionalidadeAux, "/", "11");
    tituloFuncionalidadeAux = replaceAll(tituloFuncionalidadeAux, "-", "22");
    window.open(url, tituloFuncionalidadeAux, "location=no, toolbar=no, directories=no, menubar=no, status=no, scrollbars=yes, resizeble=yes, height=450, width=460");
}

/*
* Oculta o elemento informa.
* Como parametro deve ser informado o ID do elemento
* o qual deseja ocultar.
*/
function ocultarElemento(id) {
   document.getElementById(id).style.display = "none";
}

/*
* Exibe o elemento ocultado com a funcao ocultarElemente(id);
* Como parametro deve ser informado o ID do elemento
* o qual deseja exibir.
*/
function exibirElemento(id) {
   document.getElementById(id).style.display = "block";
}

/*
* Utilizado para fazer a navegação para página de LOG.
*
*
*/
function detalharlog()
{
 //     document.form.action='<%=FormAcesso.getUrlServletOriginal(JspUtil.getCodigoPermissaoOriginal(request), request)+"?"+PARAMETRO_DETALHAR_LOG+"=99"%>';
       document.form.action='/itc/generico/acompanhamento/ViewDetalhamentoLog.jsp';
      document.form.submit();

}


function identificarBrowser(){
    var nav = navigator.userAgent.toLowerCase();
    var browser;
    if(nav.indexOf("msie") != -1){
        browser = "msie";
        
    }else if(nav.indexOf("opera") != -1){
        browser = "opera";
    }else if(nav.indexOf("mozilla") != -1){
        if(nav.indexOf("firefox") != -1){
            browser = "firefox";
        }else if(nav.indexOf("firefox") != -1){
            browser = "mozilla";
        }else if(nav.indexOf("chrome") != -1){
            browser = "chrome";
        }
        alert("Por favor! utilize os navegadores Mozilla Firefox ou Google Chrome!");
    }else{
	alert("Navegador desconhecido!");
    }
}

//-----------------------------------------------------------FUNCOES ADICIONAIS--------------------------------------------------

/*
* Este método tem por função comparar o valor
* dois campos numericos.
* Retornanco true caso o valor do campoX seja
* menor que valor do campoY
*
*/
function campoXmenorQueCampoY(idCampoX , idCampoY){
   var campoX = document.getElementById(idCampoX).value;
   var campoY = document.getElementById(idCampoY).value;
   if(campoX < campoY){
      return true;
   }
   return false;
}


