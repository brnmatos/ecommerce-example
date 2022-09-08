/*
 * package br.com.crb.mercury.mail.server.controller.impl;
 * 
 * import java.util.List; import java.util.concurrent.ExecutorService;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.MediaType; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestMethod; import
 * org.springframework.web.bind.annotation.RestController; import
 * org.springframework.web.context.request.async.DeferredResult;
 * 
 * import br.com.crb.mercury.api.model.CountSatsModel; import
 * br.com.crb.mercury.api.model.mail.AlertsWrapper; import
 * br.com.crb.mercury.api.model.mail.MailResponse; import
 * br.com.crb.mercury.mail.server.controller.MainController; import
 * br.com.crb.mercury.mail.server.service.MailgunMailService; import
 * br.com.crb.mercury.mail.server.utils.MailMessageUtil; import
 * br.com.crb.mercury.mail.server.utils.MailWrapper; import
 * lombok.extern.slf4j.Slf4j;
 * 
 * @Slf4j
 * 
 * @RestController
 * 
 * @RequestMapping(value = "/mail/alerts/sats") public class cadastroController
 * extends MainController<AlertsWrapper>{
 * 
 * private ExecutorService executor; private MailMessageUtil mailMessage;
 * 
 * @Autowired public cadastroController(ExecutorService executor,
 * MailgunMailService mailgunMailService, MailMessageUtil mailMessage) {
 * super(mailgunMailService); this.executor = executor; this.mailMessage =
 * mailMessage; }
 * 
 * @RequestMapping(method = RequestMethod.POST, consumes =
 * MediaType.APPLICATION_JSON_VALUE, produces =
 * MediaType.APPLICATION_JSON_VALUE) public
 * DeferredResult<ResponseEntity<MailResponse>> alerts(@RequestBody
 * AlertsWrapper alerts){ DeferredResult<ResponseEntity<MailResponse>> response
 * = new DeferredResult<ResponseEntity<MailResponse>>();
 * log.info("Received request. User -> {}", alerts);
 * executor.submit(sendMail(response, alerts)); return response; }
 * 
 * @Override protected String okMessage(AlertsWrapper request) { return String.
 * format("Email enviado com sucesso para o email: crbsistemas@rotadasbandeiras.com.br"
 * ); }
 * 
 * @Override protected String errorMessage(AlertsWrapper request) { return
 * String.
 * format("Erro ao tentar enviar email para o email: crbsistemas@rotadasbandeiras.com.br "
 * ); }
 * 
 * @Override protected MailWrapper buildMessage(AlertsWrapper object) {
 * List<CountSatsModel> equipamentos = object.getEquipamentos(); return
 * mailMessage.buildAlertsMessage(equipamentos);
 * 
 * }
 * 
 * }
 */