package com.boichenko.logic;

import com.boichenko.feature.currency.MonoBankCurrencyService;
import com.boichenko.feature.currency.NBUCurrencyService;
import com.boichenko.feature.currency.PrivatBankCurrencyService;
import com.boichenko.feature.telegram.command.settings.RoundRate;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

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
        String template = new String("""
                        Курс в ${bank}: ${currency}/UAN 
                        \nПокупка: ${buy} 
                        \nПродаж: ${sell}
                        """.getBytes(), StandardCharsets.UTF_8);



        return template
                .replace("${bank}", settings.getBank().getName())
                .replace("${currency}", settings.getCurrency().name())
                .replace("${buy}", getRoundedRate().get(0) + "")
                .replace("${sell}", getRoundedRate().get(1) + "");
    }



    private List<BigDecimal> getRoundedRate() {

        List<BigDecimal> roundedRate = new ArrayList<>();

        BigDecimal roundedBuy = BigDecimal.valueOf(
                        settings.getBank().getBuyRate(settings.getCurrency()))
                .setScale(settings.getRoundDigit(), RoundingMode.HALF_UP);

        BigDecimal roundedSell = BigDecimal.valueOf(
                        settings.getBank().getSellRate(settings.getCurrency()))
                .setScale(settings.getRoundDigit(), RoundingMode.HALF_UP);

        roundedRate.add(roundedBuy);
        roundedRate.add(roundedSell);

        return roundedRate;

    }
}
