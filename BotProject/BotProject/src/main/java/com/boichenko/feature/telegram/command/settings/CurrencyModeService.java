package com.boichenko.feature.telegram.command.settings;

import com.boichenko.feature.currency.dto.CurrencyItem;

public interface CurrencyModeService {
    static CurrencyModeService getInstance() {
        return new HashMapCurrencyModeService();
    }

    CurrencyItem getOriginalCurrency(long chatId);

    CurrencyItem getTargetCurrency(long chatId);

    void setOriginalCurrency(long chatId, CurrencyItem currency);

    void setTargetCurrency(long chatId, CurrencyItem currency);
}
