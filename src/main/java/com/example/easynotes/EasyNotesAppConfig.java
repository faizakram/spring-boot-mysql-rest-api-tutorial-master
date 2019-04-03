package com.example.easynotes;

import java.util.Properties;
import java.util.concurrent.Executor;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.easynotes.utils.ConfigurationConstant;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableScheduling
@EnableSwagger2
@EnableAsync
public class EasyNotesAppConfig implements WebMvcConfigurer {
	private Environment propertyReader;

	@Autowired
	public void setPropertyReader(Environment propertyReader) {
		this.propertyReader = propertyReader;
	}

	@Bean
	public VelocityEngine getVelocityEngine() {
		Properties props = new Properties();
		props.put(ConfigurationConstant.RESOURCE_LOADER, ConfigurationConstant.RESOURCE_LOADER_VALUE);
		props.put(ConfigurationConstant.RESOURCE_LOADER_CLASS, ConfigurationConstant.RESOURCE_LOADER_CLASS_VALUE);
		props.put(ConfigurationConstant.RESOURCE_LOADER_FILE_PATH, propertyReader.getProperty(ConfigurationConstant.RESOURCE_LOADER_FILE_PATH_VALUE));
		return new VelocityEngine(props);
	}

	@Bean
	public JavaMailSender getMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost(propertyReader.getProperty(ConfigurationConstant.HOST));
		mailSender.setPort(Integer.parseInt(propertyReader.getProperty(ConfigurationConstant.MAIL_SMTP_PORT_PROPERTY)));
		mailSender.setUsername(propertyReader.getProperty(ConfigurationConstant.USERNAME));
		mailSender.setPassword(propertyReader.getProperty(ConfigurationConstant.PASS));
		Properties javaMailProperties = new Properties();
		javaMailProperties.put(ConfigurationConstant.MAIL_SMTP_START_TLS_ENABLE,
				propertyReader.getProperty(ConfigurationConstant.MAIL_SMTP_START_TLS_ENABLE_PROPERTY));
		javaMailProperties.put(ConfigurationConstant.MAIL_SMTP_AUTH,
				propertyReader.getProperty(ConfigurationConstant.MAIL_SMTP_AUTH_PROPERTY));
		javaMailProperties.put(ConfigurationConstant.MAIL_TRANSPORT_PROTOCOL,
				propertyReader.getProperty(ConfigurationConstant.MAIL_TRANSPORT_PROTOCOL_PROPERTY));
		javaMailProperties.put(ConfigurationConstant.MAIL_DEBUG,
				propertyReader.getProperty(ConfigurationConstant.MAIL_DEBUG_TRUE_PROPERTY));
		mailSender.setJavaMailProperties(javaMailProperties);
		return mailSender;
	}

	// Swagger Implementation
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
	}

	@Bean(name = "asyncExecutor")
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3);
        executor.setMaxPoolSize(100);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("AsynchThread");
        executor.initialize();
        return executor;
    }
}