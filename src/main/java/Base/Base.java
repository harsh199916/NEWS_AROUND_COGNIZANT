package Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import utils.ExtentReportManager;

public class Base {

	public static WebDriver driver;
	public static Properties prop;
	JavascriptExecutor js;
	public static WebDriverWait wait;
	public ExtentReports report = ExtentReportManager.getReportInstance();
	public ExtentTest logger;
	
	public void invokeBrowser( ) {
		prop = new Properties();
		
		try {
			 prop.load(new FileInputStream("src/main/java/Configuration/Config.properties"));
		}catch (Exception e) {
			e.printStackTrace();
		}
		if (prop.getProperty("browserName").matches("chrome")) {
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+".//Drivers//chromedriver.exe");
			ChromeOptions option = new ChromeOptions();
			
			driver = new ChromeDriver(option);
		}
		if (prop.getProperty("browserName").matches("firefox")) {
			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+".//Drivers//geckodriver.exe");
			FirefoxOptions options = new FirefoxOptions();
			
			options.addPreference("dom.webnotifications.enabled",false);
			driver = new FirefoxDriver(options);
		
	}
	// To maximize the Browser Window
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(500, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
}
	
	public void openURL(String websiteURLKey) {
		driver.get(prop.getProperty(websiteURLKey));
	}
	
	public void reportFail(String report) {
		logger.log(Status.FAIL, report);
		
	}
	public void reportPass(String report) {
		logger.log(Status.PASS, report);
	}
	public void Screenshoot(String fileName) throws IOException {
		TakesScreenshot t = (TakesScreenshot)driver;
		File src=t.getScreenshotAs(OutputType.FILE);
		File dest=new File(System.getProperty("user.dir")+"./Screenshots/"+fileName+".png");
		FileUtils.copyFile(src, dest);
	}
	
	public void endReport() {
		report.flush();
	}
	
	public void closeBrowser() {
		driver.quit();
	}
}