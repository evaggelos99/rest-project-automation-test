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

	public CustomerController(final IUserRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/customers")
	public List<Customer> getAll() {
		return this.repository.findAll();
	}

	@PostMapping("/customers")
	public Customer createCustomer(@RequestBody final Customer user) {
		return this.repository.save(user);
	}

	@GetMapping("/customers/{id}")
	public Customer getCustomer(@PathVariable("id") final Long id) {
		return this.repository.findById(id).orElseThrow(() -> new RuntimeException("The user cannot be found"));
	}

	@PutMapping("/customers/{id}")
	public Customer updateCustomer(@RequestBody final Customer user, @PathVariable("id") final Long id) {
		return this.repository.findById(id).map(userToBeReplaced -> {
			userToBeReplaced.setFirstName(user.getFirstName());
			userToBeReplaced.setLastName(user.getLastName());
			userToBeReplaced.setAge(user.getAge());
			userToBeReplaced.setInsurance(user.getInsurance());
			return this.repository.save(userToBeReplaced);
		}).orElseGet(() -> {
			user.setId(id);
			return this.repository.save(user);
		});
	}

	@DeleteMapping("/customers/{id}")
	public void deleteCustomer(@PathVariable("id") final Long id) {
		this.repository.deleteById(id);
	}

}
