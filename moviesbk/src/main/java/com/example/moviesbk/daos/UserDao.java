package com.example.moviesbk.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.example.moviesbk.entities.User;
import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

    List<User> getUserByEmailAndPassword(String email, String password);

    int countByEmail(String email);
    
    @Query(value = "SELECT * FROM user WHERE iduser = ?1", nativeQuery = true)
    User getUser(int iduser);
    
    @Query(value = "SELECT * FROM user WHERE email != ?1", nativeQuery = true)
    List<User> getAllUsers(String email);
    
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM user WHERE iduser = ?1", nativeQuery = true)
    void deleteUser(Integer iduser);
    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user (email, lastname, name, password) VALUES (?1, ?2, ?3, ?4)", nativeQuery = true)
    void addSuperuser(String email, String lastname, String name, String password);

}
