package com.testproject.stepDefs;

import com.testproject.base.BasePage;
import com.testproject.cucumber.ScenarioContext;
import com.testproject.pages.EspritElements;
import com.testproject.pages.EspritMethods;
import com.testproject.utils.Driver;
import com.testproject.utils.Environment;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.io.IOException;
import java.time.Duration;

import static com.testproject.pages.EspritMethods.LOG;


public class EspritStepDef {

    /******************  This class is to map scenarios to actual selenium code ******************/
    private final EspritElements EspritPage;
    private final ScenarioContext context;
    private final EspritMethods AllMethods;
    private final BasePage AllFunctions;

    public EspritStepDef(ScenarioContext context, EspritElements EspritPage, EspritMethods AllMethods, BasePage AllFunctions) {
        this.context = context;
        this.EspritPage = EspritPage;
        this.AllMethods = AllMethods;
        this.AllFunctions = AllFunctions;
    }

    /***************Methods for Background**************/
    @When("User navigates to Esprit URL")
    public void user_navigates_to_esprit_url() {
        Driver.get().get(Environment.URL);
        Driver.get().manage().window().maximize();
        EspritPage.waitForPageToLoad();
    }
    @When("User clicks on Login button")
    public void user_clicks_on_login_button() {
        EspritPage.LoginTitle.click();
    }
    @Then("User enters valid {string} and {string}")
    public void user_enters_valid_and(String string, String string2) {
        Driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        EspritPage.username.sendKeys(Environment.USERNAME);
        EspritPage.pwd.sendKeys(Environment.PASSWORD);
    }
    @Then("User clicks on Submit button")
    public void user_clicks_on_submit_button() {
        EspritPage.LoginButton.click();
    }

    /*Test01 >> verify user is able login to webShop*/
    @Given("User clicks on Logo")
    public void user_clicks_on_logo() {
        EspritPage.EspritLogo.click();
    }
    @Then("User navigates to Women collection")
    public void user_navigates_to_women_collection() {
        EspritPage.WomenSelection.click();
    }

}
