package com.boichenko.logic;

import com.boichenko.feature.currency.dto.CurrencyItem;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class BankItem {
    private String name;

    public abstract double getBuyRate(CurrencyItem currency);
    public abstract double getSellRate(CurrencyItem currency);
}
