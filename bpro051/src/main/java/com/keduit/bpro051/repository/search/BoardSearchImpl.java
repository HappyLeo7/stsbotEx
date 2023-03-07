package com.keduit.bpro051.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.keduit.bpro051.entity.Memom;

public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch{
	public BoardSearchImpl() {
		super(Memom.class);
		
	}
	
	@Override
	public Page<Memom> search1(Pageable pageable){
		return null;
	}
	

}
