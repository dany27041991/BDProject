package com.example.moviesbk.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
public class LoginFormDTO {

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String password;

}
