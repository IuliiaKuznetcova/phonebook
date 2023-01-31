package kw;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;

public class RegistrNewUserTest extends TestBase {

    By loginForm = By.id("login-form");
    By userRegistrationLink = By.cssSelector("[href=\"/user/registration\"]");
    By registrationForm = By.id("registration-form");
    By emailField = By.cssSelector("[placeholder=\"Email\"]");
    By passwordField = By.cssSelector("[placeholder=\"Password\"]");
    By confirmPasswordField = By.cssSelector("[ng-reflect-name=\"confirm_password\"]");
    By loginButton = By.xpath("//*[@type=\"submit\"]");
    By errorMessageBlock = By.id("error-message");
    By errorEmailMessageBlock = By.id("email-error-invalid");
    By errorPasswordMaxLengthMessageBlock = By.id("password-error-maxlength");
    Faker faker = new Faker();


    @Test
    public void registrNewUserwithValidData() {
        String userData = faker.internet().emailAddress();
        String password = faker.internet().password();
        String expectedErrorMessage = "noErrorMsg";

        driver.findElement(loginForm).isDisplayed();
        driver.findElement(userRegistrationLink).click();
        driver.findElement(registrationForm).isDisplayed();
        fillField(userData, emailField);
        fillField(password, passwordField);
        fillField(password, confirmPasswordField);
        driver.findElement(loginButton).click();
        String actualErrorMessage = driver.findElement(errorMessageBlock).getText();
        String err = "Actual error message is not equal expected";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, err);
    }


    @Test
    public void registrNewUserwithInvalidData() {
        String userData = faker.internet().password();
        String password = faker.internet().emailAddress();
        String expectedEmailErrorMessage = "Email must be a valid email address.";
        String expectedPasswordErrorMessage = "Password must be no longer than 20 characters.";
//1.43        //String expectedErrorMessage = "noErrorMsg";

        driver.findElement(loginForm).isDisplayed();
        driver.findElement(userRegistrationLink).click();
        driver.findElement(registrationForm).isDisplayed();
        fillField(userData, emailField);
        fillField(password, passwordField);
        fillField(password, confirmPasswordField);
//1.19
//1.26        //Assert.assertFalse(isElementClickable(loginButton));
//1.40        //String actualErrorMessage = driver.findElement(errorMessageBlock).getText();
//1.42
        Assert.assertFalse(isElementPresent(errorMessageBlock));
        String actualEmailErrorMessage = driver.findElement(errorEmailMessageBlock).getText();
        String actualPasswortErrorMessage = driver.findElement(errorPasswordMaxLengthMessageBlock).getText();

        //driver.findElement(errorMessageBlock).getText();
//1.44        //String err = "Actual error message is equal expected";
        String err = "Actual error message is not equal expected";
        Assert.assertEquals(actualEmailErrorMessage, expectedEmailErrorMessage, err);
        Assert.assertEquals(actualPasswortErrorMessage, expectedPasswordErrorMessage, err);
//1.41       //Assert.assertEquals(actualErrorMessage, expectedErrorMessage, err);
    }

    @Test
    public void registerExistingUser() {
        String userData = "test@gmail.com";
        String password = "test@gmail.com";
        String expectedErrorMessage = "Error! User already exists Login now?";

        fillField(userData, emailField);
        fillField(password, passwordField);
        fillField(password, confirmPasswordField);
        driver.findElement(loginButton).click();
        String actualErrorMessage = driver.findElement(errorMessageBlock).getText();

        String err = "Actual error message is not equal expected";
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

}