import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class TestBase {
    WebDriver driver;

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

    public void fillField(String userData, By locator) {        //заполнение филдов
        driver.findElement(locator).click();
        driver.findElement(locator).sendKeys(userData);
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(1000);
        if (driver != null) {
            driver.quit();          // закроет браузер
            //  driver.close();  закроет вкладку
        }
    }

}
