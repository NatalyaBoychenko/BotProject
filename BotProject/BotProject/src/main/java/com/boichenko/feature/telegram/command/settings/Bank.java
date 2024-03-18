package com.boichenko.feature.telegram.command.settings;

import com.boichenko.feature.currency.MonoBankCurrencyService;
import com.boichenko.feature.currency.NBUCurrencyService;
import com.boichenko.feature.currency.PrivatBankCurrencyService;
import com.boichenko.feature.telegram.command.Command;
import com.boichenko.feature.telegram.emoji.Icon;
import com.boichenko.logic.ChatSettings;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Bank extends Command {

private boolean isExecutedPrivat = false;
private boolean isExecutedMono = false;
private boolean isExecutedNBU = false;

private String getBankButton(ChatSettings setting, String name){
    return setting.getBank().getName().equals(name) ? Icon.CHECK.get() + name : name.toString();
}


    public InlineKeyboardMarkup bankKeyboard(ChatSettings settings){

        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder()
//                        .text(getBankButton(settings, "ПриватБанк"))
                        .text("ПриватБанк")
                        .callbackData("privat")
                        .build()
        ));
        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder()
//                        .text(getBankButton(settings, "MonoBank"))
                        .text( "MonoBank")
                        .callbackData("mono")
                        .build()
        ));
        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder()
//                        .text(getBankButton(settings,"НБУ"))
                        .text("НБУ")
                        .callbackData("nbu")
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


        switch (answer) {
            case "mono" ->
                //появляется галочка на кнопке
            {
                settings.setBank(new MonoBankCurrencyService());
                System.out.println("answer: " + answer);
            }
            case "nbu" -> {
                settings.setBank(new NBUCurrencyService());
                System.out.println("answer: " + answer);
            }
            default -> {
                settings.setBank(new PrivatBankCurrencyService());
                System.out.println("answer: " + answer);
            }
        }
    }


}
