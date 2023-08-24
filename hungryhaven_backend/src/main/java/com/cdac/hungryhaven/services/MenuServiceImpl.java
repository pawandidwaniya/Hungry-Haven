package com.cdac.hungryhaven.services;

import com.cdac.hungryhaven.dto.Item;
import com.cdac.hungryhaven.dto.Menu;
import com.cdac.hungryhaven.exceptions.ItemNotFoundInRestaurantMenuException;
import com.cdac.hungryhaven.exceptions.ItemNotFromSameRestaurantException;
import com.cdac.hungryhaven.repositoryservices.ItemRepositoryService;
import com.cdac.hungryhaven.repositoryservices.MenuRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class
MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepositoryService menuRepositoryService;

    @Autowired
    private ItemRepositoryService itemRepositoryService;

    @Override
    public Menu findMenu(Long restaurantId) {
        return menuRepositoryService.findMenu(restaurantId);
    }

    @Override
    public Item findItem(Long itemId, Long restaurantId)
            throws ItemNotFoundInRestaurantMenuException, ItemNotFromSameRestaurantException {
        Menu menu = menuRepositoryService.findMenu(restaurantId);

        if (menu != null) {
            for (Item item : menu.getItems()) {
                if (itemId.equals(item.getItemId())) {
                    return item;
                }
            }
            throw new ItemNotFoundInRestaurantMenuException("No item found matching the itemId " + itemId);
        } else {
            throw new ItemNotFoundInRestaurantMenuException("Restaurant not found");
        }
    }

    @Override
    public Menu addItem(Item item, Long restaurantId) throws Exception {
        Long itemId = itemRepositoryService.addItem(item);
        menuRepositoryService.addItemToMenu(itemId, restaurantId);
        return findMenu(restaurantId); // Return updated menu
    }

    @Override
    public Menu removeItem(Long itemId, Long restaurantId) throws Exception {
        menuRepositoryService.removeItemFromMenu(itemId, restaurantId);
        return findMenu(restaurantId); // Return updated menu
    }

    @Override
    public Menu updateItem(Item editItem, Long restaurantId) throws ItemNotFoundInRestaurantMenuException {
        Menu menu = menuRepositoryService.findMenu(restaurantId);
        if (menu != null) {
            itemRepositoryService.addItem(editItem);
            menuRepositoryService.updateItemInMenu(editItem, restaurantId);
            return findMenu(restaurantId); // Return updated menu
        } else {
            throw new ItemNotFoundInRestaurantMenuException("Restaurant not found");
        }
    }
}
