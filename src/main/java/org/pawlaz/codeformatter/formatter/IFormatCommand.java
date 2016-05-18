package org.pawlaz.codeformatter.formatter;

/**
 * Created by Hns on 18.05.2016.
 */
@FunctionalInterface
public interface IFormatCommand {
    String format(Character c);
}
