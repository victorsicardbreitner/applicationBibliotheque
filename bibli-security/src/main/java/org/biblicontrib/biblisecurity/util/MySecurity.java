package org.biblicontrib.biblisecurity.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@Profile("withSecurity")
public class MySecurity {
	
	private static Logger logger = LoggerFactory.getLogger(MySecurity.class);
	
	public static String DEFAULT_SPRING_SECURITY_ROLE_PREFIX = "ROLE_";
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(); // By default since spring 5
	}

	static List<String> roleNameList(Authentication authentication) {
		UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
		List<String> roleNameList = new ArrayList<String>();
		for (GrantedAuthority ga : userPrincipal.getAuthorities()) {
			String springSecurityRoleName = ga.getAuthority();
			String roleName = springSecurityRoleName;
			logger.info("####### roleName="+roleName);
			System.out.println("roleName="+roleName);
			// ou bien roleName = springSecurityRoleName moins le préfixe "ROLE_" (affaire
			// de préférence)
			/*
			 * if(roleName.startsWith(DEFAULT_SPRING_SECURITY_ROLE_PREFIX)){ roleName =
			 * roleName.substring(DEFAULT_SPRING_SECURITY_ROLE_PREFIX.length()); }
			 */
			roleNameList.add(roleName);
		}
		return roleNameList;
	}
}
