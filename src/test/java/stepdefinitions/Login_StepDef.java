package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.SettingsPage;
import utilities.Driver;
import utilities.SeleniumUtils;

public class Login_StepDef {
    //this file is used to link the steps in the feature file (Login_Crater.feature) to Java

    //since we have the (Driver) class then we can declare and assign the driver like below, but if we didn't
    //have the (Driver) class we could declare the driver on top then we can assign to anything we want in the
    //rest of steps like what we did in (Login_StepDef) class

    WebDriver driver = Driver.getDriver();
    LoginPage loginPage = new LoginPage();
    SettingsPage settingsPage = new SettingsPage();


    //this is the first scenario1
    //for some reason if you run this at the first time it will not work so you need to login manually to the
    //crater app then come back and run it by automation:
    //user name: entityadmin@primetechschool.com , password: primetech@school
    @Given("user is navigated to Crater login page")
    public void user_is_navigated_to_crater_login_page() {
        driver.manage().window().maximize();//we added this line in the hook so we don't need it here

        driver.get("http://crater.primetech-apps.com/login");

        //add implicit wait after loading the web page if we don't add the implicit wait we will have an error
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));//we added this line in the hook so we don't need it here
    }

    @When("user enters valid username and valid password")
    public void user_enters_valid_username_and_valid_password() throws InterruptedException {
        //these steps are not working so we will try the javascriptexecutor
        //loginPage.emailInput.sendKeys("entityadmin@primetechschool.com");
        //loginPage.passwordInput.sendKeys("primetech@school");

        //using JavaScriptExecutor also not working
        //SeleniumUtils.sendKeysUsingJavaScriptExecutor("entityadmin@primetechschool.com", loginPage.emailInput);
        //Thread.sleep(2000);
        //SeleniumUtils.sendKeysUsingJavaScriptExecutor("primetech@school", loginPage.passwordInput);
        //Thread.sleep(2000);

        //using Action class
        Thread.sleep(3000);
        SeleniumUtils.sendkeysWithActionsClass(loginPage.emailInput ,"entityadmin@primetechschool.com");
        Thread.sleep(1000);
        SeleniumUtils.sendkeysWithActionsClass(loginPage.passwordInput,"primetech@school");
        Thread.sleep(1000);
    }

    //here you need to replace @When with @And, it's cucumber error not us
    @And("user clicks on login button")
    public void user_clicks_on_login_button() throws InterruptedException {
        loginPage.loginButton.click();
        Thread.sleep(2000);//you have to put the waiting time here otherwise it won't see the button
    }

    @Then("user should be logged in successfully")
    public void user_should_be_logged_in_successfully() throws InterruptedException {
        //verify using url that is different from login url
        Thread.sleep(2000);
        String loginUrl = "http://crater.primetech-apps.com/login";
        String afterLoginUrl = driver.getCurrentUrl();
        System.out.println("Current URL after login in is : " + afterLoginUrl);

        //we will use Assert to compare between the 2 urls if they area not equals it means true nothing will
        //show on console
        Assert.assertNotEquals(loginUrl , afterLoginUrl);
        Thread.sleep(2000);
        //here we rae verifying if the (Settings) text is display if yes it means true
        Assert.assertTrue(settingsPage.settingsLabel.isDisplayed());
        Thread.sleep(2000);
        Driver.closeDriver();//we could leave this commented or uncommented because we already added in the
        //(Hooks) class and it will run from there
    }



}
