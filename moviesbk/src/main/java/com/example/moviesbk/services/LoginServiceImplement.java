package com.example.moviesbk.services;

import com.example.moviesbk.daos.UserDao;
import com.example.moviesbk.entities.User;
import com.example.moviesbk.exceptions.UserNotExist;
import com.example.moviesbk.interfaces.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImplement implements LoginService{

    @Autowired
    UserDao userDao;

    @Override
    public Object getUserFromDbAndVerifyPassword(String email, String password) throws UserNotExist {
        List<User> userr = userDao.getUserByEmailAndPassword(email, password);
        if(userr.size()>0) {
            User user = userr.get(0);
            return user;
        } else {
            throw new UserNotExist("User not found in the database. Try again!");
        }
    }
}
