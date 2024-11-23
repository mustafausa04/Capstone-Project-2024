package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Driver {
    //The (singleton pattern) in software engineering is a design pattern that
    //restricts the instantiation of a class to one object only. which means we create a driver and we force to use it
    //by it self not any other driver that's it's called (singleton pattern)
    //What if we keep instantiating new driver for every test ?
    //When you run a suite of tests, it creates multiple browser windows which can cause:
    //1.Increased overhead (resource usage).
    //2.Risk of one test interfering with another due to shared resources or race conditions.a.This type of issue can
    //occur especially when you want to run test in parallel in the future
    //3.Tests failing due to session timeouts or browser performance issues.
    //basically we will instantiate one driver for all sessions or all test cases in this case we don't have to create
    //a driver for each session or each test case imagine if we have 1000 test case and we do regression then we will
    //have all these drivers run at the same time and it will be conflict between them

    //we made the driver static because we will call it inside static method in class SauceDemoLogin and we just
    //declared it here we haven't assigned it
    private static WebDriver driver;

    //here we will make the driver private so no one can create a driver in the class (SauceDemoLogin) in this case we
    //force everyone to use the method getDriver() below
    private Driver(){
    }

    //Static method to get the single instance of WebDriver
    //here we creating a utility method to get the driver, so we will say if the driver is null, which is the driver is
    //null at the beginning because there is no driver at the beginning, then create a driver which
    //is ChromeDriver and start the session and then return it to use it (we have to return it because we put the return
    //type (WebDriver) before the name of the method (getDriver), if we put (void) then we don't need to return anything
    public static WebDriver getDriver(){
        //the first way we learned
        //System.out.println("Getting an instance of the driver");
        //if(driver == null){//here we assigned the driver
        //    driver = new ChromeDriver(); //Instantiate only once

        //the second way
        //String browserType = "firefox"; // we will replace this with the one below
        //we will get the browserType value from the (ConfigurationReader.getPropertyValue()) which will go to
        //(configuration.properties) and grab it.
        String browserType = ConfigurationReader.getPropertyValue("browserType");
        if (driver == null){
            //we could use this below to convert the word to lower case in case if someone made a mistake and put
            //"Chrome" instead of "chrome"
            //switch (browserType.toLowerCase()){
            switch (browserType){ //if the browserType is chrome then make the driver ChromeDriver and break if it
                case "chrome":    //is firefox then switch to it and make the driver FirefoxDriver then break and so
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                case "safari":
                    driver = new SafariDriver();
                    break;
                case "chrome-headless":          //we will create new instance of chromedriver in headless mode
                    System.out.println("Running in headless mode in chrome");
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--headless");
                    driver = new ChromeDriver(chromeOptions);
                    break;
                case "firefox-headless":         //we will create new instance of firefoxdriver in headless mode
                    System.out.println("Running in headless mode in firefox");
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("--headless");
                    driver = new FirefoxDriver(firefoxOptions);
                    break;
                case "sauce-labs"://this is for selenium Grid and all code is paste from the website
                    //   https://app.saucelabs.com/getting-started/guides/selenium
                    //after you log in go step 2 and copy the code and paste here
                    ChromeOptions browserOptions = new ChromeOptions();
                    browserOptions.setPlatformName("Windows 11");
                    browserOptions.setBrowserVersion("latest");
                    Map<String, Object> sauceOptions = new HashMap<>();
                    sauceOptions.put("username", "oauth-mikeusa04-05043");
                    sauceOptions.put("accessKey", "8d1bed93-005c-476f-b1de-0971d6d84f22");//make sure you don't include the code if you push the file to git hub because everyone can access it
                    sauceOptions.put("build", "selenium-build-TLG57");
                    sauceOptions.put("name", "PrimeTech_Test");//name it "PrimeTech_Test"
                    browserOptions.setCapability("sauce:options", sauceOptions);

                    //go to step 3 in that website and copy the first two line and paste them here
                    // start the session
                    URL url = null;
                    try {//this mean i'm going to the url to execute the code if the url is not exist i will throw an exception if there is no problem with url i will execute on remote
                        url = new URL("https://ondemand.us-west-1.saucelabs.com:443/wd/hub");
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                    driver = new RemoteWebDriver(url, browserOptions);
                    break;
                default:                      //if nothing matches then go use what is in the default
                    driver = new ChromeDriver();
                    break;
            }

        }
        return driver;// this is an existing one that is not null ( driver this is alive)
    }

    /**
     * This method will quit the driver and set it to null
     */
    public static void closeDriver() {
        //We Check if the driver still alive then quit the driver and set the driver object to null to make sure it is
        //died
        System.out.println("Closing the driver");
        if (driver != null) {
            driver.quit();
            driver = null;//this is extra step to make sure the driver is not exist
        }
    }


}
