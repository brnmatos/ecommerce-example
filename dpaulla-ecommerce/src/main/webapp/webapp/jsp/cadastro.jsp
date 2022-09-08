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
      <title>Cadastro</title>
	  <script type="text/javascript" charset="UTF-8" src="assets/dpaulla-scripts.js"></script>
	  <script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
  	  <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>	  
  	  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js"></script>	 
  	     	  
</head>
<body onload="calculaValorReais(); verificaUsarioLogado();">
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
		<div class="section-cadastro">
		<h1 class="titulo-DPaulla">${user.nome}</h1>
	 		<form:form method="POST" modelAttribute="userForm" class="section-cadastro-FormCadastro">
				<div class="section-cadastro-FormCadastro-divFormNome">
		            <spring:bind path="nome">
		            	<label>Nome</label>
		                <div class="form-group ${status.error ? 'has-error' : ''}">
		                    <form:input type="text" path="nome" class="section-cadastro-FormCadastro-textbox" placeholder="Nome" autofocus="true"></form:input>
		                    <form:errors path="nome" class="alertFormError"></form:errors>
		                </div>
		            </spring:bind>
				</div>
		 		<div class="section-cadastro-FormCadastro-divFormSobreNome">
		            <spring:bind path="sobrenome">
		            <label>Sobrenome</label>
		                <div class="form-group ${status.error ? 'has-error' : ''}">
		                    <form:input type="text" path="sobrenome" class="section-cadastro-FormCadastro-textbox" placeholder="Sobrenome"></form:input>	
		                    <form:errors path="sobrenome" class="alertFormError"></form:errors>
		                </div>
		            </spring:bind>	     	                
				</div>
		 		<div class="section-cadastro-FormCadastro-divFormDataNascimento">
			        <label>Data de Nascimento</label>
		            <spring:bind path="datanascimento">
		                <div class="form-group ${status.error ? 'has-error' : ''}">
		                    <form:input type="text" path="datanascimento" id="datanascimento" name="datanascimento" class="section-cadastro-FormCadastro-textbox" onkeypress="$(this).mask('00/00/0000')" placeholder="00/00/0000" 
		                    value="" size="15" maxlength="15"></form:input>
		                    <form:errors path="datanascimento" class="alertFormError"></form:errors>
		                </div>
		            </spring:bind> 		
				</div>					
		 		<div class="section-cadastro-FormCadastro-divFormCPF">
			        <label>CPF</label>
		            <spring:bind path="cpf">
		                <div class="form-group ${status.error ? 'has-error' : ''}">
		                    <form:input type="text" path="cpf" id="cpf" name="cpf" class="section-cadastro-FormCadastro-textbox" onkeypress="$(this).mask('000.000.000-00')" placeholder="000.000.000-00" 
		                    value="" size="15" maxlength="15"></form:input>
		                    <form:errors path="cpf" class="alertFormError"></form:errors>
		                </div>
		            </spring:bind> 		
				</div>
				<div class="section-cadastro-FormCadastro-divFormTelefone">
			        <label>Telefone</label>
		            <spring:bind path="telefone">
		                <div class="form-group ${status.error ? 'has-error' : ''}">
		                    <form:input type="text" path="telefone" class="section-cadastro-FormCadastro-textbox" onkeypress="$(this).mask('(00)0000-0000')" placeholder="(00)0000-0000"></form:input>
		                    <form:errors path="telefone" class="alertFormError"></form:errors> 
		                </div>
		            </spring:bind> 	
				</div>
				<div class="section-cadastro-FormCadastro-divFormCelular">
			        <label>Celular</label>
		            <spring:bind path="celular">
		                <div class="form-group ${status.error ? 'has-error' : ''}">
		                    <form:input type="text" path="celular" class="section-cadastro-FormCadastro-textbox" onkeypress="$(this).mask('(00)00000-0000')" placeholder="(00)00000-0000"></form:input>
		                    <form:errors path="celular" class="alertFormError"></form:errors>
		                </div>
		            </spring:bind> 	
				</div>
						
		 	 	<div class="section-cadastro-FormCadastro-divFormEmail">
			        <label>Email</label>
		            <spring:bind path="username">
		                <div class="form-group ${status.error ? 'has-error' : ''}">
		                    <form:input type="text" path="username" class="section-cadastro-FormCadastro-textbox" placeholder="Email"></form:input>
		                    <form:errors path="username" class="alertFormError"></form:errors>
		                </div>
		            </spring:bind>
				</div>	
		 		<div class="section-cadastro-FormCadastro-divFormConfirmacaoEmail">
			        <label>Confirmação Email</label>
		            <spring:bind path="usernameConfirm">
		                <div class="form-group ${status.error ? 'has-error' : ''}">
		                    <form:input type="text" path="usernameConfirm" class="section-cadastro-FormCadastro-textbox" placeholder="Email"></form:input>
		                    <form:errors path="usernameConfirm" class="alertFormError"></form:errors>
		                </div>
		            </spring:bind>
				</div>
		
		 		<div class="section-cadastro-FormCadastro-divFormSenha">
		  			<label>Senha</label>
		            <spring:bind path="password">
		                <div class="form-group ${status.error ? 'has-error' : ''}">
		                    <form:input type="password" path="password" class="section-cadastro-FormCadastro-textbox" placeholder="Senha"></form:input>
		                    <form:errors path="password" class="alertFormError"></form:errors>
		                </div>
		            </spring:bind>            			         
				</div>		    
		 		<div class="section-cadastro-FormCadastro-divFormConfirmacaoSenha">
			        <label>Confirmação Senha</label>
			        <spring:bind path="passwordConfirm">
			                <div class="form-group ${status.error ? 'has-error' : ''}">
			                    <form:input type="password" path="passwordConfirm" class="section-cadastro-FormCadastro-textbox"
			                                placeholder="Confirme sua senha"></form:input>
			                    <form:errors path="passwordConfirm" class="alertFormError"></form:errors>
			                </div>
		            </spring:bind>
				</div>
				
				<c:if test="${user.nome != 'anonimo'}">
					<div class="section-cadastro-FormCadastro-divFormCEP">
				        <label>CEP</label>
			            <spring:bind path="cep">
			                <div class="form-group ${status.error ? 'has-error' : ''}">
			                    <form:input type="text" path="cep" id="cep" name="cep" class="section-cadastro-FormCadastro-textbox" onkeypress="$(this).mask('00.000-000')" placeholder="12.123-454" 
			                    value="" size="10" maxlength="9" onblur="pesquisacep(this.value);"></form:input>
			                    <form:errors path="cep" class="alertFormError"></form:errors>
			                </div>
			            </spring:bind> 	
					</div>
					<div class="section-cadastro-FormCadastro-divFormLogradouro">
				        <label>Logradouro</label>
			            <spring:bind path="logradouro">
			                <div class="form-group ${status.error ? 'has-error' : ''}">
			                    <form:input type="text" path="logradouro" id="logradouro" class="section-cadastro-FormCadastro-textbox" placeholder="Logradouro"></form:input>
			                    <form:errors path="logradouro" class="alertFormError"></form:errors>
			                </div>
			            </spring:bind> 	
					</div>	
					<div class="section-cadastro-FormCadastro-divFormLogradouroNumero">
				        <label>Número</label>
			            <spring:bind path="logradouronumero">
			                <div class="form-group ${status.error ? 'has-error' : ''}">
			                    <form:input type="text" path="logradouronumero" id="logradouronumero" class="section-cadastro-FormCadastro-textbox" placeholder="Nº"></form:input>
			                    <form:errors path="logradouronumero" class="alertFormError"></form:errors>
			                </div>
			            </spring:bind> 	
					</div>
					<div class="section-cadastro-FormCadastro-divFormComplemento">
				        <label>Complemento</label>
			            <spring:bind path="complemento">
			                <div class="form-group ${status.error ? 'has-error' : ''}">
			                    <form:input type="text" path="complemento" id="complemento" class="section-cadastro-FormCadastro-textbox"></form:input>
			                    <form:errors path="complemento" class="alertFormError"></form:errors>
			                </div>
			            </spring:bind> 	
					</div>					
					<div class="section-cadastro-FormCadastro-divFormBairro">
				        <label>Bairro</label>
			            <spring:bind path="bairro">
			                <div class="form-group ${status.error ? 'has-error' : ''}">
			                    <form:input type="text" path="bairro" id="bairro" class="section-cadastro-FormCadastro-textbox" placeholder="Bairro"></form:input>
			                    <form:errors path="bairro" class="alertFormError"></form:errors>
			                </div>
			            </spring:bind> 	
					</div>	
					<div class="section-cadastro-FormCadastro-divFormCidade">
				        <label>Cidade</label>
			            <spring:bind path="cidade">
			                <div class="form-group ${status.error ? 'has-error' : ''}">
			                    <form:input type="text" path="cidade" id="cidade" class="section-cadastro-FormCadastro-textbox" placeholder="Cidade"></form:input>
			                    <form:errors path="cidade" class="alertFormError"></form:errors>
			                </div>
			            </spring:bind> 	
					</div>	
				</c:if>	
				
				<c:if test="${user.nome == 'anonimo'}">
					<div class="section-cadastro-FormCadastro-divFormCEP">
				        <label>CEP</label>
			            <spring:bind path="cep">
			                <div class="form-group ${status.error ? 'has-error' : ''}">
			                    <form:input type="text" path="cep" id="cep" name="cep" class="section-cadastro-FormCadastro-textbox" onkeypress="$(this).mask('00.000-000')" placeholder="12.123-454" 
			                    value="${user.cep}" size="10" maxlength="9" onblur="pesquisacep(this.value);"></form:input>
			                    <form:errors path="cep" class="alertFormError"></form:errors>
			                </div>
			            </spring:bind> 	
					</div>
					<div class="section-cadastro-FormCadastro-divFormLogradouro">
				        <label>Logradouro</label>
			            <spring:bind path="logradouro">
			                <div class="form-group ${status.error ? 'has-error' : ''}">
			                    <form:input type="text" path="logradouro" id="logradouro" class="section-cadastro-FormCadastro-textbox" placeholder="Logradouro"
			                    value="${user.logradouro}"></form:input>
			                    <form:errors path="logradouro" class="alertFormError"></form:errors>
			                </div>
			            </spring:bind> 	
					</div>	
					<div class="section-cadastro-FormCadastro-divFormLogradouroNumero">
				        <label>Número</label>
			            <spring:bind path="logradouronumero">
			                <div class="form-group ${status.error ? 'has-error' : ''}">
			                    <form:input type="text" path="logradouronumero" id="logradouronumero" class="section-cadastro-FormCadastro-textbox" placeholder="Nº"
			                    value="${user.logradouronumero}"></form:input>
			                    <form:errors path="logradouronumero" class="alertFormError"></form:errors>
			                </div>
			            </spring:bind> 	
					</div>				
					<div class="section-cadastro-FormCadastro-divFormBairro">
				        <label>Bairro</label>
			            <spring:bind path="bairro">
			                <div class="form-group ${status.error ? 'has-error' : ''}">
			                    <form:input type="text" path="bairro" id="bairro" class="section-cadastro-FormCadastro-textbox" placeholder="Bairro"
			                    value="${user.bairro}"></form:input>
			                    <form:errors path="bairro" class="alertFormError"></form:errors>
			                </div>
			            </spring:bind> 	
					</div>	
					<div class="section-cadastro-FormCadastro-divFormCidade">
				        <label>Cidade</label>
			            <spring:bind path="cidade">
			                <div class="form-group ${status.error ? 'has-error' : ''}">
			                    <form:input type="text" path="cidade" id="cidade" class="section-cadastro-FormCadastro-textbox" placeholder="Cidade"
			                    value="${user.cidade}"></form:input>
			                    <form:errors path="cidade" class="alertFormError"></form:errors>
			                </div>
			            </spring:bind> 	
					</div>	
				</c:if>														
					
			    <div class="section-cadastro-FormCadastro-divFormCadastrar">
			    	<button class="btn-dpaulla" type="submit">Cadastrar</button>
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
<script>
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