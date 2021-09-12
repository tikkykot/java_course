package ru.stqu.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqu.pft.addressbook.model.UserData;

public class UserModificationTests extends TestBase {


  @Test
  public void testUserModification() {
    int before = app.getContactHelper().getContactCount();
    if (! app.getContactHelper().isThereAUser()) {
      app.getContactHelper().createUser(new UserData("Dmitry", "Zagumenny", "Saint_Petersburg", "+7123456789", "qa@java.com", "test1", false));
    }
    app.getContactHelper().selectUser(before - 1);
    app.getContactHelper().initUserModification();
    app.getContactHelper().fillUserForm(new UserData("Dmitry", "Zagumenny", "Saint_Petersburg", "+7123456789", "qa@java.com", null, false), false);
    app.getContactHelper().submitUserModification();
    app.getContactHelper().returnToHomePage1();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before);

  }
}