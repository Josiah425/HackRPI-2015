import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

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
        WebElement query3 = driver.findElement(By.cssSelector("div#content .feature table:nth-child(2) tr:nth-child(5)>td>a"));
        query3.click();
        WebElement query4 = driver.findElement(By.cssSelector("div#content .feature table"));
        byte[] sodexoData = query4.getText().getBytes();
        File fileOut = new File("C:\\Users\\Josiah\\Documents\\HackRPI2015\\dataOutput.txt");
        FileOutputStream out = null;
        try {
			out = new FileOutputStream(fileOut);
			out.write(sodexoData);
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
