package com.example.moviesbk.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.moviesbk.entities.Contact;

import java.util.List;

@Repository
public interface ContactDao extends JpaRepository<Contact, Integer> {

    @Query(value = "SELECT * FROM Contact u WHERE u.iduser = ?1", nativeQuery = true)
    List<Contact> getAllContactsByIduser(Integer id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Contact WHERE (iduser = ?1 AND idcontact = ?2)", nativeQuery = true)
    void deleteContactByIdcontact(Integer id, Integer idcontact);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Contact (name, surname, house_number, cell_number, address, detail, iduser) VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7)", nativeQuery = true)
    void addContact(String name, String surname, String housenumber, String cellnumber, String address, String details, Integer iduser);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Contact SET name = ?2, surname = ?3, house_number = ?4, cell_number = ?5, address = ?6, detail = ?7 WHERE idcontact = ?1", nativeQuery = true)
    void updateContact(Integer idcontact, String name, String surname, String housenumber, String cellnumber, String address, String details);
}
