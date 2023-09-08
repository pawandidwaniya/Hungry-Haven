package com.app.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.daos.RestaurantManagerDao;
import com.app.dtos.RestaurantManagerDto;
import com.app.entities.RestaurantManager;

@SpringBootTest
public class RestaurantManagerServiceTest {

    @Mock
    private RestaurantManagerDao restaurantManagerDao;

    @InjectMocks
    private RestaurantManagerService restaurantManagerService;

    @Test
    public void testGetRestaurantManagerDtoById() {
        RestaurantManager restaurantManager = new RestaurantManager();
        restaurantManager.setId(1);
        when(restaurantManagerDao.findById(1)).thenReturn(Optional.of(restaurantManager));

        RestaurantManagerDto result = restaurantManagerService.getRestaurantManagerDtoById(1);

        assertNotNull(result);
        assertEquals(restaurantManager.getId(), result.getId());

    }

    @Test
    public void testSaveRestaurantManager() {
        RestaurantManager restaurantManager = new RestaurantManager();
        when(restaurantManagerDao.save(any(RestaurantManager.class))).thenReturn(restaurantManager);

        boolean saved = restaurantManagerService.saveRestaurantManager(restaurantManager);

        assertTrue(saved);
    }


}

