function myFunction() {
  // Declare variables
  var input, filter, ul, li, a, i, txtValue;
  input = document.getElementById('myInput');
  filter = input.value.toUpperCase();
  ul = document.getElementById("myUL");
  li = ul.getElementsByTagName('li');

  // Loop through all list items, and hide those who don't match the search query
  for (i = 0; i < li.length; i++) {
    a = li[i].getElementsByTagName("a")[0];
    txtValue = a.textContent || a.innerText;
    if (txtValue.toUpperCase().indexOf(filter) > -1) {
      li[i].style.display = "";
    } else {
      li[i].style.display = "none";
    }
  }
}

var paginaAtiva = "";
function mousein(name) {
	document.getElementById("divMenu1").style.visibility = "visible";
	document.getElementById("divMenu1").style.zIndex  = "10";
	document.getElementById("divMenu1").style.display = "grid";
	
	if (name == "divMenu1"){
		paginaAtiva = name;
		document.getElementById("divMenu-sub-item1").innerHTML = "Cacheado";
		document.getElementById("divMenu-sub-item2").innerHTML = "Liso";
		document.getElementById("divMenu-sub-item3").innerHTML = "Ondulado";
		document.getElementById("divMenu-sub-item4").innerHTML = "Crespo";
		document.getElementById("divMenu-sub-item5").innerHTML = "Sem Definição";
	}else if (name == "divMenu2"){
		paginaAtiva = name;
		document.getElementById("divMenu-sub-item1").innerHTML = "Shampoo";
		document.getElementById("divMenu-sub-item2").innerHTML = "Creme";
		document.getElementById("divMenu-sub-item3").innerHTML = "Condicionador";
		document.getElementById("divMenu-sub-item4").innerHTML = "Hidratação";
		document.getElementById("divMenu-sub-item5").innerHTML = "Finalização";
		
	}else if (name == "divMenu3"){
		paginaAtiva = name;
		document.getElementById("divMenu-sub-item1").innerHTML = "Skala";
		document.getElementById("divMenu-sub-item2").innerHTML = "Soul Power";
		document.getElementById("divMenu-sub-item3").innerHTML = "Embeleze";
		document.getElementById("divMenu-sub-item4").innerHTML = "Salon Line";
		document.getElementById("divMenu-sub-item5").innerHTML = "";

	}else if (name == "divMenu5"){
		paginaAtiva = name;
		document.getElementById("divMenu-sub-item1").innerHTML = "Caseiro";
		document.getElementById("divMenu-sub-item2").innerHTML = "Teste de Cabelo";
		document.getElementById("divMenu-sub-item3").innerHTML = "Cronograma Capilar";
		document.getElementById("divMenu-sub-item4").innerHTML = "";
		document.getElementById("divMenu-sub-item5").innerHTML = "";
	}else if (name == "divMenu4"){
		document.getElementById("divMenu1").style.visibility = "hidden";
	}
}

function mouseininterno(name) {
	document.getElementById("divMenu1").style.visibility = "visible";
	document.getElementById("divMenu1").style.zIndex  = "10";
	document.getElementById("divMenu1").style.display = "grid";
	
	if (name == "divMenu1"){
		paginaAtiva = name;
		document.getElementById("divMenu-sub-item1").innerHTML = "Marcas";
		document.getElementById("divMenu-sub-item2").innerHTML = "Produtos";
		document.getElementById("divMenu-sub-item3").innerHTML = "Administrador";
		document.getElementById("divMenu-sub-item4").innerHTML = "";
		document.getElementById("divMenu-sub-item5").innerHTML = "";
	}else if (name == "divMenu2"){
		paginaAtiva = name;
		document.getElementById("divMenu-sub-item1").innerHTML = "Pedidos em aberto";
		document.getElementById("divMenu-sub-item2").innerHTML = "";
		document.getElementById("divMenu-sub-item3").innerHTML = "";
		document.getElementById("divMenu-sub-item4").innerHTML = "";
		document.getElementById("divMenu-sub-item5").innerHTML = "";
		
	}else if (name == "divMenu3"){
		paginaAtiva = name;
		document.getElementById("divMenu-sub-item1").innerHTML = "Saldo Atual";
		document.getElementById("divMenu-sub-item2").innerHTML = "Consulta Pedidos";
		document.getElementById("divMenu-sub-item3").innerHTML = "";
		document.getElementById("divMenu-sub-item4").innerHTML = "";
		document.getElementById("divMenu-sub-item5").innerHTML = "";

	}else if (name == "divMenu5"){
		paginaAtiva = name;
		document.getElementById("divMenu-sub-item1").innerHTML = "";
		document.getElementById("divMenu-sub-item2").innerHTML = "";
		document.getElementById("divMenu-sub-item3").innerHTML = "";
		document.getElementById("divMenu-sub-item4").innerHTML = "";
		document.getElementById("divMenu-sub-item5").innerHTML = "";
	}else if (name == "divMenu4"){
		document.getElementById("divMenu1").style.visibility = "hidden";
	}
}

