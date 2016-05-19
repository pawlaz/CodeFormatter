package org.pawlaz.codeformatter.io.reader;

import org.pawlaz.codeformatter.io.exceptions.ReaderException;

import java.io.Closeable;

/**
 * Created by Hns on 15.05.2016.
 * Interface for Readers
 */
public interface IReader extends Closeable {
    /**
     * Reads a single character
     * @return The character read
     * @throws ReaderException if an read error occurs
     */
    int read() throws ReaderException;

    /**
     * Checks the availability of reading
     * @return True if the next read() is guaranteed not to block for input, false otherwise
     * @throws ReaderException if an read error occurs
     */
    boolean ready() throws ReaderException;
}
