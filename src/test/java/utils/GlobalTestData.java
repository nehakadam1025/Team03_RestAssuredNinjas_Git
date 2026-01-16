package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class GlobalTestData {

    private static GlobalTestData instance;
    private List<Map<String, String>> excelData = new ArrayList<>();
    private List<String> headers = new ArrayList<>();

    // Singleton instance
    public static GlobalTestData getInstance() {
        if (instance == null) {
            instance = new GlobalTestData();
        }
        return instance;
    }

    /**
     * Load Excel data once from file
     */
    public void loadExcelData(String filePath, String sheetName) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new RuntimeException("Sheet " + sheetName + " not found in Excel file: " + filePath);
            }

            excelData.clear();
            headers.clear();

            // Read header row
            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                throw new RuntimeException("Excel sheet " + sheetName + " has no header row");
            }

            for (Cell cell : headerRow) {
                headers.add(cell.getStringCellValue().trim());
            }

            // Read data rows
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Map<String, String> rowData = new HashMap<>();
                for (int j = 0; j < headers.size(); j++) {
                    Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    String cellValue = getCellValueAsString(cell);
                    rowData.put(headers.get(j), cellValue);
                }
                excelData.add(rowData);
            }
        }
    }

    /**
     * Get row data by TestcaseId
     */
    public Map<String, String> getDataByTestcaseId(String testcaseId) {
        if (testcaseId == null || testcaseId.isEmpty()) return null;
        for (Map<String, String> row : excelData) {
            String id = row.getOrDefault("TestcaseId", "").trim();
            if (id.equals(testcaseId.trim())) {
                return row;
            }
        }
        return null;
    }

    /**
     * Convert cell value to string
     */
    private String getCellValueAsString(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return new DataFormatter().formatCellValue(cell); // formatted date
                } else {
                    // Convert numeric to string, avoid scientific notation
                    double d = cell.getNumericCellValue();
                    if (d == (long) d) return String.valueOf((long) d); // integer
                    return String.valueOf(d);
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case BLANK:
                return "";
            case FORMULA:
                return new DataFormatter().formatCellValue(cell);
            default:
                return "";
        }
    }
}
