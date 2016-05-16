package org.pawlaz.codeformatter;

import org.pawlaz.codeformatter.formatter.Formatter;
import org.pawlaz.codeformatter.io.reader.FileReader;
import org.pawlaz.codeformatter.io.reader.StringReader;
import org.pawlaz.codeformatter.io.writer.FileWriter;
import org.pawlaz.codeformatter.io.writer.StringWriter;

/**
 * Created by Hns on 15.05.2016.
 * Main class
 */
public class Main {
    public static void main(String[] args) {
        Formatter formatter = new Formatter();
        String s = "while (inputStream.hasNext()){\nchar symbol = inputStream.read();}";
        StringWriter sw = new StringWriter();
        formatter.format(new StringReader(s),sw);
        System.out.println(sw.toString());
//        try {
//            FileReader fr = new FileReader("E:/Я/1.txt");
//            FileWriter fw = new FileWriter("E:/Я/2.txt");
//            formatter.format(fr,fw);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }
}
