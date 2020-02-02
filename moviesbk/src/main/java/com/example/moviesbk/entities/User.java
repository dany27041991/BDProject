package com.example.moviesbk.entities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user")
@AllArgsConstructor @NoArgsConstructor
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
    @Id
    @Column(name = "iduser")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    private int iduser;

    @Column(name = "name")
    @Getter @Setter
    @NotNull @NotEmpty @NotBlank
    private String name;

    @Column(name = "lastname")
    @Getter @Setter
    @NotNull @NotEmpty @NotBlank
    private String lastname;

    @Column(name = "email")
    @Getter @Setter
    @Email
    @NotNull @NotEmpty @NotBlank
    private String email;

    @Column(name = "password")
    @Getter @Setter
    @NotNull @NotEmpty @NotBlank
    private String password;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
      name = "add_favorite", 
      joinColumns = @JoinColumn(name = "iduser"), 
      inverseJoinColumns = @JoinColumn(name = "idmovie"))
    @Getter @Setter
    Set<Movie> likedMovies;
    
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Getter @Setter
    Set<AddRating> ratings;

    public User(String name, String lastname, String email, String password) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }
}
