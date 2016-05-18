package org.pawlaz.codeformatter.formatter;

import java.util.HashMap;

/**
 * Created by Hns on 18.05.2016.
 */
public class ProcessStrategies implements  IFormatterStrategies{
    private StringMaker stringMaker;
    private int bktCount = 0;
    private boolean firstInLine = false;
    private boolean firstInWord = false;

    public ProcessStrategies(final char spaceSymbol, final int baseOffsetCount, final char lineSeparator) {
        this.stringMaker = new StringMaker(spaceSymbol, baseOffsetCount, lineSeparator);
    }

    public String lineSeparatorMethod(final Character c) {
        return "";
    }

    public String spaceMethod(final Character c) {
        firstInWord = true;
        return  "";
    }

    public String openBktMethod(final Character c) {
        if (firstInLine) {
            stringMaker.addOffset(bktCount);
        } else {
            stringMaker.addSpace();
            bktCount++;
            stringMaker.addSymbol(c);
            stringMaker.addLineSeparator();
            firstInLine = true;
            firstInWord = false;
        }
        return stringMaker.getResult();
    }

    public String closeBktMethod(final Character c) {
        bktCount--;
        stringMaker.addOffset(bktCount);
        stringMaker.addSymbol(c);
        if (bktCount > 0)
            stringMaker.addLineSeparator();

        firstInLine = true;
        firstInWord = false;
        return stringMaker.getResult();
    }

    public String usualSymbolMethod(final Character c) {
        if (firstInLine) {
            stringMaker.addOffset(bktCount);
            firstInLine = false;
            firstInWord = false;
        } else if (firstInWord) {
            stringMaker.addSpace();
            firstInWord = false;
        }
        stringMaker.addSymbol(c);
        return stringMaker.getResult();
    }

    public String semicolonMethod(final Character c) {
        stringMaker.addSymbol(c);
        stringMaker.addLineSeparator();
        firstInLine = true;
        return stringMaker.getResult();
    }

    @Override
    public HashMap<Character, IFormatCommand> getFormatterStrategies() {
        HashMap<Character, IFormatCommand> strategies = new HashMap<>();
        strategies.put(' ', this::spaceMethod);
        strategies.put('\n', this::lineSeparatorMethod);
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
