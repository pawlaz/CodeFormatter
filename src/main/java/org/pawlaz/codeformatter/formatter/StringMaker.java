package org.pawlaz.codeformatter.formatter;

import java.util.Arrays;

/**
 * Created by Hns on 16.05.2016.
 * Class for making lexemes to recording in stream
 */
public class StringMaker {
    private StringBuilder stringBuilder;

    private int baseOffsetCount;
    private char spaceSymbol;
    private char lineSeparator;


    public StringMaker(final char spaceSymbol, final int baseOffsetCount, final char lineSeparator) {
        stringBuilder = new StringBuilder();
        this.spaceSymbol = spaceSymbol;
        this.baseOffsetCount = baseOffsetCount;
        this.lineSeparator = lineSeparator;
    }

    /**
     * add space to formatted string
     */
    public void addSpace() {
        stringBuilder.append(spaceSymbol);
    }

    /**
     * add single character to formatted string
     * @param c - added character
     */
    public void addSymbol(final char c) {
        stringBuilder.append(c);
    }

    /**
     * add line separator to formatted string
     */
    public void addLineSeparator() {
        stringBuilder.append(lineSeparator);
    }

    /**
     * add offset to formatted string
     * @param nestedLevel - nested level (count of offsets)
     */
    public void addOffset(final int nestedLevel) {
        char[] offset = new char[nestedLevel * baseOffsetCount];
        Arrays.fill(offset, spaceSymbol);
        stringBuilder.append(offset);
    }

    /**
     * Returns formatted string
     * @return formatted string
     */
    public String getResult() {
        String s = stringBuilder.toString();
        clear();
        return s;
    }

    /**
     * clears buffer
     */
    public void clear() {
        stringBuilder.setLength(0);
    }
}
