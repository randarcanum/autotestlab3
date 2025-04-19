package com.autotest.lab3.Csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SplitCsvRead {
    public static void main(String[] args)
    {
        try (BufferedReader fileReader = new BufferedReader(new FileReader("students1.csv"));)
        {
            String line;
            while ((line = fileReader.readLine()) != null) {
                String[] tokens = line.split(",");
                for (String token : tokens) System.out.println(token);
                System.out.println("-----------------");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
