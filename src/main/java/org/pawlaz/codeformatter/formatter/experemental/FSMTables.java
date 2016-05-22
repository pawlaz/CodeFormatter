package org.pawlaz.codeformatter.formatter.experemental;

import org.pawlaz.codeformatter.formatter.StringMaker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Hns on 22.05.2016.
 * Implementation ITables
 */
public class FSMTables implements ITables {
    private List<Character> inputAlf;
    private List<FSMCommand> outputAlf;
    private Map<Character, List<FSMCommand>> outputTable;
    private List<FSMCommand> defaultOutput;
    private Map<Character, List<Integer>> transitionTable;
    private List<Integer> defaultTransition;
    private Integer beginState;

    private StringMaker stringMaker;

    /**
     * Constructs tables for finite-state machine
     * @param indentSymbol - symbol used to indent
     * @param baseOffsetCount - offset size
     * @param lineSeparator - symbol used for line separator
     */
    public FSMTables(final char indentSymbol, final int baseOffsetCount, final char lineSeparator) {
        stringMaker = new StringMaker(indentSymbol, baseOffsetCount, lineSeparator);
        initOutputAlf();
        initInputAlf();
        initTransitionTable();
        initOutputTable();
    }

    private void initOutputAlf() {
        outputAlf = new ArrayList<>();

        outputAlf.add(new FSMCommand() {
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

        outputAlf.add(new FSMCommand() {
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

        outputAlf.add(new FSMCommand() {
            @Override
            String format(final Character c) {
                stringMaker.addSymbol(c);
                return stringMaker.getResult();
            }
        });

        outputAlf.add(new FSMCommand() {
            @Override
            String format(final Character c) {
                stringMaker.addIndentSymbol();
                stringMaker.addSymbol(c);
                return stringMaker.getResult();
            }
        });

        outputAlf.add(new FSMCommand() {
            @Override
            String format(final Character c) {
                return "";
            }
        });

        outputAlf.add(new FSMCommand() {
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
        inputAlf.add('s'); // фикция (unchecked symbols)
        inputAlf.add('{');
        inputAlf.add('}');
        inputAlf.add(';');
        inputAlf.add(stringMaker.getIndentSymbol());
        inputAlf.add(stringMaker.getLineSeparator());
    }

    private void initTransitionTable() {
        transitionTable = new HashMap<>();
        defaultTransition = new ArrayList<>();
        beginState = 0;

        defaultTransition.add(0);
        defaultTransition.add(0);
        defaultTransition.add(0);
        defaultTransition.add(0);
        defaultTransition.add(0);

        List<Integer> line1State = new ArrayList<>();
        line1State.add(1);
        line1State.add(1);
        line1State.add(2);
        line1State.add(3);
        line1State.add(1);
        transitionTable.put(inputAlf.get(1), line1State);

        List<Integer> line2State = new ArrayList<>();
        line2State.add(0);
        line2State.add(1);
        line2State.add(2);
        line2State.add(2);
        line2State.add(4);
        transitionTable.put(inputAlf.get(2), line2State);

        List<Integer> line3State = new ArrayList<>();
        line3State.add(3);
        line3State.add(3);
        line3State.add(3);
        line3State.add(3);
        line3State.add(3);
        transitionTable.put(inputAlf.get(3),  line3State);

        List<Integer> line4State = new ArrayList<>();
        line4State.add(4);
        line4State.add(1);
        line4State.add(2);
        line4State.add(3);
        line4State.add(4);
        transitionTable.put(inputAlf.get(4),  line4State);

        List<Integer> line5State = new ArrayList<>();
        line5State.add(0);
        line5State.add(1);
        line5State.add(2);
        line5State.add(3);
        line5State.add(4);
        transitionTable.put(inputAlf.get(5), line5State);
    }

    private void initOutputTable() {
        outputTable = new HashMap<>();
        defaultOutput = new ArrayList<>();

        defaultOutput.add(outputAlf.get(2));
        defaultOutput.add(outputAlf.get(0));
        defaultOutput.add(outputAlf.get(1));
        defaultOutput.add(outputAlf.get(5));
        defaultOutput.add(outputAlf.get(3));

        List<FSMCommand> lineOneOut = new ArrayList<>();
        lineOneOut.add(outputAlf.get(3));
        lineOneOut.add(outputAlf.get(4));
        lineOneOut.add(outputAlf.get(4));
        lineOneOut.add(outputAlf.get(4));
        lineOneOut.add(outputAlf.get(3));
        outputTable.put(inputAlf.get(1), lineOneOut);

        List<FSMCommand> lineTwoOut = new ArrayList<>();
        lineTwoOut.add(outputAlf.get(4));
        lineTwoOut.add(outputAlf.get(1));
        lineTwoOut.add(outputAlf.get(1));
        lineTwoOut.add(outputAlf.get(1));
        lineTwoOut.add(outputAlf.get(4));
        outputTable.put(inputAlf.get(2), lineTwoOut);

        List<FSMCommand> lineThreeOut = new ArrayList<>();
        lineThreeOut.add(outputAlf.get(2));
        lineThreeOut.add(outputAlf.get(0));
        lineThreeOut.add(outputAlf.get(2));
        lineThreeOut.add(outputAlf.get(2));
        lineThreeOut.add(outputAlf.get(3));
        outputTable.put(inputAlf.get(3), lineThreeOut);

        List<FSMCommand> lineFourOut = new ArrayList<>();
        lineFourOut.add(outputAlf.get(4));
        lineFourOut.add(outputAlf.get(4));
        lineFourOut.add(outputAlf.get(4));
        lineFourOut.add(outputAlf.get(4));
        lineFourOut.add(outputAlf.get(4));
        outputTable.put(inputAlf.get(4), lineFourOut);

        List<FSMCommand> lineFiveOut = new ArrayList<>();
        lineFiveOut.add(outputAlf.get(4));
        lineFiveOut.add(outputAlf.get(4));
        lineFiveOut.add(outputAlf.get(4));
        lineFiveOut.add(outputAlf.get(4));
        lineFiveOut.add(outputAlf.get(4));
        outputTable.put(inputAlf.get(5), lineFiveOut);
    }

    @Override
    public int getBeginState() {
        return beginState;
    }

    @Override
    public Map<Character, List<FSMCommand>> getOutputTable() {
        return outputTable;
    }

    @Override
    public List<FSMCommand> getDefaultOutput() {
        return defaultOutput;
    }

    @Override
    public Map<Character, List<Integer>> getTransitionTable() {
        return transitionTable;
    }

    @Override
    public List<Integer> getDefaultTransition() {
        return defaultTransition;
    }
}
