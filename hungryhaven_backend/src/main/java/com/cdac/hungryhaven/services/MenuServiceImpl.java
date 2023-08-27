package com.cdac.hungryhaven.services;

import com.cdac.hungryhaven.dto.Item;

import com.cdac.hungryhaven.dto.Menu;
import com.cdac.hungryhaven.exceptions.ItemNotFoundInRestaurantMenuException;
import com.cdac.hungryhaven.exceptions.ItemNotFromSameRestaurantException;
import com.cdac.hungryhaven.exchanges.GetMenuResponse;
import com.cdac.hungryhaven.repositoryservices.ItemRepositoryService;
import com.cdac.hungryhaven.repositoryservices.MenuRepositoryService;

import jakarta.inject.Provider;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements MenuService {

	  @Autowired
	  MenuRepositoryService menuRepositoryService;

	  @Autowired
	  ItemRepositoryService itemRepositoryService;


	  @Autowired
	  Provider<ModelMapper> modelMapperProvider;


	@Override
	public GetMenuResponse findMenu(Long restaurantId) {
	    GetMenuResponse getMenuResponse = new GetMenuResponse();
	    Menu menu = menuRepositoryService.findMenu(restaurantId);
	    getMenuResponse.setMenu(menu);
	    return getMenuResponse;
	}


	@Override
	public Item findItem(Long itemId, Long restaurantId)
			throws ItemNotFoundInRestaurantMenuException, ItemNotFromSameRestaurantException {
	    Menu menu = menuRepositoryService.findMenu(restaurantId);

	    for (Item item : menu.getItems()) {
	      if (itemId.equals(item.getItemId())) {
	        return item;
	      }
	    }

	    throw new ItemNotFoundInRestaurantMenuException("No item found matching the itemId " + itemId);
	}


	@Override
	public GetMenuResponse addItem(Item item, Long restaurantId) throws Exception {
	    Long itemId = itemRepositoryService.addItem(item);
	    GetMenuResponse menuResponse = new GetMenuResponse();

	    try {
	      Menu menu = menuRepositoryService.addItemToMenu(itemId, restaurantId);
	      menuResponse.setMenu(menu);
	      menuResponse.setMenuResponseType(0);
	    } catch (Exception ex) {
	      throw ex;
	    }


	    return menuResponse;
	}


	@Override
	public GetMenuResponse removeItem(Long itemId, Long restaurantId) throws Exception {
	    GetMenuResponse menuResponse = new GetMenuResponse();
	    try {

	      Menu menu = menuRepositoryService.removeItemFromMenu(itemId, restaurantId);
	      menuResponse.setMenu(menu);
	      menuResponse.setMenuResponseType(0);
	    } catch (Exception ex) {
	      if (ex instanceof ItemNotFoundInRestaurantMenuException) {
	        menuResponse.setMenuResponseType(((ItemNotFoundInRestaurantMenuException) ex).getErrorType());
	        return menuResponse;
	      }
	    }

	    return menuResponse;
	}


	@Override
	public GetMenuResponse updateItem(Item editItem, Long restaurantId) throws ItemNotFoundInRestaurantMenuException {
	    GetMenuResponse menuResponse = new GetMenuResponse();
	    Menu menu = menuRepositoryService.findMenu(restaurantId);
	    if (menu != null) {
	      itemRepositoryService.addItem(editItem);
	      menu = menuRepositoryService.updateItemInMenu(editItem, restaurantId);
	      menuResponse.setMenu(menu);
	      menuResponse.setMenuResponseType(0);
	    } else {
	      menuResponse.setMenuResponseType(new ItemNotFoundInRestaurantMenuException("").getErrorType());
	    }

	    return menuResponse;
	}

	  
	}