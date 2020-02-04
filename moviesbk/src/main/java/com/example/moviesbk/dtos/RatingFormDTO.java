package com.example.moviesbk.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
public class RatingFormDTO {

	@Getter @Setter
    private int iduser;

    @Getter @Setter
    private int idmovie;
    
    @Getter @Setter
    private int rating;
	
}
