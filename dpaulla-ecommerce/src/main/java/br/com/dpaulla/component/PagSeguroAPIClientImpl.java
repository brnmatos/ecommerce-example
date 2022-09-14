package br.com.dpaulla.component;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.xml.sax.SAXException;

import br.com.dpaulla.model.OrdersBuy;
import br.com.dpaulla.model.order;

@Service
public class PagSeguroAPIClientImpl implements PagSeguroAPIClient {
	
	private static Logger log = LoggerFactory.getLogger(PagSeguroAPIClientImpl.class);

	@Value("${app.config.pagseguro.urlsessao}")
	private String urlSession;
	
	@Value("${app.config.pagseguro.urltransacao}")
	private String urlOrder;
	
	@Value("${app.config.pagseguro.email}")
	private String mailPag;

	@Value("${app.config.pagseguro.token}")
	private String tokenPag;
	
	@Value("${app.config.pagseguro.paymentMode}")
	private String paymentMode ;
	
	@Value("${app.config.pagseguro.paymentMethodBoleto}")
	private String paymentMethodBoleto;
	
	@Value("${app.config.pagseguro.paymentMethodCartao}")
	private String paymentMethodCartao;
	
	@Value("${app.config.pagseguro.currencyCoin}")
	private String currency;
	
	@Value("${app.config.pagseguro.extraAmount}")
	private String extraAmount;
	
	@Value("${app.config.pagseguro.notificationURL}")
	private String notificationURL;
	
	@Value("${app.config.pagseguro.shippingAddressRequired}")
	private String shippingAddressRequired;
	
	@Value("${app.config.pagseguro.shippingAddressCountry}")
	private String shippingAddressCountry;
	
	@Value("${app.config.pagseguro.noInterestInstallmentQuantity}")
	private String noInterestInstallmentQuantity; //quantidade que assumimos de parcelas sem juros, 2 é o padrão do pagseguro, maior que isso será descontado do DPAULLA.
	
	private static int countProdutos = 0;
	private RestTemplate restTemplate = new RestTemplate();
	private final String modeBol = "1";
	private final String modeCard = "2";

	@Override
	public ResponseEntity<String> getSession(String email, String token) {
		String uri = urlSession + "email=" + mailPag + "&token=" + tokenPag;
		log.debug("Enter on API client !!!");

		return restTemplate.exchange(uri, HttpMethod.POST, null, String.class);
	}

