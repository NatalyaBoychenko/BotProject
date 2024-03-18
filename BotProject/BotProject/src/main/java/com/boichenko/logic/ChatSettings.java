package com.boichenko.logic;

import com.boichenko.feature.currency.PrivatBankCurrencyService;
import com.boichenko.feature.currency.dto.CurrencyItem;
import lombok.Data;

import java.nio.charset.StandardCharsets;

import static com.boichenko.feature.currency.dto.CurrencyItem.USD;

@Data
public class ChatSettings {
    private long chatId;
    private int roundDigit;
    private BankItem bank;
    private int reminderTime;
    private CurrencyItem currency;
    PrivatBankCurrencyService privat = new PrivatBankCurrencyService();

    public ChatSettings() {
    }

    public ChatSettings(int roundDigit, BankItem bank, int reminderTime, CurrencyItem currency) {
        this.roundDigit = roundDigit;
        this.bank = bank;
        this.reminderTime = reminderTime;
        this.currency = currency;
    }

    public ChatSettings(long chatId, int roundDigit, BankItem bank, int reminderTime, CurrencyItem currency) {
        this.chatId = chatId;
        this.roundDigit = roundDigit;
        this.bank = bank;
        this.reminderTime = reminderTime;
        this.currency = currency;
    }

//    public String getDefault() {
////        CurrencyItem currency = CurrencyItem.valueOf("USD");
////        double currencyBuyRate =privat.getBuyRate(currency);
////        double currencySellRate = privat.getSellRate(currency);
////        String prettyText = new RoundRate().roundRate(currencyBuyRate, currencySellRate, currency, 2);
//
//        String template = new String("""
//                        Курс в ПриватБанк: USD/UAN
//                        \nПокупка: ${buy}
//                        \nПродаж: ${sell}
//                        """.getBytes(), StandardCharsets.UTF_8);
//
//        String roundedBuyRate = String.format("%.2f", privat.getBuyRate(USD));
//        String roundedSellRate = String.format("%.2f", privat.getSellRate(USD));
//
//
//        return template
//                .replace("${buy}", roundedBuyRate + "")
//                .replace("${sell}", roundedSellRate + "");
//
//    }
}
