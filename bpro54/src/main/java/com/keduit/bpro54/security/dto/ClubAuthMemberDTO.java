package com.keduit.bpro54.security.dto;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;



@Log4j2

//@Data 로해도됨
@Getter
@Setter
@ToString

public class ClubAuthMemberDTO extends User implements OAuth2User{
	private String email;
	private String password;
	private String name;
	private boolean fromSocial;
	
	
	private Map<String, Object> attr;


	public ClubAuthMemberDTO(
			String username, 
			String password, 
			boolean fromSocial,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.password = password;
		this.email = username;
		this.fromSocial=fromSocial;
	}


	@Override
	public Map<String, Object> getAttributes() {

		
		return this.attr;
	}




	public ClubAuthMemberDTO(
			String username, 
			String password, 
			boolean fromSocial,
			Collection<? extends GrantedAuthority> authorities,
			Map<String, Object> attr) {
		this(username, password, fromSocial, authorities);
		this.attr = attr;
		
		// TODO Auto-generated constructor stub
	}
	

}