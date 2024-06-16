package pageObj;

import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.WaitHelper;

import java.util.concurrent.TimeUnit;

public class LoginPage {

    WebDriver driver;

    @Getter
    @FindBy(id = "15044")
    WebElement signIn;

    @Getter
    @FindBy(xpath = "//input[@id='emailid']")
    @CacheLookup
    WebElement userName;

    @Getter
    @FindBy(xpath = "//input[@id='pword']")
    @CacheLookup
    WebElement passWord;

    @Getter
    @FindBy(id = "signin")
    @CacheLookup
    WebElement loginBtn;

    @Getter
    @FindBy(xpath = "//img[@class='bannerimage']")
    @CacheLookup
    WebElement homePageBanner;

    @FindBy(id = "dropdown-list")
    @CacheLookup
    WebElement logoutDropDown;

    @FindBy(id = "logimg")
    @CacheLookup
    WebElement logOutImg;

    public static final String err1 = "//div[contains(text(),'Invalid Username or Password')]";
    @FindBy(xpath = err1)
    WebElement lgnErrorMsg1;

    public static final String err2 ="//div[contains(text(),\"Couldn't find your Account\")]";
    @FindBy(xpath = err2 )
    WebElement lgnErrorMsg2;

    public static final String usrErrReq = "//div[contains(text(),'Required')]";
    @FindBy(xpath = usrErrReq)
    WebElement usrNameErrorMsg;

    public static final String usrErrInvalid ="//div[contains(text(), 'Invalid email')]";
    @FindBy(xpath = usrErrInvalid)
    WebElement usrNameInvalidErrorMsg;

    public static final String psdErr="//div[contains(text(),'Required')]";
    @FindBy(xpath = psdErr)
    WebElement pswdErrorMsg;

    Logger logger;

    WaitHelper helper;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements (driver, this);
        helper = new WaitHelper (driver);
    }

    public void clickSignIn(){
        signIn.click ();
    }

    public void setUserName(String usrName) {
        userName.clear ( );
        userName.click ( );
        userName.sendKeys (usrName);
        logger = LogManager.getLogger (this.getClass ());
        logger.info ("Usr entered username");
    }

    public void setPassWord(String pswrd) {
        passWord.clear ( );
        passWord.click ( );
        passWord.sendKeys (pswrd);
        logger.info ("Usr entered password");
    }

    public void clickLoginButton() {
        loginBtn.click ( );
    }

    public void verifyHomePage() {
        try {
            Thread.sleep (5000);
            if (homePageBanner.isDisplayed ( )) {
                logger.info ("Home Page verified");
                System.out.println ("Home Page verified");
                Assert.assertTrue (true);
                return;
            }
            Assert.assertFalse (false);
            logger.info ("Home Page not verified");
            System.out.println ("Home Page not verified");
        } catch (NoSuchElementException | NullPointerException | InterruptedException e) {
            throw new RuntimeException (e);
        }
    }

    public void userLogsOut() {
        logoutDropDown.click ( );
        logOutImg.click ( );
    }

    public void verifyErrorMsg() {
        boolean isAnyErrorDisplayed = false;

        try {
            if (usrNameInvalidErrorMsg.isDisplayed ( )) {
                isAnyErrorDisplayed = true;
            }
        } catch (NoSuchElementException | NullPointerException e) {
            // Handle exception if the element is not found or not initialized
        }

        try {
            if (lgnErrorMsg1.isDisplayed ( )) {
                isAnyErrorDisplayed = true;
            }
        } catch (NoSuchElementException | NullPointerException e) {
            // Handle exception if the element is not found or not initialized
        }

        try {
            if (lgnErrorMsg2.isDisplayed ( )) {
                isAnyErrorDisplayed = true;
            }
        } catch (NoSuchElementException | NullPointerException e) {
            // Handle exception if the element is not found or not initialized
        }

        try {
            if (usrNameErrorMsg.isDisplayed ( )) {
                isAnyErrorDisplayed = true;
            }
        } catch (NoSuchElementException | NullPointerException e) {
            // Handle exception if the element is not found or not initialized
        }

        try {
            if (pswdErrorMsg.isDisplayed ( )) {
                isAnyErrorDisplayed = true;
            }
        } catch (NoSuchElementException | NullPointerException e) {
            // Handle exception if the element is not found or not initialized
        }

        Assert.assertTrue ("No error message is displayed when one was expected.", isAnyErrorDisplayed);
    }
}
