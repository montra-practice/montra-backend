package com.epam.controller;

import com.epam.data.Attachment;
import com.epam.data.Bill;
import com.epam.dto.BillDto;
import com.epam.service.AttachmentService;
import com.epam.service.BillService;
import com.epam.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@Api(tags = "Bill")
@Slf4j
@RequestMapping("/bills")
public class BillController {

    @Autowired
    private BillService billService;

    @Autowired
    private AttachmentService attachmentService;

    @ApiOperation(value = "save bill", notes = "save bill")
    @PostMapping
    public Result<Boolean> saveBill(@RequestBody @Validated BillDto billDto) {
        if (log.isInfoEnabled()) {
            log.info("saveBill:{}", billDto);
        }
        Result result = billService.saveBill(billDto);
        return result;
    }

    @ApiOperation(value = "get user bill list", notes = "get user recent bill transaction")
    @GetMapping("/recent")
    public Result<List<Bill>> getUserRecentBillList() {
        if (log.isInfoEnabled()) {
            log.info("getUserRecentBillList");
        }
        Result result = billService.getUserRecentBillList();
        return result;
    }

    @ApiOperation(value = "get user bill", notes = "get user bill by id")
    @GetMapping("/{id}")
    public Result<Bill> getUserBill(@PathVariable Long id) {
        if (log.isInfoEnabled()) {
            log.info("saveBill:{}", id);
        }
        Result result = billService.getUserBill(id);
        return result;
    }

    @ApiOperation(value = "upload attachment", notes = "")
    @PostMapping("/attachments")
    public Result<Long> uploadAttachment(@RequestParam("file") MultipartFile file) throws IOException {

        Result result = attachmentService.uploadAttachment(file);
        return result;
    }

    @ApiOperation(value = "get attachment", notes = "")
    @GetMapping(path = { "/attachments/{id}" })
    public ResponseEntity<byte[]> getAttachment(@PathVariable Long id) throws IOException {

        Optional<Attachment> optional = attachmentService.getAttachment(id);

        return optional.map(a -> ResponseEntity.ok().contentType(MediaType.valueOf(a.getType())).body(a.getAttachByte()))
                .orElse(null);
    }

}
