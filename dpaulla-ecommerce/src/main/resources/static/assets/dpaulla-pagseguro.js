var urlImageCartaoVisa;
var urlImageCartaoMastercard;
var formasPagamento;
function getPaymentMethodsDPaulla(sessionToken, valortotal){
	var urlPagSeguroImagem = "https://stc.pagseguro.uol.com.br";
	PagSeguroDirectPayment.setSessionId(sessionToken);
	PagSeguroDirectPayment.getPaymentMethods({
	    amount: valortotal,
	    success: function(response) {
	        // Retorna os meios de pagamento disponíveis.
	        console.log("Log: --" + "***");
	        var cartao = response.paymentMethods.CREDIT_CARD;
	        var boleto = response.paymentMethods.BOLETO;

	        if(boleto != null){
	        	document.getElementById("section-finalizarpagamento-view-pagamento-radio-boleto").style.display = "grid";
		        //ENTRANDO DENTRO DO XML E PEGANDO O VALOR DE PATH.
		        var imageBoleto = urlPagSeguroImagem + boleto.options.BOLETO.images.SMALL.path;
	        	document.getElementById("section-finalizarpagamento-view-pagamento-radio-boleto-img").src = imageBoleto;
	        	document.getElementById("section-finalizarpagamento-view-pagamento-radio-boleto-img").style.display = "flex";
	        }
	        if(cartao != null){
		        cartaoVisa = cartao.options.VISA;
		        cartaoMastercard = cartao.options.MASTERCARD;

		        if(cartaoVisa != null){
		    		var imageCartaoVisa = urlPagSeguroImagem + cartaoVisa.images.SMALL.path;
		    		urlImageCartaoVisa = imageCartaoVisa;
		    		document.getElementById("section-finalizarpagamento-view-pagamento-radio-cartaocredito-descricao-img-2cards-img-visa").src = imageCartaoVisa;
		    		document.getElementById("section-finalizarpagamento-view-pagamento-radio-cartaocredito-descricao-img-2cards-img-visa").style.display = "flex";
		    	}
		        if(cartaoMastercard != null){
		    		var imageCartaoMastercard = urlPagSeguroImagem + cartaoMastercard.images.SMALL.path;
		    		urlImageCartaoMastercard = imageCartaoMastercard;
		    		document.getElementById("section-finalizarpagamento-view-pagamento-radio-cartaocredito-descricao-img-2cards-img-mastercard").src = imageCartaoMastercard;
		    		document.getElementById("section-finalizarpagamento-view-pagamento-radio-cartaocredito-descricao-img-2cards-img-mastercard").style.display = "flex";
		    	}
		        
	        	document.getElementById("section-finalizarpagamento-view-pagamento-radio-cartaocredito").style.display = "grid";	        	
	        }else{
	        	console.log("Cartao esta vazio!");
	        }
	    },
	    error: function(response) { 
	        // Callback para chamadas que falharam.
	    },
	    complete: function(response) {
	    }
	});
}

function splitInstallments(valor){
	if(valor != ""){
		var valorParcela = formasPagamento[valor-1].installmentAmount;
		document.getElementById("transacaoQuantidadeParcelas").value = valor;
		document.getElementById("transacaoValorParcela").value = valorParcela;
	}
}

function getHashCode(){
	var hashCode;
	PagSeguroDirectPayment.onSenderHashReady(function(response){
	    if(response.status == 'error') {
	        hashCode = "vazio";
	        console.log(response);
	    }else{
	    	hashCode = response.senderHash;
	    }
    	document.getElementById("section-finalizarpagamento-radio-boleto-hashcode").value = hashCode;
	});
}

