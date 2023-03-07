package com.keduit.bpro54.repositoryT;

import java.util.stream.IntStream;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.keduit.bpro54.entity.Member;
import com.keduit.bpro54.repository.MemberRepository;
import com.keduit.bpro54.repository.ReviewRepository;

@SpringBootTest
public class MemberRepositoryTests {

	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	//member 데이터 생성
	@Test
	public void insertMembers() {
		IntStream.rangeClosed(1, 200).forEach(i->{
			Member member = Member.builder()
									.email("member"+i+"@abc.com")
									.pw("1"+i)
									.nickname("reviewr"+i)
									.build();
			memberRepository.save(member);
		});
	}
	
	//멤버 삭제시 리뷰도 삭제할수 있게 한다.
	@Test
	@javax.transaction.Transactional
	@Commit
	public void testDeleteMember() {
		Long mid = 2L; //member id
		Member member = Member.builder()
				.mid(mid)
				.build();
		System.out.println(" [삭제된할 member :  "+member+"]");
		System.out.println(" [mid : "+mid+"번 삭제예정.]" );
		reviewRepository.deleteByMember(member);
		memberRepository.deleteById(mid);
		System.out.println(" 삭제된 member :  "+member);
		System.out.println(" mid : "+mid+"번 삭제되었습니다." );
		
	}
}
