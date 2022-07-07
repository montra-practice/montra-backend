package com.epam.service;

import com.epam.data.Bill;
import com.epam.data.Payment;
import com.epam.dto.BillDto;
import com.epam.utils.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BillService {
    Result saveBill(BillDto billDto);

    Result getUserBillList();

    Result getUserBill(Long id);


}
