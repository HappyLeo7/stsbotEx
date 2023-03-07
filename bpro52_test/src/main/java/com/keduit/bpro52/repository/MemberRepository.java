package com.keduit.bpro52.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.keduit.bpro52.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String>{

	Page<Object[]> searchPage(String string, String string2, Pageable pagrable);

}
