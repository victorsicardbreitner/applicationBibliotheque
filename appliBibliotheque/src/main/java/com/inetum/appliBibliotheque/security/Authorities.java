package com.inetum.appliBibliotheque.security;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "AUTHORITIES")
@Getter @Setter @NoArgsConstructor
public class Authorities {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "USERNAME", length = 128, nullable = false)
    private String username;

    @Column(name = "AUTHORITY", length = 128, nullable = false)
    private String authority;

	public Authorities(String username, String authority) {
		super();
		this.username = username;
		this.authority = authority;
	}

    
}
