package ru.stqu.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqu.pft.addressbook.model.UserData;

import java.util.List;

public class UserDeletionTests extends TestBase{


  @Test
  public void testUserDeletion() throws Exception {
    if (! app.getContactHelper().isThereAUser()) {
      app.getContactHelper().createUser(new UserData("Dmitry", "Zagumenny", "Saint_Petersburg", "+7123456789", "qa@java.com", "test1", false));
    }
    List<UserData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectUser(before.size() - 1);
    app.getContactHelper().deleteSelectedUser();
    app.closeAlert();
    app.getContactHelper().returnToHomePage1();
    List<UserData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() - 1);
  }

}
