package com.boichenko.feature.telegram.command.settings;

import com.boichenko.feature.telegram.command.Command;
import com.boichenko.feature.telegram.emoji.Icon;
import com.boichenko.logic.ChatSettings;
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
    public void handleCallback(ChatSettings settings, Update update) {
        String answer = update.getCallbackQuery().getData();

        switch (answer){
            case "EUR": {
                //появляется галочка на кнопке
                settings.setCurrency(EUR);
                System.out.println("answer: " + answer);
                break;
            }
            default: {
                settings.setCurrency(USD);
                System.out.println("answer: " + answer);
                break;
            }

        }
    }


}
