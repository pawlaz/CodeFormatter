package org.pawlaz.codeformatter.formatter;
import org.junit.Before;
import org.junit.Test;
import org.pawlaz.codeformatter.formatter.exceptions.FormatterException;
import org.pawlaz.codeformatter.io.PropertiesLoader;
import org.pawlaz.codeformatter.io.exceptions.PropertiesLoaderException;
import org.pawlaz.codeformatter.io.exceptions.ReaderException;
import org.pawlaz.codeformatter.io.exceptions.WriterException;
import org.pawlaz.codeformatter.io.reader.IReader;
import org.pawlaz.codeformatter.io.reader.StringReader;
import org.pawlaz.codeformatter.io.writer.IWriter;
import org.pawlaz.codeformatter.io.writer.StringWriter;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

/**
 * Created by Hns on 16.05.2016.
 * Unit tests for Formatter class
 */
public class FormatterTest {
    Formatter fsmFormatter;
    IReader reader = null;
    IWriter writer = null;
    final Logger logger = Logger.getLogger(FormatterTest.class.getName());

    final String goodStringFirstLevel = "while (inputStream.hasNext()) {\n" +
            "    char symbol = inputStream.read();\n" +
            "    while(true) {\n" +
            "        method();\n" +
            "    }\n" +
            "}";

    @Before
    public void setUp()
    {
        try {
            PropertiesLoader pl = new PropertiesLoader();
            ITablesMaker tablesMaker = new TablesMaker(pl.getIndentSymbol(),pl.getBaseOffsetCount(),pl.getLineSeparator());
            ITables fsmTables = new FSMTables(tablesMaker.getBeginState(),tablesMaker.getOutputTable(),tablesMaker.getTransitionTable());
            fsmFormatter = new Formatter(fsmTables);
        }catch (FormatterException | PropertiesLoaderException e) {
            logger.log(Level.SEVERE,e.getMessage());
        }
    }

    private void closeStreams(){
        try {
            if (reader != null) {
                reader.close();
            }
        } catch (ReaderException ex) {
            logger.log(Level.SEVERE,ex.getMessage());
        }

        try {
            if (writer != null) {
                writer.close();
            }
        } catch (WriterException ex) {
            logger.log(Level.SEVERE,ex.getMessage());
        }
    }

    @Test(expected = FormatterException.class)
    public void test0FormatFirstLevel() throws FormatterException
    {
        reader = null;
        writer = null;
        fsmFormatter.format(reader, writer);
    }

    @Test
    public void test1FormatFirstLevel() throws FormatterException
    {
        try {
            reader = new StringReader(goodStringFirstLevel);
            writer = new StringWriter();
            fsmFormatter.format(reader, writer);
            assertEquals(goodStringFirstLevel, writer.toString());
        } finally {
            closeStreams();
        }
    }

    @Test
    public void test2FormatFirstLevel() throws FormatterException
    {
        try {
            String badString = "while (inputStream.hasNext()){char symbol = inputStream.read();while(true) {method();}}";
            reader = new StringReader(badString);
            writer = new StringWriter();
            fsmFormatter.format(reader,writer);
            assertEquals(goodStringFirstLevel,writer.toString());
        } finally {
            closeStreams();
        }
    }

    @Test
    public void test3FormatFirstLevel() throws FormatterException
    {
        try {
            String badString = "while (inputStream.hasNext()) {\nchar symbol = inputStream.read(); while(true){method();}\n}\n";
            reader = new StringReader(badString);
            writer = new StringWriter();
            fsmFormatter.format(reader,writer);
            assertEquals(goodStringFirstLevel,writer.toString());
        } finally {
            closeStreams();
        }
    }
}