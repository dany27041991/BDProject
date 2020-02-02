package com.example.moviesbk.services;

import com.example.moviesbk.daos.ContactDao;
import com.example.moviesbk.entities.Contact;
import com.example.moviesbk.interfaces.ContactService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImplement implements ContactService {

    @Autowired
    ContactDao contactDao;

    @Override
    public List<Contact> getAllContactsByIdUser(Integer id) {
        return contactDao.getAllContactsByIduser(id);
    }

    @Override
    public void deleteContactByIdcontact(Integer id, Integer idcontact) {
        contactDao.deleteContactByIdcontact(id, idcontact);
    }

    @Override
    public void insertContact(String name, String surname, String homenumber, String phonenumber, String address, String details, Integer iduser) {
        contactDao.addContact(name, surname, homenumber, phonenumber, address, details, iduser);
    }

    @Override
    public void updateContact(Integer idcontact, String name, String surname, String housenumber, String cellnumber, String address, String details) {
        contactDao.updateContact(idcontact, name, surname, housenumber, cellnumber, address, details);
    }


}
