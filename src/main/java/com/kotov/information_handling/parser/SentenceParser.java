package com.kotov.information_handling.parser;

import com.kotov.information_handling.entity.CompositeText;
import com.kotov.information_handling.entity.TextComponent;
import com.kotov.information_handling.exception.TextException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser extends AbstractTextParser {
    public static Logger logger = LogManager.getLogger();
    private static final String SENTENCE_REGEX = ".+?([.]{3}|[.!?])\\p{Punct}?((\r\n)|$)?";

    public SentenceParser() {
        parser = new LexemeParser();
    }

    @Override
    public void parse(String paragraph, TextComponent paragraphComponent) throws TextException {
        if (paragraphComponent == null || paragraph == null || paragraph.isBlank()) {
            throw new TextException("Incorrect argument(s) for parsing:\n" + paragraph + "\n" + paragraphComponent);
        }
        Matcher matcher = Pattern.compile(SENTENCE_REGEX, Pattern.DOTALL).matcher(paragraph);
        String sentence;
        TextComponent sentenceComponent;
        while (matcher.find()) {
            sentence = matcher.group();
            sentenceComponent = new CompositeText();
            parser.parse(sentence, sentenceComponent);
            paragraphComponent.add(sentenceComponent);
        }
    }
}