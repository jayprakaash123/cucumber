package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Homepage {

	public WebDriver driver;
	
	@FindBy(name="mobile no")
	public WebElement mbno;
	
	@FindBy(name="password")
	public WebElement pwd;
	
	@FindBy(xpath="(//button[contains(text(),'Login')])[1]")
	public WebElement login;
	
	@FindBy(xpath="(//*[contains(text(),'Enter your mobile number')])[1]")
	public WebElement mbno_blank_err;
	
	@FindBy(xpath="(//*[contains(text(),'Enter password')])[2]")
	public WebElement pwd_err;
	
	@FindBy(xpath="(//*[contains(text(),'Try Again.')])[1]")
	public WebElement pwd_invalid_err;
	
	@FindBy(xpath="(//*[contains(text(),'Your mobile number is not register')])[1]")
	public WebElement mbno_invalid_err;
	
	//operation
	public Homepage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	public void fillmbno(String x)
	{
		mbno.sendKeys(x);
	}
	public void fillpwd(String x)
	{
		pwd.sendKeys(x);
	}
	public void clicklogin()
	{
		login.click();
	}
	
}


