package ru.stqu.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqu.pft.addressbook.model.UserData;

public class UserDeletionTests extends TestBase{


  @Test
  public void testUserDeletion() throws Exception {
    int before = app.getContactHelper().getContactCount();
    if (! app.getContactHelper().isThereAUser()) {
      app.getContactHelper().createUser(new UserData("Dmitry", "Zagumenny", "Saint_Petersburg", "+7123456789", "qa@java.com", "test1", false));
    }
    app.getContactHelper().selectUser();
    app.getContactHelper().deleteSelectedUser();
    app.closeAlert();
    app.getContactHelper().returnToHomePage1();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before - 1);
  }

}
