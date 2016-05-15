package org.pawlaz.codeformatter.io.writer;

import org.pawlaz.codeformatter.io.exceptions.WriterException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Hns on 15.05.2016.
 * IWriter implementation for working with Files
 */
public class FileWriter implements IWriter {
    private OutputStream outputStream;

    /**
     * Base constructor
     * @param filename - the name of the file to write in
     * @throws WriterException if file not exist
     */
    public FileWriter(final String filename) throws WriterException {
        try {
            this.outputStream = new FileOutputStream(filename);
        } catch (IOException e) {
            throw new WriterException();
        }
    }

    /**
     * Write single character in file
     * @param c - int specifying a character to be written
     * @throws WriterException
     */
    public void write(final char c) throws WriterException {
        try {
            outputStream.write(c);
        } catch (IOException e) {
            throw new WriterException();
        }
    }

    /**
     * Write a count of offsets in file
     * @param count - nesting level
     * @throws WriterException if an write error occurs
     */
    public void writeOffset(final int count) throws WriterException {
        try {
            for (int i = 0; i < count; i++) {
                outputStream.write(OFFSET.getBytes());
            }
        } catch (IOException e) {
            throw new WriterException();
        }
    }

    /**
     * Writes a line separator in file
     * @throws WriterException if an write error occurs
     */
    public void writeLineSeparator() throws WriterException {
        try {
            outputStream.write(System.lineSeparator().getBytes());
        } catch (IOException e) {
            throw new WriterException();
        }
    }

    /**
     * Close outputStream
     * @throws IOException if an I/O error occurs
     */
    public void close() throws IOException {
        try {
            outputStream.close();
        } catch (IOException e) {
            throw new WriterException();
        }
    }
}
