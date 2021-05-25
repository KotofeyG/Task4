package com.kotov.information_handling.parser;

import com.kotov.information_handling.entity.CompositeText;
import com.kotov.information_handling.entity.TextComponent;
import com.kotov.information_handling.exception.TextException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParagraphParser extends AbstractTextParser {
    public static Logger logger = LogManager.getLogger();
    private static final int PARAGRAPH_GROUP = 1;
    private static final String PARAGRAPH_REGEX = "( {4}.+?)( {4}|$)";

    public ParagraphParser() {
        parser = new SentenceParser();
    }

    @Override
    public void parse(String text, TextComponent textComponent) throws TextException {
        if (textComponent == null || text == null || text.isBlank()) {
            throw new TextException("Incorrect argument(s) for parsing:\n" + text + "\n" + textComponent);
        }
        Matcher matcher = Pattern.compile(PARAGRAPH_REGEX, Pattern.DOTALL).matcher(text);
        String paragraph;
        TextComponent paragraphComponent;
        int next = 0;
        while (matcher.find(next)) {
            next = matcher.end(PARAGRAPH_GROUP);
            paragraph = matcher.group(PARAGRAPH_GROUP);
            paragraphComponent = new CompositeText();
            parser.parse(paragraph, paragraphComponent);
            textComponent.add(paragraphComponent);
        }
    }
}