package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class LoginPage {

    //creating Constructor for the page to initialize the elements so when we create an object of the (LoginPage) later
    //the constructor here will initialize the elements on this page so we can use them in the other class
    public LoginPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    //************ ELEMENTS *************
    //to find the email input box
    @FindBy(xpath = "//input[@name='email']")
    public WebElement emailInput;

    //to find the password input box
    @FindBy(xpath = "//input[@name='password']")
    public WebElement passwordInput;

    //to find the login button
    @FindBy(xpath = "//button[text()='Login' and @type='submit']")
    public WebElement loginButton;

    //this is tricky because the message will disappear so we need to get the xpath for it in the devtools
    //then click on login button again it will show us the message again but in the xpath it will tell us it
    //is 1 of 1 xpath in the devtools
    @FindBy(xpath = "//p[text()='These credentials do not match our records.']")
    public WebElement loginErrorMessageLabel;

}
