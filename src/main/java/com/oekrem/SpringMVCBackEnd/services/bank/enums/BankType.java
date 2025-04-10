package com.oekrem.SpringMVCBackEnd.services.bank.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum BankType {
    GARANTI_BANK("454671"),
    IS_BANK("589283"),
    YAPI_KREDI("428220"),
    UNKNOWN("000000");

    private final String bin;

    BankType(String bin) {
        this.bin = bin;
    }

    public static BankType getBankByBin(String bin) {
        return Arrays.stream(values())
                .filter(bank -> bank.bin.equals(bin))
                .findFirst()
                .orElse(UNKNOWN);
    }
}
