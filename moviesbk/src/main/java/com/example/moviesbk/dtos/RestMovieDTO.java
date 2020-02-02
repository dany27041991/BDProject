package com.example.moviesbk.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
public class RestMovieDTO {
	
	@Getter @Setter
	private String title;
	
	@Getter @Setter
	private int year;
	
	@Getter @Setter
	private String rated;
	
	@Getter @Setter
	private String released;
	
	@Getter @Setter
	private String runtime;
	
	@Getter @Setter
	private String genre;
	
	@Getter @Setter
	private int director;
	
	@Getter @Setter
	private String writer;
	
	@Getter @Setter
	private String actors;
	
	@Getter @Setter
	private String plot;
	
	@Getter @Setter
	private String language;
	
	@Getter @Setter
	private String country;
	
	@Getter @Setter
	private String adwars;
	
	@Getter @Setter
	private String poster;
	
	@Getter @Setter
	private Object ratings;
	
	@Getter @Setter
	private String metascore;
	
	@Getter @Setter
	private String imdbRating;
	
	@Getter @Setter
	private String imdbVotes;
	
	@Getter @Setter
	private String imdbID;
	
	@Getter @Setter
	private String type;
	
	@Getter @Setter
	private String dvd;
	
	@Getter @Setter
	private String BoxOffice;
	
	@Getter @Setter
	private String production;
	
	@Getter @Setter
	private String website;
	
	@Getter @Setter
	private String response;
}
