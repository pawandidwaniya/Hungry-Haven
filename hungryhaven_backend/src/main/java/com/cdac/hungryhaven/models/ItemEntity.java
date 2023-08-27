package com.cdac.hungryhaven.models;


import lombok.Data;
import lombok.NoArgsConstructor;

import com.cdac.hungryhaven.dto.Item;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;


@Entity
@Data
@Table(name = "items")
@NoArgsConstructor
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Column(name = "item_name")
    @NotBlank
    private String name;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "item_price")
    @NotBlank
    private Double price;
    
    @ManyToOne
    @JoinColumn(name = "cart_id") // This links the item to its parent cart
    private CartEntity cart;
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private MenuEntity menu;
    
    @ManyToOne
    @JoinColumn(name = "order_id") // This links the item to its parent order
    private OrderEntity order;
    
    public ItemEntity(Item item) {
        // Perform the conversion from Item to ItemEntity here
        this.id = item.getItemId();
        this.name = item.getName();
        // Map other fields as needed
}
}
