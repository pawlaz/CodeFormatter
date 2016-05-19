package org.pawlaz.codeformatter.formatter;

import java.util.HashMap;

/**
 * Created by Hns on 18.05.2016.
 * Basic interface for formatting strategies
 */
public interface IFormatterStrategies {
    /**
     * Returns map with formatting strategy
     * @return map containing a certain character formatting commands
     */
    HashMap<Character, IFormatCommand> getFormatterStrategies();

    /**
     * Default command for character processing
     * @return default command for character processing
     */
    IFormatCommand defaultCommand();
}
