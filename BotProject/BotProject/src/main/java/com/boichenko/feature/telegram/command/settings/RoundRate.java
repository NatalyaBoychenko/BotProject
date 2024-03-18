package com.boichenko.feature.telegram.command.settings;

import com.boichenko.feature.telegram.command.Command;
import com.boichenko.logic.ChatSettings;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.boichenko.feature.telegram.BotConstants.BACK;

public class RoundRate  extends Command {



    public RoundRate() {
        super("round");
    }


    public InlineKeyboardMarkup setKeyboard() {
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder().text("2").callbackData("2").build()
        ));
        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder().text("3").callbackData("3").build()
        ));
        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder().text("4").callbackData("4").build()
        ));
        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder().text(BACK).callbackData("BACK").build()
        ));


        InlineKeyboardMarkup keyboard = InlineKeyboardMarkup.builder().keyboard(buttons).build();

        return keyboard;

    }

    @Override
    public void handleCallback(ChatSettings settings, Update update) {

        String answer = update.getCallbackQuery().getData();

        switch (answer){
            case "3": {
                //появляется галочка на кнопке
                settings.setRoundDigit(3);
                System.out.println("input 3");
                break;
            }
            case "4": {
                settings.setRoundDigit(4);
                System.out.println("input 4");
                break;
            }
            default: {
                settings.setRoundDigit(2);
                System.out.println("input 2");
                break;
            }

        }
    }


}
