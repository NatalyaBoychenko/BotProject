package com.boichenko.feature.telegram;

import com.boichenko.feature.currency.CurrencyService;
import com.boichenko.feature.currency.PrivatBankCurrencyService;
import com.boichenko.feature.telegram.command.settings.*;
import com.boichenko.logic.ChatSettings;
import com.boichenko.logic.Exchange;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

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
    private ChatSettings settings;
    private Exchange exchange = new Exchange();


    public CurrencyTelegramBot() {
        super(BOT_TOKEN);
        currencyService = new PrivatBankCurrencyService();
        roundRate = new RoundRate();
        settingCommand = new SettingCommand();
        reminder = new Reminder();
        settings = new ChatSettings();

//        register(new StartCommand());
//        register(new HelpCommand());
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

            String callbackQuery = update.getCallbackQuery().getData();
            Long chatId = update.getCallbackQuery().getMessage().getChatId();


            if (callbackQuery.equals(INFO)) {
                String aDefault = exchange.printMessage();
                responseMessage.setChatId(chatId);
                responseMessage.setText(aDefault);
                responseMessage.setReplyMarkup(standardKeyboard());

            } else  if (callbackQuery.equals(SETTINGS)) {

                responseMessage.setChatId(chatId);
                responseMessage.setText(new String("Налаштування".getBytes(), StandardCharsets.UTF_8));
                responseMessage.setReplyMarkup(settingCommand.setKeyboard());

            } else if (callbackQuery.equals("ROUNDED_INDEX")) {
                responseMessage.setChatId(chatId);
                responseMessage.setText(new String("Виберіть кількість чисел після коми".getBytes(), StandardCharsets.UTF_8));
                responseMessage.setReplyMarkup(roundRate.setKeyboard());

                roundRate.handleCallback(update.getCallbackQuery().getData());
                System.out.println("callbackQuery roundedindex = " + callbackQuery);
            }else if (callbackQuery.equals(BANK)) {
                responseMessage.setChatId(chatId);
                responseMessage.setText(new String("Банк".getBytes(), StandardCharsets.UTF_8));
                responseMessage.setReplyMarkup(new Bank().bankKeyboard());
                System.out.println("callbackQuery Банк = " + update.getCallbackQuery().getMessage().getMessageId());
            } else if (callbackQuery.equals(CURRENCY)) {
                responseMessage.setChatId(chatId);
                responseMessage.setText(new String("Валюта".getBytes(), StandardCharsets.UTF_8));
                responseMessage.setReplyMarkup(new Currency().currencyKeyboard());
            } else if (callbackQuery.equals(BACK)) {
                String s = new String(update.getCallbackQuery().getData().getBytes(), StandardCharsets.UTF_16);
                System.out.println("callbackQuery = " + s);
                responseMessage.setChatId(chatId);
                responseMessage.setText(new String("Налаштування".getBytes(), StandardCharsets.UTF_8));
                responseMessage.setReplyMarkup(settingCommand.setKeyboard());
            } else if (callbackQuery.equals(HOME)) {

                responseMessage.setChatId(chatId);
                responseMessage.setText(new String("Виберіть одну зі дій".getBytes(), StandardCharsets.UTF_8));
                responseMessage.setReplyMarkup(standardKeyboard());
            } else if (callbackQuery.equals(REMINDER_TIME)) {
                responseMessage.setChatId(chatId);
                responseMessage.setText(new String("Виберіть час сповіщення (у годинах)".getBytes(), StandardCharsets.UTF_8));
                responseMessage.setReplyMarkup(reminder.remainderKeyboard());
            }
        } else {
            String message = new String(update.getMessage().getText().getBytes(), StandardCharsets.UTF_8);
            String response = new String(("Ви ввели: " + message).getBytes(), StandardCharsets.UTF_8);

            responseMessage.setText(response);
            responseMessage.setChatId(update.getMessage().getChatId());
            responseMessage.setReplyMarkup(standardKeyboard());
        }

        try {
            execute(responseMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

//    @SneakyThrows
//    private String getInfo(long chatId) {
//        CurrencyItem currency = CurrencyItem.valueOf("USD");
//        double currencyBuyRate = currencyService.getBuyRate(currency);
//        double currencySellRate = currencyService.getSellRate(currency);
//        String prettyText = roundRate.roundRate(currencyBuyRate, currencySellRate, currency, 2);
//        return prettyText;
//    }

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
