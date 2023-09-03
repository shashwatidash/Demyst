package com.example.demyst.controller;

import com.example.demyst.model.BalanceSheet;
import com.example.demyst.model.BusinessDetails;
import com.example.demyst.service.AccountingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(
        path = {"api/v1"}
)
public class AccountingController {
    private final AccountingService accountingService;

    @Autowired
    public AccountingController(AccountingService accountingService) {
        this.accountingService = accountingService;
    }

    @PostMapping(
            path = {"saveBusinessDetails"}
    )
    public ResponseEntity<Long> addBusinessDetails(@RequestBody BusinessDetails businessDetails) {
        Long response = this.accountingService.addNewBusinessDetail(businessDetails);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping(
            path = {"{id}/balanceSheet"}
    )
    public BalanceSheet getBalanceSheet(@PathVariable("id") long businessId) {
        return this.accountingService.getBalanceSheet(businessId);
    }

    @GetMapping(
            path = {"{id}/assessment"}
    )
    public int calculateAssessment(@PathVariable("id") long businessId) {
        return this.accountingService.calculateAssessment(businessId);
    }

    @GetMapping(
            path = {"{id}/result"}
    )
    public double calculateGrantedLoanAmount(@PathVariable("id") long businessId, @RequestParam int preAssessment) {
        return this.accountingService.calculateGrantedLoanAmount(businessId, preAssessment);
    }
}
