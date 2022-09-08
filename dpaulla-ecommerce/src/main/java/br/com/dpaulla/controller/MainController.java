package br.com.dpaulla.controller;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import br.com.dpaulla.component.calcShippingCorreiosAPI;
import br.com.dpaulla.component.PagSeguroAPIClient;
import br.com.dpaulla.component.Util;
import br.com.dpaulla.model.OrdersBuy;
import br.com.dpaulla.model.Imagem;
import br.com.dpaulla.model.Marca;
import br.com.dpaulla.model.Produto;
import br.com.dpaulla.model.Role;
import br.com.dpaulla.model.order;
import br.com.dpaulla.model.User;
import br.com.dpaulla.model.shipping.ShippingCorreiosGet;
import br.com.dpaulla.repository.ImagemRepository;
import br.com.dpaulla.repository.MarcaRepository;
import br.com.dpaulla.repository.ProdutoRepository;
import br.com.dpaulla.service.CompraService;
import br.com.dpaulla.service.SecurityService;
import br.com.dpaulla.service.TransacaoService;
import br.com.dpaulla.service.UserService;

@Configuration
@Controller
@Service
public class MainController {
	
	private static Logger log = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	private SecurityService securityService;

	@Autowired
	MarcaRepository marcaRepository;

	@Autowired
	ProdutoRepository produtoRepository;

	@Autowired
	ImagemRepository imagemRepository;
	
	@Autowired
	TransacaoService transacaoService;
	
	@Autowired
	PagSeguroAPIClient clientePagSeguroAPI;

	@Autowired
	CompraService compraService;
	
	@Autowired
	UserService userService;
	
	Util utilFeatures = new Util();
	
	private final String formaPagamentoBoleto = "1";
	private final String formaPagamentoCartaoVisa = "2";
	private final String formaPagamentoCartaoMaster = "3";
	
	//private static String UPLOADED_FOLDER = System.getProperty("java.io.tmpdir"); //"F://temp//";
	//private static String UPLOADED_FOLDER = System.getenv("UPLOAD_FILE_PATH");
	//private static String UPLOADED_FOLDER = System.getProperties().toString();
	private static String UPLOADED_FOLDER = ""; 
	
	@RequestMapping(value = { "/para_seu_cabelo" }, method = RequestMethod.GET)
	public String cabeloPage(ModelMap model) {
		log.info("Enter on login");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("Para seu Cabelo");
		
		User user = securityService.findUserLogged();
		model.put("user", user);	

		List<Marca> listMarca = marcaRepository.findAll();
		List<String> list = new ArrayList<>();

		listMarca.stream().forEach(action -> {
			log.info("item -> {}", action.getMarcaDescricao());
			String descricao = action.getMarcaDescricao();
			list.add(descricao);
		});
		model.put("lists", list);

		return "cabelo";
	}

	@RequestMapping(value = { "/tipos_de_cabelo" }, method = RequestMethod.GET)
	public String tiposDeCabeloPage(ModelMap model) {
		log.info("Enter on login");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("Tipos De Cabelo");

		User user = securityService.findUserLogged();
		model.put("user", user);	
		
		List<Marca> listMarca = marcaRepository.findAll();
		List<String> list = new ArrayList<>();

		listMarca.stream().forEach(action -> {
			log.info("item -> {}", action.getMarcaDescricao());
			String descricao = action.getMarcaDescricao();
			list.add(descricao);
		});
		model.put("lists", list);

		return "tipo_cabelo";
	}

	
	@RequestMapping(value = { "/marcas" }, method = RequestMethod.GET)
	public String marcasPage(ModelMap model, HttpServletRequest request) {
		quantidadeProdutoCarrinho=0;
		log.info("Enter on login");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("Marcas");

		List<Marca> listMarca = marcaRepository.findAll();
		List<String> listMarcaString = new ArrayList<>();
		
		User user = securityService.findUserLogged();
		model.put("user", user);	

		List<Produto> listProduto = produtoRepository.findAll();

		listMarca.stream().forEach(action -> {
			log.info("item -> {}", action.getMarcaDescricao());
			String descricao = action.getMarcaDescricao();
			listMarcaString.add(descricao);
		});
		model.put("listMarcas", listMarcaString);
		model.put("listProduto", listProduto);
		model.put("quantidadeProduto", getQuantidadeProdutoMeuCarrinho(request));

		return "marca";
	}

