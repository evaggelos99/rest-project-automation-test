package com.example.demo.customer;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.database.IUserRepository;

@RestController
public class CustomerController {

	private final IUserRepository repository;
	
	public CustomerController(IUserRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/customers")
	public List<Customer> getAll() {
		return repository.findAll();
	}

	@PostMapping("/customers")
	public Customer createCustomer(@RequestBody Customer customer) {
		return repository.save(customer);
	}

	@GetMapping("/customers/{id}")
	public Customer getCustomer(@PathVariable("id") Long id) {
		return repository.findById(id).orElseThrow(() -> new RuntimeException("The user cannot be found"));
	}

	@PutMapping("/customers/{id}")
	public Customer updateCustomer(@RequestBody Customer customer, @PathVariable("id") Long id) {
		return repository.findById(id)
				.map(customerToBeReplaced -> {
					customerToBeReplaced.setFirstName(customer.getFirstName());
					customerToBeReplaced.setLastName(customer.getLastName());
					customerToBeReplaced.setAge(customer.getAge());
					customerToBeReplaced.setInsurance(customer.getInsurance());
					return repository.save(customerToBeReplaced);
				})
				.orElseGet(() -> {
					customer.setId(id);
			        return repository.save(customer);
			      });
	}

	@DeleteMapping("/users/{id}")
	public void deleteCustomer(@PathVariable("id") Long id) {
		repository.deleteById(id);
	}

}
