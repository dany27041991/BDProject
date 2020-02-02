package com.example.moviesbk.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
public class ContactFormDTO {

    @Getter @Setter
    private Integer idcontact;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String surname;

    @Getter @Setter
    private String housenumber;

    @Getter @Setter
    private String cellnumber;

    @Getter @Setter
    private String address;

    @Getter @Setter
    private String detail;

    @Getter @Setter
    private Integer iduser;
}
