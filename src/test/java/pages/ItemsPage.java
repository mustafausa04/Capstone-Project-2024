package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class ItemsPage {

    //to create a Constructor for the page
    public ItemsPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    //************ ELEMENTS *************
    //to find the Add item button
    @FindBy(xpath = "//button[text()=' Add Item']")
    public WebElement addItemButton;

    //to find the name input box, here we will use the (parent) div to get to the child 9input)
    @FindBy(xpath = "//div[@class='relative rounded-md shadow-sm font-base']/input")
    public WebElement nameInput;

    //to find the price input box
    @FindBy(xpath = "//input[@id='0']")
    public WebElement priceInput;

    //to find the unit input box, here we can not use (Select) as drop down because there is no (Select) tag
    //If the tag was (SElECT) then --> you can use Select class from selenium
    //Select select = new Select(driver)
    //so here we will get the normal input box through its parent (div)
    @FindBy(xpath = "//div[@tabindex='-1']/input")
    public WebElement unitSelect;

    //to find the description input box
    @FindBy(xpath = "//textarea[@name='description']")
    public WebElement descriptionInput;

    //to find the save item button
    @FindBy(xpath = "//button[text()=' Save Item']")
    public WebElement saveItemButton;

    //to find the list of anchors items
    @FindBy(xpath = "//a[contains(@href,'/admin/items/')]")
    public List<WebElement> itemsList;


}
