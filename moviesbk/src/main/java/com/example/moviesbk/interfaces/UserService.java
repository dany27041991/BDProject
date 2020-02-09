package com.example.moviesbk.interfaces;

import java.util.List;

import com.example.moviesbk.dtos.FavouriteFormDTO;
import com.example.moviesbk.dtos.RatingFormDTO;
import com.example.moviesbk.dtos.UserFormDTO;

public interface UserService {
	
	String insertToFavourite(FavouriteFormDTO favouriteFormDTO);
	
	String insertRating(RatingFormDTO ratingFormDTO);
	
	String removeFromFavourite(FavouriteFormDTO favouriteFormDTO);
	
	List<UserFormDTO> getAllUsers();
	
	String deleteUser(int iduser);
	
}
