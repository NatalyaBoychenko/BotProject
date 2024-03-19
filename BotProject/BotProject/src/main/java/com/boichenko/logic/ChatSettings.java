package com.boichenko.logic;

import com.boichenko.feature.currency.PrivatBankCurrencyService;
import com.boichenko.feature.currency.dto.CurrencyItem;
import lombok.Data;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.boichenko.feature.currency.dto.CurrencyItem.USD;

@Data
public class ChatSettings {
    private long chatId;
    private int roundDigit;
    private BankItem bank;
    private int reminderTime;
    private List<CurrencyItem> currencies;

    public ChatSettings(long chatId) {
        this.chatId = chatId;
    }


    public ChatSettings(long chatId, int roundDigit, BankItem bank, int reminderTime, List<CurrencyItem> currencies) {
        this.chatId = chatId;
        this.roundDigit = roundDigit;
        this.bank = bank;
        this.reminderTime = reminderTime;
        this.currencies = currencies;
    }

public static ChatSettings getDefaultSettings(long chatId){

    ChatSettings defaultSetting = new ChatSettings(chatId);
    defaultSetting.setBank(new PrivatBankCurrencyService());
    defaultSetting.setRoundDigit(2);
    List<CurrencyItem> currencyList = new ArrayList<>();

    currencyList.add(CurrencyItem.USD);
    defaultSetting.setCurrencies(currencyList);
    defaultSetting.setReminderTime(13);
    return defaultSetting;

//        return new ChatSettings(
//                chatId,
//                2,
//                new PrivatBankCurrencyService(),
//                0,
//                new ArrayList<CurrencyItem>(List.of(USD)));

}

}
