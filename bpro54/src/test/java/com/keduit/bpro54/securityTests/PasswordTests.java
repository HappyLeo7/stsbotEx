package com.keduit.bpro54.securityTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PasswordTests {

	@Autowired
	public PasswordEncoder passwordEncoder;
	
	@Test
	public void testEncode() {
		String password = "1111";
		String enPw = passwordEncoder.encode(password);
		System.out.println("enPw : " +enPw);
		
		//비교
		boolean matchResult = passwordEncoder.matches(password, enPw);
		if(matchResult==true) {
		System.out.println(matchResult);
		System.out.println("일치 합니다.");
		}else {
			System.out.println("일치 하지 않습니다..");
		}
	}
}
