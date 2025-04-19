package com.autotest.lab3.Csv;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class CommonCsvWrite {
    public static void main(String[] args) {
        try (
            BufferedWriter writer = Files.newBufferedWriter(Paths.get("students1.csv"), StandardOpenOption.CREATE_NEW);
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                        .builder()
                        .setHeader("ID", "Name", "Faculty", "University")
                        .build())
        ) {
            csvPrinter.printRecord("1", "Boris Ivanov", "Computer Science", "Technical University of Moldova");
            csvPrinter.printRecord("2", "Anastasia Petrov", "Economics", "Moldova State University");
            csvPrinter.printRecord("3", "Vladimir Kozlov", "Physics", "Moldova State University");
            csvPrinter.printRecord("4", "Olga Novikova", "Mathematics", "Academy of Economic Studies of Moldova");
            csvPrinter.printRecord("5", "Ivan Kuznetsov", "Computer Science", "Moldova State University");

            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

