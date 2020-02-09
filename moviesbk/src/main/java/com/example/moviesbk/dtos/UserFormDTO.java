package com.example.moviesbk.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
public class UserFormDTO {
	
	@Getter @Setter
	private int iduser;
	
	@Getter @Setter
    private String name;

    @Getter @Setter
    private String lastname;

    @Getter @Setter
    private String email;

}
