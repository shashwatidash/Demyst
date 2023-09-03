package com.example.demyst.model;

import java.util.List;

public class BalanceSheet {
    private List<BalanceSheetItem> balanceSheetItems;

    public List<BalanceSheetItem> getBalanceSheetItems() {
        return this.balanceSheetItems;
    }

    public BalanceSheet(List<BalanceSheetItem> balanceSheetItems) {
        this.balanceSheetItems = balanceSheetItems;
    }
}
