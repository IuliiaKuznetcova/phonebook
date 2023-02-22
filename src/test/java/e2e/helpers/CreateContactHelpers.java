package e2e.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CreateContactHelpers extends ContactHelpers {
    public CreateContactHelpers(WebDriver driver) {
        super(driver);
    }

    public void openAddNewContactDialog() throws InterruptedException {
        openDialog(By.cssSelector("[href='/contacts']"));

    }

    public void fillAddNewContactForm(String firstName, String lastName, String description) {
        //Fill field First name
        fillField(firstName, By.id("form-name"));
        //Fill field Last name
        fillField(lastName, By.id("form-lastName"));
        //Fill field About
        fillField(description, By.xpath("//*[@role='dialog']//*[@placeholder='About']"));
    }

    public void saveNewContact() throws InterruptedException {
        //driver.findElement(By.xpath("//form//button[@type='submit']")).click();
        //driver.findElement(By.xpath("//form//button[@type=\"submit\"]")).click();
        clickOnVisibleElement(By.xpath("//button[@class='btn btn-primary']"));
        Thread.sleep(2000);
        //код Леонида, c ним падает на app.getCreateContact().saveNewContact();
        // Assert.assertTrue(isElementPresent(By.xpath("//*[@role='dialog']")));
        //Assert.assertFalse(isElementPresent(By.xpath("//*[@class=‘modal-content’]")));
        Assert.assertFalse(isElementPresent(By.xpath("//*[@role='dialog']")));
    }


}
