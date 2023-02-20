package e2e.helpers;

import e2e.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;

public class CommomHelpers extends TestBase {


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

    public boolean isElementClickable(By by) {
        try {
            driver.findElement(by).click();
            return true;
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            return false;
        }
    }


}
