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
     * Write string in file
     * @param s - string to be written
     * @throws WriterException if an write error occurs
     */
    public void writeString(final String s) throws WriterException {
        try {
            outputStream.write(s.getBytes());
        } catch (IOException e) {
            throw new WriterException();
        }
    }

    /**
     * Close outputStream
     * @throws WriterException if an close error occurs
     */
    public void close() throws WriterException {
        try {
            outputStream.close();
        } catch (IOException e) {
            throw new WriterException();
        }
    }
}
