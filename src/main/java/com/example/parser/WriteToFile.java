package com.example.parser;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import static com.example.parser.config.StructureCardBuilder.BuildDescriptions;
import static com.example.parser.config.StructureCardBuilder.getTtx;

/**
 * Записывает информацию о товаре в текстовый файл testFile.txt
 * актуально для единичной выборки
 */

public class WriteToFile {


    static File file = new File("testFile.txt");

    public static void main(String[] args) throws IOException {
        try(PrintWriter pw = new PrintWriter(file)) {
            BuildDescriptions();
            pw.println(getTtx());
            pw.println("Successfully!");
        }
    }
}



