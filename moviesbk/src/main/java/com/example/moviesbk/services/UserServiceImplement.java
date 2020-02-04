package com.example.moviesbk.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.moviesbk.daos.Add_FavouriteDao;
import com.example.moviesbk.daos.MovieDao;
import com.example.moviesbk.daos.UserDao;
import com.example.moviesbk.dtos.FavouriteFormDTO;
import com.example.moviesbk.entities.Movie;
import com.example.moviesbk.interfaces.UserService;

@Service
public class UserServiceImplement implements UserService{

	@Autowired
    Add_FavouriteDao add_FavouriteDao;
	
	@Override
	public void insertToFavourite(FavouriteFormDTO favouriteFormDTO) {
		add_FavouriteDao.insertToFavourite(favouriteFormDTO.getIduser(), favouriteFormDTO.getIdmovie());
	}

}
