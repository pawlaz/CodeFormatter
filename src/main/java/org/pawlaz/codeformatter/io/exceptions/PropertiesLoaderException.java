package org.pawlaz.codeformatter.io.exceptions;

import java.io.IOException;

/**
 * Created by Hns on 18.05.2016.
 * Signals that an read properties exception of some sort has occurred.
 * This class is the general class of exceptions produced by failed or interrupted properties reading operations.
 */
public class PropertiesLoaderException extends IOException {
    /**
     * Constructs an PropertiesLoaderException with the specified cause
     * @param cause - The cause (A null value is permitted, and indicates that the cause is nonexistent or unknown).
     */
    public PropertiesLoaderException(final Throwable cause) {
        super(cause);
    }
}
