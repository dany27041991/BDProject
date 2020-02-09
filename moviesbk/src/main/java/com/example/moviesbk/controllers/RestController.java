package com.example.moviesbk.controllers;

import com.example.moviesbk.dtos.FavouriteFormDTO;
import com.example.moviesbk.dtos.LoginFormDTO;
import com.example.moviesbk.dtos.MovieDTO;
import com.example.moviesbk.dtos.MovieUpdateFormDTO;
import com.example.moviesbk.dtos.RatingFormDTO;
import com.example.moviesbk.dtos.RegistrationFormDTO;
import com.example.moviesbk.entities.User;
import com.example.moviesbk.exceptions.UserNotExist;
import com.example.moviesbk.interfaces.LoginService;
import com.example.moviesbk.interfaces.MovieService;
import com.example.moviesbk.interfaces.RegistrationService;
import com.example.moviesbk.interfaces.UserService;
import com.example.moviesbk.utils.JsonResponseBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
public class RestController {

    @Autowired
    RegistrationService registrationService;

    @Autowired
    LoginService loginService;
    
    @Autowired
    MovieService movieService;
    
    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<JsonResponseBody> loginUser(@Valid @RequestBody LoginFormDTO loginFormDTO){
        try {
            User user = (User) loginService.getUserFromDbAndVerifyPassword(loginFormDTO.getEmail(), loginFormDTO.getPassword());
            ObjectMapper mapper = new ObjectMapper();
            ObjectNode userLogged = mapper.createObjectNode();
            userLogged.put("id_user", user.getIduser());
            userLogged.put("name", user.getName());
            userLogged.put("surname", user.getLastname());
            userLogged.put("email", user.getEmail());
            return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), userLogged));
        }catch (UserNotExist e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new JsonResponseBody(HttpStatus.NOT_FOUND.value(), e.getMessage()));
        } catch (Exception e1) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "Problems connecting to the database, try again later!"));
        }
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<JsonResponseBody> signupUser(@Valid @RequestBody RegistrationFormDTO registrationFormDTO){
        try {
            if(registrationService.controlIfUserExistByEmail(registrationFormDTO.getEmail())){
                registrationService.saveUser(registrationFormDTO.getName(), registrationFormDTO.getLastname(), registrationFormDTO.getEmail(), registrationFormDTO.getPassword());
                return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), "Registration was successful. You can login!"));
            }
            else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(new JsonResponseBody(HttpStatus.CONFLICT.value(), "Existing username, register with another one!"));
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "Problems connecting to the database, try again later!"));
        }
    }
    
    @RequestMapping(value = "/movies/{page}", method = RequestMethod.GET)
    public ResponseEntity<JsonResponseBody> getMovieForPage(@PathVariable(name = "page") Integer page) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), movieService.getFilmsListForPage(page-1)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "Problems connecting to the database, try again later!"));
        }
    }
    
    @RequestMapping(value = "/movies/add-favourite", method = RequestMethod.POST)
    public ResponseEntity<JsonResponseBody> addToFavourite(@Valid @RequestBody FavouriteFormDTO favouriteFormDTO){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), userService.insertToFavourite(favouriteFormDTO)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "Problems connecting to the database, try again later!"));
        }
    }
    
    @RequestMapping(value = "/movies/add-rate", method = RequestMethod.POST)
    public ResponseEntity<JsonResponseBody> addRate(@Valid @RequestBody RatingFormDTO ratingFormDTO){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), userService.insertRating(ratingFormDTO)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "Problems connecting to the database, try again later!"));
        }
    }
    
    @RequestMapping(value = "/movies/update", method = RequestMethod.POST)
    public ResponseEntity<JsonResponseBody> updateMovie(@Valid @RequestBody MovieUpdateFormDTO movieUpdateFormDTO){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), movieService.updateMovie(movieUpdateFormDTO)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "Problems connecting to the database, try again later!"));
        }
    }
    
    @RequestMapping(value = "/movies/search/{title}", method = RequestMethod.GET)
    public ResponseEntity<JsonResponseBody> searchMovieByTitle(@PathVariable(name = "title") String title) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), movieService.searchMovieByTitle(title)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "Problems connecting to the database, try again later!"));
        }
    }
    
    @RequestMapping(value = "/movies/add", method = RequestMethod.POST)
    public ResponseEntity<JsonResponseBody> addMovie(@Valid @RequestBody String movie){
        try {
        	ObjectMapper objectMapper = new ObjectMapper();
        	MovieDTO movieDTO = objectMapper.readValue(movie, MovieDTO.class);
        	movieService.insertMovie(movieDTO);
            return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), "Added correctly!"));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "Problems connecting to the database, try again later!"));
        }
    }
    
    @RequestMapping(value = "/movies/delete-movie/{idmovie}", method = RequestMethod.GET)
    public ResponseEntity<JsonResponseBody> deleteMovie(@PathVariable(name = "idmovie") int idmovie) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), movieService.deleteMovie(idmovie)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "Problems connecting to the database, try again later!"));
        }
    }
    
    @RequestMapping(value = "/movies/favourite/{iduser}", method = RequestMethod.GET)
    public ResponseEntity<JsonResponseBody> getFavouritesMovies(@PathVariable(name = "iduser") int iduser) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), movieService.getFavouritesMovies(iduser)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "Problems connecting to the database, try again later!"));
        }
    }
    
    @RequestMapping(value = "/movies/remove-from-favourite", method = RequestMethod.POST)
    public ResponseEntity<JsonResponseBody> removeMovieFromFavourite(@Valid @RequestBody FavouriteFormDTO favouriteFormDTO){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), userService.removeFromFavourite(favouriteFormDTO)));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "Problems connecting to the database, try again later!"));
        }
    }
   
    @RequestMapping(value = "/users/all-users", method = RequestMethod.GET)
    public ResponseEntity<JsonResponseBody> getAllUsers() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), userService.getAllUsers()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "Problems connecting to the database, try again later!"));
        }
    }
    
    @RequestMapping(value = "/users/delete-user/{iduser}", method = RequestMethod.GET)
    public ResponseEntity<JsonResponseBody> deleteUser(@PathVariable(name = "iduser") int iduser) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), userService.deleteUser(iduser)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "Problems connecting to the database, try again later!"));
        }
    }

}
