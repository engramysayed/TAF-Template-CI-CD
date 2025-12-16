package project.validations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import project.utils.Actions.ElementsHandler;
import project.utils.Logs.LogsManager;
import project.utils.WaitHandler;

public abstract class  Assertion {
    protected final WebDriver driver;
    protected final WaitHandler wait;
    protected ElementsHandler element;

    protected Assertion(WebDriver driver){
        this.driver=driver;
        this.wait=new WaitHandler(driver);
        this.element=new ElementsHandler(driver);
    }


    protected abstract void assertTrue(boolean Condition,String message);
    protected abstract void assertFalse(boolean Condition,String message);
    protected abstract void assertEquals(String actual,String expected,String message);


    public void Equals(String actual,String expected,String message){
        assertEquals( actual, expected, message);
    }

    public void elementVisable(By locator){

        try {
            wait.waitForElementToBeVisible(locator);
        } catch (Exception e) {
            LogsManager.error("Couldn't find element "+locator);
        }
        assertTrue(element.findElement(locator).isDisplayed(), "Element not visable");

    }

    public void verifyUrl(String expected){
        String currentUrl=driver.getCurrentUrl();
        assertEquals(currentUrl,expected,"Url Doesn't match "+expected +"  "+ currentUrl);

    }

    public void verifyTitle(String expected){
        String currentUrl=driver.getTitle();
        assertEquals(currentUrl,expected,"Title Doesn't match "+expected +"  "+ currentUrl);

    }
}
