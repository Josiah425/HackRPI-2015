import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class AbstractTest {
	
	public static void test(WebDriver driver, String[] args){
		driver.get("https://bing.campuscardcenter.com/ch/login.html");
        WebElement query = driver.findElement(By.name("username"));
        query.sendKeys(args[0]);
        WebElement query2 = driver.findElement(By.name("password"));
        query2.sendKeys(args[1]);
        query2.sendKeys(Keys.ENTER);
        WebElement query3 = driver.findElement(By.cssSelector("div#content .feature table:nth-child(2) tr:last-child>td>a"));
        query3.click();
	}
}
