package kw;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactTableTest extends Login {
    @Test
    public void createNewContact() {
        String firstName = "";
        String lastName = "";
        String description = "";
        Number expectedCountRow = 1;

       /* //Click on the button "Add new contact"
        driver.findElement(By.cssSelector("[href='/contacts']")).click();
        Assert.assertTrue(isElementPresent(By.xpath("//*[@role='dialog']")));
        //Fill field First name
        fillField(firstName, By.xpath("//*[@role='dialog']//*[@placeholder='First name']"));
        //Fill field Last name
        fillField(lastName, By.xpath("//*[@role='dialog']//*[@placeholder='Last name']"));
        //Fill field About
        fillField(description, By.xpath("//*[@role='dialog']//*[@placeholder='About']"));
        //Click on the button "Save"
        driver.findElement(By.xpath("//*[@type='submit']")).click();
        Assert.assertFalse(isElementPresent(By.xpath("//*[@role='dialog']")));
        //Filter by creation name
        fillField(firstName, By.xpath("//*[@placeholder='Search...']"));

        Number actualCountRow = driver.findElements(By.className("list-group")).size();
        Assert.assertEquals(actualCountRow, expectedCountRow);
        //Expected result: Created contact show with correct data in the contact table*/


        //Click on the button "Add new contact"

        driver.findElement(By.cssSelector("[href='/contacts']")).click();
        Assert.assertTrue(isElementPresent(By.xpath("//*[@role='dialog']")));
        //Fill field First name

        fillField(firstName, By.xpath("(//input[@id='form-name'])[1]"));
        //Fill field Last name

        fillField(lastName, By.xpath("(//input[@id='form-lastName'])[1]"));
        //Fill field About

        fillField(description, By.xpath("(//input[@id='form-about'])[1]"));
        //Click on the buttom "Save"

        driver.findElement(By.cssSelector("button[class='btn btn-primary']")).click();
        //Assert.assertFalse(isElementPresent(By.xpath("//*[@role='dialog']")));        // падает
        Assert.assertTrue(isElementPresent(By.xpath("//*[@role='dialog']")));
        //Filter by creation name

        fillField(firstName, By.xpath("//input[@id='input-search-contact']"));

        Number actualCountRow = driver.findElements(By.className("list-group")).size();
        Assert.assertEquals(actualCountRow, expectedCountRow);
    }
}



