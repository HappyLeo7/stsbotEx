package com.keduit.bpro54.security.service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.keduit.bpro54.entity.ClubMember;
import com.keduit.bpro54.entity.ClubMemberRole;
import com.keduit.bpro54.repository.ClubMemberRepository;
import com.keduit.bpro54.security.dto.ClubAuthMemberDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor // 생성자 받아오기
public class ClubOAth2UserDetailsService extends DefaultOAuth2UserService{

	private final ClubMemberRepository clubMemberRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		
		// TODO Auto-generated method stub
		log.info("............. loadUser() start===========");
		log.info("1. userRequest : " + userRequest);
		
		
		String clientName = userRequest.getClientRegistration().getClientName();
		log.info("2. clintName : " +clientName);
		log.info("3. parameter 유저정보 : " +userRequest.getAdditionalParameters());
		OAuth2User oAuth2User= super.loadUser(userRequest);
		
		Map<String, Object> paramMap = oAuth2User.getAttributes();
		String email = null;
		
		switch(clientName) {
		case "kakao":
			email= getKaKaoEmail(paramMap);
			break;
		}
		
		log.info(".......................................");
		
		log.info(email);
		//로그인 정보를 다 불러온다.
		//oAuth2User.getAttributes().forEach((key,value) -> log.info(key+ "(key) : " + value+"(value)"));
		log.info("............. / loadUser() end=========");
//		return super.loadUser(userRequest);
		
		ClubMember member = saveSocialMember(email);
		
		
		//DTO 변환작업
		ClubAuthMemberDTO clubAuthMemberDTO=new ClubAuthMemberDTO(
				member.getEmail(),
				member.getPassword(),
				true,
				member.getRoleSet().stream()
				.map(role -> new SimpleGrantedAuthority("ROLE_"+role.name())).collect(Collectors.toList()),
				oAuth2User.getAttributes());
				
//		위에 들어갈 형태	=>	clientName, email, false, null, paramMap);
		
		clubAuthMemberDTO.setName(member.getName());
		
	//	return oAuth2User;
		return clubAuthMemberDTO;
	}

	private ClubMember saveSocialMember(String email) {
		// 이메일 주소로 기존에 가입된 회원이 있는지 체크해야한다.
		Optional<ClubMember> result = clubMemberRepository.findByEmail(email,true);
		
		if(result.isPresent()) {
			return result.get();
		}
		
		// 가입 이력이 없다면 등록 한다.
		ClubMember clubMember = ClubMember.builder()
				.email(email)
				.name(email)
				.password(passwordEncoder.encode("1111"))//패스워드는 인코딩 필수
				.fromSocial(true)
				.build();
		
		clubMember.addMemberRole(ClubMemberRole.USER);
		return clubMember;
	}

	private String getKaKaoEmail(Map<String, Object> paramMap) {
		log.info("............. getKaKaoEmail() start===========");
		
		Object value = paramMap.get("kakao_account");
		log.info("kakao_account : "+ value);
		
		LinkedHashMap accountMap = (LinkedHashMap)value;
		String email = (String) accountMap.get("email");
		
		log.info("email : " + email);
		
		log.info("............. /getKaKaoEmail() end=========");
		return email;
	}

}
