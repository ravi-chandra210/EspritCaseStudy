package com.testproject.pages;

import com.testproject.base.BasePage;
import io.cucumber.java.zh_cn.假如;
import org.bouncycastle.jcajce.provider.asymmetric.X509;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EspritElements extends BasePage<EspritElements> {
    /*
    This page contains the locators of webelements
     */
    /******************** Login Page ***********************/
    @FindBy(xpath = "//a[@title='Log in']")
    public WebElement LoginTitle;

    @FindBy(xpath = "//input[@id='login-form-email']")
    public WebElement username;

    @FindBy(xpath = "//input[@id='login-form-password']")
    public WebElement pwd;

    @FindBy(xpath = "//button[@data-action='Login_My_Esprit']")
    public WebElement LoginButton;

    @FindBy(xpath = "//a[@class='dropdown-item js-customer-logout']")
    public WebElement LogoutButton;

    /******************** Category selection elements*********/
    @FindBy(xpath = "//img[@class='logo-home-img']")
    public WebElement EspritLogo;

    @FindBy(xpath = "//img[@alt='SFCC_Square_W']")
    public WebElement WomenSelection;


    /******************** Filters Option Elements *************/
    @FindBy(xpath = "//span[contains(text(),'Filters')]")
    public WebElement FilterButton;

    @FindBy(xpath = "//button[@aria-controls='refinement-size']")
    public WebElement SizeunderFilters;

    @FindBy(xpath = "//span[contains(text(),' XS')]")
    public WebElement SizeSelectionUnderFilters;

    @FindBy(xpath = "//button[@title='BLACK']")
    public WebElement ColorUnderFilters;

    @FindBy(xpath = "//button[@class='close-refinebar btn btn-block btn-primary']")
    public WebElement ResultsUnderFilters;


    /******************** Checkout Page ***********************/


    @FindBy(xpath = "//i[@class='icon minicart-icon icon-074-shopping-bag']")
    public WebElement CartIcon;

    @FindBy(xpath = "(//a[contains(text(),'Checkout')])[1]")
    public WebElement CheckoutButton;

    @FindBy(xpath = "//button[contains(text(),'Continue to payment')]")
    public WebElement PaymentButton;

    @FindBy(xpath = "(//div[@class='esp-payment-card'])[1]")
    public WebElement PaymentOptions;

    @FindBy(xpath = "//button[contains(text(),'Buy now')]")
    public WebElement BuyNowButton;

    @FindBy(xpath = "//input[@id='cardNumber']")
    public WebElement CreditCardNumber;

    @FindBy(xpath = "//input[@id='cardHolder']")
    public WebElement CreditCardHolder;

    @FindBy(xpath = "//input[@id='expiryDate']")
    public WebElement CreditCardExpiry;

    @FindBy(xpath = "//input[@id='cardCode']")
    public WebElement CreditCardCVV;

    @FindBy(xpath = "//label[contains(text(),'Save credit card for the next payment')]")
    public WebElement CreditCardSave;

    @FindBy(xpath = "//input[@id='btn-submit']")
    public WebElement ProductBuyNow;

}
