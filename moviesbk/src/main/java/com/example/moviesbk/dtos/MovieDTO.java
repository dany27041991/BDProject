package com.example.moviesbk.dtos;

import java.sql.Date;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class MovieDTO {

    @Getter @Setter
    private int idmovie;
	
	@Getter @Setter
	private String title;
	
	@Getter @Setter
	private int year;
	
	@Getter @Setter
	private Date released;
	
	@Getter @Setter
	private String runtime;
	
	@Getter @Setter
	private String genre;
	
	@Getter @Setter
	private String director;
	
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
	private String adwards;
	
	@Getter @Setter
	private String poster;
	
	@Getter @Setter
	private Date dvd;
	
	@Getter @Setter
	private String production;
	
	@Getter @Setter
	List<FavouriteFormDTO> addFavouritesObj;
	
	@Getter @Setter
	List<RatingFormDTO> ratingsObj;
	
	public MovieDTO(int idmovie, String title, int year, Date released, String runtime, String genre, 
			 String director, String writer, String actors, String plot, String language,
			 String country, String adwards, String poster, Date dvd, String production,
			 List<FavouriteFormDTO> addFavourites, List<RatingFormDTO> ratings) {
		this.idmovie = idmovie; 
		this.title = title;
		this.year = year;
		this.released = released;
		this.runtime = runtime;
		this.genre = genre;
		this.director = director;
		this.writer = writer;
		this.actors = actors;
		this.plot = plot;
		this.language = language;
		this.country = country;
		this.adwards = adwards;
		this.poster = poster;
		this.dvd = dvd; 
		this.production = production;
		this.addFavouritesObj = addFavourites;
		this.ratingsObj = ratings;
	}
	
	public MovieDTO(String title, int year, Date released, String runtime, String genre, 
			 String director, String writer, String actors, String plot, String language,
			 String country, String adwards, String poster, Date dvd, String production) {
		 this.title = title;
		 this.year = year;
		 this.released = released;
		 this.runtime = runtime;
		 this.genre = genre;
		 this.director = director;
		 this.writer = writer;
		 this.actors = actors;
		 this.plot = plot;
		 this.language = language;
		 this.country = country;
		 this.adwards = adwards;
		 this.poster = poster;
		 this.dvd = dvd;
		 this.production = production;
	}
}
