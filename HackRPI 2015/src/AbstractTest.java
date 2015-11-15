import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.rosuda.JRI.Rengine;

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
		File fileOut = new File(path + "\\dataOutput.txt");

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

		for (int i=0; i < lastPage; i++){
			System.out.println("Retreiving data from page " + (i+1));
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
			for (int j=0; j < sodexoData.length; j++){
				out.write(sodexoData[j]);
			}
			System.out.println("----- File created. -----");
			out.close();
		}
		catch (Exception e){

		}
		parseData(fileOut);
	}

	public static void parseData(File fileOut){

		Scanner scan = null;
		try {
			scan = new Scanner(fileOut);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ArrayList<String> data = new ArrayList<>();
		int k = 0;

		while (scan.hasNext()){
			String s = scan.nextLine();
			if (s.contains("AUTH")){		
				String[] date = s.split("[\\s,]+");			
				data.add(k, date[1] + " " + date[2] + " " + date[3] + " " + scan.nextLine());
				k++;
			}
		}		
		scan.close();


		// Format data
		Map<String, String> map = new TreeMap<>();
		String[][] stringData = new String[data.size()][2];
		DecimalFormat df = new DecimalFormat("0.00");
		for (int i = 0; i < data.size(); i++){
			String[] s = data.get(i).split("\\s+");
			stringData[i][0] = monthToNum(s[0]) + "/" + s[1] + "/" + s[2];
			stringData[i][1] = s[3].substring(1, s[3].length()-1);
			
			if (map.get(stringData[i][0]) == null){
				map.put(stringData[i][0], stringData[i][1]);
			}
			else {
				map.put(stringData[i][0], "" + df.format(((Double.parseDouble(map.get(stringData[i][0])) + Double.parseDouble(stringData[i][1])))));
			}
		}


		// Output text into text file
		Path currentPath = Paths.get("");
		String path = currentPath.toAbsolutePath().toString();
		File dataOut = new File(path + "\\RData.csv");

		FileOutputStream out = null;
		try{
			out = new FileOutputStream(dataOut);
			for (String key : map.keySet()){
				String s = key + "," + map.get(key) + "\n";
				out.write(s.getBytes());
			}
			System.out.println("----- Raw data file created. -----");
			out.close();

		}
		catch(Exception e){

		}
		
//		// TODO:
//		System.loadLibrary("jri");
//		Rengine re = new Rengine(new String[] { "--vanilla" }, false, null);
//
//        System.out.println("Rengine created, waiting for R");
//
// 
//
//        // the engine creates R is a new thread, so we should wait until it's
//
//        // ready
//
//        if (!re.waitForR()) {
//
//            System.out.println("Cannot load R");
//
//            return;
//
//        }
	}

	public static int monthToNum(String month){
		int m = 0;
		switch(month){
		case "Jan":
			m = 1;
			break;
		case "Feb":
			m = 2;
			break;
		case "Mar":
			m = 3;
			break;
		case "Apr":
			m = 4;
			break;
		case "May":
			m = 5;
			break;
		case "June":
			m = 6;
			break;
		case "Jul":
			m = 7;
			break;
		case "Aug":
			m = 8;
			break;
		case "Sep":
			m = 9;
			break;
		case "Oct":
			m = 10;
			break;
		case "Nov":
			m = 11;
			break;
		case "Dec":
			m = 12;
			break;
		default:
		}

		return m;
	}
}