	@Override
	public ResponseEntity<String> checkout(order transacao, List<OrdersBuy> orderBuyList, String bolOrCard) throws IOException, ParserConfigurationException, SAXException {
		log.debug("Enter on API client |BOL|!!!" );
		String uri = urlOrder + "email=" + mailPag + "&token=" + tokenPag;
		log.info("{}", transacao);
		log.info("{}", orderBuyList);

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("paymentMode", paymentMode); //OK
		map.add("currency", currency); //OK
		map.add("extraAmount", extraAmount);  //
		map.add("notificationURL", notificationURL); //
		map.add("reference", String.valueOf(transacao.getId())); //
		map.add("senderName", transacao.getTransacaoCompradorNome()); //OK
		map.add("senderEmail", "matos.brn@sandbox.pagseguro.com.br");//transacao.getTransacaoCompradorEmail());
		map.add("senderHash", transacao.getHashCode()); //
		map.add("shippingAddressStreet", transacao.getTransacaoCobrancaEndereco()); //
		map.add("shippingAddressNumber", transacao.getTransacaoCobrancaNumero());//
		map.add("shippingAddressComplement", transacao.getTransacaoCobrancaEnderecoComplemento()); //
		map.add("shippingAddressDistrict", transacao.getTransacaoCobrancaBairro());//
		map.add("shippingAddressPostalCode", transacao.getTransacaoCobrancaCep());//
		map.add("shippingAddressCity", transacao.getTransacaoCobrancaCidade());//
		map.add("shippingAddressState", transacao.getTransacaoCobrancaEstado());//
		map.add("shippingAddressCountry", shippingAddressCountry);		//
		
		//AQUI COMEÇA O ESPECIFICO.
		if(bolOrCard.equals(modeBol)) {
			map.add("paymentMethod", paymentMethodBoleto); //
			map.add("senderCPF",  transacao.getTransacaoBoletoCPF()); //
			map.add("senderAreaCode", transacao.getTransacaoBoletoTelefoneDDD()); //
			map.add("senderPhone", transacao.getTransacaoBoletoTelefone()); //
		}else if(bolOrCard.equals(modeCard)) {
			map.add("senderCPF", transacao.getTransacaoCompradorCPF());
			map.add("senderAreaCode", transacao.getTransacaoCompradorTelefoneDDD());
			map.add("senderPhone", transacao.getTransacaoCompradorTelefone());
			map.add("shippingAddressRequired", shippingAddressRequired);   //
			map.add("shippingType", transacao.getOrderShippingType()); //VERIFICAR SE SUBIRÁ OK COMO 1 OU 2
			map.add("shippingCost", transacao.getOrderShippingPrice()); //VERIFICAR
			map.add("creditCardToken", transacao.getTransacaoCartaoToken()); //VERIFICAR SE SUBIRÁ OK
			map.add("installmentQuantity", transacao.getOrderPaymentAmounts()); //VERIFICAR SE SUBIRÁ OK
			map.add("installmentValue", transacao.getOrderPaymentPrinceAmount()); //VERIFICAR SE SUBIRÁ OK
			map.add("noInterestInstallmentQuantity", noInterestInstallmentQuantity); //VERIFICAR SE SUBIRÁ OK
			map.add("creditCardHolderName", transacao.getTransacaoCartaoNome()); //VERIFICAR
			map.add("creditCardHolderCPF", transacao.getTransacaoCartaoCPF());  //VERIFICAR
			map.add("creditCardHolderBirthDate", transacao.getTransacaoCartaoDatadeNascimento());  //VERIFICAR SE SUBIRÁ OK
			map.add("creditCardHolderAreaCode", transacao.getTransacaoCartaoTelefoneDDD());  //VERIFICAR SE SUBIRÁ OK
			map.add("creditCardHolderPhone", transacao.getTransacaoCartaoTelefone());	//VERIFICAR SE SUBIRÁ OK
			map.add("billingAddressStreet", transacao.getTransacaoEntregaEndereco()); //VERIFICAR
			map.add("billingAddressNumber", transacao.getTransacaoEntregaNumero()); //VERIFICAR 
			map.add("billingAddressComplement", transacao.getTransacaoEntregaEnderecoComplemento()); //VERIFICAR
	 		map.add("billingAddressDistrict", transacao.getTransacaoEntregaBairro()); //VERIFICAR
			map.add("billingAddressPostalCode", transacao.getTransacaoEntregaCep()); //VERIFICAR
			map.add("billingAddressCity", transacao.getTransacaoEntregaCidade()); //VERIFICAR
			map.add("billingAddressState", transacao.getTransacaoEntregaEstado()); //VERIFICAR
			map.add("billingAddressCountry", shippingAddressCountry); //
		}
		
		Util util = new Util();
		countProdutos = 1; 
		orderBuyList.stream().forEach(action -> {
			map.add("itemId"+countProdutos, String.valueOf(action.getCompraProdutoId()));
			map.add("itemDescription"+countProdutos, action.getCompraProdutoDescricao());
			map.add("itemAmount"+countProdutos, util.convertValue(action.getCompraProdutoValor()));	
			map.add("itemQuantity"+countProdutos, String.valueOf(action.getCompraProdutoQuantidade())); 
			countProdutos++; 
			}
		);
		 
		log.debug("mount de map: {}", map);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		log.debug("mount header: {}", headers);

		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(map, headers);
		log.debug("entity: {}", entity);
		return restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
	}
	
}
