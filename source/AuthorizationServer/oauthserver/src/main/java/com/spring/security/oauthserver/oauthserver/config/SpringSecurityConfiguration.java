package com.spring.security.oauthserver.oauthserver.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;

@Configuration
public class SpringSecurityConfiguration {
    
    @Bean
    SecurityFilterChain configureSecurityFilterChain(HttpSecurity http) throws Exception {
		
		http.formLogin(Customizer.withDefaults())
		.authorizeHttpRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated());
		
		return http.build();
	}

    @Bean
    public UserDetailsService users(){
		
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		UserDetails user = User.withUsername("ramen")
				.password(encoder.encode("password"))
				.roles("read")
				.build();
		
		return new InMemoryUserDetailsManager(user);
    }

}
