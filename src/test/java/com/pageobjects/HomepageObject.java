package com.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomepageObject {
	
	public static final Logger logger = LogManager.getLogger(HomepageObject.class);

	
	WebDriver driver;
	private By Login=By.id("h_login");
	private By SignUp=By.id("h_sin_up");
	private By UserName=By.id("lgn_name");
	private By Mobile_Number=By.id("lgn_mob");
	private By Send_OTP=By.id("lgn_smtn");
	private By otp=By.xpath("//div[@class='otp-text wrapper pb-10']");

	
	 public HomepageObject(WebDriver driver)  {
	        this.driver = driver;
	    }
	 

   public void ClickOnLogin()  {
	   boolean b = driver.findElement(Login).isDisplayed();
		Assert.assertEquals("Navigation to login ",true, b);
		driver.findElement(Login).click();
    }
   
   public void ClickOnSignUp()  {
	   boolean b = driver.findElement(SignUp).isDisplayed();
		Assert.assertEquals("Navigation to Sign Up",true, b);
		driver.findElement(SignUp).click();
		
		
    }
   
   public void SignUp() {
	   boolean b = driver.findElement(UserName).isDisplayed();
		Assert.assertEquals("User is ready to sign up",true, b);
		driver.findElement(UserName).sendKeys("Kavita");
		driver.findElement(Mobile_Number).sendKeys("9175333717");
		driver.findElement(Send_OTP).click();
		
   }
   
  
   public void validatePageTitleMatch(String expectedTitle) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		Boolean b = wait.until(ExpectedConditions.titleContains(expectedTitle));
		Assert.assertEquals("Title Validation",true, b);
		logger.info("Page title matched: " + expectedTitle );
	}

   public void Otp_Validation() {
		
	String actual=driver.findElement(otp).getText();
   	String expected="OTP is sent on number";
   	Assert.assertEquals("OTP sent",expected, actual);
   	System.out.println("Otp has been sent to registered mobile numebr");
   	
  	}
   
}
