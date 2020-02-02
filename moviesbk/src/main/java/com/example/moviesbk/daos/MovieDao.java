package com.example.moviesbk.daos;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.moviesbk.entities.Movie;

public interface MovieDao extends JpaRepository<Movie, Integer>{
	
	@Modifying
    @Transactional
    @Query(value = "INSERT INTO Movie (title, year, released, runtime, genre, director, writer, actors, plot, language,"
    		+ "country, adwards, poster, dvd, production) VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11, ?12, "
    		+ "?13, ?14, ?15)", nativeQuery = true)
    void addMovie(String title, int year, Date released, String runtime, String genre, 
			 String director, String writer, String actors, String plot, String language,
			 String country, String adwards, String poster, Date dvd, String production);
	
	Page<Movie> findAll(Pageable page);
	
	@Query(value = "SELECT COUNT(*) FROM movie ORDER BY idmovie DESC", nativeQuery = true)
    int getAllMovies();
	
}