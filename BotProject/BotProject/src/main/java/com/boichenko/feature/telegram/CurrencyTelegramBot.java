package com.boichenko.feature.telegram;

import com.boichenko.feature.currency.CurrencyService;
import com.boichenko.feature.currency.PrivatBankCurrencyService;
import com.boichenko.feature.telegram.command.settings.*;
import com.boichenko.logic.ChatSettings;
import com.boichenko.logic.Exchange;
import com.boichenko.logic.Savingettings;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import com.vdurmont.emoji.EmojiParser;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static com.boichenko.feature.telegram.BotConstants.*;

public class CurrencyTelegramBot extends TelegramLongPollingBot {

    private CurrencyService currencyService;
    private RoundRate roundRate;
    private SettingCommand settingCommand;
    private Reminder reminder;

    private Exchange exchange = new Exchange();
    private Bank bankName;
    private Currency currency;
private Savingettings savingSettings;


    public CurrencyTelegramBot(Savingettings savingSettings) {
        super(BOT_TOKEN);
        currencyService = new PrivatBankCurrencyService();
        roundRate = new RoundRate();
        settingCommand = new SettingCommand();
        reminder = new Reminder();

        bankName = new Bank();
        currency = new Currency();
        this.savingSettings = savingSettings;

//        register(new StartCommand());

    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }


