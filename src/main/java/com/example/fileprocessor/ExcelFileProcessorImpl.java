package com.example.fileprocessor;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.parser.config.StructureCardBuilder.*;

public class ExcelFileProcessorImpl {

//    private static final String FILE_NAME = "ParserFile.xlsx";


    public static FileInputStream createExcelFile(List<String> taskList) throws IOException {
        //Формирование документа на основе шаблона - файла .docx из папки resources
        XSSFWorkbook doc = new XSSFWorkbook(ExcelFileProcessorImpl.class.getClassLoader().getResourceAsStream("Template.xslx"));
        setTaskListToXWPFDocument( doc, taskList);
        return createTempFile(doc);
    }


    private static void setTaskListToXWPFDocument(XSSFWorkbook doc, List<String> taskList) {
        //запись первой строки списка заданий в первый абзац документа (создаётся по умолчанию при создании документа)
        try (XSSFWorkbook workbook = new XSSFWorkbook()){
            XSSFSheet sheet = workbook.createSheet("ParserFile");
            //массивы для записи в эксель

            List<String> productCards = new ArrayList(getTtx());
            List<String> photoList = new ArrayList(getPhotos());
            List<String> instrList = new ArrayList(getInstr());

            int rowNum = 0;//счетчик строк
            int index = 0;

            for (String crm : productCards) {//цикл создания параметризированного списка

                Row row = sheet.createRow(rowNum++);

                Cell cell = row.createCell(0);//первый столбец, описание товаров
                Cell cellPhoto = row.createCell(1);//второй столбец, ссылки на фото
                Cell cellInstr = row.createCell(2);//третий столбец, ссылки на инструкции (если есть)

                cell.setCellValue(crm);
                cellInstr.setCellValue(instrList.get(index));
                cellPhoto.setCellValue(photoList.get(index).replace("68x60", "800x800"));
                System.out.println("Product card"+index+++" has been successfully created");
            }
//            FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
//            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static FileInputStream createTempFile(XSSFWorkbook doc) throws IOException {
        File result = File.createTempFile("Print_Me", ".xlsx");
        try (FileOutputStream out = new FileOutputStream(result)) {
            doc.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new FileInputStream(result);
    }

    public static FileInputStream getFile(List<String> operations)
            throws IOException {
        List<String> taskList = new ArrayList<>();

        return createExcelFile(taskList);
    }
}
