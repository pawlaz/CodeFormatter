package org.pawlaz.codeformatter.formatter;
import org.junit.Before;
import org.junit.Test;
import org.pawlaz.codeformatter.formatter.exceptions.FormatterException;
import org.pawlaz.codeformatter.io.PropertiesLoader;
import org.pawlaz.codeformatter.io.exceptions.PropertiesLoaderException;
import org.pawlaz.codeformatter.io.reader.IReader;
import org.pawlaz.codeformatter.io.reader.StringReader;
import org.pawlaz.codeformatter.io.writer.IWriter;
import org.pawlaz.codeformatter.io.writer.StringWriter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by Hns on 16.05.2016.
 * Unit tests for Formatter class
 */
public class FormatterTest {

    Formatter formatter;
    IReader reader = null;
    IWriter writer = null;

    final String goodStringFirstLevel = "while (inputStream.hasNext()) {\n" +
            "    char symbol = inputStream.read();\n" +
            "    while(true) {\n" +
            "        method();\n" +
            "    }\n" +
            "}";

    @Before
    public void init()
    {
        try {
            PropertiesLoader pl = new PropertiesLoader();
            IFormatterCommands strategies =
                    new ProcessCommands(pl.getIndentSymbol(),pl.getBaseOffsetCount(),pl.getLineSeparator());
            formatter = new Formatter(strategies);
        }catch (FormatterException | PropertiesLoaderException e) {
            fail();
        }

    }

    @Test(expected = FormatterException.class)
    public void test0FormatFirstLevel() throws FormatterException
    {
        reader = null;
        writer = null;
        formatter.format(reader, writer);
    }

    @Test
    public void test1FormatFirstLevel()
    {
        try {
            reader = new StringReader(goodStringFirstLevel);
            writer = new StringWriter();
            formatter.format(reader, writer);
            assertEquals(goodStringFirstLevel, writer.toString());
        } catch (FormatterException e) {
            fail();
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

    @Test
    public void test2FormatFirstLevel()
    {
        try {
            String badString = "while (inputStream.hasNext()){char symbol = inputStream.read();while(true) {method();}}";
            reader = new StringReader(badString);
            writer = new StringWriter();
            formatter.format(reader,writer);
            assertEquals(goodStringFirstLevel,writer.toString());
        } catch (FormatterException e) {
            fail();
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

    @Test
    public void test3FormatFirstLevel()
    {
        try {
            String badString = "while (inputStream.hasNext()) {\nchar symbol = inputStream.read(); while(true){method();}\n}\n";
            reader = new StringReader(badString);
            writer = new StringWriter();
            formatter.format(reader,writer);
            assertEquals(goodStringFirstLevel,writer.toString());
        } catch (FormatterException e) {
            fail();
        }finally {
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
}
