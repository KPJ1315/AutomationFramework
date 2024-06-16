package utility;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BaseClass {
     @Getter
     public static WebDriver driver;

    public static void setDriver() {
        if(driver==null){
            System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.setBinary("D:\\chrome-win64\\chrome.exe");
            options.addArguments("--remote-allow-origins=*");
            driver=new ChromeDriver (options);
//        options.setBinary("116");
            options.addArguments("--disable-notifications");
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage ().window ().maximize ();
            System.out.println ("Driver opened from baseClass" );
        }
    }
}
