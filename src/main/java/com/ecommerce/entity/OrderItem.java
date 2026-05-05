package com.ecommerce.entity;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class OrderItem {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private double price;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "order_id")
	    private Order order;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "project_id")
	    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	    private Project project;
	
	
}
