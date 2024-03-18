package com.boichenko.feature.telegram.command.settings;

import com.boichenko.feature.currency.MonoBankCurrencyService;
import com.boichenko.feature.currency.NBUCurrencyService;
import com.boichenko.feature.currency.PrivatBankCurrencyService;
import com.boichenko.feature.telegram.command.Command;
import com.boichenko.logic.ChatSettings;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.boichenko.feature.telegram.BotConstants.BACK;

public class Bank extends Command {

    public Bank() {
        super("Bank");
    }


    public InlineKeyboardMarkup bankKeyboard(){

        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder()
                        .text( new String("ПриватБанк".getBytes(), StandardCharsets.UTF_8))
                        .callbackData("privat")
                        .build()
        ));
        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder()
                        .text(new String("МоноБанк".getBytes(), StandardCharsets.UTF_8))
                        .callbackData("mono")
                        .build()
        ));
        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder()
                        .text(new String("НБУ".getBytes(), StandardCharsets.UTF_8))
                        .callbackData("nbu")
                        .build()
        ));
        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder()
                        .text(BACK)
                        .callbackData("BACK")
                        .build()
        ));


        InlineKeyboardMarkup keyboard = InlineKeyboardMarkup.builder().keyboard(buttons).build();

        return keyboard;
    }

    @Override
    public void handleCallback(ChatSettings settings, Update update) {
        String answer = update.getCallbackQuery().getData();


        switch (answer) {
            case "mono" ->
                //появляется галочка на кнопке
            {
                settings.setBank(new MonoBankCurrencyService());
                System.out.println("from handle method mono");
            }
            case "nbu" -> {
                settings.setBank(new NBUCurrencyService());
                System.out.println("from handle method nbu");
            }
            default -> {
                settings.setBank(new PrivatBankCurrencyService());
                System.out.println("from handle method privat");
            }
        }
    }


}
