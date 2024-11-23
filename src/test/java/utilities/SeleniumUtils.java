package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SeleniumUtils {
    //we create this class to keep all the utilities method which means all the methods that could be used for any
    //repeated steps in anywhere or any other class we need too.
    //we make sure that we create these methods as (static) so we can access them in the other classes by its class
    //which means when i call the method in the other class i will put this first (SeleniumUtils.) and all the methods
    //we have here will pop up and then we can choose any of them.

    //now when you create a method here you need to leave a comment on it so everyone else if they come to use these
    //methods they know what they are about, so put this (/** and ENTER) intellij will add the rest like the parameters
    //and then you could add any notes you want also.

    /**
     * This method will select from a Drop down using selectByValue from Selenium
     * @param element the Select element with select tag from the html
     * @param valueToBeSelected the option value that we want to select
     */
    public static void selectByValueFromDropDown(WebElement element, String valueToBeSelected){
        Select select = new Select(element);
        select.selectByValue(valueToBeSelected);
    }


    /**
     * This method will drag and drop an element from a source to a target element using Action class
     * if selenium
     * @param driver the driver object that was instantiated
     * @param sourceElement the source element that we want to drag
     * @param targetElement the target element that we want to drag the source element to
     */
    public static void dragAndDrop(WebDriver driver , WebElement sourceElement, WebElement targetElement ){
        Actions actions = new Actions(driver);
        actions.dragAndDrop(sourceElement, targetElement).build().perform();
    }


    /**
     * This method is a JavascriptExecutor used to send Key
     * @param inputString
     * @param element
     */
    public static void sendKeysUsingJavaScriptExecutor(String inputString , WebElement element){
        JavascriptExecutor js  = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].setAttribute('value', '" + inputString +"')", element);
    }


    /**
     * This is an action class method used to send key
     * @param element
     * @param input
     */
    public static void sendkeysWithActionsClass(WebElement element , String input){
        Actions actions = new Actions(Driver.getDriver());
        actions.sendKeys(element , input).build().perform();
    }


    /**
     * This method will check if the item is in the table
     * @param elementList the list of elements that are in the table
     * @param itemNameToSearchFor the item name that we are looking for
     * @return true if the item is in the table, false if the item is not in the table
     */
    public static boolean isItemInTable(List<WebElement> elementList , String itemNameToSearchFor){
        //loop through the list of elements
        for(WebElement item : elementList){
            //if the item text is equal to the item name that we are looking for
            if(item.getText().equals(itemNameToSearchFor)){
                //return true if the item is in the table
                return true;
            }
        }
        //if the item is not in the table
        return  false;
    }


}
