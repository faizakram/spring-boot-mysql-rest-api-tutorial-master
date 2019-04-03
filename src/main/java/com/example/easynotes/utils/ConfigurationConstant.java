package com.example.easynotes.utils;

public class ConfigurationConstant {
	private ConfigurationConstant() {
	}

	// velocity configurations
	public static final String RESOURCE_LOADER = "resource.loader";
	public static final String RESOURCE_LOADER_CLASS = "file.resource.loader.class";
	public static final String RESOURCE_LOADER_VALUE = "file";
	public static final String RESOURCE_LOADER_CLASS_VALUE = "org.apache.velocity.runtime.resource.loader.FileResourceLoader";
	public static final String RESOURCE_LOADER_FILE_PATH = "file.resource.loader.path";
	public static final String RESOURCE_LOADER_FILE_PATH_VALUE = "test.vm.template.path";
	//
	public static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
	public static final String MAIL_SMTP_START_TLS_ENABLE = "mail.smtp.starttls.enable";
	public static final String EMAIL_BODY_TYPE = "text/html";
	public static final String TRANSPORT_TYPE = "smtp";
	public static final String USERNAME = "test.mail.smtp.host.user.name";
	public static final String PASS = "test.mail.smtp.host.user.credential";
	public static final String MAIL_SMTP_PORT_PROPERTY = "test.mail.smtp.host.port";
	public static final String MAIL_SMTP_START_TLS_ENABLE_PROPERTY = "test.mail.smtp.start.tls.enable";
	public static final String MAIL_SMTP_AUTH_PROPERTY = "test.mail.smtp.auth";
	public static final String HOST = "test.mail.smtp.host.name";
	public static final String USERNAME_FROM = "test.mail.smtp.user.from";
	public static final String MAIL_TRANSPORT_PROTOCOL_PROPERTY = "test.mail.smtp.transport.protocol";
	public static final String MAIL_DEBUG_TRUE_PROPERTY = "test.mail.smtp.debug.true";
	public static final String MAIL_TRANSPORT_PROTOCOL = "mail.transport.protocol";
	public static final String MAIL_DEBUG = "mail.debug";
	// Date Time
	public static final String DAYS = "DAYS";
	public static final String MONTHS = "MONTHS";
	public static final String YEARS = "YEARS";
	public static final String SIMPLE_DATE_FORMAT_VALUE = "yyyy-MM-dd";
	public static final String UTC_TIME_ZONE = "UTC";
	public static final String SIMPLE_DATE_TIME_FORMAT_VALUE = "yyyy-MM-dd HH:mm:ss";

	//
	public static final String UTF_8 = "UTF-8";
}
