package org.pawlaz.codeformatter.io.reader;

import org.pawlaz.codeformatter.io.exceptions.ReaderException;

/**
 * Created by Hns on 15.05.2016.
 */
public interface IReader {
    /**
     * Reads a single character
     * @return The character read, or -1 if the end of the source has been reached
     * @throws ReaderException if an read error occurs
     */
    int read() throws ReaderException;
    
}
