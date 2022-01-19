package com.example.demo.insurance;


import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.database.IInsuranceRepository;

@RestController
public class InsuranceController {

private final IInsuranceRepository repository;
	
	public InsuranceController(IInsuranceRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/insurances")
	public List<Insurance> getAll() {
		return repository.findAll();
	}

	@PostMapping("/insurances")
	public Insurance createInsurance(@RequestBody Insurance insurance) {
		return repository.save(insurance);
	}

	@GetMapping("/insurances/{id}")
	public Insurance getInsurance(@PathVariable("id") Long id) {
		return repository.findById(id).orElseThrow(() -> new RuntimeException("The insurance cannot be found"));
	}

	@PutMapping("/insurances/{id}")
	public Insurance updateInsurance(@RequestBody Insurance insurance, @PathVariable("id") Long id) {
		return repository.findById(id)
				.map(insuranceToBeReplaced -> {
					insuranceToBeReplaced.setCar(insurance.getCar());
					return repository.save(insuranceToBeReplaced);
				}).orElseGet(() -> {
					insurance.setId(id);
			        return repository.save(insurance);
			      });
	}

	@DeleteMapping("/insurances/{id}")
	public void deleteInsurance(@PathVariable("id") Long id) {
		repository.deleteById(id);
	}
	
}
