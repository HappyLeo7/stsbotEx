package com.keduit.bpro051.dto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.Data;

@Data
public class PageResultDTO<DTO,EN> {
	private List<DTO> dtolist;
	
	//페이지 관련 DTO
	private int totalPage;
	private int page;
	private int size;
	private int start;
	private int end;
	private boolean prev, next;
	private List<Integer> pageList;
	
	public PageResultDTO(Page<EN> result, Function<EN,DTO> fn){
		dtolist = result.stream().map(fn).collect(Collectors.toList());
		
		//[추가]
		totalPage=result.getTotalPages();
		makePageList(result.getPageable());
		
	}

	private void makePageList(Pageable pageable) {
		this.page=pageable.getPageNumber()+1;
		this.size=pageable.getPageSize();
		
		
		//[페이지 클릭?]
		int tempEnd =(int)(Math.ceil(page/10.0))*10;
		start = tempEnd - 9; // 0~10 안나오게하려고 -9
		prev = start > 1;
		end = totalPage > tempEnd? tempEnd+1 : totalPage+1;
		next = totalPage > tempEnd;
		pageList = IntStream.range(start, end).boxed().collect(Collectors.toList());
	}

}
