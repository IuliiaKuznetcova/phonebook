package kw;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CreateContactTableTest extends ChangeLangue {

    Faker faker = new Faker();

    private void openAddNewContactDialog() {
        driver.findElement(By.cssSelector("[href='/contacts']")).click();
        Assert.assertTrue(isElementPresent(By.xpath("//*[@role='dialog']")));
    }

    private void fillAddNewContactForm(String firstName, String lastName, String description) {
        //Fill field First name
        fillField(firstName, By.id("form-name"));
        //Fill field Last name
        fillField(lastName, By.id("form-lastName"));
        //Fill field About
        fillField(description, By.xpath("//*[@role='dialog']//*[@placeholder='About']"));
    }

    private void saveNewContact() throws InterruptedException {
        driver.findElement(By.xpath("//form//button[@type='submit']")).click();
        Thread.sleep(1000);
        Assert.assertFalse(isElementPresent(By.xpath("//*[@class=‘modal-content’]")));
    }

    private void checkFieldsOnContactInfoAfterCreatedContact(String firstName, String lastName, String description) {
        checkItemText(By.id("contact-first-name"), firstName, "Actual first name ist not equal expected");
        checkItemText(By.id("contact-last-name"), lastName, "Actual last name ist not equal expected");
        By.xpath("(//input[@id='form-about'])[1]");
    }

    private void checkCountRows(Number expectedCountRow) {
        Number actualCountRow = driver.findElements(By.xpath("//div[@id='contacts-list']")).size();
        Assert.assertEquals(actualCountRow, expectedCountRow);
    }

    private void goToContactPageAndFillFilterField(String firstName) {
        driver.findElement(By.xpath("//a[@class='navbar-brand']//*[name()='svg']")).click();
        //Filter by creation name
        fillField(firstName, By.xpath("//*[@placeholder='Search...']"));
    }


    @Test
    public void createNewContact() throws InterruptedException {
        //  for (int i = 0; i < 2; i++) {

        String firstName = faker.internet().uuid();
        String lastName = faker.internet().uuid();
        String description = faker.internet().uuid();
        Number expectedCountRow = 1;

        //Click on the button "Add new contact"
        openAddNewContactDialog();
        //Fill field Form
        fillAddNewContactForm(firstName, lastName, description);
        //Click on the button "Save"
        saveNewContact();

        //Checking the correctness of the created contact
        //click "click"
        checkFieldsOnContactInfoAfterCreatedContact(firstName, lastName, description);

        goToContactPageAndFillFilterField(firstName);

        checkCountRows(expectedCountRow);
        //Expected result: Created contact show with correct data in the contact table


        //Click on the button "Add new contact"

        //   }
    }

}



