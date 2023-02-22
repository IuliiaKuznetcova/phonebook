package e2e.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
        //Filter by creation name
        fillField(firstName, By.xpath("//*[@placeholder='Search...']"));
    }

    public void openContact() {
        // clickOnVisibleElement(By.cssSelector("[link='/contacts/3607']"));
        // clickOnVisibleElement(By.xpath("//*[@id='contacts-list']//*[@class='list-group']"));  // код Леонида для локатора из checkCountRows
        clickOnVisibleElement(By.xpath("//div[@id='contacts-list']"));
    }

    public void openRemoveContactDialog() throws InterruptedException {
        openDialog(By.xpath("//*[@id='contacts-list']//*[@class='list-group-item']/img")); // код Леонида
        // openDialog(By.xpath("//div[@id='contacts-list']//*[@class='list-group-item']/img"));
    }

    public void removeContact() throws InterruptedException {
        // clickOnVisibleElement(By.id("check-box-remove-contact"));
        // clickOnVisibleElement(By.id("submit-remove"));
        clickOnVisibleElement(By.id("check-box-remove-contact"));
        clickOnVisibleElement(By.id("submit-remove"));
        Thread.sleep(1000);
        Assert.assertFalse(isElementPresent(By.xpath("//*[@role='dialog']")));

    }

    public void checkFieldsOnContactInfo(String firstName, String lastName, String description) {
        checkItemText(By.id("contact-first-name"), firstName, "Actual first name ist not equal expected");
        checkItemText(By.id("contact-last-name"), lastName, "Actual last name ist not equal expected");
        //перепроверить, почему тут нет третьего чека
        By.xpath("(//input[@id='form-about'])[1]");
    }

    // считает количество строчек с таким локатором в числовом виде и сравнивает (в нашем примере 1)
    public void checkCountRows(Number expectedCountRow) throws InterruptedException {

        // Number actualCountRow = driver.findElements(By.xpath("//*[@id='contacts-list']//*[@class='list-group']")).size();
        Number actualCountRow = driver.findElements(By.xpath("//div[@id='contacts-list']//div[@class='list-group']")).size();
        Thread.sleep(1000);
        Assert.assertEquals(actualCountRow, expectedCountRow);
    }
}
