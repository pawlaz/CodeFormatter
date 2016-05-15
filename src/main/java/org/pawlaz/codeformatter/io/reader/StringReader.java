package org.pawlaz.codeformatter.io.reader;

import org.pawlaz.codeformatter.io.exceptions.ReaderException;

import java.io.IOException;

/**
 * Created by Hns on 15.05.2016.
 * IReader implementation for reading from string
 */
public class StringReader implements IReader {
    private java.io.StringReader stringReader;

    /**
     * base constructor
     * @param data - source string
     */
    public StringReader(final String data) {
        stringReader = new java.io.StringReader(data);
    }

    /**
     * Reads a single character from string
     * @return The current character read from string, or ReaderException if the end of the source has been reached
     * @throws ReaderException if an read error occurs
     */
    public int read() throws ReaderException {
        try {
            int symbol = stringReader.read();
            if (symbol < 0)
                throw new ReaderException();

            return symbol;
        } catch (IOException e) {
            throw new ReaderException();
        }
    }

    /**
     * Close inputStream
     * @throws IOException if an I/O error occurs
     */
    public void close() throws IOException {
        stringReader.close();
    }
}
