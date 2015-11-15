import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxTest extends AbstractTest{
	public static void main(String[] args){
		
		WebDriver driver = new FirefoxDriver();
		test(driver, args);
//		driver.get("https://bing.campuscardcenter.com/ch/login.html");
//        WebElement query = driver.findElement(By.name("username"));
//        query.sendKeys(args[0]);
//        WebElement query2 = driver.findElement(By.name("password"));
//        query2.sendKeys(args[1]);
//        query2.sendKeys(Keys.ENTER);
//        WebElement query3 = driver.findElement(By.cssSelector("div#content .feature table:nth-child(2) tr:last-child>td>a"));
//        System.out.println(query3.getText());
//        query3.click();
        driver.quit();
	}
}
