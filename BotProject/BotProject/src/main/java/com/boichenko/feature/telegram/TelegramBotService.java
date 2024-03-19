package com.boichenko.feature.telegram;

import com.boichenko.logic.Savingettings;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class TelegramBotService {

    private CurrencyTelegramBot currencyTelegramBot;
    public TelegramBotService(){
        Savingettings savingettings = new Savingettings();
        currencyTelegramBot = new CurrencyTelegramBot(savingettings);
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(currencyTelegramBot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
