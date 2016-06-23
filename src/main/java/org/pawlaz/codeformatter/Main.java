package org.pawlaz.codeformatter;


import org.pawlaz.codeformatter.formatter.*;
import org.pawlaz.codeformatter.formatter.exceptions.FormatterException;
import org.pawlaz.codeformatter.io.PropertiesLoader;
import org.pawlaz.codeformatter.io.exceptions.PropertiesLoaderException;
import org.pawlaz.codeformatter.io.exceptions.ReaderException;
import org.pawlaz.codeformatter.io.exceptions.WriterException;
import org.pawlaz.codeformatter.io.reader.FileReader;
import org.pawlaz.codeformatter.io.reader.IReader;
import org.pawlaz.codeformatter.io.reader.StringReader;
import org.pawlaz.codeformatter.io.writer.FileWriter;
import org.pawlaz.codeformatter.io.writer.IWriter;
import org.pawlaz.codeformatter.io.writer.StringWriter;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main class
 */
public final class Main {

    private static final Logger LOG = Logger.getLogger(Main.class.getName());

    private Main() {
    }

    /**
     * Launches the program
     *
     * @param args - command line arguments
     */
    public static void main(final String[] args) {
        IReader reader = null;
        IWriter writer = null;
        Formatter formatter;
        PropertiesLoader pl;

        try {
            if (args != null) {
                if ("-help".equals(args[0])) {
                    System.out.println("Simple formatter produces formatting data from the input stream\n" +
                            "Use -f <inputFileName> <outputFileName> to format data from inputFile" +
                            " and recording the formatted data in the outputFile\n" +
                            "Use -s <inputString> to format string");

                } else if ("-s".equals(args[0])) {
                    if (args[1] != null) {
                        reader = new StringReader(args[1]);
                        writer = new StringWriter();
                    } else {
                        outputBadDataMessage();
                    }
                } else if ("-f".equals(args[0])) {
                    if (args[1] != null && args[2] != null) {
                        reader = new FileReader(args[1]);
                        writer = new FileWriter(args[2]);
                    } else {
                        outputBadDataMessage();
                    }
                } else {
                    outputBadDataMessage();
                }

                pl = new PropertiesLoader();
                ITablesMaker tablesMaker = new TablesMaker(pl.getIndentSymbol(), pl.getBaseOffsetCount(), pl.getLineSeparator());
                ITables fsmTables = new FSMTables(tablesMaker.getBeginState(), tablesMaker.getOutputTable(), tablesMaker.getTransitionTable());
                formatter = new Formatter(fsmTables);
                formatter.format(reader, writer);

                if ("-s".equals(args[0])) {
                    System.out.println(writer.toString());
                }

            } else {
                outputBadDataMessage();
            }
        } catch (ReaderException | WriterException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Incorrect arguments, use -help for more information");
        } catch (FormatterException | PropertiesLoaderException e) {
            System.out.println("Error in program: " + e.getMessage());
        } catch (Exception e) {
            // never used, because exceptions wrapped
            System.out.println("Error in program, this line used for debugging:");
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (ReaderException ex) {
                LOG.log(Level.SEVERE, ex.getMessage());
            }

            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (WriterException ex) {
                LOG.log(Level.SEVERE, ex.getMessage());
            }
        }
    }

    private static void outputBadDataMessage() throws ReaderException {
        throw new ReaderException(new IllegalArgumentException());
    }
}
