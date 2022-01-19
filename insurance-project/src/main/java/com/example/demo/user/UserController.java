package com.example.demo.user;

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
public class UserController {

	private final IUserRepository repository;
	
	public UserController(IUserRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/users")
	public List<User> getAll() {
		return repository.findAll();
	}

	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		return repository.save(user);
	}

	@GetMapping("/users/{id}")
	public User getUser(@PathVariable("id") Long id) {
		return repository.findById(id).orElseThrow(() -> new RuntimeException("The user cannot be found"));
	}

	@PutMapping("/users/{id}")
	public User updateUser(@RequestBody User user, @PathVariable("id") Long id) {
		return repository.findById(id)
				.map(userToBeReplaced -> {
					userToBeReplaced.setFirstName(user.getFirstName());
					userToBeReplaced.setLastName(user.getLastName());
					userToBeReplaced.setAge(user.getAge());
					userToBeReplaced.setInsurance(user.getInsurance());
					return repository.save(userToBeReplaced);
				})
				.orElseGet(() -> {
					user.setId(id);
			        return repository.save(user);
			      });
	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable("id") Long id) {
		repository.deleteById(id);
	}

}
