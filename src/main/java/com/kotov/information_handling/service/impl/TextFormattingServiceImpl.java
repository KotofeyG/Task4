package com.kotov.information_handling.service.impl;

import com.kotov.information_handling.entity.CompositeText;
import com.kotov.information_handling.entity.TextComponent;
import com.kotov.information_handling.service.TextFormattingService;

import java.util.Iterator;

public class TextFormattingServiceImpl implements TextFormattingService {
    @Override
    public TextComponent removeSentencesWithWordsLessThanNumber(TextComponent text, int number) {   // to do
        TextComponent formattingText = text.clone();
        for (TextComponent paragraph : formattingText.getChildren()) {
            Iterator<TextComponent> sentences = paragraph.getChildren().iterator();
            while (sentences.hasNext()) {
                int count = (int) sentences.next().getChildren().stream()
                        .filter(lexemePart -> lexemePart.getClass() == CompositeText.class)
                        .count();
                if (count < number) {
                    sentences.remove();
                }
            }
        }
        return formattingText;
    }
}