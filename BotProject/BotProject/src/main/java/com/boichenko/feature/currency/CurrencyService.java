package com.boichenko.feature.currency;

import com.boichenko.feature.currency.dto.CurrencyItem;

import java.io.IOException;

public interface CurrencyService {

    double getBuyRate(CurrencyItem currency) throws IOException;
    double getSellRate(CurrencyItem currency) throws IOException, InterruptedException;
}
