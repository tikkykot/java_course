package ru.stqu.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqu.pft.addressbook.model.UserData;

import java.util.List;

public class UserModificationTests extends TestBase {


  @Test
  public void testUserModification() {
    if (! app.getContactHelper().isThereAUser()) {
      app.getContactHelper().createUser(new UserData("Dmitry", "Zagumenny", "Saint_Petersburg", "+7123456789", "qa@java.com", "test1", false));
    }
    List<UserData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectUser(before.size() - 1);
    app.getContactHelper().initUserModification();
    app.getContactHelper().fillUserForm(new UserData("Dmitry", "Zagumenny", "Saint_Petersburg", "+7123456789", "qa@java.com", null, false), false);
    app.getContactHelper().submitUserModification();
    app.getContactHelper().returnToHomePage1();
    List<UserData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

  }
}