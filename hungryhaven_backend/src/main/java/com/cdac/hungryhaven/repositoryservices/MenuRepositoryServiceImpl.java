package com.cdac.hungryhaven.repositoryservices;

import com.cdac.hungryhaven.dto.Item;
import com.cdac.hungryhaven.dto.Menu;
import com.cdac.hungryhaven.exceptions.ItemNotFoundInRestaurantMenuException;
import com.cdac.hungryhaven.models.ItemEntity;
import com.cdac.hungryhaven.models.MenuEntity;
import com.cdac.hungryhaven.repositories.MenuRepository;
import com.cdac.hungryhaven.repositories.ItemRepository;
import jakarta.inject.Provider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MenuRepositoryServiceImpl implements MenuRepositoryService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private Provider<ModelMapper> modelMapperProvider;

    @Override
    public Menu findMenu(Long restaurantId) {
        ModelMapper modelMapper = modelMapperProvider.get();
        Optional<MenuEntity> menuById = menuRepository.findMenuByRestaurantId(restaurantId);
        Menu menu = null;

        if (menuById.isPresent()) {
            menu = modelMapper.map(menuById.get(), Menu.class);
        }

        return menu;
    }

    @Override
    @Transactional
    public Menu addItemToMenu(Long itemId, Long restaurantId) throws Exception {
        ModelMapper modelMapper = modelMapperProvider.get();
        Optional<MenuEntity> optionalMenuEntity = menuRepository.findMenuByRestaurantId(restaurantId);
        Menu menu = null;

        if (optionalMenuEntity.isPresent()) {
            MenuEntity menuEntity = optionalMenuEntity.get();
            Optional<ItemEntity> itemById = itemRepository.findById(itemId);

            if (itemById.isPresent()) {
                ItemEntity itemEntity = itemById.get();
                menuEntity.getItems().add(itemEntity);
                menuRepository.save(menuEntity);
                menu = modelMapper.map(menuEntity, Menu.class);
            } else {
                throw new ItemNotFoundInRestaurantMenuException("Item not found");
            }
        } else {
            throw new Exception("Invalid restaurant Id");
        }

        return menu;
    }

    @Override
    @Transactional
    public Menu removeItemFromMenu(Long itemId, Long restaurantId) throws Exception {
        Optional<MenuEntity> menuByRestaurantId = menuRepository.findMenuByRestaurantId(restaurantId);
        ModelMapper modelMapper = modelMapperProvider.get();
        Menu menu = null;

        if (menuByRestaurantId.isPresent()) {
            MenuEntity menuEntity = menuByRestaurantId.get();
            ItemEntity itemToBeDeleted = null;

            for (ItemEntity item : menuEntity.getItems()) {
                if (itemId.equals(item.getId())) {
                    itemToBeDeleted = item;
                    break;
                }
            }

            if (itemToBeDeleted != null) {
                menuEntity.getItems().remove(itemToBeDeleted);
                menuRepository.save(menuEntity);
                menu = modelMapper.map(menuEntity, Menu.class);
            } else {
                throw new ItemNotFoundInRestaurantMenuException("Item not in menu");
            }
        } else {
            throw new Exception("Invalid restaurant Id");
        }

        return menu;
    }

    @Override
    @Transactional
    public Menu updateItemInMenu(Item editedItem, Long restaurantId) {
        Optional<MenuEntity> menuByRestaurantId = menuRepository.findMenuByRestaurantId(restaurantId);
        ModelMapper modelMapper = modelMapperProvider.get();
        Menu menu = null;

        if (menuByRestaurantId.isPresent()) {
            MenuEntity menuEntity = menuByRestaurantId.get();

            for (ItemEntity item : menuEntity.getItems()) {
                if (editedItem.getId().equals(item.getId())) {
                    modelMapper.map(editedItem, item);
                    menuRepository.save(menuEntity);
                    menu = modelMapper.map(menuEntity, Menu.class);
                    break;
                }
            }
        }

        return menu;
    }
}
