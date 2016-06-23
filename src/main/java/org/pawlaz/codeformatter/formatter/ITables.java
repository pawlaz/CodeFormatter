package org.pawlaz.codeformatter.formatter;

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
     * Returns next state of FSM
     * @param currentSymbol - current input symbol of FSM
     * @param currentState - current state of FSM
     * @return next state of FSM
     */
    State getNextState(final char currentSymbol, final State currentState);

    /**
     * Returns output signal of FSM
     * @param currentSymbol - current input symbol of FSM
     * @param currentState - current state of FSM
     * @return output signal of FSM
     */
    String getOutputSignal(final char currentSymbol, final State currentState);

}
