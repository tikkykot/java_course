package ru.stqu.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqu.pft.addressbook.model.GroupData;
import ru.stqu.pft.addressbook.model.UserData;

import java.util.List;

public class UserCreationTests extends TestBase{


  @Test
  public void testUserCreation() throws Exception {
    List<UserData> before = app.getContactHelper().getContactList();
    app.getContactHelper().createUser(new UserData("Dmitry", "Zagumenny", "Saint_Petersburg", "+7123456789", "qa@java.com", "test1", true));
    app.returnToHomePage();
    List<UserData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);
  }

}
