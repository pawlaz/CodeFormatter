package org.pawlaz.codeformatter.formatter;

/**
 * Created by Hns on 16.05.2016.
 * Class for making lexemes to recording in stream
 */
public class StringMaker {
    private StringBuilder stringBuilder;

    private final String BASE_OFFSET = "    ";

    /**
     * base constructor
     */
    public StringMaker() {
        stringBuilder = new StringBuilder();
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
        stringBuilder.append('\n');
    }

    /**
     * add offset to formatted string
     * @param count - count of offsets (nested level)
     */
    public void addOffset(final int count) {
        for (int i = 0; i < count; i++) {
            stringBuilder.append(BASE_OFFSET);
        }
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

    public int getLength() {
        return stringBuilder.length();
    }
}
