package com.oekrem.SpringMVCBackEnd.services.bank;

import com.oekrem.SpringMVCBackEnd.services.bank.enums.BankType;
import org.springframework.stereotype.Service;

@Service
public class BinLookupService {

    public BankType getBankByBin(String bin) {
        return BankType.getBankByBin(bin);
    }
}