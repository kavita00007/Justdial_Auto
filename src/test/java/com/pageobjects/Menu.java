package com.pageobjects;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Menu {
	
	WebDriver driver;
	
	private By Menu_list=By.className("//span[@class='hotkeys_text']");

	public Menu(WebDriver driver) {
		this.driver = driver;
	}
	
	public void Menu_Selection(String category)  {
        
	List<WebElement> menu=driver.findElements(Menu_list);
	for(int i=0;i<menu.size();i++)
	{
		WebElement Menulist=menu.get(i);
		String Menu_Category=Menulist.getText();
		if(Menu_Category.equals(category))
		{
			Menulist.click();
		}
	}

	}
	
	public void NavigationToCatogoryPage(String cat) {
		String Title=driver.getTitle();
		if(Title.contains(cat))
		{
	           Assert.assertTrue(true);
		}
		
		else
		{
			Assert.fail("Incorrect page");
		}
	}

}
