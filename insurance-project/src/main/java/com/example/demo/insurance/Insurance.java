package com.example.demo.insurance;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.example.demo.car.Car;

/**
 * POJO that represents the state of an Insurance in our system
 * @author EvGeorgiou
 *
 */
@Entity
@Table(name = "insurances")
public class Insurance {
	
	@Id @GeneratedValue @Column(name = "insurances_id")
	private Long id;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "insurance_car_id")
	private Car car;
	
	public Insurance() {}
	
	
	
	public Insurance(Car car) {
		this.car = car;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(getId())
				.append(getCar())
				.build();
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj == null ) {
			return false;
		}
		
		if (!(obj instanceof Insurance)) {
			return false;
		}
		
		Insurance insurance = (Insurance) obj;
		
		return new EqualsBuilder()
				.append(this.getId(), insurance.getId())
				.append(this.getCar() , insurance.getCar())
				.build();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("id",this.getId())
				.append("car", this.getCar())
				.build();
	}
	
}
