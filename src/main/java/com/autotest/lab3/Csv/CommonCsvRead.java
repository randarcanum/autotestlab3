package com.autotest.lab3.Csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CommonCsvRead {
    public static void main(String[] args) {
        try (
            Reader reader = new BufferedReader(new FileReader("students1.csv"));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                        .builder()
                        .setHeader()
                        .setIgnoreHeaderCase(true)
                        .setTrim(true)
                        .build());
        ) {
            for (CSVRecord csvRecord : csvParser) {
                System.out.println("Record No - " + csvRecord.getRecordNumber());
                System.out.println("Name : " + csvRecord.get("Name"));
                System.out.println("Faculty : " + csvRecord.get("Faculty"));
                System.out.println("University : " + csvRecord.get("University"));
                System.out.println("---------------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
