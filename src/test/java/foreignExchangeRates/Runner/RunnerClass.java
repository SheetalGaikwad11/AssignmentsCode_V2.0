//Runner class to run the cucumber flow

package foreignExchangeRates.Runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
		features = "C:\\Users\\Sheetal Gaikwad\\eclipse-workspace\\VirtusaAssignment\\src\\main\\java\\Features\\Regression.feature"
		,glue= {"foreignExchangeRates.gluecode"}
		)

public class RunnerClass {
	

}
