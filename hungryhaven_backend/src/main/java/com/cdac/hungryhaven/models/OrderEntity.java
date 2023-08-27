package com.cdac.hungryhaven.models;

import java.time.Instant;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Entity
@Table(name = "orders")
public class OrderEntity {
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  @Column(name = "order_id")
	  private Long id;

	  @NotNull
	  private Long restaurantId;

	  @NotNull
	  private Long userId;

//	  @NotNull
	  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	  private List<ItemEntity> items = new ArrayList();

	  @NotNull
	  private int total = 0;

	  @NotNull
	  private Instant placedTime;

	  @NotNull
	  private String status;
}
