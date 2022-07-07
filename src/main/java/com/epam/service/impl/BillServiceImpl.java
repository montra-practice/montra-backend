package com.epam.service.impl;

import com.epam.dao.BillRepository;
import com.epam.dao.PaymentRepository;
import com.epam.data.Payment;
import com.epam.service.BillService;
import com.epam.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository billRepository;

}
