package com.cdac.hungryhaven.repositoryservices;

import com.cdac.hungryhaven.dto.Item;

public interface ItemRepositoryService {
    String addItem(Item item);

    Item findByItemId(String itemId);
}
