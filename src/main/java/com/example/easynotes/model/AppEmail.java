package com.example.easynotes.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/** The persistent class for the sl_emails database table. */
@Entity
@Table(name = "app_emails")
public class AppEmail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Long id;

	@Column(name = "modified_on")
	private Timestamp modifiedon;

	@Column(name = "modified_by")
	private Long modifiedby;

	@Column(updatable = false, name = "created_on")
	private Timestamp createdon;

	@Column(updatable = false, name = "created_by")
	private Long createdby;

	@Column(name = "fromEmail")
	private String emailFrom;

	@Column(name = "toEmail")
	private String emailTo;

	@Column(name = "cc")
	private String cc;

	@Column(name = "bcc")
	private String bcc;

	@Column(name = "body")
	private String body;

	@Column(name = "subject")
	private String subject;

	@Column(name = "isAttachment")
	private boolean isAttachment;

	@Column(name = "attachmentFilePath")
	private String attachmentFilePath;

	@Column(name = "templateName")
	private String templateName;

	@Column(name = "sendOn")
	private Date sendOn;

	@Column(name = "isDeleted")
	private boolean isDelete;
	
	@Column(name = "sendStatus")
	private boolean sendStatus;

	/** @return The emailFrom */
	public String getEmailFrom() {
		return emailFrom;
	}

	/**
	 * @param emailFrom
	 *            The emailFrom to set
	 */
	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}

	/** @return The emailTo */
	public String getEmailTo() {
		return emailTo;
	}

	/**
	 * @param emailTo
	 *            The emailTo to set
	 */
	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}

	/** @return The cc */
	public String getCc() {
		return cc;
	}

	/**
	 * @param cc
	 *            The cc to set
	 */
	public void setCc(String cc) {
		this.cc = cc;
	}

	/** @return The bcc */
	public String getBcc() {
		return bcc;
	}

	/**
	 * @param bcc
	 *            The bcc to set
	 */
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	/** @return The body */
	public String getBody() {
		return body;
	}

	/**
	 * @param body
	 *            The body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/** @return The subject */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject
	 *            The subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/** @return The isAttachment */
	public boolean isAttachment() {
		return isAttachment;
	}

	/**
	 * @param isAttachment
	 *            The isAttachment to set
	 */
	public void setAttachment(boolean isAttachment) {
		this.isAttachment = isAttachment;
	}

	/** @return The attachmentFilePath */
	public String getAttachmentFilePath() {
		return attachmentFilePath;
	}

	/**
	 * @param attachmentFilePath
	 *            The attachmentFilePath to set
	 */
	public void setAttachmentFilePath(String attachmentFilePath) {
		this.attachmentFilePath = attachmentFilePath;
	}

	/** @return The templateName */
	public String getTemplateName() {
		return templateName;
	}

	/**
	 * @param templateName
	 *            The templateName to set
	 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	/** @return The sendOn */
	public Date getSendOn() {
		return sendOn;
	}

	/**
	 * @param sendOn
	 *            The sendOn to set
	 */
	public void setSendOn(Date sendOn) {
		this.sendOn = sendOn;
	}

	/** @return The isDelete */
	public boolean isDelete() {
		return isDelete;
	}

	/**
	 * @param isDelete
	 *            The isDelete to set
	 */
	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

	/**
	 * @return the sendStatus
	 */
	public boolean isSendStatus() {
		return sendStatus;
	}

	/**
	 * @param sendStatus the sendStatus to set
	 */
	public void setSendStatus(boolean sendStatus) {
		this.sendStatus = sendStatus;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the modifiedon
	 */
	public Timestamp getModifiedon() {
		return modifiedon;
	}

	/**
	 * @param modifiedon the modifiedon to set
	 */
	public void setModifiedon(Timestamp modifiedon) {
		this.modifiedon = modifiedon;
	}

	/**
	 * @return the modifiedby
	 */
	public Long getModifiedby() {
		return modifiedby;
	}

	/**
	 * @param modifiedby the modifiedby to set
	 */
	public void setModifiedby(Long modifiedby) {
		this.modifiedby = modifiedby;
	}

	/**
	 * @return the createdon
	 */
	public Timestamp getCreatedon() {
		return createdon;
	}

	/**
	 * @param createdon the createdon to set
	 */
	public void setCreatedon(Timestamp createdon) {
		this.createdon = createdon;
	}

	/**
	 * @return the createdby
	 */
	public Long getCreatedby() {
		return createdby;
	}

	/**
	 * @param createdby the createdby to set
	 */
	public void setCreatedby(Long createdby) {
		this.createdby = createdby;
	}

	
}
