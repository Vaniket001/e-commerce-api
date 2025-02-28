//package com.company.model;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Embedded;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Table(name = "orders")
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//public class Order {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private Long id;
//	
//	@Column(name = "order_id")
//	private String orderId;
//	
//	@ManyToOne
//	private User user;
//	
//	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
//	private List<OrderItem> orderItems = new ArrayList<>();
//	
//	private LocalDateTime orderDate;
//	
//	private LocalDateTime deliveryDate;
//	
//	@OneToOne
//	private Address shippingAddress;
//	
//	@Embedded
//	private PaymentDetails paymentDetails = new PaymentDetails();
//	
//	private double totalPrice;
//	
//	private Integer totalDiscountedPrice;
//	
//	private Integer discount;
//	
//	private String orderStatus;
//	
//	private int totalItem;
//	
//	private LocalDateTime createdAt;
//}

package com.company.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "order_id")
    private String orderId;
    
    @ManyToOne
    private User user;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private List<OrderItem> orderItems = new ArrayList<>();
    
    private LocalDateTime orderDate;
    
    private LocalDateTime deliveryDate;
    
    @OneToOne
    private Address shippingAddress;
    
    @Embedded
    private PaymentDetails paymentDetails = new PaymentDetails();
    
    private double totalPrice;
    
    private Integer totalDiscountedPrice;
    
    private Integer discount;
    
    private String orderStatus;
    
    private int totalItem;
    
    private LocalDateTime createdAt;

    @Override
    public String toString() {
        return "Order{" +
               "id=" + id +
               ", orderId='" + orderId + '\'' +
               ", orderDate=" + orderDate +
               ", deliveryDate=" + deliveryDate +
               ", totalPrice=" + totalPrice +
               ", totalDiscountedPrice=" + totalDiscountedPrice +
               ", discount=" + discount +
               ", orderStatus='" + orderStatus + '\'' +
               ", totalItem=" + totalItem +
               ", createdAt=" + createdAt +
               // Exclude orderItems or provide a limited representation
               '}';
    }
}
