package com.epam.controller;

import com.epam.data.Bill;
import com.epam.data.Payment;
import com.epam.dto.BillDto;
import com.epam.service.BillService;
import com.epam.service.PaymentService;
import com.epam.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.epam.constant.Constants.API_PREFIX;

@RestController
@Api(tags = "BillController")
@Slf4j
@RequestMapping(API_PREFIX + "/bills")
public class BillController {

    @Autowired
    private BillService billService;

    @ApiOperation(value = "save bill", notes = "save bill")
    @PostMapping
    public Result saveBill(@RequestBody BillDto billDto) {
        if (log.isInfoEnabled()) {
            log.info("saveBill:{}", billDto);
        }
        Result result = billService.saveBill(billDto);
        return result;
    }

    @ApiOperation(value = "get user bill list", notes = "get user bill list")
    @GetMapping
    public Result getUserBillList() {
        if (log.isInfoEnabled()) {
            log.info("getUserBillList");
        }
        Result result = billService.getUserBillList();
        return result;
    }

    @ApiOperation(value = "get user bill", notes = "get user bill")
    @GetMapping("/{id}")
    public Result getUserBill(@PathVariable Long id) {
        if (log.isInfoEnabled()) {
            log.info("saveBill:{}", id);
        }
        Result result = billService.getUserBill(id);
        return result;
    }

}
