package br.com.dpaulla.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.apache.velocity.runtime.resource.loader.StringResourceLoader;
import org.apache.velocity.runtime.resource.util.StringResourceRepositoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TemplateHelper {
	
	private static Logger log = LoggerFactory.getLogger(TemplateHelper.class);
	
	private VelocityEngine ve;
	private VelocityContext context;
	private Template template;
	private boolean stringResourceLoader;

	public TemplateHelper() {
		this(false);
	}

	public TemplateHelper(boolean stringResourceLoader) {
		this.stringResourceLoader = stringResourceLoader;
		this.ve = new VelocityEngine();
		this.context = new VelocityContext();

		if (this.stringResourceLoader) {
			this.ve = newStringEngine("application.repo", true);
		} else {
			ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
			ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		}
	}

	public void ignite() {
		this.ve.init();
	}

	public void ignite(Properties properties) {
		this.ve.init(properties);
	}

	public void prepare(String template, Map<String, Object> context) throws IOException {
		final String templatePath = "templates/" + template;

		try (InputStream input = this.getClass().getClassLoader().getResourceAsStream(templatePath)) {

			if (input == null) {
				throw new IOException("Template file doesn't exist");
			}

			this.template = ve.getTemplate(templatePath, "UTF-8");
			context.forEach((k, v) -> {
				this.context.put(k, v);
			});
		}
	}

	public String render() {
		StringWriter writer = new StringWriter();
		this.template.merge(this.context, writer);
		return writer.toString();
	}

	public String render(String inputMessage, Map<String, Object> context) {
		ApplicationRepo repo = new ApplicationRepo();
		repo.put("messageTemplate", inputMessage);
		StringResourceLoader.setRepository("application.repo", repo);
		log.info("Repo -> {}", repo);

		this.template = ve.getTemplate("messageTemplate");

		context.forEach((k, v) -> {
			this.context.put(k, v);
		});

		StringWriter writer = new StringWriter();
		this.template.merge(this.context, writer);
		String out =  writer.toString();
		log.debug("String -> {}", out);

		return out;
	}

	private VelocityEngine newStringEngine(String repoName, boolean isStatic) {
		VelocityEngine engine = new VelocityEngine();
		engine.setProperty(Velocity.RESOURCE_LOADER, "string");
		engine.addProperty("string.resource.loader.class", StringResourceLoader.class.getName());
		if (repoName != null) {
			engine.addProperty("string.resource.loader.repository.name", repoName);
		}
		if (!isStatic) {
			engine.addProperty("string.resource.loader.repository.static", "false");
		}
		engine.addProperty("string.resource.loader.modificationCheckInterval", "1");
		return engine;
	}

	public static class ApplicationRepo extends StringResourceRepositoryImpl {
		public void put(String name, String template) {
			putStringResource(name, template);
		}
	}
	
}
