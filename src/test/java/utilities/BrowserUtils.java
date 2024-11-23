package utilities;

import org.openqa.selenium.WebDriver;

public class BrowserUtils {

    /**
     * This method will refresh the browser
     * @param driver this is the driver object
     */
    public static void refreshPage(WebDriver driver){
        driver.navigate().refresh();
    }

    /**
     * this method will take you to the previous page
     * @param driver this is the driver object
     */
    public static void backWord(WebDriver driver){
        driver.navigate().back();
    }

    /**
     * this method will take you to the next page
     * @param driver
     */
    public static void forward(WebDriver driver){
        driver.navigate().forward();
    }

}
