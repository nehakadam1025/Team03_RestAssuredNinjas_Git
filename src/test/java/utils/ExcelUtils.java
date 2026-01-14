package utils;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.DateUtil;

public class ExcelUtils {

    private static final String EXCEL_PATH =
            System.getProperty("user.dir")
            + "/src/test/resources/testdata/Testdatateam3.xlsx";

    public static String getCellData(String sheetName, int rowNum, int colNum) {

        try (FileInputStream fis = new FileInputStream("src/test/resources/testdata/Testdatateam3.xlsx")) {

            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheet(sheetName);
            //Row row = sheet.getRow(rowNum);
            Row row = sheet.getRow(rowNum);
            if (row == null) {
                throw new RuntimeException("Row " + rowNum + " does not exist in sheet " + sheetName);
            }
            Cell cell = row.getCell(colNum);

            DataFormatter formatter = new DataFormatter();
            return formatter.formatCellValue(cell);

        } catch (Exception e) {
            throw new RuntimeException("Failed to read Excel data", e);
        }
    }
}