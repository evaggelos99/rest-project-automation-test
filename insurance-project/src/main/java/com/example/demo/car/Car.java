package com.example.demo.car;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * POJO that represents the state of a car
 * @author EvGeorgiou
 *
 */
@Entity
@Table(name = "cars")
public class Car {

	@Id @GeneratedValue @Column(name = "cars_id")
	private Long id;
	@Column(name = "cars_manufacter")
	private String manufacter;
	@Column(name = "cars_model")
	private String model;
	
	
	public Car() {}
	
	public Car(String manufacter, String model) {
		this.manufacter = manufacter;
		this.model = model;
	}
	
	public String getManufacter() {
		return manufacter;
	}
	public void setManufacter(String manufacter) {
		this.manufacter = manufacter;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(getId())
				.append(getManufacter())
				.append(getModel())
				.build();
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj == null ) {
			return false;
		}
		
		if (!(obj instanceof Car)) {
			return false;
		}
		
		Car car = (Car) obj;
		
		return new EqualsBuilder()
				.append(this.getId(), car.getId())
				.append(this.getManufacter(), car.getManufacter())
				.append(this.getModel() , car.getModel())
				.build();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("id",this.getId())
				.append("manufacter", this.getManufacter())
				.append("model", this.getModel())
				.build();
	}
	
	
}
