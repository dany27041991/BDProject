package com.example.moviesbk.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.Getter;
import lombok.Setter;

@Entity
public class AddRating {
	
	@EmbeddedId
	AddRatingKey id;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @MapsId("iduser")
    @JoinColumn(name = "iduser")
	@Getter @Setter
    User user;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @MapsId("idmovie")
    @JoinColumn(name = "idmovie")
	@Getter @Setter
    Movie movie;
 
	@Column(name = "rating")
    @Getter @Setter
    int rating;
}
