package org.pawlaz.codeformatter.formatter;

import java.util.HashMap;

/**
 * Created by Hns on 18.05.2016.
 * It represents methods for formatting certain characters
 */
public class ProcessCommands implements  IFormatterCommands {
    private StringMaker stringMaker;
    private int bktCount = 0;
    private boolean firstInLine = false;
    private boolean firstInWord = false;

    /**
     * Constructs an ProcessCommands with the specified data for character processing strategy
     * @param indentSymbol - symbol used to indent
     * @param baseOffsetCount - offset size
     * @param lineSeparator - symbol used for line separator
     */
    public ProcessCommands(final char indentSymbol, final int baseOffsetCount, final char lineSeparator) {
        this.stringMaker = new StringMaker(indentSymbol, baseOffsetCount, lineSeparator);
    }

    @Override
    public HashMap<Character, FormatCommand> getFormatterCommands() {
        HashMap<Character, FormatCommand> commands = new HashMap<>();

        commands.put(stringMaker.getIndentSymbol(), new FormatCommand() {
            @Override
            String format(final Character c) {
                firstInWord = true;
                return  "";
            }
        });

        commands.put(stringMaker.getLineSeparator(), new FormatCommand() {
            @Override
            String format(final Character c) {
                return "";
            }
        });

        commands.put('{', new FormatCommand() {
            @Override
            String format(final Character c) {
                if (firstInLine) {
                    stringMaker.addOffset(bktCount);
                } else {
                    stringMaker.addIndentSymbol();
                    bktCount++;
                    stringMaker.addSymbol(c);
                    stringMaker.addLineSeparator();
                    firstInLine = true;
                    firstInWord = false;
                }
                return stringMaker.getResult();
            }
        });

        commands.put('}', new FormatCommand() {
            @Override
            String format(final Character c) {
                bktCount--;
                stringMaker.addOffset(bktCount);
                stringMaker.addSymbol(c);
                if (bktCount > 0)
                    stringMaker.addLineSeparator();

                firstInLine = true;
                firstInWord = false;
                return stringMaker.getResult();
            }
        });
        commands.put(';', new FormatCommand() {
            @Override
            String format(final Character c) {
                stringMaker.addSymbol(c);
                stringMaker.addLineSeparator();
                firstInLine = true;
                return stringMaker.getResult();
            }
        });
        return commands;
    }

    @Override
    public FormatCommand defaultCommand() {
        return new FormatCommand() {
            @Override
            String format(final Character c) {
                if (firstInLine) {
                    stringMaker.addOffset(bktCount);
                    firstInLine = false;
                    firstInWord = false;
                } else if (firstInWord) {
                    stringMaker.addIndentSymbol();
                    firstInWord = false;
                }
                stringMaker.addSymbol(c);
                return stringMaker.getResult();
            }
        };
    }
}
