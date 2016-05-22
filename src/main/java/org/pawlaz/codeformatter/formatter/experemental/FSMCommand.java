package org.pawlaz.codeformatter.formatter.experemental;

/**
 * Created by Hns on 21.05.2016.
 * Basic abstract class for character processing commands using in finite-state machine
 */
public abstract class FSMCommand {

    private static int nestedLevel = 0;

    protected static int getNestedLevel() {
        return nestedLevel;
    }

    protected static void setNestedLevel(int nestedLevel) {
        FSMCommand.nestedLevel = nestedLevel;
    }

    /**
     * Formatting symbol
     * @param c - input character
     * @return formatted string with input character
     */
    abstract String format(Character c);
}
