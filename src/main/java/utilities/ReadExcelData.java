package utilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadExcelData {
    public FileInputStream fis;
    public static XSSFWorkbook wb;
    public static XSSFSheet ws = null;
    public static XSSFRow row, rowVal = null;
    public static XSSFCell cell = null;

    public ReadExcelData(String xlFilePath) throws Exception {
        fis = new FileInputStream(xlFilePath);
        wb = new XSSFWorkbook(fis);
        fis.close();
    }
    /*public String fetchDeviceConf(String colName) throws IOException{
        try{
            //Get the row from where the device configuration is to be read
            int rowNum = -1;
            ws = wb.getSheet("DeviceConf");
            int rCnt = ws.getLastRowNum() - ws.getFirstRowNum();
            for(int i = 0; i< rCnt; i++){
                cell = ws.getRow(i).getCell(0);
                if(cell == null || cell.getCellType() == CellType.BLANK) {
                    rowNum = i;
                 //   System.out.println("SKIP Row at: " + i);
                    break;
                }
            }

            //Fetch the device configuration from the identified row above
            int colNum = -1;
            row = ws.getRow(0);
            for(int i = 0; i < row.getLastCellNum(); i++){
                if(row.getCell(i).getStringCellValue().trim().equals(colName.trim())) {
                //    System.out.println("Column Name from Excel: " + row.getCell(i).getStringCellValue());
                    colNum = i;
                }
            }
            row = ws.getRow(rowNum);
            cell = row.getCell(colNum);

            if(cell.getCellType() == CellType.STRING)
                return cell.getStringCellValue();
            else if(cell.getCellType() == CellType.NUMERIC || cell.getCellType() == CellType.FORMULA){
                return String.valueOf(cell.getNumericCellValue());

            } else if(cell.getCellType() == CellType.BLANK)
                return "";
            else
                return String.valueOf(cell.getBooleanCellValue());

        } catch(Exception e){
            e.printStackTrace();
            return "Column " + colName + " does not exist  in Excel";
        }

    }*/

    public static Map<String, Map<String, String>> setExcelData(String sheetName) throws IOException {

        ws = wb.getSheet(sheetName);
        int lastRow = ws.getLastRowNum();

        Map<String, Map<String, String>> excelFileMap = new HashMap<String, Map<String,String>>();
        Map<String, String> dataMap = new HashMap<String, String>();

        //Fetching the row from where data is to be used (SKIP = NULL)
        int rowNum = -1;
        int rCnt = ws.getLastRowNum() - ws.getFirstRowNum();
        for(int i = 0; i< rCnt; i++){
            cell = ws.getRow(i).getCell(0);
            if(cell == null || cell.getCellType() == CellType.BLANK) {
                rowNum = i;
                break;
            }
        }

        row = ws.getRow(0);
        rowVal = ws.getRow(rowNum);
        for(int colCnt = 0; colCnt < row.getLastCellNum(); colCnt++){
            String key = String.valueOf(row.getCell(colCnt)).trim();
            String value = String.valueOf(rowVal.getCell(colCnt)).trim();
            dataMap.put(key, value);
            excelFileMap.put("DataSheet", dataMap);
        }
        return excelFileMap;
    }

    public static String getCustData(String key) throws IOException{
        Map<String, String> m = setExcelData("CustInfo").get("DataSheet");
        return m.get(key);
    }

    public static String getDeviceConf(String key) throws IOException{
        Map<String, String> m = setExcelData("DeviceConf").get("DataSheet");
        return m.get(key);
    }
}
