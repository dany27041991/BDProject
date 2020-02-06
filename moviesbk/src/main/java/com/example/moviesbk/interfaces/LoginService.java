package com.example.moviesbk.interfaces;

import com.example.moviesbk.exceptions.UserNotExist;
import com.example.moviesbk.exceptions.UserNotLoggedException;

public interface LoginService {

    Object getUserFromDbAndVerifyPassword(String email, String password) throws UserNotLoggedException, UserNotExist;

}
