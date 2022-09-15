<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
<script type="text/javascript" charset="UTF-8" src="assets/dpaulla-pagseguro.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>	  
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js"></script>	 
<script type="text/javascript" src="https://stc.sandbox.pagseguro.uol.com.br/pagseguro/api/v2/checkout/pagseguro.directpayment.js"></script>

<title>Finalizar Pagamento</title>

</head>
<body onload="getPaymentMethodsDPaulla('${sessao}', '${transacaoSession.orderTotalPrice}');calculaValorReais();" class="body-left">
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
		
		<div class="section-finalizarpagamento">
 			<div class="section-finalizarpagamento-view">
				<div class="section-finalizarpagamento-view-pagamento" id="finalizarpagamento-container-view-pagamento">
					<h4 class="titulo-DPaulla">PAGAMENTO</h4> 
					<form:form method="POST" id="transacao" name="transacao" action="checkout">
 					<div class="section-finalizarpagamento-view-pagamento-radio-cartaocredito" id="section-finalizarpagamento-view-pagamento-radio-cartaocredito">
						<div class="section-finalizarpagamento-view-pagamento-radio-cartaocredito-descricao-img">
							<div>
								<input type="radio" id="creditCard" name="pagamento" value="creditCard" onselect="radioSelected(this.value);" onclick="radioSelected(this.value); getHashCode();"> 
								<label for="creditCard">Cartão de Crédito</label>
							</div>
							<div class="section-finalizarpagamento-view-pagamento-radio-cartaocredito-descricao-img-2cards">
								<img src="" alt="credit-card" class="section-finalizarpagamento-view-pagamento-radio-cartaocredito-descricao-img-2cards-img" id="section-finalizarpagamento-view-pagamento-radio-cartaocredito-descricao-img-2cards-img-mastercard"></img>
								<img src="" alt="credit-card" class="section-finalizarpagamento-view-pagamento-radio-cartaocredito-descricao-img-2cards-img" id="section-finalizarpagamento-view-pagamento-radio-cartaocredito-descricao-img-2cards-img-visa"></img>
							</div>
						</div>
						<div class="section-finalizarpagamento-view-pagamento-radio-credito" id="finalizarpagamento-radio-credito">
							<div class="textbox-label section-finalizarpagamento-view-pagamento-radio-credito-cartaocredito">
								<label>Número do cartão</label> 
								<input type="text" id="textCartaoCredito" name="transacaoCartaoNumero" onblur="getInstallments('${transacaoSession.orderTotalPrice}',this.value)" onkeyup="getBandeiraCartao(this.value);"  onblur="getBandeiraCartao(this.value);" onkeypress="$(this).mask('0000.0000.0000.0000')" placeholder="0000.0000.0000.0000">
							</div>
							<div class="section-finalizarpagamento-view-pagamento-radio-credito-cartaocredito-imagem" id="section-finalizarpagamento-view-pagamento-radio-credito-cartaocredito-imagem"> 
								<img src="assets/images/cartao-credito.png" alt="creditCard" class="section-finalizarpagamento-view-pagamento-radio-credito-cartaocredito-imagem-card" id="section-finalizarpagamento-view-pagamento-radio-credito-cartaocredito-imagem-card"></img>
								<label id="labelNumeroCartao" class="labelNumeroCartao white-link"></label>
								<label id="labelNomeCartao" class="labelNomeCartao white-link">${user.nome} ${user.sobrenome}</label>
 								<img id="imgBandeiraCartao" class="imgBandeiraCartao" src=""></img>
 								<input type="hidden" id="bandeiraCartao" value="">
								<label id="labelVencimentoCartao" class="labelVencimentoCartao white-link"></label>
								<label id="labelSegurancaCartao" class="labelSegurancaCartao white-link"></label>							
							</div>							
							<div class="section-finalizarpagamento-view-pagamento-radio-credito-textDataVencimento-e-codigoseguranca">
								<div class="textbox-label">
									<label>Data de vencimento</label> 
									<input type="text" id="transacaoCartaoVencimento" name="transacaoCartaoVencimento" onkeyup="setVencimentoCartao(this.value);" onkeypress="$(this).mask('00/0000')" placeholder="00/0000">
								</div>
								<div class="textbox-label">
									<label>Código de segurança</label> 
									<input type="text" id="textCodigoSeguranca" name="transacaoCartaoCodigoSeguranca" onblur="setCartaoCredito();">
								</div>
							</div>
							<div class="textbox-label section-finalizarpagamento-view-pagamento-radio-credito-formaspagamento">
								<label>Formas de Pagamento</label> 
							    <select  id="selectTransacaoQuantidadeParcelas" class="section-finalizarpagamento-view-pagamento-radio-credito-formaspagamento-select" onblur="splitInstallments(this.value);" onclick="splitInstallments(this.value);">
								</select>
							</div>							
							<div class="section-finalizarpagamento-view-pagamento-radio-credito-checkbox">
								<div>
									<input type="checkbox" id="dadosPortador" name="dadosPortador" value="dadosPortador" checked onselect="radioSelected(this.value);" onclick="radioSelected(this.value);"> 
									<label for="dadosPortador">Dados do portador iguais aos pessoais</label>
									<div class="section-finalizarpagamento-view-pagamento-radio-credito-dadosportador" id="finalizarpagamento-radio-credito-dadosportador">
										<div class="textbox-label section-finalizarpagamento-view-pagamento-radio-credito-dadosportador-textNomeCompleto">
											<label>Nome completo</label> 
											<input type="text" id="textNomeCompleto" name="transacaoCartaoNome" value="${user.nome} ${user.sobrenome}" onkeyup="setNomeCartao(this.value)">
										</div>								
										<div class="textbox-label section-finalizarpagamento-view-pagamento-radio-credito-dadosportador-textCPF">
											<label>CPF</label> 
		                    				<input type="text" path="cpf" id="transacaoCartaoCPF" name="transacaoCartaoCPF" class="section-cadastro-FormCadastro-textbox" onkeypress="$(this).mask('000.000.000-00')" placeholder="000.000.000-00" size="15" maxlength="15" value="${user.cpf}"></input>
										</div>
										<div class="textbox-label section-finalizarpagamento-view-pagamento-radio-credito-dadosportador-textNascimento">
											<label>Data de nascimento</label> 
						                    <input type="text" path="textDadosDataNascimento" id=transacaoCartaoDatadeNascimento name="transacaoCartaoDatadeNascimento" class="section-cadastro-FormCadastro-textbox" onkeypress="$(this).mask('00/00/0000')" placeholder="01/01/1991"  value="${user.datanascimento}" size="10" maxlength="10"></input>											
										</div>
										<div class="textbox-label section-finalizarpagamento-view-pagamento-radio-credito-dadosportador-textCelular">
											<label>Celular</label> 
											<input type="text" path="transacaoCartaoTelefone" id="transacaoCartaoTelefone" name="transacaoCartaoTelefone" class="section-cadastro-FormCadastro-textbox" onkeypress="$(this).mask('(00)00000-0000')" placeholder="(11)98765-4321" value="${user.celular}" size="15" maxlength="15"></input>											
										</div>
										<div class="textbox-label section-finalizarpagamento-view-pagamento-radio-credito-dadosportador-textEmail">
											<label>Email</label> 
											<input type="text" id="textDadosEmail" name="transacaoCartaoEmail" value="${user.username}">
										</div>																																																																
									</div>								
								</div>
								<div>
									<input type="checkbox" id="enderecoPortador" name="enderecoPortador" value="enderecoPortador" checked onselect="radioSelected(this.value);" onclick="radioSelected(this.value);"> 
									<label for="enderecoPortador">Dados da cobrança iguais aos da entrega</label>
									<div  class="section-finalizarpagamento-view-pagamento-radio-credito-enderecoportador" id="finalizarpagamento-radio-credito-enderecoportador">
										<div class="textbox-label section-finalizarpagamento-view-pagamento-radio-credito-enderecoportador-textCEP">
											<label>CEP</label> 
						                    <input type="text" path="cep" id="transacaoCobrancaCep" name="transacaoCobrancaCep" class="section-cadastro-FormCadastro-textbox" onkeypress="$(this).mask('00.000-000')" placeholder="12.123-454"  value="${user.cep}" size="10" maxlength="9" onblur="pesquisacep(this.value);"></input>
										</div>	
										<div class="textbox-label section-finalizarpagamento-view-pagamento-radio-credito-enderecoportador-textLogradouro">
											<label>Logradouro</label> 
											<input type="text" id="logradouro" name="transacaoCobrancaEndereco" value="${user.logradouro}">
										</div>
										<div class="textbox-label section-finalizarpagamento-view-pagamento-radio-credito-enderecoportador-textNumero">
											<label>Número</label> 
											<input type="text" id="numero" name="transacaoCobrancaNumero" value="${user.logradouronumero}">
										</div>											
										<div class="textbox-label section-finalizarpagamento-view-pagamento-radio-credito-enderecoportador-textComplemento">
											<label>Complemento</label> 
											<input type="text" id="complemento" name="transacaoCobrancaEnderecoComplemento" value="${user.complemento}">
										</div>										
										<div class="textbox-label section-finalizarpagamento-view-pagamento-radio-credito-enderecoportador-textBairro">
											<label>Bairro</label> 
											<input type="text" id="bairro" name="transacaoCobrancaBairro" value="${user.bairro}">
										</div>		
										<div class="textbox-label section-finalizarpagamento-view-pagamento-radio-credito-enderecoportador-textCidade">
											<label>Cidade</label> 
											<input type="text" id="cidade" name="transacaoCobrancaCidade" value="${user.cidade}">
										</div>													
										<div class="textbox-label section-finalizarpagamento-view-pagamento-radio-credito-enderecoportador-textEstado" >
											<label>Estado</label> 
											<input type="text" id="estado" name="transacaoCobrancaEstado" value="${user.estado}">
										</div>			
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="section-finalizarpagamento-view-pagamento-radio-boleto" id="section-finalizarpagamento-view-pagamento-radio-boleto">
						<div class="section-finalizarpagamento-view-pagamento-radio-boleto-descricao-img">
							<div>
								<input type="radio" id="billet" name="pagamento" value="billet" onselect="radioSelected(this.value);" onclick="radioSelected(this.value); getHashCode();"> 
								<label for="billet">Boleto</label><br>
							</div>
							<img src="" alt="boleto" class="section-finalizarpagamento-view-pagamento-radio-boleto-img" id="section-finalizarpagamento-view-pagamento-radio-boleto-img"></img>
						</div>	
						<div class="section-finalizarpagamento-radio-boleto" id="finalizarpagamento-radio-boleto">
							<div class="textbox-label textTelefone">
								<label>Telefone (Opcional)</label>
								<input type="text" path="textDadosCelular" id="textDadosCelular" name="transacaoBoletoTelefone" class="section-cadastro-FormCadastro-textbox" onkeypress="$(this).mask('(00)00000-0000')" placeholder="(11)98765-4321" value="${user.celular}" size="15" maxlength="15"></input> 
							</div>
							<div class="textbox-label textCPF">
								<label>CPF (Opcional)</label> 
								<input type="text" path="cpf" id="cpf" name="transacaoBoletoCPF" class="section-cadastro-FormCadastro-textbox" onkeypress="$(this).mask('000.000.000-00')" placeholder="000.000.000-00" size="15" maxlength="15" value="${user.cpf}"></input>									
							</div>
	
							<div class="section-finalizarpagamento-pagamento-3-descricao">
								<h4>Boleto Bancário</h4>
								<text> A Seguir o boleto bancário será exibido e disponível para
									pagamento dentro do prazo de validade em qualquer agência
									bancária, internet banking ou casas lotéricas.</text>
							</div>
							<input type="hidden" id="section-finalizarpagamento-radio-boleto-hashcode" name="transacaoHashCode" value="">							
							<input type="hidden" id="boletoOrCard" name="transacaoFormaPagamento" value="">
							<input type="hidden" id="dadosPortadorIguaisComprador" name="transacaoDadosPortadorIguaisComprador" value="true">
							<input type="hidden" id="dadosCobrancaIguaisEntrega" name="transacaoDadosCobrancaIguaisEntrega" value="true">
							<input type="hidden" id="transacaoCartaoToken" name="transacaoCartaoToken">
							<input type="hidden" id="transacaoQuantidadeParcelas" name="transacaoQuantidadeParcelas" value="">
							<input type="hidden" id="transacaoValorParcela" name="transacaoValorParcela" value="">
						</div>					
					</div>				
					<div class="section-finalizarpagamento-pagamento-4">
						<button type="submit" class="btn-secundario" name="name">Voltar</button>
						<button type="submit" class="btn-dpaulla" name="name" >Finalizar Pagamento</button>
					</div>
					</form:form>
 				</div>
				<div class="section-finalizarpagamento-view-resumo">
					<h4 class="titulo-DPaulla">RESUMO</h4> 
					<div class="section-finalizarpagamento-view-resumo-produtos" id="meucarrinho-container-produtos">
						<c:forEach var="comprasListSessions" items="${comprasListSession}">
							<h3 class="section-finalizarpagamento-view-resumo-produtos-rows" id="meucarrinho-container-descricao_${comprasListSessions.compraProdutoId}">${comprasListSessions.compraProdutoQuantidade}x ${comprasListSessions.compraProdutoDescricao}</h3>
							<input type="hidden" id="meucarrinho-container-valor-hidden_${comprasListSessions.compraProdutoId}" value="${comprasListSessions.compraProdutoValor}">
							<input type="hidden" id="meucarrinho-container-quantidade-hidden_${comprasListSessions.compraProdutoId}" value="${comprasListSessions.compraProdutoQuantidade}">
							<a class="section-finalizarpagamento-view-resumo-produtos-rows" id="meucarrinho-container-valor-text_${comprasListSessions.compraProdutoId}"></a>
						</c:forEach>					
					</div>
					<div class="section-finalizarpagamento-view-resumo-produtos-subtotal">
						<h3 class="section-finalizarpagamento-view-resumo-produtos-rows">Subtotal</h3>
						<a class="section-finalizarpagamento-view-resumo-produtos-rows" id="section-finalizarpagamento-view-resumo-produtos-subtotal-valor"></a>
						<h3 class="section-finalizarpagamento-view-resumo-produtos-rows">Frete</h3>
						<a class="section-finalizarpagamento-view-resumo-produtos-rows" id="section-finalizarpagamento-view-resumo-produtos-frete-valor"></a>						
					</div>					
					<div class="section-finalizarpagamento-view-resumo-produtos-total">
						<h3 class="section-finalizarpagamento-view-resumo-produtos-rows">Total</h3>
						<a class="section-finalizarpagamento-view-resumo-produtos-rows" id="section-finalizarpagamento-view-resumo-produtos-total-valor"></a>
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
<script>

