package com.example.moviesbk.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
public class FavouriteFormDTO {

	@Getter @Setter
    private int iduser;

    @Getter @Setter
    private int idmovie;
    
}
