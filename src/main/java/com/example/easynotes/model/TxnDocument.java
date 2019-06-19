package com.example.easynotes.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the txn_document database table.
 * 
 */
@Entity
@Table(name="txn_document")
@NamedQuery(name="TxnDocument.findAll", query="SELECT t FROM TxnDocument t")
public class TxnDocument implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer docreferid;

	private String applicationid;

	private String archivelocation;

	private String buid;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createddatetime;

	private String createduser;

	@Temporal(TemporalType.TIMESTAMP)
	private Date deleteddatetime;

	private String deleteduser;

	private String docapplicationsource;

	private Integer doccategoryid;

	private String docname;

	private String docownerid;

	private Integer docownertypeid;

	private String docphysicalfilename;

	private String docsavefilename;

	private String docsavelocation;

	private Integer docstoragemapid;

	private Integer doctypeid;

	private String docuniquerefno;

	private Integer docversionno;

	private Boolean isactive;

	private Boolean isarchived;

	private Boolean iscopytopublic;

	private Boolean isdeleted;

	private Boolean islatestversion;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastupdatedatetime;

	private String lastupdateuser;

	@Temporal(TemporalType.TIMESTAMP)
	private Date publicexpirydatetime;

	private String publiclocation;

	private String sbuid;

	public TxnDocument() {
	}

	public Integer getDocreferid() {
		return this.docreferid;
	}

	public void setDocreferid(Integer docreferid) {
		this.docreferid = docreferid;
	}

	public String getApplicationid() {
		return this.applicationid;
	}

	public void setApplicationid(String applicationid) {
		this.applicationid = applicationid;
	}

	public String getArchivelocation() {
		return this.archivelocation;
	}

	public void setArchivelocation(String archivelocation) {
		this.archivelocation = archivelocation;
	}

	public String getBuid() {
		return this.buid;
	}

	public void setBuid(String buid) {
		this.buid = buid;
	}

	public Date getCreateddatetime() {
		return this.createddatetime;
	}

	public void setCreateddatetime(Date createddatetime) {
		this.createddatetime = createddatetime;
	}

	public String getCreateduser() {
		return this.createduser;
	}

	public void setCreateduser(String createduser) {
		this.createduser = createduser;
	}

	public Date getDeleteddatetime() {
		return this.deleteddatetime;
	}

	public void setDeleteddatetime(Date deleteddatetime) {
		this.deleteddatetime = deleteddatetime;
	}

	public String getDeleteduser() {
		return this.deleteduser;
	}

	public void setDeleteduser(String deleteduser) {
		this.deleteduser = deleteduser;
	}

	public String getDocapplicationsource() {
		return this.docapplicationsource;
	}

	public void setDocapplicationsource(String docapplicationsource) {
		this.docapplicationsource = docapplicationsource;
	}

	public Integer getDoccategoryid() {
		return this.doccategoryid;
	}

	public void setDoccategoryid(Integer doccategoryid) {
		this.doccategoryid = doccategoryid;
	}

	public String getDocname() {
		return this.docname;
	}

	public void setDocname(String docname) {
		this.docname = docname;
	}

	public String getDocownerid() {
		return this.docownerid;
	}

	public void setDocownerid(String docownerid) {
		this.docownerid = docownerid;
	}

	public Integer getDocownertypeid() {
		return this.docownertypeid;
	}

	public void setDocownertypeid(Integer docownertypeid) {
		this.docownertypeid = docownertypeid;
	}

	public String getDocphysicalfilename() {
		return this.docphysicalfilename;
	}

	public void setDocphysicalfilename(String docphysicalfilename) {
		this.docphysicalfilename = docphysicalfilename;
	}

	public String getDocsavefilename() {
		return this.docsavefilename;
	}

	public void setDocsavefilename(String docsavefilename) {
		this.docsavefilename = docsavefilename;
	}

	public String getDocsavelocation() {
		return this.docsavelocation;
	}

	public void setDocsavelocation(String docsavelocation) {
		this.docsavelocation = docsavelocation;
	}

	public Integer getDocstoragemapid() {
		return this.docstoragemapid;
	}

	public void setDocstoragemapid(Integer docstoragemapid) {
		this.docstoragemapid = docstoragemapid;
	}

	public Integer getDoctypeid() {
		return this.doctypeid;
	}

	public void setDoctypeid(Integer doctypeid) {
		this.doctypeid = doctypeid;
	}

	public String getDocuniquerefno() {
		return this.docuniquerefno;
	}

	public void setDocuniquerefno(String docuniquerefno) {
		this.docuniquerefno = docuniquerefno;
	}

	public Integer getDocversionno() {
		return this.docversionno;
	}

	public void setDocversionno(Integer docversionno) {
		this.docversionno = docversionno;
	}

	public Boolean getIsactive() {
		return this.isactive;
	}

	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}

	public Boolean getIsarchived() {
		return this.isarchived;
	}

	public void setIsarchived(Boolean isarchived) {
		this.isarchived = isarchived;
	}

	public Boolean getIscopytopublic() {
		return this.iscopytopublic;
	}

	public void setIscopytopublic(Boolean iscopytopublic) {
		this.iscopytopublic = iscopytopublic;
	}

	public Boolean getIsdeleted() {
		return this.isdeleted;
	}

	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	public Boolean getIslatestversion() {
		return this.islatestversion;
	}

	public void setIslatestversion(Boolean islatestversion) {
		this.islatestversion = islatestversion;
	}

	public Date getLastupdatedatetime() {
		return this.lastupdatedatetime;
	}

	public void setLastupdatedatetime(Date lastupdatedatetime) {
		this.lastupdatedatetime = lastupdatedatetime;
	}

	public String getLastupdateuser() {
		return this.lastupdateuser;
	}

	public void setLastupdateuser(String lastupdateuser) {
		this.lastupdateuser = lastupdateuser;
	}

	public Date getPublicexpirydatetime() {
		return this.publicexpirydatetime;
	}

	public void setPublicexpirydatetime(Date publicexpirydatetime) {
		this.publicexpirydatetime = publicexpirydatetime;
	}

	public String getPubliclocation() {
		return this.publiclocation;
	}

	public void setPubliclocation(String publiclocation) {
		this.publiclocation = publiclocation;
	}

	public String getSbuid() {
		return this.sbuid;
	}

	public void setSbuid(String sbuid) {
		this.sbuid = sbuid;
	}
	
	@Override
	public String toString() {
		return "TxnDocument [docreferid=" + docreferid + ", applicationid=" + applicationid + ", archivelocation="
				+ archivelocation + ", buid=" + buid + ", createddatetime=" + createddatetime + ", createduser="
				+ createduser + ", deleteddatetime=" + deleteddatetime + ", deleteduser=" + deleteduser
				+ ", docapplicationsource=" + docapplicationsource + ", doccategoryid=" + doccategoryid + ", docname="
				+ docname + ", docownerid=" + docownerid + ", docownertypeid=" + docownertypeid
				+ ", docphysicalfilename=" + docphysicalfilename + ", docsavefilename=" + docsavefilename
				+ ", docsavelocation=" + docsavelocation + ", docstoragemapid=" + docstoragemapid + ", doctypeid="
				+ doctypeid + ", docuniquerefno=" + docuniquerefno + ", docversionno=" + docversionno + ", isactive="
				+ isactive + ", isarchived=" + isarchived + ", iscopytopublic=" + iscopytopublic + ", isdeleted="
				+ isdeleted + ", islatestversion=" + islatestversion + ", lastupdatedatetime=" + lastupdatedatetime
				+ ", lastupdateuser=" + lastupdateuser + ", publicexpirydatetime=" + publicexpirydatetime
				+ ", publiclocation=" + publiclocation + ", sbuid=" + sbuid + "]";
	}

	public TxnDocument(Integer docreferid, String applicationid, String archivelocation, String buid,
			Date createddatetime, String createduser, Date deleteddatetime, String deleteduser,
			String docapplicationsource, Integer doccategoryid, String docname, String docownerid,
			Integer docownertypeid, String docphysicalfilename, String docsavefilename, String docsavelocation,
			Integer docstoragemapid, Integer doctypeid, String docuniquerefno, Integer docversionno, Boolean isactive,
			Boolean isarchived, Boolean iscopytopublic, Boolean isdeleted, Boolean islatestversion,
			Date lastupdatedatetime, String lastupdateuser, Date publicexpirydatetime, String publiclocation,
			String sbuid) {
	
		this.docreferid = docreferid;
		this.applicationid = applicationid;
		this.archivelocation = archivelocation;
		this.buid = buid;
		this.createddatetime = createddatetime;
		this.createduser = createduser;
		this.deleteddatetime = deleteddatetime;
		this.deleteduser = deleteduser;
		this.docapplicationsource = docapplicationsource;
		this.doccategoryid = doccategoryid;
		this.docname = docname;
		this.docownerid = docownerid;
		this.docownertypeid = docownertypeid;
		this.docphysicalfilename = docphysicalfilename;
		this.docsavefilename = docsavefilename;
		this.docsavelocation = docsavelocation;
		this.docstoragemapid = docstoragemapid;
		this.doctypeid = doctypeid;
		this.docuniquerefno = docuniquerefno;
		this.docversionno = docversionno;
		this.isactive = isactive;
		this.isarchived = isarchived;
		this.iscopytopublic = iscopytopublic;
		this.isdeleted = isdeleted;
		this.islatestversion = islatestversion;
		this.lastupdatedatetime = lastupdatedatetime;
		this.lastupdateuser = lastupdateuser;
		this.publicexpirydatetime = publicexpirydatetime;
		this.publiclocation = publiclocation;
		this.sbuid = sbuid;
	}
}