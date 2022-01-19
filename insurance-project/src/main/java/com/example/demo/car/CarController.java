package com.example.demo.car;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.database.ICarRepository;

@RestController
public class CarController {
	
private final ICarRepository repository;
	
	public CarController(ICarRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/cars")
	public List<Car> getAll() {
		return repository.findAll();
	}

	@PostMapping("/cars")
	public Car createInsurance(@RequestBody Car car) {
		return repository.save(car);
	}

	@GetMapping("/cars/{id}")
	public Car getInsurance(@PathVariable("id") Long id) {
		return repository.findById(id).orElseThrow(() -> new RuntimeException("The car cannot be found"));
	}

	@PutMapping("/cars/{id}")
	public Car updateInsurance(@RequestBody Car car, @PathVariable("id") Long id) {
		return repository.findById(id)
				.map(carToBeReplaced -> {
					carToBeReplaced.setManufacter(car.getManufacter());
					carToBeReplaced.setModel(car.getModel());
					return repository.save(carToBeReplaced);
				}).orElseGet(() -> {
					car.setId(id);
			        return repository.save(car);
			      });
	}

	@DeleteMapping("/cars/{id}")
	public void deleteInsurance(@PathVariable("id") Long id) {
		repository.deleteById(id);
	}
}
