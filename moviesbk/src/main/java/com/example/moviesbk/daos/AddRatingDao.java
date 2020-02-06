package com.example.moviesbk.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.moviesbk.entities.AddRating;

public interface AddRatingDao extends JpaRepository<AddRating, Integer>{
	
	@Modifying
    @Transactional
    @Query(value = "INSERT INTO add_rating (iduser_fk, idmovie_fk, rating) VALUES (?1, ?2, ?3)", nativeQuery = true)
    void insertRating(int iduser_fk, int idmovie_fk, int rating);

	@Query(value = "SELECT COUNT(*) FROM add_rating WHERE iduser_fk = ?1 AND idmovie_fk = ?2", nativeQuery = true)
    int checkIfIsRating(int iduser_fk, int idmovie_fk);
	
	@Query(value = "SELECT COUNT(*) FROM add_rating WHERE iduser_fk = ?1 AND idmovie_fk = ?2 AND rating = ?3", nativeQuery = true)
    int checkIfIsRatingDifferent(int iduser_fk, int idmovie_fk, int rating);
	
	@Modifying
    @Transactional
    @Query(value = "UPDATE add_rating SET rating = ?3 WHERE iduser_fk = ?1 AND idmovie_fk = ?2", nativeQuery = true)
    void updateRate(int iduser_fk, int idmovie_fk, int rating);
	
}
