package com.example.moviesbk.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.moviesbk.entities.AddFavourite;

public interface AddFavouriteDao extends JpaRepository<AddFavourite, Integer>{
	
	@Modifying
    @Transactional
    @Query(value = "INSERT INTO add_favourite (iduser_fk, idmovie_fk) VALUES (?1, ?2)", nativeQuery = true)
    void insertToFavourite(int iduser_fk, int idmovie_fk);
	
	@Query(value = "SELECT COUNT(*) FROM add_favourite WHERE iduser_fk = ?1 AND idmovie_fk = ?2", nativeQuery = true)
    int checkIfIsFavourite(int iduser_fk, int idmovie_fk);
	
	@Modifying
    @Transactional
    @Query(value = "DELETE FROM add_favourite WHERE iduser_fk = ?1 AND idmovie_fk = ?2", nativeQuery = true)
    void deleteFavourite(int iduser_fk, int idmovie_fk);
}
