package com.boichenko.feature.telegram.command;

import com.boichenko.logic.ChatSettings;
import org.telegram.telegrambots.meta.api.objects.Update;

public abstract class Command{

    private String commandName;

    public Command(String commandName) {
        this.commandName = commandName;
    }

    public abstract void handleCallback(ChatSettings settings, Update update);



}
