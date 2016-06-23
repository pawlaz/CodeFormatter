package org.pawlaz.codeformatter.formatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Hns on 23.06.2016.
 * Implementation of ITablesMaker
 */
public class TablesMaker implements ITablesMaker {

    private List<Character> inputAlf;
    private List<FormatCommand> outputAlf;
    private Map<Character, List<FormatCommand>> outputTable;
    private Map<Character, List<State>> transitionTable;
    private State beginState;
    private StringMaker stringMaker;

    /**
     * Constructs tables for finite-state machine
     * @param indentSymbol - symbol used to indent
     * @param baseOffsetCount - offset size
     * @param lineSeparator - symbol used for line separator
     */
    public TablesMaker(final char indentSymbol, final int baseOffsetCount, final char lineSeparator) {
        this.stringMaker = new StringMaker(indentSymbol, baseOffsetCount, lineSeparator);
        initInputAlf();
        initOutputAlf();
        initOutputTable();
        initTransitionTable();
    }

    private void initOutputAlf() {
        outputAlf = new ArrayList<>();

        outputAlf.add(new FormatCommand() {
            @Override
            String format(final Character c) {
                int nestedLevel = getNestedLevel();
                stringMaker.addLineSeparator();
                nestedLevel++;
                stringMaker.addOffset(nestedLevel);
                stringMaker.addSymbol(c);
                setNestedLevel(nestedLevel);
                return stringMaker.getResult();
            }
        });

        outputAlf.add(new FormatCommand() {
            @Override
            String format(final Character c) {
                int nestedLevel = getNestedLevel();
                stringMaker.addLineSeparator();
                if (nestedLevel > 0) {
                    nestedLevel--;
                }
                stringMaker.addOffset(nestedLevel);
                stringMaker.addSymbol(c);
                setNestedLevel(nestedLevel);
                return stringMaker.getResult();
            }
        });

        outputAlf.add(new FormatCommand() {
            @Override
            String format(final Character c) {
                stringMaker.addSymbol(c);
                return stringMaker.getResult();
            }
        });

        outputAlf.add(new FormatCommand() {
            @Override
            String format(final Character c) {
                stringMaker.addIndentSymbol();
                stringMaker.addSymbol(c);
                return stringMaker.getResult();
            }
        });

        outputAlf.add(new FormatCommand() {
            @Override
            String format(final Character c) {
                return "";
            }
        });

        outputAlf.add(new FormatCommand() {
            @Override
            String format(final Character c) {
                stringMaker.addLineSeparator();
                stringMaker.addOffset(getNestedLevel());
                stringMaker.addSymbol(c);
                return stringMaker.getResult();
            }
        });
    }

    private void initInputAlf() {
        inputAlf = new ArrayList<>();
        inputAlf.add('s'); // symbols (null in tables)
        inputAlf.add('{');
        inputAlf.add('}');
        inputAlf.add(';');
        inputAlf.add(stringMaker.getIndentSymbol());
        inputAlf.add(stringMaker.getLineSeparator());
    }

