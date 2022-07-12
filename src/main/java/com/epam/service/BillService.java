package com.epam.service;

import com.epam.data.Bill;
import com.epam.dto.BillDto;
import com.epam.utils.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BillService {
    Result<Boolean> saveBill(BillDto billDto);

    Result<List<Bill>> getUserRecentBillList();

    Result<Bill> getUserBill(Long id);

}
