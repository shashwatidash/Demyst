package com.example.demyst.model.softwareprovider;

import com.example.demyst.model.BalanceSheet;
import com.example.demyst.model.BalanceSheetItem;
import com.example.demyst.model.BusinessDetails;
import com.example.demyst.model.softwareprovider.AccountingSoftwareProvider;

import java.util.Iterator;
import java.util.List;

public class MYOB implements AccountingSoftwareProvider {

    public BalanceSheet calculateBalanceSheet(BusinessDetails businessDetails) {
        System.out.println("Using the MYOB Accounting Provider");
        List<BalanceSheetItem> balanceSheetItems = businessDetails.getBalanceSheetItems();
        Iterator var3 = balanceSheetItems.iterator();

        while(var3.hasNext()) {
            BalanceSheetItem balanceSheetData = (BalanceSheetItem)var3.next();
            balanceSheetData.setAssetsValue(businessDetails.getTotalCurrentAssets() + businessDetails.getAnnualIncome());
            if (balanceSheetData.getAssetsValue() > balanceSheetData.getLiabilitiesSum()) {
                balanceSheetData.setProfitOrLoss("P");
            } else {
                balanceSheetData.setProfitOrLoss("L");
            }
        }

        return new BalanceSheet(balanceSheetItems);
    }
}