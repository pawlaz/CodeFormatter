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
     * Writes a string in destination stream.
     * @param s - string to be written
     * @throws WriterException if an write error occurs
     */
    void writeString(String s) throws WriterException;
}
