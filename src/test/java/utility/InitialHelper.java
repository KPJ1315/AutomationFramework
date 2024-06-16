
package utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pageObj.LoginPage;

import java.util.concurrent.TimeUnit;

public class InitialHelper {
    public static WebDriver driver;
    public static Logger logger;


    public static WebDriver iniitializeChromeBrowser(){
        System.setProperty ("webdriver.chrome.driver", "D:\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions ( );
        options.setBinary ("D:\\chrome-win64\\chrome.exe");
        options.addArguments ("--disable-notifications");
        driver = new ChromeDriver (options);
        driver.manage ( ).window ( ).maximize ( );
        driver.manage ( ).timeouts ( ).implicitlyWait (8, TimeUnit.SECONDS);
        return driver;
    }

    public static Logger initializeLogger(Class<? extends LoginPage> aClass){
        logger = LogManager.getLogger (aClass);
        return logger;
    }
}
