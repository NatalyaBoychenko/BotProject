package com.boichenko.feature.telegram.command;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static com.boichenko.feature.telegram.BotConstants.*;

public class StartCommand extends BotCommand {

    public StartCommand() {
        super("start", "Start command");
    }


    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        String text = "Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют";
        SendMessage message = new SendMessage();
        message.setText(new String(text.getBytes(), StandardCharsets.UTF_8));
        message.setChatId(chat.getId());

        InlineKeyboardMarkup keyboard = standardKeyboard();

        message.setReplyMarkup(keyboard);


        try {
            absSender.execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
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
