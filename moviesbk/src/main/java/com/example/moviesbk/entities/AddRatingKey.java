package com.example.moviesbk.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Embeddable
public class AddRatingKey implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Column(name = "iduser")
	@Getter @Setter
    int userId;
 
    @Column(name = "idmovie")
    @Getter @Setter
    int movieId;
    
}
