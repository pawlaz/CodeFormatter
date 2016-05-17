package org.pawlaz.codeformatter.io.exceptions;

import java.io.IOException;

/**
 * Created by Hns on 15.05.2016.
 * Signals that an read exception of some sort has occurred.
 * This class is the general class of exceptions produced by failed or interrupted read operations.
 */
public class ReaderException extends IOException {
    /**
     * Constructs an ReaderException with the specified cause
     * @param cause - The cause (A null value is permitted, and indicates that the cause is nonexistent or unknown).
     */
    public ReaderException(final Throwable cause) {
        super(cause);
    }
}
