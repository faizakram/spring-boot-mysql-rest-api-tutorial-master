package com.example.easynotes.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the "CFG_Application" database table.
 * 
 */
@Entity
@Table(name = "\"CFG_Application\"")
@NamedQuery(name = "CFGApplication.findAll", query = "SELECT c FROM CFGApplication c")
public class CFGApplication implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer applconfigid;

	private String applicationid;

	private String configname;

	private String configvalue;

	private Boolean isactive;

	public Integer getApplconfigid() {
		return this.applconfigid;
	}

	public void setApplconfigid(Integer applconfigid) {
		this.applconfigid = applconfigid;
	}

	public String getApplicationid() {
		return this.applicationid;
	}

	public void setApplicationid(String applicationid) {
		this.applicationid = applicationid;
	}

	public String getConfigname() {
		return this.configname;
	}

	public void setConfigname(String configname) {
		this.configname = configname;
	}

	public String getConfigvalue() {
		return this.configvalue;
	}

	public void setConfigvalue(String configvalue) {
		this.configvalue = configvalue;
	}

	public Boolean getIsactive() {
		return this.isactive;
	}

	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}

}