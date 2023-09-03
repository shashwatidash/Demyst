package com.example.demyst.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Entity
@Table(
    name = "BusinessDetails"
)
public class BusinessDetails {
    @Id
    @GeneratedValue(
        strategy = GenerationType.AUTO
    )
    private long businessId;
    @Column(
        name = "name"
    )
    private String name;
    @Column(
        name = "email"
    )
    private String email;
    @Transient
    private Integer age;
    @Column(
        name = "dob"
    )
    private LocalDate dob;
    @Column(
        name = "accountingProvider"
    )
    private String accountingProvider;
    @Column(
        name = "yearOfEstablishment"
    )
    private LocalDate yearOfEstablishment;
    @Column(
        name = "address"
    )
    private String address;
    @Column(
        name = "organisation"
    )
    private String organisation;
    @Column(
        name = "loanReason"
    )
    private String loanReason;
    @Column(
        name = "loanAmount"
    )
    private Double loanAmount;
    @Column(
        name = "annualIncome"
    )
    private Double annualIncome;
    @Column(
        name = "totalCurrentAssets"
    )
    private Double totalCurrentAssets;
    @Column(
        name = "shareholderEquity"
    )
    private Double shareholderEquity;
    @OneToMany(
        cascade = {CascadeType.ALL}
    )
    private List<BalanceSheetItem> balanceSheetItems;

    public BusinessDetails() {
    }

    public BusinessDetails(String name, String email, LocalDate dob, String address, String organisation, String loanReason, Double loanAmount, Double annualIncome, LocalDate yearOfEstablishment, String accountingProvider, Double totalCurrentAssets, Double shareholderEquity, List<BalanceSheetItem> balanceSheetItems) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.address = address;
        this.organisation = organisation;
        this.loanReason = loanReason;
        this.loanAmount = loanAmount;
        this.annualIncome = annualIncome;
        this.yearOfEstablishment = yearOfEstablishment;
        this.totalCurrentAssets = totalCurrentAssets;
        this.shareholderEquity = shareholderEquity;
        this.accountingProvider = accountingProvider;
        this.balanceSheetItems = balanceSheetItems;
    }

    public long getBusinessId() {
        return this.businessId;
    }

    public void setBusinessId(long businessId) {
        this.businessId = businessId;
    }

    public LocalDate getYearOfEstablishment() {
        return this.yearOfEstablishment;
    }

    public void setYearOfEstablishment(LocalDate yearOfEstablishment) {
        this.yearOfEstablishment = yearOfEstablishment;
    }

    public Double getAnnualIncome() {
        return this.annualIncome;
    }

    public void setAnnualIncome(Double annualIncome) {
        this.annualIncome = annualIncome;
    }

    public String getAccountingProvider() {
        return this.accountingProvider;
    }

    public void setAccountingProvider(String accountingProvider) {
        this.accountingProvider = accountingProvider;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getDob() {
        return this.dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOrganisation() {
        return this.organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getLoanReason() {
        return this.loanReason;
    }

    public void setLoanReason(String loanReason) {
        this.loanReason = loanReason;
    }

    public Double getLoanAmount() {
        return this.loanAmount;
    }

    public void setLoanAmount(Double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Double getTotalCurrentAssets() {
        return this.totalCurrentAssets;
    }

    public void setTotalCurrentAssets(Double totalCurrentAssets) {
        this.totalCurrentAssets = totalCurrentAssets;
    }

    public Double getShareholderEquity() {
        return this.shareholderEquity;
    }

    public void setShareholderEquity(Double shareholderEquity) {
        this.shareholderEquity = shareholderEquity;
    }

    public List<BalanceSheetItem> getBalanceSheetItems() {
        return this.balanceSheetItems;
    }

    public void setBalanceSheetItems(List<BalanceSheetItem> balanceSheetItems) {
        this.balanceSheetItems = balanceSheetItems;
    }
}
