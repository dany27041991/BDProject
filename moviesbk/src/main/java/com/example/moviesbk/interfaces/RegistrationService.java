package com.example.moviesbk.interfaces;

public interface RegistrationService {

    boolean controlIfUserExistByEmail(String email);

    void saveUser(String name, String lastname, String email, String password);

}
