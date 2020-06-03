package foreignExchangeRates.testInputs;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Data {
	
	static XSSFWorkbook w;
	static String extension;
	static String sheet = "TestData";
	
	public static String getData(String testID) {
		
		try{
			w = new XSSFWorkbook("C:\\Users\\Sheetal Gaikwad\\eclipse-workspace\\VirtusaAssignment\\src\\main\\java\\foreignExchangeRates\\testInputs\\Exchange Rates_Test Cases_Test DataV0.1.xlsx");
		   }
		catch(Exception e) {
			System.out.println("cannot find or access workbook");
			e.printStackTrace();
		}
		
		XSSFSheet s = w.getSheet(sheet);
		int rowCount = s.getLastRowNum()-s.getFirstRowNum();
		
		for(int i=0; i<rowCount;i++) {
			
			Row row = s.getRow(i);
			String rowValue = row.getCell(0).getStringCellValue();
			if(rowValue == testID)
				extension =row.getCell(1).getStringCellValue();
			
		}
		
		return extension;
		
		
	}

}
