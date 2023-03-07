package com.keduit.bpro53.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class MovieDTO {

	private Long mno;
	private String title;
	@Builder.Default
	private List<MovieImageDTO> imageDTOList = new ArrayList<>();
}
