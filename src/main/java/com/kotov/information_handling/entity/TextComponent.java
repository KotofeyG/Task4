package com.kotov.information_handling.entity;

import com.kotov.information_handling.exception.TextException;

import java.util.Collection;
import java.util.List;

public interface TextComponent extends Cloneable {
    byte SINGLE_ELEMENT = 0;

    boolean add(TextComponent component) throws TextException;

    void add(int index, TextComponent component) throws TextException;

    boolean remove(TextComponent component) throws TextException;

    TextComponent remove(int index);

    TextComponent getChild(int index);

    void setChildren(List<TextComponent> components) throws TextException;

    List<TextComponent> getChildren();

    boolean contains(TextComponent component) throws TextException;

    boolean containsAny(Collection<TextComponent> collection) throws TextException;

    int size();

    TextComponent clone();
}