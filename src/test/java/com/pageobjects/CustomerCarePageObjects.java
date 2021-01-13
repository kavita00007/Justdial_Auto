package com.pageobjects;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomerCarePageObjects {
	
	WebDriver driver;
	private By CustomerCare=By.xpath("//a[@class='cscare ']");
	private By BusinessSelection=By.id("what");
	//private By OnlineSearchResult= By.xpath("//a[@class='tcon']");
	private By Dropdown=By.xpath("//span[@class='lng_autowht tcon']");


	public CustomerCarePageObjects(WebDriver driver) {
		this.driver = driver;
	}
	
	public void ClickOnCustomerCare() {
		driver.findElement(CustomerCare).click();
		
	}
	
	public void BusinessSelection(String s) {
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.elementToBeClickable(BusinessSelection));
		driver.findElement(BusinessSelection).sendKeys(s);
	}
	
	public void ResultOptions(String text) {
		 List<WebElement> list = driver.findElements(Dropdown);
	        for (WebElement list_suggestions : list)
	        {
	            String s = list_suggestions.getText();
	            if (s.contains(text))
	            {
	                Assert.assertTrue(true);

	            }
	            break;

	        }
	 }

	

}
