package org.pawlaz.codeformatter.formatter;

import java.util.Arrays;

/**
 * Created by Hns on 16.05.2016.
 * Helper class for formation formatted strings
 */
public class StringMaker {
    private StringBuilder stringBuilder;

    private int baseOffsetCount;
    private char indentSymbol;
    private char lineSeparator;

    public char getIndentSymbol() {
        return indentSymbol;
    }

    public char getLineSeparator() {
        return lineSeparator;
    }

    /**
     * Constructs an StringMaker with the basic data necessary for it to work
     * @param indentSymbol - symbol used to indent
     * @param baseOffsetCount - offset size
     * @param lineSeparator - symbol used for line separator
     */
    public StringMaker(final char indentSymbol, final int baseOffsetCount, final char lineSeparator) {
        stringBuilder = new StringBuilder();
        this.indentSymbol = indentSymbol;
        this.baseOffsetCount = baseOffsetCount;
        this.lineSeparator = lineSeparator;
    }

    /**
     * add indent symbol to formatted string
     */
    public void addIndentSymbol() {
        stringBuilder.append(indentSymbol);
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
        Arrays.fill(offset, indentSymbol);
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
