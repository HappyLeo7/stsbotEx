package com.keduit.bpro53.controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.keduit.bpro53.dto.MovieDTO;
import com.keduit.bpro53.dto.PageRequestDTO;
import com.keduit.bpro53.service.MovieService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/movie")
@Log4j2
@RequiredArgsConstructor //생성자 주입을 받기위해서 private final MovieService movieService;
public class MovieController {
	
	private final MovieService movieService;
	
	@GetMapping("/register")
	public void register() {
		
	}
	
	
	@PostMapping("/register")
	public String register(MovieDTO movieDTO,RedirectAttributes redirectAttributes) {
		log.info("movieDTO: " + movieDTO);
		Long mno = movieService.register(movieDTO);
		redirectAttributes.addFlashAttribute("msg", mno);
		return "redirect:/movie/list";
		
		
	}
	
	//목록 페이지생성
	@GetMapping("/list")
	public void list (PageRequestDTO pageRequestDTO, Model model) {
	System.out.println("[컨트롤러] list");
		log.info("pageRequestDTO[위치컨트롤러] : " + pageRequestDTO);
		model.addAttribute("result", movieService.getList(pageRequestDTO));
	}
	
	
	
	@GetMapping({"/read", "/modify"})
	public void read(long mno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {
		log.info("movie컨트롤러 mno : "+mno);
		
		MovieDTO movieDTO = movieService.getMovie(mno);
		
		log.info("movie컨트롤러 mno1 : "+mno);
		model.addAttribute("dto", movieDTO);
	}
}
