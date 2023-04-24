package MainPage;

import Base.Base;

import java.util.Iterator;
import java.util.Set;
import java.util.List;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.apache.poi.ss.usermodel.Cell;
import  com.google.common.collect.Table;

public class Page extends Base {
    By Signin = By.xpath("//*[@id=\"i0116\"]");
    By NextBtn = By.xpath("//*[@id=\"idSIButton9\"]");
    By password = By.xpath("//*[@id=\"i0118\"]");
    By pwdSignin = By.xpath("//*[@id=\"idSIButton9\"]");
    By Nobtn = By.xpath("//*[@id=\"idBtn_Back\"]");
    By viewall = By.xpath("/html/body/div/app-root/main/div/unily-spa/dynamic-content-viewer/section/upgrade-component-wrapper/div/div/div[3]/div[1]/div[2]/div/reusable-widget/div/ufs-story-rollup/section/div/div[7]/a");
    By opt = By.xpath("/html/body/div[1]/app-root/main/div/unily-spa/dynamic-content-viewer/section/upgrade-component-wrapper/div/div/div/div[1]/div[1]/section/div[1]/div[2]");
 @Test
 public void page() throws InterruptedException, IOException { 
	 try{                                                //To print profile details
	logger = report.createTest("Print profile from the webpage");
	{
	openURL("websiteURLKey");
	driver.findElement(Signin).sendKeys("2250646@cognizant.com");     //Signin to portal
	Thread.sleep(5000);
	driver.findElement(NextBtn).click();
	Thread.sleep(3000);
	driver.findElement(password).sendKeys("Gshb@13161623");           //Password is inserted
	Thread.sleep(3000);
	driver.findElement(pwdSignin).click();
	driver.findElement(Nobtn).click();
	WebElement p = driver.findElement(By.xpath("//*[@id=\"user-name\"]"));    //Retrieval of Profile Details
	String s = p.getText();
	System.out.println("Profile details: " + s);  
	Screenshoot(" Profile details");
	reportPass("Profile details Retrieved");
     }
	 }catch(Exception e) {
	 reportFail(e.getMessage());
 }
 }
     public void around() throws InterruptedException, IOException{  //To Verify "Around Cognizant" presence
	try {
   logger = report.createTest("Verify AROUND COGNIZANT on the webpage");
	{
	String t = "AROUND COGNIZANT";
	//getPageSource()
	if(driver.getPageSource().contains("AROUND COGNIZANT")) {
		System.out.println("Text: "+ t + " is present");
	} else {
		System.out.println("Text: " + t + " is not present");
	}
     reportPass("AROUND COGNIZANT is verified");
	} 
	}catch(Exception e) {
		 reportFail(e.getMessage());
	 }
     }
     public void View() throws InterruptedException, IOException{           // To get the news page
	try {
   logger = report.createTest("To Retrieve News Page");
	{
	driver.findElement(viewall).click();
	
	Set<String>handles = driver.getWindowHandles();                         //To handle new window tab
	Iterator it = handles.iterator();
	String parentid = (String) it.next();
	String childid = (String) it.next();
	driver.switchTo().window(childid);
	Screenshoot("News Page");
	WebElement r = driver.findElement(By.cssSelector("search-centre-total[class='modern-search-center__menu__item ng-isolate-scope'] span[class='ng-binding ng-scope']"));
	String t1 = r.getText();                                          // About 11,711 results is Retrieved
	System.out.println("Text :" + t1);
	
	WebElement b = driver.findElement(By.xpath("//button[@aria-label='Sorting options. Press enter to expand sort options., Currently sorting using: Newest']"));
	String a = b.getText();                                           // Newest is retrieved
	System.out.println("Text :" + a);	

	WebElement link = driver.findElement(By.cssSelector("label[for='toggleExactMatch']"));   //By clicking on exactMatchesOnly It is verified in the url
	link.click();                                                     
	 
    String g = "exactMatchesOnly";
	if(driver.getCurrentUrl().contains("exactMatchesOnly")) {
		System.out.println("Verified");
	} else {
		System.out.println("Not Verified");
	}
	
	WebElement k = driver.findElement(By.xpath("//button[normalize-space()='Today']"));    //Click on Today button
	k.click();
	
	WebElement newsLinks = driver.findElement(By.cssSelector("span[ng-if='!document.hitHighlights.title']"));
    newsLinks.click();
    
    WebElement text = driver.findElement(By.cssSelector(".story-page__body"));
    String f = text.getText();
    System.out.println(" " + f);
    
    
    XSSFWorkbook workbook = new XSSFWorkbook();                         //Content of news article is saved in the Excel
    XSSFSheet sheet = workbook.createSheet();
        
    String[] sentences = f.split("\\. ");
    
    // Create a new row and cell for each sentence
    int rowIndex = 0;
    for (String sentence : sentences) {
      Row row = sheet.createRow(rowIndex++);
      Cell cell = (Cell) row.createCell(0);
      cell.setCellValue(sentence);
    }
    
    FileOutputStream outstream = new FileOutputStream("C:\\Users\\USER\\eclipse-workspace\\cognizantnews\\ExcelReport\\News.xlsx");
    workbook.write(outstream);
    outstream.close();
    
    
    reportPass("NewsPage is Verified");
	}
	}catch(Exception e) {
		 reportFail(e.getMessage());
	 }
} 
}

 

