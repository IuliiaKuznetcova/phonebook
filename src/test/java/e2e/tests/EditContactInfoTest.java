package e2e.tests;

import e2e.TestBase;
import e2e.utils.DataProviders;
import org.testng.annotations.Test;


public class EditContactInfoTest extends TestBase {
    //логин, фильтрация, поиск, войти, изменить (добавить ластнейм и дескриршн),
    //нажать сохранить, прочитать изменения и верунться на Contacts

    @Test(dataProvider = "changeLastNameAndDescription", dataProviderClass = DataProviders.class)

    public void editContactInfo(String lastName, String description) throws InterruptedException {

        String firstName = "3302443e-b935-49ba-924b-1697263c87b6";

        app.getLogin().login();
        app.getEditContact().changeLanguage();
        app.getEditContact().goToContactPageAndFillFilterField(firstName);
        app.getEditContact().checkCountRows(1);
        //  app.getContact().checkCountRows(1); // если таких имен несколько, нужно указать количество строчек
        // и локатор нужно будет заменить на уникальный, например (By.cssSelector("[link='/contacts/3607']"))
        app.getEditContact().openContact();
        app.getEditContact().openEditForm();
        app.getEditContact().editeLastNameAndDescription(lastName, description);
        app.getEditContact().saveEditedContact();
        app.getEditContact().checkFieldsOnContactInfo(firstName, lastName, description);

    }
}
