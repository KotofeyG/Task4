package com.kotov.information_handling.parser;

import com.kotov.information_handling.entity.CompositeText;
import com.kotov.information_handling.entity.TextComponent;
import com.kotov.information_handling.exception.TextException;
import com.kotov.information_handling.factory.CompositeLeafFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser extends AbstractTextParser {
    public static Logger logger = LogManager.getLogger();
    private static final String NOT_A_LETTER_REGEX = "\\P{Alpha}";
    private static final String LEXEME_ELEMENT_REGEX = "(\\b\\w+'?\\w*\\b)|\\s|\\p{Punct}";

    public LexemeParser() {
        parser = new SymbolParser();
    }

    @Override
    public void parse(String sentence, TextComponent sentenceComponent) throws TextException {
        if (sentenceComponent == null || sentence == null || sentence.isBlank()) {
            throw new TextException("Incorrect argument(s) for parsing:\n" + sentence + "\n" + sentenceComponent);
        }
        Matcher matcher = Pattern.compile(LEXEME_ELEMENT_REGEX).matcher(sentence);
        String lexemePart;
        TextComponent lexemePartComponent;

        while (matcher.find()) {
            lexemePart = matcher.group();
            if (lexemePart.matches(NOT_A_LETTER_REGEX)) {
                lexemePartComponent = CompositeLeafFactory.createLeaf(lexemePart);
            } else {
                lexemePartComponent = new CompositeText();
                parser.parse(lexemePart, lexemePartComponent);
            }
            sentenceComponent.add(lexemePartComponent);
        }
    }
}