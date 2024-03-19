package com.boichenko.feature.telegram.emoji;

import com.vdurmont.emoji.EmojiParser;

public enum Icon {

    CHECK(":white_check_mark:"),
    BACK(":back:"),
    EURO(":euro:"),
    DOLLAR(":dollar:"),
    HOME(":house:");

    private String value;

    public String get() {
        return EmojiParser.parseToUnicode(value);
    }

    Icon(String value) {
        this.value = value;
    }
}
