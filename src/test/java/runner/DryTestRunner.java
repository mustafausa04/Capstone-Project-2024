package runner;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

//here we need to tell junit to run the TestRunner class as a cucumber test so we do the below
@RunWith(Cucumber.class)

//here we need to tell the TestRunner, this is the path for the features files to run them.
//(to get the path, right click on Login_saucedemo.feature, copy path/reference, path from content root, so the path will
//be    src/test/resources/features/Login_saucedemo.feature   this will execute the (Login_saucedemo.feature) file only but if we
//need the TestRunner to execute all the files there we just erase the (Login_saucedemo.feature) file and keep the rest
//of the path like this   src/test/resources/features/  to execute all the files there )
//now the second part is where the step definitions are which is in (stepdefinitions) folder so we put it in
//(glue) part without need to get its path because cucmber will complains, so basically what we just did is we
//linked up the (features) files or cucumber files to (stepdefinitions) files which is java files so we can
//execute them and run them.
//now let's run it, on the console left side click on the first scenario (User can login using valid username
//and password) located under (Login feature), we will copy the steps starting from annotation (@Given) all the
//way to the end but make sure that is for that scenario only because on the console left side you will see
//(Login feature) and 2 scenarios under it, so if you click on the (Login feature) you will get the full steps
// on the right side of the console but if you click on any scenario under the (Login feature) you will get the
// steps only for that specific scenario on the right side of the console.
//go to ( Login_StepDef) and paste it there
@CucumberOptions(features = "src/test/resources/features/",//here we will specify to execute
                                               //(User_Access.feature) file only not all files inside(features)
        glue = "stepdefinitions",
        plugin = {"pretty" ,"html:target/primetech-report.html"}, //this is to generate a report for us since it
                                                                //has 2 values then we need to put them in { }
        dryRun = true //this will compare the steps we have in the (User_Access.feature) file to the
                      //(stepdefinitions) files and if nothing matches it will generate steps for you, so dryRun
                      //doesn't execute the code inside the steps that are in the (stepDefinitions) files, it
                      //only generate the non match steps.
)



//This class will allow us to execute and run our feature files/step definitions.
//run this class it will pass and generate a report for you so go to the left menu click on (target) folder
//then double click on (primetech-report.html) it will open a file on the right side go there you see browsers
//icons, choose chrome you see the report.
//what does the dryRunner do??
//it will go to (features) files and check if each file is glued to its step definition file in the (stepdefinitions)
//folder, it will be good but if any of those (features) files has no its step definition file in the
//(stepdefinitions), the dryRunner will give you in the console all the steps that are missing and this what we need
//to copy and go paste them in the step definition file once we created it for that specific feature file.

public class DryTestRunner {

}
