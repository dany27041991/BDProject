package com.example.moviesbk.interfaces;

import com.example.moviesbk.dtos.MoviePageDTO;
import com.example.moviesbk.dtos.MovieUpdateFormDTO;
import com.example.moviesbk.entities.Movie;

public interface MovieService {
	
	void saveMovie(Movie movie);
	
	boolean getNumbersOfMovie();
	
	MoviePageDTO getFilmsListForPage(int numberPage);
	
	String updateMovie(MovieUpdateFormDTO movieUpdateFormDTO);
	
	boolean searchMovieByTitle(String title);
	
}
