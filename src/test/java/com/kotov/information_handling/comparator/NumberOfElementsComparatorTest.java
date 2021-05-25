package com.kotov.information_handling.comparator;

import com.kotov.information_handling.entity.*;
import com.kotov.information_handling.exception.TextException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class NumberOfElementsComparatorTest {
    NumberOfElementsComparator comparator;

    @BeforeClass
    public void setUpComparator() {
        comparator = new NumberOfElementsComparator();
    }

    @Test
    public void testCompare() throws TextException {
        Letter w = new Letter('w');
        Letter o = new Letter('o');
        Letter r = new Letter('r');
        Letter d = new Letter('d');
        Space space = new Space(' ');
        PunctuationMark period = new PunctuationMark('.');

        TextComponent word = new CompositeText(w, o, r, d);
        TextComponent sentence1 = new CompositeText(word, space, word, period);
        TextComponent sentence2 = new CompositeText(word, period);
        int actual = comparator.compare(sentence1, sentence2);
        int expected = 1;
        assertEquals(actual, expected, "Logic of compare method is incorrectly implemented");
    }
}