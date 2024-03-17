package com.boichenko.feature.telegram.command.settings;

import com.boichenko.feature.telegram.command.Command;
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
    ChatSettings settings = new ChatSettings();

    public Currency() {
        super("currency");
    }

    public InlineKeyboardMarkup currencyKeyboard(){
//        List<InlineKeyboardButton> buttons = Stream.of( "USD", "EUR", BACK)
//                .map(it -> InlineKeyboardButton
//                        .builder()
//                        .text(it)
//                        .callbackData(it)
//                        .build())
//                .toList();
//
//
//        InlineKeyboardMarkup keyboard = InlineKeyboardMarkup
//                .builder()
//                .keyboard(Collections.singleton(buttons))
//                .build();
//        return keyboard;

        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder()
                        .text(USD.name())
                        .callbackData(USD.name())
                        .build()
        ));
        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder()
                        .text(EUR.name())
                        .callbackData(EUR.name())
                        .build()
        ));
        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder()
                        .text(BACK)
                        .callbackData(BACK)
                        .build()
        ));


        InlineKeyboardMarkup keyboard = InlineKeyboardMarkup.builder().keyboard(buttons).build();

        return keyboard;
    }

    @Override
    public void handleCallback(Update update) {
        String answer = update.getCallbackQuery().getData();

        switch (answer){
            case "EUR":
                //появляется галочка на кнопке
                settings.setCurrency(EUR);
                break;
            default:
                settings.setCurrency(USD);
                break;

        }
    }

    @Override
    public void handleCallback(String update) {

    }
}
