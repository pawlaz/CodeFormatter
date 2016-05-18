package org.pawlaz.codeformatter.io.writer;

import org.pawlaz.codeformatter.io.exceptions.WriterException;

/**
 * Created by Hns on 15.05.2016.
 * IWriter implementation for writing in string
 */
public class StringWriter implements IWriter {
    private StringBuilder stringBuilder;

    /**
     * base constructor
     */
    public StringWriter() {
        this.stringBuilder = new StringBuilder();
    }

    /**
     * Adds a string token to string
     * @param s - string to be written
     * @throws WriterException if an write error occurs
     */
    public void writeString(final String s)throws WriterException {
        stringBuilder.append(s);
    }

    /**
     * Closes the source
     * @throws WriterException if an close error occurs
     */
    @Override
    public void close() throws WriterException {
        //как корректно освободить ресурсы?
       // stringBuilder = null;
    }

    @Override
    public String toString() {
        return stringBuilder.toString();
    }
}
