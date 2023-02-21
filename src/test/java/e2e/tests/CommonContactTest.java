package e2e.tests;

import com.github.javafaker.Faker;
import e2e.TestBase;
import org.testng.annotations.Test;

public class CommonContactTest extends TestBase {
    Faker faker = new Faker();

    @Test
    public void userCanCreateEditRemoveContact() throws InterruptedException {

        String firstName = faker.internet().uuid();
        String lastName = faker.internet().uuid();
        String description = faker.lorem().paragraph(1);
        Number expectedCountRow = 1;

        app.getLogin().login();
        app.getCreateContact().changeLanguge();
        app.getCreateContact().openAddNewContactDialog();
        app.getCreateContact().fillAddNewContactForm(firstName, lastName, description);
        app.getCreateContact().saveNewContact();
        app.getCreateContact().checkFieldsOnContactInfo(firstName, lastName, description);
        

        // String newfirstName = faker.internet().uuid();
        String newlastName = faker.internet().uuid();
        String newdescription = faker.lorem().paragraph(1);


        app.getEditContact().goToContactPageAndFillFilterField(firstName);
        app.getEditContact().checkCountRows(expectedCountRow);
        app.getEditContact().openContact();
        app.getEditContact().openEditForm();
        app.getEditContact().editeLastNameAndDescription(newlastName, newdescription);
        app.getEditContact().saveEditedContact();
        app.getEditContact().checkFieldsOnContactInfo(firstName, newlastName, newdescription);

    }

}
