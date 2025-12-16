package project.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import project.utils.dataReader.PropertyReader;

public class EdgeFactory extends AbstractDriver {


    private EdgeOptions getOptions(){
        EdgeOptions options=new EdgeOptions();
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
            return new EdgeDriver(getOptions());
        }else{
            //todo to work remote
            return null;
        }
     }
}

