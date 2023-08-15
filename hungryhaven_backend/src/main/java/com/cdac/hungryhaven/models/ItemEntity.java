package com.cdac.hungryhaven.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "items")
@NoArgsConstructor
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    private String itemId;

    @NotNull
    private String name;

    @NotNull
    private String imageUrl;

    @NotNull
    private Double price;

    @NotNull
    private List<String> attributes = new ArrayList<>();
}
