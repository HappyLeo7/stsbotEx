package com.keduit.bpro01.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.keduit.bpro01.entity.Memo;


//인터페이스를 생성하고 JpaRepository를 무조건 상속해줘야한다
//T : 클래스명
//ID : 타입
public interface MemoRepository extends JpaRepository<Memo, Long>{

	//from 에서 to 까지 불러줄때
	List<Memo> findByMnoBetweenOrderByMnoDesc(Long from, Long to);
	
	Page<Memo> findByMnoBetween(Long from, Long to, Pageable pageable);
	
	void deleteMemoByMnoLessThan(Long num);
	
	@Query("select m from Memo m order by m.mno desc")
	List<Memo> getListDesc();
	
	
	
}
