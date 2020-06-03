//gluecode for feature file
package foreignExchangeRates.gluecode;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.testng.Assert;

import foreignExchangeRates.testBase.TestBase;
import foreignExchangeRates.testInputs.Data;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
//import io.restassured.mapper.factory
//here StepDefinition extends TestBase to directly access its methods and variable references
public class StepDefinition extends TestBase{
	static String extension;
	Response response;
	String strResponse;
	JsonPath jsonPath;
	String applicationType;
	String incorrectURI;
	String param;
	Date date;
	boolean flag=true;
	
	//calling initialize method present in base class to get values of global variables written in properties file
	public StepDefinition() throws IOException {
		initialize();
	}
	
	//getting API for 1st scenario
	@Given("API is available")
	public void api_is_available() {
	    extension = Data.getData("Exchange Rates_01");
	}
	
	//sending request and receiving response for all positive scenarios 
	@When("GET request is sent")
	public void get_request_is_sent() {
	    RestAssured.baseURI = TestBase.baseURI;
	    RequestSpecification request = RestAssured.given();
	    response = request.get(extension);
	    strResponse = response.asString();

	}

	//checking if any response is received for all positive scenarios
	@Then("response is received")
	public void response_is_received() {
		if(!(strResponse.isEmpty()))
			System.out.println("Received some response for API "+TestBase.baseURI+extension);		
	}

	//checking success status for all positive scenarios
	@And("response will give success status")
	public void response_will_give_success_status() {
	    int actualStatusCode = response.getStatusCode();
	    Assert.assertEquals(actualStatusCode, successCode);
	    String actualStatusLine = response.getStatusLine();
	    Assert.assertEquals(actualStatusLine, successLine);
	    //throw new io.cucumber.java.PendingException();
	}

	//response validation for 1st scenario
	@And("response contains all latest exchange rates for base EURO")
	public void response_contains_all_latest_exchange_rates_for_base_EURO() {
	    applicationType = response.getContentType();
	    if(applicationType.contains("json")) {
	    	String baseCurrency = jsonPath.getString("base");
	    	Assert.assertEquals(baseCurrency, "EUR");
	    	List<Object> list = jsonPath.getList("rates");
	    	Assert.assertNotNull(list);
	    }
	}

	//getting API for 2nd scenario
	@Given("incorrect API is used")
	public void incorrect_API_is_used() {
	    incorrectURI = Data.getData("Exchange Rates_02");
	}
	
	//request trigger for 2nd scenario
	@When("GET request is sent with incorrect API")
	public void get_request_is_sent_with_incorrect_API() {
		RestAssured.baseURI = incorrectURI;
		RequestSpecification request = RestAssured.given();
		response = request.get("");
		strResponse = response.asString();
	}

	//response validation for 2nd scenario
	@Then("response contains error message")
	public void response_contains_error_message() {
	   int errorCode = response.getStatusCode();
	   Assert.assertEquals(errorCode, 400);
	   String errorLine = response.getStatusLine();
	   Assert.assertEquals(errorLine, "Bad Request");
	   System.out.println(jsonPath.get("error"));
	}

	//getting API for 3rd scenario
	@Given("API with symbols parameter {string} is available")
	public void api_with_symbols_parameter_is_available(String string) {
		extension = Data.getData("Exchange Rates_03");
		extension = extension+string;
		param=string;
	}

	//response validation for 3rd scenario
	@And("response contains latest exchange rates for currencies given in symbols parameter")
	public void response_contains_latest_exchange_rates_for_currencies_given_in_symbols_parameter() {
		String[] lp = param.split(",");
	    List<Object> l = jsonPath.getList("rates");
	    Assert.assertEquals(l.size(), lp.length);
	    for(int i=0;i<lp.length;i++) {
	    	if(!(strResponse.contains(lp[i]))) {
	    		flag=false;
	    	}
	    }
	   Assert.assertEquals(flag, true);
	}

	//getting API for 4th scenario
	@Given("API with base parameter {string} is available")
	public void api_with_base_parameter_value_is_available(String currency) {
		extension = Data.getData("Exchange Rates_04");
		extension = extension+currency;
		param = currency;
	}
	
	//response validation for 4th scenario
	@And("response contains all latest exchange rates for base currency given in base parameter")
	public void response_contains_all_latest_exchange_rates_for_base_currency_given_in_base_parameter() {
	    
		Assert.assertEquals(jsonPath.get("base"), param);;
	}

	//getting API for 5th scenario
	@Given("API for past date since 1999 is available")
	public void api_for_since_is_available() {
		extension = Data.getData("Exchange Rates_06");
	}

	//response validation for 5th scenario
	@Then("response contains historic exchange rates for base EURO for specified date")
	public void response_contains_historic_exchange_rates_for_base_EURO_on_specified_date() {
	    
		Assert.assertEquals(jsonPath.get("date"), extension);
	}

	//getting API for 6th scenario
	@Given("API for past date for base parameter and symbols parameter values is available")
	public void api_for_past_date_for_base_and_symbols_parameter_values_is_available() {
	    extension = Data.getData("Exchange Rates_09");
	}

	//response validation for 6th scenario
	@Then("response contains historic exchange rates between base and symbols currencies on specified date")
	public void response_contains_historic_exchange_rates_between_base_and_symbols_currencies_on_specified_date() {
	    
		Assert.assertEquals(jsonPath.get("base"), "USD");
		//List l = jsonPath.getList("rates");
		
	}

	//getting API for 7th scenario
	@Given("API for future date is available")
	public void api_for_future_date_is_available() {
	    extension = Data.getData("Exchange Rates_12");
	    
	}

	//response validation for 7th scenario
	@Then("response contains latest exchange rates for base EURO")
	public void response_contains_latest_exchange_rates_for_base_EURO() {
	    
		Assert.assertEquals(jsonPath.get("date"), extension);
	}


}
