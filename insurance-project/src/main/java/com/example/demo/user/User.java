package com.example.demo.user;

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
@Table(name = "users")
public class User {

	@Id @GeneratedValue @Column(name = "users_id")
	private  Long id;
	@Column(name = "users_firstName")
	private String firstName;
	@Column(name = "users_lastName")
	private String lastName;
	@Column(name = "users_age")
	private int age;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_insurance_id", nullable = true)
	private Insurance insurance;

	
	
	public User(String firstName, String lastName, int age, Insurance insurance) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.insurance = insurance;
	}

	public User() {
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
		
		if (!(obj instanceof User)) {
			return false;
		}
		
		User user = (User) obj;
		
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
