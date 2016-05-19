package org.pawlaz.codeformatter.formatter;

/**
 * Created by Hns on 18.05.2016.
 * Basic interface for character processing commands
 */
@FunctionalInterface
public interface IFormatCommand {
    /**
     * Formatting symbol
     * @param c - input character
     * @return formatted string with input character
     */
    String format(Character c);
}
