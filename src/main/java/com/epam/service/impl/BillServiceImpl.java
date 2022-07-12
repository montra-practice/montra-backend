package com.epam.service.impl;

import com.epam.dao.BillRepository;
import com.epam.dao.PaymentRepository;
import com.epam.data.Bill;
import com.epam.data.Payment;
import com.epam.dto.BillDto;
import com.epam.service.BillService;
import com.epam.service.PaymentService;
import com.epam.utils.ContextUtil;
import com.epam.utils.Result;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private BillRepository billRepository;

    private Long getCurrentUserId() {
        return Objects.requireNonNull(ContextUtil.getUserId());
    }

    private String getCurrentUserName() {
        return Objects.requireNonNull(ContextUtil.getUsername());
    }

    @Override
    public Result<Boolean> saveBill(BillDto billDto) {

        Bill bill = new Bill();
        BeanUtils.copyProperties(billDto, bill);
        bill.setCreator(getCurrentUserName());
        bill.setUserId(getCurrentUserId());
        bill.setGmtCreate(new Date());
        List<Long> attachments = billDto.getAttachments();
        bill.setAttachmentIds(StringUtils.join(attachments, ","));

        billRepository.save(bill);

        return Result.success(true);
    }

    @Override
    public Result<List<Bill>> getUserBillList(Map<String, Object> param) {

        Bill bill = new Bill();
        bill.setUserId(getCurrentUserId());
        Example<Bill> example = Example.of(bill);

        Sort sort = Sort.sort(Bill.class).by(Bill::getGmtCreate).descending();
        Pageable pageable = PageRequest.of(0, MapUtils.getIntValue(param, "limit"), sort);

        Page<Bill> bills = billRepository.findAll(example, pageable);
        return Result.success(bills.getContent());
    }

    @Override
    public Result<Bill> getUserBill(Long id) {

        Long currentUserId = getCurrentUserId();

        Optional<Bill> billOptional = billRepository.findById(id);

        return billOptional.filter(b -> currentUserId.equals(b.getUserId()))
                .map(b -> Result.success(b))
                .orElse(Result.fail("Not Found Data"));
    }
}
