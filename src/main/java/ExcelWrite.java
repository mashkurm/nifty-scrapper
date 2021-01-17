import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelWrite {
    public static void write(AddExcel addExcel){
        /*XSSFWorkbook workbook;*/
       try {
           FileInputStream inputStream = new FileInputStream(new File("NG.xlsx"));
           Workbook workbook = WorkbookFactory.create(inputStream);
           Sheet sheet = workbook.getSheetAt(0);

           Object[][] bookData = {
                   {addExcel.getArtistName(), addExcel.getItemName(),addExcel.getOriginalPrimaryMarket(),
                           addExcel.getAvgResalePrice(), addExcel.getAvgResalePrice(),
                           addExcel.getPriceChangedFromPrimaryMarket(),addExcel.getHighestAvgBid(),
                           addExcel.getLastSoldPrice(),addExcel.getSecondayMarketVolume(),
                           addExcel.getSecondarySales(),addExcel.getPrimarySales(),
                           addExcel.getDateCreated(), addExcel.getInstagramURl(),
                           addExcel.getTwitterURL()    }
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

}
