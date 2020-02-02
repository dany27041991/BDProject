package com.example.moviesbk.interfaces;

import com.example.moviesbk.entities.User;
import com.example.moviesbk.exceptions.UserNotExist;
import com.example.moviesbk.exceptions.UserNotLoggedException;

import java.util.Optional;

public interface LoginService {

    Object getUserFromDbAndVerifyPassword(String email, String password) throws UserNotLoggedException, UserNotExist;

}
