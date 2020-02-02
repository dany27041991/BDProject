package com.example.moviesbk.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.moviesbk.entities.Movie;

public interface MovieService {
	
	void saveMovie(Movie movie);
	
	boolean getNumbersOfMovie();
	
	Page<Movie> getFilmsListForPage(int numberPage);
	
}
