package com.stepsdefs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.core.WebDriverFactory;
import com.pageobjects.CustomerCarePageObjects;
import com.pageobjects.HomepageObject;
import com.pageobjects.LoginPageObjects;
import com.pageobjects.Menu;
import com.pageobjects.SearchPageObjects;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefn {
	
	public static final Logger logger = LogManager.getLogger(StepDefn.class);

	
	WebDriver driver;
	String Url =  "https://www.justdial.com/";
	int implict_wait_timeout_in_sec = 20;
	Scenario sc;
	
	
	LoginPageObjects loginpage;
	HomepageObject homepage;
	SearchPageObjects searchobject;
	CustomerCarePageObjects customercare;
	Menu menuobject;
	
	
	@Before
	public void setUp(Scenario sc) throws Exception
	{
		this.sc = sc;
		String browserName = WebDriverFactory.getBrowserName();
		driver = WebDriverFactory.getWebDriverForBrowser(browserName);
		logger.info("Browser invoked.");
		
		loginpage=new LoginPageObjects(driver);
		homepage=new HomepageObject(driver);
		searchobject=new SearchPageObjects(driver);
		customercare=new CustomerCarePageObjects(driver);
		menuobject=new Menu(driver);
		
		
	}
	
	@After(order=1)
	public void cleanUp() 
	{
		WebDriverFactory.quitDriver();
		 sc.log("Browser Closed");
	}
	
	@After(order=2)
	public void takeScreenShot(Scenario s) 
	
	{
		if (s.isFailed()) 
		{
			TakesScreenshot srnsht = (TakesScreenshot)driver;
			byte[] data = srnsht.getScreenshotAs(OutputType.BYTES);
			 sc.attach(data, "image/png","Failed Step Name: " + s.getName());
        }
		else
		{
            sc.log("Test case is passed, no screen shot captured");
        }
	}
	
	
//_____________________________________________Login Page Implementation____________________________________________________________________________________________________________________________

//Pre-requisite for all scenarios
	
		@Given("User is navigated to home page of application")
		public void user_is_navigated_to_home_page_of_application() {
		    WebDriverFactory.navigateToTheUrl(Url);
		    sc.log("User navigated to application url");
		    String OriginalTitle="Justdial - Local Search, Social, News, Videos, Shopping";
		    homepage.validatePageTitleMatch(OriginalTitle);
		    
		}


// 1st scenario to sign up the application with user name and mobile number
		
		@When("User clicks on Sign up link at the top right corner of the application")
		public void user_clicks_on_sign_up_link_at_the_top_right_corner_of_the_application() 
		{
			homepage.ClickOnSignUp();                       
		    
		}
		@When("User enters name as {string} and Phone number as {string} and clicks on Submit Button")
		public void user_enters_name_as_and_phone_number_as_and_clicks_on_submit_button(String string, String string2) 
		{
			homepage.SignUp();
		    
		}
		@Then("User is displayed with the message as {string}")
		public void user_is_displayed_with_the_message_as(String string) {
			homepage.Otp_Validation();
		    
		}


//2nd scenario to login with correct name and incorrect mobile number to fetch error message
		
		

		@When("User clicks on Login-in link of the application")
		public void user_clicks_on_login_in_link_of_the_application() throws InterruptedException 
		{
			homepage.ClickOnLogin();
			Thread.sleep(implict_wait_timeout_in_sec);
			    
		}


		@When("User enters name {string} and Phone number {string} and clicks on Submit Button")
		public void user_enters_name_and_phone_number_and_clicks_on_submit_button(String string, String string2) throws Exception
		{
				 
			loginpage.LogIn(string2, string);
			sc.log("Enetred wrong mobile number for validation of error message");
					
		}


        @Then("User gets error message as {string}")
		public void user_gets_error_message_as(String string)
        {
		     loginpage.ErrorMessage_Valid_Name_MobileNumber(string);
		}

//3rd scenario user login without credentials to show error alert 
			
			
			
		@When("User do not enter any name and phone number but clicks on Submit Button")
		public void user_do_not_enter_any_name_and_phone_number_but_clicks_on_submit_button() 
		{
			loginpage.LogIn_WithoutCredentials();
		}
		
		@Then("User gets error message as \"Please enter a valid name !!\"")
		public void user_gets_error_message(String string) {
			
			loginpage.ErrorMessage_Valid_Name_MobileNumber(string);
		}

// 4th scenario to restrict number of digits for mobile number field
		

		@When("User clicks on Login-in link at the top right corner of the application")
		public void user_clicks_on_login_in_link_at_the_top_right_corner_of_the_application() {
			user_clicks_on_sign_up_link_at_the_top_right_corner_of_the_application();
		}



		@Then("User is able to enter only {string} digits in the Mobile text field")
		public void user_is_able_to_enter_only_digits_in_the_mobile_text_field(String string) {
			 loginpage.MobileNumberDigitsLimit(string);
		}



// _____________________________________________Search Page Implementation_____________________________________________________________________________
				
				
       @Given("User navigated to the home application url")
	   public void user_navigated_to_the_home_application_url() {
						
    	   user_is_navigated_to_home_page_of_application();
		}


       @When("User enters {string} in search textbox")
		public void user_enters_in_search_textbox(String string) throws Exception {
						
			searchobject.SearchOperation(string);	
					    
		}
					
		@When("User clicks search button")
		public void user_clicks_search_button() {
					 
			       searchobject.ClickSearch();
		}
					
					
		@Then("User is able to see result related to {string}")
		public void user_is_able_to_see_result_realated_to(String string) {
           logger.info("Result display");					   
		}

// 2nd scenario of search functionality

       @When("User enters {string} in search text box")
	   public void user_enters_in_search_text_box(String string) {
			 searchobject.EnteringPartialSTring(string);
	   }

       @Then("User is able to see the drop down under search text box with all the items with text {string}")
		public void user_is_able_to_see_the_drop_down_under_search_text_box_with_all_the_items_with_text(String string) throws Exception {
    	   searchobject.DropDownOfSuggestions();
	   }

//______________________________________________________Customer Care Implementation_________________________________________________________________________						
						


      @Given("User navigates to Customer care page by clicking on Customer Care tab at the right-mid of the page")
       public void user_navigates_to_customer_care_page_by_clicking_on_customer_care_tab_at_the_right_mid_of_the_page() {
    	       customercare.ClickOnCustomerCare();
    	   }



      @When("User enter {string} in the Text box")
       public void user_enter_in_the_text_box(String string) {
    	       customercare.BusinessSelection(string);
    	   }
    	   
      @Then("User is able to see the drop down and all the items in the list must have text as {string}")
      public void user_is_able_to_see_the_drop_down_and_all_the_items_in_the_list_must_have_text_as(String string) {
    	      customercare.ResultOptions(string);               
    	   }



//____________________________________________________Menu Implementation______________________________________________________________________________________________


	}
