
package com.example.demyst.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(
        name = "balanceSheetItem"
)
public class BalanceSheetItem {
    @Id
    @SequenceGenerator(
            name = "item_sequence",
            sequenceName = "item_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "item_sequence"
    )
    private long itemSeq;
    @Column
    private long businessId;
    @Column(
            name = "year"
    )
    private int year;
    @Column(
            name = "profitOrLoss"
    )
    private String profitOrLoss;
    @Column(
            name = "assetsValue"
    )
    private double assetsValue;
    @Column(
            name = "liabilitiesSum"
    )
    private double liabilitiesSum;

    public BalanceSheetItem() {
    }

    public BalanceSheetItem(int year, double assetsValue, double liabilitiesSum) {
        this.assetsValue = assetsValue;
        this.liabilitiesSum = liabilitiesSum;
        this.year = year;
    }

    public long getItemSeq() {
        return this.itemSeq;
    }

    public void setItemSeq(long itemSeq) {
        this.itemSeq = itemSeq;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getProfitOrLoss() {
        return this.profitOrLoss;
    }

    public void setProfitOrLoss(String profitOrLoss) {
        this.profitOrLoss = profitOrLoss;
    }

    public double getAssetsValue() {
        return this.assetsValue;
    }

    public void setAssetsValue(double assetsValue) {
        this.assetsValue = assetsValue;
    }

    public double getLiabilitiesSum() {
        return this.liabilitiesSum;
    }

    public void setLiabilitiesSum(double liabilitiesSum) {
        this.liabilitiesSum = liabilitiesSum;
    }

    public long getBusinessId() {
        return this.businessId;
    }

    public void setBusinessId(long businessId) {
        this.businessId = businessId;
    }
}
