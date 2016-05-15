package org.pawlaz.codeformatter.io.writer;

import org.pawlaz.codeformatter.io.exceptions.WriterException;

import java.io.IOException;

/**
 * Created by Hns on 15.05.2016.
 * IWriter implementation for writing in string
 */
public class StringWriter implements IWriter {
    private StringBuilder stringBuilder;

    /**
     * base constructor
     */
    public StringWriter() {
        this.stringBuilder = new StringBuilder();
    }

    /**
     * Writes single character in string
     * @param c - int specifying a character to be written
     * @throws WriterException if an write error occurs
     */
    public void write(final char c) throws WriterException {
        stringBuilder.append(c);
    }

    /**
     * Writes count of offsets in string
     * @param count - nesting level
     * @throws WriterException if an write error occurs
     */
    public void writeOffset(final int count) throws WriterException {
        for (int i = 0; i < count; i++) {
            stringBuilder.append(OFFSET);
        }
    }

    /**
     * Writes line separator in string
     * @throws WriterException if an write error occurs
     */
    public void writeLineSeparator() throws WriterException {
        stringBuilder.append(System.lineSeparator());
    }

    /**
     * Closes the source
     * @throws IOException if an I/O error occurs
     */
    public void close() throws IOException {
       // stringBuilder.setLength(0);
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}
