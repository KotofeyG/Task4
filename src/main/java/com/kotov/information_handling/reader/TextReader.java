package com.kotov.information_handling.reader;

import com.kotov.information_handling.exception.TextException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TextReader {
    public static Logger logger = LogManager.getLogger();

    public String readFromFile(String pathToFile) throws TextException {
        if (pathToFile == null) {
            throw new TextException("Reading data is impossible. Argument contains null");
        }
        try {
            String text = Files.readString(Paths.get(pathToFile));
            logger.log(Level.INFO, "Text from " + pathToFile + " is read successfully");
            return text;
        } catch (IOException e) {
            logger.log(Level.ERROR, "Error reading data in " + pathToFile, e);
            throw new TextException("Error reading data in " + pathToFile, e);
        }
    }
}