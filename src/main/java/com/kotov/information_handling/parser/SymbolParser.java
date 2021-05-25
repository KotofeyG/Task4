package com.kotov.information_handling.parser;

import com.kotov.information_handling.entity.TextComponent;
import com.kotov.information_handling.exception.TextException;
import com.kotov.information_handling.factory.CompositeLeafFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SymbolParser extends AbstractTextParser {
    public static Logger logger = LogManager.getLogger();
    private static final String SYMBOL_REGEX = ".+?";

    @Override
    public void parse(String lexemePart, TextComponent lexemePartComponent) throws TextException {
        if (lexemePartComponent == null || lexemePart == null || lexemePart.isBlank()) {
            throw new TextException("Incorrect argument(s) for parsing:\n" + lexemePart + "\n" + lexemePartComponent);
        }
        Matcher matcher = Pattern.compile(SYMBOL_REGEX, Pattern.DOTALL).matcher(lexemePart);
        String symbol;
        TextComponent symbolComponent;
        while (matcher.find()) {
            symbol = matcher.group();
            symbolComponent = CompositeLeafFactory.createLeaf(symbol);
            lexemePartComponent.add(symbolComponent);
        }
    }
}