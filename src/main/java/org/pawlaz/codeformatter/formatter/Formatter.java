package org.pawlaz.codeformatter.formatter;

import org.pawlaz.codeformatter.io.reader.IReader;
import org.pawlaz.codeformatter.io.writer.IWriter;

import java.io.IOException;

/**
 * Created by Hns on 15.05.2016.
 * The formatter produces formatting
 */
public class Formatter {
    private StringMaker stringMaker;
    /**
     * base constructor
     */
    public Formatter() {
        stringMaker = new StringMaker();
    }

    /**
     * Produces data formatting
     * @param reader - input source.
     * @param writer - output source.
     */
    public void format(final IReader reader, final IWriter writer) {
        char currentSymbol;
        int bktCount = 0;
        boolean firstInLine = false;
        boolean firstInWord = false;

        try {
            while (true) {
                currentSymbol = (char) reader.read();
                stringMaker.clear();
                if (currentSymbol == '\n') {
                    //ignore
                } else if (currentSymbol == ' ') {
                    firstInWord = true;
                } else if (currentSymbol == ';') {
                    stringMaker.addSymbol(currentSymbol);
                    stringMaker.addLineSeparator();
                    firstInLine = true;
                } else if (currentSymbol == '{') {
                    if (firstInLine) {
                        stringMaker.addOffset(bktCount);
                    } else {
                        stringMaker.addSymbol(' ');
                        bktCount++;
                        stringMaker.addSymbol(currentSymbol);
                        stringMaker.addLineSeparator();
                        firstInLine = true;
                        firstInWord = false;
                    }
                } else if (currentSymbol == '}') {
                    bktCount--;
                    stringMaker.addOffset(bktCount);
                    stringMaker.addSymbol(currentSymbol);
                    if (bktCount > 0)
                        stringMaker.addLineSeparator();

                    firstInLine = true;
                    firstInWord = false;
                } else {
                    if (firstInLine) {
                        stringMaker.addOffset(bktCount);
                        firstInLine = false;
                        firstInWord = false;
                    } else if (firstInWord) {
                        stringMaker.addSymbol(' ');
                        firstInWord = false;
                    }
                    stringMaker.addSymbol(currentSymbol);
                }
                writer.writeString(stringMaker.getResult());
            }
        } catch (Exception e) {
            try {
                reader.close();
                writer.close();
            } catch (IOException ex) {
                e.printStackTrace();
            }
        }
    }
}
