package com.pageobjects;



import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.rules.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPageObjects {
	
	public static final Logger logger = LogManager.getLogger(SearchPageObjects.class);

	
	WebDriver driver;
	private By SearchTextBox = By.id("srchbx");
    private By SeachButton=By.xpath("//span[@id='srIconwpr']");
    private By AutoSuggestList=By.className("sautoM");
    
    public SearchPageObjects(WebDriver driver) {
    	this.driver=driver;
    }

    
    public void SearchOperation(String text) throws Exception {
    	driver.findElement(SearchTextBox).sendKeys(text);
    }
    
    public void ClickSearch() {
    	driver.findElement(SeachButton).click();
    	
    }
    
    public void EnteringPartialSTring(String s) {
    	WebDriverWait Wait = new WebDriverWait(driver,20);
		Wait.until(ExpectedConditions.elementToBeClickable(SearchTextBox));
		driver.findElement(SearchTextBox).sendKeys(s);    
	}
    
	public void DropDownOfSuggestions() throws Exception {
		Thread.sleep(2000);
        List <WebElement> suggestions=driver.findElements(AutoSuggestList);
		for(WebElement suggest: suggestions)
		{
			System.out.println(suggest.getText());
		}
	}
}
