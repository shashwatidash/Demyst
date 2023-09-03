package com.example.demyst.model.softwareprovider;

import com.example.demyst.model.BalanceSheet;
import com.example.demyst.model.BusinessDetails;

public interface AccountingSoftwareProvider {
    BalanceSheet calculateBalanceSheet(BusinessDetails businessDetails);
}
