package com.boichenko.feature.currency.dto;

import lombok.Data;

@Data
public class CurrencyPrivatItem {
    private CurrencyItem ccy;
    private CurrencyItem base_ccy;
    private float buy;
    private float sale;
}
