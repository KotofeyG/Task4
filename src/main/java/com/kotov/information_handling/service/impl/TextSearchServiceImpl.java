package com.kotov.information_handling.service.impl;

import com.kotov.information_handling.comparator.NumberOfElementsComparator;
import com.kotov.information_handling.entity.CompositeText;
import com.kotov.information_handling.entity.TextComponent;
import com.kotov.information_handling.exception.TextException;
import com.kotov.information_handling.parser.AbstractTextParser;
import com.kotov.information_handling.parser.LexemeParser;
import com.kotov.information_handling.parser.SentenceParser;
import com.kotov.information_handling.service.TextSearchService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class TextSearchServiceImpl implements TextSearchService {
    @Override
    public List<TextComponent> findLongestWords(TextComponent component) throws TextException {
        AbstractTextParser parser = new LexemeParser();
        TextComponent words = new CompositeText();
        parser.parse(component.toString(), words);

        int longest = words.getChildren().stream()
                .filter(lexemePart -> lexemePart.getClass() == CompositeText.class)
                .max(new NumberOfElementsComparator())
                .get()
                .size();

        List<TextComponent> longestWords = words.getChildren().stream()
                .filter(lexemePart -> lexemePart.getClass() == CompositeText.class)
                .filter(word -> word.size() == longest)
                .distinct()
                .collect(Collectors.toList());
        return longestWords;
    }

    @Override
    public List<TextComponent> findSentencesWithLongestWord(TextComponent text) throws TextException {
        AbstractTextParser parser = new SentenceParser();
        TextComponent sentences = new CompositeText();
        parser.parse(text.toString(), sentences);
        List<TextComponent> longestWords = findLongestWords(text);

        List<TextComponent> result = new ArrayList<>();
        for (TextComponent sentence : sentences.getChildren()) {
            if (sentence.containsAny(longestWords)) {
                result.add(sentence);
            }
        }
        return result;
    }

    @Override
    public List<TextComponent> findSameWords(TextComponent text) throws TextException {         //to do
        AbstractTextParser parser = new LexemeParser();
        TextComponent words = new CompositeText();
        parser.parse(text.toString(), words);
        List<TextComponent> sameWords = new ArrayList<>();
        Iterator<TextComponent> wordIterator = words.getChildren().iterator();

        while (wordIterator.hasNext()) {
            TextComponent nextWord = wordIterator.next();
            wordIterator.remove();
            boolean isWordContained = words.contains(nextWord);
            if (isWordContained) {
                if (!sameWords.contains(nextWord)) {
                    sameWords.add(nextWord);
                }
            }
        }
        return sameWords;
    }
}