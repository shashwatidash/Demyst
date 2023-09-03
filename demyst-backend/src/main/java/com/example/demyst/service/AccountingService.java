package com.example.demyst.service;

import com.example.demyst.model.BalanceSheet;
import com.example.demyst.model.BalanceSheetItem;
import com.example.demyst.model.BusinessDetails;
import com.example.demyst.model.softwareprovider.AccountingSoftwareProvider;
import com.example.demyst.model.softwareprovider.MYOB;
import com.example.demyst.model.softwareprovider.Xero;
import com.example.demyst.repository.AccountingRepository;
import com.example.demyst.repository.BalanceSheetItemRepository;
import java.util.Iterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountingService {
    private final AccountingRepository accountingRepository;
    private final BalanceSheetItemRepository balanceSheetItemRepository;
    AccountingSoftwareProvider accountingSoftwareProvider;

    public AccountingService(AccountingRepository accountingRepository, BalanceSheetItemRepository balanceSheetItemRepository) {
        this.accountingRepository = accountingRepository;
        this.balanceSheetItemRepository = balanceSheetItemRepository;
    }

    public long addNewBusinessDetail(BusinessDetails businessDetails) {
        this.accountingRepository.save(businessDetails);
        if (businessDetails.getBalanceSheetItems() != null) {

            for (BalanceSheetItem balanceSheetItem : businessDetails.getBalanceSheetItems()) {
                balanceSheetItem.setBusinessId(businessDetails.getBusinessId());
                this.balanceSheetItemRepository.save(balanceSheetItem);
            }
        }

        return businessDetails.getBusinessId();
    }

    public BalanceSheet getBalanceSheet(long businessId) {
        Optional<BusinessDetails> businessDetails = this.accountingRepository.findBusinessDetailsById(businessId);
        if (businessDetails.isEmpty()) {
            throw new IllegalStateException("This business id does not exists");
        } else {
            if (((BusinessDetails)businessDetails.get()).getAccountingProvider().equals("Xero")) {
                accountingSoftwareProvider = new Xero();
            } else {
                this.accountingSoftwareProvider = new MYOB();
            }

            BalanceSheet balanceSheet = this.accountingSoftwareProvider.calculateBalanceSheet((BusinessDetails)businessDetails.get());
            this.balanceSheetItemRepository.saveAll(balanceSheet.getBalanceSheetItems());
            return balanceSheet;
        }
    }

    public int calculateAssessment(long businessId) {
        Optional<BusinessDetails> businessDetailsById = this.accountingRepository.findBusinessDetailsById(businessId);
        if (businessDetailsById.isEmpty()) {
            throw new IllegalStateException("This business id does not exists");
        } else {
            int year = 0;
            Iterator var5 = ((BusinessDetails)businessDetailsById.get()).getBalanceSheetItems().iterator();

            while(var5.hasNext()) {
                BalanceSheetItem balanceSheetItem = (BalanceSheetItem)var5.next();
                if (balanceSheetItem.getYear() > year) {
                    year = balanceSheetItem.getYear();
                }
            }

            String profitOrLoss = "";
            double sumTotalAssets = 0.0;

            for (BalanceSheetItem balanceSheetItem : ((BusinessDetails) businessDetailsById.get()).getBalanceSheetItems()) {
                if (balanceSheetItem.getYear() == year) {
                    profitOrLoss = balanceSheetItem.getProfitOrLoss();
                    sumTotalAssets += balanceSheetItem.getAssetsValue();
                }
            }

            int avg = (int)sumTotalAssets / ((BusinessDetails)businessDetailsById.get()).getBalanceSheetItems().size();
            int preAssessment = 0;
            if (profitOrLoss.equals("P")) {
                preAssessment = 60;
            } else if ((double)avg > ((BusinessDetails)businessDetailsById.get()).getLoanAmount()) {
                preAssessment = 100;
            } else {
                preAssessment = 20;
            }

            return preAssessment;
        }
    }

    public double calculateGrantedLoanAmount(long businessId, int preAssessment) {
        Optional<BusinessDetails> businessDetailsById = this.accountingRepository.findBusinessDetailsById(businessId);
        if (businessDetailsById.isEmpty()) {
            throw new IllegalStateException("This business id does not exists");
        } else {
            return (double)preAssessment * 0.01 * ((BusinessDetails)businessDetailsById.get()).getLoanAmount();
        }
    }
}