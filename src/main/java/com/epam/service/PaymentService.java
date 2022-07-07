package com.epam.service;

import com.epam.data.Category;
import com.epam.data.Payment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentService {

    List<Payment> getAllPayments();

}
