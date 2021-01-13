@Login

Feature: Just Dial Application User stories Journeys

Background: Navigation to the url
Given       User is navigated to home page of application

@signup
 Scenario:  User is able to Sign up in the application
    When    User clicks on Sign up link at the top right corner of the application
    And     User enters name as "Kavita" and Phone number as "9175333717" and clicks on Submit Button
    Then    User is displayed with the message as "OTP is sent on number"
    
@login1
 Scenario:  User receives an error message when tries to enter incorrect mobile number in the Login pop up
    When    User clicks on Login-in link of the application
    And     User enters name as "Kavita" and Phone number as "917533371" and clicks on Submit Button
    Then    User gets error message as "Please Enter valid Mobile number!"
    
    
@login2
 Scenario: User receives an error message when tries to enter incorrect name in the Login pop up
    When   User clicks on Login-in link of the application
    And    User do not enter any name and phone number but clicks on Submit Button
    Then   User gets error message as "Please enter a valid name !!"
    
    
 @login3
 Scenario: User is able to enter only 10 digits in the Mobile Text box
   When    User clicks on Login-in link at the top right corner of the application
   Then    User is able to enter only "10" digits in the Mobile text field