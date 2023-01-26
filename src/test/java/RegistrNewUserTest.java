import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrNewUserTest extends TestBase {

    By loginForm = By.id("login-form");
    By userRegistrationLink = By.cssSelector("[href=\"/user/registration\"]");
    By registrationForm = By.cssSelector("registration-form");
    By emailField = By.cssSelector("[placeholder=\"Email\"]");
    By passwordField = By.cssSelector("[placeholder=\"Password\"]");
    By confirmPassField = By.cssSelector("[ng-reflect-name=\"confirm_password\"]");
    By loginButtom = By.xpath("//*[@type=\"submit\"]");
    By errorMessageBlock = By.id("error-message");
    Faker faker = new Faker();               //randomEmail

    @Test
    public void registerNewUser() {
        // Arrange   подготовительная часть
        String userData = faker.internet().emailAddress();
        String password = faker.internet().password();
        String expectedErrorMessage = "noErrorMsg";
        // String expectedErrorMessage = "noErrorMsge";  //  лишняя буква, тест упадет

        //Act
        driver.findElement(By.id("login-form")).isDisplayed();
        driver.findElement(By.cssSelector("[href=\"/user/registration\"]")).click();
        driver.findElement(By.id("registration-form")).isDisplayed();
        fillField(userData, emailField);
        fillField(password, passwordField);
        fillField(password, confirmPassField);
        // убрать, это дубли того, что выше
       /* fillField(userData, By.cssSelector("[placeholder=\"Password\"]"));
        fillField(userData, By.cssSelector("[ng-reflect-name=\"confirm_password\"]"));*/
        driver.findElement(By.xpath("//*[@type=\"submit\"]")).click();

        //Assert
        String actualErrorMessage = driver.findElement(errorMessageBlock).getText();
        String err = "Actual error message ist not equal expected";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, err);
    }
}


