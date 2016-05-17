package org.pawlaz.codeformatter.io.reader;

import org.pawlaz.codeformatter.io.exceptions.ReaderException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Hns on 15.05.2016.
 * IReader implementation for reading from string
 */
public class StringReader implements IReader {
    private InputStreamReader inputStream;

    /**
     * base constructor
     * @param data - source string
     */
    public StringReader(final String data) {
        inputStream = new InputStreamReader(new ByteArrayInputStream(data.getBytes()));
    }

    /**
     * Reads a single character from string
     * @return The character read, or -1 if the end of the stream has been reached
     * @throws ReaderException if an read error occurs
     */
    public int read() throws ReaderException {
        try {
            return inputStream.read();
        } catch (IOException e) {
            throw new ReaderException(e);
        }
    }

    /**
     * Checks the availability of reading
     * @return True if the next read() is guaranteed not to block for input, false otherwise
     * @throws ReaderException if an read error occurs
     */
    public boolean ready() throws ReaderException {
        try {
            return inputStream.ready();
        } catch (IOException e) {
            throw new ReaderException(e);
        }
    }

    /**
     * Close inputStream
     * @throws ReaderException if an close error occurs
     */
    public void close() throws ReaderException {
        try {
            inputStream.close();
        } catch (IOException e) {
            throw new ReaderException(e);
        }
    }
}