	public static int marcaId = 0;
	public static int quantidadeProdutoCarrinho = 0;
	@RequestMapping(value = { "/marca" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String marcaPage(ModelMap model, @RequestParam("p") String marcaName, HttpServletRequest request) {
		quantidadeProdutoCarrinho=0;
		log.info("Enter on login {}", marcaName);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("Marcas");
		
		User user = securityService.findUserLogged();
		model.put("user", user);	

		List<Marca> listMarca = marcaRepository.findAll();
		List<String> listMarcaString = new ArrayList<>();
		listMarca.stream().forEach(action -> {
			String descricao = action.getMarcaDescricao();
			listMarcaString.add(descricao);
			if (descricao.equals(marcaName)) {
				marcaId = action.getMarcaId();
			}
			log.info("item -> {}", action.getMarcaDescricao());
		});
		List<Produto> listProduto = new ArrayList<Produto>();
		if (marcaId == 0) {
			listProduto = produtoRepository.findAll();
		} else {
			listProduto = produtoRepository.findByprodutoMarca(marcaId);
		}

		listProduto.stream().forEach(action -> {
			log.info("caminho prd: {}", action.getProdutoImagem());
		});
		model.put("listMarcas", listMarcaString);

		model.put("listProduto", listProduto);
		
		model.put("quantidadeProduto", getQuantidadeProdutoMeuCarrinho(request));

		return "marca";
	}
	
	private int getQuantidadeProdutoMeuCarrinho(HttpServletRequest request) {
		List<OrdersBuy> comprasListSession =  (List<OrdersBuy>) request.getSession().getAttribute("SESSION_COMPRAS");
		if (comprasListSession!=null) {
			comprasListSession.stream().forEach(action -> {
				int quantidadeTemp = action.getCompraProdutoQuantidade();
				quantidadeProdutoCarrinho = quantidadeProdutoCarrinho + quantidadeTemp;
			});
		}
		
		return quantidadeProdutoCarrinho;
	}
	
	@RequestMapping(value = { "/produto" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String produtoPage(ModelMap model, @RequestParam("p") int produtoId, HttpServletRequest request) {
		log.info("Enter on login {}", produtoId);
		Produto produtoSelecionado = produtoRepository.getOne(produtoId);
		
		User user = securityService.findUserLogged();
		model.put("user", user);	

		Marca marcaLogo = marcaRepository.getOne(produtoSelecionado.getProdutoMarca());
		List<Imagem> listImagem = imagemRepository.findImagemByprodutoId(produtoId);
		List<Integer> saldo = new ArrayList<Integer>();

		for (int i = 1; i <= produtoSelecionado.getProdutoSaldo(); i++) {
			saldo.add(i);
		}

		model.put("produto", produtoSelecionado);
		model.put("listImagens", listImagem);
		model.put("saldo", saldo);
		model.put("marcaLogo", marcaLogo);
		model.put("quantidadeProduto", getQuantidadeProdutoMeuCarrinho(request));
		
		return "produto";
	}

	boolean produtoExistenteSessao = false;
	Produto produtoTemp;
	OrdersBuy comprasSessionTemp;
	@PostMapping("/meuCarrinhoComprarAgora")
	public String meuCarrinhoComprarAgora(@ModelAttribute("produto") Produto produtoItem, HttpServletRequest request) {
		log.info("enter on /meucarrinhocomprarAgora");
		List<OrdersBuy> comprasListSession =  (List<OrdersBuy>) request.getSession().getAttribute("SESSION_COMPRAS");
		produtoExistenteSessao = false;
		if (comprasListSession == null) {
			comprasListSession = new ArrayList<>();
			//NECESSÁRIO MONTAR O DE-PARA DE PRODUTO PARA COMPRAS.
			OrdersBuy comprasTemp = new OrdersBuy();
			comprasTemp.setCompraProdutoDescricao(produtoItem.getProdutoDescricao());
			comprasTemp.setCompraProdutoId(produtoItem.getProdutoId());
			comprasTemp.setCompraProdutoQuantidade(produtoItem.getProdutoSaldo()); //NECESSÁRIO CONFIRMAR O SALDO, SE ESTÁ SUBINDO CERTO DO FORM DE PRODUTO
			comprasTemp.setCompraProdutoValor(produtoItem.getProdutoValor());
			comprasTemp.setCompraStatus("PENDENTE");
			comprasTemp.setCompraProdutoImagemPrincipal(produtoItem.getProdutoImagem());
			comprasListSession.add(comprasTemp);	
		}else {
			//TRANSFORMAR O PRODUTO NORMAL EM "PRODUTO PARA O MEU CARRINHO/COMPRA"
			//ESSE MÉTODO PRECISA PEGAR O ID DA COMPRAS JÁ EXISTENTE, E COLOCAR NOVAMENTE. 
			//verifica se já existe o produto na sessao do carrinho...
			log.info("Carrinho não está vazio, verifica se já existe produto, o atual selecionado é: {}", produtoItem);
			int produtoId = produtoItem.getProdutoId();
			comprasSessionTemp = null;
			int produtoPosition = 0;
			comprasListSession.stream().forEach(action -> {
				if(action.getCompraProdutoId()==produtoId) {
					produtoExistenteSessao = true;
					comprasSessionTemp = action;
				}
			});
			
			if (produtoExistenteSessao) {
				produtoPosition = comprasListSession.indexOf(comprasSessionTemp);
				comprasSessionTemp.setCompraProdutoQuantidade(comprasSessionTemp.getCompraProdutoQuantidade() + produtoItem.getProdutoSaldo());
				comprasListSession.set(produtoPosition, comprasSessionTemp);
			}
			else {
				comprasSessionTemp = new OrdersBuy();
				comprasSessionTemp.setCompraProdutoDescricao(produtoItem.getProdutoDescricao());
				comprasSessionTemp.setCompraProdutoId(produtoItem.getProdutoId());
				comprasSessionTemp.setCompraProdutoQuantidade(produtoItem.getProdutoSaldo()); //NECESSÁRIO CONFIRMAR O SALDO, SE ESTÁ SUBINDO CERTO DO FORM DE PRODUTO
				comprasSessionTemp.setCompraProdutoValor(produtoItem.getProdutoValor());
				comprasSessionTemp.setCompraStatus("PENDENTE");
				//comprasSessionTemp.setCompraUsuarioId(user.getUserId());
				comprasSessionTemp.setCompraProdutoImagemPrincipal(produtoItem.getProdutoImagem());
				comprasListSession.add(comprasSessionTemp);	
			}
		}
		request.getSession().setAttribute("SESSION_COMPRAS", comprasListSession);
		log.info("AS INFORMAÇÕES NO MSG: {}", comprasListSession);
		
		return "redirect:/meu-carrinho";
	}
	
	@RequestMapping(value = { "/meuCarrinhoRemoverProduto" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String produtoTeste(ModelMap model, @RequestParam("p") int produtoId, HttpServletRequest request) throws InterruptedException, ExecutionException {
		log.info("produto id: {}", produtoId);

		Produto produtoItem = produtoRepository.getOne(produtoId);
		User user = securityService.findUserLogged();
		List<OrdersBuy> comprasListSession =  (List<OrdersBuy>) request.getSession().getAttribute("SESSION_COMPRAS");
		log.info("ok get session");
		produtoExistenteSessao = false;
		comprasSessionTemp = null;
		int produtoPosition = 0;
		comprasListSession.stream().forEach(action -> {
			if(action.getCompraProdutoId()==produtoId) {
				produtoExistenteSessao = true;
				comprasSessionTemp = action;
			}
		});
		
		if (produtoExistenteSessao) {
			produtoPosition = comprasListSession.indexOf(comprasSessionTemp);
			comprasSessionTemp.setCompraProdutoQuantidade(comprasSessionTemp.getCompraProdutoQuantidade() + produtoItem.getProdutoSaldo());
			comprasListSession.remove(comprasSessionTemp);
		}
		
		request.getSession().setAttribute("SESSION_COMPRAS", comprasListSession);
		log.info("AS INFORMAÇÕES NO MSG: {}", comprasListSession);
		
		return "redirect:/meu-carrinho";
	}
	
	
	@PostMapping("/valorFrete")
	public String getValorFrete(ModelMap model, @ModelAttribute("endereco") User userEndereco, HttpServletRequest request) 
			throws ParseException, IOException, ParserConfigurationException, SAXException {
		log.info("enter on /valorFrete: {}", userEndereco);
		
		User userEnderecoSession = (User) request.getSession().getAttribute("SESSION_USER_ENDERECO");
		log.info("userEnderecoSession: {}", userEnderecoSession);
		if (userEnderecoSession == null) {
			userEnderecoSession = new User();
			request.getSession().setAttribute("SESSION_USER_ENDERECO", userEnderecoSession);
		}
		userEnderecoSession = userEndereco;
		request.getSession().setAttribute("SESSION_USER_ENDERECO", userEnderecoSession);

		log.info("saindo do /frete e entrando no /carrinho : {}", request.getSession().getAttribute("SESSION_USER_ENDERECO"));
		return "redirect:/meu-carrinho";
	}

	@GetMapping("/meu-perfil")
	public String meuPerfil(ModelMap model, HttpServletRequest request){
		log.info("enter on meu-perfil: {}");
		User user = securityService.findUserLogged();
		model.put("user", user);
		
		List<order> listTransacao = transacaoService.findAllByUsuarioId(user);
		
		model.put("transacoes", listTransacao);
		
		return "perfil";
	}
	
	private Double somaValorSubTotal = 0.0;
	
	//PRIMEIRA COISA A SE VERIFICAR NESSA TELA É SE TEM SESSÃO ATIVA DE PRODUTOS, SÓ CARREGAR O CALCULAR FRENTE SE TIVER.
	@GetMapping("/meu-carrinho")
	public String meuCarrinho(ModelMap model, HttpServletRequest request) throws IOException, ParserConfigurationException, SAXException, ParseException {
		log.info("enter on meu-carrinho: {}");
		//BUSCA O USUÁRIO LOGADO.
		User user = securityService.findUserLogged();
		//BUSCA USUÁRIO DA SESSÃO, SÓ VAI EXISTIR SE ENTROU NO CONTROLLER "CALCULAR FRETE", PARA USUÁRIOS LOGADOS ESSA FUNÇÃO ESTÁ DESABILITADA.
		User userEnderecoSession = (User) request.getSession().getAttribute("SESSION_USER_ENDERECO");
		//SE TIVER SESSÃO ATIVA, ALTERA O NOME PARA ANONIMO, PARA DIFERENCIAR ENTRE ANONIMO QUE CALCULOU FRETE E ANONIMO QUE NÃO CALCULOU FRETE.
		if (userEnderecoSession != null) {
			userEnderecoSession.setNome("anonimo");
		}
		//SE O USUÁRIO LOGADO FOR IGUAL A ANONIMO, ELE TRANSFERE PARA A VARIÁVEL "USER" OS VALORES DO USUÁRIO QUE CLICOU EM CALCULAR FRETE.
		if (user.getUsername().toString().equals("anonymousUser")) {
			user = userEnderecoSession;
		}
		
		
		List<OrdersBuy> comprasListSession =  (List<OrdersBuy>) request.getSession().getAttribute("SESSION_COMPRAS");
		
		if (comprasListSession == null) {
			comprasListSession = new ArrayList<>();
			model.addAttribute("comprasListSession", comprasListSession);
		}else {
			model.addAttribute("comprasListSession", comprasListSession);
		}
		log.info("sessionProdutos: {}", comprasListSession);
		
		//NECESSÁRIO AVALIAR ESSAS INICIALIZAÇÕES NULL.
		//SE TUDO ESTIVER OK, ELE ENTRA NA TELA COMO SE ESTIVESSE LOGADO.
		if (user != null) {
			model.put("user", user);
			calcShippingCorreiosAPI calculaFrete = new calcShippingCorreiosAPI();
			ShippingCorreiosGet freteCorreiosGet = calculaFrete.calcShipping(user.getCep());

			model.put("valorfrete", freteCorreiosGet);	
			somaValorSubTotal = 0.0;
			comprasListSession.stream().forEach(action -> {
				somaValorSubTotal = somaValorSubTotal + (Double.parseDouble(action.getCompraProdutoValor()) * action.getCompraProdutoQuantidade());
			});
			log.info("valor subtotal: {}", somaValorSubTotal);
			//float valorFrete = Float.parseFloat(freteCorreiosGet.getValor());
			//String strValorFrete = freteCorreiosGet.getValor().replace(",", ".");
			Double valorFrete = Double.parseDouble((freteCorreiosGet.getValor().replace(",", ".")));
			
			log.info("valor frete: {}", valorFrete);
			
			Double valorTotal = somaValorSubTotal + valorFrete;
			
			log.info("valor total: {}", valorTotal);

			order transacaoSession = (order) request.getSession().getAttribute("SESSION_TRANSACAO");
			if (transacaoSession == null) {
				transacaoSession = new order();
			}
			transacaoSession.setTransacaoCobrancaBairro(user.getBairro());
			transacaoSession.setTransacaoCobrancaCep(user.getCep());
			transacaoSession.setTransacaoCobrancaCidade(user.getCidade());
			transacaoSession.setTransacaoCobrancaEndereco(user.getLogradouro());
			transacaoSession.setTransacaoCobrancaEstado(user.getEstado());//NECESSÁRIO ADICIONAR ESTADO NO CADASTRO.
			transacaoSession.setTransacaoCobrancaNumero(user.getLogradouronumero());
			transacaoSession.setTransacaoEntregaBairro(user.getBairro());
			transacaoSession.setTransacaoEntregaCep(user.getCep());
			transacaoSession.setTransacaoEntregaCidade(user.getCidade());
			transacaoSession.setTransacaoEntregaEndereco(user.getLogradouro());
			transacaoSession.setTransacaoEntregaEstado(user.getEstado());//NECESSÁRIO ADICIONAR ESTADO NO CADASTRO
			transacaoSession.setTransacaoEntregaNumero(user.getLogradouronumero());
			transacaoSession.setTransacaoValorFrete(freteCorreiosGet.getValor());
			transacaoSession.setTransacaoTipoFrete(freteCorreiosGet.getCodigoDePara());
			transacaoSession.setTransacaoValorSubTotal(String.valueOf(somaValorSubTotal));
			transacaoSession.setTransacaoValorTotal(String.valueOf(valorTotal));
			request.getSession().setAttribute("SESSION_TRANSACAO", transacaoSession);
		}
		
		model.put("quantidadeProduto", getQuantidadeProdutoMeuCarrinho(request));
		
		return "meucarrinho";
	}
	
	
	@GetMapping("/finalizar-pagamento")
	public String finalizarPagamento(ModelMap model, HttpServletRequest request) throws IOException, ParserConfigurationException, SAXException, InterruptedException, ExecutionException, ParseException {
		log.info("enter on finalizar-pagamento{}");
		//PEGANDO SESSAO DA API PAGSEGURO
		ResponseEntity<String> retorno = clientePagSeguroAPI.getSession("", "");
		User user = securityService.findUserLogged();
		String retornoString = retorno.getBody();
		int httpCode = retorno.getStatusCodeValue();
		log.debug("RetornoString: {}", retornoString);
		log.debug("httpCode: {}", httpCode);
		
		Util util = new Util();

		String tokenSession = retornoString.substring(retornoString.lastIndexOf("<session><id>") + "<session><id>".length(), retornoString.indexOf("</id></session>"));
		log.info("tokenSession: {}", tokenSession);
		
		//PEGANDO SESSÃO DOS PRODUTOS EM MEU CARRINHO DPAULLA
		List<OrdersBuy> comprasListSession =  (List<OrdersBuy>) request.getSession().getAttribute("SESSION_COMPRAS");
		order transacaoSession = (order) request.getSession().getAttribute("SESSION_TRANSACAO");
		if (comprasListSession == null) {
			comprasListSession = new ArrayList<>();
			log.info("carrinho vazio! retornar vazio:");
			model.addAttribute("comprasListSession", comprasListSession);
			return "redirect:/meu-carrinho";

		}else {
			log.info("carrinho cheio, prosseguir!");
			model.addAttribute("comprasListSession", comprasListSession);
			model.put("transacaoSession", transacaoSession);
			
			log.info("VALOR JOGADO EM TELA: {}", transacaoSession);
			log.info("Informações de usuario: {}", user);
			order transacaoToSession = new order();
			transacaoToSession.setTransacaoCartaoCodigoSeguranca("");
			transacaoToSession.setTransacaoCartaoNumero("");
			transacaoToSession.setTransacaoCartaoNome(user.getNome() + " " + user.getSobrenome());
			transacaoToSession.setTransacaoCartaoVencimento("");
			transacaoToSession.setTransacaoCartaoCPF(user.getCpf());
			transacaoToSession.setTransacaoCartaoDatadeNascimento(user.getDatanascimento());
			transacaoToSession.setTransacaoCartaoTelefoneDDD(util.returnDDD(user));
			transacaoToSession.setTransacaoCartaoTelefone(util.returnTelefone(user));
			transacaoToSession.setTransacaoCobrancaBairro(user.getBairro());
			transacaoToSession.setTransacaoCobrancaCep(util.tratarCEP(user.getCep()));
			transacaoToSession.setTransacaoCobrancaCidade(user.getCidade());
			transacaoToSession.setTransacaoCobrancaEndereco(user.getLogradouro());
			transacaoToSession.setTransacaoCobrancaEnderecoComplemento(user.getComplemento());
			transacaoToSession.setTransacaoCobrancaEstado(user.getEstado());
			transacaoToSession.setTransacaoCobrancaNumero(user.getLogradouronumero());
			transacaoToSession.setTransacaoCompradorCPF(util.tratarCPF(user.getCpf()));	
			transacaoToSession.setTransacaoCompradorDataNascimento(user.getDatanascimento());
			transacaoToSession.setTransacaoCompradorEmail(user.getUsername());
			transacaoToSession.setTransacaoCompradorNome(user.getNome() + " " + user.getSobrenome());
			transacaoToSession.setTransacaoCompradorTelefone(util.returnTelefone(user));
			transacaoToSession.setTransacaoCompradorTelefoneDDD(util.returnDDD(user));
			transacaoToSession.setTransacaoEntregaBairro(user.getBairro());
			transacaoToSession.setTransacaoEntregaCep(util.tratarCEP(user.getCep()));
			transacaoToSession.setTransacaoEntregaCidade(user.getCidade());
			transacaoToSession.setTransacaoEntregaEndereco(user.getLogradouro());
			transacaoToSession.setTransacaoEntregaEnderecoComplemento(user.getComplemento());
			transacaoToSession.setTransacaoEntregaEstado(user.getEstado());
			transacaoToSession.setTransacaoEntregaNumero(user.getLogradouronumero());
			transacaoToSession.setTransacaoFormaPagamento("");
			transacaoToSession.setTransacaoHashCode("");
			transacaoToSession.setTransacaoBoletoLink("");
			transacaoToSession.setTransacaoQuantidadeParcelas("");
			transacaoToSession.setTransacaoDataGeracao(utilFeatures.dateTimeNow());
			transacaoToSession.setTransacaoBoletoDataGeracao("");
			transacaoToSession.setTransacaoDataPagamento("");
			transacaoToSession.setTransacaoStatus("Aguardando");
			transacaoToSession.setTransacaoUsuarioId(user.getUserId());
			transacaoToSession.setTransacaoValorFrete(transacaoSession.getTransacaoValorFrete());
			transacaoToSession.setTransacaoValorSubTotal(transacaoSession.getTransacaoValorSubTotal());
			transacaoToSession.setTransacaoValorTotal(transacaoSession.getTransacaoValorTotal());
			request.getSession().setAttribute("SESSION_TRANSACAO", transacaoToSession);
			
			model.put("sessao", tokenSession);
			model.put("user", user);	
			model.put("quantidadeProduto", getQuantidadeProdutoMeuCarrinho(request));

			return "finalizarpagamento";
		}
	}
	
	//ADICIONAR REGRA PARA QUANDO ESTIVER VAZIO RECIREDIONAR PARA O "MEU-CARRINHO"
	//PRIMEIRO PASSO, UNIR AS INFORMAÇÕES ENTRE OQUE SUBIU DO FORM E A ATUAL SESSÃO QUE POSSÚI JÁ TODAS AS INFORMAÇÕES COM EXCEÇÃO DOS DADOS DE CARTÃO
	//FLUXO PERFEITO - PESSOA COMPRANDO POR BOLETO.
	//FLUXO PERFEITO - PESSOA COMPRANDO COM CARTÃO DE CRÉDITO PRÓPRIO.
	//FLUXO IMPERFEITO - PESSOA COMPRANDO COM CARTÃO DE CRÉDITO DE OUTRA PESSOA.
	//FLUXO IMPERFEITO - PESSOA COMPRANDO COM CARTÃO DE CRÉDITO CORPORATIVO (DADOS DE COBRANÇA DIFERENTE DE DADOS DE ENTREGA)

	@PostMapping("/checkout")
	public String checkout(ModelMap model, @ModelAttribute("transacao") order transacao, HttpServletRequest request) 
			throws ParseException, IOException, ParserConfigurationException, SAXException, InterruptedException, ExecutionException, TransformerConfigurationException {
		log.info("enter on /checkout: {}", transacao);
		log.info("enter on /checkout Token Card: {}", transacao.getTransacaoCartaoToken());
		log.info("enter on /checkout Quantidade: {}", transacao.getTransacaoQuantidadeParcelas());
		log.info("enter on /checkout Valor: {}", transacao.getTransacaoValorParcela());		
		
		order transacaoSession = (order) request.getSession().getAttribute("SESSION_TRANSACAO");
		Util util = new Util();
		
		if (transacaoSession != null) {
			User user = securityService.findUserLogged();
			List<OrdersBuy> comprasListSession = (List<OrdersBuy>) request.getSession().getAttribute("SESSION_COMPRAS");
			
			String formaPagamento = transacao.getTransacaoFormaPagamento();
			String dadosPortadorIguaisComprador = transacao.getTransacaoDadosPortadorIguaisComprador();
			String dadosCobrancaIguaisEntrega = transacao.getTransacaoDadosCobrancaIguaisEntrega();
			
			log.info("dadosPortadorIguaisComprador {}", dadosPortadorIguaisComprador);
			log.info("dadosCobrancaIguaisEntrega {}", dadosCobrancaIguaisEntrega);
			//AQUI DEVE VERIFICAR QUAL É A FORMA DE PAGAMENTO, 1 BOLETO, 2 CARTAO DE CREDITO VISA E 3 CARTAO DE CREDITO MASTER.
			transacaoSession.setTransacaoFormaPagamento(formaPagamento);
			transacaoSession.setTransacaoHashCode(transacao.getTransacaoHashCode());			
			
			if(formaPagamento.equals(formaPagamentoBoleto)) {
				transacaoSession.setTransacaoBoletoTelefone(util.returnTelefone(transacao.getTransacaoBoletoTelefone()));
				transacaoSession.setTransacaoBoletoTelefoneDDD(util.returnDDD(transacao.getTransacaoBoletoTelefone()));
				transacaoSession.setTransacaoBoletoCPF(util.tratarCPF(transacao.getTransacaoBoletoCPF()));
			}else if(formaPagamento.equals(formaPagamentoCartaoVisa)) {
				transacaoSession.setTransacaoCartaoToken(transacao.getTransacaoCartaoToken());
				transacaoSession.setTransacaoQuantidadeParcelas(transacao.getTransacaoQuantidadeParcelas());
				transacaoSession.setTransacaoValorParcela(util.convertValue(transacao.getTransacaoValorParcela()));
				
				if (dadosPortadorIguaisComprador.equals("false")) {
					log.info("DEVE PREENCHER OS CAMPOS COM AS INFORMAÇÕES QUE SOBEM DO JSP");
					transacaoSession.setTransacaoCartaoNome(transacao.getTransacaoCartaoNome());					
					transacaoSession.setTransacaoCartaoCPF(util.tratarCPF(transacao.getTransacaoCartaoCPF()));
					transacaoSession.setTransacaoCartaoDatadeNascimento(transacao.getTransacaoCartaoDatadeNascimento());
					transacaoSession.setTransacaoCartaoTelefone(util.returnTelefone(transacao.getTransacaoCartaoTelefone()));
					transacaoSession.setTransacaoCartaoTelefoneDDD(util.returnDDD(transacao.getTransacaoCartaoTelefone()));
				}
				if(dadosCobrancaIguaisEntrega.equals("false")) {
					log.info("DEVE PREENCHER OS CAMPOS DE ENDEREÇO COM AS INFORMAÇÕES QUE SOBEM DO JSP");
					transacaoSession.setTransacaoCobrancaCep(util.tratarCEP(transacao.getTransacaoCobrancaCep()));
					transacaoSession.setTransacaoCobrancaEndereco(transacao.getTransacaoCobrancaEndereco());
					transacaoSession.setTransacaoCobrancaNumero(transacao.getTransacaoCobrancaNumero());
					transacaoSession.setTransacaoCobrancaEnderecoComplemento(transacao.getTransacaoCobrancaEnderecoComplemento());
					transacaoSession.setTransacaoCobrancaBairro(transacao.getTransacaoCobrancaBairro());
					transacaoSession.setTransacaoCobrancaCidade(transacao.getTransacaoCobrancaCidade());
					transacaoSession.setTransacaoCobrancaEstado(transacao.getTransacaoCobrancaEstado());
				}
			}

			order transacaoTemp = transacaoService.saveAndReturn(transacaoSession);
			log.info("transacao apos salva: {}", transacaoTemp);
			
			if (transacaoTemp != null) {
				request.getSession().setAttribute("SESSION_TRANSACAO", transacaoTemp);
			}

			int transacaoId = transacaoTemp.getTransacaoId();

			//FLUXO PERFEITO - BOLETO
			if(formaPagamento.equals(formaPagamentoBoleto)) {
				if (comprasListSession != null) {
					saveComprasList(comprasListSession, user, transacaoId);
				}
				
				ResponseEntity<String> retorno = clientePagSeguroAPI.checkout(transacaoTemp, comprasListSession, formaPagamento);
				String retornoString = retorno.getBody();
				int httpCode = retorno.getStatusCodeValue();
				log.info("RetornoString: {}", retornoString);
				log.info("httpCode: {}", httpCode);

				DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		        Document document = docBuilder.parse(new InputSource(new StringReader(retornoString)));
			
		        document.normalize();  
				NodeList nodeList = document.getElementsByTagName(document.getDocumentElement().getNodeName());
				
				String date = null, lastEventDate = null, code = null,paymentLink = null;

				for (int itr = 0; itr < nodeList.getLength(); itr++)   
				{  
					Node node = nodeList.item(itr);  
					if (node.getNodeType() == Node.ELEMENT_NODE)   
					{  
						Element eElement = (Element) node;  
						date = eElement.getElementsByTagName("date").item(0).getTextContent();
						lastEventDate = eElement.getElementsByTagName("lastEventDate").item(0).getTextContent();
						code = eElement.getElementsByTagName("code").item(0).getTextContent();
						paymentLink = eElement.getElementsByTagName("paymentLink").item(0).getTextContent();
					}  
				}

				transacaoTemp.setTransacaoBoletoLink(paymentLink);
				transacaoTemp.setTransacaoBoletoDataGeracao(date);
				transacaoTemp.setTransacaoDataPagamento(lastEventDate);
				transacaoTemp.setTransacaoCodigo(code);

				order teste = transacaoService.saveAndReturn(transacaoTemp);
				log.info("TransacaoSalva após receber o retorno do boleto com o Link etc... {}", teste);
			}else {
				log.info("cartao");
			
				if (comprasListSession != null) {
					saveComprasList(comprasListSession, user, transacaoId);
				}
				
				ResponseEntity<String> retorno = clientePagSeguroAPI.checkout(transacaoTemp, comprasListSession, formaPagamento);
				String retornoString = retorno.getBody();
				int httpCode = retorno.getStatusCodeValue();
				log.info("RetornoString: {}", retornoString);
				log.info("httpCode: {}", httpCode);

				DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		        Document document = docBuilder.parse(new InputSource(new StringReader(retornoString)));
			
		        document.normalize();  
				NodeList nodeList = document.getElementsByTagName(document.getDocumentElement().getNodeName());
				
				String date = null, lastEventDate = null, code = null,paymentLink = null;

				for (int itr = 0; itr < nodeList.getLength(); itr++)   
				{  
					Node node = nodeList.item(itr);  
					if (node.getNodeType() == Node.ELEMENT_NODE)   
					{  
						Element eElement = (Element) node;  
						date = eElement.getElementsByTagName("date").item(0).getTextContent();
						lastEventDate = eElement.getElementsByTagName("lastEventDate").item(0).getTextContent();
						code = eElement.getElementsByTagName("code").item(0).getTextContent();
					}  
				}

				/*
				 * transacaoTemp.setTransacaoBoletoLink(paymentLink);
				 * transacaoTemp.setTransacaoBoletoDataGeracao(date);
				 * transacaoTemp.setTransacaoDataPagamento(lastEventDate);
				 * transacaoTemp.setTransacaoCodigo(code);
				 */
				order teste = transacaoService.saveAndReturn(transacaoTemp);
				log.info("TransacaoSalva após receber o retorno do boleto com o Link etc... {}", teste);
			}
			
			comprasListSession = null;
			request.getSession().setAttribute("SESSION_COMPRAS", comprasListSession);
			request.getSession().setAttribute("SESSION_TRANSACAO", transacaoTemp);
			 		
			return "redirect:/checkout";
		}
		else {
			return "redirect:/meu-carrinho";
		}
	}
	
	private void saveComprasList(List<OrdersBuy> listCompras, User user, int transacaoId) {
		listCompras.stream().forEach(action -> {
			OrdersBuy comprasTemp = new OrdersBuy(); // comprasTemp.setCompraNumeroTransacao(transacaoId);
			comprasTemp.setCompraProdutoDescricao(action.getCompraProdutoDescricao());
			comprasTemp.setCompraProdutoId(action.getCompraProdutoId());
			comprasTemp.setCompraProdutoImagemPrincipal(action.getCompraProdutoImagemPrincipal());
			comprasTemp.setCompraProdutoQuantidade(action.getCompraProdutoQuantidade());
			comprasTemp.setCompraProdutoValor(action.getCompraProdutoValor());
			comprasTemp.setCompraStatus(action.getCompraStatus());
			comprasTemp.setCompraUsuarioId(user.getUserId());
			comprasTemp.setCompraTransacao(transacaoId);
			compraService.save(comprasTemp);
			log.info("compras dps de salvar: {}", comprasTemp);
		}); 
		
	}
	
	@GetMapping("/checkout")
	public String checkout(ModelMap model, HttpServletRequest request) throws IOException, ParserConfigurationException, SAXException, InterruptedException, ExecutionException {
		log.info("enter on checkout-get");

		//PEGANDO SESSÃO DOS PRODUTOS EM MEU CARRINHO DPAULLA
		order transacaoSession = (order) request.getSession().getAttribute("SESSION_TRANSACAO");
		log.info("transaction: {}", transacaoSession);
		if (transacaoSession != null) {
			model.put("transacaoSession", transacaoSession);
		}
		transacaoSession = null;
		
		request.getSession().setAttribute("SESSION_TRANSACAO", transacaoSession);
		model.put("quantidadeProduto", getQuantidadeProdutoMeuCarrinho(request));
		
		return "checkout";
	}
	
	@GetMapping("/acesso-restrito")
	public String acessoRestrito(ModelMap model) {
		log.info("enter on: {acesso restrito}");
		User user = securityService.findUserLogged();
		log.info("usuário logado: '{}', e '{}' e permissão: '{}'", user.getNome(), user.getUsername(), user.getRoles());
		model.put("user", user);
		String buildPage = "error";
		if (!user.getUsername().toString().equals("anonymousUser")) {
			Role roleTemp = new Role();
			roleTemp.setName("admin");
			roleTemp.setRoleId(Long.valueOf(String.valueOf(1)));
			roleTemp.setUsers(null);

			if(user.getRoles().contains(roleTemp)) {
				model.put("roles", user.getRoles());
			  	buildPage = "acesso_restrito";
			  	List<Marca> listMarca = marcaRepository.findAll(); 
			  	model.put("marcas",listMarca);
			  	
			  	List<Produto> listProduto = produtoRepository.findAll(); 
			  	model.put("produtos", listProduto);
			  	
			  }else {
				  buildPage = "error";
			  }			  
			
		}else {
			buildPage = "error";
		}
		
		model.put("tipo", "nok");

		return buildPage;
	}
	
	@GetMapping("/acesso-restrito-cadastrar-marca")
	public String acessoRestritoCadastrarMarca(ModelMap model) {
		log.info("enter on: {acesso acesso-restrito-cadastrar-marca}");
		User user = securityService.findUserLogged();
		log.info("usuário logado: '{}', e '{}' e permissão: '{}'", user.getNome(), user.getUsername(), user.getRoles());
		model.put("user", user);
		String buildPage = "error";
		if (!user.getUsername().toString().equals("anonymousUser")) {
			Role roleTemp = new Role();
			roleTemp.setName("admin");
			roleTemp.setRoleId(Long.valueOf(String.valueOf(1)));
			roleTemp.setUsers(null);

			if(user.getRoles().contains(roleTemp)) {
				model.put("roles", user.getRoles());
			  	buildPage = "cadastrar_marca";
			  }else {
				  buildPage = "error";
			  }			  
			
		}else {
			buildPage = "error";
		}

		return buildPage;
	}
	
	@GetMapping("/acesso-restrito-cadastrar-produto")
	public String acessoRestritoCadastrarProduto(ModelMap model) {
		log.info("enter on: {acesso acesso-restrito-cadastrar-produto}");
		User user = securityService.findUserLogged();
		log.info("usuário logado: '{}', e '{}' e permissão: '{}'", user.getNome(), user.getUsername(), user.getRoles());
		model.put("user", user);
		String buildPage = "error";
		if (!user.getUsername().toString().equals("anonymousUser")) {
			Role roleTemp = new Role();
			roleTemp.setName("admin");
			roleTemp.setRoleId(Long.valueOf(String.valueOf(1)));
			roleTemp.setUsers(null);

			if(user.getRoles().contains(roleTemp)) {
				model.put("roles", user.getRoles());
				List<Marca> listMarca = marcaRepository.findAll(); 
			  	model.put("marcas", listMarca);
			  	
			  	List<Integer> saldo = new ArrayList<Integer>();
				for (int i = 1; i <= 30; i++) { /// ASSUMIR QUE A QUANTIDADE MÁXIMA DE PRODUTO QUE PODE SE TER EM ESTOQUE É 30.
					saldo.add(i);
				}
				
				model.put("saldo", saldo);
			  	
			  	buildPage = "cadastrar_produto";
			  }else {
				  buildPage = "error";
			  }			  
			
		}else {
			buildPage = "error";
		}

		return buildPage;
	}	
	
	
	@PostMapping("/cadastrarMarca")
	public String acessoRestritoCadastrarMarca(@RequestParam("marcaDescricao") String marcaDescricao, 
			@RequestParam("marcaNivel") String marcaNivel,
            @RequestParam("marcaLogo") MultipartFile[] uploadfiles, ModelMap model) 
			throws ParseException, IOException, ParserConfigurationException, SAXException {
		log.info("enter on /cadastrarMarca: {}", marcaDescricao);
		UPLOADED_FOLDER = "src/main/resources/static/assets/images/marcas/";
		log.info("UPLOAD FOLDER: {}, full path: {},  array: {}", UPLOADED_FOLDER);
		
		Marca marca = new Marca();
		marca.setMarcaDescricao(marcaDescricao);
		marca.setMarcaNivel(marcaNivel);
		
		Marca marcaReturn = marcaRepository.saveAndFlush(marca);	
		
		log.info("retorno do marca Id: {}, tudo: {}", marcaReturn.getMarcaId(), marcaReturn);
		
		String filename = marcaReturn.getMarcaId() + ".jpg";
		marcaReturn.setMarcaLogo("assets/images/marcas/" + filename);
		marcaRepository.saveAndFlush(marcaReturn);
		
        //String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
        //        .filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

        try {
            saveUploadedFiles(Arrays.asList(uploadfiles), filename);
        } catch (IOException e) {
        	log.info("Error to save File: '{}'", e);
        }

        model.put("tipo", "ok");
        
        return "acesso_restrito";
	}
	
	@PostMapping("/cadastrarProduto")
	public String acessoRestritoCadastrarProduto(@RequestParam("produtoDescricao") String produtoDescricao, 
			@RequestParam("produtoMarca") String produtoMarca,@RequestParam("produtoSaldo") String produtoSaldo,
			@RequestParam("produtoValor") String produtoValor,
            @RequestParam("produtoImagem") MultipartFile[] produtoImagem, 
            @RequestParam("imagemLateral") MultipartFile[] imagemLateral,
            @RequestParam("imagemSuperior") MultipartFile[] imagemSuperior,
            @RequestParam("imagemModelo") MultipartFile[] imagemModelo,
            ModelMap model) 
			throws ParseException, IOException, ParserConfigurationException, SAXException {
		log.info("enter on /cadastrarProduto: {}", produtoDescricao);
		UPLOADED_FOLDER = "src/main/resources/static/assets/images/produtos/";
		log.info("UPLOAD FOLDER: {}, full path: {},  array: {}", UPLOADED_FOLDER);

		Produto produto = new Produto();
		produto.setProdutoDescricao(produtoDescricao);
		log.info("marca do produto: {}", produtoMarca);
		produto.setProdutoMarca(Integer.parseInt(produtoMarca));
		produto.setProdutoSaldo(Integer.parseInt(produtoSaldo));
		produto.setProdutoValor(produtoValor);
		
		Produto produtoTemp = produtoRepository.saveAndFlush(produto);
		
		log.info("retorno do produto Id: {}, tudo: {}", produtoTemp.getProdutoId(), produtoTemp);
		
		String filenameFrontal = produtoTemp.getProdutoId() + ".jpg";
		produtoTemp.setProdutoImagem("assets/images/produtos/" + filenameFrontal);
		
		Imagem imageFrontal = new Imagem();
		imageFrontal.setImagemDiretorio("assets/images/produtos/" + filenameFrontal);
		imageFrontal.setProdutoId(produtoTemp.getProdutoId());
		imageFrontal.setImagemTipo("Frontal");
		imagemRepository.save(imageFrontal);
		
		Imagem imageLateral = new Imagem();
		String filenameLateral = produtoTemp.getProdutoId() + "-2" + ".jpg";
		imageLateral.setImagemDiretorio("assets/images/produtos/" + filenameLateral);
		imageLateral.setImagemTipo("Lateral");
		imageLateral.setProdutoId(produtoTemp.getProdutoId());
		imagemRepository.save(imageLateral);
		
		Imagem imageSuperior = new Imagem();
		String filenameSuperior = produtoTemp.getProdutoId() + "-3" + ".jpg";
		imageSuperior.setImagemDiretorio("assets/images/produtos/" + filenameSuperior);
		imageSuperior.setImagemTipo("Superior");
		imageSuperior.setProdutoId(produtoTemp.getProdutoId());
		imagemRepository.save(imageSuperior);
		
		Imagem imageModelo = new Imagem();
		String filenameModelo = produtoTemp.getProdutoId() + "-4" + ".jpg";
		imageModelo.setImagemDiretorio("assets/images/produtos/" + filenameModelo);
		imageModelo.setImagemTipo("Modelo");
		imageModelo.setProdutoId(produtoTemp.getProdutoId());
		imagemRepository.save(imageModelo);
		
		produtoRepository.saveAndFlush(produtoTemp);
		
        try {
        	//PRIMEIRO UPLOAD DA FRONTAL
            saveUploadedFiles(Arrays.asList(produtoImagem), filenameFrontal);
            //SEGUNDO UPLOAD DA LATERAL
            saveUploadedFiles(Arrays.asList(imagemLateral), filenameLateral);
            //TERCEIRO UPLOAD DA SUPERIOR
            saveUploadedFiles(Arrays.asList(imagemSuperior), filenameSuperior);
            //QUARTO UPLOAD DA SUPERIOR
            saveUploadedFiles(Arrays.asList(imagemModelo), filenameModelo);
            
        } catch (IOException e) {
        	log.info("Error to save File: '{}'", e);
        }

        model.put("tipo", "ok");
        
        return "acesso_restrito";
	}
	
    private void saveUploadedFiles(List<MultipartFile> files, String nameFile) throws IOException {
        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue;
            }
            log.info("file: {}", file.getOriginalFilename());
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + nameFile);
            Files.write(path, bytes);
        }
    }
	
	
}