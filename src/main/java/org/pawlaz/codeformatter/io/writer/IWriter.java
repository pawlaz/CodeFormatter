package org.pawlaz.codeformatter.io.writer;

import org.pawlaz.codeformatter.io.exceptions.WriterException;

import java.io.Closeable;
import java.io.IOException;

/**
 * Created by Hns on 15.05.2016.
 * Interface for Writers
 */
public interface IWriter extends Closeable {
    /**
     * base offset
     */
    String OFFSET = "    ";

    /**
     * Writes a single character.
     * @param c - int specifying a character to be written
     * @throws WriterException if an write error occurs
     */
    void write(char c) throws WriterException;

    /**
     * Writes a count of offsets.
     * @param count - nesting level
     * @throws WriterException if an write error occurs
     */
    void writeOffset(int count) throws WriterException;

    /**
     * Writes a line separator.
     * @throws WriterException if an write error occurs
     */
    void writeLineSeparator() throws WriterException;

    /**
     *
     * Closes this source
     * @throws IOException if an write error occurs
     */
    void close() throws IOException;
}
