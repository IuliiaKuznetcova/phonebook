package e2e.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CommonHelpers {

    WebDriver driver;

    public CommonHelpers(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnVisibleElement(By locator) {
        Assert.assertTrue(isElementPresent(locator));
        driver.findElement(locator).click();
    }

 /*   public void fillField(String userData, By cssSelector) {
        driver.findElement(cssSelector).click();
         driver.findElement(cssSelector).sendKeys(userData);
    }*/

    public void openDialog(By locator) throws InterruptedException {
        clickOnVisibleElement(locator);
        Thread.sleep(1000);
        Assert.assertTrue(isElementPresent(By.xpath("//*[@role='dialog']")));
    }

    public void fillField(String userData, By locator) {
        driver.findElement(locator).click();
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(userData);
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

    /*public boolean isElementClickable(By by) {
        try {
            driver.findElement(by).click();
            return true;
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            return false;
        }
    }*/

    public void checkItemText(By locator, String expectedErrorMessage, String err) {
        String actualErrorMessage = driver.findElement(locator).getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, err);
    }
}
