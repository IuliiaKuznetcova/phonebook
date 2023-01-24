import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TwoSeleniumTest {
    WebDriver driver;
    By emailField = By.cssSelector("[placeholder=\"Email\"]");


    //beforeTest

    @BeforeClass
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setupTest() {
        driver = new ChromeDriver();
        //  driver.get("https://www.google.ru/");
        // driver.navigate().to("https://www.google.ru/");  другой вариант вызова
        driver.get("http://phonebook.telran-edu.de:8080/");
        // driver.manage().window().getSize();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); //время ожидания


    }

    @Test
    public void registrNewUser() {
        String userData = "test3@gmail";
        driver.findElement(By.id("login-form")).isDisplayed();
        driver.findElement(By.cssSelector("[href=\"/user/registration\"]")).click();
        driver.findElement(By.id("registration-form")).isDisplayed();
        fillField(userData, emailField);
        fillField(userData, By.cssSelector("[placeholder=\"Password\"]"));
        //   driver.findElement(By.cssSelector("[placeholder=\"Password\"]")).sendKeys(userData);
        driver.findElement(By.name("confirm-password")).sendKeys(userData);
        // driver.findElement(By.xpath("//*[@name=\"confirm-password\"]")).sendKeys(userData);
        driver.findElement(By.xpath("//*[@type=\"submit\"]")).click();


        //driver.findElement(By.cssSelector("[placeholder=\"Email\"]")).sendKeys(userData);

    }

    private void fillField(String userData, By cssSelector) {
        driver.findElement(cssSelector).click();
        driver.findElement(cssSelector).sendKeys(userData);
    }

    //afterTest
    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(1000);
        if (driver != null) {
            driver.quit();          // закроет браузер
            //  driver.close();  закроет вкладку
        }
    }

}
