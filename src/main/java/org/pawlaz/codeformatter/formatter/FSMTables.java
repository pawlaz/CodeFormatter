package org.pawlaz.codeformatter.formatter;

import java.util.List;
import java.util.Map;

/**
 * Created by Hns on 22.05.2016.
 * Implementation of ITables
 */
public class FSMTables implements ITables {
    private Map<Character, List<FormatCommand>> outputTable;
    private Map<Character, List<State>> transitionTable;
    private State beginState;

    /**
     * Base constructor for FSMTables class
     * @param beginState - begin state of FSM
     * @param outputTable - output table of FSM
     * @param transitionTable - transition table of FSM
     */
    public FSMTables(final State beginState, final Map<Character, List<FormatCommand>> outputTable,
                     final Map<Character, List<State>> transitionTable) {
        this.beginState = beginState;
        this.outputTable = outputTable;
        this.transitionTable = transitionTable;
    }

    @Override
    public State getBeginState() {
        return beginState;
    }

    @Override
    public State getNextState(final char currentSymbol, final State currentState) {
        if (transitionTable.containsKey(currentSymbol)) {
            return transitionTable.get(currentSymbol).get(currentState.getIndex());
        } else {
            return transitionTable.get(null).get(currentState.getIndex());
        }
    }

    @Override
    public String getOutputSignal(final char currentSymbol, final State currentState) {
        if (outputTable.containsKey(currentSymbol)) {
            return outputTable.get(currentSymbol).get(currentState.getIndex()).format(currentSymbol);
        } else {
            return outputTable.get(null).get(currentState.getIndex()).format(currentSymbol);
        }
    }
}
