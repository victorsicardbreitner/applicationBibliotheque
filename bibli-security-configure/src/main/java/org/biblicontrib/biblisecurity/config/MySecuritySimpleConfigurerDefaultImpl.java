package org.biblicontrib.biblisecurity.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MySecuritySimpleConfigurerDefaultImpl implements MySecuritySimpleConfigurer {

	private static Logger logger = LoggerFactory.getLogger(MySecuritySimpleConfigurerDefaultImpl.class);
	
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	public MySecuritySimpleConfigurerDefaultImpl(BCryptPasswordEncoder passwordEncoder){
		this.passwordEncoder=passwordEncoder;
	}
	
	@Override
	public void configureDefaultUsers(UserDetailsManagerConfigurer udmc) {
		logger.info("####### configureDefaultUsers");
		
		udmc
		.withUser("user1").password(passwordEncoder.encode("pwd1")).roles("USER")
		.and().withUser("admin1").password(passwordEncoder.encode("pwd1")).roles("ADMIN")
		.and().withUser("user2").password(passwordEncoder.encode("pwd2")).roles("USER")
		.and().withUser("admin2").password(passwordEncoder.encode("pwd2")).roles("ADMIN");
	}

}
