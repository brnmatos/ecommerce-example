package br.com.crb.mercury.mail.server.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import br.com.dpaulla.model.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MailMessageUtil {
	
	//CONTATOS
	@Value("${app.mail.contact.address}")
	private String dpaullaContato;
	
	@Value("${app.mail.platform.contact.from}")
	private String from;
	
	@Value("${app.mail.cadastro.subject}")
	private String cadastroMailSubject;
	
	@Value("${app.mail.cadastro.template}")
	private String cadastroEmailTemplate;
	
	private String dateTime;
	private static String codSat;
	private static String ra;
	private static String km;
	private static String rodovia;
	
	public MailWrapper buildCadastroEmailMessage(User user) {
		log.info("Building E-mail message... {}", cadastroMailSubject);
		MailWrapper mailWrapper = cadastroEmailWrapper(user);
		Map<String, Object> aditional = new HashMap<>();
		List<String> listOfLinks = new ArrayList<String>();
		//aditional.put("VARIAVEL NO TEMPLATE", "VALOR QUE DEVE ASSUMIR");
		
		aditional.put("data", dateTime);
		aditional.put("email", dpaullaContato);
		aditional.put("sat", codSat);
		aditional.put("km", km);
		aditional.put("rodovia", rodovia);
		aditional.put("listdevices", listOfLinks);
		aditional.put("ra", ra);
		log.info("Aditional parameters message -> {}", aditional);
		String nameSat = "SAT" + codSat;
		mailWrapper.setSubject(cadastroMailSubject);
		mailWrapper.setTemplate(cadastroEmailTemplate);
		mailWrapper.setAditional(aditional);
		
		log.info("Add parameters. \n\tfrom: {} \n\temail: {} \n\tbcc: {} \n\tsubject: {} \n\ttemplate: {} \n\taditional: {} \n\tmailWrapper: {}", 
				from, cadastroMailSubject, cadastroEmailTemplate, aditional, mailWrapper);
		return mailWrapper;
	}
	
	/*
	 * public MailWrapper buildAntennaMessage(FileOutputStream fos) {
	 * log.info("Building E-mail message... {}", antennaMailSubject); MailWrapper
	 * mailWrapper = antennaWrapper(fos); Map<String, Object> aditional = new
	 * HashMap<>();
	 * 
	 * //aditional.put("VARIAVEL NO TEMPLATE", "VALOR QUE DEVE ASSUMIR");
	 * aditional.put("email", crbContact);
	 * log.info("Aditional parameters message -> {}", aditional);
	 * mailWrapper.setSubject(antennaMailSubject);
	 * mailWrapper.setTemplate(antennaTemplate);
	 * mailWrapper.setAditional(aditional);
	 * 
	 * log.
	 * info("Add parameters. \n\tfrom: {} \n\temail: {} \n\tbcc: {} \n\tsubject: {} \n\ttemplate: {} \n\taditional: {} \n\tmailWrapper: {}"
	 * , from, antennaMailSubject, antennaTemplate, aditional, mailWrapper);
	 * 
	 * return mailWrapper; }
	 */
	
	/*
	 * public MailWrapper buildAlertsHourMessage(List<AlertHourSatsModel>
	 * listOfLinksAndSats) { log.info("Building E-mail message... {}",
	 * alertsHourMailSubject); MailWrapper mailWrapper =
	 * alertsHourWrapper(listOfLinksAndSats); Map<String, Object> aditional = new
	 * HashMap<>(); List<String> listOfLinks = new ArrayList<String>();
	 * 
	 * listOfLinksAndSats.stream().forEach(action -> { int intCodSat =
	 * action.getNUM_ESTACAO(); codSat = String.valueOf(intCodSat); ra =
	 * action.getNOME_ESTACAO(); String link = "SAT: " + action.getNUM_ESTACAO() +
	 * " - " + action.getNOME_LACO(); listOfLinks.add(link); });
	 * //aditional.put("VARIAVEL NO TEMPLATE", "VALOR QUE DEVE ASSUMIR");
	 * aditional.put("email", crbContact); aditional.put("sat", codSat);
	 * aditional.put("listdevices", listOfLinks);
	 * log.info("Aditional parameters message -> {}", aditional);
	 * mailWrapper.setSubject(alertsHourMailSubject);
	 * mailWrapper.setTemplate(alertsHourTemplate);
	 * mailWrapper.setAditional(aditional);
	 * 
	 * return mailWrapper; }
	 */
	
	private MailWrapper cadastroEmailWrapper(User user) {
		log.info("default Wrapper...{}", user.getUsername());
		MailWrapper mailWrapper = new MailWrapper();
		mailWrapper.setFrom(from);
		//mailWrapper.setTo(artespContact);
		//mailWrapper.setBcc(crbContact);
		return mailWrapper;
	}
	
	/*
	 * private MailWrapper antennaWrapper(FileOutputStream fos) {
	 * log.info("default Wrapper...{}", fos); MailWrapper mailWrapper = new
	 * MailWrapper(); mailWrapper.setFrom(from); mailWrapper.setTo(artespContact);
	 * mailWrapper.setBcc(crbContact); return mailWrapper; }
	 * 
	 * private MailWrapper alertsHourWrapper(List<AlertHourSatsModel>
	 * listOfLinksAndSats) { log.info("default Wrapper...{}", listOfLinksAndSats);
	 * MailWrapper mailWrapper = new MailWrapper(); mailWrapper.setFrom(from);
	 * mailWrapper.setTo(crbContactManutencao);
	 * mailWrapper.setBcc("ti@rotadasbandeiras.com.br"); log.info("Sent!"); return
	 * mailWrapper; }
	 */

}
