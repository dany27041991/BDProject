package com.example.moviesbk.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.moviesbk.daos.AddFavouriteDao;
import com.example.moviesbk.dtos.FavouriteFormDTO;
import com.example.moviesbk.interfaces.UserService;

@Service
public class UserServiceImplement implements UserService{

	@Autowired
    AddFavouriteDao addFavouriteDao;
	
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

}
