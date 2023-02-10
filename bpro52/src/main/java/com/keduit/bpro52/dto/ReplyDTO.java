package com.keduit.bpro52.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDTO {

	
	private Long rno; //
	private String text; //내용
	private String replyer; //글쓴이
	private Long bno; //게시글 번호
	private LocalDateTime regDate, updateDate;
}
