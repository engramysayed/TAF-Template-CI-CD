package project.utils.Actions;

import org.openqa.selenium.*;
import project.utils.Logs.LogsManager;
import project.utils.WaitHandler;

import java.io.File;

public class ElementsHandler {
    private final WebDriver driver;
    private WaitHandler waitHandler;

    public ElementsHandler(WebDriver driver) {
        this.driver = driver;
        this.waitHandler = new WaitHandler(driver);
    }


    public void scrollToElement(By locator) {
        try {
            ((JavascriptExecutor) driver)
                    .executeScript("""
                            arguments[0].scrollIntoView({behavior: "auto", block: "center",inline: "center"});""", findElement(locator));

        }  catch (Exception e) {
            LogsManager.error("Failed to scroll to element: " + locator);
        }
    }

    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }



    public void uploadFile(By locator,String path){

      try {
          String filePath = System.getProperty("user.dir") + File.separator + path;
          waitHandler.waitForElementToBeVisible(locator);
          findElement(locator).sendKeys(filePath);
          LogsManager.info("File uploaded successfully: " + path);
      }catch (Exception e) {
          LogsManager.error("Failed to upload file: " + path);
          throw new RuntimeException(e);
      }
    }

    public void click(By locator) {
        try {
            scrollToElement(locator);
            waitHandler.waitForElementToBeVisible(locator);
            findElement(locator).click();

        } catch (Exception e) {
            LogsManager.error("Failed to click  element: " + locator);
        }
    }


    public void type(By locator, String text) {
        try {
            waitHandler.waitForElementToBeVisible(locator);
            findElement(locator).clear();
            findElement(locator).sendKeys(text);
            LogsManager.info("Text typed successfully into element: " + locator + " with text: " + text);
        } catch (Exception e) {
            LogsManager.error("Failed to type into element: " + locator);
        }
    }


    public String getText(By locator) {
        try {
            waitHandler.waitForElementToBeVisible(locator);
            LogsManager.info("Text retrieved from element: " + locator);
             return !(findElement(locator).getText()).isEmpty() ? findElement(locator).getText() : null;
        } catch (Exception e) {
            LogsManager.error("Failed to get text from: " + locator);
             return null;
        }
    }
}
