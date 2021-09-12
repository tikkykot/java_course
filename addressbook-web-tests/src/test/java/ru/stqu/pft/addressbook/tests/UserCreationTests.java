package ru.stqu.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqu.pft.addressbook.model.UserData;

public class UserCreationTests extends TestBase{


  @Test
  public void testUserCreation() throws Exception {
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().createUser(new UserData("Dmitry", "Zagumenny", "Saint_Petersburg", "+7123456789", "qa@java.com", "test1", true));
    app.returnToHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before + 1);
  }

}
