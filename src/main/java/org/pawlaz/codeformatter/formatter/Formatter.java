package org.pawlaz.codeformatter.formatter;

import org.pawlaz.codeformatter.formatter.exceptions.FormatterException;
import org.pawlaz.codeformatter.io.exceptions.ReaderException;
import org.pawlaz.codeformatter.io.exceptions.WriterException;
import org.pawlaz.codeformatter.io.reader.IReader;
import org.pawlaz.codeformatter.io.writer.IWriter;

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
     * @throws FormatterException if an format error occurs
     */
    public void format(final IReader reader, final IWriter writer) throws FormatterException {
        char currentSymbol;
        int bktCount = 0;
        boolean firstInLine = false;
        boolean firstInWord = false;
        try {
            while (reader.ready()) {
                currentSymbol = (char) reader.read();
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
        } catch (ReaderException | WriterException e) {
            throw new FormatterException(e);
        } finally {
            try {
                if (reader != null)
                    reader.close();
                if (writer != null)
                    writer.close();
            } catch (Exception ex) {
                throw new FormatterException(ex);
            }
        }
    }
}