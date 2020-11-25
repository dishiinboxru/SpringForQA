package net.javaguides.springmvc.controller.rest;

import net.javaguides.springmvc.entity.Contact;
import net.javaguides.springmvc.exception.ResourceNotFoundException;
import net.javaguides.springmvc.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("rest")
@RequestMapping("/api/v1")
public class ContactController {

    private static final Logger LOG = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @GetMapping("/contacts")
    @ResponseBody
    public List<Contact> listContacts() {
        LOG.debug("inside show contact-rest handler method");
        return contactService.getContacts();
    }

    // Save
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/contacts")
    @ResponseBody
    public Contact newContact(@RequestBody Contact theContact) {
        return contactService.saveContact(theContact);
    }

    // Find by name
    @GetMapping("/contacts/{name}")
    @ResponseBody
    public Contact findOne(@PathVariable String name) throws ResourceNotFoundException {
        return contactService.getContactByName(name);
    }

    //TODO - open question - PutMapping operation for updating by name with phone. Since it us put, not PATCH I use whole Contact, not just phone to update
    @PutMapping("/contacts/{name}")
    @ResponseBody
    public Contact replaceContact(@RequestBody Contact newContact, @PathVariable String name) throws ResourceNotFoundException {

        Contact contactToReplace = contactService.getContactByName(name);
        contactToReplace.setName(newContact.getName());
        contactToReplace.setPhone(newContact.getPhone());

        return contactService.saveContact(contactToReplace);
    }

    @DeleteMapping("/contacts/{name}")
    @ResponseBody
    public void deleteContact (@PathVariable String name) throws ResourceNotFoundException {
        Contact contactCheck = contactService.getContactByName(name); //TODO - open question - perhaps there is a better way to trigger exception and a text for DELETE, but I've spent too much time here already

        contactService.deleteContactByName(name);
    }
}