    @Override
//    public void processNonCommandUpdate(Update update) {
    public void onUpdateReceived(Update update) {

        SendMessage responseMessage = new SendMessage();

        if (update.hasCallbackQuery()) {
            long chatId = update.getCallbackQuery().getMessage().getChatId();
            ChatSettings settings = savingSettings.containsSettingsForConcreteUser(chatId) ?
                    savingSettings.getSettingForConcreteUser(chatId) : ChatSettings.getDefaultSettings(chatId);
            handleKeyboard(update, settings, responseMessage);

            try {
                handleSettingKeyboard(update, savingSettings, responseMessage, settings);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }

            try {
                handleButtons(update, responseMessage, savingSettings, settings);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }

        } else {
            echoResponse(update, responseMessage);
        }

        try {
            execute(responseMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }




    private void handleKeyboard(Update update, ChatSettings settings, SendMessage responseMessage) {
        String callbackQuery = update.getCallbackQuery().getData();
        Long chatId = update.getCallbackQuery().getMessage().getChatId();

        if (callbackQuery.equals(INFO)) {
            responseMessage.setChatId(chatId);
            responseMessage.setText(exchange.printMessage(settings));
            responseMessage.setReplyMarkup(standardKeyboard());

        } else  if (callbackQuery.equals(SETTINGS)) {
            responseMessage.setChatId(chatId);
//            responseMessage.setText(new String("Налаштування".getBytes(), StandardCharsets.UTF_8));
            responseMessage.setText("Налаштування");
            responseMessage.setReplyMarkup(settingCommand.setKeyboard());
        }
    }

    private void handleSettingKeyboard(Update update, Savingettings savingSettings, SendMessage responseMessage, ChatSettings settings) throws TelegramApiException {
        String callbackQuery = update.getCallbackQuery().getData();
        Long chatId = update.getCallbackQuery().getMessage().getChatId();

        if (callbackQuery.equals("ROUNDED_INDEX")) {
            responseMessage.setChatId(chatId);
//            responseMessage.setText(new String("Виберіть кількість чисел після коми".getBytes(), StandardCharsets.UTF_8));
            responseMessage.setText("Виберіть кількість чисел після коми");
            responseMessage.setReplyMarkup(new RoundRate().setKeyboard());

        } else if (callbackQuery.equals("BANK")) {
            responseMessage.setChatId(chatId);
//            responseMessage.setText(new String("Банк".getBytes(), StandardCharsets.UTF_8));
            responseMessage.setText("Банк");
            responseMessage.setReplyMarkup(new Bank().bankKeyboard(settings));


        }  else if (callbackQuery.equals("privat") || callbackQuery.equals("mono") || callbackQuery.equals("nbu")) {
            responseMessage.setReplyMarkup(bankName.bankKeyboard(settings));
//            bankName.handleCallback(settings, savingSettings, update);
            System.out.println("choosen bank");

        }

        else if (callbackQuery.equals("CURRENCY")) {
            responseMessage.setChatId(chatId);
//            responseMessage.setText(new String("Валюта".getBytes(), StandardCharsets.UTF_8));
            responseMessage.setText("Валюта");
            responseMessage.setReplyMarkup(new Currency().currencyKeyboard(settings, update));


        } else if (callbackQuery.equals("BACK")) {
            String s = new String(update.getCallbackQuery().getData().getBytes(), StandardCharsets.UTF_16);
            responseMessage.setChatId(chatId);
//            responseMessage.setText(new String("Налаштування".getBytes(), StandardCharsets.UTF_8));
            responseMessage.setText("Налаштування");
            responseMessage.setReplyMarkup(settingCommand.setKeyboard());

        } else if (callbackQuery.equals("HOME")) {
            responseMessage.setChatId(chatId);
//            responseMessage.setText(new String("Виберіть одну зі дій".getBytes(), StandardCharsets.UTF_8));
            responseMessage.setText("Виберіть одну зі дій");
            responseMessage.setReplyMarkup(standardKeyboard());

        } else if (callbackQuery.equals("REMINDER_TIME")) {
            responseMessage.setChatId(chatId);
//            responseMessage.setText(new String("Виберіть час сповіщення (у годинах)".getBytes(), StandardCharsets.UTF_8));
            responseMessage.setText("Виберіть час сповіщення (у годинах)");
            responseMessage.setReplyMarkup(reminder.remainderKeyboard());

        }
    }

    private void handleButtons(Update update, SendMessage responseMessage, Savingettings savedSettings, ChatSettings settings) throws TelegramApiException {
        String callbackQuery = update.getCallbackQuery().getData();

        if (callbackQuery.equals("privat") || callbackQuery.equals("mono") || callbackQuery.equals("nbu")) {
            responseMessage.setReplyMarkup(new Bank().bankKeyboard(settings));
            bankName.handleCallback(settings, savedSettings, update);
//            execute(EditMessageReplyMarkup.builder()
//                    .inlineMessageId("privat")
//                    .chatId(update.getCallbackQuery().getMessage().getChatId())
//                    .replyMarkup(new Bank().bankKeyboard(settings))
//                    .build());
            System.out.println("choosen bank");
        } else
            if (callbackQuery.equals("2") || callbackQuery.equals("3") || callbackQuery.equals("4")) {
            roundRate.handleCallback(settings, savedSettings, update);
            System.out.println("choosen rounded index");
        } else if (callbackQuery.equals("EUR") || callbackQuery.equals("USD")) {
            currency.handleCallback(settings, savedSettings, update);
            System.out.println("choosen currency");
        }
    }

    private static void echoResponse(Update update, SendMessage responseMessage) {
        String message = new String(update.getMessage().getText().getBytes(), StandardCharsets.UTF_8);
//        String response = new String(("Ви ввели: " + message).getBytes(), StandardCharsets.UTF_8);
        String response = ("Ви ввели: " + message);

        responseMessage.setText(response);
        responseMessage.setChatId(update.getMessage().getChatId());
        responseMessage.setReplyMarkup(standardKeyboard());
    }

    private static InlineKeyboardMarkup standardKeyboard() {
        List<InlineKeyboardButton> buttons = Stream.of(INFO, SETTINGS)
                .map(it -> InlineKeyboardButton
                        .builder()
                        .text(it)
                        .callbackData(it)
                        .build())
                .toList();


        InlineKeyboardMarkup keyboard = InlineKeyboardMarkup
                .builder()
                .keyboard(Collections.singleton(buttons))
                .build();
        return keyboard;
    }


}