function redirectSubPageInterno(subpage){
	if (paginaAtiva == "divMenu1") {
		if(subpage == "subpage1"){
			//BUILD PAGE FOR PRODUTO, EXAMPLE
			document.getElementById("titulo-DPaulla").innerHTML = "Cadastro de Marcas";
			document.getElementById("section-acessorestrito-container-marcas").style.display = "grid";
			document.getElementById("section-acessorestrito-container-marcas").style.visibility = "visible";
			document.getElementById("section-acessorestrito-container-botoes").style.visibility = "visible";
			//AGORA INATIVA TODOS OS DIVS.
			document.getElementById("section-acessorestrito-container-produto").style.visibility = "hidden";
			document.getElementById("section-acessorestrito-container-produto").style.display = "none";
			document.getElementById("btn-dpaulla-novo").value = "marca";
			
		}else if (subpage == "subpage2"){
			document.getElementById("titulo-DPaulla").innerHTML = "Cadastro de Produto";
			document.getElementById("section-acessorestrito-container-marcas").style.visibility = "hidden";
			document.getElementById("section-acessorestrito-container-marcas").style.display = "none";
			document.getElementById("section-acessorestrito-container-produto").style.display = "grid";
			document.getElementById("section-acessorestrito-container-produto").style.visibility = "visible";
			document.getElementById("section-acessorestrito-container-botoes").style.visibility = "visible";
			
			document.getElementById("btn-dpaulla-novo").value = "produto";
			
		}else if (subpage == "subpage3"){
			document.getElementById("titulo-DPaulla").innerHTML = "Cadastro de Administradores";	
		}else if (subpage == "subpage4"){
			window.location.replace("/tipos_de_cabelo?crespo");	
		}else if (subpage == "subpage5"){
			window.location.replace("/tipos_de_cabelo?semdefinicao");	
		}
	}else if (paginaAtiva == "divMenu2") {
		if(subpage == "subpage1"){
			window.location.replace("/para_seu_cabelo?shampoo");	
		}else if (subpage == "subpage2"){
			window.location.replace("/para_seu_cabelo?creme");	
		}else if (subpage == "subpage3"){
			window.location.replace("/para_seu_cabelo?condicionador");	
		}else if (subpage == "subpage4"){
			window.location.replace("/para_seu_cabelo?hidratacao");	
		}else if (subpage == "subpage5"){
			window.location.replace("/para_seu_cabelo?finalizacao");	
		}
	}else if (paginaAtiva == "divMenu3") {
		if(subpage == "subpage1"){
			window.location.replace("/marca?p=Skala");	
		}else if (subpage == "subpage2"){
			window.location.replace("/marca?p=Soul Power");	
		}else if (subpage == "subpage3"){
			window.location.replace("/marca?p=Embeleze");	
		}else if (subpage == "subpage4"){
			window.location.replace("/marca?p=Salon Line");	
		}else if (subpage == "subpage5"){
			window.location.replace("/");	
		}
	}else if (paginaAtiva == "divMenu5") {
		if(subpage == "subpage1"){
			window.location.replace("/dicas?caseiro");	
		}else if (subpage == "subpage2"){
			window.location.replace("/dicas?testedecabelo");	
		}else if (subpage == "subpage3"){
			window.location.replace("/dicas?cronograma");	
		}else if (subpage == "subpage4"){
			window.location.replace("/");	
		}else if (subpage == "subpage5"){
			window.location.replace("/");	
		}
	}
}
function internoCadastroRadioSelected(){
	document.getElementById("btn-dpaulla-editar").style.visibility = "visible";
	document.getElementById("btn-dpaulla-excluir").style.visibility = "visible";
}

function internoCadastroNovo(page){
	if(page == "marca"){
		window.location.replace("/acesso-restrito-cadastrar-marca");	
	}else if(page == "produto"){
		window.location.replace("/acesso-restrito-cadastrar-produto");	
	}
}

function mouseout() {
		document.getElementById("divMenu1").style.visibility = "hidden";
		document.getElementById("divMenu1").style.display = "none";		
}


function redirectPage(page){
	if(page == "page1"){
		window.location.replace("/tipos_de_cabelo");	
	}else if (page == "page2"){
		window.location.replace("/para_seu_cabelo");	
	}else if (page == "page3"){
		window.location.replace("/marcas");	
	}else if (page == "page4"){
		window.location.replace("/promocoes");	
	}else if (page == "page5"){
		window.location.replace("/dicas");	
	}
}

