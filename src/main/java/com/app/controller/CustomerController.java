package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Customer;
import com.app.repository.CustomerRepository;

@RestController
@RequestMapping("/api/")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;

	@PostMapping(path = "customers", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> customerRegistration(@RequestBody Customer customer) throws Exception {
		boolean result = customerRepository.customerSaveOrUpdate(customer);
		if (result) {
			return new ResponseEntity<String>("Customer saved Successfully", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Customer is not saved, try again!!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path = "customers", produces = "application/json")
	public ResponseEntity<List<Customer>> listOfCustomers() {
		return new ResponseEntity<List<Customer>>(customerRepository.getCustomerList(), HttpStatus.OK);
	}

	@GetMapping(path = "customers/{id}", produces = "application/json")
	public ResponseEntity<Customer> getById(@PathVariable("id") Integer id) throws Exception {
		Customer customer = customerRepository.getById(id);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	@DeleteMapping(path = "customers/{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id") Integer id) throws Exception {
		boolean result = customerRepository.deleteById(id);
		if (result) {
			return new ResponseEntity<String>("Customer Deleted Successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Customer is not deleted, try again!!", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
