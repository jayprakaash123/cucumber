package tests;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.DataTable;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AutomationTest {
	public WebDriver driver;
	public WebDriverWait wait;
	public Properties pro;
	public Scenario sc;
	@Before
	public void method1(Scenario sc)throws Exception
	{
		this.sc=sc;
		pro=new Properties();
		FileInputStream fs=new FileInputStream("D:\\selenium2018\\myproject\\src\\test\\resources\\mypack\\automationtest.properties");
		pro.load(fs);
	}
	@Given("^launch site using\"(.*)\"$")
	public void launch(String b)
	{
		if(b.equals("chrome"))
		 {
			 System.setProperty("webdriver.chrome.driver",pro.getProperty("cdriver"));
			 driver=new ChromeDriver();
		 }
		else
		{
			System.setProperty("webdriver.gecko.driver",pro.getProperty("ffdriver"));
			 driver=new FirefoxDriver();
		}
		
			
		driver.get(pro.getProperty("url"));
		driver.manage().window().maximize();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@name='login']")));
		
			
	} 
	
	@Then("^title contain\"(.*)\"$")
	public void title(String y)
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@name='login']")));
		String st=driver.getTitle();
		System.out.println(st);
		if(st.contains("y"))
		{
			sc.write("title test passed");
		}
		else
		{
			sc.write("title test failed");
		}
	}
	@When("^do login with valid data$")
	public void login(DataTable dt) throws Exception
	{
		List<List<String>> list=dt.asLists(String.class);
		driver.findElement(By.xpath("//*[@name='username']")).sendKeys(list.get(1).get(0));
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//*[@name='password'])[1]")).sendKeys(list.get(1).get(1));
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@name='login']")).click();
	}
	@Then("^do logout$")
	public void logout()
	{
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='Dashboard']")));
		driver.findElement(By.xpath("//*[text()='Logout']")).click();
	}
	@Then("^close site$")
	public void close()
	{
		driver.close();
	}
	
}