function getInstallments(valorTotal, numeroCartao){
	var bandeiraCartao;
	var quantidadeFormasPagamento;
	var formasPagamentoDetalhe;
	
	numeroCartao = numeroCartao.replace(".", "");
	numeroCartao = numeroCartao.substring(6, 0);
	PagSeguroDirectPayment.getBrand({
	    cardBin: numeroCartao,
	    success: function(response) {
	    	bandeiraCartao = response.brand.name;
	    	console.log(response.brand.name);
	    	PagSeguroDirectPayment.getInstallments({
	            amount: valorTotal,
	            maxInstallmentNoInterest: 2,
	            brand: bandeiraCartao,
	            success: function(response){
	            	if(bandeiraCartao == "visa"){
	            		quantidadeFormasPagamento = response.installments.visa.length;
	            		formasPagamento = response.installments.visa;
	            		
	            		for (var i = 0; i < quantidadeFormasPagamento; i++) {
	            			var nParcela = formasPagamento[i].quantity;
	            			var qtdeParcela = nParcela;
	            			if(nParcela == 1){
	            				nParcela = nParcela + " Parcela de R$ ";
	            				document.getElementById("transacaoQuantidadeParcelas").value = qtdeParcela;
	            				document.getElementById("transacaoValorParcela").value = valorParcela;	            				
	            			}else{
	            				nParcela = nParcela + " Parcelas de R$ ";
	            			}
	            			var valorParcela = formasPagamento[i].installmentAmount;
	            			var totalParcela = formasPagamento[i].totalAmount;
	            			var semJuros;
	            			
	            			if(formasPagamento[i].interestFree == true){
	            				semJuros = "- Sem juros"
	            			}else{
	            				semJuros = "***"
	            			}
	            			
	            			var detalhe = nParcela + valorParcela + "(R$ " + totalParcela + ") " + semJuros;
	            			var formaPagamentoSelect = document.getElementById("selectTransacaoQuantidadeParcelas");
	            			var option = document.createElement("option");
	            			option.text = detalhe;
	            			option.value = qtdeParcela;
	            			option.id = valorParcela;
	            			
	            			formaPagamentoSelect.add(option, formaPagamentoSelect[i]);	            			
						}
	            		
	            	}else{
	            		console.log("entrando no js");
	            		quantidadeFormasPagamento = response.installments.mastercard.length;
	            		formasPagamento = response.installments.mastercard;
	            		
	            		for (var i = 0; i < quantidadeFormasPagamento; i++) {
	            			var nParcela = formasPagamento[i].quantity;
	            			var qtdeParcela = nParcela;
	            			if(nParcela == 1){
	            				nParcela = nParcela + " Parcela de R$ ";
	            				document.getElementById("transacaoQuantidadeParcelas").value = qtdeParcela;
	            				document.getElementById("transacaoValorParcela").value = valorParcela;	            				
	            			}else{
	            				nParcela = nParcela + " Parcelas de R$ ";
	            			}
	            			var valorParcela = formasPagamento[i].installmentAmount;
	            			var totalParcela = formasPagamento[i].totalAmount;
	            			var semJuros;
	            			
	            			if(formasPagamento[i].interestFree == true){
	            				semJuros = "- Sem juros"
	            			}else{
	            				semJuros = "***"
	            			}
	            			
	            			var detalhe = nParcela + valorParcela + "(R$ " + totalParcela + ") " + semJuros;
	            			var formaPagamentoSelect = document.getElementById("selectTransacaoQuantidadeParcelas");
	            			var option = document.createElement("option");
	            			option.text = detalhe;
	            			option.value = qtdeParcela;
	            			option.id = valorParcela;
	            			
	            			formaPagamentoSelect.add(option, formaPagamentoSelect[i]);	            			
	            		}
	            	}
	           },
	            error: function(response) {
	           },
	            complete: function(response){
	           }
	    });
	    },
	    error: function(response) {
	    	console.log("error, número de cartão não encontrado");
	    },
	    complete: function(response) {
	    	console.log("retorno complete");
	    }
	});	
}
//MELHORAR ESSA FUNÇÃO, COLOCAR PARA BUSCAR A BANDEIRA APENAS QUANDO SAIR DO CAMPO, FAZER O SUBSTRING E PEGAR OS 6 PRIMEIROS DIGITOS.
function getBandeiraCartao(cardBinValue){
	document.getElementById("labelNumeroCartao").innerHTML= cardBinValue;
	cardBinValue = cardBinValue.replace(".", "");
	var binCard = cardBinValue.length;
	var bandeira;
	if(binCard == 6){
		PagSeguroDirectPayment.getBrand({
		    cardBin: cardBinValue,
		    success: function(response) {
		    	bandeira = response.brand.name;
		    	if(bandeira == "visa"){
		    		document.getElementById("imgBandeiraCartao").src = urlImageCartaoVisa;
		    		document.getElementById("imgBandeiraCartao").style.display = "flex";
		    		document.getElementById("bandeiraCartao").value = "visa";
		    	}else if(bandeira == "mastercard"){
		    		document.getElementById("imgBandeiraCartao").src = urlImageCartaoMastercard;
		    		document.getElementById("imgBandeiraCartao").style.display = "flex";
		    		document.getElementById("bandeiraCartao").value = "mastercard";
		    	}
		    },
		    error: function(response) {
		    	console.log("error, número de cartão não encontrado2");
		    },
		    complete: function(response) {
		    }
		});
	}
}

function setCartaoCredito(){
	var numeroCartaoCredito = document.getElementById("textCartaoCredito").value;
	numeroCartaoCredito = numeroCartaoCredito.replace(".","");
	numeroCartaoCredito = numeroCartaoCredito.replace(".","");
	numeroCartaoCredito = numeroCartaoCredito.replace(".","");
	numeroCartaoCredito = numeroCartaoCredito.replace(".","");
	var bandeiraCartao = document.getElementById("bandeiraCartao").value;
	var cvvCartao = document.getElementById("textCodigoSeguranca").value;
	var dataVencimento = document.getElementById("transacaoCartaoVencimento").value;
	var mesExpiracaoCartao = dataVencimento.substr(0, 2);
	var anoExpiracaoCartao = dataVencimento.substr(3, 4);
	var cardToken;
	
	if((numeroCartaoCredito!="") && (bandeiraCartao !="") && (cvvCartao != "") && (mesExpiracaoCartao != "") && (anoExpiracaoCartao != "")){
		PagSeguroDirectPayment.createCardToken({
			   cardNumber: numeroCartaoCredito, // Número do cartão de crédito
			   brand: bandeiraCartao, // Bandeira do cartão
			   cvv: cvvCartao, // CVV do cartão
			   expirationMonth: mesExpiracaoCartao, // Mês da expiração do cartão
			   expirationYear: anoExpiracaoCartao, // Ano da expiração do cartão, é necessário os 4 dígitos.
			   success: function(response) {
			        // Retorna o cartão tokenizado.
				   console.log(response.card.token);
				   cardToken = response.card.token;
				   //window.location.replace("/checkout?cardToken="+cardToken);
				   document.getElementById("transacaoCartaoToken").value = cardToken;
				   console.log("retorno - " + cardToken);
			   },
			   error: function(response) {
			            // Callback para chamadas que falharam.
				   console.log(response);
			   },
			   complete: function(response) {
			        // Callback para todas chamadas.
				   console.log(response);
			   }
			});
	}
}

function setVencimentoCartao(vencimento){
	document.getElementById("labelVencimentoCartao").innerHTML= vencimento;
}

function setNomeCartao(nome){
	document.getElementById("labelNomeCartao").innerHTML= nome;
}