//Base class for initializing global data written in properties file and to use it in Step definition
package foreignExchangeRates.testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	
	public static Properties prop;
	public static String baseURI;
	public static int successCode;
	public static String successLine;
	
	//constructor for getting access to properties file
	public TestBase() throws IOException {
		prop = new Properties();
		File file = new File("C:\\Users\\Sheetal Gaikwad\\eclipse-workspace\\VirtusaAssignment\\src\\main\\java\\foreignExchangeRates\\testInputs\\ExchangeRates.properties");
		FileInputStream ip = new FileInputStream(file);
		prop.load(ip);
	}
	
	//method to get data from properties file
	public static void initialize() throws IOException {
		TestBase t = new TestBase();
		baseURI = prop.getProperty("BaseURI");
		String SuccessCode = prop.getProperty("SuccessCode");
		successCode = Integer.parseInt(SuccessCode);
		successLine = prop.getProperty("SuccessLine");
	}

}
