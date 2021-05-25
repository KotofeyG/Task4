package com.kotov.information_handling.service.impl;

import com.kotov.information_handling.entity.CompositeText;
import com.kotov.information_handling.entity.Letter;
import com.kotov.information_handling.entity.TextComponent;
import com.kotov.information_handling.exception.TextException;
import com.kotov.information_handling.parser.AbstractTextParser;
import com.kotov.information_handling.parser.SymbolParser;
import com.kotov.information_handling.parser.LexemeParser;
import com.kotov.information_handling.service.TextCalculateService;

public class TextCalculateServiceImpl implements TextCalculateService {
    private static final String VOWEL_REGEX = "[AaEeIiOoUuYy]";
    private static final String CONSONANT_REGEX = "[A-Za-z&&[^AaEeIiOoUuYy]]";

    @Override
    public int calculateNumberOfVowelsInSentence(TextComponent sentence) throws TextException {
        AbstractTextParser parser = new SymbolParser();
        TextComponent symbols = new CompositeText();
        parser.parse(sentence.toString(), symbols);

        int counter = 0;
        for (TextComponent symbol : symbols.getChildren()) {
            if (symbol.getClass() == Letter.class) {
                if (symbol.toString().matches(VOWEL_REGEX)) {
                    counter++;
                }
            }
        }
        return counter;
    }

    @Override
    public int calculateNumberOfConsonantsInSentence(TextComponent sentence) throws TextException {
        AbstractTextParser parser = new SymbolParser();
        TextComponent symbols = new CompositeText();
        parser.parse(sentence.toString(), symbols);

        int counter = 0;
        for (TextComponent symbol : symbols.getChildren()) {
            if (symbol.getClass() == Letter.class) {
                if (symbol.toString().matches(CONSONANT_REGEX)) {
                    counter++;
                }
            }
        }
        return counter;
    }

    @Override
    public int calculateNumberOfWordInText(TextComponent text, TextComponent word) throws TextException {
        AbstractTextParser parser = new LexemeParser();
        TextComponent words = new CompositeText();
        parser.parse(text.toString(), words);
        int counter = (int) words.getChildren().stream()
                .filter(lexemePart -> lexemePart.getClass() == CompositeText.class)
                .filter(nextWord -> nextWord.equals(word))
                .count();
        return counter;
    }
}