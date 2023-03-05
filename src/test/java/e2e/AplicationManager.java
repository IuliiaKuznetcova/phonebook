package e2e;

import com.google.common.io.Files;
import e2e.helpers.CreateContactHelpers;
import e2e.helpers.EditContactHelpers;
import e2e.helpers.LoginHelpers;
import e2e.helpers.RegisterHelpers;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.concurrent.TimeUnit;

public class AplicationManager {
    public WebDriver driver;
    LoginHelpers login;
    RegisterHelpers register;
    //ContactHelpers contact;
    CreateContactHelpers createContact;


    EditContactHelpers editContact;

    public LoginHelpers getLogin() {
        return login;
    }

    public RegisterHelpers getRegister() {
        return register;
    }

    // public ContactHelpers getContact() {return contact;}

    public CreateContactHelpers getCreateContact() {
        return createContact;
    }

    public EditContactHelpers getEditContact() {
        return editContact;
    }

    //для работы в докере
    public WebDriver remoteDriverSelenoid() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("firefox");
        capabilities.setVersion("109.0");
        capabilities.setCapability("enableVNS", true);
        capabilities.setCapability("enablelog", true);
        driver = new RemoteWebDriver(
                URI.create("http://127.0.0.1:4444/wd/hub").toURL(),
                capabilities);
        return driver;
    }

    protected void init() throws MalformedURLException {
        //для работы локально
        /*WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();*/

        //для работы в докере
        driver = remoteDriverSelenoid();

        driver.get("http://phonebook.telran-edu.de:8080/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        login = new LoginHelpers(driver);
        register = new RegisterHelpers(driver);
        //contact = new ContactHelpers(driver);
        createContact = new CreateContactHelpers(driver);
        editContact = new EditContactHelpers(driver);


    }

    public String takeScreenshot() throws IOException {
        File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File screenshot = new File("reference/screen" + System.currentTimeMillis() + ".png");

        Files.copy(tmp, screenshot);
        return screenshot.getAbsolutePath();
    }

    /*protected void stop() {
        if (driver != null) {
            driver.quit();
        }
    }*/

    protected void stop() {
        driver.quit();
    }
}
