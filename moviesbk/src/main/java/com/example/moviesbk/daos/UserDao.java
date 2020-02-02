package com.example.moviesbk.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.moviesbk.entities.User;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    List<User> getUserByEmailAndPassword(String email, String password);

    int countByEmail(String email);

}
