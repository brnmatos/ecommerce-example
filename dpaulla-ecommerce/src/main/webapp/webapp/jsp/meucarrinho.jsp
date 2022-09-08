<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1">
  <link rel="shortcut icon" href="assets/images/logo-90x66.png" type="image/x-icon">
  <meta name="description" content="">
  <link rel="stylesheet" href="assets/style.css">
  
  <script type="text/javascript" charset="UTF-8" src="assets/dpaulla-scripts.js"></script>
  <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
  <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js"></script>
  
  <title>Meu Carrinho</title>
  
</head>
<body onload="calculaValorReais(); verificaUsarioLogado();" class="body-left">
	<div class="section-body">
		<div class="section-menu-principal-view">
			<div class="section-menu-principal-view-logo">
				<a href="/"><img src="assets/images/logo_transparent.png" alt="DPaulla" class="section-menu-principal-view-logo-logo"></img></a>
			</div>
			<div class="section-menu-principal-view-login">
					<img src="assets/icons/icons-female-user.png" alt="icons-female-user" class="icone-imagem"></img>
					<a href="/login" class="icone-legenda"> 
						<c:if test="${pageContext.request.userPrincipal.name != null}">
		        			Olá, ${user.nome}
						</c:if> 
						<c:if test="${pageContext.request.userPrincipal.name == null}">
			        		Login
			    		</c:if>
					</a>
			</div>
			<div class="section-menu-principal-view-meuspedidos">
				<img src="assets/icons/icons-address-book.png" alt="icons-book" class="icone-imagem"></img>
				<a href="/meus-pedidos" class="icone-legenda">
					Meus Pedidos
				</a> 
			</div>
			<div class="section-menu-principal-view-faleconosco">
				<img src="assets/icons/icons-call-female.png" alt="icons-call-female" class="icone-imagem"></img>
				<a href="/fale-conosco" class="icone-legenda">
					Fale Conosco
				</a>
			</div>
			<div class="section-menu-principal-view-sairoculto">
				<c:if test="${pageContext.request.userPrincipal.name != null}">
					<form id="logoutForm" method="GET" action="${contextPath}/logout">
            			<button type="submit" name="username">
            				<img src="assets/icons/icons-exit.png" alt="icons-exit" class="icone"></img>
           				</button>
        			</form>
				</c:if>
			</div>
			<div class="section-menu-principal-view-listadesejos-meucarrinho">
				<div class="section-menu-principal-view-listadesejos">
					<img src="assets/icons/icons-page.png" alt="icons-shopping-cart" class="icone-imagem"></img>
					<a href="/lista-desejos" class="icone-legenda white-link">
						Lista de Desejos
					</a>
				</div>		
				<div class="section-menu-principal-view-meucarrinho">
					<img src="assets/icons/icons-shopping-cart.png" alt="icons-shopping-cart" class="icone-imagem"></img>
					<a href="/meu-carrinho" class="icone-legenda white-link">
						Meu Carrinho (${quantidadeProduto})
					</a>
				</div>		
			</div>
			<div class="section-menu-principal-view-menusecundario">
				<a class="white-link"> Minha Conta </a>
				<a class="white-link"> Quem Somos </a>
				<a class="white-link"> Política do Site</a>
				<a class="white-link"> FAQ</a>
			</div>
			
			<div class="section-menu-principal-view-busca">
				<input name="buscar" type="text" placeholder="Buscar" class="text-buscar"/>
				<button type="submit" class="btn-dpaulla button-buscar" name="name">Buscar</button>
			</div>

			<div class="menuCategoriasPrincipal section-menu-principal-view-menucategorias" onmouseleave="mouseout()">
				<div class="menuCategoriasPrincipal-grupoitem1">
					<div class="menuCategoriasPrincipal-item" onclick="redirectPage('page1')" onmouseenter="mousein('divMenu1')">
						TIPOS DE CABELO
					</div>
					<div class="menuCategoriasPrincipal-item" onclick="redirectPage('page2')" onmouseenter="mousein('divMenu2')">
						PARA SEU CABELO
					</div>
					<div class="menuCategoriasPrincipal-item" onclick="redirectPage('page3')" onmouseenter="mousein('divMenu3')">
						MARCAS
					</div>
					<div class="menuCategoriasPrincipal-item" onclick="redirectPage('page4')" onmouseenter="mousein('divMenu4')">
						PROMOÇÃO
					</div>
					<div class="menuCategoriasPrincipal-item" onclick="redirectPage('page5')" onmouseenter="mousein('divMenu5')">
						DICAS
					</div>
				</div>

				<div class="menuCategoriasPrincipal-grupoitem2 section-menu-principal-view-menusubcategorias">
					<div class="menuCategoriasPrincipal-item-oculto">
						<div class="divMenu1" id="divMenu1" name="divMenu1">
							<div class="divMenu-sub-item1" id="divMenu-sub-item1" onclick="redirectSubPage('subpage1')"></div>
							<div class="divMenu-sub-item2" id="divMenu-sub-item2" onclick="redirectSubPage('subpage2')"></div>
							<div class="divMenu-sub-item3" id="divMenu-sub-item3" onclick="redirectSubPage('subpage3')"></div>
							<div class="divMenu-sub-item4" id="divMenu-sub-item4" onclick="redirectSubPage('subpage4')"></div>
							<div class="divMenu-sub-item5" id="divMenu-sub-item5" onclick="redirectSubPage('subpage5')"></div>
						</div>
					</div>
				</div>
			</div>		
		</div>
		
		<div class="section-meucarrinhoVisualizacao">
			<c:if test="${comprasListSession != '[]'}">
				<div class="section-meucarrinhoVisualizacao-view">
					<div class="section-meucarrinhoVisualizacao-view-produtos" id="meucarrinho-container-produtos" onload="actionVerificaItensCarrinho(${comprasListSession})">
						<c:forEach var="comprasListSessions" items="${comprasListSession}">
							<img class="section-meucarrinhoVisualizacao-view-produtos-imagem" src="${comprasListSessions.compraProdutoImagemPrincipal}" id="meucarrinho-container-imagem_${comprasListSessions.compraProdutoId}"></img>	
							<h4 class="section-meucarrinhoVisualizacao-view-produtos-descricao descricao-produto-DPaulla" id="meucarrinho-container-descricao_${comprasListSessions.compraProdutoId}">${comprasListSessions.compraProdutoDescricao}</h4>
							<div class="section-meucarrinhoVisualizacao-view-produtos-quantidade" id="meucarrinho-container-quantidade_${comprasListSessions.compraProdutoId}">
					              <button type="button" class="btn-number section-meucarrinhoVisualizacao-view-produtos-quantidade-btn-minus" disabled="disabled" data-type="minus" data-field="quant[1]_${comprasListSessions.compraProdutoId}" name="buttonMinus_${comprasListSessions.compraProdutoId}" id="buttonMinus_${comprasListSessions.compraProdutoId}">
					                  <span class="glyphicon glyphicon-minus"></span>
					              </button>
						          <input type="text" name="quant[1]_${comprasListSessions.compraProdutoId}" class="section-meucarrinhoVisualizacao-view-produtos-quantidade-input-number" value="${comprasListSessions.compraProdutoQuantidade}" min="1" max="10" id="quantidadeValue_${comprasListSessions.compraProdutoId}">
	 				              <button type="button" class="btn-number section-meucarrinhoVisualizacao-view-produtos-quantidade-btn-plus" data-type="plus" data-field="quant[1]_${comprasListSessions.compraProdutoId}" name="buttonPlus_${comprasListSessions.compraProdutoId}" id="buttonPlus_${comprasListSessions.compraProdutoId}">
					                  <span class="glyphicon glyphicon-plus"></span>
					              </button>
			      			</div>
				 			<input type="hidden" id="meucarrinho-container-valor-hidden_${comprasListSessions.compraProdutoId}" value="${comprasListSessions.compraProdutoValor}">
							<h4 class="valor-DPaulla section-meucarrinhoVisualizacao-view-produtos-valor" id="meucarrinho-container-valor-text_${comprasListSessions.compraProdutoId}"></h4>
							<a type="submit"><img class="section-meucarrinhoVisualizacao-view-produtos-iconeremover" src="/assets/images/icone-excluir.png" id="${comprasListSessions.compraProdutoId}" onclick="actionPostRemoverMeuCarrinho(${comprasListSessions.compraProdutoId})"></img></a>
						</c:forEach>	
					</div>
					<div>
						<div class="section-meucarrinhoVisualizacao-enderecoentrega">
							<h4 class="titulo-DPaulla">Entrega</h4>
							<div class="section-meucarrinhoVisualizacao-enderecoentrega-usuariologado" id="section-meucarrinhoVisualizacao-enderecoentrega-usuariologado">
								<b id="descricaoFrete">Endereço Principal</b>
								<input type="hidden" id="user_nome" value="${user.nome}">
								<b class="descricao-endereco-DPaulla" id="logradouroFrete">${user.logradouro}</b>
								<b class="descricao-endereco-DPaulla" id="numeroFrete">Número ${user.logradouronumero}, ${user.bairro}</b>
								<b class="descricao-endereco-DPaulla" id="cepFrete">CEP ${user.cep} - ${user.cidade}</b>
								<a class ="dpaulla-link" id="alterarEndereço" href="/profile?p${user.userId}">[Alterar endereço]</a>
							</div>
							<div class="section-meucarrinhoVisualizacao-enderecoentrega-usuarioanonimo" id="section-meucarrinhoVisualizacao-enderecoentrega-usuarioanonimo">
								<form:form method="POST" id="endereco" name="user" action="valorFrete">
									<div>
							        	<label>CEP</label>
						                <div class="form-group ${status.error ? 'has-error' : ''}">
						                    <input type="text" path="cep" id="cep" name="cep" class="section-meucarrinhoVisualizacao-cep-cepFrete" onkeypress="$(this).mask('00.000-000')" placeholder="12.123-454" value="" size="10" maxlength="9" onblur="pesquisacep(this.value);"></input>
						                </div>
									</div>
									<div>
								        <label>Logradouro</label>
						                <div class="form-group ${status.error ? 'has-error' : ''}">
						                    <input type="text" path="logradouro" id="logradouro" name="logradouro" class="section-meucarrinhoVisualizacao-cep-logradouroFrete" placeholder="Logradouro"></input>
						                    <form:errors path="logradouro" class="alertFormError"></form:errors>
						                </div>
									</div>	
									<div>
								        <label>Número</label>
						                <div class="form-group ${status.error ? 'has-error' : ''}">
						                    <input type="text" path="logradouronumero" id="logradouronumero" name="logradouronumero" class="section-meucarrinhoVisualizacao-cep-logradouroNumero" placeholder="Nº"></input>
						                    <form:errors path="logradouronumero" class="alertFormError"></form:errors>
						                </div>
									</div>				
									<div>
								        <label>Bairro</label>
						                <div class="form-group ${status.error ? 'has-error' : ''}">
						                    <input type="text" path="bairro" id="bairro" name="bairro" class="section-meucarrinhoVisualizacao-cep-bairroFrete" placeholder="Bairro"></input>
						                    <form:errors path="bairro" class="alertFormError"></form:errors>
						                </div>
									</div>	
									<div>
								        <label>Cidade</label>
						                <div class="form-group ${status.error ? 'has-error' : ''}">
						                    <input type="text" path="cidade" id="cidade" name="cidade" class="section-meucarrinhoVisualizacao-cep-localidadeFrete" placeholder="Cidade"></input>
						                    <form:errors path="cidade" class="alertFormError"></form:errors>
						                </div>
									</div>
									<button type="submit" class="btn-secundario" id="btn-secundario" name="calcularFrete">Calcular Frete</button>	
								</form:form>
							</div>
									
							<div class="section-meucarrinhoVisualizacao-enderecoentrega-valor" id="section-meucarrinhoVisualizacao-enderecoentrega-valor">
								<b class="descricao-produto-DPaulla" id="tipoFrete">Frete: SEDEX</b>
								<b class="descricao-produto-DPaulla" id="prazoFrete">Prazo: De Até ${valorfrete.prazoEntrega} dias úteis</b>
								<b class="descricao-produto-DPaulla" id="valorFrete">Valor: ${valorfrete.valor}</b>
							</div>
							<button type="submit" class="btn-dpaulla" id="btn-dpaulla" name="realizarPagamento" onclick="actionCarrinhoRealizarPagamento()">Realizar Pagamento</button>
						</div>
					</div>
				</div>
			</c:if>
			<c:if test="${comprasListSession == '[]'}">
				<div class="section-meucarrinhoVisualizacao-view-carrinhovazio">
					<h4 class="section-meucarrinhoVisualizacao-view-carrinhovazio-descricao descricao-produto-DPaulla" id="section-meucarrinhoVisualizacao-view-carrinhovazio-descricao">O seu carrinho está vazio</h4>
					<h4 class="section-meucarrinhoVisualizacao-view-carrinhovazio-descricao-detalhe descricao-produto-DPaulla" id="section-meucarrinhoVisualizacao-view-carrinhovazio-descricao-detalhe">Não sabe oque comprar? Acesse a Aba dicas e encontre tudo oque você precisa saber para deixar seu cabelo lindo</h4>
				</div>
			</c:if>								
		</div>
 		<div class="section-footer">
			<div class="section-footer-logo">
				<a href="/"><img src="assets/images/logo_transparent.png" alt="DPaulla" class="section-footer-logo-logo"></img></a>
			</div>
			<div class="section-footer-grid-informacoes">
				<h1 class="white-link">Novidades</h1>
				<h1 class="white-link">Categorias</h1>
				<h1 class="white-link">Siga</h1>
				<h6 class="white-link">Linha Própria</h6>
				<h6 class="white-link">Tipos de Cabelo</h6>
				<h6 class="white-link">Instagram</h6>
				<h6 class="white-link">Nos indique</h6>
				<h6 class="white-link">Para Cabelo</h6>
				<h6 class="white-link">Youtube</h6>
				<h6 class="white-link">Promoção</h6>
				<h6 class="white-link">Marcas</h6>
				<h6 class="white-link">Facebook</h6>
			</div>
		</div> 
	</div>
