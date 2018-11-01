package tests;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import pages.Homepage;
import pages.SendSMS;

public class Gluecode
{
 public WebDriver driver;
 public WebDriverWait wait;
 public Homepage hp;
 public SendSMS sms;
 public Scenario sce;
 public Properties pro;
 
 @Before
 public void method1(Scenario sce) throws Exception
 {
	 //use scenarion object for current scenario
	 this.sce=sce;
	 //load property file for current scenario
	 pro=new Properties();
	 FileInputStream fis=new  FileInputStream("D:\\selenium2018\\myproject\\src\\test\\resources\\mypack\\myprojectproperties.properties");
	 pro.load(fis);
 }
 @Given("^launch site using \"(.*)\"$")
 public void method2(String b)
 {
	 //open browser for current scenario
	 if(b.equals("chrome"))
	 {
		 System.setProperty("webdriver.chrome.driver",pro.getProperty("cdriver"));
		 driver=new ChromeDriver();
	 }
	 else if(b.equals("firefox"))
	 {
		 System.setProperty("webdriver.gecko.driver",pro.getProperty("firefoxdriver"));
		 driver=new FirefoxDriver();	 
	 }
	 else
	 {
		System.setProperty("webdriver.ie.driver",pro.getProperty("iedriver"));
		driver=new InternetExplorerDriver();
	 }
	 //create object for page class for current scenario
	 hp=new Homepage(driver);
	 sms=new SendSMS(driver);
	 //open site
	 driver.get(pro.getProperty("url"));
	 
	 //Define wait object
	 wait=new WebDriverWait(driver,20);
	 wait.until(ExpectedConditions.visibilityOf(hp.mbno));
	 driver.manage().window().maximize();
 }
 @Then("^title contain \"(.*)\"$")
 public void method3(String a)
 {
	 wait.until(ExpectedConditions.visibilityOf(hp.mbno));
	 String str=driver.getTitle();
	 if(str.contains(a))
	 {
		 sce.write("title test passed");
	 }
	 else
	 {
		 byte ssbytes[]=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
		 sce.embed(ssbytes, "title test fail");
		 Assert.fail(); 
	 }
 }
 @And("^close site$")
 public void method4()
 {
	 driver.close();
 }
 @When("^enter mobile number as\"(.*)\"$")
 public void method5(String u)
 {
	 wait.until(ExpectedConditions.visibilityOf(hp.mbno));
	 	hp.fillmbno(u);
 }
 @And("^enter password as\"(.*)\"$")
 public void method6(String p)
 {
	 wait.until(ExpectedConditions.visibilityOf(hp.pwd));
	 hp.fillpwd(p);
 }
 @And("^click login$")
 public void method7()
 {
	 wait.until(ExpectedConditions.visibilityOf(hp.pwd));
	 hp.clicklogin();
 }
 @Then("^validate output for criteria\"(.*)\"$")
 public void method8(String c) throws Exception
 {
	// wait.until(temp->((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete"));
	 Thread.sleep(10000);
	 try
	 {
		if(c.equals("all_valid")&&sms.sendsms.isDisplayed())
		{
			sce.write("test passed for valid data");
		}
		else if(c.equals("all_valid") && driver.findElement(By.xpath("//*[text()='SendSMS']")).isDisplayed())
		{
	    sce.write("test passed for valid data");
	    }
		else if(c.equals("mbno_blank")&& hp.mbno_blank_err.isDisplayed())
		{
		 sce.write("test passed for blank number");
		}
		else if(c.equals("pwd_blank") && hp.pwd_err.isDisplayed())
		{
			sce.write("test passed for blank password");
		}
		else if(c.equals("mbno_invalid")&& hp.mbno_invalid_err.isDisplayed())
		{
			sce.write("test passed for invalid mobile no");
		}
		else if(c.equals("pwd_invalid")&& hp.pwd_invalid_err.isDisplayed())
		{
			sce.write("test passed for invalid password");
		}
		else
		{
			byte ssbytes[]=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
			sce.embed(ssbytes, "login test failed");
			Assert.fail();
		}
	 }
	 catch(Exception ex)
	 {
		 byte ssbytes[]=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
			sce.embed(ssbytes,ex.getMessage());			
	 }
 }
 @When("^do login with valid data$")
 public void method9(DataTable dt)
 {
	 List<List<String>> l=dt.asLists(String.class);
	 hp.fillmbno(l.get(1).get(0));
	 hp.fillpwd(l.get(1).get(1));
	 hp.clicklogin();	 
 }
 @And("^do logout$")
 public void method10() throws Exception
 {
	// wait.until(temp->((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete"));
	 Thread.sleep(10000);
	 driver.findElement(By.xpath("(//*[@href='Logout'][@class='logout'])/i")).click();
 }
 @Then("^home page is reopen$")
 public void method11()
 {
	 if(hp.mbno.isDisplayed())
	 {
		 sce.write("logout successfully");
	 }
	 else
	 {
		 byte ssbytes[]=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
			sce.embed(ssbytes, "unsuccessful logout");
			Assert.fail(); 
	 }
 }	
	
}
