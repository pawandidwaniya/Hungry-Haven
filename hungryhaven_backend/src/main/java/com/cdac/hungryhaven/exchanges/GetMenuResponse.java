package com.cdac.hungryhaven.exchanges;

import com.cdac.hungryhaven.dto.Menu;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetMenuResponse {
  @NotNull
  Menu menu;

  @NotNull
  int menuResponseType;
}
