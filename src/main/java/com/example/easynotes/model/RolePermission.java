package com.example.easynotes.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role_permission")
public class RolePermission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String role;
	private Boolean create;
	private Boolean read;
	private Boolean update;
	private Boolean delete;
	@OneToMany(mappedBy = "rolePermission", cascade = CascadeType.ALL)
	private List<User> user;

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
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the create
	 */
	public Boolean getCreate() {
		return create;
	}

	/**
	 * @param create the create to set
	 */
	public void setCreate(Boolean create) {
		this.create = create;
	}

	/**
	 * @return the read
	 */
	public Boolean getRead() {
		return read;
	}

	/**
	 * @param read the read to set
	 */
	public void setRead(Boolean read) {
		this.read = read;
	}

	/**
	 * @return the update
	 */
	public Boolean getUpdate() {
		return update;
	}

	/**
	 * @param update the update to set
	 */
	public void setUpdate(Boolean update) {
		this.update = update;
	}

	/**
	 * @return the delete
	 */
	public Boolean getDelete() {
		return delete;
	}

	/**
	 * @param delete the delete to set
	 */
	public void setDelete(Boolean delete) {
		this.delete = delete;
	}

	/**
	 * @return the user
	 */
	public List<User> getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(List<User> user) {
		this.user = user;
	}

}
