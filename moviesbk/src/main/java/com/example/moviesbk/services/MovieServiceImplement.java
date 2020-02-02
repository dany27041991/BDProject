package com.example.moviesbk.services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.Forbidden;

import com.example.moviesbk.daos.MovieDao;
import com.example.moviesbk.entities.Movie;
import com.example.moviesbk.interfaces.MovieService;

@Service
public class MovieServiceImplement implements MovieService {
	
	@Autowired
	MovieDao movieDao;

	@Override
	public void saveMovie(Movie movie) {
		movieDao.addMovie(movie.getTitle(), movie.getYear(), movie.getReleased(), movie.getRuntime(), movie.getGenre(), 
			movie.getDirector(), movie.getWriter(), movie.getActors(), movie.getPlot(), movie.getLanguage(),
			movie.getCountry(), movie.getAdwards(), movie.getPoster(), movie.getDvd(), movie.getProduction());
	}
	
	@Override
	public boolean getNumbersOfMovie() {
		int numbers = movieDao.getAllMovies();
		if(numbers>0){
            return true;
        }
		return false;
	}

	@Override
	public Page<Movie> getFilmsListForPage(int numberPage) {
		Pageable page = PageRequest.of(numberPage, 20);
		return movieDao.findAll(page);
	}
}
