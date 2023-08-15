package com.cdac.hungryhaven.repositoryservices;

import com.cdac.hungryhaven.dto.Item;
import com.cdac.hungryhaven.models.ItemEntity;
import com.cdac.hungryhaven.repositories.ItemRepository;

import jakarta.inject.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import org.springframework.ui.Model;

import java.util.Optional;

@Service
public class ItemRepositoryServiceImpl implements ItemRepositoryService{

    @Autowired
    Provider<ModelMapper> modelMapperProvider;

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public String addItem(Item item) {
        ModelMapper modelMapper = modelMapperProvider.get();
        ItemEntity itemEntity = modelMapper.map(item, ItemEntity.class);
        itemRepository.save(itemEntity);

        return item.getItemId();
    }

    @Override
    public Item findByItemId(String itemId) {
        ModelMapper modelMapper = modelMapperProvider.get();
        Optional<ItemEntity> itemEntity = itemRepository.findByItemId(itemId);

        Item item = null;
        if (itemEntity.isPresent()) {
            item = modelMapper.map(itemEntity, Item.class);
        }
        return item;
    }

}
