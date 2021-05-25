package com.kotov.information_handling.service;

import com.kotov.information_handling.entity.TextComponent;
import com.kotov.information_handling.exception.TextException;

import java.util.List;

public interface TextSearchService {
    List<TextComponent> findLongestWords(TextComponent text) throws TextException;

    List<TextComponent> findSentencesWithLongestWord(TextComponent text) throws TextException;

    List<TextComponent> findSameWords(TextComponent text) throws TextException;
}