package org.biblicontrib.biblisecurity.config;

import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer;

public interface MySecuritySimpleConfigurer {
	void configureDefaultUsers(UserDetailsManagerConfigurer udmc);
	//...
}