function redirectSubPage(subpage){
	if (paginaAtiva == "divMenu1") {
		if(subpage == "subpage1"){
			window.location.replace("/tipos_de_cabelo?cacheado");	
		}else if (subpage == "subpage2"){
			window.location.replace("/tipos_de_cabelo?liso");	
		}else if (subpage == "subpage3"){
			window.location.replace("/tipos_de_cabelo?ondulado");	
		}else if (subpage == "subpage4"){
			window.location.replace("/tipos_de_cabelo?crespo");	
		}else if (subpage == "subpage5"){
			window.location.replace("/tipos_de_cabelo?semdefinicao");	
		}
	}else if (paginaAtiva == "divMenu2") {
		if(subpage == "subpage1"){
			window.location.replace("/para_seu_cabelo?shampoo");	
		}else if (subpage == "subpage2"){
			window.location.replace("/para_seu_cabelo?creme");	
		}else if (subpage == "subpage3"){
			window.location.replace("/para_seu_cabelo?condicionador");	
		}else if (subpage == "subpage4"){
			window.location.replace("/para_seu_cabelo?hidratacao");	
		}else if (subpage == "subpage5"){
			window.location.replace("/para_seu_cabelo?finalizacao");	
		}
	}else if (paginaAtiva == "divMenu3") {
		if(subpage == "subpage1"){
			window.location.replace("/marca?p=Skala");	
		}else if (subpage == "subpage2"){
			window.location.replace("/marca?p=Soul Power");	
		}else if (subpage == "subpage3"){
			window.location.replace("/marca?p=Embeleze");	
		}else if (subpage == "subpage4"){
			window.location.replace("/marca?p=Salon Line");	
		}else if (subpage == "subpage5"){
			window.location.replace("/");	
		}
	}else if (paginaAtiva == "divMenu5") {
		if(subpage == "subpage1"){
			window.location.replace("/dicas?caseiro");	
		}else if (subpage == "subpage2"){
			window.location.replace("/dicas?testedecabelo");	
		}else if (subpage == "subpage3"){
			window.location.replace("/dicas?cronograma");	
		}else if (subpage == "subpage4"){
			window.location.replace("/");	
		}else if (subpage == "subpage5"){
			window.location.replace("/");	
		}
	}
}


function actionPostRemoverMeuCarrinho(id) {
	var tempId = id;
	window.location.replace("/meuCarrinhoRemoverProduto?p="+id);
}

function actionCarrinhoRealizarPagamento() {
	window.location.replace("/finalizar-pagamento");
}

function actionVerificaItensCarrinho(comprasListSession){
	alert("hello");
	console.log("session:"+comprasListSession);
}

function calculafrete(valor) {
    var cep = valor.replace(/\D/g, '');
    if (cep != "") {
    	window.location.replace("/valorFrete?frete="+cep);
    } 
}   


var ALERT_TITLE = "DPaulla Alerta!";
var ALERT_BUTTON_TEXT = "Ok";

function alertaCustomizadoInterno(txt) {
    d = document;

    if(d.getElementById("modalContainer")) return;

    mObj = d.getElementsByTagName("body")[0].appendChild(d.createElement("div"));
    mObj.id = "modalContainer";
    mObj.style.height = d.documentElement.scrollHeight + "px";

    alertObj = mObj.appendChild(d.createElement("div"));
    alertObj.id = "alertBox";
    if(d.all && !window.opera) alertObj.style.top = document.documentElement.scrollTop + "px";
    alertObj.style.left = (d.documentElement.scrollWidth - alertObj.offsetWidth)/2 + "px";
    alertObj.style.visiblity="visible";

    h1 = alertObj.appendChild(d.createElement("h1"));
    h1.appendChild(d.createTextNode(ALERT_TITLE));

    msg = alertObj.appendChild(d.createElement("p"));
    //msg.appendChild(d.createTextNode(txt));
    msg.innerHTML = txt;

    btn = alertObj.appendChild(d.createElement("a"));
    btn.id = "closeBtn";
    btn.appendChild(d.createTextNode(ALERT_BUTTON_TEXT));
    btn.href = "#";
    btn.focus();
    btn.onclick = function() { removeAlertaCustomizadoInterno();return false; }

    alertObj.style.display = "block";

}

function removeAlertaCustomizadoInterno() {
    document.getElementsByTagName("body")[0].removeChild(document.getElementById("modalContainer"));
}	


/* $(".section-meucarrinhoVisualizacao-view-produtos-iconeremover").click(function(){
id = $(this).attr('id').toString();
console.log(id);
$.post("localhost:8080/meuCarrinhoRemoverProduto",
{
  id: id
},
function(data, status){
  alert("Data: " + data + "\nStatus: " + status);
});
}); */
/* $(".section-meucarrinhoVisualizacao-view-produtos-iconeremover").click(function(){
id = $(this).attr('id').toString();
log.console(id);
$.post("meuCarrinhoRemoverProduto",
{
  name: "Donald Duck",
  city: "Duckburg"
},
function(data, status){
  alert("Data: " + data + "\nStatus: " + status);
});
});	
*/
