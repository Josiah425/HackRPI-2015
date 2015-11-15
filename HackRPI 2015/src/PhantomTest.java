import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class PhantomTest extends AbstractTest {

	public static void main(String[] args){

		Capabilities caps = new DesiredCapabilities();
        ((DesiredCapabilities) caps).setJavascriptEnabled(true); 
        Path currentPath = Paths.get("");
        String path = currentPath.toAbsolutePath().toString();
        ((DesiredCapabilities) caps).setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, path + "\\phantomjs.exe");
        WebDriver driver = new PhantomJSDriver(caps);
        try {
	        test(driver, args);
	        driver.quit();
        } catch (Exception e){
        	driver.quit();
        }


	}
}
