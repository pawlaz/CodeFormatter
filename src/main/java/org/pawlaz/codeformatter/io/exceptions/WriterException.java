package org.pawlaz.codeformatter.io.exceptions;

import java.io.IOException;

/**
 * Created by Hns on 15.05.2016.
 * Signals that an write exception of some sort has occurred.
 * This class is the general class of exceptions produced by failed or interrupted write operations.
 */
public class WriterException extends IOException {
    /**
     * Constructs an WriterException with the specified cause
     * @param cause - The cause (A null value is permitted, and indicates that the cause is nonexistent or unknown).
     */
    public WriterException(final Throwable cause) {
        super(cause);

    }
}
