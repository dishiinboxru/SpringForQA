package net.javaguides.springmvc.service;

import java.util.List;

import net.javaguides.springmvc.entity.Contact;
import net.javaguides.springmvc.exception.ResourceNotFoundException;

public interface ContactService {

	List<Contact> getContacts();

	Contact saveContact(Contact theContact);

	Contact getContact(int theId) throws ResourceNotFoundException;

	Contact getContactByName(String name) throws ResourceNotFoundException;


	void deleteContact(int theId) throws ResourceNotFoundException;

	void deleteContactByName(String name) throws ResourceNotFoundException ;

}
