package org.pawlaz.codeformatter.formatter;

import java.util.List;
import java.util.Map;

/**
 * Created by Hns on 22.05.2016.
 * Basic interface for tables of finite-state machine
 */
public interface ITables {

    /**
     * Returns beginState
     * @return number of state from which the FSM starts
     */
    State getBeginState();

    /**
     * Returns output table
     * @return output table for FSM
     */
    Map<Character, List<FormatCommand>> getOutputTable();

    /**
     * Returns line of output table for other input signals (unchecked symbols)
     * @return line of output table for other input signals (unchecked symbols)
     */
    List<FormatCommand> getDefaultOutput();

    /**
     * Returns transition table for FSM
     * @return transition table for FSM
     */
    Map<Character, List<State>> getTransitionTable();

    /**
     * Returns line of transition table for other input signals (unchecked symbols)
     * @return line of transition table for other input signals (unchecked symbols)
     */
    List<State> getDefaultTransition();
}
