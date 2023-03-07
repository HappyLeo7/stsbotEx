package com.keduit.bpro051.re;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.keduit.bpro051.entity.Memom;


public interface MemoRe extends JpaRepository<Memom, Long> {
	/* List<Memom> fin(Long from,Long to); */
	
	@Query(value="select now()", nativeQuery=true)
	String getTime();
}
