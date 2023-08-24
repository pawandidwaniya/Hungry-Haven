package com.cdac.hungryhaven.repositoryservices;

import org.springframework.stereotype.Service;

import com.cdac.hungryhaven.dto.Item;
import com.cdac.hungryhaven.dto.Menu;

@Service
public interface MenuRepositoryService {
	Menu findMenu(Long restaurantId);

	  Menu addItemToMenu(Long itemId, Long restaurantId) throws Exception;

	  Menu removeItemFromMenu(Long itemId, Long restaurantId) throws Exception;

	  Menu updateItemInMenu(Item item, Long restaurantId);

}
