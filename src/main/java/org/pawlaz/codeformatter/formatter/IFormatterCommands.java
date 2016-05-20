package org.pawlaz.codeformatter.formatter;

import java.util.HashMap;

/**
 * Created by Hns on 18.05.2016.
 * Basic interface for formatting strategies
 */
public interface IFormatterCommands {
    /**
     * Returns map with formatting commands
     * @return map containing a certain character formatting commands
     */
    HashMap<Character, FormatCommand> getFormatterCommands();

    /**
     * Default command for character processing
     * @return default command for character processing
     */
    FormatCommand defaultCommand();
}
