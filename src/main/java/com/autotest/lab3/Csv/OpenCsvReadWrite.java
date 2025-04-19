package com.autotest.lab3.Csv;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class OpenCsvReadWrite {
    public static void main(String[] args)
    {
        try (
            CSVReader reader = new CSVReader(new FileReader("students1.csv"));
            CSVWriter writer = new CSVWriter(new FileWriter("students2.csv"),
                                CSVWriter.DEFAULT_SEPARATOR,
                                CSVWriter.NO_QUOTE_CHARACTER, 
                                CSVWriter.DEFAULT_ESCAPE_CHARACTER, 
                                CSVWriter.DEFAULT_LINE_END)
        ) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                writer.writeNext(nextLine);
                for (String token : nextLine) System.out.println(token);
                System.out.println("-----------------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }
    }
}
