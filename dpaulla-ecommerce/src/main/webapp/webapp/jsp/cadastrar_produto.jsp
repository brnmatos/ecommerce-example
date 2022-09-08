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
      <title>Cadastrar Produtos</title>
	  <script type="text/javascript" charset="UTF-8" src="assets/dpaulla-scripts.js"></script>  	  
</head>

<body class="body-left">
	<div class="section-body">
		<div class="section-menu-principal-view">
			<div class="section-menu-principal-view-logo">
				<a href="/"><img src="assets/images/logo_transparent.png" alt="DPaulla" class="section-menu-principal-view-logo-logo"></img></a>
			</div>
			<div class="section-menu-principal-view-login">
					<img src="assets/icons/icons-female-user.png" alt="icons-female-user" class="icone-imagem"></img>
					 
						<c:if test="${pageContext.request.userPrincipal.name != null}">
							<a href="/meu-perfil" class="icone-legenda">
		        				Olá, ${user.nome}
		        			</a>	
						</c:if> 
						<c:if test="${pageContext.request.userPrincipal.name == null}">
							<a href="/meu-perfil" class="icone-legenda">		
			        			Login
	        				</a>
			    		</c:if>
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
			<c:if test="${role.role_id == 1}">
				<div class="section-menu-principal-view-menusecundario">
					<a class="white-link" href="/acesso-restrito"> Acesso Restrito </a>
				</div>
    		</c:if>
			<c:if test="${role.role_id == 3}">
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
	<div class="section-cadastro">
		<h1 class="titulo-DPaulla">Cadastro de Produtos</h1>
			<form:form method="POST" id="produto" name="produto" enctype="multipart/form-data" action="cadastrarProduto">
				<div>
		        	<label>Descrição</label>
	                <div class="form-group ${status.error ? 'has-error' : ''}">
	                    <input type="text" path="produtoDescricao" id="produtoDescricao" name="produtoDescricao" class="section-meucarrinhoVisualizacao-cep-cepFrete" value="" size="200" maxlength="200"></input>
	                </div>
				</div>
				<div>
			        <label>Marca</label>
	                <div class="form-group ${status.error ? 'has-error' : ''}">
						  <select name="produtoMarca" id="produtoMarca" class="section-meucarrinhoVisualizacao-cep-cepFrete">
						  	<c:forEach var="produtoMarca" items="${marcas}" >
								<option type="text" name="produtoMarca" class="section-meucarrinhoVisualizacao-cep-cepFrete" value="${produtoMarca.marcaId}">${produtoMarca.marcaDescricao}</option>
							</c:forEach>
						  </select>
	                  <form:errors path="marcaNivel" class="alertFormError"></form:errors>  
	                </div>
				 </div>	
				<div>
					<label>Saldo</label>
	                	<div class="form-group ${status.error ? 'has-error' : ''}">					
						  <select name="produtoSaldo" id="quantidades" class="section-meucarrinhoVisualizacao-cep-cepFrete">
						  	<c:forEach var="saldosValue" items="${saldo}" >
								<option type="text" name="produtoSaldo" class="section-meucarrinhoVisualizacao-cep-cepFrete">${saldosValue}</option>
							</c:forEach>
						  </select>
					   </div>
				</div>
				<div>
		        	<label>Valor</label>
	                <div class="form-group ${status.error ? 'has-error' : ''}">
	                    <input type="text" path="produtoValor" id="produtoValor" name="produtoValor" class="section-meucarrinhoVisualizacao-cep-cepFrete" value="" size="200" maxlength="200"></input>
	                </div>
				</div>
				<div>
			        <label>Foto Frontal (Principal)</label>
	                <div class="form-group ${status.error ? 'has-error' : ''}">
	                    <input type="file" id="produtoImagem" name="produtoImagem" path="produtoImagem" multiple size="50" onchange="marcaLogoFunction()">
	                    <form:errors path="produtoImagem" class="alertFormError"></form:errors>
	                </div>
				</div>
				<div>
			        <label>Foto Lateral</label>
	                <div class="form-group ${status.error ? 'has-error' : ''}">
	                    <input type="file" id="imagemLateral" name="imagemLateral" path="imagemLateral" multiple size="50" onchange="marcaLogoFunction()">
	                    <form:errors path="imagemLateral" class="alertFormError"></form:errors>
	                </div>
				</div>
				<div>
			        <label>Foto Superior</label>
	                <div class="form-group ${status.error ? 'has-error' : ''}">
	                    <input type="file" id="imagemSuperior" name="imagemSuperior" path="imagemSuperior" multiple size="50" onchange="marcaLogoFunction()">
	                    <form:errors path="imagemSuperior" class="alertFormError"></form:errors>
	                </div>
				</div>						
				<div>
			        <label>Foto Com Modelo</label>
	                <div class="form-group ${status.error ? 'has-error' : ''}">
	                    <input type="file" id="imagemModelo" name="imagemModelo" path="imagemModelo" multiple size="50" onchange="marcaLogoFunction()">
	                    <form:errors path="imagemModelo" class="alertFormError"></form:errors>
	                </div>
				</div>						
				<div>
					<button type="submit" class="btn-secundario" id="btn-secundario" name="cadastrarMarca" onclick="createCustomAlert('Marca cadastrada com sucesso!');">Salvar</button>
				</div>		
			</form:form>
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

function marcaLogoFunction(){
	  var x = document.getElementById("marcaLogo");
	  var txt = "";
	  if ('files' in x) {
	    if (x.files.length == 0) {
	      txt = "Select one or more files.";
	    } else {
	      for (var i = 0; i < x.files.length; i++) {
	        txt += "<br><strong>" + (i+1) + ". file</strong><br>";
	        var file = x.files[i];
	        if ('name' in file) {
	          txt += "name: " + file.name + "<br>";
	        }
	        if ('size' in file) {
	          txt += "size: " + file.size + " bytes <br>";
	        }
	      }
	    }
	  } 
	  else {
	    if (x.value == "") {
	      txt += "Select one or more files.";
	    } else {
	      txt += "The files property is not supported by your browser!";
	      txt  += "<br>The path of the selected file: " + x.value; // If the browser does not support the files property, it will return the path of the selected file instead. 
	    }
	  }
	  document.getElementById("demo").innerHTML = txt;
	}
	
</script>

</html>