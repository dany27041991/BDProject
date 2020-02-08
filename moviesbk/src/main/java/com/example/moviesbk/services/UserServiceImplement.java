package com.example.moviesbk.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.moviesbk.daos.AddFavouriteDao;
import com.example.moviesbk.daos.AddRatingDao;
import com.example.moviesbk.dtos.FavouriteFormDTO;
import com.example.moviesbk.dtos.RatingFormDTO;
import com.example.moviesbk.interfaces.UserService;

@Service
public class UserServiceImplement implements UserService{

	@Autowired
    AddFavouriteDao addFavouriteDao;
	
	@Autowired
	AddRatingDao addRatingDao; 
	
	@Override
	public String insertToFavourite(FavouriteFormDTO favouriteFormDTO) {
		if(addFavouriteDao.checkIfIsFavourite(favouriteFormDTO.getIduser(), favouriteFormDTO.getIdmovie())>0) {
			addFavouriteDao.deleteFavourite(favouriteFormDTO.getIduser(), favouriteFormDTO.getIdmovie());
			return "Removed from favourite";
		} else {
			addFavouriteDao.insertToFavourite(favouriteFormDTO.getIduser(), favouriteFormDTO.getIdmovie());
			return "Added to favourite";
		}
	}

	@Override
	public String insertRating(RatingFormDTO ratingFormDTO) {
		if(addRatingDao.checkIfIsRating(ratingFormDTO.getIduser(), ratingFormDTO.getIdmovie())>0) {
			if(addRatingDao.checkIfIsRatingDifferent(ratingFormDTO.getIduser(), ratingFormDTO.getIdmovie(), ratingFormDTO.getRating())<1) {
				addRatingDao.updateRate(ratingFormDTO.getIduser(), ratingFormDTO.getIdmovie(), ratingFormDTO.getRating());
				return "Updated rate";
			}else {
				return "Existing rate";
			}
			
		} else {
			addRatingDao.insertRating(ratingFormDTO.getIduser(), ratingFormDTO.getIdmovie(), ratingFormDTO.getRating());
			return "Added rate";
		}
	}

	@Override
	public String removeFromFavourite(FavouriteFormDTO favouriteFormDTO) {
		addFavouriteDao.deleteFavourite(favouriteFormDTO.getIduser(), favouriteFormDTO.getIdmovie());
		return "Removed from favourite!";
	}

}
