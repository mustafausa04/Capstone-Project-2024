package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class DashboardPage {
    //dash board page is the side menu page where it has the other pages links

    //creating a Constructor for the page to initialize the elements
    public DashboardPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }


    //to find the link Items on the left menu to click on it to take us to Items page
    @FindBy(xpath = "//a[@href='/admin/items']")
    public WebElement itemsTab;

    //to find the link Invoices on the left menu to click on it to take us to Invoices page
    @FindBy(xpath = "//a[@href='/admin/invoices']")
    public WebElement invoicesTab;


}
