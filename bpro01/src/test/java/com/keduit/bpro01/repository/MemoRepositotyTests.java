package com.keduit.bpro01.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import com.keduit.bpro01.entity.Memo;

//스프링부트에서 테스트를 쓰기위해서 꼭넣어야하는 에노테이션
@SpringBootTest
public class MemoRepositotyTests {
	
	//인터에피스를 생성함??
	@Autowired
	MemoRepository memoRepository;
	
	//
	@Test
	public void testClass() {
		System.out.println("memoRepository 주소:::"+memoRepository.getClass().getName());
	}
	
	@Test
	public void testInsertDummies() {
		IntStream.rangeClosed(1, 100).forEach(i->{
			Memo memo = Memo.builder().memoText("Sample memo...."+i).build();
			memoRepository.save(memo);
		});
		
	}
	
	@Test
	public void testSelect() {
		System.out.println("--------구분라인---------");
		Long mno = 100L;
		// mno로 만든 id(db에서 pk를의미)
		Optional<Memo> result= memoRepository.findById(mno); //Optional 화해야한다.
		
		System.out.println("--------구분라인2---------");
		if(result.isPresent()) {
			Memo memo = result.get();
			System.out.println("[memo]값출력 : "+memo);
		}
		System.out.println("--------/구분라인---------");
	}
	
	@Transactional  //실제 객체가 생길때까지 기다렸다가 주입을해준다.
	@Test
	public void testSelet2() {
		Long mno = 50L;
		Memo memo = memoRepository.getOne(mno); //지연처리 ??
		System.out.println("------구분선------");
		System.out.println("[memo]값출력 : "+memo);
		System.out.println("------/구분선------");
	}
	
	@Test
	public void testDelete() {
		Long mno = 100L;
		 memoRepository.deleteById(mno);
	}
	
	@Test
	public void testUpdate() {
		/*Long mno =99L;*/
		Memo memo = Memo.builder().mno(97L).memoText("Update text......").build();
		System.out.println("[memo]값출력"+memoRepository.save(memo));
	}

	//table 페이지 쪽수를 테스트하는 코드
	@Test
	public void testPageDefault() {
		//Pageable 도메인으로 import
		//PageRequest 도메인으로 import
		//of(페이지위치,1페이지에 뽑아낼 수량)
		Pageable pageable = PageRequest.of(0, 10);
		
		//Page 도메인으로 import
		Page<Memo> result = memoRepository.findAll(pageable);
		System.out.println("--------구분선1--------");
		System.out.println("[result]값출력 : "+result);
		
		System.out.println("--------구분선2--------");
		
		System.out.println("[Total pages] : "+result.getTotalPages());
		System.out.println("[Total count] : "+result.getTotalElements());
		System.out.println("[Page number] : "+result.getNumber());
		System.out.println("[Page size] : "+result.getSize());
		System.out.println("[has next page?] : "+result.hasNext());
		System.out.println("[first page?] : "+result.isFirst());
		System.out.println("--------/구분선--------");
		
		//데이터 내용을 순차로 출력
		System.out.println("--------데이터내용출력--------");
		for(Memo memo : result.getContent()) {
			System.out.println(memo);
		}
		System.out.println("--------/데이터내용출력--------");
	}
	
	//역순으로 읽어온다.
	@Test
	public void testSort() {
		
//		Sort sort1 = Sort.by("mno").descending(); //역순으로 읽음
//		Pageable pageable = PageRequest.of(0, 10, sort1);
		
		Sort sort1 = Sort.by("mno").descending(); //역순으로 읽음
		Sort sort2 = Sort.by("memoText").ascending();
		Sort sortAll = sort1.and(sort2);
		Pageable pageable = PageRequest.of(0, 10, sortAll);
		Page<Memo> result = memoRepository.findAll(pageable);
		
		result.get().forEach(memo ->{
			
			System.out.println("[memo] : "+memo);
		});
	}
	
	
	//?? 에서 ?? 까지 불러올때
	@Test
	public void testQueryMethods() {
		List<Memo> list = memoRepository.findByMnoBetweenOrderByMnoDesc(65L, 90L);
		
		for(Memo memo : list) {
			System.out.println(memo);
		}
	}
	
	@Test
	public void testQueryMethodWithPages() {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());
		Page<Memo> result=memoRepository.findByMnoBetween(10L, 50L, pageable);
		result.get().forEach(memo -> System.out.println("[memo]값출력 : "+memo));
	}
	
	//삭제하기
	@Commit
	@Transactional
	@Test
	public void testDeleteQueryMethods() {
		memoRepository.deleteMemoByMnoLessThan(10L);
	}
	
	   @Transactional
	   @Test
	   public void testSelectQuery(){
	      
	      List<Memo> list = memoRepository.getListDesc();
	      
	      for(Memo memo : list) {
	         System.out.println(memo);         
	      }
	   }
	   
	
	// -- /Class -- //
}
