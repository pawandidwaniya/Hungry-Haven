package com.cdac.hungryhaven.exchanges;

import org.springframework.web.bind.annotation.RequestParam;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetCartRequest {
	  @NonNull
	  private Long userId;

	  public Long getUserId() {
	    return userId;
	  }

	  public void setUserId(@RequestParam(value = "userId") Long userId) {
	    this.userId = userId;
	  }
}
