package ru.stqu.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqu.pft.addressbook.model.UserData;

public class UserModificationTests extends TestBase {


  @Test
  public void testUserModification() {
    if (! app.getContactHelper().isThereAUser()) {
      app.getContactHelper().createUser(new UserData("Dmitry", "Zagumenny", "Saint_Petersburg", "+7123456789", "qa@java.com", null, false));
    }
    app.getContactHelper().selectUser();
    app.getContactHelper().initUserModification();
    app.getContactHelper().fillUserForm(new UserData("Dmitry", "Zagumenny", "Saint_Petersburg", "+7123456789", "qa@java.com", null, false), false);
    app.getContactHelper().submitUserModification();
    app.getContactHelper().returnToHomePage1();

  }
}