package com.app.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.daos.CustomerDao;
import com.app.dtos.Credentials;
import com.app.dtos.CustomerDto;
import com.app.entities.Customer;

@SpringBootTest
public class CustomerServiceTest {

    @Mock
    private CustomerDao customerDao;

    @InjectMocks
    private CustomerService customerService;

    @Test
    public void testGetCustomerDtoById() {
        Customer customer = new Customer();
        customer.setId(1);
        when(customerDao.findById(1)).thenReturn(Optional.of(customer));

        CustomerDto result = customerService.getCustomerDtoById(1);

        assertNotNull(result);
        assertEquals(customer.getId(), result.getId());
        
    }

    @Test
    public void testSaveCustomer() {
        Customer customer = new Customer();
        when(customerDao.save(any(Customer.class))).thenReturn(customer);

        boolean saved = customerService.saveCustomer(customer);

        assertTrue(saved);
       
    }

    @Test
    public void testFindCustomerByEmailAndPassword() {
        String email = "test@example.com";
        String password = "password";
        Credentials credentials = new Credentials(email, password);
        Customer customer = new Customer();
        customer.setEmail(email);
        customer.setPassword(password);
        when(customerDao.findByEmail(email)).thenReturn(customer);

        CustomerDto result = customerService.findCustomerByEmailAndPassword(credentials);

        assertNotNull(result);
        assertEquals(customer.getEmail(), result.getEmail());
        assertEquals(customer.getPassword(), credentials.getPassword());
    }

    
}

