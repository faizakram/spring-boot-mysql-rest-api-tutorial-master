package com.example.easynotes.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * The persistent class for the hierarchical database table.
 * 
 */
@Entity
@NamedQuery(name = "Hierarchical.findAll", query = "SELECT h FROM Hierarchical h")
public class Hierarchical implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	// bi-directional many-to-one association to HierarchalUser
	@JsonBackReference
	@OneToMany(mappedBy = "hierarchical" ,cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<HierarchalUser> hierarchalUsers = new ArrayList<>();

	// bi-directional many-to-one association to Hierarchical
	@JsonManagedReference
	@ManyToOne
	@JoinColumn(name = "parent_id")
	private Hierarchical hierarchical;

	// bi-directional many-to-one association to Hierarchical
	@JsonBackReference
	@OneToMany(mappedBy = "hierarchical", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Hierarchical> hierarchicals = new HashSet<>();

	public Hierarchical() {
	}

	public Hierarchical(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<HierarchalUser> getHierarchalUsers() {
		return this.hierarchalUsers;
	}

	public void setHierarchalUsers(List<HierarchalUser> hierarchalUsers) {
		this.hierarchalUsers = hierarchalUsers;
	}

	public HierarchalUser addHierarchalUser(HierarchalUser hierarchalUser) {
		getHierarchalUsers().add(hierarchalUser);
		hierarchalUser.setHierarchical(this);

		return hierarchalUser;
	}

	public HierarchalUser removeHierarchalUser(HierarchalUser hierarchalUser) {
		getHierarchalUsers().remove(hierarchalUser);
		hierarchalUser.setHierarchical(null);

		return hierarchalUser;
	}

	public Hierarchical getHierarchical() {
		return this.hierarchical;
	}

	public void setHierarchical(Hierarchical hierarchical) {
		this.hierarchical = hierarchical;
	}

	public Set<Hierarchical> getHierarchicals() {
		return this.hierarchicals;
	}

	public void setHierarchicals(Set<Hierarchical> hierarchicals) {
		this.hierarchicals = hierarchicals;
	}

	public Hierarchical addHierarchical(Hierarchical hierarchical) {
		getHierarchicals().add(hierarchical);
		hierarchical.setHierarchical(this);

		return hierarchical;
	}

	public Hierarchical removeHierarchical(Hierarchical hierarchical) {
		getHierarchicals().remove(hierarchical);
		hierarchical.setHierarchical(null);

		return hierarchical;
	}

}