package kw;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;

public class ChangeLangue extends Login {

    @BeforeMethod
    public void changeLanguge() {
        driver.findElement(By.id("langSelect")).click();
        driver.findElement(By.cssSelector("[value='en']")).isDisplayed();
        driver.findElement(By.cssSelector("[value='en']")).click();

    }
}
