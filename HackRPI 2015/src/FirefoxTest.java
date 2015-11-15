import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxTest extends AbstractTest{
	public static void main(String[] args){
		
		WebDriver driver = new FirefoxDriver();
		try {
			test(driver, args);
			driver.quit();
		} catch (Exception e){
			driver.quit();
		}
	}
}