package org.pawlaz.codeformatter.io.reader;

import org.pawlaz.codeformatter.io.exceptions.ReaderException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Hns on 15.05.2016.
 * IReader implementation for reading from file
 */
public class FileReader implements IReader {
    private InputStream inputStream;

    /**
     * base constructor
     * @param filename - the name of the file to read from
     * @throws ReaderException if an read error occurs
     */
    public FileReader(final String filename) throws ReaderException {
        try {
            inputStream = new FileInputStream(filename);
        } catch (IOException e) {
            throw new ReaderException();
        }
    }

    /**
     * Reads a single character from file
     * @return The current character read from file, or ReaderException if the end of the source has been reached
     * @throws ReaderException if an write error occurs
     */
    public int read() throws ReaderException {
        try {
            int symbol = inputStream.read();
            if (symbol < 0)
                throw new ReaderException();

            return symbol;
        } catch (IOException e) {
            throw new ReaderException();
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
            throw new ReaderException();
        }
    }
}
