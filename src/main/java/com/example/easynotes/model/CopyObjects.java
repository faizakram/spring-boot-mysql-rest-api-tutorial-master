package com.example.easynotes.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "copy_object")
public class CopyObjects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name ="file_name")
    private String fileName;
    @Column(name ="file_size")
    private Long fileSize;
    
    @Column(name ="valid_min")
    private Long validMin;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name ="expiration_time")
    private Date expirationTime;

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
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the fileSize
	 */
	public Long getFileSize() {
		return fileSize;
	}

	/**
	 * @param fileSize the fileSize to set
	 */
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	/**

	/**
	 * @return the validMin
	 */
	public Long getValidMin() {
		return validMin;
	}

	/**
	 * @param validMin the validMin to set
	 */
	public void setValidMin(Long validMin) {
		this.validMin = validMin;
	}

	/**
	 * @return the expirationTime
	 */
	public Date getExpirationTime() {
		return expirationTime;
	}

	/**
	 * @param expirationTime the expirationTime to set
	 */
	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CopyObjects [id=");
		builder.append(id);
		builder.append(", fileName=");
		builder.append(fileName);
		builder.append(", fileSize=");
		builder.append(fileSize);
		builder.append(", validMin=");
		builder.append(validMin);
		builder.append(", expirationTime=");
		builder.append(expirationTime);
		builder.append("]");
		return builder.toString();
	}


   

}
