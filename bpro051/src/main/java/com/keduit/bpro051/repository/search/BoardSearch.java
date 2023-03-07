package com.keduit.bpro051.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.keduit.bpro051.entity.Memom;

public interface BoardSearch {
	Page<Memom> search1(Pageable pageable);
}
