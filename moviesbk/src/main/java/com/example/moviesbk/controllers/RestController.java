package com.example.moviesbk.controllers;

import com.example.moviesbk.dtos.ContactFormDTO;
import com.example.moviesbk.dtos.LoginFormDTO;
import com.example.moviesbk.dtos.RegistrationFormDTO;
import com.example.moviesbk.entities.User;
import com.example.moviesbk.exceptions.UserNotExist;
import com.example.moviesbk.interfaces.ContactService;
import com.example.moviesbk.interfaces.LoginService;
import com.example.moviesbk.interfaces.MovieService;
import com.example.moviesbk.interfaces.RegistrationService;
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
    ContactService contactService;
    
    @Autowired
    MovieService movieService;

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

    @RequestMapping(value = "/contacts/{id}", method = RequestMethod.GET)
    public ResponseEntity<JsonResponseBody> getContacts(@PathVariable(name = "id") Integer id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), contactService.getAllContactsByIdUser(id)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "Problems connecting to the database, try again later!"));
        }
    }

    @RequestMapping(value = "/contacts/{id}/delete/{idcontact}", method = RequestMethod.GET)
    public ResponseEntity<JsonResponseBody> deleteContact(@PathVariable(name = "id") Integer id, @PathVariable(name = "idcontact") Integer idcontact) {
        try {
            contactService.deleteContactByIdcontact(id, idcontact);
            return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), contactService.getAllContactsByIdUser(id)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "Problems connecting to the database, try again later!"));
        }
    }

    @RequestMapping(value = "/contacts/addcontact", method = RequestMethod.PUT)
    public ResponseEntity<JsonResponseBody> addContact(@Valid @RequestBody ContactFormDTO contactFormDTO) {
        try {
            contactService.insertContact(contactFormDTO.getName(), contactFormDTO.getSurname(), contactFormDTO.getHousenumber(), contactFormDTO.getCellnumber(), contactFormDTO.getAddress(), contactFormDTO.getDetail(), contactFormDTO.getIduser());
            return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), contactService.getAllContactsByIdUser(contactFormDTO.getIduser())));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "Problems connecting to the database, try again later!"));
        }
    }

    @RequestMapping(value = "/contacts/update", method = RequestMethod.PUT)
    public ResponseEntity<JsonResponseBody> updateContact(@Valid @RequestBody ContactFormDTO contactFormDTO) {
        try {
            contactService.updateContact(contactFormDTO.getIdcontact(), contactFormDTO.getName(), contactFormDTO.getSurname(), contactFormDTO.getHousenumber(), contactFormDTO.getCellnumber(), contactFormDTO.getAddress(), contactFormDTO.getDetail());
            return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), contactService.getAllContactsByIdUser(contactFormDTO.getIduser())));
        } catch (Exception e) {
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

}
