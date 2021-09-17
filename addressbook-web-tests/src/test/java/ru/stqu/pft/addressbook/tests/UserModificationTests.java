package ru.stqu.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqu.pft.addressbook.model.UserData;

import java.util.Comparator;
import java.util.List;

public class UserModificationTests extends TestBase {


  @Test
  public void testUserModification() {
    if (! app.getContactHelper().isThereAUser()) {
      app.getContactHelper().createUser(new UserData("Dmitry", "Zagumenny", "Saint_Petersburg", "+7123456789", "qa@java.com", "test1", false));
    }
    List<UserData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectUser(before.size() - 1);
    app.getContactHelper().initUserModification(before.size() - 1);
    UserData user = new UserData(before.get(before.size() - 1).getId(), "Dmitry", "Zagumenny", "Saint_Petersburg", "+7123456789", "qa@java.com", null, false);
    app.getContactHelper().fillUserForm(user, false);
    app.getContactHelper().submitUserModification();
    app.getContactHelper().returnToHomePage1();
    List<UserData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(user);
    Comparator<? super UserData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }
}