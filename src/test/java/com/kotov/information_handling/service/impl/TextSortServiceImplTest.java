package com.kotov.information_handling.service.impl;

import com.kotov.information_handling.entity.CompositeText;
import com.kotov.information_handling.entity.TextComponent;
import com.kotov.information_handling.exception.TextException;
import com.kotov.information_handling.parser.AbstractTextParser;
import com.kotov.information_handling.parser.ParagraphParser;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TextSortServiceImplTest {
    TextSortServiceImpl service;

    @BeforeClass
    public void setUpService() {
        service = new TextSortServiceImpl();
    }

    @Test
    public void testSortTextByParagraphs() throws TextException {
        String text = "    First paragraph. Second sentence. Third sentence." +
                "    Second paragraph." +
                "    Third paragraph. Second sentence.";
        AbstractTextParser parser = new ParagraphParser();
        TextComponent component = new CompositeText();
        parser.parse(text, component);
        TextComponent actual = service.sortParagraphsByNumberOfSentences(component);
        String sortedText = "    Second paragraph." +
                "    Third paragraph. Second sentence." +
                "    First paragraph. Second sentence. Third sentence.";
        TextComponent expected = new CompositeText();
        parser.parse(sortedText, expected);
        assertEquals(actual.toString(), expected.toString(), "");
    }
}