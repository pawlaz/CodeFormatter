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
     * Closes this source
     * @throws ReaderException if an read error occurs
     */
    void close() throws ReaderException;
}
