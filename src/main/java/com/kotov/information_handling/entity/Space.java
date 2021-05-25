package com.kotov.information_handling.entity;

import com.kotov.information_handling.exception.TextException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.List;

public class Space implements TextComponent {
    public static Logger logger = LogManager.getLogger();

    private static final String NOT_A_SPACE_REGEX = "\\S";
    private char symbol;

    public Space(char symbol) throws TextException {
        if (String.valueOf(symbol).matches(NOT_A_SPACE_REGEX)) {
            throw new TextException("Incorrect argument: " + symbol);
        }
        this.symbol = symbol;
    }

    public Space(String symbol) throws TextException {
        if (symbol == null || symbol.matches(NOT_A_SPACE_REGEX)) {
            throw new TextException("Incorrect argument: " + symbol);
        }
        this.symbol = symbol.charAt(SINGLE_ELEMENT);
    }

    public void setSymbol(char symbol) throws TextException {
        if (String.valueOf(symbol).matches(NOT_A_SPACE_REGEX)) {
            throw new TextException("Incorrect argument: " + symbol);
        }
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    @Override
    public int size() {
        return 1;
    }

    @Override
    public Space clone() {
        Space copy = null;
        try {
            copy = (Space) super.clone();
        } catch (CloneNotSupportedException e) {
            logger.log(Level.ERROR, "Cloning failed: " + e);
        }
        return copy;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || getClass() != otherObject.getClass()) {
            return false;
        }
        Space other = (Space) otherObject;
        return this.symbol == other.symbol;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) symbol;
        return result;
    }

    @Override
    public String toString() {
        return String.valueOf(symbol);
    }

    @Override
    public boolean add(TextComponent component) {
        return false;
    }

    @Override
    public void add(int index, TextComponent component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(TextComponent component) {
        return false;
    }

    @Override
    public TextComponent remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public TextComponent getChild(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setChildren(List<TextComponent> components) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<TextComponent> getChildren() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(TextComponent component) {
        return false;
    }

    @Override
    public boolean containsAny(Collection<TextComponent> collection) {
        return false;
    }
}