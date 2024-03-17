package com.boichenko.feature.telegram.command.settings;

import com.boichenko.feature.currency.MonoBankCurrencyService;
import com.boichenko.feature.currency.NBUCurrencyService;
import com.boichenko.feature.currency.PrivatBankCurrencyService;
import com.boichenko.feature.telegram.command.Command;
import com.boichenko.logic.ChatSettings;
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
    ChatSettings settings = new ChatSettings();

    public Reminder() {
        super("reminder");
    }

    public void sendRemainder(String message, int time){
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(()-> System.out.println(message), 0, settings.getReminderTime(), TimeUnit.HOURS);
    }

    public InlineKeyboardMarkup remainderKeyboard(){

//        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
//        List<InlineKeyboardButton> buttons = new ArrayList<>();
//
//        for (int i = 0; i < 13; i++) {
//            buttons.add(new InlineKeyboardButton());
//        }
//
//        for (int i = 0; i < 10; i++) {
//            buttons.get(i).setText(String.valueOf(i+9));
//            buttons.get(i).setCallbackData(String.valueOf(i+9));
//        }
//
//        buttons.get(10).setText(CANCEL_REMINDER);
//        buttons.get(10).setCallbackData("0");
//
//        buttons.get(11).setText(BACK);
//        buttons.get(11).setCallbackData(BACK);
//
//        buttons.get(12).setText(HOME);
//        buttons.get(12).setCallbackData(HOME);
//
//        List<InlineKeyboardButton> row1 = new ArrayList<>();
//        List<InlineKeyboardButton> row2 = new ArrayList<>();
//        List<InlineKeyboardButton> row3 = new ArrayList<>();
//        List<InlineKeyboardButton> row4 = new ArrayList<>();
//        List<InlineKeyboardButton> row5 = new ArrayList<>();
//
//
//        row1.add(buttons.get(0));
//        row1.add(buttons.get(1));
//        row1.add(buttons.get(2));
//        row2.add(buttons.get(3));
//        row2.add(buttons.get(4));
//        row2.add(buttons.get(5));
//        row3.add(buttons.get(6));
//        row3.add(buttons.get(7));
//        row3.add(buttons.get(8));
//        row4.add(buttons.get(9));
//        row4.add(buttons.get(10));
//        row5.add(buttons.get(11));
//        row5.add(buttons.get(12));
//
//
//
//        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
//        rowList.add(row1);
//        rowList.add(row2);
//        rowList.add(row3);
//        rowList.add(row4);
//        rowList.add(row5);
//
//        inlineKeyboardMarkup.setKeyboard(rowList);

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
                InlineKeyboardButton.builder().text(BACK).callbackData(BACK).build(),
                InlineKeyboardButton.builder().text(HOME).callbackData(HOME).build()
        ));

        InlineKeyboardMarkup keyboard = InlineKeyboardMarkup.builder().keyboard(buttons).build();


        return keyboard;
    }


    @Override
    public void handleCallback(Update update) {
        String answer = update.getCallbackQuery().getData();

        switch (answer){
            case "9":
                //появляется галочка на кнопке
                settings.setReminderTime(9);
                break;
            case "10":
                settings.setReminderTime(10);
                break;
            case "11":
                //появляется галочка на кнопке
                settings.setReminderTime(11);
                break;
            case "12":
                settings.setReminderTime(12);
                break;
            case "13":
                //появляется галочка на кнопке
                settings.setReminderTime(13);
                break;
            case "14":
                settings.setReminderTime(14);
                break;
            case "15":
                //появляется галочка на кнопке
                settings.setReminderTime(15);
                break;
            case "16":
                settings.setReminderTime(16);
                break;
            case "17":
                //появляется галочка на кнопке
                settings.setReminderTime(17);
                break;
            case "18":
                settings.setReminderTime(18);
                break;
            default:
                settings.setReminderTime(0);
                break;
        }
    }

    @Override
    public void handleCallback(String update) {

    }
}
