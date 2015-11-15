import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class PhantomTest extends AbstractTest {

	public static void main(String[] args){

		WebDriver driver = null;
		try {
			
		Capabilities caps = new DesiredCapabilities();
        ((DesiredCapabilities) caps).setJavascriptEnabled(true); 
        ((DesiredCapabilities) caps).setCapability(
//                PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,     "C:\\Users\\JeffreyChan\\Downloads\\phantomjs-2.0.0-windows\\phantomjs-2.0.0-windows\\bin\\phantomjs.exe"  );
        PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,     "C:\\Users\\JeffreyChan\\Documents\\GitHub\\HackRPI-2015\\libs\\phantomjs.exe"  );

        driver = new PhantomJSDriver(caps);
        test(driver, args);
//        driver.get("https://bing.campuscardcenter.com/ch/login.html");
//
//        WebElement query = driver.findElement(By.name("username"));
//        query.sendKeys(args[0]);
//        WebElement query2 = driver.findElement(By.name("password"));
//        query2.sendKeys(args[1]);
//        query2.sendKeys(Keys.ENTER);
//        WebElement query3 = driver.findElement(By.cssSelector("div#content .feature table:nth-child(2) tr:last-child>td>a"));
//        System.out.println(query3.getText());
//        query3.click();
        driver.quit();
        
		} catch (Exception e){
		    driver.quit();

		}
		


	}
}
