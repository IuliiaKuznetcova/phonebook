package kw;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class TestBase {

    WebDriver driver;
    final static Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeClass
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        logger.info("Setup chrome driver");
    }

    @BeforeMethod
    public void setupTest() {
        driver = new ChromeDriver();
        driver.get("http://phonebook.telran-edu.de:8080/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        // logger.info("Running a test: prepare");
        logger.info("Start test");
    }

    public void fillField(String userData, By cssSelector) {
        driver.findElement(cssSelector).click();
        driver.findElement(cssSelector).sendKeys(userData);
    }

    public void checkItemText(By locator, String expectedErrorMessage, String err) {
        String actualErrorMessage = driver.findElement(locator).getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, err);
    }

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    //1.18
    //проврка клика
    public boolean isElementClickable(By by) {
        try {
            driver.findElement(by).click();
            return true;
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(1000);
        if (driver != null) {
            driver.quit();
        }
        logger.info("Stop test");
    }
}
