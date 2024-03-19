package com.boichenko.feature.telegram.command.settings;

import com.boichenko.feature.currency.dto.CurrencyItem;

import java.util.HashMap;
import java.util.Map;

public class HashMapCurrencyModeService implements CurrencyModeService{
    private final Map<Long, CurrencyItem> originalCurrency = new HashMap<>();
    private final Map<Long, CurrencyItem> targetCurrency = new HashMap<>();


    public HashMapCurrencyModeService() {
        System.out.println("HASHMAP MODE is created");
    }


    public CurrencyItem getOriginalCurrency(long chatId) {
        return originalCurrency.getOrDefault(chatId, CurrencyItem.USD);
    }


    public CurrencyItem getTargetCurrency(long chatId) {
        return targetCurrency.getOrDefault(chatId, CurrencyItem.USD);
    }


    public void setOriginalCurrency(long chatId, CurrencyItem currency) {
        originalCurrency.put(chatId, currency);
    }


    public void setTargetCurrency(long chatId, CurrencyItem currency) {
        targetCurrency.put(chatId, currency);
    }
}
