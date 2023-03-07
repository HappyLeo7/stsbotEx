package com.keduit.bpro53;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.keduit.bpro53.entity.Member;
import com.keduit.bpro53.repository.MemberRepository;

@SpringBootTest
class Bpro53ApplicationTests {

	@Autowired
	private MemberRepository memberRepository;
	
	@Test
	public void insertmembers() {
		IntStream.rangeClosed(1,100).forEach(i->{
			Member member = Member.builder()
					.email("user"+i+"@abc.com")
					.password("0001")
					.name("user"+i)
					.build();
			memberRepository.save(member);
		});
	}
	
	@Test
	void contextLoads() {
	}

}
