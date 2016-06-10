package org.pawlaz.codeformatter.formatter;

import org.pawlaz.codeformatter.formatter.exceptions.FormatterException;
import org.pawlaz.codeformatter.io.reader.IReader;
import org.pawlaz.codeformatter.io.writer.IWriter;

import java.util.List;
import java.util.Map;

/**
 * Created by Hns on 21.05.2016.
 * finite-state machine produces formatting
 */
public class Formatter {

    private Map<Character, List<FormatCommand>> outputTable;
    private Map<Character, List<State>> transitionTable;
    private List<FormatCommand> defaultOutput;
    private List<State> defaultTransition;
    private State beginState;

    /**
     * Constructs an Formatter with tables
     * @param tables - finite-state machine tables
     * @throws FormatterException if an init error occurs
     */
    public Formatter(final ITables tables) throws FormatterException {
        try {
            outputTable = tables.getOutputTable();
            transitionTable = tables.getTransitionTable();
            defaultOutput = tables.getDefaultOutput();
            defaultTransition = tables.getDefaultTransition();
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
                if (outputTable.containsKey(currentSymbol)) {
                    writer.writeString(outputTable.get(currentSymbol).get(currentState.getIndex()).format(currentSymbol));
                    currentState = transitionTable.get(currentSymbol).get(currentState.getIndex());
                } else {
                    writer.writeString(defaultOutput.get(currentState.getIndex()).format(currentSymbol));
                    currentState = defaultTransition.get(currentState.getIndex());
                }
            }
        } catch (Exception e) {
            throw new FormatterException(e);
        }
    }
}
