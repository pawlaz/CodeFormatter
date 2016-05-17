package org.pawlaz.codeformatter.formatter.exceptions;

/**
 * Created by Hns on 15.05.2016.
 * Signals that an format exception of some sort has occurred.
 * This class is the general class of exceptions produced by failed or interrupted format operations.
 */
public class FormatterException extends Exception {
    /**
     * Constructs an FormatterException with the specified cause
     * @param cause - The cause (A null value is permitted, and indicates that the cause is nonexistent or unknown).
     */
    public FormatterException(final Throwable cause) {
        super(cause);
    }
}
