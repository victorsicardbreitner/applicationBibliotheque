package com.inetum.appliBibliotheque.dao.security;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inetum.appliBibliotheque.security.Users;

public interface DaoUsers extends JpaRepository<Users,String> {

}
