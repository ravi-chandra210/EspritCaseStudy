package com.testproject.base;

import com.testproject.utils.Driver;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Page {


    public Page() {
        PageFactory.initElements(Driver.get(), this);
    }
    public void refreshPage() {
        Driver.get().navigate().refresh();
    }

    public void navigateBack() {
        Driver.get().navigate().back();
    }

    /**
     * Wait till DOM is loaded
     */
    public void waitForPageToLoad() {
        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(10));
        wait.until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }
}
