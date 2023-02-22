package e2e.tests;

import com.github.javafaker.Faker;
import e2e.TestBase;
import e2e.helpers.EditContactHelpers;
import org.testng.annotations.Test;

public class CommonContactTest extends TestBase {
    Faker faker = new Faker();

    @Test
    public void userCanCreateEditRemoveContact() throws InterruptedException {

        String firstName = faker.internet().uuid();
        String lastName = faker.internet().uuid();
        String description = faker.lorem().paragraph(1);
        Number expectedCountRow = 1;
        String newfirstName = faker.internet().uuid();
        String newlastName = faker.internet().uuid();
        String newdescription = faker.lorem().paragraph(1);

        //login
        app.getLogin().login();

        //add contact
        app.getCreateContact().changeLanguge();
        app.getCreateContact().openAddNewContactDialog();
        app.getCreateContact().fillAddNewContactForm(firstName, lastName, description);
        app.getCreateContact().saveNewContact();
        app.getCreateContact().checkFieldsOnContactInfo(firstName, lastName, description);

        //edit contact
        app.getEditContact().goToContactPageAndFillFilterField(firstName);
        app.getEditContact().checkCountRows(expectedCountRow);
        app.getEditContact().openContact();
        app.getEditContact().openEditForm();
        //app.getEditContact().editeLastNameAndDescription(newlastName, newdescription);
        app.getEditContact().editeContactInfoForm(newfirstName, newlastName, newdescription);
        app.getEditContact().saveEditedContact();
        app.getEditContact().checkFieldsOnContactInfo(newfirstName, newlastName, newdescription);

        //remove contact
        EditContactHelpers getRemoveContact = app.getEditContact();
        getRemoveContact.goToContactPageAndFillFilterField(newfirstName);
        getRemoveContact.openRemoveContactDialog();
        getRemoveContact.removeContact();
        getRemoveContact.checkCountRows(0);
    }
}
