package org.pawlaz.codeformatter.formatter;

import java.util.HashMap;

/**
 * Created by Hns on 18.05.2016.
 */
public interface IFormatterStrategies {
    HashMap<Character, IFormatCommand> getFormatterStrategies();

    IFormatCommand defaultCommand();
}
