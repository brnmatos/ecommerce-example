<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
	  <meta charset="UTF-8">
	  <meta http-equiv="X-UA-Compatible" content="IE=edge">
	  <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1">
	  <link rel="shortcut icon" href="assets/images/logo-90x66.png" type="image/x-icon">
	  <meta name="description" content="">
  	  <link rel="stylesheet" href="assets/style.css">
      <title>Acesso Restrito</title>
	  <script type="text/javascript" charset="UTF-8" src="assets/dpaulla-scripts.js"></script>  	  
</head>

<body onload="verificaEstaVindoDoPost();">
	<div class="section-body">
		<div class="section-menu-principal-view">
			<div class="section-menu-principal-view-logo">
				<a href="/"><img src="assets/images/logo_transparent.png" alt="DPaulla" class="section-menu-principal-view-logo-logo"></img></a>
			</div>
			<div class="section-menu-principal-view-login">
					<img src="assets/icons/icons-female-user.png" alt="icons-female-user" class="icone-imagem"></img>
					<a href="/meu-perfil" class="icone-legenda"> 
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
				<a href="/meu-perfil" class="icone-legenda">
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
			<c:if test="${userRole.roles_role_id == 1}">
				<div class="section-menu-principal-view-menusecundario">
					<a class="white-link" href="/meu-perfil"> Acesso Restrito </a>
				</div>
    		</c:if>
			<c:if test="${userRole.roles_role_id == 3}">
				<div class="section-menu-principal-view-menusecundario">
					<a class="white-link" href="/meu-perfil"> Minha Conta </a>
					<a class="white-link"> Quem Somos </a>
					<a class="white-link"> Política do Site</a>
					<a class="white-link"> FAQ</a>
				</div>
			</c:if> 
    		
			<div class="section-menu-principal-view-busca">
				<input name="buscar" type="text" placeholder="Buscar" class="text-buscar"/>
				<button type="submit" class="btn-dpaulla button-buscar" name="name">Buscar</button>
			</div>

			<div class="menuCategoriasPrincipal section-menu-principal-view-menucategorias" onmouseleave="mouseout()">
				<div class="menuCategoriasPrincipal-grupoitem1">
					<div class="menuCategoriasPrincipal-item" onclick="redirectPage('page1')" onmouseenter="mouseininterno('divMenu1')">
						Cadastros
					</div>
					<div class="menuCategoriasPrincipal-item" onclick="redirectPage('page2')" onmouseenter="mouseininterno('divMenu2')">
						Consultar Pedidos
					</div>
					<div class="menuCategoriasPrincipal-item" onclick="redirectPage('page3')" onmouseenter="mouseininterno('divMenu3')">
						Relatórios
					</div>
					<div class="menuCategoriasPrincipal-item" onclick="redirectPage('page4')" onmouseenter="mouseininterno('divMenu4')">
						Em Construção
					</div>
					<div class="menuCategoriasPrincipal-item" onclick="redirectPage('page5')" onmouseenter="mouseininterno('divMenu5')">
						Em Construção
					</div>
				</div>

				<div class="menuCategoriasPrincipal-grupoitem2 section-menu-principal-view-menusubcategorias">
					<div class="menuCategoriasPrincipal-item-oculto">
						<div class="divMenu1" id="divMenu1" name="divMenu1">
							<div class="divMenu-sub-item1" id="divMenu-sub-item1" onclick="redirectSubPageInterno('subpage1')"></div>
							<div class="divMenu-sub-item2" id="divMenu-sub-item2" onclick="redirectSubPageInterno('subpage2')"></div>
							<div class="divMenu-sub-item3" id="divMenu-sub-item3" onclick="redirectSubPageInterno('subpage3')"></div>
							<div class="divMenu-sub-item4" id="divMenu-sub-item4" onclick="redirectSubPageInterno('subpage4')"></div>
							<div class="divMenu-sub-item5" id="divMenu-sub-item5" onclick="redirectSubPageInterno('subpage5')"></div>
						</div>
					</div>
				</div>
			</div>		
		</div>
		
	<div class="section-acessorestrito">
		<div class="section-acessorestrito-container">
			<h1 class="titulo-DPaulla" id="titulo-DPaulla"></h1>
			<div class="section-acessorestrito-container-marcas" id="section-acessorestrito-container-marcas">
				<b class="descricao-endereco-DPaulla" id="radio">#</b>
				<b class="descricao-endereco-DPaulla" id="id">Id</b>
				<b class="descricao-endereco-DPaulla" id="descricao">Descrição</b>
				<b class="descricao-endereco-DPaulla" id="total">Nível</b>
				<b class="descricao-endereco-DPaulla" id="status">Logo</b>
				<c:forEach var="marcas" items="${marcas}">
					<input type="radio" id="radioButton_${marcas.marcaId}" name="marcas" value="radioButton_${marcas.marcaId}" onselect="internoCadastroRadioSelected()" onclick="internoCadastroRadioSelected()">
					<b class="descricao-endereco-DPaulla">${marcas.marcaId}</b>
					<b class="descricao-endereco-DPaulla">${marcas.marcaDescricao}</b>
					<b class="descricao-endereco-DPaulla">${marcas.marcaNivel}</b>
					<b class="descricao-endereco-DPaulla">${marcas.marcaLogo}</b>
				</c:forEach>
			</div>
			<div class="section-acessorestrito-container-produto" id="section-acessorestrito-container-produto">
				<b class="descricao-endereco-DPaulla" id="radio">#</b>
				<b class="descricao-endereco-DPaulla" id="id">Id</b>
				<b class="descricao-endereco-DPaulla" id="descricao">Descrição</b>
				<b class="descricao-endereco-DPaulla" id="total">Imagem</b>
				<b class="descricao-endereco-DPaulla" id="status">Marca</b>
				<b class="descricao-endereco-DPaulla" id="status">Saldo</b>
				<b class="descricao-endereco-DPaulla" id="status">Valor</b>
				<c:forEach var="produtos" items="${produtos}">
					<input type="radio" id="radioButton_${produtos.produtoId}" name="produtos" value="radioButton_${produtos.produtoId}" onselect="internoCadastroRadioSelected()" onclick="internoCadastroRadioSelected()">
					<b class="descricao-endereco-DPaulla">${produtos.produtoId}</b>
					<b class="descricao-endereco-DPaulla">${produtos.produtoDescricao}</b>
					<b class="descricao-endereco-DPaulla">${produtos.produtoImagem}</b>
					<b class="descricao-endereco-DPaulla">${produtos.produtoMarca}</b>
					<b class="descricao-endereco-DPaulla">${produtos.produtoSaldo}</b>
					<b class="descricao-endereco-DPaulla">${produtos.produtoValor}</b>
				</c:forEach>
			</div>
			<div class="section-acessorestrito-container-botoes" id="section-acessorestrito-container-botoes">
				<button type="submit" class="btn-dpaulla" id="btn-dpaulla-novo" name="Novo" value="marca" onclick="internoCadastroNovo(this.value)">Novo</button>
				<button type="submit" class="btn-dpaulla btn-oculto" id="btn-dpaulla-editar" name="Editar" onclick="actionCarrinhoRealizarPagamento()">Editar</button>
				<button type="submit" class="btn-dpaulla btn-oculto" id="btn-dpaulla-excluir" name="Excluir" onclick="actionCarrinhoRealizarPagamento()">Excluir</button>
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
function verificaEstaVindoDoPost(){
	console.log("onload ok");
	var opcao = "${tipo}";

	if(opcao == "ok"){
		alertaCustomizadoInterno("Cadastro realizado com sucesso!");
	}else{
		console.log("Nao veio de nenhuma inserção.");
	}
}

</script>
								
</html>