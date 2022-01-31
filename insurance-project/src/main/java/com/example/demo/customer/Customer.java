package com.example.demo.customer;

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

import com.example.demo.insurance.Insurance;


/**
 * POJO that represents the state of an User in our system
 * @author EvGeorgiou
 *
 */
@Entity
@Table(name = "customers")
public class Customer {

	@Id @GeneratedValue @Column(name = "customers_id")
	private  Long id;
	@Column(name = "customers_firstName")
	private String firstName;
	@Column(name = "customers_lastName")
	private String lastName;
	@Column(name = "customers_age")
	private int age;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "customer_insurance_id", nullable = true)
	private Insurance insurance;

	
	
	public Customer(String firstName, String lastName, int age, Insurance insurance) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.insurance = insurance;
	}

	public Customer() {
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Insurance getInsurance() {
		return insurance;
	}

	public void setInsurance(Insurance insurance) {
		this.insurance = insurance;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37)
				.append(getId())
				.append(getFirstName())
				.append(getLastName())
				.append(getAge())
				.append(getInsurance())
				.build();
	}

	@Override
	public boolean equals(Object obj) {
		
		if (obj == null ) {
			return false;
		}
		
		if (!(obj instanceof Customer)) {
			return false;
		}
		
		Customer user = (Customer) obj;
		
		return new EqualsBuilder()
				.append(this.getId(), user.getId())
				.append(this.getFirstName() , user.getFirstName())
				.append(this.getLastName(), user.getLastName())
				.append(this.getAge(), user.getAge())
				.append(this.getInsurance(), user.getInsurance())
				.build();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("id",this.getId())
				.append("first name", this.getFirstName())
				.append("last name", this.getLastName())
				.append("age", this.getAge())
				.append("insurance", this.getInsurance())
				.build();
	}
	
	

}
