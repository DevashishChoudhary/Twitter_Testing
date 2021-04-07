package twitter.testcases.loginAndRegistration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTests {
	public WebDriver driver;
	
	public String location = "/Users/isshu/eclipse-workspace/Twitter_Testing/reportLoginTests.html";
	public static ExtentReports report;
	public static ExtentTest test;
	
	@BeforeClass
	public void setup() {
		
		report = new ExtentReports(location);
		test = report.startTest("Login Tests Report - ");
		
		WebDriverManager.chromedriver().setup();
		System.setProperty("webdriver.chrome.driver", "/Users/isshu/Desktop/Test_Automation/Selenium_Drivers/chromedriver");

		driver=new ChromeDriver();  

		driver.navigate().to("https://twitter.com/");	  
		driver.manage().window().maximize();
		
		report = new ExtentReports(location);
		test = report.startTest("Login Tests Report - ");

		
	}
	
	
	/*@BeforeMethod
	public static void startReport(){
		test = report.startTest("Login Tests Report - ");
	}
	
	@AfterMethod
	public void endReport(){
		report.endTest(test);
		report.flush();
	}*/
	
	
	//	Checking login functionality with all passing test cases
	@Test(dataProvider = "passing-login-details" , dataProviderClass = twitter.utils.ReadLoginData.class)
	public void tc_001(String username, String password) throws InterruptedException {
		
		test.log(LogStatus.INFO, "The Login test Case");
		
		try {
			Thread.sleep(3000);
			WebElement helloSignIn = driver.findElement(By.xpath("//*[@id=\"react-root\"]/div/div/div/main/div/div/div/div[1]/div/div[3]/a[2]/div/span/span"));
			helloSignIn.click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id=\"react-root\"]/div/div/div[2]/main/div/div/div[2]/form/div/div[1]/label/div/div[2]/div/input")).sendKeys(username);  
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"react-root\"]/div/div/div[2]/main/div/div/div[2]/form/div/div[2]/label/div/div[2]/div/input")).click();
			driver.findElement(By.xpath("//*[@id=\"react-root\"]/div/div/div[2]/main/div/div/div[2]/form/div/div[2]/label/div/div[2]/div/input")).sendKeys(password);
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"react-root\"]/div/div/div[2]/main/div/div/div[2]/form/div/div[3]/div/div")).click();
			Thread.sleep(2000);
			
			test.log(LogStatus.PASS, "The Login test Passed");
		}
		catch(Exception e) {
			test.log(LogStatus.ERROR, "The Login test Failed");
			System.out.println("Error Encountered");
		}
	}
	
	@AfterClass
	public void terminate() {
		
		report.endTest(test);
		report.flush();
		driver.quit();
	}
	
}