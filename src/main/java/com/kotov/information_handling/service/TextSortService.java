package com.kotov.information_handling.service;

import com.kotov.information_handling.entity.TextComponent;
import com.kotov.information_handling.exception.TextException;

public interface TextSortService {
    TextComponent sortParagraphsByNumberOfSentences(TextComponent text) throws TextException;
}