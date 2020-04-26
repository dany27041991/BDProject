package com.example.moviesbk.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import org.hibernate.annotations.Check;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
public class AddRating {
	
	@EmbeddedId
	@Getter @Setter
	AddRatingKey id;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("iduser")
    @JoinColumn(name = "iduser_fk")
	@Getter @Setter
	@JsonIgnore
    User user;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("idmovie")
    @JoinColumn(name = "idmovie_fk")
	@Getter @Setter
	@JsonIgnore
    Movie movie;
 
	@Column(name = "rating")
	@Check(constraints = "rating <= 5")
    @Getter @Setter
    int rating;
}
