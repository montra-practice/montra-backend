package com.epam.controller;

import com.epam.data.Category;
import com.epam.data.Payment;
import com.epam.service.CategoryService;
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

import static com.epam.constant.Constants.API_PREFIX;

@RestController
@Api(tags = "PaymentController")
@Slf4j
@RequestMapping(API_PREFIX + "/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @ApiOperation(value = "get payments", notes = "get all payments")
    @GetMapping
    public Result getAllPayments() {
        if (log.isInfoEnabled()) {
            log.info("call get all payments");
        }
        List<Payment> payments = paymentService.getAllPayments();
        return Result.success(payments);
    }

}
