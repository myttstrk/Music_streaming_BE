package com.musicstreaming.components;

import org.springframework.beans.factory.annotation.Autowired;
import com.musicstreaming.utils.LocalizationUtils;

public class TranslateMessages {
    @Autowired
    private LocalizationUtils localizationUtils;

    // translate message
    protected String translate(String message) {
        return localizationUtils.getLocalizedMessage(message);
    }

    // translate many message
    protected String translate(String message, Object... listMessage) {
        return localizationUtils.getLocalizedMessage(message, listMessage);
    }
}
