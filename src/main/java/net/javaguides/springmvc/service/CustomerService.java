package net.javaguides.springmvc.service;

import java.util.List;

import net.javaguides.springmvc.entity.Customer;
import net.javaguides.springmvc.exception.DeletionImpossibleException;
import net.javaguides.springmvc.exception.ResourceNotFoundException;

public interface CustomerService {

	List<Customer> getCustomers();

	Customer saveCustomer(Customer theCustomer);

	Customer getCustomer(int theId) throws ResourceNotFoundException;

	Customer getCustomerByName(String name) throws ResourceNotFoundException;


	void deleteCustomer(int theId) throws ResourceNotFoundException;

	void deleteCustomerByName(String name) throws ResourceNotFoundException ;


}