<script>

function verificaUsarioLogado(){
	var usuario = document.getElementById("user_nome").value;
	console.log(usuario);
    if(usuario != ""){
    	document.getElementById("section-meucarrinhoVisualizacao-enderecoentrega-usuariologado").style.display = "grid";
    	document.getElementById("section-meucarrinhoVisualizacao-enderecoentrega-valor").style.display = "grid";
    	document.getElementById("btn-dpaulla").style.display = "flex";
    	document.getElementById("btn-secundario").style.display = "none";
    }else{
    	document.getElementById("section-meucarrinhoVisualizacao-enderecoentrega-usuarioanonimo").style.display = "grid";
    	document.getElementById("section-meucarrinhoVisualizacao-enderecoentrega-valor").style.display = "none";
    	document.getElementById("btn-dpaulla").style.display = "none";
    }
}

function mouseclick(path) {
	document.getElementById("img-produto-view").src = path;
}

function calculaValorReais(){
	var ele = document.getElementById('meucarrinho-container-produtos').children;
	var match = new Array();
	percorreArray(ele,match);
}

function percorreArray(e1,a1){
	var id;
 	for(var i =0;i<e1.length;i++)
	{
		if(e1[i].id.toString().replace("meucarrinho-container-imagem_","") > 0){
			id = e1[i].id.toString().replace("meucarrinho-container-imagem_", "");
			verificaElementoInput(id);
		}
	}
}
 
 
function verificaElementoInput(id){
	quantidade = parseFloat(document.getElementById("quantidadeValue_" + id).value);
	valor = parseFloat(document.getElementById("meucarrinho-container-valor-hidden_" + id).value);
	texto = quantidade*valor;
	var number2 = texto.toLocaleString('pt-br',{style: 'currency', currency: 'BRL'});
    document.getElementById("meucarrinho-container-valor-text_"+id).innerHTML = number2;
}

