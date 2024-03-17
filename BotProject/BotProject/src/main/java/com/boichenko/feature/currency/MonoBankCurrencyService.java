package com.boichenko.feature.currency;

import com.boichenko.feature.currency.dto.CurrencyItem;
import com.boichenko.feature.currency.dto.CurrencyMonoItem;
import com.boichenko.logic.BankItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import static com.boichenko.feature.telegram.BotConstants.MONO_URL;

public class MonoBankCurrencyService extends BankItem implements CurrencyService {
    public MonoBankCurrencyService() {
        super("MonoBank");
    }

    @Override
    public double getBuyRate(CurrencyItem currency) {

        List<CurrencyMonoItem> currencyItems = getRate(currency);
        float converted = 0;

        if (currency.name().equalsIgnoreCase("usd")) {
            converted = currencyItems.stream()
                    .filter(it -> it.getCurrencyCodeA() == 840)
                    .filter(it -> it.getCurrencyCodeB() == 980)
                    .map(CurrencyMonoItem::getRateBuy)
                    .findFirst()
                    .orElseThrow();
        } else  if (currency.name().equalsIgnoreCase("eur")){
            converted = currencyItems.stream()
                    .filter(it -> it.getCurrencyCodeA() == 978)
                    .filter(it -> it.getCurrencyCodeB() == 980)
                    .map(CurrencyMonoItem::getRateBuy)
                    .findFirst()
                    .orElseThrow();
        }

        return converted;
    }

    @Override
    public double getSellRate(CurrencyItem currency) {
        List<CurrencyMonoItem> currencyItems = getRate(currency);

        float converted = 0;

        if (currency.name().equalsIgnoreCase("usd")) {
            converted = currencyItems.stream()
                    .filter(it -> it.getCurrencyCodeA() == 840)
                    .filter(it -> it.getCurrencyCodeB() == 980)
                    .map(CurrencyMonoItem::getRateSell)
                    .findFirst()
                    .orElseThrow();
        } else  if (currency.name().equalsIgnoreCase("eur")){
            converted = currencyItems.stream()
                    .filter(it -> it.getCurrencyCodeA() == 978)
                    .filter(it -> it.getCurrencyCodeB() == 980)
                    .map(CurrencyMonoItem::getRateSell)
                    .findFirst()
                    .orElseThrow();
        }

        return converted;
    }


    private List<CurrencyMonoItem> getRate(CurrencyItem currency) {

        String json;
        try {
            json = Jsoup
                    .connect(MONO_URL)
                    .ignoreContentType(true)
                    .get()
                    .body()
                    .text();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalStateException("Cannot connect to Monobank API");
        }

        Type typeTocken = TypeToken
                .getParameterized(List.class, CurrencyMonoItem.class)
                .getType();
        List<CurrencyMonoItem> currencyItems = new Gson().fromJson(json, typeTocken);

        return currencyItems;
    }
}
