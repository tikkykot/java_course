package ru.stqu.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqu.pft.addressbook.model.UserData;

public class UserModificationTests extends TestBase {


  @Test
  public void testUserModification() {
    app.getContactHelper().selectUser();
    app.getContactHelper().initUserModification();
    app.getContactHelper().fillUserForm(new UserData("Dmitry", "Zagumenny", "Saint_Petersburg", "+7123456789", "qa@java.com"));
    app.getContactHelper().submitUserModification();
    app.getContactHelper().returnToHomePage1();

  }
}