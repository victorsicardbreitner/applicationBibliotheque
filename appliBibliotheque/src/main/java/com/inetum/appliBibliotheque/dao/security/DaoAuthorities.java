package com.inetum.appliBibliotheque.dao.security;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inetum.appliBibliotheque.security.Authorities;

public interface DaoAuthorities extends JpaRepository<Authorities,Long> {

}
