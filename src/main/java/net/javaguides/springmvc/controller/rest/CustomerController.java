package net.javaguides.springmvc.controller.rest;

import net.javaguides.springmvc.entity.Customer;
import net.javaguides.springmvc.exception.DeletionImpossibleException;
import net.javaguides.springmvc.exception.ResourceNotFoundException;
import net.javaguides.springmvc.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("rest")
@RequestMapping("/api/v1")
public class CustomerController {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers") //works fine
    @ResponseBody
    public List<Customer> listCustomers() {
        LOG.debug("inside show customer-rest handler method");
        return customerService.getCustomers();
    }

    // Save
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/customers")
    @ResponseBody
    public Customer newCustomer(@RequestBody Customer theCustomer) {
        return customerService.saveCustomer(theCustomer);
    }

//    // Find - outcommented to aovid rest-ambiguous handler exception
//    @GetMapping("/customers/{id}")
//    @ResponseBody
//    public Customer findOne(@PathVariable Integer id) throws ResourceNotFoundException {
//        return customerService.getCustomer(id);
//    }

    // Find by name
    @GetMapping("/customers/{name}")
    @ResponseBody
    public Customer findOne(@PathVariable String name) throws ResourceNotFoundException {
        return customerService.getCustomerByName(name);
    }

    //todo - put mapping operation for updating by name with phone. Since it us put, not PATCH I use whole Customer, not just phone to update
    @PutMapping("/customers/{name}")
    @ResponseBody
    public Customer replaceCustomer(@RequestBody Customer newCustomer, @PathVariable String name) throws ResourceNotFoundException {

        Customer customerToReplace = customerService.getCustomerByName(name);
        customerToReplace.setName(newCustomer.getName());
        customerToReplace.setPhone(newCustomer.getPhone());

        return customerService.saveCustomer(customerToReplace);
    }

//    @PutMapping("/customers/{id}")
//    @ResponseBody
//    public Customer replaceCustomer(@RequestBody Customer newCustomer, @PathVariable Integer id) throws ResourceNotFoundException {
//
//        //oldschool approach, but I can't get .map method to work...
//        Customer customerToReplace = customerService.getCustomer(id);
//        customerToReplace.setName(newCustomer.getName());
//        // customerToReplace.setLastName(newCustomer.getLastName());
//        customerToReplace.setPhone(newCustomer.getPhone());
//
//        return customerService.saveCustomer(customerToReplace);
//
//
//    }

//    //outcommented to avoid ambugiuity for rest
//    @DeleteMapping("/customers/{id}")
//    @ResponseBody //means the response is serialized into JSON
//    public void deleteCustomer (@PathVariable Integer id) throws ResourceNotFoundException{
//        customerService.deleteCustomer(id);
//    }

    @DeleteMapping("/customers/{name}")
    @ResponseBody
    public void deleteCustomer (@PathVariable String name) throws ResourceNotFoundException {
        Customer customerCheck = customerService.getCustomerByName(name); //TODO - perhaps there is a better way to trigger exception and a text for DELETE, but I've spent too much time here already

        customerService.deleteCustomerByName(name);
    }
}