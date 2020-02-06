package com.example.moviesbk.dtos;



import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
public class MovieUpdateFormDTO {
	
	@Getter @Setter
	private int idmovie;
	
	@Getter @Setter
	private String country;
	
	@Getter @Setter
	private String production;
	
	@Getter @Setter
	private String plot;
	
	@Getter @Setter
	private String adwards;
	
	@Getter @Setter
	private Date dvd;
	
	@Getter @Setter
	private Date released;
	
	@Getter @Setter
	private int year;
	
	@Getter @Setter
	private String runtime;

}
