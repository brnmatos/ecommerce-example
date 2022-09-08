 <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="pt">
<head>
	  <meta charset="UTF-8">
	  <meta http-equiv="X-UA-Compatible" content="IE=edge">
	  <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1">
	  <link rel="shortcut icon" href="assets/images/logo-90x66.png" type="image/x-icon">
	  <meta name="description" content="">
  	  <link rel="stylesheet" href="assets/style.css">
      <title>Meu Perfil</title>
	  <script type="text/javascript" charset="UTF-8" src="assets/dpaulla-scripts.js"></script>
	  <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
  	  <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>	  
  	  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js"></script>	 
  	     	  
</head>
<body>
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
		<div class="section-meuperfil">
			<div class="section-meuperfil-menusuperior">
				<div class="section-meuperfil-menusuperior-meuspedidos">
					<img src="assets/icons/icons-female-user.png" alt="icons-female-user" class="icone-imagem"></img>
					<a class="icone-legenda" id="section-meuperfil-menusuperior-minhaconta" onclick="perfilSelected(this.id)"> 
						Minha Conta
					</a>
				</div>
				<div class="section-meuperfil-menusuperior-meuspedidos">
					<img src="assets/icons/icons-address-book.png" alt="icons-book" class="icone-imagem"></img>
					<a class="icone-legenda" id="section-meuperfil-menusuperior-meuspedidos" onclick="perfilSelected(this.id)">
						Meus Pedidos
					</a> 
				</div>
			</div>
			<div class="section-meuperfil-menugeral">
				<div class="section-meuperfil-menugeral-detalhesconta" id="section-meuperfil-menugeral-detalhesconta">
			        <h2 class="titulo-DPaulla">
			            DETALHES DA SUA CONTA
			        </h2>
			        <div class="section-meuperfil-menugeral-detalhesconta-informacoes" id="section-meuperfil-menugeral-detalhesconta-informacoes">
						<b class="descricao-endereco-DPaulla" id="nome">${user.nome} ${user.sobrenome}</b>
						<b class="descricao-endereco-DPaulla" id="datanascimento">Data Nascimento: ${user.datanascimento}</b>
						<b class="descricao-endereco-DPaulla" id="cpf">CPF: ${user.cpf}</b>
						<b class="descricao-endereco-DPaulla" id="telefone">Telefone: ${user.telefone}</b>
						<b class="descricao-endereco-DPaulla" id="celular">Celular: ${user.celular}</b>
						<b class="descricao-endereco-DPaulla" id="email">Email: ${user.username}</b>
						<b class="descricao-endereco-DPaulla" id="cepFrete">Frete: ${user.cep}</b>
						<b class="descricao-endereco-DPaulla" id="logradouroFrete">Endereço: ${user.logradouro}</b>
						<b class="descricao-endereco-DPaulla" id="numeroFrete">Nº: ${user.logradouronumero}</b>
						<b class="descricao-endereco-DPaulla" id="complementoFrete">Complemento: ${user.complemento}</b>
						<b class="descricao-endereco-DPaulla" id="bairroFrete">Bairro: ${user.bairro}</b>
						<b class="descricao-endereco-DPaulla" id="cidadeFrete">Cidade/Estado: ${user.cidade} - ${user.estado}</b>
			        </div>
			        <div class="section-meuperfil-menugeral-detalhesconta-botoes">
			        	<button class="btn-dpaulla" type="submit">Editar</button>
			        	<button class="btn-secundario" type="submit">Alterar Senha</button>
			        </div>
				</div>
				<div class="section-meuperfil-menugeral-meuspedidos" id="section-meuperfil-menugeral-meuspedidos">
			        <h2 class="titulo-DPaulla">
			            MEUS PEDIDOS
			        </h2>
					<div class="section-meuperfil-menugeral-meuspedidos-informacoes">
						<b class="descricao-endereco-DPaulla" id="pedido">Pedido #</b>
						<b class="descricao-endereco-DPaulla" id="data">Data</b>
						<b class="descricao-endereco-DPaulla" id="total">Total</b>
						<b class="descricao-endereco-DPaulla" id="status">Status</b>
						<b class="descricao-endereco-DPaulla" id="acao">Ação</b>
				
						<c:forEach var="transacoes" items="${transacoes}">
							<b class="descricao-endereco-DPaulla">${transacoes.transacaoId}</b>
							<b class="descricao-endereco-DPaulla">${transacoes.transacaoDataGeracao}</b>
							<b class="descricao-endereco-DPaulla">${transacoes.transacaoValorTotal}</b>
							<b class="descricao-endereco-DPaulla">${transacoes.transacaoStatus}</b>
							<b class="descricao-endereco-DPaulla">Visualizar</b>
						</c:forEach>						
					</div>					
				</div>
			</div>
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
</body>
<script>

function perfilSelected(valor){
	if (valor == "section-meuperfil-menusuperior-minhaconta") {
		document.getElementById("section-meuperfil-menugeral-detalhesconta").style.display = "grid";
    	document.getElementById("section-meuperfil-menugeral-meuspedidos").style.display = "none";
	}else if("section-meuperfil-menusuperior-meuspedidos"){
		document.getElementById("section-meuperfil-menugeral-meuspedidos").style.display = "grid";
		document.getElementById("section-meuperfil-menugeral-detalhesconta").style.display = "none";
	}
	
}
function meu_callback(conteudo) {
	alert("CEP Buscando...");
    if (!("erro" in conteudo)) {
        //Atualiza os campos com os valores.
        document.getElementById("logradouro").value=(conteudo.logradouro);
        document.getElementById("bairro").value =(conteudo.bairro);
        document.getElementById("cidade").value =(conteudo.localidade);
        
//        document.getElementById('uf').value=(conteudo.uf);
//        document.getElementById('ibge').value=(conteudo.ibge);
    } //end if.
    else {
        //CEP não Encontrado.
        alert("CEP não encontrado.");
    }
}
    
function pesquisacep(valor) {
    //Nova variável "cep" somente com dígitos.
    var cep = valor.replace(/\D/g, '');

    //Verifica se campo cep possui valor informado.
    if (cep != "") {

        //Expressão regular para validar o CEP.
        var validacep = /^[0-9]{8}$/;

        //Valida o formato do CEP.
        if(validacep.test(cep)) {

            //Preenche os campos com "..." enquanto consulta webservice.
        	document.getElementById("logradouro").value="...";
        	document.getElementById("bairro").value="...";
        	document.getElementById("cidade").value="...";

            //Cria um elemento javascript.
            var script = document.createElement('script');

            //Sincroniza com o callback.
            script.src = 'https://viacep.com.br/ws/'+ cep + '/json/?callback=meu_callback';

            //Insere script no documento e carrega o conteúdo.
            document.body.appendChild(script);

        } //end if.
        else {
            //cep é inválido.
            alert("Formato de CEP inválido.");
        }
    } //end if.
    else {
        //cep sem valor, limpa formulário.
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