package e2e.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class ContactHelpers extends CommonHelpers {

    public ContactHelpers(WebDriver driver) {
        super(driver);
    }

    public void changeLanguge() {
        driver.findElement(By.id("langSelect")).click();
        driver.findElement(By.cssSelector("[value='en']")).isDisplayed();
        driver.findElement(By.cssSelector("[value='en']")).click();
    }

    public void goToContactPageAndFillFilterField(String firstName) throws InterruptedException {
        driver.findElement(By.xpath("//a[@class='navbar-brand']//*[name()='svg']")).click();
        fillField(firstName, By.xpath("//*[@placeholder='Search...']"));
    }

    public void openContact() {
        // clickOnVisibleElement(By.cssSelector("[link='/contacts/3607']"));
        clickOnVisibleElement(By.xpath("//div[@id='contacts-list']"));
    }

    public void openRemoveContactDialog() throws InterruptedException {
        openDialog(By.xpath("//div[@id='contacts-list']//*[@class='list-group-item']/img"));
    }


    public void removeContact() {
        clickOnVisibleElement(By.id("check-box-remove-contact"));
        clickOnVisibleElement(By.id("submit-remove"));
        setWait().until(ExpectedConditions.invisibilityOfElementLocated
                (By.xpath("//*[@role='dialog']")));
    }

    public void checkFieldsOnContactInfo(String firstName, String lastName, String description) {
        checkItemText(By.id("contact-first-name"), firstName, "Actual first name ist not equal expected");
        checkItemText(By.id("contact-last-name"), lastName, "Actual last name ist not equal expected");
        By.xpath("(//input[@id='form-about'])[1]");
    }


    public void checkCountRows(Number expectedCountRow) throws InterruptedException {
        Number actualCountRow = driver.findElements(By.xpath("//div[@id='contacts-list']//div[@class='list-group']")).size();
        Assert.assertEquals(actualCountRow, expectedCountRow);
    }
}