    private void initTransitionTable() {
        transitionTable = new HashMap<>();

        State stateZero = new State(0);
        State stateOne = new State(1);
        State stateTwo = new State(2);
        State stateThree = new State(3);
        State stateFour = new State(4);
        beginState = stateZero;

        List<State> defaultTransition = new ArrayList<>();
        defaultTransition.add(stateZero);
        defaultTransition.add(stateZero);
        defaultTransition.add(stateZero);
        defaultTransition.add(stateZero);
        defaultTransition.add(stateZero);
        transitionTable.put(null, defaultTransition);

        List<State> line1State = new ArrayList<>();
        line1State.add(stateOne);
        line1State.add(stateOne);
        line1State.add(stateTwo);
        line1State.add(stateThree);
        line1State.add(stateOne);
        transitionTable.put(inputAlf.get(1), line1State);

        List<State> line2State = new ArrayList<>();
        line2State.add(stateZero);
        line2State.add(stateOne);
        line2State.add(stateTwo);
        line2State.add(stateTwo);
        line2State.add(stateFour);
        transitionTable.put(inputAlf.get(2), line2State);

        List<State> line3State = new ArrayList<>();
        line3State.add(stateThree);
        line3State.add(stateThree);
        line3State.add(stateThree);
        line3State.add(stateThree);
        line3State.add(stateThree);
        transitionTable.put(inputAlf.get(3),  line3State);

        List<State> line4State = new ArrayList<>();
        line4State.add(stateFour);
        line4State.add(stateOne);
        line4State.add(stateTwo);
        line4State.add(stateThree);
        line4State.add(stateFour);
        transitionTable.put(inputAlf.get(4),  line4State);

        List<State> line5State = new ArrayList<>();
        line5State.add(stateZero);
        line5State.add(stateOne);
        line5State.add(stateTwo);
        line5State.add(stateThree);
        line5State.add(stateFour);
        transitionTable.put(inputAlf.get(5), line5State);
    }

    private void initOutputTable() {
        outputTable = new HashMap<>();

        List<FormatCommand> defaultOutput = new ArrayList<>();
        defaultOutput.add(outputAlf.get(2));
        defaultOutput.add(outputAlf.get(0));
        defaultOutput.add(outputAlf.get(1));
        defaultOutput.add(outputAlf.get(5));
        defaultOutput.add(outputAlf.get(3));
        outputTable.put(null, defaultOutput);

        List<FormatCommand> lineOneOut = new ArrayList<>();
        lineOneOut.add(outputAlf.get(3));
        lineOneOut.add(outputAlf.get(4));
        lineOneOut.add(outputAlf.get(4));
        lineOneOut.add(outputAlf.get(4));
        lineOneOut.add(outputAlf.get(3));
        outputTable.put(inputAlf.get(1), lineOneOut);

        List<FormatCommand> lineTwoOut = new ArrayList<>();
        lineTwoOut.add(outputAlf.get(4));
        lineTwoOut.add(outputAlf.get(1));
        lineTwoOut.add(outputAlf.get(1));
        lineTwoOut.add(outputAlf.get(1));
        lineTwoOut.add(outputAlf.get(4));
        outputTable.put(inputAlf.get(2), lineTwoOut);

        List<FormatCommand> lineThreeOut = new ArrayList<>();
        lineThreeOut.add(outputAlf.get(2));
        lineThreeOut.add(outputAlf.get(0));
        lineThreeOut.add(outputAlf.get(2));
        lineThreeOut.add(outputAlf.get(2));
        lineThreeOut.add(outputAlf.get(3));
        outputTable.put(inputAlf.get(3), lineThreeOut);

        List<FormatCommand> lineFourOut = new ArrayList<>();
        lineFourOut.add(outputAlf.get(4));
        lineFourOut.add(outputAlf.get(4));
        lineFourOut.add(outputAlf.get(4));
        lineFourOut.add(outputAlf.get(4));
        lineFourOut.add(outputAlf.get(4));
        outputTable.put(inputAlf.get(4), lineFourOut);

        List<FormatCommand> lineFiveOut = new ArrayList<>();
        lineFiveOut.add(outputAlf.get(4));
        lineFiveOut.add(outputAlf.get(4));
        lineFiveOut.add(outputAlf.get(4));
        lineFiveOut.add(outputAlf.get(4));
        lineFiveOut.add(outputAlf.get(4));
        outputTable.put(inputAlf.get(5), lineFiveOut);
    }
    @Override
    public Map<Character, List<FormatCommand>> getOutputTable() {
        return outputTable;
    }

    @Override
    public Map<Character, List<State>> getTransitionTable() {
        return transitionTable;
    }

    @Override
    public State getBeginState() {
        return beginState;
    }
}
