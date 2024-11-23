package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pages.DashboardPage;
import pages.ItemsPage;
import pages.LoginPage;
import utilities.ConfigurationReader;
import utilities.Driver;
import utilities.SeleniumUtils;

public class AddItem_StepDef {

    WebDriver driver = Driver.getDriver();
    //LoginPage() is your constructor to create an object and initialize the variables inside the class, so it will
    //tell the memory to create an object name it (loginPade) of the class (LoginPage), so when we call the
    //constructor LoginPage() it will go to the (LoginPage) class and execute what is inside the constructor there
    //which is initializing the elements that are inside that page (LoginPage)
    LoginPage loginPage = new LoginPage();
    DashboardPage dashboardPage = new DashboardPage();
    ItemsPage itemsPage = new ItemsPage();

    @Given("user is logged in successfully")
    public void user_is_logged_in_successfully() throws InterruptedException {
        driver.manage().window().maximize();//we added this line in the hook so we don't need it here
        //navigate to crater, login , enter user/pass/click on login
        driver.get(ConfigurationReader.getPropertyValue("craterUrl"));//this will replace the one under it
        //driver.get("http://crater.primetech-apps.com/login");
        Thread.sleep(2000);
        SeleniumUtils.sendkeysWithActionsClass(loginPage.emailInput ,"entityadmin@primetechschool.com");
        Thread.sleep(2000);
        SeleniumUtils.sendkeysWithActionsClass(loginPage.passwordInput,"primetech@school");
        Thread.sleep(2000);
        loginPage.loginButton.click();
        //Thread.sleep(2000);
    }

    //we will change the @Given to @And to match it to the step in the (Add_Item.feature) file
    @And("the user is on the item page")
    public void the_user_is_on_the_item_page() throws InterruptedException {
        Thread.sleep(3000);
        dashboardPage.itemsTab.click();
        Thread.sleep(2000);
    }

    @When("the user clicks on the Add Item button")
    public void the_user_clicks_on_the_add_item_button() throws InterruptedException {
        Thread.sleep(3000);
        itemsPage.addItemButton.click();
        Thread.sleep(3000);
    }

    //we will change the @Given to @And to match it to the step in the (Add_Item.feature) file
    @And("the user enters the item name")
    public void the_user_enters_the_item_name() throws InterruptedException {
        Thread.sleep(2000);
        itemsPage.nameInput.sendKeys("Book");
        Thread.sleep(3000);
    }

    //we will change the @Given to @And to match it to the step in the (Add_Item.feature) file
    @And("the user enters the item description")
    public void the_user_enters_the_item_description() throws InterruptedException {
        Thread.sleep(2000);
        itemsPage.descriptionInput.sendKeys("Harry Potter");
        Thread.sleep(2000);
    }

    //we will change the @Given to @And to match it to the step in the (Add_Item.feature) file
    //here the itemPrice is equal to "12.99" from the (Add_Item,feature) gherkin
    @And("the user enters the item price {string}")
    public void the_user_enters_the_item_price(String itemPrice) throws InterruptedException {
        Thread.sleep(2000);
        itemsPage.priceInput.sendKeys(itemPrice);
        Thread.sleep(2000);
    }

    //we will change the @Given to @And to match it to the step in the (Add_Item.feature) file
    //here the itemUnit is equal to "grams" from the (Add_Item,feature) gherkin
    @And("the user enters the item unit {string}")
    public void the_user_enters_the_item_unit(String itemUnit) throws InterruptedException {
        Thread.sleep(2000);
        itemsPage.unitSelect.sendKeys(itemUnit);//this here just to find the input box
        Thread.sleep(2000);
        //we will be back here
        itemsPage.unitSelect.sendKeys(Keys.ENTER);//this here to find (grams) and click on it to put it in the box
    }

    //we will change the @Given to @And to match it to the step in the (Add_Item.feature) file
    @And("the user clicks on the Save Item button")
    public void the_user_clicks_on_the_save_item_button() throws InterruptedException {
        Thread.sleep(3000);
        itemsPage.saveItemButton.click();
        Thread.sleep(3000);
    }

    @Then("the item should be listed in the items table")
    public void the_item_should_be_listed_in_the_items_table() throws InterruptedException {
        Thread.sleep(4000);
        //to verify the item that we entered "Harry Potter" is listed in the table
        String itemName = "Book";
        Assert.assertTrue(SeleniumUtils.isItemInTable(itemsPage.itemsList , itemName));
        Driver.closeDriver();
    }

}
