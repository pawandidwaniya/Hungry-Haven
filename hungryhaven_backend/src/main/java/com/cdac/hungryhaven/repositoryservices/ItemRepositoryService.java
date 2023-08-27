package com.cdac.hungryhaven.repositoryservices;

import com.cdac.hungryhaven.dto.Item;

public interface ItemRepositoryService {
    Long addItem(Item item);

    Item findByItemId(Long itemId);
}
