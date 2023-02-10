package com.keduit.bpro54.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2

@Configuration   //(@Bean)빈을 등록해서 사용할수있다.

@RequiredArgsConstructor
public class CustomSecurityConfig  {

/*
	
	  @Bean
	  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{ System.out.println("--------------configure---------------");
	  log.info("--------------configure---------------");
	  
	  String ps ="123";
	  if(ps=="123") {
		  
		  return http.build();
	  }
return null;	  
	  }
	 */
}
