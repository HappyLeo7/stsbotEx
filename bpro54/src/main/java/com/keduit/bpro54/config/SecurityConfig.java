
package com.keduit.bpro54.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.keduit.bpro54.security.handler.ClubLoginSuccessHandler;
import com.keduit.bpro54.security.service.ClubUserDetailsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Configuration
@Log4j2
//@RequiredArgsConstructor

@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	
	@Bean
	PasswordEncoder passwordEncoder() {//인터페이스
		return new BCryptPasswordEncoder(); //암호화를해서 돌려준다 ?
	}
	
	
	@Autowired
	private ClubUserDetailsService clubuserDetailsService;
	
	
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
	
	log.info("=========filterChain==========");
	http.formLogin().loginPage("/sample/login");
	
	http.authorizeHttpRequests((auth)->{
		auth.antMatchers("/sample/all").permitAll();
		auth.antMatchers("/sample/member").hasRole("USER");
		auth.antMatchers("/sample/manger").hasRole("MANAGER");
		auth.antMatchers("/sample/admin").hasRole("ADMIN");
		
	});
	
	
	http.formLogin(); // 인가, 인증에 문제가 발생할때 로그인 화면을 띄움
	http.csrf().disable();
	http.logout();
	
	//카카오 로그인 , 핸들러 클래스를 만든 후 succesHandler를 붙여준다.
//	http.oauth2Login().loginPage("/sample/login").successHandler(successHandler());
	http.oauth2Login().loginPage("/sample/login");
	http.rememberMe().tokenValiditySeconds(60*60*7) //시간 60*60*7 = 7일
	.userDetailsService(clubuserDetailsService);
	return http.build();
	
}

@Bean
public ClubLoginSuccessHandler successHandler() {
	// TODO Auto-generated method stub
	return new ClubLoginSuccessHandler(passwordEncoder());
}
}
