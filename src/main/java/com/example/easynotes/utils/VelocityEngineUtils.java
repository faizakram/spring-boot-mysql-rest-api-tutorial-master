package com.example.easynotes.utils;

import java.io.StringWriter;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VelocityEngineUtils {

	@Autowired
	private VelocityEngine velocityEngine;

	
	public String getContentFromTemplate(Map<String, Object> model, String templateFileLocation) {
		StringBuilder content = new StringBuilder();
		try (StringWriter writer = new StringWriter()) {
			VelocityContext context = new VelocityContext(model);
			boolean isSuccess = velocityEngine.mergeTemplate(templateFileLocation, "UTF-8", context, writer);
			if (isSuccess) {
				content.append(writer.toString());
				return content.toString();
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}
}
