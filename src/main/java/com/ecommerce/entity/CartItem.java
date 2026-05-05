package com.ecommerce.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"cart_id", "project_id"}))
public class CartItem {
	

	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private int quantity = 1;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "cart_id")
	    @JsonIgnore
	    private Cart cart;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "project_id")
	    private Project project;
	
}
