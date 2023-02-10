package com.keduit.bpro54.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller //화면 출력
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/sample/")
public class SampleController {
	@GetMapping("/all")
	public void exAll() {
		log.info("=====> SampleController => /sample/all  연결 <======");
	}
	
	@GetMapping("/member")
	public void exMember() {
		log.info("=====> SampleController => /sample/member  연결 <======");
		
	}
	
	@GetMapping("/admin")
	public void exAdmin() {
		log.info("=====> SampleController => /sample/admin  연결 <======");
		
	}
}
