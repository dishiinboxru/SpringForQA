package net.javaguides.springmvc.controller.mvc;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.javaguides.springmvc.entity.Contact;
import net.javaguides.springmvc.exception.ResourceNotFoundException;
import net.javaguides.springmvc.service.ContactService;

@Controller("mvc")
@RequestMapping("/contact")
public class ContactController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ContactController.class);

	@Autowired
	private ContactService contactService;
	
	@GetMapping("/list")
	public String listContacts(Model theModel) {
		List<Contact> theContacts = contactService.getContacts();
		theModel.addAttribute("contacts", theContacts);
		return "list-contacts";
	}
	
	@GetMapping("/showForm")
	public String showFormForAdd(Model theModel) {
		LOG.debug("inside show contact-form handler method");
		Contact theContact = new Contact();
		theModel.addAttribute("contact", theContact);
		return "contact-form";
	}
	
	@PostMapping("/saveContact")
	public String saveContact(@ModelAttribute("contact") Contact theContact) {
		contactService.saveContact(theContact);
		return "redirect:/contact/list";
	}
	
	@GetMapping("/updateForm")
	public String showFormForUpdate(@RequestParam("contactId") int theId, Model theModel) throws ResourceNotFoundException {
		Contact theContact = contactService.getContact(theId);
		theModel.addAttribute("contact", theContact);
		return "contact-form";
	}
	
	@GetMapping("/delete")
	public String deleteContact(@RequestParam("contactId") int theId) throws ResourceNotFoundException {
		contactService.deleteContact(theId);
		return "redirect:/contact/list";
	}
}
