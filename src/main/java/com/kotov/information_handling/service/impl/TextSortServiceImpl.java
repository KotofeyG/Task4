package com.kotov.information_handling.service.impl;

import com.kotov.information_handling.comparator.NumberOfElementsComparator;
import com.kotov.information_handling.entity.CompositeText;
import com.kotov.information_handling.entity.TextComponent;
import com.kotov.information_handling.exception.TextException;
import com.kotov.information_handling.service.TextSortService;

import java.util.List;
import java.util.stream.Collectors;

public class TextSortServiceImpl implements TextSortService {
    @Override
    public TextComponent sortParagraphsByNumberOfSentences(TextComponent text) throws TextException {
        List<TextComponent> paragraphs = text.getChildren()
                .stream()
                .sorted(new NumberOfElementsComparator())
                .collect(Collectors.toList());
        TextComponent sortedText = new CompositeText(paragraphs);
        return sortedText;
    }
}