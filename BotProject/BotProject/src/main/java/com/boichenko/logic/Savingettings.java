package com.boichenko.logic;

import java.util.ArrayList;
import java.util.List;

public class Savingettings {
    private final List<ChatSettings> chatSettings;

    public Savingettings() {
        this.chatSettings = new ArrayList<>();
    }




    public boolean containsSettingsForConcreteUser(long chatId) {
        for (ChatSettings chatSetting : chatSettings){
            if(chatSetting.getChatId() == chatId){
                return true;
            }
        }

        return false;

//        return chatSettings.stream().anyMatch(chatSetting -> chatSetting.getChatId() == chatId);
    }


    public void addSetting(long chatId, ChatSettings chatSetting) {
        for (int i = 0; i < chatSettings.size(); i++) {
            if (chatSettings.get(i).getChatId() == chatId) {
                chatSettings.set(i, chatSetting);
                return;
            }
        }
        chatSettings.add(chatSetting);
    }


    public void deleteSetting(long chatId) {
        for (int i = 0; i < chatSettings.size(); i++) {
            if (chatSettings.get(i).getChatId() == chatId) {
                chatSettings.remove(i);
                return;
            }
        }
    }


    public ChatSettings getSettingForConcreteUser(long chatId) {
        for (ChatSettings chatSetting : chatSettings) {
            if (chatSetting.getChatId() == chatId) {
                return chatSetting;
            }
        }
        return null;
    }


    public List<ChatSettings> getListOfSettings() {
        return List.copyOf(chatSettings);
    }
}
