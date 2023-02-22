package e2e.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CreateContactHelpers extends ContactHelpers {
    public CreateContactHelpers(WebDriver driver) {
        super(driver);
    }

    public void openAddNewContactDialog() throws InterruptedException {
        openDialog(By.cssSelector("[href='/contacts']"));

    }

    public void fillAddNewContactForm(String firstName, String lastName, String description) {
        fillField(firstName, By.id("form-name"));
        fillField(lastName, By.id("form-lastName"));
        fillField(description, By.xpath("//*[@role='dialog']//*[@placeholder='About']"));
    }

    public void saveNewContact() {
        clickOnVisibleElement(By.xpath("//button[@class='btn btn-primary']"));
        setWait().until(ExpectedConditions.invisibilityOfElementLocated
                (By.cssSelector("By.xpath(\"//*[@role='dialog']\"))")));
    }
}
