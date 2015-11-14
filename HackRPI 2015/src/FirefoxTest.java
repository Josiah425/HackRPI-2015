import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class FirefoxTest {
	public static void main(String[] args){
		
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.youtube.com");
		System.out.println(driver.getTitle());
        WebElement query = driver.findElement(By.name("search_query"));
        query.sendKeys("Hello");
        query.submit();
        System.out.println(driver.getTitle());
        //driver.quit();
	}
}
