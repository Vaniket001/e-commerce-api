package com.company.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String firstName;

	private String lastName;

	private String email;

	private String password;

	private String mobile;

	private String role;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Address> addresses = new ArrayList<>();

	@Embedded
	@ElementCollection
	@CollectionTable(name = "payment_information", joinColumns = @JoinColumn(name = "user_id"))
	private List<PaymentInformation> paymentInformations = new ArrayList<>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Rating> ratings = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Review> reviews = new ArrayList<>();
	
	private LocalDateTime createdAt;

}

//package com.company.model;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//
//import javax.persistence.CascadeType;
//import javax.persistence.CollectionTable;
//import javax.persistence.ElementCollection;
//import javax.persistence.Embedded;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToMany;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.ToString;
//
//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class User {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    private String firstName;
//
//    private String lastName;
//
//    private String email;
//
//    private String password;
//
//    private String mobile;
//
//    private String role;
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    @JsonIgnore
//    @ToString.Exclude
//    private List<Address> addresses = new ArrayList<>();
//
//    @Embedded
//    @ElementCollection
//    @CollectionTable(name = "payment_information", joinColumns = @JoinColumn(name = "user_id"))
//    private List<PaymentInformation> paymentInformations = new ArrayList<>();
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    @JsonIgnore
//    @ToString.Exclude
//    private List<Rating> ratings = new ArrayList<>();
//
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    @JsonIgnore
//    @ToString.Exclude
//    private List<Review> reviews = new ArrayList<>();
//
//    private LocalDateTime createdAt;
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        User user = (User) o;
//        return Objects.equals(id, user.id) &&
//               Objects.equals(firstName, user.firstName) &&
//               Objects.equals(lastName, user.lastName) &&
//               Objects.equals(email, user.email) &&
//               Objects.equals(password, user.password) &&
//               Objects.equals(mobile, user.mobile) &&
//               Objects.equals(role, user.role);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, firstName, lastName, email, password, mobile, role);
//    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//               "id=" + id +
//               ", firstName='" + firstName + '\'' +
//               ", lastName='" + lastName + '\'' +
//               ", email='" + email + '\'' +
//               ", mobile='" + mobile + '\'' +
//               ", role='" + role + '\'' +
//               ", createdAt=" + createdAt +
//               '}';
//    }
//}

