package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class SettingsPage {

    //creating a constructor for the page
    public SettingsPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    //************ ELEMENTS *************
    //to find the text Settings
    @FindBy(xpath = "//h3[text()='Settings']")
    public WebElement settingsLabel;

}
