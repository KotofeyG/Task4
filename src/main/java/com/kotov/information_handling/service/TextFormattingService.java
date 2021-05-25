package com.kotov.information_handling.service;

import com.kotov.information_handling.entity.TextComponent;

public interface TextFormattingService {
    TextComponent removeSentencesWithWordsLessThanNumber(TextComponent text, int number);
}