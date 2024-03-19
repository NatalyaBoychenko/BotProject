package com.boichenko.logic;

import com.boichenko.feature.currency.MonoBankCurrencyService;
import com.boichenko.feature.currency.NBUCurrencyService;
import com.boichenko.feature.currency.PrivatBankCurrencyService;
import com.boichenko.feature.currency.dto.CurrencyItem;
import com.boichenko.feature.currency.dto.CurrencyPrivatItem;
import com.boichenko.feature.telegram.command.settings.Bank;
import com.boichenko.feature.telegram.command.settings.RoundRate;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.boichenko.feature.currency.dto.CurrencyItem.USD;

public class Exchange {

    public String printMessage(ChatSettings settings) {



        StringBuilder result = new StringBuilder();


        for (int i = 0; i < settings.getCurrencies().size(); i++) {

            result.append("Курс в " + settings.getBank().getName() + "\n");
            result.append(settings.getCurrencies().get(i) + "/UAN");
            result.append("\nПокупка: " + getRoundedBuyRate(settings).get(i));
            result.append("\nПродаж: " + getRoundedSellRate(settings).get(i));
            result.append("\n\n");
        }

        return result.toString();
    }

    private List<BigDecimal> getRoundedBuyRate(ChatSettings settings) {

        List<BigDecimal> roundedRate = new ArrayList<>();
        BigDecimal roundedBuy = new BigDecimal(0);

        for (CurrencyItem currency : settings.getCurrencies()) {

            roundedBuy = BigDecimal.valueOf(
                            settings.getBank().getBuyRate(currency))
                    .setScale(settings.getRoundDigit(), RoundingMode.HALF_UP);
            roundedRate.add(roundedBuy);
        }

        return roundedRate;

    }

    private List<BigDecimal> getRoundedSellRate(ChatSettings settings) {

        List<BigDecimal> roundedRate = new ArrayList<>();
        BigDecimal roundedSell = new BigDecimal(0);

        for (CurrencyItem currency : settings.getCurrencies()) {

            roundedSell = BigDecimal.valueOf(
                            settings.getBank().getSellRate(currency))
                    .setScale(settings.getRoundDigit(), RoundingMode.HALF_UP);
            roundedRate.add(roundedSell);
        }

        return roundedRate;

    }


}
