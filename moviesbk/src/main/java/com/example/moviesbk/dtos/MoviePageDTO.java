package com.example.moviesbk.dtos;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
public class MoviePageDTO {
		  
	@Getter @Setter
	private List<MovieDTO> content;

	@Getter @Setter
	private Pageable pageableObject;
	
	@Getter @Setter
	private int totalPages;
	
	@Getter @Setter
	private long totalElements;
	
	@Getter @Setter
	private int size;
	
	@Getter @Setter
	private int number;
	
	@Getter @Setter
	private int numberOfElements;

	@Getter @Setter
	private Sort sort;

}
