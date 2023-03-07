package com.keduit.bpro01.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//화면없이할때
@RestController
public class SampleController {
	
	
	//겟으로 받아올거다 (경로)
	@GetMapping("/hello")
	// 배열 메서드()
	public String[] hello() {
		//리턴해오기 
		return new String[] {"Hello", "World!"};
	}
}
