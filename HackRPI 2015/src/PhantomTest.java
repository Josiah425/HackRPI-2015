import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class PhantomTest {

	public static void main(String[] args){

		Capabilities caps = new DesiredCapabilities();
        ((DesiredCapabilities) caps).setJavascriptEnabled(true); 
        ((DesiredCapabilities) caps).setCapability(
                PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,     "C:\\Users\\JeffreyChan\\Downloads\\phantomjs-2.0.0-windows\\phantomjs-2.0.0-windows\\bin\\phantomjs.exe"  );
		
        PhantomJSDriver driver = new PhantomJSDriver(caps);//FirefoxDriver();
        
        driver.get("http://www.youtube.com");

        WebElement query = driver.findElement(By.name("search_query"));
        
        query.sendKeys("selenium webdriver");
        query.sendKeys(Keys.ENTER);
        List<WebElement> search_result = driver.findElements(By.cssSelector("div#results ol.section-list>li ol.item-section li h3>a"));   //#search-results > li h3 > a"));
       
        for(WebElement each_result : search_result) {
        	System.out.println(each_result.getAttribute("title"));
        }



       driver.quit();


	}
}
