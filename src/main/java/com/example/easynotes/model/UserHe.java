package com.example.easynotes.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="UserHe.findAll", query="SELECT u FROM UserHe u")
public class UserHe implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private Integer id;

	@Column(nullable=false, length=50)
	private String designation;

	@Column(nullable=false, length=50)
	private String img;

	@Column(nullable=false, length=50)
	private String name;

	//bi-directional many-to-one association to User
	@JsonManagedReference
	@ManyToOne
	@JsonProperty("subordinates")
	@JoinColumn(name="subordinates")
	private UserHe user;

	//bi-directional many-to-one association to User
	@JsonBackReference
	@OneToMany(mappedBy="user")
	private List<UserHe> users;

	public UserHe() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getImg() {
		return this.img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@JsonProperty("subordinates")
	public UserHe getUser() {
		return this.user;
	}
	@JsonProperty("subordinates")
	public void setUser(UserHe user) {
		this.user = user;
	}

	public List<UserHe> getUsers() {
		return this.users;
	}

	public void setUsers(List<UserHe> users) {
		this.users = users;
	}

	public UserHe addUser(UserHe user) {
		getUsers().add(user);
		user.setUser(this);

		return user;
	}

	public UserHe removeUser(UserHe user) {
		getUsers().remove(user);
		user.setUser(null);

		return user;
	}

}