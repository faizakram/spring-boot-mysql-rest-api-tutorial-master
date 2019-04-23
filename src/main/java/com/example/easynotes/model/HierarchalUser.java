package com.example.easynotes.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;


/**
 * The persistent class for the hierarchal_user database table.
 * 
 */
@Entity
@Table(name="hierarchal_user")
@NamedQuery(name="HierarchalUser.findAll", query="SELECT h FROM HierarchalUser h")
public class HierarchalUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="user_name")
	private String userName;

	//bi-directional many-to-one association to Hierarchical
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name="hierarchy_id")
	private Hierarchical hierarchical;

	public HierarchalUser() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Hierarchical getHierarchical() {
		return this.hierarchical;
	}

	public void setHierarchical(Hierarchical hierarchical) {
		this.hierarchical = hierarchical;
	}

}