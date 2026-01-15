package utils;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelDataReader {

    private static final String EXCEL_PATH = 
            System.getProperty("user.dir") + "/src/test/resources/testdata/Testdatateam3.xlsx";

    /**
     * Get single row data as HashMap
     * @param sheetName - Name of the sheet
     * @param rowNum - Row number (1, 2, 3... - excludes header row 0)
     * @return HashMap with column headers as keys and cell values as values
     */
    public static Map<String, String> getRowDataAsMap(String sheetName, int rowNum) {
        Map<String, String> rowData = new HashMap<>();
        
        try (FileInputStream fis = new FileInputStream(EXCEL_PATH)) {
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheet(sheetName);
            
            if (sheet == null) {
                throw new RuntimeException("Sheet '" + sheetName + "' not found");
            }
            
            // Get header row (row 0)
            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                throw new RuntimeException("Header row not found in sheet " + sheetName);
            }
            
            // Get data row
            Row dataRow = sheet.getRow(rowNum);
            if (dataRow == null) {
                throw new RuntimeException("Row " + rowNum + " does not exist in sheet " + sheetName);
            }
            
            DataFormatter formatter = new DataFormatter();
            
            // Map each header to its corresponding cell value
            for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                Cell headerCell = headerRow.getCell(i);
                Cell dataCell = dataRow.getCell(i);
                
                if (headerCell != null) {
                    String header = formatter.formatCellValue(headerCell);
                    String value = (dataCell != null) ? formatter.formatCellValue(dataCell) : "";
                    rowData.put(header, value);
                }
            }
            
        } catch (Exception e) {
            throw new RuntimeException("Failed to read Excel data: " + e.getMessage(), e);
        }
        
        return rowData;
    }

    /**
     * Get all rows data as List of HashMaps
     * @param sheetName - Name of the sheet
     * @return List of HashMaps, each representing a row
     */
    public static List<Map<String, String>> getAllRowsAsMap(String sheetName) {
        List<Map<String, String>> allData = new ArrayList<>();
        
        try (FileInputStream fis = new FileInputStream(EXCEL_PATH)) {
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheet(sheetName);
            
            if (sheet == null) {
                throw new RuntimeException("Sheet '" + sheetName + "' not found");
            }
            
            // Get header row
            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                throw new RuntimeException("Header row not found in sheet " + sheetName);
            }
            
            DataFormatter formatter = new DataFormatter();
            
            // Store headers
            List<String> headers = new ArrayList<>();
            for (Cell cell : headerRow) {
                headers.add(formatter.formatCellValue(cell));
            }
            
            // Read all data rows (starting from row 1)
            for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row dataRow = sheet.getRow(rowNum);
                if (dataRow != null) {
                    Map<String, String> rowData = new HashMap<>();
                    
                    for (int i = 0; i < headers.size(); i++) {
                        Cell cell = dataRow.getCell(i);
                        String value = (cell != null) ? formatter.formatCellValue(cell) : "";
                        rowData.put(headers.get(i), value);
                    }
                    
                    allData.add(rowData);
                }
            }
            
        } catch (Exception e) {
            throw new RuntimeException("Failed to read Excel data: " + e.getMessage(), e);
        }
        
        return allData;
    }

    /**
     * Get total number of data rows (excluding header)
     * @param sheetName - Name of the sheet
     * @return Number of data rows
     */
    public static int getRowCount(String sheetName) {
        try (FileInputStream fis = new FileInputStream(EXCEL_PATH)) {
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheet(sheetName);
            
            if (sheet == null) {
                return 0;
            }
            
            return sheet.getLastRowNum(); // Excludes header row
            
        } catch (Exception e) {
            throw new RuntimeException("Failed to get row count: " + e.getMessage(), e);
        }
    }

    /**
     * Print all data from a sheet (for debugging)
     * @param sheetName - Name of the sheet
     */
    public static void printSheetData(String sheetName) {
        List<Map<String, String>> data = getAllRowsAsMap(sheetName);
        
        System.out.println("\n========== " + sheetName + " Sheet Data ==========");
        for (int i = 0; i < data.size(); i++) {
            System.out.println("\n--- Row " + (i + 1) + " ---");
            Map<String, String> row = data.get(i);
            for (Map.Entry<String, String> entry : row.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
        System.out.println("\n=====================================\n");
    }
}


