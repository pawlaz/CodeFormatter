package org.pawlaz.codeformatter.formatter;

import org.pawlaz.codeformatter.formatter.exceptions.FormatterException;
import org.pawlaz.codeformatter.io.reader.IReader;
import org.pawlaz.codeformatter.io.writer.IWriter;

import java.util.HashMap;

/**
 * Created by Hns on 15.05.2016.
 * The formatter produces formatting
 */
public class Formatter {
    private HashMap<Character, FormatCommand> commandMap;
    private FormatCommand defaultCommand;

    /**
     * Constructs an Formatter with the specified character processing strategy
     * @param formatterCommands - character processing commands
     * @throws FormatterException if an format error occurs
     */
    public Formatter(final IFormatterCommands formatterCommands) throws FormatterException {
        try {
            commandMap = formatterCommands.getFormatterCommands();
            defaultCommand = formatterCommands.defaultCommand();
        } catch (Exception e) {
            throw new FormatterException(e);
        }
    }

    /**
     * Produces data formatting
     * @param reader - input source.
     * @param writer - output source.
     * @throws FormatterException if an format error occurs
     */
    public void format(final IReader reader, final IWriter writer) throws FormatterException {
        char currentSymbol;
        try {
            while (reader.ready()) {
                currentSymbol = reader.read();
                if (commandMap.containsKey(currentSymbol)) {
                    writer.writeString(commandMap.get(currentSymbol).format(currentSymbol));
                } else {
                    writer.writeString(defaultCommand.format(currentSymbol));
                }
            }
        } catch (Exception e) {
            throw new FormatterException(e);
        }
    }
}
