package com.kotov.information_handling.comparator;

import com.kotov.information_handling.entity.TextComponent;

import java.util.Comparator;

public class NumberOfElementsComparator implements Comparator<TextComponent> {
    @Override
    public int compare(TextComponent component1, TextComponent component2) {
        int result = Integer.compare(component1.size(), component2.size());
        return result;
    }
}