package org.pawlaz.codeformatter.formatter;

/**
 * State of finite-state machine
 */
public class State {
    private int index;

    public State(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
