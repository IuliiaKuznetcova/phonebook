import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class RegistrNewUserTest extends TestBase {


    By loginForm = By.id("login-form");
    By userRegistrationLink = By.cssSelector("[href=\"/user/registration\"]");
    By registrationForm = By.cssSelector("registration-form");
    By emailField = By.cssSelector("[placeholder=\"Email\"]");
    By passworfField = By.cssSelector("[placeholder=\"Password\"]");
    By ConfirmPasswordField = By.cssSelector("[ng-reflect-name=\"confirm_password\"]");
    By loginButtom = By.xpath("//*[@type=\"submit\"]");
    Faker faker = new Faker();               //randomEmail


    @Test
    public void registerNewUser() {
        String userData = faker.internet().emailAddress();

        driver.findElement(By.id("login-form")).isDisplayed();
        driver.findElement(By.cssSelector("[href=\"/user/registration\"]")).click();
        driver.findElement(By.id("registration-form")).isDisplayed();
        fillField(userData, emailField);
        fillField(userData, By.cssSelector("[placeholder=\"Password\"]"));
        fillField(userData, By.cssSelector("[ng-reflect-name=\"confirm_password\"]"));
        driver.findElement(By.xpath("//*[@type=\"submit\"]")).click();
    }
}


