package org.pawlaz.codeformatter.formatter;

import java.util.List;
import java.util.Map;

/**
 * Created by Hns on 23.06.2016.
 * Base interface for constructors of tables for FSM
 */
public interface ITablesMaker {
    /**
     * Returns output table
     * @return output table for FSM
     */
    Map<Character, List<FormatCommand>> getOutputTable();


    /**
     * Returns transition table for FSM
     * @return transition table for FSM
     */
    Map<Character, List<State>> getTransitionTable();

    /**
     * Returns beginState
     * @return number of state from which the FSM starts
     */
    State getBeginState();
}
