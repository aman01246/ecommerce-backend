package com.ecommerce.entity;

import jakarta.persistence.*;

import lombok.*;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "projects")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Project {
	

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private double price;

    private String fileUrl;
    
    private String imageUrl;

    private LocalDateTime createdAt = LocalDateTime.now();

}
