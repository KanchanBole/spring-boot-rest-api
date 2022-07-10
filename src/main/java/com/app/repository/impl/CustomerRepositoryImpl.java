package com.app.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.model.Customer;
import com.app.repository.CustomerRepository;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

	@Autowired
	SessionFactory sessionFactory;

	//save customer
	
	@Override
	public boolean customerSaveOrUpdate(Customer customer) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		session.saveOrUpdate(customer);
		tx.commit();
		session.close();
		return true;
	}

	//get all customer
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> getCustomerList() {
		Session session = sessionFactory.openSession();
		List<Customer> list = session.createCriteria(Customer.class).list();
		session.close();
		return list;
	}

	//get customer by id
	@Override
	public Customer getById(int id) {
		Session session = sessionFactory.openSession();
		Customer customer = (Customer) session.get(Customer.class, id);
		session.close();
		return customer;
	}

	//delete customer
	@Override
	public boolean deleteById(int id) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		Customer customer = (Customer) session.get(Customer.class, id);
		session.delete(customer);
		tx.commit();
		session.close();
		return true;
	}

}
