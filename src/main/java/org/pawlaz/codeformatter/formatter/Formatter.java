package org.pawlaz.codeformatter.formatter;

import org.pawlaz.codeformatter.io.reader.IReader;
import org.pawlaz.codeformatter.io.writer.IWriter;

import java.io.IOException;

/**
 * Created by Hns on 15.05.2016.
 * The formatter produces formatting
 */
public class Formatter {
    /**
     * base constructor
     */
    public Formatter() {
    }

    /**
     * Produces data formatting
     * @param reader - input source.
     * @param writer - output source.
     */
    public void format(IReader reader, IWriter writer) {
        char currentSymbol;
        int bktCount = 0;
        boolean firstInLine = false;
        boolean firstInWord = false;

        try {
            while (true) {
                currentSymbol = (char) reader.read();
                if (currentSymbol == '\n') {
                    //ignore
                } else if (currentSymbol == ' ') {
                    firstInWord = true;
                } else if (currentSymbol == ';') {
                    writer.write(currentSymbol);
                    writer.writeLineSeparator();
                    firstInLine = true;
                } else if (currentSymbol == '{') {
                    if (firstInLine)
                        writer.writeOffset(bktCount);
                    else
                        writer.write(' ');
                    bktCount++;
                    writer.write(currentSymbol);
                    writer.writeLineSeparator();
                    firstInLine = true;
                    firstInWord = false;
                } else if (currentSymbol == '}') {
                    bktCount--;
                    writer.writeOffset(bktCount);
                    writer.write(currentSymbol);
                    writer.writeLineSeparator();
                    firstInLine = true;
                    firstInWord = false;
                } else {
                    if (firstInLine) {
                        writer.writeOffset(bktCount);
                        firstInLine = false;
                        firstInWord = false;
                    } else if (firstInWord) {
                        writer.write(' ');
                        firstInWord = false;
                    }
                    writer.write(currentSymbol);
                }

            }
        } catch (Exception e) {
            try {
                reader.close();
                writer.close();
            } catch (IOException ex) {
            }
        }
    }
}
