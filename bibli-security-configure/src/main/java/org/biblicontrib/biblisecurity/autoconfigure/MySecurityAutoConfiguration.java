package org.biblicontrib.biblisecurity.autoconfigure;

import org.biblicontrib.biblisecurity.config.WebSecurityRecentConfig;
import org.biblicontrib.biblisecurity.config.WithoutSecurityRecentConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
/*
NB: cette classe est référencée dans le fichier
META-INF/spring.factories (de src/main/resources)
org.springframework.boot.autoconfigure.EnableAutoConfiguration=org.biblicontrib.biblisecurity.autoconfigure.MySecurityAutoConfiguration
(ou bien = AutoConfig1,...,AutoConfigN
selon les spécifications suivantes:
https://docs.spring.io/spring-boot/docs/2.1.18.RELEASE/reference/html/boot-features-developing-auto-configuration.html
*/
@Configuration
@ComponentScan({ "org.biblicontrib.biblisecurity.config" , "org.biblicontrib.biblisecurity.util" , "org.biblicontrib.biblisecurity.rest"})
public class MySecurityAutoConfiguration {

}
