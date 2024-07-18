package com.example.ContactList.repository;

import com.example.ContactList.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Long> {

}

