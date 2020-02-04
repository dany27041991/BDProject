package com.example.moviesbk.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.moviesbk.entities.AddFavourite;

public interface Add_FavouriteDao extends JpaRepository<AddFavourite, Integer>{
	
	@Modifying
    @Transactional
    @Query(value = "INSERT INTO add_favourite (iduser_fk, idmovie_fk) VALUES (?1, ?2)", nativeQuery = true)
    void insertToFavourite(int iduser_fk, int idmovie_fk);
}
