package com.epam.controller;

import com.epam.data.Payment;
import com.epam.service.BillService;
import com.epam.service.PaymentService;
import com.epam.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "BillController")
@Slf4j
@RequestMapping("/bills")
public class BillController {

    @Autowired
    private BillService billService;

//    @ApiOperation(value = "get payments", notes = "get all payments")
//    @GetMapping
//    public Result getAllPayments() {
//        if (log.isDebugEnabled()) {
//            log.debug("call get all payments");
//        }
//        List<Payment> payments = paymentService.getAllPayments();
//        return Result.success(payments);
//    }

}
