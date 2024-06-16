package stepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import pageObj.LoginPage;
import utility.InitialHelper;
import utility.WaitHelper;

public class LoginStep {
    WebDriver driver;
    LoginPage loginPage;
    WaitHelper helper;

    Logger logger;
    public LoginStep(){
        driver= InitialHelper.iniitializeChromeBrowser ();
        System.out.println ("Driver opened from LoginStep" );
        loginPage = new LoginPage (driver);
        helper = new WaitHelper (driver);
        logger = LogManager.getLogger (this.getClass ());
        logger.info ("Initializing browser");
    }

    @Given ("User enters the URL {string}")
    public void userEntersTheURL(String appURL) {
        logger.info ("Execution of test case begins");
        logger.info ("Docisn Web App URL is eneterd");
        driver.get (appURL);
    }

    @And("User enters email {string} and password {string} and clicks login")
    public void userEntersEmailAndPasswordAndClicksLogin(String username, String password) {
        logger.info ("User enters the username and password");
        logger.info ("Username is: {}", username);
        logger.info ("Password is: {}", password);
        loginPage.setUserName (username);
        loginPage.setPassWord (password);
        loginPage.clickLoginButton ();
    }

    @Then("User lands on provider homepage")
    public void userLandsOnProviderHomepage() {
        logger.info ("User is directed to home page of docisn provider");
        loginPage.verifyHomePage ();
    }

    @When("User clicks on logout")
    public void userClicksOnLogout() {
        logger.info ("User logsout ofdocisn provider web app");
        loginPage.userLogsOut ();
        logger.info ("End of test case");
    }

    @Then("User closes browser")
    public void userClosesBrowser() {
        driver.quit ();
    }

    @And("User is shown error message")
    public void userIsShownErrorMessage() {
        loginPage.verifyErrorMsg ();
    }

    @Then("User clicks on sign-in")
    public void userClicksOnSignIn() {
        loginPage.clickSignIn ();
    }
}
