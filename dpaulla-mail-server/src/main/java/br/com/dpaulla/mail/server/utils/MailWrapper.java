package br.com.dpaulla.mail.server.utils;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Builder
@EqualsAndHashCode
@AllArgsConstructor
@RequiredArgsConstructor
public @Data class MailWrapper implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final TemplateHelper templateHelper = new TemplateHelper();

	private String from;
	private String to;
	private String subject;
	private String bcc;
	private String template;
	private String pathFile;
	private Map<String, Object> aditional;

	public String getMail() throws Exception {
		final Map<String, Object> context = new HashMap<>();
		context.put("StringUtils", org.apache.commons.lang.StringUtils.class);
		context.put("from", from);
		context.put("to", to);
		context.put("subject", subject);
		context.put("createdAt", LocalTime.now());
		context.put("attachment", pathFile);
		aditional.forEach((k,v) -> context.put(k, v));
		templateHelper.prepare(template, context);
		return templateHelper.render();
	}
}
