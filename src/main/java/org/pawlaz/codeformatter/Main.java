package org.pawlaz.codeformatter;


import org.pawlaz.codeformatter.formatter.Formatter;
import org.pawlaz.codeformatter.formatter.IFormatterStrategies;
import org.pawlaz.codeformatter.formatter.ProcessStrategies;
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

/**
 * Main class
 */
public final class Main {

    private Main() {
    }

    /**
     * Launches the program
     * @param args - command line arguments
     */
    public static void main(final String[] args) {
        IReader reader = null;
        IWriter writer = null;
        Formatter formatter;
        IFormatterStrategies strategies;
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
                strategies = new ProcessStrategies(pl.getIndentSymbol(), pl.getBaseOffsetCount(), pl.getLineSeparator());
                formatter = new Formatter(strategies);
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
                if (writer != null) {
                    writer.close();
                }
            } catch (Exception ex) {
                //pass
            }
        }
    }

    private static void outputBadDataMessage() throws ReaderException {
        throw new ReaderException(new IllegalArgumentException());
    }
}
