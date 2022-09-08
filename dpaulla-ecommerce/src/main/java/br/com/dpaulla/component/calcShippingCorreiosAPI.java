package br.com.dpaulla.component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.time.LocalDate;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import br.com.dpaulla.model.shipping.ShippingCorreiosGet;
import br.com.dpaulla.model.shipping.ShippingCorreiosPost;

public class calcShippingCorreiosAPI {
	
	@Value("${app.config.correios.dias.padrao.envio}")
	private int defaultDaysToSend;
	
	@Value("${app.config.correios.endereco.origem.envio}")
	private String streetCepSource;
	
	private static Logger log = LoggerFactory.getLogger(calcShippingCorreiosAPI.class);
	
	public ShippingCorreiosGet calcShipping(String streetCep) throws IOException, ParserConfigurationException, SAXException, ParseException {
		ShippingCorreiosPost shippingCorreiosPost = shippingCorreiosPost(streetCep); 
		String urlWSCorreios = "http://ws.correios.com.br/calculador/CalcPrecoPrazo.aspx?";
		String nCdEmpresa = "&nCdEmpresa=" + shippingCorreiosPost.getNCdEmpresa(); //empresa no contrato, se nao tiver igual a vazio.
		String sDsSenha = "&sDsSenha=" + shippingCorreiosPost.getSDsSenha();	//senha no contrato, se nao tiver igual a vazio.
		String sCepOrigem = "&sCepOrigem=" + shippingCorreiosPost.getSCepOrigem(); 	//cep sem hifen ou ponto.
		String sCepDestino = "&sCepDestino=" + shippingCorreiosPost.getSCepDestino();	//cep sem hifen ou ponto.
		String nVlPeso = "&nVlPeso=" + shippingCorreiosPost.getNVlPeso();		//em KG 
		String nCdFormato = "&nCdFormato=" + shippingCorreiosPost.getNCdFormato();		//1 - caixa pacote, 2 - rolo prisma, 3 - carta
		String nVlComprimento = "&nVlComprimento=" + shippingCorreiosPost.getNVlComprimento();	//em centimetros
		String nVlAltura = "&nVlAltura=" + shippingCorreiosPost.getNVlAltura();		//em centimetros
		String nVlLargura = "&nVlLargura=" + shippingCorreiosPost.getNVlLargura();		//em centimetros
		String sCdMaoPropria = "&sCdMaoPropria=" + shippingCorreiosPost.getSCdMaoPropria();		//n
		String nVlValorDeclarado = "&nVlValorDeclarado=" + shippingCorreiosPost.getNVlValorDeclarado();	//n
		String sCdAvisoRecebimento = "&sCdAvisoRecebimento=" + shippingCorreiosPost.getSCdAvisoRecebimento();//n
		String nCdServico = "&nCdServico=" + shippingCorreiosPost.getNCdServico();	//tabela de codigo acima
		String nVlDiametro = "&nVlDiametro=" + shippingCorreiosPost.getNVlDiametro();		//default, 0 pois nao é prisma.
		String StrRetorno = "&StrRetorno=" + shippingCorreiosPost.getStrRetorno();		//default do retorno, xml
		String nIndicaCalculo = "&nIndicaCalculo=" + shippingCorreiosPost.getNIndicaCalculo();		//
		String sDtCalcul = "&sDtCalcul=" + shippingCorreiosPost.getSDtCalcul(); //formato data aaaammdd
		
		ShippingCorreiosGet freteCorreios = new ShippingCorreiosGet();
		
		URL url;
		URLConnection urlConnection;
		
		url = new URL(urlWSCorreios + nCdEmpresa + sDsSenha + sCepOrigem + sCepDestino + nVlPeso + nCdFormato + nVlComprimento + nVlAltura +
				nVlLargura + sCdMaoPropria + nVlValorDeclarado + sCdAvisoRecebimento + nCdServico + nVlDiametro + StrRetorno +  nIndicaCalculo + sDtCalcul);

		urlConnection = url.openConnection();

		BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		String inputLine;
		File fileXML = new File("retornoXML.xml");
		FileWriter f = new FileWriter(fileXML);
		while ((inputLine = reader.readLine()) != null) {
			log.info("inputLine: {}", inputLine);
		    f.write(inputLine);
		}
		f.close();
		reader.close();

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
		DocumentBuilder db = dbf.newDocumentBuilder();  
		Document doc = db.parse(fileXML);
		doc.normalize();  
		log.info("Root element: " + doc.getDocumentElement().getNodeName());  
		NodeList nodeList = doc.getElementsByTagName("Servicos");  
		// nodeList is not iterable, so we are using for loop  
		for (int itr = 0; itr < nodeList.getLength(); itr++)   
		{  
			Node node = nodeList.item(itr);  
			System.out.println("\nNode Name :" + node.getNodeName());  
			if (node.getNodeType() == Node.ELEMENT_NODE)   
			{  
				Element eElement = (Element) node;  
				freteCorreios.setCodigo(eElement.getElementsByTagName("Codigo").item(0).getTextContent());
				freteCorreios.setValor(eElement.getElementsByTagName("Valor").item(0).getTextContent().replace(",", "."));
				freteCorreios.setPrazoEntrega(eElement.getElementsByTagName("PrazoEntrega").item(0).getTextContent());
				freteCorreios.setErro(eElement.getElementsByTagName("Erro").item(0).getTextContent());
			}  
		} 		
		return freteCorreios;
	}
	
	public String convertDate() throws ParseException {
		LocalDate date =  LocalDate.now().plusDays(defaultDaysToSend);
		String currentDate = date.toString().replace("-", "");
		return currentDate;
	}
	
	private ShippingCorreiosPost shippingCorreiosPost(String cep) throws ParseException {
		ShippingCorreiosPost freteCorreiosPost = new ShippingCorreiosPost();
		
		String dataSaidaProduto = convertDate();

		freteCorreiosPost.setNCdEmpresa("");
		freteCorreiosPost.setSDsSenha("");
		freteCorreiosPost.setSCepOrigem(streetCepSource);
		freteCorreiosPost.setSCepDestino(cep);
		freteCorreiosPost.setNVlPeso("1");
		freteCorreiosPost.setNCdFormato("1");
		freteCorreiosPost.setNVlComprimento("20");
		freteCorreiosPost.setNVlAltura("20");
		freteCorreiosPost.setNVlLargura("20");
		freteCorreiosPost.setSCdMaoPropria("n");
		freteCorreiosPost.setNVlValorDeclarado("0");
		freteCorreiosPost.setSCdAvisoRecebimento("n");
		freteCorreiosPost.setNCdServico("40010");
		freteCorreiosPost.setNVlDiametro("0");
		freteCorreiosPost.setStrRetorno("xml");
		//freteCorreiosPost.setSDtCalcul("3");
		freteCorreiosPost.setSDtCalcul(dataSaidaProduto); //20200820
		
		return freteCorreiosPost;
	}
	
	
}
