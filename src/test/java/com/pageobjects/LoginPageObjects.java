package com.pageobjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPageObjects {
	
	public static final Logger logger = LogManager.getLogger(LoginPageObjects.class);

	
	WebDriver driver;
	private By UserName=By.id("lgn_name");
	private By Mobile_Number=By.id("lgn_mob");
	private By Send_OTP=By.id("lgn_smtn");
	private By errormsg=By.xpath("//div[@id='iup']");
	//private By Errormsg2=By.xpath("//div[@id='iup']");

	
	 public LoginPageObjects(WebDriver driver) 
	 {
	        this.driver = driver;
	 }
	 
	
	 public void LogIn(String name, String no) throws Exception {
		 
		    boolean b = driver.findElement(UserName).isDisplayed();
			Assert.assertEquals("User is ready to log in",true, b);
			driver.findElement(UserName).sendKeys(name);
			logger.info("user entered user name");
			driver.findElement(Mobile_Number).sendKeys(no);    
			logger.info("User entered mobile number");
			driver.findElement(Send_OTP).click();
			Thread.sleep(2000);
		 
	 }
	 
     public void ErrorMessage_Valid_Name_MobileNumber(String msg) {
    	 
    	 String actual=driver.findElement(errormsg).getText();
    	 String expected="Please enter a valid name !!";
    	 Assert.assertEquals(actual, expected);

	}

     
    public void LogIn_WithoutCredentials() {
			 
			boolean b = driver.findElement(Send_OTP).isDisplayed();
			Assert.assertEquals("User is ready to log in",true, b);
			driver.findElement(UserName).sendKeys("");
			logger.info("user kept user name field empty");
			driver.findElement(Mobile_Number).sendKeys("");
			logger.info("User Kept mobile files empty");
			driver.findElement(Send_OTP).click();
			logger.info("User tried to login without username and mobile number");
			 
	 }
	    
	 public void MobileNumberDigitsLimit(String number ) {
		 
		 String max_mobile_length=driver.findElement(Mobile_Number).getAttribute("maxlength");
		 
		    if(max_mobile_length.equals(number))
		    {
		        Assert.assertTrue(true);
		    }
		    else
		    {
		        Assert.fail("Max length of mobile no is not set to 10");
		    }
	 }
	 
}
