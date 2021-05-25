package com.kotov.information_handling.service;

import com.kotov.information_handling.entity.TextComponent;
import com.kotov.information_handling.exception.TextException;

public interface TextCalculateService {
    int calculateNumberOfVowelsInSentence(TextComponent sentence) throws TextException;

    int calculateNumberOfConsonantsInSentence(TextComponent sentence) throws TextException;

    int calculateNumberOfWordInText(TextComponent text, TextComponent word) throws TextException;
}