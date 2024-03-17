package com.boichenko.logic;

import com.boichenko.feature.currency.MonoBankCurrencyService;
import com.boichenko.feature.currency.NBUCurrencyService;
import com.boichenko.feature.currency.PrivatBankCurrencyService;
import com.boichenko.feature.telegram.command.settings.RoundRate;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.nio.charset.StandardCharsets;

import static com.boichenko.feature.currency.dto.CurrencyItem.USD;

public class Exchange {
    Update update;
    PrivatBankCurrencyService privat = new PrivatBankCurrencyService();
     MonoBankCurrencyService mono = new MonoBankCurrencyService();
     NBUCurrencyService nbu = new NBUCurrencyService();

    ChatSettings settings = new ChatSettings(
            2,
            privat,
            0,
            USD
    );
     RoundRate round = new RoundRate();


    public String printMessage() {
//        String template = new String("""
//                        Курс в ${bank}: ${currency}/UAN
//                        \nПокупка: ${buy}
//                        \nПродаж: ${sell}
//                        """.getBytes(), StandardCharsets.UTF_8);
//
//
//        String roundedBuy = getBuyRate();
//        String roundedSell = getSellRate();
//
//
//        return template
//                .replace("${bank}", settings.getBank().getName())
//                .replace("${currency}", settings.getCurrency().name())
//                .replace("${buy}", roundedBuy + "")
//                .replace("${sell}", roundedSell + "");
        String template = new String("""
                        Курс в ${bank}: ${currency}/UAN 
                        \nПокупка: ${buy} 
                        \nПродаж: ${sell}
                        """.getBytes(), StandardCharsets.UTF_8);

        String roundedBuyRate = String.format("%.2f", privat.getBuyRate(USD));
        String roundedSellRate = String.format("%.2f", privat.getSellRate(USD));


        return template
                .replace("${bank}", settings.getBank().getName())
                .replace("${currency}", settings.getCurrency().name())
                .replace("${buy}", roundedBuyRate + "")
                .replace("${sell}", roundedSellRate + "");
    }



    private String getBuyRate() {
        String roundedBuy = "";
//        String roundedSell = "";
        switch(settings.getBank().getName()){
            case "mono":
                roundedBuy = String.valueOf(round.round(mono.getBuyRate(settings.getCurrency())));
//                roundedSell = String.valueOf(round.round(mono.getSellRate(USD)));
                break;
            case "nbu":
                roundedBuy = String.valueOf(round.round(nbu.getBuyRate(settings.getCurrency())));
//                roundedSell = String.valueOf(round.round(nbu.getSellRate(USD)));
            default:
                roundedBuy = String.valueOf(round.round(privat.getBuyRate(settings.getCurrency())));
//                roundedSell = String.valueOf(round.round(privat.getSellRate(USD)));
        }
        return roundedBuy;
    }

    private String getSellRate() {
//        String roundedBuy = "";
        String roundedSell = "";
        switch(settings.getBank().getName()){
            case "mono":
//                roundedBuy = String.valueOf(round.round(mono.getBuyRate(USD)));
                roundedSell = String.valueOf(round.round(mono.getSellRate(settings.getCurrency())));
                break;
            case "nbu":
//                roundedBuy = String.valueOf(round.round(nbu.getBuyRate(USD)));
                roundedSell = String.valueOf(round.round(nbu.getSellRate(settings.getCurrency())));
            default:
//                roundedBuy = String.valueOf(round.round(privat.getBuyRate(USD)));
                roundedSell = String.valueOf(round.round(privat.getSellRate(settings.getCurrency())));
        }
        return roundedSell;
    }
}
