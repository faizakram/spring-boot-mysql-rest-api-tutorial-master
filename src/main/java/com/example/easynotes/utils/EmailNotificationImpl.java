package com.example.easynotes.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.example.easynotes.model.AppEmail;
import com.example.easynotes.repository.EmailRepository;

/** To send the Email Notification */
@Component
public class EmailNotificationImpl {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private VelocityEngine velocityEngine;

	@Autowired
	private Environment systemPropertyReader;

	@Autowired
	private EmailRepository emailDao;

	/**
	 * {@inheritDoc}
	 */
	public Map<String, Object> insertEmailRecordForRest(String userEmail, String fName) {
		String templatePath = systemPropertyReader.getProperty(EmailConstant.REST_TEMPLATE_VM_FILE);
		String subject = EmailConstant.REST_MAIL_SUBJECT;
		Map<String, Object> model = generateEmailContentForRest(userEmail, fName);
		return commonMailSendingUtil(userEmail, templatePath, subject, model);
	}

	/**
	 * commonMailSendingUtil
	 * 
	 * @param user
	 * @param templatePath
	 * @param subject
	 * @param model
	 * @return
	 */
	private Map<String, Object> commonMailSendingUtil(String userEmail, String templatePath, String subject,
			Map<String, Object> model) {
		String body = getContentFromTemplate(model, templatePath);
		String[] template = templatePath.split("/");
		String templateName = template[template.length - 1];
		model.put(EmailConstant.REST_TEMPLATE_VM_FILE, templatePath);
		Long emailRecordId;
		AppEmail emailDetail = setEmailObjectData(userEmail, subject, body, templateName);
		emailRecordId = this.insertEmail(emailDetail);
		emailDetail.setId(emailRecordId);
		model.put(EmailConstant.EMAIL_DETAILS, emailDetail);
		return model;
	}

	/**
	 * insertEmail
	 * 
	 * @param email
	 * @return
	 */
	private Long insertEmail(AppEmail email) {
		return emailDao.save(email).getId();
	}

	/**
	 * setEmailObjectData
	 * 
	 * @param userEmail
	 * @param subject
	 * @param body
	 * @param templateName
	 * @return
	 */
	private AppEmail setEmailObjectData(String userEmail, String subject, String body, String templateName) {
		String from = systemPropertyReader.getProperty(ConfigurationConstant.USERNAME_FROM);
		Date utcDate = null;
		AppEmail emailDetail = new AppEmail();
		emailDetail.setAttachment(false);
		emailDetail.setAttachmentFilePath(null);
		emailDetail.setBcc(null);
		emailDetail.setCc(null);
		emailDetail.setBody(null);
		emailDetail.setEmailFrom(from);
		emailDetail.setEmailTo(userEmail);
		emailDetail.setSendOn(utcDate);
		emailDetail.setSubject(subject);
		emailDetail.setTemplateName(templateName);
		emailDetail.setSendStatus(true);
		emailDetail.setBody(body);
		emailDetail.setCreatedon(DateUtil.getCurrentTimestampInUTC());
		emailDetail.setCreatedby(1L);
		return emailDetail;
	}

	/**
	 * get Content From Template
	 * 
	 * @param model
	 * @param templateFileLocation
	 * @return
	 */
	private String getContentFromTemplate(Map<String, Object> model, String templateFileLocation) {
		try {
			StringBuilder content = new StringBuilder();
			StringWriter writer = new StringWriter();
			VelocityContext context = new VelocityContext(model);
			boolean isSuccess = velocityEngine.mergeTemplate(templateFileLocation, ConfigurationConstant.UTF_8, context,
					writer);
			if (isSuccess) {
				content.append(writer.toString());
				return content.toString();
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	/**
	 * Get
	 * 
	 * @param user
	 * @return
	 */
	private Map<String, Object> generateEmailContentForRest(String userEmail, String fName) {
		Map<String, Object> model = new HashMap<>();
		model.put(EmailConstant.FIRST_NAME, fName);
		model.put(EmailConstant.CREDENTIAL, userEmail);
		return model;
	}

	/**
	 * {@inheritDoc}
	 */
	@Async
	public void sendEmailNotification(Map<String, Object> emailMap, String userEmail, boolean isTemplateRequired,
			MultipartFile attatchment, String ccEmailIds) {
		AppEmail emailDetail = (AppEmail) emailMap.get(EmailConstant.EMAIL_DETAILS);
		String[] emailTo = userEmail.split(",");
		try {
			MimeMessageHelper mimeMessageHelper = this.createEmailToSend(emailTo, true, isTemplateRequired, emailMap,
					attatchment);
			this.sendEmail(mimeMessageHelper);
			emailDetail.setSendOn(DateUtil.getCurrentTimestampInUTC());
		} catch (Exception e) {
		}

	}

	/**
	 * Create Email to Send
	 * 
	 * @param to
	 * @param textType
	 * @param isTemplateProvided
	 * @param model
	 * @param attachment
	 * @return
	 * @throws MessagingException
	 * @throws IOException
	 */
	private MimeMessageHelper createEmailToSend(String[] to, boolean textType, boolean isTemplateProvided,
			Map<String, Object> model, MultipartFile attachment) throws MessagingException, IOException {
		AppEmail emailDetail = (AppEmail) model.get(EmailConstant.EMAIL_DETAILS);
		String templateFileLocation = (String) model.get(EmailConstant.REST_TEMPLATE_VM_FILE);
		String emailBody = emailDetail.getBody();
		String from = systemPropertyReader.getProperty(ConfigurationConstant.USERNAME_FROM);
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		if (StringUtils.isNotEmpty(emailDetail.getSubject()) && StringUtils.isNotEmpty(from)) {
			mimeMessageHelper.setSubject(emailDetail.getSubject());
			mimeMessageHelper.setFrom(from);
			mimeMessageHelper.setTo(to);
			if (attachment != null) {
				mimeMessageHelper.addAttachment(attachment.getOriginalFilename(),
						new ByteArrayResource(attachment.getBytes()));
			}

			if (isTemplateProvided) {
				emailBody = getContentFromTemplate(model, templateFileLocation);
			}
			if (StringUtils.isNotEmpty(emailBody)) {
				mimeMessageHelper.setText(emailBody, textType);
			}
		}
		return mimeMessageHelper;
	}

	/**
	 * Send Email
	 * 
	 * @param messageHelper
	 */
	private void sendEmail(MimeMessageHelper messageHelper) {
		mailSender.send(messageHelper.getMimeMessage());
	}


}
