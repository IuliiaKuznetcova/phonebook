import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class FirstSeleniumTest {
    WebDriver driver;

    //beforeTest

    @BeforeClass
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setupTest() {
        driver = new ChromeDriver();
        // driver.get("https://www.google.ru/");
        // driver.navigate().to("https://www.google.ru/");  // другой вариант вызова
        driver.get("http://phonebook.telran-edu.de:8080/");
        // driver.manage().window().getSize(); // открыть окно определенного размера
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS); //время ожидания, таймаут на время прогрузки


    }

    //test

    @Test
    public void loginWithValidData() {
        //  driver.findElement(By.name("email")).sendKeys("test@gmail");    //ввести в поле
        //driver.findElement(By.cssSelector("[placeholder=\"Password\"]")).sendKeys("test@gmail");    //ввести в поле,
        // cssSelector заключается в квадратные скобки
        //  driver.findElement(By.xpath("//input [@name="email"])).sendKeys("test@gmail");
        //driver.findElement(By.name("password")).sendKeys("test@gmail");
        //*[@id="defaultRegisterFormEmail"]
        //   driver.findElement(By.xpath("//*[@id="registration-form"]/div[1]/div[1]/div/input)");
        //  driver.findElement(By.xpath(“//*[@id="registration-form"]/div[1]/div[1]/div/input”);
        driver.findElement(By.xpath("//*[@id=\"registration-form\"]/div[1]/div[1]/div/input"));


        driver.findElement(By.name("password")).sendKeys("test@gmail");


        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("ok");

    }

    @Test
    public void registrNewUser() {
        String userData = "test3@gmail";
        driver.findElement(By.cssSelector("[href=\"/user/registration\"]")).click();
        driver.findElement(By.cssSelector("[placeholder=\"Email\"]")).sendKeys(userData);
        driver.findElement(By.cssSelector("[placeholder=\"Password\"]")).sendKeys(userData);
        driver.findElement(By.name("confirm-password")).sendKeys(userData);
        // driver.findElement(By.xpath("//*[@name=\"confirm-password\"]")).sendKeys(userData);
        driver.findElement(By.xpath("//*[@type=\"submit\"]")).click();


        //driver.findElement(By.cssSelector("[placeholder=\"Email\"]")).sendKeys(userData);

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
