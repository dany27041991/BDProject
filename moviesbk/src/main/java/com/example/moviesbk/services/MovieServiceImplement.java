package com.example.moviesbk.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.moviesbk.daos.MovieDao;
import com.example.moviesbk.dtos.MovieDTO;
import com.example.moviesbk.dtos.MoviePageDTO;
import com.example.moviesbk.entities.AddFavourite;
import com.example.moviesbk.entities.AddFavouriteKey;
import com.example.moviesbk.entities.AddRating;
import com.example.moviesbk.entities.AddRatingKey;
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
	public MoviePageDTO getFilmsListForPage(int numberPage) {
		Pageable page = PageRequest.of(numberPage, 20);
		Page<Movie> pageMovie = movieDao.findAll(page);
		
		Iterator<Movie> iteratorMovies = pageMovie.getContent().iterator(); 
		List<MovieDTO> filteredMovies = new ArrayList<>();
		while (iteratorMovies.hasNext()) {
			Movie movie = (Movie) iteratorMovies.next();
			
			List<AddFavouriteKey> addFavouriteKeysFiltered = new ArrayList<>();
			Set<AddFavourite> addFavouriteSet = movie.getAddFavourites();
			Iterator<AddFavourite> iteratorAddFavourite = addFavouriteSet.iterator();
			while (iteratorAddFavourite.hasNext()) {
				AddFavourite addFavourite = iteratorAddFavourite.next();
				AddFavouriteKey addFavouriteKey = addFavourite.getId();
				addFavouriteKeysFiltered.add(addFavouriteKey);
			}
			
			List<AddRatingKey> addRatingKeysFiltered = new ArrayList<>();
			Set<AddRating> addRatingKeysSet = movie.getRatings();
			Iterator<AddRating> iteratorAddRating = addRatingKeysSet.iterator();
			while (iteratorAddRating.hasNext()) {
				AddRating addRating = iteratorAddRating.next();
				addRatingKeysFiltered.add(addRating.getId());
			}
			
			MovieDTO movieFilteredMovie = new MovieDTO(movie.getIdmovie(), movie.getTitle(), movie.getYear(), movie.getReleased(), movie.getRuntime(), 
					movie.getGenre(), movie.getDirector(), movie.getWriter(), movie.getActors(), movie.getPlot(), 
					movie.getLanguage(), movie.getCountry(), movie.getAdwards(), movie.getPoster(), movie.getDvd(), 
					movie.getProduction(), addFavouriteKeysFiltered, addRatingKeysFiltered);
			
			filteredMovies.add(movieFilteredMovie);
		}
		MoviePageDTO moviePageDTO = new MoviePageDTO(filteredMovies, pageMovie.getPageable(), pageMovie.getTotalPages(),
				pageMovie.getTotalElements(), pageMovie.getSize(), pageMovie.getNumber(), pageMovie.getNumberOfElements(),
				pageMovie.getSort());
		return moviePageDTO;
	}
}
