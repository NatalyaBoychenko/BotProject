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

    ChatSettings settings = new ChatSettings();


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


//        List<InlineKeyboardButton> buttons = Stream.of("2", "3", "4", BACK)
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
//        return keyboard;
    }

    public double round (double rate){

        double scale = Math.pow(10, settings.getRoundDigit());
        return Math.ceil(rate *scale) / scale;

    }

    @Override
    public void handleCallback(String  update) {

    }

    public void handleCallback(Update update) {

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


//    public String roundRate (double buy, double sell, CurrencyItem currecy, int roundedIndex){
//        String template = new String("""
//                        Курс в ${bank}: ${currency}/UAN
//                        \nПокупка: ${buy}
//                        \nПродаж: ${sell}
//                        """.getBytes(), StandardCharsets.UTF_8);
//
//
//
//        String roundedBuy = "";
//        String roundedSell = "";
//        switch(roundedIndex){
//            case 3:
//                roundedBuy = String.format("%.3f", buy, roundedIndex);
//                roundedSell = String.format("%.3f", sell);
//                break;
//            case 4:
//                roundedBuy = String.format("%.4f", buy, roundedIndex);
//                roundedSell = String.format("%.4f", sell);
//                break;
//            default:
//                roundedBuy = String.format("%.2f", buy, roundedIndex);
//                roundedSell = String.format("%.2f", sell);
//                break;
//        }
//
//
//
//        return template
//                .replace("${bank}", currecy.name())
//                .replace("${currency}", currecy.name())
//                .replace("${buy}", roundedBuy + "")
//                .replace("${sell}", roundedSell + "");
//    }
//






}
