package org.pawlaz.codeformatter.io;

import org.pawlaz.codeformatter.io.exceptions.PropertiesLoaderException;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by Hns on 18.05.2016.
 */
public class PropertiesLoader {

    private char spaceSymbol;
    private int baseOffsetCount;
    private char lineSeparator;

    public char getSpaceSymbol() {
        return spaceSymbol;
    }

    public int getBaseOffsetCount() {
        return baseOffsetCount;
    }

    public char getLineSeparator() {
        return lineSeparator;
    }

    public PropertiesLoader() throws PropertiesLoaderException {
        try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
            Properties properties = new Properties();
            properties.load(fis);
            spaceSymbol = (char) Integer.parseInt(properties.getProperty("spaceSymbol"));
            baseOffsetCount = Integer.parseInt(properties.getProperty("baseOffsetCount"));
            lineSeparator = (char) Integer.parseInt(properties.getProperty("lineSeparator"));
        } catch (Exception e) {
            throw new PropertiesLoaderException(e);
        }
    }
}
