package com.testproject.base;

import com.testproject.utils.Driver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;


public abstract class BasePage <T extends BasePage<T>> extends Page {

    public BasePage() {
        PageFactory.initElements(Driver.get(), this);
    }


    public boolean urlContains(String url) {
        boolean contains;
        contains = Driver.get().getCurrentUrl().contains(url);
        return contains;
    }

    public boolean isDisplayed(WebElement element) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(10));
        return wait.until((ExpectedCondition<Boolean>) wd -> element.isDisplayed());
    }

    public static void switchToWindow(String targetTitle) {
        String origin = Driver.get().getWindowHandle();
        for (String handle : Driver.get().getWindowHandles()) {
            Driver.get().switchTo().window(handle);
            if (Driver.get().getTitle().equals(targetTitle)) {
                return;
            }
        }
        Driver.get().switchTo().window(origin);
    }

    public static WebElement waitForVisibility(WebElement element, int timeToWaitSec) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(timeToWaitSec));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void scrollToElement(WebElement element) {
        ((JavascriptExecutor)Driver.get()).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static WebElement waitToBeClickable(WebElement element, int timeToWaitSec) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(timeToWaitSec));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static List<String> getElementsText(List<WebElement> list) {
        List<String> elemText = new ArrayList<>();
        for (WebElement el : list) {
            elemText.add(el.getText());
        }
        return elemText;
    }

    public static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String DatePicker() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String date1 = dateFormat.format(date);
        return date1;
    }

    public static void takeScreenShot() throws IOException {
        String path;
        WebDriver augmentedDriver = new Augmenter().augment(Driver.get());
        File source = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
        path = "./target/screenshots/" + source.getName();
        FileUtils.copyFile(source, new File(path));

    }

    /*New methods for regular use*/
    // ----------------------- WAITS -----------------------
    public void waitForDialogFrameAndSwitch(int seconds) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("bnn-dialog-iframe"));
    }

    public void waitForTextToBePresentInElement(WebElement element, String text, int seconds) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public void waitForVisibility(List<WebElement> elements, int timeToWaitSec) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(timeToWaitSec));
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void waitForInVisibility(WebElement element, int timeToWaitSec) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(timeToWaitSec));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public void waitUntilElementsSizeIsMoreThanZero(List<WebElement> elements) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void waitUntilAlertIsPresent() {
        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public void waitUntilUrlContains(String text, int seconds) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.urlContains(text));
    }

    public void waitForAttributeToBe(WebElement el, String attribute, String value, int seconds) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.attributeToBe(el, attribute, value));
    }

    public void waitUntilPresenceOfNestedElement(int seconds, String option) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//option[.='"+ option +"']")));
    }

    public void waitForPageToLoad() {
        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(25));
        wait.until(d -> ((JavascriptExecutor) Driver.get()).executeScript("return document.readyState").equals("complete"));
    }

    public void waitUntilTitleIs(String expectedTitle) {
        WebDriverWait wait = new WebDriverWait(Driver.get(), Duration.ofSeconds(30));
        wait.until(ExpectedConditions.titleIs(expectedTitle));
    }

    // ----------------------- ALERT -----------------------

    public void handleAlert(String command) {
        Alert alert = Driver.get().switchTo().alert();
        switch (command) {
            case "accept":
                alert.accept();
                break;
            case "dismiss":
                alert.dismiss();
        }
    }

    public boolean isAlertPresent(String command) {
        try {
            handleAlert(command);
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public String getAlertText() {
        waitUntilAlertIsPresent();
        Alert alert = Driver.get().switchTo().alert();
        return alert.getText();
    }

    // ----------------------- OTHER -----------------------

    public String getCurrentDateAndTime(String targetFormat) {
        Date date = Calendar.getInstance().getTime();
        DateFormat formatter = new SimpleDateFormat(targetFormat, Locale.US);
        return formatter.format(date);
    }

    public String getYesterdayDate(String targetFormat) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date date = cal.getTime();
        DateFormat formatter = new SimpleDateFormat(targetFormat, Locale.US);
        return formatter.format(date);
    }

    // ---------------- SWITCHING WINDOWS/FRAMES -------------------

    public void switchToFrame(String nameOrID) {
        Driver.get().switchTo().frame(nameOrID);
    }

    public void switchToDialogFrame() {
        Driver.get().switchTo().frame("bnn-dialog-iframe");
    }

    public void switchToParentFrame() {
        Driver.get().switchTo().defaultContent();
    }

    public void switchToPFrame() {
        Driver.get().switchTo().parentFrame();
    }

}
