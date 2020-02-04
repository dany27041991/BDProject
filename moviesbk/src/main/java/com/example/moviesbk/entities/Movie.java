package com.example.moviesbk.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings({ "deprecation", "unused" })
@Entity
@Table(name = "movie")
@AllArgsConstructor @NoArgsConstructor
public class Movie implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @Column(name = "idmovie", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private int idmovie;
	
	@Column(name = "title", unique=true)
	@Getter @Setter
	@NotNull @NotEmpty @NotBlank
	private String title;
	
	@Column(name = "year")
	@Getter @Setter
	@NotNull @NotEmpty @NotBlank
	private int year;
	
	@Column(name = "released")
	@Getter @Setter
	@NotNull @NotEmpty @NotBlank
	private Date released;
	
	@Column(name = "runtime")
	@Getter @Setter
	@NotNull @NotEmpty @NotBlank
	private String runtime;
	
	@Column(name = "genre")
	@Getter @Setter
	@NotNull @NotEmpty @NotBlank
	private String genre;
	
	@Column(name = "director")
	@Getter @Setter
	@NotNull @NotEmpty @NotBlank
	private String director;
	
	@Column(name = "writer", length=512)
	@Getter @Setter
	@NotNull @NotEmpty @NotBlank
	private String writer;
	
	@Column(name = "actors", length=512)
	@Getter @Setter
	@NotNull @NotEmpty @NotBlank
	private String actors;
	
	@Column(name = "plot", length=512)
	@Getter @Setter
	@NotNull @NotEmpty @NotBlank
	private String plot;
	
	@Column(name = "language")
	@Getter @Setter
	@NotNull @NotEmpty @NotBlank
	private String language;
	
	@Column(name = "country")
	@Getter @Setter
	@NotNull @NotEmpty @NotBlank
	private String country;
	
	@Column(name = "adwards", length=512)
	@Getter @Setter
	private String adwards;
	
	@Column(name = "poster")
	@Getter @Setter
	private String poster;
	
	@Column(name = "dvd")
	@Getter @Setter
	@NotNull @NotEmpty @NotBlank
	private Date dvd;
	
	@Column(name = "production")
	@Getter @Setter
	@NotNull @NotEmpty @NotBlank
	private String production;
	
	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY,  orphanRemoval = true)
	@Getter @Setter
	Set<AddFavourite> addFavourites;
	
	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, fetch = FetchType.LAZY,  orphanRemoval = true)
	@Getter @Setter
    Set<AddRating> ratings;
	
	public Movie(String title, int year, Date released, String runtime, String genre, 
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
