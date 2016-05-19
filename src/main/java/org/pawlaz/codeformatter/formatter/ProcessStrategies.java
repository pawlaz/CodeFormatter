package org.pawlaz.codeformatter.formatter;

import java.util.HashMap;

/**
 * Created by Hns on 18.05.2016.
 * Implementation of IFormatterStrategies. It represents methods for formatting certain characters
 */
public class ProcessStrategies implements  IFormatterStrategies {
    private StringMaker stringMaker;
    private int bktCount = 0;
    private boolean firstInLine = false;
    private boolean firstInWord = false;

    /**
     * Constructs an ProcessStrategies with the specified data for character processing strategy
     * @param indentSymbol - symbol used to indent
     * @param baseOffsetCount - offset size
     * @param lineSeparator - symbol used for line separator
     */
    public ProcessStrategies(final char indentSymbol, final int baseOffsetCount, final char lineSeparator) {
        this.stringMaker = new StringMaker(indentSymbol, baseOffsetCount, lineSeparator);
    }

    /**
     * Formatting method for line separator
     * @param c - input character
     * @return formatted string
     */
    private String lineSeparatorMethod(final Character c) {
        return "";
    }

    /**
     * Formatting method for indent symbol
     * @param c - input character
     * @return formatted string
     */
    private String indentMethod(final Character c) {
        firstInWord = true;
        return  "";
    }

    /**
     * Formatting method for open bracket
     * @param c - input character
     * @return formatted string
     */
    private String openBktMethod(final Character c) {
        if (firstInLine) {
            stringMaker.addOffset(bktCount);
        } else {
            stringMaker.addIndentSymbol();
            bktCount++;
            stringMaker.addSymbol(c);
            stringMaker.addLineSeparator();
            firstInLine = true;
            firstInWord = false;
        }
        return stringMaker.getResult();
    }

    /**
     * Formatting method for close bracket
     * @param c - input character
     * @return formatted string
     */
    private String closeBktMethod(final Character c) {
        bktCount--;
        stringMaker.addOffset(bktCount);
        stringMaker.addSymbol(c);
        if (bktCount > 0)
            stringMaker.addLineSeparator();

        firstInLine = true;
        firstInWord = false;
        return stringMaker.getResult();
    }

    /**
     * Formatting method for other characters
     * @param c - input character
     * @return formatted string
     */
    private String usualSymbolMethod(final Character c) {
        if (firstInLine) {
            stringMaker.addOffset(bktCount);
            firstInLine = false;
            firstInWord = false;
        } else if (firstInWord) {
            stringMaker.addIndentSymbol();
            firstInWord = false;
        }
        stringMaker.addSymbol(c);
        return stringMaker.getResult();
    }

    /**
     * Formatting method for semicolon symbol
     * @param c - input character
     * @return formatted string
     */
    private String semicolonMethod(final Character c) {
        stringMaker.addSymbol(c);
        stringMaker.addLineSeparator();
        firstInLine = true;
        return stringMaker.getResult();
    }

    @Override
    public HashMap<Character, IFormatCommand> getFormatterStrategies() {
        HashMap<Character, IFormatCommand> strategies = new HashMap<>();
        strategies.put(stringMaker.getIndentSymbol(), this::indentMethod);
        strategies.put(stringMaker.getLineSeparator(), this::lineSeparatorMethod);
        strategies.put('{', this::openBktMethod);
        strategies.put('}', this::closeBktMethod);
        strategies.put(';', this::semicolonMethod);
        return strategies;
    }

    @Override
    public IFormatCommand defaultCommand() {
        return this::usualSymbolMethod;
    }
}
