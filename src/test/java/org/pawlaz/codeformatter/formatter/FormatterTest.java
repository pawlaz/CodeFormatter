package org.pawlaz.codeformatter.formatter;
import org.junit.Before;
import org.junit.Test;
import org.pawlaz.codeformatter.io.reader.IReader;
import org.pawlaz.codeformatter.io.reader.StringReader;
import org.pawlaz.codeformatter.io.writer.IWriter;
import org.pawlaz.codeformatter.io.writer.StringWriter;

import static org.junit.Assert.assertEquals;

/**
 * Created by Hns on 16.05.2016.
 * Unit test for Formatter class
 */
public class FormatterTest {

    Formatter formatter;

    String goodStringFirstLevel = "while (inputStream.hasNext()) {\n" +
            "    char symbol = inputStream.read();\n" +
            "    while(true) {\n" +
            "        method();\n" +
            "    }\n" +
            "}";

    @Before
    public void init()
    {
        formatter = new Formatter();
    }

    @Test
    public void test1FormatFirstLevel()
    {
        IReader reader = new StringReader(goodStringFirstLevel);
        IWriter writer = new StringWriter();
        formatter.format(reader,writer);
        assertEquals(goodStringFirstLevel,writer.toString());
    }

    @Test
    public void test2FormatFirstLevel()
    {
        String badString = "while (inputStream.hasNext()){char symbol = inputStream.read();while(true) {method();}}";
        IReader reader = new StringReader(badString);
        IWriter writer = new StringWriter();
        formatter.format(reader,writer);
        assertEquals(goodStringFirstLevel,writer.toString());
    }

    @Test
    public void test3FormatFirstLevel()
    {
        String badString = "while (inputStream.hasNext()) {\nchar symbol = inputStream.read();while(true){method();}\n}\n";
        IReader reader = new StringReader(badString);
        IWriter writer = new StringWriter();
        formatter.format(reader,writer);
        assertEquals(goodStringFirstLevel,writer.toString());
    }
}
