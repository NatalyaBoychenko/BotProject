package com.boichenko.feature.telegram.command.settings;

import com.boichenko.feature.currency.dto.CurrencyItem;
import com.boichenko.feature.telegram.command.Command;
import com.boichenko.feature.telegram.emoji.Icon;
import com.boichenko.logic.ChatSettings;
import com.boichenko.logic.Savingettings;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.boichenko.feature.currency.dto.CurrencyItem.EUR;
import static com.boichenko.feature.currency.dto.CurrencyItem.USD;
import static com.boichenko.feature.telegram.BotConstants.BACK;

public class Currency extends Command {

//    public Currency() {
//        super("currency");
//    }

    public InlineKeyboardMarkup currencyKeyboard(){

        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder()
                        .text(USD.name() + " " + Icon.DOLLAR.get())
                        .callbackData(USD.name())
                        .build()
        ));
        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder()
                        .text(USD.name() + " " + Icon.EURO.get())
                        .callbackData(EUR.name())
                        .build()
        ));
        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder()
                        .text(Icon.BACK.get())
                        .callbackData("BACK")
                        .build()
        ));


        InlineKeyboardMarkup keyboard = InlineKeyboardMarkup.builder().keyboard(buttons).build();

        return keyboard;
    }

    @Override
    public void handleCallback(ChatSettings settings, Savingettings savedSettings, Update update) {
        String answer = update.getCallbackQuery().getData();
        List<CurrencyItem> currencies = settings.getCurrencies();

        switch (answer){
            case "EUR": {

                if (settings.getCurrencies().contains(EUR)){
                    currencies.remove(EUR);
                } else {
                    currencies.add(EUR);
                }
                settings.setCurrencies(currencies);
                savedSettings.addSetting(settings.getChatId(), settings);
                System.out.println("answer: " + answer);
                break;
            }
            default: {

                if (settings.getCurrencies().contains(USD)){
                    currencies.remove(USD);
                } else {
                    currencies.add(USD);
                }
                settings.setCurrencies(currencies);
                savedSettings.addSetting(settings.getChatId(), settings);
                System.out.println("answer: " + answer);
                break;
            }

        }
    }


}
