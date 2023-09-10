package com.app.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.daos.PaymentDao;
import com.app.entities.Payment;

@SpringBootTest
public class PaymentServiceTest {

    @Mock
    private PaymentDao paymentDao;

    @InjectMocks
    private PaymentService paymentService;

    @Test
    public void testFindAllPayments() {
        List<Payment> payments = new ArrayList<>();
        payments.add(new Payment());
        payments.add(new Payment());
        when(paymentDao.findAll()).thenReturn(payments);

        List<Payment> result = paymentService.findAllPayments();

        assertNotNull(result);
        assertEquals(payments.size(), result.size());
    }


}
