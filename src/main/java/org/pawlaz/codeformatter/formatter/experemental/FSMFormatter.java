package org.pawlaz.codeformatter.formatter.experemental;

import org.pawlaz.codeformatter.formatter.exceptions.FormatterException;
import org.pawlaz.codeformatter.io.reader.IReader;
import org.pawlaz.codeformatter.io.writer.IWriter;

import java.util.List;
import java.util.Map;

/**
 * Created by Hns on 21.05.2016.
 * finite-state machine produces formatting
 */
public class FSMFormatter {

    private Map<Character, List<FSMCommand>> outputTable;
    private Map<Character, List<Integer>> transitionTable;
    private List<FSMCommand> defaultOutput;
    private List<Integer> defaultTransition;
    private Integer beginState;

    /**
     * Constructs an FSMFormatter with tables
     * @param tables - finite-state machine tables
     * @throws FormatterException if an init error occurs
     */
    public FSMFormatter(final ITables tables) throws FormatterException {
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
        Integer currentState = beginState;
        try {
            while (reader.ready()) {
                currentSymbol = reader.read();
                if (outputTable.containsKey(currentSymbol)) {
                    writer.writeString(outputTable.get(currentSymbol).get(currentState).format(currentSymbol));
                    currentState = transitionTable.get(currentSymbol).get(currentState);
                } else {
                    writer.writeString(defaultOutput.get(currentState).format(currentSymbol));
                    currentState = defaultTransition.get(currentState);
                }
            }
        } catch (Exception e) {
            throw new FormatterException(e);
        }
    }
}
