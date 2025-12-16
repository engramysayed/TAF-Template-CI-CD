package project.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import project.utils.dataReader.PropertyReader;

public class ChromeFactory extends AbstractDriver{

    private ChromeOptions getOptions(){
        ChromeOptions options=new ChromeOptions();
        options.addArguments("no-sandbox");
        options.addArguments("disable-dev-shm-usage");
        if(PropertyReader.getProperty("EXECUTION_TYPE").equalsIgnoreCase("HEADLESS")
                ||PropertyReader.getProperty("EXECUTION_TYPE").equalsIgnoreCase("REMOTE"))
        {
            options.addArguments("--headless");
        }
        return options;
    }

    @Override
    public WebDriver createDriver() {
        if(PropertyReader.getProperty("EXECUTION_TYPE").equalsIgnoreCase("HEADLESS")
                ||PropertyReader.getProperty("EXECUTION_TYPE").equalsIgnoreCase("LOCAL")) {
            return new ChromeDriver(getOptions());
        }else{
            //todo to work remote
            return null;
        }

    }
}
