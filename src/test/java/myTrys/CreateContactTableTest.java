package myTrys;

import com.github.javafaker.Faker;
import e2e.MainPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CreateContactTableTest extends MainPage {

    Faker faker = new Faker();

    @DataProvider
    public Iterator<Object[]> newContact() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"Jul", "Kuz", "Ich"});
        list.add(new Object[]{"Jull", "Kuzz", "Ichh"});
        list.add(new Object[]{"Julll", "Kuzzz", "Ichhh"});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> newContactWithCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/data.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(",");
            list.add(new Object[]{split[0], split[1], split[2]});
            line = reader.readLine();
        }
        return list.iterator();
    }

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


    @Test(dataProvider = "newContact")
    public void createNewContact(String firstName, String lastName, String description) throws InterruptedException {
        //  for (int i = 0; i < 2; i++) {

       /* String firstName = faker.internet().uuid();
        String lastName = faker.internet().uuid();
        String description = faker.internet().uuid();*/
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



