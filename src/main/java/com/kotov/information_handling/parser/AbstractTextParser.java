package com.kotov.information_handling.parser;

import com.kotov.information_handling.entity.TextComponent;
import com.kotov.information_handling.exception.TextException;

public abstract class AbstractTextParser {
    protected AbstractTextParser parser;

    public abstract void parse(String data, TextComponent component) throws TextException;
}