package org.pawlaz.codeformatter.formatter.experemental;


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
    int getBeginState();

    /**
     * Returns output table
     * @return output table for FSM
     */
    Map<Character, List<FSMCommand>> getOutputTable();

    /**
     * Returns line of output table for other input signals
     * @return line of output table for other input signals
     */
    List<FSMCommand> getDefaultOutput();

    /**
     * Returns transition table for FSM
     * @return transition table for FSM
     */
    Map<Character, List<Integer>> getTransitionTable();

    /**
     * Returns line of transition table for other input signals
     * @return line of transition table for other input signals
     */
    List<Integer> getDefaultTransition();
}
