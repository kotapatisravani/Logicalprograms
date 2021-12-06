package com.bitlabs.bischeduler.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name="unique_values",columnNames = {"username"}))
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@NotNull(message="Please Enter Full Name")
	private String full_name;
	@NotNull(message="Email id should not be empty")
	@Email(message="Please eneter correct email id")
	private String username;
	//@Pattern(regexp="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",message="invalid password")
	@NotNull(message="Please Enter Password")
	private String password;
	
	private String roles="ROLE_USER";
	
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date created_at;
	
	private int created_by=1;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public java.util.Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(java.util.Date created_at) {
		this.created_at = created_at;
	}
	@PrePersist
	public void onCreate() {
		created_at=new java.util.Date();
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	



	
	
}
