package com.keduit.bpro051.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.keduit.bpro051.dto.MemomDTO;
import com.keduit.bpro051.dto.PageRequestDTO;
import com.keduit.bpro051.service.MemoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/board")
@Log4j2
//생성자 주입
@RequiredArgsConstructor
public class MemomController {
	private final MemoService service;
	
	@GetMapping({"/"})
	public String index() {
		return "redirect:/board/list";
		
	}
	
	@GetMapping("/tests")
	public void tests() {
		
	}
	
	@GetMapping({"/list"})
	public void list(PageRequestDTO pageRequestDTO, Model model) {
		log.info("list페이지................==::" + pageRequestDTO);
		model.addAttribute("result", service.getList(pageRequestDTO));
		
	}
	
	@GetMapping({"/register"})
	public void register() {
		log.info(".......register get.......");
	}
	
	@PostMapping({"/register"})
	public String registerPost(MemomDTO dto, RedirectAttributes redirectAttributes) {
		log.info("register PostMapping dto ......."+dto);
		
		Long bno = service.register(dto);
		redirectAttributes.addFlashAttribute("msg",bno);
		return "redirect:/board/list";
	}
	
	@GetMapping({"/read","/modify"})
	public void read(Long bno, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {
		log.info("/read ==> bno :"+bno);
		MemomDTO dto =service.read(bno);
		model.addAttribute("dto",dto);
	}
	
	@PostMapping({"/remove"})
	public String remove(long bno, RedirectAttributes redirectAttributes) {
		log.info("[[[remove]]] bno : "+bno+"번 삭제됨");
		service.remove(bno);
		redirectAttributes.addFlashAttribute("msg",bno);
		return "redirct:/board/list";
	}
	
	@PostMapping({"/modify"})
	public String modify(MemomDTO dto, @ModelAttribute("requestDTO") PageRequestDTO requestDTO, RedirectAttributes redirectAttributes) {
		log.info("post modify ........>");
		log.info("modify dto : ", dto);
		
		service.modify(dto);
		redirectAttributes.addAttribute("page", requestDTO.getPage());
		redirectAttributes.addAttribute("bno", dto.getBno());
	return "redirect:/board/read";	
	}

	
}
