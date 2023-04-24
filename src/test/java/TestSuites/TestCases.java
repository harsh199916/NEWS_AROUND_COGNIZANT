package TestSuites;
import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Base.Base;
import MainPage.Page;
public class TestCases extends Base {
     Page mp = new Page();
     
	@BeforeTest
	public void invokeBrowser() {
		logger = report.createTest("Executing Test Cases");
		mp.invokeBrowser();
		reportPass("Browser is Invoked");
	}
	
	@Test(priority = 1)
	public void page() throws InterruptedException, IOException  {
		mp.page();
		reportPass("Profile details Retrieved");
	   
	    //reportPass("News Page is retrieved by using view button");
	}
	@Test(priority = 2)
		public void around() throws InterruptedException, IOException {
		mp.around();
		reportPass("AROUND COGNIZANT is verified");
	}
	@Test(priority = 3)
	public void view() throws InterruptedException, IOException {
		mp.View();
		reportPass("NewsPage is Verified");
	}
	
	@AfterTest
	public void closeBrowser() {
		mp.endReport();
		mp.closeBrowser();
	}
}
