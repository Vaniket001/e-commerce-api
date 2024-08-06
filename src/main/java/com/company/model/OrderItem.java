//package com.company.model;
//
//import java.time.LocalDateTime;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class OrderItem {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//	
//	@JsonIgnore
//	@ManyToOne
//	private Order order;
//	
//	@ManyToOne
//	private Product product;
//	
//	private String size;
//	
//	private int quantity;
//	
//	private Integer price;
//	
//	private Integer discountedPrice;
//	
//	private Long userId;
//	
//	private LocalDateTime deliveryDate;
//}

package com.company.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @JsonIgnore
    @ManyToOne
    @ToString.Exclude
    private Order order;
    
    @ManyToOne
    private Product product;
    
    private String size;
    
    private int quantity;
    
    private Integer price;
    
    private Integer discountedPrice;
    
    private Long userId;
    
    private LocalDateTime deliveryDate;

    @Override
    public String toString() {
        return "OrderItem{" +
               "id=" + id +
               ", product=" + product +
               ", size='" + size + '\'' +
               ", quantity=" + quantity +
               ", price=" + price +
               ", discountedPrice=" + discountedPrice +
               ", userId=" + userId +
               ", deliveryDate=" + deliveryDate +
               // Exclude order or provide a limited representation
               '}';
    }
}

