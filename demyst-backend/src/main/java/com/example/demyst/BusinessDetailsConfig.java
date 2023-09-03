package com.example.demyst;

import com.example.demyst.model.BalanceSheet;
import com.example.demyst.model.BalanceSheetItem;
import com.example.demyst.model.BusinessDetails;
import com.example.demyst.repository.AccountingRepository;
import com.example.demyst.repository.BalanceSheetItemRepository;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BusinessDetailsConfig {

    @Bean
    CommandLineRunner commandLineRunner(AccountingRepository accountingRepository, BalanceSheetItemRepository balanceSheetItemRepository) {
        return (args) -> {
            BalanceSheet balanceSheet = new BalanceSheet(List.of(new BalanceSheetItem(2020, 889.9, 8000.0), new BalanceSheetItem(2021, 768.9, 2932.9)));
            BusinessDetails businessDetails = new BusinessDetails("Alex", "alex@gmail.com", LocalDate.of(1999, Month.JULY, 9), "H5 Street, UK", "Sole Proprietor", "Assets", 3329.93, 11200.0, LocalDate.of(2010, Month.JULY, 9), "Xero", 23322.1, 91.2, balanceSheet.getBalanceSheetItems());
            BusinessDetails save = (BusinessDetails)accountingRepository.save(businessDetails);

            for (BalanceSheetItem balanceSheetitem : balanceSheet.getBalanceSheetItems()) {
                balanceSheetitem.setBusinessId(save.getBusinessId());
            }

            balanceSheetItemRepository.saveAll(balanceSheet.getBalanceSheetItems());
        };
    }
}
