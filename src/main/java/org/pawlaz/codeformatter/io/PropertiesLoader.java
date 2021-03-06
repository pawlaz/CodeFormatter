package org.pawlaz.codeformatter.io;

import org.pawlaz.codeformatter.io.exceptions.PropertiesLoaderException;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by Hns on 18.05.2016.
 * Helper class for loading of properties of formatting
 */
public class PropertiesLoader {

    private char indentSymbol;
    private int baseOffsetCount;
    private char lineSeparator;

    public char getIndentSymbol() {
        return indentSymbol;
    }

    public int getBaseOffsetCount() {
        return baseOffsetCount;
    }

    public char getLineSeparator() {
        return lineSeparator;
    }

    /**
     * base constructor with loading properties
     * @throws PropertiesLoaderException if an loading of properties error occurs
     */
    public PropertiesLoader() throws PropertiesLoaderException {
        try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
            Properties properties = new Properties();
            properties.load(fis);
            indentSymbol = (char) Integer.parseInt(properties.getProperty("indentSymbol"));
            baseOffsetCount = Integer.parseInt(properties.getProperty("baseOffsetCount"));
            lineSeparator = (char) Integer.parseInt(properties.getProperty("lineSeparator"));
        } catch (Exception e) {
            throw new PropertiesLoaderException(e);
        }
    }
}
