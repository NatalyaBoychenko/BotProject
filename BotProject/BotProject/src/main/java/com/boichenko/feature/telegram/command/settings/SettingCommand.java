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


//    public SettingCommand() {
//        super("setting", "setting command");
//    }
//
//
//    @Override
//    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
//        String text = "Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют";
//        SendMessage message = new SendMessage();
//        message.setText(new String(text.getBytes(), StandardCharsets.UTF_8));
//        message.setChatId(chat.getId());
//
//        InlineKeyboardMarkup keyboard = setkeyboard();
//
//        message.setReplyMarkup(keyboard);
//
//
//        try {
//            absSender.execute(message);
//        } catch (TelegramApiException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public  InlineKeyboardMarkup setReplyMarkup() {
//        List<InlineKeyboardButton> buttons = Stream.of(ROUNDED_INDEX, BANK, CURRENCY, REMINDER_TIME)
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
//
//
//        return keyboard;
//
//
//    }

    public InlineKeyboardMarkup setKeyboard() {

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
//        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
//        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
//        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
//        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
//        InlineKeyboardButton inlineKeyboardButton5 = new InlineKeyboardButton();
//
//        inlineKeyboardButton1.setText(ROUNDED_INDEX);
//        inlineKeyboardButton1.setCallbackData(ROUNDED_INDEX);
//        inlineKeyboardButton2.setText(BANK);
//        inlineKeyboardButton2.setCallbackData(BANK); //Отклик на нажатие кнопки
//        inlineKeyboardButton3.setText(CURRENCY);
//        inlineKeyboardButton3.setCallbackData(CURRENCY); //Отклик на нажатие кнопки
//        inlineKeyboardButton4.setText(REMINDER_TIME);
//        inlineKeyboardButton4.setCallbackData(REMINDER_TIME); //Отклик на нажатие кнопки
//        inlineKeyboardButton5.setText(HOME);
//        inlineKeyboardButton5.setCallbackData(HOME); //Отклик на нажатие кнопки
//
//
//        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
//        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
//        List<InlineKeyboardButton> keyboardButtonsRow3 = new ArrayList<>();
//        List<InlineKeyboardButton> keyboardButtonsRow4 = new ArrayList<>();
//        List<InlineKeyboardButton> keyboardButtonsRow5 = new ArrayList<>();
//
//
//        keyboardButtonsRow1.add(inlineKeyboardButton1);
//        keyboardButtonsRow2.add(inlineKeyboardButton2);
//        keyboardButtonsRow3.add(inlineKeyboardButton3);
//        keyboardButtonsRow4.add(inlineKeyboardButton4);
//        keyboardButtonsRow5.add(inlineKeyboardButton5);
//
//
//        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
//        rowList.add(keyboardButtonsRow1);
//        rowList.add(keyboardButtonsRow2);
//        rowList.add(keyboardButtonsRow3);
//        rowList.add(keyboardButtonsRow4);
//        rowList.add(keyboardButtonsRow5);
//
//        inlineKeyboardMarkup.setKeyboard(rowList);

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
