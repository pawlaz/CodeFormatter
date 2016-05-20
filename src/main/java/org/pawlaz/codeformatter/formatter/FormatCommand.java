package org.pawlaz.codeformatter.formatter;

/**
 * Created by Hns on 18.05.2016.
 * Basic abstract class for character processing commands
 */

public abstract class FormatCommand {
    /**
     * Formatting symbol
     * @param c - input character
     * @return formatted string with input character
     */
    abstract String format(Character c);
}
