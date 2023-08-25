package com.cdac.hungryhaven.exchanges;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    @NotNull
    Long restaurantId;

    @NotNull
    int userResponseType;
}
