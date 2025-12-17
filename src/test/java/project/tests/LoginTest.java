package project.tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import project.drivers.WebDriverFactory;
import project.pages.LoginPage;

public class LoginTest extends BaseTest{


    @Test(description = "Validates the login functionality with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Story("User should be able to access the dashboard with valid credentials")
    public void validLoginTest() {
        new LoginPage(driver).navigateToLoginPage()
                .enterUsername(testData.getJsonData("validUsername"))
                .enterPassword(testData.getJsonData("validPassword"))
                .clickLoginButton()
                .verifyLoginSuccess();
    }

    @Test(description = "Validates the login functionality with invalid credentials")
    @Severity(SeverityLevel.NORMAL)
    @Story("User should see an error message when entering invalid credentials")
    public void invalidLoginTest() {
        new LoginPage(driver).navigateToLoginPage()
                .enterUsername("invalidUsername")
                .enterPassword("invalidPassword")
                .clickLoginButton()
                .verifyLoginFailure(testData.getJsonData("expectedError"));
    }

    @Test(description = "Validates the login functionality with valid sup user credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Sup User should be able to access the dashboard with valid credentials")
    public void validSupUserLoginTest() {
        new LoginPage(driver).navigateToLoginPage()
                .enterUsername(testData.getJsonData("validSupUser"))
                .enterPassword(testData.getJsonData("validSupPassword"))
                .clickSupUser()
                .clickLoginButton()
                .verifyLoginSuccess();
    }

    @Test(description = "Validates the login functionality with invalid sup user credentials")
    @Severity(SeverityLevel.NORMAL)
    @Story("Sup User should see an error message when entering invalid credentials")
    public void invalidSupUserLoginTest() {
        new LoginPage(driver).navigateToLoginPage()
                .enterUsername(testData.getJsonData("invalidSupUser"))
                .enterPassword(testData.getJsonData("invalidSupPassword"))
                .clickSupUser()
                .clickLoginButton()
                .verifyLoginFailure(testData.getJsonData("expectedError"));
    }



    @BeforeMethod
    public void setUp() {
        driver=new WebDriverFactory();

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }


}
