package com.boichenko.feature.telegram.command.settings;

import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.boichenko.feature.telegram.BotConstants.*;

//public class SettingCommand extends BotCommand {
public class SettingCommand {

    public InlineKeyboardMarkup setKeyboard() {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        buttons.add(Arrays.asList(
            InlineKeyboardButton.builder().text(ROUNDED_INDEX).callbackData("ROUNDED_INDEX").build()
        ));
        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder().text(BANK).callbackData("BANK").build()
        ));
        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder().text(CURRENCY).callbackData("CURRENCY").build()
        ));
        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder().text(REMINDER_TIME).callbackData("REMINDER_TIME").build()
        ));
        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder().text(HOME).callbackData("HOME").build()
        ));


        InlineKeyboardMarkup keyboard = InlineKeyboardMarkup.builder().keyboard(buttons).build();

        return keyboard;
    }



}
