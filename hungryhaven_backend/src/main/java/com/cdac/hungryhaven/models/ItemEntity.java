package com.cdac.hungryhaven.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private String id;

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
