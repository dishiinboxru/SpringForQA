package net.javaguides.springmvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springmvc.entity.Contact;

import java.util.Optional;

@Repository("contactRepository")
public interface ContactRepository extends JpaRepository<Contact, Integer> {
    Optional<Contact> findByName(String name);

    void deleteByName(String name);
}
