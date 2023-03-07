package com.keduit.bpro02.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.keduit.bpro02.dto.SampleDTO;

import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping({"/sample","/layout"})
@Log4j2
public class SampleController {
   
   @GetMapping({"ex1","layout1"})
   public void ex1() {
      log.info("===== ex1 or layout1 =====");
   }
   
   @GetMapping({"/ex2","/exLink"})
   public void exModel(Model model) {
	
	   List<SampleDTO> list = IntStream
			   .rangeClosed(1, 20)
			   .asLongStream()
			   .mapToObj(i->{
				   SampleDTO dtto = SampleDTO.builder()
						   					.sno(i)
						   					.first("first.........."+i)
						   					.last("Last........"+i)
						   					.regTime(LocalDateTime.now()).build();
			   return dtto;
			   }).collect(Collectors.toList());
	   model.addAttribute("list", list);
   }

   @GetMapping("/exInline")
   public String exInline(RedirectAttributes redirectAttributes) {
	   log.info("---- exInline 테스트중 ----");
	   
	   Long num=10L;
	   SampleDTO dto = SampleDTO.builder()
			   .sno(num)
			   .first("First.... "+num)
			   .last("Last.... "+num)
			   .regTime(LocalDateTime.now())
			   .build();
	   redirectAttributes.addFlashAttribute("result","success");
	   redirectAttributes.addFlashAttribute("dto",dto);
	   
	   
	   log.info("---- /exInline 테스트중 ----");
	   return "redirect:/sample/ex3";
   }
   
   @GetMapping("/ex3")
   public void ex3() {
	   log.info("ex3 출력");
	   
   }
   
   @GetMapping({"/exLayout1","/exLayout2","/exSidebar"})
   public void exLayout1() {
	   log.info("exLayout=========>");
	   
	   
   }
   
   ///end
   
   
   ////
}