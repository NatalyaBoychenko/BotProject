package com.boichenko.feature.currency;

import com.boichenko.feature.currency.dto.CurrencyItem;
import com.boichenko.feature.currency.dto.CurrencyNBUItem;
import com.boichenko.logic.BankItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import static com.boichenko.feature.telegram.BotConstants.NBU_URL;

public class NBUCurrencyService extends BankItem implements CurrencyService {
    public NBUCurrencyService() {
        super("NBU");
    }

    @Override
    public double getBuyRate(CurrencyItem currency) {

        List<CurrencyNBUItem> currencyItems = getRate(currency);
        float converted = 0;

        if (currency.name().equalsIgnoreCase("usd")) {
            converted = currencyItems.stream()
                    .filter(it -> it.getR030() == 840)
                    .map(CurrencyNBUItem::getRate)
                    .findFirst()
                    .orElseThrow();
        } else  if (currency.name().equalsIgnoreCase("eur")){
            converted = currencyItems.stream()
                    .filter(it -> it.getR030() == 978)
                    .map(CurrencyNBUItem::getRate)
                    .findFirst()
                    .orElseThrow();
        }

        return converted;
    }

    @Override
    public double getSellRate(CurrencyItem currency) {
        List<CurrencyNBUItem> currencyItems = getRate(currency);

        float converted = 0;

        if (currency.name().equalsIgnoreCase("usd")) {
            converted = currencyItems.stream()
                    .filter(it -> it.getR030() == 840)
                    .map(CurrencyNBUItem::getRate)
                    .findFirst()
                    .orElseThrow();
        } else  if (currency.name().equalsIgnoreCase("eur")){
            converted = currencyItems.stream()
                    .filter(it -> it.getR030() == 978)
                    .map(CurrencyNBUItem::getRate)
                    .findFirst()
                    .orElseThrow();
        }

        return converted;
    }


    private List<CurrencyNBUItem> getRate(CurrencyItem currency) {

        String json;
        try {
            json = Jsoup
                    .connect(NBU_URL)
                    .ignoreContentType(true)
                    .get()
                    .body()
                    .text();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Cannot connect to NBU API");
        }

        Type typeTocken = TypeToken
                .getParameterized(List.class, CurrencyNBUItem.class)
                .getType();
        List<CurrencyNBUItem> currencyItems = new Gson().fromJson(json, typeTocken);

        return currencyItems;
    }
}
