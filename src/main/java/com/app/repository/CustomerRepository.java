package com.app.repository;

import java.util.List;

import com.app.model.Customer;

public interface CustomerRepository {

	public boolean customerSaveOrUpdate(Customer customer);

	public List<Customer> getCustomerList();

	public Customer getById(int id);

	public boolean deleteById(int id);

}