$('.btn-number').click(function(e){
    e.preventDefault();
    
    fieldName = $(this).attr('data-field');
    type      = $(this).attr('data-type');
	var id;
    var input = $("input[name='"+fieldName+"']");
    var currentVal = parseInt(input.val());
    if (!isNaN(currentVal)) {
        if(type == 'minus') {
            id = $(this).attr('id').toString().replace("buttonMinus_", "");
            if(currentVal > input.attr('min')) {
                input.val(currentVal - 1).change();
                verificaElementoInput(id);
            } 
            if(parseInt(input.val()) == input.attr('min')) {
                $(this).attr('disabled', true);
            }
        } else if(type == 'plus') {
        	id = $(this).attr('id').toString().replace("buttonPlus_", "");
            if(currentVal < input.attr('max')) {
                input.val(currentVal + 1).change();
                verificaElementoInput(id);
            }
            if(parseInt(input.val()) == input.attr('max')) {
                $(this).attr('disabled', true);
            }
        }
    } else {
        input.val(0);
    }
});

$('.section-meucarrinhoVisualizacao-view-produtos-quantidade-input-number').focusin(function(){
   $(this).data('oldValue', $(this).val());
});

$('.section-meucarrinhoVisualizacao-view-produtos-quantidade-input-number').change(function() {
    var id = $(this).attr('id').toString().replace("quantidadeValue_", "");
    minValue =  parseInt($(this).attr('min'));
    maxValue =  parseInt($(this).attr('max'));
    valueCurrent = parseInt($(this).val());
    name = $(this).attr('name');
    if(valueCurrent >= minValue) {
        $(".btn-number[data-type='minus'][data-field='"+name+"']").removeAttr('disabled')
    } else {
        alert('Sorry, the minimum value was reached');
        $(this).val($(this).data('oldValue'));
    }
    if(valueCurrent <= maxValue) {
        $(".btn-number[data-type='plus'][data-field='"+name+"']").removeAttr('disabled')
    } else {
        alert('Sorry, the maximum value was reached');
        $(this).val($(this).data('oldValue'));
    }
});

