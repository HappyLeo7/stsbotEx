package com.keduit.bpro53.service;

import com.keduit.bpro53.dto.MovieDTO;

public interface MovieService {

	Long register(MovieDTO movieDTO);
	
	//default Map<String, Object> dtoTOEntity;
}
