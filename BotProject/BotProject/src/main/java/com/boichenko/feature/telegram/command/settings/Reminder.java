package com.boichenko.feature.telegram.command.settings;

import com.boichenko.feature.currency.MonoBankCurrencyService;
import com.boichenko.feature.currency.NBUCurrencyService;
import com.boichenko.feature.currency.PrivatBankCurrencyService;
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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


import static com.boichenko.feature.telegram.BotConstants.*;

public class Reminder extends Command {
    private Long id;
    private String text;


//    public Reminder() {
//        super("reminder");
//    }

    public void sendRemainder(ChatSettings settings, String message, int time){
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(()-> System.out.println(message), 0, settings.getReminderTime(), TimeUnit.HOURS);
    }

    public InlineKeyboardMarkup remainderKeyboard(){
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder().text("9").callbackData("9").build(),
                InlineKeyboardButton.builder().text("10").callbackData("10").build(),
                InlineKeyboardButton.builder().text("11").callbackData("11").build()
        ));
        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder().text("12").callbackData("12").build(),
                InlineKeyboardButton.builder().text("13").callbackData("13").build(),
                InlineKeyboardButton.builder().text("14").callbackData("14").build()
        ));
        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder().text("15").callbackData("15").build(),
                InlineKeyboardButton.builder().text("16").callbackData("16").build(),
                InlineKeyboardButton.builder().text("17").callbackData("17").build()
        ));
        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder().text("18").callbackData("18").build(),
                InlineKeyboardButton.builder().text(CANCEL_REMINDER).callbackData("0").build()
        ));
        buttons.add(Arrays.asList(
                InlineKeyboardButton.builder().text(Icon.BACK.get()).callbackData("BACK").build(),
                InlineKeyboardButton.builder().text(Icon.HOME.get()).callbackData("HOME").build()
        ));

        InlineKeyboardMarkup keyboard = InlineKeyboardMarkup.builder().keyboard(buttons).build();


        return keyboard;
    }


    @Override
    public void handleCallback(ChatSettings settings, Savingettings savedSettings, Update update) {
        String answer = update.getCallbackQuery().getData();

        switch (answer){
            case "9": {
                settings.setReminderTime(9);
                savedSettings.addSetting(settings.getChatId(), settings);
                break;
            }
            case "10": {
                settings.setReminderTime(10);
                savedSettings.addSetting(settings.getChatId(), settings);
                break;
            }
            case "11": {
                settings.setReminderTime(11);
                savedSettings.addSetting(settings.getChatId(), settings);
                break;
            }
            case "12": {
                settings.setReminderTime(12);
                savedSettings.addSetting(settings.getChatId(), settings);
                break;
            }
            case "13": {
                settings.setReminderTime(13);
                savedSettings.addSetting(settings.getChatId(), settings);
                break;
            }
            case "14": {
                settings.setReminderTime(14);
                savedSettings.addSetting(settings.getChatId(), settings);
                break;
            }
            case "15": {
                settings.setReminderTime(15);
                savedSettings.addSetting(settings.getChatId(), settings);
                break;
            }
            case "16": {
                settings.setReminderTime(16);
                savedSettings.addSetting(settings.getChatId(), settings);
                break;
            }
            case "17": {
                settings.setReminderTime(17);
                savedSettings.addSetting(settings.getChatId(), settings);
                break;
            }
            case "18": {
                settings.setReminderTime(18);
                savedSettings.addSetting(settings.getChatId(), settings);
                break;
            }
            default: {
                settings.setReminderTime(0);
                savedSettings.addSetting(settings.getChatId(), settings);
                break;
            }
        }
    }


}
