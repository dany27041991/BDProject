package com.example.moviesbk.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.moviesbk.daos.MovieDao;
import com.example.moviesbk.dtos.FavouriteFormDTO;
import com.example.moviesbk.dtos.MovieDTO;
import com.example.moviesbk.dtos.MoviePageDTO;
import com.example.moviesbk.dtos.MovieUpdateFormDTO;
import com.example.moviesbk.dtos.RatingFormDTO;
import com.example.moviesbk.entities.AddFavourite;
import com.example.moviesbk.entities.AddFavouriteKey;
import com.example.moviesbk.entities.AddRating;
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
		Pageable page = PageRequest.of(numberPage, 20, Sort.by("idmovie").descending());
		Page<Movie> pageMovie = movieDao.findAll(page);
		
		Iterator<Movie> iteratorMovies = pageMovie.getContent().iterator(); 
		List<MovieDTO> filteredMovies = new ArrayList<>();
		while (iteratorMovies.hasNext()) {
			Movie movie = (Movie) iteratorMovies.next();
			
			List<FavouriteFormDTO> addFavouriteKeysFiltered = new ArrayList<>();
			Set<AddFavourite> addFavouriteSet = movie.getAddFavourites();
			Iterator<AddFavourite> iteratorAddFavourite = addFavouriteSet.iterator();
			while (iteratorAddFavourite.hasNext()) {
				AddFavourite addFavourite = iteratorAddFavourite.next();
				AddFavouriteKey addFavouriteKey = addFavourite.getId();
				addFavouriteKeysFiltered.add(new FavouriteFormDTO(addFavouriteKey.getUserId(), addFavouriteKey.getMovieId()));
			}
			
			List<RatingFormDTO> addRatingKeysFiltered = new ArrayList<>();
			Set<AddRating> addRatingKeysSet = movie.getRatings();
			Iterator<AddRating> iteratorAddRating = addRatingKeysSet.iterator();
			while (iteratorAddRating.hasNext()) {
				AddRating addRating = iteratorAddRating.next();
				addRatingKeysFiltered.add(new RatingFormDTO(addRating.getId().getUserId(), addRating.getId().getMovieId(), addRating.getRating()));
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

	@Override
	public String updateMovie(MovieUpdateFormDTO movieUpdateFormDTO) {
		movieDao.updateMovie(movieUpdateFormDTO.getIdmovie(), movieUpdateFormDTO.getCountry(), movieUpdateFormDTO.getProduction(), 
				movieUpdateFormDTO.getPlot(), movieUpdateFormDTO.getAdwards(), movieUpdateFormDTO.getDvd(), movieUpdateFormDTO.getReleased(), 
				movieUpdateFormDTO.getYear(), movieUpdateFormDTO.getRuntime());
		return "Movie Updated";
	}

	@Override
	public boolean searchMovieByTitle(String title) {
		int count = movieDao.searchMovieByTitle(title.trim());
		if(count>0) {
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public void insertMovie(MovieDTO movieDTO) {
		movieDao.addMovie(movieDTO.getTitle(), movieDTO.getYear(), movieDTO.getReleased(), movieDTO.getRuntime(), 
				movieDTO.getGenre(), movieDTO.getDirector(), movieDTO.getWriter(), movieDTO.getActors(), movieDTO.getPlot(),
				movieDTO.getLanguage(), movieDTO.getCountry(), movieDTO.getAdwards(), movieDTO.getPoster(), movieDTO.getDvd(),
				movieDTO.getProduction());
	}

	@Override
	public String deleteMovie(int idmovie) {
		movieDao.deleteMovie(idmovie);
		return "Deleted successfully!";
	}

	@Override
	public List<MovieDTO> getFavouritesMovies(int iduser) {
		List<Movie> moviesFavouriteList = movieDao.getFavoritesMovies(iduser);
		List<MovieDTO> moviesFavouriteFiltered = new ArrayList<>();
		
		Iterator<Movie> iteratorMovies = moviesFavouriteList.iterator();
		while (iteratorMovies.hasNext()) {
			Movie movie = (Movie) iteratorMovies.next();
			
			List<FavouriteFormDTO> addFavouriteKeysFiltered = new ArrayList<>();
			Set<AddFavourite> addFavouriteSet = movie.getAddFavourites();
			Iterator<AddFavourite> iteratorAddFavourite = addFavouriteSet.iterator();
			while (iteratorAddFavourite.hasNext()) {
				AddFavourite addFavourite = iteratorAddFavourite.next();
				AddFavouriteKey addFavouriteKey = addFavourite.getId();
				addFavouriteKeysFiltered.add(new FavouriteFormDTO(addFavouriteKey.getUserId(), addFavouriteKey.getMovieId()));
			}
			
			List<RatingFormDTO> addRatingKeysFiltered = new ArrayList<>();
			Set<AddRating> addRatingKeysSet = movie.getRatings();
			Iterator<AddRating> iteratorAddRating = addRatingKeysSet.iterator();
			while (iteratorAddRating.hasNext()) {
				AddRating addRating = iteratorAddRating.next();
				addRatingKeysFiltered.add(new RatingFormDTO(addRating.getId().getUserId(), addRating.getId().getMovieId(), addRating.getRating()));
			}
			
			MovieDTO movieFilteredMovie = new MovieDTO(movie.getIdmovie(), movie.getTitle(), movie.getYear(), movie.getReleased(), movie.getRuntime(), 
					movie.getGenre(), movie.getDirector(), movie.getWriter(), movie.getActors(), movie.getPlot(), 
					movie.getLanguage(), movie.getCountry(), movie.getAdwards(), movie.getPoster(), movie.getDvd(), 
					movie.getProduction(), addFavouriteKeysFiltered, addRatingKeysFiltered);
			
			moviesFavouriteFiltered.add(movieFilteredMovie);
		}
		return moviesFavouriteFiltered;
	}
}
