package com.bi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Encoders {

    @Bean
    public PasswordEncoder oauthClientPasswordEncoder() {
        return new BCryptPasswordEncoder(4);
    }

    @Bean
    public PasswordEncoder userPasswordEncoder() {
        return new BCryptPasswordEncoder(8);
    }
    
    public static void main(String[] args) {
//    	System.out.println(new BCryptPasswordEncoder(8).encode("admin1234"));
    	System.out.println(new Encoders().userPasswordEncoder().matches("test1231", "$2a$08$VV1zQVMSvg1OiC/5Rw.l6evt9K3XBMkzgNpRvnIunLFB7XaU2sdhq"));
	}
}