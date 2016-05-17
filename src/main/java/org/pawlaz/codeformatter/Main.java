package org.pawlaz.codeformatter;

import org.pawlaz.codeformatter.formatter.Formatter;
import org.pawlaz.codeformatter.io.reader.IReader;
import org.pawlaz.codeformatter.io.reader.StringReader;
import org.pawlaz.codeformatter.io.writer.IWriter;
import org.pawlaz.codeformatter.io.writer.StringWriter;

/**
 * Created by Hns on 15.05.2016.
 * Main class
 */
public class Main {
    public static void main(String[] args) {
        try {
            Formatter f = new Formatter();
            IWriter writer = new StringWriter();
            f.format(new StringReader("a++b--c==2 123 qwe=5"), writer);
            System.out.println(writer.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
