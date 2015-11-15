import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

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

        // Output text into text file
        Path currentPath = Paths.get("");
        String path = currentPath.toAbsolutePath().toString();
        File fileOut = new File(path + "\\dataOutput2.txt");
        
		FileOutputStream out = null;
		try{
			out = new FileOutputStream(fileOut);
		}
		catch(Exception e){

		}
		WebElement pageNumber = driver.findElement(By.cssSelector("div#content .feature table tr a:last-child"));
		String stringNumber = pageNumber.getAttribute("href");
		String[] hyperLinkArray = stringNumber.split("=");
		int lastPage = Integer.parseInt(hyperLinkArray[2]);
		byte[][] sodexoData = new byte[lastPage][];

		for (int i=0; i < lastPage-1; i++){
			WebElement query4 = driver.findElement(By.cssSelector("div#content .feature table"));
			sodexoData[i] = query4.getText().getBytes();
			if (i == lastPage - 2){
				WebElement pageTurner = driver.findElement(By.cssSelector("div#content .feature table tr a:last-child"));
				pageTurner.click();
			}
			else if (i < lastPage-2){
				WebElement pageTurner = driver.findElement(By.cssSelector("div#content .feature table tr a:nth-last-child(2)"));
				pageTurner.click();
			}
		}
		try{
			for (int j=0; j<sodexoData.length; j++){
				out.write(sodexoData[j]);
			}
			out.close();
			System.out.println("----- File created. -----");
		}
		catch (Exception e){

		}
		parseData(fileOut);
	}
	
	public static void parseData(File fileOut){
		ArrayList<String> s = new ArrayList<>();
		int i = 0;
		try {
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(fileOut);
			while (scan.hasNext()){
				s.add(i, scan.nextLine());
				i++;
			}
		} catch (FileNotFoundException e) {

		}
		
		ArrayList<String> data = new ArrayList<>();
		int k = 0;
		for (int j = 0; j < s.size(); j++){
			if (s.get(j).contains("AUTH")){
		
				String[] date = s.get(j).split("[\\s,]+");			
				String money = s.get(j+1);
				data.add(k, date[1] + " " + date[2] + " " + date[3] + " " + money);
				k++;
			}
		}
		
		for (int m = 0; m < data.size(); m++){
			System.out.println(data.get(m));
		}
	}
}