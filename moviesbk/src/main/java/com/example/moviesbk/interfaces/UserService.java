package com.example.moviesbk.interfaces;

import com.example.moviesbk.dtos.FavouriteFormDTO;
import com.example.moviesbk.dtos.RatingFormDTO;

public interface UserService {
	
	String insertToFavourite(FavouriteFormDTO favouriteFormDTO);
	
	String insertRating(RatingFormDTO ratingFormDTO);
	
	String removeFromFavourite(FavouriteFormDTO favouriteFormDTO);
	
}
