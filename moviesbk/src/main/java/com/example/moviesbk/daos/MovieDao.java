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
	
	@Query(value = "SELECT * FROM movie WHERE idmovie = ?1", nativeQuery = true)
    Movie getMovie(int idmovie);
	
	@Modifying
    @Transactional
    @Query(value = "UPDATE movie SET country = ?2, production = ?3, plot = ?4, adwards = ?5, dvd = ?6, released = ?7, year = ?8, runtime = ?9 WHERE idmovie = ?1", nativeQuery = true)
    void updateMovie(Integer idmovie, String country, String production, String plot, String adwards, Date dvd, Date released, Integer year, String runtime);
	
	@Query(value = "SELECT COUNT(*) FROM movie WHERE UPPER(title) LIKE UPPER(?1)", nativeQuery = true)
    int searchMovieByTitle(String title);
	
	@Modifying
    @Transactional
    @Query(value = "DELETE FROM movie WHERE idmovie = ?1", nativeQuery = true)
    void deleteMovie(Integer idmovie);
	
	@Query(value = "SELECT * FROM movie, add_favourite WHERE movie.idmovie = add_favourite.idmovie_fk AND add_favourite.iduser_fk = ?1", nativeQuery = true)
    List<Movie> getFavoritesMovies(int iduser);
	
}