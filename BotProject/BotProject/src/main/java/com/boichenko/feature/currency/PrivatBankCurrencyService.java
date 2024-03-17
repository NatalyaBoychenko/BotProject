package com.boichenko.feature.currency;

import com.boichenko.feature.currency.dto.CurrencyItem;
import com.boichenko.feature.currency.dto.CurrencyPrivatItem;
import com.boichenko.logic.BankItem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrivatBankCurrencyService extends BankItem implements CurrencyService{
    public PrivatBankCurrencyService() {
        super("PrivatBank");
    }
//
//    @Override
//    public double getBuyRate(CurrencyItem currency) {

//        List<CurrencyPrivatItem> currencyItems = getRate(currency);
//
//        Float converted = currencyItems.stream()
//                .filter(it -> it.getCcy() == currency)
//                .filter(it -> it.getBase_ccy() == CurrencyItem.UAH)
//                .map(CurrencyPrivatItem::getBuy)
//                .findFirst()
//                .orElseThrow();
//
//        return converted;
//    }
//
//    @Override
//    public double getSellRate(CurrencyItem currency) {
//        List<CurrencyPrivatItem> currencyItems = getRate(currency);
//
//        Float converted = currencyItems.stream()
//                .filter(it -> it.getCcy() == currency)
//                .filter(it -> it.getBase_ccy() == CurrencyItem.UAH)
//                .map(CurrencyPrivatItem::getSale)
//                .findFirst()
//                .orElseThrow();
//
//        return converted;
//    }
//
//
//    private List<CurrencyPrivatItem> getRate(CurrencyItem currency) {
//
//        String json;
//        try {
//            json = Jsoup
//                    .connect(PRIVAT_URL)
//                    .ignoreContentType(true)
//                    .get()
//                    .body()
//                    .text();
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new IllegalStateException("Cannot connect to Privat API");
//        }
//
//        Type typeTocken = TypeToken
//                .getParameterized(List.class, CurrencyPrivatItem.class)
//                .getType();
//        List<CurrencyPrivatItem> currencyItems = new Gson().fromJson(json, typeTocken);
//
//        return currencyItems;
//    }

    @Override
    public double getBuyRate(CurrencyItem currency) {

        return getCurrenciesOfBank().stream()
                .filter(x -> x.getCcy() == currency)
                .map(x-> x.getBuy())
                .findFirst()
                .orElseThrow();
    }

    @Override
    public double getSellRate(CurrencyItem currency) {

        return getCurrenciesOfBank().stream()
                .filter(x -> x.getCcy() == currency)
                .map(x-> x.getSale())
                .findFirst()
                .orElseThrow();
    }

    public List<CurrencyPrivatItem> getCurrenciesOfBank() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        HttpClient client = HttpClient.newHttpClient();
        String url = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";
        //Get JSON
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        //Convert json => Java Object
        CurrencyPrivatItem[] todosArray = gson.fromJson(response.body(), (Type) CurrencyPrivatItem[].class);
        List<CurrencyPrivatItem> curencyList = new ArrayList<>(Arrays.asList(todosArray));
        return curencyList;
    }
}
