package com.example.moviesbk.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Contact")
@NoArgsConstructor @AllArgsConstructor
public class Contact {

    @Id
    @Column(name = "idcontact")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private int idcontact;

    @Column(name = "name")
    @NotBlank @NotEmpty @NotNull
    @Getter @Setter
    private String name;

    @Column(name = "surname")
    @NotBlank @NotEmpty @NotNull
    @Getter @Setter
    private String surname;

    @Column(name = "house_number")
    @NotBlank @NotEmpty @NotNull
    @Getter @Setter
    private String housenumber;

    @Column(name = "cell_number")
    @NotBlank @NotEmpty @NotNull
    @Getter @Setter
    private String cellnumber;

    @Column(name = "address")
    @NotBlank @NotEmpty @NotNull
    @Getter @Setter
    private String address;

    @Column(name = "detail")
    @Getter @Setter
    private String detail;

    @Column(name = "iduser")
    @NotNull
    @Getter @Setter
    private int iduser;
}
