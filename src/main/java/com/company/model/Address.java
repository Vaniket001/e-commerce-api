package com.company.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "street_address")
	private String streetAddress;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;
	
	@Column(name = "zip_code")
	private String zipCode;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@ToString.Exclude
	@JsonIgnore
	private User user;

	@Column(name = "mobile")
	private String mobile;
	
	  @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        Address address = (Address) o;
	        return Objects.equals(id, address.id) &&
	               Objects.equals(firstName, address.firstName) &&
	               Objects.equals(lastName, address.lastName) &&
	               Objects.equals(email, address.email) &&
	               Objects.equals(streetAddress, address.streetAddress) &&
	               Objects.equals(city, address.city) &&
	               Objects.equals(state, address.state) &&
	               Objects.equals(zipCode, address.zipCode) &&
	               Objects.equals(mobile, address.mobile);
	    }

	    // Overriding hashCode method
	    @Override
	    public int hashCode() {
	        return Objects.hash(id, firstName, lastName, email, streetAddress, city, state, zipCode, mobile);
	    }
	    
	    @Override
	    public String toString() {
	        return "Address{" +
	               "id=" + id +
	               ", firstName='" + firstName + '\'' +
	               ", lastName='" + lastName + '\'' +
	               ", email='" + email + '\'' +
	               ", streetAddress='" + streetAddress + '\'' +
	               ", city='" + city + '\'' +
	               ", state='" + state + '\'' +
	               ", zipCode='" + zipCode + '\'' +
	               ", mobile='" + mobile + '\'' +
	               '}';
	    }
}
