/*
package e2e.tests;

import e2e.TestBase;
import org.testng.annotations.Test;

public class AddingAPhoneNumberToAContact extends TestBase {


    //логин, фильтрация, поиск, войти, изменить (добавить номер телефона),
    //нажать сохранить, прочитать изменения и верунться на Contacts
    @Test

    public void addPhoneNumbers(String firstName, String tellNummer) throws InterruptedException {


        app.getLogin().login();
        app.getEditContact().changeLanguge();
        app.getEditContact().goToContactPageAndFillFilterField(firstName);
        app.getEditContact().checkCountRows(1);
        //  app.getContact().checkCountRows(1); // если таких имен несколько, нужно указать количество строчек
        // и локатор нужно будет заменить на уникальный, например (By.cssSelector("[link='/contacts/3607']"))
        app.getEditContact().openContact();
        app.getEditContact().openEditForm();


        //openFhonesForm

        //  app.getEditContact().eddFhoneNumber();
        app.getEditContact().saveEditedContact();
        // app.getEditContact().checkFieldsOnContactInfo(firstName, lastName, description);

    }


}
*/
