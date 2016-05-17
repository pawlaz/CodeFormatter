package org.pawlaz.codeformatter.io.reader;

import org.pawlaz.codeformatter.io.exceptions.ReaderException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Hns on 15.05.2016.
 * IReader implementation for reading files
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
            throw new ReaderException(e);
        }
    }

    /**
     * Reads a single character from file
     * @return Reads a single character.
     * This method will block until a character is available, an read error occurs, or the end of the stream is reached
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
            return inputStream.available() > 0;
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
