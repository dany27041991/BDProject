package com.example.moviesbk.services;

import com.example.moviesbk.daos.UserDao;
import com.example.moviesbk.entities.User;
import com.example.moviesbk.interfaces.RegistrationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceImplement implements RegistrationService {

    @Autowired
    UserDao userDao;

    @Override
    public boolean controlIfUserExistByEmail(String email) {
        if(userDao.countByEmail(email)>0){
            return false;
        }
        return true;
    }

    @Override
    public void saveUser(String name, String lastname, String email, String password) {
        userDao.save(new User(name, lastname, email, password));
    }
}