$(".section-meucarrinhoVisualizacao-view-produtos-quantidade-input-number").keydown(function (e) {
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 190]) !== -1 ||
            (e.keyCode == 65 && e.ctrlKey === true) || 
            (e.keyCode >= 35 && e.keyCode <= 39)) {
                 return;
        }
        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
            e.preventDefault();
        }
    });
    
function meu_callback(conteudo) {
    if (!("erro" in conteudo)) {
        document.getElementById("logradouro").value=(conteudo.logradouro);
        document.getElementById("bairro").value =(conteudo.bairro);
        document.getElementById("cidade").value =(conteudo.localidade);
    } 
    else {
        alert("CEP não encontrado.");
    }
}
    

function teste(conteudo2) {
    if (!("erro" in conteudo2)) {
		alert(conteudo2);
    } 
    else {
        alert("CEP não encontrado.");
    }
}
    
function pesquisacep(valor) {
    var cep = valor.replace(/\D/g, '');
    if (cep != "") {
        var validacep = /^[0-9]{8}$/;
        if(validacep.test(cep)) {
        	document.getElementById("logradouro").value="...";
        	document.getElementById("bairro").value="...";
        	document.getElementById("cidade").value="...";
            var script = document.createElement('script');
            script.src = 'https://viacep.com.br/ws/'+ cep + '/json/?callback=meu_callback';
            document.body.appendChild(script);
        } 
        else {
            alert("Formato de CEP inválido.");
        }
    } 
    else {
        limpa_formulario_cep();
    }
}

function limpa_formulario_cep(){
	document.getElementById("logradouro").value="";
	document.getElementById("bairro").value="";
	document.getElementById("cidade").value="";
};

</script>
</body>
</html>