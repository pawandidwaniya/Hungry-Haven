package com.app.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.daos.DeliveryPersonDao;
import com.app.dtos.Credentials;
import com.app.dtos.DeliveryPersonDto;
import com.app.entities.DeliveryPerson;

@SpringBootTest
public class DeliveryPersonServiceTest {

    @Mock
    private DeliveryPersonDao deliveryPersonDao;

    @InjectMocks
    private DeliveryPersonService deliveryPersonService;

    @Test
    public void testGetDeliveryPersonDtoById() {
        DeliveryPerson deliveryPerson = new DeliveryPerson();
        deliveryPerson.setId(1);
        when(deliveryPersonDao.findById(1)).thenReturn(Optional.of(deliveryPerson));

        DeliveryPersonDto result = deliveryPersonService.getDeliveryPersonDtoById(1);

        assertNotNull(result);
        assertEquals(deliveryPerson.getId(), result.getId());
        
    }

    @Test
    public void testSaveDeliveryPerson() {
        DeliveryPerson deliveryPerson = new DeliveryPerson();
        when(deliveryPersonDao.save(any(DeliveryPerson.class))).thenReturn(deliveryPerson);

        boolean saved = deliveryPersonService.saveDeliveryPerson(deliveryPerson);

        assertTrue(saved);
        
    }

    @Test
    public void testFindDeliveryPersonByEmailAndPassword() {
        String email = "test@example.com";
        String password = "password";
        Credentials credentials = new Credentials(email, password);
        DeliveryPerson deliveryPerson = new DeliveryPerson();
        deliveryPerson.setEmail(email);
        deliveryPerson.setPassword(password);
        when(deliveryPersonDao.findByEmail(email)).thenReturn(deliveryPerson);

        DeliveryPersonDto result = deliveryPersonService.findDeliveryPersonByEmailAndPassword(credentials);

        assertNotNull(result);
        assertEquals(deliveryPerson.getEmail(), result.getEmail());
        assertEquals(deliveryPerson.getPassword(), credentials.getPassword());
    }

    @Test
    public void testFindDeliveryPersonByIsAvailable() {
        boolean status = true;
        List<DeliveryPerson> deliveryPersons = new ArrayList<>();
        when(deliveryPersonDao.findByIsAvailable(status)).thenReturn(deliveryPersons);

        List<DeliveryPersonDto> result = deliveryPersonService.findDeliveryPersonByIsAvailable(status);

        assertNotNull(result);
        
    }

}
