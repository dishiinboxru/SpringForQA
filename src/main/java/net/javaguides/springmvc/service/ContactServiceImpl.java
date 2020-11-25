package net.javaguides.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.javaguides.springmvc.entity.Contact;
import net.javaguides.springmvc.exception.ResourceNotFoundException;
import net.javaguides.springmvc.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository contactRepository;
	
	@Override
	@Transactional
	public List<Contact> getContacts() {
		return contactRepository.findAll();
	}

	@Override
	@Transactional
	public Contact saveContact(Contact theContact) {
		return contactRepository.save(theContact);
	}

	@Override
	@Transactional
	public Contact getContact(int id) throws ResourceNotFoundException {
		return contactRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(id));
	}

	@Transactional
	public Contact getContactByName(String name) throws ResourceNotFoundException {
		return contactRepository.findByName(name).orElseThrow(
				() -> new ResourceNotFoundException(name));
	}

	@Override
	@Transactional
	public void deleteContact(int theId) {
		contactRepository.deleteById(theId);
	}

	@Override
	@Transactional
	public void deleteContactByName(String name) {
		contactRepository.deleteByName(name);
	}
}