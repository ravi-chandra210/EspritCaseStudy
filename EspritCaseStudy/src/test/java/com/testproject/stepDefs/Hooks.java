package com.testproject.stepDefs;


import com.testproject.base.BasePage;
import com.testproject.utils.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks {
/********************* This class is executed contains before and after annotations which are executed before and after every test ********************/
    private static final Logger LOG = LoggerFactory.getLogger(BasePage.class.getName());

    @Before
    public void setUp(Scenario scenario) {
        LOG.info("Setting up browser");
        LOG.info("Executing scenario: " + scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                final byte[] screenshot = ((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Scenario fail");
            } catch (Exception e) {
                e.printStackTrace();
                LOG.info("Failed scenario: " + scenario.getName());
            }
        }
        LOG.info("Closing browser");
        Driver.get().manage().deleteAllCookies();
        Driver.closeDriver();
    }
}
