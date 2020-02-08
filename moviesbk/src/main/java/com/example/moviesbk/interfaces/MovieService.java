package com.example.moviesbk.interfaces;

import java.util.List;

import com.example.moviesbk.dtos.MovieDTO;
import com.example.moviesbk.dtos.MoviePageDTO;
import com.example.moviesbk.dtos.MovieUpdateFormDTO;
import com.example.moviesbk.entities.Movie;

public interface MovieService {
	
	void saveMovie(Movie movie);
	
	boolean getNumbersOfMovie();
	
	MoviePageDTO getFilmsListForPage(int numberPage);
	
	String updateMovie(MovieUpdateFormDTO movieUpdateFormDTO);
	
	boolean searchMovieByTitle(String title);
	
	void insertMovie(MovieDTO movieDTO);
	
	String deleteMovie(int idmovie);
	
	List<MovieDTO> getFavouritesMovies(int iduser);
	
}
