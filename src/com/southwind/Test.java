package com.southwind;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Test {
//    public static void main(String[] args) {
//        //创建HSSFWorkbook对象
//        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
//        Sheet sheet = hssfWorkbook.createSheet("测试");
//        Row row = sheet.createRow(0);
//        Cell cell = row.createCell(0);
//        cell.setCellValue("Java高级编程");
//        cell = row.createCell(1);
//        cell.setCellValue("MySQL数据库");
//        row = sheet.createRow(1);
//        cell = row.createCell(0);
//        cell.setCellValue("Spring MVC");
//        cell = row.createCell(1);
//        cell.setCellValue("MongoDB");
//
//        sheet = hssfWorkbook.createSheet("你好");
//        row = sheet.createRow(0);
//        cell = row.createCell(0);
//        cell.setCellValue("Java高级编程");
//        cell = row.createCell(1);
//        cell.setCellValue("MySQL数据库");
//        row = sheet.createRow(1);
//        cell = row.createCell(0);
//        cell.setCellValue("Spring MVC");
//        cell = row.createCell(1);
//        cell.setCellValue("MongoDB");
//        OutputStream outputStream = null;
//        try {
//            outputStream = new FileOutputStream("/Users/southwind/Desktop/测试.xls");
//            hssfWorkbook.write(outputStream);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }  catch (IOException e){
//            e.printStackTrace();
//        } finally {
//            try {
//                outputStream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//
//    }
}
