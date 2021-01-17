import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;

public class ExcelWrite {
    public static void write(AddExcel addExcel){
        /*XSSFWorkbook workbook;*/
       try {
           FileInputStream inputStream = new FileInputStream(new File("NG.xlsx"));
           Workbook workbook = WorkbookFactory.create(inputStream);
           Sheet sheet = workbook.getSheetAt(0);

           Object[][] bookData = {
                   {"", "", addExcel.getArtistName(), addExcel.getItemName(),"","",addExcel.getOriginalPrimaryMarket(),
                           addExcel.getAvgResalePrice(), addExcel.getAvgResalePrice(),
                           addExcel.getPriceChangedFromPrimaryMarket(),addExcel.getHighestAvgBid(),
                           addExcel.getLastSoldPrice(),addExcel.getSecondayMarketVolume(),
                           addExcel.getSecondarySales(),addExcel.getPrimarySales(),
                           addExcel.getDateCreated(), "",addExcel.getInstagramURl(),"",
                           addExcel.getTwitterURL(),"",new Timestamp(System.currentTimeMillis())    }
           };
           int rowCount = sheet.getLastRowNum();

           for (Object[] aBook : bookData) {
               Row row = sheet.createRow(++rowCount);

               int columnCount = 0;

               Cell cell = row.createCell(columnCount);
               cell.setCellValue(rowCount);

               for (Object field : aBook) {
                   cell = row.createCell(++columnCount);
                   if (field instanceof String) {
                       cell.setCellValue((String) field);
                   } else if (field instanceof Integer) {
                       cell.setCellValue((Integer) field);
                   }
               }

           }

           inputStream.close();
           FileOutputStream outputStream = new FileOutputStream("NG.xlsx");
           workbook.write(outputStream);
           workbook.close();
           outputStream.close();


       } catch (IOException | EncryptedDocumentException
               | InvalidFormatException ex) {
           ex.printStackTrace();
       }
    }
/*
           if(file.exists()) {
               FileInputStream fs = new FileInputStream(file);
               workbook = new XSSFWorkbook(fs);
           }
           String sheetName = "Scrap Data";
           XSSFSheet worksheet = workbook.getSheet(sheetName);
           if(worksheet == null) {
               worksheet = workbook.createSheet(sheetName);
           }



           int lastRow = worksheet.getLastRowNum();
           System.out.println(lastRow);
           Row row = worksheet.createRow(++lastRow);
           System.out.println("Artist name -- "+addExcel.getArtistName());
           row.createCell(2).setCellValue(addExcel.getArtistName());
           row.createCell(3).setCellValue(addExcel.getItemName());
           row.createCell(6).setCellValue(addExcel.getOriginalPrimaryMarket());
           row.createCell(7).setCellValue(addExcel.getAvgResalePrice());
           row.createCell(8).setCellValue(addExcel.getPriceChangedFromPrimaryMarket());
           row.createCell(9).setCellValue(addExcel.getHighestAvgBid());
           row.createCell(10).setCellValue(addExcel.getLastSoldPrice());
           row.createCell(11).setCellValue(addExcel.getSecondayMarketVolume());
           row.createCell(12).setCellValue(addExcel.getSecondarySales());
           row.createCell(13).setCellValue(addExcel.getPrimarySales());
           row.createCell(14).setCellValue(addExcel.getDateCreated());
           row.createCell(16).setCellValue(addExcel.getInstagramURl());
           row.createCell(17).setCellValue(addExcel.getTwitterURL());


           FileOutputStream out = new FileOutputStream(new File("NG.xlsx"),true);
           workbook.write(out);
           out.close();
           System.out.println("Write Successfully.");

       }
       catch(IOException | InvalidFormatException io){
           System.out.println(io.getMessage());
           System.out.println(io.getStackTrace());
       }

    }*/
}
