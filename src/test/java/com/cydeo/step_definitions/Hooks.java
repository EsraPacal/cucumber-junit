package com.cydeo.step_definitions;

import com.cydeo.utilities.Driver;
import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

/*
   In this class we will be able to pass pre & post conditions to each scenario and step
 */
public class Hooks {

    //import from io.cucumber.java not from junit
    @Before(order = 0)  // This method will run first
    public void setupScenario(){
        System.out.println("====Setting uo browser using cucumber @Before");
    }

    @Before(value = "@login", order = 1)  // it will run after order "0"
    public void setupScenarioForLogins() {
        System.out.println("====This will only apply to scenario with @login tag");
    }

    @Before("@db")
    public void setupScenarioForDataBase() {
        System.out.println("====This will only apply to scenario with @db tag");
    }

    @After
    public void teardownScenario(Scenario scenario){

        // scenario.isFailed() --> if scenario fails, this method return TRUE boolean value
        if(scenario.isFailed()){
            //                      down casting
            byte [] screenshot = ((TakesScreenshot)Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot,"image/png", scenario.getName());
        }

        Driver.closeDriver();

        //System.out.println("=====Closing browser using cucumber @After");
        //System.out.println("=====Scenario ended/ Take screenshot if failed!");
    }

    @BeforeStep
    public void setupStep(){
        System.out.println("------applying setup using @BeforeStep");
    }

    @AfterStep
    public void afterStep(){
        System.out.println("------applying tearDown using @AfterStep");
    }

}
