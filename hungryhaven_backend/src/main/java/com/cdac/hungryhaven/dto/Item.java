package com.cdac.hungryhaven.dto;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    private Long id;

    @NotNull
    String itemId;

    @NotNull
    String name;

    @NotNull
    String imageUrl;

    @NotNull
    List<String> attributes = new ArrayList<>();

    @NotNull
    int price;

}
