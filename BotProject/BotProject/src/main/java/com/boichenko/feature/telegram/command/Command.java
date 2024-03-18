package com.boichenko.feature.telegram.command;

import com.boichenko.logic.ChatSettings;
import org.telegram.telegrambots.meta.api.objects.Update;

public abstract class Command{


    public abstract void handleCallback(ChatSettings settings, Update update);



    }
