package org.pawlaz.codeformatter.formatter;

import org.pawlaz.codeformatter.formatter.exceptions.FormatterException;
import org.pawlaz.codeformatter.io.reader.IReader;
import org.pawlaz.codeformatter.io.writer.IWriter;

/**
 * Created by Hns on 21.05.2016.
 * finite-state machine produces formatting
 */
public class Formatter {

    private ITables tables;
    private State beginState;

    /**
     * Constructs an Formatter with tables
     * @param tables - finite-state machine tables
     * @throws FormatterException if an init error occurs
     */
    public Formatter(final ITables tables) throws FormatterException {
        try {
            this.tables = tables;
            beginState = tables.getBeginState();
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
        State currentState = beginState;
        try {
            while (reader.ready()) {
                currentSymbol = reader.read();
                writer.writeString(tables.getOutputSignal(currentSymbol, currentState));
                currentState = tables.getNextState(currentSymbol, currentState);
            }
        } catch (Exception e) {
            throw new FormatterException(e);
        }
    }
}
