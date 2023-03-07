package com.keduit.bpro051.memoTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.keduit.bpro051.entity.Memom;
import com.keduit.bpro051.re.MemoRe;

import lombok.extern.log4j.Log4j2;

//스프링부트에서 테스트를위해 넣어야하는 에노테이션
@SpringBootTest
@Log4j2

public class MemoTests {

	@Autowired
	MemoRe memore;

	@Test
	public void testClass() {
		System.out.println("memore 주소 ::: " + memore.getClass().getName());
	}

	@Test
	public void testInsert() {
		IntStream.rangeClosed(201, 500).forEach(i -> {
			Memom memom = Memom.builder().title("test제목")
					.content("test내용")
					.writer("test사용자")
					.build();
			memore.save(memom);
			System.out.println("Memom 테이블에 저장되었습니다 :: " + memom);

		});
	}
	
	@Test
	public void testSelet() {
		List<Memom> mm = memore.findAll();
		System.out.println(mm);
	}
	
	/*
	 * @Test public void testSelet1() { List<Memom> mo=memore.fin(1L, 5L);
	 * System.out.println(mo); }
	 */
	
	@Test
	public void testDelete() {
		Long bro = 50L;
		memore.deleteById(bro);
		System.out.println(bro+"번 삭제하였습니다.");
	}

	@Test
	public void testUpdate() {
		 Long bno = 51L; 
		
		 Memom memom = Memom.builder().bno(bno)
				 .title("제목이 수정되었습니다.")
				 .content("수정된 내용")
				 .writer("사용자").build();
		 System.out.println("[번 제목 수정] : "+ memore.save(memom));
		 
	}
	
	@Test
	public void testUp() {
		Long bno=5L;
		Optional<Memom> result= memore.findById(bno);
		Memom mo=result.orElseThrow();
		/* mo.change("","",""); */
	
		memore.save(mo);
	}
	
	@Test
	public void testPaging() {
		//org.spring.domain
		Pageable pageable = PageRequest.of(0, 10,Sort.by("bno").descending());
		Page<Memom> result = memore.findAll(pageable);
		log.info("total count : "+result.getTotalElements());
		log.info("total page : "+result.getTotalPages());
		log.info("page number : "+result.getNumber());
		log.info("page size : "+result.getSize());
		
		List<Memom> memo = result.getContent();
		memo.forEach(mm->log.info(mm)); 
	}
}
