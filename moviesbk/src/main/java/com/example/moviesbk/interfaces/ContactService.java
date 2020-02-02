package com.example.moviesbk.interfaces;

import java.util.List;

import com.example.moviesbk.entities.Contact;

public interface ContactService {

    List<Contact> getAllContactsByIdUser(Integer id);

    void deleteContactByIdcontact(Integer id, Integer idcontact);

    void insertContact(String name, String surname, String homenumber, String phonenumber, String address, String details, Integer iduser);

    void updateContact(Integer idcontact, String name, String surname, String housenumber, String cellnumber, String address, String details);
}