function calculaValorReais(){
	var subtotalvalor = ${transacaoSession.orderSubtotalPrice};
	var valorconvertido = subtotalvalor.toLocaleString('pt-br',{style: 'currency', currency: 'BRL'});
	document.getElementById("section-finalizarpagamento-view-resumo-produtos-subtotal-valor").innerHTML = valorconvertido;
	var fretevalor = ${transacaoSession.orderShippingPrice};
	valorconvertido = fretevalor.toLocaleString('pt-br',{style: 'currency', currency: 'BRL'});
	document.getElementById("section-finalizarpagamento-view-resumo-produtos-frete-valor").innerHTML = valorconvertido;
	var totalvalor = ${transacaoSession.orderTotalPrice};
	valorconvertido = totalvalor.toLocaleString('pt-br',{style: 'currency', currency: 'BRL'});
	document.getElementById("section-finalizarpagamento-view-resumo-produtos-total-valor").innerHTML = valorconvertido;
	
	var ele = document.getElementById('meucarrinho-container-produtos').children;
	var match = new Array();
	var id;
 	for(var i =0;i<ele.length;i++)
	{
		if(ele[i].id.toString().replace("meucarrinho-container-descricao_","") > 0){
			id = ele[i].id.toString().replace("meucarrinho-container-descricao_", "");
			verificaElementoParaCalcularValor(id);
		}
	}
}
 
