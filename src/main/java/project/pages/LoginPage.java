package project.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import project.drivers.WebDriverFactory;
import project.utils.dataReader.PropertyReader;

public class LoginPage {

    private final WebDriverFactory driver;
    private final String loginEndPoint="auth/login";
    public LoginPage(WebDriverFactory driver){
        this.driver = driver;
    }

    //locators
    private final By userNameLocator = By.id("mat-input-0");
    private final By passwordLocator = By.id("mat-input-1");
    private final By loginButtonLocator = By.xpath("(//span[.='Sign In'])[1]");
    private final By dashboardLocator = By.cssSelector("[href='/home/dashboard']");
    private final By loginErrorLocator = By.xpath("//div[.='Username or Password is incorrect']");
    private final By supUserLocator = By.id("mat-mdc-slide-toggle-1-button");


    //actions
    @Step("Navigate to Login Page")
    public LoginPage navigateToLoginPage() {
        driver.browser().navigateToUrl(PropertyReader.getProperty("BASE_WEB") +loginEndPoint);
        return this;
    }

    @Step("Enter Username {username}")
    public LoginPage enterUsername(String username) {
        driver.element().type(userNameLocator,username);
        return this;
    }

    @Step("Enter Password {password}")
    public LoginPage enterPassword(String password) {
        driver.element().type(passwordLocator,password);
        return this;
    }

    @Step("Click Login Button")
    public LoginPage clickLoginButton() {
        driver.element().click(loginButtonLocator);
        return this;
    }

    public LoginPage clickSupUser(){
        driver.element().click(supUserLocator);
        return this;
    }

    //validations

    @Step("Verify Login Success")
    public DashboardPage verifyLoginSuccess(){
        driver.verification().elementVisable(dashboardLocator);
        return new DashboardPage(driver);
    }

    @Step("Verify Login Failure with error message {errorMessage}")
    public LoginPage verifyLoginFailure(String errorMessage){
        String actualErrorMessage = driver.element().getText(loginErrorLocator);
        driver.verification().Equals(actualErrorMessage, errorMessage, "Login Error Message Verification Failed");
        return this;
    }







}
