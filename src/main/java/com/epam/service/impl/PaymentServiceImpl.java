package com.epam.service.impl;

import com.epam.dao.PaymentRepository;
import com.epam.data.Payment;
import com.epam.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public List<Payment> getAllPayments() {

        return paymentRepository.findAll();
    }
}
