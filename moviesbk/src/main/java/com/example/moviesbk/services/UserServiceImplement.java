package com.example.moviesbk.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.moviesbk.daos.AddFavouriteDao;
import com.example.moviesbk.daos.AddRatingDao;
import com.example.moviesbk.daos.UserDao;
import com.example.moviesbk.dtos.FavouriteFormDTO;
import com.example.moviesbk.dtos.RatingFormDTO;
import com.example.moviesbk.dtos.UserFormDTO;
import com.example.moviesbk.entities.User;
import com.example.moviesbk.interfaces.UserService;

@Service
public class UserServiceImplement implements UserService{

	@Autowired
    AddFavouriteDao addFavouriteDao;
	
	@Autowired
	AddRatingDao addRatingDao; 
	
	@Autowired
	UserDao userDao;
	
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

	@Override
	public List<UserFormDTO> getAllUsers() {
		String superuserEmail = "superuser@gmail.com";
		List<User> users = userDao.getAllUsers(superuserEmail);
		Iterator<User> iteratorUsers = users.iterator(); 
		List<UserFormDTO> filterUserFormDTOs = new ArrayList<>();
		while (iteratorUsers.hasNext()) {
			User user = (User) iteratorUsers.next();
			
			UserFormDTO userFormDTO = new UserFormDTO(user.getIduser(), user.getName(), user.getLastname(), user.getEmail());
			filterUserFormDTOs.add(userFormDTO);
		}
		return filterUserFormDTOs;
	}

	@Override
	public String deleteUser(int iduser) {
		userDao.deleteUser(iduser);
		return "Deleted successfully!";
	}

}
