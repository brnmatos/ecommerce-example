package br.com.dpaulla.component;

import java.io.IOException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import org.springframework.http.ResponseEntity;
import org.xml.sax.SAXException;
import br.com.dpaulla.model.OrdersBuy;
import br.com.dpaulla.model.order;

public interface PagSeguroAPIClient {
	ResponseEntity<String> getSession(String email, String token);
	ResponseEntity<String> checkout(order transacao, List<OrdersBuy> ordersByList, String boleOrCard) throws TransformerConfigurationException, SAXException, IOException, ParserConfigurationException;
//	ResponseEntity<String> checkoutCartaoCredito(Transacao transacao, List<Compra> comprasList);
}
