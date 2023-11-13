package com.inetum.appliBibliotheque.security;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="users")
@Getter @Setter @NoArgsConstructor
public class Users {

	@Id
	private String username;
	private String password;
	private String enabled;
	
	public Users(String username, String password, String enabled) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}
	
	
	

}