function verificaElementoParaCalcularValor(id){
	quantidade = parseFloat(document.getElementById("meucarrinho-container-quantidade-hidden_" + id).value);
	valor = parseFloat(document.getElementById("meucarrinho-container-valor-hidden_" + id).value);
	texto = quantidade*valor;
	var number2 = texto.toLocaleString('pt-br',{style: 'currency', currency: 'BRL'});
    document.getElementById("meucarrinho-container-valor-text_"+id).innerHTML = number2;
}

function radioSelected(valor) {
	if (valor == "billet") {
		document.getElementById("finalizarpagamento-radio-credito").style.display = "none";
    	document.getElementById("finalizarpagamento-radio-boleto").style.display = "grid";
        document.getElementById("boletoOrCard").value = 1;
        $(textDadosCelular).mask('(00)00000-0000');
        $(cpf).mask('000.000.000-00');
        //document.getElementById("textDadosCelular").mask('(00)00000-0000');
        //document.getElementById("cpf").mask('000.000.0000');
        //1 = BOLETO, 2 = CARTÃO DE CRÉDITO VISA, 3 = CARTÃO DE CRÉDITO MASTERCARD.

    }else if (valor == "creditCard") {
    	document.getElementById("finalizarpagamento-radio-boleto").style.display = "none";
    	document.getElementById("finalizarpagamento-radio-credito").style.display = "grid";
        document.getElementById("boletoOrCard").value = 2;
    }else if(valor == "dadosPortador"){
        $(transacaoCartaoTelefone).mask('(00)00000-0000');
        $(transacaoCartaoCPF).mask('000.000.000-00');
    	if (document.getElementById("dadosPortador").checked == true){
    		document.getElementById("finalizarpagamento-radio-credito-dadosportador").style.display = "none";	
    		document.getElementById("dadosPortadorIguaisComprador").value = "true";
    	}else{
    		document.getElementById("finalizarpagamento-radio-credito-dadosportador").style.display = "grid";
    		document.getElementById("dadosPortadorIguaisComprador").value = "false";
    	}
    }else if(valor == "enderecoPortador"){
    	$(transacaoCobrancaCep).mask('00.000-000');
    	if (document.getElementById("enderecoPortador").checked == true){
    		document.getElementById("finalizarpagamento-radio-credito-enderecoportador").style.display = "none";
    		document.getElementById("dadosCobrancaIguaisEntrega").value = "true";
    	}else{
    		document.getElementById("finalizarpagamento-radio-credito-enderecoportador").style.display = "grid";
    		document.getElementById("dadosCobrancaIguaisEntrega").value = "false";
    	}
    }
}

function meu_callback(conteudo) {
    if (!("erro" in conteudo)) {
        document.getElementById("logradouro").value=(conteudo.logradouro);
        document.getElementById("bairro").value =(conteudo.bairro);
        document.getElementById("cidade").value =(conteudo.localidade);
        document.getElementById('estado').value=(conteudo.uf);
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
        	document.getElementById("estado").value="...";
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
	document.getElementById("estado").value="";
};

</script>
</body>
</html>