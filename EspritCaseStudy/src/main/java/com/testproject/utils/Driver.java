package com.testproject.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;


public class Driver {

    private Driver() {}

    private static final InheritableThreadLocal<WebDriver> driverPool = new InheritableThreadLocal<>();

    /**
     * TO-DO: add remote drivers with configs to run on Selenium Grid/SauceLabs
     * @return driver
     * enhanced switch - no need for break statements
     */

    public static WebDriver get() {
        String browser = System.getProperty("browser") != null ? System.getProperty("browser") : ConfigurationReader.getProperty("browser");
        if (driverPool.get() == null) {
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driverPool.set(new ChromeDriver());
                    break;
                case "chrome-headless":
                    WebDriverManager.chromedriver().setup();
                    driverPool.set(new ChromeDriver(new ChromeOptions().addArguments("headless").addArguments("window-size=1920,1440")));
                    break;
                case "chrome-remote":
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("headless");
                    chromeOptions.setCapability("platform", "ANY");
                    chromeOptions.addArguments("window-size=1920,1440");
                    chromeOptions.addArguments("--disable-dev-shm-usage");
                    chromeOptions.addArguments("--no-sandbox");
                    driverPool.set(new RemoteWebDriver(chromeOptions));
                    System.out.println("Running REMOTE Chrome");
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver());
                    break;
                case "firefox-headless":
                    WebDriverManager.firefoxdriver().setup();
                    driverPool.set(new FirefoxDriver(new FirefoxOptions().setHeadless(true).addArguments("-width=1920").addArguments("-height=1080")));
                    break;
                case "firefox-remote":
                    FirefoxOptions ffOptions = new FirefoxOptions();
                    ffOptions.addArguments("headless");
                    ffOptions.setCapability("platform", "ANY");
                    ffOptions.addArguments("window-size=1920,1440");
                    driverPool.set(new RemoteWebDriver(ffOptions));
                    System.out.println("Running REMOTE Firefox");
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driverPool.set(new EdgeDriver());
                    break;
                case "edge-headless":
                    WebDriverManager.edgedriver().setup();
                    driverPool.set(new EdgeDriver(new EdgeOptions().addArguments("headless").addArguments("window-size=1920,1440")));
                    break;
                case "safari":
                    WebDriverManager.safaridriver().setup();
                    driverPool.set(new SafariDriver());
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + browser);
            }
        }
        return driverPool.get();
    }

    public static void closeDriver() {
        if (driverPool.get() != null) {
            driverPool.get().quit();
            driverPool.remove();
        }
    }
}
