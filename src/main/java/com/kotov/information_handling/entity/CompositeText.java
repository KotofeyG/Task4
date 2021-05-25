package com.kotov.information_handling.entity;

import com.kotov.information_handling.exception.TextException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CompositeText implements TextComponent {
    public static Logger logger = LogManager.getLogger();

    private List<TextComponent> components;

    {
        components = new ArrayList<>();
    }

    public CompositeText() {
    }

    public CompositeText(TextComponent... components) throws TextException {
        if (components == null) {
            throw new TextException(this.getClass().getSimpleName() + " cannot be created. Argument contains null");
        }
        for (TextComponent component : components) {
            this.components.add(component.clone());
        }
    }

    public CompositeText(List<TextComponent> components) throws TextException {
        if (components == null) {
            throw new TextException(this.getClass().getSimpleName() + " cannot be created. Argument contains null");
        }
        for (TextComponent component : components) {
            this.components.add(component.clone());
        }
    }

    @Override
    public boolean add(TextComponent component) throws TextException {
        if (component == null) {
            throw new TextException("Argument cannot be added. It contains null");
        }
        return components.add(component.clone());
    }

    @Override
    public void add(int index, TextComponent component) throws TextException {
        if (component == null) {
            throw new TextException("Argument cannot be added. It contains null");
        }
        components.add(index, component.clone());
    }

    @Override
    public boolean remove(TextComponent component) throws TextException {
        if (component == null) {
            throw new TextException("Argument cannot be removed. It contains null");
        }
        return components.remove(component);
    }

    @Override
    public TextComponent remove(int index) {
        return components.remove(index);
    }

    @Override
    public TextComponent getChild(int index) {
        return components.get(index).clone();
    }

    @Override
    public void setChildren(List<TextComponent> components) throws TextException {
        if (components == null) {
            throw new TextException("Argument cannot be set. It contains null");
        }
        this.components = new ArrayList<>();
        for (TextComponent component : components) {
            this.components.add(component.clone());
        }
    }

    @Override
    public List<TextComponent> getChildren() {
        List<TextComponent> copy = new ArrayList<>();
        for (TextComponent component : components) {
            copy.add(component.clone());
        }
        return copy;
    }

    @Override
    public boolean contains(TextComponent component) throws TextException {
        if (component == null) {
            throw new TextException("Condition cannot be checked. Argument contains null");
        }
        return components.contains(component);
    }

    @Override
    public boolean containsAny(Collection<TextComponent> collection) throws TextException {
        if (collection == null) {
            throw new TextException("Condition cannot be checked. Argument contains null");
        }
        boolean result = false;
        for (TextComponent component : collection) {
            if (contains(component)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public int size() {
        return components.size();
    }

    @Override
    public CompositeText clone() {
        CompositeText copy = null;
        try {
            copy = (CompositeText) super.clone();
            copy.components = new ArrayList<>();
            for (TextComponent component : this.components) {
                copy.components.add(component.clone());
            }
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
        CompositeText other = (CompositeText) otherObject;
        return components.equals(other.components);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + components.hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (TextComponent component : components) {
            result.append(component);
        }
        return result.toString();
    }
}