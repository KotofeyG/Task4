package com.kotov.information_handling.factory;

import com.kotov.information_handling.entity.*;
import com.kotov.information_handling.exception.TextException;

public class CompositeLeafFactory {
    private static final byte SINGLE_ELEMENT = 0;
    private static final byte ONE_ELEMENT = 1;

    private enum LeafType {
        LETTER("\\p{Alpha}"), SPACE("\\s"), PUNCTUATION_MARK("\\p{Punct}");
        private String regex;

        LeafType(String regex) {
            this.regex = regex;
        }

        public static LeafType getLeafTypeBySymbol(String symbol) throws TextException {
            LeafType result = null;

            if (symbol.matches(LETTER.regex)) {
                result = LETTER;
            } else if (symbol.matches(SPACE.regex)) {
                result = SPACE;
            } else if (symbol.matches(PUNCTUATION_MARK.regex)) {
                result = PUNCTUATION_MARK;
            }
            if (result == null) {
                throw new TextException("Incorrect argument: " + symbol);
            }
            return result;
        }
    }

    public static TextComponent createLeaf(String symbol) throws TextException {
        if (symbol == null || symbol.length() != ONE_ELEMENT) {
            throw new TextException("Incorrect argument: " + symbol);
        }
        LeafType type = LeafType.getLeafTypeBySymbol(symbol);
        return switch (type) {
            case LETTER -> new Letter(symbol.charAt(SINGLE_ELEMENT));
            case SPACE -> new Space(symbol.charAt(SINGLE_ELEMENT));
            case PUNCTUATION_MARK -> new PunctuationMark(symbol.charAt(SINGLE_ELEMENT));
        };
    }
}