package com.company.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name ="user_id", nullable = false)
	private User user;
	
//	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
//	@Column(name ="cart_items")
//	private Set<CartItem> cartItems = new HashSet<>();
	
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<CartItem> cartItems = new HashSet<>();
	
	@Column(name ="total_price")
	private double totalPrice;
	
	@Column(name ="total_item")
	private int totalItem;
	
	private int totalDiscountedPrice;
	
	private int discount;
	
	  @Override
	    public int hashCode() {
	        return Objects.hash(id);
	    }

	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        Cart cart = (Cart) o;
	        return Objects.equals(id, cart.id);
	    }
	
	
}
