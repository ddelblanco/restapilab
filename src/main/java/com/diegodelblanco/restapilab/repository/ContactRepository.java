package com.diegodelblanco.restapilab.repository;

import com.diegodelblanco.restapilab.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {}
